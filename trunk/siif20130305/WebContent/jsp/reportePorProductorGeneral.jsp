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


<script type="text/javascript"> 

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function generarReporte(){

	var periodo = $("#idPeriodo").val();
	var productor = $("#selectProductores").val();
	var metodo = $("#paramForward").val();
	
	var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
	if(type == "IE"){
		//window.open("./reportesPorProductor.do?metodo=generarReporteVolumenFiscalizadoPorProductos&periodo="+periodo+"&productor="+productor,"",especificaciones);
		window.open("./reportesPorProductor.do?metodo="+metodo+"&periodo="+periodo+"&productor="+productor,"",especificaciones);		
	}else{
		//window.open("../../reportesPorProductor.do?metodo=generarReporteVolumenFiscalizadoPorProductos&periodo="+periodo+"&productor="+productor,"",especificaciones);
		window.open("../../reportesPorProductor.do?metodo="+metodo+"&periodo="+periodo+"&productor="+productor,"",especificaciones);				
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
<input id="paramForward" type="hidden" value="${paramForward}">
<!-- <input id="validator" type="hidden" value="<c:out value="${validator}" />"> -->   
<div id="error" class="rojoAdvertencia"></div>

<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
	<tr>
		<td class="azulAjustado">
			Reporte por Productor
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
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoDeProductor'/></td>
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
						<select id="idPeriodo" class="botonerab">
							<c:forEach items="${periodos}" var="periodo" varStatus="i">
								<option value="<c:out value='${periodo.periodo}'></c:out>">
									<c:out value="${periodo.periodo}"></c:out>
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
