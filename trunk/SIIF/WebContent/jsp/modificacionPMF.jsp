<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/JQuery/jquery-1.3.2.min.js'/>"></script>

<script type="text/javascript">
	function toggle(id){
		$('#' + id).toggle();
	}

	function mostrarDatosRodal(idFila,idRodal, nombreRodal){
		$("#tablaRodales tr").removeClass("seleccionado");
		$("#R" + idFila).addClass("seleccionado"); 
		$('#divModificacionRodal').fadeIn(600);
		$('#idRodal').val(idRodal);	
		$('#nombreRodal').val(nombreRodal); 	
	}

	function mostrarDatosMarcacion(idFila,idMarcacion, disposicionMarcacion){
		$("#tablaMarcaciones tr").removeClass("seleccionado");
		$("#M" + idFila).addClass("seleccionado"); 
		$('#divModificacionMarcacion').fadeIn(600);
		$('#idMarcacion').val(idMarcacion);	
		$('#disposicionMarcacion').val(disposicionMarcacion); 	
	}

	function mostrarDatosTranzon(idFila,idTranzon, numeroTranzon, disposicionTranzon){
		$("#tablaTranzones tr").removeClass("seleccionado");
		$("#T" + idFila).addClass("seleccionado"); 
		$('#divModificacionTranzon').fadeIn(600);
		$('#idTranzon').val(idTranzon);
		$('#numeroTranzon').val(numeroTranzon);		
		$('#disposicionTranzon').val(disposicionTranzon); 	
	}

	function mostrarDatosPMF(idFila,idPMF, nombrePMF, expedientePMF){
		$("#tablaPmfs tr").removeClass("seleccionado");
		$("#P" + idFila).addClass("seleccionado"); 
		$('#divModificacionPMF').fadeIn(600);
		$('#idPMF').val(idPMF);
		$('#nombrePMF').val(nombrePMF);		
		$('#expedientePMF').val(expedientePMF); 	
	}
	
	function modificarRodal(){
		idRodal = $('#idRodal').val();
		nombreRodal = $('#nombreRodal').val() 
		UbicacionFachada.modificarRodal(idRodal,nombreRodal,submitirRodalCallback );
	}

	function modificarMarcacion(){
		idMarcacion = $('#idMarcacion').val();
		disposicionMarcacion = $('#disposicionMarcacion').val() 
		UbicacionFachada.modificarMarcacion(idMarcacion,disposicionMarcacion,submitirMarcacionCallback );
	}

	function modificarTranzon(){
		idTranzon = $('#idTranzon').val();
		numeroTranzon = $('#numeroTranzon').val();
		disposicionTranzon = $('#disposicionTranzon').val() 
		UbicacionFachada.modificarTranzon(idTranzon,numeroTranzon,disposicionTranzon,submitirTranzonCallback );
	}

	function modificarPMF(){
		idPMF = $('#idPMF').val();
		nombrePMF = $('#nombrePMF').val();
		expedientePMF = $('#expedientePMF').val() 
		UbicacionFachada.modificarPMF(idPMF,nombrePMF,expedientePMF,submitirPMFCallback );
	}
	
	function deleteRodal(){
		idRodal = $('#idRodal').val();
		UbicacionFachada.deleteRodal(idRodal,submitirRodalCallback );
	}

	function deleteMarcacion(){
		idMarcacion = $('#idMarcacion').val();
		UbicacionFachada.deleteMarcacion(idMarcacion,submitirMarcacionCallback );
	}

	function deleteTranzon(){
		idTranzon = $('#idTranzon').val();
		UbicacionFachada.deleteTranzon(idTranzon,submitirTranzonCallback );
	}

	function deletePMF(){
		idPMF = $('#idPMF').val();
		UbicacionFachada.deletePMF(idPMF,submitirPMFCallback );
	}
	
	function cancelarRodal(){
		$('#exitoGrabado').html("");
		$("#tablaRodales tr").removeClass("seleccionado");
		$('#divModificacionRodal').hide();
	}

	function cancelarMarcacion(){
		$('#exitoGrabado').html("");
		$("#tablaMarcaciones tr").removeClass("seleccionado");
		$('#divModificacionMarcacion').hide();
	}

	function cancelarTranzon(){
		$('#exitoGrabado').html("");
		$("#tablaTranzones tr").removeClass("seleccionado");
		$('#divModificacionTranzon').hide();
	}

	function cancelarPMF(){
		$('#exitoGrabado').html("");
		$("#tablaPmfs tr").removeClass("seleccionado");
		$('#divModificacionPMF').hide();
	}
	
	function submitirRodalCallback(){
		cancelarRodal();
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales';
		$('#bloqueRodales').load(url);
		$('#exitoGrabado').html("Rodal Modificado/Borrado con Exito");
	}

	function submitirMarcacionCallback(){
		cancelarMarcacion();
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales';
		$('#bloqueRodales').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeMarcaciones';
		$('#bloqueMarcaciones').load(url);
		$('#exitoGrabado').html("Marcacion Modificada/Borrada con Exito");
	}

	function submitirTranzonCallback(){
		cancelarTranzon();
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales';
		$('#bloqueRodales').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeMarcaciones';
		$('#bloqueMarcaciones').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeTranzones';
		$('#bloqueTranzones').load(url);
		$('#exitoGrabado').html("Tranzon Modificado/Borrado con Exito");
	}

	function submitirPMFCallback(){
		cancelarPMF();
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales';
		$('#bloqueRodales').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeMarcaciones';
		$('#bloqueMarcaciones').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeTranzones';
		$('#bloqueTranzones').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDePMFs';
		$('#bloquePMFs').load(url);
		$('#exitoGrabado').html("PMF Modificado/Borrado con Exito");
	}
	
	
	$().ready(function() {
		
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales';
		$('#bloqueRodales').load(url);

		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeMarcaciones';
		$('#bloqueMarcaciones').load(url);
	
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeTranzones';
		$('#bloqueTranzones').load(url);
		
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDePMFs';
		$('#bloquePMFs').load(url);
		
		})
	
	
	function errorHandler(msg, exc) {
		$('#errores').html(msg + " - Detalle: " + dwr.util.toDescriptiveString(exc, 2));
	}
	dwr.engine.setErrorHandler(errorHandler);

	
	
</script>
&nbsp;
&nbsp;
<div id="exitoGrabado" class="verdeExito" >
</div>
&nbsp;
&nbsp;
<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">
	${error}
</div>

	
	<div id="bloqueRodales" >
	</div>
	
	<div id="bloqueMarcaciones">
	</div>
	
	<div id="bloqueTranzones">
	</div>
	
	<div id="bloquePMFs">
	</div>
