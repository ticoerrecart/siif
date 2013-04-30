<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="<html:rewrite page='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<html:rewrite page='/dwr/util.js'/>"></script>

<div>
<table class="header">
	<tr>
		<td width="15%" height="65"><img height="120%" width="110%" src="../../imagenes/LOGOSIIF2.jpg">
		<!--<td width="15%" height="65"><img height="120%" width="100%" src="../../imagenes/LOGOSIIF.jpg">-->
		<!-- <td width="25%" height="65"><img src="../../imagenes/Arba.jpg">-->
		<!--  <td width="25%" height="65"><img src="../../imagenes/logo5.JPG"> -->
		</td>
		<td width="60%" style="text-align: center; font-weight: bold"><jsp:useBean
			id="now" class="java.util.Date" /> <br>
		<!-- SIIF <br> -->
		Usuario: <label id="usuario"></label> <br>
		Roles: <label id="roles"></label> <br>
		<!--Fecha: <fmt:formatDate value="${now}" pattern="dd/MM/yyyy" /> <br>-->
		<!--Versión: <label id="version"></label></td>-->
		 <td width="25%"><img src="../../imagenes/LaProvincia.jpg">
		<!-- <td width="25%"><img src="../../imagenes/provTF.JPG"> -->
		</td>
	</tr>
</table>

</div>
