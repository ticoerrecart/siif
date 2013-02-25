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
	src="<html:rewrite page='/dwr/interface/LocalidadFachada.js'/>"></script>			
<script type="text/javascript"
	src="<html:rewrite page='/js/certificadoOrigen.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/Concurrent.Thread-full-20090713.js'/>"></script>

<link rel="stylesheet" href="<html:rewrite page='/css/ui-lightness/jquery-ui-1.8.10.custom.css'/>"
	type="text/css">


<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";



function volverConsultaCertificado(){	

	var productor = $('#idParamProductor').val();
	var entidad = $('#idParamIdTipoDeEntidad').val();
	var pmf = $('#idPMF').val();
	var periodoForestal = $('#periodoForestal').val();
	
	parent.location = contextRoot() +  '/certificadoOrigen.do?metodo=cargarProductoresParaConsultaCertificadoOrigen&entidad='+entidad+"&productor="+productor+"&pmf="+pmf+"&periodoForestal="+periodoForestal;	
}

function imprimirCertificado(){	
	
	var idCertificado = $('#idCertificado').val();	
	var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
	if(type == "IE"){
		window.open("./reporte.do?metodo=generarReporteCertificadoOrigen&idCertificado="+idCertificado,"",especificaciones);
	}else{
		window.open("../../reporte.do?metodo=generarReporteCertificadoOrigen&idCertificado="+idCertificado,"",especificaciones);
	}			
}

function mostrarCuit() {
	
	var cuit = $('#cuit').val();

	$('#prefijoCuit').val(cuit.substring(0,2));
	$('#nroCuit').val(cuit.substring(2,cuit.length-1));
	$('#sufijoCuit').val(cuit.substring(cuit.length-1));
}
			
//-----------------------------------------------------//

