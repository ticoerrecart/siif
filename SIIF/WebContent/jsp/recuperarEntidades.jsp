<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/jquery-1.3.2.min.js'/>"></script>

<script>
	var tr = null;
	var clase = null;
	function mostrarDatos(idEntidad,idTr){
		$('#exitoGrabado').empty();
		if(tr!=null){
			$('#tr'+tr).attr("class", clase);	
		}
		tr=idTr;
		clase = $('#tr'+tr).attr("class");
		$('#tr'+tr).attr("class", "seleccionado");
		
		$('#divModificacion').load('../../entidad.do?metodo=cargarEntidadAModificar&id=' + idEntidad);
		
		$('#divModificacion').hide();
		$('#divModificacion').fadeIn(600);
	}
</script>


<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<table border="0" class="cuadrado" align="center" width="80%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado">Modificaci�n de Entidad</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
		<table border="0" class="cuadrado" align="center" width="90%"
			cellpadding="2">
			<tr>
				<td class="azulAjustado">Nombre</td>
				<td class="azulAjustado">Direcci�n</td>
				<td class="azulAjustado">Localidad</td>
				<td class="azulAjustado">Tel�fono</td>
				<td class="azulAjustado">E-Mail</td>
				<td class="azulAjustado">Tipo de Entidad</td>
				<td class="azulAjustado"></td>
			</tr>
			<%String clase=""; %>
			<c:forEach items="${entidades}" var="entidad" varStatus="i">
				<%clase=(clase.equals("")?"par":""); %>
				<tr id="tr${i.count}" class="botonerab <%=clase%>">
					<td><c:out value="${entidad.nombre}" /></td>
					<td><c:out value="${entidad.direccion}" /></td>
					<td><c:out value="${entidad.localidad.nombre}" /></td>
					<td><c:out value="${entidad.telefono}" /></td>
					<td><c:out value="${entidad.email}" /></td>
					<td><c:out value="${entidad.tipoEntidad}" /></td>
					<td><!--a href="../../entidad.do?metodo=cargarEntidadAModificar&id=<c:out value='${entidad.id}'/>">Editar</a-->
					<a href="javascript:mostrarDatos(${entidad.id},${i.count});">Editar</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
		<div id="divModificacion"></div>
		</td>
	</tr>
</table>

