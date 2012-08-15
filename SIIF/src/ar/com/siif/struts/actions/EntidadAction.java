package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Obrajero;
import ar.com.siif.negocio.PPF;
import ar.com.siif.negocio.RecursosNaturales;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.struts.actions.forms.EntidadForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class EntidadAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaEntidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "cargarAltaEntidad";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_ENTIDAD_MENU,usuario.getRol());
			
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");			
			
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidad());
			request.setAttribute("localidades", localidadFachada.getLocalidadesDTO());
			request.setAttribute("titulo", Constantes.TITULO_ALTA_ENTIDAD);
			request.setAttribute("metodo", "altaEntidad");
			
		}catch(Exception e){
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaEntidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaEntidad";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			EntidadForm entidadForm = (EntidadForm) form;
			EntidadDTO entidadDTO = entidadForm.getEntidadDTO();
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			entidadFachada.altaEntidad(entidadDTO);

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_ENTIDAD);
		} catch (NegocioException ne) {
			strForward = "errorAltaEntidad";
			request.setAttribute("error", ne.getMessage());
		} catch (Exception e) {
			request.setAttribute("error", e.toString());
		}
		return mapping.findForward(strForward);
	}

	public boolean validarEntidadForm(StringBuffer error, ActionForm form) {
		EntidadForm entidadForm = (EntidadForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
		boolean existe = entidadFachada.existeEntidad(entidadForm.getEntidadDTO().getNombre(),
				entidadForm.getEntidadDTO().getId());
		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_ENTIDAD);
		}
		return !existe && entidadForm.validar(error);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarEntidadesAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoRecuperarEntidades";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_ENTIDAD_MENU,usuario.getRol());
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			List<EntidadDTO> entidades = entidadFachada.getEntidadesDTO();
			request.setAttribute("entidades", entidades);
			
		}catch(Exception e){
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarEntidadAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoCargarEntidadAModificar";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_ENTIDAD_MENU,usuario.getRol());
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
	
			// recupero la entidad de la B.D.
			String id = request.getParameter("id");
			EntidadDTO entidad = entidadFachada.getEntidadDTO(Long.parseLong(id));
			request.setAttribute("entidad", entidad);
	
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			request.setAttribute("localidades", localidadFachada.getLocalidadesDTO());
			request.setAttribute("metodo", "modificacionEntidad");

		}catch(Exception e){
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}	
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionEntidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionEntidad";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			EntidadForm entidadForm = (EntidadForm) form;
			EntidadDTO entidad = entidadForm.getEntidadDTO();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			entidadFachada.modificacionEntidad(entidad);

			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_ENTIDAD);
		} catch (NegocioException ne) {
			strForward = "errorAltaEntidad";
			request.setAttribute("error", ne.getMessage());
		} catch (Exception e) {
			request.setAttribute("error", e.toString());
		}
		return mapping.findForward(strForward);
	}

}