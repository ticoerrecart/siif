package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.AforoNuevoDTO;
import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IAforoFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.negocio.AforoNuevo;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.struts.actions.forms.AforoForm;
import ar.com.siif.struts.actions.forms.AforoNuevoForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

public class AforoNuevoAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaAforo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarAltaAforo";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.ALTA_AFORO_MENU,usuario.getRol());

			ITipoProductoForestalFachada tipoProductoFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			List<TipoProductoForestalDTO> tiposProducto = tipoProductoFachada
					.recuperarTiposProductoForestalDTO();

			request.setAttribute("estadosProducto",
					tipoProductoFachada.getEstadosProductos());
			request.setAttribute("tiposProducto", tiposProducto);
			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("exitoGrabado",
					request.getAttribute("exitoGrabado"));

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionAforos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarModificacionAforos";

		try {
			WebApplicationContext ctx = getWebApplicationContext();

			IAforoFachada aforoFachada = (IAforoFachada) ctx
					.getBean("aforoFachada");

			List<AforoNuevoDTO> aforos = aforoFachada
					.recuperarAforosNuevosDTO();
			request.setAttribute("aforos", aforos);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarAforoNuevo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarAforo";

		try {
			WebApplicationContext ctx = getWebApplicationContext();

			IAforoFachada aforoFachada = (IAforoFachada) ctx
					.getBean("aforoFachada");

			String id = request.getParameter("id");

			AforoNuevoDTO aforo = aforoFachada.recuperarAforoNuevoDTO(new Long(
					id).longValue());

			request.getSession().setAttribute("aforoParam", aforo);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionAforo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionAforo";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx
					.getBean("aforoFachada");

			AforoNuevoForm aforoForm = (AforoNuevoForm) form;

			// valido nuevamente por seguridad.
			if (!validarAforoNuevoForm(new StringBuffer(), aforoForm)) {
				throw new Exception("Error de Seguridad");
			}

			aforoFachada.modificacionAforoNuevo(aforoForm.getAforoDTO());

			request.setAttribute("exitoGrabado",
					Constantes.EXITO_MODIFICACION_AFORO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public boolean validarAforoNuevoForm(StringBuffer error, ActionForm form) {
		try {
			boolean ok = true;
			AforoNuevoForm aforoNuevoForm = (AforoNuevoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx
					.getBean("aforoFachada");

			AforoNuevoDTO aforoDTO = aforoNuevoForm.getAforoDTO();
			AforoNuevo aforo = aforoFachada.recuperarAforoNuevo(aforoDTO
					.getId());

			if (aforo.getMonto() != null) {
				if (aforoDTO.getMonto() == null
						|| aforoDTO.getMonto().doubleValue() == 0.0) {
					ok = false;
					Validator.addErrorXML(error, "El monto es requerido");
				}

				if (aforoDTO.getPorcentaje() != null
						&& aforoDTO.getPorcentaje().intValue() > 0) {
					ok = false;
					Validator.addErrorXML(error,
							"Este Aforo no admite porcentaje");
				}
			}

			if (ok && aforo.getPorcentaje() != null) {
				if (aforoDTO.getPorcentaje() == null
						|| aforoDTO.getPorcentaje().intValue() == 0) {
					ok = false;
					Validator.addErrorXML(error, "El porcentaje es requerido");
				}

				if (aforoDTO.getMonto() != null
						&& aforoDTO.getMonto().doubleValue() > 0.0) {
					ok = false;
					Validator.addErrorXML(error, "Este Aforo no admite monto");
				}
			}

			return ok;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}
}
