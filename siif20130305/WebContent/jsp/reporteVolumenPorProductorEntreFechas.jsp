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

	var volumen = $("#idVolumen").val();
	var productor = $("#selectProductores").val();
	var fechaDesde = $("#idFechaDesde").val();
	var fechaHasta = $("#idFechaHasta").val();

	if(volumen != "-1" && productor != "-1" && fechaDesde != "" && fechaHasta != ""){
		var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
		if(type == "IE"){
			window.open("./reportesPorProducto.do?metodo=generarReporteVolumenPorProductorEntreFechas&volumen="+volumen+"&productor="+productor+"&fechaDesde="+fechaDesde+"&fechaHasta="+fechaHasta,"",especificaciones);
		}else{
			window.open("../../reportesPorProducto.do?metodo=generarReporteVolumenPorProductorEntreFechas&volumen="+volumen+"&productor="+productor+"&fechaDesde="+fechaDesde+"&fechaHasta="+fechaHasta,"",especificaciones);
		}
	}
	else{
		var textoError1 = (volumen == "-1")?"* Seleccione un Volumen<br>":"";
		var textoError2 = (productor == "-1")?"* Seleccione un Productor Forestal<br>":"";
		var textoError3 = (fechaDesde == "")?"* Fecha Desde es un dato obligatorio<br>":"";
		var textoError4 = (fechaHasta == "")?"* Fecha Hasta es un dato obligatorio":"";
		$("#error").html(textoError1 + textoError2 + textoError3 + textoError4);		
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
			Volumen Por Producto y Productor Entre Fechas
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
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Volumen'/></td>
					<td align="left" class="botonerab">
						<select id="idVolumen" class="botonerab">
							<option value="-1">-Seleccione-</option>
							<option value="1">Fiscalizado</option>
							<option value="2">En Gu�a Forestal</option>
						</select>
					</td>
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
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.FechaDesde'/></td>
					<td align="left">			
						<input id="idFechaDesde" class="botonerab" type="text" size="23" readonly="readonly">
						<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				 
					</td>				
				</tr>
				<tr>
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.FechaHasta'/></td>
					<td align="left">			
						<input id="idFechaHasta" class="botonerab" type="text" size="23" readonly="readonly">
						<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				 
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
