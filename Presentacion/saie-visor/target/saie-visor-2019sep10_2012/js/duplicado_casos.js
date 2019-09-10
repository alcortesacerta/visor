$(document).ready(ready);

var tampag = 1000;
var ipag = 0;
var ipag2 = 0;
var indOrden = 0;
var bOrden = -1;
var jsonResultados;			//arreglo de json con todos los resultados de posibles duplicados
var jsonResultadosActual;	//arreglo de json con todos los resultados de dictaminados
var jsonResultados2;		//arreglo de json con el subconjunto de posibles duplicados dependiendo de la busqueda
var jsonResultados2actual;	//arreglo de json con el subconjunto dictaminados dependiendo de la busqueda
var jsonResultadosPag;		//arreglo de json con el subsubconjunto posibles dependiendo de la pagina actual
var jsonResultadosPag2;		//Arreglo de json con el subsubconjunto dictaminados dependiendo de la pagina actual
var tablaCampos = ["caso_numero_caso","biometricos","nombre_completo","rfc","fecha_enrolamiento","localidad_enrolamiento","reporte_url","score"];
var indOrden2 = 0;
var bOrden2 = -1;
var tablaCampos2 = ["caso_numero_caso","biometricos","nombre_completo","rfc","fecha_enrolamiento","localidad_enrolamiento","resolucion","observaciones","score","reporte_url","casoSat"];

