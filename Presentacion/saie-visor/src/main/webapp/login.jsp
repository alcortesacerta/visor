<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAIE III - login</title>
	
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="js/login.js?v=${applicationScope.version}"></script>
	<script type="text/javascript">
		var error = "${not empty requestScope.mensaje}";
		var login = ${requestScope.login == 'pendiente' ? 'true' : 'false'};
	</script>
	<link rel="stylesheet" href="css/login.css?v=${applicationScope.version}" type="text/css" media="screen">
	
  </head>
  
  <body>
  	<div id="head-titulo"></div>
  	<div id="logo-sat"><img src="imgs/logo_shcp.png" style="margin-right: 20px;"><img src="imgs/logo_sat1.jpg"></div>
  	<div id="login">
		<div id="pFiel" class="pestana">Fiel</div>
		<div id="hfiel" class="holder">
			<form id="loginFiel" action="login" method="post" enctype="multipart/form-data">
				<div class="label">Clave de la llave privada:</div>
				<div class="campo">
					<input type="password" id="claveFiel" name="claveFiel"></input>
				</div>
				<div class="label">Llave privada (*.key):</div>
				<div class="campo">
					<input type="file" id="llaveFiel" name="llaveFiel" accept=".key"></input>
				</div>
				<div class="label">Certificado (*.cert):</div>
				<div class="campo">
					<input type="file" id="certificadoFiel" name="certificadoFiel" accept=".cer"></input>
				</div>
				<div id="errorFiel" class="errorLabel">${requestScope.mensaje}</div>
				<div class="boton">
					<input type="submit" id="ingresar1" name="ingresar" value="ingresar"/>
				</div>
			</form>
		</div>
	</div>
  </body>
</html>
