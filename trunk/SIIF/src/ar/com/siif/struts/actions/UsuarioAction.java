package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.IUsuarioFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.struts.actions.forms.UsuarioForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class UsuarioAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "cargarAltaUsuario";
		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_USUARIO_MENU,usuario.getRol());
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
	
			request.setAttribute("entidades", entidadFachada.getOficinasForestalesDTO());
			request.setAttribute("roles", rolFachada.getRolesDTO());
	
			request.setAttribute("titulo", Constantes.TITULO_ALTA_USUARIO);
			request.setAttribute("metodo", "altaUsuario");
			request.setAttribute("idRolAdministrador", rolFachada.getRolAdministrador().getId());

		} catch (NegocioException e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}

	public boolean validarUsuarioForm(StringBuffer error, ActionForm form) {
		UsuarioForm usuarioForm = (UsuarioForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");
		boolean existe = usuarioFachada.existeUsuario(usuarioForm.getUsuarioDTO().getNombreUsuario(),
				usuarioForm.getUsuarioDTO().getId());
		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_ENTIDAD);
		}
		return !existe && usuarioForm.validar(error);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoAltaUsuario";
		try {
			UsuarioForm usuarioForm = (UsuarioForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");

			// el Usuario nuevo siempre se crea habilitado
			usuarioForm.getUsuarioDTO().setHabilitado(true);
			usuarioFachada.altaUsuario(usuarioForm.getUsuarioDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_USUARIO);
			
		} catch (NegocioException ne) {
			strForward = "errorAltaUsuario";
			request.setAttribute("error", ne.getMessage());
		} catch (Exception e) {
			request.setAttribute("error", e.toString());
		}
		return mapping.findForward(strForward);
	}

	private void cargarUsuarioAModificar(HttpServletRequest request) throws NegocioException {
		String id = request.getParameter("id");
		if (id == null) {
			id = String.valueOf(((UsuarioDTO) request.getSession().getAttribute("usuario")).getId());
		}
		WebApplicationContext ctx = getWebApplicationContext();
		IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");
		IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
		IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

		UsuarioDTO usuario = usuarioFachada.getUsuarioDTO(Long.valueOf(id));
		request.setAttribute("usu", usuario);
		request.setAttribute("entidades", entidadFachada.getOficinasForestalesDTO());
		request.setAttribute("roles", rolFachada.getRolesDTO());

		request.setAttribute("metodo", "modificacionUsuario");
		request.setAttribute("idRolAdministrador", rolFachada.getRolAdministrador().getId());
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarUsuarioAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarUsuarioAModificar";
		try{
			cargarUsuarioAModificar(request);
			
		} catch (NegocioException e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
					
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarUsuariosAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		String strForward = "exitoRecuperarUsuarios";
		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_USUARIO_MENU,usuario.getRol());		
						
			long idAdministrador = rolFachada.getRolAdministrador().getId();
			
			if (usuario != null && idAdministrador == usuario.getRol().getId().longValue()) {
				
				IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");
				List<UsuarioDTO> usuarios = usuarioFachada.getUsuariosDTO();
				request.setAttribute("usuarios", usuarios);
			} else {
				cargarUsuarioAModificar(request);
				request.setAttribute("titulo", Constantes.TITULO_MODIFICACION_USUARIO);
				strForward = "exitoCargarUsuarioAModificar";
			}
			
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
	public ActionForward modificacionUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionUsuario";
		try{
			WebApplicationContext ctx = getWebApplicationContext();
			IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");
			UsuarioForm usuarioForm = (UsuarioForm) form;
			
			usuarioFachada.modificacionUsuario(usuarioForm.getUsuarioDTO());	
			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_USUARIO);
			
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
		} catch (Exception e) {
			request.setAttribute("error", e.toString());
		}	
		return mapping.findForward(strForward);
	}
}
