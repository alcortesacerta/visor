/*     */ package com.iecisa.sat.saie.vf.integration.service.dto;

/*     */
/*     */ import java.util.List;

/*     */
/*     */ public class VersionEnrolamientoGeneralDTO {
	/*     */ private String fechaEnrolamiento;
	/*     */ private String localidadEnrolamiento;
	/*     */ private String unidadEnrolamiento;
	/*     */ private String tipoEnrolamiento;
	/*     */ private String tipoMovimiento;
	/*     */ private String detalleTipoEnrolamiento;
	/*     */ private String nombre;
	/*     */ private String representante;
	/*     */ private String archivo;
	/*     */ private List<String> archivos;
	/*     */ private int historico;
	/*     */ private int estatus;
	/*     */ private int version;
	/*     */ private String estatusUnidad;
	/*     */ private String estatusProceso;
	/*     */ private ArchivoDTO foto;
	/*     */ private ArchivoDTO firma;

	/*     */
	/* 24 */ public String getArchivo() {
		return this.archivo;
	}

	/*     */
	/*     */
	/*     */
	/* 28 */ public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ public VersionEnrolamientoGeneralDTO(String fechaEnrolamiento, String localidadEnrolamiento,
			String unidadEnrolamiento, String tipoEnrolamiento, String tipoMovimiento, String detalleTipoEnrolamiento,
			String nombre, String representante, String archivo, int historico, int estatus, int version,
			String estatusUnidad, String estatusProceso) {
		/* 39 */ this.fechaEnrolamiento = fechaEnrolamiento;
		/* 40 */ this.localidadEnrolamiento = localidadEnrolamiento;
		/* 41 */ this.unidadEnrolamiento = unidadEnrolamiento;
		/* 42 */ this.tipoEnrolamiento = tipoEnrolamiento;
		/* 43 */ this.tipoMovimiento = tipoMovimiento;
		/* 44 */ this.detalleTipoEnrolamiento = detalleTipoEnrolamiento;
		/* 45 */ this.nombre = nombre;
		/* 46 */ this.representante = representante;
		/* 47 */ this.archivo = archivo;
		/* 48 */ this.historico = historico;
		/* 49 */ this.estatus = estatus;
		/* 50 */ this.version = version;
		/* 51 */ this.estatusUnidad = estatusUnidad;
		/* 52 */ this.estatusProceso = estatusProceso;
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */ public VersionEnrolamientoGeneralDTO() {
	}

	/*     */
	/*     */
	/* 60 */ public String getFechaEnrolamiento() {
		return this.fechaEnrolamiento;
	}

	/*     */
	/*     */
	/* 63 */ public void setFechaEnrolamiento(String fechaEnrolamiento) {
		this.fechaEnrolamiento = fechaEnrolamiento;
	}

	/*     */
	/*     */
	/* 66 */ public String getLocalidadEnrolamiento() {
		return this.localidadEnrolamiento;
	}

	/*     */
	/*     */
	/* 69 */ public void setLocalidadEnrolamiento(String localidadEnrolamiento) {
		this.localidadEnrolamiento = localidadEnrolamiento;
	}

	/*     */
	/*     */
	/* 72 */ public String getUnidadEnrolamiento() {
		return this.unidadEnrolamiento;
	}

	/*     */
	/*     */
	/* 75 */ public void setUnidadEnrolamiento(String unidadEnrolamiento) {
		this.unidadEnrolamiento = unidadEnrolamiento;
	}

	/*     */
	/*     */
	/* 78 */ public String getTipoEnrolamiento() {
		return this.tipoEnrolamiento;
	}

	/*     */
	/*     */
	/* 81 */ public void setTipoEnrolamiento(String tipoEnrolamiento) {
		this.tipoEnrolamiento = tipoEnrolamiento;
	}

	/*     */
	/*     */
	/* 84 */ public String getTipoMovimiento() {
		return this.tipoMovimiento;
	}

	/*     */
	/*     */
	/* 87 */ public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	/*     */
	/*     */
	/* 90 */ public String getDetalleTipoEnrolamiento() {
		return this.detalleTipoEnrolamiento;
	}

	/*     */
	/*     */
	/* 93 */ public void setDetalleTipoEnrolamiento(String operador) {
		this.detalleTipoEnrolamiento = operador;
	}

	/*     */
	/*     */
	/* 96 */ public String getNombre() {
		return this.nombre;
	}

	/*     */
	/*     */
	/* 99 */ public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*     */
	/*     */
	/* 102 */ public String getRepresentante() {
		return this.representante;
	}

	/*     */
	/*     */
	/* 105 */ public void setRepresentante(String representante) {
		this.representante = representante;
	}

	/*     */
	/*     */
	/* 108 */ public ArchivoDTO getFoto() {
		return this.foto;
	}

	/*     */
	/*     */
	/* 111 */ public void setFoto(ArchivoDTO foto) {
		this.foto = foto;
	}

	/*     */
	/*     */
	/* 114 */ public ArchivoDTO getFirma() {
		return this.firma;
	}

	/*     */
	/*     */
	/* 117 */ public void setFirma(ArchivoDTO firma) {
		this.firma = firma;
	}

	/*     */
	/*     */
	/*     */
	/*     */ public String toString() {
		/* 122 */ StringBuilder builder = new StringBuilder();
		/* 123 */ builder.append("VersionEnrolamientoGeneralDTO [");
		/* 124 */ if (this.fechaEnrolamiento != null) {
			/* 125 */ builder.append("fechaEnrolamiento=");
			/* 126 */ builder.append(this.fechaEnrolamiento);
			/* 127 */ builder.append(", ");
			/*     */ }
		/* 129 */ if (this.localidadEnrolamiento != null) {
			/* 130 */ builder.append("localidadEnrolamiento=");
			/* 131 */ builder.append(this.localidadEnrolamiento);
			/* 132 */ builder.append(", ");
			/*     */ }
		/* 134 */ if (this.unidadEnrolamiento != null) {
			/* 135 */ builder.append("unidadEnrolamiento=");
			/* 136 */ builder.append(this.unidadEnrolamiento);
			/* 137 */ builder.append(", ");
			/*     */ }
		/* 139 */ if (this.tipoEnrolamiento != null) {
			/* 140 */ builder.append("tipoEnrolamiento=");
			/* 141 */ builder.append(this.tipoEnrolamiento);
			/* 142 */ builder.append(", ");
			/*     */ }
		/* 144 */ if (this.tipoMovimiento != null) {
			/* 145 */ builder.append("tipoMovimiento=");
			/* 146 */ builder.append(this.tipoMovimiento);
			/* 147 */ builder.append(", ");
			/*     */ }
		/* 149 */ if (this.detalleTipoEnrolamiento != null) {
			/* 150 */ builder.append("detalleTipoEnrolamiento=");
			/* 151 */ builder.append(this.detalleTipoEnrolamiento);
			/* 152 */ builder.append(", ");
			/*     */ }
		/* 154 */ if (this.nombre != null) {
			/* 155 */ builder.append("nombre=");
			/* 156 */ builder.append(this.nombre);
			/* 157 */ builder.append(", ");
			/*     */ }
		/* 159 */ if (this.representante != null) {
			/* 160 */ builder.append("representante=");
			/* 161 */ builder.append(this.representante);
			/* 162 */ builder.append(", ");
			/*     */ }
		/* 164 */ if (this.archivo != null) {
			/* 165 */ builder.append("archivo=");
			/* 166 */ builder.append(this.archivo);
			/* 167 */ builder.append(", ");
			/*     */ }
		/* 169 */ if (this.archivos != null) {
			/* 170 */ builder.append("archivos=");
			/* 171 */ builder.append(this.archivos);
			/* 172 */ builder.append(", ");
			/*     */ }
		/* 174 */ builder.append("historico=");
		/* 175 */ builder.append(this.historico);
		/* 176 */ builder.append(", estatus=");
		/* 177 */ builder.append(this.estatus);
		/* 178 */ builder.append(", version=");
		/* 179 */ builder.append(this.version);
		/* 180 */ builder.append(", ");
		/* 181 */ if (this.estatusUnidad != null) {
			/* 182 */ builder.append("estatusUnidad=");
			/* 183 */ builder.append(this.estatusUnidad);
			/* 184 */ builder.append(", ");
			/*     */ }
		/* 186 */ if (this.estatusProceso != null) {
			/* 187 */ builder.append("estatusProceso=");
			/* 188 */ builder.append(this.estatusProceso);
			/* 189 */ builder.append(", ");
			/*     */ }
		/* 191 */ if (this.foto != null) {
			/* 192 */ builder.append("foto=");
			/* 193 */ builder.append(this.foto);
			/* 194 */ builder.append(", ");
			/*     */ }
		/* 196 */ if (this.firma != null) {
			/* 197 */ builder.append("firma=");
			/* 198 */ builder.append(this.firma);
			/*     */ }
		/* 200 */ builder.append("]");
		/* 201 */ return builder.toString();
		/*     */ }

	/*     */
	/*     */
	/* 205 */ public List<String> getArchivos() {
		return this.archivos;
	}

	/*     */
	/*     */
	/*     */
	/* 209 */ public void setArchivos(List<String> archivos) {
		this.archivos = archivos;
	}

	/*     */
	/*     */
	/*     */
	/* 213 */ public int getHistorico() {
		return this.historico;
	}

	/*     */
	/*     */
	/*     */
	/* 217 */ public void setHistorico(int historico) {
		this.historico = historico;
	}

	/*     */
	/*     */
	/*     */
	/* 221 */ public int getEstatus() {
		return this.estatus;
	}

	/*     */
	/*     */
	/*     */
	/* 225 */ public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	/*     */
	/*     */
	/*     */
	/* 229 */ public int getVersion() {
		return this.version;
	}

	/*     */
	/*     */
	/*     */
	/* 233 */ public void setVersion(int version) {
		this.version = version;
	}

	/*     */
	/*     */
	/*     */
	/* 237 */ public String getEstatusUnidad() {
		return this.estatusUnidad;
	}

	/*     */
	/*     */
	/*     */
	/* 241 */ public void setEstatusUnidad(String estatusUnidad) {
		this.estatusUnidad = estatusUnidad;
	}

	/*     */
	/*     */
	/*     */
	/* 245 */ public String getEstatusProceso() {
		return this.estatusProceso;
	}

	/*     */
	/*     */
	/*     */
	/* 249 */ public void setEstatusProceso(String estatusProceso) {
		this.estatusProceso = estatusProceso;
	}
	/*     */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dto\VersionEnrolamientoGeneralDTO.class Java
 * compiler version: 8 (52.0) JD-Core Version: 1.0.7
 */