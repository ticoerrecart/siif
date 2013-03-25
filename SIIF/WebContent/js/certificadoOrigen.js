	function actualizarComboExportador() {

		$('#nroMatricula').val("");
		$('#prefijoCuit').val("");
		$('#nroCuit').val("");
		$('#sufijoCuit').val("");		
		
		$('#idTrFacturaVolTrans').hide();
		
		var idTipoDeEntidad = $('#selectTiposDeEntidadExportador').val();
		if(idTipoDeEntidad != "-1"){
			$('#idExportador').attr('disabled',false);
			EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarExportadoresCallback );		
		}else{
			dwr.util.removeAllOptions("idExportador");
			var data = [ { nombre:"-Seleccione un Exportador-", id:-1 }];
			dwr.util.addOptions("idExportador", data, "id", "nombre");		
			$('#idExportador').attr('disabled',true);
		}		
	}

	function actualizarExportadoresCallback(exportadores) {

		dwr.util.removeAllOptions("idExportador");
		var data = [ {
			nombre : "-Seleccione un Exportador-",
			id : -1
		} ];
		dwr.util.addOptions("idExportador", data, "id", "nombre");
		dwr.util.addOptions("idExportador", exportadores, "id", "nombre");
	}

	function actualizarDatosExportador(){
		
		idExp = $('#idExportador').val();
		$('#nroMatricula').val("");
		$('#prefijoCuit').val("");
		$('#nroCuit').val("");
		$('#sufijoCuit').val("");

		verificarExportadorProductor();		
		
		if(idExp != "-1"){
		
			EntidadFachada.getEntidadDTO(idExp,actualizarDatosExportadorCallback );	
		}
	}
	
	function actualizarDatosExportadorCallback(exportador) {
				
		dwr.util.setValue("nroMatricula", exportador.nroMatricula);
		
		cuit = exportador.cuit;

		dwr.util.setValue("prefijoCuit", cuit.substring(0,2));
		dwr.util.setValue("nroCuit", cuit.substring(2,cuit.length-1));
		dwr.util.setValue("sufijoCuit", cuit.substring(cuit.length-1));			
	}	
	
	function actualizarComboProductores() {

		//deshabilitarLocalizacion([ "idZMF"]);

		$('#idTrFacturaVolTrans').hide();
		
		var idTipoDeEntidad = $('#selectTiposDeEntidad').val();
		if(idTipoDeEntidad != "-1"){
			$('#idProductor').attr('disabled',false);
			EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresCallback );		
		}else{
			dwr.util.removeAllOptions("idProductor");
			var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
			dwr.util.addOptions("idProductor", data, "id", "nombre");		
			$('#idProductor').attr('disabled',true);
		}
		
		$('#divFiscalizaciones').html("");		
	}

	function actualizarProductoresCallback(productores) {

		dwr.util.removeAllOptions("idProductor");
		var data = [ {
			nombre : "-Seleccione un Productor-",
			id : -1
		} ];
		dwr.util.addOptions("idProductor", data, "id", "nombre");
		dwr.util.addOptions("idProductor", productores, "id", "nombre");
				
	}

	/*function actualizarComboPMF() {
		idPF = $('#idProductor').val();

		deshabilitarLocalizacion([ "idPMF" ]);

		if (idPF > 0) {
			UbicacionFachada.getPMFs(idPF, actualizarComboPMFCallback);
		}
		
		$('#divFiscalizaciones').html("");	
		
		verificarExportadorProductor();		
	}*/
	

	function actualizarComboPMF() {
		idPF = $('#idProductor').val();
		UbicacionFachada.getPMFs(idPF, {
			callback : actualizarComboPMFCallback,
			async : false
		});
	}

	function actualizarComboPMFCallback(pmfs) {
		dwr.util.removeAllOptions("idPMF");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("idPMF", data, "id", "nombre");
		dwr.util.addOptions("idPMF", pmfs, "id", "nombreExpediente");
		$('#idZMF').removeAttr('disabled');
		$(".plan").show();
	}

	function actualizarComboArea() {
		idPF = $('#idProductor').val();
		UbicacionFachada.getAreasDTO(idPF, {
			callback : actualizarComboAreaCallback,
			async : false
		});
	}

	function actualizarComboAreaCallback(areas) {
		dwr.util.removeAllOptions("idArea");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("idArea", data, "id", "nombre");
		dwr.util.addOptions("idArea", areas, "id", "fullNombre");
		$('#idZMF').removeAttr('disabled');
		$(".area").show();
	}


	function cambioComboProductores() {
		$('#idZMF').removeAttr('disabled');
		$('#idZMF').val('0');
		cambioComboZona();
	}

	function cambioComboZona() {

		zmf = $('#idZMF').val();
		idPF = $('#idProductor').val();
		
		if (idPF == 0 || zmf == 0) {
			$(".area").hide();
			$(".plan").hide();
		} else {
			if (zmf == 1) {
				$(".plan").show();
				$(".area").hide();

				actualizarComboPMF();
			}
			if (zmf == 2) {
				$(".area").show();
				$(".plan").hide();

				actualizarComboArea();
			}
		}
		
		$('#divFiscalizaciones').html("");
		
		verificarExportadorProductor();	
	}
	
	
	
	

