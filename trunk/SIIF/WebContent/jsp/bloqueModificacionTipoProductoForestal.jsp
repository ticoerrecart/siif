<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>

<script type="text/javascript">

function cambiarDiametro(diam){

	switch(diam)
	{
	case '0':
		$("#trRangos").hide();
		$("#largoDesde").val(0);
		$("#largoHasta").val(0);
		$("#diam1Desde").val(0);
		$("#diam1Hasta").val(0);
		$("#diam2Desde").val(0);
		$("#diam2Hasta").val(0);
		
	  	break;
	case '1':
		$("#trRangos").show();
		$("#trRangoLargoTit").show();
		$("#trRangoLargo").show();
		$("#trRangoDiam1Tit").show();
		$("#trRangoDiam1").show();
		$("#trRangoDiam2Tit").hide();
		$("#trRangoDiam2").hide();
		$("#diam2Desde").val(0);
		$("#diam2Hasta").val(0);
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

<html:form action="tipoProductoForestal"
	styleId="tipoProductoForestalForm">
	<html:hidden property="metodo" value="${metodo}" />
	<html:hidden property="tipoProductoForestalDTO.id" value="${tipoProducto.id}" />
	<table border="0" class="cuadrado" align="center" width="70%"
		cellpadding="2">
		<tr>
			<td colspan="3" height="15"></td>
		</tr>
		<tr>
			<td width="45%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.TipoProducto'/>
			</td>
			<td align="left">
				<input name="tipoProductoForestalDTO.nombre" class="botonerab" type="text" size="30"
						value="<c:out value='${tipoProducto.nombre}'></c:out>" 
						onkeypress="return evitarAutoSubmit(event)">
			</td>
			<td width="10%"></td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>
		
		<tr>
			<td width="45%" class="botoneralNegritaRight" rowspan="3">
				El Tipo de Producto es de Exportación
			</td>
			<td align="left">
				<input type="hidden" id="idEsDeExportacion" value="<c:out value='${tipoProducto.esDeExportacion}'></c:out>">
				<input type="radio" id="esDeExpo" name="tipoProductoForestalDTO.esDeExportacion" value="true" checked="checked"/>Si  
			</td>
			<td width="10%"></td>
		</tr>	
		<tr>
			<td align="left">
				<input type="radio" id="noEsDeExpo" name="tipoProductoForestalDTO.esDeExportacion" value="false" />No
			</td>
			<td width="10%"></td>
		</tr>		
				
		<tr>
			<td colspan="3" height="10"></td>
		</tr>		
		<tr>
			<td width="45%" class="botoneralNegritaRight" rowspan="3">
				Cantidad de diámetros en muestras
			</td>
			<td align="left">
				<input type="hidden" id="cantDiam" value="<c:out value='${tipoProducto.cantDiametros}'></c:out>">
				<input type="radio" id="radio0" name="tipoProductoForestalDTO.cantDiametros" value="0" 
									onchange="cambiarDiametro('0')"/>Sin muestras  
			</td>
			<td width="10%"></td>
		</tr>	
		<tr>
			<td align="left">
				<input type="radio" id="radio1" name="tipoProductoForestalDTO.cantDiametros" value="1" 
									onchange="cambiarDiametro('1')"/>1 Diámetro  
			</td>
			<td width="10%"></td>
		</tr>
		<tr>
			<td align="left">
				<input type="radio" id="radio2" name="tipoProductoForestalDTO.cantDiametros" value="2" 
									onchange="cambiarDiametro('2')"/>2 Diámetros  
			</td>
			<td width="10%"></td>
		</tr>			
		<tr>
			<td height="10" colspan="3"></td>
		</tr>			
			
				<tr id="trRangos" style="display: none">
					<td colspan="3">						
						<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
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
									<input id="largoDesde" class="botonerab" type="text" size="5" 
											name="tipoProductoForestalDTO.largoDesde"
											onkeypress="javascript:esNumericoConDecimal(event);" 
											onkeyup="javascript: twoDigits(this);"
											value="<c:out value='${tipoProducto.largoDesde}'></c:out>">
									mts
								</td>
								<td class="botoneralNegritaRight" width="20%">
									Hasta
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input id="largoHasta" class="botonerab" type="text" size="5" 
											name="tipoProductoForestalDTO.largoHasta"
											onkeypress="javascript:esNumericoConDecimal(event);" 
											onkeyup="javascript: twoDigits(this);"
											value="<c:out value='${tipoProducto.largoHasta}'></c:out>">
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
									<input id="diam1Desde" class="botonerab" type="text" size="5" 
											name="tipoProductoForestalDTO.diam1Desde" 
											onkeypress="javascript:esNumerico(event);"
											value="<c:out value='${tipoProducto.diam1Desde}'></c:out>">
									cm
								</td>
								<td class="botoneralNegritaRight" width="20%">
									Hasta
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input id="diam1Hasta" class="botonerab" type="text" size="5" 
											name="tipoProductoForestalDTO.diam1Hasta"
											onkeypress="javascript:esNumerico(event);"
											value="<c:out value='${tipoProducto.diam1Hasta}'></c:out>">
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
									<input id="diam2Desde" class="botonerab" type="text" size="5" 
											name="tipoProductoForestalDTO.diam2Desde" 
											onkeypress="javascript:esNumerico(event);"
											value="<c:out value='${tipoProducto.diam2Desde}'></c:out>">
									cm
								</td>
								<td class="botoneralNegritaRight" width="20%">
									Hasta
								</td>
								<td align="left" width="25%" class="botoneralNegritaLeft">
									<input id="diam2Hasta" class="botonerab" type="text" size="5" 
											name="tipoProductoForestalDTO.diam2Hasta" 
											onkeypress="javascript:esNumerico(event);"
											value="<c:out value='${tipoProducto.diam2Hasta}'></c:out>">
									cm
								</td>								
							</tr>			
							<tr>
								<td height="5" colspan="4"></td>
							</tr>													
						</table>						
					</td>
				</tr>			
			
			
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="3"><input type="button" class="botonerab"
				value="Modificar" id="enviar" onclick="javascript:submitir('<c:out value="${metodoValidacion}"></c:out>');">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
	</table>
</html:form>
<script type="text/javascript">
	var cantDiam = $("#cantDiam").val();	
	$("#radio"+cantDiam).attr("checked","checked");
	cambiarDiametro(cantDiam);

	var esDeExportacion = $("#idEsDeExportacion").val();
	if(esDeExportacion == 'true'){
		$("#esDeExpo").attr("checked","checked");
	}else{
		$("#noEsDeExpo").attr("checked","checked");
	}	
</script>
