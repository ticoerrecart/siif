package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.struts.actions.forms.TipoProductoForestalForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

public class TipoProductoForestalAction extends ValidadorAction {

	public ActionForward altaTipoProductoForestal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoAltaTipoProductoForestal";
		try {
			WebApplicationContext ctx = getWebApplicationContext();

			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");
			TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
			tipoProductoForestalFachada.altaTipoProductoForestal(tipoProductoForestalForm.getTipoProductoForestalDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_TIPO_PRODUCTO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward cargarModificacionTipoProductoForestal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarModificacionTipoProducto";

		try {

			WebApplicationContext ctx = getWebApplicationContext();

			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");

			List<TipoProductoForestalDTO> tiposProducto = tipoProductoForestalFachada.recuperarTiposProductoForestalDTO();
			request.setAttribute("tiposProducto", tiposProducto);
			request.setAttribute("metodo", "recuperarTipoProductoForestal");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarTipoProductoForestal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarTipoProductoForestal";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) 
																			ctx.getBean("tipoProductoForestalFachada");

			String id = request.getParameter("id");

			TipoProductoForestalDTO tipoProductoForestal = tipoProductoForestalFachada.
																recuperarTipoProductoForestalDTO(new Long(id).longValue());
			request.getSession().setAttribute("tipoProducto", tipoProductoForestal);
			request.getSession().setAttribute("metodoValidacion", "validarTipoProductoForestalForm");
			request.getSession().setAttribute("metodo", "modificacionTipoProductoForestal");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward modificacionTipoProductoForestal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionTipoProductoForestal";
		TipoProductoForestalForm tipoProductoForm = null;

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");

			tipoProductoForm = (TipoProductoForestalForm) form;

			tipoProductoForestalFachada.modificacionTipoProductoForestal(tipoProductoForm.getTipoProductoForestalDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_TIPO_PRODUCTO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward altaTipoProductoExportacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoAltaTipoProductoExportacion";

		try {

			WebApplicationContext ctx = getWebApplicationContext();

			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");

			TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
			tipoProductoForestalFachada.altaTipoProductoExportacion(tipoProductoForestalForm.getProductoForestalDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_TIPO_PRODUCTO_EXPORTACION);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward cargarModificacionTipoProductoExportacion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarModificacionTipoProducto";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");

			List<TipoProductoDTO> tiposProducto = tipoProductoForestalFachada.recuperarTiposProductoExportacionDTO();
			request.setAttribute("tiposProducto", tiposProducto);
			request.setAttribute("metodo", "recuperarTipoProductoExportacion");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarTipoProductoExportacion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarTipoProductoExportacion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");

			String id = request.getParameter("id");

			TipoProductoDTO tipoProducto = tipoProductoForestalFachada.recuperarTipoProductoExportacionDTO(new Long(id).longValue());
			request.getSession().setAttribute("tipoProducto", tipoProducto);
			request.getSession().setAttribute("metodoValidacion", "validarTipoProductoExportacionForm");
			request.getSession().setAttribute("metodo", "modificacionTipoProductoExportacion");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward modificacionTipoProductoExportacion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionTipoProductoExportacion";
		TipoProductoForestalForm tipoProductoForm = null;

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");

			tipoProductoForm = (TipoProductoForestalForm) form;
			tipoProductoForestalFachada.modificacionTipoProductoExportacion(tipoProductoForm.getProductoForestalDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_TIPO_PRODUCTO);

		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public boolean validarTipoProductoExportacionForm(StringBuffer error, ActionForm form) {

		TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");

		boolean existe = tipoProductoForestalFachada.existeTipoProductoExportacion(tipoProductoForestalForm.getProductoForestalDTO());

		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_TIPO_PRODUCTO_EXPORTACION);
		}

		return !existe && tipoProductoForestalForm.validar(error);
	}

	public boolean validarTipoProductoForestalForm(StringBuffer error, ActionForm form) {

		TipoProductoForestalForm tipoProductoForestalForm = (TipoProductoForestalForm) form;
		TipoProductoForestalDTO tipoProductoForestalDTO = tipoProductoForestalForm.getTipoProductoForestalDTO();
		WebApplicationContext ctx = getWebApplicationContext();
		ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");

		boolean existe = tipoProductoForestalFachada.existeTipoProductoForestal(tipoProductoForestalDTO);
		boolean ok = true;
		boolean ok1 = true;
		boolean ok2 = true;
		boolean ok3 = true;
		boolean ok4 = true;
		boolean ok5 = true;
		boolean ok6 = true;
		boolean ok7 = true;
		boolean ok8 = true;
		boolean ok9 = true;
		
		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_TIPO_PRODUCTO);
		}

		ok = Validator.requerido(tipoProductoForestalDTO.getNombre(), "Producto Forestal", error);
		
		if(tipoProductoForestalDTO.getCantDiametros() > 0) {

			ok1 = Validator.validarDoubleMayorQue(0,String.valueOf(tipoProductoForestalDTO.getLargoDesde()),"Largo Desde",error);
			ok2 = Validator.validarDoubleMayorQue(0,String.valueOf(tipoProductoForestalDTO.getLargoHasta()),"Largo Hasta",error);
			if(ok1 && ok2){
				ok3 = Validator.validarDoubleMayorQue(tipoProductoForestalDTO.getLargoDesde(),
													  String.valueOf(tipoProductoForestalDTO.getLargoHasta()),"Largo Hasta",error);
			}				
			ok4 = Validator.validarDoubleMayorQue(0,String.valueOf(tipoProductoForestalDTO.getDiam1Desde()),"Diámetro1 Desde",error);
			ok5 = Validator.validarDoubleMayorQue(0,String.valueOf(tipoProductoForestalDTO.getDiam1Hasta()),"Diámetro1 Hasta",error);
			if(ok4 && ok5){
				ok6 = Validator.validarDoubleMayorQue(tipoProductoForestalDTO.getDiam1Desde(),
						  							  String.valueOf(tipoProductoForestalDTO.getDiam1Hasta()),"Diámetro1 Hasta",error);
			}
			
			if(tipoProductoForestalDTO.getCantDiametros() > 1) {
				ok7 = Validator.validarDoubleMayorQue(0,String.valueOf(tipoProductoForestalDTO.getDiam2Desde()),"Diámetro2 Desde",error);
				ok8 = Validator.validarDoubleMayorQue(0,String.valueOf(tipoProductoForestalDTO.getDiam2Hasta()),"Diámetro2 Hasta",error);
				if(ok7 && ok8){
					ok9 = Validator.validarDoubleMayorQue(tipoProductoForestalDTO.getDiam2Desde(),
  														  String.valueOf(tipoProductoForestalDTO.getDiam2Hasta()),"Diámetro2 Hasta",error);
				}	
			}
		}

		
		return !existe && ok && ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9;
	}
}
