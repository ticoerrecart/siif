<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import= "ar.com.siif.dto.FiscalizacionDTO" %>
<%@ page import= "ar.com.siif.dto.GuiaForestalDTO" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript" 
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>		

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volver(){	

	parent.location = contextRoot() +  '/jsp.do?page=.recuperarGuiaAsociarFiscalizacion';		
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

function agrElimFisc(indice,idFiscalizacion){

	alert(indice);
	alert(idFiscalizacion);
	var i = indice+1;
	if($('#idCheck'+i).is(':checked')){
		$("#idFiscalizacion"+indice).val(idFiscalizacion);
		//$("#idTipoProducto"+indice).val(idTipoProducto);			
	}else{
		$("#idFiscalizacion"+indice).val(0);
		//$("#idTipoProducto"+indice).val(0);
	}
}

function submitAsociarGuia(){
	validarForm("guiaForestalForm","../guiaForestal","validarGuiaForestalBasicaForm","GuiaForestalForm");
} 
</script>

<%
	//GuiaForestalDTO guia = (GuiaForestalDTO)request.getAttribute("guiaForestal");
%>

<input id="idParamForward" type="hidden" value="${paramForward}">
<!-- <input id="idParamProductor" type="hidden" value="${guiaForestal.productorForestal.id}">
<input id="idParamIdTipoDeEntidad" type="hidden" value="${guiaForestal.productorForestal.tipoEntidad}"> -->

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
		<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
		<td align="left">
			<input value="${guiaForestal.periodoForestal}" class="botonerab" type="text" 
				   size="40" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Localidad'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.productorForestal.localidad.nombre}" readonly="readonly" class="botonerab" size="40">
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
<table border="0" class="cuadrado" align="center" width="80%"
	cellpadding="2">
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.PlanManejoForestal'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.rodal.marcacion.tranzon.pmf.nombre} - ${guiaForestal.rodal.marcacion.tranzon.pmf.expediente}" 
					class="botonerab" type="text" size="40" readonly="readonly">
		</td>
		<td width="30%" class="botoneralNegritaRight">
			<bean:message key='SIIF.label.Tranzon'/>
		</td>
		<td align="left">
			<input value="${guiaForestal.rodal.marcacion.tranzon.numero} - ${guiaForestal.rodal.marcacion.tranzon.disposicion}" 
					class="botonerab" type="text" size="40" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td width="12%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Marcacion'/></td>
		<td width="30%" align="left">
			<input value="${guiaForestal.rodal.marcacion.disposicion}" 
					class="botonerab" type="text" size="40" readonly="readonly">
		</td>
		<td width="30%" class="botoneralNegritaRight">
			<bean:message key='SIIF.label.Rodal'/>
		</td>
		<td align="left">
			<input value="${guiaForestal.rodal.nombre}" class="botonerab" 
					type="text" size="40" readonly="readonly">
		</td>
	</tr>		
	<tr>
		<td height="10" colspan="4"></td>
		</tr>
	</table>
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

	<!-- PRODUCTOS FORESTALES -->
	<tr>
		<td colspan="4" align="left">
			<div id="e1" style="DISPLAY: ">
				<label onclick="javascript:exp('1')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion"><bean:message key='SIIF.subTitulo.ProductosForestales'/></U><BR>
				</label>
			</div>
			<div id="c1" style="DISPLAY: none">
				<label onclick="javascript:col('1')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion"><bean:message key='SIIF.subTitulo.ProductosForestales'/></U><BR>
				</label>
	
				<table border="0" class="cuadradoSinBorde" align="center"
					   width="100%" cellpadding="2">
					<tr>
						<td height="5" colspan="4"></td>
					</tr>
					<tr>
						<td align="left" colspan="4">
							<bean:message key='SIIF.label.CertificadoGuia'/>
						</td>
					</tr>
					<tr>
						<td height="5" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4">
							<table border="0" class="cuadrado" align="center" width="90%"
								   cellpadding="2" cellspacing="0">
								<tr>
									<td class="azulAjustado"><bean:message key='SIIF.label.Tipo'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.Estado'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.Especie'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.M3'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.ValorAforo'/></td>
									<td class="azulAjustado"><bean:message key='SIIF.label.Importe'/></td>
								</tr>

								<c:forEach items="${guiaForestal.subImportes}" var="subImporte" varStatus="i">	
								<tr>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.tipoProducto.nombre}">														
									</td>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.estado}">														
									</td> 
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.especie}">
									</td>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.cantidadMts}">									
									</td>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.valorAforos}">													
									</td>
									<td>
										<input class="botonerab" type="text" readonly="readonly" value="${subImporte.importe}">
									</td>
								</tr>
								</c:forEach>	
							</table>
							<table border="0" class="cuadrado" align="center" width="90%"
								cellpadding="2" cellspacing="0">				
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td align="right"width="82%">
										<bean:message key='SIIF.label.DerechoInspFisca'/>
									</td>
									<td width="18%">
										<input value="${guiaForestal.inspFiscalizacion}" readonly="readonly" class="botonerab" type="text">
									</td>
								</tr> 
								<tr>
									<td class="botoneralNegritaRight"><bean:message key='SIIF.label.IMPORTE_TOTAL'/></td>
									<td>
										<input readonly="readonly" value="${guiaForestal.importeTotal}" class="botonerab" type="text">
									</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
							</table>							
						</td>
					</tr>
					<tr>
						<td height="15" colspan="4"></td>
					</tr>
				</table>
			</div>
		</td>
	</tr>

	<!-- FISCALIZACIONES A ASOCIAR -->

	<tr>
		<td colspan="4" align="left">
			<div id="e2" style="DISPLAY: ">
				<label onclick="javascript:exp('2')"> 
					<img src="../../imagenes/expand.gif" border="0" /> 
					<U class="azulOpcion">
						<!--<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>-->
						Fiscalizaciones aptas para asociar a la Guía Forestal_
					</U>
					<BR>
				</label>
			</div>
			<div id="c2" style="DISPLAY: none">
				<label onclick="javascript:col('2')"> 
					<img src="../../imagenes/collapse.gif" border="0" /> 
					<U class="azulOpcion">
						<!--<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>-->
						Fiscalizaciones aptas para asociar a la Guía Forestal_
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
								<%clase=(clase.equals("")?"par":""); %>
								
								<tr id="trAptas<c:out value='${i.count}'></c:out>" class="<%=clase%>"
									onmouseover="javascript:pintarFila('trAptas<c:out value='${i.count}'></c:out>');"
									onmouseout="javascript:despintarFila('trAptas',<c:out value='${i.count}'></c:out>);">
									
									<html:hidden property="listaFiscalizaciones[${i.count-1}].id" value="${fiscalizacion.id}"/>									
									<!--<html:hidden property="listaFiscalizaciones[${i.count-1}].tipoProducto.id" value="${fiscalizacion.tipoProducto.id}"/>-->
									
									<td class="botonerab">
										<input type="checkbox" id="idCheck<c:out value='${i.count}'></c:out>"
											onclick="javascript:pintarFila(<c:out value='${i.count}'></c:out>);agrElimFisc(<c:out value='${i.count-1}'></c:out>,<c:out value='${fiscalizacion.id}'></c:out>);">
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
						<br>				
					</c:when>
										
					<c:otherwise>
						<table border="0" class="cuadradoSinBorde" align="center" width="70%" cellpadding="2">
							<tr>
								<td class="botonerab">
									No existen Fiscalizaciones aptas para asociar
								</td>
							</tr>
						</table>													
					</c:otherwise>
				</c:choose>	
			</div>	
		</td>
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr>
</table>
<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>    
		<td height="10" colspan="4"></td>
	</tr>
	<tr>
		<td height="20" width="49%" align="right">
			<input id="idBotonCrearGuia" class="botonerab" type="submit" value="Aceptar">
			<input type="button" class="botonerab" value="Volver" onclick="javascript:volverConsultaGuia();">
		</td>
		<td height="20" colspan="2">
		</td>
	</tr>
	<tr>
		<td height="10" colspan="4"></td>
	</tr> 
</table>
</div>	 				  
<div id="idDivFiscalizacion" style="display: none;">
</div>
</html:form>