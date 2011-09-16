package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.IAforoFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;

import ar.com.siif.struts.actions.forms.AforoForm;
import ar.com.siif.struts.actions.forms.TipoProductoForestalForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class AforoAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaAforo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarAltaAforo";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx.getBean("aforoFachada");

			List<TipoProducto> tiposProducto = aforoFachada.recuperarTiposProducto();

			request.setAttribute("tiposProducto", tiposProducto);

			request.setAttribute("exitoGrabado", request.getAttribute("exitoGrabado"));

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaAforo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoAltaAforo";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx.getBean("aforoFachada");

			AforoForm aforoForm = (AforoForm) form;
			aforoFachada.altaAforo(aforoForm.getAforo(), aforoForm.getIdTipoProductoForestal());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_AFORO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionAforos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarModificacionAforos";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx.getBean("aforoFachada");

			List<Aforo> aforos = aforoFachada.recuperarAforos();
			request.setAttribute("aforos", aforos);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarAforo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarAforo";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx.getBean("aforoFachada");

			String id = request.getParameter("id");

			Aforo aforo = aforoFachada.recuperarAforo(new Long(id).longValue());
			List<TipoProducto> tiposProducto = aforoFachada.recuperarTiposProducto();

			request.setAttribute("tiposProducto", tiposProducto);
			request.getSession().setAttribute("aforoParam", aforo);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionAforo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionAforo";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IAforoFachada aforoFachada = (IAforoFachada) ctx.getBean("aforoFachada");

			AforoForm aforoForm = (AforoForm) form;


			aforoFachada.modificacionAforo(aforoForm.getAforo(),
					aforoForm.getIdTipoProductoForestal());


			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_AFORO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	public boolean validarAforoForm(StringBuffer error, ActionForm form) {
		AforoForm aforoForm = (AforoForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		IAforoFachada aforoFachada = (IAforoFachada) ctx.getBean("aforoFachada");

		boolean existe = aforoFachada.existeAforo(aforoForm.getAforo(),
				new Long(aforoForm.getIdTipoProductoForestal()));

		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_AFORO);
		}

		return !existe && aforoForm.validar(error);
	}
}
