package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.struts.actions.forms.FiscalizacionForm;
import ar.com.siif.struts.actions.forms.TipoProductoForestalForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class TipoProductoForestalAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward altaTipoProductoForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoAltaTipoProductoForestal";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
			tipoProductoForestalFachada.altaTipoProductoForestal(tipoProductoForestalForm
					.getProductoForestal().getNombre());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_TIPO_PRODUCTO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionTipoProductoForestal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoCargarModificacionTipoProductoForestal";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			List<TipoProducto> tiposProducto = tipoProductoForestalFachada.recuperarTiposProducto();
			request.setAttribute("tiposProducto", tiposProducto);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarTipoProductoForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarTipoProductoForestal";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			String id = request.getParameter("id");

			TipoProducto tipoProducto = tipoProductoForestalFachada
					.recuperarTipoProductoForestal(new Long(id).longValue());
			request.getSession().setAttribute("tipoProducto", tipoProducto);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionTipoProductoForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionTipoProductoForestal";
		TipoProductoForestalForm tipoProductoForm = null;

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			tipoProductoForm = (TipoProductoForestalForm) form;

			tipoProductoForestalFachada.modificacionTipoProductoForestal(tipoProductoForm
					.getProductoForestal());

			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_TIPO_PRODUCTO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	public boolean validarTipoProductoForestalForm(StringBuffer error, ActionForm form) {
		TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
				.getBean("tipoProductoForestalFachada");

		boolean existe = tipoProductoForestalFachada.existeTipoProductoForestal(
				tipoProductoForestalForm.getProductoForestal().getNombre(),
				tipoProductoForestalForm.getProductoForestal().getId());

		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_TIPO_PRODUCTO);
		}

		return !existe && tipoProductoForestalForm.validar(error);
	}
}
