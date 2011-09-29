<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">

var type;
if (navigator.userAgent.indexOf("Opera")!=-1 && document.getElementById) type="OP"; 
if (document.all) type="IE"; 
if (!document.all && document.getElementById) type="MO";

function llamarReporte(){	

	var metodo = document.getElementById("paramForward").value;
	//var especificaciones = "top=0, left=0, toolbar=no,location=no, status=no,menubar=no,scrollbars=no, resizable=no";
	var especificaciones = 'top=0,left=0,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable';
	if(type == "IE"){
		window.open("./reporte.do?metodo="+metodo,"",especificaciones);
	}else{
		window.open("../../reporte.do?metodo="+metodo,"",especificaciones);
	}	 
}

</script> 

<input id="paramForward" type="hidden" value="${paramForward}">

<script type="text/javascript">
	llamarReporte();
</script>

