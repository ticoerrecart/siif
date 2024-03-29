<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volver(){

	var metodo = $('#paramForward').val();
	var productor = $('#paramProductor').val();
	var entidad = $('#paramIdTipoDeEntidad').val();
	var idPeriodo = $('#idPeriodo').val();
	parent.location = contextRoot() +  '/consultasFiscalizacion.do?metodo=cargarTiposDeEntidadConsultaFiscalizacion&forward=' + metodo + '&idProductor=' + productor + '&idTipoDeEntidad=' + entidad + '&idPeriodo=' + idPeriodo;		
}

function imprimir(){	
	
	var idFiscalizacion = $('#idFiscalizacion').val();
	//var especificaciones = "top=0, left=0, toolbar=no,location=no, status=no,menubar=no,scrollbars=no, resizable=no";
	var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
	if(type == "IE"){
		window.open("./reporte.do?metodo=generarReporteFiscalizacion&idFiscalizacion="+idFiscalizacion,"",especificaciones);
	}else{
		window.open("../../reporte.do?metodo=generarReporteFiscalizacion&idFiscalizacion="+idFiscalizacion,"",especificaciones);
	}		
}

function headerTabla(){
	var idTipoProductoForestal = '${fiscalizacion.tipoProducto.id}';
	if (idTipoProductoForestal == 1){
		return headerTablaRollizos;
	}  else if (idTipoProductoForestal == 2){
		return headerTablaFustes;
	}  else if (idTipoProductoForestal == 4){
		return headerTablaPostes;
	}  else if (idTipoProductoForestal == 5){
		return headerTablaTrineos;
	}
	
}

</script>

<input id="idFiscalizacion" type="hidden" value="${fiscalizacion.id}">
<input id="paramIdTipoDeEntidad" type="hidden" value="${fiscalizacion.productorForestal.tipoEntidad}">
<input id="paramProductor" type="hidden" value="${fiscalizacion.productorForestal.id}">
<input id="paramForward" type="hidden" value="${paramForward}">

