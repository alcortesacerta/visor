/*    */ package com.iecisa.sat.saie.vf.integration.service.rest;

/*    */
/*    */ import com.iecisa.sat.saie.response.ServiceResponse;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.AraResponseDTO;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.ConsultaAraRequestDTO;
/*    */ import feign.Feign;
/*    */ import feign.gson.GsonDecoder;
/*    */ import feign.gson.GsonEncoder;
/*    */ import feign.jaxrs.JAXRSModule;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class ConsultarAraRestClient
/*    */ {
	/* 19 */ private IServiceAra proxy = (IServiceAra) Feign.builder()/* 20 */ .decoder(new GsonDecoder())
			/* 21 */ .encoder(new GsonEncoder())/* 22 */ .contract(new JAXRSModule.JAXRSContract())
			/* 23 */ .target(IServiceAra.class, System.getProperty("ARA_URL"));

	/*    */
	/*    */
	/*    */
	/* 27 */ public ServiceResponse<AraResponseDTO> getCertificadoAra(ConsultaAraRequestDTO request) {
		return this.proxy.getCertificadoAra(request);
	}
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\rest\ConsultarAraRestClient.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */