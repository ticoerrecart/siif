<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/ReportesAction.js'/>"></script>

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

function submitir(){

	var idProd = $("#selectProductores").val();
	var fechaDesde = $("#idFechaDesde").val();
	var fechaHasta = $("#idFechaHasta").val();

	//var validator = $("#validator").val();
	
	//llamar a ReportesAction.validarReporteVolumenFiscalizadoProductoFecha(idProd,fechaDesde,fechaHasta,validarReporteCallback);
	ReportesAction.validarReporteVolumenFiscalizadoPorProductorFecha(idProd,fechaDesde,fechaHasta,validarReporteCallback);
	//eval("ReportesAction."+validator+"(fechaDesde,fechaHasta,validarReporteCallback)");
	
	//en validarReporteCallback hacer el llamado con window.open() para generar el reporte
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
}

</script>
<input id="paramForward" type="hidden" value="${paramForward}">
<!-- <input id="validator" type="hidden" value="<c:out value="${validator}" />"> -->   
<div id="error" class="rojoAdvertencia"></div>

<c:set var="tituloPagina" value="${titulo}"></c:set>
<c:if test="${titulo == null}">
	<c:set var="tituloPagina" value="Volumen Fiscalizado por Producto Forestal entre Fechas"></c:set>
</c:if>

<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
	<tr>
		<td class="azulAjustado"><c:out value="${tituloPagina}" /></td>
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
				<c:if test="${titulo != null}">
					<tr>
						<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
						<td align="left" class="botonerab">
							<select id="selectProductores" class="botonerab">
								<option value="">-Seleccione un Productor-</option>
								<c:forEach items="${productores}" var="productor" varStatus="i">
									<option value="<c:out value='${productor.id}'></c:out>">
										<c:out value="${productor.nombre}"></c:out>
									</option>																
								</c:forEach>
							</select>
						</td>
					</tr>				
					<tr>
						<td height="2" colspan="2"></td>
					</tr>
				</c:if>
				<tr>
					<td class="botoneralNegritaRight"><bean:message key='SIIF.label.FechaDesde'/></td>
					<td align="left">			
						<input id="idFechaDesde" class="botonerab" type="text" size="23" readonly="readonly">
						<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				 
					</td>				
				</tr>
				<tr>
					<td height="2" colspan="2"></td>
				</tr>
				<tr>
					<td class="botoneralNegritaRight"><bean:message key='SIIF.label.FechaHasta'/></td>
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
			<input type="button" value="Generar Reporte" class="botonerab" onclick="submitir();">
		</td>
	</tr>	
	<tr>
		<td height="20"></td>
	</tr>
</table>
