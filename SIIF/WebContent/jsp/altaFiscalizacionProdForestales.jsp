<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>	
<script type="text/javascript"
	src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>
<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	
<script>
	$(function() {
		$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});		
	});
</script>

<script type="text/javascript">

function submitir(){

	var prodEnabled = $('#idProductor').attr('disabled');
	var pmfEnabled = $('#idPMF').attr('disabled');
	var tranEnabled = $('#idTranzon').attr('disabled');
	var marcEnabled = $('#idMarcacion').attr('disabled');
	var rodEnabled = $('#idRodal').attr('disabled');
	
	$('#idProductor').attr('disabled','');
	$('#idPMF').attr('disabled','');
	$('#idTranzon').attr('disabled','');
	$('#idMarcacion').attr('disabled','');
	$('#idRodal').attr('disabled','');
	validarForm("fiscalizacionForm","../fiscalizacion","validarFiscalizacionForm","FiscalizacionForm");
	$('#idProductor').attr('disabled',prodEnabled);
	$('#idPMF').attr('disabled',pmfEnabled);
	$('#idTranzon').attr('disabled',tranEnabled);
	$('#idMarcacion').attr('disabled',marcEnabled);
	$('#idRodal').attr('disabled',rodEnabled);	
}

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";
function exp(sec) {
	
	 if (type=="IE") { 
	 	 eval("document.all." + "e"+sec + ".style.display='none'");
	 	 eval("document.all." + "c"+sec + ".style.display=''"); 	 
	 	}
	 if (type=="MO" || type=="OP") {
	    eval("document.getElementById('" + "e"+sec + "').style.display='none'");
	    eval("document.getElementById('" + "c"+sec + "').style.display=''");
	   }
}

function col(sec) {
	
 if (type=="IE") { 
 	 eval("document.all." + "c"+sec + ".style.display='none'");
 	 eval("document.all." + "e"+sec + ".style.display=''"); 	 
 	}
 if (type=="MO" || type=="OP") {
    eval("document.getElementById('" + "c"+sec + "').style.display='none'");
    eval("document.getElementById('" + "e"+sec + "').style.display=''");
   }
}

var i=2;
var cantTotales = parseInt(0);
var indiceDiv = 1;
function agregarMuestras(){

	var cantMuestras = $("#idCantMuestras").val();
	
	if(cantMuestras != "" && cantMuestras > 0){
		//$("#tablaMuestras").show();
		$('#tablaMuestras').fadeIn(600);
		
		var cant = parseInt($("#idCantMuestras").val());
		//$("#tabla").load('/SIIF/jsp/bloqueMuestrasFiscalizacion.jsp?cantMuestras=' + cant);
	 
	 	var iDiv = parseInt(indiceDiv)+1;
		 
		$('#muestrasAux').load('/SIIF/jsp/bloqueMuestrasFiscalizacion.jsp?cantMuestras=' + cant + '&cantTotal='+ cantTotales
				+ '&indiceDiv='+ iDiv, function(){
					//$("#prueba1").append($("#dummy").html()); NO ANDA ESTA LINEA EN IE
					var id = "tabla"+indiceDiv;
					document.getElementById(id).innerHTML = //document.getElementById("tabla").innerHTML + 
					document.getElementById("muestrasAux").innerHTML;
					cantTotales = parseInt(cantTotales) + parseInt(cant);
					indiceDiv++;				 			
				}
		);
	}		
}

function removerMuestras(){

	var cant = $("#idCantMuestras").val();
	var i=0;
	while(i<cant && cantTotales>0){
		var id = cantTotales-1;
		$("#espAntes"+id).remove();
		$("#fila"+ id).remove();
		$("#espDespues"+id).remove();
		
		cantTotales--;
		i++;
	}	

	if(cantTotales == 0){
		$("#tablaMuestras").hide();
	}
	
}

function actualizarComboProductores(){

	deshabilitarLocalizacion(["idPMF","idTranzon","idMarcacion","idRodal"]);

	if($("#idLocalidad").val() != "-1"){		
		$('#idProductor').attr('disabled','');
		EntidadFachada.getEntidadesPorLocalidad($("#idLocalidad").val(),actualizarProductoresCallback );
	}else{
		dwr.util.removeAllOptions("idProductor");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("idProductor", data, "id", "nombre");
		$('#idProductor').attr('disabled','disabled');		
	}		
}

