$(document).ready(ready);

function ready(){
	$("#curp").on("input",cambiaCampo);
	$("#nombre").on("input",cambiaCampo);
	$("#rfc").on("input",cambiaCampo);
	
	$("#formaBuscar").submit(buscar);
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
	    llenaTabla();
	    $.ajax({url: 'servicios/guarda_tabla_rfc', data: {"orden":orden.toString()}}); 
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
	if(rfc != "null" && rfc != ""){
		rfc = rfc.toUpperCase();
		$("#rfc").val(rfc);
		$("#rfc").trigger("input");
		$("#busBoton").trigger("click");
	}else if(buscacurp != ""){
		$("#curp").val(buscacurp);
		$("#curp").trigger("input");
		$("#busBoton").trigger("click");
	}else if(buscanombre != ""){
		$("#nombre").val(buscanombre);
		$("#nombre").trigger("input");
		$("#busBoton").trigger("click");
	}
	
	if($("#bCerrarDialogo")){
		$("#bCerrarDialogo").click(cerrarError);
	}
	
	$.post("servicios/limpiar_sesion", {"tipo":"buscar"});
}

function cambiaCampo(e){
	if(e.currentTarget.id == "curp"){
		$("#nombre").val("");
		$("#rfc").val("");
	}else if(e.currentTarget.id == "nombre"){
		$("#curp").val("");
		$("#rfc").val("");
	}else if(e.currentTarget.id == "rfc"){
		$("#curp").val("");
		$("#nombre").val("");
	}
}

function buscar(e){
	e.preventDefault();
	if($("#rfc").val() != ""){
		$("#rfc").val($("#rfc").val().toUpperCase());
		$("#rfc").val($("#rfc").val().trim());
		//var rfcregex = /^([A-Z,Ñ,&]{3,4})[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?$/;
		var rfcregex = /^([A-Z,Ñ,&]{3,4})[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?$/;
		var dif = isNaN($("#rfc").val().substr(3,1)) ? 0 : 1;
		var dia = parseInt($("#rfc").val().substr(8-dif,2));
		var mes = parseInt($("#rfc").val().substr(6-dif,2));
		var ano = parseInt($("#rfc").val().substr(4-dif,2));
		if($("#rfc").val().length < 10){
			$("#error").html("Por favor, introduce al menos 10 caracteres");
		}else if(!rfcregex.test($("#rfc").val())){
			$("#error").html("Por favor, introduce un formato de RFC v&aacute;lido");
		}else if(dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)){
			$("#error").html("Por favor, introduce un formato de RFC v&aacute;lido");
		}else if(dia > 29 && mes == 2){
			$("#error").html("Por favor, introduce un formato de RFC v&aacute;lido");
		}else if(dia > 28 && mes == 2 && ano%4 != 0 && ano != 0){
			$("#error").html("Por favor, introduce un formato de RFC v&aacute;lido");
		}else{
			$("#busBoton").prop("disabled",true);
			$("#error").html("");
			cargando();
			$.post("servicios/busqueda_rfc", {"rfc":$("#rfc").val(),"buscar":"rfc"}, mostrarResultados);
		}
	}else if($("#curp").val() != ""){
		$("#curp").val($("#curp").val().toUpperCase());
		$("#curp").val($("#curp").val().trim());
		var curpregex = /^[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])([HM](((A|B|C|D|G|H|J|M|N|O|P|Q|S|T|V|Y|Z)?)|((AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)([B-DF-HJ-NP-TV-Z]([B-DF-HJ-NP-TV-Z]([B-DF-HJ-NP-TV-Z]([0-9A-Z][0-9]?)?)?)?)?)?))?$/;
		var dia = parseInt($("#curp").val().substr(8,2));
		var mes = parseInt($("#curp").val().substr(6,2));
		var ano = parseInt($("#curp").val().substr(4,2));
		if($("#curp").val().length < 10){
			$("#error").html("Por favor, introduce al menos 10 caracteres");
		}else if(!curpregex.test($("#curp").val())){
			$("#error").html("Por favor, introduce un formato de CURP v&aacute;lido");
		}else if(dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)){
			$("#error").html("Por favor, introduce un formato de CURP v&aacute;lido");
		}else if(dia > 29 && mes == 2){
			$("#error").html("Por favor, introduce un formato de CURP v&aacute;lido");
		}else if(dia > 28 && mes == 2 && ano%4 != 0 && ano != 0){
			$("#error").html("Por favor, introduce un formato de CURP v&aacute;lido");
		}else{
			$("#busBoton").prop("disabled",true);
			$("#error").html("");
			cargando();
			$.post("servicios/busqueda_rfc", {"curp":$("#curp").val(),"buscar":"curp"}, mostrarResultados);
		}
	}else if($("#nombre").val() != ""){
		$("#nombre").val($("#nombre").val().toUpperCase());
		$("#nombre").val($("#nombre").val().trim());
		if($("#nombre").val().length < 10){
			$("#error").html("Por favor, introduce al menos 10 caracteres");
		}else{
			$("#busBoton").prop("disabled",true);
			$("#error").html("");
			cargando();
			$.post("servicios/busqueda_rfc", {"nombre":$("#nombre").val(),"buscar":"nombre"}, mostrarResultados);
		}
	}
}

