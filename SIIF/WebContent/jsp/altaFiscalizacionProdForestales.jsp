<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
	<script type="text/javascript"
	src="<html:rewrite page='/js/fiscalizacion.js'/>"></script>
	
<script type="text/javascript"
	src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>	
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>	


<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>
<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	
<script type="text/javascript">

function submitir(){
	
	calcularVolumen();
	
	var prodEnabled = $('#idProductor').attr('disabled');
	var pmfEnabled = $('#idPMF').attr('disabled');
	var tranEnabled = $('#idTranzon').attr('disabled');
	var marcEnabled = $('#idMarcacion').attr('disabled');
	var rodEnabled = $('#idRodal').attr('disabled');
	
	$('#idProductor').attr('disabled',false);
	$('#idPMF').attr('disabled',false);
	$('#idTranzon').attr('disabled',false);
	$('#idMarcacion').attr('disabled',false);
	$('#idRodal').attr('disabled',false);
	validarForm("fiscalizacionForm","../fiscalizacion","validarFiscalizacionForm","FiscalizacionForm");
	$('#idProductor').attr('disabled',prodEnabled);
	$('#idPMF').attr('disabled',pmfEnabled);
	$('#idTranzon').attr('disabled',tranEnabled);
	$('#idMarcacion').attr('disabled',marcEnabled);
	$('#idRodal').attr('disabled',rodEnabled);	
}

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";
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

var headerTabla = '<tr id="headerT">' +
'   <td class="azulAjustado" width="3%"></td>' +
'   <td class="azulAjustado" width="33%"><bean:message key="SIIF.label.Largo"/></td>' +
'   <td class="azulAjustado" width="32%"><bean:message key="SIIF.label.Diametro1"/></td>' +
'   <td class="azulAjustado diam2" width="32%"><bean:message key="SIIF.label.Diametro2"/></td>' +
'</tr>';


$(function() {
	$( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});		
});
</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="fiscalizacion" styleId="fiscalizacionForm">
	<html:hidden property="metodo" value="altaFiscalizacion" />
	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIF.titulo.AltaFiscalizacion'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoEntidad'/></td>
			<td width="30%">
				<select id="selectTiposDeEntidad" class="botonerab" onchange="actualizarComboProductores();">
					<option value="-1">-Seleccione una Entidad-</option>
					<c:forEach items="${tiposEntidad}" var="tipoDeEntidad" varStatus="i">
						<option value="${tipoDeEntidad.name}">
							<c:out value="${tipoDeEntidad.descripcion}"></c:out>
						</option>						
					</c:forEach>	
				</select>
			</td>
			<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
			<td width="30%">
				<select id="idProductor" class="botonerab" name="fiscalizacionDTO.productorForestal.id" onchange="actualizarComboPMF();" 
						disabled="disabled">
					<option value="-1">-Seleccione un Productor-</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha'/></td>
			<td>			
				<input id="datepicker" name="fiscalizacionDTO.fecha" class="botonerab" type="text" size="23"	readonly="readonly">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				 
			</td>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.PeríodoForestal'/></td>
			<td >
				<select name="fiscalizacionDTO.periodoForestal" class="botonerab" style="width: 16em">
					<c:forEach items="${periodos}" var="per">
						<option value="${per.periodo}">
							<c:out value="${per.periodo}"></c:out>
						</option>
					</c:forEach>
				</select>	
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.CantUnd'/></td>
			<td>
				<input name="fiscalizacionDTO.cantidadUnidades" class="botonerab" type="text" size="27"
					   onkeypress="javascript:esNumerico(event);"  id="cantidadUnidades">
			</td>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoProducto'/></td>
			<td>
				<select class="botonerab" name="fiscalizacionDTO.tipoProducto.id" id="idTipoProductoForestal" onchange="actualizarMuestras();" >
					<option value="-1">- Seleccione un Producto -</option>
					<c:forEach items="${tiposProducto}" var="tipoProducto">
						<option value="${tipoProducto.id}"><c:out value="${tipoProducto.nombre}"></c:out></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.CantMts3'/>
			</td>
			<td>
				<input id="cantidadMts" name="fiscalizacionDTO.cantidadMts" class="botonerab" type="text" size="27"
					   readonly="readonly" onkeypress="javascript:esNumericoConDecimal(event);">
			</td>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.TamañoMuestra'/>
			</td>
			<td>
				<input name="fiscalizacionDTO.tamanioMuestra" class="botonerab" type="text" size="27" value="0"
				 	   id="idTamanioMuestra" readonly="readonly" onkeypress="javascript:esNumerico(event);">
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.Oficina'/>
			</td>
			<td>
				<select class="botonerab" name="fiscalizacionDTO.oficinaAlta.id">
					<option value="-1">- Seleccione una Oficina -</option>
					<c:forEach items="${oficinas}" var="oficina">
						<option value="${oficina.id}"><c:out value="${oficina.nombre}"></c:out></option>
					</c:forEach>
				</select>
			</td>
			<td class="botoneralNegritaRight" colspan="2">

			</td>
		</tr>		
		<tr>
			<td height="20" colspan="4"></td>
		</tr>

		<!-- LOCALIZACION -->
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message key='SIIF.subTitulo.Localizacion'/></td>
					</tr>
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.PlanManejoForestal'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<select id="idPMF" class="botonerab" name="fiscalizacionDTO.idPlanManejoForestal" disabled="disabled" 
									onchange="actualizarComboTranzon();">
								<option value="-1">- Seleccione -</option>						
							</select>					
						</td>						
					</tr>				
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Tranzon'/>
						</td>
						<td width="4%"></td>						
						<td align="left">
							<select id="idTranzon" class="botonerab" name="fiscalizacionDTO.idTranzon" disabled="disabled" 
									onchange="actualizarComboMarcacion();">
								<option value="-1">- Seleccione -</option>
							</select>					
						</td>
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Marcacion'/>
						</td>
						<td width="4%"></td>
						<td align="left">
							<select id="idMarcacion" class="botonerab" name="fiscalizacionDTO.idMarcacion" disabled="disabled" 
									onchange="actualizarComboRodal();">
								<option value="-1">- Seleccione -</option>
							</select>					
						</td>
					</tr>
					<tr>
						<td width="47%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.Rodal'/>
						</td>
						<td width="4%"></td>
						<td align="left">
							<select id="idRodal" class="botonerab" name="fiscalizacionDTO.rodal.id" disabled="disabled">
								<option value="-1">- Seleccione -</option>						
							</select>					
						</td>
					</tr>															
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>

		<!-- MUESTRAS -->
		<tr id="trMuestras" style="display: none">
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo"><bean:message key='SIIF.subTitulo.Muestras'/></td>
					</tr>
					<tr>
						<td height="20" colspan="3"></td>
					</tr>
					<tr>
						<td width="35%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.CantidadMuestras'/>
						</td>
						<td>
							<input id="idCantMuestras" class="botonerab" type="text" 
										onkeypress="javascript:esNumerico(event);">
						</td>
						<td width="35%" align="left">
							<input class="botonerab" type="button" value="Agregar" onclick="agregarMuestras();">
							<input class="botonerab" type="button" value="Remover" onclick="removerMuestras();">
						</td>
					</tr>
					<tr>
						<td height="10" colspan="3"></td>
					</tr>

					<tr>
						<td colspan="3">
							<table id="tablaMuestras" border="0" class="cuadrado" align="center"
								width="70%" cellpadding="2" cellspacing="0" >
								
								
							</table>																	
						</td>
					</tr>

					<tr id="calcularVolumen">
						<td height="10" colspan="4">
							<input class="botonerab" type="button" value="Calcular Volumen" onclick="calcularVolumen();">
						</td>
					</tr>
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
	
	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4"><input class="botonerab" type="button" value="Aceptar" id="enviar"
				onclick="javascript:submitir();"> 
				<input class="botonerab" type="button" value="Cancelar"
					   onclick="javascript:parent.location= contextRoot() + '/jsp.do?page=.index'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>
</html:form>
