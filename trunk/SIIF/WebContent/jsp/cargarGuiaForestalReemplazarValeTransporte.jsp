<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import= "ar.com.siif.dto.FiscalizacionDTO" %>
<%@ page import= "ar.com.siif.dto.GuiaForestalDTO" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
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
	'/guiaForestal.do?metodo=recuperarProductoresParaValeTransporte&forward=cargarGuiaForestalReemplazarValeTransporte'+ 
	'&forwardBuscarNroGuia=cargarGuiaForestalReemplazarValeTransportePorNroGuia&idProductor=' + productor + '&idTipoDeEntidad=' + entidad;		
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

var idValeTransporte;
var nroVale;
var origen;
var destino;
var vehiculo;
var marca;
var dominio;
var htmlInputFechaVenc;
var fechaVencimiento;
var producto;
var nroPiezas;
var cantM3;
var especie;

function reemplazarVale(idVale){

	idValeTransporte = idVale;
	nroVale = $("#idNroVale"+idVale).val();
	origen = $("#idOrigen"+idVale).val();
	destino = $("#idDestino"+idVale).val();
	vehiculo = $("#idVehiculo"+idVale).val();		
	marca = $("#idMarca"+idVale).val();
	dominio = $("#idDominio"+idVale).val();
	htmlInputFechaVenc = $("#idFechaVenc"+idVale).html();
	fechaVencimiento = document.getElementById("idInputFechaVenc"+idVale).value;
	producto = $("#idProducto"+idVale).val();
	nroPiezas = $("#idNroPiezas"+idVale).val();
	cantM3 = $("#idCantM3"+idVale).val();
	especie = $("#idEspecie"+idVale).val();
		 	
	/*$("#idNroVale"+idVale).attr('readonly', false);
	$("#idOrigen"+idVale).attr('readonly', false);
	$("#idDestino"+idVale).attr('readonly', false);
	$("#idVehiculo"+idVale).attr('readonly', false);
	$("#idMarca"+idVale).attr('readonly', false);
	$("#idDominio"+idVale).attr('readonly', false);	
	$("#idInputFechaVenc"+idVale).datepicker({ dateFormat: 'dd/mm/yy'});
	$("#idProducto"+idVale).attr('readonly', false);
	$("#idNroPiezas"+idVale).attr('readonly', false);
	$("#idCantM3"+idVale).attr('readonly', false);
	$("#idEspecie"+idVale).attr('readonly', false);
	
	$("#idAceptar"+idVale).toggle();
	$("#idCancelar"+idVale).toggle();
	$("#idReemplazar"+idVale).toggle();*/

	$("#idInputFechaVenc"+idVale).datepicker({ dateFormat: 'dd/mm/yy'});
	cambiarAtributos(idVale,false);
		
	$('input[value*="Reemplazar Vale"]').attr('disabled', true);		
}

function aceptarReemplazoVale(idVale){

	if(confirm("¿Está seguro que desea reemplazar el vale?")){

		/*$("#idNroVale"+idVale).attr('readonly', true);
		$("#idOrigen"+idVale).attr('readonly', true);
		$("#idDestino"+idVale).attr('readonly', true);
		$("#idVehiculo"+idVale).attr('readonly', true);
		$("#idMarca"+idVale).attr('readonly', true);
		$("#idDominio"+idVale).attr('readonly', true);	
		$("#idProducto"+idVale).attr('readonly', true);
		$("#idNroPiezas"+idVale).attr('readonly', true);
		$("#idCantM3"+idVale).attr('readonly', true);
		$("#idEspecie"+idVale).attr('readonly', true);
		
		$("#idAceptar"+idVale).toggle();
		$("#idCancelar"+idVale).toggle();
		$("#idReemplazar"+idVale).toggle();*/

		cambiarAtributos(idVale,true);
		
		$('input[value*="Reemplazar Vale"]').attr('disabled', false);
		
		GuiaForestalFachada.reemplazarValeTransporte(idVale,$("#idNroVale"+idVale).val(),$("#idOrigen"+idVale).val(),
						   $("#idDestino"+idVale).val(),$("#idVehiculo"+idVale).val(),$("#idMarca"+idVale).val(),
						   $("#idDominio"+idVale).val(),$("#idProducto"+idVale).val(),$("#idNroPiezas"+idVale).val(),
						   $("#idCantM3"+idVale).val(),$("#idEspecie"+idVale).val(),$("#idInputFechaVenc"+idVale).val(),
						   reemplazarValeCallback);

		var fechaVenc = document.getElementById("idInputFechaVenc"+idVale).value;
		$("#idFechaVenc"+idVale).html(htmlInputFechaVenc);
		document.getElementById("idInputFechaVenc"+idVale).value = fechaVenc;				   
	}
}

