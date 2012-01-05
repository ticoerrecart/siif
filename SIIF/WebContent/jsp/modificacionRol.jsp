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

<script type="text/javascript">

var cantMenues = 0;
var tr = null;
var clase = null;
	
function mostrarDatos(idRol,idTr){

	$('#exitoGrabado').html("");
	$('#errores').html("");
	
	if(tr!=null){
		$('#tr'+tr).attr("class", clase);	
	}
	tr=idTr;
	clase = $('#tr'+tr).attr("class");
	$('#tr'+tr).attr("class", "seleccionado");

	$('#divCargando').show();	
	$('#divModificacion').html("");
	
	$('#divModificacion').load('../../recuperarRol.do?metodo=recuperarRol&id='+idRol);
	$('#divModificacion').hide();
	$('#divModificacion').fadeIn(600);

	Concurrent.Thread.create(function(){
	    while ($('#divModificacion').html() == "") {}
	    $('#divCargando').hide();
	});
}

function checkMenu(id,value){
	if(!$('#menu'+id).attr("checked")){
		$('#menu'+id).val(null);
	}else{
		$('#menu'+id).val(value);	
	}		
}

function seleccionarTodos(){
	for(i=0;i<cantMenues-1;i++){
		$('#menu'+i).attr("checked","checked");
	}
}

function quitarSeleccion(){
	for(i=0;i<cantMenues-1;i++){
		$('#menu'+i).attr("checked","");
	}
}

function submitir(){

	validarForm("rolForm","../rol","validarRolForm","RolForm");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>



<table border="0" class="cuadrado" align="center" width="60%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado">
			<bean:message key='SIIF.titulo.ModificacionRol'/>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
		<table id="idRoles" border="0" class="cuadrado" align="center"
			width="80%" cellpadding="2">
			<tr>
				<td class="azulAjustado"><bean:message key='SIIF.label.Rol'/></td>
				<td class="azulAjustado"></td>
			</tr>

			<c:forEach items="${roles}" var="rol" varStatus="i">

				<tr id="tr<c:out value='${i.count}'></c:out>">
					<td class="botonerab"><c:out value="${rol.rol}"></c:out></td>
					<td><a href="javascript:mostrarDatos(<c:out value='${rol.id}'></c:out>,<c:out value='${i.count}'></c:out>);">
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
	$("#idRoles tr:nth-child(even)").addClass("par"); //Para pintar en cebra los tr de la tabla
</script>