</script>

	<input id="idParamProductor" type="hidden" value="${certificado.productor.id}">
	<input id="idParamIdTipoDeEntidad" type="hidden" value="${certificado.productor.tipoEntidad}">
	<input id="idPMF" type="hidden" value="${certificado.pmf.id}">
	<input id="periodoForestal" type="hidden" value="${certificado.periodoForestal}">
	
	<input id="idCertificado" type="hidden" value="<c:out value="${certificado.id}"></c:out>">
	
	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td class="azulAjustado">
				<bean:message key='SIIF.titulo.CertificadoOrigen'/>
			</td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
		<tr>
			<td>		
				<table border="0" class="cuadrado" align="center" width="35%" cellpadding="10" cellspacing="2">						
					<tr>
						<td width="60%" class="botoneralNegritaGrande grisSubtitulo">
							<bean:message key='SIIF.label.NroDeCertificado'/>
						</td>					
						<td width="40%" class="botoneralNegritaGrande">
							<c:out value="${certificado.nroCertificado}"></c:out>
						</td>
														
					</tr>					
				</table>		
			</td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
		
		<!-- DATOS DEL EXPORTADOR -->
		<tr>
			<td>		
				<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="4" class="grisSubtitulo"><bean:message key='SIIF.label.DatosExportador'/></td>
					</tr>
					<tr>
						<td colspan="4" height="15"></td>
					</tr>						
					<tr>
						<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoDeProductor'/></td>
						<td width="30%">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.exportador.tipoEntidadDesc}"></c:out>'>
						</td>
						
						<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Exportador'/></td>
						<td width="32%" align="center">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.exportador.nombre}"></c:out>'>
						</td>																
					</tr>	
					
					<tr>
						<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroMatricula'/></td>
						<td width="30%">
							<input class="botonerab" type="text" size="27" readonly="readonly"
									value='<c:out value="${certificado.exportador.nroMatricula}"></c:out>'>							
						</td>
						
						<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Cuit'/></td>
						<td width="32%" align="center">
							<input type="hidden" id="cuit" value='<c:out value="${certificado.exportador.cuit}"></c:out>'>
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
			
		<!-- DATOS DEL PRODUCTOR -->				
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
						<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoDeProductor'/></td>
						<td width="30%">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.productor.tipoEntidadDesc}"></c:out>'>
						</td>
						
						<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
						<td width="32%" align="center">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.productor.nombre}"></c:out>'>
						</td>																
					</tr>	
					
					<tr>
						<td width="17%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.PeríodoForestal'/>							
						</td>						
						<td width="30%" align="center">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.periodoForestal}"></c:out>'>										
						</td>	
						
						<td width="21%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.PlanManejoForestal'/>						
						</td>
						<td width="32%" align="center">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.pmf.nombre}- ${certificado.pmf.expediente}"></c:out>'>	
						</td>																
					</tr>
					
					<tr>
						<td width="17%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.ReservaForestal'/>
						</td>						
						<td width="30%" align="center">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.reservaForestal}"></c:out>'>						
						</td>	
						
						<td colspan="2"></td>
																
					</tr>					
					
					<c:if test="${certificado.exportador.id != certificado.productor.id}">
					
						<tr id="idTrFacturaVolTrans" style="display: ">
							<td width="17%" class="botoneralNegritaRight">
								<bean:message key='SIIF.label.NroFactura'/>
							</td>						
							<td width="30%" align="center">
								<input class="botonerab" type="text" size="27" readonly="readonly" 
										value='<c:out value="${certificado.nroFactura}"></c:out>'>					
							</td>	
							<td width="21%" class="botoneralNegritaRight">
								<bean:message key='SIIF.label.VolumenTransferido'/>						
							</td>
							<td width="32%" align="center">
								<input class="botonerab" type="text" size="27" readonly="readonly" 
										value='<c:out value="${certificado.volumenTransferido}"></c:out>'>	
							</td>																
						</tr>					
					</c:if>
					
					<tr>
						<td colspan="4" height="15"></td>
					</tr>				
				</table>		
			</td>
		</tr>				
		<tr>
			<td height="20"></td>
		</tr>
		
		<!-- VERIFICACION DE CARGAS -->		
		<tr>
			<td>			
				<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="4" class="grisSubtitulo">
							<bean:message key='SIIF.subTitulo.VerificacionDeCargas'/>
						</td>
					</tr>
					<tr>
						<td colspan="4" height="15"></td>
					</tr>						
					<tr>
						<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.OrigenMateriaPrima'/></td>
						<td width="30%">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.origenMateriaPrima}"></c:out>'>
						</td>
						
						<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.NroRemito'/></td>
						<td width="32%" align="center">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.nroRemito}"></c:out>'>
						</td>																
					</tr>	
					
					<tr>
						<td width="17%" class="botoneralNegritaRight"><bean:message key='SIIF.label.ProvinciaDestino'/></td>
						<td width="30%">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.localidadDestino.provinciaDestinoDTO.nombre}"></c:out>'>
						</td>
						
						<td width="21%" class="botoneralNegritaRight"><bean:message key='SIIF.label.LocalidadDestino'/></td>
						<td width="32%" align="center">
							<input class="botonerab" type="text" size="27" readonly="readonly" 
									value='<c:out value="${certificado.localidadDestino.nombre}"></c:out>'>																
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
					
		<!-- TIPOS DE PRODUCTOS -->		
		<tr>
			<td>
				<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
					<tr>
						<td class="grisSubtitulo">
							<bean:message key='SIIF.subTitulo.TiposDeProductos'/>
						</td>
					</tr>
					<tr>
						<td height="15"></td>
					</tr>						
	
					<tr>
						<td>
							<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2">
								<tr>
									<td class="verdeTituloTablaChico" width="60%">
										<bean:message key='SIIF.label.TipoDeProducto'/>
									</td>
									<td class="verdeTituloTablaChico" width="40%">
										<bean:message key='SIIF.label.VolumenM3'/>
									</td>					
								</tr>			
												
								<%String clase="par"; %>
								<c:forEach items="${certificado.tiposProductoEnCertificado}" var="tipoProdEnCert" varStatus="i">
									<%clase=(clase.equals("")?"par":""); %>
									<tr class="<%=clase%>">
										<td class="botoneralNegritaMediana">
											<c:out value="${tipoProdEnCert.tipoProductoExportacion.nombre}"></c:out>
										</td>
										<td align="center">										
											<input class="botonerab" type="text" size="12" readonly="readonly"
													value="<c:out value="${tipoProdEnCert.volumenTipoProducto}"></c:out>"> 
										</td>											
									</tr>
								</c:forEach>											
							</table>		
																
							<br>
							<table border="0" class="cuadrado" align="center" width="75%" cellpadding="2" cellspacing="0">
								<tr>
									<td class="grisClaroSubtituloCenter" width="60%">
										Volumen Total Exportado(m³)
									</td>
									<td class="grisClaroSubtituloCenter" width="40%">
										<input class="botonerab" type="text" size="12" readonly="readonly" 
												value='<c:out value="${certificado.volumenTotalTipoProductos}"></c:out>'>	
									</td>					
								</tr>
							</table>					
						</td>
					</tr>	
															
					<tr>
						<td height="15"></td>
					</tr>				
				</table>				
			</td>					
		</tr>
				
		<tr>
			<td height="20"></td>
		</tr>										
	</table>									
 
	<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
		<tr>
			<td height="10" colspan="4"></td>
		</tr>
		<tr>
			<td width="15%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Fecha'/></td>
			<td align="left" width="35">						
				<input type="text" value="<c:out value='${certificado.fecha}'/>" readonly="readonly" class="botonerab">
				<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" align="top" width='17' height='21'>				
			</td>
			<td width="20%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Usuario'/></td>
			<td align="left" width="35">
								
				<input type="text" readonly="readonly" class="botonerab" 
						value="<c:out value='${certificado.usuarioAlta.nombreUsuario}'></c:out>">							
			</td>			
		</tr>
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
			<td height="20" width="49%" align="right"> 
				<input class="botonerab" type="button" value="Volver" 
						onclick="javascript:volverConsultaCertificado();">
			</td>
			<td height="20" colspan="2">
			</td>
			<td height="20" width="49%" align="left">
				<input type="button" class="botonerab" value="Imprimir" onclick="javascript:imprimirCertificado();">
			</td>			
			
		</tr>
		<tr>
			<td height="10" colspan="4"></td> 
		</tr>
	</table>

<script type="text/javascript">
	mostrarCuit();	
</script>	

