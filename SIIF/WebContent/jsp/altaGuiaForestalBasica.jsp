<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import= "ar.com.siif.negocio.Fiscalizacion" %> 

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
	src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>


<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	

<script>
	$(function() {

		$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#datepickerFecha" ).datepicker({ dateFormat: 'dd/mm/yy'});		
	});
</script>

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volver(){	
	//document.forms[0].elements["metodo"].value = "recuperarLocalidadesParaAltaGFB";
	//document.forms[0].submit();

	var localidad = $('#paramLocalidad').val();
	var productor = $('#paramProductor').val();	
	parent.location = contextRoot() +  
	'/guiaForestal.do?metodo=recuperarLocalidadesParaAltaGFB&idLocalidad=' + localidad + '&idProductor=' + productor;		
}

function submitir(){

	validarForm("guiaForestalForm","../guiaForestal","validarGuiaForestalBasicaForm","GuiaForestalForm");
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

var indiceVale = 2;										 
function agregarVale(){
	var nom = $("#nombreProductor").val();

	var nombre = "";

	nombre = reemplazarCaracter(" ","%20",nom,nombre);
	
	$('#dummyVales').load('/SIIF/jsp/bloqueValesTransporteAltaGFB.jsp?nombreProductor='+ nombre +'&indice=' + indiceVale , 
			function(){
				//$("#prueba1").append($("#dummy").html()); NO ANDA ESTA LINEA EN IE
								
				/*document.getElementById("divPlanVales").innerHTML = document.getElementById("divPlanVales").innerHTML 
																	+ document.getElementById("dummyVales").innerHTML*/

				var i = "divPlanVales"+indiceVale;																
				document.getElementById(i).innerHTML = document.getElementById("dummyVales").innerHTML;
				document.getElementById("dummyVales").innerHTML = "";

				var ind = indiceVale-1;
				var indiceDate = "#datepickerVale"+ind;											
				$( indiceDate ).datepicker({ dateFormat: 'dd/mm/yy'});
				
				document.getElementById("idBotonRemoverVale").disabled="";
				indiceVale++;				
			}								
	);
}

function removerVale(){

	indiceVale--;
	document.getElementById("dummyVales").innerHTML = "";	
	
	/*var i = "#idTableVale"+indiceVale;	
	$(i).remove();*/

	var i = "divPlanVales"+indiceVale;
	document.getElementById(i).innerHTML = "";	

	if(indiceVale == 2){
		document.getElementById("idBotonRemoverVale").disabled="disabled";
	}																			
}

function cambiarEstado(){

	var estado = $('#idEstado').val();
	var idFiscalizacion = $('#idFiscalizacion').val();	

	if(estado != ""){

		AforoFachada.getValor(idFiscalizacion,estado,actualizarImporteCallback );
	}
}

function actualizarImporteCallback(valor){
	
	if(valor!=null){
		$('#errorAforo').hide();
		$('#idValorAforo').val(valor);
		var cantidadMts = $('#idCantidadMts').val();
		$('#idImporte').val(valor*cantidadMts);
		$('#idImporte').focus();		
		$('#idValorAforo').focus();
	}
	else{
		$('#idImporte').val(0.0);
		$('#idValorAforo').val(0.0);
		$('#errorAforo').show();
		$('#idPorcentaje').val(0);
		$('#idTotal').val(0);
	}	
}

function calcularTotales(){

	var importe = $('#idImporte').val();
			
	if(importe == null || importe == ""){
		$('#idPorcentaje').val(0);
		$('#idTotal').val(0);
	}
	else{
		var importeFloat = parseFloat(importe);
		var porcentaje = parseFloat(importe*0.2);
		
		document.getElementById("idPorcentaje").value = new Number(porcentaje).toFixed(2);
		document.getElementById("idTotal").value = new Number(parseFloat(importe) + parseFloat(porcentaje)).toFixed(2);
	}	
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>
<input id="paramLocalidad" type="hidden" value="${fiscalizacion.productorForestal.localidad.id}">
<input id="paramProductor" type="hidden" value="${fiscalizacion.productorForestal.id}">

<html:form action="guiaForestal" styleId="guiaForestalForm">
	<html:hidden property="metodo" value="altaGuiaForestalBasica" />
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">Alta de Guía Forestal
			Básica</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight">Nro de Guía</td>
			<td width="30%" align="left">
				<input name="guiaForestal.nroGuia" 
				class="botonerab" type="text" size="40">
			</td>
			<html:hidden styleId="idFiscalizacion" property="idFiscalizacion" value="${fiscalizacion.id}" />

			<td width="30%" class="botoneralNegritaRight">Permisionario</td>
			<td align="left">
				<input id="nombreProductor" name="fiscalizacion.productorForestal.nombre" 
					value="${fiscalizacion.productorForestal.nombre}" class="botonerab"
					type="text" size="40" readonly="readonly">			
			</td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight">Valido Hasta</td>
			<td width="30%" align="left">
				<!-- <input name="fechaVencimiento" class="botonerab" type="text" size="16" readonly="readonly">
				<cal:cal propiedad="fechaVencimiento" formato="date11" name="fechaVencimiento" />-->
				<input id="datepicker" type="text" name="fechaVencimiento" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>
			</td>
			<td width="30%" class="botoneralNegritaRight">Período Forestal</td>
			<td align="left">
				<input name="guiaForestal.periodoForestal"
					value="${fiscalizacion.periodoForestal}" class="botonerab"
					type="text" size="40" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		
			<td width="30%" class="botoneralNegritaRight">
				Distancia establacida para la aplicación del aforo móvil
			</td>
			<td align="left">
				<input name="guiaForestal.distanciaAforoMovil"
					class="botonerab" type="text" size="10"
					onkeypress="javascript:esNumerico(event);">km
					
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
			<td width="12%" class="botoneralNegritaRight">Plan Manejo Forestal</td>
			<td width="30%" align="left">
				<input value="${fiscalizacion.rodal.marcacion.tranzon.pmf.nombre} - ${fiscalizacion.rodal.marcacion.tranzon.pmf.expediente}" 
						class="botonerab" type="text" size="40" readonly="readonly">
			</td>
			<td width="30%" class="botoneralNegritaRight">
				Tranzon
			</td>
			<td align="left">
				<input value="${fiscalizacion.rodal.marcacion.tranzon.numero} - ${fiscalizacion.rodal.marcacion.tranzon.disposicion}" 
						class="botonerab" type="text" size="40" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight">Marcacion</td>
			<td width="30%" align="left">
				<input value="${fiscalizacion.rodal.marcacion.disposicion}" 
						class="botonerab" type="text" size="40" readonly="readonly">
			</td>
			<td width="30%" class="botoneralNegritaRight">
				Rodal
			</td>
			<td align="left">
				<input value="${fiscalizacion.rodal.nombre}"
					   class="botonerab" type="text" size="40" readonly="readonly">
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

		<!-- PRODUCTOS FORESTALES -->
		<tr>
			<td colspan="4" align="left">

			<div id="e1" style="DISPLAY: "><label
				onclick="javascript:exp('1')"> <img
				src="../../imagenes/expand.gif" border="0" /> <U class="azulOpcion">Productos
			Forestales</U><BR>
			</label></div>
			<div id="c1" style="DISPLAY: none"><label
				onclick="javascript:col('1')"> <img
				src="../../imagenes/collapse.gif" border="0" /> <U
				class="azulOpcion">Productos Forestales</U><BR>
			</label>


			<table border="0" class="cuadradoSinBorde" align="center"
				width="100%" cellpadding="2">
				<tr>
					<td height="5" colspan="4"></td>
				</tr>
				<tr>
					<td align="left" colspan="4">La presente Guía Forestal Básica
					certifica la propiedad de la siguiente partida de productos
					forestales:</td>
				</tr>
				<tr>
					<td height="5" colspan="4"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table border="0" class="cuadrado" align="center" width="90%"
						cellpadding="2" cellspacing="0">
						<tr>
							<td class="azulAjustado">Tipo</td>
							<td class="azulAjustado">Estado</td>
							<td class="azulAjustado">Especie</td>
							<td class="azulAjustado">M³</td>
							<td class="azulAjustado">Unidad</td>
							<td class="azulAjustado">Importe</td>
						</tr>
						<tr>
							<td>
								<input class="botonerab" type="text" 
									value="<c:out value='${fiscalizacion.tipoProducto.nombre}'/>"
									readonly="readonly">																							
							</td>
							<td>
								<select id="idEstado" class="botonerab" name="guiaForestal.estado" onchange="cambiarEstado();">
												<option value="">-Seleccione un Estado-</option>								
												<option value="Seco">Seco</option>
												<option value="Verde">Verde</option>
								</select>								
							</td>
							<td>
								<input class="botonerab" type="text" name="guiaForestal.especie">
							</td>
							<td>
								<input id="idCantidadMts" class="botonerab" type="text"
									value="<c:out value='${fiscalizacion.cantidadMts}'/>"
									readonly="readonly"
									onkeypress="javascript:esNumericoConDecimal(event);">
							</td>
							<td>
								<input class="botonerab" type="text"
									value="<c:out value='${fiscalizacion.cantidadUnidades}'/>"
									readonly="readonly"
									onkeypress="javascript:esNumerico(event);">
							</td>
							<td>
								<input id="idImporte" class="botonerab" type="text"
									name="guiaForestal.importe"
									onkeypress="javascript:esNumericoConDecimal(event);"
									onblur="calcularTotales();">
							</td>
						</tr>
						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
							<td colspan="2">Derecho de Inspección y Fiscalización 20%</td>
							<td><input id="idPorcentaje"
								name="guiaForestal.inspFiscalizacion" readonly="readonly"
								class="botonerab" type="text"></td>
						</tr>
						<tr>
							<td colspan="4"></td>
							<td class="botoneralNegrita">TOTAL</td>
							<td><input id="idTotal" readonly="readonly"
								class="botonerab" type="text"></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="15" colspan="4"></td>
				</tr>
				<tr>
					<td width="12%" class="botoneralNegritaRight">Valor de Aforo</td>
					<td width="55%" align="left">
						<input id="idValorAforo" name="guiaForestal.valorAforos" 
							class="botonerab" type="text" size="70" readonly="readonly">
					</td>
					<td id="errorAforo" class="rojoAdvertenciaLeft" style="display: none;">
						No existe Valor de Aforo definido 
					</td>
					<td></td>
				</tr>
				<tr>
					<td width="12%" class="botoneralNegritaRight">Observaciones</td>
					<td align="left" colspan="3"><textarea
						name="guiaForestal.observaciones" class="botonerab" cols="130"
						rows="3"></textarea></td>
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
					<U class="azulOpcion">Plan de Pagos</U><BR>
				</label>
			</div>
			<div id="c2" style="DISPLAY: none">
				<label onclick="javascript:col('2')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion">Plan de Pagos</U><BR>
				</label>
				<br>
				<table class="cuadrado" align="center" width="90%" cellpadding="2"> 
					<tr>
						<td colspan="4" class="azulAjustado">Boletas de Deposito</td>
					</tr>				
	
					<tr>
						<td height="10" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4">
						<table border="0" class="cuadrado" align="center" width="80%"
							cellpadding="2">
							<tr>
								<td colspan="4" class="grisSubtitulo">Cuota n°1</td>
							</tr>
							<tr>
								<td height="5" colspan="4"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Número de
								Cuota</td>
								<td width="40%" align="left"><input
									name="boletasDeposito[0].numero" class="botonerab" type="text"
									size="20" onkeypress="javascript:esNumerico(event);"></td>
								<td width="10%" class="botoneralNegritaRight">Productor</td>
								<td width="40%" align="left"><input
									name="fiscalizacion.productorForestal.nombre"
									value="${fiscalizacion.productorForestal.nombre}"
									class="botonerab" type="text" size="40" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Concepto</td>
								<td colspan="3" align="left"><input
									name="boletasDeposito[0].concepto" class="botonerab"
									type="text" size="90"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Area</td>
								<td colspan="3" align="left"><input
									name="boletasDeposito[0].area" class="botonerab" type="text"
									size="90"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Efectico/Cheque:</td>
								<td width="40%" align="left"><input
									name="boletasDeposito[0].efectivoCheque" class="botonerab"
									type="text" size="20"
									onkeypress="javascript:esAlfaNumerico(event);"></td>
								<td width="10%" class="botoneralNegritaRight">Monto:</td>
								<td width="40%" align="left"><input
									name="boletasDeposito[0].monto" class="botonerab" type="text"
									size="20" onkeypress="javascript:esNumericoConDecimal(event);"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Fecha
								Vencimiento</td>
								<td colspan="3" align="left">
									<input id="datepicker0" type="text" readonly="readonly" class="botonerab" 
											name='boletasDeposito[0].fechaVencimientoTransient'>
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
	
	<!--  -->
	
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
					<U class="azulOpcion">Vales de Transporte</U>
					<BR>
				</label>
			</div>
			<div id="c3" style="DISPLAY: none">
				<label onclick="javascript:col('3')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion">Vales de Transporte</U>
					<BR>
				</label>
				<br>
				<table class="cuadrado" align="center" width="90%" cellpadding="2">
					<tr>
						<td colspan="4" class="azulAjustado">Vales de Transporte</td>
					</tr>				
					<tr>
						<td height="10" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4">
						<table border="0" class="cuadrado" align="center"
							width="80%" cellpadding="2">

							<tr>
								<td colspan="4" class="grisSubtitulo">Vale de Transporte
								n°1</td>
							</tr>
							<tr>
								<td height="5" colspan="4"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Número de
								Vale</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].numero" class="botonerab" type="text"
									size="25" onkeypress="javascript:esNumerico(event);"></td>
								<td width="10%" class="botoneralNegritaRight">
									Transportados por
								</td>
								<td width="40%" align="left">
									<input value="${fiscalizacion.productorForestal.nombre}"
										class="botonerab" type="text" size="40" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Origen</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].origen" class="botonerab" type="text"
									size="25"></td>
								<td width="10%" class="botoneralNegritaRight">Destino</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].destino" class="botonerab"
									type="text" size="25"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Vehiculo</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].vehiculo" class="botonerab"
									type="text" size="25"></td>
								<td width="10%" class="botoneralNegritaRight">Marca</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].marca" class="botonerab" type="text"
									size="25"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">Dominio</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].dominio" class="botonerab"
									type="text" size="7"></td>
								<td width="10%" class="botoneralNegritaRight">
									Fecha Vencimiento
								</td>
								<td width="40%" align="left">
									<input id="datepickerVale0" type="text" readonly="readonly" class="botonerab" 
											name='valesTransporte[0].fechaVencimientoTransient'>
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
								<td height="5" colspan="4"></td>
							</tr>
							<tr>
								<td colspan="4">
								<table class="cuadradoSinBorde" align="center" width="80%"
									cellpadding="2">
									<tr>
										<td class="grisSubtitulo">Producto</td>
										<td class="grisSubtitulo">N° de Piezas</td>
										<td class="grisSubtitulo">Cantidad m³</td>
										<td class="grisSubtitulo">Especie</td>
									</tr>
									<tr>
										<td><input class="botonerab" type="text"
											name="valesTransporte[0].producto"></td>
										<td><input class="botonerab" type="text"
											name="valesTransporte[0].nroPiezas"
											onkeypress="javascript:esNumerico(event);"></td>
										<td><input class="botonerab" type="text"
											name="valesTransporte[0].cantidadMts"
											onkeypress="javascript:esNumericoConDecimal(event);"></td>
										<td><input class="botonerab" type="text"
											name="valesTransporte[0].especie"></td>
									</tr>
								</table>
								</td>
							</tr>
	
							<tr>
								<td height="5" colspan="4"></td>
							</tr>
						</table>

	
						<div id="dummyVales" style="display: none"></div>
						<!-- <div id="divPlanVales"></div> -->
						<div id="divPlanVales2"></div>
						<script>
								var indice = 2;
								function cargar(){
									$('#dummy2').load('/SIIF/jsp/bloquePlanPagosAltaGFB.jsp?indice=' + indice, 
										function(){
											//$("#prueba1").append($("#dummy").html()); NO ANDA ESTA LINEA EN IE
											document.getElementById("divPlanDePagos").innerHTML = document.getElementById("divPlanDePagos").innerHTML + document.getElementById("dummy2").innerHTML  
											
											indice++;	
										}								
									);
								}
								
						</script>


						<table  class="cuadradoSinBorde" align="center" width="80%" cellpadding="2">
							<tr>
								<td height="5" colspan="4"></td>
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
			<td width="12%" class="botoneralNegritaRight">Localidad</td>
			<td width="30%" align="left"><input
				name="guiaForestal.localidad" class="botonerab" type="text"
				size="40"></td>
			<td width="30%" class="botoneralNegritaRight">Fecha</td>
			<td align="left">
				<!-- <input name="fecha" class="botonerab"
				type="text" size="16" readonly="readonly"> <cal:cal
				propiedad="fecha" formato="date11" name="fecha" />-->
				
				<input id="datepickerFecha" type="text" name="fecha" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>				
				
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
					class="botonerab" onclick="javascript:submitir();"> 
				<input type="button" class="botonerab" value="Volver" 
					onclick="javascript:volver();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
</html:form>
