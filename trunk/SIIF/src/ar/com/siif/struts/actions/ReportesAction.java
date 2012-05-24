package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.fachada.IReportesFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.AccesoDenegadoException;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

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
			long idGuiaForestal = Long.valueOf(idGuia).intValue();

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
	public ActionForward cargarReporteVolumenFiscalizadoEntreFechas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String strForward = "exitoCargarReporteVolumenFiscalizadoProductorEntreFechas";

		try {
			String paramForward = request.getParameter("paramForward");
			String paramValidator = request.getParameter("validator");
			
			Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			
			rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());
			
			IFiscalizacionFachada fiscalizacionFachada = 
											(IFiscalizacionFachada) ctx.getBean("fiscalizacionFachada");

			List<Entidad> productores = fiscalizacionFachada.recuperarProductores();			
			
			request.setAttribute("productores", productores);
			request.setAttribute("paramForward", paramForward);
			request.setAttribute("validator", paramValidator);
			request.setAttribute("titulo", Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);			

			if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_VIGENTES)){
				request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_VIGENTES);
			}
			/*else{
				if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_NO_VIGENTES)){
					request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_NO_VIGENTES);
				}
				else{
					if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_CON_DEUDAS_AFORO)){
						request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_AFORO);
					}
					else{
						request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_VALE_TRANSPORTE);
					}
				}
			}*/	

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward generarReporteVolumenFiscalizadoPorProductoForestalEntreFecha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Usuario u = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);		
		
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {
	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IReportesFachada reportesFachada = (IReportesFachada) ctx.getBean("reportesFachada");
			
			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");			
			
			byte[] bytes = reportesFachada.generarReporteVolumenFiscalizadoPorProductoForestalFecha(path,fechaDesde,fechaHasta);		
			
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
	public ActionForward generarReporteVolumenFiscalizadoPorProductorEntreFechas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String path = request.getSession().getServletContext().getRealPath("jasper");
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx.getBean("reportesFachada");
			
			String idProd = request.getParameter("idProd");
			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");
			
			byte[] bytes = reportesFachada.generarReporteVolumenFiscalizadoPorProductorYFecha(Long.valueOf(idProd).longValue(),fechaDesde,fechaHasta,path);		
			
			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		return null;
	}			
	
	public String validarReporteVolumenFiscalizadoPorProductorFecha(String idProd,String fechaDesde,String fechaHasta){
		StringBuffer pError = new StringBuffer();
		Validator.requerido(idProd, "Productor", pError);
		Validator.validarFechaMenorA(fechaDesde, fechaHasta, "Fecha Desde", "Fecha Hasta", pError);
	
		return pError.toString();
	}	
	
	public String validarReporteVolumenFiscalizadoProdForestalEntreFechas(String fechaDesde,String fechaHasta){
		StringBuffer pError = new StringBuffer();
		Validator.validarFechaMenorA(fechaDesde, fechaHasta, "Fecha Desde", "Fecha Hasta", pError);
	
		return pError.toString();
	}	
}
