
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
var trMarcacion = null;
var claseMarcacion = null;
function seleccionarMarcacion(idTr){
	
	if(document.getElementById("divModificacionMarcacion").style.display == "none"){
		if(trMarcacion!=null){
			$(trMarcacion).attr("class", claseMarcacion);	
		}
		trMarcacion=idTr;
		claseMarcacion = $(trMarcacion).attr("class");
		$(trMarcacion).attr("class", "seleccionado");
	}	
}
</script>

	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="6" cellspacing="0">
		<tr height="5">
			<td class="grisSubtitulo">
				<bean:message key='SIIF.subTitulo.ModificacionMarcaciones'/>
			</td>			
		</tr>
		<tr>
			<td class="blancoAjustado">
				<a href="javascript:ocultarMostrar('tablaMarcaciones','#divModificacionMarcacion');">
					<bean:message key='SIIF.label.MostrarOcultar'/>
				</a>
				</td>
		</tr>
		<tr>
			<td>
				<table style="display: none" id="tablaMarcaciones" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Productor'/></td>
						<td class="azulAjustado" colspan="2" width="20%">
							<bean:message key='SIIF.label.PlanManejoForestal'/>
						</td>
						<td class="azulAjustado" colspan="2" width="20%">
							<bean:message key='SIIF.label.Tranzon'/>
						</td>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Marcacion'/></td>												
						<td class="azulAjustado" width="10%"></td>
					</tr>
					<tr>
						<td class="azulAjustado" width="20%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Nombre'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Exepdiente'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Numero'/></td>												
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Disposicion'/></td>
						<td class="azulAjustado" width="10%"><bean:message key='SIIF.label.Disposicion'/></td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					
					
					<c:forEach items="${marcaciones}" var="marcacion" varStatus="marcacionSt">
						<tr id=M${marcacionSt.index} onmouseover="javascript:seleccionarMarcacion(M${marcacionSt.index});">
							<td class="botonerab"> ${marcacion.tranzon.pmf.productorForestal.nombre}</td>
							<td class="botonerab"> ${marcacion.tranzon.pmf.nombre}</td>
							<td class="botonerab"> ${marcacion.tranzon.pmf.expediente}</td>
							<td class="botonerab"> ${marcacion.tranzon.numero}</td>
							<td class="botonerab"> ${marcacion.tranzon.disposicion}</td>
							<td class="botonerab"> ${marcacion.disposicion}</td>
							<td class="botonerab">
								<a href="javascript:mostrarDatosMarcacion(${marcacionSt.index}, ${marcacion.id}, '${marcacion.disposicion}')">
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
				<div id="divModificacionMarcacion" style="display: none" >
					<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
						<tr>
							<td colspan="3" height="15" class="negrita">
								<bean:message key='SIIF.subTitulo.ModificacionMarcacion'/>
							</td>
						</tr>	
						<tr>
							<td width="33%" class="botoneralNegritaRight" ><bean:message key='SIIF.label.Disposicion'/></td>
							<td width="33%"align="left">
								<input id="disposicionMarcacion" type="text" class="botonerab" > 
								<input type="hidden" id="idMarcacion"> 
							</td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td> <input type="button" class="botonerab" value="Grabar" onclick="modificarMarcacion()"></td>
							<td> <input type="button" class="botonerab" value="Borrar" onclick="deleteMarcacion()"></td>
							<td> <input type="button" class="botonerab" value="Cancelar" onclick="cancelarMarcacion()"></td>							
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
		$("#tablaMarcaciones tr:nth-child(odd)").addClass("par");
	</script>