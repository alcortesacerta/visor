/*     */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*     */
/*     */
/*     */
/*     */ public class ControlVersionesDTO
/*     */ {
	/*     */ private String operador;
	/*     */ private String rfc;
	/*     */ private String listaDocumentos;
	/*     */ private String fechaEnrolamiento;
	/*     */ private String unidadEnrolamiento;
	/*     */ private String localidadEnrolamiento;
	/*     */ private String justificacion;
	/*     */ private Integer estatus;
	/*     */ private int version;
	/*     */ private int id;

	/*     */
	/*     */ public ControlVersionesDTO(int id, String operador, String rfc, String listaDocumentos,
			String fechaEnrolamiento, String unidadEnrolamiento, String localidadEnrolamiento, String justificacion,
			Integer estatus, int version) {
		/* 19 */ this.id = id;
		/* 20 */ this.operador = operador;
		/* 21 */ this.rfc = rfc;
		/* 22 */ this.listaDocumentos = listaDocumentos;
		/* 23 */ this.fechaEnrolamiento = fechaEnrolamiento;
		/* 24 */ this.unidadEnrolamiento = unidadEnrolamiento;
		/* 25 */ this.localidadEnrolamiento = localidadEnrolamiento;
		/* 26 */ this.justificacion = justificacion;
		/* 27 */ this.estatus = estatus;
		/* 28 */ this.version = version;
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */ public ControlVersionesDTO() {
	}

	/*     */
	/*     */
	/* 36 */ public String getOperador() {
		return this.operador;
	}

	/*     */
	/*     */
	/*     */
	/* 40 */ public void setOperador(String operador) {
		this.operador = operador;
	}

	/*     */
	/*     */
	/*     */
	/* 44 */ public String getRfc() {
		return this.rfc;
	}

	/*     */
	/*     */
	/*     */
	/* 48 */ public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/*     */
	/*     */
	/*     */
	/* 52 */ public String getListaDocumentos() {
		return this.listaDocumentos;
	}

	/*     */
	/*     */
	/*     */
	/* 56 */ public void setListaDocumentos(String listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	/*     */
	/*     */
	/*     */
	/* 60 */ public String getFechaEnrolamiento() {
		return this.fechaEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 64 */ public void setFechaEnrolamiento(String fechaEnrolamiento) {
		this.fechaEnrolamiento = fechaEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 68 */ public String getUnidadEnrolamiento() {
		return this.unidadEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 72 */ public void setUnidadEnrolamiento(String unidadEnrolamiento) {
		this.unidadEnrolamiento = unidadEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 76 */ public String getJustificacion() {
		return this.justificacion;
	}

	/*     */
	/*     */
	/*     */
	/* 80 */ public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	/*     */
	/*     */
	/*     */
	/* 84 */ public Integer getEstatus() {
		return this.estatus;
	}

	/*     */
	/*     */
	/*     */
	/* 88 */ public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	/*     */
	/*     */
	/*     */
	/*     */
	/* 93 */ public String toString() {
		return "ControlVersionesDTO [operador=" + this.operador + ", rfc=" + this.rfc + ", listaDocumentos="
				+ this.listaDocumentos + ", fechaEnrolamiento=" + this.fechaEnrolamiento + ", unidadEnrolamiento="
				+ this.unidadEnrolamiento + ", justificacion=" + this.justificacion + ", estatus=" + this.estatus + "]";
	}

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/* 102 */ public int getVersion() {
		return this.version;
	}

	/*     */
	/*     */
	/*     */
	/* 106 */ public void setVersion(int version) {
		this.version = version;
	}

	/*     */
	/*     */
	/*     */
	/* 110 */ public int getId() {
		return this.id;
	}

	/*     */
	/*     */
	/*     */
	/* 114 */ public void setId(int id) {
		this.id = id;
	}

	/*     */
	/*     */
	/*     */
	/* 118 */ public String getLocalidadEnrolamiento() {
		return this.localidadEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 122 */ public void setLocalidadEnrolamiento(String localidadEnrolamiento) {
		this.localidadEnrolamiento = localidadEnrolamiento;
	}
	/*     */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\ControlVersionesDTO.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */