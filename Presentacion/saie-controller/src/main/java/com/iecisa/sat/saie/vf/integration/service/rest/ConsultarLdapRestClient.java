/*    */ package com.iecisa.sat.saie.vf.integration.service.rest;

/*    */
/*    */ import com.iecisa.sat.saie.response.ServiceResponse;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.ConsultaLdapRequestDTO;
/*    */ import feign.Feign;
/*    */ import feign.gson.GsonDecoder;
/*    */ import feign.gson.GsonEncoder;
/*    */ import feign.jaxrs.JAXRSModule;
/*    */ import java.util.List;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class ConsultarLdapRestClient
/*    */ {
	/* 20 */ private IServiceLdap proxy = (IServiceLdap) Feign.builder()/* 21 */ .decoder(new GsonDecoder())
			/* 22 */ .encoder(new GsonEncoder())/* 23 */ .contract(new JAXRSModule.JAXRSContract())
			/* 24 */ .target(IServiceLdap.class, System.getProperty("LDAP_URL"));

	/*    */
	/*    */
	/*    */
	/* 28 */ public ServiceResponse<List<String>> getRolesLdap(ConsultaLdapRequestDTO request) {
		return this.proxy.getRolesLdap(request);
	}
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\rest\ConsultarLdapRestClient.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */