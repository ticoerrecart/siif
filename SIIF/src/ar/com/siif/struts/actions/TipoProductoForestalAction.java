package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoExportacionDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.AccesoDenegadoException;
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
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_TIPO_PROD_FORESTAL_MENU,usuario.getRol());			
			
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
			tipoProductoForestalFachada.altaTipoProductoForestal(
					tipoProductoForestalForm.getProductoForestalDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_TIPO_PRODUCTO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		/*} catch (AccesoDenegadoException ade) {
			request.setAttribute("error", ade.getMessage());
			strForward = "error";
			*/
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());			
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionTipoProductoForestal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoCargarModificacionTipoProducto";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_TIPO_PROD_FORESTAL_MENU,usuario.getRol());
			
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			List<TipoProductoDTO> tiposProducto = tipoProductoForestalFachada.recuperarTiposProductoForestalDTO();
			request.setAttribute("tiposProducto", tiposProducto);
			request.setAttribute("metodo", "recuperarTipoProductoForestal");

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarTipoProductoForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarTipoProducto";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			String id = request.getParameter("id");

			TipoProductoDTO tipoProducto = tipoProductoForestalFachada
					.recuperarTipoProductoForestalDTO(new Long(id).longValue());
			request.getSession().setAttribute("tipoProducto", tipoProducto);
			request.getSession().setAttribute("metodoValidacion", "validarTipoProductoForestalForm");
			request.getSession().setAttribute("metodo", "modificacionTipoProductoForestal");

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
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
					.getProductoForestalDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_TIPO_PRODUCTO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaTipoProductoExportacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoAltaTipoProductoExportacion";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_TIPO_PROD_FORESTAL_MENU,usuario.getRol());			
			
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
			tipoProductoForestalFachada.altaTipoProductoExportacion(
												tipoProductoForestalForm.getProductoForestalDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_TIPO_PRODUCTO_EXPORTACION);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		/*} catch (AccesoDenegadoException ade) {
			request.setAttribute("error", ade.getMessage());
			strForward = "error";
			*/
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());			
		}

		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionTipoProductoExportacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoCargarModificacionTipoProducto";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
						
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_TIPO_PROD_FORESTAL_MENU,usuario.getRol());
			
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			List<TipoProductoDTO> tiposProducto = tipoProductoForestalFachada.recuperarTiposProductoExportacionDTO();
			request.setAttribute("tiposProducto", tiposProducto);
			request.setAttribute("metodo", "recuperarTipoProductoExportacion");

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward recuperarTipoProductoExportacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarTipoProducto";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
																.getBean("tipoProductoForestalFachada");

			String id = request.getParameter("id");

			TipoProductoDTO tipoProducto = tipoProductoForestalFachada
					.recuperarTipoProductoExportacionDTO(new Long(id).longValue());
			request.getSession().setAttribute("tipoProducto", tipoProducto);
			request.getSession().setAttribute("metodoValidacion", "validarTipoProductoExportacionForm");
			request.getSession().setAttribute("metodo", "modificacionTipoProductoExportacion");

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward modificacionTipoProductoExportacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionTipoProductoExportacion";
		TipoProductoForestalForm tipoProductoForm = null;

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			tipoProductoForm = (TipoProductoForestalForm) form;

			tipoProductoForestalFachada.modificacionTipoProductoExportacion(tipoProductoForm
					.getProductoForestalDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_TIPO_PRODUCTO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());

		}

		return mapping.findForward(strForward);
	}	
	
	public boolean validarTipoProductoExportacionForm(StringBuffer error, ActionForm form) {
		
		TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
				.getBean("tipoProductoForestalFachada");

		boolean existe = tipoProductoForestalFachada.existeTipoProductoExportacion(
				tipoProductoForestalForm.getProductoForestalDTO());

		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_TIPO_PRODUCTO_EXPORTACION);
		}

		return !existe && tipoProductoForestalForm.validar(error);
	}		
	
	public boolean validarTipoProductoForestalForm(StringBuffer error, ActionForm form) {
		
		TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
				.getBean("tipoProductoForestalFachada");

		boolean existe = tipoProductoForestalFachada.existeTipoProductoForestal(
				tipoProductoForestalForm.getProductoForestalDTO());

		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_TIPO_PRODUCTO);
		}

		return !existe && tipoProductoForestalForm.validar(error);
	}
}
