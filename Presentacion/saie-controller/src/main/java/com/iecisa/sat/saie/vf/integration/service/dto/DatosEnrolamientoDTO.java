/*     */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*     */
/*     */ import java.util.List;

/*     */
/*     */
/*     */
/*     */ public class DatosEnrolamientoDTO
/*     */ {
	/*     */ private String rfc;
	/*     */ private String curp;
	/*     */ private String nombre;
	/*     */ private String fechaEnrolamiento;
	/*     */ private String localidadEnrolamiento;
	/*     */ private String unidadEnrolamiento;
	/*     */ private TipoPersona tipoPersona;
	/*     */ private boolean duplicidad;
	/*     */ private boolean expuesto;
	/*     */ private List<VersionEnrolamientoGeneralDTO> versiones;

	/*     */
	/*     */ public DatosEnrolamientoDTO(String rfc, String curp, TipoPersona tipoPersona, boolean duplicidad) {
		/* 21 */ this.rfc = rfc;
		/* 22 */ this.curp = curp;
		/* 23 */ this.tipoPersona = tipoPersona;
		/* 24 */ this.duplicidad = duplicidad;
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ public DatosEnrolamientoDTO(String rfc, String curp, String nombre, String fechaEnrolamiento,
			String localidadEnrolamiento, String unidadEnrolamiento, TipoPersona tipoPersona) {
		/* 31 */ this.rfc = rfc;
		/* 32 */ this.curp = curp;
		/* 33 */ this.nombre = nombre;
		/* 34 */ this.fechaEnrolamiento = fechaEnrolamiento;
		/* 35 */ this.localidadEnrolamiento = localidadEnrolamiento;
		/* 36 */ this.unidadEnrolamiento = unidadEnrolamiento;
		/* 37 */ this.tipoPersona = tipoPersona;
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */ public DatosEnrolamientoDTO() {
	}

	/*     */
	/*     */
	/* 45 */ public String getRfc() {
		return this.rfc;
	}

	/*     */
	/*     */
	/* 48 */ public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/*     */
	/*     */
	/* 51 */ public String getCurp() {
		return this.curp;
	}

	/*     */
	/*     */
	/* 54 */ public void setCurp(String curp) {
		this.curp = curp;
	}

	/*     */
	/*     */
	/* 57 */ public TipoPersona getTipoPersona() {
		return this.tipoPersona;
	}

	/*     */
	/*     */
	/* 60 */ public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/*     */
	/*     */
	/* 63 */ public boolean isDuplicidad() {
		return this.duplicidad;
	}

	/*     */
	/*     */
	/* 66 */ public void setDuplicidad(boolean duplicidad) {
		this.duplicidad = duplicidad;
	}

	/*     */
	/*     */
	/* 69 */ public List<VersionEnrolamientoGeneralDTO> getVersiones() {
		return this.versiones;
	}

	/*     */
	/*     */
	/* 72 */ public void setVersiones(List<VersionEnrolamientoGeneralDTO> versiones) {
		this.versiones = versiones;
	}

	/*     */
	/*     */
	/*     */
	/* 76 */ public String getNombre() {
		return this.nombre;
	}

	/*     */
	/*     */
	/*     */
	/* 80 */ public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*     */
	/*     */
	/*     */
	/* 84 */ public String getFechaEnrolamiento() {
		return this.fechaEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 88 */ public void setFechaEnrolamiento(String fechaEnrolamiento) {
		this.fechaEnrolamiento = fechaEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 92 */ public String getLocalidadEnrolamiento() {
		return this.localidadEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 96 */ public void setLocalidadEnrolamiento(String localidadEnrolamiento) {
		this.localidadEnrolamiento = localidadEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 100 */ public String getUnidadEnrolamiento() {
		return this.unidadEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/* 104 */ public void setUnidadEnrolamiento(String unidadEnrolamiento) {
		this.unidadEnrolamiento = unidadEnrolamiento;
	}

	/*     */
	/*     */
	/*     */
	/*     */ public String toString() {
		/* 109 */ StringBuilder builder = new StringBuilder();
		/* 110 */ builder.append("DatosEnrolamientoDTO [");
		/* 111 */ if (this.rfc != null) {
			/* 112 */ builder.append("rfc=");
			/* 113 */ builder.append(this.rfc);
			/* 114 */ builder.append(", ");
			/*     */ }
		/* 116 */ if (this.curp != null) {
			/* 117 */ builder.append("curp=");
			/* 118 */ builder.append(this.curp);
			/* 119 */ builder.append(", ");
			/*     */ }
		/* 121 */ if (this.tipoPersona != null) {
			/* 122 */ builder.append("tipoPersona=");
			/* 123 */ builder.append(this.tipoPersona);
			/* 124 */ builder.append(", ");
			/*     */ }
		/* 126 */ builder.append("duplicidad=");
		/* 127 */ builder.append(this.duplicidad);
		/* 128 */ builder.append(", ");
		/* 129 */ if (this.versiones != null) {
			/* 130 */ builder.append("versiones=");
			/* 131 */ builder.append(this.versiones);
			/*     */ }
		/* 133 */ builder.append("]");
		/* 134 */ return builder.toString();
		/*     */ }

	/*     */
	/*     */
	/* 138 */ public boolean isExpuesto() {
		return this.expuesto;
	}

	/*     */
	/*     */
	/*     */
	/* 142 */ public void setExpuesto(boolean expuesto) {
		this.expuesto = expuesto;
	}
	/*     */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\DatosEnrolamientoDTO.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */