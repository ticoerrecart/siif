<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/calendario.tld" prefix="cal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<table border="0" class="cuadrado" 
				align="center" width="60%" cellpadding="2">
				<tr>
					<td class="azulAjustado">Fecha</td>
					<td class="azulAjustado">Productor Forestal</td>
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
							<a href="../../fiscalizacion.do?metodo=cargarFiscalizacionAModificar&id=<c:out value='${fiscalizacion.id}'></c:out>">
								Editar
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
