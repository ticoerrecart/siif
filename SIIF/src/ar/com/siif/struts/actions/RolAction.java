package ar.com.siif.struts.actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.ItemMenuDTO;
import ar.com.siif.dto.RolDTO;
import ar.com.siif.dto.UsuarioDTO;
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
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_ROL_MENU,usuario.getRol());

			List<ItemMenuDTO> menues = rolFachada.recuperarMenuesDTO();
			Collections.sort(menues);

			request.setAttribute("menues", menues);

			//request.setAttribute("exitoGrabado", request.getAttribute("exitoGrabado"));

		} catch (NegocioException e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
			
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
			rolFachada.altaRol(rolForm.getRolDTO(),rolForm.getMenuesDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_ROL);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
			
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
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_ROL_MENU,usuario.getRol());

			List<RolDTO> roles = rolFachada.getRolesDTO();
			request.setAttribute("roles", roles);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
						
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
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_ROL_MENU,usuario.getRol());

			String id = request.getParameter("id");

			RolDTO rol = rolFachada.recuperarRolDTO(new Long(id).longValue());
			List<ItemMenuDTO> menues = rolFachada.recuperarMenuesDTO();
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

			rolFachada.modificacionRol(rolForm.getRolDTO(), rolForm.getMenuesDTO());

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

		boolean existe = rolFachada.existeRol(rolForm.getRolDTO());

		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_ROL);
		}

		return !existe && rolForm.validar(error);
	}
}
