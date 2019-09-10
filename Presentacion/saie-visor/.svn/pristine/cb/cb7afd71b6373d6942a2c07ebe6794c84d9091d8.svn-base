<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>SAIE III - Visor de duplicados - reporte</title>
	
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="css/saie_comun.css?v=${applicationScope.version}" type="text/css" media="screen">
	<link rel="stylesheet" href="css/duplicado_reporte.css?v=${applicationScope.version}" type="text/css" media="screen">
	<script type="text/javascript" src="js/visor_imagenes.js?v=${applicationScope.version}"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			visorImagenesInit(cambiaImagen);
		});

		function cambiaImagen(id){}
	</script>
  </head>
  
  <body>
  	<%@ include file="header.jsp" %>
  	
  	<div id="holder">
  		<div id="innerHolder">
  			<div id="contribuyenteHolder">
  				<div class="holderTitulo">
  					<img alt="icono_persona" src="imgs/icono_persona.png">
  					Datos del caso: ${sessionScope.duplicadoDto.caso_numero_caso}
  					<a href="duplicado_tablas.jsp"><img id="bCerrar" src="imgs/boton_cerrar.png"></a>
  				</div>
  				<div class="candidatoLabel">
  					Candidato
  				</div>
  				<div class="candidatoLabel">
  					Adjudicante
  				</div>
  				
				<div class="holderFoto">
  					<img id="fotoCandidato" class="imagenVisor${sessionScope.versionEnrolamiento1.foto.name == 'noaplica'?'No':''}" alt="foto" src="imagen/${sessionScope.duplicadoDto.rfc}?tipo=foto&version=0&duplicidad=1" width="180px" height="240px" data-imagen="imagen/${sessionScope.duplicadoDto.rfc}?tipo=foto&version=0&duplicidad=1">
  				</div>
			 	
  				<div class="holderDatos">
  					<div class="holderDatoPar">
  						<p><b>RFC:</b></p>
  						<p>${sessionScope.duplicadoDto.rfc}</p>
  					</div>
  					
			    	<div class="holderDatoPar">
				        <p><b>Nombre:</b></p>
						<p>${sessionScope.duplicadoDto.nombre_completo}</p>
					</div>
			    	<div class="holderDatoPar">
				        <p><b>Fecha enrolamiento:</b></p>
				        <p>${sessionScope.duplicadoDto.fecha_enrolamiento}</p>
			        </div>
			    	<div class="holderDatoPar">
			    		<p><b>Localidad enrolamiento:</b></p>
			    		<p>${sessionScope.duplicadoDto.localidad_enrolamiento}</p>
			    	</div>
  				</div>
  				
  				<div class="holderFoto">
  					<img id="fotoAdjudicante"  class="imagenVisor${sessionScope.versionEnrolamiento2.foto.name == 'noaplica'?'No':''}" alt="foto" src="imagen/${sessionScope.duplicadoDto.rfc2}?tipo=foto&version=0&duplicidad=2" width="180px" height="240px" data-imagen="imagen/${sessionScope.duplicadoDto.rfc2}?tipo=foto&version=0&duplicidad=2">
  				</div>
			 	
  				<div class="holderDatos">
  					<div class="holderDatoPar">
  						<p><b>RFC:</b></p>
  						<p>${sessionScope.duplicadoDto.rfc2}</p>
  					</div>
  					
			    	<div class="holderDatoPar">
				        <p><b>Nombre:</b></p>
						<p>${sessionScope.duplicadoDto.nombre_completo2}</p>
					</div>
			    	<div class="holderDatoPar">
				        <p><b>Fecha enrolamiento:</b></p>
				        <p>${sessionScope.duplicadoDto.fecha_enrolamiento2}</p>
			        </div>
			    	<div class="holderDatoPar">
			    		<p><b>Localidad enrolamiento:</b></p>
			    		<p>${sessionScope.duplicadoDto.localidad_enrolamiento2}</p>
			    	</div>
  				</div>
		   		<div id="holderBotonesValidar">
		   			<a href="imagen/${sessionScope.duplicadoDto.reporte_url}?tipo=reporte&imagen=${sessionScope.duplicadoDto.reporte_url}">
		        		<button type="button" id="botonValidar">Descargar reporte</button>
		        	</a>
		        </div>
			    <div style="clear: both;margin-bottom: 20px;"></div>
  			</div>
  		</div>
  	</div>
  	
  </body>
</html>
