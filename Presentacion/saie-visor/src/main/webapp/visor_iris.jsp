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
    <title>SAIE III - Visor de documentos - Iris</title>
	
	<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/visor_iris.css?v=${applicationScope.version}" type="text/css" media="screen">
	
	<script src="js/jquery-1.10.2.js"></script>
	
	<script type="text/javascript" src="js/visor_iris.js?v=${applicationScope.version}"></script>
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
					<a href="visor_huella.jsp"><img src="imgs/boton_huelladoc.png" width="50px" height="50px"></a>
				</div>
				<div class="botonBiometrico">
					<img src="imgs/boton_ojodoc2.png" width="50px" height="50px">
				</div>
				<div class="botonBiometrico">
					<a href="visor_doc.jsp"><img src="imgs/boton_doc.png" width="50px" height="50px"></a>
				</div>
			</div>
  			<div id="huellasHolder">
  				<div class="holderTitulo">
  					<img alt="icono_huella" src="imgs/icono_iris.png"  style="top:0px;">
  					Iris
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
  				<table id="tablaIris">
  					<tr>
  						<td><div id="iris0" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=iris&version=${sessionScope.version}&imagen=IOD');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=iris&version=${sessionScope.version}&imagen=IOD" class="imagenVisor${sessionScope.adjuntosDto.iris[0].name == 'amputado' || sessionScope.adjuntosDto.iris[0].name == 'vendado' || sessionScope.adjuntosDto.iris[0].name == 'noaplica'?'No':''}" >
							<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="IOD"></div>
			  				</c:if>
  						</div></td>
  						<td>
  							<div id="marcadorHolder">
  								<div id="circuloRojo"><img src="imgs/marcador_iris1.png"></div>
  								<img id="marcaIris" src="imgs/rostro250n.png">
  							</div>
  						</td>
  						<td><div id="iris1" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=iris&version=${sessionScope.version}&imagen=IOZ');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=iris&version=${sessionScope.version}&imagen=IOZ" class="imagenVisor${sessionScope.adjuntosDto.iris[1].name == 'amputado' || sessionScope.adjuntosDto.iris[1].name == 'vendado' || sessionScope.adjuntosDto.iris[1].name == 'noaplica'?'No':''}" >
  							<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_BIOMETRICOS)%>">
				  				<div class="downloadLink" id="IOZ"></div>
			  				</c:if>
  						</div></td>
  					</tr>
  					<tr>
  						<td>
  							Iris Derecho
  						</td>
  						<td height="50px;">
  							
  						</td>
  						<td>
  							Iris Izquierdo
  						</td>
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
