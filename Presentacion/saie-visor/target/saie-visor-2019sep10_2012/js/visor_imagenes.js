var visorCallback;

function visorImagenesInit(callback){
	$("body").prepend("<div id='imageViewer'><div id='imageViewerImageHolder' style='background-color:white'><img id='imageViewerImage' src=''></div></div>");
	$("<style type='text/css'> .imagenVisor{ cursor: pointer;} </style>").appendTo("head");
	$("#imageViewer").hide();
	$("#imageViewer").click(escondeImagen);
	$.each($('.imagenVisor'), function() {
		$(this).click(function(e){
			cargaImagen($(e.target).attr("id"));
		});
	});
	visorCallback = callback;
}

function cargaImagen(id){
	visorCallback(id);
	$("#imageViewerImage").attr("src",$("#"+id).data("imagen"));
	if($("#imageViewerImage").prop("complete") == "true"){
		muestraImagen();
	}else{
		$("#imageViewerImage").load(muestraImagen);
		$("#imageViewerImage").error(errorImagen);
	}
}

function errorImagen(){
	$("#imageViewerImage").off();
}
var h2, h1, w2, w1;
function muestraImagen(){
	$("#imageViewerImage").off();
	var img = new Image();
	img.src = $("#imageViewerImage").attr("src");
	$("#imageViewer").fadeIn(250);
	$('#imageViewerImage').css({"width":"", "height":""});
	if($("#imageViewerImage").height() > $("#imageViewer").height()){
		$("#imageViewerImage").height($("#imageViewer").height());
		h2 = $("#imageViewer").height();
		$("#imageViewerImage").width('auto');
	}
	h2 = $("#imageViewerImage").height();
	h1 = h2/2;
	w2 = $("#imageViewerImage").width();
	w1 = w2/2;
	$("#imageViewerImage").width(w1);
	$("#imageViewerImage").height(h1);
	$("#imageViewerImage").animate({
		height: h2,
	    width: w2
	},250);
	$("#imageViewerImageHolder").css("left",($("#imageViewer").width() - w1)/2);
	$("#imageViewerImageHolder").css("top",($("#imageViewer").height() - h1)/2);
	$("#imageViewerImageHolder").animate({
	    top: ($("#imageViewer").height() - h2)/2,
	    left: ($("#imageViewer").width() - w2)/2
	  },250);
}

function escondeImagen(){
	var img = new Image();
	img.src = $("#imageViewerImage").attr("src");
	$("#imageViewer").fadeOut(250);
	$("#imageViewerImage").animate({
		height: h1,
	    width: w1
	},250);
	$("#imageViewerImageHolder").animate({
	    top: ($("#imageViewer").height() - h1)/2,
	    left: ($("#imageViewer").width() - w1)/2
	  },250);
}