/*     */ package com.iecisa.sat.saie.vf.integration.service.dao;

/*     */
/*     */ import com.iecisa.sat.saie.audit.dto.DBSaie;
/*     */ import com.iecisa.sat.saie.audit.dto.DBSaieResult;
/*     */ import com.iecisa.sat.saie.dao.DBManagerSaie;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.CatalogoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ControlVersionesDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosDuplicidadDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.ReporteBiometristaDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceDuplicidad;
/*     */ import com.iecisa.sat.saie.vf.integration.service.impl.ServiceEnrolamiento;
/*     */ import java.io.File;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */
/*     */
/*     */ public class DBManager
/*     */ {
	/* 25 */ private static DBManager instance = null;
	/* 26 */ public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/*     */
	/*     */ public static DBManager getInstance() {
		/* 29 */ if (instance == null) {
			/* 30 */ instance = new DBManager();
			/*     */ }
		/* 32 */ return instance;
		/*     */ }

	/*     */
	/*     */ public List<CatalogoDTO> cargaCatalogoTipoObservacion() throws SQLException {
		/* 36 */ List<CatalogoDTO> resultado = new ArrayList<CatalogoDTO>();
		/*     */
		/* 38 */ String sql = "SELECT * FROM cat_tipo_observacion;";
		/* 39 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.DUPLICIDAD, null);
		/* 40 */ for (int i = 0; i < rs.size(); i++) {
			/* 41 */ CatalogoDTO catalogodto = new CatalogoDTO(rs.getString(i, "tipo_observacion_desc"),
					rs.getInt(i, "id_tipo_observacion"));
			/* 42 */ resultado.add(catalogodto);
			/*     */ }
		/*     */
		/* 45 */ return resultado;
		/*     */ }

	/*     */
	/*     */ public List<CatalogoDTO> cargaCatalogoTipoResolucion() throws SQLException {
		/* 49 */ List<CatalogoDTO> result = new ArrayList<CatalogoDTO>();
		/*     */
		/* 51 */ String sql = "SELECT * FROM cat_tipo_resolucion ORDER BY id = '1' ASC, id = '2'desc;";
		/* 52 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.DUPLICIDAD, null);
		/* 53 */ for (int i = 0; i < rs.size(); i++) {
			/* 54 */ CatalogoDTO catalogoDTO = new CatalogoDTO(rs.getString(i, "tipo_resolucion"), rs.getInt(i, "id"));
			/* 55 */ result.add(catalogoDTO);
			/*     */ }
		/*     */
		/* 58 */ return result;
		/*     */ }

	/*     */
	/*     */ public List<String> getUnidadesLocalidad(String idLocalidad) throws SQLException {
		/* 62 */ List<String> result = new ArrayList<String>();
		/*     */
		/* 64 */ String sql = "SELECT workstation_id FROM workstation_localidad WHERE lcrm = ?";
		/* 65 */ List<Object> args = new ArrayList<Object>();
		/* 66 */ args.add(idLocalidad);
		/* 67 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.EXTERNA, args);
		/* 68 */ for (int i = 0; i < rs.size(); i++) {
			/* 69 */ String unidad = rs.getString(i, "workstation_id");
			/* 70 */ result.add(unidad);
			/*     */ }
		/*     */
		/* 73 */ return result;
		/*     */ }

	/*     */
	/*     */
	/*     */ public List<ControlVersionesDTO> catalogoActualizacionesPendientes() throws SQLException {
		/* 78 */ List<ControlVersionesDTO> resultado = new ArrayList<ControlVersionesDTO>();
		/* 79 */ String sql = "SELECT * FROM control_versiones WHERE (estatus_validacion_actualizacion = 3 OR estatus_validacion_actualizacion = 5) AND id > ?;";
		/* 80 */ List<Object> args = new ArrayList<Object>();
		/* 81 */ args.add(new Integer(ServiceEnrolamiento.VALIDACION_AUTORIZACION_INDICE));
		/*     */
		/* 83 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.VISOR, args);
		/*     */
		/* 85 */ for (int i = 0; i < rs.size(); i++) {
			/*     */
			/*     */
			/* 88 */ ControlVersionesDTO datosdto = new ControlVersionesDTO(rs.getInt(i, "id"),
					rs.getString(i, "operador"), rs.getString(i, "rfc_contribuyente"),
					rs.getString(i, "lista_documentos"),
					(rs.getTimestamp(i, "fecha_actualizacion") != null)
							? dateFormat.format(rs.getTimestamp(i, "fecha_actualizacion"))
							: "",
					rs.getString(i, "unidad_actualizacion"), rs.getString(i, "localidad_actualizacion"),
					rs.getString(i, "justificacion_actualizacion"),
					Integer.valueOf(rs.getInt(i, "estatus_validacion_actualizacion")), rs.getInt(i, "num_version"));
			/* 89 */ resultado.add(datosdto);
			/* 90 */ if (datosdto.getId() > ServiceEnrolamiento.VALIDACION_AUTORIZACION_INDICE) {
				/* 91 */ ServiceEnrolamiento.VALIDACION_AUTORIZACION_INDICE = datosdto.getId();
				/*     */ }
			/*     */ }
		/*     */
		/* 95 */ return resultado;
		/*     */ }

	/*     */
	/*     */ public Integer estatusValidacionAutorizacion(String rfc, String version,
			Integer estatus_validacion_actualizacion, String comentarios, String movitoCancelacion, String firma,
			String cadenaOriginal) throws SQLException {
		/* 99 */ String sql = "";
		/* 100 */ String sql2 = "";
		/* 101 */ Integer resultado = Integer.valueOf(0);
		/* 102 */ List<Object> args = new ArrayList<Object>();
		/* 103 */ List<Object> args2 = new ArrayList<Object>();
		/*     */
		/* 105 */ if (estatus_validacion_actualizacion.intValue() == 5
				|| estatus_validacion_actualizacion.intValue() == 4) {
			/* 106 */ sql = "UPDATE control_versiones SET estatus_validacion_actualizacion = ?, firma_validacion = ?, cadena_original_validacion = ?, comentarios = ?, motivo_cancelacion = ? WHERE rfc_contribuyente = ? AND num_version = ?;";
			/*     */ } else {
			/* 108 */ sql = "UPDATE control_versiones SET estatus_validacion_actualizacion = ?, firma_autorizacion = ?, cadena_original_autorizacion = ?, comentarios = ?, motivo_cancelacion = ? WHERE rfc_contribuyente = ? AND num_version = ?;";
			/*     */ }
		/* 110 */ args.add(estatus_validacion_actualizacion);
		/* 111 */ args.add(firma);
		/* 112 */ args.add(cadenaOriginal);
		/* 113 */ args.add(comentarios);
		/* 114 */ args.add(movitoCancelacion);
		/* 115 */ args.add(rfc);
		/* 116 */ args.add(Integer.valueOf(Integer.parseInt(version)));
		/*     */
		/* 118 */ sql2 = "UPDATE det_dato_enrolamiento SET estatus_validacion_actualizacion = ? WHERE rfc_enrolamiento = ? AND version = ?;";
		/* 119 */ args2.add(estatus_validacion_actualizacion);
		/* 120 */ args2.add(rfc);
		/* 121 */ args2.add(Integer.valueOf(Integer.parseInt(version)));
		/*     */
		/* 123 */ DBManagerSaie.insertUpdate(sql, DBSaie.VISOR, args);
		/* 124 */ DBManagerSaie.insertUpdate(sql2, DBSaie.VISOR, args2);
		/*     */
		/* 126 */ return resultado;
		/*     */ }

	/*     */
	/*     */ public void reporteBiometristaDuplicidad(ReporteBiometristaDTO request) throws SQLException {
		/* 130 */ String sql = "INSERT INTO resolucion (caso_numero_caso, fecha_hora_carga, reporte_url, operador, firma_reporte, tipo_resolucion, id_tipo_observacion,id_reporte, cadena_original, caso_sat)values (?,?,?,?,?,?,?,?,?,?);";
		/*     */
		/* 132 */ List<Object> args = new ArrayList<Object>();
		/* 133 */ args.add(request.getCaso());
		/* 134 */ args.add(new Timestamp((new Date()).getTime()));
		/* 135 */ args.add(request.getUrl());
		/* 136 */ args.add(request.getOperador());
		/* 137 */ args.add(request.getFirma());
		/* 138 */ args.add(Integer.valueOf(Integer.parseInt(request.getDictaminacion())));
		/* 139 */ args.add(Integer.valueOf(Integer.parseInt(request.getObservaciones())));
		/* 140 */ args.add(request.getIdReporte());
		/* 141 */ args.add(request.getCadenaOriginal());
		/* 142 */ args.add(new Integer(request.getCasoSat()));
		/* 143 */ List<String> sqls = new ArrayList<String>();
		/* 144 */ List<List<Object>> argsList = new ArrayList<List<Object>>();
		/* 145 */ sqls.add(sql);
		/* 146 */ argsList.add(args);
		/*     */
		/*     */
		/* 149 */ if (Integer.parseInt(request.getDictaminacion()) == 2
				|| Integer.parseInt(request.getDictaminacion()) == 3) {
			/* 150 */ String sqlafis = "UPDATE cola_afis SET estado = ? WHERE epid = ? AND RFC = ?;";
			/* 151 */ List<Object> argsafis = new ArrayList<Object>();
			/* 152 */ if (request.getTipoCaso() != null && request.getTipoCaso().equals("Suplantacion")) {
				/* 153 */ argsafis.add(Integer.valueOf(30));
				/*     */ } else {
				/* 155 */ argsafis.add(Integer.valueOf(20));
				/*     */ }
			/* 157 */ argsafis.add(request.getEpid());
			/* 158 */ argsafis.add(request.getRfc1());
			/* 159 */ sqls.add(sqlafis);
			/* 160 */ argsList.add(argsafis);
			/*     */ }
		/*     */
		/* 163 */ DBManagerSaie.insertUpdateTransaction(sqls, DBSaie.DUPLICIDAD, argsList);
		/*     */ }

	/*     */
	/*     */ public List<DatosDuplicidadDTO> consultarCatalogoDuplicidad() throws SQLException {
		/* 167 */ List<DatosDuplicidadDTO> resultado = new ArrayList<DatosDuplicidadDTO>();
		/* 168 */ String sql = "SELECT * FROM view_casos_duplicidad where CAST(caso_numero_caso AS INTEGER) > ?;";
		/* 169 */ List<Object> args = new ArrayList<Object>();
		/* 170 */ args.add(new Integer(ServiceDuplicidad.DUPLICADO_CASO));
		/*     */
		/* 172 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.DUPLICIDAD, args);
		/* 173 */ for (int i = 0; i < rs.size(); i++) {
			/*     */
			/*     */
			/*     */
			/*     */
			/* 178 */ DatosDuplicidadDTO datosdto = new DatosDuplicidadDTO(rs.getString(i, "caso_numero_caso"),
					rs.getString(i, "nombre_completo"), rs.getString(i, "rfc"),
					(rs.getTimestamp(i, "fecha_enrolamiento") != null)
							? dateFormat.format(rs.getTimestamp(i, "fecha_enrolamiento"))
							: "",
					rs.getString(i, "localidad_enrolamiento"), rs.getString(i, "nombre_completo2"),
					rs.getString(i, "rfc2"),
					(rs.getTimestamp(i, "fecha_enrolamiento2") != null)
							? dateFormat.format(rs.getTimestamp(i, "fecha_enrolamiento2"))
							: "",
					rs.getString(i, "localidad_enrolamiento2"), rs.getString(i, "reporte_url"),
					rs.getString(i, "observaciones_resolucion"), rs.getString(i, "tipo_resolucion"),
					rs.getString(i, "score"), rs.getString(i, "id_reporte"), rs.getString(i, "epid"),
					Integer.valueOf(rs.getInt(i, "caso_sat")));
			/* 179 */ resultado.add(datosdto);
			/* 180 */ if (Integer.parseInt(datosdto.getCaso_numero_caso()) > ServiceDuplicidad.DUPLICADO_CASO) {
				/* 181 */ ServiceDuplicidad.DUPLICADO_CASO = Integer.parseInt(datosdto.getCaso_numero_caso());
				/*     */ }
			/*     */ }
		/* 184 */ return resultado;
		/*     */ }

	/*     */
	/*     */
	/*     */ public String insertarBitacoraFolio(String documentos_descargados, String operador) throws SQLException {
		/* 189 */ String sql = "INSERT INTO bitacora_descarga (documentos_descargados, operador) values (?,?);";
		/*     */
		/* 191 */ List<Object> args = new ArrayList<Object>();
		/* 192 */ args.add(documentos_descargados);
		/* 193 */ args.add(operador);
		/*     */
		/* 195 */ DBManagerSaie.insertUpdate(sql, DBSaie.DUPLICIDAD, args);
		/*     */
		/* 197 */ return "OK";
		/*     */ }

	/*     */
	/*     */ public List<DatosEnrolamientoDTO> buscarRFC(String rfc) throws SQLException {
		/* 201 */ List<DatosEnrolamientoDTO> resultado = new ArrayList<DatosEnrolamientoDTO>();
		/* 202 */ String sql = "SELECT * FROM view_busqueda_principal WHERE rfc LIKE ? LIMIT 20;";
		/* 203 */ List<Object> args = new ArrayList<Object>();
		/* 204 */ args.add(rfc + "%");
		/*     */
		/* 206 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.VISOR, args);
		/*     */
		/* 208 */ for (int i = 0; i < rs.size(); i++) {
			/*     */
			/* 210 */ DatosEnrolamientoDTO datosdto = new DatosEnrolamientoDTO(rs.getString(i, "rfc"),
					rs.getString(i, "curp"), rs.getString(i, "nombre_razonsocial"),
					(rs.getTimestamp(i, "fecha_enrolamiento") != null)
							? dateFormat.format(rs.getTimestamp(i, "fecha_enrolamiento"))
							: "",
					rs.getString(i, "localidad_enrolamiento"), rs.getString(i, "unidad_enrolamiento"), null);
			/* 211 */ resultado.add(datosdto);
			/*     */ }
		/*     */
		/* 214 */ return resultado;
		/*     */ }

	/*     */
	/*     */ public List<DatosEnrolamientoDTO> buscarCURP(String curp) throws SQLException {
		/* 218 */ List<DatosEnrolamientoDTO> resultado = new ArrayList<DatosEnrolamientoDTO>();
		/* 219 */ String sql = "SELECT * FROM view_busqueda_principal WHERE curp LIKE ? LIMIT 20;";
		/* 220 */ List<Object> args = new ArrayList<Object>();
		/* 221 */ args.add(curp + "%");
		/*     */
		/* 223 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.VISOR, args);
		/*     */
		/* 225 */ for (int i = 0; i < rs.size(); i++) {
			/*     */
			/* 227 */ DatosEnrolamientoDTO datosdto = new DatosEnrolamientoDTO(rs.getString(i, "rfc"),
					rs.getString(i, "curp"), rs.getString(i, "nombre_razonsocial"),
					(rs.getTimestamp(i, "fecha_enrolamiento") != null)
							? dateFormat.format(rs.getTimestamp(i, "fecha_enrolamiento"))
							: "",
					rs.getString(i, "localidad_enrolamiento"), rs.getString(i, "unidad_enrolamiento"), null);
			/* 228 */ resultado.add(datosdto);
			/*     */ }
		/*     */
		/* 231 */ return resultado;
		/*     */ }

	/*     */
	/*     */ public List<DatosEnrolamientoDTO> buscarNombre(String nombre) throws SQLException {
		/* 235 */ List<DatosEnrolamientoDTO> resultado = new ArrayList<DatosEnrolamientoDTO>();
		/* 236 */ String sql = "SELECT * FROM view_busqueda_principal WHERE nombre_razonsocial LIKE ? LIMIT 20;";
		/* 237 */ List<Object> args = new ArrayList<Object>();
		/* 238 */ args.add("%" + nombre + "%");
		/*     */
		/* 240 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.VISOR, args);
		/*     */
		/* 242 */ for (int i = 0; i < rs.size(); i++) {
			/*     */
			/* 244 */ DatosEnrolamientoDTO datosdto = new DatosEnrolamientoDTO(rs.getString(i, "rfc"),
					rs.getString(i, "curp"), rs.getString(i, "nombre_razonsocial"),
					(rs.getTimestamp(i, "fecha_enrolamiento") != null)
							? dateFormat.format(rs.getTimestamp(i, "fecha_enrolamiento"))
							: "",
					rs.getString(i, "localidad_enrolamiento"), rs.getString(i, "unidad_enrolamiento"), null);
			/* 245 */ resultado.add(datosdto);
			/*     */ }
		/*     */
		/* 248 */ return resultado;
		/*     */ }

	/*     */
	/*     */ public List<VersionEnrolamientoGeneralDTO> listarVersiones(String rfc) throws SQLException {
		/* 252 */ List<VersionEnrolamientoGeneralDTO> resultado = new ArrayList<VersionEnrolamientoGeneralDTO>();
		/* 253 */ String sql = "SELECT * FROM view_busqueda_version WHERE rfc = ?;";
		/* 254 */ List<Object> args = new ArrayList<Object>();
		/* 255 */ args.add(rfc);
		/*     */
		/* 257 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.VISOR, args);
		/* 258 */ for (int i = 0; i < rs.size(); i++) {
			/*     */
			/*     */
			/*     */
			/* 262 */ VersionEnrolamientoGeneralDTO versiondto = new VersionEnrolamientoGeneralDTO(
					(rs.getTimestamp(i, "fecha_enrolamiento") != null)
							? dateFormat.format(rs.getTimestamp(i, "fecha_enrolamiento"))
							: "",
					rs.getString(i, "localidad_enrolamiento"), rs.getString(i, "unidad_enrolamiento"),
					rs.getString(i, "tipo_enrolamiento"), rs.getString(i, "tipo_movimiento"),
					rs.getString(i, "detalle_tipo_enrolamiento"), rs.getString(i, "nombre_razonsocial"),
					rs.getString(i, "representante_legal"), rs.getString(i, "archivo"), rs.getInt(i, "historico"),
					rs.getInt(i, "estatus_validacion_actualizacion"), rs.getInt(i, "version"),
					rs.getString(i, "estacion_enrolamiento_estatus"), rs.getString(i, "estacion_proceso_estatus"));
			/* 263 */ if (versiondto.getHistorico() == 1) {
				/* 264 */ sql = "select filename from view_documento_rfc where rfc_enrolamiento = ? and version = ? AND historico = ?;";
				/* 265 */ args = new ArrayList<Object>();
				/* 266 */ args.add(rfc);
				/* 267 */ args.add(Integer.valueOf(versiondto.getVersion()));
				/* 268 */ args.add(Integer.valueOf(versiondto.getHistorico()));
				/* 269 */ DBSaieResult rs2 = DBManagerSaie.select(sql, DBSaie.VISOR, args);
				/* 270 */ List<String> archivos = new ArrayList<String>();
				/* 271 */ for (int j = 0; j < rs2.size(); j++) {
					/* 272 */ String filename = rs2.getString(j, "filename");
					/* 273 */ if (filename.contains("\\")) {
						/* 274 */ filename = filename.replace("\\", "/");
						/*     */ }
					/* 276 */ archivos.add(filename);
					/*     */ }
				/* 278 */ versiondto.setArchivos(archivos);
				/* 279 */ resultado.add(versiondto);
				/*     */ } else {
				/* 281 */ String directorio = versiondto.getArchivo().replace("/datospersonales.xml", "");
				/* 282 */ File fdirectorio = new File(directorio);
				/* 283 */ List<String> archivos = new ArrayList<String>();
				/* 284 */ if (fdirectorio.exists() && fdirectorio.isDirectory()) {
					/* 285 */ String[] nomArchivos = fdirectorio.list();
					/* 286 */ for (int k = 0; k < nomArchivos.length; k++) {
						/* 287 */ archivos.add(directorio + "/" + nomArchivos[k]);
						/*     */ }
					/*     */ }
				/* 290 */ versiondto.setArchivos(archivos);
				/* 291 */ resultado.add(versiondto);
				/*     */ }
			/*     */ }
		/* 294 */ return resultado;
		/*     */ }

	/*     */
	/*     */ public VersionEnrolamientoGeneralDTO getVersionUno(String rfc) throws SQLException {
		/* 298 */ String sql = "SELECT * FROM view_busqueda_version where rfc = ? AND version = 1 LIMIT 1;";
		/* 299 */ List<Object> args = new ArrayList<Object>();
		/* 300 */ args.add(rfc);
		/*     */
		/* 302 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.VISOR, args);
		/* 303 */ VersionEnrolamientoGeneralDTO versiondto = null;
		/* 304 */ if (rs.size() > 0) {
			/*     */
			/*     */
			/*     */
			/* 308 */ versiondto = new VersionEnrolamientoGeneralDTO(
					(rs.getTimestamp(0, "fecha_enrolamiento") != null)
							? dateFormat.format(rs.getTimestamp(0, "fecha_enrolamiento"))
							: "",
					rs.getString(0, "localidad_enrolamiento"), rs.getString(0, "unidad_enrolamiento"),
					rs.getString(0, "tipo_enrolamiento"), rs.getString(0, "tipo_movimiento"),
					rs.getString(0, "detalle_tipo_enrolamiento"), rs.getString(0, "nombre_razonsocial"),
					rs.getString(0, "representante_legal"), rs.getString(0, "archivo"), rs.getInt(0, "historico"),
					rs.getInt(0, "estatus_validacion_actualizacion"), rs.getInt(0, "version"),
					rs.getString(0, "estacion_enrolamiento_estatus"), rs.getString(0, "estacion_proceso_estatus"));
			/* 309 */ if (versiondto.getHistorico() == 1) {
				/* 310 */ sql = "select filename from view_documento_rfc where rfc_enrolamiento = ? and version = ? AND historico = ?;";
				/* 311 */ args = new ArrayList<Object>();
				/* 312 */ args.add(rfc);
				/* 313 */ args.add(Integer.valueOf(versiondto.getVersion()));
				/* 314 */ args.add(Integer.valueOf(versiondto.getHistorico()));
				/* 315 */ DBSaieResult rs2 = DBManagerSaie.select(sql, DBSaie.VISOR, args);
				/* 316 */ List<String> archivos = new ArrayList<String>();
				/* 317 */ for (int j = 0; j < rs2.size(); j++) {
					/* 318 */ String filename = rs2.getString(j, "filename");
					/* 319 */ if (filename.contains("\\")) {
						/* 320 */ filename = filename.replace("\\", "/");
						/*     */ }
					/* 322 */ archivos.add(filename);
					/*     */ }
				/* 324 */ versiondto.setArchivos(archivos);
				/*     */ } else {
				/* 326 */ String directorio = versiondto.getArchivo().replace("/datospersonales.xml", "");
				/* 327 */ File fdirectorio = new File(directorio);
				/* 328 */ List<String> archivos = new ArrayList<String>();
				/* 329 */ if (fdirectorio.exists() && fdirectorio.isDirectory()) {
					/* 330 */ String[] nomArchivos = fdirectorio.list();
					/* 331 */ for (int k = 0; k < nomArchivos.length; k++) {
						/* 332 */ archivos.add(directorio + "/" + nomArchivos[k]);
						/*     */ }
					/*     */ }
				/* 335 */ versiondto.setArchivos(archivos);
				/*     */ }
			/*     */ }
		/* 338 */ return versiondto;
		/*     */ }

	/*     */
	/*     */ public List<String> obtenerRoles(List<String> roles) throws SQLException {
		/* 342 */ List<String> rolesResult = new ArrayList<String>();
		/* 343 */ if (roles != null && roles.size() > 0) {
			/* 344 */ String sql = "select * from cat_grupo where ";
			/* 345 */ List<Object> args = new ArrayList<Object>();
			/* 346 */ for (int i = 0; i < roles.size(); i++) {
				/* 347 */ System.out.println("rolLDAP: " + (String) roles.get(i));
				/* 348 */ if (i != 0) {
					/* 349 */ sql = sql + " or ";
					/*     */ }
				/* 351 */ sql = sql + "id_ldap = ?";
				/* 352 */ args.add(roles.get(i));
				/*     */ }
			/* 354 */ sql = sql + ";";
			/* 355 */ DBSaieResult rs = DBManagerSaie.select(sql, DBSaie.DUPLICIDAD, args);
			/* 356 */ for (int i = 0; i < rs.size(); i++) {
				/* 357 */ String rol = rs.getString(i, "nombre_grupo");
				/* 358 */ System.out.println("rolBD: " + rol);
				/* 359 */ rolesResult.add(rol);
				/*     */ }
			/*     */ }
		/*     */
		/* 363 */ return rolesResult;
		/*     */ }
	/*     */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\dao\DBManager.class Java compiler version: 8
 * (52.0) JD-Core Version: 1.0.7
 */