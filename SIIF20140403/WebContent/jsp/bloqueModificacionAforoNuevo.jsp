<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
 
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>
<html:form action="aforoNuevo" styleId="aforoNuevoForm">
	<html:hidden property="metodo" value="modificacionAforo" />
	<html:hidden property="aforoDTO.id" value="${aforoParam.id}" />
	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="2" height="15"></td>
		</tr>
		<tr>
			<td width="35%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.ValorAforo$'/>
			</td>
			<td align="left">
				<input name="aforoDTO.monto" class="botonerab" type="text" size="30" 
						value="<c:out value='${aforoParam.monto}'></c:out>" 
						onkeypress="esNumericoConDecimal(event); return evitarAutoSubmit(event)"
						onkeyup="javascript: twoDigits(this);">
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.Porcentaje'/>
			</td>
			<td align="left">
				<input name="aforoDTO.porcentaje" class="botonerab" type="text" size="30" 
						value="<c:out value='${aforoParam.porcentaje}'></c:out>" 
						onkeypress="esNumerico(event); return evitarAutoSubmit(event)"
						onkeyup="javascript: esNumerico(this);">
			</td>
		</tr>
		
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" class="botonerab" value="Modificar" 
					   id="enviar" onclick="javascript:submitir();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>
</html:form>
