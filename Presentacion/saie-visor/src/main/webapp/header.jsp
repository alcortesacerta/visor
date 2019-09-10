<%@page import="com.iecisa.sat.saie.vf.integration.service.dto.Permisos"%>
<%@page import="com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.saie.beans.Contribuyente"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
	<script type="text/javascript">
		/*$(document).on('contextmenu', function(e){
			return false;
		});*/
	</script>
	<div id="head-sat">
  		<div id="logo-sat"><img alt="logoSat" src="imgs/logo_shcp.png"/>
  		<img src="imgs/logo_sat1.jpg"/></div>
  		<div id="navegacion">
  			<%
  			String opcion = request.getServletPath();
  			String separador = "  <img src='imgs/flecha_ct0.png'>  ";
  			String spano = "<span>";
  			String spanc = "</span>";
  			boolean validar = request.getSession().getAttribute("referer") != null && request.getSession().getAttribute("referer").equals("validar");
  			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuario");
  			
 			out.println(spano);
			out.println("<a href='menu.jsp'>Inicio</a>");
			out.println(separador);
			
  			if(opcion.equals("/visor_buscar.jsp")){
  				out.println(spanc);
  				out.println("Visor de documentos - Búsqueda");
  			}else if(opcion.equals("/visor_documentos.jsp")){
  				if(validar){
  					out.println("<a href='visor_validar.jsp'>Visor de documentos - Actualizaciones pendientes</a>");
  				}else{
  					out.println("<a href='visor_buscar.jsp'>Visor de documentos - Búsqueda</a>");
  				}
  				out.println(separador);
  				out.println(spanc);
  				out.println("Datos del contribuyente");
  			}else if(opcion.equals("/visor_validar.jsp")){
  				out.println(spanc);
  				out.println("Actualizaciones pendientes");
  			}else if(opcion.equals("/visor_huella.jsp")){
  				if(validar){
  					out.println("<a href='visor_validar.jsp'>Visor de documentos - Actualizaciones pendientes</a>");
  				}else{
  					out.println("<a href='visor_buscar.jsp'>Visor de documentos - Búsqueda</a>");
  				}
  				out.println(separador);
  				out.println("<a href='visor_documentos.jsp'>Datos del contribuyente</a>");
  				out.println(separador);
  				out.println(spanc);
  				out.println("Huellas dactilares");
  			}else if(opcion.equals("/visor_iris.jsp")){
  				if(validar){
  					out.println("<a href='visor_validar.jsp'>Visor de documentos - Actualizaciones pendientes</a>");
  				}else{
  					out.println("<a href='visor_buscar.jsp'>Visor de documentos - Búsqueda</a>");
  				}
  				out.println(separador);
  				out.println("<a href='visor_documentos.jsp'>Datos del contribuyente</a>");
  				out.println(separador);
  				out.println(spanc);
  				out.println("Iris");
  			}else if(opcion.equals("/visor_doc.jsp")){
  				if(validar){
  					out.println("<a href='visor_validar.jsp'>Visor de documentos - Actualizaciones pendientes</a>");
  				}else{
  					out.println("<a href='visor_buscar.jsp'>Visor de documentos - Búsqueda</a>");
  				}
  				out.println(separador);
  				out.println("<a href='visor_documentos.jsp'>Datos del contribuyente</a>");
  				out.println(separador);
  				out.println(spanc);
  				out.println("Documentos");
  			}else if(opcion.equals("/duplicado_tablas.jsp")){
  				out.println(spanc);
  				out.println("Visor de duplicados");
  			}else if(opcion.equals("/duplicado_huella.jsp")){
  				out.println("<a href='duplicado_tablas.jsp'>Visor de duplicados</a>");
  				out.println(separador);
  				out.println(spanc);
  				out.println("Fusor - Huella dactilar");
  			}else if(opcion.equals("/duplicado_iris.jsp")){
  				out.println("<a href='duplicado_tablas.jsp'>Visor de duplicados</a>");
  				out.println(separador);
  				out.println(spanc);
  				out.println("Fusor - Iris");
  			}else if(opcion.equals("/duplicado_rostro.jsp")){
  				out.println("<a href='duplicado_tablas.jsp'>Visor de duplicados</a>");
  				out.println(separador);
  				out.println(spanc);
  				out.println("Fusor - Rostro");
  			}else if(opcion.equals("/duplicado_reporte.jsp")){
  				out.println("<a href='duplicado_tablas.jsp'>Visor de duplicados</a>");
  				out.println(separador);
  				out.println(spanc);
  				out.println("Datos generales de caso");
  			}
  			%>
  		</div>
  	</div>
  	<div id="barra-usuario">
  		<div id="usuario">
  			<img src="${pageContext.request.contextPath}/imgs/login_usuario.png">
  			<span id="usuario-nombre">Usuario: ${sessionScope.usuario.nombre}</span>
  			<span id="usuario-fecha">Fecha de consulta: 
  				<%Date date = new Date(); 
  				SimpleDateFormat format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","MX"));
				out.println(format.format(date));
  				%>
  			</span>
  		</div>
  		<div id="notificacion">
  		<c:choose>
		    <c:when test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.VALIDAR) || ((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.AUTORIZAR)%>">
		        <a href="visor_validar.jsp"><span>Actualizaciones</span><img src="${pageContext.request.contextPath}/imgs/icono_campana.png"></a>
		    </c:when>
		</c:choose>
  		</div>
  	</div>