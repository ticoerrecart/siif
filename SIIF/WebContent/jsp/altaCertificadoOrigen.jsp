<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/ui/jquery-ui-1.8.10.custom.min.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>		
<script type="text/javascript"
	src="<html:rewrite page='/js/certificadoOrigen.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/Concurrent.Thread-full-20090713.js'/>"></script>

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">
	

<script>
	/*$(function() {

		$( "#datepickerFecha" ).datepicker({ dateFormat: 'dd/mm/yy'});		
	});*/
</script>

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function volverAltaGuia(){	
	
	var entidad = $('#paramIdTipoDeEntidad').val();
	var productor = $('#paramProductor').val();
	var rodal = $('#idRodal').val();
	parent.location = contextRoot() +
	'/guiaForestal.do?metodo=recuperarTiposDeEntidadParaAltaGFB&idTipoDeEntidad=' + entidad +  '&idProductor=' + productor + '&idRodal=' + rodal;		
}

function submitir(){

	validarForm("guiaForestalForm","../guiaForestal","validarAltaGuiaForestalBasicaForm","GuiaForestalForm");
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

function reemplazarCaracter(caracAReemp, caracterNuevo, stringViejo){

	var stringNuevo = "";
	
	for(var i=0;i<stringViejo.length;i++){
		if(stringViejo.charAt(i) == caracAReemp){
			stringNuevo += caracterNuevo;	
		}else{
			stringNuevo += stringViejo.charAt(i);
		}	
	}

	return stringNuevo;
	
}


var clase2;
function pintarFila(idTr){

	$('#tr'+idTr).attr("class", "seleccionado");	
}

function despintarFila(idTr){

	if(!$('#idCheck'+idTr).is(':checked')){
		if(idTr%2){
			clase2 = "par";		
		}else{
			clase2 = "";
		}	
		$('#tr'+idTr).attr("class", clase2);
	}		
}

function mostrarFiscalizacion(idFiscalizacion){

	$("#idGuia").hide();	
	$("#idDivFiscalizacion").load("../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&idFiscalizacion="+idFiscalizacion+"&strForward=exitoCargarFiscalizacionDesdeAltaGFB");
	$("#idDivFiscalizacion").show(); 
	$("#errores").hide();
}

function volverAltaGFB(){

	$("#idGuia").show();
	$("#idDivFiscalizacion").hide();
	$("#idDivFiscalizacion").empty();
	$("#errores").show();
}



//-----------------------------------------------------//

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${warning}</div>

<div id="idGuia">
<html:form action="certificadoOrigen" styleId="CertificadoOrigenForm">
	<html:hidden property="metodo" value="altaCertificadoOrigen" />
	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td class="azulAjustado">
				<bean:message key='SIIF.titulo.AltaCertificadoOrigen'/>
			</td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
		<tr>
			<td>		
				<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="4" class="grisSubtitulo">Datos Exportador</td>
					</tr>
					<tr>
						<td colspan="4" height="15"></td>
					</tr>						
					<tr>
						<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoEntidad'/></td>
						<td width="30%">
							<select id="selectTiposDeEntidadExportador" class="botonerab" onchange="actualizarComboExportador();">
								<option value="-1">-Seleccione una Entidad-</option>
								<c:forEach items="${tiposEntidad}" var="tipoDeEntidad" varStatus="i">
									<option value="${tipoDeEntidad.name}">
										<c:out value="${tipoDeEntidad.descripcion}"></c:out>
									</option>						
								</c:forEach>	
							</select>
						</td>
						
						<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Exportador'/></td>
						<td width="32%" align="center">
							<select id="idExportador" class="botonerab" name="certificadoOrigenDTO.exportador.id" onchange="actualizarDatosExportador();" 
									disabled="disabled">
								<option value="-1">-Seleccione un Exportador-</option>
							</select>
						</td>																
					</tr>	
					
					<tr>
						<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroMatricula'/></td>
						<td width="30%">
							<input class="botonerab" type="text" size="27" id="nroMatricula" readonly="readonly">							
						</td>
						
						<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Cuit'/></td>
						<td width="32%" align="center">
							<input type="text" class="botonerab" size="2" id="prefijoCuit" readonly="readonly">
							<input type="text" class="botonerab" size="9" maxlength="8" id="nroCuit" readonly="readonly">
							<input type="text" class="botonerab" size="2" id="sufijoCuit" readonly="readonly">
						</td>																
					</tr>
											
					<tr>
						<td colspan="4" height="15"></td>
					</tr>				
				</table>		
			</td>
		</tr>	
		<tr>
			<td height="20"></td>
		</tr>
		<tr>
			<td>		
				<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="4" class="grisSubtitulo">Datos Productor</td>
					</tr>
					<tr>
						<td colspan="4" height="15"></td>
					</tr>						
					<tr>
						<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoEntidad'/></td>
						<td width="30%">
							<select id="selectTiposDeEntidad" style="width: 16em" class="botonerab" onchange="actualizarComboProductores();">
								<option value="-1">-Seleccione una Entidad-</option>
								<c:forEach items="${tiposEntidad}" var="tipoDeEntidad" varStatus="i">
									<option value="${tipoDeEntidad.name}">
										<c:out value="${tipoDeEntidad.descripcion}"></c:out>
									</option>						
								</c:forEach>	
							</select>
						</td>
						
						<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
						<td width="32%" align="center">
							<select id="idProductor" class="botonerab" style="width: 16em" name="fiscalizacionDTO.productorForestal.id" 
									onchange="actualizarComboPMF();" disabled="disabled">
								<option value="-1">-Seleccione un Productor-</option>
							</select>
						</td>																
					</tr>	
					
					<tr>
						<td width="17%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.PeríodoForestal'/>							
						</td>						
						<td width="30%" align="center">
							<select id="periodo" class="botonerab" style="width: 16em" onchange="mostrarFiscalizaciones();">
								<c:forEach items="${periodos}" var="per">
									<option value="${per.periodo}">
										<c:out value="${per.periodo}"></c:out>
									</option>
								</c:forEach>
							</select>											
						</td>	
						
						<td width="21%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.PlanManejoForestal'/>						
						</td>
						<td width="32%" align="center">
							<select id="idPMF" class="botonerab" disabled="disabled" style="width: 16em" onchange="mostrarFiscalizaciones();">
								<option value="-1">- Seleccione -</option>						
							</select>	
						</td>																
					</tr>
					
					<tr>
						<td width="17%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.ReservaForestal'/>
						</td>						
						<td width="30%" align="center">
							<input class="botonerab" type="text" size="27" id="idReservaForestal">					
						</td>	
						
						<td colspan="2"></td>
																
					</tr>					
					
					<tr>
						<td width="17%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.NroFactura'/>
						</td>						
						<td width="30%" align="center">
							<input class="botonerab" type="text" size="27" id="idNroFactura" onkeypress="javascript:esNumerico(event);">					
						</td>	
						<td width="21%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.VolumenTransferido'/>						
						</td>
						<td width="32%" align="center">
							<input class="botonerab" type="text" size="27" id="idVolumenTransferido" 
									onkeypress="javascript:esNumericoConDecimal(event);">
						</td>																
					</tr>					
					
					<tr>
						<td colspan="4" height="15"></td>
					</tr>				
				</table>		
			</td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>

		<tr>
			<td id="divCargando" style="display: none">
				<img src="<html:rewrite page='/imagenes/cargando.gif'/>">
			</td>	
			<td>
				<div id="divFiscalizaciones"></div>		
			</td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>		
										
	</table>									

	<!-- 
	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
		<tr>
			<td width="30%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha'/></td>
			<td align="left">		
				<input id="datepickerFecha" type="text" name="guiaForestal.fecha" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>
	 -->
	
	<table border="0" class="cuadrado" align="center" width="80%"
		cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td height="20" colspan="4">
				<input type="button" value="Aceptar" id="enviar" class="botonerab" onclick="javascript:submitir();" > 
				<input class="botonerab" type="button" value="Cancelar" 
						onclick="javascript:parent.location= contextRoot() + '/jsp.do?page=.index'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>
</html:form>
</div>	 				  
<div id="idDivFiscalizacion" style="display: none;">

</div>