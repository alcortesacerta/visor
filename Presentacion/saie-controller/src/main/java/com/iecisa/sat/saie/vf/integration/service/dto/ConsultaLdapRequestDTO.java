/*    */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*    */
/*    */
/*    */
/*    */ public class ConsultaLdapRequestDTO
/*    */ {
	/*    */ private String rfcCorto;
	/*    */ private String tipo;

	/*    */
	/* 10 */ public ConsultaLdapRequestDTO(String rfcCorto) {
		this.rfcCorto = rfcCorto;
	}

	/*    */
	/*    */
	/*    */
	/*    */ public ConsultaLdapRequestDTO(String rfcCorto, String tipo) {
		/* 15 */ this.rfcCorto = rfcCorto;
		/* 16 */ this.tipo = tipo;
		/*    */ }

	/*    */
	/*    */
	/* 20 */ public String getRfcCorto() {
		return this.rfcCorto;
	}

	/*    */
	/*    */
	/*    */
	/* 24 */ public void setRfcCorto(String rfcCorto) {
		this.rfcCorto = rfcCorto;
	}

	/*    */
	/*    */
	/*    */
	/* 28 */ public String getTipo() {
		return this.tipo;
	}

	/*    */
	/*    */
	/*    */
	/* 32 */ public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\ConsultaLdapRequestDTO.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */