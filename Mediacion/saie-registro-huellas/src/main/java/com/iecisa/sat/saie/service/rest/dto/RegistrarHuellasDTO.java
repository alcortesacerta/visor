package com.iecisa.sat.saie.service.rest.dto;

public class RegistrarHuellasDTO {

	private String rfc;
	private String ePid;
	private String eUri;
	
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
	public String geteUri() {
		return eUri;
	}
	public void seteUri(String eUri) {
		this.eUri = eUri;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistrarHuellasDTO [");
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
		if (eUri != null) {
			builder.append("eUri=");
			builder.append(eUri);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
