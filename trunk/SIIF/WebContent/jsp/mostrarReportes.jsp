<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
	function submitir(id){
		var idForm = "form" + id;
		$("#" + idForm).submit();
	}
	
	//este metodo se llama en el load de la página
	function init() {
		//sobrescribo el load!!!!!
		$("#upload_target").unbind("load");
	    $("#upload_target").load(function() {
	      		var theFrame = $("#upload_target");
				var detalle = $("#upload_target").contents().find("#detalle").html();
				if(detalle!=null && detalle!=""){
					alert(detalle);
				}
				//$("#mensajeCallbackErrorExito").html(detalle);
				//$("#mensajeCallbackErrorExito").dialog({width: 250, height:100, resizable: false ,modal: true});
	    });
	
	}
</script> 

<iframe id="upload_target" name="upload_target" src="" scrolling="no" style="width:100%;height:0;border:0px solid #fff;">
</iframe>

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
	    	<form method="post" id="form${status.count}" enctype="multipart/form-data" action="../../actualizarReporte.do" target="upload_target">
				<input type="hidden" name="metodo" value="actualizarReporte">
				<input type="hidden" name="idReporte" value="${reporte.idReporte}">

				<input name="file" type="file" class="botonerab" id="file${status.count}">
				<input type="button" name="action" class="botonerab" value="Subir reporte" onclick="submitir('${status.count}')">

			</form>
			
	    </td>
	  </tr>
	  
	 </c:forEach>
</table>

<br/>
<br/>

<script>
	init();
</script>
