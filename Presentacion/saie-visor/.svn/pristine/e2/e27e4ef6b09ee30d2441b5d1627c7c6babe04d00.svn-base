$(document).ready(ready);

var jsonResultados;
var jsonResultadosActual;
var jsonResultadosPag;
var tablaCampos = ["rfc","listaDocumentos","fechaEnrolamiento","unidadEnrolamiento","estatus"];

function ready(){
	$('#paginacion').paginacion({tamano: 20, refrescar: llenaTabla});
	$('#tablaResultadosHead').dragtable({dragHandle:'.tablaDrag',persistState: function(table) {
		orden = new Array();
	    table.el.find('th').each(function(i) {
	    	orden.push(this.id.substring(2));
	    });
	    var n = 0;
	    $("#tablaResultados").find('th').each(function(item){
	    	console.log(this.id + " " + $(this).attr("class"));
	    	$(this).attr("id","td"+orden[n]);
	    	$(this).attr("class","th"+orden[n]);
	    	n++;
	    });
	    llenaTabla(jsonResultadosPag);
	    $.ajax({url: 'servicios/guarda_tabla_validar', data: {"orden":orden.toString()}}); 
	  } 
	});
	for(var i = 0; i < orden.length;i++){
		$("#td"+i).click(ordena);
	}
	$("#tableHolder").scroll(function(){
		$("#tablaHeadHolder").css("top",$("#tableHolder").scrollTop()+"px");
		if($("ul") && $(".ui-sortable-helper")){
			var ulwidth = $("ul").width();
			var ilwidth = $(".ui-sortable-helper").width();
			var illeft = $(".ui-sortable-helper").css("left").substr(0,$(".ui-sortable-helper").css("left").length-2);
			if(illeft > ulwidth/2){
				$(".ui-sortable-helper").css("left",ulwidth-ilwidth+"px");
			}
		}
	});
	cargando();
	$.post("servicios/lista_validar", mostrarResultados);
	
	$("#frfc").on("input",busca);
	$("#funidad").on("input",busca);
	$("#fdocumentos").on("input",busca);
	
	$.post("servicios/limpiar_sesion", {"tipo":"buscar"});
}

function cargando(){
	$("#tresultados").html("");
	var s = "<tr id='filaMensaje'>";
	s += "<td colspan='7' style='background-color:#DBDBDB'><img src='imgs/cargando3.gif' style='left: 450px; position: relative;'></td>";
	s += "</tr>";
	$("#tresultados").append(s);
}

var timeid;
var buscae = null;
function busca(e){
	if(buscae != null){
		clearTimeout(timeid);
	}
	buscae = e;
	timeid = setTimeout(retrasoBusca, 500);
}

function retrasoBusca(){
	timebol = true;
	buscar(buscae);
	buscae = null;
}

function buscar(e){
	jsonResultadosActual = new Array();
	if(e.currentTarget.id == "frfc"){
		$("#funidad").prop('disabled', true);
		$("#fdocumentos").prop('disabled', true);
		$("#frfc").val($("#frfc").val().toUpperCase());
		for(var i = 0; i < jsonResultados.length; i++){
			if(jsonResultados[i]["rfc"].indexOf($("#frfc").val()) != -1){
				jsonResultadosActual.push(jsonResultados[i]);
			}
		}
	}else if(e.currentTarget.id == "funidad"){
		$("#frfc").prop('disabled', true);
		$("#fdocumentos").prop('disabled', true);
		var unidad = $("#funidad").val().toLowerCase();
		for(var i = 0; i < jsonResultados.length; i++){
			if(jsonResultados[i]["unidadEnrolamiento"].toLowerCase().indexOf(unidad)!= -1){
				jsonResultadosActual.push(jsonResultados[i]);
			}
		}
	}else{
		$("#frfc").prop('disabled', true);
		$("#funidad").prop('disabled', true);
		var documentos = $("#fdocumentos").val().toLowerCase();
		for(var i = 0; i < jsonResultados.length; i++){
			if(jsonResultados[i]["listaDocumentos"].toLowerCase().indexOf(documentos) != -1){
				jsonResultadosActual.push(jsonResultados[i]);
			}
		}
	}
	$('#paginacion').paginacion("actualizaDatos",jsonResultadosActual);
	if($("#funidad").val() == "" && $("#frfc").val() == ""  && $("#fdocumentos").val() == ""){
		$("#frfc").prop('disabled', false);
		$("#funidad").prop('disabled', false);
		$("#fdocumentos").prop('disabled', false);
	}
}

