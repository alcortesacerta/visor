<%@page
	import="com.iecisa.sat.saie.vf.integration.service.dto.VersionEnrolamientoGeneralDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="/visor_documentos_auxiliar.jsp" flush="false" />
<c:if test="${sise == 'error'}">
	<c:redirect url="/visor_buscar.jsp?na=true"></c:redirect>
</c:if>
<c:if test="${empty sessionScope.versionEnrolamiento}">
	<c:redirect url="/visor_buscar.jsp"></c:redirect>
</c:if>
<c:if test="<%=!((UsuarioDTO) session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.VISOR)%>">
	<c:redirect url="/menu.jsp"></c:redirect>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SAIE III - Visor de documentos - búsqueda</title>

<script src="js/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
<link rel="stylesheet" href="css/visor_documentos.css?v=${applicationScope.version}" type="text/css" media="screen">
<script type="text/javascript" src="js/visor_documentos.js?v=${applicationScope.version}"></script>
<script type="text/javascript" src="js/visor_imagenes.js?v=${applicationScope.version}"></script>
<script type="text/javascript">
	var rfc = "${sessionScope.enrolamientoDto.rfc}";
</script>

<c:choose>
	<c:when test="${sessionScope.versionEnrolamiento.tipoEnrolamiento.charAt(0) == 'F'.charAt(0)}">
		<style type="text/css">
			.holderDatoPar {
				margin-bottom: 10px;
			}
			.holderDatoFull {
				width: 500px;
				float: left;
				margin-bottom: 10px;
			}
			.holderDatos {
				width: 500px;
			}
			.holderBox {
				width: 250px;
				float: left;
			}
		</style>
	</c:when>
	<c:otherwise>
		<style type="text/css">
			.holderDatoPar {
				width: 280px;
				float: left;
				margin-bottom: 10px;
			}
			.holderDatoFull {
				width: 840px;
				float: left;
				margin-bottom: 10px;
			}
			.holderDatos {
				width: 850px;
			}
			.holderBox {
				width: 280px;
				float: left;
			}
		</style>
	</c:otherwise>
</c:choose>

</head>

