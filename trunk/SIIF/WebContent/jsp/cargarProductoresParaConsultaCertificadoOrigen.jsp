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

		//deshabilitarLocalizacion([ "idZMF" ]);

		if(idTipoDeEntidad != "-1"){
			$('#idProductor').attr('disabled',false);
			EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresCallback );		
		}else{
			dwr.util.removeAllOptions("idProductor");
			var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
			dwr.util.addOptions("idProductor", data, "id", "nombre");
			$('#idProductor').attr('disabled','disabled');
		}		
		$('#divDetalle').hide(600);
		$('#divDetalle').html("");
	}

	function actualizarProductoresCallback(productores){
		dwr.util.removeAllOptions("idProductor");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("idProductor", data, "id", "nombre");	
		dwr.util.addOptions("idProductor", productores,"id","nombre");	
	}


function actualizarComboPMF() {
	idPF = $('#idProductor').val();
	UbicacionFachada.getPMFs(idPF, {
		callback : actualizarComboPMFCallback,
		async : false
	});
}

function actualizarComboPMFCallback(pmfs) {
	dwr.util.removeAllOptions("idPMF");
	var data = [ {
		nombre : "- Seleccione -",
		id : -1
	} ];
	dwr.util.addOptions("idPMF", data, "id", "nombre");
	dwr.util.addOptions("idPMF", pmfs, "id", "nombreExpediente");
	$('#idZMF').removeAttr('disabled');
	$(".plan").show();
}

function actualizarComboArea() {
	var idPF = $('#idProductor').val();
	UbicacionFachada.getAreasDTO(idPF, {
		callback : actualizarComboAreaCallback,
		async : false
	});
}

function actualizarComboAreaCallback(areas) {
	dwr.util.removeAllOptions("idArea");
	var data = [ {
		nombre : "- Seleccione -",
		id : -1
	} ];
	dwr.util.addOptions("idArea", data, "id", "nombre");
	dwr.util.addOptions("idArea", areas, "id", "fullNombre");
	$('#idZMF').removeAttr('disabled');
	$(".area").show();
}


function cambioComboProductores() {
	$('#idZMF').removeAttr('disabled');
	$('#idZMF').val('0');
	cambioComboZona();
}

