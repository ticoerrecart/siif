<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
 
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>
<html:form action="aforo" styleId="aforoForm">
	<html:hidden property="metodo" value="modificacionAforo" />
	<html:hidden property="aforoDTO.id" value="${aforoParam.id}" />
	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="2" height="15"></td>
		</tr>
		<tr>
			<td width="35%" class="botoneralNegritaRight">
				<bean:message key='SIIF.label.ValorAforo$'/>
			</td>
			<td>
				<input name="aforoDTO.valorAforo" class="botonerab" type="text" size="30" 
						value="<c:out value='${aforoParam.valorAforo}'></c:out>" 
						onkeypress="return evitarAutoSubmit(event)">
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.TipoProducto'/>
			</td>
			<td>
				<html:select styleClass="botonerab" property="aforoDTO.tipoProducto.id"
							 value="${aforoParam.tipoProducto.id}">
								 
					<c:forEach items="${tiposProducto}" var="tipoProducto">
						<html:option value="${tipoProducto.id}">
							<c:out value="${tipoProducto.nombre}"></c:out>
						</html:option>
					</c:forEach>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">
				<bean:message key='SIIF.label.Estado'/>
			</td>
			<td>
				<html:select styleClass="botonerab" property="aforoDTO.estado" value="${aforoParam.estado}">							 
					<c:forEach items="${estadosProducto}" var="estadoProducto" varStatus="i">
						<c:choose>
							<c:when test="${estadoProducto.name == aforoParam.estado}">
								<option value="<c:out value='${estadoProducto.name}'></c:out>" selected="selected">
									<c:out value="${estadoProducto.descripcion}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value="<c:out value='${estadoProducto.name}'></c:out>">
									<c:out value="${estadoProducto.descripcion}"></c:out>
								</option>
							</c:otherwise>
						</c:choose>					
					</c:forEach>							 
				</html:select>			 			

				<!-- <select class="botonerab" name="aforoDTO.estado">
					<c:choose>
						<c:when test="${aforoParam.estado == 'Seco'}">
							<option selected="selected" value="Seco">Seco</option>
							<option value="Verde">Verde</option>
						</c:when>
						<c:otherwise>
							<option value="Seco">Seco</option>
							<option selected="selected" value="Verde">Verde</option>
						</c:otherwise>
					</c:choose>
				</select> -->
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.TipoProductor'/></td>
			<td>
				<html:select styleClass="botonerab" property="aforoDTO.tipoProductor" value="${aforoParam.tipoProductor}">
					<c:forEach items="${tiposDeEntidad}" var="tipoDeEntidad" varStatus="i">
						<c:choose>
							<c:when test="${tipoDeEntidad.name == aforoParam.tipoProductor}">
								<option value="<c:out value='${tipoDeEntidad.name}'></c:out>" selected="selected">
									<c:out value="${tipoDeEntidad.descripcion}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value="<c:out value='${tipoDeEntidad.name}'></c:out>">
									<c:out value="${tipoDeEntidad.descripcion}"></c:out>
								</option>
							</c:otherwise>
						</c:choose>					
					</c:forEach>
				</html:select>
			
				<!-- <select class="botonerab" name="aforoDTO.tipoProductor">
					<c:choose>
						<c:when test="${aforoParam.tipoProductor == 'PPF'}">
							<option value="OBR">
								Obrajero
							</option>
							<option selected="selected" value="PPF">
								Pequeño Productor Forestal
							</option>
						</c:when>
						<c:otherwise>
							<option selected="selected" value="OBR">
								Obrajero
							</option>
							<option value="PPF">
								Pequeño Productor Forestal
							</option>
						</c:otherwise>
					</c:choose>
				</select> -->
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" class="botonerab" value="Modificar" 
					   id="enviar" onclick="javascript:submitir();">
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>
	</table>
</html:form>
