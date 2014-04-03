<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>
<script type="text/javascript"
	src="<html:rewrite page='/js/funcUtiles.js'/>"></script>

<script type="text/javascript">

</script>

<div id="exito" class="verdeExito">${exitoGrabado}</div>
	<br>
	<table border="0" class="cuadrado" align="center" width="35%" cellpadding="3">			
		<tr>
			<td class="grisSubtitulo" width="50%">
				<bean:message key='SIIF.label.NroDeCertificado'/>
			</td>
			<td class="grisClaroSubtituloLeft" width="50%">
				<c:out value="${nroCertificado}"></c:out> 
			</td>											
		</tr>
		<tr>
			<td class="grisSubtitulo">
				<bean:message key='SIIF.label.VolExportado'/>
			</td>
			<td class="grisClaroSubtituloLeft">
				<c:out value="${volumenExportado}"></c:out> 
			</td>											
		</tr>	
	</table>
