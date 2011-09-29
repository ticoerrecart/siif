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

<script type="text/javascript">

function cambioTipoProducto(){

    var idTipoProducto = document.forms[0].elements["idTipoProductoForestal"].value;
    var url = "../recuperarTipoProductoForestal.do?metodo=validarCierreOperativo&nomOp=" + escape(nomOp);	
	
	$.post(url,null,validarFormCallBack);	
}

var tr = null;
var clase = null;
	
function mostrarDatos(idTipoProducto,idTr){

	$('#exitoGrabado').html("");
	$('#errores').html("");
	
	if(tr!=null){
		$('#tr'+tr).attr("class", clase);	
	}
	tr=idTr;
	clase = $('#tr'+tr).attr("class");
	$('#tr'+tr).attr("class", "seleccionado");
	
	$('#divModificacion').load('../../recuperarTipoProductoForestal.do?metodo=recuperarTipoProductoForestal&id='+idTipoProducto);
	$('#divModificacion').hide();
	$('#divModificacion').fadeIn(600);
}

function submitir(){

	validarForm("tipoProductoForestalForm","../tipoProductoForestal","validarTipoProductoForestalForm","TipoProductoForestalForm");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>



<table border="0" class="cuadrado" align="center" width="60%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado"><bean:message key='SIIF.titulo.ModificacionTipoProducto'/></td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
		<table id="idTipoProducto" border="0" class="cuadrado" align="center"
			width="60%" cellpadding="2">
			<tr>
				<td class="azulAjustado"><bean:message key='SIIF.label.TipoProducto'/></td>
				<td class="azulAjustado"></td>
			</tr>

			<c:forEach items="${tiposProducto}" var="tipoProducto" varStatus="i">

				<tr id="tr<c:out value='${i.count}'></c:out>">
					<td class="botonerab"><c:out value="${tipoProducto.nombre}"></c:out></td>
					<td>
						<a href="javascript:mostrarDatos(<c:out value='${tipoProducto.id}'></c:out>,<c:out value='${i.count}'></c:out>);">
							<bean:message key='SIIF.label.Editar'/>
						</a>
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
<script type="text/javascript">
	$("#idTipoProducto tr:nth-child(even)").addClass("par"); //Para pintar en cebra los tr de la tabla
</script>
