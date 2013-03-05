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

function submitir(metodoValidacion){

	$('#exitoGrabado').val("");
	validarForm("tipoProductoForestalForm","../tipoProductoForestal",metodoValidacion,"TipoProductoForestalForm");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="tipoProductoForestal"
	styleId="tipoProductoForestalForm">
	<html:hidden property="metodo" value="${metodo}" />
	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				<c:choose>
					<c:when test="${metodo == 'altaTipoProductoForestal'}">
						<bean:message key='SIIF.titulo.AltaTipoProducto'/>
						<c:set var="metodoValidacion" value="${'validarTipoProductoForestalForm'}" />
					</c:when>
					<c:otherwise>
						<bean:message key='SIIF.titulo.AltaTipoProductoExportacion'/>
						<c:set var="metodoValidacion" value="${'validarTipoProductoExportacionForm'}" />
					</c:otherwise>
				</c:choose>																		
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">
				<c:choose>
					<c:when test="${metodo == 'altaTipoProductoForestal'}">
						<bean:message key='SIIF.label.TipoProducto'/>
					</c:when>
					<c:otherwise>
						<bean:message key='SIIF.label.TipoProductoExportacion'/>
					</c:otherwise>
				</c:choose>										
			</td>
			<td align="left">
				<input name="productoForestalDTO.nombre" class="botonerab" type="text" size="30" 
						onkeypress="return evitarAutoSubmit(event)">
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2">
				<input type="button" class="botonerab" value="Aceptar" id="enviar" 
						onclick="javascript:submitir('<c:out value="${metodoValidacion}"></c:out>');"> 
				<input type="button" class="botonerab" value="Cancelar" 
						onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>
</html:form>
