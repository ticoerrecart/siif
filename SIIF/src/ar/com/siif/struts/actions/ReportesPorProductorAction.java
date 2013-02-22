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
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.fachada.IReportesPorProductorFachada;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

public class ReportesPorProductorAction extends ValidadorAction {

	public ActionForward cargarReporteVolumenFiscalizadoTotal(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoReportePorProductorVolumenFiscalizadoTotal";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");

			List<PeriodoDTO> periodos = periodoFachada.getPeriodosDTO();

			request.setAttribute("periodos", periodos);
			request.setAttribute("titulo",
					Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_TOTAL);
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward generarReporteVolumenFiscalizadoTotal(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteVolumenFiscalizadoTotal(path, periodo);

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

	public ActionForward cargarReportePorProductorGeneral(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoReportePorProductorGeneral";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			List<PeriodoDTO> periodos = periodoFachada.getPeriodosDTO();
			String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodos);
			request.setAttribute("paramForward", paramForward);
			// request.setAttribute("titulo",
			// Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);

			// request.setAttribute("validator", paramValidator);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward generarReporteVolumenFiscalizadoPorProductos(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteVolumenFiscalizadoPorProductos(path,
							periodo, new Long(productor));

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

	public ActionForward generarReporteVolumenGFBMontosPagos(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteVolumenGFBMontosPagos(path, periodo,
							new Long(productor));

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

	public ActionForward generarReporteVolumenGFBMontosAdeudados(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteVolumenGFBMontosAdeudados(path, periodo,
							new Long(productor));

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

	public ActionForward generarReporteListaBoletasTotales(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteListaBoletasTotales(path, periodo, new Long(
							productor));

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

	public ActionForward generarReporteListaBoletasPagas(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteListaBoletasPagas(path, periodo, new Long(
							productor));

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

	public ActionForward generarReporteListaBoletasImpagas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteListaBoletasImpagas(path, periodo, new Long(
							productor));

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

	public ActionForward generarReporteListaValesDevueltos(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteListaValesDevueltos(path, periodo, new Long(
							productor));

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

	public ActionForward generarReporteListaValesEnUso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteListaValesEnUso(path, periodo, new Long(
							productor));

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

	public ActionForward generarReporteListaValesTotales(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteListaValesTotales(path, periodo, new Long(
							productor));

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

	public ActionForward cargarReportePorProductorVolumenPorUbicacion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoReportePorProductorVolumenPorUbicacion";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			List<PeriodoDTO> periodos = periodoFachada.getPeriodosDTO();
			String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodos);
			request.setAttribute("paramForward", paramForward);

			// request.setAttribute("validator", paramValidator);
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward generarReporteVolumenPorUbicacion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductorFachada reportesPorProductorFachada = (IReportesPorProductorFachada) ctx
					.getBean("reportesPorProductorFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");
			String pmf = request.getParameter("pmf");
			String tranzon = request.getParameter("tranzon");
			String marcacion = request.getParameter("marcacion");

			byte[] bytes = reportesPorProductorFachada
					.generarReporteVolumenPorUbicacion(path, periodo, new Long(
							productor), new Long(pmf), new Long(tranzon),
							new Long(marcacion));

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
