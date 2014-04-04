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

function volverAltaGuia(){	
	var entidad = $('#paramIdTipoDeEntidad').val();
	var productor = $('#paramProductor').val();
	var idPeriodo = $('#idPeriodo').val();
	parent.location = contextRoot() +
	'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaAltaGFB&idTipoDeEntidad=' + entidad +  '&idProductor=' + productor + '&idPeriodo=' + idPeriodo;		
}

function setValorLocalizacion(valor){
	$("#idLocalizacion").val(valor);
}

function submitir(){
	//bug del serialize de jquery con los checkbox.  No viaja el valor de los que están seleccionados.
	$("#guiaForestalForm").find(':checkbox:checked').attr('value', true);
	validarForm("guiaForestalForm","../guiaForestal","validarAltaGuiaForestalBasicaForm","GuiaForestalForm");
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
		var newId = $("#tablaVales tr:last").attr('id')
				.replace(j - 1, j);
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
function cambiarTipoDeAforoSegunEstado(ind){

	var tipoDeAforo = $('#tipoDeAforo').val();
	//alert(tipoDeAforo)
	var comercializaEnProvincia = $('#comercializaEnProvincia'+ind).is(':checked') ;
	//alert(comercializaEnProvincia)
	idRenglon = ind;
		
	AforoFachada.getValorAforoNuevo(tipoDeAforo, comercializaEnProvincia, 
		{callback : actualizarImporteCallback,
		 async : false}
	);
}


function cambiarTipoDeAforo(){

	var tipoDeAforo = $('#tipoDeAforo').val();
	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();
	//alert(j)
	for(var i=0; i<=j;i++){
		//alert("i:" + i);
		var comercializaEnProvincia = $('#comercializaEnProvincia'+i).is(':checked') ;
		//alert(comercializaEnProvincia)
		//visibilidad del campo Comercializacion dentro de Provincia.

		if($("#tipoDeAforo").val() == 'ESTRUCTURA_IRREGULAR' ||
			$("#tipoDeAforo").val() == 'MAT_CAIDO_O_TRAT_SILVIC_INCOMPL' ||
			$("#tipoDeAforo").val() == 'CLASIFICACION_DIAMETROS'){
			$("#comercializaEnProvinciaTD" + i).show();
		}else{
			$("#comercializaEnProvinciaTD" + i + " > input[name='listaSubImportes[" + i + "].comercializaDentroProvinciaStr']").attr("checked",false);
			$("#comercializaEnProvinciaTD" + i).hide();
		}

		cambiarTipoDeAforoSegunEstado(i);
		
	}
	
	if ($("#tipoDeAforo").val() == 'CLASIFICACION_DIAMETROS'){
		$("#trAfip").show();
	} else {
		$("#trAfip").hide();
		$("#afip").attr("checked",false);
		calcularTotales();
	}
	
	
	
	/*AforoFachada.getValor(tipoDeAforo, comercializaEnProvincia, 
		{callback : actualizarImporteCallback,
		 async : false}
	);*/
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
		$('#idValorAforo'+idRenglon).val(-1.0);
		$('#errorAforo'+idRenglon).show();
		$('#idPorcentaje').val(0);
		$('#idTotal').val(0);
	}
	
	actualizarImporte(idRenglon);
}

function calcularSubImporteRenglon(ind){
	
	var cantidadMts = $('#idCantidadMts'+ind).val();
	
	//Pregunto si es = a -1.0, pq cuando el aforo no esta definido le seteo ese valor.
	//Lo cambio a 0.0 pq sino las sumatorias pueden dar negativas.
	var valorAforo = ($('#idValorAforo'+ind).val() == -1.0)?0.0:$('#idValorAforo'+ind).val();
	
	var result = Math.round(valorAforo*cantidadMts*100)/100;
		
	$('#idImporte'+ind).val(result);	
}

function actualizarImporte(ind){

	calcularSubImporteRenglon(ind);
	calcularTotales();	
}

