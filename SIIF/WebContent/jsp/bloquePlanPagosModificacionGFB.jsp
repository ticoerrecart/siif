<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
	Integer i = Integer.parseInt(request.getParameter("indiceBoleta"));	
									
	String nombre = request.getParameter("nombreProductor");
	
%>


<table border="0" class="cuadradoSinBorde" align="right" cellpadding="2" width="91%">									
	<tr>
		<td width="90%">
			<table border="0" class="cuadrado" align="right" width="100%" cellpadding="2">
				<tr>
					<td colspan="4" class="grisSubtitulo">Cuota n°<%=i+1%></td>
				</tr>
				<tr>
					<td height="5" colspan="4"></td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NumeroCuota'/></td>
					<td width="40%" align="left">
						<input name='<%="boletasDeposito["+(i)+"].numero"%>' class="botonerab" type="text" 
							size="20" onkeypress="javascript:esNumerico(event);">
					</td>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Productor'/></td>
					<td width="40%" align="left">
						<input value="<%=nombre %>" class="botonerab" type="text" size="40" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Concepto'/></td>
					<td colspan="3" align="left">
						<input name='<%="boletasDeposito["+(i)+"].concepto"%>' class="botonerab"
							type="text" size="90">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Area'/></td>
					<td colspan="3" align="left">
						<input name='<%="boletasDeposito["+(i)+"].area"%>' class="botonerab"
							type="text" size="90">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.EfecticoCheque'/></td>
					<td width="40%" align="left">
						<input name='<%="boletasDeposito["+(i)+"].efectivoCheque"%>' class="botonerab" 
							type="text" size="20" onkeypress="javascript:esAlfaNumerico(event);">
					</td>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Monto'/></td>
					<td width="40%" align="left">
						<input name='<%="boletasDeposito["+(i)+"].monto"%>' class="botonerab" type="text" 
							size="20" onkeypress="javascript:esNumericoConDecimal(event);">
					</td>
				</tr>
				<tr>
					<td width="10%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha_Venc'/></td>
					<td colspan="3" align="left">
						<input id="datepicker<%=i%>" type="text" readonly="readonly" class="botonerab" 
								name='<%="boletasDeposito["+(i)+"].fechaVencimientoTransient"%>'>
						<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
							align="top" width='17' height='21'>															
				
					</td>
				</tr>
				<tr>
					<td height="5" colspan="4"></td>
				</tr>
			</table>
		</td>
		<td width="10%">
			<input id="idBotonPago<c:out value='${boletaDeposito.id}'></c:out>"
					type="button" value="Eliminar" class="botonerab" 
					onclick="eliminarBoleta(<%=i%>);">																								
		</td>		
	</tr>
</table>

<div id="divBoletaDeposito<%=i+1%>"></div>
