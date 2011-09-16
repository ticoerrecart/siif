<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/RolFachada.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

<script type="text/javascript">
	function submitir(){
		validarForm("usuarioFormId","../usuario","validarUsuarioForm","UsuarioForm");
	}

	function cargarRolesSegunEntidad(){
		dwr.util.removeAllOptions("idRol");
		var data = [ { nombre:"Cargando...", id:-1 }];
		dwr.util.addOptions("idRol", data, "id", "rol");
				
		var idEntidad = $("select[name='idEntidad'] ").val()
		RolFachada.cargarRolesSegunEntidad(idEntidad,'${usuario.id}', cargarRolesSegunEntidadCallback);
	}

	function cargarRolesSegunEntidadCallback(roles){
		dwr.util.removeAllOptions("idRol");
		dwr.util.addOptions("idRol", roles,"id","rol");
		if(''!='${usu.rol.id}'){
			dwr.util.setValue("idRol",'${usu.rol.id}');
		}
	}

	$(document).ready(function() {
		cargarRolesSegunEntidad()
		});
</script>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">
	${error}
</div>
<html:form action="usuario" styleId="usuarioFormId">
	<html:hidden property="metodo" value="${metodo}"/>
	<html:hidden property="usuario.id" value="${usu.id}"/>

	<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
		<tr>
			<td colspan="2"  class="azulAjustado" >${titulo}</td>
		</tr>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>				
		<tr>
			<td class="botoneralNegritaRight">Nombre</td>
			<td align="left">
				<c:choose>
					<c:when test="${metodo=='altaUsuario'}">
						<html:text property="usuario.nombreUsuario" value="${usu.nombreUsuario}" />
					</c:when>
					<c:otherwise><!-- AL MODIFICAR NO PUEDO CAMBIAR EL NOMBRE DE USUARIO -->
						<html:text property="usuario.nombreUsuario" value="${usu.nombreUsuario}" readonly="true"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>	
		<tr>
			<td class="botoneralNegritaRight">Contraseņa</td>
			<td  align="left">
				<html:password property="usuario.password" value="${usu.password}"/>			
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Confirmar Contraseņa</td>
			<td  align="left">
				<html:password property="contrasenia" value="${usu.password}"/>			
			</td>
		</tr>
		<tr>
				<c:choose>
					<c:when test="${metodo=='altaUsuario'}"><!-- ALTA -->
						<td class="botoneralNegritaRight">Habilitado</td>
						<td align="left">
							<input type="checkbox" name="usuario.habilitado" class="botonerab" checked="checked" value="true" disabled="disabled">
						</td>
					</c:when>

					<c:when test="${usuario.rol.id!=idRolAdministrador}"><!-- MODIFICACION USUARIO COMUN -->
						<html:hidden property="usuario.habilitado" value="true"/>
					</c:when>
					<c:otherwise><!-- MODIFICACION ADMINISTRADOR -->
					
						<td class="botoneralNegritaRight">Habilitado</td>
						<td align="left">
							<c:choose>
								<c:when test="${usu.habilitado}">
										<input type="checkbox" name="usuario.habilitado" class="botonerab" checked="checked" value="true">
									</td>
								</c:when>
								<c:otherwise>
										<input type="checkbox" name="usuario.habilitado" class="botonerab" value="true">
								</c:otherwise>
							</c:choose>
						</td>
					</c:otherwise>
					
				</c:choose>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Entidad</td>
			<td align="left">
				<c:choose>
					<c:when test="${metodo=='altaUsuario'}">
						<html:select  styleClass="botonerab" property="idEntidad" value="${usu.entidad.id}" onchange="cargarRolesSegunEntidad()">
							<c:forEach items="${entidades}" var="entidad">
								<html:option value="${entidad.id}">
									<c:out value="${entidad.nombre}"/>
								</html:option>
							</c:forEach>
		
						</html:select>							
					</c:when>
					<c:otherwise><!-- AL MODIFICAR NO PUEDO CAMBIAR LA ENTIDAD -->
						<html:select  styleClass="botonerab" property="idEntidad" value="${usu.entidad.id}">
							<html:option value="${usu.entidad.id}">${usu.entidad.nombre}</html:option>
						</html:select>
						
					</c:otherwise>
				</c:choose>
			</td>			
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Rol</td>
			<td align="left">
				<html:select  styleClass="botonerab" property="idRol" styleId="idRol">
					<html:option value="-1">Cargando...</html:option>
				</html:select>
			
			</td>
		</tr>

		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="2">				
				<c:choose>
					<c:when test="${metodo=='altaUsuario'}">
						<input type="button" class="botonerab" value="Aceptar" id="enviar" onclick="javascript:submitir();">
						<input type="button" class="botonerab" value="Cancelar" onclick="javascript:parent.location= contextRoot() +  '/jsp.do?page=.index'">
					</c:when>
					<c:otherwise>
						<input type="button" class="botonerab" value="Modificar" id="enviar" onclick="javascript:submitir();">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="2"></td>
		</tr>									
	</table>

</html:form>
