$(document).ready(ready);
		
function ready(){
	$("#firmaFiel").submit(validarFirma);
	$("#bCerrarDialogoFiel").on("click",cerrarDialogoFiel);
	$("#bCerrarDialogoError").on("click",cerrarDialogoError);
	visorImagenesInit(cambiaImagen);
	$("#dialogoHolderFiel").css("visibility","hidden");
	$("#dialogoHolderDescartar").css("visibility","hidden");
	$("#botonValidar").on("click",abrirDialogoFiel);
	$("#botonDescartar").on("click",abrirDialogoDescartar);
}

function abrirDialogoFiel(){
	$("#bvalidar").val("true");
	$("#comentariosHolder").css("display","none");
	resetDialogoFiel();
}

function cerrarDialogoFiel(){
	$("#dialogoHolderFiel").css("visibility","hidden");
}

function abrirDialogoDescartar(){
	$("#bvalidar").val("false");
	$("#comentariosHolder").css("display","inline");
	resetDialogoFiel();
}

function resetDialogoFiel(){
	$("#usuarioFiel").val("");
	$("#claveFiel").val("");
	$("#llaveFiel").val("");
	$("#certificadoFiel").val("");
	$("#fcomentarios").val("");
	$("#errorFiel").html("");
	$("#dialogoHolderFiel").css("visibility","visible");
}

function cerrarDialogoError(){
	$("#dialogoHolderError").css("visibility","hidden");
}

function cambiaImagen(id){}

function validarFirma(e){
	if($("#claveFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce la clave");
	}else if($("#llaveFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce la llave");
	}else if($("#certificadoFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce el archivo del reporte");
	}else if($("#fcomentarios").val() == "" && $("#comentariosHolder").css("display") == "inline"){
		$("#errorFiel").html("Por favor, introduce comentarios");
	}else{
		$("#errorFiel").html("");
		return true;
	}
	return false;
}