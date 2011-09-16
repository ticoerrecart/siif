package ar.com.siif.struts.actions;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.IReportesFachada;

public class ReportesAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward mostrarReporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String forward = "exitoMostrarReporte";
		request.setAttribute("paramForward", request.getParameter("paramForward"));
		
		return mapping.findForward(forward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward pruebaJasper(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx.getBean("reportesFachada");
			
			byte[] bytes = reportesFachada.pruebaJasper(path);		
			
			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			//response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return null;
	}	

	@SuppressWarnings("unchecked")
	public ActionForward generarReporteGuiaForestal(ActionMapping mapping, 
													ActionForm form,
													HttpServletRequest request, 
													HttpServletResponse response) 
													throws Exception 
	{
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx.getBean("reportesFachada");
			
			String idGuia = request.getParameter("idGuia");
			int idGuiaForestal = Integer.valueOf(idGuia).intValue();

			byte[] bytes = reportesFachada.generarReporteGuiaForestal(idGuiaForestal,path);		
			
			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			//response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();				

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return null;
	}	

	@SuppressWarnings("unchecked")
	public ActionForward generarReporteFiscalizacion(ActionMapping mapping, 
													 ActionForm form,
													 HttpServletRequest request, 
													 HttpServletResponse response) 
													 throws Exception 
	{
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx.getBean("reportesFachada");
			
			String paramFiscalizacion = request.getParameter("idFiscalizacion");
			long idFiscalizacion = Long.valueOf(paramFiscalizacion).intValue();

			byte[] bytes = reportesFachada.generarReporteFiscalizacion(idFiscalizacion,path);		
			
			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			//response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();				

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteVolumenFiscalizadoPorProducto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx.getBean("reportesFachada");
			
			byte[] bytes = reportesFachada.generarReporteVolumenFiscalizadoPorProducto(path);		
			
			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			//response.setContentLength(baos.size());
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
