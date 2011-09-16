<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript">

function volver(){	

	var metodo = $('#paramForward').val();
	var productor = $('#paramProductor').val();
	var localidad = $('#paramLocalidad').val();
	parent.location = contextRoot() +  '/consultasFiscalizacion.do?metodo=cargarLocalidadesConsultaFiscalizacion&forward=' + metodo + '&idProd=' + productor + '&idLoc=' + localidad;		
}

function imprimir(){	
	
	var idFiscalizacion = $('#idFiscalizacion').val();	
	var especificaciones="top=0, left=0, toolbar=no,location=no, status=no,menubar=no,scrollbars=no, resizable=no";
	window.open("../../reporte.do?metodo=generarReporteFiscalizacion&idFiscalizacion="+idFiscalizacion,especificaciones);		
}

</script>

<input id="idFiscalizacion" type="hidden" value="${fiscalizacion.id}">
<input id="paramLocalidad" type="hidden" value="${fiscalizacion.productorForestal.localidad.id}">
<input id="paramProductor" type="hidden" value="${fiscalizacion.productorForestal.id}">
<input id="paramForward" type="hidden" value="${paramForward}">

<div id="errores" class="rojoAdvertencia">${error}</div>

	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				Fiscalización de Productos Forestales
			</td>
		</tr>
		<tr>
			<td height="20" colspan="4"></td>
		</tr>
		<tr>
			<td width="22%" class="botoneralNegritaRight">Localidad</td>
			<td align="left" width="22%">
				<input class="botonerab" type="text" size="20" readonly="readonly"
					   value="<c:out value='${fiscalizacion.productorForestal.localidad.nombre}'></c:out>">			
			</td>
			<td width="20%" class="botoneralNegritaRight">Productor Forestal</td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly" 
					   value="<c:out value='${fiscalizacion.productorForestal.nombre}'></c:out>">
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Fecha</td>
			<td align="left">
				<input class="botonerab" type="text" size="16" readonly="readonly"
					   value="<fmt:formatDate value='${fiscalizacion.fecha}' pattern='dd/MM/yyyy' />">
			<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
				align="top" width='17' height='21'>		
			</td>
			<td class="botoneralNegritaRight">Período Forestal</td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly" 
					   value="<c:out value='${fiscalizacion.periodoForestal}'></c:out>">			
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Cantidades(Unidades)</td>
			<td align="left">
				<input class="botonerab" type="text" size="20" readonly="readonly" 
					   value="<c:out value='${fiscalizacion.cantidadUnidades}'></c:out>">
			</td>
			<td class="botoneralNegritaRight">Tipo de Producto</td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly" 
					   value="<c:out value='${fiscalizacion.tipoProducto.nombre}'></c:out>">			
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Cantidades(m³)</td>
			<td align="left">
				<input class="botonerab" type="text" size="20" readonly="readonly"
					   value="<c:out value='${fiscalizacion.cantidadMts}'></c:out>">
			</td>
			<td class="botoneralNegritaRight">Tamaño de la Muestra</td>
			<td align="left">
				<input class="botonerab" type="text" size="29" readonly="readonly"
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
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.rodal.marcacion.tranzon.pmf.nombre}- ${fiscalizacion.rodal.marcacion.tranzon.pmf.expediente}'></c:out>">																					
						</td>
						<td width="25%"></td>
					</tr>		
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							Tranzon
						</td>
						<td>
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.rodal.marcacion.tranzon.numero}- ${fiscalizacion.rodal.marcacion.tranzon.disposicion}'></c:out>">																						
						</td>
						<td width="25%"></td>
					</tr>	
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							Marcacion
						</td>
						<td>
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.rodal.marcacion.disposicion}'></c:out>">																					
						</td>
						<td width="25%"></td>
					</tr>
					<tr>
						<td width="40%" class="botoneralNegritaRight">
							Rodal
						</td>
						<td>
							<input class="botonerab" type="text" size="25" readonly="readonly"
								   value="<c:out value='${fiscalizacion.rodal.nombre}'></c:out>">	
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
										<c:if test="${fn:length(fiscalizacion.muestra) > 0}">
											<table id="tablaMuestras" border="0" class="cuadradoSinBorde" align="center"
												width="70%" cellpadding="2" cellspacing="0">																						

												<c:forEach items="${fiscalizacion.muestra}" varStatus="i" var="muestra">
											
													<tr>
														<td height="5" colspan="4"></td>
													</tr>
													<tr>
														<td class="botoneralNegritaRight">${i.index+1}</td>
														<td>
															<input class="botonerab" type="text" readonly="readonly" 
																   value="${muestra.largo}">
														</td>
														<td>
															<input class="botonerab" type="text" readonly="readonly"
																value="${muestra.diametro1}">
														</td>
														<td>
															<input class="botonerab" type="text" readonly="readonly"
																value="${muestra.diametro2}">
														</td>
													</tr>
												
													<tr>
														<td height="5" colspan="4"></td>
													</tr>												
												</c:forEach>
											</table>																														
										</c:if>																																																	
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
			
	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
		<tr>
			<td height="20" width="49%" align="right">
				<input type="button" class="botonerab" value="Volver" onclick="javascript:volver();">			
			</td>
			<td height="20">
			</td>
			<td height="20" width="49%" align="left">
				<input type="button" class="botonerab" value="Imprimir" onclick="javascript:imprimir();">
			</td>			
		</tr>		
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
	</table>	
