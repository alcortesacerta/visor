/*      */ package com.iecisa.sat.saie.vf.integration.service.impl;

/*      */
/*      */ import com.iecisa.sat.saie.audit.AuditManager;
/*      */ import com.iecisa.sat.saie.audit.dto.AuditEvent;
/*      */ import com.iecisa.sat.saie.response.ServiceResponse;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dao.DBManager;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.AraResponseDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.ArchivoDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.BuscarEnrolamientoPorCriteriosDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.CatalogoDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.ConsultaAraRequestDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.ConsultaLdapRequestDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.ConsultaSiseRequestDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.ControlVersionesDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.DatosEnrolamientoDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.EstatusValidacionAutorizacionDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.Permisos;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.ReporteBiometristaDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoAdjuntosDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoDetalleDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO;
/*      */ import com.iecisa.sat.saie.vf.integration.service.iface.IServiceEnrolamiento;
/*      */ import com.iecisa.sat.saie.vf.integration.service.rest.ConsultarAraRestClient;
/*      */ import com.iecisa.sat.saie.vf.integration.service.rest.ConsultarLdapRestClient;
/*      */ import com.iecisa.sat.saie.vf.integration.service.rest.ConsultarRfcSiseRestClient;
/*      */ import com.itextpdf.text.BaseColor;
/*      */ import com.itextpdf.text.Chunk;
/*      */ //import com.itextpdf.text.Document;
/*      */ import com.itextpdf.text.DocumentException;
/*      */ import com.itextpdf.text.Font;
/*      */ import com.itextpdf.text.FontFactory;
/*      */ import com.itextpdf.text.Image;
/*      */ import com.itextpdf.text.Paragraph;
/*      */ import com.itextpdf.text.Rectangle;
/*      */ import com.itextpdf.text.pdf.BaseFont;
/*      */ import com.itextpdf.text.pdf.ColumnText;
/*      */ import com.itextpdf.text.pdf.PdfContentByte;
/*      */ import com.itextpdf.text.pdf.PdfReader;
/*      */ import com.itextpdf.text.pdf.PdfStamper;
/*      */ import com.itextpdf.text.pdf.PdfWriter;
/*      */ import com.itextpdf.text.pdf.RandomAccessFileOrArray;
/*      */ import com.itextpdf.text.pdf.codec.TiffImage;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
/*      */ import java.math.BigInteger;
/*      */ import java.security.PrivateKey;
/*      */ import java.security.Signature;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Base64;
/*      */ import java.util.Date;
import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
import java.util.Map;
/*      */ import java.util.UUID;
/*      */ import java.util.zip.ZipEntry;
/*      */ import java.util.zip.ZipOutputStream;
/*      */ import javax.crypto.BadPaddingException;
/*      */ import javax.imageio.IIOImage;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.imageio.ImageReader;
/*      */ import javax.imageio.ImageWriteParam;
/*      */ import javax.imageio.ImageWriter;
/*      */ import javax.imageio.stream.ImageInputStream;
/*      */ import javax.imageio.stream.MemoryCacheImageOutputStream;
/*      */ import javax.security.cert.CertificateExpiredException;
/*      */ import javax.security.cert.CertificateNotYetValidException;
/*      */ import javax.security.cert.X509Certificate;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import org.apache.commons.ssl.PKCS8Key;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.InputSource;

