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
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.IUbicacionFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.struts.actions.forms.UbicacionForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class UbicacionAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward recuperarUbicacionesParaAlta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "error";
		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_PMF_MENU,usuario.getRol());
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			List<EntidadDTO> productores = entidadFachada.getProductoresDTO();

			request.setAttribute("productores", productores);
			strForward = "exitoRecuperarUbicacionParaAlta";
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public boolean validarUbicacionForm(StringBuffer error, ActionForm form) {
		UbicacionForm ubicacionForm = (UbicacionForm) form;
		return ubicacionForm.validar(error);
	}

	public ActionForward recuperarProductoresParaModificacionPMF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoRecuperarProductoresParaModificacionPMF";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_PMF_MENU,usuario.getRol());
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			
		} catch (Exception e) {
			strForward = "error";
			request.setAttribute("error", e.getMessage());
		}

		return mapping.findForward(strForward);
	}	
	
	public ActionForward recuperarUbicacionesParaModificacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacion";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_PMF_MENU,usuario.getRol());
			
			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			request.setAttribute("idProductor", idProductor.toString());
			
			/*IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");
			
			
			request.setAttribute("rodales", ubicacionFachada.getRodalesDTOPorProductor(idProductor));
			request.setAttribute("marcaciones", ubicacionFachada.getMarcacionesDTOPorProductor(idProductor));
			request.setAttribute("tranzones", ubicacionFachada.getTranzonesDTOPorProductor(idProductor));
			request.setAttribute("pmfs", ubicacionFachada.getPMFsDTOPorProductor(idProductor));*/			
			
			
			/*request.setAttribute("rodales", ubicacionFachada.getRodales());
			request.setAttribute("marcaciones", ubicacionFachada.getMarcaciones());
			request.setAttribute("tranzones", ubicacionFachada.getTranzones());
			request.setAttribute("pmfs", ubicacionFachada.recuperarPMFs());*/
			
		} catch (Exception e) {
			strForward = "error";
			request.setAttribute("error", e.getMessage());
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarUbicacionesParaModificacionDeRodales(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDeRodales";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");
			
			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			
			request.setAttribute("rodales", ubicacionFachada.getRodalesDTOPorProductor(idProductor));
			//request.setAttribute("rodales", ubicacionFachada.getRodales());
			
		} catch (Exception e) {
			strForward = "error";
			request.setAttribute("error", e.getMessage());
		}
		return mapping.findForward(strForward);
	}

	public ActionForward recuperarUbicacionesParaModificacionDeMarcaciones(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDeMarcaciones";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");
			
			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			request.setAttribute("rodales", ubicacionFachada.getRodalesDTOPorProductor(idProductor));
			request.setAttribute("marcaciones", ubicacionFachada.getMarcacionesDTOPorProductor(idProductor));
			
			//request.setAttribute("rodales", ubicacionFachada.getRodales());
			//request.setAttribute("marcaciones", ubicacionFachada.getMarcaciones());
			
		} catch (Exception e) {
			strForward = "error";
			request.setAttribute("error", e.getMessage());
		}
		return mapping.findForward(strForward);
	}

	public ActionForward recuperarUbicacionesParaModificacionDeTranzones(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDeTranzones";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");
			
			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			request.setAttribute("rodales", ubicacionFachada.getRodalesDTOPorProductor(idProductor));
			request.setAttribute("marcaciones", ubicacionFachada.getMarcacionesDTOPorProductor(idProductor));			
			request.setAttribute("tranzones", ubicacionFachada.getTranzonesDTOPorProductor(idProductor));			
			
			//request.setAttribute("rodales", ubicacionFachada.getRodales());
			//request.setAttribute("marcaciones", ubicacionFachada.getMarcaciones());
			//request.setAttribute("tranzones", ubicacionFachada.getTranzones());
			
		} catch (Exception e) {
			strForward = "error";
			request.setAttribute("error", e.getMessage());
		}
		return mapping.findForward(strForward);
	}

	public ActionForward recuperarUbicacionesParaModificacionDePMFs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String strForward = "exitoRecuperarUbicacionParaModificacionDePMFs";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");
			
			Long idProductor = Long.valueOf(request.getParameter("idProductor"));
			request.setAttribute("pmfs", ubicacionFachada.getPMFsDTOPorProductor(idProductor));			
			
			//request.setAttribute("rodales", ubicacionFachada.getRodales());
			//request.setAttribute("marcaciones", ubicacionFachada.getMarcaciones());
			//request.setAttribute("tranzones", ubicacionFachada.getTranzones());
			//request.setAttribute("pmfs", ubicacionFachada.recuperarPMFsDTO());
			
		} catch (Exception e) {
			strForward = "error";
			request.setAttribute("error", e.getMessage());
		}
		return mapping.findForward(strForward);
	}

	public String validarPMF(String expedientePMF, String nombrePMF, Long idPF){
		StringBuffer pError = new StringBuffer();
		Validator.requerido(expedientePMF, "Expediente", pError);
		Validator.requerido(nombrePMF, "Nombre", pError);
		return pError.toString();
	}
	
	public String validarTranzon(String numeroTranzon, String disposicionTranzon, Long idPMF){
		StringBuffer pError = new StringBuffer();
		Validator.requerido(numeroTranzon, "Número", pError);
		Validator.requerido(disposicionTranzon, "Disposición", pError);
		return pError.toString();
	}

	public String validarMarcacion(String disposicionMarcacion, Long idTranzon){
		StringBuffer pError = new StringBuffer();
		Validator.requerido(disposicionMarcacion, "Disposición", pError);
		return pError.toString();
	}

	public String validarRodal(String nombreRodal, Long idMarcacion){
		StringBuffer pError = new StringBuffer();
		Validator.requerido(nombreRodal, "Nombre", pError);
		return pError.toString();
	}
	

	
}
