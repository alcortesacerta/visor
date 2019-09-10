<%@page isErrorPage="true"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - error</title>
	
	<link rel="stylesheet" href="css/error.css?v=${applicationScope.version}" type="text/css" media="screen">
	
  </head>
  
  <body>  	
  	<div id="head-titulo"></div>
  	<div id="logo-sat"><img src="imgs/logo_shcp.png" style="margin-right: 20px;"><img src="imgs/logo_sat1.jpg"></div>
	<div id="errorHolder">
		${param.error == 1 ? 'Solo puede tener una sesión activa' : param.error == 2 ? 'No cuenta con permisos de acceso para el aplicativo' : param.error == 3? 'Usted está intentando acceder al VISOR con una versión inferior a MS Internet Explorer 9. Ésta versión de navegador no soporta varias funcionalidades del sistema. Por favor actualícelo a una versión superior.' : 'La sesión ha expirado'}
	</div>
  </body>
</html>
