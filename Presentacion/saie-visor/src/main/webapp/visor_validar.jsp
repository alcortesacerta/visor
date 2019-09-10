<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.Arrays"%>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - Visor de documentos - validar/autorizar actualizaciones</title>
	
	<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/saie_tabla.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/visor_validar.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/dragtable.css?v=${applicationScope.version}" type="text/css" />
	<link rel="stylesheet" href="css/paginacion.css?v=${applicationScope.version}" type="text/css" />
	
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery-ui.js"></script>
	
	<script type="text/javascript">
		var orden = ["0","1","2","3","4"];
		<%
	    	if(session.getAttribute("tabla_validar") != null && ((String[])session.getAttribute("tabla_validar")).length == 5){
	    		out.println("orden = "+Arrays.toString((String[])session.getAttribute("tabla_validar")));
	    	}
	    %>
	    var rfc = "${not empty param.rfc ? param.rfc : sessionScope.busqueda}";
	</script>
	<script src="js/jquery.dragtable.js?v=${applicationScope.version}"></script>
	<script src="js/saie.paginacion.js?v=${applicationScope.version}"></script>
	<script type="text/javascript" src="js/visor_validar.js?v=${applicationScope.version}"></script>
	
  </head>
  
  <body>
  	<%@ include file="header.jsp" %>
  	
  	<div id="holder">
  		<div id="innerHolder">
  			<div id="searchHolder">
  				<div class="holderTitulo">
  					<img alt="icono_lupa" src="imgs/icono_lupa.png">
  					Búsqueda
  					<a href="menu.jsp"><img id="bCerrar" src="imgs/boton_cerrar.png"></a>
  				</div>
  				<div id="busRFC" class="dato">R.F.C:</div>
  				<div id="campoRFC">
  					<input type="text" id="frfc" maxlength="13"/>
  				</div>
  				<div id="busDocumentos" class="dato">Documentos:</div>
  				<div id="campoDocumentos">
  					<input type="text" id="fdocumentos"/>
  				</div>
  				<div id="busUnidad" class="dato">Unidad:</div>
  				<div id="campoUnidad">
  					<input type="text" id="funidad"/>
  				</div>
			</div>
  			<div id="resultadosHolder">
  				<div class="holderTitulo">
  					<img alt="icono_doc2" src="imgs/icono_doc2.png">
  					Actualizaciones pendientes por validar/autorizar
  					<div class="paginacionHolder" id="paginacion">
		  				<div class="paginacionElemento paginacionInicio"><img src="imgs/fini.png"></div>
		  				<div class="paginacionElemento paginacionAnt"><img src="imgs/fant.png"></div>
		  				<div class="paginacionActual">0 de 0</div>
		  				<div class="paginacionElemento paginacionSig"><img src="imgs/fsif.png"></div>
		  				<div class="paginacionElemento paginacionFin"><img src="imgs/fult.png"></div>
		  			</div>
  				</div>
  				<div id="tableHolder">
  					<div class="tablaHeadHolder" id="tablaHeadHolder">
  						<table class="tablaResultados" id="tablaResultadosHead">
		  					<thead>
		  						<tr>
		  							<%
		  								String[] orden = {"0","1","2","3","4"};
		  								String[] celdas = {"RFC","Tipo de actualización","Fecha de actualización","Unidad de actualización","Estatus"};
		  								if(session.getAttribute("tabla_validar") != null && ((String[])session.getAttribute("tabla_validar")).length == 5){
		  									orden =  (String[])session.getAttribute("tabla_validar");
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
