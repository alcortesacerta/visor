package com.iecisa.sat.saie.service.abis.client.dto;

import java.util.List;

public class SearchAndInsertResultDTO {
	
	private Boolean hit;
	private List<SearchAndInsertCandidatesDTO> candidatos;
	
	public Boolean getHit() {
		return hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}
	public List<SearchAndInsertCandidatesDTO> getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(List<SearchAndInsertCandidatesDTO> candidatos) {
		this.candidatos = candidatos;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchAndInsertResponseDTO [hit=");
		builder.append(hit);
		builder.append(", ");
		if (candidatos != null) {
			builder.append("candidatos=");
			builder.append(candidatos);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
