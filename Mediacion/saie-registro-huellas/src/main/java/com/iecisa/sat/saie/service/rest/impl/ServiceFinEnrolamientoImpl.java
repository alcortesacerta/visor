package com.iecisa.sat.saie.service.rest.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iecisa.sat.saie.audit.AuditManager;
import com.iecisa.sat.saie.audit.dto.AuditEvent;
import com.iecisa.sat.saie.response.ServiceResponse;
import com.iecisa.sat.saie.service.rest.dao.FinEnrolamientoDAO;
import com.iecisa.sat.saie.service.rest.dto.RegistrarFinEnrolamiento;

@Path("/enrollment")
public class ServiceFinEnrolamientoImpl {

	private static final Logger logger = LoggerFactory.getLogger(ServiceFinEnrolamientoImpl.class);
	private SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
	
	@POST
	@Path("/status")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setRegistrarFinEnrolamiento(@Context HttpServletRequest httpRequest, @Context HttpHeaders headers, RegistrarFinEnrolamiento request) {
		logger.info("RQS setRegistrarFinEnrolamiento: " + request);
		ServiceResponse<String> response = new ServiceResponse<String>();
		Date inicioRegEnrola = Calendar.getInstance().getTime();
		String uuid = UUID.randomUUID().toString();
		String ipAddress = httpRequest.getRemoteAddr();
		String macAddress = "00:00:00:00:00";
		String user = "generico";
		
		try {
			String dir = "";
			String eUri = request.geteUri();
			eUri = eUri.replace("file:///", "");
			dir = eUri;
			dir = dir.charAt(dir.length()-1) == '/' ? dir : dir+"/";
			eUri = dir;
			request.seteUri(eUri);
			
			if(request.getPersonType() != null  && request.getPersonType().equals("M")){
				request.setNombre(String.format("%s %s", request.getRazonSocial() != null ? request.getRazonSocial() : "" ,
														 request.getTipoSociedad() != null ? request.getTipoSociedad() : "").trim());
			}else{
				request.setNombre(String.format("%s %s %s",request.getNombre() != null ? request.getNombre() : "", 
									request.getaPaterno() != null ? request.getaPaterno() : "",
									request.getaMaterno() != null ? request.getaMaterno() : "").trim());
			}
			if(request.getRfcRepresentante() == null){
				request.setRfcRepresentante("");
			}
			
			FinEnrolamientoDAO.getInstance().registrarFinEnrolamiento(request,dir);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Date finRegEnrola = Calendar.getInstance().getTime();
			AuditEvent eventoEstatusEnrola = new AuditEvent();
			eventoEstatusEnrola.setUuid(uuid);
			eventoEstatusEnrola.setDateTime(Calendar.getInstance().getTime());
			eventoEstatusEnrola.setUser(user);
			eventoEstatusEnrola.setIpAddress(ipAddress);
			eventoEstatusEnrola.setMacAddress(macAddress);
			String huellasMsg = String.format("EstatusEnrolamiento, %s, %s, %s, %s, %s", 
							    sdt.format(inicioRegEnrola), sdt.format(finRegEnrola), request.getRfc(), request.geteUri(), request.geteStatus());
			eventoEstatusEnrola.setMessage(huellasMsg);
			AuditManager.register(eventoEstatusEnrola);
		}
		
		return Response.ok(response).build();
	}

}
