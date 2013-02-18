<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/Concurrent.Thread-full-20090713.js'/>"></script>


<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
	
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>	

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

	function submitir(){
		validarForm("certificadoOrigenForm","../certificadoOrigen","validarNroCertificadoForm","CertificadoOrigenForm");
	}

	function cargarProductores(){

		var idTipoDeEntidad = $('#selectTiposDeEntidad').val();

		deshabilitarLocalizacion([ "selectPmf" ]);
		
		if(idTipoDeEntidad != "-1"){
			$('#selectProductores').attr('disabled',false);
			EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresCallback );		
		}else{
			dwr.util.removeAllOptions("selectProductores");
			var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
			dwr.util.addOptions("selectProductores", data, "id", "nombre");		
			$('#selectProductores').attr('disabled','disabled');
		}		
		$('#divDetalle').hide(600);
		$('#divDetalle').html("");
	}

	function actualizarProductoresCallback(productores){
		dwr.util.removeAllOptions("selectProductores");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("selectProductores", data, "id", "nombre");	
		dwr.util.addOptions("selectProductores", productores,"id","nombre");	
	}

	function actualizarComboPMF() {
		idPF = $('#selectProductores').val();

		deshabilitarLocalizacion([ "selectPmf" ]);

		if (idPF > 0) {
			UbicacionFachada.getPMFs(idPF, actualizarComboPMFCallback);
		}						
	}

	function actualizarComboPMFCallback(pmfs) {
		
		dwr.util.removeAllOptions("selectPmf");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("selectPmf", data, "id", "nombre");
		dwr.util.addOptions("selectPmf", pmfs, "id", "nombreExpediente");
		$('#selectPmf').removeAttr('disabled');
	}
	
	function mostrarListaCertificadosOrigen(){

		var idProductor = $('#selectProductores').val();
		var periodo= $('#selectPeriodo').val();
		var idPmf = $('#selectPmf').val();
		
		//var forward = $('#paramForward').val();
		$('#divCargando').show();	
		$('#divDetalle').html("");
		
		if(idProductor != "" && idProductor != "-1" && idPmf != "" && idPmf != "-1"){
			$('#divDetalle').load('../../certificadoOrigen.do?metodo=recuperarCertificadosOrigenParaConsulta&idProductor='+idProductor+'&periodo='+periodo+'&idPmf='+idPmf);
			$('#divDetalle').hide();
			$('#divDetalle').fadeIn(600);

			Concurrent.Thread.create(function(){
			    while ($('#divDetalle').html() == "") {}
			    $('#divCargando').hide();
			});		
			
		}else{
			$('#divDetalle').hide(600);
			$('#divDetalle').html("");
			$('#divCargando').hide();
		}	
	}

	function deshabilitarLocalizacion(ids) {

		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		for (i = 0; i < ids.length; i++) {
			dwr.util.removeAllOptions(ids[i]);
			dwr.util.addOptions(ids[i], data, "id", "nombre");
			$('#' + ids[i]).attr('disabled', 'disabled');
		}
	}

	var tr = null;
	var clase = null;
	function pintarFila(idTr){
		
		if(tr!=null){
			$('#tr'+tr).attr("class", clase);	
		}
		tr=idTr;
		clase = $('#tr'+tr).attr("class");
		$('#tr'+tr).attr("class", "seleccionado");	
	}

	function cargarProductoresVolver(){
		$('#selectTiposDeEntidad').val($('#paramIdTipoDeEntidad').val());
		var idTipoDeEntidad = $('#paramIdTipoDeEntidad').val();

		$('#selectProductores').attr('disabled',false);
		EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresVolverCallback );		

		$('#divDetalle').hide(600);
		$('#divDetalle').html("");
	}

	function actualizarProductoresVolverCallback(productores){
		dwr.util.removeAllOptions("selectProductores");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("selectProductores", data, "id", "nombre");	
		dwr.util.addOptions("selectProductores", productores,"id","nombre");

		$('#selectProductores').val($('#paramProductor').val());
		idPF = $('#selectProductores').val();
		
		UbicacionFachada.getPMFs(idPF, actualizarComboPMFVolverCallback);
	}

	function actualizarComboPMFVolverCallback(pmfs) {
		
		dwr.util.removeAllOptions("selectPmf");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("selectPmf", data, "id", "nombre");
		dwr.util.addOptions("selectPmf", pmfs, "id", "nombreExpediente");
		$('#selectPmf').removeAttr('disabled');

		$('#selectPmf').val($('#idPMF').val());
		$('#selectPeriodo').val($('#periodoForestal').val());
		
		mostrarListaCertificadosOrigen();
	}
	
