<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>
<html:form action="aforo" styleId="aforoForm">
	<html:hidden property="metodo" value="modificacionAforo" />
	<html:hidden property="aforo.id" value="${aforoParam.id}" />
	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" height="15"></td>
		</tr>
		<tr>
			<td width="35%" class="botoneralNegritaRight">Valor de Aforo $</td>
			<td><input name="aforo.valorAforo" class="botonerab" type="text"
				size="30" value="<c:out value='${aforoParam.valorAforo}'></c:out>"></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Tipo de Producto Forestal</td>
			<td><html:select styleClass="botonerab"
				property="idTipoProductoForestal"
				value="${aforoParam.tipoProducto.id}">

				<c:forEach items="${tiposProducto}" var="tipoProducto">
					<html:option value="${tipoProducto.id}">
						<c:out value="${tipoProducto.nombre}"></c:out>
					</html:option>
				</c:forEach>

			</html:select></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Estado</td>
			<td><select class="botonerab" name="aforo.estado">

				<c:choose>
					<c:when test="${aforoParam.estado == 'Seco'}">
						<option selected="selected" value="Seco">Seco</option>
						<option value="Verde">Verde</option>
					</c:when>
					<c:otherwise>
						<option value="Seco">Seco</option>
						<option selected="selected" value="Verde">Verde</option>
					</c:otherwise>
				</c:choose>
			</select></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Tipo de Productor</td>
			<td><select class="botonerab" name="aforo.tipoProductor">
				<c:choose>
					<c:when test="${aforoParam.tipoProductor == 'PPF'}">
						<option value="OBR">Obrajero</option>
						<option selected="selected" value="PPF">Pequeño Productor
						Forestal</option>
					</c:when>
					<c:otherwise>
						<option selected="selected" value="OBR">Obrajero</option>
						<option value="PPF">Pequeño Productor Forestal</option>
					</c:otherwise>
				</c:choose>
			</select></td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" class="botonerab"
				value="Modificar" id="enviar" onclick="javascript:submitir();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>
</html:form>
