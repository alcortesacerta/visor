package com.iecisa.sat.saie.service.abis.client.dto;

import java.util.List;

public class VerifyUknownPositionResultDTO {
	
	private List<VerifyUnknownPositionMatchInfoDTO> responseList;
	private String fingerPositionSearched;
	
	public List<VerifyUnknownPositionMatchInfoDTO> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<VerifyUnknownPositionMatchInfoDTO> responseList) {
		this.responseList = responseList;
	}
	public String getFingerPositionSearched() {
		return fingerPositionSearched;
	}
	public void setFingerPositionSearched(String fingerPositionSearched) {
		this.fingerPositionSearched = fingerPositionSearched;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VerifyUknownPositionResultDTO [");
		if (responseList != null) {
			builder.append("responseList=");
			builder.append(responseList);
			builder.append(", ");
		}
		if (fingerPositionSearched != null) {
			builder.append("fingerPositionSearched=");
			builder.append(fingerPositionSearched);
		}
		builder.append("]");
		return builder.toString();
	}

}
