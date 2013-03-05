
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="errores" class="rojoAdvertencia">
<c:out value="${error}"></c:out>
</div>
<div class="rojoAdvertencia">
	<button onclick="window.close();" name="Cerrar">Cerrar</button>
</div>