function calcularTotales(){

	var tipoTerreno = $('#tipoTerreno').val();
	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();
	var sumaImportes = 0;
	
	if(tipoTerreno == "Privado"){

		for ( var i = 0; i <= j; i++) {
			if($('#idImporte'+i) != null && $('#idImporte'+i).val() != null && $('#idImporte'+i).val() != ""){
				calcularSubImporteRenglon(i);
				sumaImportes += parseFloat($('#idImporte'+i).val());
				$('#idImporte'+i).val(0); 
			}	 
		}		
		var porcentaje = parseFloat(sumaImportes*0.2);
		$('#idPorcentaje').val(new Number(porcentaje).toFixed(2));		
		
		var compensacion = parseFloat($('#compXcaminos').val());
		if (isNaN(compensacion)){
			compensacion = 0;
		}
		
		var total = porcentaje - compensacion;
		
		if ($('#afip').is(':checked')) {
		 total = total * .9;	
		}
		
		$('#idTotal').val(new Number(parseFloat(total)).toFixed(2));		
	}
	else{	
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
			$('#idPorcentaje').val(new Number(porcentaje).toFixed(2));

			var compensacion = parseFloat($('#compXcaminos').val());
			if (isNaN(compensacion)){
				compensacion = 0;
			}
			var total = (sumaImportes * 1.2) - compensacion;
			
			if ($('#afip').is(':checked')) {
				 total = total * .9;	
			}
			
			$('#idTotal').val(new Number(parseFloat(total)).toFixed(2));
		}	
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

function mostrarFiscalizacion(idFiscalizacion){

	$("#idGuia").hide();	
	$("#idDivFiscalizacion").load("../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&idFiscalizacion="+idFiscalizacion+"&strForward=exitoCargarFiscalizacionDesdeAltaGFB");
	$("#idDivFiscalizacion").show(); 
	$("#errores").hide();
}

function volverAltaGFB(){

	$("#idGuia").show();
	$("#idDivFiscalizacion").hide();
	$("#idDivFiscalizacion").empty();
	$("#errores").show();
}

//Funciones para agregar filas en el calculo de la deuda//

function agregarFila() {

	$('#idRemFila').attr('disabled',false);
	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();

	if(j==8){
		$('#idAgrFila').attr('disabled',true);	
	}

	var k = parseInt(j)+1;
	
	$("#tablaImportes tr[id*=fila]:last").clone().find("input,select,td,span").each(function() {
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
	

	$("input[name='listaSubImportes[" + k + "].especie']").val("${defaultEspecie}");
	cambiarTipoDeAforoSegunEstado(k);
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

function actualizarTipoTerrenoPMF(){

	idPMF = $('#idPMF').val();
	if (idPMF > 0) {
		UbicacionFachada.getTipoTerrenoPMF(idPMF,
				actualizarTipoTerrenoCallback);  
	}
	$('#tipoTerreno').val("");		
}

function actualizarTipoTerrenoArea(){

	var idArea = $('#idArea').val();
	if (idArea > 0) {
		UbicacionFachada.getTipoTerrenoArea(idArea,
				actualizarTipoTerrenoCallback);
	}
	$('#tipoTerreno').val("");		
}

function actualizarTipoTerrenoCallback(tipoTerreno) {

	$('#tipoTerreno').val(tipoTerreno);

	//Vuelvo a poner los valores en los subImportes por si cambio a un PMF con terreno Fiscal.
	var j = $('#tablaImportes tr[id*=fila]:last input.ind').val();
	for ( var i = 0; i <= j; i++) {
		if($('#idImporte'+i) != null && $('#idImporte'+i).val() != null && $('#idImporte'+i).val() != ""){
			calcularSubImporteRenglon(i);
		}	 
	}
		
	calcularTotales();
}


function showCompensacion() {
	$('#usarCompensacion').hide();
	$('#compensacion').show();
	$('#compXcaminos').val($('#saldoXCaminos').val());
	calcularTotales();
	
}

//-----------------------------------------------------//

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>
<input id="paramIdTipoDeEntidad" type="hidden" value="${productorForestal.tipoEntidad}">
<input id="paramProductor" type="hidden" value="${productorForestal.id}">


<div id="idGuia">
<html:form action="guiaForestal" styleId="guiaForestalForm">
	<html:hidden property="metodo" value="altaGuiaForestalBasica" />
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIF.titulo.AltaGuia'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroDeGuia'/></td>
			<td width="30%" align="left">
				<input name="guiaForestal.nroGuia" class="botonerab" type="text" size="40" 
						onkeypress="javascript:esNumerico(event);">
			</td>
			<!--<html:hidden styleId="idFiscalizacion" property="guiaForestal.fiscalizacion.id" value="${fiscalizacion.id}" />-->

			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Permisionario'/></td>
			<td align="left">
				<input id="idProductor" type="hidden" name="guiaForestal.productorForestal.id" value="${productorForestal.id}">
				<input id="nombreProductor" value="${productorForestal.nombre}" class="botonerab" type="text" size="40" readonly="readonly">
			</td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ValidoHasta'/></td>
			<td width="30%" align="left">
				<input id="datepicker" type="text" name="guiaForestal.fechaVencimiento" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
			<td align="left">
					<input id="idPeriodo" value="${periodo}" name="guiaForestal.periodoForestal" class="botonerab" type="text" size="10" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		
			<td width="30%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.DistanciaEstablecida'/>
			</td>
			<td align="left" class="botoneraNegritaLeft">
				<input name="guiaForestal.distanciaAforoMovil" class="botonerab" type="text" size="10"
					onkeypress="javascript:esNumerico(event);">
					<bean:message key='SIIF.label.km'/>					
			</td>				
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>


	<!-- LOCALIZACION -->
	
	<html:hidden styleId="idLocalizacion" property="guiaForestal.idLocalizacion" value="${localizacion.id}" />
	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<c:choose>
				<c:when test="${localizacion.esAreaDeCosecha}">
					<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
						<tr>
							<td height="10" colspan="4"></td>
						</tr>
						<tr>
							<td width="12%" align="left" class="botoneralNegritaRight"><bean:message key='SIIF.label.AreaDeCosecha'/></td>
							<td width="30%" align="left">
								<input value="${localizacion.nombreArea}" class="botonerab" type="text" size="40" readonly="readonly">
							</td>
							<td width="30%" class="botoneralNegritaRight">
								<bean:message key='SIIF.label.TipoTerreno'/>
							</td>
							<td align="left">
								<input value="${localizacion.tipoTerreno}" id="tipoTerreno" name="tipoTerreno"
										class="botonerab" type="text" size="40" readonly="readonly">
							</td>
						</tr>

						<!-- TIPO DE AFORO -->
						<tr>
							<td width="25%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoDeAforo'/></td>
							<td  align="left" width="75%" colspan="3">
								<html:select styleId="tipoDeAforo" onchange="cambiarTipoDeAforo(${i.count-1});" property="guiaForestal.tipoDeAforoStr" styleClass="botonerab">
									<c:forEach items="${tiposDeAforo}" var="tipoDeAforo">
										<html:option value="${tipoDeAforo}">
											<c:out value="${tipoDeAforo.descripcion}"/>
										</html:option>
									</c:forEach>
								</html:select>
							</td>
						</tr>

						<tr>
							<td height="10" colspan="4"></td>
						</tr>						
					</table>
				</c:when>

				<c:otherwise>
					<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
						<tr>
							<td height="10" colspan="4"></td>
						</tr>
						<tr>
							<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PlanManejoForestal'/></td>
							<td width="30%" align="left">
								<input value="${localizacion.expedientePMF} - ${localizacion.nombrePMF}" 
										class="botonerab" type="text" size="40" readonly="readonly">
							</td>
							<td width="30%" class="botoneralNegritaRight">
								<bean:message key='SIIF.label.Tranzon'/>
							</td>
							<td align="left">
								<input value="${localizacion.numeroTranzon} - ${localizacion.disposicionTranzon}" 
										class="botonerab" type="text" size="40" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Marcacion'/></td>
							<td width="30%" align="left">
								<input value="${localizacion.disposicionMarcacion}" 
										class="botonerab" type="text" size="40" readonly="readonly">
							</td>
							<td width="30%" class="botoneralNegritaRight">
								<bean:message key='SIIF.label.Rodal'/>
							</td>
							<td align="left">
								<input value="${localizacion.nombreRodal}"
									   class="botonerab" type="text" size="40" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoTerreno'/></td>
							<td width="30%" align="left">
								<input value="${localizacion.tipoTerreno}" id="tipoTerreno" name="tipoTerreno"
										class="botonerab" type="text" size="40" readonly="readonly">
							</td>
							<td colspan="2">
							
							</td>
						</tr>

						<!-- TIPO DE AFORO -->
						<tr>
							<td width="25%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoDeAforo'/></td>
							<td  align="left" width="75%" colspan="3">
								<html:select styleId="tipoDeAforo" onchange="cambiarTipoDeAforo(${i.count-1});" property="guiaForestal.tipoDeAforoStr" styleClass="botonerab">
									<c:forEach items="${tiposDeAforo}" var="tipoDeAforo">
										<html:option value="${tipoDeAforo}">
											<c:out value="${tipoDeAforo.descripcion}"/>
										</html:option>
									</c:forEach>
								</html:select>
							</td>
						</tr>
				
						<tr>
							<td height="10" colspan="4"></td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			
		</c:when>
		<c:otherwise>
			<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
				<tr>
					<td height="10"></td>
				</tr>
				
				<!-- LOCALIZACION -->
				<tr>
					<td colspan="4" align="left">
						<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="0">
							<tr>
								<td colspan="3" class="grisSubtitulo"><bean:message key='SIIF.subTitulo.Localizacion'/></td>
							</tr>
							<tr>
								<td colspan="3" height="10"></td>
							</tr>			
							
							<tr>
								<td width="47%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.ZonaManejoForestal'/>
								</td>
								<td width="4%"></td>						
								<td align="left">
									<select id="idZMF" class="botonerab" 
											onchange="cambioComboZona();">
										<option value="0">--Seleccione una Opcion de Zona--</option>
										<option value="1">--PMF--</option>
										<option value="2">--Area de Cosecha--</option>
									</select>
								</td>
							</tr>			
		
							<tr class="area" style="display: none">
								<td width="47%"  class="botoneralNegritaRight"><bean:message key='SIIF.label.AreaDeCosecha'/></td>
								<td width="4%"></td>
								<td align="left"> 
									<select id="idArea" class="botonerab" name="fiscalizacionDTO.idArea" onchange="actualizarTipoTerrenoArea();setValorLocalizacion(this.value);">
										<option value="-1">- Seleccione -</option>
									</select>	
								</td>
							</tr>
								
							<tr class="plan" style="display: none">
								<td width="47%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.PlanManejoForestal'/>
								</td>
								<td width="4%"></td>			
								<td align="left">
									<select id="idPMF" class="botonerab" name="fiscalizacionDTO.idPlanManejoForestal" 	onchange="actualizarComboTranzon();actualizarTipoTerrenoPMF();setValorLocalizacion(this.value);">
										<option value="-1">- Seleccione -</option>						
									</select>					
								</td>						
							</tr>				
							
							<tr class="plan" style="display: none">
								<td width="47%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Tranzon'/>
								</td>
								<td width="4%"></td>						
								<td align="left">
									<select id="idTranzon" class="botonerab" name="fiscalizacionDTO.idTranzon" 	onchange="actualizarComboMarcacion();setValorLocalizacion(this.value);">
										<option value="-1">- Seleccione -</option>
									</select>					
								</td>
							</tr>
							
							<tr class="plan" style="display: none">
								<td width="47%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Marcacion'/>
								</td>
								<td width="4%"></td>
								<td align="left">
									<select id="idMarcacion" class="botonerab" name="fiscalizacionDTO.idMarcacion" 	onchange="actualizarComboRodal();setValorLocalizacion(this.value);">
										<option value="-1">- Seleccione -</option>
									</select>					
								</td>
							</tr>
							
							<tr class="plan" style="display: none">
								<td width="47%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Rodal'/>
								</td>
								<td width="4%"></td>
								<td align="left">
									<select id="idRodal" class="botonerab" name="fiscalizacionDTO.idRodal" onchange="setValorLocalizacion(this.value);">
										<option value="-1">- Seleccione -</option>						
									</select>					
								</td>
							</tr>															
							<tr>
								<td colspan="3" height="10"></td>
							</tr>				
						</table>
					</td>
				</tr>
							
											
							
				<tr>
					<td width="25%" class="botoneralNegritaRight">
						<bean:message key='SIIF.label.TipoTerreno'/>
					</td>
					<td align="left" width="75%">
						<input type="text" id="tipoTerreno" value="" name="tipoTerreno" readonly="readonly"
							class="botonerab" type="text" size="15">
					</td>
				</tr>
				
				<!-- TIPO DE AFORO -->
				<tr>
					<td width="25%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoDeAforo'/></td>
					<td  align="left" width="75%">
						<html:select styleId="tipoDeAforo" onchange="cambiarTipoDeAforo(${i.count-1});" property="guiaForestal.tipoDeAforoStr" styleClass="botonerab">
							<c:forEach items="${tiposDeAforo}" var="tipoDeAforo">
								<html:option value="${tipoDeAforo}">
									<c:out value="${tipoDeAforo.descripcion}"/>
								</html:option>
							</c:forEach>
						</html:select>
					</td>
				</tr>

				<tr>
					<td colspan="3" height="10"></td>
				</tr>				
			</table>									
		</c:otherwise>
	</c:choose>

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
						<c:when test="${fn:length(fiscalizaciones)>0}">	
							<br>
							<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
								<tr>
									<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.ProductorForestal'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.TipoDeProducto'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.CantMts3'/></td>
									<td class="azulAjustado"></td>
								</tr>							
								<%String clase=""; %>
								<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
									<%clase=(clase.equals("")?"par":""); %>
									<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
										onmouseover="javascript:pintarFila(<c:out value='${i.count}'></c:out>);"
										onmouseout="javascript:despintarFila(<c:out value='${i.count}'></c:out>);">
										<html:hidden property="listaFiscalizaciones[${i.count-1}].id" value="${fiscalizacion.id}"/>									
										<html:hidden property="listaFiscalizaciones[${i.count-1}].tipoProducto.id" value="${fiscalizacion.tipoProducto.id}"/>
										<html:hidden property="listaFiscalizaciones[${i.count-1}].tipoProducto.nombre" value="${fiscalizacion.tipoProducto.nombre}"/>
										<html:hidden property="listaFiscalizaciones[${i.count-1}].cantidadMts" value="${fiscalizacion.cantidadMts}"/>
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
										<td class="botonerab">
											<a href="javascript:mostrarFiscalizacion(<c:out value='${fiscalizacion.id}'></c:out>);">
												<bean:message key='SIIF.label.Ver'/>	</a>									
											<!-- <a href="../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&amp;idFiscalizacion=<c:out value='${fiscalizacion.id}'></c:out>&amp;strForward=exitoCargarFiscalizacionDesdeAltaGFB">
												<bean:message key='SIIF.label.Ver'/>
											</a> -->
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

		<!-- PRODUCTOS FORESTALES -->
		<tr>
			<td colspan="4" align="left">

			<div id="e1" style="DISPLAY: ">
				<label onclick="javascript:exp('1')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion">
						Sub Importes
					</U>
					<BR>
				</label>
			</div>
			<div id="c1" style="DISPLAY: none">
				<label onclick="javascript:col('1')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion">
						Sub Importes
					</U>
					<BR>
				</label>
			
				<table border="0" class="cuadradoSinBorde" align="center"
					width="100%" cellpadding="2">
					<tr>
						<td height="5" colspan="4"></td>
					</tr>
					<tr>
						<td align="left" colspan="4">
							<bean:message key='SIIF.label.CertificadoGuia'/>
						</td>
					</tr>
					<tr>
						<td height="5" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4">
							<table border="0" class="cuadrado" align="center" width="90%"
								cellpadding="2" cellspacing="0" id="tablaImportes" >
								<tr>
									<td class="azulAjustado" width="19%"><bean:message key='SIIF.label.Tipo'/></td>
									<td class="azulAjustado" width="35"><bean:message key='SIIF.label.Estado'/></td>
									<td class="azulAjustado" width="8%"><bean:message key='SIIF.label.Especie'/></td>
									<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.M3'/></td>
									<td class="azulAjustado" width="10%">Aforo</td>
									<td class="azulAjustado" width="18%"><bean:message key='SIIF.label.Importe'/></td>									
								</tr>
								<c:choose>
									<c:when test="${fn:length(subImportes)>0}">
										<c:forEach items="${subImportes}" var="subImporte" varStatus="i">	
											<tr id="fila${i.count-1}">
												<td>
													<input class="ind" type="hidden" value="${i.count-1}">		
																	
													<html:select styleId="selectTiposDeProductos${i.count-1}"
															property="listaSubImportes[${i.count-1}].tipoProducto.id" 
															styleClass="botonerab" value="${subImporte.tipoProducto.id}">
																																											
														<c:forEach items="${tiposProductosForestales}" var="tipoProducto">
															<html:option value="${tipoProducto.id}">
																<c:out value="${tipoProducto.nombre}"></c:out>
															</html:option>
														</c:forEach>
													</html:select>															
												</td>
												
												<td>
													<span style="display:none" id="comercializaEnProvinciaTD${i.count-1}">
														Comercializa dentro de Provincia <html:checkbox styleId="comercializaEnProvincia${i.count-1}" property="listaSubImportes[${i.count-1}].comercializaDentroProvinciaStr" value="true" onchange="javascript:cambiarTipoDeAforoSegunEstado(${i.count-1});"/>
													</span>
												</td>
												<td>
													<select name="listaSubImportes[${i.count-1}].especieStr" 
															class="botonerab">
														<c:forEach items="${especieProductoForestal}" var="especie">
															<option value="<c:out value='${especie.name}'></c:out>">
																<c:out value="${especie.descripcion}"></c:out>
															</option>
														</c:forEach>
													</select>															
												</td>
												<td>
													<input id="idCantidadMts${i.count-1}" class="botonerab" type="text" size="9" 
														name="listaSubImportes[${i.count-1}].cantidadMts" 
														onchange="javascript:actualizarImporte(${i.count-1});"
														onkeypress="javascript:esNumericoConDecimal(event);"
														value="${subImporte.cantidadMts}">									
												</td>
												<td id="TDValorAforo${i.count-1}">
													<input id="idValorAforo${i.count-1}" name="listaSubImportes[${i.count-1}].valorAforos" 
														class="botonerab" type="text" readonly="readonly" size="9">				
												</td>
												<td id="errorAforo${i.count-1}" class="rojoAdvertenciaLeft" style="display: none;">
													No Definido 
												</td>
												<td>
													<input id="idImporte${i.count-1}" class="botonerab" type="text" 
														name="listaSubImportes[${i.count-1}].importe" size="20" 
														readonly="readonly" onkeypress="javascript:esNumericoConDecimal(event);">
												</td>
											</tr>

										</c:forEach>
									</c:when>
									<c:otherwise>
	
	
											<tr id="fila0">
												<td>
													<input class="ind" type="hidden" value="0">										
													<select id="selectTiposDeProductos0" name="listaSubImportes[0].tipoProducto.id" 
															class="botonerab">
														<c:forEach items="${tiposProductosForestales}" var="tipoProducto" varStatus="i">
															<option value="<c:out value='${tipoProducto.id}'></c:out>">
																<c:out value="${tipoProducto.nombre}"></c:out>
															</option>
														</c:forEach>
													</select>												
												</td>
												<td>
													<span style="display:none" id="comercializaEnProvinciaTD0">
														Comercializa dentro de Provincia <html:checkbox styleId="comercializaEnProvincia0" property="listaSubImportes[0].comercializaDentroProvinciaStr" value="true" onchange="javascript:cambiarTipoDeAforoSegunEstado(0);"/>
													</span>																									
												</td>
												<td>											 
													<select name="listaSubImportes[0].especieStr" 
															class="botonerab">
														<c:forEach items="${especieProductoForestal}" var="especie">
															<option value="<c:out value='${especie.name}'></c:out>">
																<c:out value="${especie.descripcion}"></c:out>
															</option>
														</c:forEach>
													</select>
												</td>
												<td>
													<input id="idCantidadMts0" class="botonerab" type="text" size="9"
														name="listaSubImportes[0].cantidadMts" onchange="javascript:actualizarImporte(0);"
														onkeypress="javascript:esNumericoConDecimal(event);">		
												</td>
												<td id="TDValorAforo0">
													<input id="idValorAforo0" name="listaSubImportes[0].valorAforos" class="botonerab" 
														type="text" readonly="readonly" size="9">

												</td>
												<td id="errorAforo0" class="rojoAdvertenciaLeft" style="display: none;">
													No Definido 
												</td>
												<td>
													<input id="idImporte0" class="botonerab" type="text" name="listaSubImportes[0].importe" 
														readonly="readonly" onkeypress="javascript:esNumericoConDecimal(event);" size="20">
												</td>
											</tr>	
	
											<script>
												cambiarTipoDeAforoSegunEstado(0);
											</script>
								
									</c:otherwise>
								</c:choose>								
							</table>
							<table border="0" class="cuadrado" align="center" width="90%"
								cellpadding="2" cellspacing="0">				
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								
								<tr>
									<td align="right"width="82%">
										<bean:message key='SIIF.label.DerechoInspFisca'/>
									</td>
									<td width="18%">
										<input id="idPorcentaje" name="guiaForestal.inspFiscalizacion" readonly="readonly"
											class="botonerab" type="text">
									</td>
								</tr> 
						<c:if test="${entidad.saldoXCaminos gt 0}">		
								<tr id="usarCompensacion">
									<td align="right"width="82%">
										Desea usar Compensación por Construccion de Caminos de 2do Orden?
									</td>
									<td width="18%" align="left">
										<input class="botonerab" type="checkbox" onclick="showCompensacion();">
									</td>
								</tr> 
	
	
								<tr id="compensacion" style="display: none">
									<td align="right"width="82%">
										Compensación por Construccion de Caminos de 2do Orden
									</td>
									<td width="18%">
										<input type="hidden" id="saldoXCaminos" value="${entidad.saldoXCaminos}"/>
										<input id="compXcaminos" name="guiaForestal.compensacionCaminos" class="botonerab" type="text" value="0" onchange="calcularTotales();">
									</td>
								</tr> 
							</c:if>
							
								<tr id="trAfip" style="display: none">
									<td align="right"width="82%">
										Cumple F931 AFIP?
									</td>
									<td width="18%" align="left">
										<input id="afip" name="guiaForestal.f931AfipStr" class="botonerab" type="checkbox" onclick="calcularTotales();">
									</td>
								</tr> 
							
								<tr>
									<td class="botoneralNegritaRight"><bean:message key='SIIF.label.IMPORTE_TOTAL'/></td>
									<td>
										<input id="idTotal" readonly="readonly" name="guiaForestal.importeTotal" 
											class="botonerab" type="text">
									</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
							</table>
							
							<%--RECIEN ACA ESTAN CREADOS LOS CAMPOS DE porcentaje e importeTotal, asi que aca tengo que llamar a cambiarEstado, porq actualiza esos campos --%>
							<c:forEach items="${subImportes}" var="subImporte" varStatus="i">	
								<script>
									cambiarTipoDeAforoSegunEstado(${i.count-1});
								</script>
							</c:forEach>
						</td>					
					</tr>
					<tr>
						<td colspan="4">
							<input id="idAgrFila" type="button" value="+" onclick="agregarFila();">
							<input id="idRemFila" type="button" value="-" onclick="removerFila();" disabled="disabled">
						</td>
					</tr>					
					<tr>
						<td height="15" colspan="4"></td>
					</tr>
					<tr>
						<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Observaciones'/></td>
						<td align="left" colspan="3">
							<textarea name="guiaForestal.observaciones" class="botonerab" cols="130" rows="3"></textarea>
						</td>
					</tr>
					<tr>
						<td height="10" colspan="4"></td>
					</tr>
				</table>					
				</div>
			</td>
		</tr>

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
					<U class="azulOpcion"><bean:message key='SIIF.subTitulo.PlanPagos'/> </U><BR>
				</label>
				<br>
				<table class="cuadrado" align="center" width="90%" cellpadding="2"> 
					<tr>
						<td colspan="4" class="azulAjustado"><bean:message key='SIIF.label.BoletasDeposito'/></td>
					</tr>				
	
					<tr>
						<td height="10" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4">
							<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
								<tr>
									<td colspan="4" class="grisSubtitulo"><bean:message key='SIIF.label.CuotaNro'/>1</td>
								</tr>
								<tr>
									<td height="5" colspan="4"></td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIF.label.BoletaDeposito'/>
									</td>
									<td width="40%" align="left">
										<input name="boletasDeposito[0].numero" class="botonerab" type="text"
											size="20" onkeypress="javascript:esNumerico(event);">
									</td>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIF.label.Productor'/>
									</td>
									<td width="40%" align="left">
										<input value="${productorForestal.nombre}"
											class="botonerab" type="text" size="40" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIF.label.Concepto'/>
									</td>
									<td colspan="3" align="left">
										<input name="boletasDeposito[0].concepto" class="botonerab" type="text" size="90"
												value="Aforo">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIF.label.Area'/>
									</td>
									<td colspan="3" align="left">
										<input name="boletasDeposito[0].area" class="botonerab" type="text" size="90" value="Direccion General de Bosques">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIF.label.EfecticoCheque'/>
									</td>
									<td width="40%" align="left">
										<input name="boletasDeposito[0].efectivoCheque" class="botonerab" 
										type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);">
									</td>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIF.label.Monto$'/>
									</td>
									<td width="40%" align="left">
										<input name="boletasDeposito[0].monto" class="botonerab" type="text"
											size="20" onkeypress="javascript:esNumericoConDecimal(event);">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										<bean:message key='SIIF.label.Fecha_Venc'/>
									</td>
									<td colspan="3" align="left">
										<input id="datepicker0" type="text" readonly="readonly" class="botonerab" 
												name='boletasDeposito[0].fechaVencimiento'>
										<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
											align="top" width='17' height='21'>		
																					
										<script>
											$(function() {
										
												$( "#datepicker0" ).datepicker({ dateFormat: 'dd/mm/yy'});
											});
										</script>	
																																
									</td>
								</tr>
								<tr>
									<td height="5" colspan="4"></td>
								</tr>
		
							</table>								
		
							<div id="dummy" style="display: none"></div>
							<div id="divPlanDePagos2"></div>
							
							<table  class="cuadradoSinBorde" align="center" width="80%" cellpadding="2">
								<tr>
									<td height="5" colspan="4"></td>
								</tr>
								<tr>
									<td colspan="4">
										<input id="idBotonAgregarCuota" type="button" value="+" 
											onclick="javascript:agregarCuota();">
										<input id="idBotonRemoverCuota" disabled="disabled" type="button"
											value="-" onclick="javascript:removerCuota();"></td>
								</tr>
								<tr>
									<td height="5" colspan="4"></td>
								</tr>
							</table>
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
				<table class="cuadrado" align="center" width="90%" cellpadding="2">
					<tr>
						<td colspan="4" class="azulAjustado"><bean:message key='SIIF.subTitulo.ValesTransporte'/></td>
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
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
			<td width="30%" align="left">				
				<select id="idLocalidad" class="botonerab" name="guiaForestal.localidad.id">
					<option value="">- Seleccione una Localidad -</option>
					<c:forEach items="${localidades}" var="localidad">
						<option value="${localidad.id}">
							<c:out value="${localidad.nombre}"></c:out>
						</option>
					</c:forEach>
				</select>				
				
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha'/></td>
			<td align="left">		
				<input id="datepickerFecha" type="text" name="guiaForestal.fecha" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				
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
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volverAltaGuia();">
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