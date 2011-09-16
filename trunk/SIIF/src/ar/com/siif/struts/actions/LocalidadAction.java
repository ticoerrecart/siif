package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.struts.actions.forms.LocalidadForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class LocalidadAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarLocalidadesAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarLocalidades";
		WebApplicationContext ctx = getWebApplicationContext();
		ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
		List<Localidad> localidades = localidadFachada.getLocalidades();
		request.setAttribute("localidades", localidades);
		return mapping.findForward(strForward);
	}

	public boolean validarLocalidadForm(StringBuffer error, ActionForm form) {
		LocalidadForm localidadForm = (LocalidadForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
		boolean existe = localidadFachada.existeLocalidad(localidadForm.getLocalidad().getNombre(),
				localidadForm.getLocalidad().getId());
		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_LOCALIDAD);
		}
		return !existe && localidadForm.validar(error);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaLocalidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoAltaLocalidad";
		LocalidadForm localidadForm = (LocalidadForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
		localidadForm.getLocalidad().setId(null);
		localidadFachada.altaLocalidad(localidadForm.getLocalidad());
		request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_LOCALIDAD);
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarLocalidadAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarLocalidadAModificar";
		String id = request.getParameter("id");
		WebApplicationContext ctx = getWebApplicationContext();
		ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
		request.setAttribute("localidad", localidadFachada.getLocalidadPorId(Long.valueOf(id)));
		request.setAttribute("metodo", "modificacionLocalidad");
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionLocalidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionLocalidad";
		LocalidadForm localidadForm = (LocalidadForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
		localidadFachada.altaLocalidad(localidadForm.getLocalidad());
		request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_LOCALIDAD);
		return mapping.findForward(strForward);
	}
}
