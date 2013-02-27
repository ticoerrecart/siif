<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/GuiaForestalFachada.js'/>"></script>

<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>

<script>
	function confirmarGuias(){
		if(confirm("Esta seguro que desea confirmar las Guías Forestales seleccionadas?")){
			var guias = new Array();
			$("input:checked").each(function(){
		    	 var input = $(this); // This is the jquery object of the input, do what you will
		    	 
			     guias.push(input.attr("name"));
			    
		    });
	
			GuiaForestalFachada.confirmarGuias(guias, confirmarGuiasCbk);
			//confirmarGuiasCbk();
		}	
	}
	
	function confirmarGuiasCbk(mensaje){

		parent.location = contextRoot() + '/guiaForestal.do?metodo=recuperarProductoresParaConfirmacionDeGuia&mensaje='+mensaje;		    		
	}
	
</script>

<br>
<c:choose>
	<c:when test="${fn:length(guiasForestales)>0}">
		<table border="0" class="cuadrado" align="center" width="80%" cellpadding="2">
			<tr>
				<td class="azulAjustado"></td>			
				<td class="azulAjustado"><bean:message key='SIIF.label.NroDeGuia'/></td>			
				<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
				<td class="azulAjustado"><bean:message key='SIIF.label.FechaVenc'/></td>		
				<td class="azulAjustado"></td>
			</tr>
			<%String clase="";%>
			<c:forEach items="${guiasForestales}" var="guia" varStatus="i">
				<%clase=(clase.equals("")?"par":""); %>
				<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"   
					onmouseover="javascript:mostrarDatos(<c:out value='${i.count}'></c:out>);">
					<td class="botonerab">
						<input type="checkbox" name="<c:out value='${guia.id}'/>">	  
					</td>					
					<td class="botonerab"><c:out value="${guia.nroGuia}" /></td>	   			
					<td class="botonerab">
						<c:out value="${guia.fecha}"></c:out>  
					</td>
					<td class="botonerab">
						<c:out value="${guia.fechaVencimiento}"></c:out>					
					</td>										
					<td>			
						<a href="../../guiaForestal.do?metodo=cargarGuiaForestalParaConfirmacion&idGuia=<c:out value='${guia.id}'></c:out>">
							<bean:message key='SIIF.label.Ver'/>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table border="0" class="cuadradoSinBorde" align="center" width="90%" cellpadding="2">
			<tr>
				<td height="10"></td>
			</tr>				
			<tr>
				<td class="botonerab" colspan="6">
					<input type="button" class="botonerab" value="Confirmar Guias" onclick="javascript:confirmarGuias();">
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>				
		</table>		
	</c:when>
	<c:otherwise>
		<bean:message key='SIIF.error.NoExiGFParaPF'/>
	</c:otherwise>	
</c:choose>

