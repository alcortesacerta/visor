package com.iecisa.sat.saie.service.abis.client.dto;

import java.sql.Timestamp;

public class ColaAfisDTO {

	private int id;
	private String rfc;
	private String eUri;
	private int estado;
	private int reintento;
	private Timestamp fechaPeticion;
	private Timestamp fechaReintento;
	private String ePid;
	
	public ColaAfisDTO(int id, String rfc, String eUri, int estado,	int reintento, Timestamp fechaPeticion, Timestamp fechaReintento, String ePid) {
		super();
		this.id = id;
		this.rfc = rfc;
		this.eUri = eUri;
		this.estado = estado;
		this.reintento = reintento;
		this.fechaPeticion = fechaPeticion;
		this.fechaReintento = fechaReintento;
		this.ePid = ePid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String geteUri() {
		return eUri;
	}

	public void seteUri(String eUri) {
		this.eUri = eUri;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getReintento() {
		return reintento;
	}

	public void setReintento(int reintento) {
		this.reintento = reintento;
	}

	public Timestamp getFechaPeticion() {
		return fechaPeticion;
	}

	public void setFechaPeticion(Timestamp fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	public Timestamp getFechaReintento() {
		return fechaReintento;
	}

	public void setFechaReintento(Timestamp fechaReintento) {
		this.fechaReintento = fechaReintento;
	}

	public String getePid() {
		return ePid;
	}

	public void setePid(String ePid) {
		this.ePid = ePid;
	}
	
	
}
