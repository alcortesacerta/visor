/*    */ package com.iecisa.sat.saie.vf.integration.service.impl;

/*    */
/*    */ import com.iecisa.sat.saie.response.ServiceResponse;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dao.DBManager;
/*    */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosDuplicidadDTO;
/*    */ import com.iecisa.sat.saie.vf.integration.service.iface.IServiceDuplicidad;
/*    */ import java.util.List;

/*    */
/*    */ public class ServiceDuplicidad/*    */ implements IServiceDuplicidad
/*    */ {
	/* 12 */ public static int DUPLICADO_CASO = -1000;

	/*    */
	/*    */
	/*    */ public ServiceResponse<List<DatosDuplicidadDTO>> getListadoDuplicidadPorCriterios() {
		/* 16 */ ServiceResponse<List<DatosDuplicidadDTO>> response = new ServiceResponse<List<DatosDuplicidadDTO>>();
		/*    */
		/*    */ try {
			/* 19 */ List<DatosDuplicidadDTO> result = DBManager.getInstance().consultarCatalogoDuplicidad();
			/*    */
			/* 21 */ response.setCode(0);
			/* 22 */ response.setMessage("OK");
			/* 23 */ response.setResult(result);
			/*    */ }
		/* 25 */ catch (Exception e) {
			/* 26 */ e.printStackTrace();
			/* 27 */ response.setCode(-1);
			/* 28 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*    */ }
		/*    */
		/* 31 */ return response;
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\impl\ServiceDuplicidad.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */