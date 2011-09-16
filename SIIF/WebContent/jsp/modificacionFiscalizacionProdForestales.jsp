<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib uri="/WEB-INF/calendario.tld" prefix="cal"%>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>	
<script type="text/javascript"
	src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript">

function submitir(){

	validarForm("fiscalizacionForm","../fiscalizacion","validarFiscalizacionForm","FiscalizacionForm");
}

function volver(){	
	//document.forms[0].elements["metodo"].value = "recuperarLocalidadesParaFiscalizacionesAModificar";
	//document.forms[0].submit();

	var localidad = $('#paramLocalidad').val();
	var productor = $('#paramProductor').val();	
	parent.location = contextRoot() +  
	'/fiscalizacion.do?metodo=recuperarLocalidadesParaFiscalizacionesAModificar&idLocalidad=' + localidad + 
	'&idProductor=' + productor;	
}

var i=2;
var cantTotales = parseInt(0);
var indiceDiv = 1;
function agregarMuestras(){

	var cantMuestras = $("#idCantMuestras").val();
	
	if(cantMuestras != "" && cantMuestras > 0){
		//$("#tablaMuestras").show();
		$('#tablaMuestras').fadeIn(600);
		
		var cant = parseInt($("#idCantMuestras").val());
		//$("#tabla").load('/SIIF/jsp/bloqueMuestrasFiscalizacion.jsp?cantMuestras=' + cant);
	 
	 	var iDiv = parseInt(indiceDiv)+1;
		 
		$('#muestrasAux').load('/SIIF/jsp/bloqueMuestrasFiscalizacion.jsp?cantMuestras=' + cant + '&cantTotal='+ cantTotales
				+ '&indiceDiv='+ iDiv, function(){
					//$("#prueba1").append($("#dummy").html()); NO ANDA ESTA LINEA EN IE
					var id = "tabla"+indiceDiv;
					document.getElementById(id).innerHTML = //document.getElementById("tabla").innerHTML + 
					document.getElementById("muestrasAux").innerHTML;
					cantTotales = parseInt(cantTotales) + parseInt(cant);
					indiceDiv++;				 			
				}
		);	
	}	
}

function removerMuestras(){

	var cant = $("#idCantMuestras").val();
	var i=0;
	while(i<cant && cantTotales>0){
		var id = cantTotales-1;
		$("#espAntes"+id).remove();
		$("#fila"+ id).remove();
		$("#espDespues"+id).remove();
			
		cantTotales--;
		i++;
	}	

	if(cantTotales == 0){
		$("#tablaMuestras").hide();
	}
	
}

function actualizarComboPMF(){
	idPF = $('#idProductor').val();

	deshabilitarLocalizacion(["idPMF","idTranzon","idMarcacion","idRodal"]);
	
	if (idPF > 0 ){
		UbicacionFachada.getPMFs(idPF,actualizarComboPMFCallback );
	}
}

function actualizarComboPMFCallback(pmfs){
	dwr.util.removeAllOptions("idPMF");
	var data = [ { nombre:"- Seleccione -", id:-1 }];
	dwr.util.addOptions("idPMF", data, "id", "nombre");	
	dwr.util.addOptions("idPMF", pmfs,"id","nombreExpediente");	
	$('#idPMF').attr('disabled','');
}

function actualizarComboTranzon(){
	idPMF = $('#idPMF').val();

	deshabilitarLocalizacion(["idTranzon","idMarcacion","idRodal"]);
	
	if (idPMF > 0 ){
		UbicacionFachada.getTranzonesById(idPMF,actualizarComboTranzonCallback );
	}
}

function actualizarComboTranzonCallback(tranzones){
	dwr.util.removeAllOptions("idTranzon");
	var data = [ { nombre:"- Seleccione -", id:-1 }];
	dwr.util.addOptions("idTranzon", data, "id", "nombre");	
	dwr.util.addOptions("idTranzon", tranzones,"id","numeroDisposicion");	
	$('#idTranzon').attr('disabled','');
}

function actualizarComboMarcacion(){
	idTranzon = $('#idTranzon').val();

	deshabilitarLocalizacion(["idMarcacion","idRodal"]);
	
	if (idTranzon > 0 ){
		UbicacionFachada.getMarcacionesById(idTranzon,actualizarComboMarcacionCallback );
	}
}

function actualizarComboMarcacionCallback(marcaciones){
	dwr.util.removeAllOptions("idMarcacion");
	var data = [ { nombre:"- Seleccione -", id:-1 }];
	dwr.util.addOptions("idMarcacion", data, "id", "nombre");	
	dwr.util.addOptions("idMarcacion", marcaciones,"id","disposicion");	
	$('#idMarcacion').attr('disabled','');
}

function actualizarComboRodal(){
	idMarcacion = $('#idMarcacion').val();

	deshabilitarLocalizacion(["idRodal"]);
	
	if (idMarcacion > 0 ){
		UbicacionFachada.getRodalesById(idMarcacion,actualizarComboRodalCallback );
	}
}

function actualizarComboRodalCallback(rodales){
	dwr.util.removeAllOptions("idRodal");
	var data = [ { nombre:"- Seleccione -", id:-1 }];
	dwr.util.addOptions("idRodal", data, "id", "nombre");	
	dwr.util.addOptions("idRodal", rodales,"id","nombre");	
	$('#idRodal').attr('disabled','');
}

function deshabilitarLocalizacion(ids){

	var data = [ { nombre:"- Seleccione -", id:-1 }];
	for(i=0;i<ids.length;i++){
		dwr.util.removeAllOptions(ids[i]);
		dwr.util.addOptions(ids[i], data, "id", "nombre");
		$('#'+ids[i]).attr('disabled','disabled');				
	}
	
}
</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>
<input id="paramLocalidad" type="hidden" value="${fiscalizacion.productorForestal.localidad.id}">
<input id="paramProductor" type="hidden" value="${fiscalizacion.productorForestal.id}">
<html:form action="fiscalizacion" styleId="fiscalizacionForm">
	<html:hidden property="metodo" value="modificacionFiscalizacion" />

	<html:hidden property="fiscalizacion.id" value="${fiscalizacion.id}" />
	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				Modificación de	Fiscalización de Productos Forestales
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Localidad</td>
			<td>
			
				<input class="botonerab" type="text" size="20" disabled="disabled"
					   value="<c:out value='${fiscalizacion.productorForestal.localidad.nombre}'></c:out>">			
			
			</td>
			<td class="botoneralNegritaRight">Productor Forestal</td>
			<td>
				<html:select styleId="idProductor" property="idProductorForestal" styleClass="botonerab"
							 value="${fiscalizacion.productorForestal.id}" onchange="actualizarComboPMF();">

					<c:forEach items="${productores}" var="entidad">
						<html:option value="${entidad.id}">
							<c:out value="${entidad.nombre}"></c:out>
						</html:option>
					</c:forEach>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Fecha</td>
			<td>
				<input name="fecha" class="botonerab" type="text" size="16" readonly="readonly"
					   value="<fmt:formatDate value='${fiscalizacion.fecha}' pattern='dd/MM/yyyy' />">
				<cal:cal propiedad="fecha" formato="date11" name="fecha" />			
			</td>
			<td class="botoneralNegritaRight">Período Forestal</td>
			<td>
				<input name="fiscalizacion.periodoForestal" class="botonerab" type="text" size="20"
					   value="<c:out value='${fiscalizacion.periodoForestal}'></c:out>">			
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Cantidades(Unidades)</td>
			<td>
				<input name="fiscalizacion.cantidadUnidades" class="botonerab" type="text" size="20"
					   value="<c:out value='${fiscalizacion.cantidadUnidades}'></c:out>"
					   onkeypress="javascript:esNumerico(event);"></td>
			<td class="botoneralNegritaRight">Tipo de Producto</td>
			<td>
				<html:select property="idTipoProductoForestal" styleClass="botonerab"
					value="${fiscalizacion.tipoProducto.id}">				
					<c:forEach items="${tiposProducto}" var="tipoProducto">
						<html:option value="${tipoProducto.id}">
							<c:out value="${tipoProducto.nombre}"></c:out>
						</html:option>
					</c:forEach>
				</html:select>			
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Cantidades(m³)</td>
			<td>
				<input name="fiscalizacion.cantidadMts" class="botonerab" type="text" size="20"
					   value="<c:out value='${fiscalizacion.cantidadMts}'></c:out>" 
					   onkeypress="javascript:esNumericoConDecimal(event);">
			</td>
			<td class="botoneralNegritaRight">Tamaño de la Muestra</td>
			<td>
				<input name="fiscalizacion.tamanioMuestra" class="botonerab" type="text" size="20"
					   value="<c:out value='${fiscalizacion.tamanioMuestra}'></c:out>">
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
				<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo">Localización</td>
					</tr>
					<tr>
						<td colspan="3" height="10"></td>
					</tr>				
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							Plan de Manejo Forestal
						</td>
						<td>
							<html:select styleId="idPMF" property="idPlanManejoForestal" styleClass="botonerab"
								value="${fiscalizacion.rodal.marcacion.tranzon.pmf.id}" onchange="actualizarComboTranzon();">
								<option value="-1">- Seleccione -</option>
								<c:forEach items="${pmfs}" var="pmf">
									<html:option value="${pmf.id}">
										<c:out value="${pmf.nombre} - ${pmf.expediente}"></c:out>
									</html:option>
								</c:forEach>
							</html:select>																
						</td>
						<td width="25%"></td>
					</tr>		
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							Tranzon
						</td>
						<td>
							<html:select styleId="idTranzon" property="idTranzon" styleClass="botonerab"
								value="${fiscalizacion.rodal.marcacion.tranzon.id}" onchange="actualizarComboMarcacion();">
								<option value="-1">- Seleccione -</option>
								<c:forEach items="${tranzones}" var="tranzon">
									<html:option value="${tranzon.id}">
										<c:out value="${tranzon.numero} - ${tranzon.disposicion}"></c:out>
									</html:option>
								</c:forEach>
							</html:select>											
						</td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							Marcacion
						</td>
						<td>				
							<html:select styleId="idMarcacion" property="idMarcacion" styleClass="botonerab"
								value="${fiscalizacion.rodal.marcacion.id}" onchange="actualizarComboRodal();">
								<option value="-1">- Seleccione -</option>
								<c:forEach items="${marcaciones}" var="marcacion">
									<html:option value="${marcacion.id}">
										<c:out value="${marcacion.disposicion}"></c:out>
									</html:option>
								</c:forEach>
							</html:select>											
						</td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							Rodal
						</td>
						<td>		
							<html:select styleId="idRodal" property="idRodal" styleClass="botonerab"
								value="${fiscalizacion.rodal.id}">
								<option value="-1">- Seleccione -</option>
								<c:forEach items="${rodales}" var="rodal">
									<html:option value="${rodal.id}">
										<c:out value="${rodal.nombre}"></c:out>
									</html:option>
								</c:forEach>
							</html:select>												
						</td>
						<td width="25%"></td>
					</tr>																
					<tr>
						<td colspan="2" height="10"></td>
					</tr>				
				</table>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>			
		
		<!-- MUESTRAS -->
		<tr>
			<td colspan="4" align="left">
				<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="3" class="grisSubtitulo">Muestras</td>
					</tr>
					<tr>
						<td height="20" colspan="3"></td>
					</tr>
					<tr>
						<td width="35%" class="botoneralNegritaRight">
							Cantidad Muestras
						</td>
						<td>
							<input id="idCantMuestras" class="botonerab" type="text" onkeypress="javascript:esNumerico(event);">
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
								   width="70%" cellpadding="2" cellspacing="0" style="display: ">
								<tr>
									<td class="azulAjustado" width="3%"></td>
									<td class="azulAjustado" width="33%">Largo</td>
									<td class="azulAjustado" width="32%">Diametro 1</td>
									<td class="azulAjustado" width="32%">Diametro 2</td>
								</tr>
								<tr>
									<td colspan="4">
										<div id="tabla1">
											<c:if test="${fn:length(fiscalizacion.muestra) > 0}">
												<table id="tablaMuestras" border="0" class="cuadradoSinBorde" align="center"
													width="70%" cellpadding="2" cellspacing="0">																						

													<c:forEach items="${fiscalizacion.muestra}" varStatus="i" var="muestra">
												
														<tr id="espAntes${i.index}">
															<td height="5" colspan="4"></td>
														</tr>
														<tr id="fila${i.index}">
															<td class="botoneralNegritaRight">${i.index+1}</td>
															<td>
																<input class="botonerab" type="text" 
																	name="muestras[${i.index}].largo"
																	value="${muestra.largo}">
															</td>
															<td>
																<input class="botonerab" type="text"
																	name="muestras[${i.index}].diametro1"
																	value="${muestra.diametro1}">
															</td>
															<td>
																<input class="botonerab" type="text"
																	name="muestras[${i.index}].diametro2"
																	value="${muestra.diametro2}">
															</td>
														</tr>
													
														<tr id="espDespues${i.index}">
															<td height="5" colspan="4"></td>
														</tr>												
												
													</c:forEach>
												</table>												
												<div id="tabla2"></div>		
												
												<script type="text/javascript">
													cantTotales = <c:out value="${fn:length(fiscalizacion.muestra)}"></c:out>;
													indiceDiv = 2;				
												</script>																			
											</c:if>																																																	
										</div>									
									</td>
								</tr>													
							</table>																	
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
		
	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4">
				<input type="button" value="Modificar" class="botonerab" 
					   id="enviar" onclick="javascript:submitir();" >
				<input type="button" class="botonerab" value="Volver" 
					   onclick="javascript:volver();">			
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
	</table>	
</html:form>
