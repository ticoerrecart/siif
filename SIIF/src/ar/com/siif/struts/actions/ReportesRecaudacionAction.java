package ar.com.siif.struts.actions;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.fachada.IReportesRecaudacionFachada;
import ar.com.siif.utils.Constantes;

public class ReportesRecaudacionAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarReporteRecaudacionPorProductorEntreFechas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String strForward = "exitoCargarReporteRecaudacionPorProductorEntreFechas";
		
		try {
			
			//UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			//rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
					
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());	
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteRecaudacionPorProductorEntreFechas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesRecaudacionFachada reportesRecaudacionFachada = 
								(IReportesRecaudacionFachada) ctx.getBean("reportesRecaudacionFachada");
			
			String productor = request.getParameter("productor");
			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");
						
			byte[] bytes = reportesRecaudacionFachada.generarReporteRecaudacionPorProductorEntreFechas(path,productor,fechaDesde,fechaHasta);		
			
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
	public ActionForward cargarReporteRecaudacionPorProductorPorAnioForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String strForward = "exitoCargarReporteRecaudacionPorProductorPorAnioForestal";
		
		try {
			
			//UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			//rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");					
			
			request.setAttribute("periodos",periodoFachada.getPeriodosDTO());			
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());	
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteRecaudacionPorProductorPorAnioForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesRecaudacionFachada reportesRecaudacionFachada = 
								(IReportesRecaudacionFachada) ctx.getBean("reportesRecaudacionFachada");
			
			String productor = request.getParameter("productor");
			String periodo = request.getParameter("periodo");
						
			byte[] bytes = reportesRecaudacionFachada.generarReporteRecaudacionPorProductorPorAnioForestal(path,productor,periodo);		
			
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
