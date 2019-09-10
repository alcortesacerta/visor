<%@page import="com.iecisa.sat.saie.vf.integration.service.dto.Permisos"%>
<%@page import="com.iecisa.sat.saie.vf.integration.service.dto.UsuarioDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - menú</title>
	
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="css/menu.css?v=${applicationScope.version}" type="text/css" media="screen">
	<script type="text/javascript" src="js/menu.js?v=${applicationScope.version}"></script>
	
  </head>
  
  <body>  	
  	<div id="head-titulo"></div>
  	<div id="logo-sat"><img src="imgs/logo_shcp.png" style="margin-right: 20px;"><img src="imgs/logo_sat1.jpg"></div>
	<div id="menu">
		<div class="menuLabel"><img alt="menu" src="imgs/menu.png" width="312px" height="75px"></div>
		<c:choose>
			<c:when test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.VISOR)%>">
				<div class="menuLabel"><a href="visor_buscar.jsp"><img alt="menu" src="imgs/menu2.png" width="312px" height="75px"></a></div>
			</c:when>
			<c:when test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.VALIDAR)
  				|| ((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.AUTORIZAR)%>">
				<div class="menuLabel"><a href="visor_validar.jsp"><img alt="menu" src="imgs/menu2.png" width="312px" height="75px"></a></div>
			</c:when>
			<c:otherwise>
				<div class="menuLabel"><img alt="menu" src="imgs/menu2.png" width="312px" height="75px"></div>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="<%=((UsuarioDTO)session.getAttribute(\"usuario\")).getPermisos().contains(Permisos.DUPLICIDAD)%>">
				<div><a href="duplicado_tablas.jsp"><img alt="menu" src="imgs/menu3.png" width="312px" height="94px"></a></div>
			</c:when>
		</c:choose>
	</div>
	<div id="logout">
		<a href="logout"><img src="imgs/boton_sesion.png"></a>
	</div>
  </body>
</html>
