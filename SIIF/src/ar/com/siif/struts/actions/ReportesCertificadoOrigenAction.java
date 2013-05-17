package ar.com.siif.struts.actions;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IReportesCertificadoOrigenFachada;
import ar.com.siif.utils.MyLogger;

public class ReportesCertificadoOrigenAction extends ValidadorAction {

	public ActionForward cargarReporteCertificadosOrigenPorProductorEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarReporteCertificadosOrigenPorProductorEntreFechas";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}
	
	public ActionForward generarReporteCertificadosOrigenPorProductorEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");
			WebApplicationContext ctx = getWebApplicationContext();

			IReportesCertificadoOrigenFachada reportesCertificadoOrigenFachada = 
					(IReportesCertificadoOrigenFachada) ctx.getBean("reportesCertificadoOrigenFachada");

			String productor = request.getParameter("productor");
			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");

			byte[] bytes = reportesCertificadoOrigenFachada
					.generarReporteCertificadosOrigenPorProductorEntreFechas(path,
							productor, fechaDesde, fechaHasta);

			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			// response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			return mapping.findForward("errorSinMenu");
		}

		return null;
	}	
	
	public ActionForward generarReporteCertificadosOrigenTotalProductoresEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");
			WebApplicationContext ctx = getWebApplicationContext();

			IReportesCertificadoOrigenFachada reportesCertificadoOrigenFachada = 
					(IReportesCertificadoOrigenFachada) ctx.getBean("reportesCertificadoOrigenFachada");

			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");

			byte[] bytes = reportesCertificadoOrigenFachada
					.generarReporteCertificadosOrigenTotalProductoresEntreFechas(path,
							fechaDesde, fechaHasta);

			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			// response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			return mapping.findForward("errorSinMenu");
		}

		return null;
	}	
}
