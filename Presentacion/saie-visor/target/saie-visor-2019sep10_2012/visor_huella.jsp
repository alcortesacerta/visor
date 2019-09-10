<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/visor_adjuntos_auxiliar.jsp" flush="false"/>
<c:if test="${empty sessionScope.adjuntosDto}">
    <c:redirect url="/visor_buscar.jsp"></c:redirect>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=9" />
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - Visor de documentos - Huellas Dactilares</title>
	
	<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/visor_huella.css?v=${applicationScope.version}" type="text/css" media="screen">
	
	<script src="js/jquery-1.10.2.js"></script>
	
	<script type="text/javascript" src="js/visor_huella.js?v=${applicationScope.version}"></script>
	<script type="text/javascript" src="js/visor_imagenes.js?v=${applicationScope.version}"></script>
	
	<script type="text/javascript">
		var rfc = "${sessionScope.enrolamientoDto.rfc}";
		var version = "${sessionScope.version}";
	</script>
	
  </head>
  
  <body>
  	<%@ include file="header.jsp" %>
  	
  	<div id="holder">
  		<div id="innerHolder">
  			<div id="botonesHolder">
  				<div class="botonBiometrico">
					<img src="imgs/boton_huelladoc2.png" width="50px" height="50px">
				</div>
				<div class="botonBiometrico">
					<a href="visor_iris.jsp"><img src="imgs/boton_ojodoc.png" width="50px" height="50px"></a>
				</div>
				<div class="botonBiometrico">
					<a href="visor_doc.jsp"><img src="imgs/boton_doc.png" width="50px" height="50px"></a>
				</div>
			</div>
  			<div id="huellasHolder">
  				<div class="holderTitulo">
  					<img alt="icono_huella" src="imgs/icono_huella.png">
  					Huellas Dactilares
  					<a href="visor_documentos.jsp"><img id="bCerrar" src="imgs/boton_cerrar.png"></a>
  				</div>
  				<div class="datosContribuyente">
				  RFC: <span>${sessionScope.enrolamientoDto.rfc}</span>
				  <br>
				  <c:choose>
					    <c:when test="${sessionScope.versionEnrolamiento.tipoEnrolamiento.charAt(0) == 'F'.charAt(0)}">
					    	Nombre del contribuyente: <span>${sessionScope.versionEnrolamiento.nombre}</span>
					    </c:when>
					    <c:otherwise>
					    	Razón social: <span>${sessionScope.versionEnrolamiento.nombre}</span>
					    </c:otherwise>
				  </c:choose>
				</div>
  				<table id="tablaHuellas">
  					<tr>
  						<td><div id="huella0" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H10');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H10" class="imagenVisor${sessionScope.adjuntosDto.huellas[0].name == 'amputado' || sessionScope.adjuntosDto.huellas[0].name == 'vendado' || sessionScope.adjuntosDto.huellas[0].name == 'noaplica'?'No':''}" >
  							<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H10"></div>
			  				</c:if>
  						</div></td>
  						<td><div id="huella1" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H09');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H09" class="imagenVisor${sessionScope.adjuntosDto.huellas[1].name == 'amputado' || sessionScope.adjuntosDto.huellas[1].name == 'vendado' || sessionScope.adjuntosDto.huellas[1].name == 'noaplica'?'No':''}" >
  							<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H09"></div>
			  				</c:if>
  						</div></td>
  						<td><div id="huella2" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H08');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H08" class="imagenVisor${sessionScope.adjuntosDto.huellas[2].name == 'amputado' || sessionScope.adjuntosDto.huellas[2].name == 'vendado' || sessionScope.adjuntosDto.huellas[2].name == 'noaplica'?'No':''}" >
							<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H08"></div>
			  				</c:if>
  						</div></td>
  						<td><div id="huella3" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H07');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H07" class="imagenVisor${sessionScope.adjuntosDto.huellas[3].name == 'amputado' || sessionScope.adjuntosDto.huellas[3].name == 'vendado' || sessionScope.adjuntosDto.huellas[3].name == 'noaplica'?'No':''}" >							
  							<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H07"></div>
			  				</c:if>
  						</div></td>
  						<td><div id="huella4" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H06');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H06" class="imagenVisor${sessionScope.adjuntosDto.huellas[4].name == 'amputado' || sessionScope.adjuntosDto.huellas[4].name == 'vendado' || sessionScope.adjuntosDto.huellas[4].name == 'noaplica'?'No':''}" >
  						  	<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H06"></div>
			  				</c:if>
  						</div></td>
  					</tr>
  					<tr>
  						<td class="tdTexto">Meñique izquierdo</td>
  						<td class="tdTexto">Anular izquierdo</td>
  						<td class="tdTexto">Medio izquierdo</td>
  						<td class="tdTexto">Índice izquierdo</td>
  						<td class="tdTexto">Pulgar izquierdo</td>
  					</tr>
  					<tr>
  						<td><div id="huella5" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H01');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H01" class="imagenVisor${sessionScope.adjuntosDto.huellas[5].name == 'amputado' || sessionScope.adjuntosDto.huellas[5].name == 'vendado' || sessionScope.adjuntosDto.huellas[5].name == 'noaplica'?'No':''}" >
						  	<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H01"></div>
			  				</c:if>
  						</div></td>
  						<td><div id="huella6" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H02');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H02" class="imagenVisor${sessionScope.adjuntosDto.huellas[6].name == 'amputado' || sessionScope.adjuntosDto.huellas[6].name == 'vendado' || sessionScope.adjuntosDto.huellas[6].name == 'noaplica'?'No':''}" >
  						  	<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H02"></div>
			  				</c:if>
  						</div></td>
  						<td><div id="huella7" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H03');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H03" class="imagenVisor${sessionScope.adjuntosDto.huellas[7].name == 'amputado' || sessionScope.adjuntosDto.huellas[7].name == 'vendado' || sessionScope.adjuntosDto.huellas[7].name == 'noaplica'?'No':''}" >
  						  	<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H03"></div>
			  				</c:if>
  						</div></td>
  						<td><div id="huella8" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H04');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H04" class="imagenVisor${sessionScope.adjuntosDto.huellas[8].name == 'amputado' || sessionScope.adjuntosDto.huellas[8].name == 'vendado' || sessionScope.adjuntosDto.huellas[8].name == 'noaplica'?'No':''}" >
  						  	<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H04"></div>
			  				</c:if>
  						</div></td>
  						<td><div id="huella9" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H05');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=huella&version=${sessionScope.version}&imagen=H05" class="imagenVisor${sessionScope.adjuntosDto.huellas[9].name == 'amputado' || sessionScope.adjuntosDto.huellas[9].name == 'vendado' || sessionScope.adjuntosDto.huellas[9].name == 'noaplica'?'No':''}" >
  						  	<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="H05"></div>
			  				</c:if>
  						</div></td>
  					</tr>
  					<tr>
  						<td class="tdTexto">Pulgar derecho</td>
  						<td class="tdTexto">Índice derecho</td>
  						<td class="tdTexto">Medio derecho</td>
  						<td class="tdTexto">Anular derecho</td>
  						<td class="tdTexto">Meñique derecho</td>
  					</tr>
  					<tr>	
  						<td></td>
  						<td></td>
  						<td>
  							<div id="manosHolder">
  								<div id="circuloRojo"><img src="imgs/marcador_mano.png"></div>
  								<img src="imgs/manos50.png">
  							</div>
  						</td>
  						<td></td>
  						<td></td>
  					</tr>
  				</table>
  				<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
  					<button id="bdescargar" type="button">descargar</button>
  				</c:if>
  			</div>
  		</div>
  	</div>
  	
  </body>
</html>
