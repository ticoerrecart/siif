<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import= "ar.com.siif.negocio.Fiscalizacion" %>
<%@ page import= "ar.com.siif.negocio.GuiaForestal" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/GuiaForestalFachada.js'/>"></script>	
<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

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

</script>

<%
	GuiaForestal guia = (GuiaForestal)request.getAttribute("guiaForestal");
%>

<input id="paramIdTipoDeEntidad" type="hidden" value="${guiaForestal.fiscalizacion.productorForestal.idTipoEntidad}">
<input id="paramProductor" type="hidden" value="${guiaForestal.fiscalizacion.productorForestal.id}">
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
			<input id="nombreProductor" value="${guiaForestal.fiscalizacion.productorForestal.nombre}" 
				   class="botonerab" type="text" size="40" readonly="readonly">			
		</td>
	</tr>

	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ValidoHasta'/></td>
		<td width="30%" align="left">
			<input type="text" value="<fmt:formatDate value='${guiaForestal.fechaVencimiento}' pattern='dd/MM/yyyy' />" 
				readonly="readonly" class="botonerab">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
				align="top" width='17' height='21'>
		</td>
		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
		<td align="left">
			<input value="${guiaForestal.fiscalizacion.periodoForestal}" class="botonerab" type="text" size="40" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.fiscalizacion.productorForestal.localidad.nombre}" readonly="readonly" class="botonerab" size="40">
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
 
<table border="0" class="cuadrado" align="center" width="80%"
	cellpadding="2">
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight">
			<bean:message key='SIIF.label.PlanManejoForestal'/>
		</td>
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

