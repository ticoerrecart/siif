
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
			<td class="blancoAjustado">Modificación de Rodales <a href="javascript:toggle('tablaRodales')">Mostrar/Ocultar</a></td>
		</tr>
		
		<tr>
			<td>
				<table id="tablaRodales" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				
					<tr>
						<td class="azulAjustado" width="20%">Productor</td>
						<td class="azulAjustado" colspan="2" width="20%">Plan de Manejo Forestal</td>
						<td class="azulAjustado" colspan="2" width="20%">Tranzon</td>
						<td class="azulAjustado" width="20%">Marcacion</td>												
						<td class="azulAjustado" width="20%">Rodal</td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					<tr>
						<td class="azulAjustado" width="20%">Nombre</td>
						<td class="azulAjustado" width="10%">Nombre</td>
						<td class="azulAjustado" width="10%">Exepdiente</td>
						<td class="azulAjustado" width="10%">Numero</td>												
						<td class="azulAjustado" width="10%">Disposicion</td>
						<td class="azulAjustado" width="10%">Disposicion</td>
						<td class="azulAjustado" width="10%">Nombre</td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					
					
					<c:forEach items="${rodales}" var="rodal" varStatus="rodalSt">
						<tr id=R${rodalSt.index}>
							<td class="botonerab"> ${rodal.marcacion.tranzon.pmf.productorForestal.nombre}</td>
							<td class="botonerab"> ${rodal.marcacion.tranzon.pmf.nombre}</td>
							<td class="botonerab"> ${rodal.marcacion.tranzon.pmf.expediente}</td>
							<td class="botonerab"> ${rodal.marcacion.tranzon.numero}</td>
							<td class="botonerab"> ${rodal.marcacion.tranzon.disposicion}</td>
							<td class="botonerab"> ${rodal.marcacion.disposicion}</td>
							<td class="botonerab"> ${rodal.nombre}</td>
							<td class="botonerab"><a href="javascript:mostrarDatosRodal(${rodalSt.index}, ${rodal.id}, '${rodal.nombre}')">Editar</a></td>
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
								Modificacion de Rodal
							</td>
						</tr>	
						<tr>
							<td width="33%" class="botoneralNegritaRight" >Nombre</td>
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

	</table>
	
	<script>
		$("#tablaRodales tr:nth-child(odd)").addClass("par");
	</script>