function ready(){
	$('#paginacionCasos').paginacion({tamano: 20, refrescar: llenaTabla});
	$('#paginacionDuplicados').paginacion({tamano: 20, refrescar: llenaTablaDuplicado});
	
	$('#tablaResultadosHead1').dragtable({dragHandle:'.tablaDrag',persistState: function(table) {
		orden = new Array();
	    table.el.find('th').each(function(i) {
	    	orden.push(this.id.substring(2));
	    });
	    var n = 0;
	    $("#tablaResultados1").find('th').each(function(item){
	    	$(this).attr("id","td"+orden[n]);
	    	$(this).attr("class","th"+orden[n]);
	    	n++;
	    });
	    llenaTabla(jsonResultadosPag);
	    $.ajax({url: 'servicios/guarda_tabla_casos', data: {"orden":orden.toString()}}); 
	  } 
	});
	for(var i = 0; i < orden.length;i++){
		if(i == 1){continue;}
		$("#td"+i).click(ordena);
	}
	$("#tableHolder").scroll(function(){
		$("#tablaHeadHolder1").css("top",$("#tableHolder").scrollTop()+"px");
		if($("ul") && $(".ui-sortable-helper")){
			var ulwidth = $("ul").width();
			var ilwidth = $(".ui-sortable-helper").width();
			var illeft = $(".ui-sortable-helper").css("left").substr(0,$(".ui-sortable-helper").css("left").length-2);
			if(illeft > ulwidth/2){
				$(".ui-sortable-helper").css("left",ulwidth-ilwidth+"px");
			}
		}
	});
	
	$('#tablaResultadosHead2').dragtable({dragHandle:'.tablaDrag',persistState: function(table) {
		orden2 = new Array();
	    table.el.find('th').each(function(i) {
	    	orden2.push(this.id.substring(3));
	    });
	    var n = 0;
	    $("#tablaResultados2").find('th').each(function(item){
	    	$(this).attr("id","tdd"+orden2[n]);
	    	$(this).attr("class","th2"+orden2[n]);
	    	n++;
	    });
	    llenaTablaDuplicado(jsonResultadosPag2);
	    $.ajax({url: 'servicios/guarda_tabla_duplicidad', data: {"orden":orden2.toString()}}); 
	  } 
	});
	for(var i = 0; i < orden2.length;i++){
		if(i == 1 || i == 9){continue;}
		$("#tdd"+i).click(ordena2);
	}
	$("#tableHolder2").scroll(function(){
		$("#tablaHeadHolder2").css("top",$("#tableHolder2").scrollTop()+"px");
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
	$.post("servicios/lista_casos", mostrarResultados);
	
	$("#catalogo").change(cambiaCatalogo);
	$("#catalogo").val("posibles");
	$("#catalogo").change();
	
	$("#bCerrarDialogoFile").click(cerrarDialogoFile);
	$("#dialogoHolderFile").css("visibility","hidden");
	$("#bCerrarDialogoError").on("click",cerrarDialogoError);
	
	$("#fnombre").on("input",busca);
	$("#frfc").on("input",busca);
	$("#firmaFiel").submit(validarFirma);
	
	$("#resolucion").change(cambiaReporte);
	$("#observaciones").change(cambiaReporte);
	$("#casoSatLabel").css("display","none");
	$("#casoSatCampo").css("display","none");
	
	$.post("servicios/limpiar_sesion");
}

function cambiaReporte(){
	if($("#resolucion").val() == 1 && $("#observaciones").val() == 5){
		$("#casoSatLabel").css("display","inline");
		$("#casoSatCampo").css("display","inline");
		$("#casoSat").val("");
	}else{		
		$("#casoSatLabel").css("display","none");
		$("#casoSatCampo").css("display","none");
		$("#casoSat").val("-1");
	}
}

function cargando(){
	$("#tresultados").html("");
	var s = "<tr id='filaMensaje'>";
	s += "<td colspan='7' style='background-color:#DBDBDB'><img src='imgs/cargando3.gif' style='left: 450px; position: relative;'></td>";
	s += "</tr>";
	$("#tresultados").append(s);
}

function cerrarDialogoError(){
	$("#dialogoHolderError").css("visibility","hidden");
}

function validarFirma(e){
	var reg = /[^0-9]/;
	if($("#ffilename").val() == ""){
		$("#errorFiel").html("Por favor, introduce el archivo del reporte");
	}else if($("#dictaminacion").val() == ""){
		$("#errorFiel").html("Por favor, selecciona un tipo de dictaminación");
	}else if($("#observaciones").val() == ""){
		$("#errorFiel").html("Por favor, introduce tus observaciones");
	}else if($("#claveFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce la clave");
	}else if($("#llaveFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce la llave");
	}else if($("#certificadoFiel").val() == ""){
		$("#errorFiel").html("Por favor, introduce el certificado");
	}else if(($("#casoSat").val() != "-1" && reg.test($("#casoSat").val())) || $("#casoSat").val() == ""){
		$("#errorFiel").html("Por favor, introduce un número de Caso SAT válido");
	}else{
		$("#errorFiel").html("");
		return true;
	}
	return false;
}

function cerrarDialogoFile(){
	$("#dialogoHolderFile").css("visibility","hidden");
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
	jsonResultados2actual = new Array();
	if(e.currentTarget.id == "frfc"){
		$("#fnombre").prop('disabled', true);
		$("#frfc").val($("#frfc").val().toUpperCase());
		for(var i = 0; i < jsonResultados.length; i++){
			if(jsonResultados[i]["rfc"].indexOf($("#frfc").val()) != -1 || jsonResultados[i]["rfc2"].indexOf($("#frfc").val()) != -1){
				jsonResultadosActual.push(jsonResultados[i]);
			}
		}
		for(var i = 0; i < jsonResultados2.length; i++){
			if(jsonResultados2[i]["rfc"].indexOf($("#frfc").val()) != -1 || jsonResultados2[i]["rfc2"].indexOf($("#frfc").val()) != -1){
				jsonResultados2actual.push(jsonResultados2[i]);
			}
		}
	}else{
		$("#frfc").prop('disabled', true);
		var nombre = $("#fnombre").val().toLowerCase();
		for(var i = 0; i < jsonResultados.length; i++){
			if(jsonResultados[i]["nombre_completo"].toLowerCase().indexOf(nombre) != -1 || jsonResultados[i]["nombre_completo2"].toLowerCase().indexOf(nombre) != -1){
				jsonResultadosActual.push(jsonResultados[i]);
			}
		}
		for(var i = 0; i < jsonResultados2.length; i++){
			if(jsonResultados2[i]["nombre_completo"].toLowerCase().indexOf(nombre) != -1 || jsonResultados2[i]["nombre_completo2"].toLowerCase().indexOf(nombre) != -1){
				jsonResultados2actual.push(jsonResultados2[i]);
			}
		}
	}
	$('#paginacionCasos').paginacion("actualizaDatos",jsonResultadosActual);
	$('#paginacionDuplicados').paginacion("actualizaDatos",jsonResultados2actual);
	if($("#fnombre").val() == "" && $("#frfc").val() == ""){
		$("#frfc").prop('disabled', false);
		$("#fnombre").prop('disabled', false);
	}
}

function abrirDialogoFile(numeroCaso, rfc1, rfc2){
	$("#fcaso").val(numeroCaso);
	$("#frfc1").val(rfc1);
	$("#frfc2").val(rfc2);
	$("#dialogoHolderFile").css("visibility","visible");
}

function ordena(){
	indOrden = $(this).attr("id").substr(2);
	$("#flechaOrden").remove();
	if(jsonResultados){
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

function mostrarResultados(json, textStatus){
	jsonResultados = new Array();
	jsonResultadosActual = new Array();
	jsonResultados2 = new Array();
	jsonResultados2actual = new Array();
	for(id in json){
		if(json[id].resolucion == ""){
			jsonResultados.push(json[id]);
			jsonResultadosActual.push(json[id]);
		}else{
			jsonResultados2.push(json[id]);
			jsonResultados2actual.push(json[id]);
		}
	}
	indOrden = 0;
	bOrden = -1;
	indOrden2 = 0;
	bOrden2 = -1;
	jsonResultados.sort(comparaObjeto);
	jsonResultados.reverse();
	jsonResultadosActual.sort(comparaObjeto);
	jsonResultadosActual.reverse();
	jsonResultados2.sort(comparaObjetoSat);
	jsonResultados2.reverse();
	jsonResultados2actual.sort(comparaObjetoSat);
	jsonResultados2actual.reverse();
	$('#paginacionCasos').paginacion("actualizaDatos",jsonResultadosActual);
	$('#paginacionDuplicados').paginacion("actualizaDatos",jsonResultados2actual);	
}

function llenaTabla(jsonResultadosTemp){
	jsonResultadosPag = jsonResultadosTemp;
	$("#flechaOrden").remove();
	$("#tresultados").html("");
	if(jsonResultadosTemp.length == 0){
		var s = "<tr id='filaError'>";
		s += "<td colspan='7'>No existen coincidencias</td>";
		s += "</tr>";
		$("#tresultados").append(s);
	}
	for(var i = 0; i < jsonResultadosTemp.length ; i++){
		var s = "<tr id='fila"+i+"'>";
		for(var j = 0; j < orden.length;j++){
			if(tablaCampos[orden[j]] == "biometricos"){
				s+= "<td>";
				s+="<div class='botonBio'><a href='duplicado_huella.jsp?caso="+jsonResultadosTemp[i]["caso_numero_caso"]+"&score="+jsonResultadosTemp[i]["score"]+"'><img src='imgs/boton_huella.png' width='30px' height='30px'/></a></div>";
				s+="<div class='botonBio'><a href='duplicado_iris.jsp?caso="+jsonResultadosTemp[i]["caso_numero_caso"]+"&score="+jsonResultadosTemp[i]["score"]+"'><img src='imgs/boton_ojo.png' width='30px' height='30px'/></a></div>";
				s+="<div class='botonBio'><a href='duplicado_rostro.jsp?caso="+jsonResultadosTemp[i]["caso_numero_caso"]+"&score="+jsonResultadosTemp[i]["score"]+"'><img src='imgs/boton_persona.png' width='30px' height='30px'/></a></div>";
				s+="</td>";
			}else if(tablaCampos[orden[j]] == "caso_numero_caso"){
				var casoAux = parseInt(jsonResultadosTemp[i][tablaCampos[orden[j]]]);
				s += "<td>"+ (casoAux < 0 ? "H"+Math.abs(casoAux) : casoAux)+"</td>";
			}else if(tablaCampos[orden[j]] == "reporte_url"){
				if(biometrista && (jsonResultadosTemp[i][tablaCampos[orden[j]]] == null || jsonResultadosTemp[i][tablaCampos[orden[j]]] == "")){
					s += "<td><div class='botonBio' onclick='abrirDialogoFile(\""+jsonResultadosTemp[i]["caso_numero_caso"]+"\",\""+jsonResultadosTemp[i]["rfc"]+"\",\""+jsonResultadosTemp[i]["rfc2"]+"\")'><img src='imgs/boton_subir.png' width='30px' height='30px'/></div></td>";
				}else if(jsonResultadosTemp[i][tablaCampos[orden[j]]] == null || jsonResultadosTemp[i][tablaCampos[orden[j]]] == ""){
					s += "<td><p>no disponible</p></td>";
				}else{
					s += "<td><div class='botonBio'><a href='imagen/"+jsonResultadosTemp[i][tablaCampos[orden[j]]]+"?tipo=reporte&imagen="+jsonResultadosTemp[i][tablaCampos[orden[j]]]+"'><img src='imgs/boton_bajar.png' width='30px' height='30px'/></a></div></td>";
				}
			}else if(tablaCampos[orden[j]] == "score"){
				if (jsonResultadosTemp[i][tablaCampos[orden[j]]] == null || jsonResultadosTemp[i][tablaCampos[orden[j]]] == "") {
					s += "<td><p></p></td>";
				}else{
					s += "<td>"+jsonResultadosTemp[i][tablaCampos[orden[j]]]+"%</td>";
				}
		}else if (tablaCampos[orden[j]] == null) {
				s += "<td>"+jsonResultadosTemp[i][tablaCampos[orden[j]]]+"</td>";	
			}else{
				s += "<td><p>"+jsonResultadosTemp[i][tablaCampos[orden[j]]]+"</p><p>"+jsonResultadosTemp[i][tablaCampos[orden[j]]+"2"]+"</p></td>";
			}
		}
		s += "</tr>";
		$("#tresultados").append(s);
	}
}

function comparaObjeto(obja, objb){
	if(indOrden == 0){
		if(parseInt(obja[tablaCampos[0]]) < parseInt(objb[tablaCampos[0]]))
			return -1;
		if (parseInt(obja[tablaCampos[0]]) > parseInt(objb[tablaCampos[0]]))
			return 1;
	}else{
		var nameA=obja[tablaCampos[indOrden]].toLowerCase(), nameB=objb[tablaCampos[indOrden]].toLowerCase();
		if (nameA < nameB) //sort string ascending
			return -1;
		if (nameA > nameB)
			return 1;
	}
	return 0;
}

function comparaObjetoSat(obja, objb){
	if(parseInt(obja["casoSat"]) < parseInt(objb["casoSat"]))
		return -1;
	if (parseInt(obja["casoSat"]) > parseInt(objb["casoSat"]))
		return 1;
	return 0;
}

function ordena2(){
	indOrden2 = $(this).attr("id").substr(3);
	$("#flechaOrden").remove();
	if(jsonResultados2){
		if(bOrden2 == -1 || bOrden2 != indOrden2){
			bOrden2 = indOrden2;
			jsonResultadosPag2.sort(comparaObjeto);
			llenaTablaDuplicado(jsonResultadosPag2);
			$(".cellHolder", this).append("<div id='flechaOrden' class='flecha'><img src='imgs/flecha_asc0.png'></div>");
		}else{
			bOrden2 = -1;
			jsonResultadosPag2.reverse();
			llenaTablaDuplicado(jsonResultadosPag2);
			$(".cellHolder", this).append("<div id='flechaOrden' class='flecha'><img src='imgs/flecha_des0.png'></div>");
		}
	}
}

function llenaTablaDuplicado(jsonResultadosTemp){
	jsonResultadosPag2 = jsonResultadosTemp;
	$("#flechaOrden").remove();
	$("#tresultados2").html("");
	if(jsonResultadosTemp.length == 0){
		var s = "<tr id='filaError'>";
		s += "<td colspan='10'>No existen coincidencias</td>";
		s += "</tr>";
		$("#tresultados2").append(s);
	}
	for(var i = 0; i < jsonResultadosTemp.length ; i++){
		var s = "<tr id='fila"+i+"'>";
		for(var j = 0; j < orden2.length;j++){
			if(tablaCampos2[orden2[j]] == "biometricos"){
				s+= "<td>";
				s+="<div class='botonBio'><a href='duplicado_huella.jsp?caso="+jsonResultadosTemp[i]["caso_numero_caso"]+"&score="+jsonResultadosTemp[i]["score"]+"'><img src='imgs/boton_huella.png' width='30px' height='30px'/></a></div>";
				s+="<div class='botonBio'><a href='duplicado_iris.jsp?caso="+jsonResultadosTemp[i]["caso_numero_caso"]+"&score="+jsonResultadosTemp[i]["score"]+"'><img src='imgs/boton_ojo.png' width='30px' height='30px'/></a></div>";
				s+="<div class='botonBio'><a href='duplicado_rostro.jsp?caso="+jsonResultadosTemp[i]["caso_numero_caso"]+"&score="+jsonResultadosTemp[i]["score"]+"'><img src='imgs/boton_persona.png' width='30px' height='30px'/></a></div>";
				s+="</td>";
			}else if(tablaCampos2[orden2[j]] == "caso_numero_caso"){
				var casoAux = parseInt(jsonResultadosTemp[i][tablaCampos2[orden2[j]]]);
				s += "<td>"+ (casoAux < 0 ? "H"+Math.abs(casoAux) : casoAux) +"</td>";
			}else if(tablaCampos2[orden2[j]] == "resolucion" || tablaCampos2[orden2[j]] == "observaciones"){
				s += "<td>"+jsonResultadosTemp[i][tablaCampos2[orden2[j]]]+"</td>";
			}else if(tablaCampos2[orden2[j]] == "reporte_url"){
				if(jsonResultadosTemp[i][tablaCampos2[orden2[j]]] == null || jsonResultadosTemp[i][tablaCampos2[orden2[j]]] == "" || jsonResultadosTemp[i][tablaCampos2[orden2[j]]] == "NO DISPONIBLE"){
					s += "<td><p>no disponible</p></td>";
				}else{
					s += "<td><div class='botonBio'><a href='imagen/"+jsonResultadosTemp[i][tablaCampos2[orden2[j]]]+"?tipo=reporte&imagen="+jsonResultadosTemp[i][tablaCampos2[orden2[j]]]+"'><img src='imgs/boton_bajar.png' width='30px' height='30px'/></a></div></td>";
				}
			}else if(tablaCampos2[orden2[j]] == "score"){
				if(jsonResultadosTemp[i][tablaCampos2[orden2[j]]] == null || jsonResultadosTemp[i][tablaCampos2[orden2[j]]] == ""){
					s += "<td><p></p></td>";
				}else{
					s += "<td>"+jsonResultadosTemp[i][tablaCampos2[orden2[j]]]+"%</td>";
				}
			}else if(tablaCampos2[orden2[j]] == "casoSat"){
				if(jsonResultadosTemp[i][tablaCampos2[orden2[j]]] == -1){
					s += "<td><p></p></td>";
				}else{
					s += "<td>"+jsonResultadosTemp[i][tablaCampos2[orden2[j]]]+"</td>";
				}
			}else{
				s += "<td><p>"+jsonResultadosTemp[i][tablaCampos2[orden2[j]]]+"</p><p>"+jsonResultadosTemp[i][tablaCampos2[orden2[j]]+"2"]+"</p></td>";
			}	
		}
		s += "</tr>";
		$("#tresultados2").append(s);
	}
}

function cambiaCatalogo(){
	$("#frfc").val("");
	$("#fnombre").val("");
	$("#frfc").trigger("input");
	if($("#catalogo").val() == "posibles"){
		$("#resultadosHolder").show();
		$("#resultadosHolder2").hide();
	}else{
		$("#resultadosHolder").hide();
		$("#resultadosHolder2").show();
	}
}
