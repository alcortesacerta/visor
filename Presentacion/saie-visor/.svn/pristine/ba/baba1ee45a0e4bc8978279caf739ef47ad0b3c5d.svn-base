<%@page import="com.iecisa.sat.saie.vf.integration.service.dto.Permisos"%>
<%@page import="com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO"%>
<%@page import="java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:if test="<%=!((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DUPLICIDAD)%>">
    <c:redirect url="/menu.jsp"></c:redirect>
</c:if>
<jsp:include page="/duplicado_auxiliar.jsp" flush="false"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - Visor de duplicados - Huella dactilar</title>
	
	<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/duplicado_huella.css?v=${applicationScope.version}" type="text/css" media="screen">
	
	<script src="js/jquery-1.10.2.js"></script>
  	<script src="js/jquery-ui.js"></script>
  	<script type="text/javascript" src="js/zoom_fusor.js?v=${applicationScope.version}"></script>
	<script type="text/javascript" src="js/duplicado_huella.js?v=${applicationScope.version}"></script>
	<script type="text/javascript">
		var rfc1 = "${sessionScope.enrolamientoDto1.rfc}";
		var rfc2 = "${sessionScope.enrolamientoDto2.rfc}";
		var version1 = "${sessionScope.version1}";
		var version2 = "${sessionScope.version2}";
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
					<a href="duplicado_iris.jsp?caso=${sessionScope.duplicadoDto.caso_numero_caso}"><img src="imgs/boton_ojodoc.png" width="50px" height="50px"></a>
				</div>
				<div class="botonBiometrico">
					<a href="duplicado_rostro.jsp?caso=${sessionScope.duplicadoDto.caso_numero_caso}"><img src="imgs/boton_persona.png" width="50px" height="50px"></a>
				</div>
			</div>
  			<div id="huellasHolder">
  				<div class="holderTitulo">
  					<img alt="icono_huella" src="imgs/icono_huella.png">
  					
  					Fusor - Huella dactilar - No. Caso: ${sessionScope.duplicadoDto.caso_numero_caso}
  					${empty sessionScope.scoreDuplicidad ? "" : "- Porcentaje de similitud: "}
  					${sessionScope.scoreDuplicidad}
  					${empty sessionScope.scoreDuplicidad ? "": "%"}
  						
  					<a href="duplicado_tablas.jsp"><img id="bCerrar" src="imgs/boton_cerrar.png"></a>
  				</div>
  				<div class="huellaHolder" id="huellaHolder1">
  					<div class="datosHolder">
  						<p class="labelCandidato"><b>Candidato</b></p>
  						<p><b>Nombre:</b> ${sessionScope.duplicadoDto.nombre_completo}</p>
  						<p><b>RFC:</b><a href="visor_buscar.jsp?rfc=${sessionScope.duplicadoDto.rfc}">${sessionScope.duplicadoDto.rfc}</a></p>
  						<p><b>Fecha Enrolamiento:</b> ${sessionScope.duplicadoDto.fecha_enrolamiento}</p>
  						<p><b>Localidad Enrolamiento:</b> ${sessionScope.duplicadoDto.localidad_enrolamiento}</p>
  					</div>
  					<div id="huellaIzq" class="huellaGrande">
  						<div class="boton_fullscreen"><img src="imgs/boton_full.png"></div>
  						<div class="boton_mas"><img src="imgs/boton_mas.png"></div>
  						<div class="boton_menos"><img src="imgs/boton_menos.png"></div>
  					</div>
  				</div>
  				<div class="huellaHolder" id="huellaHolder2">
  					<div class="datosHolder">
  						<p class="labelCandidato"><b>Adjudicante</b></p>
  						<p><b>Nombre:</b> ${sessionScope.duplicadoDto.nombre_completo2}</p>
  						<p><b>RFC:</b><a href="visor_buscar.jsp?rfc=${sessionScope.duplicadoDto.rfc2}">${sessionScope.duplicadoDto.rfc2}</a></p>
  						<p><b>Fecha Enrolamiento:</b> ${sessionScope.duplicadoDto.fecha_enrolamiento2}</p>
  						<p><b>Localidad Enrolamiento:</b> ${sessionScope.duplicadoDto.localidad_enrolamiento2}</p>
  					</div>
  					<div id="huellaDer" class="huellaGrande">
  						<div class="boton_fullscreen"><img src="imgs/boton_full.png"></div>
  						<div class="boton_mas"><img src="imgs/boton_mas.png"></div>
  						<div class="boton_menos"><img src="imgs/boton_menos.png"></div>
  					</div>
  				</div>
  				<div style="clear: both;"></div>
  			</div>
  			<div id="selectorHolder">
  				<div class="holderTitulo">
  					<img alt="icono_huella" src="imgs/icono_huella.png">
  					Huellas dactilares
  				</div>
  				<div class="huellasSelectorHolder">
  					<div class="huellaSelector" id="huella10"></div>
  					<div class="huellaSelector" id="huella09"></div>
  					<div class="huellaSelector" id="huella08"></div>
  					<div class="huellaSelector" id="huella07"></div>
  					<div class="huellaSelector" id="huella06"></div>
  					<p>Mano izquierda</p>
  				</div>
  				<div id="manosSelector">
  					<div id="circuloRojo"><img src="imgs/marcador_mano.png" width="15px" height="15px"></div>
  					<img src="imgs/manos50.png" width="200px" height="160px">
  				</div>
  				<div class="huellasSelectorHolder">
  					<div class="huellaSelector" id="huella01"></div>
  					<div class="huellaSelector" id="huella02"></div>
  					<div class="huellaSelector" id="huella03"></div>
  					<div class="huellaSelector" id="huella04"></div>
  					<div class="huellaSelector" id="huella05"></div>
  					<p>Mano derecha</p>
  				</div>
  				
  			</div>
  		</div>
  	</div>
  	
  </body>
</html>
