
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
var trTranzon = null;
var claseTranzon = null;
function seleccionarTranzon(idTr){
	
	if(document.getElementById("divModificacionTranzon").style.display == "none"){
		if(trTranzon!=null){
			$(trTranzon).attr("class", claseTranzon);	
		}
		trTranzon=idTr;
		claseTranzon = $(trTranzon).attr("class");
		$(trTranzon).attr("class", "seleccionado");
	}	
}
</script>

	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="6" cellspacing="0">
		<tr height="5">
			<td class="grisSubtitulo">
				<bean:message key='SIIF.subTitulo.ModificacionTranzones'/>
			</td>			
		</tr>	
		<tr>
			<td class="blancoAjustado">				
				<a href="javascript:ocultarMostrar('tablaTranzones','#divModificacionTranzon');">
					<bean:message key='SIIF.label.MostrarOcultar'/>
				</a>
			</td>
		</tr>
		
		<tr>
			<td>
				<table style="display: none" id="tablaTranzones" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Productor'/></td>
						<td class="azulAjustado" colspan="2" width="20%">
							<bean:message key='SIIF.label.PlanManejoForestal'/>
						</td>
						<td class="azulAjustado" colspan="2" width="20%">
							<bean:message key='SIIF.label.Tranzon'/>
						</td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Exepdiente'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Numero'/></td>												
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Disposicion'/></td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					
					
					<c:forEach items="${tranzones}" var="tranzon" varStatus="tranzonSt">
						<tr id=T${tranzonSt.index} onmouseover="javascript:seleccionarTranzon(T${tranzonSt.index});">
							<td class="botonerab"> ${tranzon.pmf.productorForestal.nombre}</td>
							<td class="botonerab"> ${tranzon.pmf.nombre}</td>
							<td class="botonerab"> ${tranzon.pmf.expediente}</td>
							<td class="botonerab"> ${tranzon.numero}</td>
							<td class="botonerab"> ${tranzon.disposicion}</td>
							<td class="botonerab">
							<a href="javascript:mostrarDatosTranzon(${tranzonSt.index}, ${tranzon.id}, ${tranzon.numero}, '${tranzon.disposicion}')">
									<bean:message key='SIIF.label.Editar'/>
								</a>
							</td>
						</tr>
					</c:forEach>
					
					
				</table>
			</td>
		</tr>

		<tr>
			<td>
				<div id="divModificacionTranzon" style="display: none" >
					<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
						<tr>
							<td colspan="3" height="15" class="negrita">
								<bean:message key='SIIF.subTitulo.ModificacionTranzon'/>
							</td>
						</tr>	
						<tr>
							<td width="33%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Numero'/></td>
							<td width="33%"align="left">
								<input id="numeroTranzon" type="text" class="botonerab" /> 
								<input type="hidden" id="idTranzon"/> 
							</td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td width="33%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Disposicion'/></td>
							<td width="33%"align="left"><input id="disposicionTranzon" type="text" class="botonerab" />  </td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td> <input type="button" class="botonerab" value="Grabar" onclick="modificarTranzon()"></td>
							<td> <input type="button" class="botonerab" value="Borrar" onclick="deleteTranzon()"></td>
							<td> <input type="button" class="botonerab" value="Cancelar" onclick="cancelarTranzon()"></td>							
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
		$("#tablaTranzones tr:nth-child(odd)").addClass("par");
	</script>