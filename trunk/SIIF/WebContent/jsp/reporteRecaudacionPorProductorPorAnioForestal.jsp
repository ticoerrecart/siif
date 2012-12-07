<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>
<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script>
	$(function() {
		$( "#idFechaDesde" ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( "#idFechaHasta" ).datepicker({ dateFormat: 'dd/mm/yy'});		
	});
</script>

<script type="text/javascript"> 

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";


function generarReporte(){

	var productor = $("#selectProductores").val();
	var periodo = $("#periodo").val();

	if(productor != "-1" && periodo != "-1"){
		$("#error").html("");
		var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
		if(type == "IE"){
			window.open("./reportesRecaudacion.do?metodo=generarReporteRecaudacionPorProductorPorAnioForestal&productor="+productor+"&periodo="+periodo,"",especificaciones);
		}else{
			window.open("../../reportesRecaudacion.do?metodo=generarReporteRecaudacionPorProductorPorAnioForestal&productor="+productor+"&periodo="+periodo,"",especificaciones);
		}
	}
	else{
		var textoError1 = (productor == "-1")?"* Seleccione un Productor Forestal<br>":"";
		var textoError2 = (periodo == "-1")?"* Seleccione un Periodo Forestal<br>":"";
		$("#error").html(textoError1 + textoError2);		
	}
}

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

</script>
   
<div id="error" class="rojoAdvertencia"></div>

<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
	<tr>
		<td class="azulAjustado">
			Volumen Por Producto - Productor - Período Forestal
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
				<tr>
					<td height="10" colspan="2"></td>
				</tr>	
				<tr>
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoEntidad'/></td>
					<td align="left" class="botonerab">
						<select id="selectTiposDeEntidad" class="botonerab" onchange="cargarProductores()">
							<option value="-1">-Seleccione un Tipo de Entidad-</option>					
							<c:forEach items="${tiposDeEntidad}" var="tipoDeEntidad" varStatus="i">
								<option value="<c:out value='${tipoDeEntidad.name}'></c:out>">
									<c:out value="${tipoDeEntidad.descripcion}"></c:out>
								</option>							
							</c:forEach>							
						</select>
					</td>
				</tr>
				<tr>
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
					<td align="left" class="botonerab">
						<select id="selectProductores" class="botonerab" disabled="disabled" onchange="mostrarDetalle()">
							<option value="-1">-Seleccione un Productor-</option>
						</select>
					</td>
				</tr>	
				<tr>
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
					<td align="left" class="botonerab">
						<select id="periodo" class="botonerab" style="width: 16em">
							<option value="-1">-Seleccione un Período Forestal-</option>						
							<c:forEach items="${periodos}" var="per">
								<option value="${per.periodo}">
									<c:out value="${per.periodo}"></c:out>
								</option>
							</c:forEach>
						</select>					
					</td>
				</tr>						
				<tr>
					<td height="10" colspan="2"></td>
				</tr>								
			</table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr>	
	<tr>
		<td>
			<input type="button" value="Generar Reporte" class="botonerab" onclick="generarReporte();">
		</td>
	</tr>	
	<tr>
		<td height="20"></td>
	</tr>
</table>
