<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/calendario.tld" prefix="cal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/guiaForestal.js'/>"></script>

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function pagarCuota(cuota){

	var check = document.getElementById("idCheckPagoCuota"+cuota);

	var display= "";
	
	if(check.value == "N"){
		check.value="S";
	}		
	else{
		display = "none";
		check.value="N";		
	}
	document.getElementById("idTitFecha"+cuota).style.display=display;
	document.getElementById("idTDFecha"+cuota).style.display=display;
	document.getElementById("idFecha"+cuota).value = "";	
}

function submitir(){

	validarForm("guiaForestalForm","../guiaForestal","validarRegistrarPagoCuotaForm","GuiaForestalForm");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<html:form action="guiaForestal" styleId="guiaForestalForm">
	<html:hidden property="metodo" value="registrarPagoCuota" />


	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">Plan de Pago de Guía
			Forestal N° <c:out value="${guia.nroGuia}"></c:out></td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<tr>
			<td width="12%" class="botoneralNegritaRight">Nro de Guía</td>
			<td width="30%" align="left"><input
				value="<c:out value='${guia.nroGuia}'></c:out>" readonly="readonly"
				class="botonerab" type="text" size="40"></td>

			<td width="30%" class="botoneralNegritaRight">Fecha de
			Vencimiento</td>
			<td align="left"><input class="botonerab" type="text" size="16"
				readonly="readonly"
				value="<fmt:formatDate value='${guia.fechaVencimiento}' pattern='dd/MM/yyyy' />">
			</td>
		</tr>
		<tr>
			<td width="12%" class="botoneralNegritaRight">Permisionario</td>
			<td width="30%" align="left"><input
				value="<c:out value='${guia.fiscalizacion.productorForestal.nombre}'></c:out>"
				readonly="readonly" class="botonerab" type="text" size="40">
			</td>

			<td width="30%" class="botoneralNegritaRight">Período Forestal</td>
			<td align="left"><input
				value="<c:out value='${guia.periodoForestal}'></c:out>"
				readonly="readonly" class="botonerab" type="text" size="40"></td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
	</table>

	<table id="idTablaCuotas" border="0" class="cuadrado" align="center"
		width="80%" cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">Boletas de Deposito</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>

		<%int cuota =1; %>
		<c:forEach items="${guia.boletasDeposito}" var="boleta"
			varStatus="nro">

			<tr>
				<td width="10%"></td>
				<td colspan="2">
				<table border="0" class="cuadrado" align="center" width="85%"
					cellpadding="2">
					<tr>
						<td colspan="4" class="grisSubtitulo">Cuota <%=cuota %></td>
					</tr>
					<tr>
						<td height="5" colspan="4"></td>
					</tr>
					<tr>
						<td width="10%" class="botoneralNegritaRight">Número de Cuota</td>
						<td width="40%" align="left"><input readonly="readonly"
							value="<c:out value='${boleta.numero}'></c:out>"
							class="botonerab" type="text" size="20"
							onkeypress="javascript:esNumerico(event);"></td>
						<td width="10%" class="botoneralNegritaRight">Productor</td>
						<td width="40%" align="left"><input
							value="<c:out value='${guia.fiscalizacion.productorForestal.nombre}'></c:out>"
							readonly="readonly" class="botonerab" type="text" size="30">
						</td>
					</tr>
					<tr>
						<td width="10%" class="botoneralNegritaRight">Concepto</td>
						<td colspan="3" align="left"><input readonly="readonly"
							value="<c:out value='${boleta.concepto}'></c:out>"
							class="botonerab" type="text" size="79"></td>
					</tr>
					<tr>
						<td width="10%" class="botoneralNegritaRight">Area</td>
						<td colspan="3" align="left"><input readonly="readonly"
							value="<c:out value='${boleta.area}'></c:out>" class="botonerab"
							type="text" size="79"></td>
					</tr>
					<tr>
						<td width="10%" class="botoneralNegritaRight">Efectico/Cheque:</td>
						<td width="40%" align="left"><input readonly="readonly"
							value="<c:out value='${boleta.efectivoCheque}'></c:out>"
							class="botonerab" type="text" size="20"
							onkeypress="javascript:esAlfaNumerico(event);"></td>
						<td width="10%" class="botoneralNegritaRight">Monto:</td>
						<td width="40%" align="left"><input readonly="readonly"
							value="<c:out value='${boleta.monto}'></c:out>" class="botonerab"
							type="text" size="20"
							onkeypress="javascript:esNumericoConDecimal(event);"></td>
					</tr>
					<tr>
						<td width="10%" class="botoneralNegritaRight">Fecha de
						Vencimiento</td>
						<td width="40%" align="left"><input class="botonerab"
							type="text" size="16" readonly="readonly"
							value="<fmt:formatDate value='${boleta.fechaVencimiento}' pattern='dd/MM/yyyy' />">
						</td>
						<td width="10%" class="botoneralNegritaRight">Fecha de Pago</td>
						<td width="40%" align="left"><input class="botonerab"
							type="text" size="16" readonly="readonly"
							value="<fmt:formatDate value='${boleta.fechaPago}' pattern='dd/MM/yyyy' />">
						</td>
					</tr>

					<c:if test="${boleta.fechaPago == null}">
						<tr>
							<td height="10" colspan="4"></td>
						</tr>
						<tr>
							<td colspan="2" class="botonerab"><input
								id="idCheckPagoCuota<%=cuota %>" type="checkbox" value="N"
								onclick="javascript:pagarCuota(<%=cuota %>);">Pagar
							Cuota</td>

							<td id="idTitFecha<%=cuota %>" style="display: none" width="10%"
								class="botoneralNegritaRight">Fecha</td>
							<td id="idTDFecha<%=cuota %>" style="display: none" width="40%"
								align="left"><input id="idFecha<%=cuota%>"
								name="boletasDeposito[${nro.count-1}].fechaPagoTransient"
								class="botonerab" type="text" size="16"> <cal:cal
								propiedad="boletasDeposito[${nro.count-1}].fechaPagoTransient"
								formato="date11" name="fecha${nro.count}" /></td>

						</tr>
					</c:if>

					<tr>
						<td height="5" colspan="4"></td>
					</tr>
				</table>
				</td>
				<c:choose>
					<c:when test="${boleta.fechaPago != null}">
						<td width="15%" class="verdeExitoLeft">PAGADA</td>

					</c:when>
					<c:otherwise>
						<td width="15%" class="rojoAdvertenciaLeft">NO PAGADA</td>
					</c:otherwise>
				</c:choose>

				<%cuota++; %>
			</tr>
			<tr>
				<td height="5" colspan="4"></td>
			</tr>
		</c:forEach>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>

	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4"><input type="button" value="Aceptar"
				id="enviar" onclick="javascript:submitir();"> <input
				type="button" value="Cancelar"></td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
</html:form>

















