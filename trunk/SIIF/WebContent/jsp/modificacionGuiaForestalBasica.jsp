<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import= "ar.com.siif.negocio.Fiscalizacion" %> 
<%@ page import= "ar.com.siif.negocio.GuiaForestal" %> 

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
	
	var entidad = $('#paramIdTipoDeEntidad').val();
	var productor = $('#paramProductor').val();	
	parent.location = contextRoot() +  
	'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaModificacionGFB&idTipoDeEntidad=' + entidad + '&idProductor=' + productor;		
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

var indBoleta = 0;
function agregarBoleta(indiceBoleta){
	alert(indiceBoleta);
	alert(indBoleta);
	indiceBoleta = indiceBoleta + indBoleta;//Le sumo indice para q vaya aumentando pq siempre llamo a agregarBoleta con el mismo indiceBoleta
	indBoleta++;
	alert(indBoleta);
	alert(indiceBoleta);

	var nom = $("#nombreProductor").val();
	var nombre = "";
	nombre = reemplazarCaracter(" ","%20",nom,nombre);	

	$('#dummy').load('/SIIF/jsp/bloquePlanPagosModificacionGFB.jsp?nombreProductor='+ nombre +'&indiceBoleta=' + indiceBoleta , 
			function(){

				var i = "divBoletaDeposito"+indiceBoleta;																
				document.getElementById(i).innerHTML = document.getElementById("dummy").innerHTML;
				document.getElementById("dummy").innerHTML = "";

				var indiceDate = "#datepicker"+indiceBoleta;
				$( indiceDate ).datepicker({ dateFormat: 'dd/mm/yy'});													
			}								
	);	
}

function eliminarBoleta(indiceBoleta){

	if(confirm("¿Está seguro que desea eliminar la Boleta de Deposito?")){
		var idBoleta = "divBoletaDeposito"+indiceBoleta;
		document.getElementById(idBoleta).innerHTML = "";
	}

	/*var html = document.getElementById("idTablaBoletas").innerHTML; 
	var html2 = document.getElementById("idTrBoletaDeposito0").innerHTML;
	document.getElementById("idTablaBoletas").innerHTML = html+"<tr>"+html2+"</tr>";*/
}

