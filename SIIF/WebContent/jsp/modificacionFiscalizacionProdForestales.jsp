<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/fiscalizacion.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/FiscalizacionFachada.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>
<link rel="stylesheet"
	href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">

<script type="text/javascript">


function submitir(){
	calcularVolumen();
	validarForm("fiscalizacionForm","../fiscalizacion","validarFiscalizacionForm","FiscalizacionForm");
}

function volver(){
	var idTipoDeEntidad = $('#paramIdTipoDeEntidad').val();
	var productor = $('#paramProductor').val();	
	parent.location = contextRoot() +  
	'/fiscalizacion.do?metodo=recuperarTiposDeEntidadParaFiscalizacionesAModificar&idTipoDeEntidad=' + idTipoDeEntidad + 
	'&idProductor=' + productor;
}

$(function() {
	$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});		
});

</script>

<%--ACA ESTAN LOS HEADERS DE CADA TIPO DE MUESTRA --%>
<%@include file="bloqueHeaderTablaMuestrasFiscalizacion.jspf" %>

<div id="dialog" style="display: none;"></div>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>
<input id="paramIdTipoDeEntidad" type="hidden"
	value="${fiscalizacionDTO.productorForestal.tipoEntidad}">
<input id="paramProductor" type="hidden"
	value="${fiscalizacionDTO.productorForestal.id}">
<html:form action="fiscalizacion" styleId="fiscalizacionForm">
	<html:hidden property="metodo" value="modificacionFiscalizacion" />

	<html:hidden property="fiscalizacionDTO.id"
		value="${fiscalizacionDTO.id}" styleId="idFiscalizacion" />
	
	<html:hidden property="fiscalizacionDTO.productorForestal.id"
		value="${fiscalizacionDTO.productorForestal.id}"/>
	<html:hidden property="fiscalizacionDTO.tipoProducto.id"
		value="${fiscalizacionDTO.tipoProducto.id}"/>		
	<html:hidden property="fiscalizacionDTO.rodal.id"
		value="${fiscalizacionDTO.rodal.id}"/>	

	<table border="0" class="cuadrado" align="center" width="70%"
		cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado"><bean:message
					key='SIIF.titulo.ModifFiscalizacion' /></td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td width="20%" class="botoneralNegritaRight"><bean:message
					key='SIIF.label.TipoEntidad' />
			</td>
			<td width="30%" align="left"><html:select styleId="selectTiposDeEntidad"
					property="selectTiposDeEntidad" styleClass="botonerab disabled"
					value="${fiscalizacionDTO.productorForestal.tipoEntidad}"
					onchange="actualizarComboProductores();">
					<c:forEach items="${tiposEntidad}" var="tipoDeEntidad">
						<html:option value="${tipoDeEntidad.name}">
							<c:out value="${tipoDeEntidad.descripcion}"></c:out>
						</html:option>
					</c:forEach>
				</html:select></td>
			<td class="botoneralNegritaRight"><bean:message
					key='SIIF.label.ProductorForestal' />
			</td>
			<td align="left"><html:select styleId="idProductor"
					property="idProductor" styleClass="botonerab disabled"
					value="${fiscalizacionDTO.productorForestal.id}"
					onchange="actualizarComboPMF();">

					<c:forEach items="${productores}" var="entidad">
						<html:option value="${entidad.id}">
							<c:out value="${entidad.nombre}"></c:out>
						</html:option>
					</c:forEach>
				</html:select></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message
					key='SIIF.label.Fecha' />
			</td>
			<td align="left"><input id="datepicker" name="fiscalizacionDTO.fecha"
				class="botonerab" type="text" size="23"
				value="${fiscalizacionDTO.fecha}"> <img alt=""
				src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>"
				align="top" width='17' height='21'></td>
			<td class="botoneralNegritaRight"><bean:message
					key='SIIF.label.PeríodoForestal' />
			</td>
			<td align="left">
					
				<select name="fiscalizacionDTO.periodoForestal" class="botonerab" style="width: 16em">
					<c:forEach items="${periodos}" var="per">
						<c:choose>
							<c:when test="${fiscalizacionDTO.periodoForestal == per.periodo}">
								<option value="${per.periodo}" selected="selected">
									<c:out value="${per.periodo}"></c:out>
								</option>
							</c:when>	
							<c:otherwise>
								<option value="${per.periodo}">
									<c:out value="${per.periodo}"></c:out>
								</option>							
							</c:otherwise>
						</c:choose>	
					</c:forEach>
				</select>
					
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message
					key='SIIF.label.CantUnd' />
			</td>
			<td align="left"><input name="fiscalizacionDTO.cantidadUnidades"
				class="botonerab" type="text" size="20"
				value="<c:out value='${fiscalizacionDTO.cantidadUnidades}'></c:out>"
				onkeypress="javascript:esNumerico(event);" id="cantidadUnidades">
			</td>
			<td class="botoneralNegritaRight"><bean:message
					key='SIIF.label.TipoProducto' />
			</td>
			<td align="left"><html:select property="idTipoProductoForestal"
					styleClass="botonerab disabled" value="${fiscalizacionDTO.tipoProducto.id}"
					styleId="idTipoProductoForestal" onchange="actualizarMuestras();">
					<c:forEach items="${tiposProducto}" var="tipoProducto">
						<html:option value="${tipoProducto.id}">
							<c:out value="${tipoProducto.nombre}"></c:out>
						</html:option>
					</c:forEach>
				</html:select></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message
					key='SIIF.label.CantMts3' />
			</td>
			<td align="left"><input id="cantidadMts" name="fiscalizacionDTO.cantidadMts"
				class="botonerab" type="text" size="20"
				value="<c:out value='${fiscalizacionDTO.cantidadMts}'></c:out>"
				onkeypress="javascript:esNumericoConDecimal(event);" readonly="readonly"></td>
			<td class="botoneralNegritaRight"><bean:message
					key='SIIF.label.TamañoMuestra' />
			</td>
			<td align="left"><input name="fiscalizacionDTO.tamanioMuestra"
				class="botonerab" type="text" size="20" id="idTamanioMuestra"
				readonly="readonly"
				value="<c:out value='${fiscalizacionDTO.tamanioMuestra}'></c:out>">
			</td>
		</tr>
		
		<tr>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.Oficina'/>
			</td>
			<td align="left">
				<html:select property="fiscalizacionDTO.oficinaAlta.id"
					styleClass="botonerab" value="${fiscalizacionDTO.oficinaAlta.id}"
					styleId="idOficinaAlta">
					<html:option value="-1">- Seleccione una Oficina -</html:option>
					<c:forEach items="${oficinas}" var="oficina">
						<html:option value="${oficina.id}">
							<c:out value="${oficina.nombre}"></c:out>
						</html:option>
					</c:forEach>
				</html:select>
			</td>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.NroDeGuia'/>
			</td>
			<td align="left">
				<input type="text" class="botonerab" value="${fiscalizacionDTO.guiaForestal.nroGuia}" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<!-- LOCALIZACION -->
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="80%"
					cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message
								key='SIIF.subTitulo.Localizacion' />
						</td>
					</tr>
					<tr>
						<td colspan="3" height="10"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight"><bean:message
								key='SIIF.label.PlanManejoForestal' /></td>
						<td><html:select styleId="idPMF"
								property="idPMF" styleClass="botonerab disabled"
								value="${fiscalizacionDTO.rodal.marcacion.tranzon.pmf.id}"
								onchange="actualizarComboTranzon();">
								<option value="-1">- Seleccione -</option>
								<c:forEach items="${pmfs}" var="pmf">
									<html:option value="${pmf.id}">
										<c:out value="${pmf.nombre} - ${pmf.expediente}"></c:out>
									</html:option>
								</c:forEach>
							</html:select></td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight"><bean:message
								key='SIIF.label.Tranzon' /></td>
						<td><html:select styleId="idTranzon" property="idTranzon"
								styleClass="botonerab disabled"
								value="${fiscalizacionDTO.rodal.marcacion.tranzon.id}"
								onchange="actualizarComboMarcacion();">
								<option value="-1">- Seleccione -</option>
								<c:forEach items="${tranzones}" var="tranzon">
									<html:option value="${tranzon.id}">
										<c:out value="${tranzon.numero} - ${tranzon.disposicion}"></c:out>
									</html:option>
								</c:forEach>
							</html:select></td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight"><bean:message
								key='SIIF.label.Marcacion' /></td>
						<td><html:select styleId="idMarcacion" property="idMarcacion"
								styleClass="botonerab disabled"
								value="${fiscalizacionDTO.rodal.marcacion.id}"
								onchange="actualizarComboRodal();">
								<option value="-1">- Seleccione -</option>
								<c:forEach items="${marcaciones}" var="marcacion">
									<html:option value="${marcacion.id}">
										<c:out value="${marcacion.disposicion}"></c:out>
									</html:option>
								</c:forEach>
							</html:select></td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight"><bean:message
								key='SIIF.label.Rodal' /></td>
						<td><html:select styleId="idRodal" property="idRodal"
								styleClass="botonerab disabled" value="${fiscalizacionDTO.rodal.id}">
								<option value="-1">- Seleccione -</option>
								<c:forEach items="${rodales}" var="rodal">
									<html:option value="${rodal.id}">
										<c:out value="${rodal.nombre}"></c:out>
									</html:option>
								</c:forEach>
							</html:select></td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td colspan="2" height="10"></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>

		<!-- MUESTRAS -->
		<c:if test="${fiscalizacionDTO.tipoProducto.id!=LENIA}">
			<tr id="trMuestras">
				<td colspan="4" align="left">
					<table border="0" class="cuadrado" align="center" width="80%"
						cellpadding="2" cellspacing="0">
						<tr>
							<td colspan="3" class="grisSubtitulo"><bean:message
									key='SIIF.subTitulo.Muestras' />
							</td>
						</tr>
						<tr>
							<td height="20" colspan="3"></td>
						</tr>
						<tr>
							<td width="35%" class="botoneralNegritaRight"><bean:message
									key='SIIF.label.CantidadMuestras' /></td>
							<td><input id="idCantMuestras" class="botonerab" type="text"
								onkeypress="javascript:esNumerico(event);"></td>
							<td width="35%" align="left"><input class="botonerab"
								type="button" value="Agregar" onclick="agregarMuestras();">
								<input class="botonerab" type="button" value="Remover"
								onclick="removerMuestras();"></td>
						</tr>
						<tr>
							<td height="10" colspan="3"></td>
						</tr>
						<tr id="diametros" style="display:none">
							<td width="35%" class="botoneralNegritaRight" align="right">
								1 Diámetro <input type="radio" name="diametros" value="1" onchange="cambiarDiametro();"/>
							</td>
							<td class="botoneralNegritaLeft" align="left">
								2 Diámetros <input type="radio" name="diametros" value="2" onchange="cambiarDiametro();"/>
							</td>
							<td width="35%" align="left">
								&nbsp;
							</td>
						<tr>
						<tr>
							<td height="10" colspan="3"></td>
						</tr>
						<tr>
							<td colspan="3">
								<table id="tablaMuestras" border="0" class="cuadrado"
									align="center" width="70%" cellpadding="2" cellspacing="0"
									style="display: ">

									<c:if test="${fn:length(fiscalizacionDTO.muestra) > 0}">
										<c:forEach items="${fiscalizacionDTO.muestra}" varStatus="i"
											var="muestra">
											<tr id="fila${i.index}">
												<td class="botoneralNegritaRight ind">${i.index+1}</td>
												<td><input class="botonerab" type="text"
													name="muestrasDTO[${i.index}].diametro1"
													value="${muestra.diametro1}" onblur="this.value = reemplazarComa(this.value)" onkeypress="javascript:esNumerico(event);" onkeydown="tabOnEnter(event,this);"></td>
												<td class="diam2"><input class="botonerab" type="text"
													name="muestrasDTO[${i.index}].diametro2"
													value="${muestra.diametro2}" onblur="this.value = reemplazarComa(this.value)" onkeypress="javascript:esNumerico(event);" onkeydown="tabOnEnter(event,this);"></td>
												<td><input class="botonerab" type="text"
													name="muestrasDTO[${i.index}].largo" value="${muestra.largo}" onblur="this.value = reemplazarComa(this.value)" onkeypress="javascript:esNumericoConDecimal(event);" onkeyup="javascript: twoDigits(this);" onkeydown="tabOnEnter(event,this);">
												</td>
											</tr>
	
										</c:forEach>
	
										
										
									</c:if>
									
								</table>
								<c:if test="${fn:length(fiscalizacionDTO.muestra) > 0}">
									<script type="text/javascript">
										$("#tablaMuestras").prepend(headerTabla);
										cantTotales = '<c:out value="${fn:length(fiscalizacionDTO.muestra)}"/>';
										actualizarMuestras();
										calcularVolumen();
									</script>
								</c:if>
							</td>
						</tr>
						
						<c:if test="${fn:length(fiscalizacionDTO.muestra) > 0}">	
								<tr id="calcularVolumen">
									<td height="10" colspan="4"><input class="botonerab"
										type="button" value="Calcular Volumen"
										onclick="calcularVolumen();"></td>
								</tr>
						</c:if>

					</c:if>

					<tr>
						<td height="10" colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>

	<div id="muestrasAux" style="display: none;"></div>

	<table border="0" class="cuadrado" align="center" width="70%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4"><input type="button"
				value="Modificar" class="botonerab" id="enviar"
				onclick="javascript:submitir();"> <input type="button"
				class="botonerab" value="Volver" onclick="javascript:volver();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
</html:form>

<script>
	$(".disabled").attr("disabled","disabled");
</script>
