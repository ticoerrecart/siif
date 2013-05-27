<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<!-- <script type="text/javascript"
	src="<html:rewrite page='/js/guiaForestal.js'/>"></script> -->
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/AforoFachada.js'/>"></script>	
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>		
<script type="text/javascript"
	src="<html:rewrite page='/js/fiscalizacion.js'/>"></script>

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	

<script>
	$(function() {
		$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFecha" ).datepicker({ dateFormat: 'dd/mm/yy'});		
	});

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volverModificacionGuia(){	
	var entidad = $('#paramIdTipoDeEntidad').val();
	var productor = $('#paramProductor').val();
	var rodal = $('#idRodal').val();
	var idPeriodo = $("#idPeriodo").val();
	parent.location = contextRoot() +
	'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaModificacionGFB&idTipoDeEntidad=' + entidad +  '&idProductor=' + productor + '&idRodal=' + rodal + '&idPeriodo=' + idPeriodo;		
}

function submitir(){

	if(confirm("Esta seguro que desea modificar la Guía Forestal?")){
		validarFormLocal("guiaForestalForm","../guiaForestal","validarModificacionGuiaForestalBasicaForm","GuiaForestalForm");
	}	
}

function validarFormLocal(idFormJsp,action,metodo,actionForm){
	var form = $('#'+idFormJsp).serialize(); 
	var url = '../' + action + '.do?metodo=validar&validador=' + metodo + '&form=' + actionForm + '&formJsp=' + idFormJsp;
	preValidar();
	$.post(url,form,validarFormLocalCallBack);
}

function validarFormLocalCallBack(xmlDoc){
	var nodos = xmlDoc.getElementsByTagName('error');
	if (nodos.length==0){
		nodos = xmlDoc.getElementsByTagName('errorGuia');
		if (nodos.length==0){
			var nodos = xmlDoc.getElementsByTagName('formId');
	   		var idForm = nodos[0].firstChild.nodeValue;
    		$('#'+idForm).submit();
		}else{
			if(confirm(nodos[0].firstChild.nodeValue)){
				var nodos = xmlDoc.getElementsByTagName('formId');
    		   	var idForm = nodos[0].firstChild.nodeValue;
				$('#'+idForm).submit();
			}else{
				posValidar();
			}
		}
	} else {
		$('#errores').text("");
    	for(var i=0; i < nodos.length; i++) { 
	    	$('#errores').append( '<div>* ' + nodos[i].firstChild.nodeValue + '</div>');
    	}
 	 	posValidar();
	}
}

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

function reemplazarCaracter(caracAReemp, caracterNuevo, stringViejo){

	var stringNuevo = "";
	
	for(var i=0;i<stringViejo.length;i++){
		if(stringViejo.charAt(i) == caracAReemp){
			stringNuevo += caracterNuevo;	
		}else{
			stringNuevo += stringViejo.charAt(i);
		}	
	}

	return stringNuevo;
	
}

var indice = 2;										 
function agregarCuota(){
	var nom = $("#nombreProductor").val();
	
	var nombre = "";
	nombre = reemplazarCaracter(" ","%20",nom,nombre);
	
	$('#dummy').load('/SIIF/jsp/bloquePlanPagosAltaGFB.jsp?nombreProductor='+ nombre +'&indice=' + indice , 
			function(){
				//$("#prueba1").append($("#dummy").html()); NO ANDA ESTA LINEA EN IE
								
				/*document.getElementById("divPlanDePagos").innerHTML = document.getElementById("divPlanDePagos").innerHTML 
																+ document.getElementById("dummy").innerHTML*/  

				var i = "divPlanDePagos"+indice;																
				document.getElementById(i).innerHTML = document.getElementById("dummy").innerHTML;
				document.getElementById("dummy").innerHTML = "";

				var ind = indice-1;
				var indiceDate = "#datepicker"+ind;
				$( indiceDate ).datepicker({ dateFormat: 'dd/mm/yy'});
				
				document.getElementById("idBotonRemoverCuota").disabled="";
				indice++;													
			}								
	);	
}

