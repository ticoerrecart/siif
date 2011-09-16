<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 
<div>
<table class="header">
	<tr>
		<td width="25%" height="65"><img src="../../imagenes/Arba.jpg">
		</td>
		<td width="50%" style="text-align: center; font-weight: bold"><jsp:useBean
			id="now" class="java.util.Date" /> <br>
		SIIF <br>
		Usuario: <label id="usuario"></label> <br>
		Roles: <label id="roles"></label> <br>
		Fecha: <fmt:formatDate value="${now}" pattern="dd/MM/yyyy" /> <br>
		Versión: <label id="version"></label></td>
		<td width="25%"><img src="../../imagenes/LaProvincia.jpg">
		</td>
	</tr>
</table>

</div>
