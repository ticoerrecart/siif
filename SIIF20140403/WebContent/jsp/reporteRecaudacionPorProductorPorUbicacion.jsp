<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>	

<script type="text/javascript"
	src="<html:rewrite page='/js/fiscalizacion.js'/>"></script>

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

	var productor = $("#idProductor").val();
	var area = $("#idArea").val();
	var pmf = $("#idPMF").val();
	var tranzon = $("#idTranzon").val();
	var marcacion = $("#idMarcacion").val();
	
	if(productor != "-1" && ( pmf != "-1" || area != "-1")){
		$("#error").html("");	
		var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
		if(type == "IE"){
			window.open("./reportesRecaudacion.do?metodo=generarReporteRecaudacionPorProductorPorUbicacion&productor="+productor+"&area="+area+"&pmf="+pmf+"&tranzon="+tranzon+"&marcacion="+marcacion,"",especificaciones);		
		}else{
			window.open("../../reportesRecaudacion.do?metodo=generarReporteRecaudacionPorProductorPorUbicacion&productor="+productor+"&area="+area+"&pmf="+pmf+"&tranzon="+tranzon+"&marcacion="+marcacion,"",especificaciones);				
		}
	}		
	else{
		var textoError1 = (productor == "-1")?"* Seleccione un Productor Forestal<br>":"";
		var textoError2 = (pmf == "-1" &&  area == "-1")?"* Seleccione una Zona de Manejo Forestal<br>":"";
		$("#error").html(textoError1 + textoError2);		
	}		
}

function cargarProductores(){

	var idTipoDeEntidad = $('#selectTiposDeEntidad').val();
	if(idTipoDeEntidad != "-1"){
		$('#idProductor').attr('disabled',false);
		EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresCallback );		
	}else{
		dwr.util.removeAllOptions("idProductor");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("idProductor", data, "id", "nombre");		
		$('#idProductor').attr('disabled','disabled');
	}		
	$('#divDetalle').hide(600);
	$('#divDetalle').html("");
}

function actualizarProductoresCallback(productores){
	dwr.util.removeAllOptions("idProductor");
	var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
	dwr.util.addOptions("idProductor", data, "id", "nombre");	
	dwr.util.addOptions("idProductor", productores,"id","nombre");	
}

</script>
<input id="paramForward" type="hidden" value="${paramForward}">
<!-- <input id="validator" type="hidden" value="<c:out value="${validator}" />"> -->   
<div id="error" class="rojoAdvertencia"></div>

<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
	<tr>
		<td class="azulAjustado">
			Recaudación por Productor y Ubicación
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
						<select id="idProductor" class="botonerab" disabled="disabled" onchange="cambioComboZona();">
							<option value="-1">-Seleccione un Productor-</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td width="40%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ZonaManejoForestal'/></td>
					<td align="left">
						<select id="idZMF" class="botonerab" 
								onchange="cambioComboZona();">
							<option value="0">--Seleccione una Opcion de Zona--</option>
							<option value="1">--PMF--</option>
							<option value="2">--Area de Cosecha--</option>
						</select>	
					</td>						
				</tr>				

				<tr class="area" style="display: none">	
					<td width="40%"  class="botoneralNegritaRight"><bean:message key='SIIF.label.AreaDeCosecha'/></td>
					<td align="left"> 
						<select id="idArea" class="botonerab" name="fiscalizacionDTO.idArea" >
							<option value="-1">- Seleccione -</option>						
						</select>	
					</td>
				</tr>
				
				<tr class="plan" >
					<td width="40%" class="botoneralNegritaRight">
						<bean:message key='SIIF.label.PlanManejoForestal'/>
					</td>
					<td align="left" class="botonerab">
						<select id="idPMF" class="botonerab"  onchange="actualizarComboTranzon();">
							<option value="-1">- Seleccione -</option>						
						</select>
					</td>
				</tr>				
				<tr class="plan">
					<td width="40%" class="botoneralNegritaRight">
						<bean:message key='SIIF.label.Tranzon'/>
					</td>
					<td align="left" class="botonerab">
						<select id="idTranzon" class="botonerab"  onchange="actualizarComboMarcacion();">
							<option value="-1">- Seleccione -</option>
						</select>
					</td>
				</tr>
				<tr class="plan">
					<td width="40%" class="botoneralNegritaRight">
						<bean:message key='SIIF.label.Marcacion'/>
					</td>
					<td align="left" class="botonerab">
						<select id="idMarcacion" class="botonerab" onchange="actualizarComboRodal();">
							<option value="-1">- Seleccione -</option>
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
