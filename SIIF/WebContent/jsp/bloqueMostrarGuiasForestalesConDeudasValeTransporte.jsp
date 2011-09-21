<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>
<!-- <td colspan="2"> -->
<br>
<c:choose>
	<c:when test="${fn:length(guiasForestales)>0}">
		<table border="0" class="cuadrado" align="center" width="80%"
			cellpadding="2">
			<tr>
				<td class="azulAjustado">Nro de Guía</td>			
				<td class="azulAjustado">Fecha</td>
				<td class="azulAjustado">Fecha de Vencimiento</td>	
				<td class="azulAjustado"></td>
			</tr>
			<%String clase="";%>
			<c:forEach items="${guiasForestales}" var="guia" varStatus="i">
				<%clase=(clase.equals("")?"par":""); %>
				<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
					onmouseover="javascript:mostrarDatos(<c:out value='${i.count}'></c:out>);">
					<td class="botonerab"><c:out value="${guia.nroGuia}" /></td>				
					<td class="botonerab">
						<fmt:formatDate value="${guia.fecha}" pattern="dd/MM/yyyy" />
					</td>
					<td class="botonerab">
						<fmt:formatDate value="${guia.fechaVencimiento}" pattern="dd/MM/yyyy" />
					</td>									
					<td>
						<a href="../../consultasPorProductor.do?metodo=cargarGuiaForestal&idGuia=<c:out value='${guia.id}'></c:out>&paramForward=<c:out value='${paramForward}'></c:out>">
							Ver
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		No existen Guías Forestales con Deudas de Vales de Transporte para este Productor Forestal
	</c:otherwise>	
</c:choose>
<!-- </td> -->
