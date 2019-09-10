$(document).ready(ready);

function ready(){	
	initZoomVisor("#huellaIzq","#huellaDer");
	
	$.each($('.huellaSelector'), function() {
		$(this).click(function(){
			cambiaHuella($(this).attr("id"));
		});
	});
	$("#circuloRojo").hide();
	for(var i = 0; i < corx.length; i++){
		var auxid = "\"huella"+(i == 9 ? "":"0")+(i+1)+"\"";
		$("#manosSelector").append("<div class='hitArea' style='top:"+cory[i]+"px; left:"+corx[i]+"px;' onClick='cambiaHuella("+auxid+")'></div>");
	}
	$("#huellaHolder1 .boton_mas").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,true,1);
	});
	$("#huellaHolder1 .boton_menos").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,false,1);
	});
	$("#huellaHolder2 .boton_mas").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,true,2);
	});
	$("#huellaHolder2 .boton_menos").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,false,2);
	});
	$(".boton_fullscreen").click(goFullScreen);
	cambiaHuella("huella02");
}

function goFullScreen(){
	$(".boton_fullscreen").off("click");
	$(".boton_fullscreen").click(exitFullScreen);
	zoomFullscreen();
}

function exitFullScreen(){
	$(".boton_fullscreen").off("click");
	$(".boton_fullscreen").click(goFullScreen);
	zoomExitFullscreen();
}

var corx = [102,133,151,170,182,82,52,33,15,3];
var cory = [65,28,20,34,48,65,28,20,34,48];
function cambiaHuella(id){
	var idaux = id.replace("huella","H");
	//zoomCambiaImagenes("imgs/muestra/"+rfcc+"_H"+indice+".png","imgs/muestra/"+rfcd+"_H"+indice+".png");
	zoomCambiaImagenes("imagen/"+rfc1+"?tipo=huella&version="+version1+"&duplicidad=1&imagen="+idaux,"imagen/"+rfc2+"?tipo=huella&version="+version2+"&duplicidad=2&imagen="+idaux);
	$.each($('.huellaSelector'), function() {
		$(this).css("border-color","#007833");
	});
	$("#"+id).css("border-color","#C10C2D");
	var indice = id.replace("huella","");
	$("#circuloRojo").show();
	$("#circuloRojo").css("top",cory[Number(indice)-1]+"px");
	$("#circuloRojo").css("left",corx[Number(indice)-1]+"px");
}


