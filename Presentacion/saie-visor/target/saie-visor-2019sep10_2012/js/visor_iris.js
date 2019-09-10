$(document).ready(ready);

function ready(){
	visorImagenesInit(cambiaIris);
	$("#circuloRojo").hide();
	$("#marcadorHolder").prepend("<div class='hitArea' style='top:60px; left:105px;' onClick='hitIris(0)'></div>");
	$("#marcadorHolder").prepend("<div class='hitArea' style='top:60px; left:142px;' onClick='hitIris(1)'></div>");
	$(".downloadLink").on("click",checa);
	$("#bdescargar").on("click",descargar);
	$("#bdescargar").prop("disabled",true);
	$(".imagenVisorNo").html("");
}

function cambiaIris(id){
	if(id.charAt(0) == "i"){
		var index = id.substring(4);
		hitIris(index);
	}
}

function hitIris(i){
	$("#circuloRojo").show();
	for(var j = 0; j < 2; j++){
		$("#iris"+j).removeClass("iris");
	}
	if(i == 0){
		$("#circuloRojo").css("top","60px");
		$("#circuloRojo").css("left","105px");
	}else{
		$("#circuloRojo").css("top","60px");
		$("#circuloRojo").css("left","142px");
	}
	$("#iris"+i).toggleClass("iris");
}

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
}

function descargar(){
	$("#bdescargar").prop("disabled",true);
	$.post("servicios/descargar_adjuntos", {"adjuntos":descargas.join(","),"tipo":"iris"}, mostrarResultados);
}

function mostrarResultados(json, textStatus){
	$("#bdescargar").prop("disabled",false);
	window.location.href = "imagen/"+rfc+"?tipo=descarga&version="+version+"&control="+Math.random();
}