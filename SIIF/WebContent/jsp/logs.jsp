<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
 
<script type="text/javascript" src="<html:rewrite page='/js/funcUtiles.js'/>"></script>
<html>

	<c:choose>
		<c:when test="${empty error}">
			<table>
				<c:forEach items="${files}" var="file">
					<tr>
						<td align="left">
							<a href='/SIIF/log.do?metodo=verLog&file=${file}'>${file.name}</a> 
						</td>
						<td>
							${filesSize[file.name]}
						</td>
						<td>
							${filesModified[file.name]}
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			${error}
		</c:otherwise>
	</c:choose>
	
	
</html>
