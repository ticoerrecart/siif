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

function mostrarGuias(){

	var metodo = $('#paramForward').val();
	var idProd = $('#selectProductores').val();
	$('#divCargando').show();	
	$('#divGuias').html("");
	
	if(idProd != "" && idProd != "-1"){
		$('#divGuias').load('../../guiaForestal.do?metodo=recuperarGuiasForestalesParaValeTransporte&forward='+ metodo +'&idProductor='+idProd);
		$('#divGuias').hide();
		$('#divGuias').fadeIn(600);

		Concurrent.Thread.create(function(){
		    while ($('#divGuias').html() == "") {}
		    $('#divCargando').hide();
		});
		
	}else{
		$('#divGuias').hide(600);
		$('#divGuias').html("");
		$('#divCargando').hide();		
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
	mostrarGuias();		
}

function submitir(){

	validarForm("guiaForestalForm","../guiaForestal","validarNroGuiaForm","GuiaForestalForm");
}

</script>
<input id="paramForward" type="hidden" value="${paramForward}">
<input id="paramLocalidad" type="hidden" value="${idLoc}">
<input id="paramProductor" type="hidden" value="${idProd}">
<div id="errores" class="rojoAdvertencia">${error}</div>
<br>
<table border="0" class="cuadrado" align="center" width="70%"
	cellpadding="2">

	<tr>
		<td class="azulAjustado"><c:out value="${titulo}" /></td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<html:form action="guiaForestal" styleId="guiaForestalForm">
				<html:hidden property="metodo" value="${forwardBuscarNroGuia}"/>		
				<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
					<tr>
						<td height="10"></td>
					</tr>
					<tr>
						<td width="38%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.NroDeGuia'/>
						</td>
						<td align="right">
							<input id="idNroGuia" class="botonerab" type="text" size="20" name="guiaForestal.nroGuia" >
						</td>	
						<td>
							<input class="botonerab" type="button" value="Buscar" onclick="javascript:submitir();">
						</td>									
					</tr>				
					<tr>
						<td height="10"></td>
					</tr>				
				</table>
			</html:form>	
		</td>
	</tr>	
	<tr>
		<td>
			<table border="0" class="cuadrado" align="center" width="60%"
				cellpadding="2">
				<tr>
					<td height="10"></td>
				</tr>
				
				<tr>
					<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
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
					<td class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
					<td class="botonerab">
						<select id="selectProductores" class="botonerab" disabled="disabled" onchange="mostrarGuias()">
							<option value="">-Seleccione un Productor-</option>
						</select>
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
			</table>
			<div id="divCargando" style="display: none">
				<br>
				<img src="<html:rewrite page='/imagenes/cargando.gif'/>">
			</div>			
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