<body>
	<%@ include file="header.jsp"%>

	<div id="dialogoHolderFiel" class="dialogoHolder">
		<div id="dialogo">
			<div class="holderTitulo">
				<img alt="icono_documento" src="imgs/icono_doc2.png"> Firma de
				transacción (con FIEL) <img id="bCerrarDialogoFiel"
					src="imgs/boton_cerrar.png" class="botonCerrar">
			</div>
			<div id="hfiel" class="holder">
				<form id="firmaFiel" action="firma_fiel.jsp" method="post"
					enctype="multipart/form-data">
					<div class="label">Clave de la llave privada:</div>
					<div class="campo">
						<input type="password" id="claveFiel" name="claveFiel"></input>
					</div>
					<div class="label">Llave privada (*.key):</div>
					<div class="campo">
						<input type="file" id="llaveFiel" name="llaveFiel" accept=".key"></input>
					</div>
					<div class="label">Certificado (*.cer):</div>
					<div class="campo">
						<input type="file" id="certificadoFiel" name="certificadoFiel"
							accept=".cer"></input>
					</div>
					<div id="comentariosHolder">
						<div class="label">Comentarios:</div>
						<div class="campo">
							<textarea rows="4" cols="29" id="fcomentarios"
								name="fcomentarios" style="resize: none;"></textarea>
						</div>
					</div>
					<input type="hidden" id="bvalidar" value="true" name="bvalidar" />
					<div class="labelError" id="errorFiel"></div>
					<div class="boton">
						<input type="submit" value="firmar" id="botonFirmar"></input>
					</div>
				</form>
			</div>
		</div>
	</div>

	<c:if test="${not empty requestScope.mensaje}">
		<div id="dialogoHolderError" class="dialogoHolder">
			<div id="dialogo">
				<div class="holderTitulo">
					<img alt="icono_documento" src="imgs/icono_doc2.png"> <img
						id="bCerrarDialogoError" src="imgs/boton_cerrar.png"
						class="botonCerrar">
				</div>
				<div class="holder">
					<div id="errorLabel">${requestScope.mensaje}</div>
				</div>
			</div>
		</div>
	</c:if>

	<div id="holder">
		<div id="innerHolder">
			<div id="contribuyenteHolder">
				<div class="holderTitulo">
					<img alt="icono_persona" src="imgs/icono_persona.png"> Datos
					del contribuyente <a
						href="${sessionScope.referer == 'validar' ? 'visor_validar.jsp':'visor_buscar.jsp'}"><img
						id="bCerrar" src="imgs/boton_cerrar.png"></a>
				</div>
				<div id="ligas_vez">
					<c:forEach var="i" begin="0"
						end="${fn:length(sessionScope.enrolamientoDto.versiones)-1}">
  						${i == 0?'':'|'}
  						<c:choose>
							<c:when test="${i != sessionScope.version}">
								<a href="visor_documentos.jsp?version=${i}">
							</c:when>
						</c:choose>
						${i == sessionScope.version?'<span>':''}
  						${i+1}${sessionScope.referer != 'validar' ? 'a vez' : 'a actualización'}
  						${i == sessionScope.version?'</span>':''}
  						<c:choose>
							<c:when test="${i != sessionScope.version}">
								</a>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
				<c:choose>
					<c:when
						test="${sessionScope.versionEnrolamiento.tipoEnrolamiento.charAt(0) == 'F'.charAt(0)}">
						<div id="holderFoto">
							<img id="foto"
								class="imagenVisor${sessionScope.versionEnrolamiento.foto.name == 'noaplica'?'No':''}"
								alt="foto"
								src="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=foto&version=${sessionScope.version}"
								width="180px" height="240px"
								data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=foto&version=${sessionScope.version}">
						</div>
						<div id="holderFirma">
							<div>
								<img id="imgFirma"
									class="imagenVisor${sessionScope.versionEnrolamiento.firma.name == 'noaplica'?'No':''}"
									src="imagen/imagen/${sessionScope.enrolamientoDto.rfc}?tipo=firma&version=${sessionScope.version}"
									width="170px" height="128px"
									data-imagen="imagen/imagen/${sessionScope.enrolamientoDto.rfc}?tipo=firma&version=${sessionScope.version}">
							</div>
							<div id="botonesHolder">
								<div class="botonBiometrico">
									<a
										href="visor_huella.jsp?rfc=${sessionScope.enrolamientoDto.rfc}"><img
										src="imgs/boton_huelladoc.png" width="50px" height="50px"></a>
								</div>
								<div class="botonBiometrico">
									<a
										href="visor_iris.jsp?rfc=${sessionScope.enrolamientoDto.rfc}"><img
										src="imgs/boton_ojodoc.png" width="50px" height="50px"></a>
								</div>
								<div class="botonBiometrico">
									<a href="visor_doc.jsp?rfc=${sessionScope.enrolamientoDto.rfc}"><img
										src="imgs/boton_doc.png" width="50px" height="50px"></a>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div id="holderBotonMoral">
							<a href="visor_doc.jsp?rfc=${sessionScope.enrolamientoDto.rfc}"><img
								src="imgs/boton_doc.png" width="50px" height="50px"></a>
						</div>
					</c:otherwise>
				</c:choose>

				<div class="holderDatos">
					<c:choose>
						<c:when
							test="${sessionScope.versionEnrolamiento.tipoEnrolamiento.charAt(0) == 'F'.charAt(0)}">
							<div class="holderDatoFull">
								<p>
									<b>Nombre:</b>
								</p>
								<p>${sessionScope.versionEnrolamiento.nombre}</p>
							</div>
							<div class="holderBox">
								<div class="holderDatoPar">
									<p>
										<b>RFC:</b>
									</p>
									<p>${sessionScope.enrolamientoDto.rfc}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Localidad de enrolamiento:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.localidadEnrolamiento}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Fecha de enrolamiento:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.fechaEnrolamiento}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Enrolamiento Histórico:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.historico == 1 ? "Si" : "No"}</p>
								</div>
								<c:if test="${not empty sessionScope.versionEnrolamiento.estatusProceso}">
									<div class="holderDatoPar">
										<p>
											<b>Estatus Enrolamiento:</b>
										</p>
										<p>${sessionScope.versionEnrolamiento.estatusProceso}</p>
									</div>
								</c:if>
								<c:if test="${not empty sessionScope.versionEnrolamiento.representante}">
									<div class="holderDatoPar">
										<p>
											<b>Representante:</b>
										</p>
										<p>
											<a
												href="visor_buscar.jsp?rfc=${sessionScope.versionEnrolamiento.representante}">${sessionScope.versionEnrolamiento.representante}</a>
										</p>
									</div>
								</c:if>
								<c:if test="${not empty sessionScope.versionEnrolamiento.estatusUnidad}">
									<div class="holderDatoPar">
										<p>
											<b>Estatus Unidad Enrolamiento:</b>
										</p>
										<p>${sessionScope.versionEnrolamiento.estatusUnidad}</p>
									</div>
								</c:if>
								<c:if test="${sessionScope.enrolamientoDto.duplicidad}">
									<div class="holderDatoPar">
										<p>
											<b>Duplicidad:</b>
										</p>
										<p>${sessionScope.enrolamientoDto.duplicidad}</p>
									</div>
								</c:if>
							</div>

							<div class="holderBox">
								<div class="holderDatoPar">
									<p>
										<b>CURP:</b>
									</p>
									<p>${not empty sessionScope.enrolamientoDto.curp ? sessionScope.enrolamientoDto.curp : "--"}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Unidad de enrolamiento:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.unidadEnrolamiento}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Tipo de enrolamiento:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.tipoEnrolamiento}</p>
								</div>
								<c:if test="${not empty sessionScope.versionEnrolamiento.tipoMovimiento}">
									<div class="holderDatoPar">
										<p>
											<b>Tipo de movimiento:</b>
										</p>
										<p>${sessionScope.versionEnrolamiento.tipoMovimiento}</p>
									</div>
								</c:if>
								<c:if
									test="${not empty sessionScope.versionEnrolamiento.detalleTipoEnrolamiento}">
									<div class="holderDatoPar">
										<p>
											<b>Detalle enrolamiento:</b>
										</p>
										<p>${sessionScope.versionEnrolamiento.detalleTipoEnrolamiento}</p>
									</div>
								</c:if>
							</div>
							<div style="clear: both; margin-bottom: 20px;"></div>
						</c:when>
						<c:otherwise>
							<div class="holderDatoFull">
								<p>
									<b>Razón social:</b>
								</p>
								<p>${sessionScope.versionEnrolamiento.nombre}</p>
							</div>
							<div class="holderBox">
								<div class="holderDatoPar">
									<p>
										<b>RFC:</b>
									</p>
									<p>${sessionScope.enrolamientoDto.rfc}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Fecha de enrolamiento:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.fechaEnrolamiento}</p>
								</div>
								<c:if test="${not empty sessionScope.versionEnrolamiento.representante}">
									<div class="holderDatoPar">
										<p>
											<b>Representante:</b>
										</p>
										<p>
											<a href="visor_buscar.jsp?rfc=${sessionScope.versionEnrolamiento.representante}">${sessionScope.versionEnrolamiento.representante}</a>
										</p>
									</div>
								</c:if>
								<c:if test="${not empty sessionScope.versionEnrolamiento.detalleTipoEnrolamiento}">
									<div class="holderDatoPar">
										<p>
											<b>Detalle enrolamiento:</b>
										</p>
										<p>${sessionScope.versionEnrolamiento.detalleTipoEnrolamiento}</p>
									</div>
								</c:if>
							</div>

							<div class="holderBox">
								<div class="holderDatoPar">
									<p>
										<b>CURP:</b>
									</p>
									<p>${not empty sessionScope.enrolamientoDto.curp ? sessionScope.enrolamientoDto.curp : "--"}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Localidad de enrolamiento:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.localidadEnrolamiento}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Enrolamiento Histórico:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.historico == 1 ? "Si" : "No"}</p>
								</div>								
								<c:if test="${not empty sessionScope.versionEnrolamiento.tipoMovimiento}">
									<div class="holderDatoPar">
										<p>
											<b>Tipo de movimiento:</b>
										</p>
										<p>${sessionScope.versionEnrolamiento.tipoMovimiento}</p>
									</div>
								</c:if>
								<c:if test="${sessionScope.enrolamientoDto.duplicidad}">
									<div class="holderDatoPar">
										<p>
											<b>Duplicidad:</b>
										</p>
										<p>${sessionScope.enrolamientoDto.duplicidad}</p>
									</div>
								</c:if>
							</div>
							<div class="holderBox">
								<div class="holderDatoPar">
									<p>
										<b>Tipo de enrolamiento:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.tipoEnrolamiento}</p>
								</div>
								<div class="holderDatoPar">
									<p>
										<b>Unidad de enrolamiento:</b>
									</p>
									<p>${sessionScope.versionEnrolamiento.unidadEnrolamiento}</p>
								</div>
								<c:if test="${not empty sessionScope.versionEnrolamiento.estatusUnidad}">
									<div class="holderDatoPar">
										<p>
											<b>Estatus Unidad Enrolamiento:</b>
										</p>
										<p>${sessionScope.versionEnrolamiento.estatusUnidad}</p>
									</div>
								</c:if>
								<c:if test="${not empty sessionScope.versionEnrolamiento.estatusProceso}">
									<div class="holderDatoPar">
										<p>
											<b>Estatus Enrolamiento:</b>
										</p>
										<p>${sessionScope.versionEnrolamiento.estatusProceso}</p>
									</div>
								</c:if>
							</div>
							<div style="clear: both; margin-bottom: 20px;"></div>
						</c:otherwise>
					</c:choose>
				</div>
				<c:if test="<%=(((UsuarioDTO) session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.VALIDAR) && ((VersionEnrolamientoGeneralDTO) session.getAttribute(\"versionEnrolamiento\")).getEstatus() == 3)
						|| (((UsuarioDTO) session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.AUTORIZAR) && ((VersionEnrolamientoGeneralDTO) session.getAttribute(\"versionEnrolamiento\")).getEstatus() == 5)%>">
					<div id="holderBotonesValidar">
						<button type="button" id="botonValidar">${sessionScope.versionEnrolamiento.estatus == 3 ? "validar" : "autorizar"}</button>
						<button type="button" id="botonDescartar">descartar</button>
					</div>
				</c:if>
				<div style="clear: both; margin-bottom: 20px;"></div>
			</div>
		</div>
	</div>

</body>
</html>
