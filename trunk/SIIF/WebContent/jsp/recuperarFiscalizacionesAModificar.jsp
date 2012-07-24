<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/Concurrent.Thread-full-20090713.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

var tr = null;
var clase = null;
function mostrarDatos(idTr){

	if(tr!=null){
		$('#tr'+tr).attr("class", clase);	
	}
	tr=idTr;
	clase = $('#tr'+tr).attr("class");
	$('#tr'+tr).attr("class", "seleccionado");	
}

function mostrarFiscalizaciones(){

	var idProductor = $('#selectProductores').val();
	$('#divCargando').show();	
	$('#divFiscalizaciones').html("");
	
	if(idProductor != "" && idProductor != "-1"){
		//Se tiene que cambiar el metodo "recuperarFiscalizacionesAModificar" se le tiene q enviar el idProductor.
		$('#divFiscalizaciones').load('../../fiscalizacion.do?metodo=recuperarFiscalizacionesAModificar&idProductor='+idProductor);
		$('#divFiscalizaciones').hide();
		$('#divFiscalizaciones').fadeIn(600);

		Concurrent.Thread.create(function(){
		    while ($('#divFiscalizaciones').html() == "") {}
		    $('#divCargando').hide();
		});		
		
	}else{
		$('#divFiscalizaciones').hide(600);
		$('#divFiscalizaciones').html("");
		$('#divCargando').hide();
	}	
}

function cargarProductores(){

	var idTipoDeEntidad = $('#selectTiposDeEntidad').val();
	if(idTipoDeEntidad != "-1"){
		$('#selectProductores').attr('disabled','');
		EntidadFachada.getEntidadesPorTipoDeEntidad(idTipoDeEntidad,actualizarProductoresCallback );		
	}else{
		dwr.util.removeAllOptions("selectProductores");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("selectProductores", data, "id", "nombre");		
		$('#selectProductores').attr('disabled','disabled');
	}		
	$('#divFiscalizaciones').hide(600);
	$('#divFiscalizaciones').html("");
}

function actualizarProductoresCallback(productores){
	dwr.util.removeAllOptions("selectProductores");
	var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
	dwr.util.addOptions("selectProductores", data, "id", "nombre");	
	dwr.util.addOptions("selectProductores", productores,"id","nombre");	
}

function cargarProductoresVolver(){
	$('#selectTiposDeEntidad').val($('#paramIdTipoDeEntidad').val());
	var idTipoDeEntidad = $('#paramIdTipoDeEntidad').val();

	$('#selectProductores').attr('disabled','');
	EntidadFachada.getEntidadesPorTipoDeEntidad(idTipoDeEntidad,actualizarProductoresVolverCallback );		

	$('#divGuias').hide(600);
	$('#divGuias').html("");
}

function actualizarProductoresVolverCallback(productores){
	dwr.util.removeAllOptions("selectProductores");
	var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
	dwr.util.addOptions("selectProductores", data, "id", "nombre");	
	dwr.util.addOptions("selectProductores", productores,"id","nombre");

	$('#selectProductores').val($('#paramProductor').val());
	mostrarFiscalizaciones();		
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoModificacion}</div>
<div id="errores" class="rojoAdvertencia">${errorModificacion}</div>
<input id="paramIdTipoDeEntidad" type="hidden" value="${idTipoDeEntidad}">
<input id="paramProductor" type="hidden" value="${idProductor}">
<br>
<table border="0" class="cuadrado" align="center" width="70%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado">
			<bean:message key='SIIF.titulo.ModifFiscalizacion'/>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<table border="0" class="cuadrado" align="center" width="60%"
				cellpadding="2">
				<tr>
					<td height="10" colspan="3"></td>
				</tr>
				<tr>
					<td class="botoneralNegritaRight" width="30%"><bean:message key='SIIF.label.TipoEntidad'/></td>
					<td class="botonerab">
						<select id="selectTiposDeEntidad" class="botonerab" onchange="cargarProductores()">
							<option value="-1">-Seleccione un Tipo de Entidad-</option>
							<c:forEach items="${tiposDeEntidad}" var="tipoDeEntidad" varStatus="i">
														
								<c:choose>
									<c:when test="${tipoDeEntidad.name == idLocalidad}">
										<option value="<c:out value='${tipoDeEntidad.name}'></c:out>" selected="selected">
											<c:out value="${tipoDeEntidad.descripcion}"></c:out>
										</option>
									</c:when>
									<c:otherwise>
										<option value="<c:out value='${tipoDeEntidad.name}'></c:out>">
											<c:out value="${tipoDeEntidad.descripcion}"></c:out>
										</option>
									</c:otherwise>
								</c:choose>									
								
							</c:forEach>
						</select>
					</td>
					<td width="15%"></td>					
				</tr>
				
				<tr>
					<td class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
					<td class="botonerab">
						<select id="selectProductores" class="botonerab" disabled="disabled" onchange="mostrarFiscalizaciones()">
							<option value="">-Seleccione un Productor-</option>
						</select>
					</td>
				</tr>				
				 
				<tr>
					<td height="10" colspan="3"></td>
				</tr>
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
		<td>
			<div id="divFiscalizaciones"></div>		
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>	
</table>
<script type="text/javascript">

var idTipoDeEntidad = $('#paramIdTipoDeEntidad').val();
if(idTipoDeEntidad != null && idTipoDeEntidad != ""){
	cargarProductoresVolver();
}

</script>
