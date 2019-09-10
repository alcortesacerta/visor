package com.iecisa.sat.saie.service.rest.dto;

public class ServiceNotificacionRequest {

	private String to;
	private String cc;
	private String from;
	private String subject;
	private String msg;
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceNotificacionRequest [");
		if (to != null) {
			builder.append("to=");
			builder.append(to);
			builder.append(", ");
		}
		if (cc != null) {
			builder.append("cc=");
			builder.append(cc);
			builder.append(", ");
		}
		if (from != null) {
			builder.append("from=");
			builder.append(from);
			builder.append(", ");
		}
		if (subject != null) {
			builder.append("subject=");
			builder.append(subject);
			builder.append(", ");
		}
		if (msg != null) {
			builder.append("msg=");
			builder.append(msg);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
