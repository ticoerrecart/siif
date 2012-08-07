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
	
			request.setAttribute("entidades", entidadFachada.getEntidadesDTO());
			request.setAttribute("roles", rolFachada.getRolesDTO());
	
			request.setAttribute("titulo", Constantes.TITULO_ALTA_USUARIO);
			request.setAttribute("metodo", "altaUsuario");
			request.setAttribute("idRolAdministrador", rolFachada.getRolAdministrador().getId());
			
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
		boolean existe = usuarioFachada.existeUsuario(usuarioForm.getUsuario().getNombreUsuario(),
				usuarioForm.getUsuario().getId());
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
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

			Entidad entidad = entidadFachada.getEntidad(Long.parseLong(usuarioForm.getIdEntidad()));
			usuarioForm.getUsuario().setEntidad(entidad);

			Rol rol = rolFachada.getRol(Long.parseLong(usuarioForm.getIdRol()));
			usuarioForm.getUsuario().setRol(rol);
			usuarioForm.getUsuario().setId(null);

			// el Usuario nuevo siempre se crea habilitado
			usuarioForm.getUsuario().setHabilitado(true);
			usuarioFachada.altaUsuario(usuarioForm.getUsuario());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_USUARIO);
		} catch (NegocioException ne) {
			strForward = "errorAltaUsuario";
			request.setAttribute("error", ne.getMessage());
		} catch (Exception e) {
			request.setAttribute("error", e.toString());
		}
		return mapping.findForward(strForward);
	}

	private void cargarUsuarioAModificar(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (id == null) {
			id = String.valueOf(((Usuario) request.getSession().getAttribute("usuario")).getId());
		}
		WebApplicationContext ctx = getWebApplicationContext();
		IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");
		IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
		IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

		Usuario usuario = usuarioFachada.getUsuario(Long.valueOf(id));
		request.setAttribute("usu", usuario);
		request.setAttribute("entidades", entidadFachada.getEntidades());
		request.setAttribute("roles", rolFachada.getRoles());

		request.setAttribute("metodo", "modificacionUsuario");
		request.setAttribute("idRolAdministrador", Constantes.ID_ROL_ADMINISTRADOR);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarUsuarioAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarUsuarioAModificar";
		try{
			cargarUsuarioAModificar(request);
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
						
			if (usuario != null && Constantes.ID_ROL_ADMINISTRADOR == usuario.getRol().getId().longValue()) {
				
				IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");
				List<Usuario> usuarios = usuarioFachada.getUsuarios();
				request.setAttribute("usuarios", usuarios);
			} else {
				cargarUsuarioAModificar(request);
				request.setAttribute("titulo", Constantes.TITULO_MODIFICACION_USUARIO);
				strForward = "exitoCargarUsuarioAModificar";
			}
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
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			UsuarioForm usuarioForm = (UsuarioForm) form;
			usuarioForm.getUsuario().setEntidad(
					entidadFachada.getEntidad(Long.parseLong(usuarioForm.getIdEntidad())));
			usuarioForm.getUsuario().setRol(rolFachada.getRol(Long.parseLong(usuarioForm.getIdRol())));
			usuarioFachada.altaUsuario(usuarioForm.getUsuario());
	
			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_USUARIO);
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
		} catch (Exception e) {
			request.setAttribute("error", e.toString());
		}	
		return mapping.findForward(strForward);
	}
}
