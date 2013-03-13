<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript" src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/JQuery/jquery-1.3.2.min.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarLetras.js'/>"></script>

<script type="text/javascript">
	function toggle(id){
		$('#' + id).toggle();
	}

	function mostrarDatosArea(idFila,idArea, reservaForestalArea,nombreArea, disposicionArea, expedienteArea){
		$("#tablaAreas tr").removeClass("seleccionado");
		$("#A" + idFila).addClass("seleccionado"); 
		$('#divModificacionArea').fadeIn(600);
		$('#idArea').val(idArea);	
		$('#reservaForestalArea').val(reservaForestalArea); 	
		$('#nombreArea').val(nombreArea);
		$('#disposicionArea').val(disposicionArea);
		$('#expedienteArea').val(expedienteArea);
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

	function mostrarDatosPMF(idFila,idPMF, nombrePMF, expedientePMF, tipoTerrenoPMF){
		$("#tablaPmfs tr").removeClass("seleccionado");
		$("#P" + idFila).addClass("seleccionado"); 
		$('#divModificacionPMF').fadeIn(600);
		$('#idPMF').val(idPMF);
		$('#nombrePMF').val(nombrePMF);		
		$('#expedientePMF').val(expedientePMF);
		$('#tipoTerrenoPMF').val(tipoTerrenoPMF); 	
	}
	
	function modificarArea(){
		idArea = $('#idArea').val();
		reservaForestalArea  = $('#reservaForestalArea').val();
		nombreArea  = $('#nombreArea').val(); 
		disposicionArea  = $('#disposicionArea').val(); 
		expedienteArea  = $('#expedienteArea').val();
		UbicacionFachada.modificarArea(idArea,reservaForestalArea,nombreArea,disposicionArea,expedienteArea,submitirAreaCallback );
	}
	
	function modificarRodal(){
		idRodal = $('#idRodal').val();
		nombreRodal = $('#nombreRodal').val(); 
		UbicacionFachada.modificarRodal(idRodal,nombreRodal,submitirRodalCallback );
	}

	function modificarMarcacion(){
		idMarcacion = $('#idMarcacion').val();
		disposicionMarcacion = $('#disposicionMarcacion').val();
		UbicacionFachada.modificarMarcacion(idMarcacion,disposicionMarcacion,submitirMarcacionCallback );
	}

	function modificarTranzon(){
		idTranzon = $('#idTranzon').val();
		numeroTranzon = $('#numeroTranzon').val();
		disposicionTranzon = $('#disposicionTranzon').val(); 
		UbicacionFachada.modificarTranzon(idTranzon,numeroTranzon,disposicionTranzon,submitirTranzonCallback );
	}

	function modificarPMF(){
		idPMF = $('#idPMF').val();
		nombrePMF = $('#nombrePMF').val();
		expedientePMF = $('#expedientePMF').val();
		UbicacionFachada.modificarPMF(idPMF,nombrePMF,expedientePMF,submitirPMFCallback );
	}

	function deleteAreal(){
		idArea = $('#idAreal').val();
		UbicacionFachada.deleteArea(idArea,submitirAreaCallback );
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
	
	function cancelarArea(){
		$('#exitoGrabado').html("");
		$("#tablaAreas tr").removeClass("seleccionado");
		$('#divModificacionArea').hide();
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

	
	function submitirAreaCallback(){
		cancelarArea();
		var idProductor = $('#idProductor').val();
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeAreas&idProductor='+ idProductor;
		$('#bloqueAreas').load(url);
		$('#exitoGrabado').html("Area de Cosecha Modificada/Borrada con Exito");
	}
	
	function submitirRodalCallback(){
		cancelarRodal();

		var idProductor = $('#idProductor').val();
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales&idProductor='+ idProductor;
		$('#bloqueRodales').load(url);
		$('#exitoGrabado').html("Rodal Modificado/Borrado con Exito");
	}

	function submitirMarcacionCallback(){
		cancelarMarcacion();

		var idProductor = $('#idProductor').val();
		
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales&idProductor='+ idProductor;
		$('#bloqueRodales').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeMarcaciones&idProductor='+ idProductor;
		$('#bloqueMarcaciones').load(url);
		$('#exitoGrabado').html("Marcacion Modificada/Borrada con Exito");
	}

	function submitirTranzonCallback(){
		cancelarTranzon();

		var idProductor = $('#idProductor').val();
		
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales&idProductor='+ idProductor;
		$('#bloqueRodales').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeMarcaciones&idProductor='+ idProductor;
		$('#bloqueMarcaciones').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeTranzones&idProductor='+ idProductor;
		$('#bloqueTranzones').load(url);
		$('#exitoGrabado').html("Tranzon Modificado/Borrado con Exito");
	}

	function submitirPMFCallback(){
		cancelarPMF();

		var idProductor = $('#idProductor').val();
		
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales&idProductor='+ idProductor;
		$('#bloqueRodales').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeMarcaciones&idProductor='+ idProductor;
		$('#bloqueMarcaciones').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeTranzones&idProductor='+ idProductor;
		$('#bloqueTranzones').load(url);
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDePMFs&idProductor='+ idProductor;
		$('#bloquePMFs').load(url);
		$('#exitoGrabado').html("PMF Modificado/Borrado con Exito");
	}
	
	
	$().ready(function() {

		var idProductor = $('#idProductor').val();
		
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeAreas&idProductor='+ idProductor;
		$('#bloqueAreas').load(url);
		
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeRodales&idProductor='+ idProductor;
		$('#bloqueRodales').load(url);

		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeMarcaciones&idProductor='+ idProductor;
		$('#bloqueMarcaciones').load(url);
	
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDeTranzones&idProductor='+ idProductor;
		$('#bloqueTranzones').load(url);
		
		var url = '../../ubicacion.do?metodo=recuperarUbicacionesParaModificacionDePMFs&idProductor='+ idProductor;
		$('#bloquePMFs').load(url);
		
	})
	
	
	function errorHandler(msg, exc) {
		alert(msg);
		$('#errores').html(msg + " - Detalle: " + dwr.util.toDescriptiveString(exc, 2));
	}
	dwr.engine.setErrorHandler(errorHandler);

	
	function ocultarMostrar(idTabla,divModificacion){	

		toggle(idTabla);
		$(divModificacion).hide();
	}

	function volver(){
		parent.location = contextRoot() +  "/ubicacion.do?metodo=recuperarProductoresParaModificacionPMF";
	}

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
	<input id="idProductor" type="hidden" value="${idProductor}">
	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				 <bean:message key='SIIF.titulo.ModificacionZMF'/>
			</td>
		</tr>
	</table>		

	<div id="bloqueAreas" >
	</div>

	<div id="bloqueRodales" >
	</div>
	
	<div id="bloqueMarcaciones">
	</div>
	
	<div id="bloqueTranzones">
	</div>
	
	<div id="bloquePMFs">
	</div>
	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
		<tr height="10">
		</tr>
		<tr>
			<td>
				 <input class="botonerab" type="button" value="Volver" onclick="volver()"> 
			</td>
		</tr>
		<tr height="10">
		</tr>		
	</table>