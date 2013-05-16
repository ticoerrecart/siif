<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/JQuery/jquery-1.3.2.min.js'/>"></script>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/UtilFachada.js'/>"></script>

<script>
	function ejecutarScript(){
		UtilFachada.execute($("#script").val(),ejecutarScriptCallback );
	}
	
	function ejecutarScriptCallback(str){
		$("#exitoGrabado").html(str + " filas Afectadas.");
	}
</script>


<div id="exitoGrabado" class="verdeExito">${exitoGrabado}</div>

<%-- errores de validaciones AJAX --%>
<div id="errores" class="rojoAdvertencia">${error}</div>

<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
	<tr>
		<td class="azulAjustado">&nbsp;</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<textarea rows="20" cols="100" id="script">
			</textarea>
		</td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<tr>
		<td>
			<input type="button" onclick="javascript:ejecutarScript();" name="Aceptar" value="Aceptar"/> 
		</td>
	</tr>	
	<tr>
		<td height="10"></td>
	</tr>	
</table>