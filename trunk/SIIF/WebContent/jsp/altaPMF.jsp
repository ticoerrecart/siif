<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/calendario.tld" prefix="cal"%>

<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/UbicacionFachada.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/Validator.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/interface/UbicacionAction.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/altaUbicacion.js'/>"></script>


<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="ubicacion" styleId="ubicacionForm">
	<html:hidden property="metodo" value="altaUbicacion" />
	
	<table border="0" class="cuadrado" align="center" width="50%" cellpadding="2">
		<tr>
			<td colspan="3" class="azulAjustado">Alta de Plan de Manejo Forestal</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td class="botoneralNegritaRight">Productor Forestal</td>
			<td width="2%"></td> 
			<td align="left">	
				<html:select styleClass="botonerab" property="idProductorForestal" styleId="idProductorForestal" onchange="actualizarComboPMF()">
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
			<td class="botoneralNegritaRight">Plan de Manejo Forestal</td>
			<td width="2%"></td>
			<td align="left"> 
				<html:select styleClass="botonerab" property="idPMF" styleId="idPMF" onchange="actualizarComboTranzon()" />
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Tranzon</td>
			<td width="2%"></td>
			<td align="left"> 
				<html:select styleClass="botonerab" property="idTranzon" styleId="idTranzon"  onchange="actualizarComboMarcacion()" />
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Marcacion</td> 
			<td width="2%"></td>
			<td align="left">	
				<html:select styleClass="botonerab" property="idMarcacion" styleId="idMarcacion" onchange="actualizarComboRodal()" />
			</td>
		</tr>
		<tr>
			<td class="botoneralNegritaRight">Rodal</td>
			<td width="2%"></td>
			<td align="left"> 
				<html:select styleClass="botonerab" property="idRodal" styleId="idRodal" onchange="cambioComboRodal()" />
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
			<td colspan="3" class="azulAjustado">Alta de Rodal</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight" >Nombre</td>
			<td width="2%"></td>
			<td align="left"><input id="nombreRodal" type="text" class="botonerab" ></td>
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
			<td colspan="3" class="azulAjustado">Alta de Marcacion</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight" >Disposicion</td>
			<td width="2%"></td>
			<td align="left"> <input id="disposicionMarcacion" type="text" class="botonerab" ></td>
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
			<td colspan="3" class="azulAjustado">Alta de Tranzon</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight" >Numero</td>
			<td width="2%"></td>
			<td align="left"> <input id="numeroTranzon" type="text" class="botonerab" ></td>
		</tr>

		<tr>
			<td width="47%" class="botoneralNegritaRight">Disposicion</td>
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
			<td colspan="3" class="azulAjustado">Alta de PMF</td>
		</tr>
		<tr>
			<td height="10" colspan="3"></td>
		</tr>		
		<tr>
			<td width="47%" class="botoneralNegritaRight" >Expediente</td>
			<td width="2%"></td>
			<td align="left"> <input id="expedientePMF" type="text" class="botonerab" ></td>
		</tr>

		<tr>
			<td width="47%" class="botoneralNegritaRight" >Nombre</td>
			<td width="2%"></td>
			<td align="left"> <input id="nombrePMF" type="text" class="botonerab" ></td>
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

	
</html:form>