function mostrarResultados(json, textStatus){
	$("#busBoton").prop("disabled",false);
	if(json.mensaje){
		llenaTablaError("Error de conexión, por favor vuelve a intentarlo");
	}else{
		jsonResultados = new Array();
		for(id in json){
			jsonResultados.push(json[id]);
		}
		if(jsonResultados.length == 0){
			llenaTablaError("No existen registros pendientes para su localidad");
		}else{
			$('#paginacion').paginacion("actualizaDatos",jsonResultados);
			indOrden = 0;
			bOrden = -1;
		}
	}
}

function llenaTabla(jsonResultadosTemp){
	jsonResultadosPag = jsonResultadosTemp;
	$("#tresultados").html("");
	$("#flechaOrden").remove();
	for(var i = 0; i < jsonResultadosTemp.length; i++){
		var s = "<tr id='fila"+i+"'>";
		for(var j = 0; j < orden.length;j++){
			if(tablaCampos[orden[j]] == "duplicidad"){
				s += "<td>"+(jsonResultadosTemp[i]["duplicidad"] == "true" ? "si" : "no")+"</td>";
			}else{
				if(tablaCampos[orden[j]] == "estatus"){
					s += "<td>por "+(jsonResultadosTemp[i][tablaCampos[orden[j]]] == 3 ? "validar" : "autorizar")+"</td>";
				}else if(jsonResultadosTemp[i][tablaCampos[orden[j]]] != undefined && jsonResultadosTemp[i][tablaCampos[orden[j]]] != ""){
					s += "<td>"+jsonResultadosTemp[i][tablaCampos[orden[j]]]+"</td>";
				}else{
					s += "<td>--</td>";
				}
			}
		}
		s += "</tr>";
		$("#tresultados").append(s);
		$("#fila"+i).one("click",{"rfclink":jsonResultadosTemp[i]["rfc"],"version":jsonResultadosTemp[i]["version"]}, function(e){
			var reemplaza = e.data["rfclink"].replace(/Ñ/g, "_");
			var rfcuri =encodeURIComponent(reemplaza);
			window.location.href = "visor_documentos.jsp?rfc="+rfcuri+"&version=0";
		});
	}
	jsonResultadosActual = jsonResultadosPag;
}

function llenaTablaError(msj){
	$("#tresultados").html("");
	var s = "<tr id='filaError'>";
	s += "<td colspan='7'>"+msj+"</td>";
	s += "</tr>";
	$("#tresultados").append(s);
}

var indOrden = 0;
var bOrden = -1;
function ordena(){
	indOrden = $(this).attr("id").substr(2);
	$("#flechaOrden").remove();
	if(jsonResultadosActual){
		if(bOrden == -1 || bOrden != indOrden){
			bOrden = indOrden;
			jsonResultadosPag.sort(comparaObjeto);
			llenaTabla(jsonResultadosPag);
			$(".cellHolder", this).append("<div id='flechaOrden' class='flecha'><img src='imgs/flecha_asc0.png'></div>");
		}else{
			bOrden = -1;
			jsonResultadosPag.reverse();
			llenaTabla(jsonResultadosPag);
			$(".cellHolder", this).append("<div id='flechaOrden' class='flecha'><img src='imgs/flecha_des0.png'></div>");
		}
	}
}

function comparaObjeto(obja, objb){
	var nameA=obja[tablaCampos[indOrden]].toLowerCase(), nameB=objb[tablaCampos[indOrden]].toLowerCase();
	if (nameA < nameB) //sort string ascending
		return -1;
	if (nameA > nameB)
		return 1;
	return 0;
}
