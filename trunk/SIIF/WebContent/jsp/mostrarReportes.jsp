<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">

</script> 

<table border="1" class="cuadrado" align="center" width="90%" cellpadding="2">
	<tr>
		<th width="2%">nroReporte</th>
		<th width="3%">idReporte</th>
	    <th width="25%">nombreReporte</th>
	    <th width="25%">nombreReportePadre</th>
	    <th width="45%">Archivo</th>
	</tr>
	 <c:forEach items="${reportes}" var="reporte" varStatus="status">
	  <tr>
	  	<td width="5%">${status.count}</td>
	    <td width="5%">${reporte.idReporte}</td>
	    <td width="25%">${reporte.nombreReporte}</td>
	    <td width="25%">${reporte.nombreReportePadre}</td>
	    <td width="45%">
	    	<%--iframe height="0" width="0" frameborder="0" name="frame${reporte.idReporte}>
	    	</iframe--%>
	    	
	    	<form action="" target="frame${reporte.idReporte}" method="post" enctype="multipart/form-data">
		    	<input type="file" name="filename">
	    		<input type="submit" name="Grabar" value="Grabar">
	    	</form>
	    </td>
	  </tr>
	  
	 </c:forEach>
</table>

<br/>
<br/>


