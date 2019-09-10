package com.iecisa.sat.saie.service.rest.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iecisa.sat.saie.audit.AuditManager;
import com.iecisa.sat.saie.audit.dto.AuditEvent;
import com.iecisa.sat.saie.response.ServiceResponse;
import com.iecisa.sat.saie.service.abis.client.dto.ServiceVerifyUnknownPositionResponse;
import com.iecisa.sat.saie.service.abis.client.dto.VerifyUnknownPositionMatchInfoDTO;
import com.iecisa.sat.saie.service.rest.dto.HitResult;
import com.iecisa.sat.saie.service.rest.dto.Verificacion1a1DTO;
import com.iecisa.sat.saie.service.rest.dto.Verify1a1Result;

@Path("/verify")
public class ServiceVerificacionImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceVerificacionImpl.class);
	private SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
	
	private ObjectMapper mapper = null;
	
	public ServiceVerificacionImpl() {
		mapper = new ObjectMapper();
	}
	
	@POST
	@Path("/1to1")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doVerify1to1(@Context HttpServletRequest httpRequest, Verificacion1a1DTO request){
		logger.info("RQS doVerify1to1: " + request);
		Date inicioVerHuellas = Calendar.getInstance().getTime();
		ServiceResponse<Verify1a1Result> response = new ServiceResponse<Verify1a1Result>();
		String uuid = UUID.randomUUID().toString();
		String ipAddress = httpRequest.getRemoteAddr();
		String macAddress = "00:00:00:00:00";
		String user = "generico";
		
		try {
			if (request != null) {
				String rfc = request.getRfc();
				byte[] fingerData = request.getFingerData();
				File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".wsq", null);
				FileOutputStream fos = new FileOutputStream(tempFile);
				fos.write(fingerData);
				fos.close();
				
				logger.info("Temp Wsq File: " + tempFile.getAbsolutePath());
				
				Process p = Runtime.getRuntime().exec(
						String.format(
								"java -jar %s -vu %s %s",
								System.getProperty("ABIS_CLIENT_LOCATION"), rfc, tempFile.getAbsolutePath()));
				p.waitFor();

				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

				String line = "";
				StringBuffer output = new StringBuffer();
				while ((line = reader.readLine()) != null) {
					output.append(line);
				}
				logger.info(output.toString());
				
				ServiceVerifyUnknownPositionResponse serviceResponse = mapper.readValue(output.toString(), ServiceVerifyUnknownPositionResponse.class);
				
				if (serviceResponse != null && serviceResponse.getCode() != 0) {
					throw new Exception(serviceResponse.getMessage());
				}

				if (serviceResponse.getResult() != null) {
					if (serviceResponse.getResult().getResponseList() != null && !serviceResponse.getResult().getResponseList().isEmpty()) {
						
						for (VerifyUnknownPositionMatchInfoDTO matchInfo : serviceResponse.getResult().getResponseList()){
							if (matchInfo != null){
								Verify1a1Result result = new Verify1a1Result();
					    		if (matchInfo.getHit() != null && matchInfo.getHit()){
					    			result.setHit(HitResult.MATCH);
					    		}else{
					    			result.setHit(HitResult.NO_MATCH);
					    		}
								result.setScore(matchInfo.getScore());
								
								response.setResult(result);
								break;
							}
						}
						
					} else {
						logger.info("Verificacion Fallida: " + rfc);
					}
				}

			}

		} catch (Exception e) {
			response.setCode(-1);
			response.setMessage(e.getMessage());
		}finally{
			Date finVerHuellas = Calendar.getInstance().getTime();
			AuditEvent eventoVerHuellas = new AuditEvent();
			eventoVerHuellas.setUuid(uuid);
			eventoVerHuellas.setDateTime(Calendar.getInstance().getTime());
			eventoVerHuellas.setUser(user);
			eventoVerHuellas.setIpAddress(ipAddress);
			eventoVerHuellas.setMacAddress(macAddress);
			String huellasMsg = String.format("VerificacionIdentidad, %s, %s, %s, %s, %s, %s", 
							    sdt.format(inicioVerHuellas), sdt.format(finVerHuellas), request.getRfc(), response.getCode(), response.getMessage(), response.getCode() == 0 ? response.getResult().getHit() : "NO RESULT");
			eventoVerHuellas.setMessage(huellasMsg);
			AuditManager.register(eventoVerHuellas);
		}
		
		return Response.ok(response).build();
	}
	
}
