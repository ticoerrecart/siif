<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<table border="0" class="cuadradoSinBorde" align="center" width="60%" cellpadding="2">
				<tr>
					<td class="botoneralNegritaLeftGrande">
						<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>
					</td>
				</tr>
			</table>			
			<table border="0" class="cuadrado" 
				align="center" width="60%" cellpadding="2">
				<tr>
					<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.ProductorForestal'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.NroDeGuia'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.TipoDeProducto'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.CantMts3'/></td>					
					<td class="azulAjustado"></td>
				</tr>
				<%String clase=""; %>
				<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
					<%clase=(clase.equals("")?"par":""); %>
					<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
						onmouseover="javascript:mostrarDatos(<c:out value='${i.count}'></c:out>);">
						<td class="botonerab">
							<c:out value="${fiscalizacion.fecha}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.productorForestal.nombre}"></c:out>
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
						<td class="botonerab">
							<a href="../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&idFiscalizacion=<c:out value='${fiscalizacion.id}'></c:out>&paramForward=<c:out value='${paramForward}'></c:out>">
								<bean:message key='SIIF.label.Ver'/>
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
