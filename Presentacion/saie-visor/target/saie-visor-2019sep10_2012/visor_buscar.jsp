<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:if test="<%=!((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.VISOR)%>">
    <c:redirect url="/menu.jsp"></c:redirect>
</c:if>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - Visor de documentos - búsqueda</title>
	
	<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/saie_tabla.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/visor_buscar.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/dragtable.css?v=${applicationScope.version}" type="text/css" />
	
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery-ui.js"></script>
	
	<script type="text/javascript">
		var orden = ["0","1","2","3","4","5","6"];
		<%
	    	if(session.getAttribute("tabla_rfc") != null){
	    		out.println("orden = "+Arrays.toString((String[])session.getAttribute("tabla_rfc")));
	    	}
	    %>
	    var rfc = "${not empty param.rfc ? param.rfc : ''}";
	    if(rfc == ''){
	    	rfc = "${not empty sessionScope.buscarfc ? sessionScope.buscarfc : ''}";
	    }
	    var buscacurp = "${not empty sessionScope.buscacurp ? sessionScope.buscacurp : ''}";
	    var buscanombre = "${not empty sessionScope.buscanombre ? sessionScope.buscanombre : ''}";
	</script>
	<script src="js/jquery.dragtable.js?v=${applicationScope.version}"></script>
	<script type="text/javascript" src="js/visor_buscar.js?v=${applicationScope.version}"></script>
	
  </head>
  
  <body>
  	<%@ include file="header.jsp" %>
  	
  	<c:if test="${param.na == 'true'}">
	  	<div id="dialogoHolderError" class="dialogoHolder">
	  		<div id="dialogo">
	  			<div class="holderTitulo">
	  				<img alt="icono_documento" src="imgs/icono_doc2.png">
					Error
					<img id="bCerrarDialogo" src="imgs/boton_cerrar.png" class="botonCerrar">
				</div>
				<div class="holder">
					<div id="errorLabel">Se está intentando acceder a información restringida y la consulta ha sido registrada en el Sistema de Seguridad para el Monitoreo de Datos.<br>
					Para la consulta de este contribuyente, solicite apoyo de su Administrador Local o Central.</div>
				</div>
	  		</div>
	  	</div>
  	</c:if>
  	
  	<div id="holder">
  		<div id="innerHolder">
  			<div id="searchHolder">
  				<div class="holderTitulo">
  					<img alt="icono_lupa" src="imgs/icono_lupa.png">
  					Visor de documentos - Búsqueda
  					<a href="menu.jsp"><img id="bCerrar" src="imgs/boton_cerrar.png"></a>
  				</div>
  				<form id="formaBuscar" action="">
	  				<div id="busRFC" class="dato">R.F.C:</div>
	  				<div id="campoRFC">
	  					<input type="text" id="rfc" maxlength="13"/>
	  				</div>
	  				<div id="busCURP" class="dato">CURP:</div>
	  				<div id="campoCURP">
	  					<input type="text" id="curp"/>
	  				</div>
	  				<div id="busNombre" class="dato">Nombre:</div>
	  				<div id="campoNombre">
	  					<input type="text" id="nombre"/>
	  				</div>
  					<input id="busBoton" type="submit" value="buscar"/>
  				</form>
  				<div id="error"></div>
  			</div>
  			<div id="resultadosHolder">
  				<div class="holderTitulo">
  					<img alt="icono_persona" src="imgs/icono_persona.png">
  					Contribuyentes
  				</div>
  				<div id="tableHolder">
  					<div class="tablaHeadHolder" id="tablaHeadHolder">
  						<table class="tablaResultados" id="tablaResultadosHead">
		  					<thead>
		  						<tr>
		  							<%
		  								String[] orden = {"0","1","2","3","4","5","6"};
		  								String[] celdas = {"RFC","CURP","Nombre/Razón social/Denominación social","Fecha enrolamiento","Localidad enrolamiento","Unidad enrolamiento","Duplicidad"};
		  								if(session.getAttribute("tabla_rfc") != null){
		  									orden =  (String[])session.getAttribute("tabla_rfc");
		  								}
		  								for(int i = 0; i < orden.length;i++){
		  									out.println("<th id='td"+orden[i]+"' class='th"+orden[i]+"'><div class='cellHolder'><div class='tablaDrag'></div><div class='tablaTitulo'>"+celdas[Integer.parseInt(orden[i])]+"</div></div></th>");
		  								}
		  							%>
		  						</tr>
		  					</thead>
		  				</table>
  					</div>
	  				<table class="tablaResultados" id="tablaResultados">
	  					<thead>
	  						<tr>
	  							<%
	  								for(int i = 0; i < orden.length;i++){
	  									out.println("<th id='td"+orden[i]+"' class='th"+orden[i]+"'></th>");
	  								}
	  							%>
	  						</tr>
	  					</thead>
	  					<tbody id="tresultados">
	  					</tbody>
	  				</table>
	  			</div>
  			</div>
  		</div>
  	</div>
  	
  </body>
</html>
