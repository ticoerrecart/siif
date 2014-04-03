	function submitir(){
		validarForm("ubicacionForm","../ubicacion","validarUbicacionForm","UbicacionForm");
	}

	
	function actualizarComboZona(){
		
		zmf = $('#idZMF').val();
		idPF = $('#idProductorForestal').val();
		if (idPF == 0 ){
			hideAltas();
			$(".area").hide();
			$(".plan").hide();
		} else {
			if (zmf == 1 ){
				$(".plan").show();
				$(".area").hide();
				hideAltas();
				actualizarComboPMF();
			}	
			if (zmf == 2 ){
				$(".area").show();
				$(".plan").hide();
				hideAltas();
				actualizarComboArea();
			}
		}
		
	}
	
	function hideAltas(){
		dwr.util.removeAllOptions("idPMF");
		$('#altaDePMF').hide();
		dwr.util.removeAllOptions("idTranzon");
		$('#altaDeTranzon').hide();
		dwr.util.removeAllOptions("idMarcacion");
		$('#altaDeMarcacion').hide();
		dwr.util.removeAllOptions("idRodal");
		$('#altaDeRodal').hide();
		dwr.util.removeAllOptions("idArea");
		$('#altaDeArea').hide();
		
	}
	
	
	function actualizarComboArea(){
		idPF = $('#idProductorForestal').val();
		if (idPF == 0 ){
			$('#altaDeArea').hide();
		} else {
			UbicacionFachada.getAreasDTO(idPF,actualizarComboAreaCallback );
		}
	}
	
	function actualizarComboAreaCallback(areas){
		dwr.util.removeAllOptions("idArea");		
		var data = [ { nombre:"--Alta de Area de Cosecha--", id:-1 }];
		dwr.util.addOptions("idArea", data, "id", "nombre");
		dwr.util.addOptions("idArea", areas,"id","fullNombre");
		cambioComboArea();
	}

	
	function cambioComboArea(){
		idArea = $('#idArea').val();
		if (idArea == -1 ){
			$('#altaDeArea').show();
		} else {
			$('#altaDeArea').hide();
		}
	}
	
	
	function actualizarComboPMF(){
		idPF = $('#idProductorForestal').val();
		if (idPF == 0 ){
			dwr.util.removeAllOptions("idPMF");
			$('#altaDePMF').hide();
			dwr.util.removeAllOptions("idTranzon");
			$('#altaDeTranzon').hide();
			dwr.util.removeAllOptions("idMarcacion");
			$('#altaDeMarcacion').hide();
			dwr.util.removeAllOptions("idRodal");
			$('#altaDeRodal').hide();
		} else {
			UbicacionFachada.getPMFsDTO(idPF,actualizarComboPMFCallback );
		}
		
	}

	
	function actualizarComboPMFCallback(pmfs){
		dwr.util.removeAllOptions("idPMF");		
		var data = [ { nombre:"--Alta de PMF--", id:-1 }];
		dwr.util.addOptions("idPMF", data, "id", "nombre");
		//dwr.util.addOptions("idPMF", pmfs,"id","nombre");
		dwr.util.addOptions("idPMF", pmfs,"id","nombreExpediente");
		
		//actualizar combos en cascada
		actualizarComboTranzon();
		
	}

	function actualizarComboTranzon(){
		idPMF = $('#idPMF').val();
		if (idPMF == -1 ){
			$('#altaDePMF').show();
			dwr.util.removeAllOptions("idTranzon");
			$('#altaDeTranzon').hide();
			dwr.util.removeAllOptions("idMarcacion");
			$('#altaDeMarcacion').hide();
			dwr.util.removeAllOptions("idRodal");
			$('#altaDeRodal').hide();
		} else {
			$('#altaDePMF').hide();
			//UbicacionFachada.getTranzones(idPMF,actualizarComboTranzonCallback );
			UbicacionFachada.getTranzonesDTOById(idPMF,actualizarComboTranzonCallback );
		}
		
	}

	function actualizarComboTranzonCallback(tranzones){
		dwr.util.removeAllOptions("idTranzon");
		//dwr.util.addOptions("idTranzon", tranzones,"id","numero");
		dwr.util.addOptions("idTranzon", tranzones,"id","numeroDisposicionTranzon");
		var data = [ { nombre:"--Alta de Tranzon--", id:-1 }];
		dwr.util.addOptions("idTranzon", data, "id", "nombre");

		//actualizar combos en cascada
		actualizarComboMarcacion();
	}

	function actualizarComboMarcacion(){
		idTranzon = $('#idTranzon').val();
		if (idTranzon == -1 ){
			$('#altaDeTranzon').show();
			dwr.util.removeAllOptions("idMarcacion");
			$('#altaDeMarcacion').hide();
			dwr.util.removeAllOptions("idRodal");
			$('#altaDeRodal').hide();
		} else {
			$('#altaDeTranzon').hide();
			//UbicacionFachada.getMarcaciones(idTranzon,actualizarComboMarcacionCallback );
			UbicacionFachada.getMarcacionesDTOById(idTranzon,actualizarComboMarcacionCallback );
		}
		
	}

	function actualizarComboMarcacionCallback(marcaciones){
		dwr.util.removeAllOptions("idMarcacion");
		dwr.util.addOptions("idMarcacion", marcaciones,"id","disposicionMarcacion");
		var data = [ { nombre:"--Alta de Marcacion--", id:-1 }];
		dwr.util.addOptions("idMarcacion", data, "id", "nombre");
		//actualizar combos en cascada
		actualizarComboRodal();
	}
	
	function actualizarComboRodal(){
		idMarcacion = $('#idMarcacion').val();
		if (idMarcacion == -1 ){
			$('#altaDeMarcacion').show();
			dwr.util.removeAllOptions("idRodal");
			$('#altaDeRodal').hide();
		} else {
			$('#altaDeMarcacion').hide();
			//UbicacionFachada.getRodales(idMarcacion,actualizarComboRodalCallback );
			UbicacionFachada.getRodalesDTOById(idMarcacion,actualizarComboRodalCallback );
		}
		
		
	}

	function actualizarComboRodalCallback(rodales){
		dwr.util.removeAllOptions("idRodal");
		dwr.util.addOptions("idRodal", rodales,"id","nombreRodal");
		var data = [ { nombre:"--Alta de Rodal--", id:-1 }];
		dwr.util.addOptions("idRodal", data, "id", "nombre");
		cambioComboRodal();
	}

	function cambioComboRodal(){
		idRodal = $('#idRodal').val();
		if (idRodal == -1 ){
			$('#altaDeRodal').show();
		} else {
			$('#altaDeRodal').hide();
		}
	}

	function ocultar(idTabla){
		clearMensajesErrores();
		$('#'+idTabla).hide();
	}

	function validarRodal(){
		nombreRodal = $('#nombreRodal').val();
		idMarcacion = $('#idMarcacion').val();
		UbicacionAction.validarRodal(nombreRodal, idMarcacion,validarRodalCallback );
	}

	function  validarRodalCallback(errores){
		mostrarErroresOSubmitir(errores,'submitirRodal()');
	}
	
	function submitirRodal(){
		nombreRodal = $('#nombreRodal').val();
		idMarcacion = $('#idMarcacion').val();
		UbicacionFachada.altaRodal(nombreRodal, idMarcacion,submitirRodalCallback );
		
	}
	
	function submitirRodalCallback(){
		actualizarComboRodal();
		$('#nombreRodal').val("");
		$('#altaDeRodal').hide();
	}


	function validarMarcacion(){
		disposicionMarcacion = $('#disposicionMarcacion').val();
		idTranzon = $('#idTranzon').val();
		UbicacionAction.validarMarcacion(disposicionMarcacion, idTranzon ,validarMarcacionCallback );
	}

	function  validarMarcacionCallback(errores){
		mostrarErroresOSubmitir(errores,'submitirMarcacion()');
	}
	
	
	function submitirMarcacion(){
		disposicionMarcacion = $('#disposicionMarcacion').val();
		idTranzon = $('#idTranzon').val();
		UbicacionFachada.altaMarcacion(disposicionMarcacion, idTranzon,submitirMarcacionCallback );
		
	}
	function submitirMarcacionCallback(){
		actualizarComboMarcacion();
		$('#disposicionMarcacion').val("");
		$('#altaDeMarcacion').hide();
	}

	function validarTranzon(){
		numeroTranzon =  $('#numeroTranzon').val();
		disposicionTranzon = $('#disposicionTranzon').val();
		idPMF = $('#idPMF').val();
		UbicacionAction.validarTranzon(numeroTranzon, disposicionTranzon, idPMF,validarTranzonCallback);
	}

	function  validarTranzonCallback(errores){
		mostrarErroresOSubmitir(errores,'submitirTranzon()');
	}

	
	function submitirTranzon(){
		numeroTranzon =  $('#numeroTranzon').val();
		disposicionTranzon = $('#disposicionTranzon').val();
		idPMF = $('#idPMF').val();
		UbicacionFachada.altaTranzon(numeroTranzon, disposicionTranzon, idPMF ,submitirTranzonCallback );
	}	
		
	function submitirTranzonCallback(errores){
			actualizarComboTranzon();
			$('#numeroTranzon').val("");
			$('#disposicionTranzon').val("");
			$('#altaDeTranzon').hide();
	}

	function validarPMF(){
		expedientePMF =  $('#expedientePMF').val();
		nombrePMF = $('#nombrePMF').val();
		idPF = $('#idProductorForestal').val();
		UbicacionAction.validarPMF(expedientePMF, nombrePMF, idPF,validarPMFCallback);
	}

	function  validarPMFCallback(errores){
		mostrarErroresOSubmitir(errores,'submitirPMF()');
	}
	
	function submitirPMF(){
		expedientePMF =  $('#expedientePMF').val();
		nombrePMF = $('#nombrePMF').val();
		tipoTerrenoPMF = $('#tipoTerrenoPMF').val();
		idPF = $('#idProductorForestal').val();
		UbicacionFachada.altaPMF(expedientePMF, nombrePMF, tipoTerrenoPMF, idPF,submitirPMFCallback );
		
	}
	function submitirPMFCallback(){
		actualizarComboPMF();
		$('#expedientePMF').val("");
		$('#nombrePMF').val("");
		$('#altaDePMF').hide();
	}

	
	function validarArea(){
		reservaForestalArea = $('#reservaForestalArea').val();
		nombreArea = $('#nombreArea').val();
		disposicionArea = $('#disposicionArea').val();
		expedienteArea =  $('#expedienteArea').val();
		idPF = $('#idProductorForestal').val();
		UbicacionAction.validarArea(reservaForestalArea, nombreArea, disposicionArea,expedienteArea, idPF,validarAreaCallback);
	}

	function  validarAreaCallback(errores){
		mostrarErroresOSubmitir(errores,'submitirArea()');
	}
	
	function submitirArea(){
		reservaForestalArea = $('#reservaForestalArea').val();
		nombreArea = $('#nombreArea').val();
		disposicionArea = $('#disposicionArea').val();
		expedienteArea =  $('#expedienteArea').val();
		tipoTerrenoArea = $('#tipoTerrenoArea').val();
		idPF = $('#idProductorForestal').val();
		UbicacionFachada.altaArea(reservaForestalArea, nombreArea, disposicionArea,expedienteArea, idPF,tipoTerrenoArea,submitirAreaCallback );
		
	}
	function submitirAreaCallback(){
		actualizarComboArea();
		$('#reservaForestalArea').val("");
		$('#nombreArea').val("");
		$('#disposicionArea').val("");
		$('#expedienteArea').val("");
		$('#altaDeArea').hide();
	}
	
	
	function errorCallback(error){
		alert(error);
	}

	
	function errorHandler(msg, exc) {
		$('#errores').html(msg);
	}
	dwr.engine.setErrorHandler(errorHandler);
