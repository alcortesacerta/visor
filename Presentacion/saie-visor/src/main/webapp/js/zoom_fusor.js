var w = 0;
var h = 0;
var w1 = 0;	//tamaño visor
var h1 = 0;	//tamaño visor
var img1w = 0;//ancho imagen1
var img1h = 0;//alto imagen1
var img2w = 0;//ancho imagen2
var img2h = 0;//alto imagen2
var iw1 = 0;
var ih1 = 0;
var iw2 = 0;
var ih2 = 0;
var esc1 = 1;//escala actual de la imagen1
var esc2 = 1;//escala actual de la imagen2
var dxy = 0;
var dy = 0;

var maxz = 20;
var zoom = 1.1;

var ID1,ID2;
var pID1,pID2; //los id's de los padres originales (se ocupan cuando se sale del fullscreen)
var wo,ho; //with y height originales (se ocupan cuando se sale del fullscreen)

function initZoomVisor(holderID1, holderID2){
	ID1 = holderID1;
	ID2 = holderID2;
	inicializaZoom();
	pID1 = $(holderID1).parent().attr("id");
	pID2 = $(holderID2).parent().attr("id");
	wo = $(holderID1).width();
	ho = $(holderID1).height();
	
	$("<style type='text/css'> .zoomHolder{ position: absolute;} </style>").appendTo("head");
	$("<style type='text/css'> .zoomImgAux{ width: "+w1+"px; height:"+h1+"px; background-color: white; text-align: center;} </style>").appendTo("head");
	$("<style type='text/css'> #zoomScreen{ position: absolute; height: 100%; width: 100%; background-color: rgba(0,0,0,0.8); z-index: 100; overflow: hidden;} </style>").appendTo("head");
	
	$("<div id='zoomAux1' class='zoomAux'></div>").appendTo(holderID1);
	$("<div id='zoomHolder1' class='zoomHolder'><div id='zoomImgAux1' class='zoomImgAux'><img id='zoomImg1' src=''></div></div>").appendTo(holderID1);
	
	$("<div id='zoomAux2' class='zoomAux'></div>").appendTo(holderID2);
	$("<div id='zoomHolder2' class='zoomHolder'><div id='zoomImgAux2' class='zoomImgAux'><img id='zoomImg2' src=''></div></div>").appendTo(holderID2);
	
	$("#zoomHolder1").on("wheel",zoomWheel);
	$("#zoomHolder2").on("wheel",zoomWheel);
	$("#zoomHolder1").draggable({drag:zoomArrastrando});
	$("#zoomHolder2").draggable({drag:zoomArrastrando});
	$("#zoomHolder1").css("cursor","crosshair");
	$("#zoomHolder2").css("cursor","crosshair");
}

function inicializaZoom(){
	var zoomaux = $(ID1).width()/w1;
	img1w *= zoomaux;
	img1h *= zoomaux;
	img2w *= zoomaux;
	img2h *= zoomaux;
	w1 = iw = w = $(ID1).width();
	h = h1 = ih = $(ID2).height();
	$("<style type='text/css'> .zoomAux{ position: absolute; width:"+w1+"px; height:"+h1+"px;} </style>").appendTo("head");
}
	
function zoomFullscreen(){
	var bounds1 = document.getElementById("zoomAux1").getBoundingClientRect();
	var bounds2 = document.getElementById("zoomAux2").getBoundingClientRect();
	
	$("body").prepend("<div id='zoomScreen'></div>");
	$("body").css("overflow","hidden");
	var screenWidth = $("#zoomScreen").width();
	var screenHeight = $("#zoomScreen").height();
	
	var zoomi = $(ID1).detach();
	$("#zoomScreen").append(zoomi);
	var zoomd = $(ID2).detach();
	$("#zoomScreen").append(zoomd);
	
	//cambio de dimensiones
	var tamanow = 0;
	var tamanoh = 0;
	var ratiow = (screenWidth/2-10)/w1;
	var ratioh = (screenHeight-10)/h1;
	if(ratiow < ratioh){
		tamanow = w1*ratiow;
		tamanoh = h1*ratiow;
	}else{
		tamanow = w1*ratioh;
		tamanoh = h1*ratioh;
	}
	$(ID1).css({"position":"absolute","left":bounds1.left+"px","top":bounds1.top+"px"});
	$(ID2).css({"position":"absolute","left":bounds2.left+"px","top":bounds2.top+"px"});
	$(ID1).animate({width:tamanow+"px",height:tamanoh+"px","left":(screenWidth/2-tamanow)/2+"px","top":($("#zoomScreen").height()-tamanoh)/2+"px"},750);
	$(ID2).animate({width:tamanow+"px",height:tamanoh+"px","left":(screenWidth/2)+(screenWidth/2-tamanow)/2+"px","top":($("#zoomScreen").height()-tamanoh)/2+"px"},750);
	
	var zoomw = tamanow/w1;
	var zoomh = tamanoh/h1;
	$("#zoomImg1").animate({width:$("#zoomImg1").width()*zoomw,height:$("#zoomImg1").height()*zoomh},750);
	$("#zoomImgAux1").animate({width:$("#zoomImgAux1").width()*zoomw,height:$("#zoomImgAux1").height()*zoomh},750);
	$("#zoomImg2").animate({width:$("#zoomImg2").width()*zoomw,height:$("#zoomImg2").height()*zoomh},750,inicializaZoom);
	$("#zoomImgAux2").animate({width:$("#zoomImgAux2").width()*zoomw,height:$("#zoomImgAux2").height()*zoomh},750,inicializaZoom);
	$("#zoomHolder1").animate({"left":($("#zoomHolder1").position().left*zoomw)+"px","top":($("#zoomHolder1").position().top*zoomh)+"px"},750);
	$("#zoomHolder2").animate({"left":($("#zoomHolder2").position().left*zoomw)+"px","top":($("#zoomHolder2").position().top*zoomh)+"px"},750);
	
	$(window).resize(zoomResize);
}

