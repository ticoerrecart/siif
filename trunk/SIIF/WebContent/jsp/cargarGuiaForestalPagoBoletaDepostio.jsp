<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import= "ar.com.siif.dto.FiscalizacionDTO" %>
<%@ page import= "ar.com.siif.dto.GuiaForestalDTO" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/GuiaForestalFachada.js'/>"></script>	

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">


<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volver(){	
	var entidad = $('#paramIdTipoDeEntidad').val();
	var productor = $('#paramProductor').val();
	parent.location = contextRoot() +  
	'/guiaForestal.do?metodo=recuperarProductoresParaBoletasDeposito&forward=cargarGuiaForestalPagoBoletaDeposito'+ 
	'&forwardBuscarNroGuia=cargarGuiaForestalPagoBoletaDepositoPorNroGuia&idProductor=' + productor + '&idTipoDeEntidad=' + entidad;		
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

var idBoleta;
function registrarPago(boleta){

	if(confirm("¿Está seguro que desea registrar el pago?")){

		/*var id= "#idTr"+idBoleta;
		$(id).html("");
		$("#idBoletas").load('../../guiaForestal.do?metodo=registrarPagoBoletaDeposito&idBoleta='+idBoleta);*/

		idBoleta = boleta;
		GuiaForestalFachada.registrarPagoBoletaDeposito(idBoleta,registrarPagoCallback);		
	}		
}

function registrarPagoCallback(valor){
	
	var idEstado = "#idEstadoBoleta"+idBoleta;
	$(idEstado).html("PAGADA");	
	$(idEstado).attr('class', "verdeExitoLeft");
		
	var idBotonPago = "#idBotonPago"+idBoleta;
	$(idBotonPago).toggle();

	var idFechaPago = "#idFechaPago"+idBoleta;	
	$(idFechaPago).attr('value', valor);		
}

function expBoletaNro(){
	var idBoletaExp = $('#expBoleta').val();
	$('[id="idTr' + idBoletaExp +'"]').show();
}

function expBoletasPagas(){
	$('td.verdeExitoLeft').parents('[id^="idTr"]').show();
	$('td.rojoAdvertenciaLeft').parents('[id^="idTr"]').hide();
}

function expBoletasImpagas(){
	$('td.rojoAdvertenciaLeft').parents('[id^="idTr"]').show();
	$('td.verdeExitoLeft').parents('[id^="idTr"]').hide();	
}

function pintarFila(idTr){

	$('#tdBoleta'+idTr).attr("class", "verdeSubtitulo");	
}

function despintarFila(idTr){

	$('#tdBoleta'+idTr).attr("class", "grisSubtitulo");
		
}

</script>

<%
	GuiaForestalDTO guia = (GuiaForestalDTO)request.getAttribute("guiaForestal");
%>

<input id="paramIdTipoDeEntidad" type="hidden" value="${guiaForestal.productorForestal.tipoEntidad}">
<input id="paramProductor" type="hidden" value="${guiaForestal.productorForestal.id}">
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td colspan="4" class="azulAjustado">
			<bean:message key='SIIF.titulo.RegPagoBoleta'/>
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
			<input type="text" value="${guiaForestal.fechaVencimiento}" 
				readonly="readonly" class="botonerab">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
				align="top" width='17' height='21'>
		</td>
		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
		<td align="left">
			<input value="${guiaForestal.periodoForestal}" class="botonerab" type="text" size="40" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.productorForestal.localidad.nombre}" readonly="readonly" class="botonerab" size="40">
		</td>
	
		<td width="30%" class="botoneralNegritaRight">
			<bean:message key='SIIF.label.DistanciaEstablecida'/>
		</td>
		<td align="left">
			<input value="${guiaForestal.distanciaAforoMovil}" readonly="readonly" class="botonerab" type="text" size="10">km
		</td>				
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
</table>

