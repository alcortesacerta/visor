/*    */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*    */
/*    */
/*    */ public class ConsultaSiseRequestDTO
/*    */ {
	/*    */ private String rfc;
	/*    */ private String usuario;

	/*    */
	/*    */ public ConsultaSiseRequestDTO(String rfc, String usuario) {
		/* 10 */ this.rfc = rfc;
		/* 11 */ this.usuario = usuario;
		/*    */ }

	/*    */
	/*    */
	/* 15 */ public String getRfc() {
		return this.rfc;
	}

	/*    */
	/*    */
	/*    */
	/* 19 */ public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/*    */
	/*    */
	/*    */
	/* 23 */ public String getUsuario() {
		return this.usuario;
	}

	/*    */
	/*    */
	/*    */
	/* 27 */ public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\ConsultaSiseRequestDTO.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */