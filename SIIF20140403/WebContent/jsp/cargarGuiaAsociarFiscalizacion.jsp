<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import= "ar.com.siif.dto.FiscalizacionDTO" %>
<%@ page import= "ar.com.siif.dto.GuiaForestalDTO" %>
<%@ page import= "ar.com.siif.dto.FilaTablaVolFiscAsociarDTO" %>  
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" 
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volverRecuperarGuiaAsociarFiscalizacion(){
	var idTipoDeEntidad = $("#idParamIdTipoDeEntidad").val();
	var idProductor = $("#idParamProductor").val();
	var idPeriodo = $("#idPeriodo").val();
	parent.location = contextRoot() +  '/guiaForestal.do?metodo=recuperarProductoresParaAsociarFiscalizacionesAGuia&idTipoDeEntidad=' + idTipoDeEntidad + '&idProductor=' + idProductor + '&idPeriodo=' + idPeriodo;		
}

function exp(sec) {
	
	 if (type=="IE") { 
	 	 eval("document.all." + "e"+sec + ".style.display='none'");
	 	 eval("document.all." + "c"+sec + ".style.display=''"); 	 
	 	}
	 if (type=="MO" || type=="OP") {
	    eval("document.getElementById('" + "e"+sec + "').style.display='none'");
	    eval("document.getElementById('" + "c"+sec + "').style.display=''");
	   }
}

function col(sec) {
	
 if (type=="IE") { 
 	 eval("document.all." + "c"+sec + ".style.display='none'");
 	 eval("document.all." + "e"+sec + ".style.display=''"); 	 
 	}
 if (type=="MO" || type=="OP") {
    eval("document.getElementById('" + "c"+sec + "').style.display='none'");
    eval("document.getElementById('" + "e"+sec + "').style.display=''");
   }
}

var clase2;
function pintarFila(idTr){

	$('#'+idTr).attr("class", "seleccionado");	
}

function despintarFila(tr,id){

	if(!$('#idCheck'+id).is(':checked')){
		if(id%2){
			clase2 = "par";		
		}else{
			clase2 = "";
		}	
		$('#'+tr+id).attr("class", clase2);
	}		
}

function mostrarFiscalizacion(idFiscalizacion){

	$("#idGuiaForestal").hide();	
	$("#idDivFiscalizacion").load("../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&idFiscalizacion="+idFiscalizacion+"&strForward=exitoCargarFiscalizacionDesdeAltaGFB");
	$("#idDivFiscalizacion").show(); 
	$("#errores").hide();
}

function volverAltaGFB(){

	$("#idGuiaForestal").show();
	$("#idDivFiscalizacion").hide();
	$("#idDivFiscalizacion").empty();
	$("#errores").show();
}

function agrElimFisc(indice,idFiscalizacion,idTipoProducto,cantM3){

	var i = indice+1;
	if($('#idCheck'+i).is(':checked')){
		$("#idFiscalizacion"+indice).val(idFiscalizacion);
		$("#idTipoProducto"+indice).val(idTipoProducto);
		$("#idCantidadMts"+indice).val(cantM3);			
	}else{
		$("#idFiscalizacion"+indice).val(0);
		$("#idTipoProducto"+indice).val(0);
		$("#idCantidadMts"+indice).val(0);
	}
}

function submitAsociarGuia(){
	if(confirm("Esta seguro que desea asociar las fiscalizaciones ?")){
		//validarForm("guiaForestalForm","../guiaForestal","validarGuiaForestalBasicaForm","GuiaForestalForm");
		//document.forms[0].submit();
		validarForm("guiaForestalForm","../guiaForestal","validarFiscalizacionesParaAsociarAGuiaForestalForm","GuiaForestalForm");
	}	
} 
</script>

<%
	//GuiaForestalDTO guia = (GuiaForestalDTO)request.getAttribute("guiaForestal");
%>

<input id="idParamForward" type="hidden" value="${paramForward}">
<input id="idParamProductor" type="hidden" value="${guiaForestal.productorForestal.id}">
<input id="idParamIdTipoDeEntidad" type="hidden" value="${guiaForestal.productorForestal.tipoEntidad}">

<div id="errores" class="rojoAdvertencia">${warning}</div>
<div id="idGuiaForestal">
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td colspan="4" class="azulAjustado">
			<bean:message key='SIIF.titulo.AsociarGuiaAFiscalizacion'/>			
		</td>
	</tr>
	<tr>
		<td height="20" colspan="4"></td>
	</tr>

	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroDeGuia'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.nroGuia}" readonly="readonly" class="botonerab" size="40">
		</td>

		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Permisionario'/></td>
		<td align="left">
			<input id="nombreProductor" value="${guiaForestal.productorForestal.nombre}" 
				   class="botonerab" type="text" size="40" readonly="readonly">			
		</td>
	</tr>

	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ValidoHasta'/></td>
		<td width="30%" align="left">
			
			<input type="text" value="<c:out value='${guiaForestal.fechaVencimiento}'/>" 
				readonly="readonly" class="botonerab">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
				align="top" width='17' height='21'>
		</td>
		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Per�odoForestal'/></td>
		<td align="left">
			<input value="${guiaForestal.periodoForestal}" class="botonerab" type="text" 
				   size="40" readonly="readonly" id="idPeriodo">
		</td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.localidad.nombre}" readonly="readonly" class="botonerab" size="40">
		</td>
		<td width="30%" class="botoneralNegritaRight">
			<bean:message key='SIIF.label.DistanciaEstablecida'/>
		</td>
		<td align="left">
			<input value="${guiaForestal.distanciaAforoMovil}" readonly="readonly" 
				   class="botonerab" type="text" size="10"><bean:message key='SIIF.label.km'/>
		</td>				
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
</table>

<!-- LOCALIZACION -->
<%@include file="bloqueLocalizacion.jspf" %>

<html:form action="guiaForestal" styleId="guiaForestalForm">
<html:hidden property="metodo" value="asociarFiscalizacionesConGuiasForestales" />
<input id="idGuia" type="hidden" name="guiaForestal.id" value="${guiaForestal.id}">
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td height="10" colspan="4"></td>
	</tr>

	<!-- FISCALIZACIONES -->
	<tr>
		<td colspan="4" align="left">
			<div id="e0" style="DISPLAY: ">
				<label onclick="javascript:exp('0')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion">
						<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>
					</U>
					<BR>
				</label>
			</div>
			<div id="c0" style="DISPLAY: none">
				<label onclick="javascript:col('0')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion">
						<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>
					</U>
					<BR>
				</label>
				<c:choose>
					<c:when test="${fn:length(guiaForestal.fiscalizaciones)>0}">	
						<br>
						<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
							<tr>
								<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
								<td class="azulAjustado"><bean:message key='SIIF.label.ProductorForestal'/></td>
								<td class="azulAjustado"><bean:message key='SIIF.label.TipoDeProducto'/></td>
								<td class="azulAjustado"><bean:message key='SIIF.label.CantMts3'/></td>
								<td class="azulAjustado"></td>
							</tr>							
							<%String clase=""; %>
							<c:forEach items="${guiaForestal.fiscalizaciones}" var="fiscalizacion" varStatus="i">
								<%clase=(clase.equals("")?"par":""); %>
								<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
									onmouseover="javascript:pintarFila('tr<c:out value='${i.count}'></c:out>');"
									onmouseout="javascript:despintarFila('tr',<c:out value='${i.count}'></c:out>);">
									
									<td class="botonerab">
										<c:out value="${fiscalizacion.fecha}"></c:out>
									</td>
									<td class="botonerab">
										<c:out value="${fiscalizacion.productorForestal.nombre}"></c:out>
									</td>
									<td class="botonerab">
										<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
									</td>	
									<td class="botonerab">
										<c:out value="${fiscalizacion.cantidadMts}"></c:out>
									</td>
									<td class="botonerab">
										<a href="javascript:mostrarFiscalizacion(<c:out value='${fiscalizacion.id}'></c:out>);">
											<bean:message key='SIIF.label.Ver'/>	
										</a>									
									</td>																
								</tr>
							</c:forEach>	
						</table>
						<br>				
					</c:when>
					<c:otherwise>
						<table border="0" class="cuadradoSinBorde" align="center" width="70%" cellpadding="2">
							<tr>
								<td class="botonerab">
									No se han seleccionado Fiscalizaciones
								</td>
							</tr>
						</table>													
					</c:otherwise>
				</c:choose>	
			</div>	
		</td>
	</tr>

	<%@include file="bloqueSubImportes.jspf" %>

	<!-- FISCALIZACIONES A ASOCIAR -->

	<tr>
		<td colspan="4" align="left">
			<div id="e2" style="DISPLAY: ">
				<label onclick="javascript:exp('2')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion">
						<!--<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>-->
						Fiscalizaciones aptas para asociar a la Gu�a Forestal
					</U>
					<BR>
				</label>
			</div>
			<div id="c2" style="DISPLAY: none">
				<label onclick="javascript:col('2')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion">
						Fiscalizaciones aptas para asociar a la Gu�a Forestal
					</U>
					<BR>
				</label>
				<c:choose>
					<c:when test="${fn:length(fiscalizaciones)>0}">		
						<br>
						<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
							<tr>
								<td class="azulAjustado"></td>
								<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
								<td class="azulAjustado"><bean:message key='SIIF.label.ProductorForestal'/></td>
								<td class="azulAjustado"><bean:message key='SIIF.label.TipoDeProducto'/></td>
								<td class="azulAjustado"><bean:message key='SIIF.label.CantMts3'/></td>
								<td class="azulAjustado"></td>
							</tr>							
							<%String clase=""; %>
							<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
								<html:hidden styleId="idFiscalizacion${i.count-1}" property="listaFiscalizaciones[${i.count-1}].id" value=""/>
								<html:hidden styleId="idTipoProducto${i.count-1}" property="listaFiscalizaciones[${i.count-1}].tipoProducto.id" value=""/>
								<html:hidden styleId="idCantidadMts${i.count-1}" property="listaFiscalizaciones[${i.count-1}].cantidadMts" value=""/>
								<%clase=(clase.equals("")?"par":""); %>
								
								<tr id="trAptas<c:out value='${i.count}'></c:out>" class="<%=clase%>"
									onmouseover="javascript:pintarFila('trAptas<c:out value='${i.count}'></c:out>');"
									onmouseout="javascript:despintarFila('trAptas',<c:out value='${i.count}'></c:out>);">
									
									<!--<html:hidden property="listaFiscalizaciones[${i.count-1}].id" value="${fiscalizacion.id}"/>									
									<html:hidden property="listaFiscalizaciones[${i.count-1}].tipoProducto.id" value="${fiscalizacion.tipoProducto.id}"/>-->
									
									<td class="botonerab">
										<input type="checkbox" id="idCheck<c:out value='${i.count}'></c:out>"
											onclick="javascript:pintarFila(<c:out value='${i.count}'></c:out>);agrElimFisc(<c:out value='${i.count-1}'></c:out>,<c:out value='${fiscalizacion.id}'></c:out>,<c:out value='${fiscalizacion.tipoProducto.id}'></c:out>,<c:out value='${fiscalizacion.cantidadMts}'></c:out>);">
									</td>									
									<td class="botonerab">
										<c:out value="${fiscalizacion.fecha}"></c:out>
									</td>
									<td class="botonerab">
										<c:out value="${fiscalizacion.productorForestal.nombre}"></c:out>
									</td>
									<td class="botonerab">
										<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
									</td>	
									<td class="botonerab">
										<c:out value="${fiscalizacion.cantidadMts}"></c:out>
									</td>
									<td class="botonerab">
										<a href="javascript:mostrarFiscalizacion(<c:out value='${fiscalizacion.id}'></c:out>);">
											<bean:message key='SIIF.label.Ver'/>	
										</a>									
									</td>																
								</tr>
							</c:forEach>	
						</table>							
					</c:when>
										
					<c:otherwise>
						<br>
						<br>
						<table border="0" class="cuadradoSinBorde" align="center" width="70%" cellpadding="2">
							<tr>
								<td class="botonerab">
									No existen Fiscalizaciones aptas para asociar a la Gu�a Forestal
								</td>
							</tr>
						</table>													
					</c:otherwise>					
				</c:choose>	
				<br>
				<br>
				<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
					<tr>
						<td class="azulAjustado">Tipo de Producto</td>
						<td class="azulAjustado">Vol Total en Gu�a</td>
						<td class="azulAjustado">Vol en Fiscalizaci�n</td>
						<td class="azulAjustado">Vol Faltante para Asociar</td>
					</tr>							
					
					<%String clase=""; %>
					<c:forEach items="${tablaVolFiscAsociar}" var="fila" varStatus="i">
						<%clase=(clase.equals("")?"par":""); %>							
						<tr class="<%=clase%>">									
							<td class="botonerab">
								<c:out value="${fila.nombreProducto}"></c:out>
							</td>									
							<td class="botonerab">
								<c:out value="${fila.volumenTotalEnGuia}"></c:out>
							</td>
							<td class="botonerab">
								<c:out value="${fila.volumenEnFiscalizaciones}"></c:out>
							</td>
							<td class="botonerab">
								<c:out value="${fila.volumenFaltante}"></c:out>
							</td>																	
						</tr>
					</c:forEach>								

				</table>				
			</div>	
		</td>
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
</table>
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>    
		<td height="10"></td>
	</tr>
	<tr>
		<td height="20" align="center">
			<input id="idBotonCrearGuia" class="botonerab" type="button" value="Aceptar" onclick="submitAsociarGuia();">
			<input type="button" class="botonerab" value="Volver" 
				onclick="javascript:volverRecuperarGuiaAsociarFiscalizacion();">
		</td>
	</tr>
	<tr>
		<td height="10"></td>
	</tr> 
</table>
</div>	 				  
<div id="idDivFiscalizacion" style="display: none;">
</div>
</html:form>