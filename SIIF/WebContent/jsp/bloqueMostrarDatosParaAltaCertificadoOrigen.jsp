<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

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
					<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.NroDeGuia'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.TipoDeProducto'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.CantMts3'/></td>					
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
						value="<c:out value="${volFiscalizadoTotal}"></c:out>">
					</td>					
				</tr>				
				
			</table>
			<br>
			<br>
			
		<!-- GUIAS FORESTALES -->				
			<table border="0" class="cuadradoSinBorde" align="center" width="75%" cellpadding="2">
				<tr>
					<td class="botoneralNegritaLeftGrande">
						<bean:message key='SIIF.subTitulo.GuiasForestales'/>
					</td>
				</tr>
			</table>			
			<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2">
				<tr>
					<td class="azulAjustado"><bean:message key='SIIF.label.NroDeGuia'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.FechaVenc'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Volumen'/></td>					
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
			<br>
						
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
					<td width="5%"></td>	
					
					<c:choose>
						<c:when test="${deuda}">
							<td width="55%" class="botoneralNegritaLeft">
								<bean:message key='SIIF.label.ActaReconocimientoDeuda'/>						
							</td>
							<td width="40%" class="botonerab" align="left">
								<input type="hidden" value="N" id="idRadioDeuda">
								<input type="radio" class="botonerab" name="radioDeuda" onclick="cambiarRadio('S');">SI
								/
								<input type="radio" class="botonerab" checked="checked" name="radioDeuda" onclick="cambiarRadio('N');">NO
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
						<input class="botonerab" type="text" size="27" id="idOrigenMateriaPrima" value="Tierra del Fuego">
					</td>
					
					<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroRemito'/></td>
					<td width="32%" align="center">
						<input class="botonerab" type="text" size="27" id="idNroRemito" onkeypress="javascript:esNumerico(event);">
					</td>																
				</tr>	
				
				<tr>
					<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProvinciaDestino'/></td>
					<td width="30%">
						<select id="idProvincia" class="botonerab" onchange="">
							<option value="-1">-Seleccione una Provincia-</option>
						</select>
					</td>
					
					<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.LocalidadDestino'/></td>
					<td width="32%" align="center">
						<select id="idLocalidad" class="botonerab" onchange="" disabled="disabled">
							<option value="-1">-Seleccione una Localidad-</option>
						</select>																
					</td>	
				</tr>
										
				<tr>
					<td colspan="4" height="15"></td>
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
</script>