function removerCuota(){

	indice--;
	document.getElementById("dummy").innerHTML = "";	
	
	/*var i = "#idTable"+indice;	
	$(i).remove();*/

	var i = "divPlanDePagos"+indice;
	document.getElementById(i).innerHTML = "";

	if(indice == 2){
		document.getElementById("idBotonRemoverCuota").disabled="disabled";
	}																			
}


								 
var fila0Vales ='<tr id="filaVales0">'+
	'<td width="2%" class="botoneralNegrita grisSubtitulo ind"> 1 </td>'+
	'<td width="10%" class="botoneralNegritaRight"> Desde </td>'+
	'<td width="40%" align="left"> <input name="rangos[0].desde" class="botonerab" type="text" size="25" onkeypress="javascript:esNumerico(event);"> </td>'+
	'<td width="10%" class="botoneralNegritaRight"> Hasta</td>'+
	'<td width="40%" align="left"> <input name="rangos[0].hasta" class="botonerab" type="text" size="25" onkeypress="javascript:esNumerico(event);"> </td>'+
	'</tr>';
				 
function agregarVale(){
	if($('#filaVales0').length==0){
		$("#tablaVales").append(fila0Vales);
		$("#fechaVencimientoVales").show();
		$("#idBotonRemoverVale").attr("disabled",false);
		
		if($('#fechaVencimientoPrimerVale').length>0){
			$("#datepickerVale0").val($("#fechaVencimientoPrimerVale").val());
		}
	}else{
		var j = $('#tablaVales tr:last .ind').text().trim();
		$("#tablaVales tr:last").clone().find("input").each(function() {
		$(this).attr({
			'name' : function(_, name) {
				return name.replace([j-1], [j]);
			},
			'value' : ''
			});
			}).end().appendTo("#tablaVales");
		
		$('#tablaVales tr:last .ind').text(1 + parseInt(j));
		var newId = $("#tablaVales tr:last").attr('id').replace(j - 1, j);
		$("#tablaVales tr:last").attr('id', newId);
		$("#idBotonRemoverVale").attr("disabled",false);
	}
}

function removerVale(){
	var j = $('#tablaVales tr:last .ind').text().trim();
	
	$('#tablaVales tr:last').remove();
	
	if(j <= 1){
		$("#idBotonRemoverVale").attr("disabled",true);
		$("#fechaVencimientoVales").hide();
	}
}

var idRenglon;
function cambiarEstado(ind){

	var estado = $('#idEstado'+ind).val();
	var idTipoProducto = $('#selectTiposDeProductos'+ind).val();
	//idTipoProducto = (idTipoProducto ==null)?$('#idTipoProducto'+ind).val():idTipoProducto;
	var idProdForestal = $('#idProdForestal').val();

	if(estado != ""){
		idRenglon = ind;	
		AforoFachada.getValor(estado,idTipoProducto,idProdForestal,actualizarImporteCallback );
	}
}

function actualizarImporteCallback(valor){
	
	if(valor!=null){
		$('#TDValorAforo'+idRenglon).show();
		$('#errorAforo'+idRenglon).hide();
		$('#idValorAforo'+idRenglon).val(valor);
		
	}
	else{
		$('#TDValorAforo'+idRenglon).hide();
		$('#idImporte'+idRenglon).val(0.0);
		$('#idValorAforo'+idRenglon).val(0.0);
		$('#errorAforo'+idRenglon).show();
		$('#idPorcentaje').val(0);
		$('#idTotal').val(0);
	}	
	actualizarImporte(idRenglon);
}

function actualizarImporte(ind){
	var cantidadMts = $('#idCantidadMts'+ind).val();
	var valorAforo = $('#idValorAforo'+ind).val();
	$('#idImporte'+ind).val(valorAforo*cantidadMts);

	calcularTotales();	
}

function calcularTotales(){

	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();
	var sumaImportes = 0;
	for ( var i = 0; i <= j; i++) {
		if($('#idImporte'+i) != null && $('#idImporte'+i).val() != null && $('#idImporte'+i).val() != ""){
			sumaImportes += parseFloat($('#idImporte'+i).val());  
		}	 
	}
			
	if(sumaImportes == 0){
		$('#idPorcentaje').val(0);
		$('#idTotal').val(0);
	}
	else{
		var porcentaje = parseFloat(sumaImportes*0.2);
		
		document.getElementById("idPorcentaje").value = new Number(porcentaje).toFixed(2);
		document.getElementById("idTotal").value = new Number(parseFloat(sumaImportes * 1.2)).toFixed(2);
	}	
}

