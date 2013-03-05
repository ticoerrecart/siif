<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>



<br>
<c:choose>
	<c:when test="${fn:length(certificados)>0}">
		<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
			<tr>
				<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.NroDeCertificado'/></td>			
				<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.Fecha'/></td>
				<td class="verdeTituloTablaChico"><bean:message key='SIIF.label.VolExportado'/></td>				
				<td class="verdeTituloTablaChico"></td>
			</tr>
			
			<%String clase="";%>
			<c:forEach items="${certificados}" var="certificado" varStatus="i">
				<%clase=(clase.equals("")?"par":""); %>
				<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
					onmouseover="javascript:pintarFila(<c:out value='${i.count}'></c:out>);">
					
					<td class="botonerab">
						<c:out value="${certificado.nroCertificado}" />
					</td>				
					<td class="botonerab">
						<c:out value="${certificado.fecha}"></c:out>						
					</td>
					<td class="botonerab">
						<c:out value="${certificado.volumenTotalTipoProductos}"></c:out>						
					</td>					
					<td>
						<a href="../../certificadoOrigen.do?metodo=cargarCertificadoOrigen&idCertificado=<c:out value='${certificado.id}'></c:out>">
							<bean:message key='SIIF.label.Ver'/>
						</a>
					</td>
				</tr>
			</c:forEach>			
			
		</table>
	</c:when>
	<c:otherwise>
		<bean:message key='SIIF.error.NoExiCOParaPF'/>
	</c:otherwise>	
</c:choose>

