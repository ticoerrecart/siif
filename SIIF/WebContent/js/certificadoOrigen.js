	function actualizarComboExportador() {

		$('#nroMatricula').val("");
		$('#prefijoCuit').val("");
		$('#nroCuit').val("");
		$('#sufijoCuit').val("");		
		
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

		deshabilitarLocalizacion([ "idPMF"]);

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

	function actualizarComboPMF() {
		idPF = $('#idProductor').val();

		deshabilitarLocalizacion([ "idPMF" ]);

		if (idPF > 0) {
			UbicacionFachada.getPMFs(idPF, actualizarComboPMFCallback);
		}
		
		$('#divFiscalizaciones').html("");		
	}

	function actualizarComboPMFCallback(pmfs) {
		dwr.util.removeAllOptions("idPMF");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("idPMF", data, "id", "nombre");
		dwr.util.addOptions("idPMF", pmfs, "id", "nombreExpediente");
		$('#idPMF').removeAttr('disabled');
	}

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

	function mostrarFiscalizaciones(){

		var idProductor = $('#idProductor').val();
		var periodo = $('#periodo').val();
		var idPMF = $('#idPMF').val();
		
		//var forward = $('#paramForward').val();
		
		$('#divCargando').show();	
		$('#divFiscalizaciones').html("");
		
		if(idProductor != "" && idProductor != "-1" && idPMF != "" && idPMF != "-1"){
		//alert($('#paramUrlDetalle').val() + '&idProductor='+idProductor)
			$('#divFiscalizaciones').load( "../../certificadoOrigen.do?metodo=recuperarDatosParaAltaCertificadoOrigen&idProductor="+idProductor+"&periodo="+periodo+"&idPMF="+idPMF);
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
	