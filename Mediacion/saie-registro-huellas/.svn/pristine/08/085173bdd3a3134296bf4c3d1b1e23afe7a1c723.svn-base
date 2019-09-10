package com.iecisa.sat.saie.service.rest.impl;

import com.iecisa.sat.saie.service.rest.dto.ServiceNotificacionRequest;
import com.iecisa.sat.saie.service.rest.dto.ServiceNotificacionResponse;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxrs.JAXRSModule.JAXRSContract;

public class NotificacionRestClient {

	private IServiceNotificacion proxy = null;
	
	public NotificacionRestClient() {
		super();
		proxy = Feign
				.builder()
				.decoder(new GsonDecoder())
				.encoder(new GsonEncoder())
				.contract(new JAXRSContract())
				.target(IServiceNotificacion.class, System.getProperty("MAIL_URL"));
	}

	public ServiceNotificacionResponse sendNotification(ServiceNotificacionRequest request) {
		return proxy.sendEmail(request);
	}
	
}