function zoomResize(){
	var screenWidth = $("#zoomScreen").width();
	var screenHeight = $("#zoomScreen").height();
	
	//cambio de dimensiones
	var tamanow = 0;
	var tamanoh = 0;
	var ratiow = (screenWidth/2-10)/w1;
	var ratioh = (screenHeight-10)/h1;
	if(ratiow < ratioh){
		tamanow = w1*ratiow;
		tamanoh = h1*ratiow;
	}else{
		tamanow = w1*ratioh;
		tamanoh = h1*ratioh;
	}
	$(ID1).css({"width":tamanow+"px","height":tamanoh+"px"});
	$(ID2).css({"width":tamanow+"px","height":tamanoh+"px"});
	$(ID1).css({"position":"absolute","left":(screenWidth/2-$(ID1).width())/2+"px","top":($("#zoomScreen").height()-$(ID1).height())/2+"px"});
	$(ID2).css({"position":"absolute","left":(screenWidth/2)+(screenWidth/2-$(ID1).width())/2+"px","top":($("#zoomScreen").height()-$(ID1).height())/2+"px"});
	
	var zoomw = tamanow/w1;
	var zoomh = tamanoh/h1;
	$(".zoomHolder").css("left",($(".zoomHolder").position().left*zoomw)+"px");
	$(".zoomHolder").css("top",($(".zoomHolder").position().top*zoomh)+"px");
	//---cambio de dimensiones
	inicializaZoom();//esta se debe llamar cuando termine la animación	
}

function zoomExitFullscreen(){
	var bounds1 = document.getElementById(pID1).getBoundingClientRect();
	var bounds2 = document.getElementById(pID2).getBoundingClientRect();
	$(window).off("resize");
	$(ID1).animate({"width":wo+"px","height":ho+"px","left":bounds1.left+"px","top":bounds1.top+56+"px"},750);
	$(ID2).animate({"width":wo+"px","height":ho+"px","left":bounds2.left+"px","top":bounds2.top+56+"px"},750);
	var zoom = wo/w1;
	
	$("#zoomImg1").animate({width:$("#zoomImg1").width()*zoom,height:$("#zoomImg1").height()*zoom},750);
	$("#zoomImgAux1").animate({width:$("#zoomImgAux1").width()*zoom,height:$("#zoomImgAux1").height()*zoom},750);
	$("#zoomImg2").animate({width:$("#zoomImg2").width()*zoom,height:$("#zoomImg2").height()*zoom},750,terminaExitFullscreen);
	$("#zoomImgAux2").animate({width:$("#zoomImgAux2").width()*zoom,height:$("#zoomImgAux2").height()*zoom},750,terminaExitFullscreen);
	$("#zoomHolder1").animate({"left":($("#zoomHolder1").position().left*zoom)+"px","top":($("#zoomHolder1").position().top*zoom)+"px"},750);
	$("#zoomHolder2").animate({"left":($("#zoomHolder2").position().left*zoom)+"px","top":($("#zoomHolder2").position().top*zoom)+"px"},750);
}

function terminaExitFullscreen(){
	$("body").css("overflow","auto");
	
	var zoomi = $(ID1).detach();
	$("#"+pID1).append(zoomi);	
	var zoomd = $(ID2).detach();
	$("#"+pID2).append(zoomd);
	
	$(ID1).css({"position":"relative","left":"0px","top":"0px"});
	$(ID2).css({"position":"relative","left":"0px","top":"0px"});
	
	$("#zoomScreen").remove();
	
	inicializaZoom();
}

function zoomWheel(e){
	e.preventDefault();
	var bounds;
	var idaux = 1;
	if(e.currentTarget.id == "zoomHolder1"){
		bounds = document.getElementById("zoomAux1").getBoundingClientRect();
		idaux = 1;
	}else{	
		bounds = document.getElementById("zoomAux2").getBoundingClientRect();
		idaux = 2;
	}
	var px = e.originalEvent.pageX - bounds.left - $('body').scrollLeft();
	var py = e.originalEvent.pageY - bounds.top - $('body').scrollTop();
	zoomZoom(px, py, e.originalEvent.deltaY < 0, idaux);
}

