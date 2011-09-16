
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
			<td class="blancoAjustado">Modificación de Tranzones <a href="javascript:toggle('tablaTranzones')">Mostrar/Ocultar</a></td>
		</tr>
		
		<tr>
			<td>
				<table id="tablaTranzones" border="0" class="cuadrado" align="center" width="90%" cellpadding="2">
				
					<tr>
						<td class="azulAjustado" width="20%">Productor</td>
						<td class="azulAjustado" colspan="2" width="20%">Plan de Manejo Forestal</td>
						<td class="azulAjustado" colspan="2" width="20%">Tranzon</td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					<tr>
						<td class="azulAjustado" width="20%">Nombre</td>
						<td class="azulAjustado" width="10%">Nombre</td>
						<td class="azulAjustado" width="10%">Exepdiente</td>
						<td class="azulAjustado" width="10%">Numero</td>												
						<td class="azulAjustado" width="10%">Disposicion</td>
						<td class="azulAjustado" width="10%"></td>
					</tr>
					
					
					<c:forEach items="${tranzones}" var="tranzon" varStatus="tranzonSt">
						<tr id=T${tranzonSt.index}>
							<td class="botonerab"> ${tranzon.pmf.productorForestal.nombre}</td>
							<td class="botonerab"> ${tranzon.pmf.nombre}</td>
							<td class="botonerab"> ${tranzon.pmf.expediente}</td>
							<td class="botonerab"> ${tranzon.numero}</td>
							<td class="botonerab"> ${tranzon.disposicion}</td>
							<td class="botonerab"><a href="javascript:mostrarDatosTranzon(${tranzonSt.index}, ${tranzon.id}, ${tranzon.numero}, '${tranzon.disposicion}')">Editar</a></td>
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
								Modificacion de Tranzon
							</td>
						</tr>	
						<tr>
							<td width="33%" class="botoneralNegritaRight" >Numero</td>
							<td width="33%"align="left"><input id="numeroTranzon" type="text" class="botonerab" /> <input type="hidden" id="idTranzon"/> </td>
							<td width="33%"></td>
						</tr>
						<tr>
							<td width="33%" class="botoneralNegritaRight" >Disposicion</td>
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

	</table>
	
	<script>
		$("#tablaTranzones tr:nth-child(odd)").addClass("par");
	</script>