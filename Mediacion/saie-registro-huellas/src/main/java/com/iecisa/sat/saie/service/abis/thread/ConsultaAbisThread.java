package com.iecisa.sat.saie.service.abis.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iecisa.sat.saie.audit.AuditManager;
import com.iecisa.sat.saie.audit.dto.AuditEvent;
import com.iecisa.sat.saie.service.abis.client.dto.ColaAfisDTO;
import com.iecisa.sat.saie.service.abis.client.dto.SearchAndInsertCandidatesDTO;
import com.iecisa.sat.saie.service.abis.client.dto.ServiceSearchAndInsertResponse;
import com.iecisa.sat.saie.service.rest.dao.FinEnrolamientoDAO;
import com.iecisa.sat.saie.service.rest.dto.ServiceNotificacionRequest;
import com.iecisa.sat.saie.service.rest.dto.ServiceNotificacionResponse;
import com.iecisa.sat.saie.service.rest.impl.NotificacionRestClient;

import feign.FeignException;

public class ConsultaAbisThread implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(ConsultaAbisThread.class);
	private ObjectMapper mapper = null;
	private NotificacionRestClient notifClient;
	private SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
	
	private ColaAfisDTO coladto;
	
	public ConsultaAbisThread(ColaAfisDTO coladto) {
		this.coladto = coladto;
		
		mapper = new ObjectMapper();
		notifClient = new NotificacionRestClient();
	}

	@Override
	public void run() {
		try {
			String uuid = UUID.randomUUID().toString();
			String ipAddress = "10.56.126.139";
			String macAddress = "00:00:00:00:00";
			String user = "generico";
			
			logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " EnviandoAfis");
			
			Date inicioAfis = Calendar.getInstance().getTime();
			Process p = Runtime.getRuntime().exec(String.format("java -jar %s -si %s %s", System.getProperty("ABIS_CLIENT_LOCATION"), coladto.getRfc(), coladto.geteUri()));
			p.waitFor();
			Date finAfis = Calendar.getInstance().getTime();
			
			// Log Evento Registro Huellas en AFIS
			AuditEvent eventoHuellaAfis = new AuditEvent();
			eventoHuellaAfis.setUuid(uuid);
			eventoHuellaAfis.setDateTime(Calendar.getInstance().getTime());
			eventoHuellaAfis.setUser(user);
			eventoHuellaAfis.setIpAddress(ipAddress);
			eventoHuellaAfis.setMacAddress(macAddress);
			String huellasMsg = "HuellasAFIS, "+sdt.format(inicioAfis) + ", " + sdt.format(finAfis) + ", " + coladto.getRfc() + ", epid="+coladto.getePid();
			eventoHuellaAfis.setMessage(huellasMsg);
			AuditManager.register(eventoHuellaAfis);

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			StringBuffer output = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				output.append(line);
			}
			coladto.setEstado(2);
			FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
			logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " RespuestaRecibida "+output.toString());
			
			ServiceSearchAndInsertResponse serviceResponse = mapper.readValue(output.toString(), ServiceSearchAndInsertResponse.class);			
			
			if(serviceResponse != null && serviceResponse.getCode() == -1){
				coladto.setEstado(7);
				logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " ErrorAfis");
				FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
				serviceResponse = null;
			}
			
			if (serviceResponse != null && serviceResponse.getResult() != null) {
				if (serviceResponse.getResult().getHit() != null && serviceResponse.getResult().getHit()) {
					
					try {
						coladto.setEstado(3);
						coladto.setReintento(coladto.getReintento()+1);
						FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
						logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " EsperandoFinEnrolamiento");
						
						logger.info(String.format("PAUSE | Periodo de Espera Reporte DUPLICADO [%s] - [%s]: ", coladto.getRfc(), serviceResponse));
						Thread.sleep(Long.parseLong(System.getProperty("SAIE_ESPERA_ENROLAMIENTO")));
						logger.info(String.format("RESUME | Periodo de Espera Reporte DUPLICADO [%s] - [%s]: ", coladto.getRfc(), serviceResponse));
						List<SearchAndInsertCandidatesDTO> candidatos = serviceResponse.getResult().getCandidatos();
						if (candidatos != null && !candidatos.isEmpty()) {
							// Insertar en BD Duplicados (1 Registro por cada Coincidencia)
							for (SearchAndInsertCandidatesDTO candidato : candidatos){
								if (candidato != null){
									coladto.setEstado(4);
									FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
									logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " GuardandoDuplicado");
									
									String logaux = FinEnrolamientoDAO.getInstance().registrarCasoDuplicado(candidato.getIdentifier(), coladto.getRfc(), candidato.getScore(), coladto.getePid());
									String numCaso = logaux.substring(0, logaux.indexOf(","));
									
									//Log Evento Duplicado en AFIS
									AuditEvent aevent = new AuditEvent();
									aevent.setUuid(uuid);
									aevent.setDateTime(Calendar.getInstance().getTime());
									aevent.setUser(user);
									aevent.setIpAddress(ipAddress);
									aevent.setMacAddress(macAddress);
									String amessage = "DuplicadoEncontrado, "+sdt.format(inicioAfis) + ", " + sdt.format(finAfis) + ", ";
									logaux = logaux.replace("#1#", ""+candidato.getScore());
									amessage += logaux + ", epid="+coladto.getePid();						
									aevent.setMessage(amessage);
									AuditManager.register(aevent);
									
									// SAT/SMTP Enviar Correo
									ServiceNotificacionRequest notifRequest = new ServiceNotificacionRequest();
									notifRequest.setTo(System.getProperty("SAIE_NOTIFICACION_TO"));
									notifRequest.setCc(System.getProperty("SAIE_NOTIFICACION_CC"));
									notifRequest.setFrom(System.getProperty("SAIE_NOTIFICACION_FROM"));
									notifRequest.setSubject("SAIE - Notificacion de Duplicado");
									notifRequest.setMsg(String.format("Se ha detectado un duplicado con la siguiente información:\n Número de caso: %s \n RFC Candidato: %s \n RFC Adjudicante: %s", numCaso, candidato.getIdentifier(), coladto.getRfc()));
									
									ServiceNotificacionResponse notifResponse = notifClient.sendNotification(notifRequest);
									logger.info("Envio Notificacion: " + notifResponse.toString());
									// TODO: Dermalog Insert
									
									coladto.setEstado(8);
									logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " Terminado");
									FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
								}
							}
							
						}
						
					} catch (FeignException e) {
						e.printStackTrace();
						coladto.setEstado(6);
						FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
						logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " ErrorNotificacion");
					} catch (Exception e) {
						e.printStackTrace();
						coladto.setEstado(5);
						FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
						logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " ErrorGuardarDuplicado");
					}
					
				} else {
					coladto.setEstado(8);
					logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " Terminado");
					FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
					//FinEnrolamientoDAO.getInstance().desencolarHuellasAfis(coladto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			coladto.setEstado(7);
			logger.info("QueueAfisID:"+coladto.getId()+" RFC: "+coladto.getRfc()+" QueueAfisStatus: "+coladto.getEstado()+ " ErrorAfis");
			FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
		}
		
	}

}
