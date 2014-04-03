function cambioComboEntidad() {
	$(".area").hide();
	$(".plan").hide();

	var idTipoDeEntidad = $('#selectTiposDeEntidad').val();
	if (idTipoDeEntidad != "-1") {
		$('#idProductor').attr('disabled', false);
		EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,
				cambioComboEntidadCallback);
	} else {
		dwr.util.removeAllOptions("idProductor");
		var data = [ {
			nombre : "-Seleccione un Productor-",
			id : -1
		} ];
		dwr.util.addOptions("idProductor", data, "id", "nombre");
		$('#idProductor').attr('disabled', true);
	}
}

function cambioComboEntidadCallback(productores) {

	dwr.util.removeAllOptions("idProductor");
	var data = [ {
		nombre : "-Seleccione un Productor-",
		id : -1
	} ];
	dwr.util.addOptions("idProductor", data, "id", "nombre");
	dwr.util.addOptions("idProductor", productores, "id", "nombre");
}

function cambioComboProductores() {
	cambioComboZona();
}

function cambioComboZona() {
	
	if($("#idLocalizacion").length > 0){
		$("#idLocalizacion").val("-1");
	}	
	zmf = $('#idZMF').val();
	idPF = $('#idProductor').val();
	if (idPF == 0 || zmf == 0) {
		$(".area").hide();
		$(".plan").hide();
	} else {
		if (zmf == 1) {
			$(".plan").show();
			$(".area").hide();
			$('#idArea').val(-1);
			actualizarComboPMF();
		}
		if (zmf == 2) {
			$(".area").show();
			$(".plan").hide();
			$('#idPMF').val(-1);
			$('#idTranzon').val(-1);
			$('#idMarcacion').val(-1);
			$('#idRodal').val(-1);
			actualizarComboArea();
		}
	}

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
	$(".area").show();
}

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
	actualizarComboTranzon();
	$(".plan").show();
}

function actualizarComboTranzon() {
	idPMF = $('#idPMF').val();
	UbicacionFachada.getTranzonesById(idPMF, {
		callback : actualizarComboTranzonCallback,
		async : false
	});
}

function actualizarComboTranzonCallback(tranzones) {
	dwr.util.removeAllOptions("idTranzon");
	var data = [ {
		nombre : "- Seleccione -",
		id : -1
	} ];
	dwr.util.addOptions("idTranzon", data, "id", "nombre");
	dwr.util.addOptions("idTranzon", tranzones, "id",
			"numeroDisposicionTranzon");

	actualizarComboMarcacion();
}

function actualizarTipoTerreno(){
	if($("#tipoTerrenoPMF").length>0){
		idPMF = $('#idPMF').val();
		if(idPMF>0){
			UbicacionFachada.getTipoTerrenoPMF(idPMF, tipoTerrenoCallback);
		}else{
			$("#tipoTerrenoPMF").val("");
		}
	}
}

function tipoTerrenoCallback(tipoTerreno){
	$("#tipoTerrenoPMF").val(tipoTerreno);
}

function actualizarComboMarcacion() {
	idTranzon = $('#idTranzon').val();
	UbicacionFachada.getMarcacionesById(idTranzon, {
		callback : actualizarComboMarcacionCallback,
		async : false
	});
}

function actualizarComboMarcacionCallback(marcaciones) {
	dwr.util.removeAllOptions("idMarcacion");
	var data = [ {
		nombre : "- Seleccione -",
		id : -1
	} ];
	dwr.util.addOptions("idMarcacion", data, "id", "nombre");
	dwr.util.addOptions("idMarcacion", marcaciones, "id",
			"disposicionMarcacion");
	actualizarComboRodal();
}

function actualizarComboRodal() {
	idMarcacion = $('#idMarcacion').val();
	UbicacionFachada.getRodalesById(idMarcacion, {
		callback : actualizarComboRodalCallback,
		async : false
	});
}

