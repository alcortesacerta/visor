$(document).ready(ready);

function ready(){	
	initZoomVisor("#holderZoomIzq","#holderZoomDer");
	$("#rostroHolder1 .boton_mas").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,true,1);
	});
	$("#rostroHolder1 .boton_menos").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,false,1);
	});
	$("#rostroHolder2 .boton_mas").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,true,2);
	});
	$("#rostroHolder2 .boton_menos").click(function(){
		zoomZoom($(".huellaGrande").width()/2,$(".huellaGrande").height()/2,false,2);
	});
	$(".boton_fullscreen").click(goFullScreen);
	cargaRostro();
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

function cargaRostro(){
	//imagen/${sessionScope.enrolamientoDto.rfc}?tipo=foto&version=${sessionScope.version}
	zoomCambiaImagenes("imagen/"+rfc1+"?tipo=foto&version="+version1+"&duplicidad=1","imagen/"+rfc2+"?tipo=foto&version="+version2+"&duplicidad=2");
}