var clase2;
function pintarFila(idTr){

	$('#tr'+idTr).attr("class", "seleccionado");	
}

function despintarFila(idTr){

	if(!$('#idCheck'+idTr).is(':checked')){
		if(idTr%2){
			clase2 = "par";		
		}else{
			clase2 = "";
		}	
		$('#tr'+idTr).attr("class", clase2);
	}		
}

//Funciones para agregar filas en el calculo de la deuda//

function agregarFila() {

	$('#idRemFila').attr('disabled',false);
	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();

	if(j==3){
		$('#idAgrFila').attr('disabled',true);	
	}

	var k = parseInt(j)+1;
	
	$("#tablaImportes tr[id*=fila]:last").clone().find("input,select,td").each(function() {
		$(this).attr(
			{'name' : function(_, name){
							if(name != null)
								return name.replace([ j ], [ k ]);
					  },
			'value' : '',
			'onchange' : function(_, name){
							if(name != null)
								return name.replace([ j ], [ k ]);
						  },
			'id' : function(_, name){
							if(name != null)
								return name.replace([ j ], [ k ]);
					  	  }
			}
		);
	}).end().appendTo("#tablaImportes");
		
	$('#tablaImportes tr[id*=fila]:last input.ind').val(k);

	$("#tablaImportes tr[id*=fila]:last").attr('id', "fila"+k);
}

function removerFila() {

	var cantSubImportes = (${fn:length(subImportes)}>0)?${fn:length(subImportes)}-1:0;
	
	$('#idAgrFila').attr('disabled',false);
	$('#tablaImportes tr:last').remove();
	calcularTotales();

	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();
	if(j==cantSubImportes){
		$('#idRemFila').attr('disabled',true);
	}
}

function expBoletaNro(){
	var idBoletaExp = $('#expBoleta').val();
	$('[id="idTrBoleta' + idBoletaExp +'"]').show();
}

function expBoletasPagas(){
	$('td.verdeExitoLeft').parents('[id^="idTrBoleta"]').show();
	$('td.rojoAdvertenciaLeft').parents('[id^="idTrBoleta"]').hide();
}

function expBoletasImpagas(){
	$('td.rojoAdvertenciaLeft').parents('[id^="idTrBoleta"]').show();
	$('td.verdeExitoLeft').parents('[id^="idTrBoleta"]').hide();	
}

function expValeNro(){
	var idValeExp = $('#expVale').val();
	$('[id="idTrVale' + idValeExp +'"]').show();
}

function expValesDevueltos(){
	$('td.verdeExitoLeft').parents('[id^="idTrVale"]').show();
	$('td.rojoAdvertenciaLeft').parents('[id^="idTrVale"]').hide();
}

function expValesNoDevueltos(){
	$('td.rojoAdvertenciaLeft').parents('[id^="idTrVale"]').show();
	$('td.verdeExitoLeft').parents('[id^="idTrVale"]').hide();	
}

function pintarFilaVale(idTd){

	$('#'+idTd).addClass("verdeSubtitulo");
	$('#'+idTd).removeClass("grisSubtitulo");
}

function despintarFilaVale(idTd){
	
	$('#'+idTd).addClass("grisSubtitulo");
	$('#'+idTd).removeClass("verdeSubtitulo");
}

function apagar(id){
	$(id).animate(
		{opacity:"0.3"},
		{duration:300}
	  );
}

function encender(id){
	$(id).animate(
		{opacity:"1"},
		{duration:300}
	  );
}

function removerValeEnUso(idVale,index){
	
	if($("#idBotonRemoverValeEnUso" + idVale).val()=="-"){
		apagar($('.apagar' + idVale));
		$("#idBotonRemoverValeEnUso" + idVale).val("+");
		$("#tituloVale" + idVale).addClass("tachado");
		$("#valesTransporte" + index).val(idVale);
	}else{
		encender($('.apagar' + idVale));
		$("#idBotonRemoverValeEnUso" + idVale).val("-");
		$("#tituloVale" + idVale).removeClass("tachado");
		$("#valesTransporte" + index).val("0");
	}
}
//-----------------------------------------------------//

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>
<input id="paramIdTipoDeEntidad" type="hidden" value="${guiaForestal.productorForestal.tipoEntidad}">
<input id="paramProductor" type="hidden" value="${guiaForestal.productorForestal.id}">

