<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/calendario.tld" prefix="cal"%>

<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarNum.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

/*var tr = null;
var clase = null;
function mostrarDatos(idTr,idProductor){

	if(tr!=null){
		$('#tr'+tr).attr("class", clase);
		$('#guias'+tr).toggle();			
	}
	tr=idTr;
	clase = $('#tr'+tr).attr("class");
	$('#tr'+tr).attr("class", "seleccionado"); 	

	$('#guias'+idTr).toggle();

	$('#guias'+idTr).load('../../consultasPorProductor.do?metodo=recuperarGuiasForestalesVigentes&idProductor='+idProductor);
}*/

var tr = null;
var clase = null;
function mostrarDatos(idTr){

	if(tr!=null){
		$('#tr'+tr).attr("class", clase);	
	}
	tr=idTr;
	clase = $('#tr'+tr).attr("class");
	$('#tr'+tr).attr("class", "seleccionado");	
	/*$('#divModificacion').load('../../recuperarAforo.do?metodo=recuperarAforo&id='+idAforo);
	$('#divModificacion').hide();
	$('#divModificacion').fadeIn(600);*/		
}

function mostrarNrosGuias(){

	var metodo = $('#paramForward').val();
	var idProd = $('#selectProductores').val();
	//var idProd = $('#paramProductor').val();
	if(idProd != "" && idProd != "-1"){
		$('#divGuias').load('../../consultasPorProductor.do?metodo='+ metodo +'&idProductor='+idProd);		
		$('#divGuias').hide();
		$('#divGuias').fadeIn(600);
	}else{
		$('#divGuias').hide(600);
		$('#divGuias').html("");
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
	$('#divGuias').hide(600);
	$('#divGuias').html("");
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
	mostrarNrosGuias();		
}

</script>
<input id="paramForward" type="hidden" value="${paramForward}">
<input id="paramLocalidad" type="hidden" value="${idLoc}">
<input id="paramProductor" type="hidden" value="${idProd}">
<div id="errores" class="rojoAdvertencia">${error}</div>
<br>
<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
	<tr>
		<td class="azulAjustado"><c:out value="${titulo}" /></td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td><!--<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
					<tr>
						<td class="azulAjustado" >Productor Forestal</td>
						<td class="azulAjustado" ></td>
					</tr>
					<%String clase=""; %>
					<c:forEach items="${productores}" var="productor" varStatus="i">
					<%clase=(clase.equals("")?"par":""); %>										
						<tr  id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>">							
							<td class="botonerab"><c:out value="${productor.nombre}"/></td>
							<td><a href="javascript:mostrarDatos(<c:out value='${i.count}'></c:out>,<c:out value='${productor.id}'></c:out>);">Seleccionar</a></td>
						</tr>
						<tr id="guias<c:out value='${i.count}'></c:out>" style="display: none;">

						</tr>
					</c:forEach>					
				</table> -->


		<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td class="botoneralNegritaRight">Localidad</td>
				<td class="botonerab">
					<select id="selectLocalidades" class="botonerab" onchange="cargarProductores()">
						<option value="-1">-Seleccione una Localidad-</option>
						<c:forEach items="${localidades}" var="localidad" varStatus="i">
							<c:choose>
								<c:when test="${localidad.id == idLoc}">
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
			</tr>
			<tr>
				<td class="botoneralNegritaRight">Productor Forestal</td>
				<td class="botonerab">
					<select id="selectProductores" class="botonerab" disabled="disabled" onchange="mostrarNrosGuias()">
						<option value="-1">-Seleccione un Productor-</option>
					</select>
				</td>
			</tr>						
			<!-- <tr>
				<td class="botoneralNegritaRight">Productor Forestal</td>
				<td class="botonerab">
					<select id="selectProductores" class="botonerab" onchange="mostrarNrosGuias()">
						<option value="">-Seleccione un Productor-</option>
						<c:forEach items="${productores}" var="productor" varStatus="i">
							<c:choose>
								<c:when test="${productor.id == idProd}">
									<option value="<c:out value='${productor.id}'></c:out>" selected="selected">
										<c:out value="${productor.nombre}"></c:out>
									</option>
								</c:when>
								<c:otherwise>
									<option value="<c:out value='${productor.id}'></c:out>">
										<c:out value="${productor.nombre}"></c:out>
									</option>								
								</c:otherwise>
							</c:choose>								
						</c:forEach>
					</select>
				</td>
			</tr> -->
			<tr>
				<td height="10"></td>
			</tr>
		</table>
		<div id="divGuias" style="display: none"></div>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
</table>
<script type="text/javascript">

var idLoc = $('#paramLocalidad').val();
if(idLoc != null && idLoc != ""){
	cargarProductoresVolver();	
}

</script>
