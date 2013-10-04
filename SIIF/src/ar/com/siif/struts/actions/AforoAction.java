package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.AforoDTO;
import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IAforoFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.struts.actions.forms.AforoForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

public class AforoAction extends ValidadorAction {

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
	public ActionForward altaAforo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoAltaAforo";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx
					.getBean("aforoFachada");
			AforoForm aforoForm = (AforoForm) form;
			
			// valido nuevamente por seguridad.  
			if (!validarAforoForm(new StringBuffer(), aforoForm)) {
				throw new Exception("Error de Seguridad");
			}			
			
			aforoFachada.altaAforo(aforoForm.getAforoDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_AFORO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

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
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.MODIFICACION_AFORO_MENU,usuario.getRol());

			IAforoFachada aforoFachada = (IAforoFachada) ctx
					.getBean("aforoFachada");

			List<AforoDTO> aforos = aforoFachada.recuperarAforosDTO();
			request.setAttribute("aforos", aforos);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarAforo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoRecuperarAforo";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.MODIFICACION_AFORO_MENU,usuario.getRol());

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");
			IAforoFachada aforoFachada = (IAforoFachada) ctx
					.getBean("aforoFachada");
			ITipoProductoForestalFachada tipoProductoFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			String id = request.getParameter("id");

			AforoDTO aforo = aforoFachada.recuperarAforoDTO(new Long(id)
					.longValue());
			List<TipoProductoForestalDTO> tiposProducto = tipoProductoFachada
					.recuperarTiposProductoForestalDTO();

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("estadosProducto",
					tipoProductoFachada.getEstadosProductos());
			request.setAttribute("tiposProducto", tiposProducto);
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

			AforoForm aforoForm = (AforoForm) form;

			// valido nuevamente por seguridad.  
			if (!validarAforoForm(new StringBuffer(), aforoForm)) {
				throw new Exception("Error de Seguridad");
			}			
			
			aforoFachada.modificacionAforo(aforoForm.getAforoDTO());

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

	public boolean validarAforoForm(StringBuffer error, ActionForm form) {
		try {
			AforoForm aforoForm = (AforoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx
					.getBean("aforoFachada");

			boolean ok = aforoForm.validar(error);
			boolean existe = true;
			if (ok) {
				existe = aforoFachada.existeAforo(aforoForm.getAforoDTO(),
						new Long(aforoForm.getAforoDTO().getTipoProducto()
								.getId()));
				if (existe) {
					Validator.addErrorXML(error, Constantes.EXISTE_AFORO);
				}
			}

			return !existe && ok;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}
}