<div id="idGuia">
<html:form action="guiaForestal" styleId="guiaForestalForm">
	<html:hidden property="metodo" value="modificacionGuiaForestalBasica" />
	<input name="guiaForestal.id" type="hidden" value="${guiaForestal.id}">
	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIF.titulo.ModificacionGuia'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
	
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroDeGuia'/></td>
			<td width="30%" align="left">
				<input value="${guiaForestal.nroGuia}" name="guiaForestal.nroGuia" class="botonerab" size="40" onkeypress="javascript:esNumerico(event);">
			</td>
	
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Permisionario'/></td>
			<td align="left">
				<input id="nombreProductor" value="${guiaForestal.productorForestal.nombre}" 
					   class="botonerab" type="text" size="40" readonly="readonly">			
			</td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.FechaAlta'/></td>
			<td width="30%" align="left">
				
				<input type="text" readonly="readonly" value="<c:out value='${guiaForestal.fecha}'/>" class="botonerab">
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
			<td align="left">
				<select name="guiaForestal.periodoForestal" class="botonerab" id="idPeriodo">
						<c:forEach items="${periodos}" var="per">
							<c:choose>
								<c:when test="${guiaForestal.periodoForestal==per.periodo}">
									<option value="${per.periodo}" selected="selected">
										<c:out value="${per.periodo}"></c:out>
									</option>
								</c:when>
								<c:otherwise>
									<option value="${per.periodo}">
										<c:out value="${per.periodo}"></c:out>
									</option>
								</c:otherwise>
							</c:choose>
							
							
						</c:forEach>
					</select>	
			</td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ValidoHasta'/></td>
			<td width="30%" align="left">
				
				<input id="datepicker" type="text" value="<c:out value='${guiaForestal.fechaVencimiento}'/>" name="guiaForestal.fechaVencimiento" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>
			</td>
			
			<td width="30%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.DistanciaEstablecida'/>
			</td>
			<td align="left">
				<input value="${guiaForestal.distanciaAforoMovil}" name="guiaForestal.distanciaAforoMovil" class="botonerab" type="text" size="10" onkeypress="javascript:esNumerico(event);">
				<bean:message key='SIIF.label.km'/>
			</td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
			<td width="30%" align="left">
				<input value="${guiaForestal.localidad.nombre}" readonly="readonly" class="botonerab" size="40">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>

	<!-- LOCALIZACION -->
	<%@include file="bloqueLocalizacion.jspf" %>


	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>

		<!-- FISCALIZACIONES -->
		<tr>
			<td colspan="4" align="left">
				<div id="e0" style="DISPLAY: ">
					<label onclick="javascript:exp('0')"> 
						<img src="../../imagenes/expand.gif" border="0" /> 
						<U class="azulOpcion">
							<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>
						</U>
						<BR>
					</label>
				</div>
				<div id="c0" style="DISPLAY: none">
					<label onclick="javascript:col('0')"> 
						<img src="../../imagenes/collapse.gif" border="0" /> 
						<U class="azulOpcion">
							<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>
						</U>
						<BR>
					</label>
					<c:choose>
						<c:when test="${fn:length(guiaForestal.fiscalizaciones)>0}">	
							<br>
							<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
								<tr>
									<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.ProductorForestal'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.TipoDeProducto'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.CantMts3'/></td>
									<!-- <td class="azulAjustado"></td>-->
								</tr>							
								<%String clase=""; %>
								<c:forEach items="${guiaForestal.fiscalizaciones}" var="fiscalizacion" varStatus="i">
									<%clase=(clase.equals("")?"par":""); %>
									<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
										onmouseover="javascript:pintarFila(<c:out value='${i.count}'></c:out>);"
										onmouseout="javascript:despintarFila(<c:out value='${i.count}'></c:out>);">
										<html:hidden property="listaFiscalizaciones[${i.count-1}].id" value="${fiscalizacion.id}"/>									
										<html:hidden property="listaFiscalizaciones[${i.count-1}].tipoProducto.id" value="${fiscalizacion.tipoProducto.id}"/>
										<td class="botonerab">
											<c:out value="${fiscalizacion.fecha}"></c:out>
										</td>
										<td class="botonerab">
											<c:out value="${fiscalizacion.productorForestal.nombre}"></c:out>
										</td>
										<td class="botonerab">
											<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
										</td>	
										<td class="botonerab">
											<c:out value="${fiscalizacion.cantidadMts}"></c:out>
										</td>														
									</tr>
								</c:forEach>	
							</table>
							<br>				
						</c:when>
						<c:otherwise>
							<table border="0" class="cuadradoSinBorde" align="center" width="70%" cellpadding="2">
								<tr>
									<td class="botonerab">
										No se han seleccionado Fiscalizaciones
									</td>
								</tr>
							</table>													
						</c:otherwise>
					</c:choose>	
				</div>	
			</td>
		</tr>		
		<!-- FIN FISCALIZACIONES -->

		<%@include file="bloqueSubImportes.jspf" %>

		<!-- PLAN DE PAGO --> 
	
		<tr>
			<td colspan="4" align="left">
			<div id="e2" style="DISPLAY: ">
				<label onclick="javascript:exp('2')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion"><bean:message key='SIIF.subTitulo.PlanPagos'/></U><BR>
				</label>
			</div>
			<div id="c2" style="DISPLAY: none">
				<label onclick="javascript:col('2')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion"><bean:message key='SIIF.subTitulo.PlanPagos'/></U><BR>
				</label>
				<br>
				<table class="cuadrado" align="center" width="90%" cellpadding="2"> 
					<tr>
						<td colspan="4" class="azulAjustado"><bean:message key='SIIF.label.BoletasDeposito'/></td>
					</tr>				
					<tr>
						<td colspan="4"  class="azulAjustado">
									<input type="button" class="botonerab" onclick="expBoletasImpagas();" value="Expandir Boletas Impagas"> 
									<input type="button" class="botonerab" onclick="expBoletasPagas();" value="Expandir Boletas Pagas">
									<input type="button" class="botonerab" onclick="expBoletaNro();" value="Expandir Boleta Nro"> 
									<Input type="text" value="" id="expBoleta">
						</td>
					</tr>
					
					<tr>
						<td colspan="4">
							<c:choose>					
								<c:when test="${fn:length(guiaForestal.boletasDeposito)>0}">	
				
									<c:forEach items="${guiaForestal.boletasDeposito}" var="boletaDeposito" varStatus="index">
									
										<tr onclick="$('#idTrBoleta<c:out value='${boletaDeposito.numero}'/>').toggle();">								
											<td colspan="4" class="grisSubtitulo" id="tdBoleta<c:out value='${index.count}'></c:out>" 									
												onmouseover="javascript:pintarFilaVale('tdBoleta<c:out value='${index.count}'></c:out>');"
												onmouseout="javascript:despintarFilaVale('tdBoleta<c:out value='${index.count}'></c:out>');">
												Boleta de Deposito n° <c:out value="${boletaDeposito.numero}"></c:out>
											</td>
										</tr>						
									
										<tr id="idTrBoleta<c:out value='${boletaDeposito.numero}'></c:out>" style="display: none">
											<td colspan="4">								
												<input type="hidden" name="boletasDeposito[${index.index}].idBoleta" 
														value="<c:out value='${boletaDeposito.idBoleta}'></c:out>">
												<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
													<tr>
														<td height="5" colspan="5"></td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight">
															<bean:message key='SIIF.label.BoletaDeposito'/>
														</td>
														<td width="35%" align="left">
															<input value="${boletaDeposito.numero}" class="botonerab" type="text"
																   size="20" readonly="readonly">
														</td>
														<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Productor'/></td>
														<td width="40%" align="left" colspan="2">
															<input value="${boletaDeposito.guiaForestal.productorForestal.nombre}"
																   class="botonerab" type="text" size="40" readonly="readonly">
														</td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Concepto'/></td>
														<td colspan="4" align="left">
															<input value="${boletaDeposito.concepto}" class="botonerab" type="text" size="94"
															 	   readonly="readonly">
														</td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Area'/></td>
														<td colspan="4" align="left">
															<input value="${boletaDeposito.area}" class="botonerab" type="text" size="94"
																   readonly="readonly">
														</td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.EfecticoCheque'/></td>
														<td width="35%" align="left">
															<input value="${boletaDeposito.efectivoCheque}" class="botonerab"
																   type="text" size="20" readonly="readonly">
														</td>
														<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Monto$'/></td>
														<td width="40%" align="left" colspan="2">
															<input value="${boletaDeposito.monto}" class="botonerab" type="text"
																   size="20" readonly="readonly">
														</td>
													</tr>
													<tr>
														<td width="10%" class="botoneralNegritaRight">
															<bean:message key='SIIF.label.Fecha_Venc'/>
														</td>
														<td width="35%" align="left">
															<input type="text" readonly="readonly" class="botonerab" size="17"
																   value="<c:out value='${boletaDeposito.fechaVencimiento}'/>">
															<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
																 align="top" width='17' height='21'>		
														</td>
														<td width="15%" class="botoneralNegritaRight">
															<bean:message key='SIIF.label.FechaPago'/>
														</td>
														<td width="23%" align="left">
															<input " type="text" readonly="readonly" class="botonerab" size="17"
																   value="<c:out value='${boletaDeposito.fechaPago}'/>"
																   id="idFechaPago<c:out value='${boletaDeposito.idBoleta}'/>"
																   name="boletasDeposito[${index.index}].fechaPago" >
															<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
																 align="top" width='17' height='21'>																						
														</td>										
														<c:choose>
															<c:when test="${boletaDeposito.fechaPago ==null}">
																<td width="17%" class="rojoAdvertenciaLeft">
																	<bean:message key='SIIF.label.NOPAGADA'/>
																</td>		
															</c:when>
															<c:otherwise>
																<td width="17%" class="verdeExitoLeft">
																	<bean:message key='SIIF.label.PAGADA'/>
																	<script type="text/javascript">
																		var idBoleta = ${boletaDeposito.idBoleta};
																		$( "#idFechaPago"+idBoleta ).datepicker({ dateFormat: 'dd/mm/yy'});
																	</script>
																</td>																
															</c:otherwise>
														</c:choose>																						
													</tr>
													<tr>
														<td height="5" colspan="5"></td>
													</tr>
												</table>
											</td>	
										</tr>
										<tr>
											<td height="5" colspan="4"></td>
										</tr>										
									</c:forEach>	
								</c:when>
								<c:otherwise>
									<bean:message key='SIIF.error.NoExiBoletas'/>
								</c:otherwise>													
							</c:choose>									
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr> 

	<!-- VALES DE TRANSPORTE -->
	<tr>
		<td colspan="4" align="left">
		<div id="e3" style="DISPLAY: ">
			<label onclick="javascript:exp('3')"> 
				<img src="../../imagenes/expand.gif" border="0" /> 
				<U class="azulOpcion"><bean:message key='SIIF.subTitulo.ValesTransporte'/></U>
				<BR>
			</label>
		</div>
		<div id="c3" style="DISPLAY: none">
			<label onclick="javascript:col('3')"> 
				<img src="../../imagenes/collapse.gif" border="0" /> 
				<U class="azulOpcion"><bean:message key='SIIF.subTitulo.ValesTransporte'/></U>
				<BR>
			</label>
			<br>	
			<c:choose>					
				<c:when test="${fn:length(guiaForestal.valesTransporte)>0}">
					<table class="cuadrado" align="center" width="90%" cellpadding="2">
						<tr>
							<td colspan="4" class="azulAjustado"><bean:message key='SIIF.subTitulo.ValesTransporte'/></td>
						</tr>					
						<tr>
							<td colspan="4"  class="azulAjustado">
								<input type="button" class="botonerab" onclick="expValesNoDevueltos();" value="Expandir Vales En Uso"> 
								<input type="button" class="botonerab" onclick="expValesDevueltos();" value="Expandir Vales Devueltos">
								<input type="button" class="botonerab" onclick="expValeNro();" value="Expandir Vale Nro"> 
								<input type="text" value="" id="expVale">
								<input type="hidden" id="fechaVencimientoPrimerVale" value="${guiaForestal.valesTransporte[0].fechaVencimiento}">
							</td>
						</tr>
						<tr>
							<td height="10" colspan="4"></td>
						</tr>							
						<c:forEach items="${guiaForestal.valesTransporte}" var="valeTransporte" varStatus="index">
						
							<tr onclick="$('#idTrVale<c:out value='${valeTransporte.numero}'/>').toggle();">								
								<td colspan="4" class="grisSubtitulo" id="tdVale<c:out value='${index.count}'></c:out>" 									
									onmouseover="javascript:pintarFilaVale('tdVale<c:out value='${index.count}'></c:out>');"
									onmouseout="javascript:despintarFilaVale('tdVale<c:out value='${index.count}'></c:out>');">
									<div id="tituloVale${valeTransporte.id}"><bean:message key='SIIF.label.ValeTransporteNro'/><c:out value="${valeTransporte.numero}"></c:out></div>
								</td>
							</tr>						
							<tr id="idTrVale<c:out value='${valeTransporte.numero}'/>" style="display: none" >
								<td colspan="4" width="90%">
									<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">																												
										<tr class="apagar${valeTransporte.id}">
											<td height="5" colspan="4"></td>
										</tr>
										<tr class="apagar${valeTransporte.id}">
											<td width="20%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.NumeroVale'/>
											</td>
											<td width="30%" align="left">
												<input value="${valeTransporte.numero}" class="botonerab" type="text"
													   size="30" readonly="readonly">
											</td>
											<td width="20%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.TransportadosPor'/>
											</td>
											<td width="30%" align="left">
												<input value="${valeTransporte.guiaForestal.productorForestal.nombre}"
													   class="botonerab" type="text" size="30" readonly="readonly">
											</td>
										</tr>
										<tr class="apagar${valeTransporte.id}">
											<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Origen'/></td>
											<td width="30%" align="left">
												<input value="${valeTransporte.origen}" class="botonerab" type="text"
													   size="30" readonly="readonly">
											</td>
											<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Destino'/></td>
											<td width="30%" align="left">
												<input value="${valeTransporte.destino}" class="botonerab"
													   type="text" size="30" readonly="readonly">
											</td>
										</tr>
										<tr class="apagar${valeTransporte.id}">
											<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Vehiculo'/></td>
											<td width="30%" align="left">
												<input value="${valeTransporte.vehiculo}" class="botonerab"
													   type="text" size="30" readonly="readonly">
											</td>
											<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Marca'/></td>
											<td width="30%" align="left">
												<input value="${valeTransporte.marca}" class="botonerab" type="text"
													   size="30" readonly="readonly">
											</td>
										</tr>
										
										
										<tr class="apagar${valeTransporte.id}">
											<td width="20%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.Fecha_Venc'/>
											</td>
											<td width="30%" align="left">
												<input type="text" readonly="readonly" class="botonerab"
													   value="<c:out value='${valeTransporte.fechaVencimiento}'/>"> 
												<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
													 align="top" width='23' height='21'>	
											</td>
											<td width="20%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.Dominio'/>
											</td>											
											<td width="30%" align="left">
												<input value="${valeTransporte.dominio}" class="botonerab"
													   type="text" size="8" readonly="readonly">																							
											</td>
										</tr>										
										
										
										<tr class="apagar${valeTransporte.id}">
											<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha_Dev'/></td>
											<td width="30%" align="left">
												<input type="text" readonly="readonly" class="botonerab"
													   value="<c:out value='${valeTransporte.fechaDevolucion}'/>"> 
												<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
													 align="top" width='23' height='21'>					
											</td>
											<td width="20%" class="botoneralNegritaRight">
												
											</td>
											<c:choose>
												<c:when test="${valeTransporte.fechaDevolucion ==null}">
													<input type="hidden" name="valesTransporte[${index.count}].id" id="valesTransporte${index.count}" value="0"/>
													
													<td width="30%" class="rojoAdvertenciaLeft">
														<bean:message key='SIIF.label.ENUSO'/>
													</td>
												</c:when>
												<c:otherwise>
													<td width="30%" class="verdeExitoLeft">
														<bean:message key='SIIF.label.DEVUELTO'/>
													</td>
												</c:otherwise>
											</c:choose>																						
										</tr>
										<tr class="apagar${valeTransporte.id}">
											<td height="5" colspan="4"></td>
										</tr>
										<tr class="apagar${valeTransporte.id}">
											<td colspan="4">
											<table class="cuadrado" align="center" width="80%"
												cellpadding="2">
												<tr>
													<td class="grisSubtitulo"><bean:message key='SIIF.label.Producto'/></td>
													<td class="grisSubtitulo"><bean:message key='SIIF.label.NroPiezas'/></td>
													<td class="grisSubtitulo"><bean:message key='SIIF.label.CantMts3'/></td>
													<td class="grisSubtitulo"><bean:message key='SIIF.label.Especie'/></td>
												</tr>
												<tr>
													<td>
														<input class="botonerab" type="text" value="${valeTransporte.producto}" readonly="readonly">
													</td>
													<td>
														<input class="botonerab" type="text" value="${valeTransporte.nroPiezas}" readonly="readonly">
													</td>
													<td>
														<input class="botonerab" type="text" value="${valeTransporte.cantidadMts}" readonly="readonly">
													</td>
													<td>
														<input class="botonerab" type="text" value="${valeTransporte.especie}" readonly="readonly">
													</td>
												</tr>
											</table>
											</td>
										</tr>
										
										<c:if test="${valeTransporte.fechaDevolucion ==null}">
											<tr>
												<td align="center" colspan="4">
													<input id="idBotonRemoverValeEnUso${valeTransporte.id}" type="button" value="-" onclick="javascript:removerValeEnUso(${valeTransporte.id},${index.count});">
												</td>
											</tr>
										</c:if>
										
										<tr class="apagar${valeTransporte.id}">
											<td height="5" colspan="4"></td>
										</tr>
									</table>
								</td>
							</tr>
							
							<tr>
								<td height="5" colspan="4"></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<table border="0" class="cuadradoSinBorde" align="center" width="70%" cellpadding="2">
						<tr>
							<td class="botonerab">
								<bean:message key='SIIF.error.NoExiVales'/>
							</td>
						</tr>
					</table>										
				</c:otherwise>													
			</c:choose>	
			
			<!-- AGREGAR VALES DE TRANSPORTE -->
			<table class="cuadrado" align="center" width="90%" cellpadding="2">
				<tr>
					<td colspan="4" align="left">
						<table class="cuadrado" align="center" width="90%" cellpadding="2">
							<tr>
								<td colspan="4" class="azulAjustado">Agregar <bean:message key='SIIF.subTitulo.ValesTransporte'/></td>
							</tr>				
							<tr>
								<td height="10" colspan="4"></td>
							</tr>
							<tr>
								<td colspan="4">
								<table border="0" class="cuadrado" align="center"  width="80%" cellpadding="2" id="tablaVales">
									
		
								</table>
		
		
		
								<table  class="cuadradoSinBorde" align="center" width="80%" cellpadding="2">
									<tr>
										<td height="10" colspan="4"></td>
									</tr>
									
									<tr id="fechaVencimientoVales" style="display: none;">
										<td width="25%" class="botoneralNegritaRight">
											<bean:message key='SIIF.label.Fecha_Venc'/>
										</td>
										<td width="40%" align="left">
											<input id="datepickerVale0" type="text" readonly="readonly" class="botonerab" 
													name='fechaVencimiento'>
											<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
												align="top" width='17' height='21'>		
																																												
											<script>
												$(function() {
													$( "#datepickerVale0" ).datepicker({ dateFormat: 'dd/mm/yy'});
												});
											</script>
										</td>
									</tr>	
									<tr>
										<td height="10" colspan="4"></td>
									</tr>
									
									<tr>
										<td colspan="4">
											<input id="idBotonAgregarVale" type="button" value="+" 
												onclick="javascript:agregarVale();">
											<input id="idBotonRemoverVale" disabled="disabled" type="button"
												value="-" onclick="javascript:removerVale();"></td>
									</tr>
									<tr>
										<td height="5" colspan="4"></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
		
					</td>
				</tr>
			</table>		
		
		
		
		
		
		
		</div>
		</td>
	</tr>
	
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		
	</table>
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4">
				<input type="button" value="Aceptar" id="enviar" 
					class="botonerab" onclick="javascript:submitir();" > 
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volverModificacionGuia();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>
</html:form>
</div>	 				  
<div id="idDivFiscalizacion" style="display: none;">

</div>