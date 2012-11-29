package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.fachada.IReportesPorProductoFachada;
import ar.com.siif.fachada.IReportesPorProductorFachada;
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
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarReporteVolumenPorProductorEntreFechas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String strForward = "exitoReporteVolumenPorProductorEntreFechas";
		
		try {
			
			//UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			//rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
					
			String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("paramForward", paramForward);
			//request.setAttribute("titulo", Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);	
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteVolumenPorProductorEntreFechas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductoFachada reportesPorProductoFachada = 
								(IReportesPorProductoFachada) ctx.getBean("reportesPorProductoFachada");
			
			String volumen = request.getParameter("volumen");
			String productor = request.getParameter("productor");
			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");
						
			byte[] bytes = reportesPorProductoFachada.generarReporteVolumenPorProductorEntreFechas(path,volumen,productor,fechaDesde,fechaHasta);		
			
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
	public ActionForward cargarReporteVolumenPorProductoPorProductorPorUbicacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String strForward = "exitoReporteVolumenPorProductoPorProductorPorUbicacion";
		
		try {
			
			//UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			//rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");
			
			//String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			//request.setAttribute("paramForward", paramForward);
			//request.setAttribute("titulo", Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);	
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteVolumenPorProductoPorProductorPorUbicacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductoFachada reportesPorProductoFachada = 
								(IReportesPorProductoFachada) ctx.getBean("reportesPorProductoFachada");
			
			String volumen = request.getParameter("volumen");
			String productor = request.getParameter("productor");
			String periodo = request.getParameter("periodo");
			String pmf = request.getParameter("pmf");
			String tranzon = request.getParameter("tranzon");
			String marcacion = request.getParameter("marcacion");			
						
			byte[] bytes = reportesPorProductoFachada.generarReporteVolumenPorProductoPorProductorPorUbicacion(
																	path,volumen,productor,periodo,pmf,tranzon,marcacion);		
			
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
