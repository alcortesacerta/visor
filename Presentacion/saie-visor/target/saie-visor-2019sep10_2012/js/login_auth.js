$(document).ready(ready);
		
function ready(){
	if(login == true){
		var validar = confirm("Existe otra sesión abierta para este usuario. ¿Cerrar sesión externa e iniciar en este equipo?");
		if(validar == true){
			window.location.href = "loginSesion";
		}else{
			window.location.href = "login_error.jsp?error=1";
		}
	}else if(auth == true){
		window.location.href = "menu.jsp";
	}else{
		window.location.href = "login_error.jsp";
	}
}