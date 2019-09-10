$(document).ready(ready);
		
function ready(){
	$("#loginFiel").submit(ingresarFiel);
	/*if(error == "true"){
		loginb = false;
		$("#hldap").hide();
		$("#pLdap").css("color","black");
		$("#pLdap").css("background-color","transparent");
		cambia();
	}else{
		loginb = true;
		$("#hfiel").hide();
		$("#pFiel").css("color","black");
		$("#pFiel").css("background-color","transparent");
		$("#ingresar1").css("disabled","disabled");
		cambia();
	}*/
	if(login == true){
		var validar = confirm("Existe otra sesión abierta para este usuario. ¿Cerrar sesión externa e iniciar en este equipo?");
		if(validar == true){
			window.location.href = "loginSesion";
		}else{
			window.location.href = "login_error.jsp?error=1";
		}
	}else if(auth == true){
		window.location.href = "menu.jsp";
	}
}

function ingresar(e){
	if($("#usuarioLdap").val() == ""){
		$("#errorLdap").html("Por favor, introduce tu RFC corto");
	}else{
		$("#errorLdap").html("");
		return true;
	}
	return false;
}

function ingresarFiel(e){
	$("#usuarioFiel").val($("#usuarioFiel").val().toUpperCase());
	if($("#claveFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce la clave");
	}else if($("#llaveFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce la llave");
	}else if($("#certificadoFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce el archivo del reporte");
	}else{
		$("#errorFiel").html("");
		return true;
	}
	return false;
}

function autorizar(json, textStatus){
	if(json.mensaje == "ok"){
		window.location.href = "menu.jsp";
	}else{
		$("#errorLabel").html("usuario incorrecto");
	}
}

var loginb = true;
function cambia(){
	$("#pLdap").off("click");
	$("#pFiel").off("click");
	if(loginb){
		$("#login").animate({"height": "auto"}, 500, activaBoton);
		$("#hfiel").fadeOut(250,function(){$("#hldap").fadeIn(250);});
		$("#pLdap").css("color","black");
		$("#pLdap").css("background-color","transparent");
		$("#pFiel").css("color","white");
		$("#pFiel").css("background-color","#007833");
	}else{
		$("#login").animate({"height": "auto"}, 500, activaBoton);
		$("#hldap").fadeOut(250,function(){$("#hfiel").fadeIn(250);});
		$("#pFiel").css("color","black");
		$("#pFiel").css("background-color","transparent");
		$("#pLdap").css("color","white");
		$("#pLdap").css("background-color","#007833");
	}
	loginb = !loginb;
}

function activaBoton(){
	if(loginb){
		$("#pLdap").on("click",cambia);
	}else{
		$("#pFiel").on("click",cambia);
	}
}