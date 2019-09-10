$(document).ready(ready);

function ready(){
	visorImagenesInit(cambia);
	$(".downloadLink").on("click",checa);
	$("#bdescargar").on("click",descargar);
	$("#bcertificar").on("click",certificar);
	$("#bdescargarCertificado").on("click",descargarCertificado);
	$("#bdescargar").prop("disabled",true);
	$("#bcertificar").prop("disabled",true);
	$("#dialogoHolder").css("visibility","hidden");
	$("#bCerrarDialogo").on("click",cerrarDialogo);
	$(document).tooltip();
	$(".imagenVisorNo").parent().children(".downloadLink").remove();
	$(".imagenVisorNo").parent().children(".documentoNombre").css("top","0px");
}

function cambia(id){}	

var descargas = new Array();
function checa(e){
	var id = $(e.currentTarget).attr("id");
	if(jQuery.inArray(id,descargas) == -1){
		descargas.push(id);
		$(e.currentTarget).css("background-image","url(imgs/check2.png)");
	}else{
		descargas.splice(jQuery.inArray(id,descargas),1);
		$(e.currentTarget).css("background-image","url(imgs/check.png)");
	}
	$("#bdescargar").prop("disabled",descargas.length == 0);
	$("#bcertificar").prop("disabled",descargas.length == 0);
}

function descargar(){
	$("#bdescargar").prop("disabled",true);
	$.post("servicios/descargar_adjuntos", {"adjuntos":descargas.join(","),"tipo":"documentos"}, mostrarDescarga);
}

function mostrarDescarga(json, textStatus){
	$("#bdescargar").prop("disabled",false);
	window.location.href = "imagen/"+rfc+"?tipo=descarga&version="+version+"&control="+Math.random();
}

function certificar(){
	$("#dialogoHolder").css("visibility","visible");
}

function cerrarDialogo(){
	$("#dialogoHolder").css("visibility","hidden");
}

function descargarCertificado(){
	if($("#nombrealsc").val().length != 0 && $("#fraccionalsc").val().length != 0 && $("#fadmin").val().length != 0 && $("#fciudad").val().length != 0){
		$("#bdescargarCertificado").prop("disabled",true);
		$("#bCerrarDialogo").prop("disabled",true);
		$.post("servicios/certificar_documentos", {"documentos":descargas.join(","),"nombrealsc":$("#nombrealsc").val(),"fraccionalsc":$("#fraccionalsc").val(),"admin":$("#fadmin").val(), "ciudad":$("#fciudad").val(),"tipoLeyenda":$("#tipoLeyenda").val()}, mostrarResultados);
	}else{
		//alert("llena los campos");
	}
}

function mostrarResultados(json, textStatus){
	$("#bdescargarCertificado").prop("disabled",false);
	$("#bCerrarDialogo").prop("disabled",false);
	cerrarDialogo();
	window.location.href = "imagen/"+rfc+"?tipo=certificacion&version="+version+"&control="+Math.random();
}