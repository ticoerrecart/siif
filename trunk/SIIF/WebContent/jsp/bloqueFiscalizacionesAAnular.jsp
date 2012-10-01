<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/FiscalizacionFachada.js'/>"></script>

<script>
	function anularFiscalizaciones(){
		var fiscalizaciones = new Array();
		$("input:checked").each(function(){
	    	 var input = $(this); // This is the jquery object of the input, do what you will
	    	 //alert(input.attr("name"))
		     fiscalizaciones.push(input.attr("name"));
		    
	    });

		FiscalizacionFachada.anularFiscalizaciones(fiscalizaciones, anularFiscalizacionesCbk());
	}
	
	function anularFiscalizacionesCbk(){
		mostrarDetalle();
	}

</script>
	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<table border="0" class="cuadrado" 
				align="center" width="70%" cellpadding="2">
				<tr>
					<td class="azulAjustado">&nbsp;</td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.ProductorForestal'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.NroDeGuia'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.TipoDeProducto'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.CantMts3'/></td>
				</tr>
				<%String clase=""; %>
				<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
					<%clase=(clase.equals("")?"par":""); %>
					<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
						onmouseover="javascript:mostrarDatos(<c:out value='${i.count}'></c:out>);">
						<td class="botonerab">
							<input type="checkbox" name="<c:out value='${fiscalizacion.id}'/>">
						</td>
						<td class="botonerab">
							<fmt:formatDate	value='${fiscalizacion.fecha}' pattern='dd/MM/yyyy' />
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.productorForestal.nombre}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.guiaForestal.nroGuia}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.cantidadMts}"></c:out>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td class="botonerab" colspan="6">
						<input type="button" value="Anular Fiscalizaciones" onclick="javascript:anularFiscalizaciones();">
					</td>
				</tr>
			</table>
		</c:when>	
		<c:otherwise>
			<bean:message key='SIIF.error.NoExiFis'/>
		</c:otherwise>
	</c:choose>	
