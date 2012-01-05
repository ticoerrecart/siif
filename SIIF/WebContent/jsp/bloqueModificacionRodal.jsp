
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
var trRodal = null;
var claseRodal = null;
function seleccionarRodal(idTr){
	
	if(document.getElementById("divModificacionRodal").style.display == "none"){
		if(trRodal!=null){
			$(trRodal).attr("class", claseRodal);	
		}
		trRodal=idTr;
		claseRodal = $(trRodal).attr("class");
		$(trRodal).attr("class", "seleccionado");
	}	
}
</script>

	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="6" cellspacing="0">
		<tr height="5">
			<td class="grisSubtitulo">
				<bean:message key='SIIF.subTitulo.ModificacionRodales'/>
			</td>			
		</tr>
		<tr>
			<td class="blancoAjustado"> 
				<a href="javascript:ocultarMostrar('tablaRodales','#divModificacionRodal');">
					<bean:message key='SIIF.label.MostrarOcultar'/>
				</a>
			</td>
		</tr>
		
		<tr>
			<td>
				<table style="display: none" id="tablaRodales" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Productor'/></td>
						<td class="azulAjustado" colspan="2" width="20%">
							<bean:message key='SIIF.label.PlanManejoForestal'/>
						</td>
						<td class="azulAjustado" colspan="2" width="20%">
							<bean:message key='SIIF.label.Tranzon'/>
						</td>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Marcacion'/></td>												
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Rodal'/></td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Exepdiente'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Numero'/></td>												
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Disposicion'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Disposicion'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					
					
					<c:forEach items="${rodales}" var="rodal" varStatus="rodalSt">
						<tr id=R${rodalSt.index} onmouseover="javascript:seleccionarRodal(R${rodalSt.index});">
							<td class="botonerab"> ${rodal.marcacion.tranzon.pmf.productorForestal.nombre}</td>
							<td class="botonerab"> ${rodal.marcacion.tranzon.pmf.nombre}</td>
							<td class="botonerab"> ${rodal.marcacion.tranzon.pmf.expediente}</td>
							<td class="botonerab"> ${rodal.marcacion.tranzon.numero}</td>
							<td class="botonerab"> ${rodal.marcacion.tranzon.disposicion}</td>
							<td class="botonerab"> ${rodal.marcacion.disposicion}</td>
							<td class="botonerab"> ${rodal.nombre}</td>
							<td class="botonerab">
								<a href="javascript:mostrarDatosRodal(${rodalSt.index}, ${rodal.id}, '${rodal.nombre}')">
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
				<div id="divModificacionRodal" style="display: none" >
					<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
						<tr>
							<td colspan="3" height="15" class="negrita">
								<bean:message key='SIIF.subTitulo.ModificacionRodal'/>
							</td>
						</tr>	
						<tr>
							<td width="33%" class="botoneralNegritaRight" >
								<bean:message key='SIIF.label.Nombre'/>
							</td>
							<td width="33%"align="left"><input id="nombreRodal" type="text" class="botonerab" > <input type="hidden" id="idRodal"> </td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td> <input type="button" class="botonerab" value="Grabar" onclick="modificarRodal()"></td>
							<td> <input type="button" class="botonerab" value="Borrar" onclick="deleteRodal()"></td>
							<td> <input type="button" class="botonerab" value="Cancelar" onclick="cancelarRodal()"></td>							
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
		$("#tablaRodales tr:nth-child(odd)").addClass("par");
	</script>