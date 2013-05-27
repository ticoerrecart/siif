<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import= "ar.com.siif.dto.FiscalizacionDTO" %>
<%@ page import= "ar.com.siif.dto.GuiaForestalDTO" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" 
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">

<script type="text/javascript">

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

	$("#idGuiaForestal").hide();	
	$("#idDivFiscalizacion").load("../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&idFiscalizacion="+idFiscalizacion+"&strForward=exitoCargarFiscalizacionDesdeAltaGFB");
	$("#idDivFiscalizacion").show(); 
	$("#errores").hide();
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

	$('#'+idTd).attr("class", "verdeSubtitulo");	
}

function despintarFilaVale(idTd){
	
	$('#'+idTd).attr("class", "grisSubtitulo");		
}

function volverAltaGFB(){

	$("#idGuiaForestal").show();
	$("#idDivFiscalizacion").hide();
	$("#idDivFiscalizacion").empty();
	$("#errores").show();
}


function submitir(){
	if(confirm("Esta seguro que desea anular la Guía Forestal?")){
		validarForm("guiaForestalForm","../guiaForestal","validarAnulacionGuiaForestalForm","GuiaForestalForm");
	}	
}

function volverAnulacionGuia(){	
	//document.forms[0].elements["metodo"].value = "recuperarLocalidadesParaAltaGFB";
	//document.forms[0].submit();

	var entidad = $('#idParamIdTipoDeEntidad').val();
	var productor = $('#idParamProductor').val();
	var idPeriodo = $('#idPeriodo').val();
	parent.location = contextRoot() +
	'/guiaForestal.do?metodo=recuperarProductoresParaAnulacionDeGuia&forward=cargarGuiaForestalParaAnulacionDeGuia&forwardBuscarNroGuia=cargarGuiaForestalParaAnulacionDeGuiaPorNroGuia' + 
	'&idTipoDeEntidad=' + entidad +  '&idProductor=' + productor + '&idPeriodo='+ idPeriodo;
	
}

</script>

<%
	GuiaForestalDTO guia = (GuiaForestalDTO)request.getAttribute("guiaForestal");
%>

<html:form action="guiaForestal" styleId="guiaForestalForm">

<html:hidden property="metodo" value="anularGuiaForestal" />


<input type="hidden" name="guiaForestal.id" value="${guiaForestal.id}" />
<input id="idGuia" type="hidden" value="${guiaForestal.id}">
<input id="idParamForward" type="hidden" value="${paramForward}">
<input id="idParamProductor" type="hidden" value="${guiaForestal.productorForestal.id}">
<input id="idParamIdTipoDeEntidad" type="hidden" value="${guiaForestal.productorForestal.tipoEntidad}">



<div id="idGuiaForestal">
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td colspan="4" class="azulAjustado">
			Anular Guía Forestal Básica
		</td>
	</tr>
	<tr>
		<td height="20" colspan="4"></td>
	</tr>

	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroDeGuia'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.nroGuia}" readonly="readonly" class="botonerab" size="40">
		</td>

		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Permisionario'/></td>
		<td align="left">
			<input id="nombreProductor" value="${guiaForestal.productorForestal.nombre}" 
				   class="botonerab" type="text" size="40" readonly="readonly">			
		</td>
	</tr>

	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ValidoHasta'/></td>
		<td width="30%" align="left">
			
			<input type="text" value="<c:out value='${guiaForestal.fechaVencimiento}'/>" 
				readonly="readonly" class="botonerab">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
				align="top" width='17' height='21'>
		</td>
		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
		<td align="left">
			<input value="${guiaForestal.periodoForestal}" class="botonerab" type="text" 
				   size="40" readonly="readonly" id="idPeriodo">
		</td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.localidad.nombre}" readonly="readonly" class="botonerab" size="40">
		</td>
		<td width="30%" class="botoneralNegritaRight">
			<bean:message key='SIIF.label.DistanciaEstablecida'/>
		</td>
		<td align="left">
			<input value="${guiaForestal.distanciaAforoMovil}" readonly="readonly" 
				   class="botonerab" type="text" size="10"><bean:message key='SIIF.label.km'/>
		</td>				
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
</table>

<!-- LOCALIZACION -->
<%@include file="bloqueLocalizacion.jspf" %>

<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
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
								<td class="azulAjustado"></td>
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
									<td class="botonerab">
										<a href="javascript:mostrarFiscalizacion(<c:out value='${fiscalizacion.id}'></c:out>);">
											<bean:message key='SIIF.label.Ver'/>	
										</a>									
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
								<input type="text" value="" id="expBoleta">
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
														<input type="text" readonly="readonly" class="botonerab" size="17"
															   value="<c:out value='${boletaDeposito.fechaPago}'/>">
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
									<bean:message key='SIIF.label.ValeTransporteNro'/><c:out value="${valeTransporte.numero}"></c:out>
								</td>
							</tr>						
							<tr id="idTrVale<c:out value='${valeTransporte.numero}'/>" style="display: none" >
								<td colspan="4" width="90%">
									<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">																												
										<tr>
											<td height="5" colspan="4"></td>
										</tr>
										<tr>
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
										<tr>
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
										<tr>
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
										
										
										<tr>
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
										
										
										<tr>
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
										<tr>
											<td height="5" colspan="4"></td>
										</tr>
										<tr>
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
				
										<tr>
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
		</div>
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
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.FechaAlta'/></td>
		<td width="30%" align="left">				
			<input type="text" value="<c:out value='${guiaForestal.fecha}'/>" 
				readonly="readonly" class="botonerab">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" 
				width='17' height='21'>						
		</td>
		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Usuario'/></td>
		<td align="left">		
			<input value="${guiaForestal.operacionAlta.usuario.nombreUsuario}" class="botonerab" type="text" 
				size="25" readonly="readonly">				
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
		<td height="20" width="49%" align="right">
			<input type="button" class="botonerab" value="Anular"  onclick="javascript:submitir();" />
		</td>
		<td height="20" colspan="2">
		</td>
		<td height="20" width="49%" align="left">
			<input type="button" class="botonerab" value="Volver" onclick="javascript:volverAnulacionGuia();" />
		</td>
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr> 
</table>
</div>	 				  
<div id="idDivFiscalizacion" style="display: none;">
</div>

</html:form>