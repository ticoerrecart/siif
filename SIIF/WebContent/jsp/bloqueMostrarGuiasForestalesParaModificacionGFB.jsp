<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>

<br>
<c:choose>
	<c:when test="${fn:length(guiasForestales)>0}">
		<table border="0" class="cuadrado" align="center" width="80%"
			cellpadding="2">
			<tr>
				<td class="azulAjustado"><bean:message key='SIIF.label.NroDeGuia'/></td>			
				<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
				<td class="azulAjustado"><bean:message key='SIIF.label.FechaVenc'/></td>		
				<td class="azulAjustado"></td>
			</tr>
			<%String clase="";%>
			<c:forEach items="${guiasForestales}" var="guia" varStatus="i">
				<%clase=(clase.equals("")?"par":""); %>
				<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"   
					onmouseover="javascript:mostrarDatos(<c:out value='${i.count}'></c:out>);">
					<td class="botonerab"><c:out value="${guia.nroGuia}" /></td>	   			
					<td class="botonerab">
						<c:out value="${guia.fecha}"></c:out>  
					</td>
					<td class="botonerab">
						<c:out value="${guia.fechaVencimiento}"></c:out>					
					</td>										
					<td>			
						<a href="../../guiaForestal.do?metodo=cargarModificacionGuiaForestalBasica&idGuia=<c:out value='${guia.id}'></c:out>">
							<bean:message key='SIIF.label.Seleccionar'/>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<bean:message key='SIIF.error.NoExiGFParaPF'/>
	</c:otherwise>	
</c:choose>