function zoomZoom(px, py, escalaMas, idaux){
	var pxe = -$("#zoomHolder"+idaux).position().left + px;
	var pye = -$("#zoomHolder"+idaux).position().top + py;
	
	zoomEscalar(escalaMas, idaux);
	
	var esc = idaux == 1 ? esc1 : esc2;
	pxe /= esc;
	pye /= esc;
	var iw = 0;
	var ih = 0;
	if(idaux == 1){
		iw1 = $("#zoomImgAux1").width();
		ih1 = $("#zoomImgAux1").height();
		iw = iw1;
		ih = ih1;
		esc1 = iw/w1;
	}else{
		iw2 = $("#zoomImgAux2").width();
		ih2 = $("#zoomImgAux2").height();
		iw = iw2;
		ih = ih2;
		esc2 = iw/w1;
	}
	esc = iw/w1;
	var pxe2 = pxe*esc;
	var pye2 = pye*esc;
	var cleft = px - pxe2;
	var ctop = py - pye2;
	
	dxy = iw-w1;
	dy = ih-h1;
	
	if(cleft > 0){
		cleft = 0;
	}else if(cleft < -dxy){
		cleft = -dxy;
	}
	if(ctop > 0){
		ctop = 0;
	}else if(ctop < -dy){
		ctop = -dy;
	}
	
	if(esc != 1){
		$("#zoomHolder"+idaux).css("cursor","move");
	}else{
		$("#zoomHolder"+idaux).css("cursor","crosshair");
	}
	$("#zoomHolder"+idaux).css("left",cleft+"px");
	$("#zoomHolder"+idaux).css("top",ctop+"px");
}

function zoomEscalar(escalaMas, idaux){
	if(escalaMas){
		$("#zoomImg"+idaux).width($("#zoomImg"+idaux).width()*zoom);
		$("#zoomImg"+idaux).height($("#zoomImg"+idaux).height()*zoom);
		$("#zoomImgAux"+idaux).width($("#zoomImgAux"+idaux).width()*zoom);
		$("#zoomImgAux"+idaux).height($("#zoomImgAux"+idaux).height()*zoom);
		if($("#zoomImgAux"+idaux).width() > w*maxz){
			$("#zoomImg"+idaux).width(w*maxz);
			$("#zoomImg"+idaux).height(h*maxz);
			$("#zoomImgAux"+idaux).width(w*maxz);
			$("#zoomImgAux"+idaux).height(h*maxz);
		}
	}else{
		$("#zoomImg"+idaux).width($("#zoomImg"+idaux).width()/zoom);
		$("#zoomImg"+idaux).height($("#zoomImg"+idaux).height()/zoom);
		$("#zoomImgAux"+idaux).width($("#zoomImgAux"+idaux).width()/zoom);
		$("#zoomImgAux"+idaux).height($("#zoomImgAux"+idaux).height()/zoom);
		if($("#zoomImgAux"+idaux).width() < w1){
			$("#zoomImg"+idaux).width(idaux == 1 ? img1w : img2w);
			$("#zoomImg"+idaux).height(idaux == 1 ? img1h : img2h);
			$("#zoomImgAux"+idaux).width(w1);
			$("#zoomImgAux"+idaux).height(h1);
		}
	}
}

function zoomArrastrando(e,ui){
	var target = e.target;;
	var targetaux;
	if(target.id == "zoomHolder1"){
		targetaux = document.getElementById("zoomHolder2");
	}else{
		targetaux = document.getElementById("zoomHolder1");
	}
	ui.position.left = Math.max(w1-$(target).width(), ui.position.left);
	ui.position.left = Math.min(0, ui.position.left);
	ui.position.top = Math.max(h1-$(target).height(), ui.position.top);
	ui.position.top = Math.min(0, ui.position.top);
}

function zoomCambiaImagenes(src1,src2){
	$("#zoomImg1").load(function(){
		$("#zoomImg1").css("width",w1+"px");
		$("#zoomImg1").css("height","auto");
		if($("#zoomImg1").height() > h1){
			$("#zoomImg1").css("height",h1+"px");
			$("#zoomImg1").css("width","auto");
		}
		img1w = $("#zoomImg1").width();
		img1h = $("#zoomImg1").height();
		zoomResetPosicion();
	});
	$("#zoomImg1").attr("src",src1);
	$("#zoomImg2").load(function(){
		$("#zoomImg2").css("width",w1+"px");
		$("#zoomImg2").css("height","auto");
		if($("#zoomImg2").height() > h1){
			$("#zoomImg2").css("height",h1+"px");
			$("#zoomImg2").css("width","auto");
		}
		img2w = $("#zoomImg2").width();
		img2h = $("#zoomImg2").height();
		zoomResetPosicion();
	});
	$("#zoomImg2").attr("src",src2);	
}

function zoomResetPosicion(){
	$("#zoomImg1").width(img1w);
	$("#zoomImg1").height(img1h);
	$("#zoomImgAux1").width(w1);
	$("#zoomImgAux1").height(h1);
	$("#zoomImg2").width(img2w);
	$("#zoomImg2").height(img2h);
	$("#zoomImgAux2").width(w1);
	$("#zoomImgAux2").height(h1);
	$(".zoomHolder").css("left","0px");
	$(".zoomHolder").css("top","0px");
}