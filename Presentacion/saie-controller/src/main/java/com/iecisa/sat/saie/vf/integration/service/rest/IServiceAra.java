package com.iecisa.sat.saie.vf.integration.service.rest;

import com.iecisa.sat.saie.response.ServiceResponse;
import com.iecisa.sat.saie.vf.integration.service.dto.AraResponseDTO;
import com.iecisa.sat.saie.vf.integration.service.dto.ConsultaAraRequestDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/cxf/rfc/ara/rfc/ara/consulta/ns")
public interface IServiceAra {
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	ServiceResponse<AraResponseDTO> getCertificadoAra(ConsultaAraRequestDTO paramConsultaAraRequestDTO);
}

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\rest\IServiceAra.class Java compiler version: 8
 * (52.0) JD-Core Version: 1.0.7
 */