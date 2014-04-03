<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Cache-Control","no-store"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setHeader("Cache-Control", "private");
   response.setDateHeader("Expires",0);
%>
<tiles:insert page="/jsp/tiles/tilesHBF.jsp">
	<tiles:put name="title" value="DSISIC - Aceptar Pdo Pdas Remito " />
	<tiles:put name="header" value="/jsp/tiles/header2.jsp" />
	<tiles:put name="menu" value="/jsp/tiles/menu2.jsp" />
	<tiles:put name="body" value="/jsp/bodyPruebaTiles.jsp" />
	<tiles:put name="footer" value="/jsp/tiles/footer2.jsp" />
</tiles:insert>