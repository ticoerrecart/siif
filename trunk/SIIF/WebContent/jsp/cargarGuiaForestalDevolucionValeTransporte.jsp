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

<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volver(){	
	var entidad = $('#paramIdTipoDeEntidad').val();
	var productor = $('#paramProductor').val();
	parent.location = contextRoot() +  
	'/guiaForestal.do?metodo=recuperarProductoresParaValeTransporte&forward=cargarGuiaForestalDevolucionValeTransporte'+ 
	'&forwardBuscarNroGuia=cargarGuiaForestalDevolucionValeTransportePorNroGuia&idProductor=' + productor + '&idTipoDeEntidad=' + entidad;		
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

var idVale;
function registrarDevolucion(vale){

	if(confirm("¿Está seguro que desea registrar la devolución del Vale de Transporte?")){

		/*var id= "#idTr"+idBoleta;
		$(id).html("");
		$("#idBoletas").load('../../guiaForestal.do?metodo=registrarPagoBoletaDeposito&idBoleta='+idBoleta);*/

		idVale = vale;
		GuiaForestalFachada.registrarDevolucionYCompletarDatosValeTransporte(idVale, 
				   $("#idDestino"+idVale).val(),$("#idVehiculo"+idVale).val(),$("#idMarca"+idVale).val(),
				   $("#idDominio"+idVale).val(),$("#idProducto"+idVale).val(),$("#idNroPiezas"+idVale).val(),
				   $("#idCantM3"+idVale).val(),$("#idEspecie"+idVale).val(), $("#idFechaDevolucion"+idVale).val(),
				   registrarDevolucionCallback);
		
	}		
}

function registrarDevolucionCallback(valor){
	
	if (valor == null){
		var idEstado = "#idEstadoVale"+idVale;
		$(idEstado).html("DEVUELTA");	
		$(idEstado).attr('class', "verdeExitoLeft");
			
		var idBotonDevolucion = "#idBotonDevolucion"+idVale;
		$(idBotonDevolucion).toggle();

		var idFechaDevolucion = "#idFechaDevolucion"+idVale;	
		$(idFechaDevolucion).attr('value', valor);
	} else {
		var valor = valor.replace("<error>","");
		var nodos = valor.split("</error>");
		$('#errores').text("");
		for ( var i = 0; i < nodos.length; i++) {
			$("#errores").append("<p>"+nodos[i]+"</p>");
		}
		moverAErrores();
	}
			
}

function expValeNro(){
	var idValeExp = $('#expVale').val();
	$('[id="idTr' + idValeExp +'"]').show();
}

function expValesDevueltos(){
	$('td.verdeExitoLeft').parents('[id^="idTr"]').show();
	$('td.rojoAdvertenciaLeft').parents('[id^="idTr"]').hide();
}

function expValesNoDevueltos(){
	$('td.rojoAdvertenciaLeft').parents('[id^="idTr"]').show();
	$('td.verdeExitoLeft').parents('[id^="idTr"]').hide();	
}


function actEspecie(indice){
	var prodEst = $("#idProductoSel"+indice).val();
	var prodArray = prodEst.split("-");
	var prod = prodArray[0]; 
	var esp = prodArray[1];
	var rel = prodArray[2];
	var piezas = $("#idNroPiezas"+indice).val();
	
	$("#idProducto"+indice).val(prod);
	$("#idEspecie"+indice).val(esp);
	
	if (prodEst.indexOf("Leña")==0){
		$("#idCantM3"+indice).attr("readonly",false);
		$("#idNroPiezas"+indice).val(0);
		$("#idNroPiezas"+indice).attr("readonly",true);
	} else {
		$("#idCantM3"+indice).attr("readonly",true);
		$("#idNroPiezas"+indice).attr("readonly",false);
	}
	
	
	
	
	if (piezas != null) {
		$("#idCantM3"+indice).val(roundNumber(rel*piezas,2));	
	}
}


function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}


</script>

<%
	GuiaForestalDTO guia = (GuiaForestalDTO)request.getAttribute("guiaForestal");
%>

