<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>
<html:form action="rol" styleId="rolForm">
	<html:hidden property="metodo" value="modificacionRol" />
	<html:hidden property="rolDTO.id" value="${rolF.id}" />
	<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
		<tr>
			<td colspan="3" height="15"></td>
		</tr>
		<tr>
			<td width="35%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.NombreRol'/>
			</td>
			<td>
				<input name="rolDTO.rol" class="botonerab" type="text"	size="30" 
						value="<c:out value='${rolF.rol}'></c:out>">
			</td>
			<td width="10%"></td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
	</table>
	<table id="idMenues" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
		<tr>
			<td colspan="5" class="azulAjustado">
				<bean:message key='SIIF.subTitulo.SeleccionMenues'/>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table border="0" class="cuadradoSinBorde" align="center" width="100%" cellpadding="2">
				<tr>
					<td width="60%"></td>
					<td width="20%">
						<a href="javascript:seleccionarTodos();">
							<bean:message key='SIIF.label.SeleccionarTodos'/>
						</a>
					</td>
					<td width="20%">
						<a href="javascript:quitarSeleccion();">
							<bean:message key='SIIF.label.QuitarSeleccion'/>
						</a>
					</td>
				</tr>
			</table>
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
							<td width="5%"></td>
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
												<%String checked=""; %> 
												<c:forEach items="${rolF.menues}" var="menuRol">
													<c:if test="${menuRol.id == menuHijoHH.id}">
														<%checked="checked='checked'"; %>
													</c:if>
												</c:forEach> 
												<input id="menu<%=i%>" type="checkbox" <%=checked%>
														name='<%="menuesDTO["+i+"].id"%>' value="${menuHijoHH.id}"
														onchange="checkMenu('<%=i%>','${menuHijoHH.id}');">
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
											<%String checked=""; %> 
											<c:forEach items="${rolF.menues}" var="menuRol">
												<c:if test="${menuRol.id == menuHijoH.id}">
													<%checked="checked='checked'"; %>
												</c:if>
											</c:forEach> 
											<input id="menu<%=i%>" type="checkbox" <%=checked%>
													name='<%="menuesDTO["+i+"].id"%>' value="${menuHijoH.id}"
													onchange="checkMenu('<%=i%>','${menuHijoH.id}');">
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
								<%String checked=""; %> 
								<c:forEach items="${rolF.menues}" var="menuRol">
									<c:if test="${menuRol.id == menuHijo.id}">
										<%checked="checked='checked'"; %>
									</c:if>
								</c:forEach> 
								<c:choose>
									<c:when test="${menuHijo.item == 'Salir de la Aplicacion'}">
										<input type="checkbox" checked="checked" disabled="disabled"
												name='<%="menuesDTO["+i+"].id"%>' value="${menuHijo.id}">
										<input type="hidden" name='<%="menuesDTO["+i+"].id"%>' 
												value="${menuHijo.id}">
									</c:when>
									<c:otherwise>
										<input id="menu<%=i%>" type="checkbox" <%=checked%>
												name='<%="menuesDTO["+i+"].id"%>' value="${menuHijo.id}"
												onchange="checkMenu('<%=i%>','${menuHijo.id}');">
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
	<script language="javascript">
		cantMenues = <%=i%>
	</script>
	<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
		<tr>
			<td>
				<input type="button" class="botonerab" value="Modificar" id="enviar" 
						onclick="javascript:submitir();">
			</td>
		</tr>
		<tr>
			<td height="10"></td>
		</tr>
	</table>
</html:form>
