
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
var trArea = null;
var claseArea = null;
function seleccionarArea(idTr){
	
	if(document.getElementById("divModificacionArea").style.display == "none"){
		if(trArea!=null){
			$(trArea).attr("class", claseArea);	
		}
		trArea=idTr;
		claseArea = $(trArea).attr("class");
		$(trArea).attr("class", "seleccionado");
	}	
}
</script>

	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="6" cellspacing="0">
		<tr height="5">
			<td class="grisSubtitulo">
				<bean:message key='SIIF.subTitulo.ModificacionAreas'/>
			</td>			
		</tr>
		<tr>
			<td class="blancoAjustado"> 
				<a href="javascript:ocultarMostrar('tablaAreas','#divModificacionArea');">
					<bean:message key='SIIF.label.MostrarOcultar'/>
				</a>
			</td>
		</tr>
		
		<tr>
			<td>
				<table style="display: none" id="tablaAreas" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Productor'/></td>
						
						<td class="azulAjustado" colspan="4" width="70%"><bean:message key='SIIF.label.Area'/></td>												
						<td class="azulAjustado" width="10%"></td>
					</tr>
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.ReservaForestal'/></td>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Nombre'/></td>												
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Disposicion'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Exepdiente'/></td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					
					
					<c:forEach items="${areas}" var="area" varStatus="areaSt">
						<tr id=A${areaSt.index} onmouseover="javascript:seleccionarArea(A${areaSt.index});">
							<td class="botonerab"> ${area.productorForestal.nombre}</td>
							<td class="botonerab"> ${area.reservaForestalArea}</td>
							<td class="botonerab"> ${area.nombreArea}</td>
							<td class="botonerab"> ${area.disposicionArea}</td>
							<td class="botonerab"> ${area.expedienteArea}</td>
							<td class="botonerab">
								<a href="javascript:mostrarDatosArea(${areaSt.index}, ${area.id},'${area.reservaForestalArea}','${area.nombreArea}','${area.disposicionArea}', '${area.expedienteArea}')">
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
				<div id="divModificacionArea" style="display: none" >
					<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
						<tr>
							<td colspan="3" height="15" class="negrita">
								<bean:message key='SIIF.subTitulo.ModificacionArea'/>
							</td>
						</tr>	
						<tr>
							<td width="33%" class="botoneralNegritaRight" >
								<bean:message key='SIIF.label.ReservaForestal'/>
							</td>
							<td width="33%"align="left"><input id="reservaForestalArea" type="text" class="botonerab" > <input type="hidden" id="idArea"> </td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td width="33%" class="botoneralNegritaRight" >
								<bean:message key='SIIF.label.Nombre'/>
							</td>
							<td width="33%"align="left"><input id="nombreArea" type="text" class="botonerab" ></td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td width="33%" class="botoneralNegritaRight" >
								<bean:message key='SIIF.label.Disposicion'/>
							</td>
							<td width="33%"align="left"><input id="disposicionArea" type="text" class="botonerab" ></td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td width="33%" class="botoneralNegritaRight" >
								<bean:message key='SIIF.label.Expediente'/>
							</td>
							<td width="33%"align="left"><input id="expedienteArea" type="text" class="botonerab" > </td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td> <input type="button" class="botonerab" value="Grabar" onclick="modificarArea()"></td>
							<td> <input type="button" class="botonerab" value="Borrar" onclick="deleteArea()"></td>
							<td> <input type="button" class="botonerab" value="Cancelar" onclick="cancelarArea()"></td>							
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
		$("#tablaAreas tr:nth-child(odd)").addClass("par");
	</script>