<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td height="10" colspan="4"></td>
	</tr>

	<!-- PRODUCTOS FORESTALES
	<tr>
		<td colspan="4" align="left">
			<div id="e1" style="DISPLAY: ">
				<label onclick="javascript:exp('1')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion">Productos Forestales</U><BR>
				</label>
			</div>
			<div id="c1" style="DISPLAY: none">
				<label onclick="javascript:col('1')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion">Productos Forestales</U><BR>
				</label>
	
				<table border="0" class="cuadradoSinBorde" align="center"
					   width="100%" cellpadding="2">
					<tr>
						<td height="5" colspan="4"></td>
					</tr>
					<tr>
						<td align="left" colspan="4">
							La presente Guía Forestal Básica certifica la propiedad de la siguiente partida de productos forestales:
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
											value="<c:out value='${guiaForestal.fiscalizacion.tipoProducto.nombre}'/>"
											readonly="readonly">																							
									</td>
									<td>
										<input class="botonerab" type="text" value="${guiaForestal.estado}" readonly="readonly">							
									</td>
									<td>
										<input class="botonerab" type="text" value="${guiaForestal.especie}" readonly="readonly">
									</td>
									<td>
										<input id="idCantidadMts" class="botonerab" type="text"
											value="<c:out value='${guiaForestal.fiscalizacion.cantidadMts}'/>"
											readonly="readonly">
									</td>
									<td>
										<input class="botonerab" type="text"
											value="<c:out value='${guiaForestal.fiscalizacion.cantidadUnidades}'/>"
											readonly="readonly">
									</td>
									<td>
										<input class="botonerab" type="text" value="${guiaForestal.importe}" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td colspan="6">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
									<td colspan="2">Derecho de Inspección y Fiscalización 20%</td>
									<td><input value="${guiaForestal.inspFiscalizacion}" readonly="readonly"
										class="botonerab" type="text"></td>
								</tr>
								<tr>
									<td colspan="4"></td>
									<td class="botoneralNegrita">TOTAL</td>
									<td>
										<input readonly="readonly" class="botonerab" type="text"
										 value="<%= guia.getImporte() + guia.getInspFiscalizacion()%>">
									</td>
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
							<input value="${guiaForestal.valorAforos}" class="botonerab" type="text" 
								size="70" readonly="readonly">
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td width="12%" class="botoneralNegritaRight">Observaciones</td>
						<td align="left" colspan="3">
							<textarea class="botonerab" cols="130" rows="3" 
									  readonly="readonly"><c:out value="${guiaForestal.observaciones}"></c:out></textarea>
						</td>
					</tr>
					<tr>
						<td height="10" colspan="4"></td>
					</tr>
				</table>
			</div>
		</td>
	</tr> -->

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

				<tr>
					<td height="10" colspan="3"></td>
				</tr>
				
				<!-- <tr>
					<td colspan="2">-->
						<c:choose>					
							<c:when test="${fn:length(guiaForestal.boletasDeposito)>0}">					
								<c:forEach items="${guiaForestal.boletasDeposito}" var="boletaDeposito" varStatus="index">
									<tr id="idTr<c:out value='${boletaDeposito.id}'></c:out>">
										<td colspan="2" width="85%">			
											<table border="0" class="cuadrado" align="right" width="80%" cellpadding="2">
												<tr>
													<td colspan="5" class="grisSubtitulo">
														<bean:message key='SIIF.label.Cuota'/><c:out value="${index.index+1}"></c:out>
													</td>
												</tr>
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
													<td width="15%" class="botoneralNegritaRight">
														<bean:message key='SIIF.label.Productor'/>
													</td>
													<td width="40%" align="left" colspan="2">
														<input value="${boletaDeposito.guiaForestal.fiscalizacion.productorForestal.nombre}"
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
															   value="<fmt:formatDate value='${boletaDeposito.fechaVencimiento}' pattern='dd/MM/yyyy' />">
														<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
															 align="top" width='17' height='21'>		
													</td>
													<td width="15%" class="botoneralNegritaRight">
														<bean:message key='SIIF.label.FechaPago'/>
													</td>
													<td width="23%" align="left">
														<input id="idFechaPago<c:out value='${boletaDeposito.id}'></c:out>"
															   type="text" readonly="readonly" class="botonerab" size="17"
															   value="<fmt:formatDate value='${boletaDeposito.fechaPago}' pattern='dd/MM/yyyy' />">
														<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
															 align="top" width='17' height='21'>																						
													</td>										
													<c:choose>
														<c:when test="${boletaDeposito.fechaPago ==null}">
															<td id="idEstadoBoleta<c:out value='${boletaDeposito.id}'></c:out>" 
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
												<input id="idBotonPago<c:out value='${boletaDeposito.id}'></c:out>"
													   type="button" value="Registrar Pago" class="botonerab" 
													   onclick="registrarPago(<c:out value='${boletaDeposito.id}'></c:out>);">
											</c:if>	
										</td>
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

	<!-- VALES DE TRANSPORTE
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
						<c:choose>					
							<c:when test="${fn:length(guiaForestal.valesTransporte)>0}">
								<c:forEach items="${guiaForestal.valesTransporte}" var="valeTransporte" varStatus="index">						
									<table border="0" class="cuadrado" align="center"
										width="80%" cellpadding="2">
				
										<tr>
											<td colspan="4" class="grisSubtitulo">
												Vale de Transporte n°<c:out value="${index.index+1}"></c:out>
											</td>
										</tr>
										<tr>
											<td height="5" colspan="4"></td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight">
												Número de Vale
											</td>
											<td width="40%" align="left">
												<input value="${valeTransporte.numero}" class="botonerab" type="text"
													   size="25" readonly="readonly">
											</td>
											<td width="10%" class="botoneralNegritaRight">
												Transportados por
											</td>
											<td width="40%" align="left">
												<input value="${valeTransporte.guiaForestal.fiscalizacion.productorForestal.nombre}"
													   class="botonerab" type="text" size="40" readonly="readonly">
											</td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight">Origen</td>
											<td width="40%" align="left">
												<input value="${valeTransporte.origen}" class="botonerab" type="text"
													   size="25" readonly="readonly">
											</td>
											<td width="10%" class="botoneralNegritaRight">Destino</td>
											<td width="40%" align="left">
												<input value="${valeTransporte.destino}" class="botonerab"
													   type="text" size="25" readonly="readonly">
											</td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight">Vehiculo</td>
											<td width="40%" align="left">
												<input value="${valeTransporte.vehiculo}" class="botonerab"
													   type="text" size="25" readonly="readonly">
											</td>
											<td width="10%" class="botoneralNegritaRight">Marca</td>
											<td width="40%" align="left">
												<input value="${valeTransporte.marca}" class="botonerab" type="text"
													   size="25" readonly="readonly">
											</td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight">Dominio</td>
											<td width="40%" align="left">
												<input value="${valeTransporte.dominio}" class="botonerab"
													   type="text" size="7" readonly="readonly">
											</td>
											<td width="10%" class="botoneralNegritaRight">
												Fecha Vencimiento
											</td>
											<td width="40%" align="left">
												<input type="text" readonly="readonly" class="botonerab"
													   value="<fmt:formatDate value='${valeTransporte.fechaVencimiento}' pattern='dd/MM/yyyy'/>"> 
												<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
													 align="top" width='17' height='21'>																							
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
									<br>
								</c:forEach>
							</c:when>
							<c:otherwise>
								No existen Vales de Transporte para esta Guía Forestal
							</c:otherwise>													
						</c:choose>	
					</td>
				</tr>
			</table>
		</div>
		</td>
	</tr>-->
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
</table>
<!-- 
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
			
			<input id="datepickerFecha" type="text" name="fecha" readonly="readonly" class="botonerab">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
				align="top" width='17' height='21'>				
			
		</td>

	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
</table>-->
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
