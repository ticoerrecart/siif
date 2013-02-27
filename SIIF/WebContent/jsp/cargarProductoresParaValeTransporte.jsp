<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarLetras.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/validarNum.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/Concurrent.Thread-full-20090713.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/EntidadFachada.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

	function submitir(){
		validarForm("guiaForestalForm","../guiaForestal","validarNroGuiaForm","GuiaForestalForm");
	}
</script>

<div id="errores" class="rojoAdvertencia">${error}</div>
<br>
<table border="0" class="cuadrado" align="center" width="70%"
	cellpadding="2">

	<tr>
		<td class="azulAjustado"><c:out value="${titulo}" /></td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<html:form action="guiaForestal" styleId="guiaForestalForm">
				<html:hidden property="metodo" value="${forwardBuscarNroGuia}"/>		
				<table border="0" class="cuadrado" align="center" width="70%" cellpadding="2">
					<tr>
						<td height="10"></td>
					</tr>
					<tr>
						<td width="30%" class="botoneralNegritaRight">
							<bean:message key='SIIF.label.NroDeGuia'/>
						</td>
						<td width="10%">
							
						</td>						
						<td align="left">
							<input id="idNroGuia" class="botonerab" type="text" size="20" name="guiaForestal.nroGuia" 
									onkeypress="return evitarAutoSubmit(event)">
							<input class="botonerab" type="button" value="Buscar" onclick="javascript:submitir();">
						</td>	
					</tr>				
					<tr>
						<td height="10"></td>
					</tr>				
				</table>
			</html:form>	
		</td>
	</tr>	
	<tr>
		<td height="10"></td>
	</tr>
	<%@include file="bloqueTipoDeEntidadProductorForestal.jsp" %>
	
</table>