</script>

<input id="paramIdTipoDeEntidad" type="hidden" value="${idTipoDeEntidad}">
<input id="paramProductor" type="hidden" value="${idProductor}">
<input id="idPMF" type="hidden" value="${idPMF}">
<input id="periodoForestal" type="hidden" value="${periodoForestal}">

<div id="errores" class="rojoAdvertencia">${error}</div>
<br>
<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">

	<tr>
		<td class="azulAjustado">Consulta de Certificados de Orígen</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<html:form action="certificadoOrigen" styleId="certificadoOrigenForm">
				<html:hidden property="metodo" value="cargarCertificadoOrigenPorNroCertificado"/>		
				<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
					<tr>
						<td height="10"></td>
					</tr>
					<tr>
						<td width="30%" class="botoneralNegritaRight">
							Nro de Certificado de Orígen
						</td>
						<td width="10%">
							
						</td>						
						<td align="left">
							<input id="idNroGuia" class="botonerab" type="text" size="20" name="certificadoOrigenDTO.nroCertificado" 
								onkeypress="javascript:esNumerico(event);">
							<input class="botonerab" type="button" value="Buscar" onclick="javascript:submitir();">
						</td>	
									
					</tr>				
					<tr>
						<td height="10"></td>
					</tr>				
				</table>
			</html:form>	
		</td>
	</tr>				
	<tr>
		<td height="10"></td>
	</tr>
	
	
	<tr>
		<td>
			<table border="0" class="cuadrado" align="center" width="70%"
				cellpadding="2">
				<tr>
					<td height="10" colspan="3"></td>
				</tr>
				<tr>
					<td class="botoneralNegritaRight" width="30%"><bean:message key='SIIF.label.TipoEntidad'/></td>
					<td class="botonerab">
						<select id="selectTiposDeEntidad" class="botonerab" onchange="cargarProductores()">
							<option value="-1">-Seleccione un Tipo de Entidad-</option>
							<c:forEach items="${tiposEntidad}" var="tipoDeEntidad" varStatus="i">
								<option value="<c:out value='${tipoDeEntidad.name}'></c:out>">
									<c:out value="${tipoDeEntidad.descripcion}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
					<td width="15%"></td>					
				</tr>
				
				<tr>
					<td class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
					<td class="botonerab">
						<select id="selectProductores" class="botonerab" disabled="disabled" onchange="actualizarComboPMF()">
							<option value="">-Seleccione un Productor-</option>
						</select>
					</td>
				</tr>				
				<tr>
					<td class="botoneralNegritaRight"><bean:message key='SIIF.label.PlanManejoForestal'/></td>
					<td class="botonerab">
							<select id="selectPmf" class="botonerab" disabled="disabled" style="width: 16em" 
									onchange="mostrarListaCertificadosOrigen();">
								<option value="-1">- Seleccione -</option>						
							</select>
					</td>
				</tr>	
				<tr>
					<td class="botoneralNegritaRight">
						<bean:message key='SIIF.label.PeríodoForestal'/>
					</td>
					<td class="botonerab">
						<select id="selectPeriodo" class="botonerab" style="width: 16em" onchange="mostrarListaCertificadosOrigen();">
							<c:forEach items="${periodos}" var="per">
								<option value="${per.periodo}">
									<c:out value="${per.periodo}"></c:out>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>											 				 
				<tr>
					<td height="10" colspan="3"></td>
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
			<div id="divDetalle"></div>		
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>	
</table>

<script type="text/javascript">	
	var idTipoDeEntidad = $('#paramIdTipoDeEntidad').val();
	if(idTipoDeEntidad != null && idTipoDeEntidad != ""){
		cargarProductoresVolver();
	}	
</script>