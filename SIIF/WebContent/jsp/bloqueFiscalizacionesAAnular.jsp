<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script type="text/javascript"
	src="<html:rewrite page='/dwr/interface/FiscalizacionFachada.js'/>"></script>

<script>
	function anularFiscalizaciones(){
		if(confirm("Esta seguro que desea anular las fiscalizaciones seleccionadas?")){
			var fiscalizaciones = new Array();
			$("input:checked").each(function(){
		    	 var input = $(this); // This is the jquery object of the input, do what you will
		    	 //alert(input.attr("name"))
			     fiscalizaciones.push(input.attr("name"));
			    
		    });
	
			FiscalizacionFachada.anularFiscalizaciones(fiscalizaciones, anularFiscalizacionesCbk);
		}	
	}
	
	function anularFiscalizacionesCbk(){

	    mostrarDetalle();		    		
	}

	function mostrarFiscalizacion(idFiscalizacion){

		$("#idGuia").hide();	
		$("#idDivFiscalizacion").load("../../consultasFiscalizacion.do?metodo=cargarFiscalizacion&idFiscalizacion="+idFiscalizacion+"&strForward=exitoCargarFiscalizacionDesdeAltaGFB");
		$("#idDivFiscalizacion").show(); 
		$("#errores").hide();
	}

	function volverAltaGFB(){

		$("#idGuia").show();
		$("#idDivFiscalizacion").hide();
		//$("#idDivFiscalizacion").empty();
		//$("#errores").show();
	}

</script>

<div id="idGuia">
	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				<tr>
					<td class="azulAjustado" rowspan="2"></td>
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.Fecha'/></td>
					<td class="azulAjustado" colspan="5"><bean:message key='SIIF.label.Localizacion'/></td>					
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.TipoDeProducto'/></td>
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.CantMts3'/></td>					
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.Ver'/></td>
				</tr>
				<tr>
					<td class="azulAjustado"><bean:message key='SIIF.label.PMF'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Tranzon'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Marcacion'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Rodal'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.AreaDeCosecha'/></td>
				</tr>
				<%String clase=""; %>
				<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
					<html:hidden styleId="idFiscalizacion${i.count-1}" property="listaFiscalizaciones[${i.count-1}].id" value=""/>
					<html:hidden styleId="idRodal${i.count-1}" property="listaFiscalizaciones[${i.count-1}].rodal.id" value=""/>					
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
							<c:out value="${fiscalizacion.localizacion.expedientePMF}"></c:out>-
							<c:out value="${fiscalizacion.localizacion.nombrePMF}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.numeroTranzon}"></c:out>-
							<c:out value="${fiscalizacion.localizacion.disposicionTranzon}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.disposicionMarcacion}"></c:out>
						</td>						
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.nombreRodal}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.localizacion.nombreArea}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
						</td>	
						<td class="botonerab">
							<c:out value="${fiscalizacion.cantidadMts}"></c:out>
						</td>	
						<td>
							<a href="javascript:mostrarFiscalizacion(<c:out value='${fiscalizacion.id}'></c:out>);">
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
						<input type="button" class="botonerab" value="Anular Fiscalizaciones" onclick="javascript:anularFiscalizaciones();">
					</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>				
			</table>
		</c:when>	
		<c:otherwise>
			<bean:message key='SIIF.error.NoExiFis'/>
		</c:otherwise>
	</c:choose>	
</div>
	
<div id="idDivFiscalizacion" style="display: none;">	