package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.fachada.IReportesPorProductorFachada;
import ar.com.siif.utils.Constantes;

public class ReportesPorProductorAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarReporteVolumenFiscalizadoTotal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String strForward = "exitoReportePorProductorVolumenFiscalizadoTotal";

		try {
			//String paramForward = request.getParameter("paramForward");
			//String paramValidator = request.getParameter("validator");
			
			//UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			//rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			

			List<PeriodoDTO> periodos = periodoFachada.getPeriodosDTO();			
			
			request.setAttribute("periodos", periodos);
			//request.setAttribute("paramForward", paramForward);
			//request.setAttribute("validator", paramValidator);
			//request.setAttribute("titulo", Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);			

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteVolumenFiscalizadoTotal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteVolumenFiscalizadoTotal(path,periodo);		
			
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
	//public ActionForward cargarReporteVolumenFiscalizadoPorProductos(ActionMapping mapping, ActionForm form,
	public ActionForward cargarReportePorProductorGeneral(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		//String strForward = "exitoReportePorProductorVolumenFiscalizadoPorProductos";
		String strForward = "exitoReportePorProductorGeneral";
		
		try {
			//String paramForward = request.getParameter("paramForward");
			//String paramValidator = request.getParameter("validator");
			
			//UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			//rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			List<PeriodoDTO> periodos = periodoFachada.getPeriodosDTO();			
			String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodos);
			request.setAttribute("paramForward", paramForward);

			//request.setAttribute("paramForward", paramForward);
			//request.setAttribute("validator", paramValidator);
			//request.setAttribute("titulo", Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);			

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteVolumenFiscalizadoPorProductos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteVolumenFiscalizadoPorProductos(path,periodo,new Long(productor));		
			
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
	public ActionForward generarReporteVolumenGFBMontosPagos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteVolumenGFBMontosPagos(path,periodo,new Long(productor));		
			
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
	public ActionForward generarReporteVolumenGFBMontosAdeudados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteVolumenGFBMontosAdeudados(path,periodo,new Long(productor));		
			
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
	public ActionForward generarReporteListaBoletasTotales(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteListaBoletasTotales(path,periodo,new Long(productor));		
			
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
	public ActionForward generarReporteListaBoletasPagas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteListaBoletasPagas(path,periodo,new Long(productor));		
			
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
	public ActionForward generarReporteListaBoletasImpagas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteListaBoletasImpagas(path,periodo,new Long(productor));		
			
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
	public ActionForward generarReporteListaValesDevueltos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteListaValesDevueltos(path,periodo,new Long(productor));		
			
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
	public ActionForward generarReporteListaValesEnUso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteListaValesEnUso(path,periodo,new Long(productor));		
			
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
	public ActionForward generarReporteListaValesTotales(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteListaValesTotales(path,periodo,new Long(productor));		
			
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
	//public ActionForward cargarReporteVolumenFiscalizadoPorProductos(ActionMapping mapping, ActionForm form,
	public ActionForward cargarReportePorProductorVolumenPorUbicacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String strForward = "exitoReportePorProductorVolumenPorUbicacion";
		
		try {
			//String paramForward = request.getParameter("paramForward");
			//String paramValidator = request.getParameter("validator");
			
			//UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			//rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			List<PeriodoDTO> periodos = periodoFachada.getPeriodosDTO();			
			String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodos);
			request.setAttribute("paramForward", paramForward);

			//request.setAttribute("paramForward", paramForward);
			//request.setAttribute("validator", paramValidator);
			//request.setAttribute("titulo", Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);			

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteVolumenPorUbicacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioDTO u = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesPorProductorFachada reportesPorProductorFachada = 
								(IReportesPorProductorFachada) ctx.getBean("reportesPorProductorFachada");
			
			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
			String pmf = request.getParameter("pmf");
			String tranzon = request.getParameter("tranzon");
			String marcacion = request.getParameter("marcacion");
						
			byte[] bytes = reportesPorProductorFachada.generarReporteVolumenPorUbicacion(path,periodo,new Long(productor)
															,new Long(pmf),new Long(tranzon),new Long(marcacion));		
			
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
