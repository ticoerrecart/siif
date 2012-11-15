package ar.com.siif.struts.actions;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IReportesPorProductoFachada;
import ar.com.siif.utils.Constantes;

public class ReportesPorProductoAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward generarReporteVolumenPorAnioForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductoFachada reportesPorProductoFachada = 
								(IReportesPorProductoFachada) ctx.getBean("reportesPorProductoFachada");
			
			String volumen = request.getParameter("volumen");
						
			byte[] bytes = reportesPorProductoFachada.generarReporteVolumenPorAnioForestal(path,volumen);		
			
			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return null;
	}	
}
