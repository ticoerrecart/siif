package ar.com.siif.struts.actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.struts.actions.forms.RolForm;
import ar.com.siif.struts.actions.forms.TipoProductoForestalForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class RolAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaRol(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarAltaRol";

		try {
			Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			rolFachada.verificarMenu(Constantes.ALTA_ROL_MENU,usuario.getRol());

			List<ItemMenu> menues = rolFachada.recuperarMenues();
			Collections.sort(menues);

			request.setAttribute("menues", menues);

			//request.setAttribute("exitoGrabado", request.getAttribute("exitoGrabado"));

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaRol(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoAltaRol";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

			RolForm rolForm = (RolForm) form;
			//rolFachada.altaRol(rolForm.getRol(),rolForm.getMenues());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_ROL);

			/*} catch (NegocioException ne) {
				request.setAttribute("error", ne.getMessage());
				*/
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionRol(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarModificacionRol";

		try {
			Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			rolFachada.verificarMenu(Constantes.MODIFICACION_ROL_MENU,usuario.getRol());

			List<Rol> roles = rolFachada.getRoles();
			request.setAttribute("roles", roles);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarRol(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarRol";

		try {
			Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			rolFachada.verificarMenu(Constantes.MODIFICACION_ROL_MENU,usuario.getRol());

			String id = request.getParameter("id");

			Rol rol = rolFachada.recuperarRol(new Long(id).longValue());
			List<ItemMenu> menues = rolFachada.recuperarMenues();
			Collections.sort(menues);

			request.setAttribute("menues", menues);

			request.getSession().setAttribute("rolF", rol);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionRol(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionRol";
		RolForm rolForm = null;

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

			rolForm = (RolForm) form;

			rolFachada.modificacionRol(rolForm.getRol(), rolForm.getMenues());

			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_ROL);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
			//request.setAttribute("idTipoProductoError", tipoProductoForm.getProductoForestal().getId());

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}
		return mapping.findForward(strForward);
	}

	public boolean validarRolForm(StringBuffer error, ActionForm form) {

		WebApplicationContext ctx = getWebApplicationContext();
		IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
		RolForm rolForm = (RolForm) form;

		boolean existe = rolFachada.existeRol(rolForm.getRol());

		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_ROL);
		}

		return !existe && rolForm.validar(error);
	}
}
