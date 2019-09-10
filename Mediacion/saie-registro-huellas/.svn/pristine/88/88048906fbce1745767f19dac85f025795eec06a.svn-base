package com.iecisa.sat.saie.service.rest.dao;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iecisa.sat.saie.audit.dto.DBSaie;
import com.iecisa.sat.saie.audit.dto.DBSaieResult;
import com.iecisa.sat.saie.dao.DBManager;
import com.iecisa.sat.saie.dao.DBManagerDuplicados;
import com.iecisa.sat.saie.dao.DBManagerSaie;
import com.iecisa.sat.saie.service.abis.client.dto.ColaAfisDTO;
import com.iecisa.sat.saie.service.abis.client.dto.TIPO_REFERENCIA;
import com.iecisa.sat.saie.service.rest.dto.RegistrarFinEnrolamiento;

public class FinEnrolamientoDAO {

	private static FinEnrolamientoDAO instance = null;
	private static final Logger logger = LoggerFactory.getLogger(FinEnrolamientoDAO.class);
	
	public static FinEnrolamientoDAO getInstance(){
		if(instance == null){
			instance = new FinEnrolamientoDAO();
		}
		return instance;
	}
	
	public void registrarFinEnrolamiento(RegistrarFinEnrolamiento request, String directorio){
		try{
			//checa version
			String sqlsel = "select max(version) as version from mm_rfc where rfc_enrolamiento = ?;";
			List<Object> args = new ArrayList<Object>();
			args.add(request.getRfc());
			DBSaieResult rs = DBManagerSaie.select(sqlsel, DBSaie.VISOR, args);
			int version = rs.getInt(0,"version");
			version = version > -1 ? version : 0;
			//checa si ya existe una versión que coincida con el ePid
			String sqlcheck = "select * from det_dato_enrolamiento where rfc_enrolamiento = ? and ep_id = ?";
			args = new ArrayList<Object>();
			args.add(request.getRfc());
			args.add(request.getePid());
			rs = DBManagerSaie.select(sqlcheck, DBSaie.VISOR, args);
			boolean cumplidoActualiza = false;
			boolean guardadoActualiza = true;
			boolean canceladoActualiza = false;
			List<String> sqls = new ArrayList<String>();
			List<List<Object>> argsList = new ArrayList<List<Object>>();
			int estatusValidacionActualizacion = 1;
			
			String listaArchivos = "";
			if (request.getePType() != null) {
				if (request.getePType().contains("ActualizaBio")){
					listaArchivos = "Biométrica";	
				}else if (request.getePType().contains("ActualizaDoc")) {
					listaArchivos = "Documental";
				}
			}
			
			// si estado enrolamiento es CUMPLIDO y tipo enrolamiento es (ActualizaBioFisica,ActualizaDocFisica,ActualizaDocMoral) pasa a estatus de validacion de actualizacion
			if (request.geteStatus() != null && request.getePType() != null){
				if(request.getePType().startsWith("Actualiza")){
					estatusValidacionActualizacion = 3; // Pendiente Validacion
					if(request.geteStatus().equals("CUMPLIDO")){
						cumplidoActualiza = true;
					}else if(request.geteStatus().equals("CANCELADO")){
						canceladoActualiza = true;
					}
				}
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			Timestamp fechaIniciot = null;
			if (request.getFechaInicio() != null && !request.getFechaInicio().trim().isEmpty()){
				Date fechaInicio = dateFormat.parse(request.getFechaInicio());
				fechaIniciot = new Timestamp(fechaInicio.getTime());
			}
			
			Timestamp fechaFint = null;
			if (request.getFechaFin() != null && !request.getFechaFin().trim().isEmpty()){
				Date fechaFin = dateFormat.parse(request.getFechaFin());
				fechaFint = new Timestamp(fechaFin.getTime());
			}
			
			Timestamp fechaCargat = null;
			if (request.getFechaCarga() != null && !request.getFechaCarga().trim().isEmpty()){
				Date fechaCarga = dateFormat.parse(request.getFechaCarga());
				fechaCargat = new Timestamp(fechaCarga.getTime());
			}
			
			if(rs.size() > 0){
				guardadoActualiza = !(rs.getString(0, "estacion_enrolamiento_estatus") != null && rs.getString(0, "estacion_enrolamiento_estatus").equals("CUMPLIDO"));
				//Actualiza lo correspondiente en det_dato
				String sqlUpdateDatosEnrolamiento = "UPDATE det_dato_enrolamiento SET fecha_enrolamiento_fin= ?, fecha_carga_enrolamiento= ?, "
						+ "estacion_enrolamiento_estatus= ?, estacion_proceso_estatus = ? WHERE rfc_enrolamiento= ? AND ep_id= ?;";
				args = new ArrayList<Object>();
				args.add(fechaFint);
				args.add(fechaCargat);
				args.add(request.geteStatus());
				args.add(request.geteStatusProceso());
				args.add(request.getRfc());
				args.add(request.getePid());
				argsList.add(args);
				sqls.add(sqlUpdateDatosEnrolamiento);
			}else{
				version++;
				//insert en mm_rfc
				String sql = "insert into mm_rfc (rfc_enrolamiento,docto_1_,version,historico) values (?,?,?,0);";
				args = new ArrayList<Object>();
				args.add(request.getRfc());
				args.add(request.geteUri());
				args.add(version);
				argsList.add(args);
				sqls.add(sql);
				//insert en det_dato_enrolamiento
				
				sql = "insert into det_dato_enrolamiento (filename,rfc_enrolamiento,nombre_contribuyente,t_estacion_enrolamiento,o_estacion_enrolamiento,"
						+ "t_localidad_enrolamiento,o_localidad_enrolamiento,fecha_enrolamiento_inicio,fecha_enrolamiento_fin,fecha_carga_enrolamiento,"
						+ "t_tipo_enrolamiento,rfc_asociado,version,curp,tipo_movimiento,detalle_tipo_enrolamiento,motivo_cierre,"
						+ "comentarios_cierre,ep_id,estatus_validacion_actualizacion,estacion_enrolamiento_estatus,estacion_proceso_estatus) "
						+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				args = new ArrayList<Object>();
				args.add(request.geteUri());
				args.add(request.getRfc());
				args.add(request.getNombre());
				args.add(request.getEstacion());
				args.add(request.getDescripcionEstacion());
				args.add(request.getLocalidad());
				args.add(request.getDescripcionLocalidad());
				args.add(fechaIniciot);
				args.add(fechaFint);
				args.add(fechaCargat);
				args.add((request.getPersonType().toUpperCase().equals("F") ? "FISICA" : "MORAL"));
				args.add("");
				args.add(version);
				args.add(request.getCurp());
				args.add(request.getePType());
				args.add(request.getePTypeDetail());
				args.add(request.getMotivoCierre());
				args.add(request.getComentariosCierre());
				args.add(request.getePid());
				args.add(estatusValidacionActualizacion);
				args.add(request.geteStatus());
				args.add(request.geteStatusProceso());
				argsList.add(args);
				sqls.add(sql);
				//si tiene representante insert en det_rfc_asociado
				if(request.getRfcRepresentante() != null && !request.getRfcRepresentante().equals("")){
					String sqlrep = "insert into det_rfc_asociado (rfc_enrolamiento,rfc_asociado,version) "
							+ "values(?,?,?);";
					args = new ArrayList<Object>();
					args.add(request.getRfc());
					args.add(request.getRfcRepresentante());
					args.add(version);
					argsList.add(args);
					sqls.add(sqlrep);
				}
				guardadoActualiza = true;
			}
			
			if (cumplidoActualiza && guardadoActualiza){
				String sqlInsert = "INSERT INTO control_versiones(operador, rfc_contribuyente, lista_documentos, fecha_actualizacion, "
						+ "unidad_actualizacion, localidad_actualizacion, justificacion_actualizacion, tipo_enrolamiento, estatus_validacion_actualizacion, comentarios, "
						+ "motivo_cancelacion, num_version) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
				args = new ArrayList<Object>();
				args.add(null);
				args.add(request.getRfc());
				args.add(listaArchivos);
				args.add(fechaFint);
				args.add(request.getEstacion());
				args.add(request.getLocalidad());
				args.add("N/A");
				args.add(request.getePType());
				args.add(estatusValidacionActualizacion);
				args.add(request.getComentariosCierre());
				args.add(request.getMotivoCierre());
				args.add(version);
				argsList.add(args);
				sqls.add(sqlInsert);
			}else if(canceladoActualiza){
				String sqlDel = "DELETE FROM control_versiones WHERE rfc_contribuyente = ? AND num_version = ?;";
				args = new ArrayList<Object>();
				args.add(request.getRfc());
				args.add(rs.getInt(0, "version"));
				argsList.add(args);
				sqls.add(sqlDel);
				
				//WARNING: Por separación de clases esta funcionalidad no debería estar contenida aquí
				try{
					URL obj = new URL("http://localhost:8080/fiel/visor/actualizaValidaciones");
					HttpURLConnection conexion = (HttpURLConnection)obj.openConnection();
			
					//request headers
					conexion.setRequestMethod("POST");
					conexion.setRequestProperty("User-Agent", "Mozilla/5.0");
					conexion.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
					
					String urlParameters = "rfc="+request.getRfc()+"&version="+rs.getInt(0, "version");
					
					// Send post request
					conexion.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(conexion.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
			
					conexion.getResponseCode();
				}catch(Exception e){}
				//FIN WARNING
			}else{
				logger.info("ACTUALIZANDO VERSION PREVIA: " + request.getRfc());
			}
			
			DBManagerSaie.insertUpdateTransaction(sqls, DBSaie.VISOR, argsList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean encolarHuellasAfis(String rfc, String eUri, String epid){
		String sql = "INSERT INTO cola_afis(rfc, euri, estado, reintento, fecha_peticion, fecha_reintento, epid) VALUES (?,?,?,?,?,?,?);";
		try(Connection connDuplicados = DBManagerDuplicados.getInstance().ds.getConnection();
				PreparedStatement psInsertCola = connDuplicados.prepareStatement(sql)){
			psInsertCola.setString(1, rfc);
			psInsertCola.setString(2, eUri);
			psInsertCola.setInt(3, 0);
			psInsertCola.setInt(4, 0);
			psInsertCola.setTimestamp(5, new Timestamp(new Date().getTime()));
			psInsertCola.setTimestamp(6, new Timestamp(new Date().getTime()));
			psInsertCola.setString(7, epid);
			psInsertCola.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean actualizaColaHuellasAfis(ColaAfisDTO coladto){
		String sql = "UPDATE cola_afis SET estado = ?, fecha_reintento = ?, reintento = ? WHERE id = ?";
		try(Connection connDuplicados = DBManagerDuplicados.getInstance().ds.getConnection();
				PreparedStatement psActualizaCola = connDuplicados.prepareStatement(sql);){
			psActualizaCola.setInt(1, coladto.getEstado());
			psActualizaCola.setTimestamp(2, coladto.getFechaReintento());
			psActualizaCola.setInt(3,coladto.getReintento());
			psActualizaCola.setInt(4, coladto.getId());
			psActualizaCola.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean desencolarHuellasAfis(ColaAfisDTO coladto){
		String sql = "DELETE FROM cola_afis where id = ?";
		logger.info("borrando id: "+coladto.getId());
		try(Connection connDuplicados = DBManagerDuplicados.getInstance().ds.getConnection();
				PreparedStatement psDesencolar = connDuplicados.prepareStatement(sql)){
			psDesencolar.setInt(1, coladto.getId());
			psDesencolar.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public List<ColaAfisDTO> getColaHuellasAfis(){
		String sql = "SELECT * FROM cola_afis WHERE estado IN (0,7) AND TIMESTAMP '"+new Date().toString()+"' > fecha_reintento;";
		
		List<ColaAfisDTO> cola = new ArrayList<ColaAfisDTO>();
		try(Connection connDuplicados = DBManagerDuplicados.getInstance().ds.getConnection();){
			DBSaieResult dbrs = DBManagerSaie.select(sql, DBSaie.DUPLICIDAD, null);
			for(int i = 0; i < dbrs.size(); i++){
				ColaAfisDTO coladto = new ColaAfisDTO(dbrs.getInt(i, "id"), dbrs.getString(i, "rfc"), dbrs.getString(i, "euri"), dbrs.getInt(i, "estado"), 
						dbrs.getInt(i, "reintento") > 5 ? 0 : dbrs.getInt(i, "reintento"), dbrs.getTimestamp(i, "fecha_peticion"), dbrs.getTimestamp(i, "fecha_reintento"),
						dbrs.getString(i, "epid"));
				cola.add(coladto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return cola;
	}
	
	public String registrarCasoDuplicado(String rfcAdjudicante, String rfcCandidato, int score, String ePid){
		logger.info(String.format("SCORE_INFO: %s %s %d", rfcAdjudicante, rfcCandidato, score));
		SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
		String log = "";
		int numeroCaso = 0;
		String statusPendiente = "Por Definir";
		
		//ADJUDICANTE
		String rfcEnrolamientoAdjudicante = null;
		String nombreContribuyenteAdjudicante = statusPendiente;
		String localidadAdjudicante = statusPendiente;
		String curpAdjudicante = statusPendiente;
		Date fechaInicioEnrolamientoAdjudicante = null;
		String unidadEnrolamientoAdjudicante = statusPendiente;
		
		//CANDIDATO
		String rfcEnrolamientoCandidato = null;
		String nombreContribuyenteCandidato = statusPendiente;
		String localidadCandidato = statusPendiente;
		String curpCandidato = statusPendiente;
		Date fechaInicioEnrolamientoCandidato = null;
		String unidadEnrolamientoCandidato = statusPendiente;
		
		String sqlNumCaso = "select nextval('seq_numero_caso')";
		
		String sqlQueryAdjudicant = String.format("select rfc, curp, nombre_razonsocial, fecha_enrolamiento, "
				+ "localidad_enrolamiento, unidad_enrolamiento from view_busqueda_principal "
				+ "where rfc = '%s';", rfcAdjudicante);
		
		String sqlQueryCandidato = String.format("select rfc, curp, nombre_razonsocial, fecha_enrolamiento, "
				+ "localidad_enrolamiento, unidad_enrolamiento from view_busqueda_principal "
				+ "where rfc = '%s';", rfcCandidato);
		
		String sqlInsertCaso = "INSERT INTO caso( "
				+ "numero_caso, cat_estado_caso_id, "
				+ "tipo_caso, score, epid) "
				+ "VALUES (?, 0, 'Posible duplicidad', "+score+", '"+ePid+"');";
		
		String sqlInsertReferencia = "INSERT INTO referencia( "
				+ "caso_numero_caso, nombre, apellido_paterno, "
				+ "apellido_materno, nombre_completo, rfc, fecha_enrolamiento, localidad_enrolamiento, tipo_referencia) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try (Connection connection = DBManager.getInstance().ds.getConnection();
				Connection connDuplicados = DBManagerDuplicados.getInstance().ds.getConnection();
				PreparedStatement psInsertCaso = connDuplicados.prepareStatement(sqlInsertCaso);
				PreparedStatement psInsertRef1 = connDuplicados.prepareStatement(sqlInsertReferencia);
				PreparedStatement psInsertRef2 = connDuplicados.prepareStatement(sqlInsertReferencia);
				Statement stm = connDuplicados.createStatement();
				ResultSet rs = stm.executeQuery(sqlNumCaso);
				Statement stmAdjudicante = connection.createStatement();
				ResultSet rsAdjudicante = stmAdjudicante.executeQuery(sqlQueryAdjudicant);
				Statement stmCandidato = connection.createStatement();
				ResultSet rsCandidato = stmCandidato.executeQuery(sqlQueryCandidato);
				) {
			
			// Consultando Numero de Caso
			while(rs.next()){
				numeroCaso = rs.getInt(1);
			}
			
			// Consultando Datos Adjudicante
			while(rsAdjudicante.next()){
				rfcEnrolamientoAdjudicante = rsAdjudicante.getString(1);
				curpAdjudicante = rsAdjudicante.getString(2);				
				nombreContribuyenteAdjudicante = rsAdjudicante.getString(3);
				fechaInicioEnrolamientoAdjudicante = rsAdjudicante.getTimestamp(4);
				localidadAdjudicante = rsAdjudicante.getString(5);
				unidadEnrolamientoAdjudicante = rsAdjudicante.getString(6);
			}
			
			// Consultando Datos Candidato
			while(rsCandidato.next()){
				rfcEnrolamientoCandidato = rsCandidato.getString(1);
				curpCandidato = rsCandidato.getString(2);
				nombreContribuyenteCandidato = rsCandidato.getString(3);
				fechaInicioEnrolamientoCandidato = rsCandidato.getTimestamp(4);
				localidadCandidato = rsCandidato.getString(5);
				unidadEnrolamientoCandidato = rsCandidato.getString(6);
			}
			
			// Agregando Caso
			psInsertCaso.setString(1, String.valueOf(numeroCaso));
			psInsertCaso.executeUpdate();
			
			// Insertando Adjudicante
			psInsertRef1.setString(1, String.valueOf(numeroCaso));
			psInsertRef1.setString(2, nombreContribuyenteAdjudicante);
			psInsertRef1.setString(3, "");
			psInsertRef1.setString(4, "");
			psInsertRef1.setString(5, nombreContribuyenteAdjudicante);
			psInsertRef1.setString(6, rfcEnrolamientoAdjudicante != null ? rfcEnrolamientoAdjudicante : rfcAdjudicante);
			Timestamp timestampInicioEnrolamientoAdjudicante = null;
			if (fechaInicioEnrolamientoAdjudicante != null){
				timestampInicioEnrolamientoAdjudicante = new Timestamp(fechaInicioEnrolamientoAdjudicante.getTime());
			}
			psInsertRef1.setTimestamp(7, timestampInicioEnrolamientoAdjudicante);
			psInsertRef1.setString(8, localidadAdjudicante);
			psInsertRef1.setInt(9, TIPO_REFERENCIA.ADJUDICANTE.getId());
			psInsertRef1.executeUpdate();
			
			// Insertando Candidato
			psInsertRef2.setString(1, String.valueOf(numeroCaso));
			psInsertRef2.setString(2, nombreContribuyenteCandidato);
			psInsertRef2.setString(3, "");
			psInsertRef2.setString(4, "");
			psInsertRef2.setString(5, nombreContribuyenteCandidato);
			psInsertRef2.setString(6, rfcEnrolamientoCandidato != null ? rfcEnrolamientoCandidato : rfcCandidato);
			Timestamp timestampInicioEnrolamientoCandidato = null;
			if (fechaInicioEnrolamientoCandidato != null){
				timestampInicioEnrolamientoCandidato = new Timestamp(fechaInicioEnrolamientoCandidato.getTime());
			}
			psInsertRef2.setTimestamp(7, timestampInicioEnrolamientoCandidato);
			psInsertRef2.setString(8, localidadCandidato);
			psInsertRef2.setInt(9, TIPO_REFERENCIA.CANDIDATO.getId());
			psInsertRef2.executeUpdate();
			
			log += ""+numeroCaso+", " + "#1#, ";
			log += curpAdjudicante + ", " + rfcAdjudicante + ", " + nombreContribuyenteAdjudicante + ", " + (fechaInicioEnrolamientoAdjudicante != null ? sdt.format(fechaInicioEnrolamientoAdjudicante) : statusPendiente) + ", ";
			log += unidadEnrolamientoAdjudicante + ", " + localidadAdjudicante + ", ";
			log += curpCandidato + ", " + rfcCandidato + ", " + nombreContribuyenteCandidato + ", " + (fechaInicioEnrolamientoCandidato != null ? sdt.format(fechaInicioEnrolamientoCandidato) : statusPendiente) + ", ";
			log += unidadEnrolamientoCandidato+ ", " + localidadCandidato;

		}catch(Exception e){
			e.printStackTrace();
		}
				
		return log;
	}
}
