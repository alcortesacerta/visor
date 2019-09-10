<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.Arrays"%>
<c:if test="<%=!((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DUPLICIDAD)%>">
    <c:redirect url="/menu.jsp"></c:redirect>
</c:if>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - Visor de documentos - búsqueda</title>
	
	<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/saie_tabla.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/duplicado_casos.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/dragtable.css?v=${applicationScope.version}" type="text/css" />
	<link rel="stylesheet" href="css/paginacion.css?v=${applicationScope.version}" type="text/css" />
	
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery-ui.js"></script>	
	
	<script type="text/javascript">
		var orden = ["0","1","2","3","4","5","6","7"];
		var orden2 = ["0","10","1","2","3","4","5","6","7","8","9"];
		<%
	    	if(session.getAttribute("tabla_casos") != null && ((String[])session.getAttribute("tabla_casos")).length == 8){
	    		out.println("orden = "+Arrays.toString((String[])session.getAttribute("tabla_casos")));
	    	}
			if(session.getAttribute("tabla_hitlist") != null && ((String[])session.getAttribute("tabla_hitlist")).length == 11){
	    		out.println("orden2 = "+Arrays.toString((String[])session.getAttribute("tabla_hitlist")));
	    	}
	    %>
	    var biometrista = <%=((UsuarioDTO)session.getAttribute("usuario")).getPermisos().contains(Permisos.REPORTE_BIO)%>;
	</script>
	<script src="js/jquery.dragtable.js?v=${applicationScope.version}"></script>
	<script src="js/saie.paginacion.js?v=${applicationScope.version}"></script>
	<script type="text/javascript" src="js/duplicado_casos.js?v=${applicationScope.version}"></script>
	
  </head>
  
  <body>
  	<%@ include file="header.jsp" %>
  	
  	<c:if test="${not empty requestScope.mensaje}">
	  	<div id="dialogoHolderError" class="dialogoHolder">
	  		<div id="dialogo">
	  			<div class="holderTitulo">
					<img alt="icono_documento" src="imgs/icono_doc2.png">
					<img id="bCerrarDialogoError" src="imgs/boton_cerrar.png" class="botonCerrar">
				</div>
				<div class="holder">
					<div id="errorLabel">${requestScope.mensaje}</div>
				</div>
	  		</div>
	  	</div>
  	</c:if>
  	
  	<div id="dialogoHolderFile" class="dialogoHolder">
  		<div id="dialogo">
  			<div class="holderTitulo">
				<img alt="icono_documento" src="imgs/icono_doc2.png">
				Subir y firmar reporte (con FIEL)
				<img id="bCerrarDialogoFile" src="imgs/boton_cerrar.png" class="botonCerrar">
			</div>
			<div id="hfiel">
				<form id="firmaFiel" action="file_upload.jsp" method="post" enctype="multipart/form-data">
					<div class="holderFileColumna">
						<div class="label">Resolución:</div>
						<div class="campoFile">
							<select id="resolucion" name="resolucion">
								<c:forEach items="${applicationScope.catalogoTipoResolucion}" var="item">
									<option value= "${item.id}"${item.id == 2 ?'selected = "selected"': ''}>${item.valor} </option>						
								</c:forEach>
							</select>
						</div>
						<div class="label">Archivo del reporte:</div>
						<div class="campoFile">
							<input type="file" id="ffilename" name="ffilename"/>
						</div>
						<div class="label">Observaciones:</div>
						<div>
							<select id="observaciones" name="observaciones">
								<c:forEach items="${applicationScope.catalogoTipoObservacion}" var="item">
									<option value="${item.id}">${item.valor}</option>
								</c:forEach>
							</select>
						</div>
						<div class="label" id="casoSatLabel">Número Caso SAT:</div>
						<div class="campoFile" id="casoSatCampo">
							<input type="text" id="casoSat" name="casoSat" value="-1"/>
						</div>
					</div>
					<div class="holderFileColumna">
						<div class="label">Clave de la llave privada:</div>
						<div class="campo">
							<input type="password" id="claveFiel" name="claveFiel"></input>
						</div>
						<div class="label">Llave privada (*.key):</div>
						<div class="campo">
							<input type="file" id="llaveFiel" name="llaveFiel" accept=".key"></input>
						</div>
						<div class="label">Certificado (*.cer):</div>
						<div class="campoFile">
							<input type="file" id="certificadoFiel" name="certificadoFiel" accept=".cer"></input>
						</div>
					</div>
					
					<div style="clear: both;"></div>
					<input type="hidden" id="fcaso" name="fcaso"/>
					<input type="hidden" id="frfc1" name="frfc1"/>
					<input type="hidden" id="frfc2" name="frfc2"/>
					<div class="labelError" id="errorFiel"></div>
					<div id="botonSubirDiv">
						<input type="submit" value="subir y firmar" id="botonSubir"></input>
					</div>
				</form>
			</div>
  		</div>
  	</div>
  	
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
  				<div id="busNombre" class="dato">Nombre:</div>
  				<div id="campoNombre">
  					<input type="text" id="fnombre"/>
  				</div>
  			
	 			<div id="busCatalogo" class="dato">Catálogo:</div>
	 			<select id="catalogo">
					<option value="posibles">Casos de posibles duplicados</option>
					<option value="duplicados">Casos dictaminados</option>
				</select>
			</div>
  			<div id="resultadosHolder">
  				<div class="holderTitulo">
  					<img alt="icono_persona" src="imgs/icono_persona.png">
  					Casos de posibles duplicados
  					<div class="paginacionHolder" id="paginacionCasos">
		  				<div class="paginacionElemento paginacionInicio"><img src="imgs/fini.png"></div>
		  				<div class="paginacionElemento paginacionAnt"><img src="imgs/fant.png"></div>
		  				<div class="paginacionActual">0 de 0</div>
		  				<div class="paginacionElemento paginacionSig"><img src="imgs/fsif.png"></div>
		  				<div class="paginacionElemento paginacionFin"><img src="imgs/fult.png"></div>
		  			</div>
  				</div>
  				<div id="tableHolder">
  					<div class="tablaHeadHolder" id="tablaHeadHolder1">
  						<table class="tablaResultados1" id="tablaResultadosHead1">
		  					<thead>
		  						<tr>
		  							<%
		  								String[] orden = {"0","1","2","3","4","5","6","7"};
		  								String[] celdas = {"Caso","Biométricos","Nombre completo","RFC","Fecha enrolamiento","Localidad enrolamiento","Reporte","Similitud"};
		  								if(session.getAttribute("tabla_casos") != null && ((String[])session.getAttribute("tabla_casos")).length == 8){
		  									orden =  (String[])session.getAttribute("tabla_casos");
		  								}
		  								for(int i = 0; i < orden.length;i++){
		  									out.println("<th id='td"+orden[i]+"' class='th"+orden[i]+"'><div class='cellHolder'><div class='tablaDrag'></div><div class='tablaTitulo'>"+celdas[Integer.parseInt(orden[i])]+"</div></div></th>");
		  								}
		  							%>
		  							
		  						</tr>
		  					</thead>
		  				</table>
  					</div>
	  				<table class="tablaResultados1" id="tablaResultados1">
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
  			
  			<div id="resultadosHolder2">
  				<div class="holderTitulo">
  					<img alt="icono_persona" src="imgs/icono_persona.png">
  					Casos dictaminados
  					<div class="paginacionHolder" id="paginacionDuplicados">
		  				<div class="paginacionElemento paginacionInicio"><img src="imgs/fini.png"></div>
		  				<div class="paginacionElemento paginacionAnt"><img src="imgs/fant.png"></div>
		  				<div class="paginacionActual">0 de 0</div>
		  				<div class="paginacionElemento paginacionSig"><img src="imgs/fsif.png"></div>
		  				<div class="paginacionElemento paginacionFin"><img src="imgs/fult.png"></div>
		  			</div>
  				</div>
  				
  				<div id="tableHolder2">
  					<div class="tablaHeadHolder" id="tablaHeadHolder2">
	  					<table class="tablaResultados2" id="tablaResultadosHead2">
		  					<thead>
		  						<tr>
		  							<%
		  								String[] orden2 = {"0", "10", "1","2","3","4","5","6","7","8", "9"};
		  								String[] celdas2 = {"Caso","Biométricos","Nombre completo","RFC","Fecha enrolamiento","Localidad enrolamiento","Resolucion","Observaciones","Similitud","Reporte","Caso SAT"};
		  								if(session.getAttribute("tabla_hitlist") != null && ((String[])session.getAttribute("tabla_hitlist")).length == 11){
		  									orden2 =  (String[])session.getAttribute("tabla_hitlist");
		  								}
		  								for(int i = 0; i < orden2.length;i++){
		  									out.println("<th id='tdd"+orden2[i]+"' class='th2"+orden2[i]+"'><div class='cellHolder'><div class='tablaDrag'></div><div class='tablaTitulo'>"+celdas2[Integer.parseInt(orden2[i])]+"</div></div></th>");
		  								}
		  							%>
		  						</tr>
		  					</thead>
		  				</table>
	  				</div>
	  				<table class="tablaResultados2" id="tablaResultados2">
	  					<thead>
	  						<tr>
	  							<%
	  								for(int i = 0; i < orden2.length;i++){
	  									out.println("<th id='tdd"+orden2[i]+"' class='th2"+orden2[i]+"'></th>");
	  								}
	  							%>
	  						</tr>
	  					</thead>
	  					<tbody id="tresultados2">
	  					</tbody>
	  				</table>
	  			</div>
  			</div>
  		</div>
  	</div>
  	
  </body>
</html>
