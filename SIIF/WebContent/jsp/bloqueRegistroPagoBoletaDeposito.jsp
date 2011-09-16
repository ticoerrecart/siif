<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>

	<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2"> 
		<tr>
			<td colspan="3" class="azulAjustado">Boletas de Deposito</td>
		</tr>				

		<tr>
			<td height="10" colspan="3"></td>
		</tr>
		<c:choose>					
			<c:when test="${fn:length(guiaForestal.boletasDeposito)>0}">					
				<c:forEach items="${guiaForestal.boletasDeposito}" var="boletaDeposito" varStatus="index">
					<tr id="idTr<c:out value='${boletaDeposito.id}'></c:out>">
						<td colspan="2" width="85%">			
							<table border="0" class="cuadrado" align="right" width="80%" cellpadding="2">
								<tr>
									<td colspan="5" class="grisSubtitulo">
										Cuota n°<c:out value="${index.index+1}"></c:out>
									</td>
								</tr>
								<tr>
									<td height="5" colspan="5"></td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										Número de Cuota
									</td>
									<td width="35%" align="left">
										<input value="${boletaDeposito.numero}" class="botonerab" type="text"
											   size="20" readonly="readonly">
									</td>
									<td width="15%" class="botoneralNegritaRight">Productor</td>
									<td width="40%" align="left" colspan="2">
										<input value="${boletaDeposito.guiaForestal.fiscalizacion.productorForestal.nombre}"
											   class="botonerab" type="text" size="40" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">Concepto</td>
									<td colspan="4" align="left">
										<input value="${boletaDeposito.concepto}" class="botonerab" type="text" size="94"
										 	   readonly="readonly">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">Area</td>
									<td colspan="4" align="left">
										<input value="${boletaDeposito.area}" class="botonerab" type="text" size="94"
											   readonly="readonly">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">Efectico/Cheque</td>
									<td width="35%" align="left">
										<input value="${boletaDeposito.efectivoCheque}" class="botonerab"
											   type="text" size="20" readonly="readonly">
									</td>
									<td width="15%" class="botoneralNegritaRight">Monto$</td>
									<td width="40%" align="left" colspan="2">
										<input value="${boletaDeposito.monto}" class="botonerab" type="text"
											   size="20" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td width="10%" class="botoneralNegritaRight">
										Fecha Vencimiento
									</td>
									<td width="35%" align="left">
										<input type="text" readonly="readonly" class="botonerab" size="17"
											   value="<fmt:formatDate value='${boletaDeposito.fechaVencimiento}' pattern='dd/MM/yyyy' />">
										<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
											 align="top" width='17' height='21'>		
									</td>
									<td width="15%" class="botoneralNegritaRight">
										Fecha Pago
									</td>
									<td width="23%" align="left">
										<input type="text" readonly="readonly" class="botonerab" size="17"
											   value="<fmt:formatDate value='${boletaDeposito.fechaPago}' pattern='dd/MM/yyyy' />">
										<img alt="" src="<html:rewrite page='/imagenes/calendar/calendar2.gif'/>" 
											 align="top" width='17' height='21'>																						
									</td>										
									<c:choose>
										<c:when test="${boletaDeposito.fechaPago ==null}">
											<td width="17%" class="rojoAdvertenciaLeft">
												NO PAGADA
											</td>		
										</c:when>
										<c:otherwise>
											<td width="17%" class="verdeExitoLeft">
												PAGADA
											</td>
										</c:otherwise>
									</c:choose>																						
								</tr>
								<tr>
									<td height="5" colspan="5"></td>
								</tr>
							</table>
							<br>
						</td>
						<td>
							<c:if test="${boletaDeposito.fechaPago ==null}">
								<input type="button" value="Registrar Pago" class="botonerab" 
										onclick="registrarPago(<c:out value='${boletaDeposito.id}'></c:out>);">
							</c:if>	
						</td>
					</tr>
				</c:forEach>	
			</c:when>
			<c:otherwise>
				No existen Boletas de Deposito para esta Guía Forestal
			</c:otherwise>													
		</c:choose>									
	</table>
