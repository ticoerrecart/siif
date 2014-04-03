<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/js/validacionAjax.js'/>"></script>

<script type="text/javascript">
	function submitir(){
		$('#monto').attr('disabled',false);
		validarForm("caminoForm","../camino","validarCaminoForm","CaminoForm");
	}
	
	function actualizarComp(){
		valor = $('#costoDelCamino').val();
		$('#monto').val(valor / 2);
	}	
	
</script>

<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<html:form action="camino" styleId="caminoForm">
	<html:hidden property="metodo" value="altaCamino" />
	
	<table border="0" class="cuadrado" align="center" width="50%" cellpadding="2">
		<tr>
			<td colspan="4" class="azulAjustado">
				<bean:message key='SIIF.titulo.AltaCamino'/>
			</td>
		</tr>
		<tr>
			<td height="10" colspan="4"></td>
		</tr>		
		<tr>
			<td class="botoneralNegritaRight"><bean:message key='SIIF.label.ProductorForestal'/></td>
			<td align="left" colspan="3">	
				<html:select styleClass="botonerab" property="idProductor" styleId="idProductorForestal">
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
			<td class="botoneralNegritaRight" >Costo Del Camino </td>
			<td align="left">
				<input type="text"  id="costoDelCamino" name="caminoConstruido.costoDelCamino" class="botonerab" onkeypress="javascript:esNumericoConDecimal(event);" onblur="actualizarComp();" >
			</td>
			<td class="botoneralNegritaRight" >Compensación </td>
			<td align="left">
				<input type="text" disabled="disabled" id="monto" name="caminoConstruido.monto" class="botonerab" onkeypress="return evitarAutoSubmit(event)">
			</td>
		</tr>
		<tr>	
			<td class="botoneralNegritaRight" >Nota De Referencia </td>
			<td align="left" colspan="3">
				<input id="notaReferencia" type="text"  name="caminoConstruido.notaReferencia" class="botonerab" onkeypress="return evitarAutoSubmit(event)">
			</td>	
			</tr>
		
		<tr>				
			
			<td class="botoneralNegritaRight" >Autorizante </td>
			<td align="left" colspan="3">
				<input id="autorizante" type="text"  name="caminoConstruido.autorizante" class="botonerab" onkeypress="return evitarAutoSubmit(event)">
			</td>
		</tr>
		
		<tr>
			<td height="20" colspan="2"></td>
		</tr>
		<tr>
			<td height="20" colspan="4">
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
