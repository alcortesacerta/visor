$(document).ready(ready);

var corx = [3,18,41,65,105,125,167,190,213,228];
var cory = [60,42,28,36,82,82,36,28,42,60];
function ready(){
	visorImagenesInit(cambiaDedo);
	$("#circuloRojo").hide();
	for(var i = 0; i < corx.length; i++){
		$("#manosHolder").prepend("<div class='hitArea' style='top:"+cory[i]+"px; left:"+corx[i]+"px;' onClick='hitDedo("+i+")'></div>");
	}
	$(".downloadLink").on("click",checa);
	$("#bdescargar").on("click",descargar);
	$("#bdescargar").prop("disabled",true);
	$(".imagenVisorNo").html("");
}

function cambiaDedo(id){
	if(id.charAt(0) == "h"){
		var index = id.substring(6);
		hitDedo(index);
	}
}

function hitDedo(i){
	$("#circuloRojo").show();
	for(var j = 0; j < 10; j++){
		$("#huella"+j).removeClass("huella");
	}
	$("#huella"+i).toggleClass("huella");
	$("#circuloRojo").css("top",cory[i]+"px");
	$("#circuloRojo").css("left",corx[i]+"px");
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
	$.post("servicios/descargar_adjuntos", {"adjuntos":descargas.join(","),"tipo":"huellas"}, mostrarResultados);
}

function mostrarResultados(json, textStatus){
	$("#bdescargar").prop("disabled",false);
	window.location.href = "imagen/"+rfc+"?tipo=descarga&version="+version+"&control="+Math.random();
}