function reemplazarValeCallback(valor){
	
	if(valor!=null && valor != ""){
		//$("#error").html(valor);
		//col('2');
		alert(valor);
		$("#idNroVale"+idValeTransporte).val(nroVale);
		$("#idOrigen"+idValeTransporte).val(origen);
		$("#idDestino"+idValeTransporte).val(destino);
		$("#idVehiculo"+idValeTransporte).val(vehiculo);		
		$("#idMarca"+idValeTransporte).val(marca);
		$("#idDominio"+idValeTransporte).val(dominio);
		$("#idProducto"+idValeTransporte).val(producto);
		$("#idNroPiezas"+idValeTransporte).val(nroPiezas);
		$("#idCantM3"+idValeTransporte).val(cantM3);
		$("#idEspecie"+idValeTransporte).val(especie);
		$("#idFechaVenc"+idValeTransporte).html(htmlInputFechaVenc);		
	}
	else{
		$("#error").html("");
		alert("El vale se ha reemplazado con exito");
	}	
}

function cancelarReemplazoVale(idVale){

	if(confirm("¿Está seguro que desea cancelar?")){

		/*$("#idNroVale"+idVale).attr('readonly', true);
		$("#idOrigen"+idVale).attr('readonly', true);
		$("#idDestino"+idVale).attr('readonly', true);
		$("#idVehiculo"+idVale).attr('readonly', true);
		$("#idMarca"+idVale).attr('readonly', true);
		$("#idDominio"+idVale).attr('readonly', true);	
		$("#idProducto"+idVale).attr('readonly', true);
		$("#idNroPiezas"+idVale).attr('readonly', true);
		$("#idCantM3"+idVale).attr('readonly', true);
		$("#idEspecie"+idVale).attr('readonly', true);
		
		$("#idAceptar"+idVale).toggle();
		$("#idCancelar"+idVale).toggle();
		$("#idReemplazar"+idVale).toggle();*/

		cambiarAtributos(idVale,true);

		$("#idNroVale"+idVale).val(nroVale);
		$("#idOrigen"+idVale).val(origen);
		$("#idDestino"+idVale).val(destino);
		$("#idVehiculo"+idVale).val(vehiculo);		
		$("#idMarca"+idVale).val(marca);
		$("#idDominio"+idVale).val(dominio);
		$("#idProducto"+idVale).val(producto);
		$("#idNroPiezas"+idVale).val(nroPiezas);
		$("#idCantM3"+idVale).val(cantM3);
		$("#idEspecie"+idVale).val(especie);
		$("#idFechaVenc"+idVale).html(htmlInputFechaVenc);

		document.getElementById("idInputFechaVenc"+idVale).value = fechaVencimiento;
		
		$('input[value*="Reemplazar Vale"]').attr('disabled', false);
	}
}

