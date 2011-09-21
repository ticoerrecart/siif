<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>

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
	if(idProductor != "" && idProductor != "-1"){
		//Se tiene que cambiar el metodo "recuperarFiscalizacionesAModificar" se le tiene q enviar el idProductor.
		$('#divFiscalizaciones').load('../../fiscalizacion.do?metodo=recuperarFiscalizacionesAModificar&idProductor='+idProductor);
		$('#divFiscalizaciones').hide();
		$('#divFiscalizaciones').fadeIn(600);
	}else{
		$('#divFiscalizaciones').hide(600);
		$('#divFiscalizaciones').html("");
	}	
}

function cargarProductores(){

	var idLocalidad = $('#selectLocalidades').val();
	if(idLocalidad != "-1"){
		$('#selectProductores').attr('disabled','');
		EntidadFachada.getEntidadesPorLocalidad(idLocalidad,actualizarProductoresCallback );		
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

	var idLocalidad = $('#selectLocalidades').val();

	$('#selectProductores').attr('disabled','');
	EntidadFachada.getEntidadesPorLocalidad(idLocalidad,actualizarProductoresVolverCallback );		

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
<input id="paramLocalidad" type="hidden" value="${idLocalidad}">
<input id="paramProductor" type="hidden" value="${idProductor}">
<br>
<table border="0" class="cuadrado" align="center" width="70%"
	cellpadding="2">
	<tr>
		<td class="azulAjustado">Modificación de Fiscalización de
		Productos Forestales</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
		<!-- <table border="0" class="cuadradoSinBorde" align="center" width="60%"
			cellpadding="2">
			<tr>
				<td class="azulAjustado">Fecha</td>
				<td class="azulAjustado">Productor Forestal</td>
				<td class="azulAjustado"></td>
			</tr>
			<%String clase=""; %>
			<c:forEach items="${fiscalizaciones}" var="fiscalizacion">
				<%clase=(clase.equals("")?"par":""); %>
				<tr>
					<td class="botonerab <%=clase%>"><fmt:formatDate
						value='${fiscalizacion.fecha}' pattern='dd/MM/yyyy' /></td>
					<td class="botonerab <%=clase%>"><c:out
						value="${fiscalizacion.productorForestal.nombre}"></c:out></td>
					<td class="<%=clase%>"><a
						href="../../fiscalizacion.do?metodo=cargarFiscalizacionAModificar&id=<c:out value='${fiscalizacion.id}'></c:out>">Editar</a></td>
				</tr>
			</c:forEach>
		</table>-->
		
			<table border="0" class="cuadrado" align="center" width="60%"
				cellpadding="2">
				<tr>
					<td height="10" colspan="3"></td>
				</tr>
				<tr>
					<td class="botoneralNegritaRight" width="30%">Localidad</td>
					<td class="botonerab">
						<select id="selectLocalidades" class="botonerab" onchange="cargarProductores()">
							<option value="-1">-Seleccione una Localidad-</option>
							<c:forEach items="${localidades}" var="localidad" varStatus="i">
														
								<c:choose>
									<c:when test="${localidad.id == idLocalidad}">
										<option value="<c:out value='${localidad.id}'></c:out>" selected="selected">
											<c:out value="${localidad.nombre}"></c:out>
										</option>
									</c:when>
									<c:otherwise>
										<option value="<c:out value='${localidad.id}'></c:out>">
											<c:out value="${localidad.nombre}"></c:out>
										</option>								
									</c:otherwise>
								</c:choose>									
								
							</c:forEach>
						</select>
					</td>
					<td width="15%"></td>					
				</tr>
				
				<tr>
					<td class="botoneralNegritaRight">Productor Forestal</td>
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
		<td>
			<div id="divFiscalizaciones"></div>		
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>	
</table>
<script type="text/javascript">

var idLocalidad = $('#paramLocalidad').val();
if(idLocalidad != null && idLocalidad != ""){
	cargarProductoresVolver();
}

</script>
