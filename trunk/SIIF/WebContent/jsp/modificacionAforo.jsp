<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/jquery-1.3.2.min.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/Concurrent.Thread-full-20090713.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>

<script type="text/javascript">

var tr = null;
var clase = null;
function mostrarDatos(idAforo,idTr){
	$("#errores").html("");
	if(tr!=null){
		$('#tr'+tr).attr("class", clase);	
	}
	tr=idTr;
	clase = $('#tr'+tr).attr("class");
	$('#tr'+tr).attr("class", "seleccionado");

	$('#divCargando').show();	
	$('#divModificacion').html("");
		
	$('#divModificacion').load('../../recuperarAforo.do?metodo=recuperarAforo&id='+idAforo);
	$('#divModificacion').hide();
	$('#divModificacion').fadeIn(600);

	Concurrent.Thread.create(function(){
	    while ($('#divModificacion').html() == "") {}
	    $('#divCargando').hide();
	});
}

function submitir(){

	$('#exitoGrabado').html("");
	$('#errores').html("");
	validarForm("aforoForm","../aforo","validarAforoForm","AforoForm");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>



<table border="0" class="cuadrado" align="center" width="70%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado"><bean:message key='SIIF.titulo.ModificacionAforo'/></td>
	</tr>
	<tr>
		<td height="30"></td>
	</tr>
	<tr>
		<td>
		<table id="idAforo" border="0" class="cuadrado" align="center"
			width="90%" cellpadding="2">
			<tr>
				<td class="azulAjustado" width="25%"><bean:message key='SIIF.label.TipoProductor'/></td>
				<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.TipoProducto'/></td>
				<td class="azulAjustado" width="25%"><bean:message key='SIIF.label.Estado'/></td>
				<td class="azulAjustado" width="12%"><bean:message key='SIIF.label.ValorAforo$'/></td>
				<td class="azulAjustado" width="8%"></td>
			</tr>
			<c:forEach items="${aforos}" var="aforo" varStatus="i">
				<tr id="tr<c:out value='${i.count}'></c:out>">
					<td class="botonerab">
						<c:out value="${aforo.tipoProductorDesc}"></c:out>
					</td>
					<td class="botonerab">
						<c:out value="${aforo.tipoProducto.nombre}"></c:out>
					</td>
					<td class="botonerab"><c:out value="${aforo.estado.descripcion}"></c:out></td>
					<td class="botonerab"><c:out value="${aforo.valorAforo}"></c:out></td>
					<td>
						<a href="javascript:mostrarDatos(<c:out value='${aforo.id}'></c:out>,<c:out value='${i.count}'></c:out>);">
							<bean:message key='SIIF.label.Editar'/>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
	<tr>
		<td height="30"></td>
	</tr>
	<tr>
		<td id="divCargando" style="display: none">
			<img src="<html:rewrite page='/imagenes/cargando.gif'/>">
		</td>
	</tr>
	<tr>
		<td>
			<div id="divModificacion"></div>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>
</table>
<script type="text/javascript">
	$("#idAforo tr:nth-child(even)").addClass("par"); //Para pintar en cebra los tr de la tabla
</script>
