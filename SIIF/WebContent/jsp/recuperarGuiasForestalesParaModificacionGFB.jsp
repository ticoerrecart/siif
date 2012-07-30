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
<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<div id="exitoGrabado" class="verdeExito">${exitoModificacion}</div>
<div id="errores" class="rojoAdvertencia">${errorModificacion}</div>

<table border="0" class="cuadrado" align="center" width="70%"
	cellpadding="2">

	<tr>
		<td class="azulAjustado"><bean:message key='SIIF.titulo.ModificacionGuia'/></td>
	</tr>
	<tr>
		<td height="20"></td>
	</tr>
	<%@include file="bloqueTipoDeEntidadProductorForestal.jsp" %>
</table>