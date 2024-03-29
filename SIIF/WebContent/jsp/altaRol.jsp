<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript">

function submitir(){
	validarForm("rolForm","../rol","validarRolForm","RolForm");
}

</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="rol" styleId="rolForm">
	<html:hidden property="metodo" value="altaRol" />
	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
		<tr>
			<td colspan="2" class="azulAjustado">
				<bean:message key='SIIF.titulo.AltaRol'/>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td width="40%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.NombreRol'/>
			</td>
			<td align="left">
				<input name="rolDTO.rol" class="botonerab"	type="text" size="30">
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2">
				<table id="idMenues" border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
					<tr>
						<td colspan="5" class="azulAjustado">
							<bean:message key='SIIF.subTitulo.SeleccionMenues'/>
						</td>
					</tr>
					<%int i=0; %>
					<c:forEach items="${menues}" var="menu">
						<tr>
							<td colspan="5" class="grisSubtitulo">
								<c:out value="${menu.item}"></c:out>
							</td>
						</tr>
						<tr height="10">
							<td colspan="5"></td>
						</tr>
						<c:forEach items="${menu.hijos}" var="menuHijo">
							<c:choose>
								<c:when test="${fn:length(menuHijo.hijos)>0}">
									<tr>
										<td width="10%"></td>
										<td colspan="3" class="grisSubtitulo">
											<c:out value="${menuHijo.item}"></c:out>
										</td>
										<td width="10%"></td>
									</tr>
									<c:forEach items="${menuHijo.hijos}" var="menuHijoH">
										
										<c:choose>
											<c:when test="${fn:length(menuHijoH.hijos)>0}">
												<tr height="5">
													<td colspan="5"></td>
												</tr>								
												<tr>
													<td colspan="2"></td>
													<td colspan="2" class="grisSubtitulo">
														<c:out value="${menuHijoH.item}"></c:out>
													</td>
													<td width="5%"></td>
												</tr>	
			
												<c:forEach items="${menuHijoH.hijos}" var="menuHijoHH">
													<tr>
														<td colspan="2"></td>
														<td width="10%">
															<input type="checkbox" name='<%="menuesDTO["+i+"].id"%>' 
																	value="${menuHijoHH.id}">
														</td>			
														<td class="botoneralNegritaLeft">			
															<c:out value="${menuHijoHH.item}"></c:out>														
														</td>
														<td width="5%"></td>
													</tr>
													<%i++; %>									
												</c:forEach>			
																	
											</c:when>
											<c:otherwise>
											
												<tr>
													<td width="10%"></td>
													<td width="10%">
														<input type="checkbox" name='<%="menuesDTO["+i+"].id"%>' 
														value="${menuHijoH.id}">
													</td>
													<td colspan="3" class="botoneralNegritaLeft">
														<c:out value="${menuHijoH.item}"></c:out>
													</td>
												</tr>
												<%i++; %>								
											
											</c:otherwise>
										</c:choose>	
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td width="10%">
											<c:choose>
												<c:when test="${menuHijo.item == 'Salir de la Aplicacion'}">
													<input type="checkbox" checked="checked" disabled="disabled"
															name='<%="menuesDTO["+i+"].id"%>' value="${menuHijo.id}">
													<input type="hidden" name='<%="menuesDTO["+i+"].id"%>'
															value="${menuHijo.id}">
												</c:when>
												<c:otherwise>
													<input type="checkbox" name='<%="menuesDTO["+i+"].id"%>'
															value="${menuHijo.id}">
												</c:otherwise>
											</c:choose>
										</td>
										<td colspan="4" class="botoneralNegritaLeft">
											<c:out value="${menuHijo.item}"></c:out>
										</td>
									</tr>
									<%i++; %>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<tr height="10">
							<td colspan="5"></td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2">
				<input type="button" class="botonerab" value="Aceptar" id="enviar" 
						onclick="javascript:submitir();"> 
				<input type="button" class="botonerab" value="Cancelar"
						onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>
</html:form>

<script type="text/javascript">
	//$("#idMenues tr:nth-child(even)").addClass("par"); //Para pintar en cebra los tr de la tabla
</script>
