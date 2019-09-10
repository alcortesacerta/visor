<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - login</title>
	
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	<script type="text/javascript">
		var login = ${requestScope.login == 'pendiente' ? 'true' : 'false'};
		var auth = ${not empty sessionScope.usuario};
	</script>
	
	<link rel="stylesheet" href="css/error.css?v=${applicationScope.version}" type="text/css" media="screen">
	<script type="text/javascript" src="js/login_auth.js?v=${applicationScope.version}"></script>
	
  </head>
  
  <body>  	
  	<div id="head-titulo"></div>
  	<div id="logo-sat"><img src="imgs/logo_shcp.png" style="margin-right: 20px;"><img src="imgs/logo_sat1.jpg"></div>
	<div id="errorHolder">
		Accediendo a SAIE...
	</div>
  </body>
</html>
