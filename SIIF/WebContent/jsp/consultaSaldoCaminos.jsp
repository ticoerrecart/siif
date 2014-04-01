<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>





<table border="0" class="cuadrado" align="center" width="50%"
	cellpadding="2">
	<tr>
		<td colspan="2" class="azulAjustado">Consulta Saldo por Construcción de Caminos de 2do Orden</td>
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
	<tr>
		<td class="grisSubtitulo">
			<bean:message key='SIIF.label.ProductorForestal' />
		</td>
		<td class="grisSubtitulo"> Saldo</td>
	</tr>


	<c:forEach items="${productores}" var="entidad">
		<tr>
			<td align="left"><c:out value="${entidad.nombre}"></c:out></td>
				<c:if test="${entidad.saldoXCaminos ge 0 }">			
						<td class="verdeExito" style="text-align: right;" >
							${entidad.saldoXCaminos}
						</td>
					</c:if>
					<c:if test="${entidad.saldoXCaminos lt 0 }">			
						<td class="rojoAdvertencia" style="text-align: right;">
							${entidad.saldoXCaminos}
						</td>
					</c:if>
		</tr>
	</c:forEach>
</table>

