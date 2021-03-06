/*    */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*    */
/*    */ public class EstatusValidacionAutorizacionDTO
/*    */ {
	/*    */ private String rfc;
	/*    */ private String version;
	/*    */ private Integer estatus_validacion_actualizacion;
	/*    */ private String comentarios;
	/*    */ private String motivoCancelacion;
	/*    */ private String firma;
	/*    */ private String cadenaOriginal;

	/*    */
	/*    */ public EstatusValidacionAutorizacionDTO(String rfc, String version,
			Integer estatus_validacion_actualizacion, String comentarios, String motivoCancelacion) {
		/* 14 */ this.rfc = rfc;
		/* 15 */ this.version = version;
		/* 16 */ this.estatus_validacion_actualizacion = estatus_validacion_actualizacion;
		/* 17 */ this.comentarios = comentarios;
		/* 18 */ this.motivoCancelacion = motivoCancelacion;
		/*    */ }

	/*    */
	/*    */
	/*    */
	/*    */ public EstatusValidacionAutorizacionDTO() {
	}

	/*    */
	/*    */
	/* 26 */ public String getRfc() {
		return this.rfc;
	}

	/*    */
	/*    */
	/*    */
	/* 30 */ public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/*    */
	/*    */
	/*    */
	/* 34 */ public String getVersion() {
		return this.version;
	}

	/*    */
	/*    */
	/*    */
	/* 38 */ public void setVersion(String version) {
		this.version = version;
	}

	/*    */
	/*    */
	/*    */
	/* 42 */ public Integer getEstatus_validacion_actualizacion() {
		return this.estatus_validacion_actualizacion;
	}

	/*    */
	/*    */
	/*    */
	/*    */
	/* 47 */ public void setEstatus_validacion_actualizacion(Integer estatus_validacion_actualizacion) {
		this.estatus_validacion_actualizacion = estatus_validacion_actualizacion;
	}

	/*    */
	/*    */
	/*    */
	/* 51 */ public String getComentarios() {
		return this.comentarios;
	}

	/*    */
	/*    */
	/*    */
	/* 55 */ public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/*    */
	/*    */
	/*    */
	/* 59 */ public String getMotivoCancelacion() {
		return this.motivoCancelacion;
	}

	/*    */
	/*    */
	/*    */
	/* 63 */ public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	/*    */
	/*    */
	/*    */
	/*    */
	/* 68 */ public String toString() {
		return "EstatusValidacionAutorizacionDTO [rfc=" + this.rfc + ", version=" + this.version
				+ ", estatus_validacion_actualizacion=" + this.estatus_validacion_actualizacion + ", comentarios="
				+ this.comentarios + ", motivoCancelacion=" + this.motivoCancelacion + "]";
	}

	/*    */
	/*    */
	/*    */
	/*    */
	/*    */
	/*    */
	/*    */
	/* 76 */ public String getFirma() {
		return this.firma;
	}

	/*    */
	/*    */
	/*    */
	/* 80 */ public void setFirma(String firmaValidacion) {
		this.firma = firmaValidacion;
	}

	/*    */
	/*    */
	/*    */
	/* 84 */ public String getCadenaOriginal() {
		return this.cadenaOriginal;
	}

	/*    */
	/*    */
	/*    */
	/* 88 */ public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\EstatusValidacionAutorizacionDTO.class Java
 * compiler version: 8 (52.0) JD-Core Version: 1.0.7
 */