function actualizarComboRodalCallback(rodales) {
	dwr.util.removeAllOptions("idRodal");
	var data = [ {
		nombre : "- Seleccione -",
		id : -1
	} ];
	dwr.util.addOptions("idRodal", data, "id", "nombre");
	dwr.util.addOptions("idRodal", rodales, "id", "nombreRodal");

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
// ----------------------------------------------------------------------------------------------------------------------------
// tener en cuenta que el largo está en mts y los diámetros en cm!!!!
function calcularVolumen() {
	var ok = true;
	var cantidadDeFilas = $('#tablaMuestras [id*=fila]').size();
	var vol = 0;
	if (cantidadDeFilas > 0) {
		if ($('[name="fiscalizacionDTO.cantidadUnidades"]').val() != "") {
			for ( var i = 0; i < cantidadDeFilas; i++) {
				var largo = $('#tablaMuestras [id=fila' + i + '] [name*=largo]')
						.val().replace(",", ".");

				var r = $('#tablaMuestras [id=fila' + i + '] [name*=diametro1]')
						.val().replace(",", ".")
						/ (2 * 100);

				if (!$('#tablaMuestras [id=fila' + i + '] [name*=diametro2]')
						.is(":visible")) {
					$('#tablaMuestras [id=fila' + i + '] [name*=diametro2]')
							.val(r * (2 * 100));
				}
				var r2 = $(
						'#tablaMuestras [id=fila' + i + '] [name*=diametro2]')
						.val().replace(",", ".")
						/ (2 * 100);

				var v = volumenTroncoDelCono(r, r2, largo);
				vol = vol + v;
			}
			var prom = vol / cantidadDeFilas;

			$('#cantidadMts').val(
					prom
							* $('[name="fiscalizacionDTO.cantidadUnidades"]')
									.val());

			var num1 = new Number($('#cantidadMts').val());
			$('#cantidadMts').val(num1.toFixed(2)); // 3.14
		} else {
			alert("Debe ingresar la Cantidad(Unidades)");
			ok = false;
			$('[name="fiscalizacionDTO.cantidadUnidades"]').focus();
		}
	} else {
		var idTipoProductoForestal = $('#idTipoProductoForestal').val();
		if (idTipoProductoForestal != 3) {// si no es Leña
			alert("Debe Agregar alguna muestra");
			$("#idCantMuestras").focus();
			ok = false;
		}
	}

	return ok;
}

/* R, r radios; h Altura */
function volumenTroncoDelCono(R, r, h) {
	/* Pi x( ((R+r))2 x(1/4) x Largo */
	/* Pi x (1/3) x Largo x (R^2 + r^2 + Rr) */
	return (Math.PI * 1 / 3 * h * (Math.pow(R, 2) + Math.pow(r, 2) + R * r));
}

var i = 2;
var cantTotales = parseInt(0);
// var indiceDiv = 1;

function agregarMuestras() {
	var cantMuestras = $("#idCantMuestras").val();

	if (cantMuestras == "") {
		alert("Ingrese la cantidad de muestras");
		$("#idCantMuestras").focus();
	}
	for ( var i = 0; i < cantMuestras; i++) {
		agregarFila();
	}
	$('[name="fiscalizacionDTO.tamanioMuestra"]').val(
			$('#tablaMuestras [id*=fila]').size());

}

var primeraFila = '<tr id="fila0">'
		+ '   <td class="botoneralNegritaRight ind">1</td> '
		+ '   <td><input class="botonerab" type="text" name="muestrasDTO[0].diametro1" onkeypress="javascript:esNumerico(event);" onkeydown="tabOnEnter(event,this);"></td>'
		+ '   <td class="diam2"><input class="botonerab" type="text" name="muestrasDTO[0].diametro2" onkeypress="javascript:esNumerico(event);" onkeydown="tabOnEnter(event,this);"></td>'
		+ '   <td><input class="botonerab" type="text" name="muestrasDTO[0].largo" onblur="this.value = reemplazarComa(this.value);" onkeypress="javascript:esNumericoConDecimal(event);" onkeyup="javascript: twoDigits(this);" onkeydown="tabOnEnter(event,this);"></td>'
		+ '</tr>';

/*
 * <option value="1">Rollizos</option> <option value="2">Fustes</option>
 * <option value="3">Leña</option> <option value="4">Postes</option> <option
 * value="5">Trineos</option>
 */
function headerTabla(funcion) {
	var idTipoProductoForestal = $('#idTipoProductoForestal').val();
	TipoProductoForestalFachada.recuperarTipoProductoForestalDTO(
			idTipoProductoForestal, {
				callback : eval(funcion),
				async : false
			});

	/*
	 * if (idTipoProductoForestal == 1){ return headerTablaRollizos; } else if
	 * (idTipoProductoForestal == 2){ return headerTablaFustes; } else if
	 * (idTipoProductoForestal == 4){ return headerTablaPostes; } else if
	 * (idTipoProductoForestal == 5){ return headerTablaTrineos; }
	 */
}

function actualizarHeaderTablaPrependCallback(tipoProducto) {

	var diam2Desde = (tipoProducto.diam2Desde == 0)?tipoProducto.diam1Desde:tipoProducto.diam2Desde;
	var diam2Hasta = (tipoProducto.diam2Hasta == 0)?tipoProducto.diam1Hasta:tipoProducto.diam2Hasta;	
	
	var headerTablaStr = '<tr id="headerT">'
			+ '   <td class="verdeTituloTablaChico" width="3%"></td>'
			+ '   <td class="verdeTituloTablaChico" width="32%">Diametro1 ('
			+ tipoProducto.diam1Desde
			+ '-'
			+ tipoProducto.diam1Hasta
			+ 'cm)</td>'
			+ '   <td class="verdeTituloTablaChico diam2" width="32%">Diametro2 ('
			+ diam2Desde + '-' + diam2Hasta
			+ 'cm)</td>'
			+ '   <td class="verdeTituloTablaChico" width="33%">Largo ('
			+ tipoProducto.largoDesde + '-' + tipoProducto.largoHasta
			+ 'mts)</td>' + '</tr>';

	$("#tablaMuestras").prepend(headerTablaStr);
	mostrarSegunCantidadDiametros();
}

function actualizarHeaderTablaAppendCallback(tipoProducto) {

	var diam2Desde = (tipoProducto.diam2Desde == 0)?tipoProducto.diam1Desde:tipoProducto.diam2Desde;
	var diam2Hasta = (tipoProducto.diam2Hasta == 0)?tipoProducto.diam1Hasta:tipoProducto.diam2Hasta;
	
	var headerTablaStr = '<tr id="headerT">'
			+ '   <td class="verdeTituloTablaChico" width="3%"></td>'
			+ '   <td class="verdeTituloTablaChico" width="32%">Diametro1 ('
			+ tipoProducto.diam1Desde
			+ '-'
			+ tipoProducto.diam1Hasta
			+ 'cm)</td>'
			+ '   <td class="verdeTituloTablaChico diam2" width="32%">Diametro2 ('
			+ diam2Desde + '-' + diam2Hasta
			+ 'cm)</td>'
			+ '   <td class="verdeTituloTablaChico" width="33%">Largo ('
			+ tipoProducto.largoDesde + '-' + tipoProducto.largoHasta
			+ 'mts)</td>' + '</tr>';

	$("#tablaMuestras").append(headerTablaStr);
	$("#tablaMuestras").append(primeraFila);
	actualizarMuestras();

}

// se usa en la edición de Fiscalización
/*
 * function agregarHeader(){ if ($('#tablaMuestras tr').size() == 0) {
 * //$("#tablaMuestras").prepend(headerTabla);
 * headerTabla("actualizarHeaderTablaPrependCallback"); } }
 */

function agregarFila() {

	if ($('#tablaMuestras tr').size() == 0) {
		// $("#tablaMuestras").append(headerTabla);
		headerTabla("actualizarHeaderTablaAppendCallback");
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
		var newId = $("#tablaMuestras tr:last").attr('id').replace(j - 1, j);
		$("#tablaMuestras tr:last").attr('id', newId);
		actualizarMuestras();
	}

}

function removerMuestras() {
	var cant = $("#idCantMuestras").val();
	removerNMuestras(cant);
}

function removerNMuestras(cant) {
	var cantTotales = $('#tablaMuestras [id*=fila]').size();
	// if(cantTotales>0){
	for ( var i = 0; i < cant; i++) {
		$('#tablaMuestras tr:last').remove();
		cantTotales--;
		if (cantTotales == 0) {
			// remuevo el encabezado
			$('#tablaMuestras tr:last').remove();
			break;
		}
	}

	if (cantTotales >= 0) {
		$('[name="fiscalizacionDTO.tamanioMuestra"]').val(cantTotales);
	}
	// }

}

/*
 * '1', 'Rollizos' '2', 'Fustes' '3', 'Leña' '4', 'Postes' '5', 'Trineos'
 */
function actualizarMuestras() {

	var cantDiam = $("#cantidadDiametros").val();		
	if(cantDiam != ''){
		actualizarMuestrasCallback(cantDiam);
	}else{			
		var idTipoProductoForestal = $('#idTipoProductoForestal').val();
		TipoProductoForestalFachada.getCantidadDiametros(idTipoProductoForestal, {
			callback : actualizarMuestrasCallback,
			async : false
		});		
	}	
}

function cambiarTipoProducto(){
	
	//Blanqueo la cantidad de diametros para que se pueda setear
	//cantidadDiametros con el valor de cantidad de diametros del nuevo tipo de producto
	$("#cantidadDiametros").val("");

	actualizarMuestras();
}

function mostrarSegunCantidadDiametros() {
	var cant = $("#cantidadDiametros").val();
	// if (idTipoProductoForestal == 2 || idTipoProductoForestal == 5){
	if (cant == 2) {
		if ($('#tablaMuestras [id*=fila]').size() > 0) {
			$('#tablaMuestras').show();
			$('#calcularVolumen').show();
			$('.diam2').show();
			$('[name="fiscalizacionDTO.tipoProducto.cantDiametros"]').filter(
					'[value="2"]').attr('checked', true);
			$('#diametros').show();
		} else {
			$('#tablaMuestras').hide();
			$('#calcularVolumen').hide();
		}
		$("#cantidadMts").attr("readonly", true);
		$("#cantidadUnidades").attr("readonly", false);

		$('#trMuestras').show();
		// } else if (idTipoProductoForestal == 1 || idTipoProductoForestal == 4
		// ){
	} else if (cant == 1) {
		if ($('#tablaMuestras [id*=fila]').size() > 0) {
			$('#tablaMuestras').show();
			$('#calcularVolumen').show();
			$('.diam2').hide();
			$('[name="fiscalizacionDTO.tipoProducto.cantDiametros"]').filter(
					'[value="1"]').attr('checked', true);
			$('#diametros').show();
		} else {
			$('#tablaMuestras').hide();
			$('#calcularVolumen').hide();
		}
		$("#cantidadMts").attr("readonly", true);
		$("#cantidadUnidades").attr("readonly", false);

		$('#trMuestras').show();
	} else {
		removerNMuestras($('[name="fiscalizacionDTO.tamanioMuestra"]').val());
		$("#cantidadMts").attr("readonly", false);

		$("#cantidadUnidades").val("");
		$("#cantidadUnidades").attr("readonly", true);
		$('#trMuestras').hide();
		$('#diametros').hide();
	}

}
function actualizarMuestrasCallback(cant) {
	// $('#cantidadMts').val('');
	$("#cantidadDiametros").val(cant);

	if ($('#tablaMuestras tr').size() > 0) {
		$("#headerT").remove();
		// $("#tablaMuestras").prepend(headerTabla);
		headerTabla("actualizarHeaderTablaPrependCallback");
	} else {
		mostrarSegunCantidadDiametros();
	}

}

function cambiarDiametro() {
	var val = $(
			'input:radio[name=fiscalizacionDTO.tipoProducto.cantDiametros]:checked')
			.val();
	if (val == 1) {
		$('.diam2').hide();
		var cantidadDeFilas = $('#tablaMuestras [id*=fila]').size();
		if (cantidadDeFilas > 0) {
			for ( var i = 0; i < cantidadDeFilas; i++) {
				$('#tablaMuestras [id=fila' + i + '] [name*=diametro2]')
						.val('');
			}
			$('#tablaMuestras [id=fila0] [name*=diametro1]').focus();
		}
	} else {
		if (val == 2) {
			$('.diam2').show();
			$('#tablaMuestras [id=fila0] [name*=diametro2]').focus();
		}
	}

	$("#cantidadDiametros").val(val);	
}

function reemplazarComa(valor) {
	return valor.replace(",", ".");
}

function twoDigits(elem) {
	if (elem.value.indexOf('.') != -1) {
		var unPunto = false;
		for ( var i = 0; i < elem.value.length; i++) {
			var c = elem.value[i];
			if (c == ".") {
				if (!unPunto) {
					unPunto = true;
				} else {
					elem.value = elem.value.substring(0, elem.value.length - 1);
					break;
				}
			}
		}
		if (elem.value.split(".")[1].length > 2) {
			if (isNaN(parseFloat(elem.value)))
				return;
			elem.value = parseFloat(elem.value).toFixed(2);
		}
	}
	return this; // for chaining
}

function tabOnEnter(event, elem) {
	var n = $('#tablaMuestras :input').length;
	var key;
	if (type == "IE") {
		key = event.keyCode;
	}

	if (type == "MO" || type == "OP") {
		key = event.which;
	}

	if (key == 13) { // Enter key
		// event.preventDefault(); //Skip default behavior of the enter key

		var nextIndex = $('#tablaMuestras :input').index(elem);

		do {
			nextIndex++;
			if (nextIndex < n) {
				sig = $('#tablaMuestras :input')[nextIndex];
			}
			// alert(nextIndex + "/" + n);
		} while (nextIndex < n && sig.parentElement.style.display == "none");

		if (nextIndex < n) {
			$('#tablaMuestras :input')[nextIndex].focus();
		}

	}
}
