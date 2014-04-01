package ar.com.siif.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.ICaminoFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IUsuarioFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.struts.actions.forms.CaminoForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

public class CaminosAction extends ValidadorAction {

	public ActionForward inicializarAltaCamino(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strForward = "exitoInicializarAltaCamino";
		try {

			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			request.setAttribute("productores", entidadFachada.getProductoresDTO());
			request.setAttribute("usuarioAlta", usuario);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward consultaSaldo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strForward = "consultaSaldoCaminos";
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			request.setAttribute("productores", entidadFachada.getProductores());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	
	public ActionForward altaCamino(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaCamino";

		try {

			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			ICaminoFachada caminoFachada = (ICaminoFachada) ctx.getBean("caminoFachada");
			IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			CaminoForm caminoForm = (CaminoForm) form;

			// valido nuevamente por seguridad.
			if (!validarCaminoForm(new StringBuffer(), caminoForm)) {
				throw new Exception("Error de Seguridad");
			}
			
			Usuario user = usuarioFachada.getUsuario(usuario.getId());
			caminoForm.getCaminoConstruido().setUsuario(user);
			Entidad e = entidadFachada.getEntidad(caminoForm.getIdProductor());
			caminoForm.getCaminoConstruido().setProductor(e);
			caminoFachada.altaCamino(caminoForm.getCaminoConstruido());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_CAMINO);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public boolean validarCaminoForm(StringBuffer error, ActionForm form) {

		try {
			CaminoForm caminoForm = (CaminoForm) form;
			boolean ok = Validator.validarDoubleMayorQue(0.0, String.valueOf(caminoForm.getCaminoConstruido().getCostoDelCamino()),"Costo Del Camino",error);
			boolean ok2 = Validator.validarDoubleMayorQue(0.0,String.valueOf(caminoForm.getCaminoConstruido().getMonto()),"Monto",error);
			
			boolean ok3 = true;
			if (caminoForm.getIdProductor() == null
					|| "".equals(caminoForm.getIdProductor())
					|| caminoForm.getIdProductor() <= 0) {
				Validator.addErrorXML(error, "Productor Forestal es requerido");
				ok3 = false;
			}
			
			return ok && ok2 && ok3;
				
		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}
}