function cargando(){
	$("#tresultados").html("");
	var s = "<tr id='filaMensaje'>";
	s += "<td colspan='7' style='background-color:#DBDBDB'><img src='imgs/cargando3.gif' style='left: 450px; position: relative;'></td>";
	s += "</tr>";
	$("#tresultados").append(s);
}

var jsonResultados;
var tablaCampos = ["rfc","curp","nombre","fechaEnrolamiento","localidadEnrolamiento","unidadEnrolamiento","duplicidad"];
function mostrarResultados(json, textStatus, error){
	$("#busBoton").prop("disabled",false);
	if(json != undefined && json.mensaje != undefined){
		llenaTablaError("Error de conexi&oacute;n, por favor vuelve a intentarlo");
	}else{
		jsonResultados = new Array();
		for(id in json){
			jsonResultados.push(json[id]);
		}
		if(jsonResultados.length == 0){
			llenaTablaError("No se encontraron resultados.");
		}else{
			llenaTabla();
			indOrden = 0;
			bOrden = -1;
		}
	}
}

function llenaTablaError(msj){
	$("#tresultados").html("");
	var s = "<tr id='filaError'>";
	s += "<td colspan='7'>"+msj+"</td>";
	s += "</tr>";
	$("#tresultados").append(s);
}

function llenaTabla(){
	$("#tresultados").html("");
	if(jsonResultados.length == 20){
		$("#error").html("Depure su b&uacutesqueda para un resultado m&aacutes preciso (Se muestran los primeros 20 resultados)");
	}
	for(var i = 0; i < jsonResultados.length; i++){
		var s = "<tr id='fila"+i+"'>";
		for(var j = 0; j < orden.length;j++){
			if(tablaCampos[orden[j]] == "duplicidad"){
				s += "<td>"+(jsonResultados[i]["duplicidad"] == "true" ? "si" : "no")+"</td>";
			}else{
				if(jsonResultados[i][tablaCampos[orden[j]]] != undefined && jsonResultados[i][tablaCampos[orden[j]]] != ""){
					s += "<td>"+jsonResultados[i][tablaCampos[orden[j]]]+"</td>";
				}else{
					s += "<td>--</td>";
				}

			}
		}
		s += "</tr>";
		$("#tresultados").append(s);
		$("#fila"+i).one("click",{"rfclink":jsonResultados[i]["rfc"]}, function(e){
			var reemplaza = e.data["rfclink"].replace(/Ñ/g, "_");
			var rfcuri =encodeURIComponent(reemplaza);
			window.location.href = "visor_documentos.jsp?rfc="+rfcuri+"&version=0";
		});	
	}
}

var indOrden = 0;
var bOrden = -1;
function ordena(){
	indOrden = $(this).attr("id").substr(2);
	$("#flechaOrden").remove();
	if(jsonResultados){
		if(bOrden == -1 || bOrden != indOrden){
			$(".cellHolder", this).append("<div id='flechaOrden' class='flecha'><img src='imgs/flecha_asc0.png'></div>");
			bOrden = indOrden;
			jsonResultados.sort(comparaObjeto);
			llenaTabla();
		}else{
			bOrden = -1;
			$(".cellHolder", this).append("<div id='flechaOrden' class='flecha'><img src='imgs/flecha_des0.png'></div>");
			jsonResultados.reverse();
			llenaTabla();
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

function cerrarError(){
	$("#dialogoHolderError").css("visibility","hidden");
}
