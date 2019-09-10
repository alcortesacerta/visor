<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>SAIE III - Visor de documentos - Documentos</title>
	
	<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/jquery-ui.css?v=${applicationScope.version}">
	<link rel="stylesheet" href="css/visor_doc.css?v=${applicationScope.version}" type="text/css" media="screen">
	
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery-ui.js"></script>
	
	<script type="text/javascript" src="js/visor_doc.js?v=${applicationScope.version}"></script>
	<script type="text/javascript" src="js/visor_imagenes.js?v=${applicationScope.version}"></script>
	
	<script type="text/javascript">
		var rfc = "${sessionScope.enrolamientoDto.rfc}";
		var version = "${sessionScope.version}";
	</script>
	
  </head>
  
  <body>
  	<%@ include file="header.jsp" %>
  	
  	<div id="dialogoHolder">
  		<div id="dialogo">
  			<div class="holderTitulo">
				<img alt="icono_documento" src="imgs/icono_doc2.png">
				Certificar documentos
				<img id="bCerrarDialogo" src="imgs/boton_cerrar.png">
			</div>
			<p><label for="tipoLeyenda">Cargo:<img src="imgs/help.png" style="margin-left:5px" width="14px" height="14px" title="Perfil del funcionario que certifica."></label>
			<select name="tipoLeyenda" id="tipoLeyenda">
				<option value="Central">Administrador Central</option>
				<option value="Admin">Administrador</option>
				<option value="Coordinador">Coordinador Nacional</option>
				<option value="Adsc">Administrador Desconcentrado</option>
				<option value="SubAdmin">Subadministrador Desconcentrado</option>
			</select></p>
			<p><label>Nombre de la ADSC:<img src="imgs/help.png" style="margin-left:5px" width="14px" height="14px" title="Nombre de la ADSC que esta certificando la documentación"></label><input type="text" id="nombrealsc"/></p>
			<p><label>Fracción:<img src="imgs/help.png" style="margin-left:5px" width="14px" height="14px" title="Especificar párrafo numeral e inciso en el que se encuentra contemplado el funcionario que realiza la certificación."></label><input type="text" id="fraccionalsc"/></p>
			<p><label>Administrador que certifica:<img src="imgs/help.png" style="margin-left:5px" width="14px" height="14px" title="Nombre del Administrador Local"></label><input type="text" id="fadmin" /></p>
			<p><label>Ciudad donde se certifica:</label><input type="text" id="fciudad"/></p>
			<p><button id="bdescargarCertificado" type="button" >descargar </button><img src="imgs/help.png"  id = "imgHelp" width="15px" height="15px" title="Una vez descargada la certificación no podrá descargarse nuevamente, de ser necesario tendrá que generar una nueva certificación con un número de folio diferente"/></p>
  		</div>
  	</div>
  	
  	<div id="holder">
  		<div id="innerHolder">
  			<c:if test="${sessionScope.versionEnrolamiento.tipoEnrolamiento.charAt(0) == 'F'.charAt(0)}">
	  			<div id="botonesHolder">
	  				<div class="botonBiometrico">
						<a href="visor_huella.jsp"><img src="imgs/boton_huelladoc.png" width="50px" height="50px"></a>
					</div>
					<div class="botonBiometrico">
						<a href="visor_iris.jsp"><img src="imgs/boton_ojodoc.png" width="50px" height="50px"></a>
					</div>
					<div class="botonBiometrico">
						<img src="imgs/boton_doc2.png" width="50px" height="50px" title="prueba 3">
					</div>
				</div>
			</c:if>
  			<div id="documentosHolder">
  				<div class="holderTitulo">
  					<img alt="icono_huella" src="imgs/icono_doc2.png">
  					Documentos
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
  				<c:if test="${fn:length(sessionScope.adjuntosDto.documentos) > 0}">
	  				<c:forEach var="i" begin="0" end="${fn:length(sessionScope.adjuntosDto.documentos)-1}">
		  				<div class="documento">
		  					<c:choose>
							    <c:when test="${not empty sessionScope.adjuntosDto.documentos[i].description}">
							        <div id="doc${i}" style="background-image: url('imagen/${sessionScope.enrolamientoDto.rfc}?tipo=documento&version=${sessionScope.version}&imagen=${sessionScope.adjuntosDto.documentos[i].id}');" data-imagen="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=documento&version=${sessionScope.version}&imagen=${sessionScope.adjuntosDto.documentos[i].id}" class="imagenVisor">
				  						<img src="imagen/${sessionScope.enrolamientoDto.rfc}?tipo=documento&version=${sessionScope.version}&imagen=${sessionScope.adjuntosDto.documentos[i].id}" width="150px" style="visibility: hidden;">
				  					</div>
				  					<c:choose>
					  					<c:when test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.CERTIFICAR) || ((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_DOCUMENTOS)%>">
							  				<div class="downloadLink" id="${sessionScope.adjuntosDto.documentos[i].id}"></div>
							  				<div class="documentoNombre">${sessionScope.adjuntosDto.documentos[i].name}</div>
						  				</c:when>
					  					<c:otherwise>
					  						<div class="documentoNombre" style="top:0px;">${sessionScope.adjuntosDto.documentos[i].name}</div>
					  					</c:otherwise>
				  					</c:choose>
							    </c:when>
							    <c:otherwise>
							    	<div id="doc${i}" style="font-size: 12px; font-weight: normal;" class="imagenVisorTexto">
				  						${sessionScope.adjuntosDto.documentos[i].name}
				  					</div>
				  					<div class="documentoNombre" style="top: 0px;">${sessionScope.adjuntosDto.documentos[i].id}</div>
							    </c:otherwise>
							</c:choose>
		  				</div>
	  				</c:forEach>
  				</c:if>
  				<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.CERTIFICAR) && !session.getAttribute(\"referer\").equals(\"validar\")%>">
	  				<button id="bcertificar" type="button">certificar</button>
  				</c:if>
  				<c:if test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DES_DOCUMENTOS)%>">
	  				<button id="bdescargar" type="button">descargar</button>
  				</c:if>
  			</div>
  		</div>
  	</div>
  	
  </body>
</html>
