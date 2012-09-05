<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>

<script type="text/javascript">

	function agrElimFisc(indice,idFiscalizacion,idTipoProducto){

		var i = indice+1;
		if($('#idCheck'+i).is(':checked')){
			$("#idFiscalizacion"+indice).val(idFiscalizacion);
			$("#idTipoProducto"+indice).val(idTipoProducto);			
		}else{
			$("#idFiscalizacion"+indice).val(0);
			$("#idTipoProducto"+indice).val(0);
		}
	}	

	function submitCrearGuia(){
		
		$("#idProdForestal").val($("#selectProductores").val());
		
		validarForm("guiaForestalForm","../guiaForestal","validarFiscalizacionesParaAltaGuiaForestalForm","GuiaForestalForm");
		//document.forms[0].submit();
	}
	
</script>

<html:form action="guiaForestal" styleId="guiaForestalForm">
	<html:hidden property="metodo" value="cargarAltaGuiaForestalBasica" />
	<html:hidden styleId="idProdForestal" property="guiaForestal.productorForestal.id" value="" />
	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<table border="0" class="cuadradoSinBorde" align="center" width="70%" cellpadding="2">
				<tr>
					<td class="botoneralNegritaLeftGrande">
						<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>
					</td>
				</tr>
			</table>			
			<table border="0" class="cuadrado" 
				align="center" width="70%" cellpadding="2">
				<tr>
					<td class="azulAjustado"></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Fecha'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.ProductorForestal'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.TipoDeProducto'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.CantMts3'/></td>
					<td class="azulAjustado"></td>
				</tr>
				<%String clase=""; %>
				<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
					<html:hidden styleId="idFiscalizacion${i.count-1}" property="listaFiscalizaciones[${i.count-1}].id" value=""/>
					<html:hidden styleId="idTipoProducto${i.count-1}" property="listaFiscalizaciones[${i.count-1}].tipoProducto.id" value=""/>					
					<%clase=(clase.equals("")?"par":""); %>
					<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
						onmouseover="javascript:pintarFila(<c:out value='${i.count}'></c:out>);"
						onmouseout="javascript:despintarFila(<c:out value='${i.count}'></c:out>);">
						
						<td class="botonerab">
							<input type="checkbox" id="idCheck<c:out value='${i.count}'></c:out>"
								onclick="javascript:pintarFila(<c:out value='${i.count}'></c:out>);agrElimFisc(<c:out value='${i.count-1}'></c:out>,<c:out value='${fiscalizacion.id}'></c:out>,<c:out value='${fiscalizacion.tipoProducto.id}'></c:out>);">
						</td>						
						<td class="botonerab">
							<c:out value="${fiscalizacion.fecha}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.productorForestal.nombre}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
						</td>	
						<td class="botonerab">
							<c:out value="${fiscalizacion.cantidadMts}"></c:out>
						</td>						
						<td class="botonerab">
							<a href="../../guiaForestal.do?metodo=cargarAltaGuiaForestalBasica&id=<c:out value='${fiscalizacion.id}'></c:out>">
								<bean:message key='SIIF.label.Seleccionar'/>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>	
		<c:otherwise>
			<bean:message key='SIIF.error.NoExiFis'/>
		</c:otherwise>
	</c:choose>
	<table border="0" class="cuadradoSinBorde" align="center" width="70%" cellpadding="2">
		<tr>
			<td height="20"></td>
		</tr>	
		<tr>
			<td>
				<input id="idBotonCrearGuia" class="botonerab" type="button"" value="Crear Guía Forestal" onclick="javascript:submitCrearGuia();">
			</td>
		</tr>
	</table>
</html:form>