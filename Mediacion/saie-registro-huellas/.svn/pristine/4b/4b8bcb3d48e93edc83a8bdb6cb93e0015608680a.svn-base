package com.iecisa.sat.saie.service.abis.client.dto;

public class ServiceSearchAndInsertResponse {

	private int code;
	private String message;
	private SearchAndInsertResultDTO result;
	
	public ServiceSearchAndInsertResponse(){
		this.code = 0;
		this.message = "OK";
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SearchAndInsertResultDTO getResult() {
		return result;
	}

	public void setResult(SearchAndInsertResultDTO result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceSearchAndInsertResponse [code=");
		builder.append(code);
		builder.append(", ");
		if (message != null) {
			builder.append("message=");
			builder.append(message);
			builder.append(", ");
		}
		if (result != null) {
			builder.append("result=");
			builder.append(result);
		}
		builder.append("]");
		return builder.toString();
	}
	
}