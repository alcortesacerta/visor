package com.iecisa.sat.saie.service.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iecisa.sat.saie.service.rest.dto.ServiceNotificacionRequest;
import com.iecisa.sat.saie.service.rest.dto.ServiceNotificacionResponse;

@Path("/cxf/email/Servmail")
public interface IServiceNotificacion {
	
	@POST
	@Path("/mail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceNotificacionResponse sendEmail(ServiceNotificacionRequest request);
	
}
