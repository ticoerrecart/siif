	function actualizarComboProductores() {

		deshabilitarLocalizacion([ "idPMF", "idTranzon", "idMarcacion",
				"idRodal" ]);

		/*if ($("#idLocalidad").val() != "-1") {
			$('#idProductor').removeAttr('disabled');
			EntidadFachada.getEntidadesPorLocalidad($("#idLocalidad").val(),
					actualizarProductoresCallback);
		} else {
			dwr.util.removeAllOptions("idProductor");
			var data = [ {
				nombre : "-Seleccione un Productor-",
				id : -1
			} ];
			dwr.util.addOptions("idProductor", data, "id", "nombre");
			$('#idProductor').attr('disabled', 'disabled');
		}*/

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

		deshabilitarLocalizacion([ "idPMF", "idTranzon", "idMarcacion",
				"idRodal" ]);

		if (idPF > 0) {
			UbicacionFachada.getPMFs(idPF, actualizarComboPMFCallback);
		}
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

	function actualizarComboTranzon() {
		idPMF = $('#idPMF').val();
		deshabilitarLocalizacion([ "idTranzon", "idMarcacion", "idRodal" ]);

		if (idPMF > 0) {
			UbicacionFachada.getTranzonesById(idPMF,
					actualizarComboTranzonCallback);
		}
	}

	function actualizarComboTranzonCallback(tranzones) {
		dwr.util.removeAllOptions("idTranzon");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("idTranzon", data, "id", "nombre");
		dwr.util.addOptions("idTranzon", tranzones, "id", "numeroDisposicion");
		$('#idTranzon').removeAttr('disabled');
	}

	function actualizarComboMarcacion() {
		idTranzon = $('#idTranzon').val();

		deshabilitarLocalizacion([ "idMarcacion", "idRodal" ]);

		if (idTranzon > 0) {
			UbicacionFachada.getMarcacionesById(idTranzon,
					actualizarComboMarcacionCallback);
		}
	}

	function actualizarComboMarcacionCallback(marcaciones) {
		dwr.util.removeAllOptions("idMarcacion");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("idMarcacion", data, "id", "nombre");
		dwr.util.addOptions("idMarcacion", marcaciones, "id", "disposicion");
		$('#idMarcacion').removeAttr('disabled');
	}

	function actualizarComboRodal() {
		idMarcacion = $('#idMarcacion').val();

		deshabilitarLocalizacion([ "idRodal" ]);

		if (idMarcacion > 0) {
			UbicacionFachada.getRodalesById(idMarcacion,
					actualizarComboRodalCallback);
		}
	}

	function actualizarComboRodalCallback(rodales) {
		dwr.util.removeAllOptions("idRodal");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("idRodal", data, "id", "nombre");
		dwr.util.addOptions("idRodal", rodales, "id", "nombre");
		$('#idRodal').removeAttr('disabled');
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
//----------------------------------------------------------------------------------------------------------------------------
function calcularVolumen() {

		var cantidadDeFilas = $('#tablaMuestras [id*=fila]').size();
		var vol = 0;
		if(cantidadDeFilas > 0){
			for ( var i = 0; i < cantidadDeFilas; i++) {
				var largo = $('#tablaMuestras [id=fila' + i + '] [name*=largo]')
						.val();
				var r = $('#tablaMuestras [id=fila' + i + '] [name*=diametro1]')
						.val() / 2;
				if (! $('#tablaMuestras [id=fila' + i + '] [name*=diametro2]').is(":visible")){
					$('#tablaMuestras [id=fila' + i + '] [name*=diametro2]')
							.val(r * 2);
				}
				var r2 = $('#tablaMuestras [id=fila' + i + '] [name*=diametro2]')
						.val() / 2;
	
				var v = volumenTroncoDelCono(r, r2, largo);
				vol = vol + v;
			}
			var prom = vol / cantidadDeFilas;
			
			$('#cantidadMts').val(prom * $('[name="fiscalizacionDTO.cantidadUnidades"]').val());

			
			var num1 = new Number($('#cantidadMts').val());			
			$('#cantidadMts').val(num1.toFixed(2)); // 3.14
		}	

	}

	/*R, r radios; h Altura */
	function volumenTroncoDelCono(R, r, h) {
		/*Pi x( ((R+r))2 x(1/4) x Largo*/
		/*Pi x (1/3) x Largo x (R^2 + r^2 + Rr)*/
		return (Math.PI * 1 / 3 * h * (Math.pow(R, 2) + Math.pow(r, 2) + R * r));
	}

	
	var i=2;
	var cantTotales = parseInt(0);
	//var indiceDiv = 1;



	function agregarMuestras(){
		var cantMuestras = $("#idCantMuestras").val();
		for ( var i = 0; i < cantMuestras; i++) {
			agregarFila();
		}	
		$('[name="fiscalizacionDTO.tamanioMuestra"]').val($('#tablaMuestras [id*=fila]').size());
		
		
	}	

	var primeraFila = '<tr id="fila0">' +
							'   <td class="botoneralNegritaRight ind">1</td> ' +
							'   <td><input class="botonerab" type="text" name="muestrasDTO[0].largo"></td>' +
							'   <td><input class="botonerab" type="text" name="muestrasDTO[0].diametro1"></td>' +
							'   <td class="diam2"><input class="botonerab" type="text" name="muestrasDTO[0].diametro2"></td>' +
							'</tr>';

	function agregarFila() {

		if ($('#tablaMuestras tr').size() == 0) {
			$("#tablaMuestras").append(headerTabla);
			$("#tablaMuestras").append(primeraFila);
		} else {
			var j = $('#tablaMuestras tr:last .ind').text();
			$("#tablaMuestras tr:last").clone().find("input").each(function() {
				$(this).attr({
					'name' : function(_, name) {
						return name.replace([ j - 1 ], [ j ]);
					},
					'value' : ''
				});
			}).end().appendTo("#tablaMuestras");
			$('#tablaMuestras tr:last .ind').text(1 + parseInt(j));
			var newId = $("#tablaMuestras tr:last").attr('id')
					.replace(j - 1, j);
			$("#tablaMuestras tr:last").attr('id', newId);
		}
		
		actualizarMuestras();
	}

	
	function removerMuestras() {
		var cant = $("#idCantMuestras").val();
		removerNMuestras(cant);
	}

	function removerNMuestras(cant) {
		var cantTotales = $('#tablaMuestras [id*=fila]').size();
		//if(cantTotales>0){
			for ( var i = 0; i < cant; i++) {
				$('#tablaMuestras tr:last').remove();
				cantTotales--;
				if (cantTotales == 0) {
					//remuevo el encabezado
					$('#tablaMuestras tr:last').remove();
					break;
				}
			}
			$('[name="fiscalizacionDTO.tamanioMuestra"]').val(cantTotales);
		//}

	}


	function actualizarMuestras() {
		$('#cantidadMts').val('');
		var idTipoProductoForestal = $('#idTipoProductoForestal').val();
		if (idTipoProductoForestal == 2 || idTipoProductoForestal == 5){
			if($('#tablaMuestras [id*=fila]').size() > 0){
				$('#tablaMuestras').show();
				$('#calcularVolumen').show();
				$('.diam2').show();
			}else{
				$('#tablaMuestras').hide();
				$('#calcularVolumen').hide();
			}
			$('#trMuestras').show();
		}  else if (idTipoProductoForestal == 1 ){
			if($('#tablaMuestras [id*=fila]').size() > 0){
				$('#tablaMuestras').show();
				$('#calcularVolumen').show();
				$('.diam2').hide();
			}else{
				$('#tablaMuestras').hide();
				$('#calcularVolumen').hide();
			}
			$('#trMuestras').show();
		} else {
			removerNMuestras($('[name="fiscalizacionDTO.tamanioMuestra"]').val()); 
			$('#trMuestras').hide();
		}

	}