function cambiarAtributos(idVale,readonly){

	$("#idNroVale"+idVale).attr('readonly', readonly);
	$("#idOrigen"+idVale).attr('readonly', readonly);
	$("#idDestino"+idVale).attr('readonly', readonly);
	$("#idVehiculo"+idVale).attr('readonly', readonly);
	$("#idMarca"+idVale).attr('readonly', readonly);
	$("#idDominio"+idVale).attr('readonly', readonly);	
	$("#idProducto"+idVale).attr('readonly', readonly);
	$("#idNroPiezas"+idVale).attr('readonly', readonly);
	$("#idCantM3"+idVale).attr('readonly', readonly);
	$("#idEspecie"+idVale).attr('readonly', readonly);
	
	$("#idAceptar"+idVale).toggle();
	$("#idCancelar"+idVale).toggle();
	$("#idReemplazar"+idVale).toggle();	
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
			<bean:message key='SIIF.titulo.ReempValeTrans'/>
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
			<input type="text" value="${guiaForestal.fechaVencimiento}" readonly="readonly" class="botonerab">
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
 
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td height="10" colspan="4"></td>
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
					<td colspan="3" class="azulAjustado"><bean:message key='SIIF.subTitulo.ValesTransporte'/></td>
				</tr>				
				<tr>
					<td height="10" colspan="3"></td>
				</tr>
					<c:choose>					
						<c:when test="${fn:length(guiaForestal.valesTransporte)>0}">
							<c:forEach items="${guiaForestal.valesTransporte}" var="valeTransporte" varStatus="index">
								<tr id="idTr<c:out value='${valeTransporte.id}'></c:out>">
									<td colspan="2" width="85%">														
										<table class="cuadrado" align="right" width="90%" cellpadding="2">
											<tr>
												<td colspan="4" class="grisSubtitulo">
													<bean:message key='SIIF.label.ValeTransporteNro'/><c:out value="${index.index+1}"></c:out>
												</td>
											</tr>
											<tr>
												<td height="5" colspan="4"></td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.NumeroVale'/>
												</td>
												<td width="40%" align="left">
													<input id="idNroVale<c:out value='${valeTransporte.id}'></c:out>"
														   value="${valeTransporte.numero}" class="botonerab" type="text"
														   size="25" readonly="readonly">
												</td>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.TransportadosPor'/>
												</td>
												<td width="40%" align="left">
													<input value="${valeTransporte.guiaForestal.productorForestal.nombre}"
														   class="botonerab" type="text" size="40" readonly="readonly">
												</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Origen'/>
												</td>
												<td width="40%" align="left">
													<input id="idOrigen<c:out value='${valeTransporte.id}'></c:out>"
														   value="${valeTransporte.origen}" class="botonerab" type="text"
														   size="25" readonly="readonly">
												</td>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Destino'/>
												</td>
												<td width="40%" align="left">
													<input id="idDestino<c:out value='${valeTransporte.id}'></c:out>"
														   value="${valeTransporte.destino}" class="botonerab"
														   type="text" size="25" readonly="readonly">
												</td>
											</tr>
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Vehiculo'/>
												</td>
												<td width="40%" align="left">
													<input id="idVehiculo<c:out value='${valeTransporte.id}'></c:out>"
														   value="${valeTransporte.vehiculo}" class="botonerab"
														   type="text" size="25" readonly="readonly">
												</td>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Marca'/>
												</td>
												<td width="40%" align="left">
													<input id="idMarca<c:out value='${valeTransporte.id}'></c:out>"
														   value="${valeTransporte.marca}" class="botonerab" type="text"
														   size="25" readonly="readonly">
												</td>
											</tr>
											
											
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Fecha_Venc'/>
												</td>
												<td id="idFechaVenc<c:out value='${valeTransporte.id}'></c:out>" 
													width="40%" align="left">
													<input id="idInputFechaVenc<c:out value='${valeTransporte.id}'></c:out>"
														   type="text" readonly="readonly" class="botonerab"
														   value="${valeTransporte.fechaVencimiento}"> 
													<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
														 align="top" width='17' height='21'>	
												</td>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Dominio'/>
												</td>											
												<td width="40%" align="left">
													<input id="idDominio<c:out value='${valeTransporte.id}'></c:out>"
														   value="${valeTransporte.dominio}" class="botonerab"
														   type="text" size="7" readonly="readonly">																							
												</td>
											</tr>											
											
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Fecha_Dev'/>
												</td>
												<td width="40%" align="left">
													<input type="text" readonly="readonly" class="botonerab"
														   value="${valeTransporte.fechaDevolucion}"> 
													<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
														 align="top" width='17' height='21'>					
												</td>
												<td width="10%" class="botoneralNegritaRight">
													
												</td>
												<c:choose>
													<c:when test="${valeTransporte.fechaDevolucion ==null}">
														<td width="40%" class="rojoAdvertenciaLeft">
															<bean:message key='SIIF.label.NODEVUELTA'/>
														</td>		
													</c:when>
													<c:otherwise>
														<td width="40%" class="verdeExitoLeft">
															<bean:message key='SIIF.label.DEVUELTA'/>
														</td>
													</c:otherwise>
												</c:choose>																						
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
														<td>
															<input id="idProducto<c:out value='${valeTransporte.id}'></c:out>"
																   class="botonerab" type="text" value="${valeTransporte.producto}" 
																   readonly="readonly">
														</td>
														<td>
															<input id="idNroPiezas<c:out value='${valeTransporte.id}'></c:out>"
																   class="botonerab" type="text" value="${valeTransporte.nroPiezas}" 
																   readonly="readonly">
														</td>
														<td>
															<input id="idCantM3<c:out value='${valeTransporte.id}'></c:out>"
																   class="botonerab" type="text" value="${valeTransporte.cantidadMts}" 
																   readonly="readonly">
														</td>
														<td>
															<input id="idEspecie<c:out value='${valeTransporte.id}'></c:out>"
																   class="botonerab" type="text" value="${valeTransporte.especie}" 
																   readonly="readonly">
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
									<td>
										<c:if test="${valeTransporte.fechaDevolucion ==null}">
											<input id="idReemplazar<c:out value='${valeTransporte.id}'></c:out>"
												   type="button" value="Reemplazar Vale" class="botonerab" 
												   onclick="reemplazarVale(<c:out value='${valeTransporte.id}'></c:out>);">
													
											<input id="idAceptar<c:out value='${valeTransporte.id}'></c:out>" type="button" 
													style="display: none;" value="Aceptar" class="botonerab"
													onclick="aceptarReemplazoVale(<c:out value='${valeTransporte.id}'></c:out>);">
													
											<input id="idCancelar<c:out value='${valeTransporte.id}'></c:out>" type="button" 
													style="display: none;" value="Cancelar" class="botonerab"
													onclick="cancelarReemplazoVale(<c:out value='${valeTransporte.id}'></c:out>);">												   
										</c:if>	
									</td>
								</tr>
								<tr>
									<td height="5" colspan="3"></td>
								</tr>																	
							</c:forEach>
						</c:when>
						<c:otherwise>
							<bean:message key='SIIF.error.NoExiVales'/>
						</c:otherwise>													
					</c:choose>	
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
			<input type="button" class="botonerab" value="Volver" 
				onclick="javascript:volver();">
		</td>
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr> 
</table>
