package ar.com.siif.struts.actions;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.fachada.IReportesPorProductoFachada;
import ar.com.siif.utils.MyLogger;

public class ReportesPorProductoAction extends ValidadorAction {

	public ActionForward generarReporteVolumenPorAnioForestal(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductoFachada reportesPorProductoFachada = (IReportesPorProductoFachada) ctx
					.getBean("reportesPorProductoFachada");

			String volumen = request.getParameter("volumen");

			byte[] bytes = reportesPorProductoFachada
					.generarReporteVolumenPorAnioForestal(path, volumen);

			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
		}

		return null;
	}

	public ActionForward cargarReporteVolumenPorProductorEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoReporteVolumenPorProductorEntreFechas";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("paramForward", paramForward);
			// request.setAttribute("titulo",
			// Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward generarReporteVolumenPorProductorEntreFechas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductoFachada reportesPorProductoFachada = (IReportesPorProductoFachada) ctx
					.getBean("reportesPorProductoFachada");

			String volumen = request.getParameter("volumen");
			String productor = request.getParameter("productor");
			String fechaDesde = request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta");

			byte[] bytes = reportesPorProductoFachada
					.generarReporteVolumenPorProductorEntreFechas(path,
							volumen, productor, fechaDesde, fechaHasta);

			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			// response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
		}

		return null;
	}

	public ActionForward cargarReporteVolumenPorProductoPorProductorPorUbicacion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoReporteVolumenPorProductoPorProductorPorUbicacion";

		try {

			// UsuarioDTO usuario =
			// (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			// rolFachada.verificarMenu(Constantes.REPORTE_VOL_FISC_PROD_FECHAS_MENU,usuario.getRol());

			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");

			// String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			// request.setAttribute("paramForward", paramForward);
			// request.setAttribute("titulo",
			// Constantes.TITULO_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_ENTRE_FECHAS);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward generarReporteVolumenPorProductoPorProductorPorUbicacion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesPorProductoFachada reportesPorProductoFachada = (IReportesPorProductoFachada) ctx
					.getBean("reportesPorProductoFachada");

			String volumen = request.getParameter("volumen");
			String productor = request.getParameter("productor");
			String periodo = request.getParameter("periodo");
			String pmf = request.getParameter("pmf");
			String tranzon = request.getParameter("tranzon");
			String marcacion = request.getParameter("marcacion");

			byte[] bytes = reportesPorProductoFachada
					.generarReporteVolumenPorProductoPorProductorPorUbicacion(
							path, volumen, productor, periodo, pmf, tranzon,
							marcacion);

			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			// response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
		}

		return null;
	}
}
