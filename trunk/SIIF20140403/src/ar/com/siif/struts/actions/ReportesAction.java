package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IReportesFachada;
import ar.com.siif.negocio.Reporte;
import ar.com.siif.utils.MyLogger;

public class ReportesAction extends ValidadorAction {

	public ActionForward mostrarReporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "exitoMostrarReporte";
		request.setAttribute("paramForward",
				request.getParameter("paramForward"));

		return mapping.findForward(forward);
	}

	public ActionForward pruebaJasper(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = request.getSession().getServletContext()
				.getRealPath("jasper");
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			byte[] bytes = reportesFachada.pruebaJasper(path);

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

	public ActionForward generarReporteGuiaForestal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String idGuia = request.getParameter("idGuia");
			long idGuiaForestal = Long.valueOf(idGuia);

			byte[] bytes = reportesFachada.generarReporteGuiaForestal(
					idGuiaForestal, path);

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

	public ActionForward generarReporteFiscalizacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String paramFiscalizacion = request.getParameter("idFiscalizacion");
			long idFiscalizacion = Long.valueOf(paramFiscalizacion);

			byte[] bytes = reportesFachada.generarReporteFiscalizacion(
					idFiscalizacion, path);

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

	public ActionForward generarReporteCertificadoOrigen(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = request.getSession().getServletContext()
				.getRealPath("jasper");
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String idCertificado = request.getParameter("idCertificado");
			long idCertificadoOrigen = Long.valueOf(idCertificado);

			byte[] bytes = reportesFachada.generarReporteCertificadoOrigen(
					idCertificadoOrigen, path);

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

	public ActionForward generarReporteDetalleGuiasEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");

			byte[] bytes = reportesFachada
					.generarReporteDetalleGuiasEntreFechas(path, fechaDesde,
							fechaHasta);

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

	public ActionForward cargarReporteGuiasForestalesPorProductorEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoReporteGuiasForestalesPorProductorEntreFechas";

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

	public ActionForward generarReporteGuiasForestalesPorProductorEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String productor = request.getParameter("productor");
			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");

			byte[] bytes = reportesFachada
					.generarReporteGuiasForestalesPorProductorEntreFechas(path,
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

	public ActionForward generarReporteVolumenPorTipoProductorEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");

			byte[] bytes = reportesFachada
					.generarReporteVolumenPorTipoProductorEntreFechas(path,
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

	public ActionForward obtenerReportes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = "exitoMostrarReportes";
		WebApplicationContext ctx = getWebApplicationContext();

		IReportesFachada reportesFachada = (IReportesFachada) ctx
				.getBean("reportesFachada");
		List<Reporte> reportes = reportesFachada.obtenerReportes();
		request.setAttribute("reportes", reportes);

		return mapping.findForward(forward);
	}

}
