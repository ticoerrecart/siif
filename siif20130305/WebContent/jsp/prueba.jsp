
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="prueba" items="${pruebas}" varStatus="status">
	<p>${prueba.name}</p>
</c:forEach>

