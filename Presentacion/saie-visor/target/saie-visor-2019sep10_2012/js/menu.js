$(document).ready(ready);
		
function ready(){
	$("#menu").hide();
	$("#menu").fadeIn(250);
	$.post("servicios/limpiar_sesion");
}

function redirecciona(data, textStatus){
	window.location.href = "login.jsp";
}