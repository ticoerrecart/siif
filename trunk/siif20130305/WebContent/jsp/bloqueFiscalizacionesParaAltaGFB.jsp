<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<script type="text/javascript"
	src="<html:rewrite page='/js/validacionAjax.js'/>"></script>

<script type="text/javascript">

	function agrElimFisc(indice,idFiscalizacion,idRodal){

		var i = indice+1;
		if($('#idCheck'+i).is(':checked')){
			$("#idFiscalizacion"+indice).val(idFiscalizacion);
			$("#idRodal"+indice).val(idRodal);			
		}else{
			$("#idFiscalizacion"+indice).val(0);
			$("#idRodal"+indice).val(0);
		}
	}	

	function submitCrearGuia(){
		
		$("#idProdForestal").val($("#selectProductores").val());
		//$("#idLocalizacionRodal").val($("#idRodal").val());
		
		validarForm("guiaForestalForm","../guiaForestal","validarFiscalizacionesParaAltaGuiaForestalForm","GuiaForestalForm");
		//document.forms[0].submit();
	}
	
</script>

<html:form action="guiaForestal" styleId="guiaForestalForm">
	<html:hidden property="metodo" value="cargarAltaGuiaForestalBasica" />
	<html:hidden styleId="idProdForestal" property="guiaForestal.productorForestal.id" value="" />
	<html:hidden styleId="idLocalizacionRodal" property="guiaForestal.rodal.id" value="" />
	<c:choose>
		<c:when test="${fn:length(fiscalizaciones)>0}">
			<table border="0" class="cuadradoSinBorde" align="center" width="90%" cellpadding="2">
				<tr>
					<td class="botoneralNegritaLeftGrande">
						<bean:message key='SIIF.subTitulo.Fiscalizaciones'/>
					</td>
				</tr>
			</table>			
			<table border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				<tr>
					<td class="azulAjustado" rowspan="2"></td>
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.Fecha'/></td>
					<td class="azulAjustado" colspan="4"><bean:message key='SIIF.label.Localizacion'/></td>					
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.TipoDeProducto'/></td>
					<td class="azulAjustado" rowspan="2"><bean:message key='SIIF.label.CantMts3'/></td>					
				</tr>
				<tr>
					<td class="azulAjustado"><bean:message key='SIIF.label.PMF'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Tranzon'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Marcacion'/></td>
					<td class="azulAjustado"><bean:message key='SIIF.label.Rodal'/></td>
				</tr>
				<%String clase=""; %>
				<c:forEach items="${fiscalizaciones}" var="fiscalizacion" varStatus="i">
					<html:hidden styleId="idFiscalizacion${i.count-1}" property="listaFiscalizaciones[${i.count-1}].id" value=""/>
					<html:hidden styleId="idRodal${i.count-1}" property="listaFiscalizaciones[${i.count-1}].rodal.id" value=""/>					
					<%clase=(clase.equals("")?"par":""); %>
					<tr id="tr<c:out value='${i.count}'></c:out>" class="<%=clase%>"
						onmouseover="javascript:pintarFila(<c:out value='${i.count}'></c:out>);"
						onmouseout="javascript:despintarFila(<c:out value='${i.count}'></c:out>);">
						
						<td class="botonerab">
							<input type="checkbox" id="idCheck<c:out value='${i.count}'></c:out>"
								onclick="javascript:pintarFila(<c:out value='${i.count}'></c:out>);agrElimFisc(<c:out value='${i.count-1}'></c:out>,<c:out value='${fiscalizacion.id}'></c:out>,<c:out value='${fiscalizacion.rodal.id}'></c:out>);">
						</td>						
						<td class="botonerab">
							<c:out value="${fiscalizacion.fecha}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.rodal.marcacion.tranzon.pmf.expediente}"></c:out>-
							<c:out value="${fiscalizacion.rodal.marcacion.tranzon.pmf.nombre}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.rodal.marcacion.tranzon.numero}"></c:out>-
							<c:out value="${fiscalizacion.rodal.marcacion.tranzon.disposicion}"></c:out>
						</td>
						<td class="botonerab">
							<c:out value="${fiscalizacion.rodal.marcacion.disposicion}"></c:out>
						</td>						
						<td class="botonerab">
							<c:out value="${fiscalizacion.rodal.nombre}"></c:out>
						</td>						
						<td class="botonerab">
							<c:out value="${fiscalizacion.tipoProducto.nombre}"></c:out>
						</td>	
						<td class="botonerab">
							<c:out value="${fiscalizacion.cantidadMts}"></c:out>
						</td>	
											
						<!--<td class="botonerab">
							 <a href="../../guiaForestal.do?metodo=cargarAltaGuiaForestalBasica&id=<c:out value='${fiscalizacion.id}'></c:out>">
								<bean:message key='SIIF.label.Seleccionar'/>
							</a> 
						</td>-->
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