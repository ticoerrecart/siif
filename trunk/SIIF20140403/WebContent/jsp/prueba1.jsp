<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%Integer i = Integer.parseInt(request.getParameter("indice"));%>

<script type="text/javascript">


	
</script>

<table id="idTable<%=i%>" border="0" class="cuadradoSinBorde" align="center" width="80%" cellpadding="2">
	<tr id="idTrCuotaEspacio<%=i%>">
		<td height="5" colspan="4"></td>
	</tr>
	<tr id="idTrCuota<%=i%>">
		<td colspan="4">
		<table border="0" class="cuadrado" align="center" width="100%"
			cellpadding="2">
			<tr>
				<td colspan="4" class="grisSubtitulo">Cuota n°<%=i%></td>
			</tr>
			<tr>
				<td height="5" colspan="4"></td>
			</tr>
			<tr>
				<td width="10%" class="botoneralNegritaRight">Número de Cuota</td>
				<td width="40%" align="left"><input
					name='<%="boletasDeposito["+(i-1)+"].numero"%>' class="botonerab"
					type="text" size="20" onkeypress="javascript:esNumerico(event);"></td>
				<td width="10%" class="botoneralNegritaRight">Productor</td>
				<td width="40%" align="left"><input
					name="fiscalizacion.productorForestal.nombre"
					value="${fiscalizacion.productorForestal.nombre}" class="botonerab"
					type="text" size="40" readonly="readonly"></td>
			</tr>
			<tr>
				<td width="10%" class="botoneralNegritaRight">Concepto</td>
				<td colspan="3" align="left"><input
					name='<%="boletasDeposito["+(i-1)+"].concepto"%>' class="botonerab"
					type="text" size="90"></td>
			</tr>
			<tr>
				<td width="10%" class="botoneralNegritaRight">Area</td>
				<td colspan="3" align="left"><input
					name='<%="boletasDeposito["+(i-1)+"].area"%>' class="botonerab"
					type="text" size="90"></td>
			</tr>
			<tr>
				<td width="10%" class="botoneralNegritaRight">Efectico/Cheque:</td>
				<td width="40%" align="left"><input
					name='<%="boletasDeposito["+(i-1)+"].efectivoCheque"%>'
					class="botonerab" type="text" size="20"
					onkeypress="javascript:esAlfaNumerico(event);"></td>
				<td width="10%" class="botoneralNegritaRight">Monto:</td>
				<td width="40%" align="left"><input
					name='<%="boletasDeposito["+(i-1)+"].monto"%>' class="botonerab"
					type="text" size="20"
					onkeypress="javascript:esNumericoConDecimal(event);"></td>
			</tr>
			<tr>
				<td width="10%" class="botoneralNegritaRight">Fecha Vencimiento</td>
				<td colspan="3" align="left">
					<input id="datepicker<%=i-1%>" type="text" readonly="readonly" class="botonerab hasDatepicker" 
							name='<%="boletasDeposito["+(i-1)+"].fechaVencimientoTransient"%>'>
					<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
						align="top" width='17' height='21'>															
									
				</td>
			</tr>
			<tr>
				<td height="5" colspan="4"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
	
