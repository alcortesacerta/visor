package com.iecisa.sat.saie.vf.integration.service.iface;

import com.iecisa.sat.saie.response.ServiceResponse;
import com.iecisa.sat.saie.vf.integration.service.dto.BuscarEnrolamientoPorCriteriosDTO;
import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoAdjuntosDTO;
import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoDetalleDTO;
import java.util.List;

public interface IServiceEnrolamiento {
	ServiceResponse<List<DatosEnrolamientoDTO>> getEnrolamientoPorCriterios(
			BuscarEnrolamientoPorCriteriosDTO paramBuscarEnrolamientoPorCriteriosDTO, boolean paramBoolean);

	ServiceResponse<VersionEnrolamientoDetalleDTO> getVersionEnrolamientoByEPID(String paramString);

	ServiceResponse<VersionEnrolamientoAdjuntosDTO> getVersionEnrolamientoAdjuntosByEPID(String paramString);
}

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\iface\IServiceEnrolamiento.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */