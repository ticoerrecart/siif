<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<table border="0" class="cuadradoSinBorde" align="center" width="60%" cellpadding="2">
				<tr>
					<td class="botoneralNegritaLeftGrande">
						Fiscalizaciones
					</td>
				</tr>
			</table>			
			<table border="0" class="cuadrado" 
				align="center" width="60%" cellpadding="2">
				<tr>
					<td class="azulAjustado">Fecha</td>
					<td class="azulAjustado">Productor Forestal</td>
					<td class="azulAjustado">Nro Gu�a</td>
					<td class="azulAjustado"></td>
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
							<a href="../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&idFiscalizacion=<c:out value='${fiscalizacion.id}'></c:out>&paramForward=<c:out value='${paramForward}'></c:out>">
								Ver
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>	
		<c:otherwise>
			No existen Fiscalizaciones para esta localidad
		</c:otherwise>
	</c:choose>	
