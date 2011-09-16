<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>
<html:form action="tipoProductoForestal"
	styleId="tipoProductoForestalForm">
	<html:hidden property="metodo" value="modificacionTipoProductoForestal" />
	<html:hidden property="productoForestal.id" value="${tipoProducto.id}" />
	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="3" height="15"></td>
		</tr>
		<tr>
			<td width="35%" class="botoneralNegritaRight">Tipo Producto
			Forestal</td>
			<td><input name="productoForestal.nombre" class="botonerab"
				type="text" size="30"
				value="<c:out value='${tipoProducto.nombre}'></c:out>"></td>
			<td width="10%"></td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="3"><input type="button" class="botonerab"
				value="Modificar" id="enviar" onclick="javascript:submitir();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
	</table>
</html:form>
