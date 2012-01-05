
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>

<script type="text/javascript">

var trPMF = null;
var clasePMF = null;
function seleccionarPMF(idTr){
	
	if(document.getElementById("divModificacionPMF").style.display == "none"){
		if(trPMF!=null){
			$(trPMF).attr("class", clasePMF);	
		}
		trPMF=idTr;
		clasePMF = $(trPMF).attr("class");
		$(trPMF).attr("class", "seleccionado");
	}	
}
</script>

	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="6" cellspacing="0">
		<tr height="5">
			<td class="grisSubtitulo">
				<bean:message key='SIIF.subTitulo.ModificacionPMF'/>
			</td>
			<!--<td class="grisSubtitulo">
				<a href="javascript:ocultarMostrar('tablaPmfs','#divModificacionPMF');">
					<bean:message key='SIIF.label.MostrarOcultar'/>
				</a>
			</td>-->			
		</tr>
		<tr>
			<td class="blancoAjustado">
				<!--<bean:message key='SIIF.subTitulo.ModificacionPlanesMF'/>-->
				<a href="javascript:ocultarMostrar('tablaPmfs','#divModificacionPMF');">
					<bean:message key='SIIF.label.MostrarOcultar'/>
				</a>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<table style="display: none" id="tablaPmfs" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Productor'/></td>
						<td class="azulAjustado" colspan="2" width="20%">
							<bean:message key='SIIF.label.PlanManejoForestal'/>
						</td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Exepdiente'/></td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					
					<c:forEach items="${pmfs}" var="pmf" varStatus="pmfSt">
						<tr id=P${pmfSt.index} onmouseover="javascript:seleccionarPMF(P${pmfSt.index});">
							<td class="botonerab"> ${pmf.productorForestal.nombre}</td>
							<td class="botonerab"> ${pmf.nombre}</td>
							<td class="botonerab"> ${pmf.expediente}</td>
							<td class="botonerab">
								<a href="javascript:mostrarDatosPMF(${pmfSt.index}, ${pmf.id}, '${pmf.nombre}', '${pmf.expediente}')">
									<bean:message key='SIIF.label.Editar'/>
								</a>
							</td>
						</tr>
					</c:forEach>
					
				</table>
			</td>
		</tr>

		<tr>
			<td colspan="2">
				<div id="divModificacionPMF" style="display: none" >
					<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
						<tr>
							<td colspan="3" height="15" class="negrita">
								<bean:message key='SIIF.subTitulo.ModificacionPMF'/>
							</td>
						</tr>	
						<tr>
							<td width="33%" class="botoneralNegritaRight"><bean:message key='SIIF.label.Nombre'/></td>
							<td width="33%"align="left">
								<input id="nombrePMF" type="text" class="botonerab">
								<input type="hidden" id="idPMF">
							</td>
							<td width="33%"></td>
						</tr>
							<tr>
							<td width="33%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Exepdiente'/></td>
							<td width="33%"align="left"><input id="expedientePMF" type="text" class="botonerab" > </td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td> <input type="button" class="botonerab" value="Grabar" onclick="modificarPMF()"></td>
							<td> <input type="button" class="botonerab" value="Borrar" onclick="deletePMF()"></td>
							<td> <input type="button" class="botonerab" value="Cancelar" onclick="cancelarPMF()"></td>							
						</tr>
					</table>
					
				</div>
			</td>
		</tr>			
		<tr height="5">
			<td></td>			
		</tr>
	</table>
	
	<script>
		$("#tablaPmfs tr:nth-child(odd)").addClass("par");
	</script>