function actualizarProductoresCallback(productores){

	dwr.util.removeAllOptions("idProductor");
	var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
	dwr.util.addOptions("idProductor", data, "id", "nombre");	
	dwr.util.addOptions("idProductor", productores,"id","nombre");	
}

function actualizarComboPMF(){
	idPF = $('#idProductor').val();

	deshabilitarLocalizacion(["idPMF","idTranzon","idMarcacion","idRodal"]);
	
	if (idPF > 0 ){
		UbicacionFachada.getPMFs(idPF,actualizarComboPMFCallback );
	}
}

function actualizarComboPMFCallback(pmfs){
	dwr.util.removeAllOptions("idPMF");
	var data = [ { nombre:"- Seleccione -", id:-1 }];
	dwr.util.addOptions("idPMF", data, "id", "nombre");	
	dwr.util.addOptions("idPMF", pmfs,"id","nombreExpediente");	
	$('#idPMF').attr('disabled','');
}

function actualizarComboTranzon(){
	idPMF = $('#idPMF').val();
	deshabilitarLocalizacion(["idTranzon","idMarcacion","idRodal"]);
	
	if (idPMF > 0 ){
		UbicacionFachada.getTranzonesById(idPMF,actualizarComboTranzonCallback );
	}
}

function actualizarComboTranzonCallback(tranzones){
	dwr.util.removeAllOptions("idTranzon");
	var data = [ { nombre:"- Seleccione -", id:-1 }];
	dwr.util.addOptions("idTranzon", data, "id", "nombre");	
	dwr.util.addOptions("idTranzon", tranzones,"id","numeroDisposicion");	
	$('#idTranzon').attr('disabled','');
}

function actualizarComboMarcacion(){
	idTranzon = $('#idTranzon').val();

	deshabilitarLocalizacion(["idMarcacion","idRodal"]);
	
	if (idTranzon > 0 ){
		UbicacionFachada.getMarcacionesById(idTranzon,actualizarComboMarcacionCallback );
	}
}

function actualizarComboMarcacionCallback(marcaciones){
	dwr.util.removeAllOptions("idMarcacion");
	var data = [ { nombre:"- Seleccione -", id:-1 }];
	dwr.util.addOptions("idMarcacion", data, "id", "nombre");	
	dwr.util.addOptions("idMarcacion", marcaciones,"id","disposicion");	
	$('#idMarcacion').attr('disabled','');
}

function actualizarComboRodal(){
	idMarcacion = $('#idMarcacion').val();

	deshabilitarLocalizacion(["idRodal"]);
	
	if (idMarcacion > 0 ){
		UbicacionFachada.getRodalesById(idMarcacion,actualizarComboRodalCallback );
	}
}

function actualizarComboRodalCallback(rodales){
	dwr.util.removeAllOptions("idRodal");
	var data = [ { nombre:"- Seleccione -", id:-1 }];
	dwr.util.addOptions("idRodal", data, "id", "nombre");	
	dwr.util.addOptions("idRodal", rodales,"id","nombre");	
	$('#idRodal').attr('disabled','');
}

