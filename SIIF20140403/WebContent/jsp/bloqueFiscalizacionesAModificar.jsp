<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<table border="0" class="cuadrado" 
				align="center" width="90%" cellpadding="2">
				<tr>
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.Fecha'/></td>
					<td class="azulAjustado" colspan="5"><bean:message key='SIIF.label.Localizacion'/></td>
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.NroDeGuia'/></td>
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.TipoDeProducto'/></td>
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.CantMts3'/></td>					
					<td class="azulAjustado" rowspan="2"></td>
				</tr>
				<tr>
					<td class="azulAjustado"><bean:message key='SIIF.label.PMF'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Tranzon'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Marcacion'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Rodal'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.AreaDeCosecha'/></td>
				</tr>
				<%String clase=""; %>
				<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
					<%clase=(clase.equals("")?"par":""); %>
					<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
						onmouseover="javascript:mostrarDatos(<c:out value='${i.count}'></c:out>);">
						<td class="botonerab">
							<fmt:formatDate	value='${fiscalizacion.fecha}' pattern='dd/MM/yyyy' />
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.expedientePMF}"></c:out>-
							<c:out value="${fiscalizacion.localizacion.nombrePMF}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.numeroTranzon}"></c:out>-
							<c:out value="${fiscalizacion.localizacion.disposicionTranzon}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.disposicionMarcacion}"></c:out>
						</td>						
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.nombreRodal}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.nombreArea}"></c:out>
						</td>
						<td class="botonerab">
							<c:choose>						
								<c:when test="${fiscalizacion.guiaForestal != null}">
									<c:out value="${fiscalizacion.guiaForestal.nroGuia}"></c:out>
								</c:when>
								<c:otherwise>---</c:otherwise>	
							</c:choose>								
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.cantidadMts}"></c:out>
						</td>
						
						
						<td class="botonerab">
							<a href="../../fiscalizacion.do?metodo=cargarFiscalizacionAModificar&id=<c:out value='${fiscalizacion.id}'></c:out>">
								<bean:message key='SIIF.label.Editar'/>
							</a>
						</td>
					</tr>
					
				</c:forEach>
			</table>
		</c:when>	
		<c:otherwise>
			<bean:message key='SIIF.error.NoExiFis'/>
		</c:otherwise>
	</c:choose>	
