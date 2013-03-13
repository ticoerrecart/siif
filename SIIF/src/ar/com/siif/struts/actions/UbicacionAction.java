package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IUbicacionFachada;
import ar.com.siif.struts.actions.forms.UbicacionForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.MyLogger;

public class UbicacionAction extends ValidadorAction {

	public ActionForward recuperarUbicacionesParaAlta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "error";
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			List<EntidadDTO> productores = entidadFachada.getProductoresDTO();

			request.setAttribute("productores", productores);
			strForward = "exitoRecuperarUbicacionParaAlta";
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public boolean validarUbicacionForm(StringBuffer error, ActionForm form) {
		UbicacionForm ubicacionForm = (UbicacionForm) form;
		return ubicacionForm.validar(error);
	}

	public ActionForward recuperarProductoresParaModificacionPMF(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarProductoresParaModificacionPMF";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward recuperarUbicacionesParaModificacion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacion";

		try {
			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			request.setAttribute("idProductor", idProductor.toString());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	
	public ActionForward recuperarUbicacionesParaModificacionDeAreas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDeAreas";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx.getBean("ubicacionFachada");

			Long idProductor = Long.valueOf(request.getParameter("idProductor"));

			request.setAttribute("areas", ubicacionFachada.getAreasDTOPorProductor(idProductor));
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}
	
	
	public ActionForward recuperarUbicacionesParaModificacionDeRodales(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDeRodales";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx.getBean("ubicacionFachada");

			Long idProductor = Long.valueOf(request.getParameter("idProductor"));

			request.setAttribute("rodales", ubicacionFachada.getRodalesDTOPorProductor(idProductor));
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward recuperarUbicacionesParaModificacionDeMarcaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDeMarcaciones";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx.getBean("ubicacionFachada");

			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			request.setAttribute("rodales", ubicacionFachada.getRodalesDTOPorProductor(idProductor));
			request.setAttribute("marcaciones", ubicacionFachada.getMarcacionesDTOPorProductor(idProductor));

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward recuperarUbicacionesParaModificacionDeTranzones(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDeTranzones";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx.getBean("ubicacionFachada");

			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			request.setAttribute("rodales", ubicacionFachada.getRodalesDTOPorProductor(idProductor));
			request.setAttribute("marcaciones", ubicacionFachada.getMarcacionesDTOPorProductor(idProductor));
			request.setAttribute("tranzones", ubicacionFachada.getTranzonesDTOPorProductor(idProductor));

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward recuperarUbicacionesParaModificacionDePMFs(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDePMFs";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx.getBean("ubicacionFachada");

			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			request.setAttribute("pmfs", ubicacionFachada.getPMFsDTOPorProductor(idProductor));

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public String validarPMF(String expedientePMF, String nombrePMF, Long idPF) {
		StringBuffer pError = new StringBuffer();
		Validator.requerido(expedientePMF, "Expediente", pError);
		Validator.requerido(nombrePMF, "Nombre", pError);
		return pError.toString();
	}

	public String validarTranzon(String numeroTranzon, String disposicionTranzon, Long idPMF) {
		StringBuffer pError = new StringBuffer();
		Validator.requerido(numeroTranzon, "Número", pError);
		Validator.requerido(disposicionTranzon, "Disposición", pError);
		return pError.toString();
	}

	public String validarMarcacion(String disposicionMarcacion, Long idTranzon) {
		StringBuffer pError = new StringBuffer();
		Validator.requerido(disposicionMarcacion, "Disposición", pError);
		return pError.toString();
	}

	public String validarRodal(String nombreRodal, Long idMarcacion) {
		StringBuffer pError = new StringBuffer();
		Validator.requerido(nombreRodal, "Nombre", pError);
		return pError.toString();
	}

	public String validarArea(String reservaForestalArea, String nombreArea, String disposicionArea, String expedienteArea,  Long idPF) {
		StringBuffer pError = new StringBuffer();
		Validator.requerido(reservaForestalArea, "Reserva Forestal", pError);
		Validator.requerido(nombreArea, "Nombre", pError);
		Validator.requerido(disposicionArea, "Disposicion", pError);
		Validator.requerido(expedienteArea, "Expediente", pError);
		return pError.toString();
	}
}