/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */ public class ServiceEnrolamiento/*      */ implements IServiceEnrolamiento
/*      */ {
	/* 99 */ public static int VALIDACION_AUTORIZACION_INDICE = 0;

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */ public ServiceResponse<List<ControlVersionesDTO>> getActualizacionesPendientes() {
		/* 126 */ ServiceResponse<List<ControlVersionesDTO>> response = new ServiceResponse<List<ControlVersionesDTO>>();
		/*      */
		/*      */ try {
			/* 129 */ List<ControlVersionesDTO> result = null;
			/* 130 */ result = DBManager.getInstance().catalogoActualizacionesPendientes();
			/*      */
			/* 132 */ response.setCode(0);
			/* 133 */ response.setMessage("OK");
			/* 134 */ response.setResult(result);
			/* 135 */ } catch (Exception e) {
			/* 136 */ e.printStackTrace();
			/* 137 */ response.setCode(-1);
			/* 138 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/*      */
		/* 141 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<String> validarFirmarFiel(InputStream certificado, InputStream llave, String pass,
			String textoPorFirmar, String rfcUsuario) {
		/* 145 */ ServiceResponse<String> response = new ServiceResponse<String>();
		/* 146 */ String rfccer = "";
		/* 147 */ String mensaje = null;
		/* 148 */ String firmaB64 = null;
		/*      */
		/*      */
		/*      */ try {
			/* 152 */ X509Certificate cer = X509Certificate.getInstance(certificado);
			/* 153 */ cer.checkValidity();
			/*      */
			/*      */
			/* 156 */ String cerdn = cer.getSubjectDN().toString();
			/* 157 */ int ind = cerdn.indexOf(".45=") + 4;
			/* 158 */ rfccer = cerdn.substring(ind, ind + 13);
			/*      */
			/* 160 */ if (rfcUsuario != null && !rfccer.equals(rfcUsuario)) {
				/* 161 */ mensaje = "La firma electrónica no corresponde al usuario actual";
				/*      */
				/*      */ }
			/* 164 */ else if (System.getProperty("SAIE_LOCAL") == null) {
				/* 165 */ BigInteger cerbi = new BigInteger(cer.getSerialNumber().toString());
				/* 166 */ byte[] dif = cerbi.toByteArray();
				/* 167 */ ConsultarAraRestClient araClient = new ConsultarAraRestClient();
				/* 168 */ ServiceResponse<AraResponseDTO> araresponse = araClient
						.getCertificadoAra(new ConsultaAraRequestDTO(new String(dif)));
				/* 169 */ if (araresponse.getCode() == 0) {
					/* 170 */ if (((AraResponseDTO) araresponse.getResult()).getEstado().equals("R")) {
						/* 171 */ mensaje = "El certificado está revocado";
						/* 172 */ } else if (((AraResponseDTO) araresponse.getResult()).getEstado().equals("C")) {
						/* 173 */ mensaje = "El certificado está cáduco";
						/* 174 */ } else if (!((AraResponseDTO) araresponse.getResult()).getEstado().equals("A")) {
						/* 175 */ mensaje = "El certificado no es válido";
						/*      */ }
					/*      */ }
				/*      */ }
			/*      */
			/*      */
			/* 181 */ if (mensaje == null) {
				/*      */
				/* 183 */ byte[] encKey = new byte[llave.available()];
				/* 184 */ llave.read(encKey);
				/*      */
				/* 186 */ PKCS8Key key = new PKCS8Key(encKey, pass.toCharArray());
				/* 187 */ PrivateKey pk = key.getPrivateKey();
				/*      */
				/*      */
				/* 190 */ Signature firma = Signature.getInstance("SHA256withRSA");
				/* 191 */ firma.initSign(pk);
				/*      */
				/* 193 */ firma.update(textoPorFirmar.getBytes("UTF-8"));
				/* 194 */ byte[] firmado = firma.sign();
				/* 195 */ firmaB64 = Base64.getEncoder().encodeToString(firmado);
				/*      */
				/* 197 */ firma.initVerify(cer.getPublicKey());
				/* 198 */ firma.update(textoPorFirmar.getBytes("UTF-8"));
				/* 199 */ if (!firma.verify(firmado)) {
					/* 200 */ mensaje = "La llave privada no concuerda con la información del certificado";
					/*      */ }
				/*      */ }
			/* 203 */ } catch (CertificateExpiredException e) {
			/* 204 */ mensaje = "El certificado no es válido, por favor corrobora la fecha de expiración";
			/* 205 */ } catch (CertificateNotYetValidException e) {
			/* 206 */ mensaje = "El certificado aún no es válido, por favor corrobora la fecha de expiración";
			/* 207 */ } catch (BadPaddingException e) {
			/* 208 */ mensaje = "La clave de la llave privada es incorrecta";
			/* 209 */ } catch (Exception e) {
			/* 210 */ mensaje = "Ocurrió un error, por favor inténtalo más tarde";
			/* 211 */ e.printStackTrace();
			/*      */ }
		/*      */
		/* 214 */ response.setCode((mensaje == null) ? 0 : -1);
		/* 215 */ response.setMessage((mensaje == null) ? rfccer : mensaje);
		/* 216 */ response.setResult(firmaB64);
		/* 217 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<Integer> setEstatusValidacionAutorizacion(
			EstatusValidacionAutorizacionDTO request, UsuarioDTO usuario) {
		/* 221 */ ServiceResponse<Integer> response = new ServiceResponse<Integer>();
		/*      */
		/*      */
		/* 224 */ AuditEvent aevent = new AuditEvent();
		/* 225 */ aevent.setUuid(UUID.randomUUID().toString());
		/* 226 */ aevent.setDateTime(new Date());
		/* 227 */ aevent.setUser(usuario.getRfc());
		/* 228 */ aevent.setIpAddress(usuario.getIp());
		/* 229 */ aevent.setMacAddress(usuario.getMac());
		/* 230 */ String amessage = request.getRfc() + ", ";
		/* 231 */ if (request.getEstatus_validacion_actualizacion().intValue() == 5) {
			/* 232 */ amessage = amessage + "ValidacionDeActualizacion,";
			/* 233 */ } else if (request.getEstatus_validacion_actualizacion().intValue() == 6) {
			/* 234 */ amessage = amessage + "AutorizacionDeActualizacion,";
			/* 235 */ } else if (request.getEstatus_validacion_actualizacion().intValue() == 4) {
			/* 236 */ amessage = amessage + "CancelaValidacionDeActualizacion,";
			/* 237 */ } else if (request.getEstatus_validacion_actualizacion().intValue() == 7) {
			/* 238 */ amessage = amessage + "CancelaAutorizacionDeActualizacion,";
			/*      */ }
		/* 240 */ amessage = amessage + "version:" + request.getVersion() + ", " + request.getComentarios() + ", "
				+ request.getCadenaOriginal() + ", " + request.getFirma();
		/* 241 */ aevent.setMessage(amessage);
		/* 242 */ AuditManager.register(aevent);
		/*      */
		/*      */ try {
			/* 245 */ Integer result = null;
			/* 246 */ if (request != null) {
				/* 247 */ result = DBManager.getInstance().estatusValidacionAutorizacion(request.getRfc(),
						request.getVersion(), request/* 248 */ .getEstatus_validacion_actualizacion(),
						request.getComentarios(), request.getMotivoCancelacion(), request/* 249 */ .getFirma(),
						request.getCadenaOriginal());
				/*      */
				/* 251 */ response.setCode(0);
				/* 252 */ response.setMessage("OK");
				/* 253 */ response.setResult(result);
				/*      */ }
			/*      */
			/* 256 */ } catch (Exception e) {
			/* 257 */ e.printStackTrace();
			/* 258 */ response.setCode(-1);
			/* 259 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/*      */
		/* 262 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<String> setReporteBiometristaDuplicados(ReporteBiometristaDTO request,
			UsuarioDTO usuario, String ePid) {
		/* 266 */ ServiceResponse<String> response = new ServiceResponse<String>();
		/*      */
		/*      */
		/* 269 */ AuditEvent aevent = new AuditEvent();
		/* 270 */ aevent.setUuid(UUID.randomUUID().toString());
		/* 271 */ aevent.setDateTime(new Date());
		/* 272 */ aevent.setUser(usuario.getRfc());
		/* 273 */ aevent.setIpAddress(usuario.getIp());
		/* 274 */ aevent.setMacAddress(usuario.getMac());
		/*      */
		/* 276 */ String amessage = usuario.getRfc() + ", " + request.getRfc1() + ", " + request.getRfc2() + ", "
				+ request.getDictaminacionValue() + ", " + request.getObservacionesValue() + ", "
				+ "ReporteBiometrista, epid=" + ePid + request.getCaso();
		/* 277 */ aevent.setMessage(amessage);
		/* 278 */ AuditManager.register(aevent);
		/*      */
		/*      */
		/*      */ try {
			/* 282 */ if (request != null) {
				/* 283 */ DBManager.getInstance().reporteBiometristaDuplicidad(request);
				/*      */
				/* 285 */ response.setCode(0);
				/* 286 */ response.setMessage("OK");
				/* 287 */ response.setResult("OK");
				/*      */ }
			/*      */
			/* 290 */ } catch (Exception e) {
			/* 291 */ e.printStackTrace();
			/* 292 */ response.setCode(-1);
			/* 293 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/* 295 */ return response;
		/*      */ }

	/*      */
	/*      */
	/*      */ public ServiceResponse<List<DatosEnrolamientoDTO>> getEnrolamientoPorCriterios(
			BuscarEnrolamientoPorCriteriosDTO request, boolean verExpuestos) {
		/* 300 */ ServiceResponse<List<DatosEnrolamientoDTO>> response = new ServiceResponse<List<DatosEnrolamientoDTO>>();
		/*      */
		/*      */ try {
			/* 303 */ List<DatosEnrolamientoDTO> result = null;
			/* 304 */ if (request.getRfc() != null) {
				/* 305 */ result = DBManager.getInstance().buscarRFC(request.getRfc());
				/* 306 */ } else if (request.getCurp() != null) {
				/* 307 */ result = DBManager.getInstance().buscarCURP(request.getCurp());
				/*      */ } else {
				/* 309 */ result = DBManager.getInstance().buscarNombre(request.getNombre());
				/*      */ }
			/* 311 */ response.setCode(0);
			/* 312 */ response.setMessage("OK");
			/* 313 */ response.setResult(result);
			/* 314 */ } catch (Exception e) {
			/* 315 */ e.printStackTrace();
			/* 316 */ response.setCode(-1);
			/* 317 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/*      */
		/* 320 */ return response;
		/*      */ }

	/*      */
	/*      */ public boolean getEnrolamientoSise(String rfc, String rfcusuario) {
		/* 324 */ ConsultarRfcSiseRestClient sise = new ConsultarRfcSiseRestClient();
		/* 325 */ ServiceResponse<String> response = sise.getConsultaSise(new ConsultaSiseRequestDTO(rfc, rfcusuario));
		/* 326 */ if (response.getCode() == 0) {
			/* 327 */ System.out.println("SISE: " + rfc + " " + (String) response.getResult());
			/* 328 */ return ((String) response.getResult()).equals("1");
			/*      */ }
		/* 330 */ return false;
		/*      */ }

	/*      */
	/*      */
	/*      */ public ServiceResponse<List<VersionEnrolamientoGeneralDTO>> getVersionEnrolamientoByRFC(String rfc) {
		/* 335 */ ServiceResponse<List<VersionEnrolamientoGeneralDTO>> response = new ServiceResponse<List<VersionEnrolamientoGeneralDTO>>();
		/*      */ try {
			/* 337 */ List<VersionEnrolamientoGeneralDTO> result = DBManager.getInstance().listarVersiones(rfc);
			/* 338 */ response.setCode(0);
			/* 339 */ response.setMessage("OK");
			/* 340 */ response.setResult(result);
			/* 341 */ } catch (Exception e) {
			/* 342 */ e.printStackTrace();
			/* 343 */ response.setCode(-1);
			/* 344 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/* 346 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<VersionEnrolamientoGeneralDTO> getVersionUnoByRFC(String rfc) {
		/* 350 */ ServiceResponse<VersionEnrolamientoGeneralDTO> response = new ServiceResponse<VersionEnrolamientoGeneralDTO>();
		/*      */ try {
			/* 352 */ VersionEnrolamientoGeneralDTO result = DBManager.getInstance().getVersionUno(rfc);
			/* 353 */ response.setCode(0);
			/* 354 */ response.setMessage("OK");
			/* 355 */ response.setResult(result);
			/* 356 */ } catch (Exception e) {
			/* 357 */ e.printStackTrace();
			/* 358 */ response.setCode(-1);
			/* 359 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/* 361 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<List<VersionEnrolamientoGeneralDTO>> getVersionEnrolamientoValidaByRFC(
			String rfc) {
		/* 365 */ ServiceResponse<List<VersionEnrolamientoGeneralDTO>> response = new ServiceResponse<List<VersionEnrolamientoGeneralDTO>>();
		/*      */ try {
			/* 367 */ List<VersionEnrolamientoGeneralDTO> resultTemp = DBManager.getInstance().listarVersiones(rfc);
			/* 368 */ List<VersionEnrolamientoGeneralDTO> result = new ArrayList<VersionEnrolamientoGeneralDTO>();
			/* 369 */ for (VersionEnrolamientoGeneralDTO version : resultTemp) {
				/* 370 */ if (version.getEstatus() == 1 || version.getEstatus() == 2 || version.getEstatus() == 6) {
					/* 371 */ result.add(version);
					/*      */ }
				/*      */ }
			/* 374 */ response.setCode(0);
			/* 375 */ response.setMessage("OK");
			/* 376 */ response.setResult(result);
			/* 377 */ } catch (Exception e) {
			/* 378 */ e.printStackTrace();
			/* 379 */ response.setCode(-1);
			/* 380 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/* 382 */ return response;
		/*      */ }

	/*      */
	/*      */ public void getVersionFotoFirma(VersionEnrolamientoGeneralDTO version, String rfc) {
		/* 386 */ ArchivoDTO archivo = null;
		/* 387 */ if (version.getHistorico() == 1) {
			/* 388 */ archivo = getImageData("FOTO", version, rfc, "FOTO");
			/*      */ } else {
			/* 390 */ archivo = getImageActualData("foto.j", version, rfc, "foto.j");
			/*      */ }
		/* 392 */ if (archivo == null) {
			/* 393 */ archivo = new ArchivoDTO();
			/* 394 */ archivo.setName("noaplica");
			/*      */ }
		/* 396 */ version.setFoto(archivo);
		/*      */
		/* 398 */ archivo = null;
		/* 399 */ if (version.getHistorico() == 1) {
			/* 400 */ archivo = getImageTiffData("FIRMA", version, rfc, "FIRMA");
			/*      */ } else {
			/* 402 */ archivo = getImageActualData("firma.jpg", version, rfc, "firma.jpg");
			/* 403 */ if (archivo == null || archivo.getData() == null) {
				/* 404 */ archivo = getImageTiffActualData("firma.tif", version, rfc, "firma.tif");
				/*      */ }
			/*      */ }
		/* 407 */ if (archivo == null) {
			/* 408 */ archivo = new ArchivoDTO();
			/* 409 */ archivo.setName("noaplica");
			/*      */ }
		/* 411 */ version.setFirma(archivo);
		/*      */ }

	/*      */
	/*      */
	/* 415 */ public ServiceResponse<VersionEnrolamientoDetalleDTO> getVersionEnrolamientoByEPID(String request) {
		return null;
	}

	/*      */
	/*      */ public ServiceResponse<List<ArchivoDTO>> getHuellas(VersionEnrolamientoGeneralDTO version, String rfc) {
		/*      */ String[] huellaFiles;
		/* 419 */ ServiceResponse<List<ArchivoDTO>> response = new ServiceResponse<List<ArchivoDTO>>();
		/* 420 */ List<ArchivoDTO> huellas = new ArrayList<ArchivoDTO>();
		/*      */
		/*      */
		/* 423 */ String[] huellaIds = { "H10", "H09", "H08", "H07", "H06", "H01", "H02", "H03", "H04", "H05" };
		/*      */
		/* 425 */ String[] huellaIdsAux = { "h10", "h09", "h08", "h07", "h06", "h01", "h02", "h03", "h04", "h05" };
		/*      */
		/* 427 */ if (version.getHistorico() == 1) {
			/* 428 */ huellaFiles = new String[] { "MEN_IZQ_RAW", "ANU_IZQ_RAW", "MED_IZQ_RAW", "IND_IZQ_RAW",
					"PUL_IZQ_RAW", "PUL_DER_RAW", "IND_DER_RAW", "MED_DER_RAW", "ANU_DER_RAW", "MEN_DER_RAW" };
			/*      */ }
		/*      */ else {
			/*      */
			/* 432 */ huellaFiles = new String[] { "h10.p", "h09.p", "h08.p", "h07.p", "h06.p", "h01.p", "h02.p",
					"h03.p", "h04.p", "h05.p" };
			/*      */ }
		/*      */
		/* 435 */ ArchivoDTO archivo = null;
		/* 436 */ for (int i = 0; i < huellaIds.length; i++) {
			/* 437 */ if (version.getHistorico() == 1) {
				/* 438 */ archivo = getImageData(huellaFiles[i], version, rfc, huellaIds[i]);
				/*      */ } else {
				/* 440 */ archivo = getImageActualData(huellaFiles[i], version, rfc, huellaIds[i]);
				/*      */
				/* 442 */ try {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					/* 443 */ DocumentBuilder db = dbf.newDocumentBuilder();
					/* 444 */ Document dom = db
							.parse(new InputSource(new FileReader(((String) version.getArchivos().get(0)).substring(0,
									((String) version.getArchivos().get(0)).lastIndexOf("/")) + "/huellas.xml")));
					/* 445 */ NodeList nodeList = dom.getElementsByTagName("property");
					/* 446 */ for (int j = 0; j < nodeList.getLength(); j++) {
						/* 447 */ Element e = (Element) nodeList.item(j);
						/* 448 */ if (e.getAttribute("key").equals(huellaIdsAux[i] + "_status")) {
							/*      */
							/* 450 */ String valor = e.getAttribute("value");
							/* 451 */ if (!valor.equals("OK")) {
								/* 452 */ if (archivo == null) {
									/* 453 */ archivo = new ArchivoDTO();
									/*      */ }
								/* 455 */ archivo.setId(huellaIds[i]);
								/* 456 */ archivo.setName(valor.equals("XX") ? "amputado" : (
								/* 457 */ valor.equals("UP") ? "vendado" : "noaplica"));
								/*      */ }
							/*      */
							/*      */ }
						/*      */ }
				}
				/* 462 */ catch (FileNotFoundException | IndexOutOfBoundsException fileNotFoundException) {
				}
				/* 463 */ catch (Exception e)
				/* 464 */ {
					e.printStackTrace();
				}
				/*      */
				/*      */ }
			/* 467 */ if (archivo != null) {
				/* 468 */ huellas.add(archivo);
				/*      */ } else {
				/* 470 */ archivo = new ArchivoDTO();
				/* 471 */ archivo.setId(huellaIds[i]);
				/* 472 */ archivo.setName("noaplica");
				/* 473 */ huellas.add(archivo);
				/*      */ }
			/*      */ }
		/*      */
		/* 477 */ response.setCode(0);
		/* 478 */ response.setMessage("OK");
		/* 479 */ response.setResult(huellas);
		/* 480 */ return response;
		/*      */ }

	/*      */ public ServiceResponse<List<ArchivoDTO>> getIris(VersionEnrolamientoGeneralDTO version, String rfc) {
		/*      */ String[] irisFiles;
		/* 484 */ ServiceResponse<List<ArchivoDTO>> response = new ServiceResponse<List<ArchivoDTO>>();
		/* 485 */ List<ArchivoDTO> iris = new ArrayList<ArchivoDTO>();
		/*      */
		/*      */
		/* 488 */ String[] irisIds = { "IOZ", "IOD" };
		/* 489 */ String[] irisIdsAux = { "irisizq", "irisder" };
		/* 490 */ if (version.getHistorico() == 1) {
			/* 491 */ irisFiles = new String[] { "IRIS_OJO_IZQ", "IRIS_OJO_DER" };
			/*      */ } else {
			/* 493 */ irisFiles = new String[] { "irisizq.tif", "irisder.tif" };
			/*      */ }
		/*      */
		/* 496 */ ArchivoDTO archivo = null;
		/* 497 */ for (int i = 0; i < irisIds.length; i++) {
			/* 498 */ if (version.getHistorico() == 1) {
				/* 499 */ archivo = getImageTiffData(irisFiles[i], version, rfc, irisIds[i]);
				/*      */ } else {
				/* 501 */ archivo = getImageTiffActualData(irisFiles[i], version, rfc, irisIds[i]);
				/*      */
				/*      */
				/* 504 */ try {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					/* 505 */ DocumentBuilder db = dbf.newDocumentBuilder();
					/* 506 */ Document dom = db
							.parse(new InputSource(new FileReader(((String) version.getArchivos().get(0)).substring(0,
									((String) version.getArchivos().get(0)).lastIndexOf("/")) + "/iris.xml")));
					/* 507 */ NodeList nodeList = dom.getElementsByTagName("property");
					/* 508 */ for (int j = 0; j < nodeList.getLength(); j++) {
						/* 509 */ Element e = (Element) nodeList.item(j);
						/* 510 */ if (e.getAttribute("key").equals(irisIdsAux[i] + "_status")) {
							/* 511 */ String valor = e.getAttribute("value");
							/* 512 */ if (!valor.equals("OK")) {
								/* 513 */ if (archivo == null) {
									/* 514 */ archivo = new ArchivoDTO();
									/*      */ }
								/* 516 */ archivo.setId(irisIds[i]);
								/* 517 */ archivo.setName(valor.equals("XX") ? "amputado"
										: (valor.equals("UP") ? "vendado" : "noaplica"));
								/*      */ }
							/*      */ }
						/*      */ }
				}
				/* 521 */ catch (FileNotFoundException | IndexOutOfBoundsException fileNotFoundException) {
				}
				/* 522 */ catch (Exception e)
				/* 523 */ {
					e.printStackTrace();
				}
				/*      */
				/*      */ }
			/* 526 */ if (archivo != null) {
				/* 527 */ iris.add(archivo);
				/*      */ } else {
				/* 529 */ archivo = new ArchivoDTO();
				/* 530 */ archivo.setId(irisIds[i]);
				/* 531 */ archivo.setName("noaplica");
				/* 532 */ iris.add(archivo);
				/*      */ }
			/*      */ }
		/*      */
		/* 536 */ response.setCode(0);
		/* 537 */ response.setMessage("OK");
		/* 538 */ response.setResult(iris);
		/* 539 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<List<ArchivoDTO>> getDocumentos(VersionEnrolamientoGeneralDTO version,
			String rfc) {
		/* 543 */ ServiceResponse<List<ArchivoDTO>> response = new ServiceResponse<List<ArchivoDTO>>();
		/* 544 */ List<ArchivoDTO> documentos = new ArrayList<ArchivoDTO>();
		/*      */
		/* 546 */ List<String> archivos = version.getArchivos();
		/* 547 */ List<String> nombreDocumentos = new ArrayList<String>();
		/* 548 */ if (version.getHistorico() == 1) {
			/* 549 */ for (int i = 0; i < archivos.size(); i++) {
				/* 550 */ if (((String) archivos.get(i))
						.substring(((String) archivos.get(i)).length() - 7, ((String) archivos.get(i)).length() - 4)
						.equals("TIF") &&
				/* 551 */ !((String) archivos.get(i)).contains("FIRMA") &&
				/* 552 */ !((String) archivos.get(i)).contains("IRIS_OJO_IZQ") &&
				/* 553 */ !((String) archivos.get(i)).contains("IRIS_OJO_DER")) {
					/* 554 */ nombreDocumentos.add(archivos.get(i));
					/*      */ }
				/*      */ }
			/*      */ } else {
			/* 558 */ for (int i = 0; i < archivos.size(); i++) {
				/* 559 */ String archivoAux = (String) archivos.get(i);
				/* 560 */ if (archivoAux.substring(archivoAux.length() - 3).equals("xml") &&
				/* 561 */ !archivoAux.contains("cierre") &&
				/* 562 */ !archivoAux.contains("datospersonales") &&
				/* 563 */ !archivoAux.contains("documentos") &&
				/* 564 */ !archivoAux.contains("firma") &&
				/* 565 */ !archivoAux.contains("foto") &&
				/* 566 */ !archivoAux.contains("huellas") &&
				/* 567 */ !archivoAux.contains("iris")) {
					/* 568 */ nombreDocumentos.add(archivos.get(i));
					/*      */ }
				/*      */ }
			/*      */ }
		/* 572 */ for (int i = 0; i < nombreDocumentos.size(); i++) {
			/* 573 */ ArchivoDTO archivo = null;
			/* 574 */ if (version.getHistorico() == 1) {
				/* 575 */ archivo = getImageTiffData((String) nombreDocumentos.get(i), version, rfc, "");
				/*      */ } else {
				/*      */
				/* 578 */ try {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					/* 579 */ DocumentBuilder db = dbf.newDocumentBuilder();
					/* 580 */ Document dom = db
							.parse(new InputSource(new FileReader((String) nombreDocumentos.get(i))));
					/* 581 */ NodeList nodeList = dom.getElementsByTagName("property");
					/* 582 */ for (int j = 0; j < nodeList.getLength(); j++) {
						/* 583 */ Element e = (Element) nodeList.item(j);
						/* 584 */ if (e.getAttribute("key").equals("legend")) {
							/* 585 */ archivo = new ArchivoDTO();
							/* 586 */ String nombre = ((String) nombreDocumentos.get(i))
									.substring(((String) nombreDocumentos.get(i)).lastIndexOf("/") + 1);
							/* 587 */ archivo.setId(nombre);
							/* 588 */ archivo.setName(e.getAttribute("value"));
							/*      */ }
						/*      */ }
				}
				/* 591 */ catch (FileNotFoundException | IndexOutOfBoundsException fileNotFoundException) {
				}
				/* 592 */ catch (Exception e)
				/* 593 */ {
					e.printStackTrace();
				}
				/*      */
				/* 595 */ if (archivo == null) {
					/* 596 */ String nombre = ((String) nombreDocumentos.get(i))
							.substring(((String) nombreDocumentos.get(i)).lastIndexOf("/") + 1);
					/* 597 */ archivo = getImageTiffActualData(nombre.replace(".xml", ".tif"), version, rfc, nombre);
					/*      */ }
				/*      */ }
			/* 600 */ if (archivo != null) {
				/* 601 */ documentos.add(archivo);
				/*      */ }
			/*      */ }
		/* 604 */ response.setCode(0);
		/* 605 */ response.setMessage("OK");
		/* 606 */ response.setResult(documentos);
		/* 607 */ return response;
		/*      */ }

	/*      */
	/*      */ private ArchivoDTO getImageData(String nombre, VersionEnrolamientoGeneralDTO version, String rfc,
			String id) {
		/*      */ try {
			/* 612 */ List<String> archivos = version.getArchivos();
			/* 613 */ String nombreArchivo = null;
			/* 614 */ for (int i = 0; i < archivos.size(); i++) {
				/* 615 */ if (((String) archivos.get(i)).contains(nombre)) {
					/* 616 */ nombreArchivo = (String) archivos.get(i);
					/*      */ }
				/*      */ }
			/* 619 */ if (nombreArchivo == null) {
				/* 620 */ return null;
				/*      */ }
			/* 622 */ DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			/* 623 */ DocumentBuilder db = dbf.newDocumentBuilder();
			/* 624 */ Document dom = db.parse(new InputSource(new FileReader(nombreArchivo)));
			/* 625 */ byte[] imagen = Base64.getDecoder()
					.decode(dom.getElementsByTagName("ARCHIVO_BINARIO").item(0).getTextContent());
			/*      */
			/* 627 */ ArchivoDTO archivodto = new ArchivoDTO();
			/* 628 */ archivodto.setData(imagen);
			/* 629 */ archivodto.setId(id);
			/* 630 */ archivodto.setName(nombre);
			/* 631 */ archivodto.setDescription(nombreArchivo);
			/*      */
			/* 633 */ return archivodto;
			/* 634 */ } catch (IOException e) {
			/* 635 */ return null;
			/* 636 */ } catch (Exception e) {
			/* 637 */ e.printStackTrace();
			/* 638 */ return null;
			/*      */ }
		/*      */ }

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */ private ArchivoDTO getImageActualData(String nombre, VersionEnrolamientoGeneralDTO version, String rfc,
			String id) {
		/*      */ try {
			/* 650 */ List<String> archivos = version.getArchivos();
			/* 651 */ String nombreArchivo = null;
			/*      */
			/* 653 */ for (int i = 0; i < archivos.size(); i++) {
				/* 654 */ if (((String) archivos.get(i)).contains(nombre)) {
					/* 655 */ nombreArchivo = (String) archivos.get(i);
					/*      */
					/*      */ break;
					/*      */ }
				/*      */ }
			/* 660 */ if (nombreArchivo == null) {
				/* 661 */ return null;
				/*      */ }
			/*      */
			/* 664 */ File file = new File(nombreArchivo);
			/* 665 */ FileInputStream inputStream = new FileInputStream(file);
			/* 666 */ ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			/* 667 */ byte[] data = new byte[16384];
			int nRead;
			/* 668 */ while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				/* 669 */ buffer.write(data, 0, nRead);
				/*      */ }
			/* 671 */ buffer.flush();
			/* 672 */ byte[] imagen = buffer.toByteArray();
			/* 673 */ inputStream.close();
			/*      */
			/* 675 */ ArchivoDTO archivodto = new ArchivoDTO();
			/* 676 */ archivodto.setData(imagen);
			/* 677 */ archivodto.setId(id);
			/* 678 */ archivodto.setName(nombre);
			/* 679 */ archivodto.setDescription(nombreArchivo);
			/*      */
			/* 681 */ return archivodto;
			/* 682 */ } catch (IOException e) {
			/* 683 */ return null;
			/* 684 */ } catch (Exception e) {
			/* 685 */ e.printStackTrace();
			/* 686 */ return null;
			/*      */ }
		/*      */ }

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */ public ArchivoDTO getReporteDuplicidadByte(String filename) {
		/*      */ try {
			/* 697 */ String directorio = System.getProperty("SAIE_BIOREPORTES_ROOT");
			/*      */
			/* 699 */ File file = new File(directorio + filename);
			/* 700 */ FileInputStream inputStream = new FileInputStream(file);
			/* 701 */ ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			/* 702 */ byte[] data = new byte[16384];
			int nRead;
			/* 703 */ while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				/* 704 */ buffer.write(data, 0, nRead);
				/*      */ }
			/* 706 */ buffer.flush();
			/* 707 */ byte[] imagen = buffer.toByteArray();
			/* 708 */ inputStream.close();
			/*      */
			/* 710 */ ArchivoDTO archivodto = new ArchivoDTO();
			/* 711 */ archivodto.setData(imagen);
			/* 712 */ archivodto.setName(filename);
			/*      */
			/* 714 */ return archivodto;
			/* 715 */ } catch (FileNotFoundException e) {
			/* 716 */ return null;
			/* 717 */ } catch (Exception e) {
			/* 718 */ e.printStackTrace();
			/* 719 */ return null;
			/*      */ }
		/*      */ }

	/*      */
	/*      */ private ArchivoDTO getImageTiffData(String nombre, VersionEnrolamientoGeneralDTO version, String rfc,
			String id) {
		/*      */ try {
			/* 725 */ List<String> archivos = version.getArchivos();
			/* 726 */ String nombreArchivo = null;
			/* 727 */ for (int i = 0; i < archivos.size(); i++) {
				/* 728 */ if (((String) archivos.get(i)).contains(nombre)) {
					/* 729 */ nombreArchivo = (String) archivos.get(i);
					/*      */ }
				/*      */ }
			/* 732 */ if (nombreArchivo == null) {
				/* 733 */ return null;
				/*      */ }
			/* 735 */ DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			/* 736 */ DocumentBuilder db = dbf.newDocumentBuilder();
			/* 737 */ Document dom = db.parse(new InputSource(new FileReader(nombreArchivo)));
			/* 738 */ byte[] imagen = Base64.getDecoder()
					.decode(dom.getElementsByTagName("ARCHIVO_BINARIO").item(0).getTextContent());
			/* 739 */ String convertirTiff = System.getProperty("SAIE_CONVERTIR_TIFF_JPG");
			/*      */
			/* 741 */ ArchivoDTO archivodto = new ArchivoDTO();
			/* 742 */ if (id.equals("")) {
				/* 743 */ String nombreImagen = dom.getElementsByTagName("T_TIPO_IMAGEN").item(0).getTextContent();
				/* 744 */ archivodto.setId(nombreImagen);
				/* 745 */ nombreImagen = nombreImagen.toLowerCase();
				/* 746 */ nombreImagen = nombreImagen.replace("_", " ");
				/* 747 */ archivodto.setName(nombreImagen);
				/*      */ } else {
				/* 749 */ archivodto.setId(id);
				/* 750 */ archivodto.setName(nombre);
				/*      */ }
			/* 752 */ archivodto.setDescription(nombreArchivo);
			/*      */
			/* 754 */ if (convertirTiff.equals("true")) {
				/* 755 */ ByteArrayInputStream baimagenTiff = new ByteArrayInputStream(imagen);
				/* 756 */ BufferedImage biimagenTiff = ImageIO.read(baimagenTiff);
				/*      */
				/* 758 */ ByteArrayOutputStream baosimagenJpg = new ByteArrayOutputStream();
				/* 759 */ ImageIO.write(biimagenTiff, "JPEG", baosimagenJpg);
				/* 760 */ baosimagenJpg.close();
				/* 761 */ archivodto.setDataContentType("image/jpeg");
				/* 762 */ archivodto.setData(baosimagenJpg.toByteArray());
				/*      */ } else {
				/* 764 */ archivodto.setDataContentType("image/tiff");
				/* 765 */ archivodto.setData(imagen);
				/*      */ }
			/* 767 */ return archivodto;
			/* 768 */ } catch (FileNotFoundException e) {
			/* 769 */ return null;
			/* 770 */ } catch (Exception e) {
			/* 771 */ e.printStackTrace();
			/* 772 */ return null;
			/*      */ }
		/*      */ }

	/*      */
	/*      */ private ArchivoDTO getImageTiffActualData(String nombre, VersionEnrolamientoGeneralDTO version,
			String rfc, String id) {
		/*      */ try {
			/* 778 */ List<String> archivos = version.getArchivos();
			/* 779 */ String nombreArchivo = null;
			/*      */
			/* 781 */ for (int i = 0; i < archivos.size(); i++) {
				/* 782 */ if (((String) archivos.get(i)).contains(nombre)) {
					/* 783 */ nombreArchivo = (String) archivos.get(i);
					/*      */ }
				/*      */ }
			/* 786 */ if (nombreArchivo == null) {
				/* 787 */ return null;
				/*      */ }
			/*      */
			/*      */
			/*      */
			/*      */
			/*      */
			/*      */
			/* 795 */ File file = new File(nombreArchivo);
			/* 796 */ FileInputStream inputStream = new FileInputStream(file);
			/* 797 */ ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			/* 798 */ byte[] data = new byte[16384];
			int nRead;
			/* 799 */ while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				/* 800 */ buffer.write(data, 0, nRead);
				/*      */ }
			/* 802 */ buffer.flush();
			/* 803 */ byte[] imagen = buffer.toByteArray();
			/* 804 */ inputStream.close();
			/*      */
			/* 806 */ ArchivoDTO archivodto = new ArchivoDTO();
			/* 807 */ archivodto.setId(id);
			/* 808 */ archivodto.setName(nombre);
			/* 809 */ archivodto.setDescription(nombreArchivo);
			/*      */
			/* 811 */ String convertirTiff = System.getProperty("SAIE_CONVERTIR_TIFF_JPG");
			/* 812 */ if (convertirTiff.equals("true")) {
				/*      */
				/* 814 */ ByteArrayInputStream baimagenTiff = new ByteArrayInputStream(imagen);
				/* 815 */ BufferedImage biimagenTiff = ImageIO.read(baimagenTiff);
				/*      */
				/* 817 */ ByteArrayOutputStream baosimagenJpg = new ByteArrayOutputStream();
				/* 818 */ ImageIO.write(biimagenTiff, "JPEG", baosimagenJpg);
				/* 819 */ baosimagenJpg.close();
				/* 820 */ archivodto.setDataContentType("image/jpeg");
				/* 821 */ archivodto.setData(baosimagenJpg.toByteArray());
				/*      */ } else {
				/* 823 */ archivodto.setDataContentType("image/tiff");
				/* 824 */ archivodto.setData(imagen);
				/*      */ }
			/* 826 */ return archivodto;
			/* 827 */ } catch (FileNotFoundException e) {
			/* 828 */ return null;
			/* 829 */ } catch (Exception e) {
			/* 830 */ e.printStackTrace();
			/* 831 */ return null;
			/*      */ }
		/*      */ }

	/*      */
	/*      */ private ArchivoDTO getImageTiffOnlyData(String nombreArchivo) {
		/*      */ try {
			/* 837 */ DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			/* 838 */ DocumentBuilder db = dbf.newDocumentBuilder();
			/* 839 */ Document dom = db.parse(new InputSource(new FileReader(nombreArchivo)));
			/* 840 */ byte[] imagen = Base64.getDecoder()
					.decode(dom.getElementsByTagName("ARCHIVO_BINARIO").item(0).getTextContent());
			/*      */
			/* 842 */ ArchivoDTO archivodto = new ArchivoDTO();
			/* 843 */ archivodto.setData(imagen);
			/* 844 */ return archivodto;
			/* 845 */ } catch (FileNotFoundException e) {
			/* 846 */ return null;
			/* 847 */ } catch (Exception e) {
			/* 848 */ e.printStackTrace();
			/* 849 */ return null;
			/*      */ }
		/*      */ }

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */ private ArchivoDTO getImageTiffActualOnlyData(String nombreArchivo) {
		/*      */ try {
			/* 860 */ File file = new File(nombreArchivo);
			/* 861 */ FileInputStream inputStream = new FileInputStream(file);
			/* 862 */ ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			/* 863 */ byte[] data = new byte[16384];
			int nRead;
			/* 864 */ while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				/* 865 */ buffer.write(data, 0, nRead);
				/*      */ }
			/* 867 */ buffer.flush();
			/* 868 */ byte[] imagen = buffer.toByteArray();
			/* 869 */ inputStream.close();
			/*      */
			/* 871 */ ArchivoDTO archivodto = new ArchivoDTO();
			/* 872 */ archivodto.setData(imagen);
			/* 873 */ return archivodto;
			/* 874 */ } catch (FileNotFoundException e) {
			/* 875 */ return null;
			/* 876 */ } catch (Exception e) {
			/* 877 */ e.printStackTrace();
			/* 878 */ return null;
			/*      */ }
		/*      */ }

	/*      */
	/*      */
	/* 883 */ public ServiceResponse<VersionEnrolamientoAdjuntosDTO> getVersionEnrolamientoAdjuntosByEPID(
			String request) {
		return null;
	}
	/*      */
	/*      */

	public ServiceResponse<ArchivoDTO> getCertificacionDocumentos(DatosEnrolamientoDTO datosdto,
			VersionEnrolamientoGeneralDTO versiondto, VersionEnrolamientoAdjuntosDTO adjuntosdto, String[] iddocumentos,
			String nombrealsc, String fraccionalsc, String funcionario, String ciudad, UsuarioDTO usuario,
			String tipoLeyenda) {
		ServiceResponse<ArchivoDTO> response = new ServiceResponse<ArchivoDTO>();

		AuditEvent aevent = new AuditEvent();
		aevent.setDateTime(new Date());
		aevent.setUser(usuario.getRfc());
		aevent.setIpAddress(usuario.getIp());
		aevent.setMacAddress(usuario.getMac());
		String amessage = datosdto.getRfc() + ", ";
		amessage += "CertificacionDocumental,";
		amessage += Arrays.toString(iddocumentos);
		aevent.setMessage(amessage);
		aevent.setUuid(UUID.randomUUID().toString());
		AuditManager.register(aevent);

		int paginacion = 0;

		try {
			ArchivoDTO certificacion = new ArchivoDTO();

			try {
				com.itextpdf.text.Document pdf = new com.itextpdf.text.Document();
				ByteArrayOutputStream pdfos = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(pdf, pdfos);
				pdf.open();
				float ancho = pdf.getPageSize().getWidth() - pdf.leftMargin() - pdf.rightMargin();
				float alto = pdf.getPageSize().getHeight() - pdf.topMargin() - pdf.bottomMargin() - 12;
				int np = 1;

				// Ciclo para inicializar la paginación total en el documento (repite el ciclo
				// de abajo).
				for (int k = 0; k < iddocumentos.length; k++) {
					String nombreArch = null;
					List<ArchivoDTO> adjuntos1 = adjuntosdto.getDocumentos();
					for (int i = 0; i < adjuntos1.size(); i++) {
						if (adjuntos1.get(i).getId().equals(iddocumentos[k])) {
							nombreArch = adjuntos1.get(i).getDescription();
							break;
						}
					}
					byte[] imagen1;
					if (versiondto.getHistorico() == 1) {
						imagen1 = getImageTiffOnlyData(nombreArch).getData();
					} else {
						imagen1 = getImageTiffActualOnlyData(nombreArch).getData();
					}
					RandomAccessFileOrArray tifFile1 = new RandomAccessFileOrArray(imagen1);

					Image img1 = null;
					try {
						img1 = TiffImage.getTiffImage(tifFile1, 1);
					} catch (Exception e) {
						img1 = null;
					}

					if (img1 != null) {
						paginacion += TiffImage.getNumberOfPages(tifFile1);
					} else {
						List<byte[]> paginas = getJpgFromTiff(imagen1);
						paginacion += paginas.size();
					}
				}
				// Termina ciclo de inicialización de paginación
				// Agregamos una unidad para la página de certificación
				// paginacion +=1;

				for (int j = 0; j < iddocumentos.length; j++, np++) {
					String nombreArchivo = null;
					List<ArchivoDTO> adjuntos = adjuntosdto.getDocumentos();
					for (int i = 0; i < adjuntos.size(); i++) {
						if (adjuntos.get(i).getId().equals(iddocumentos[j])) {
							nombreArchivo = adjuntos.get(i).getDescription();
							break;
						}
					}
					byte[] imagen;
					if (versiondto.getHistorico() == 1) {
						imagen = getImageTiffOnlyData(nombreArchivo).getData();
					} else {
						imagen = getImageTiffActualOnlyData(nombreArchivo).getData();
					}
					RandomAccessFileOrArray tifFile = new RandomAccessFileOrArray(imagen);

					Image img = null;
					try {
						img = TiffImage.getTiffImage(tifFile, 1);
					} catch (Exception e) {
						img = null;
					}

					if (img != null) {
						for (int i = 1; i <= TiffImage.getNumberOfPages(tifFile); i++, np++) {
//							paginacion = TiffImage.getNumberOfPages(tifFile)+1;
							PdfContentByte canvas = writer.getDirectContent();
							canvas.beginText();
							canvas.setFontAndSize(BaseFont.createFont(), 12);
							canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT,
									iddocumentos[j] + " - " + i + "/" + datosdto.getRfc(), pdf.leftMargin(),
									pdf.getPageSize().getHeight() - pdf.topMargin(), 0);
							canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_RIGHT,
									"" + (paginacion - np + 1) + " de " + paginacion,
									pdf.getPageSize().getWidth() - pdf.rightMargin(), pdf.bottomMargin(), 0);
							canvas.endText();

							img = TiffImage.getTiffImage(tifFile, i);
							float escala = alto / img.getHeight() * 100;
							img.scalePercent(escala);
							if (img.getScaledWidth() > ancho) {
								escala = ancho / img.getWidth() * 100;
								img.scalePercent(escala);
							}
							img.setAbsolutePosition((pdf.getPageSize().getWidth() - img.getScaledWidth()) / 2,
									pdf.getPageSize().getHeight() - img.getScaledHeight() - pdf.topMargin() - 12);
							pdf.add(img);
							pdf.newPage();
						}
					} else {
						List<byte[]> paginas = getJpgFromTiff(imagen);
						for (int i = 0; i < paginas.size(); i++, np++) {
							PdfContentByte canvas = writer.getDirectContent();
							canvas.beginText();
							canvas.setFontAndSize(BaseFont.createFont(), 12);
							canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_LEFT,
									iddocumentos[j] + " - " + (i + 1) + "/" + datosdto.getRfc(), pdf.leftMargin(),
									pdf.getPageSize().getHeight() - pdf.topMargin(), 0);
							canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_RIGHT,
									"" + (paginacion - np + 1) + " de " + paginacion,
									pdf.getPageSize().getWidth() - pdf.rightMargin(), pdf.bottomMargin(), 0);
							canvas.endText();

							img = Image.getInstance(paginas.get(i));
							float escala = alto / img.getHeight() * 100;
							img.scalePercent(escala);
							if (img.getScaledWidth() > ancho) {
								escala = ancho / img.getWidth() * 100;
								img.scalePercent(escala);
							}
							img.setAbsolutePosition((pdf.getPageSize().getWidth() - img.getScaledWidth()) / 2,
									pdf.getPageSize().getHeight() - img.getScaledHeight() - pdf.topMargin() - 12);
							pdf.add(img);
							pdf.newPage();
						}
					}
					np--;
				}

				// TODO crear mapa e invocar clase-template
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("nombrealsc", nombrealsc);
				params.put("fraccionalsc", fraccionalsc);
				params.put("np", np);
				params.put("versiondto", versiondto);
				params.put("datosdto", datosdto);
				params.put("ciudad", ciudad);
				params.put("funcionario", funcionario);
				// params.put("tipoLeyenda", tipoLeyenda);

				String leyenda1 = "";
				ILeyenda leyendaFactory;
				try {
					// Aqui determino si va a la clase Central o a cualquiera de las otras clases,
					// además de agregar en esta
					// las variables necesarias para su operación.
					if (tipoLeyenda.equals("Central") || tipoLeyenda.equals("Coordinador")
							|| tipoLeyenda.equals("Admin")) {
						params.put("tipoLeyenda", tipoLeyenda);
						leyenda1 = "Central";
					} else {
						leyenda1 = tipoLeyenda;
					}
					Class leyendaClazz = Class
							.forName("com.iecisa.sat.saie.vf.integration.service.impl." + "Leyenda" + leyenda1);
					Constructor ctor = leyendaClazz.getConstructor();
					leyendaFactory = (ILeyenda) ctor.newInstance();
				} catch (ClassNotFoundException e) {
					response.setCode(-1);
					throw new RuntimeException("No se encontro la clase para la leyenda " + leyenda1);
				} catch (Exception e) {
					response.setCode(-1);
					throw new RuntimeException("Error al instanciar clase para la leyenda " + leyenda1);
				}

				leyendaFactory.leyenda(pdf, params);

				PdfContentByte canvas = writer.getDirectContent();
				canvas.beginText();
				canvas.setFontAndSize(BaseFont.createFont(), 12);
				// canvas.showTextAligned(com.itextpdf.text.Element.ALIGN_RIGHT, ""+np+" de
				// "+paginacion, pdf.getPageSize().getWidth()-pdf.rightMargin(),
				// pdf.bottomMargin(), 0);
				canvas.endText();

				pdf.close();
				certificacion.setData(pdfos.toByteArray());

			} catch (DocumentException e) {
				e.printStackTrace();
			}

			DBManager.getInstance().insertarBitacoraFolio(Arrays.toString(iddocumentos), funcionario);
			response.setCode(0);
			response.setMessage("OK");
			response.setResult(certificacion);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-1);
		}
		return response;
	}

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */
	/*      */ public ServiceResponse<ArchivoDTO> getCertificacionDictamenCentral(String dictamenUrl, String numeroastc,
			String nombrealsc, String administrador, String ciudad, String subadministrador, String numerosubadmin,
			String nombre1, String nombre2, String rfc1, String rfc2, String localidad1, String localidad2,
			String fecha1, String fecha2, UsuarioDTO usuario) {
		/* 1335 */ ServiceResponse<ArchivoDTO> response = new ServiceResponse<ArchivoDTO>();
		/*      */
		/* 1337 */ AuditEvent aevent = new AuditEvent();
		/* 1338 */ aevent.setDateTime(new Date());
		/* 1339 */ aevent.setUser(usuario.getRfc());
		/* 1340 */ aevent.setIpAddress(usuario.getIp());
		/* 1341 */ aevent.setMacAddress(usuario.getMac());
		/* 1342 */ String amessage = rfc1 + ", ";
		/* 1343 */ amessage = amessage + "CertificacionDictamen,";
		/* 1344 */ aevent.setMessage(amessage);
		/* 1345 */ aevent.setUuid(UUID.randomUUID().toString());
		/* 1346 */ AuditManager.register(aevent);
		/*      */
		/*      */ try {
			/* 1349 */ ArchivoDTO certificacion = new ArchivoDTO();
			/*      */
			/*      */ try {
				/* 1352 */ ArchivoDTO archivodtoTemp = (new ServiceEnrolamiento())
						.getReporteDuplicidadByte(dictamenUrl);
				/*      */
				/*      */
				/* 1355 */ PdfReader pdfr = new PdfReader(archivodtoTemp.getData());
				/* 1356 */ ByteArrayOutputStream baosDictamen = new ByteArrayOutputStream();
				/* 1357 */ PdfStamper pdfs = new PdfStamper(pdfr, baosDictamen);
				/* 1358 */ Rectangle bounds = pdfr.getPageSize(pdfr.getNumberOfPages());
				/*      */
				/* 1360 */ pdfs.insertPage(pdfr.getNumberOfPages() + 1, bounds);
				/* 1361 */ PdfContentByte pdfc = pdfs.getOverContent(pdfr.getNumberOfPages());
				/*      */
				/* 1363 */ ColumnText ct = new ColumnText(pdfc);
				/*      */
				/* 1365 */ int np = pdfr.getNumberOfPages() + 1;
				/* 1366 */ int fuenteSize = 10;
				/* 1367 */ int fuenteSizeSmall = 8;
				/* 1368 */ Font fuente = FontFactory.getFont("Noto-Sans", fuenteSize);
				/* 1369 */ Font fuenteBold = FontFactory.getFont("Noto-Sans", fuenteSize, 1);
				/* 1370 */ Font fuenteSmall = FontFactory.getFont("Noto-Sans", fuenteSizeSmall);
				/* 1371 */ Font fuenteSmallBold = FontFactory.getFont("Noto-Sans", fuenteSizeSmall, 1);
				/* 1372 */ SimpleDateFormat sdf = new SimpleDateFormat("dd' de 'MMMM' de 'YYYY",
						new Locale("es", "MX"));
				/* 1373 */ SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss", new Locale("es", "MX"));
				/*      */
				/* 1375 */ String texto = "El que suscribe, en su carácter de ";
				/* 1376 */ Paragraph par = new Paragraph(texto, fuente);
				/* 1377 */ par.setAlignment(3);
				/* 1378 */ Chunk chunk = new Chunk(
						"Administrador de Servicios Tributarios al Contribuyente " + numeroastc, fuenteBold);
				/* 1379 */ par.add(chunk);
				/* 1380 */ texto = ", de la  Administración Central de Servicios Tributarios al Contribuyente, dependiente de la Administración General de Servicios al Contribuyente, del Servicio de Administración Tributaria (SAT); con fundamento en lo dispuesto por el artículo 13, fracción II en relación con el 12, fracción XXIV y por el artículo 33 apartado A en relación con el 32 fracción IV del Reglamento Interior del Servicio de Administración Tributaria, publicado en el Diario Oficial de la Federación el 24 de agosto de 2015, cuya vigencia inició el 22 de noviembre de 2015;";
				/*      */
				/*      */
				/*      */
				/*      */
				/* 1385 */ chunk = new Chunk(texto);
				/* 1386 */ par.add(chunk);
				/* 1387 */ chunk = new Chunk(" CERTIFICA: ", fuenteBold);
				/* 1388 */ par.add(chunk);
				/*      */
				/* 1390 */ texto = "Que la impresión del presente documento, misma que consta de "
						+ Math.round((np / 2)) + " hojas útiles, es copia fiel y exacta de "
						+ "las imágenes contenidas en el expediente electrónico de Firma Electrónica Avanzada (Fiel) en poder del SAT, "
						+ "resultantes de la captura de datos biométricos realizada por dicho órgano desconcentrado a los contribuyentes ";
				/*      */
				/*      */
				/* 1393 */ chunk = new Chunk(texto);
				/* 1394 */ par.add(chunk);
				/* 1395 */ chunk = new Chunk(nombre1, fuenteBold);
				/* 1396 */ par.add(chunk);
				/* 1397 */ texto = ", con Clave de Registro Federal del Contribuyente ";
				/* 1398 */ chunk = new Chunk(texto);
				/* 1399 */ par.add(chunk);
				/* 1400 */ chunk = new Chunk(rfc1, fuenteBold);
				/* 1401 */ par.add(chunk);
				/* 1402 */ texto = ", en la ";
				/* 1403 */ chunk = new Chunk(texto);
				/* 1404 */ par.add(chunk);
				/* 1405 */ chunk = new Chunk(
						"Administración (Desconcentrada o Local) de (Asistencia o Servicios) al Contribuyente de "
								+ localidad1,
						fuenteBold);
				/* 1406 */ par.add(chunk);
				/* 1407 */ texto = " el ";
				/* 1408 */ chunk = new Chunk(texto);
				/* 1409 */ par.add(chunk);
				/* 1410 */ chunk = new Chunk(sdf.format(sdfr.parse(fecha1)), fuenteBold);
				/* 1411 */ par.add(chunk);
				/*      */
				/* 1413 */ chunk = new Chunk(" y ");
				/* 1414 */ par.add(chunk);
				/* 1415 */ chunk = new Chunk(nombre2, fuenteBold);
				/* 1416 */ par.add(chunk);
				/* 1417 */ texto = ", con Clave de Registro Federal del Contribuyente ";
				/* 1418 */ chunk = new Chunk(texto);
				/* 1419 */ par.add(chunk);
				/* 1420 */ chunk = new Chunk(rfc2, fuenteBold);
				/* 1421 */ par.add(chunk);
				/* 1422 */ texto = ", en la ";
				/* 1423 */ chunk = new Chunk(texto);
				/* 1424 */ par.add(chunk);
				/* 1425 */ chunk = new Chunk(
						"Administración (Desconcentrada o Local) de (Asistencia o Servicios) al Contribuyente de "
								+ localidad2,
						fuenteBold);
				/* 1426 */ par.add(chunk);
				/* 1427 */ texto = " el ";
				/* 1428 */ chunk = new Chunk(texto);
				/* 1429 */ par.add(chunk);
				/*      */
				/* 1431 */ chunk = new Chunk(sdf.format(sdfr.parse(fecha2)), fuenteBold);
				/* 1432 */ par.add(chunk);
				/* 1433 */ texto = ", para la realización del trámite de obtención del certificado de Fiel que emite el Servicio de Administración Tributaria.\n\nDe acuerdo al artículo 5 del Reglamento Interior del Servicio de Administración Tributaria. “Las Administraciones Generales y las Administraciones Centrales, así como las Coordinaciones, las Administraciones y las Subadministraciones adscritas a éstas, tendrán su sede en la Ciudad de México y ejercerán sus facultades en todo el territorio nacional.\n\nLa información y documentación que se le proporciona sólo podrá ser utilizada para los efectos por los cuales fue solicitada, sin que por ello pierda su confidencialidad, señalando a su vez, que la información fiscal se encuentra clasificada como RESERVADA, en términos de lo previsto por el artículo 14, fracciones I Y II, de la Ley Federal de Transparencia y Acceso a la Información Pública Gubernamental, en relación con el artículo 69 del Código Fiscal de la Federación y con los Lineamientos Generales para la Clasificación y Desclasificación de Información de las Dependencias y Entidades de la Administración Pública Federal, publicados en el Diario Oficial de la Federación el 18 de agosto de 2003, previniendo de las penas administrativas o penales que se contemplan en los artículos 8, fracción V, de la Ley Federal de Responsabilidades Administrativas de los Servidores Públicos, 210 y 211 del Código Penal Federal, para los casos de violentar la reserva legal.\n\n";
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/* 1445 */ chunk = new Chunk(texto);
				/* 1446 */ par.add(chunk);
				/*      */
				/* 1448 */ texto = ciudad + ", a " + sdf.format(new Date());
				/* 1449 */ chunk = new Chunk(texto, fuenteBold);
				/* 1450 */ par.add(chunk);
				/* 1451 */ texto = "\n\n";
				/* 1452 */ chunk = new Chunk(texto);
				/* 1453 */ par.add(chunk);
				/* 1454 */ chunk = new Chunk("Nombre Administrador " + administrador, fuenteBold);
				/* 1455 */ par.add(chunk);
				/* 1456 */ texto = "\n\n";
				/* 1457 */ chunk = new Chunk(texto);
				/* 1458 */ par.add(chunk);
				/* 1459 */ texto = "Administrador de Servicios Tributarios al Contribuyente " + numeroastc;
				/* 1460 */ chunk = new Chunk(texto, fuenteBold);
				/* 1461 */ par.add(chunk);
				/*      */
				/* 1463 */ ct.setSimpleColumn(par, bounds.getLeft() + 35.0F, bounds.getBottom() + 35.0F,
						bounds.getRight() - 35.0F, bounds.getTop() - 35.0F, 15.0F, 3);
				/* 1464 */ ct.go();
				/*      */
				/* 1466 */ if (subadministrador != null) {
					/* 1467 */ PdfContentByte cb = pdfc;
					/* 1468 */ Rectangle rect = new Rectangle(bounds.getLeft() + bounds.getWidth() / 2.0F,
							bounds.getBottom() + 35.0F, bounds.getRight() - 35.0F, bounds.getBottom() + 200.0F);
					/*      */
					/* 1470 */ ct = new ColumnText(pdfc);
					/* 1471 */ par = new Paragraph("", fuenteSmall);
					/* 1472 */ texto = "\n\nPor ausencia del Administrador de Servicios Tributarios al Contribuyente "
							+ numeroastc
							+ " , con fundamento en los artículos 2, Apartado B, fracción VII, inciso a) y4, quinto párrafo, en relación con el artículo 32, "
							+ "numeral 1, inciso d), todos del Reglamento Interior del Servicio de Administración Tributaria, publicado en el Diario "
							+ "Oficial de la Federación el día 24 de agosto de 2015, en vigor a partir del 22 de noviembre de 2015, en suplencia del "
							+ "Administrador de Servicios Tributarios al Contribuyente " + numeroastc
							+ ", firma el Subadministrador de Servicios Tributarios al Contribuyente " + numerosubadmin
							+ ".\n\n";
					/*      */
					/*      */
					/*      */
					/*      */
					/*      */
					/* 1478 */ chunk = new Chunk(texto);
					/* 1479 */ par.add(chunk);
					/* 1480 */ chunk = new Chunk(subadministrador, fuenteSmallBold);
					/* 1481 */ par.add(chunk);
					/* 1482 */ ct.setSimpleColumn(par, rect.getLeft(), rect.getBottom(), rect.getRight(), rect.getTop(),
							10.0F, 3);
					/* 1483 */ ct.go();
					/*      */
					/* 1485 */ cb.saveState();
					/* 1486 */ cb.setColorStroke(BaseColor.BLACK);
					/* 1487 */ cb.rectangle(rect.getLeft(), rect.getTop() - rect.getHeight() / 2.0F - 40.0F,
							rect.getWidth(), rect.getHeight() / 2.0F + 20.0F);
					/* 1488 */ cb.stroke();
					/* 1489 */ cb.restoreState();
					/*      */ }
				/*      */
				/* 1492 */ pdfs.close();
				/* 1493 */ certificacion.setData(baosDictamen.toByteArray());
				/*      */ }
			/* 1495 */ catch (DocumentException e) {
				/* 1496 */ e.printStackTrace();
				/*      */ }
			/*      */
			/*      */
			/* 1500 */ response.setCode(0);
			/* 1501 */ response.setMessage("OK");
			/* 1502 */ response.setResult(certificacion);
			/* 1503 */ } catch (Exception e) {
			/* 1504 */ e.printStackTrace();
			/* 1505 */ response.setCode(-1);
			/*      */ }
		/* 1507 */ return response;
		/*      */ }

	/*      */
	/*      */
	/*      */
	/*      */ public ServiceResponse<ArchivoDTO> getCertificacionDictamenDesconcentrado(String dictamenUrl,
			String fraccion, String inciso, String nombrealsc, String administrador, String ciudad,
			String subadministrador, String nombre1, String nombre2, String rfc1, String rfc2, String localidad1,
			String localidad2, String fecha1, String fecha2, UsuarioDTO usuario) {
		/* 1513 */ ServiceResponse<ArchivoDTO> response = new ServiceResponse<ArchivoDTO>();
		/*      */
		/* 1515 */ AuditEvent aevent = new AuditEvent();
		/* 1516 */ aevent.setDateTime(new Date());
		/* 1517 */ aevent.setUser(usuario.getRfc());
		/* 1518 */ aevent.setIpAddress(usuario.getIp());
		/* 1519 */ aevent.setMacAddress(usuario.getMac());
		/* 1520 */ String amessage = rfc1 + ", ";
		/* 1521 */ amessage = amessage + "CertificacionDictamen,";
		/* 1522 */ aevent.setMessage(amessage);
		/* 1523 */ aevent.setUuid(UUID.randomUUID().toString());
		/* 1524 */ AuditManager.register(aevent);
		/*      */
		/*      */ try {
			/* 1527 */ ArchivoDTO certificacion = new ArchivoDTO();
			/*      */
			/*      */ try {
				/* 1530 */ ArchivoDTO archivodtoTemp = (new ServiceEnrolamiento())
						.getReporteDuplicidadByte(dictamenUrl);
				/*      */
				/*      */
				/* 1533 */ PdfReader pdfr = new PdfReader(archivodtoTemp.getData());
				/* 1534 */ ByteArrayOutputStream baosDictamen = new ByteArrayOutputStream();
				/* 1535 */ PdfStamper pdfs = new PdfStamper(pdfr, baosDictamen);
				/* 1536 */ Rectangle bounds = pdfr.getPageSize(pdfr.getNumberOfPages());
				/*      */
				/* 1538 */ pdfs.insertPage(pdfr.getNumberOfPages() + 1, bounds);
				/* 1539 */ PdfContentByte pdfc = pdfs.getOverContent(pdfr.getNumberOfPages());
				/*      */
				/* 1541 */ ColumnText ct = new ColumnText(pdfc);
				/*      */
				/* 1543 */ int np = pdfr.getNumberOfPages() + 1;
				/* 1544 */ int fuenteSize = 10;
				/* 1545 */ int fuenteSizeSmall = 8;
				/* 1546 */ Font fuente = FontFactory.getFont("Noto-Sans", fuenteSize);
				/* 1547 */ Font fuenteBold = FontFactory.getFont("Noto-Sans", fuenteSize, 1);
				/* 1548 */ Font fuenteSmall = FontFactory.getFont("Noto-Sans", fuenteSizeSmall);
				/* 1549 */ Font fuenteSmallBold = FontFactory.getFont("Noto-Sans", fuenteSizeSmall, 1);
				/* 1550 */ SimpleDateFormat sdf = new SimpleDateFormat("dd' de 'MMMM' de 'YYYY",
						new Locale("es", "MX"));
				/* 1551 */ SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss", new Locale("es", "MX"));
				/*      */
				/* 1553 */ String texto = "El que suscribe, en su carácter de ";
				/* 1554 */ Paragraph par = new Paragraph(texto, fuente);
				/* 1555 */ par.setAlignment(3);
				/* 1556 */ Chunk chunk = new Chunk(
						"Administrador Desconcentrado de Servicios al Contribuyente de " + nombrealsc, fuenteBold);
				/* 1557 */ par.add(chunk);
				/* 1558 */ texto = ", dependiente de la Administración General de Servicios al Contribuyente, del Servicio de Administración Tributaria (SAT); con fundamento en lo dispuesto por el artículo 14, fracción II en relación con el 12, fracción XXIV y por el artículo 6, apartado A, fracción "
						+ fraccion + " inciso " + inciso
						+ " del Reglamento Interior del Servicio de Administración Tributaria, publicado en el "
						+ "Diario Oficial de la Federación el 24 de agosto de 2015, cuya vigencia inició el 22 de noviembre de 2015;";
				/*      */
				/*      */
				/*      */
				/* 1562 */ chunk = new Chunk(texto);
				/* 1563 */ par.add(chunk);
				/* 1564 */ chunk = new Chunk(" CERTIFICA: ", fuenteBold);
				/* 1565 */ par.add(chunk);
				/*      */
				/* 1567 */ texto = "Que la impresión del presente documento, misma que consta de "
						+ Math.round((np / 2)) + " hojas, "
						+ "es copia fiel y exacta de las imágenes contenidas en el expediente electrónico de Firma Electrónica Avanzada (Fiel) "
						+ "en poder del SAT, resultantes de la captura de datos biométricos realizada por dicho órgano desconcentrado a los contribuyentes ";
				/*      */
				/*      */
				/* 1570 */ chunk = new Chunk(texto);
				/* 1571 */ par.add(chunk);
				/* 1572 */ chunk = new Chunk(nombre1, fuenteBold);
				/* 1573 */ par.add(chunk);
				/* 1574 */ texto = ", con Clave de Registro Federal del Contribuyente ";
				/* 1575 */ chunk = new Chunk(texto);
				/* 1576 */ par.add(chunk);
				/* 1577 */ chunk = new Chunk(rfc1, fuenteBold);
				/* 1578 */ par.add(chunk);
				/* 1579 */ texto = ", en la ";
				/* 1580 */ chunk = new Chunk(texto);
				/* 1581 */ par.add(chunk);
				/* 1582 */ chunk = new Chunk(
						"Administración (Desconcentrada o Local) de (Asistencia o Servicios) al Contribuyente de "
								+ localidad1,
						fuenteBold);
				/* 1583 */ par.add(chunk);
				/* 1584 */ texto = ", el ";
				/* 1585 */ chunk = new Chunk(texto);
				/* 1586 */ par.add(chunk);
				/* 1587 */ chunk = new Chunk(sdf.format(sdfr.parse(fecha1)), fuenteBold);
				/* 1588 */ par.add(chunk);
				/*      */
				/* 1590 */ par.add(" y ");
				/* 1591 */ chunk = new Chunk(nombre2, fuenteBold);
				/* 1592 */ par.add(chunk);
				/* 1593 */ texto = ", con Clave de Registro Federal del Contribuyente ";
				/* 1594 */ chunk = new Chunk(texto);
				/* 1595 */ par.add(chunk);
				/* 1596 */ chunk = new Chunk(rfc2, fuenteBold);
				/* 1597 */ par.add(chunk);
				/* 1598 */ texto = ", en la ";
				/* 1599 */ chunk = new Chunk(texto);
				/* 1600 */ par.add(chunk);
				/* 1601 */ chunk = new Chunk(
						"Administración (Desconcentrada o Local) de (Asistencia o Servicios) al Contribuyente de "
								+ localidad2,
						fuenteBold);
				/* 1602 */ par.add(chunk);
				/* 1603 */ texto = ", el ";
				/* 1604 */ chunk = new Chunk(texto);
				/* 1605 */ par.add(chunk);
				/* 1606 */ chunk = new Chunk(sdf.format(sdfr.parse(fecha2)), fuenteBold);
				/* 1607 */ par.add(chunk);
				/* 1608 */ texto = ", para la realización del trámite de obtención del certificado de Fiel que emite el Servicio de Administración Tributaria.\n\nLa información y documentación que se le proporciona sólo podrá ser utilizada para los efectos por los cuales fue solicitada, sin que por ello pierda su confidencialidad, señalando a su vez, que la información fiscal se encuentra clasificada como RESERVADA, en términos de lo previsto por el artículo 14, fracciones I Y II, de la Ley Federal de Transparencia y Acceso a la Información Pública Gubernamental, en relación con el artículo 69 del Código Fiscal de la Federación y con los Lineamientos Generales para la Clasificación y Desclasificación de Información de las Dependencias y Entidades de la Administración Pública Federal, publicados en el Diario Oficial de la Federación el 18 de agosto de 2003, previniendo de las penas administrativas o penales que se contemplan en los artículos 8, fracción V, de la Ley Federal de Responsabilidades Administrativas de los Servidores Públicos, 210 y 211 del Código Penal Federal, para los casos de violentar la reserva legal.\n\n";
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/*      */
				/* 1616 */ chunk = new Chunk(texto);
				/* 1617 */ par.add(chunk);
				/*      */
				/* 1619 */ texto = ciudad + ", a " + sdf.format(new Date());
				/* 1620 */ chunk = new Chunk(texto, fuenteBold);
				/* 1621 */ par.add(chunk);
				/* 1622 */ texto = "\n\n\n";
				/* 1623 */ chunk = new Chunk(texto);
				/* 1624 */ par.add(chunk);
				/* 1625 */ chunk = new Chunk("Nombre Administrador Desconcentrado " + administrador, fuenteBold);
				/* 1626 */ par.add(chunk);
				/* 1627 */ texto = "\n";
				/* 1628 */ chunk = new Chunk(texto);
				/* 1629 */ par.add(chunk);
				/* 1630 */ texto = "Administrador Desconcentrado de Servicios al Contribuyente de " + nombrealsc;
				/* 1631 */ chunk = new Chunk(texto, fuenteBold);
				/* 1632 */ par.add(chunk);
				/*      */
				/* 1634 */ ct.setSimpleColumn(par, bounds.getLeft() + 35.0F, bounds.getBottom() + 35.0F,
						bounds.getRight() - 35.0F, bounds.getTop() - 35.0F, 15.0F, 3);
				/* 1635 */ ct.go();
				/*      */
				/* 1637 */ if (subadministrador != null) {
					/* 1638 */ PdfContentByte cb = pdfc;
					/* 1639 */ Rectangle rect = new Rectangle(bounds.getLeft() + bounds.getWidth() / 2.0F,
							bounds.getBottom() + 35.0F, bounds.getRight() - 35.0F, bounds.getBottom() + 250.0F);
					/*      */
					/* 1641 */ ct = new ColumnText(pdfc);
					/* 1642 */ par = new Paragraph("", fuenteSmall);
					/* 1643 */ texto = "\n\nPor ausencia del Administrador Desconcentrado de Servicios al Contribuyente de "
							+ nombrealsc + ", "
							+ "con fundamento en los artículos 2, Apartado C, 4, quinto párrafo y 34, segundo párrafo, todos del Reglamento Interior "
							+ "del Servicio de Administración Tributaria, publicado en el Diario Oficial de la Federación el día 24 de agosto de 2015, "
							+ "en vigor a partir del 22 de noviembre de 2015, en suplencia del Administrador Desconcentrado de Servicios "
							+ "al Contribuyente de " + nombrealsc + ", firma el Subadministrador.\n\n";
					/*      */
					/*      */
					/*      */
					/*      */
					/* 1648 */ chunk = new Chunk(texto);
					/* 1649 */ par.add(chunk);
					/* 1650 */ chunk = new Chunk(subadministrador, fuenteBold);
					/* 1651 */ par.add(chunk);
					/* 1652 */ ct.setSimpleColumn(par, rect.getLeft(), rect.getBottom(), rect.getRight(), rect.getTop(),
							10.0F, 3);
					/* 1653 */ ct.go();
					/*      */
					/* 1655 */ cb.saveState();
					/* 1656 */ cb.setColorStroke(BaseColor.BLACK);
					/* 1657 */ cb.rectangle(rect.getLeft(), rect.getTop() - rect.getHeight() / 2.0F - 20.0F,
							rect.getWidth(), rect.getHeight() / 2.0F);
					/* 1658 */ cb.stroke();
					/* 1659 */ cb.restoreState();
					/*      */ }
				/*      */
				/* 1662 */ pdfs.close();
				/* 1663 */ certificacion.setData(baosDictamen.toByteArray());
				/*      */ }
			/* 1665 */ catch (DocumentException e) {
				/* 1666 */ e.printStackTrace();
				/*      */ }
			/*      */
			/*      */
			/* 1670 */ response.setCode(0);
			/* 1671 */ response.setMessage("OK");
			/* 1672 */ response.setResult(certificacion);
			/* 1673 */ } catch (Exception e) {
			/* 1674 */ e.printStackTrace();
			/* 1675 */ response.setCode(-1);
			/*      */ }
		/* 1677 */ return response;
		/*      */ }

	/*      */
	/*      */ private List<byte[]> getJpgFromTiff(byte[] imagen) {
		/*      */ try {
			/* 1682 */ List<byte[]> resultado = new ArrayList<byte[]>();
			/*      */
			/* 1684 */ ByteArrayInputStream baisTiff = new ByteArrayInputStream(imagen);
			/* 1685 */ ImageInputStream iis = ImageIO.createImageInputStream(baisTiff);
			/* 1686 */ Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
			/* 1687 */ ImageReader ir = (ImageReader) readers.next();
			/* 1688 */ ir.setInput(iis);
			/* 1689 */ int frameCount = ir.getNumImages(true);
			/*      */
			/* 1691 */ for (int i = 0; i < frameCount; i++) {
				/* 1692 */ ByteArrayOutputStream baosTiff = new ByteArrayOutputStream();
				/*      */
				/* 1694 */ Iterator<ImageWriter> iteriw = ImageIO.getImageWritersByFormatName("jpeg");
				/* 1695 */ ImageWriter iw = (ImageWriter) iteriw.next();
				/* 1696 */ ImageWriteParam iwp = iw.getDefaultWriteParam();
				/* 1697 */ iwp.setCompressionMode(2);
				/* 1698 */ iwp.setCompressionQuality(0.5F);
				/* 1699 */ iw.setOutput(new MemoryCacheImageOutputStream(baosTiff));
				/* 1700 */ iw.write(null, new IIOImage(ir.read(i), null, null), iwp);
				/* 1701 */ iw.dispose();
				/* 1702 */ baosTiff.flush();
				/* 1703 */ baosTiff.close();
				/*      */
				/* 1705 */ resultado.add(baosTiff.toByteArray());
				/*      */ }
			/* 1707 */ return resultado;
			/* 1708 */ } catch (Exception e) {
			/* 1709 */ e.printStackTrace();
			/* 1710 */ return null;
			/*      */ }
		/*      */ }

	/*      */
	/*      */
	/*      */
	/*      */
	/*      */ public ServiceResponse<ArchivoDTO> getZipAdjuntos(DatosEnrolamientoDTO datosdto,
			VersionEnrolamientoGeneralDTO versiondto, VersionEnrolamientoAdjuntosDTO adjuntosdto, String[] idadjuntos,
			String tipo, UsuarioDTO usuario) {
		/* 1718 */ ServiceResponse<ArchivoDTO> response = new ServiceResponse<ArchivoDTO>();
		/*      */
		/*      */ try {
			/* 1721 */ ByteArrayOutputStream byoszip = new ByteArrayOutputStream();
			/*      */
			/*      */
			/* 1724 */ List<ArchivoDTO> adjuntos = tipo.equals("huellas") ? adjuntosdto.getHuellas()
					: (tipo.equals("iris") ? adjuntosdto.getIris() : adjuntosdto.getDocumentos());
			/* 1725 */ List<ArchivoDTO> adjuntosFiles = new ArrayList<ArchivoDTO>();
			/* 1726 */ ZipOutputStream zos = new ZipOutputStream(byoszip);
			/* 1727 */ for (int i = 0; i < idadjuntos.length; i++) {
				/* 1728 */ for (int j = 0; j < adjuntos.size(); j++) {
					/* 1729 */ if (idadjuntos[i].equals(((ArchivoDTO) adjuntos.get(j)).getId())) {
						/* 1730 */ if (tipo.equals("huellas")) {
							/* 1731 */ adjuntosFiles.add(adjuntos.get(j));
							break;
							/*      */ }
						/* 1733 */ ArchivoDTO temp = null;
						/* 1734 */ if (versiondto.getHistorico() == 1) {
							/* 1735 */ temp = getImageTiffOnlyData(
									((ArchivoDTO) adjuntos.get(j))/* 1736 */ .getDescription());
							/*      */ } else {
							/* 1738 */ temp = getImageTiffActualOnlyData(
									((ArchivoDTO) adjuntos.get(j))/* 1739 */ .getDescription());
							/*      */ }
						/* 1741 */ temp.setId(idadjuntos[i]);
						/* 1742 */ adjuntosFiles.add(temp);
						/*      */
						/*      */ break;
						/*      */ }
					/*      */ }
				/*      */ }
			/* 1748 */ String[] nombresHuellas = { "pulgar_derecho", "indice_derecho", "medio_derecho",
					"anular_derecho", "menique_derecho", "pulgar_izquierdo", "indice_izquierdo", "medio_izquierdo",
					"anular_izquierdo", "menique_izquierdo" };
			/*      */
			/*      */
			/*      */
			/* 1752 */ String idsarchivos = "";
			/* 1753 */ for (int i = 0; i < adjuntosFiles.size(); i++) {
				/* 1754 */ String nombreArchivo = null;
				/* 1755 */ if (tipo.equals("huellas")) {
					/* 1756 */ int indice = Integer.parseInt(((ArchivoDTO) adjuntosFiles.get(i)).getId().substring(1))
							- 1;
					/* 1757 */ nombreArchivo = nombresHuellas[indice];
					/* 1758 */ } else if (tipo.equals("iris")) {
					/* 1759 */ nombreArchivo = ((ArchivoDTO) adjuntosFiles.get(i)).getId().equals("IOD")
							? "iris_derecho"
							: "iris_izquierdo";
					/*      */ } else {
					/* 1761 */ nombreArchivo = ((ArchivoDTO) adjuntosFiles.get(i)).getId().replace(".xml", "");
					/*      */ }
				/* 1763 */ ZipEntry zipEntry = new ZipEntry(nombreArchivo + (tipo.equals("huellas") ? ".png" : ".tif"));
				/* 1764 */ idsarchivos = idsarchivos + ((i != 0) ? ", " : "") + zipEntry;
				/* 1765 */ zos.putNextEntry(zipEntry);
				/* 1766 */ zos.write(((ArchivoDTO) adjuntosFiles.get(i)).getData());
				/* 1767 */ zos.closeEntry();
				/*      */ }
			/*      */
			/* 1770 */ AuditEvent aevent = new AuditEvent();
			/* 1771 */ aevent.setDateTime(new Date());
			/* 1772 */ aevent.setUser(usuario.getRfc());
			/* 1773 */ aevent.setIpAddress(usuario.getIp());
			/* 1774 */ aevent.setMacAddress(usuario.getMac());
			/* 1775 */ String amessage = datosdto.getRfc() + ", ";
			/* 1776 */ if (tipo.equals("huellas") || tipo.equals("iris")) {
				/* 1777 */ amessage = amessage + "DescargaBiometricos,";
				/*      */ } else {
				/* 1779 */ amessage = amessage + "DescargaDocumentos,";
				/*      */ }
			/* 1781 */ amessage = amessage + "[" + idsarchivos + "]";
			/* 1782 */ aevent.setMessage(amessage);
			/* 1783 */ aevent.setUuid(UUID.randomUUID().toString());
			/* 1784 */ AuditManager.register(aevent);
			/*      */
			/* 1786 */ zos.close();
			/* 1787 */ byoszip.close();
			/* 1788 */ response.setCode(0);
			/* 1789 */ response.setMessage("OK");
			/* 1790 */ ArchivoDTO archivodto = new ArchivoDTO();
			/* 1791 */ archivodto.setData(byoszip.toByteArray());
			/* 1792 */ response.setResult(archivodto);
			/* 1793 */ } catch (Exception e) {
			/* 1794 */ e.printStackTrace();
			/* 1795 */ response.setCode(-1);
			/*      */ }
		/*      */
		/* 1798 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<UsuarioDTO> autorizaUsuario(UsuarioDTO usuario) {
		/* 1802 */ ServiceResponse<UsuarioDTO> response = new ServiceResponse<UsuarioDTO>();
		/*      */
		/* 1804 */ if (usuario.getNumeroSerieCertificado() != null
				&& usuario.getNumeroSerieCertificado().substring(6, 12).equals("999999")) {
			/* 1805 */ usuario.setPermisos(new HashSet());
			/* 1806 */ response.setCode(0);
			/* 1807 */ response.setMessage("OK");
			/* 1808 */ response.setResult(usuario);
			/* 1809 */ return response;
			/*      */ }
		/*      */
		/* 1812 */ List<String> roles = null;
		/* 1813 */ ServiceResponse<List<String>> ldapRoles = null;
		/* 1814 */ if (System.getProperty("SAIE_LOCAL") == null) {
			/* 1815 */ ConsultarLdapRestClient ldapClient = new ConsultarLdapRestClient();
			/* 1816 */ ldapRoles = ldapClient.getRolesLdap(
					new ConsultaLdapRequestDTO(usuario.getRfc(), (usuario.getRfc().length() < 12) ? null : "largo"));
			/*      */ }
		/*      */ try {
			/* 1819 */ if (System.getProperty("SAIE_LOCAL") == null) {
				/* 1820 */ roles = DBManager.getInstance().obtenerRoles((List) ldapRoles.getResult());
				/*      */ } else {
				/* 1822 */ roles = new ArrayList<String>();
				/* 1823 */ roles.add("administradorCentral");
				/* 1824 */ roles.add("validador");
				/* 1825 */ roles.add("autorizador");
				/* 1826 */ roles.add("biometrista");
				/* 1827 */ roles.add("analista");
				/* 1828 */ roles.add("superValidador");
				/*      */ }
			/*      */
			/* 1831 */ HashSet<Permisos> permisos = new HashSet<Permisos>();
			/* 1832 */ for (int i = 0; i < roles.size(); i++) {
				/* 1833 */ String rol = (String) roles.get(i);
				/* 1834 */ if (rol.equals("administradorLocal") || rol.equals("operadorCentral")) {
					/* 1835 */ permisos.add(Permisos.VISOR);
					/* 1836 */ permisos.add(Permisos.DES_DOCUMENTOS);
					/* 1837 */ permisos.add(Permisos.CERTIFICAR);
					/* 1838 */ permisos.add(Permisos.DUPLICIDAD);
					/* 1839 */ } else if (rol.equals("operadorLocal")) {
					/* 1840 */ permisos.add(Permisos.VISOR);
					/* 1841 */ permisos.add(Permisos.DUPLICIDAD);
					/* 1842 */ } else if (rol.equals("administradorCentral")) {
					/* 1843 */ permisos.add(Permisos.VISOR);
					/* 1844 */ permisos.add(Permisos.DES_BIOMETRICOS);
					/* 1845 */ permisos.add(Permisos.DES_DOCUMENTOS);
					/* 1846 */ permisos.add(Permisos.CERTIFICAR);
					/* 1847 */ permisos.add(Permisos.DUPLICIDAD);
					/* 1848 */ permisos.add(Permisos.EXPUESTOS);
					/* 1849 */ } else if (rol.equals("biometrista")) {
					/* 1850 */ permisos.add(Permisos.VISOR);
					/* 1851 */ permisos.add(Permisos.DES_BIOMETRICOS);
					/* 1852 */ permisos.add(Permisos.DUPLICIDAD);
					/* 1853 */ permisos.add(Permisos.REPORTE_BIO);
					/* 1854 */ } else if (rol.equals("validador")) {
					/* 1855 */ permisos.add(Permisos.VISOR);
					/* 1856 */ permisos.add(Permisos.DUPLICIDAD);
					/* 1857 */ permisos.add(Permisos.VALIDAR);
					/* 1858 */ } else if (rol.equals("autorizador")) {
					/* 1859 */ permisos.add(Permisos.VISOR);
					/* 1860 */ permisos.add(Permisos.DUPLICIDAD);
					/* 1861 */ permisos.add(Permisos.AUTORIZAR);
					/* 1862 */ } else if (rol.equals("analista")) {
					/* 1863 */ permisos.add(Permisos.VISOR);
					/* 1864 */ permisos.add(Permisos.DES_DOCUMENTOS);
					/* 1865 */ } else if (rol.equals("superValidador")) {
					/* 1866 */ permisos.add(Permisos.VISOR);
					/* 1867 */ permisos.add(Permisos.DES_DOCUMENTOS);
					/* 1868 */ permisos.add(Permisos.VALIDAR);
					/* 1869 */ permisos.add(Permisos.AUTORIZAR);
					/* 1870 */ permisos.add(Permisos.VALIDA_NACIONAL);
					/* 1871 */ } else if (rol.equals("operadorSat")) {
					/* 1872 */ permisos.add(Permisos.VISOR);
					/* 1873 */ permisos.add(Permisos.DES_BIOMETRICOS);
					/* 1874 */ permisos.add(Permisos.DUPLICIDAD);
					/*      */ }
				/*      */ }
			/* 1877 */ usuario.setPermisos(permisos);
			/*      */
			/* 1879 */ String directorio = System.getProperty("USER_PROPERTIES");
			/* 1880 */ File f = new File(directorio + "/" + usuario.getNombre() + "_orden_tabla.ser");
			/*      */
			/* 1882 */ if (f.isFile() && f.canRead()) {
				/* 1883 */ FileInputStream file = new FileInputStream(f);
				/* 1884 */ ObjectInputStream inStream = new ObjectInputStream(file);
				/* 1885 */ String[] tabla_rfc = (String[]) inStream.readObject();
				/* 1886 */ String[] tabla_casos = (String[]) inStream.readObject();
				/* 1887 */ String[] tabla_hitlist = (String[]) inStream.readObject();
				/* 1888 */ String[] tabla_validar = (String[]) inStream.readObject();
				/* 1889 */ inStream.close();
				/* 1890 */ file.close();
				/*      */
				/* 1892 */ usuario.setTabla_rfc(tabla_rfc);
				/* 1893 */ usuario.setTabla_casos(tabla_casos);
				/* 1894 */ usuario.setTabla_hitlist(tabla_hitlist);
				/* 1895 */ usuario.setTabla_validar(tabla_validar);
				/*      */ }
			/*      */
			/*      */
			/* 1899 */ AuditEvent aevent = new AuditEvent();
			/* 1900 */ aevent.setUuid(UUID.randomUUID().toString());
			/* 1901 */ aevent.setDateTime(new Date());
			/* 1902 */ aevent.setUser(usuario.getRfc());
			/* 1903 */ aevent.setIpAddress(usuario.getIp());
			/* 1904 */ aevent.setMacAddress(usuario.getMac());
			/* 1905 */ String amessage = usuario.getLocalidad() + ", " + usuario.getNumeroSerieCertificado() + ", "
					+ "loginUsuario";
			/* 1906 */ aevent.setMessage(amessage);
			/* 1907 */ AuditManager.register(aevent);
			/*      */
			/*      */
			/* 1910 */ response.setCode(0);
			/* 1911 */ response.setMessage("OK");
			/* 1912 */ response.setResult(usuario);
			/* 1913 */ } catch (Exception e) {
			/* 1914 */ e.printStackTrace();
			/* 1915 */ response.setCode(-1);
			/*      */ }
		/*      */
		/* 1918 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<List<CatalogoDTO>> cargarCatalogoTipoObservacionDTO() {
		/* 1922 */ ServiceResponse<List<CatalogoDTO>> response = new ServiceResponse<List<CatalogoDTO>>();
		/*      */
		/*      */ try {
			/* 1925 */ List<CatalogoDTO> result = null;
			/* 1926 */ result = DBManager.getInstance().cargaCatalogoTipoObservacion();
			/*      */
			/* 1928 */ response.setCode(0);
			/* 1929 */ response.setMessage("OK");
			/* 1930 */ response.setResult(result);
			/*      */ }
		/* 1932 */ catch (Exception e) {
			/* 1933 */ e.printStackTrace();
			/* 1934 */ response.setCode(-1);
			/* 1935 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/*      */
		/* 1938 */ return response;
		/*      */ }

	/*      */ public ServiceResponse<List<CatalogoDTO>> cargarCatalogoTipoResolucionDTO() {
		/* 1941 */ ServiceResponse<List<CatalogoDTO>> response = new ServiceResponse<List<CatalogoDTO>>();
		/*      */
		/*      */ try {
			/* 1944 */ List<CatalogoDTO> result = null;
			/* 1945 */ result = DBManager.getInstance().cargaCatalogoTipoResolucion();
			/*      */
			/* 1947 */ response.setCode(0);
			/* 1948 */ response.setMessage("OK");
			/* 1949 */ response.setResult(result);
			/*      */ }
		/* 1951 */ catch (Exception e) {
			/* 1952 */ e.printStackTrace();
			/* 1953 */ response.setCode(-1);
			/* 1954 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/*      */
		/* 1957 */ return response;
		/*      */ }

	/*      */
	/*      */ public ServiceResponse<List<String>> getUnidadesLocalidad(String localidad) {
		/* 1961 */ ServiceResponse<List<String>> response = new ServiceResponse<List<String>>();
		/*      */
		/*      */ try {
			/* 1964 */ List<String> result = null;
			/* 1965 */ result = DBManager.getInstance().getUnidadesLocalidad(localidad);
			/*      */
			/* 1967 */ response.setCode(0);
			/* 1968 */ response.setMessage("OK");
			/* 1969 */ response.setResult(result);
			/*      */ }
		/* 1971 */ catch (Exception e) {
			/* 1972 */ e.printStackTrace();
			/* 1973 */ response.setCode(-1);
			/* 1974 */ response.setMessage("Error Desconocido: " + e.getMessage());
			/*      */ }
		/*      */
		/* 1977 */ return response;
		/*      */ }
	/*      */ }

/*
 * Location: C:\Users\Alejandro
 * Cortés\Desktop\VersionesVisor\saie-visor-2017ene17_1233.war!\WEB-INF\lib\
 * visor-documentos-presentacion-controller-0.0.1-SNAPSHOT.jar!\com\iecisa\sat\
 * saie\vf\integration\service\impl\ServiceEnrolamiento.class Java compiler
 * version: 8 (52.0) JD-Core Version: 1.0.7
 */