function removerCuota(){

	indice--;
	document.getElementById("dummy").innerHTML = "";	

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

/*function cambiarEstado(){

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
}*/

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

<%
	GuiaForestal guia = (GuiaForestal)request.getAttribute("guiaForestal");
%>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>
<input id="paramIdTipoDeEntidad" type="hidden" value="${guiaForestal.fiscalizacion.productorForestal.idTipoEntidad}">
<input id="paramProductor" type="hidden" value="${guiaForestal.fiscalizacion.productorForestal.id}">

<html:form action="guiaForestal" styleId="guiaForestalForm">
	<html:hidden property="metodo" value="modificacionGuiaForestalBasica" />
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
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
				<input name="guiaForestal.nroGuia" value="${guiaForestal.nroGuia}"
				class="botonerab" type="text" size="40">
			</td>
			<html:hidden styleId="idFiscalizacion" property="idFiscalizacion" value="${guiaForestal.fiscalizacion.id}" />

			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Permisionario'/></td>
			<td align="left">
				<input id="nombreProductor" name="fiscalizacion.productorForestal.nombre" 
					value="${guiaForestal.fiscalizacion.productorForestal.nombre}" class="botonerab"
					type="text" size="40" readonly="readonly">			
			</td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ValidoHasta'/></td>
			<td width="30%" align="left">
				<input id="datepicker" type="text" name="fechaVencimiento" class="botonerab"
						value="<fmt:formatDate value='${guiaForestal.fechaVencimiento}' pattern='dd/MM/yyyy'/>">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>
			</td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
			<td align="left">
				<input name="guiaForestal.periodoForestal" value="${guiaForestal.fiscalizacion.periodoForestal}" 
					class="botonerab" type="text" size="40" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
			<td width="30%" align="left">
				<input value="${guiaForestal.localidad}" readonly="readonly" 
					   name="guiaForestal.localidad" class="botonerab" size="40">
					   
				<input value="<fmt:formatDate value='${guiaForestal.fecha}' pattern='dd/MM/yyyy'/>" 
						type="hidden" name="fecha">	
								   
			</td>
			<td width="30%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.DistanciaEstablecida'/>
			</td>
			<td align="left" class="botoneraNegritaLeft">
				<input name="guiaForestal.distanciaAforoMovil" value="${guiaForestal.distanciaAforoMovil}"
					class="botonerab" type="text" size="10"	onkeypress="javascript:esNumerico(event);">
					<bean:message key='SIIF.label.km'/>
			</td>				
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>

	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PlanManejoForestal'/></td>
			<td width="30%" align="left">
				<input value="${guiaForestal.fiscalizacion.rodal.marcacion.tranzon.pmf.nombre} - ${guiaForestal.fiscalizacion.rodal.marcacion.tranzon.pmf.expediente}" 
						class="botonerab" type="text" size="40" readonly="readonly">
			</td>
			<td width="30%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.Tranzon'/>
			</td>
			<td align="left">
				<input value="${guiaForestal.fiscalizacion.rodal.marcacion.tranzon.numero} - ${guiaForestal.fiscalizacion.rodal.marcacion.tranzon.disposicion}" 
						class="botonerab" type="text" size="40" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Marcacion'/></td>
			<td width="30%" align="left">
				<input value="${guiaForestal.fiscalizacion.rodal.marcacion.disposicion}" 
						class="botonerab" type="text" size="40" readonly="readonly">
			</td>
			<td width="30%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.Rodal'/>
			</td>
			<td align="left">
				<input value="${guiaForestal.fiscalizacion.rodal.nombre}"
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

			<div id="e1" style="DISPLAY: ">
				<label onclick="javascript:exp('1')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion">
						<bean:message key='SIIF.subTitulo.ProductosForestales'/>
					</U>
					<BR>
				</label>
			</div>
			<div id="c1" style="DISPLAY: none">
				<label onclick="javascript:col('1')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion">
						<bean:message key='SIIF.subTitulo.ProductosForestales'/>
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
						cellpadding="2" cellspacing="0">
						<tr>
							<td class="azulAjustado"><bean:message key='SIIF.label.Tipo'/></td>
							<td class="azulAjustado"><bean:message key='SIIF.label.Estado'/></td>
							<td class="azulAjustado"><bean:message key='SIIF.label.Especie'/></td>
							<td class="azulAjustado"><bean:message key='SIIF.label.M3'/></td>
							<td class="azulAjustado"><bean:message key='SIIF.label.Unidad'/></td>
							<td class="azulAjustado"><bean:message key='SIIF.label.Importe'/></td>
						</tr>
						<tr>
							<td>
								<input class="botonerab" type="text" 
									value="<c:out value='${guiaForestal.fiscalizacion.tipoProducto.nombre}'/>"
									readonly="readonly">																							
							</td>
							<td>
								<input class="botonerab" type="text" name="guiaForestal.estado" 
									   value="${guiaForestal.estado}" readonly="readonly">							
							</td>
							<!-- <td>
								<select id="idEstado" class="botonerab" "<c:out value='${guiaForestal.estado}'/>" 
										name="guiaForestal.estado" onchange="cambiarEstado();">
										<c:choose>
											<c:when test="${guiaForestal.estado =='Seco'}">
												<option value="">-Seleccione un Estado-</option>								
												<option value="Seco" selected="selected">Seco</option>
												<option value="Verde">Verde</option>											
											</c:when>
											<c:when test="${guiaForestal.estado =='Verde'}">
												<option value="">-Seleccione un Estado-</option>								
												<option value="Seco">Seco</option>
												<option value="Verde" selected="selected">Verde</option>											
											</c:when>									
											<c:otherwise>
												<option value="">-Seleccione un Estado-</option>								
												<option value="Seco">Seco</option>
												<option value="Verde">Verde</option>
											</c:otherwise>		
										</c:choose>
								</select>								
							</td>-->
							<td>
								<input class="botonerab" type="text" name="guiaForestal.especie" 
										value="<c:out value='${guiaForestal.especie}'/>">
							</td>
							<td>
								<input id="idCantidadMts" class="botonerab" type="text"
									value="<c:out value='${guiaForestal.fiscalizacion.cantidadMts}'/>"
									readonly="readonly"
									onkeypress="javascript:esNumericoConDecimal(event);">
							</td>
							<td>
								<input class="botonerab" type="text"
									value="<c:out value='${guiaForestal.fiscalizacion.cantidadUnidades}'/>"
									readonly="readonly"
									onkeypress="javascript:esNumerico(event);">
							</td>
							<td>
								<input id="idImporte" class="botonerab" type="text"
									name="guiaForestal.importe" value='<c:out value="${guiaForestal.importe}"></c:out>'
									onkeypress="javascript:esNumericoConDecimal(event);" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
							<td colspan="2"><bean:message key='SIIF.label.DerechoInspFisca'/></td>
							<td>
								<input id="idPorcentaje" name="guiaForestal.inspFiscalizacion" readonly="readonly"
									   value='<c:out value="${guiaForestal.inspFiscalizacion}"></c:out>'
									   class="botonerab" type="text">
							</td>
						</tr>
						<tr>
							<td colspan="4"></td>
							<td class="botoneralNegrita"><bean:message key='SIIF.label.TOTAL'/></td>
							<td>
								<input id="idTotal" readonly="readonly"	class="botonerab" type="text"
										 	value="<%= guia.getImporteTotal()%>">
											<!--guia.getImporte() + guia.getInspFiscalizacion() -->
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="15" colspan="4"></td>
				</tr>
				<tr>
					<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ValorAforo'/></td>
					<td width="55%" align="left">
						<input id="idValorAforo" name="guiaForestal.valorAforos" value='<c:out value="${guiaForestal.valorAforos}"></c:out>'
							class="botonerab" type="text" size="70" readonly="readonly">
					</td>
					<td id="errorAforo" class="rojoAdvertenciaLeft" style="display: none;">
						<bean:message key='SIIF.error.NoExiValorAforo'/> 
					</td>
					<td></td>
				</tr>
				<tr>
					<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Observaciones'/></td>
					<td align="left" colspan="3">
						<textarea name="guiaForestal.observaciones" class="botonerab" cols="130" rows="3">
							<c:out value="${guiaForestal.observaciones}"></c:out>
						</textarea>
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
					<U class="azulOpcion"><bean:message key='SIIF.subTitulo.PlanPagos'/></U><BR>
				</label>
				<br>
				<div id="idBoletas">
					<table id="idTablaBoletas" border="0" class="cuadrado" align="center" width="90%" cellpadding="2"> 
						<tr>
							<td colspan="3" class="azulAjustado"><bean:message key='SIIF.label.BoletasDeposito'/></td>
						</tr>				
						<tr>
							<td height="10" colspan="3"></td>
						</tr>
						<c:choose>					 
						<c:when test="${fn:length(guiaForestal.boletasDeposito)>0}">
							
							<c:forEach items="${guiaForestal.boletasDeposito}" var="boletaDeposito" varStatus="index">
								<c:choose>
									<c:when test="${boletaDeposito.fechaPago ==null}">
										<c:set var="readonly" value=""></c:set>				
									</c:when>
									<c:otherwise>
										<c:set var="readonly" value="readonly='readonly'"></c:set>	
									</c:otherwise>
								</c:choose>								
								<tr id="idTrBoletaDeposito${index.index}">
									<td colspan="3">
										<div id="divBoletaDeposito${index.index}">	
											<table border="0" class="cuadradoSinBorde" align="right" cellpadding="2">									
												<tr>
													<td width="90%">													
														<table border="0" class="cuadrado" align="right" width="100%" cellpadding="2">
															<tr>
																<td colspan="5" class="grisSubtitulo">
																	<bean:message key='SIIF.label.CuotaNro'/><c:out value="${index.index+1}"></c:out>
																</td>
															</tr>
															<tr>
																<td height="5" colspan="5"></td>
															</tr>
															<tr>											
																<td width="10%" class="botoneralNegritaRight">
																	<bean:message key='SIIF.label.NumeroCuota'/>
																	
																</td>
																<td width="40%" align="left">
																	<input value="${boletaDeposito.numero}" class="botonerab" type="text"
																		   name="boletasDeposito[${index.index}].numero" size="20"
																		   <c:out value="${readonly}"></c:out> 
																		   onkeypress="javascript:esNumerico(event);">											
																</td>											
																<td width="10%" class="botoneralNegritaRight">
																	<bean:message key='SIIF.label.Productor'/>
																</td>
																<td width="40%" align="left" colspan="2">
																	<input value="${boletaDeposito.guiaForestal.fiscalizacion.productorForestal.nombre}"
																			readonly="readonly" class="botonerab" type="text" size="40">
																</td>
															</tr>
															<tr>
																<td width="10%" class="botoneralNegritaRight">
																	<bean:message key='SIIF.label.Concepto'/>
																</td>
																<td colspan="4" align="left">
																	<input value="${boletaDeposito.concepto}" class="botonerab" type="text" size="94"
																	 	   name="boletasDeposito[${index.index}].concepto" 
																		   <c:out value="${readonly}"></c:out>>
																</td>
															</tr>
															<tr>
																<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Area'/></td>
																<td colspan="4" align="left">
																		<input value="${boletaDeposito.area}" class="botonerab" type="text" size="94"
																			   name="boletasDeposito[${index.index}].area" 
																			   <c:out value="${readonly}"></c:out>>
																</td>
															</tr>
															<tr>
																<td width="10%" class="botoneralNegritaRight">
																	<bean:message key='SIIF.label.EfecticoCheque'/>
																</td>
																<td width="40%" align="left">
																		<input value="${boletaDeposito.efectivoCheque}" class="botonerab"
																			   name="boletasDeposito[${index.index}].efectivoCheque" 
																			   type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);"
																			   <c:out value="${readonly}"></c:out>>											
																<td width="10%" class="botoneralNegritaRight">
																	<bean:message key='SIIF.label.Monto$'/>
																</td>
																<td width="40%" align="left" colspan="2">
																		<input value="${boletaDeposito.monto}" class="botonerab" type="text"
																			   name="boletasDeposito[${index.index}].monto" 
																			   size="20" <c:out value="${readonly}"></c:out>
																			   onkeypress="javascript:esNumericoConDecimal(event);">					
																</td>
															</tr>
															<tr>
																<td width="10%" class="botoneralNegritaRight">
																	<bean:message key='SIIF.label.Fecha_Venc'/>
																</td>
																<td width="40%" align="left">
																	<input id="datepicker${index.index}" type="text"  
																		class="botonerab" size="17" readonly="readonly"
																		value="<fmt:formatDate value='${boletaDeposito.fechaVencimiento}' pattern='dd/MM/yyyy'/>"
																		name='boletasDeposito[${index.index}].fechaVencimientoTransient'>											
																	<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
																		align="top" width='17' height='21'>		
																													
																	<script>
																		$(function() {
																			var indiceDate = "#datepicker"+${index.index};
																			$(indiceDate).datepicker({ dateFormat: 'dd/mm/yy'});
																		});
																	</script>																						
																</td>
																<td width="10%" class="botoneralNegritaRight">
																	<bean:message key='SIIF.label.FechaPago'/>
																</td>
																<td width="23%" align="left">
																	<input id="idFechaPago<c:out value='${boletaDeposito.id}'></c:out>"
																		   type="text" readonly="readonly" class="botonerab" size="17"
																		   value="<fmt:formatDate value='${boletaDeposito.fechaPago}' pattern='dd/MM/yyyy' />"
																		   name='boletasDeposito[${index.index}].fechaPagoTransient'>
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
																		</td>
																	</c:otherwise>
																</c:choose>												
															</tr>
															<tr>
																<td height="5" colspan="4"></td>
															</tr>
														</table>
													</td>
													<td width="10%">
														<c:choose>
															<c:when test="${boletaDeposito.fechaPago ==null}">
																<input id="idBotonPago<c:out value='${boletaDeposito.id}'></c:out>"
																		type="button" value="Eliminar" class="botonerab" 
																		onclick="eliminarBoleta(${index.index});">														
															</c:when>
															<c:otherwise>
																<input type="button" value="Eliminar" class="botonerab" disabled="disabled">
															</c:otherwise>
														</c:choose>										
													</td>
												</tr>
											</table>													
										</div>											
									</td>
								</tr>
								<tr>
									<td height="5" colspan="3"></td>
								</tr>		
								<c:set var="indiceBoleta" value="${index.index + 1}"></c:set>				
							</c:forEach>
								<tr>
									<td colspan="3">
										<div id="dummy" style="display: none"></div>
										<div id="divBoletaDeposito${indiceBoleta}"></div>								
									</td>
								</tr>
								<tr>
									<td height="5" colspan="3"></td>
								</tr>
								<tr>
									<td colspan="3">
										<input type="button" value="Agregar Boleta" class="botonerab" 
											   onclick="agregarBoleta(${indiceBoleta});">								
									</td>
								</tr>							
								<tr>
									<td height="10" colspan="3"></td>
								</tr>						
						</c:when>
						<c:otherwise>
							<bean:message key='SIIF.error.NoExiBoletas'/>
						</c:otherwise>													
						</c:choose>									
					</table>
				</div>
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
						<table border="0" class="cuadrado" align="center"
							width="80%" cellpadding="2">

							<tr>
								<td colspan="4" class="grisSubtitulo">
									<bean:message key='SIIF.label.ValeTransporteNro'/>1
								</td>
							</tr>
							<tr>
								<td height="5" colspan="4"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.NumeroVale'/>
								</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].numero" class="botonerab" type="text"
									size="25" onkeypress="javascript:esNumerico(event);"></td>
								<td width="10%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.TransportadosPor'/>
								</td>
								<td width="40%" align="left">
									<input value="${fiscalizacion.productorForestal.nombre}"
										class="botonerab" type="text" size="40" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Origen'/>
								</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].origen" class="botonerab" type="text"
									size="25"></td>
								<td width="10%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Destino'/>
								</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].destino" class="botonerab"
									type="text" size="25"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Vehiculo'/>     
								</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].vehiculo" class="botonerab"
									type="text" size="25"></td>
								<td width="10%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Marca'/>
								</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].marca" class="botonerab" type="text"
									size="25"></td>
							</tr>
							<tr>
								<td width="10%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Dominio'/>
								</td>
								<td width="40%" align="left"><input
									name="valesTransporte[0].dominio" class="botonerab"
									type="text" size="7"></td>
								<td width="10%" class="botoneralNegritaRight">
									<bean:message key='SIIF.label.Fecha_Venc'/>
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
										<td class="grisSubtitulo"><bean:message key='SIIF.label.Producto'/></td>
										<td class="grisSubtitulo"><bean:message key='SIIF.label.NroPiezas'/></td>
										<td class="grisSubtitulo"><bean:message key='SIIF.label.CantMts3'/></td>
										<td class="grisSubtitulo"><bean:message key='SIIF.label.Especie'/></td>
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
	<!-- <table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
			<td width="30%" align="left"><input
				name="guiaForestal.localidad" class="botonerab" type="text"
				size="40"></td>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha'/></td>
			<td align="left">		
				<input id="datepickerFecha" type="text" name="fecha" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
					align="top" width='17' height='21'>				
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table> -->
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
					onclick='javascript:volver();'>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
</html:form>
