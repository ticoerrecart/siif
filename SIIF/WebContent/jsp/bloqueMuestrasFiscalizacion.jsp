<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/calendario.tld" prefix="cal"%>

<%Integer cant = Integer.parseInt(request.getParameter("cantMuestras"));
  Integer cantT = Integer.parseInt(request.getParameter("cantTotal"));
  Integer indiceDiv = Integer.parseInt(request.getParameter("indiceDiv"));
%>

<!-- <table id="tablaMuestras" border="0" class="cuadrado" align="center"
	width="70%" cellpadding="2" cellspacing="0">
	<tr>
		<td class="azulAjustado"></td>
		<td class="azulAjustado">Largo</td>
		<td class="azulAjustado">Diametro 1</td>
		<td class="azulAjustado">Diametro 2</td>
	</tr>
 -->
 <table id="tablaMuestras" border="0" class="cuadradoSinBorde" align="center"
	width="70%" cellpadding="2" cellspacing="0">
	<%for(int i=cantT;i<=cantT-1+cant;i++){%>
	<tr id="espAntes<%=i%>">
		<td height="5" colspan="4"></td>
	</tr>
	<tr id="fila<%=i%>">
		<td class="botoneralNegritaRight"><%=i+1 %></td>
		<td><input class="botonerab" type="text"
			name='<%="muestras["+i+"].largo"%>'></td>
		<td><input class="botonerab" type="text"
			name='<%="muestras["+i+"].diametro1"%>'></td>
		<td><input class="botonerab" type="text"
			name='<%="muestras["+i+"].diametro2"%>'></td>
	</tr>

	<tr id="espDespues<%=i%>">
		<td height="5" colspan="4"></td>
	</tr>
	<%}%>
</table>
<div id="tabla<%=indiceDiv%>"></div>
