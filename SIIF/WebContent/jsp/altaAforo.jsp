<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript">

function submitir(){

	validarForm("aforoForm","../aforo","validarAforoForm","AforoForm");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="aforo" styleId="aforoForm">
	<html:hidden property="metodo" value="altaAforo" />
	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">Alta de Aforo</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Valor Aforo $</td>
			<td align="left"><input name="aforo.valorAforo"
				class="botonerab" type="text" size="30"></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Tipo de Producto Forestal</td>
			<td align="left"><html:select styleClass="botonerab"
				property="idTipoProductoForestal">

				<c:forEach items="${tiposProducto}" var="tipoProducto">
					<html:option value="${tipoProducto.id}">
						<c:out value="${tipoProducto.nombre}"></c:out>
					</html:option>
				</c:forEach>

			</html:select></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Estado</td>
			<td align="left"><select class="botonerab" name="aforo.estado">
				<option value="Seco">Seco</option>
				<option value="Verde">Verde</option>
			</select></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Tipo Productor</td>
			<td align="left"><select class="botonerab"
				name="aforo.tipoProductor">
				<option value="OBR">Obrajero</option>
				<option value="PPF">Pequeño Productor Forestal</option>
			</select></td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2"><input type="button"
				class="botonerab" value="Aceptar" id="enviar"
				onclick="javascript:submitir();"> <input type="button"
				class="botonerab" value="Cancelar"
				onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>
</html:form>