function cambioComboZona() {

	var zmf = $('#idZMF').val();
	var idPF = $('#idProductor').val();
	
	if (idPF == 0 || zmf == 0) {
		$(".area").hide();
		$(".plan").hide();
	} else {
		if (zmf == 1) {
			$(".plan").show();
			$(".area").hide();

			actualizarComboPMF();
		}
		if (zmf == 2) {
			$(".area").show();
			$(".plan").hide();

			actualizarComboArea();
		}
	}

}


	function mostrarListaCertificadosOrigen(){

		var idProductor = $('#idProductor').val();
		var periodo= $('#selectPeriodo').val();
		var zmf = $('#idZMF').val();
		var idLocalizacion = -1;
		if (zmf == 1) {
			idLocalizacion =  $('#idPMF').val();
		}
		if (zmf == 2) {
			idLocalizacion =  $('#idArea').val();
		}

		$('#divCargando').show();	
		$('#divDetalle').html("");
		//alert(idProductor + "/" + idLocalizacion + "/" + periodo);
		if(idProductor != "" && idProductor != "-1" && idLocalizacion != "" && idLocalizacion != "-1"){
			$('#divDetalle').load('../../certificadoOrigen.do?metodo=recuperarCertificadosOrigenParaConsulta&idProductor='+idProductor+'&periodo='+periodo+'&idLocalizacion='+idLocalizacion);
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

		$('#idProductor').attr('disabled',false);
		EntidadFachada.getEntidadesPorTipoDeEntidadDTO(idTipoDeEntidad,actualizarProductoresVolverCallback );		

		$('#divDetalle').hide(600);
		$('#divDetalle').html("");
	}


	
	function actualizarProductoresVolverCallback(productores){
		dwr.util.removeAllOptions("idProductor");
		var data = [ { nombre:"-Seleccione un Productor-", id:-1 }];
		dwr.util.addOptions("idProductor", data, "id", "nombre");	
		dwr.util.addOptions("idProductor", productores,"id","nombre");

		$('#idProductor').val($('#paramProductor').val());
		idPF = $('#idProductor').val();

		if($('#paramPMF').val()>0){
			$('#idZMF').val("1");
			cambioComboZona();
			$('#idPMF').val($('#paramPMF').val());

		}else{
			if($('#paramArea').val()>0){
				$('#idZMF').val("2");
				cambioComboZona();
				$('#idArea').val($('#paramArea').val());
			}
		}
		
		mostrarListaCertificadosOrigen();
		//UbicacionFachada.getPMFs(idPF, actualizarComboPMFVolverCallback);
	}
	
/*	function actualizarComboPMFVolverCallback(pmfs) {
		
		 dwr.util.removeAllOptions("idPMF");
		var data = [ {
			nombre : "- Seleccione -",
			id : -1
		} ];
		dwr.util.addOptions("idPMF", data, "id", "nombrePMF");
		dwr.util.addOptions("idPMF", pmfs, "id", "expedientePMF");
		$('#idPMF').removeAttr('disabled');

		$('#idPMF').val($('#idPMF').val());
		$('#selectPeriodo').val($('#periodoForestal').val());
		
		mostrarListaCertificadosOrigen();
	}
	*/
</script>

<input id="paramIdTipoDeEntidad" type="hidden" value="${idTipoDeEntidad}">
<input id="paramProductor" type="hidden" value="${idProductor}">
<input id="paramPMF" type="hidden" value="${idPMF}">
<input id="paramArea" type="hidden" value="${idArea}">
<input id="periodoForestal" type="hidden" value="${periodoForestal}">

<div id="errores" class="rojoAdvertencia">${error}</div>
<br>
<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">

	<tr>
		<td class="azulAjustado">Consulta de Acta de Verificación de C.O</td>
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
					<td class="botoneralNegritaRight" width="30%"><bean:message key='SIIF.label.TipoDeProductor'/></td>
					<td class="botonerab" align="left">
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
					<td class="botonerab" align="left">
						<select id="idProductor" class="botonerab" disabled="disabled" onchange="cambioComboProductores();">
							<option value="">-Seleccione un Productor-</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="botoneralNegritaRight">
						<bean:message key='SIIF.label.ZonaManejoForestal'/>						
					</td>
					<td align="left">
						<select id="idZMF" class="botonerab" 
								onchange="cambioComboZona();" disabled="disabled">
							<option value="0">--Seleccione una Opcion de Zona--</option>
							<option value="1">--PMF--</option>
							<option value="2">--Area de Cosecha--</option>
						</select>	
					</td>						
				</tr>

				<tr class="area" style="display: none">	
					<td class="botoneralNegritaRight"><bean:message key='SIIF.label.AreaDeCosecha'/></td>
					
					<td align="left"> 
						<select id="idArea" class="botonerab" name="certificadoOrigenDTO.areaDeCosecha.id" onchange="mostrarListaCertificadosOrigen();">
							<option value="-1">- Seleccione -</option>						
						</select>	
					</td>
				</tr>
						
				<tr class="plan" style="display: none">
					<td class="botoneralNegritaRight">
						<bean:message key='SIIF.label.PlanManejoForestal'/>
					</td>
					<td align="left">
						<select id="idPMF" class="botonerab" name="certificadoOrigenDTO.pmf.id" onchange="mostrarListaCertificadosOrigen();">
							<option value="-1">- Seleccione -</option>
						</select>
					</td>
				</tr>

				<tr>
					<td class="botoneralNegritaRight">
						<bean:message key='SIIF.label.PeríodoForestal'/>
					</td>
					<td class="botonerab" align="left">
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