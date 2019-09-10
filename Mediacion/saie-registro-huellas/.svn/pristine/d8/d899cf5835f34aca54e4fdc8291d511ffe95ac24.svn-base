package com.iecisa.sat.saie.service.rest.impl;

import java.io.File;
import java.net.URI;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iecisa.sat.saie.audit.AuditManager;
import com.iecisa.sat.saie.audit.dto.AuditEvent;
import com.iecisa.sat.saie.response.ServiceResponse;
import com.iecisa.sat.saie.service.rest.dao.FinEnrolamientoDAO;
import com.iecisa.sat.saie.service.rest.dto.RegistrarHuellasDTO;

@Path("/enrollment")
public class ServiceRegistroHuellasImpl {

	private static final Logger logger = LoggerFactory.getLogger(ServiceRegistroHuellasImpl.class);
	private SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
	
	public ServiceRegistroHuellasImpl() {
		super();
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setRegistrarHuellas(@Context HttpServletRequest httpRequest, RegistrarHuellasDTO request) {
		logger.info("RQS setRegistrarHuellas: " + request);
		Date inicioRegHuellas = Calendar.getInstance().getTime();
		ServiceResponse<String> response = new ServiceResponse<String>();
		String uuid = UUID.randomUUID().toString();
		String ipAddress = httpRequest.getRemoteAddr();
		String macAddress = "00:00:00:00:00";
		String user = "generico";
		
		if(request != null){
			String rfc = request.getRfc();
			String eUri = request.geteUri();
			File file = null;
			try{
				if(eUri != null && !eUri.isEmpty() && (file = new File(new URI(eUri).getPath().replace("//", "/"))).exists() && file.isDirectory()){
					FinEnrolamientoDAO.getInstance().encolarHuellasAfis(rfc, eUri, request.getePid());
				}else{
					response.setCode(-1);
					response.setMessage("el directorio no existe");
				}
			}catch(Exception e){
				e.printStackTrace();
				response.setCode(-1);
				response.setMessage("falló la conexión a la bd");
			}finally{
				Date finRegHuellas = Calendar.getInstance().getTime();
				AuditEvent eventoRegHuellas = new AuditEvent();
				eventoRegHuellas.setUuid(uuid);
				eventoRegHuellas.setDateTime(Calendar.getInstance().getTime());
				eventoRegHuellas.setUser(user);
				eventoRegHuellas.setIpAddress(ipAddress);
				eventoRegHuellas.setMacAddress(macAddress);
				String huellasMsg = String.format("RegistroBiometricos, %s, %s, %s, %s, %s", 
								    sdt.format(inicioRegHuellas), sdt.format(finRegHuellas), request.getRfc(), request.geteUri(), request.getePid());
				eventoRegHuellas.setMessage(huellasMsg);
				AuditManager.register(eventoRegHuellas);
			}
		}

		return Response.ok(response).build();
	}
}
