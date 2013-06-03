<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>	
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript">

function submitir(metodoValidacion){

	$('#exitoGrabado').val("");
	validarForm("tipoProductoForestalForm","../tipoProductoForestal",metodoValidacion,"TipoProductoForestalForm");
}

function cambiarDiametro(diam){

	switch(diam)
	{
	case 0:
		$("#trRangos").hide();
	  	break;
	case 1:
		$("#trRangos").show();
		$("#trRangoLargoTit").show();
		$("#trRangoLargo").show();
		$("#trRangoDiam1Tit").show();
		$("#trRangoDiam1").show();
		$("#trRangoDiam2Tit").hide();
		$("#trRangoDiam2").hide();			 
	  	break;
	default:
		$("#trRangos").show();
		$("#trRangoLargoTit").show();
		$("#trRangoLargo").show();
		$("#trRangoDiam1Tit").show();
		$("#trRangoDiam1").show();
		$("#trRangoDiam2Tit").show();
		$("#trRangoDiam2").show();	  
	}	
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="tipoProductoForestal"
	styleId="tipoProductoForestalForm">
	<html:hidden property="metodo" value="${metodo}" />
	<table border="0" class="cuadrado" align="center" width="60%"
		cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				<c:choose>
					<c:when test="${metodo == 'altaTipoProductoForestal'}">
						<bean:message key='SIIF.titulo.AltaTipoProducto'/>
						<c:set var="metodoValidacion" value="${'validarTipoProductoForestalForm'}" />
					</c:when>
					<c:otherwise>
						<bean:message key='SIIF.titulo.AltaTipoProductoExportacion'/>
						<c:set var="metodoValidacion" value="${'validarTipoProductoExportacionForm'}" />
					</c:otherwise>
				</c:choose>																		
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<c:choose>
			<c:when test="${metodo == 'altaTipoProductoForestal'}">
				<tr>
					<td class="botoneralNegritaRight" width="45%">
						<bean:message key='SIIF.label.TipoProducto'/>
					</td>
					<td align="left">
						<input name="tipoProductoForestalDTO.nombre" class="botonerab" type="text" size="30" 
								onkeypress="return evitarAutoSubmit(event)">
					</td>
				</tr>
				<tr>
					<td height="10" colspan="2"></td>
				</tr>

				<tr>
					<td class="botoneralNegritaRight" rowspan="2" width="45%">
						El Tipo de Producto es de Exportaci?n
					</td>
					<td align="left">
						<input type="radio" name="tipoProductoForestalDTO.esDeExportacion" value="true" checked="checked"/>Si  
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" name="tipoProductoForestalDTO.esDeExportacion" value="false" />No  
					</td>
				</tr>
								
				<tr>
					<td height="10" colspan="2"></td>
				</tr>				
				<tr>
					<td class="botoneralNegritaRight" rowspan="3" width="45%">
						Cantidad de diámetros en muestras
					</td>
					<td align="left">
						<input type="radio" name="tipoProductoForestalDTO.cantDiametros" value="0" onchange="cambiarDiametro(0)" checked="checked"/>Sin muestras  
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" name="tipoProductoForestalDTO.cantDiametros" value="1" onchange="cambiarDiametro(1)"/>1 Diámetro  
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" name="tipoProductoForestalDTO.cantDiametros" value="2" onchange="cambiarDiametro(2)"/>2 Diámetros  
					</td>
				</tr>
				
				<tr id="trRangos" style="display: none">
					<td colspan="2">						
						<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
							<tr id="trRangoLargoTit" style="display: ">
								<td class="grisSubtituloCentro" colspan="4">
									Rango Largo
								</td>								
							</tr>
							<tr id="trRangoLargo" style="display: ">	
								<td class="botoneralNegritaRight" width="20%">
									Desde
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input class="botonerab" type="text" size="5" name="tipoProductoForestalDTO.largoDesde"
											onkeypress="javascript:esNumericoConDecimal(event);" 
											onkeyup="javascript: twoDigits(this);">
									mts
								</td>
								<td class="botoneralNegritaRight" width="20%">
									Hasta
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input class="botonerab" type="text" size="5" name="tipoProductoForestalDTO.largoHasta"
											onkeypress="javascript:esNumericoConDecimal(event);" 
											onkeyup="javascript: twoDigits(this);">
									mts
								</td>								
							</tr>				

							<tr id="trRangoDiam1Tit" style="display: ">
								<td class="grisSubtituloCentro" colspan="4">
									Rango Diámetro 1
								</td>								
							</tr>
							<tr id="trRangoDiam1" style="display: ">	
								<td class="botoneralNegritaRight" width="20%">
									Desde
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input class="botonerab" type="text" size="5" name="tipoProductoForestalDTO.diam1Desde" 
											onkeypress="javascript:esNumerico(event);">
									cm
								</td>
								<td class="botoneralNegritaRight" width="20%">
									Hasta
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input class="botonerab" type="text" size="5" name="tipoProductoForestalDTO.diam1Hasta"
											onkeypress="javascript:esNumerico(event);">
									cm
								</td>								
							</tr>			

							<tr id="trRangoDiam2Tit" style="display: ">
								<td class="grisSubtituloCentro" colspan="4">
									Rango Diámetro 2
								</td>								
							</tr>
							<tr id="trRangoDiam2" style="display: ">	
								<td class="botoneralNegritaRight" width="20%">
									Desde
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input class="botonerab" type="text" size="5" name="tipoProductoForestalDTO.diam2Desde" 
											onkeypress="javascript:esNumerico(event);">
									cm
								</td>
								<td class="botoneralNegritaRight" width="20%">
									Hasta
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input class="botonerab" type="text" size="5" name="tipoProductoForestalDTO.diam2Hasta" 
											onkeypress="javascript:esNumerico(event);">
									cm
								</td>								
							</tr>			
							<tr>
								<td height="5" colspan="4"></td>
							</tr>													
						</table>						
					</td>
				</tr>				
			</c:when>
			<c:otherwise>
				<tr>
					<td class="botoneralNegritaRight">
						<bean:message key='SIIF.label.TipoProductoExportacion'/>
					</td>
					<td align="left">
						<input name="productoForestalDTO.nombre" class="botonerab" type="text" size="30" 
								onkeypress="return evitarAutoSubmit(event)">
					</td>
				</tr>			
			</c:otherwise>	
		</c:choose>					
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2">
				<input type="button" class="botonerab" value="Aceptar" id="enviar" 
						onclick="javascript:submitir('<c:out value="${metodoValidacion}"></c:out>');"> 
				<input type="button" class="botonerab" value="Cancelar" 
						onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>
</html:form>