function deshabilitarLocalizacion(ids){

	var data = [ { nombre:"- Seleccione -", id:-1 }];
	for(i=0;i<ids.length;i++){
		dwr.util.removeAllOptions(ids[i]);
		dwr.util.addOptions(ids[i], data, "id", "nombre");
		$('#'+ids[i]).attr('disabled','disabled');				
	}
	
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="fiscalizacion" styleId="fiscalizacionForm">
	<html:hidden property="metodo" value="altaFiscalizacion" />
	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIF.titulo.AltaFiscalizacion'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
			<td width="30%">
				<select id="idLocalidad" class="botonerab" onchange="actualizarComboProductores();">
					<option value="-1">-Seleccione una Localidad-</option>
					<c:forEach items="${localidades}" var="localidad">
						<option value="${localidad.id}">
							<c:out value="${localidad.nombre}"></c:out>
						</option>
					</c:forEach>	
				</select>
			</td>
			<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
			<td width="30%">
				<select id="idProductor" class="botonerab" name="idProductorForestal" onchange="actualizarComboPMF();" 
						disabled="disabled">
					<option value="-1">-Seleccione un Productor-</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha'/></td>
			<td>			
				<input id="datepicker" name="fecha" class="botonerab" type="text" size="23"	readonly="readonly">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				 
			</td>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
			<td>
				<input name="fiscalizacion.periodoForestal"	class="botonerab" type="text" size="27">
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.CantUnd'/></td>
			<td>
				<input name="fiscalizacion.cantidadUnidades" class="botonerab" type="text" size="27"
					   onkeypress="javascript:esNumerico(event);">
			</td>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoProducto'/></td>
			<td>
				<select class="botonerab" name="idTipoProductoForestal">
					<option value="-1">- Seleccione un Producto -</option>
					<c:forEach items="${tiposProducto}" var="tipoProducto">
						<option value="${tipoProducto.id}"><c:out value="${tipoProducto.nombre}"></c:out></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.CantMts3'/>
			</td>
			<td>
				<input name="fiscalizacion.cantidadMts" class="botonerab" type="text" size="27"
					   onkeypress="javascript:esNumericoConDecimal(event);">
			</td>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.TamañoMuestra'/>
			</td>
			<td>
				<input name="fiscalizacion.tamanioMuestra" class="botonerab" type="text" size="27" 
					   onkeypress="javascript:esNumerico(event);">
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<!-- LOCALIZACION -->
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="80%"
					cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message key='SIIF.subTitulo.Localizacion'/></td>
					</tr>
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.PlanManejoForestal'/>
						</td>
						<td>
							<select id="idPMF" class="botonerab" name="idPlanManejoForestal" disabled="disabled" 
									onchange="actualizarComboTranzon();">
								<option value="-1">- Seleccione -</option>						
							</select>					
						</td>
						<td width="25%"></td>
					</tr>				
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Tranzon'/>
						</td>
						<td>
							<select id="idTranzon" class="botonerab" name="idTranzon" disabled="disabled" 
									onchange="actualizarComboMarcacion();">
								<option value="-1">- Seleccione -</option>
							</select>					
						</td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Marcacion'/>
						</td>
						<td>
							<select id="idMarcacion" class="botonerab" name="idMarcacion" disabled="disabled" 
									onchange="actualizarComboRodal();">
								<option value="-1">- Seleccione -</option>
							</select>					
						</td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Rodal'/>
						</td>
						<td>
							<select id="idRodal" class="botonerab" name="idRodal" disabled="disabled">
								<option value="-1">- Seleccione -</option>						
							</select>					
						</td>
						<td width="25%"></td>
					</tr>															
					<tr>
						<td colspan="2" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>

		<!-- MUESTRAS -->
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message key='SIIF.subTitulo.Muestras'/></td>
					</tr>
					<tr>
						<td height="20" colspan="3"></td>
					</tr>
					<tr>
						<td width="35%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.CantidadMuestras'/>
						</td>
						<td>
							<input id="idCantMuestras" class="botonerab" type="text" 
										onkeypress="javascript:esNumerico(event);">
						</td>
						<td width="35%" align="left">
							<input class="botonerab" type="button" value="Agregar" onclick="agregarMuestras();">
							<input class="botonerab" type="button" value="Remover" onclick="removerMuestras();">
						</td>
					</tr>
					<tr>
						<td height="10" colspan="3"></td>
					</tr>
	
					<tr>
						<td colspan="3">
							<table id="tablaMuestras" border="0" class="cuadrado" align="center"
								width="70%" cellpadding="2" cellspacing="0" style="display: none">
								<tr>
									<td class="azulAjustado" width="3%"></td>
									<td class="azulAjustado" width="33%"><bean:message key='SIIF.label.Largo'/></td>
									<td class="azulAjustado" width="32%"><bean:message key='SIIF.label.Diametro1'/></td>
									<td class="azulAjustado" width="32%"><bean:message key='SIIF.label.Diametro2'/></td>
								</tr>
								<tr>
									<td colspan="4">
										<div id="tabla1"></div>									
									</td>
								</tr>													
							</table>																	
						</td>
					</tr>
					<tr>
						<td height="10" colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
	
	<div id="muestrasAux" style="display: none;"></div>
	
	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4"><input class="botonerab" type="button" value="Aceptar" id="enviar"
				onclick="javascript:submitir();"> 
				<input class="botonerab" type="button" value="Cancelar"
					   onclick="javascript:parent.location= contextRoot() + '/jsp.do?page=.index'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
</html:form>
