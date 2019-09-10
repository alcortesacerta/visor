/*    */ package com.iecisa.sat.saie.vf.integration.service.rest;

/*    */
/*    */ import com.iecisa.sat.saie.response.ServiceResponse;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.ConsultaSiseRequestDTO;
/*    */ import feign.Feign;
/*    */ import feign.gson.GsonDecoder;
/*    */ import feign.gson.GsonEncoder;
/*    */ import feign.jaxrs.JAXRSModule;

/*    */
/*    */ public class ConsultarRfcSiseRestClient
/*    */ {
	/*    */ public ConsultarRfcSiseRestClient() {
		/* 13 */ this.proxy = null;
		/*    */
		/*    */
		/*    */
		/* 17 */ this
				/*    */
				/*    */
				/*    */
				/*    */
				/* 22 */ .proxy = (IServiceSise) Feign.builder().decoder(new GsonDecoder()).encoder(new GsonEncoder())
						.contract(new JAXRSModule.JAXRSContract())
						.target(IServiceSise.class, System.getProperty("SISE_URL"));
		/*    */ }

	/*    */ private IServiceSise proxy;

	/*    */
	/* 26 */ public ServiceResponse<String> getConsultaSise(ConsultaSiseRequestDTO request) {
		return this.proxy.getConsultaSise(request);
	}
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\rest\ConsultarRfcSiseRestClient.class Java
 * compiler version: 8 (52.0) JD-Core Version: 1.0.7
 */