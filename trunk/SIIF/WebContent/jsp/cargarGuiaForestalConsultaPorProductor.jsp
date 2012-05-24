<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import= "ar.com.siif.negocio.Fiscalizacion" %>
<%@ page import= "ar.com.siif.negocio.GuiaForestal" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript" 
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volver(){	
	var metodo = $('#paramForward').val();
	var productor = $('#paramProductor').val();
	var localidad = $('#paramLocalidad').val();
	parent.location = contextRoot() +  '/consultasPorProductor.do?metodo=cargarConsultaPorProductores&forward=' + metodo + '&idProd=' + productor + '&idLoc=' + localidad;

	/*var especificaciones="top=0, left=0, toolbar=no,location=no, status=no,menubar=no,scrollbars=no, resizable=no";
	window.open("../../mostrarReporte.do?metodo=pruebaJasper",especificaciones);*/		
}

function imprimir(){	
	
	var idGuia = $('#idGuia').val();	
	var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
	if(type == "IE"){
		window.open("./reporte.do?metodo=generarReporteGuiaForestal&idGuia="+idGuia,"",especificaciones);
	}else{
		window.open("../../reporte.do?metodo=generarReporteGuiaForestal&idGuia="+idGuia,"",especificaciones);
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
</script>

<%
	GuiaForestal guia = (GuiaForestal)request.getAttribute("guiaForestal");
%>

<input id="idGuia" type="hidden" value="${guiaForestal.id}">
<input id="paramForward" type="hidden" value="${paramForward}">
<input id="paramProductor" type="hidden" value="${guiaForestal.fiscalizacion.productorForestal.id}">
<input id="paramLocalidad" type="hidden" value="${guiaForestal.fiscalizacion.productorForestal.localidad.id}">
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td colspan="4" class="azulAjustado">
			<bean:message key='SIIF.titulo.GuiaForestal'/>
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
			<input type="text" value="<fmt:formatDate value='${guiaForestal.fechaVencimiento}' pattern='dd/MM/yyyy'/>" 
				readonly="readonly" class="botonerab">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
				align="top" width='17' height='21'>
		</td>
		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
		<td align="left">
			<input value="${guiaForestal.fiscalizacion.periodoForestal}" class="botonerab" type="text" 
				   size="40" readonly="readonly">
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
			<input value="${guiaForestal.distanciaAforoMovil}" readonly="readonly" 
				   class="botonerab" type="text" size="10"><bean:message key='SIIF.label.km'/>
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
									<td colspan="2"><bean:message key='SIIF.label.DerechoInspFisca'/></td>
									<td><input value="${guiaForestal.inspFiscalizacion}" readonly="readonly"
										class="botonerab" type="text"></td>
								</tr>
								<tr>
									<td colspan="4"></td>
									<td class="botoneralNegrita"><bean:message key='SIIF.label.TOTAL'/></td>
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
						<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ValorAforo'/></td>
						<td width="55%" align="left">
							<input value="${guiaForestal.valorAforos}" class="botonerab" type="text" 
								size="70" readonly="readonly">
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Observaciones'/></td>
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
			<table class="cuadrado" align="center" width="90%" cellpadding="2"> 
				<tr>
					<td colspan="4" class="azulAjustado"><bean:message key='SIIF.label.BoletasDeposito'/></td>
				</tr>				

				<tr>
					<td height="10" colspan="4"></td>
				</tr>
				
				<tr>
					<td colspan="4">
						<c:choose>					
							<c:when test="${fn:length(guiaForestal.boletasDeposito)>0}">					
								<c:forEach items="${guiaForestal.boletasDeposito}" var="boletaDeposito" varStatus="index">
									<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
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
											<td width="35%" align="left">
												<input value="${boletaDeposito.numero}" class="botonerab" type="text"
													   size="20" readonly="readonly">
											</td>
											<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Productor'/></td>
											<td width="40%" align="left" colspan="2">
												<input value="${boletaDeposito.guiaForestal.fiscalizacion.productorForestal.nombre}"
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
													   value="<fmt:formatDate value='${boletaDeposito.fechaVencimiento}' pattern='dd/MM/yyyy' />">
												<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
													 align="top" width='17' height='21'>		
											</td>
											<td width="15%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.FechaPago'/>
											</td>
											<td width="23%" align="left">
												<input type="text" readonly="readonly" class="botonerab" size="17"
													   value="<fmt:formatDate value='${boletaDeposito.fechaPago}' pattern='dd/MM/yyyy' />">
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
									<br>
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
			<table class="cuadrado" align="center" width="90%" cellpadding="2">
				<tr>
					<td colspan="4" class="azulAjustado"><bean:message key='SIIF.subTitulo.ValesTransporte'/></td>
				</tr>				
				<tr>
					<td height="10" colspan="4"></td>
				</tr>
				<tr>
					<td colspan="4">
						<c:choose>					
							<c:when test="${fn:length(guiaForestal.valesTransporte)>0}">
								<c:forEach items="${guiaForestal.valesTransporte}" var="valeTransporte" varStatus="index">						
									<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
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
												<input value="${valeTransporte.numero}" class="botonerab" type="text"
													   size="25" readonly="readonly">
											</td>
											<td width="10%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.TransportadosPor'/>
											</td>
											<td width="40%" align="left">
												<input value="${valeTransporte.guiaForestal.fiscalizacion.productorForestal.nombre}"
													   class="botonerab" type="text" size="40" readonly="readonly">
											</td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Origen'/></td>
											<td width="40%" align="left">
												<input value="${valeTransporte.origen}" class="botonerab" type="text"
													   size="25" readonly="readonly">
											</td>
											<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Destino'/></td>
											<td width="40%" align="left">
												<input value="${valeTransporte.destino}" class="botonerab"
													   type="text" size="25" readonly="readonly">
											</td>
										</tr>
										<tr>
											<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Vehiculo'/></td>
											<td width="40%" align="left">
												<input value="${valeTransporte.vehiculo}" class="botonerab"
													   type="text" size="25" readonly="readonly">
											</td>
											<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Marca'/></td>
											<td width="40%" align="left">
												<input value="${valeTransporte.marca}" class="botonerab" type="text"
													   size="25" readonly="readonly">
											</td>
										</tr>
										
										
										<tr>
											<td width="10%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.Fecha_Venc'/>
											</td>
											<td width="40%" align="left">
												<input type="text" readonly="readonly" class="botonerab"
													   value="<fmt:formatDate value='${valeTransporte.fechaVencimiento}' pattern='dd/MM/yyyy'/>"> 
												<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
													 align="top" width='17' height='21'>	
											</td>
											<td width="10%" class="botoneralNegritaRight">
												<bean:message key='SIIF.label.Dominio'/>
											</td>											
											<td width="40%" align="left">
												<input value="${valeTransporte.dominio}" class="botonerab"
													   type="text" size="7" readonly="readonly">																							
											</td>
										</tr>										
										
										
										<tr>
											<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha_Dev'/></td>
											<td width="40%" align="left">
												<input type="text" readonly="readonly" class="botonerab"
													   value="<fmt:formatDate value='${valeTransporte.fechaDevolucion}' pattern='dd/MM/yyyy'/>"> 
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
								<bean:message key='SIIF.error.NoExiVales'/>
							</c:otherwise>													
						</c:choose>	
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
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>    
		<td height="10" colspan="4"></td>
	</tr>
	<tr>
		<td height="20" width="49%" align="right">
			<input type="button" class="botonerab" value="Volver" onclick="javascript:volver();">
		</td>
		<td height="20" colspan="2">
		</td>
		<td height="20" width="49%" align="left">
			<input type="button" class="botonerab" value="Imprimir" onclick="javascript:imprimir();">
		</td>
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr> 
</table>
