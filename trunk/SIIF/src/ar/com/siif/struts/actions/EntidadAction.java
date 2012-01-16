package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

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
			Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			rolFachada.verificarMenu(Constantes.ALTA_ENTIDAD_MENU,usuario.getRol());
			
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			request.setAttribute("localidades", localidadFachada.getLocalidades());
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
			Entidad entidad = null;
			if ("PPF".equalsIgnoreCase(entidadForm.getTipoEntidad())) {
				entidad = new PPF();
			} else {
				if ("OBR".equals(entidadForm.getTipoEntidad())) {
					entidad = new Obrajero();
				} else {
					entidad = new RecursosNaturales();
				}
			}

			// recuperar la localidad y setear el objeto
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");
			entidad.setLocalidad(localidadFachada.getLocalidadPorId(Long.valueOf(entidadForm
					.getIdLocalidad())));

			entidad.setDireccion(entidadForm.getEntidad().getDireccion());
			entidad.setEmail(entidadForm.getEntidad().getEmail());
			entidad.setNombre(entidadForm.getEntidad().getNombre());
			entidad.setTelefono(entidadForm.getEntidad().getTelefono());
			// grabar la entidad
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			entidadFachada.altaEntidad(entidad);

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
		boolean existe = entidadFachada.existeEntidad(entidadForm.getEntidad().getNombre(),
				entidadForm.getEntidad().getId());
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
			Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			rolFachada.verificarMenu(Constantes.MODIFICACION_ENTIDAD_MENU,usuario.getRol());
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			List<Entidad> entidades = entidadFachada.getEntidades();
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
		WebApplicationContext ctx = getWebApplicationContext();
		IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

		// recupero la entidad de la B.D.
		String id = request.getParameter("id");
		Entidad entidad = entidadFachada.getEntidad(Long.parseLong(id));
		//request.getSession().setAttribute("entidad", entidad);
		request.setAttribute("entidad", entidad);

		ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
		request.setAttribute("localidades", localidadFachada.getLocalidades());
		//request.setAttribute("titulo", Constantes.TITULO_MODIFICACION_ENTIDAD);
		request.setAttribute("metodo", "modificacionEntidad");
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionEntidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionEntidad";
		try {
			/*
			 * Entidad entidad = (Entidad) request.getSession().getAttribute(
			 * "entidad");
			 */
			WebApplicationContext ctx = getWebApplicationContext();
			EntidadForm entidadForm = (EntidadForm) form;
			Entidad entidad = entidadForm.getEntidad();

			// recuperar la localidad y setear el objeto
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");
			entidad.setLocalidad(localidadFachada.getLocalidadPorId(Long.valueOf(entidadForm
					.getIdLocalidad())));

			// grabar la entidad
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			entidadFachada.altaEntidad(entidad);

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
