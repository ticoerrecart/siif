
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>


	<table border="0" class="cuadrado" align="center" width="70%" cellpadding="10">
		<tr>
			<td class="blancoAjustado" >Modificación de Planes de Manejo Forestales  <a href="javascript:toggle('tablaPmfs')">Mostrar/Ocultar</a></td>
		</tr>
		
		<tr>
			<td>
				<table id="tablaPmfs" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				
					<tr>
						<td class="azulAjustado" width="20%">Productor</td>
						<td class="azulAjustado" colspan="2" width="20%">Plan de Manejo Forestal</td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					<tr>
						<td class="azulAjustado" width="20%">Nombre</td>
						<td class="azulAjustado" width="10%">Nombre</td>
						<td class="azulAjustado" width="10%">Exepdiente</td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					
					<c:forEach items="${pmfs}" var="pmf" varStatus="pmfSt">
						<tr id=P${pmfSt.index}>
							<td class="botonerab"> ${pmf.productorForestal.nombre}</td>
							<td class="botonerab"> ${pmf.nombre}</td>
							<td class="botonerab"> ${pmf.expediente}</td>
							<td class="botonerab"><a href="javascript:mostrarDatosPMF(${pmfSt.index}, ${pmf.id}, '${pmf.nombre}', '${pmf.expediente}')">Editar</a></td>
						</tr>
					</c:forEach>
					
				</table>
			</td>
		</tr>

		<tr>
			<td>
				<div id="divModificacionPMF" style="display: none" >
					<table border="0" class="cuadrado" align="center" width="60%" cellpadding="2">
						<tr>
							<td colspan="3" height="15" class="negrita">
								Modificación de Plan de Manejo Forestal 
							</td>
						</tr>	
						<tr>
							<td width="33%" class="botoneralNegritaRight" >Nombre</td>
							<td width="33%"align="left"><input id="nombrePMF" type="text" class="botonerab" > <input type="hidden" id="idPMF"> </td>
							<td width="33%"></td>
						</tr>
							<tr>
							<td width="33%" class="botoneralNegritaRight" >Expediente</td>
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

	</table>
	
	<script>
		$("#tablaPmfs tr:nth-child(odd)").addClass("par");
	</script>