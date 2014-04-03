package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.struts.actions.forms.PeriodoForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

public class PeriodoAction extends ValidadorAction {

	public ActionForward cargarPeriodosAModificar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarPeriodos";
		try {
			WebApplicationContext ctx = getWebApplicationContext();

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			List<PeriodoDTO> periodos = periodoFachada.getPeriodosDTO();
			request.setAttribute("periodos", periodos);

		} catch (Throwable e) {
			MyLogger.logError(e);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward altaPeriodo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoAltaPeriodo";
		try {
			PeriodoForm periodoForm = (PeriodoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();

			// valido nuevamente por seguridad.  
			if (!validarPeriodoForm(new StringBuffer(), periodoForm)) {
				throw new Exception("Error de Seguridad");
			}			
			
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			periodoFachada.altaPeriodo(periodoForm.getPeriodoDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_PERIODO);

		} catch (NegocioException n){
			MyLogger.log(n.getMessage());
			request.setAttribute("error", n.getMessage());
			
		} catch (Throwable e) {
			MyLogger.logError(e);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward cargarPeriodoAModificar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarPeriodoAModificar";
		try {
			WebApplicationContext ctx = getWebApplicationContext();

			String id = request.getParameter("id");
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			request.setAttribute("periodo",
					periodoFachada.getPeriodoDTOPorId(Long.valueOf(id)));
			request.setAttribute("metodo", "modificacionPeriodo");

		} catch (Throwable e) {
			MyLogger.logError(e);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward modificacionPeriodo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionPeriodo";
		try {
			PeriodoForm periodoForm = (PeriodoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			
			// valido nuevamente por seguridad.  
			if (!validarPeriodoForm(new StringBuffer(), periodoForm)) {
				throw new Exception("Error de Seguridad");
			}				
			
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			periodoFachada.modificacionPeriodo(periodoForm.getPeriodoDTO());
			request.setAttribute("exitoGrabado",
					Constantes.EXITO_MODIFICACION_PERIODO);
		} catch (NegocioException n){
			MyLogger.log(n.getMessage());
			request.setAttribute("error", n.getMessage());
			strForward = "bloqueError";
		} catch (Throwable e) {
			MyLogger.logError(e);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	public boolean validarPeriodoForm(StringBuffer error, ActionForm form) {
		try {
			PeriodoForm periodoForm = (PeriodoForm) form;

			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			boolean existe = periodoFachada.existePeriodo(periodoForm
					.getPeriodoDTO());
			if (existe) {
				Validator.addErrorXML(error, Constantes.EXISTE_PERIODO);
			}
			return !existe && periodoForm.validar(error);

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}

	}
}
