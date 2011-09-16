<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/calendario.tld" prefix="cal"%>

<%

	Integer i = Integer.parseInt(request.getParameter("indice"));
	String nombre = request.getParameter("nombreProductor");

%>

<script type="text/javascript">	
</script>

<table id="idTableVale<%=i%>" border="0" class="cuadradoSinBorde" align="center" width="100%" cellpadding="2">
	<tr id="idTrCuotaEspacio<%=i%>">
		<td height="5" colspan="4"></td>
	</tr>
	<tr id="idTrCuota<%=i%>">
		<td colspan="4">
			<table border="0" class="cuadrado" align="center" width="81%" cellpadding="2">
				<tr>
					<td colspan="4" class="grisSubtitulo">
						Vale de Transporte n°<%=i%>
					</td>
				</tr>
				<tr>
					<td height="5" colspan="4"></td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight">Número de Vale</td>
					<td width="40%" align="left">
						<input name='<%="valesTransporte["+(i-1)+"].numero"%>' class="botonerab" 
							type="text" size="25" onkeypress="javascript:esNumerico(event);">
					</td>
					<td width="10%" class="botoneralNegritaRight">
						Transportados por
					</td>
					<td width="40%" align="left">
						<input value="<%=nombre%>" class="botonerab" type="text" 
							size="40" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight">Origen</td>
					<td width="40%" align="left">
						<input name='<%="valesTransporte["+(i-1)+"].origen"%>' class="botonerab" type="text"
							size="25">
					</td>
					<td width="10%" class="botoneralNegritaRight">Destino</td>
					<td width="40%" align="left">
						<input name='<%="valesTransporte["+(i-1)+"].destino"%>' class="botonerab"
							type="text" size="25">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight">Vehiculo</td>
					<td width="40%" align="left">
						<input name='<%="valesTransporte["+(i-1)+"].vehiculo"%>' class="botonerab"
							type="text" size="25">
					</td>
					<td width="10%" class="botoneralNegritaRight">Marca</td>
					<td width="40%" align="left">
						<input name='<%="valesTransporte["+(i-1)+"].marca"%>' class="botonerab" type="text"
							size="25">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight">Dominio</td>
					<td width="40%" align="left">
						<input name='<%="valesTransporte["+(i-1)+"].dominio"%>' class="botonerab"
							type="text" size="7">
					</td>
					<td width="10%" class="botoneralNegritaRight">
						Fecha Vencimiento
					</td>
					<td width="40%" align="left">
						<input id="datepickerVale<%=i-1%>" type="text" readonly="readonly" class="botonerab" 
								name='<%="valesTransporte["+(i-1)+"].fechaVencimientoTransient"%>'>
						<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
							align="top" width='17' height='21'>										
					</td>
				</tr>
	
				<tr>
					<td height="5" colspan="4"></td>
				</tr>
				<tr>
					<td colspan="4">
					<table class="cuadradoSinBorde" align="center" width="80%"
						cellpadding="2">
						<tr>
							<td class="grisSubtitulo">Producto</td>
							<td class="grisSubtitulo">N° de Piezas</td>
							<td class="grisSubtitulo">Cantidad m³</td>
							<td class="grisSubtitulo">Especie</td>
						</tr>
						<tr>
							<td>
								<input class="botonerab" type="text"
									name='<%="valesTransporte["+(i-1)+"].producto"%>'>
							</td>
							<td>
								<input class="botonerab" type="text"
									name='<%="valesTransporte["+(i-1)+"].nroPiezas"%>'
									onkeypress="javascript:esNumerico(event);">
							</td>
							<td>
								<input class="botonerab" type="text"
									name='<%="valesTransporte["+(i-1)+"].cantidadMts"%>'
									onkeypress="javascript:esNumericoConDecimal(event);">
							</td>
							<td>
								<input class="botonerab" type="text"
									name='<%="valesTransporte["+(i-1)+"].especie"%>'>
							</td>
						</tr>
					</table>
					</td>
				</tr>
	
				<tr>
					<td height="5" colspan="4"></td>
				</tr>
			</table>		
		</td>
	</tr>
</table>
<div id="divPlanVales<%=i+1%>"></div>