<input id="paramIdTipoDeEntidad" type="hidden" value="${guiaForestal.productorForestal.tipoEntidad}">
<input id="paramProductor" type="hidden" value="${guiaForestal.productorForestal.id}">

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td colspan="4" class="azulAjustado">
			<bean:message key='SIIF.titulo.DevValeTrans'/>
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
								<td class="azulAjustado"><bean:message key='SIIF.label.CantUnd'/></td>
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
										<c:out value="${fiscalizacion.cantidadUnidades}"></c:out>
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
									<td class="azulAjustado"><bean:message key='SIIF.label.ValorAforo'/></td>
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
				
					<c:choose>					
						<c:when test="${fn:length(guiaForestal.valesTransporte)>0}">

								<tr >
									<td colspan="4"  class="azulAjustado">
										<button class="botonerab" onclick="expValesNoDevueltos();"> Expandir Vales No Devueltos </button> 
										<button class="botonerab" onclick="expValesDevueltos();"> Expandir Vales Devueltos </button>
										<button class="botonerab" onclick="expValeNro();"> Expandir Vale Nro </button> <Input type="text" value="" id="expVale">
									</td>
								</tr>
				<tr>
					<td height="10" colspan="3"></td>
				</tr>

							<c:forEach items="${guiaForestal.valesTransporte}" var="valeTransporte" varStatus="index">
								<tr onclick="$('#idTr<c:out value='${valeTransporte.numero}'/>').toggle();">
									<td colspan="4" class="grisSubtitulo" >
										<bean:message key='SIIF.label.ValeTransporteNro'/><c:out value="${valeTransporte.numero}"></c:out>
									</td>
								</tr>
								<tr id="idTr<c:out value='${valeTransporte.numero}'/>" style="display: none" >
									<td colspan="2" width="85%">	
									
									
										<c:choose>
											<c:when test="${valeTransporte.fechaDevolucion ==null}">
												<c:set var="readonly" scope="session" value=""/>
											</c:when>
											<c:otherwise>
												<c:set var="readonly" scope="session" value="readonly='readonly'"/>
											</c:otherwise>
										</c:choose>									
									
																						
										<table class="cuadrado" align="right" width="90%" cellpadding="2">
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.NumeroVale'/>
												</td>
												<td width="40%" align="left">
													<input id="idNroVale<c:out value='${valeTransporte.id}'/>"
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
													<input id="idOrigen<c:out value='${valeTransporte.id}'/>" 
														   value="${valeTransporte.origen}" class="botonerab" type="text"
														   size="25" readonly="readonly">
												</td>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Destino'/>
												</td>
												<td width="40%" align="left">
													<input id="idDestino<c:out value='${valeTransporte.id}'/>"
														   value="${valeTransporte.destino}" class="botonerab"
														   type="text" size="25" <c:out value="${readonly}"></c:out> >
														   
												</td>
											</tr>
											
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Vehiculo'/>
												</td>
												<td width="40%" align="left">
													<input id="idVehiculo<c:out value='${valeTransporte.id}'/>"
														   value="${valeTransporte.vehiculo}" class="botonerab"
														   type="text" size="25" <c:out value="${readonly}"></c:out>>
												</td>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Marca'/>
												</td>
												<td width="40%" align="left">
													<input id="idMarca<c:out value='${valeTransporte.id}'/>"
														   value="${valeTransporte.marca}" class="botonerab" type="text"
														   size="25" <c:out value="${readonly}"></c:out>>
												</td>
											</tr>
											
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Fecha_Venc'/>
												</td>
												<td width="40%" align="left">
													<input id="idFechaVenc<c:out value='${valeTransporte.id}'></c:out>"  type="text" readonly="readonly" class="botonerab"
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
														   type="text" size="7" <c:out value="${readonly}"></c:out>>																							
												</td>
											</tr>											
					
											<tr>
												<td width="10%" class="botoneralNegritaRight">
													<bean:message key='SIIF.label.Fecha_Dev'/>
												</td>
												<td width="40%" align="left">
													<input id="idFechaDevolucion<c:out value='${valeTransporte.id}'></c:out>"
														   type="text"  class="botonerab" value="${valeTransporte.fechaDevolucion}"
														   <c:out value="${readonly}"></c:out>> 
													<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
														 align="top" width='17' height='21'>														 					
												</td>
												<td width="10%" class="botoneralNegritaRight">
													
												</td>
												<c:choose>
													<c:when test="${valeTransporte.fechaDevolucion ==null}">
														<script>
														$(function() {
															$( "#idFechaDevolucion<c:out value='${valeTransporte.id}'/>" ).datepicker({ dateFormat: 'dd/mm/yy'});
														});
														</script>													
														<td width="40%" class="rojoAdvertenciaLeft"
															id="idEstadoVale<c:out value='${valeTransporte.id}'></c:out>">
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
															<c:choose>
																<c:when test="${valeTransporte.fechaDevolucion ==null}">
																	<select id="idProductoSel<c:out value='${valeTransporte.id}'/>" class="botonerab"  onchange="actEspecie(<c:out value='${valeTransporte.id}'/>)">
																			<c:forEach items="${guiaForestal.productosEspeciesYRelacionMtsPorPieza}" var="prod">
																				<option value="${prod.producto}-${prod.especie}-${prod.mts3xpieza}">${prod.producto}</option> 
																			</c:forEach>
																	</select>
																</c:when>
																<c:otherwise>
																	<input class="botonerab" type="text" value="${valeTransporte.producto}" readonly="readonly">
																</c:otherwise>
															</c:choose>															
 
															
															<input id="idProducto<c:out value='${valeTransporte.id}'/>"  type="hidden" value="" />
														</td>
														<td>
															<input id="idNroPiezas<c:out value='${valeTransporte.id}'/>"  class="botonerab" type="text" value="${valeTransporte.nroPiezas}" 
																	onblur="actEspecie(<c:out value='${valeTransporte.id}'/>)" <c:out value="${readonly}"></c:out>> 
														</td>
														<td>
															<input id="idCantM3<c:out value='${valeTransporte.id}'/>" class="botonerab" type="text" value="${valeTransporte.cantidadMts}" readonly="readonly">
														</td>
														<td>
															<input id="idEspecie<c:out value='${valeTransporte.id}'/>" class="botonerab" type="text" value="${valeTransporte.especie}" readonly="readonly">
															<script>
																actEspecie(<c:out value='${valeTransporte.id}'/>);
															</script>
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
											<input id="idBotonDevolucion<c:out value='${valeTransporte.id}'></c:out>"
												   type="button" value="Registrar Devolución" class="botonerab" 
												   onclick="registrarDevolucion(<c:out value='${valeTransporte.id}'></c:out>);" />
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
