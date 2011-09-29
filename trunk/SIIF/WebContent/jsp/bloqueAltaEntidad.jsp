<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript">
	function submitir(){
		validarForm("entidadFormId","../entidad","validarEntidadForm","EntidadForm");
	}
</script>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>
<html:form action="entidad" styleId="entidadFormId">
	<html:hidden property="metodo" value="${metodo}" />
	<html:hidden property="entidad.id" value="${entidad.id}" />

	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">${titulo}</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="45%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoEntidad'/></td>
			<td align="left"><c:choose>
				<c:when test="${metodo=='altaEntidad'}">
					<html:select property="tipoEntidad" styleClass="botonerab"
						value="${entidad.idTipoEntidad}">
						<html:option value="OBR">Obrajero</html:option>
						<html:option value="PPF">Pequeño Prod. Forestal</html:option>
						<html:option value="RN">Recursos Naturales</html:option>
					</html:select>
				</c:when>
				<c:otherwise>
					<!-- AL MODIFICAR NO PUEDO CAMBIAR EL TIPO DE ENTIDAD -->
					<html:select property="tipoEntidad" styleClass="botonerab"
						value="${entidad.idTipoEntidad}">
						<html:option value="${entidad.idTipoEntidad}">${entidad.tipoEntidad}</html:option>
					</html:select>
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Nombre'/></td>
			<td align="left"><html:text property="entidad.nombre"
				styleClass="botonerab" value="${entidad.nombre}" /></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
			<td align="left"><html:select styleClass="botonerab"
				property="idLocalidad" value="${entidad.localidad.id}">
				<c:forEach items="${localidades}" var="localidad">
					<html:option value="${localidad.id}">
						<c:out value="${localidad.nombre}" />
					</html:option>
				</c:forEach>

			</html:select></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Direccion'/></td>
			<td align="left"><html:text property="entidad.direccion"
				styleClass="botonerab" value="${entidad.direccion}" /></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Telefono'/></td>
			<td align="left"><html:text property="entidad.telefono"
				styleClass="botonerab" value="${entidad.telefono}" /></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.EMail'/></td>
			<td align="left"><html:text property="entidad.email"
				styleClass="botonerab" value="${entidad.email}" /></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.ConfirmacionEMail'/></td>
			<td align="left"><html:text property="confirmacionEmail"
				styleClass="botonerab" value="${entidad.email}" /></td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2"><c:choose>
				<c:when test="${metodo=='altaEntidad'}">
					<input type="button" class="botonerab" value="Aceptar" id="enviar"
						onclick="javascript:submitir();">
					<input type="button" class="botonerab" value="Cancelar"
						onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">
				</c:when>
				<c:otherwise>
					<input type="button" class="botonerab" value="Aceptar" id="enviar"
						onclick="javascript:submitir();">
				</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>

</html:form>