<div id="errores" class="rojoAdvertencia">${error}</div>

	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIF.titulo.FiscalizacionProdForest'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoDeProductor'/></td>
			<td align="left" width="30%">
				<input class="botonerab" type="text" size="29" readonly="readonly"
					   value="<c:out value='${fiscalizacion.productorForestal.tipoEntidadDesc}'></c:out>">			
			</td>
			<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly" 
					   value="<c:out value='${fiscalizacion.productorForestal.nombre}'></c:out>">
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha'/></td>
			<td align="left">
				<input class="botonerab" type="text" size="25" readonly="readonly"
					   value="<c:out value='${fiscalizacion.fecha}'></c:out>">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
				align="top" width='17' height='21'>		
			</td>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly" id="idPeriodo" 
					   value="<c:out value='${fiscalizacion.periodoForestal}'></c:out>">			
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.CantUnd'/></td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly" 
					   value="<c:out value='${fiscalizacion.cantidadUnidades}'></c:out>">
			</td>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoProducto'/></td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly" 
					   value="<c:out value='${fiscalizacion.tipoProducto.nombre}'></c:out>">			
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.CantMts3'/></td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly"
					   value="<c:out value='${fiscalizacion.cantidadMts}'></c:out>">
			</td>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.TamañoMuestra'/></td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly"
					   value="<c:out value='${fiscalizacion.tamanioMuestra}'></c:out>">
			</td>
		</tr>
		<tr>		
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Oficina'/></td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly"
					   value="<c:out value='${fiscalizacion.oficinaAlta.nombre}'></c:out>">
			</td>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.NroDeGuia'/>
			</td>
			<td align="left">
				<input type="text" class="botonerab" value="${fiscalizacion.guiaForestal.nroGuia}" readonly="readonly">
			</td>			
		</tr>		
				
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<!-- LOCALIZACION -->
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="85%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message key='SIIF.subTitulo.Localizacion'/></td>
					</tr>
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
					
					<tr class="area">
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.AreaDeCosecha'/>
						</td>
						<td>
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.localizacion.nombreArea}'></c:out>">																					
						</td>
						<td width="25%"></td>
					</tr>	
								
					<tr class="plan">
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.PlanManejoForestal'/>
						</td>
						<td>
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.localizacion.nombrePMF}- ${fiscalizacion.localizacion.expedientePMF}'></c:out>">																					
						</td>
						<td width="25%"></td>
					</tr>		
					<tr class="plan">
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Tranzon'/>
						</td>
						<td>
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.localizacion.numeroTranzon}- ${fiscalizacion.localizacion.disposicionTranzon}'></c:out>">																						
						</td>
						<td width="25%"></td>
					</tr>	
					<tr class="plan">
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Marcacion'/>
						</td>
						<td>
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.localizacion.disposicionMarcacion}'></c:out>">																					
						</td>
						<td width="25%"></td>
					</tr>
					<tr class="plan">
						<td width="40%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Rodal'/>
						</td>
						<td>
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.localizacion.nombreRodal}'></c:out>">
						<script>
							if (eval("<c:out value='${fiscalizacion.localizacion.esAreaDeCosecha}'/>")){
								$(".area").show();
								$(".plan").hide();
							} else {
								$(".area").hide();
								$(".plan").show();
							}						
						</script>
								   	
						</td>
						<td width="25%"></td>
					</tr>																
					<tr>
						<td colspan="2" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>			

		<%--ACA ESTAN LOS HEADERS DE CADA TIPO DE MUESTRA --%>
		<%@include file="bloqueHeaderTablaMuestrasFiscalizacion.jspf" %>

		<!-- MUESTRAS -->
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="85%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message key='SIIF.subTitulo.Muestras'/></td>
					</tr>
					<tr>
						<td height="20" colspan="3"></td>
					</tr>
					<tr>
						<td colspan="3">
							<table id="tablaMuestras" border="0" class="cuadrado" align="center"
								   width="70%" cellpadding="2" cellspacing="0" style="display: ">
								<%--tr>
									<td class="azulAjustado" width="3%"></td>
									<td class="azulAjustado" width="33%"><bean:message key='SIIF.label.Largo'/></td>
									<td class="azulAjustado" width="32%"><bean:message key='SIIF.label.Diametro1'/></td>
									<td class="azulAjustado" width="32%"><bean:message key='SIIF.label.Diametro2'/></td>
								</tr--%>
								<tr>
									<td colspan="4">
										<c:if test="${fn:length(fiscalizacion.muestra) > 0}">
																																	

												<c:forEach items="${fiscalizacion.muestra}" varStatus="i" var="muestra">
											
													<tr>
														<td height="5" colspan="4"></td>
													</tr>
													<tr>
														<td class="botoneralNegritaRight">${i.index+1}</td>
														<td>
															<input class="botonerab" type="text" readonly="readonly"
																value="${muestra.diametro1}">
														</td>
														<td>
															<input class="botonerab" type="text" readonly="readonly"
																value="${muestra.diametro2}">
														</td>
														<td>
															<input class="botonerab" type="text" readonly="readonly" 
																   value="${muestra.largo}">
														</td>
													</tr>
												
													<tr>
														<td height="5" colspan="4"></td>
													</tr>												
												</c:forEach>
											
											
										</c:if>																																																	
									</td>
								</tr>													
							</table>																	
						</td>
					</tr>
					<tr>
						<td height="10" colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
	
	<c:if test="${fn:length(fiscalizacion.muestra) > 0}">
		<script>
			$("#tablaMuestras").prepend(headerTabla);
		</script>																														
	</c:if>

	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
		<tr>
			<td height="20" width="49%" align="right">
				<input type="button" class="botonerab" value="Volver" onclick="${botonVolver}">			
			</td>
			<td height="20">
			</td>
			<td height="20" width="49%" align="left">
				<input type="button" class="botonerab" value="Imprimir" onclick="javascript:imprimir();">
			</td>			
		</tr>		
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
	</table>	
