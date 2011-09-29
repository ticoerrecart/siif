<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript">

function submitir(){

	validarForm("tipoProductoForestalForm","../tipoProductoForestal","validarTipoProductoForestalForm","TipoProductoForestalForm");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="tipoProductoForestal"
	styleId="tipoProductoForestalForm">
	<html:hidden property="metodo" value="altaTipoProductoForestal" />
	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				<bean:message key='SIIF.titulo.AltaTipoProducto'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoProducto'/></td>
			<td align="left"><input name="productoForestal.nombre"
				class="botonerab" type="text" size="30"></td>
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
