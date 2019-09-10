package com.iecisa.sat.saie.service.rest.dto;

public class RegistrarFinEnrolamiento {

	private String rfc;//---
	
	private String ePid; //no se ocupa (enrolement process id)
	private String ePType;//no se ocupa (catalogo de tipo de trabajo: actualiza, enrola, etc...)
	private String ePTypeDetail;//detalle tipo de persona, ej persona "física capacidad especiales"
	private String eStatus;
	private String eStatusProceso;
	
	private String personType;//física o moral (f o m)---
	private String eUri;//directorio de enrolamiento(ruta absoluta)---
	private String nombre;//---
	private String aPaterno;//---
	private String aMaterno;//---
	private String razonSocial;//---
	private String tipoSociedad;//---
	private String curp;//---
	private String estacion;//---
	private String descripcionEstacion;
	private String localidad;//---
	private String descripcionLocalidad;
	private String fechaInicio;//---
	private String fechaFin;//---
	private String fechaCarga;//---
	private String rfcRepresentante;//--- escribir a tabla det_rfc_asociado
	
	private String motivoCierre;//no se ocupa
	private String comentariosCierre;//no se ocupa
	//ultimo directorio es el id
	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getePid() {
		return ePid;
	}
	public void setePid(String ePid) {
		this.ePid = ePid;
	}
	public String getePType() {
		return ePType;
	}
	public void setePType(String ePType) {
		this.ePType = ePType;
	}
	public String getePTypeDetail() {
		return ePTypeDetail;
	}
	public void setePTypeDetail(String ePTypeDetail) {
		this.ePTypeDetail = ePTypeDetail;
	}
	public String geteStatus() {
		return eStatus;
	}
	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public String geteUri() {
		return eUri;
	}
	public void seteUri(String eUri) {
		this.eUri = eUri;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getTipoSociedad() {
		return tipoSociedad;
	}
	public void setTipoSociedad(String tipoSociedad) {
		this.tipoSociedad = tipoSociedad;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getEstacion() {
		return estacion;
	}
	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public String getRfcRepresentante() {
		return rfcRepresentante;
	}
	public void setRfcRepresentante(String rfcRepresentante) {
		this.rfcRepresentante = rfcRepresentante;
	}
	public String getMotivoCierre() {
		return motivoCierre;
	}
	public void setMotivoCierre(String motivoCierre) {
		this.motivoCierre = motivoCierre;
	}
	public String getComentariosCierre() {
		return comentariosCierre;
	}
	public void setComentariosCierre(String comentariosCierre) {
		this.comentariosCierre = comentariosCierre;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistrarFinEnrolamiento [");
		if (rfc != null) {
			builder.append("rfc=");
			builder.append(rfc);
			builder.append(", ");
		}
		if (ePid != null) {
			builder.append("ePid=");
			builder.append(ePid);
			builder.append(", ");
		}
		if (ePType != null) {
			builder.append("ePType=");
			builder.append(ePType);
			builder.append(", ");
		}
		if (ePTypeDetail != null) {
			builder.append("ePTypeDetail=");
			builder.append(ePTypeDetail);
			builder.append(", ");
		}
		if (eStatus != null) {
			builder.append("eStatus=");
			builder.append(eStatus);
			builder.append(", ");
		}
		if (eStatusProceso != null) {
			builder.append("eStatusProceso=");
			builder.append(eStatusProceso);
			builder.append(", ");
		}
		if (personType != null) {
			builder.append("personType=");
			builder.append(personType);
			builder.append(", ");
		}
		if (eUri != null) {
			builder.append("eUri=");
			builder.append(eUri);
			builder.append(", ");
		}
		if (nombre != null) {
			builder.append("nombre=");
			builder.append(nombre);
			builder.append(", ");
		}
		if (aPaterno != null) {
			builder.append("aPaterno=");
			builder.append(aPaterno);
			builder.append(", ");
		}
		if (aMaterno != null) {
			builder.append("aMaterno=");
			builder.append(aMaterno);
			builder.append(", ");
		}
		if (razonSocial != null) {
			builder.append("razonSocial=");
			builder.append(razonSocial);
			builder.append(", ");
		}
		if (tipoSociedad != null) {
			builder.append("tipoSociedad=");
			builder.append(tipoSociedad);
			builder.append(", ");
		}
		if (curp != null) {
			builder.append("curp=");
			builder.append(curp);
			builder.append(", ");
		}
		if (estacion != null) {
			builder.append("estacion=");
			builder.append(estacion);
			builder.append(", ");
		}
		if (descripcionEstacion != null) {
			builder.append("descripcionEstacion=");
			builder.append(descripcionEstacion);
			builder.append(", ");
		}
		if (localidad != null) {
			builder.append("localidad=");
			builder.append(localidad);
			builder.append(", ");
		}
		if (descripcionLocalidad != null) {
			builder.append("descripcionLocalidad=");
			builder.append(descripcionLocalidad);
			builder.append(", ");
		}
		if (fechaInicio != null) {
			builder.append("fechaInicio=");
			builder.append(fechaInicio);
			builder.append(", ");
		}
		if (fechaFin != null) {
			builder.append("fechaFin=");
			builder.append(fechaFin);
			builder.append(", ");
		}
		if (fechaCarga != null) {
			builder.append("fechaCarga=");
			builder.append(fechaCarga);
			builder.append(", ");
		}
		if (rfcRepresentante != null) {
			builder.append("rfcRepresentante=");
			builder.append(rfcRepresentante);
			builder.append(", ");
		}
		if (motivoCierre != null) {
			builder.append("motivoCierre=");
			builder.append(motivoCierre);
			builder.append(", ");
		}
		if (comentariosCierre != null) {
			builder.append("comentariosCierre=");
			builder.append(comentariosCierre);
		}
		builder.append("]");
		return builder.toString();
	}
	public String getDescripcionEstacion() {
		return descripcionEstacion;
	}
	public void setDescripcionEstacion(String descripcionEstacion) {
		this.descripcionEstacion = descripcionEstacion;
	}
	public String getDescripcionLocalidad() {
		return descripcionLocalidad;
	}
	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}
	public String geteStatusProceso() {
		return eStatusProceso;
	}
	public void seteStatusProceso(String eStatusProceso) {
		this.eStatusProceso = eStatusProceso;
	}
	
}
