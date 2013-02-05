<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript">

function calcularVolumenTiposProductos(){
	
	var size = ${fn:length(tiposProductoExportacion)};
	var volumentTotal = 0;
	var volumen = 0;
	
	for (var i=1;i<=size;i++){
		
		volumen = ($('#idTipoProd'+i).val() == "")?0:new Number($('#idTipoProd'+i).val());
		
		volumentTotal = volumentTotal + volumen;
	}

	volumentTotal = new Number(volumentTotal).toFixed(2);		
	
	$('#idVolumentTotalTiposProductos').val(volumentTotal);
}

</script>

	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
		<!-- FISCALIZACIONES -->
			<table border="0" class="cuadradoSinBorde" align="center" width="75%" cellpadding="2">
				<tr>
					<td class="botoneralNegritaLeftGrande">
						<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>
					</td>
				</tr>
			</table>			
			<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2">
				<tr>
					<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.Fecha'/></td>
					<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.NroDeGuia'/></td>
					<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.TipoDeProducto'/></td>
					<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.CantMts3'/></td>					
				</tr>
				
				<%String clase=""; %>
				<c:set var="volFiscalizadoTotal" value="${0}"/>
				
				<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
				
					<%clase=(clase.equals("")?"par":""); %>
					<c:set var="volFiscalizadoTotal" value="${volFiscalizadoTotal + fiscalizacion.cantidadMts}" />
					
					<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>">
						<!-- onmouseover="javascript:mostrarDatos(<c:out value='${i.count}'></c:out>);">-->
						<td class="botonerab">
							<c:out value="${fiscalizacion.fecha}"></c:out>
						</td>
						<td class="botonerab">
							<c:choose>
								<c:when test="${fiscalizacion.guiaForestal != null}">
									<c:out value="${fiscalizacion.guiaForestal.nroGuia}"></c:out>
								</c:when>
								<c:otherwise>
									---
								</c:otherwise>
							</c:choose>																				
						</td>	
						<td class="botonerab">
							<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.cantidadMts}"></c:out>
						</td>											
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4"></td>					
				</tr>				
				<tr>
					<td colspan="2"></td>
					<td class="botoneralNegritaRight">Volumen Total Fiscalizado</td>
					<td>
						<input class="botonerab" type="text" size="27" id="idVolumentTotalFiscalizado" readonly="readonly"
						value="<c:out value='${volFiscalizadoTotal}'></c:out>">
					</td>					
				</tr>				
				
			</table>
			<br>
			<br>
			
		<!-- GUIAS FORESTALES 				
			<table border="0" class="cuadradoSinBorde" align="center" width="75%" cellpadding="2">
				<tr>
					<td class="botoneralNegritaLeftGrande">
						<bean:message key='SIIF.subTitulo.GuiasForestales'/>
					</td>
				</tr>
			</table>			
			<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2">
				<tr>
					<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.NroDeGuia'/></td>
					<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.FechaVenc'/></td>
					<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.Volumen'/></td>					
				</tr>
				<tr class="par">				
					<td class="botonerab">-</td>
					<td class="botonerab">-</td>
					<td class="botonerab">-</td>
				</tr>
				<tr class="">				
					<td class="botonerab">-</td>
					<td class="botonerab">-</td>
					<td class="botonerab">-</td>
				</tr>
				<tr class="par">				
					<td class="botonerab">-</td>
					<td class="botonerab">-</td>
					<td class="botonerab">-</td>
				</tr>													
			</table>			
			<br>
			<br>-->
						
		<!-- ESTADO DE DEUDA DEL PRODUCTOR -->	
			<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
				<tr>
					<td colspan="3" class="grisSubtitulo"><bean:message key='SIIF.subTitulo.EstadoDeudaProductor'/></td>
				</tr>
				<tr>
					<td colspan="3" height="15"></td>
				</tr>						
				<tr>
					<td width="5%"></td>				
					<td width="55%" class="botoneralNegritaLeft" id="idSubtituloDeuda">
						<bean:message key='SIIF.label.DeudaConDGB'/>
						<input type="hidden" name="tieneDeuda" value="<c:out value='${deuda}'></c:out>">						
					</td>
					<c:choose>
						<c:when test="${deuda}">
							<td width="40%" class="rojoAdvertenciaLeft" id="idDeuda">
								SI
							</td>
						</c:when>
						<c:otherwise>
							<td width="40%" class="verdeExitoLeft">
								NO
							</td>						
						</c:otherwise>	
					</c:choose>																	
				</tr>	
				<tr>
					<td width="5%">						
					</td>	
					
					<c:choose>
						<c:when test="${deuda}">
							<td width="55%" class="botoneralNegritaLeft">
								<bean:message key='SIIF.label.ActaReconocimientoDeuda'/>						
							</td>
							<td width="40%" class="botonerab" align="left">
								<input type="hidden" value="N" id="idRadioDeuda" name="radioDeuda">
								<input type="radio" class="botonerab" name="radio" onclick="cambiarRadio('S');">SI
								/
								<input type="radio" class="botonerab" checked="checked" name="radio" onclick="cambiarRadio('N');">NO
							</td>
						</c:when>
						<c:otherwise>
							<td width="55%" class="grisLeft">
								<bean:message key='SIIF.label.ActaReconocimientoDeuda'/>						
							</td>
							<td width="40%" class="botonerab" align="left">
								<input type="hidden" value="N" id="idRadioDeuda">
								<input type="radio" disabled="disabled" class="grisLeft" name="radioDeuda" onclick="cambiarRadio('S');">SI
								/
								<input type="radio" disabled="disabled" class="grisLeft" checked="checked" name="radioDeuda" onclick="cambiarRadio('N');">NO
							</td>						
						</c:otherwise>	
					</c:choose>												
				</tr>
				<tr>
					<td colspan="3" height="15"></td>
				</tr>								
			</table>			
			<br>
			<br>
				
		<!-- VERIFICACION DE CARGAS -->			
			<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
				<tr>
					<td colspan="4" class="grisSubtitulo">
						<bean:message key='SIIF.subTitulo.VerificacionDeCargas'/>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="15"></td>
				</tr>						
				<tr>
					<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.OrigenMateriaPrima'/></td>
					<td width="30%">
						<input class="botonerab" type="text" size="27" id="idOrigenMateriaPrima" value="Tierra del Fuego"
								name="certificadoOrigenDTO.origenMateriaPrima">
					</td>
					
					<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroRemito'/></td>
					<td width="32%" align="center">
						<input class="botonerab" type="text" size="27" id="idNroRemito" onkeypress="javascript:esNumerico(event);"
								name="certificadoOrigenDTO.nroRemito">
					</td>																
				</tr>	
				
				<tr>
					<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProvinciaDestino'/></td>
					<td width="30%">
						<select id="idProvincia" class="botonerab" onchange="mostrarLocalidades();">
							<option value="-1">-Seleccione una Provincia-</option>
							<c:forEach items="${provincias}" var="provincia" varStatus="i">
								<option value="${provincia.id}">
									<c:out value="${provincia.nombre}"></c:out>
								</option>						
							</c:forEach>							
						</select>
					</td>
					
					<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.LocalidadDestino'/></td>
					<td width="32%" align="center">
						<select id="idLocalidad" class="botonerab" onchange="" disabled="disabled" 
								name="certificadoOrigenDTO.localidadDestino.id">
							<option value="-1">-Seleccione una Localidad-</option>
						</select>																
					</td>	
				</tr>
										
				<tr>
					<td colspan="4" height="15"></td>
				</tr>				
			</table>			
			<br>
			<br>
			
		<!-- TIPOS DE PRODUCTOS -->			
			<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
				<tr>
					<td class="grisSubtitulo">
						<bean:message key='SIIF.subTitulo.TiposDeProductos'/>
					</td>
				</tr>
				<tr>
					<td height="15"></td>
				</tr>						

				<tr>
					<td>
						<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2">
							<tr>
								<td class="verdeTituloTablaChico" width="60%">
									<bean:message key='SIIF.label.TipoDeProducto'/>
								</td>
								<td class="verdeTituloTablaChico" width="40%">
									<bean:message key='SIIF.label.VolumenM3'/>
								</td>					
							</tr>			
											
							<%clase="par"; %>
							<c:forEach items="${tiposProductoExportacion}" var="tipoProducto" varStatus="i">
								<%clase=(clase.equals("")?"par":""); %>
								<tr class="<%=clase%>">
									<td class="botoneralNegritaMediana">
										<c:out value="${tipoProducto.nombre}"></c:out>
									</td>
									<td align="center">
										<input type="hidden" value="<c:out value="${tipoProducto.id}"></c:out>" 
												name="tiposProductoEnCertificado[<c:out value='${i.count-1}'></c:out>].tipoProductoExportacion.id">
									
										<input class="botonerab" id="idTipoProd<c:out value='${i.count}'></c:out>" 
												type="text" value="" size="12" onkeypress="javascript:esNumericoConDecimal(event);"
												onblur="javascript:calcularVolumenTiposProductos();"
												onkeyup="javascript: twoDigits(this);" 
												name="tiposProductoEnCertificado[<c:out value='${i.count-1}'></c:out>].volumenTipoProducto"> 
									</td>											
								</tr>
							</c:forEach>											
						</table>
						<br>
						<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
							<tr>
								<td class="grisClaroSubtituloCenter" width="60%">
									<bean:message key='SIIF.label.VolumenTotalM3'/>
								</td>
								<td class="grisClaroSubtituloCenter" width="40%">
									<input class="botonerab" type="text" size="12" id="idVolumentTotalTiposProductos" 
											readonly="readonly" value="0" name="certificadoOrigenDTO.volumenTotalTipoProductos">
								</td>					
							</tr>
						</table>					
					</td>
				</tr>	
														
				<tr>
					<td height="15"></td>
				</tr>				
			</table>			
			<br>
			<br>
			
		<!-- VOLUMEN HABILITADO PARA EXPORTAR -->			
			<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
				<tr>
					<td class="grisSubtitulo">
						<bean:message key='SIIF.subTitulo.VolumenHabilitadoParaExportar'/>
					</td>
				</tr>
				<tr>
					<td height="15"></td>
				</tr>						

				<tr>
					<td>
						<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2">			
							<tr>
								<td class="grisClaroSubtituloLeft" width="60%">
									Volúmen habilitado para cosecha
								</td>
								<td class="grisClaroSubtituloCenter" width="40%">
									<input class="botonerab" id="idVolHabParaCosecha" type="text" 
											value="<c:out value="${volFiscalizadoTotal}"></c:out>" 
											size="12" readonly="readonly"> 
								</td>											
							</tr>
							<tr>
								<td class="grisClaroSubtituloLeft">
									Volúmen habilitado procesado para exportar
								</td>
								<td class="grisClaroSubtituloCenter">
									<input class="botonerab" id="idVolHabProc" type="text" 
											value="<c:out value="${volFiscalizadoTotal*0.3}"></c:out>" 
											size="12" readonly="readonly"> 
								</td>											
							</tr>
							<tr>
								<td class="grisClaroSubtituloLeft">
									Volúmen exportado
								</td>
								<td class="grisClaroSubtituloCenter">
									<input class="botonerab" id="idVolExp" type="text" 
											value="<c:out value="${volumenExportado}"></c:out>" 
											size="12" readonly="readonly"> 
								</td>											
							</tr>
							<tr>
								<td class="grisClaroSubtituloLeft">
									Volúmen máximo permitido por exportar
								</td>
								<td class="grisClaroSubtituloCenter">
									<input class="botonerab" id="idVolPorExp" type="text" 
											value=""  name="volumenMaximoParaExportar"
											size="12" readonly="readonly"> 
								</td>											
							</tr>																								
						</table>					
					</td>
				</tr>	
														
				<tr>
					<td height="15"></td>
				</tr>				
			</table>				
			
		</c:when>	
		<c:otherwise>
			<bean:message key='SIIF.error.NoExiFis'/>
		</c:otherwise>
	</c:choose>	
<script type="text/javascript">
	var volTotalFiscalizado = $("#idVolumentTotalFiscalizado").val();
	volTotalFiscalizado = roundNumber(volTotalFiscalizado,2);
	$("#idVolumentTotalFiscalizado").val(volTotalFiscalizado);

	var volHabParaCosecha = $("#idVolHabParaCosecha").val();
	volHabParaCosecha = roundNumber(volHabParaCosecha,2);
	$("#idVolHabParaCosecha").val(volHabParaCosecha);
	
	var volHabProcesado = $("#idVolHabProc").val();
	volHabProcesado = roundNumber(volHabProcesado,2);
	$("#idVolHabProc").val(volHabProcesado);	

	var volExp = $("#idVolExp").val();
	var volMaxExp = volHabProcesado - volExp;

	$("#idVolPorExp").val(roundNumber(volMaxExp,2));
</script>