/*	function actualizarComboPMFCallback(pmfs) {
		dwr.util.removeAllOptions("idPMF");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("idPMF", data, "id", "nombre");
		dwr.util.addOptions("idPMF", pmfs, "id", "nombreExpediente");
		$('#idPMF').removeAttr('disabled');
	}*/

	function deshabilitarLocalizacion(ids) {

		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		for (i = 0; i < ids.length; i++) {
			dwr.util.removeAllOptions(ids[i]);
			dwr.util.addOptions(ids[i], data, "id", "nombre");
			$('#' + ids[i]).attr('disabled', 'disabled');
		}
	}

	function verificarExportadorProductor(){
		
		idExp = $('#idExportador').val();
		idProd = $('#idProductor').val();
						
		if(idExp != idProd && idExp != -1 && idProd != -1){
			
			$('#idTrFacturaVolTrans').show();
		}
		else{
			$('#idTrFacturaVolTrans').hide();
		}
	}
	
	function mostrarFiscalizaciones(){

		var idProductor = $('#idProductor').val();
		var periodo = $('#periodo').val();
		var zmf = $('#idZMF').val();
		var idLocalizacion = -1;
		if (zmf == 1) {
			idLocalizacion =  $('#idPMF').val();
		}
		if (zmf == 2) {
			idLocalizacion =  $('#idArea').val();
		}
		
		$('#divCargando').show();	
		$('#divFiscalizaciones').html("");
		//alert(idProductor + "/" + zmf + "/" + idLocalizacion)
		if(idProductor != "" && idProductor != "-1" && idLocalizacion != "" && idLocalizacion != "-1"){
			$('#divFiscalizaciones').load( "../../certificadoOrigen.do?metodo=recuperarDatosParaAltaCertificadoOrigen&idProductor="+idProductor+"&periodo="+periodo+"&idLocalizacion="+idLocalizacion);
			$('#divFiscalizaciones').hide();
			$('#divFiscalizaciones').fadeIn(600);

			Concurrent.Thread.create(function(){
			    while ($('#divFiscalizaciones').html() == "") {}
			    $('#divCargando').hide();
			});		
			
		}else{
			$('#divFiscalizaciones').hide(600);
			$('#divFiscalizaciones').html("");
			$('#divCargando').hide();
		}	
	}

	var tr = null;
	var clase = null;
	function mostrarDatos(idTr){
		
		if(tr!=null){
			$('#tr'+tr).attr("class", clase);	
		}
		tr=idTr;
		clase = $('#tr'+tr).attr("class");
		$('#tr'+tr).attr("class", "seleccionado");	
	}	

	function roundNumber(num, dec) {
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}

	function cambiarRadio(res){

		$('#idRadioDeuda').val(res);
		if(res == "S"){
			$('#idSubtituloDeuda').attr("class", "grisLeft");
			$('#idDeuda').attr("class", "grisLeft");
		}
		else{
			$('#idSubtituloDeuda').attr("class", "botoneralNegritaLeft");
			$('#idDeuda').attr("class", "rojoAdvertenciaLeft");			
		}
	}
	
	function mostrarLocalidades(){

		var idProvinciaSelect = $('#idProvincia').val();
		if(idProvinciaSelect != "-1"){
			
			$('#idLocalidad').attr('disabled',false);
			LocalidadFachada.getLocalidadesDetinoDTODeProvincia(idProvinciaSelect,actualizarLocalidadesCallback );
			
		}else{
			
			dwr.util.removeAllOptions("idLocalidad");
			var data = [ { nombre:"-Seleccione una Localidad-", id:-1 }];
			dwr.util.addOptions("idLocalidad", data, "id", "nombre");		
			$('#idLocalidad').attr('disabled',true);			
		}			
	}

	function actualizarLocalidadesCallback(localidades) {

		dwr.util.removeAllOptions("idLocalidad");
		var data = [ {
			nombre : "-Seleccione una Localidad-",
			id : -1
		} ];
		dwr.util.addOptions("idLocalidad", data, "id", "nombre");
		dwr.util.addOptions("idLocalidad", localidades, "id", "nombre");
	}	
	

	