<!-- LOCALIZACION -->
<table border="0" class="cuadrado" align="center" width="80%"
	cellpadding="2">
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PlanManejoForestal'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.rodal.marcacion.tranzon.pmf.nombre} - ${guiaForestal.rodal.marcacion.tranzon.pmf.expediente}" 
					class="botonerab" type="text" size="40" readonly="readonly">
		</td>
		<td width="30%" class="botoneralNegritaRight">
			<bean:message key='SIIF.label.Tranzon'/>
		</td>
		<td align="left">
			<input value="${guiaForestal.rodal.marcacion.tranzon.numero} - ${guiaForestal.rodal.marcacion.tranzon.disposicion}" 
					class="botonerab" type="text" size="40" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Marcacion'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.rodal.marcacion.disposicion}" 
					class="botonerab" type="text" size="40" readonly="readonly">
		</td>
		<td width="30%" class="botoneralNegritaRight">
			<bean:message key='SIIF.label.Rodal'/>
		</td>
		<td align="left">
			<input value="${guiaForestal.rodal.nombre}"
				   class="botonerab" type="text" size="40" readonly="readonly">
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

	<!-- PRODUCTOS FORESTALES -->
	<tr>
		<td colspan="4" align="left">
			<div id="e1" style="DISPLAY: ">
				<label onclick="javascript:exp('1')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion"><bean:message key='SIIF.subTitulo.ProductosForestales'/></U><BR>
				</label>
			</div>
			<div id="c1" style="DISPLAY: none">
				<label onclick="javascript:col('1')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion"><bean:message key='SIIF.subTitulo.ProductosForestales'/></U><BR>
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

								<c:forEach items="${guiaForestal.subImportes}" var="subImporte" varStatus="i">	
								<tr>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.tipoProducto.nombre}">														
									</td>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.estado}">														
									</td> 
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.especie}">
									</td>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.cantidadMts}">									
									</td>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.valorAforos}">													
									</td>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.importe}">
									</td>
								</tr>
								</c:forEach>	
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
										<input value="${guiaForestal.inspFiscalizacion}" readonly="readonly" class="botonerab" type="text">
									</td>
								</tr> 
								<tr>
									<td class="botoneralNegritaRight"><bean:message key='SIIF.label.IMPORTE_TOTAL'/></td>
									<td>
										<input readonly="readonly" value="${guiaForestal.importeTotal}" class="botonerab" type="text">
									</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
							</table>							
						</td>
					</tr>
					<tr>
						<td height="15" colspan="4"></td>
					</tr>
					<tr>
						<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Observaciones'/></td>
						<td align="left" colspan="3">
							<textarea class="botonerab" cols="130" rows="3">
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
			<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2"> 
				<tr>
					<td colspan="3" class="azulAjustado"><bean:message key='SIIF.label.BoletasDeposito'/></td>
				</tr>				

				<c:choose>					
					<c:when test="${fn:length(guiaForestal.boletasDeposito)>0}">
						<tr>
							<td colspan="3"  class="azulAjustado">
								<button class="botonerab" onclick="expBoletasImpagas();"> Expandir Boletas Impagas </button> 
								<button class="botonerab" onclick="expBoletasPagas();"> Expandir Boletas Pagas </button>
								<button class="botonerab" onclick="expBoletaNro();"> Expandir Boleta Nro </button> <Input type="text" value="" id="expBoleta">
							</td>
						</tr>
						<tr>
							<td height="10" colspan="3"></td>
						</tr>					
										
						<c:forEach items="${guiaForestal.boletasDeposito}" var="boletaDeposito" varStatus="index">
						
							<tr onclick="$('#idTr<c:out value='${boletaDeposito.numero}'/>').toggle();">								
								<td colspan="3" class="grisSubtitulo" id="tdBoleta<c:out value='${index.count}'></c:out>" 									
									onmouseover="javascript:pintarFila(<c:out value='${index.count}'></c:out>);"
									onmouseout="javascript:despintarFila(<c:out value='${index.count}'></c:out>);">
									Boleta n° <c:out value="${boletaDeposito.numero}"></c:out>
								</td>
							</tr>						
						
							<tr id="idTr<c:out value='${boletaDeposito.numero}'></c:out>" style="display: none">
								<td colspan="2" width="85%">			
									<table border="0" class="cuadrado" align="right" width="80%" cellpadding="2">
										<tr>
											<td width="10%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.BoletaDeposito'/>
											</td>
											<td width="35%" align="left">
												<input value="${boletaDeposito.numero}" class="botonerab" type="text"
													   size="20" readonly="readonly">
											</td>
											<td width="15%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.Productor'/>
											</td>
											<td width="40%" align="left" colspan="2">
												<input value="${boletaDeposito.guiaForestal.productorForestal.nombre}"
													   class="botonerab" type="text" size="40" readonly="readonly">
											</td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.Concepto'/>
											</td>
											<td colspan="4" align="left">
												<input value="${boletaDeposito.concepto}" class="botonerab" type="text" size="94"
												 	   readonly="readonly">
											</td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.Area'/>
											</td>
											<td colspan="4" align="left">
												<input value="${boletaDeposito.area}" class="botonerab" type="text" size="94"
													   readonly="readonly">
											</td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.EfecticoCheque'/>
											</td>
											<td width="35%" align="left">
												<input value="${boletaDeposito.efectivoCheque}" class="botonerab"
													   type="text" size="20" readonly="readonly">
											</td>
											<td width="15%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.Monto$'/>
											</td>
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
													   value="${boletaDeposito.fechaVencimiento}">
												<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
													 align="top" width='17' height='21'>		
											</td>
											<td width="15%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.FechaPago'/>
											</td>
											<td width="23%" align="left">
												<input id="idFechaPago<c:out value='${boletaDeposito.idBoleta}'></c:out>"
													   type="text" readonly="readonly" class="botonerab" size="17"
													   value="${boletaDeposito.fechaPago}">
												<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
													 align="top" width='17' height='21'>																						
											</td>										
											<c:choose>
												<c:when test="${boletaDeposito.fechaPago ==null}">
													<td id="idEstadoBoleta<c:out value='${boletaDeposito.idBoleta}'></c:out>" 
														width="17%" class="rojoAdvertenciaLeft">
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
									<br>
								</td>
								<td>
									<c:if test="${boletaDeposito.fechaPago ==null}">
										<input id="idBotonPago<c:out value='${boletaDeposito.idBoleta}'></c:out>"
											   type="button" value="Registrar Pago" class="botonerab" 
											   onclick="registrarPago(<c:out value='${boletaDeposito.idBoleta}'></c:out>);">
									</c:if>	
								</td>
							</tr>
							<tr>
								<td height="5" colspan="3"></td>
							</tr>							
						</c:forEach>	
					</c:when>
					<c:otherwise>
						<bean:message key='SIIF.error.NoExiBoletas'/>
					</c:otherwise>													
				</c:choose>									
					<!-- </td>
					<td>
						<input type="button" class="botonerab" value="Pagar">
					</td>
				</tr> -->
			</table>
			</div>
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
			<input type="button" class="botonerab" value="Volver" 
				onclick="javascript:volver();">
		</td>
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr> 
</table>
