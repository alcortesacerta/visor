(function($) {
  $.widget("saie.paginacion", {
	  options: {
		  tamano: 20,			//tamaño de la paginación
		  refrescar: null,		//funcion que se llama al cambiar de página
		  datos: new Array() 	//arreglo de jsons con los objectos conteniendo datos
	  },
	  tamano: 20,
	  paginaActual: 0,
	  paginaTotal: 0,
	  datos: null,
	  refrescar: null,
	  indiceActual: 0,
	  _create: function(){
		  this.tamano = this.options.tamano;
		  this.refrescar = this.options.refrescar;
		  
		 this._on('.paginacionSig', {
			click: function(event){
				if(this.paginaActual < this.paginaTotal-1){
					this.paginaActual++;
					this.cambiaPagina();
				}
			} 
		 });
		 this._on('.paginacionAnt', {
			click: function(event){
				if(this.paginaActual > 0){
					this.paginaActual--;
					this.cambiaPagina();
				}
			} 
		 });
		 this._on('.paginacionInicio', {
			click: function(event){
				this.paginaActual = 0;
				this.cambiaPagina();
			} 
		 });
		 this._on('.paginacionFin', {
			click: function(event){
				this.paginaActual = this.paginaTotal-1;
				this.cambiaPagina();
			} 
		 });
		 this.actualizaDatos(this.options.datos);
	  },
	  actualizaDatos: function(datos){
		  this.datos = datos;
		  if(this.datos.length > 0){	
			  this.paginaTotal = Math.ceil(this.datos.length/this.tamano);
		  }else{
			  this.paginaTotal = 1;
		  }
		  this.paginaActual = 0;
		  $(this.element).find(".paginacionActual").html((this.paginaActual+1) + " de "+this.paginaTotal);
		  this.cambiaPagina();
	  },
	  cambiaPagina: function(){
		  indice = this.paginaActual*this.tamano;
		  var datosMostrar = this.datos.slice(indice,indice+this.tamano);
		  this.refrescar(datosMostrar);
		  $(this.element).find(".paginacionActual").html((this.paginaActual+1) + " de "+this.paginaTotal);
		  //deshabilitar los botones
		  if(this.paginaActual == 0){
			  $(this.element).find(".paginacionAnt").html("<img src='imgs/fant_off.png'>");
			  $(this.element).find(".paginacionInicio").html("<img src='imgs/fini_off.png'>");
		  }else{
			  $(this.element).find(".paginacionAnt").html("<img src='imgs/fant.png'>");
			  $(this.element).find(".paginacionInicio").html("<img src='imgs/fini.png'>");
		  }
		  if(this.paginaActual+1 == this.paginaTotal){
			  $(this.element).find(".paginacionSig").html("<img src='imgs/fsig_off.png'>");
			  $(this.element).find(".paginacionFin").html("<img src='imgs/fult_off.png'>");
		  }else{
			  $(this.element).find(".paginacionSig").html("<img src='imgs/fsig.png'>");
			  $(this.element).find(".paginacionFin").html("<img src='imgs/fult.png'>");
		  }
	  }
  });
})(jQuery);