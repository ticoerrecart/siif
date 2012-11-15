<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/dwr/interface/ReportesAction.js'/>"></script>

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

/*function submitir(){

	var idProd = $("#selectProductores").val();
	var fechaDesde = $("#idFechaDesde").val();
	var fechaHasta = $("#idFechaHasta").val();

	ReportesAction.validarReporteVolumenFiscalizadoPorProductorFecha(idProd,fechaDesde,fechaHasta,validarReporteCallback);
}

function validarReporteCallback(valor){                  
	if(valor!=null && valor != ""){             
		$("#error").html(valor.replace(/<error>/gi,"<p>*"));     
	}
	else{
		$("#error").html("");
		
		var idProd = $("#selectProductores").val();
		var fechaDesde = $("#idFechaDesde").val();
		var fechaHasta = $("#idFechaHasta").val();
		var paramForward = $("#paramForward").val();
			
		var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
		if(type == "IE"){
			window.open("./reporte.do?metodo="+paramForward+"&idProd="+idProd+"&fechaDesde="+fechaDesde+"&fechaHasta="+fechaHasta,"",especificaciones);
		}else{
			window.open("../../reporte.do?metodo="+paramForward+"&idProd="+idProd+"&fechaDesde="+fechaDesde+"&fechaHasta="+fechaHasta,"",especificaciones);
		}		
	}
}*/

function generarReporte(){

	var volumen = $("#idVolumen").val();

	if(volumen != "-1"){
		var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
		if(type == "IE"){
			window.open("./reportesPorProducto.do?metodo=generarReporteVolumenPorAnioForestal&volumen="+volumen,"",especificaciones);
		}else{
			window.open("../../reportesPorProducto.do?metodo=generarReporteVolumenPorAnioForestal&volumen="+volumen,"",especificaciones);
		}
	}

	$("#error").html("* Seleccione un Volumen<br>");
}

</script>
   
<div id="error" class="rojoAdvertencia"></div>

<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
	<tr>
		<td class="azulAjustado">
			Volumen Por Año Forestal
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
							<option value="2">En Guía Forestal</option>
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
