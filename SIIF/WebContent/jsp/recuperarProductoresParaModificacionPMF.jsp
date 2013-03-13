<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/Concurrent.Thread-full-20090713.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<<script type="text/javascript">

function cargarProductores(){

	var idTipoDeEntidad = $('#selectTiposDeEntidad').val();
	if(idTipoDeEntidad != "-1"){
		$('#selectProductores').attr('disabled',false);
		EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresCallback );		
	}else{
		dwr.util.removeAllOptions("selectProductores");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("selectProductores", data, "id", "nombre");		
		$('#selectProductores').attr('disabled','disabled');
	}		
	$('#divDetalle').hide(600);
	$('#divDetalle').html("");
}

function actualizarProductoresCallback(productores){
	dwr.util.removeAllOptions("selectProductores");
	var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
	dwr.util.addOptions("selectProductores", data, "id", "nombre");	
	dwr.util.addOptions("selectProductores", productores,"id","nombre");	
}

function mostrarModificacionPMF(){

	var idProductor = $('#selectProductores').val();
	parent.location = contextRoot() +  '/ubicacion.do?metodo=recuperarUbicacionesParaModificacion&idProductor=' + idProductor;
	
}	


</script>

<div id="errores" class="rojoAdvertencia">${error}</div>
<br>
<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
	<tr>
		<td class="azulAjustado">Modificación Zona de Manejo Forestal </td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<table border="0" class="cuadrado" align="center" width="70%"
				cellpadding="2">
				<tr>
					<td height="10" colspan="3"></td>
				</tr>
				<tr>
					<td class="botoneralNegritaRight" width="30%"><bean:message key='SIIF.label.TipoDeProductor'/></td>
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
						<select id="selectProductores" class="botonerab" disabled="disabled" onchange="mostrarModificacionPMF()">
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
</table>