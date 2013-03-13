<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/Validator.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/UbicacionAction.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/altaUbicacion.js?ver=3'/>"></script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="ubicacion" styleId="ubicacionForm">
	<html:hidden property="metodo" value="altaUbicacion" />
	
	<table border="0" class="cuadrado" align="center" width="50%" cellpadding="2">
		<tr>
			<td colspan="3" class="azulAjustado">
				<bean:message key='SIIF.titulo.AltaZMF'/>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
			<td width="2%"></td> 
			<td align="left">	
				<html:select styleClass="botonerab" property="idProductorForestal" styleId="idProductorForestal" onchange="actualizarComboZona()">
					<html:option value="0">--Seleccionar--</html:option>
					<c:forEach items="${productores}" var="entidad">
						<html:option value="${entidad.id}">
							<c:out value="${entidad.nombre}"></c:out>
						</html:option>
					</c:forEach>
				</html:select>
			</td>
		</tr>
	
		<tr>	
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.ZonaManejoForestal'/></td>
			<td width="2%"></td>
			<td align="left">
				
				<select  class="botonerab" id="idZMF" onchange="actualizarComboZona()" >
					<option value="0">--Seleccione una Opcion de Zona--</option>
					<option value="1">--PMF--</option>
			 		<option value="2">--Area de Cosecha--</option>
				</select>
			</td>
		</tr>		
	
	
		<tr class="plan" style="display: none">	
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.PlanManejoForestal'/></td>
			<td width="2%"></td>
			<td align="left"> 
				<html:select styleClass="botonerab" property="idPMF" styleId="idPMF" onchange="actualizarComboTranzon()" />
			</td>
		</tr>
		<tr class="plan" style="display: none">
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Tranzon'/></td>
			<td width="2%"></td>
			<td align="left"> 
				<html:select styleClass="botonerab" property="idTranzon" styleId="idTranzon"  onchange="actualizarComboMarcacion()" />
			</td>
		</tr>
		<tr class="plan" style="display: none">
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Marcacion'/></td> 
			<td width="2%"></td>
			<td align="left">	
				<html:select styleClass="botonerab" property="idMarcacion" styleId="idMarcacion" onchange="actualizarComboRodal()" />
			</td>
		</tr>
		<tr class="plan" style="display: none">
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.Rodal'/></td>
			<td width="2%"></td>
			<td align="left"> 
				<html:select styleClass="botonerab" property="idRodal" styleId="idRodal" onchange="cambioComboRodal()" />
			</td>
		</tr>
		
	
		<tr class="area" style="display: none">	
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.AreaDeCosecha'/></td>
			<td width="2%"></td>
			<td align="left"> 
				<html:select styleClass="botonerab" property="idArea" styleId="idArea" onchange="cambioComboArea()" />
			</td>
		</tr>

		
		<tr>
			<td height="10" colspan="3"></td>
		</tr>
	</table>	
	
	
	<br>
	<br>
	<table id="altaDeRodal" style="display: none" border="0" class="cuadrado" align="center" width="50%"  cellpadding="2">
		<tr>
			<td colspan="3" class="azulAjustado"><bean:message key='SIIF.subTitulo.AltaRodal'/></td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Nombre'/></td>
			<td width="2%"></td>
			<td align="left">
				<input id="nombreRodal" type="text" class="botonerab" onkeypress="return evitarAutoSubmit(event)">
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
		<tr>
			<td align="right"> <input type="button" class="botonerab" value="Grabar" onclick="validarRodal()"></td>
			<td width="2%"></td>
			<td align="left"> <input type="button" class="botonerab" value="Cancelar" onclick="ocultar('altaDeRodal')"></td>
		</tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
	</table>
	
	<table id="altaDeMarcacion" style="display: none" border="0" class="cuadrado" align="center" width="50%"  cellpadding="2">
		<tr>
			<td colspan="3" class="azulAjustado"><bean:message key='SIIF.subTitulo.AltaMarcacion'/></td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Disposicion'/></td>
			<td width="2%"></td>
			<td align="left"> 
				<input id="disposicionMarcacion" type="text" class="botonerab" 
						onkeypress="return evitarAutoSubmit(event)">
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>				
		<tr>
			<td align="right"> <input type="button" class="botonerab" value="Grabar" onclick="validarMarcacion()"></td>
			<td width="2%"></td>
			<td align="left"> <input type="button" class="botonerab" value="Cancelar" onclick="ocultar('altaDeMarcacion')"></td>
		</tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
	</table>
	
	
	<table id="altaDeTranzon" style="display: none" border="0" class="cuadrado" align="center" width="50%"  cellpadding="2">
		<tr>
			<td colspan="3" class="azulAjustado"><bean:message key='SIIF.subTitulo.AltaTranzon'/></td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Numero'/></td>
			<td width="2%"></td>
			<td align="left">
				<input id="numeroTranzon" type="text" class="botonerab" onkeypress="javascript:validarNumeroRomano(event)"
						onblur="javascript:pasarAMayuscula('numeroTranzon')">
			</td>
		</tr>

		<tr>
			<td width="47%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Disposicion'/></td>
			<td width="2%"></td>
			<td align="left"> <input id="disposicionTranzon" type="text" class="botonerab" ></td>
		</tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
		<tr>
			<td align="right"> <input type="button" class="botonerab" value="Grabar" onclick="validarTranzon()"></td>
			<td width="2%"></td>
			<td align="left"> <input type="button" class="botonerab" value="Cancelar" onclick="ocultar('altaDeTranzon')"></td>
		</tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
	</table>

	<table id="altaDePMF" style="display: none" border="0" class="cuadrado" align="center" width="50%"  cellpadding="2">
		<tr>
			<td colspan="3" class="azulAjustado"><bean:message key='SIIF.subTitulo.AltaPMF'/></td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Expediente'/></td>
			<td width="2%"></td>
			<td align="left"> <input id="expedientePMF" type="text" class="botonerab" ></td>
		</tr>

		<tr>
			<td width="47%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Nombre'/></td>
			<td width="2%"></td>
			<td align="left"> <input id="nombrePMF" type="text" class="botonerab" ></td>
		</tr>

		<tr>
			<td width="47%" class="botoneralNegritaRight" >Tipo de Terreno</td>
			<td width="2%"></td>
			<td align="left">
				<select id="tipoTerrenoPMF" class="botonerab">
					<option value="Fiscal">Fiscal</option>
					<option value="Privado">Privado</option>
				</select> 
			</td>
		</tr>
		
		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
		<tr>
			<td align="right"> <input type="button" class="botonerab" value="Grabar" onclick="validarPMF()"></td>
			<td width="2%"></td>
			<td align="left"> <input type="button" class="botonerab" value="Cancelar" onclick="ocultar('altaDePMF')"></td>
		</tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
	</table>

	
	<table id="altaDeArea" style="display: none" border="0" class="cuadrado" align="center" width="50%"  cellpadding="2">
		<tr>
			<td colspan="3" class="azulAjustado"><bean:message key='SIIF.subTitulo.AltaArea'/></td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.ReservaForestal'/></td>
			<td width="2%"></td>
			<td align="left"> <input id="reservaForestalArea" type="text" class="botonerab" ></td>
		</tr>

		<tr>
			<td width="47%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Nombre'/></td>
			<td width="2%"></td>
			<td align="left"> <input id="nombreArea" type="text" class="botonerab" ></td>
		</tr>

		<tr>
			<td width="47%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Disposicion'/></td>
			<td width="2%"></td>
			<td align="left"> <input id="disposicionArea" type="text" class="botonerab" ></td>
		</tr>

		<tr>
			<td width="47%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Expediente'/></td>
			<td width="2%"></td>
			<td align="left"> <input id="expedienteArea" type="text" class="botonerab" ></td>
		</tr>


		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
		<tr>
			<td align="right"> <input type="button" class="botonerab" value="Grabar" onclick="validarArea()"></td>
			<td width="2%"></td>
			<td align="left"> <input type="button" class="botonerab" value="Cancelar" onclick="ocultar('altaDeArea')"></td>
		</tr>
		<tr>
			<td height="5" colspan="3"></td>
		</tr>		
	</table>
	
	
</html:form>
