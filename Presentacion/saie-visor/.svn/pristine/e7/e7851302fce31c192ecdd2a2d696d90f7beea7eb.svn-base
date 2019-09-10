$(document).ready(ready);

function ready(){	
	initZoomVisor("#huellaIzq","#huellaDer");
	
	$.each($('.huellaSelector'), function() {
		$(this).click(function(){
			cambiaIris($(this).attr("id"));
		});
	});
	$("#circuloRojo").hide();
	for(var i = 0; i < corx.length; i++){
		var auxid = "\"huella"+(i == 9 ? "":"0")+(i+1)+"\"";
		$("#manosSelector").append("<div class='hitArea' style='top:"+cory[i]+"px; left:"+corx[i]+"px;' onClick='cambiaHuella("+auxid+")'></div>");
	}
	$("#irisHolder1 .boton_mas").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,true,1);
	});
	$("#irisHolder1 .boton_menos").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,false,1);
	});
	$("#irisHolder2 .boton_mas").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,true,2);
	});
	$("#irisHolder2 .boton_menos").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,false,2);
	});
	$(".boton_fullscreen").click(goFullScreen);
	cambiaIris("iris1");
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

var corx = [74,102];
var cory = [50,50];
function cambiaIris(id){
	var indice = id.substring(4);
	//zoomCambiaImagenes("imgs/muestra/"+rfcc+"_IO"+(indice == 1 ? "D" : "Z")+".png","imgs/muestra/"+rfcd+"_IO"+(indice == 1 ? "D" : "Z")+".png");
	//zoomCambiaImagenes("imagen/"+rfc1+"?tipo=foto&version="+version1+"&duplicidad=1","imagen/"+rfc2+"?tipo=foto&version="+version2+"&duplicidad=2");
	var irisaux = id == "iris1" ? "IOD" : "IOZ";
	zoomCambiaImagenes("imagen/"+rfc1+"?tipo=iris&version="+version1+"&duplicidad=1&imagen="+irisaux,"imagen/"+rfc2+"?tipo=iris&version="+version2+"&duplicidad=2&imagen="+irisaux);
	console.log("imagen/"+rfc1+"?tipo=iris&version="+version1+"&duplicidad=1&imagen="+irisaux);
	$.each($('.huellaSelector'), function() {
		$(this).css("border-color","#007833");
	});
	$("#iris"+indice).css("border-color","#C10C2D");
	$("#circuloRojo").show();
	$("#circuloRojo").css("top",cory[Number(indice)-1]+"px");
	$("#circuloRojo").css("left",corx[Number(indice)-1]+"px");
}


