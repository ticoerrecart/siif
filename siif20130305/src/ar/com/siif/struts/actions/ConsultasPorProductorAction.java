package ar.com.siif.struts.actions;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IConsultasPorProductorFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IGuiaForestalFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

public class ConsultasPorProductorAction extends ValidadorAction {

	public ActionForward cargarConsultaPorProductores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarConsultaPorProductor";

		try {

			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();			

			if (paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_VIGENTES)) {
				// rolFachada.verificarMenu(Constantes.CONSULTA_GUIA_VIGENTE_MENU,usuario.getRol());
				request.setAttribute("titulo",
						Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_VIGENTES);
			} else {
				if (paramForward
						.equals(Constantes.METODO_RECUPERAR_GUIAS_NO_VIGENTES)) {
					// rolFachada.verificarMenu(Constantes.CONSULTA_GUIA_NO_VIGENTE_MENU,usuario.getRol());
					request.setAttribute(
							"titulo",
							Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_NO_VIGENTES);
				} else {
					if (paramForward
							.equals(Constantes.METODO_RECUPERAR_GUIAS_ANULADAS)) {
						request.setAttribute(
								"titulo",
								Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_ANULADAS);
					} else {
						if (paramForward
								.equals(Constantes.METODO_RECUPERAR_GUIAS_CON_DEUDAS_AFORO)) {
							// rolFachada.verificarMenu(Constantes.CONSULTA_DEUDA_AFORO_MENU,usuario.getRol());
							request.setAttribute(
									"titulo",
									Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_AFORO);
						} else {
							// rolFachada.verificarMenu(Constantes.CONSULTA_DEUDA_VALE_MENU,usuario.getRol());
							request.setAttribute(
									"titulo",
									Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_VALE_TRANSPORTE);
						}
					}
				}
			}
			
			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../consultasPorProductor.do?metodo=" + paramForward);
			//request.setAttribute("paramForward", paramForward);
	
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarProductores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarProductoresGuiasConsultaPorProductor";

		try {

			String paramForward = request.getParameter("forward");
			String paramProd = request.getParameter("idProd");

			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			List<EntidadDTO> productores = entidadFachada.getProductoresDTO();

			request.setAttribute("productores", productores);
			request.setAttribute("paramForward", paramForward);
			request.setAttribute("idProd", paramProd);

			if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_VIGENTES)){
				request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_VIGENTES);
			}
			else{
				if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_NO_VIGENTES)){
					request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_NO_VIGENTES);
				}
				else{
					request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_AFORO);
				}
			}	

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesVigentes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesConsultaPorProductor";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestalesVigentes = consultasPorProductorFachada
					.recuperarGuiasForestalesVigentes(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesVigentes);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_VIGENTES);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesNoVigentes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesConsultaPorProductor";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestalesNoVigentes = consultasPorProductorFachada
					.recuperarGuiasForestalesNoVigentes(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesNoVigentes);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_NO_VIGENTES);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}	
	
	public ActionForward recuperarGuiasForestalesAnuladas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesConsultaPorProductor";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestalesAnuladas = consultasPorProductorFachada
					.recuperarGuiasForestalesAnuladas(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesAnuladas);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_ANULADAS);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesConDeudasAforo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesConDeudasAforo";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestalesDeudaAforo = consultasPorProductorFachada
					.recuperarGuiasForestalesConDeudasAforo(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesDeudaAforo);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_CON_DEUDAS_AFORO);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}	

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesConDeudasValeTransporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesConDeudasValeTransporte";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestalesDeudaVale = consultasPorProductorFachada
					.recuperarGuiasForestalesConDeudasVales(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesDeudaVale);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_CON_DEUDAS_VALE_TRANSPORTE);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestal";

		try {

			String paramForward = request.getParameter("paramForward");	
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			
			/*if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_VIGENTES)){
				rolFachada.verificarMenu(Constantes.CONSULTA_GUIA_VIGENTE_MENU,usuario.getRol());
			}
			else{
				if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_NO_VIGENTES)){
					rolFachada.verificarMenu(Constantes.CONSULTA_GUIA_NO_VIGENTE_MENU,usuario.getRol());
				}
				else{
					if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_CON_DEUDAS_AFORO)){
						rolFachada.verificarMenu(Constantes.CONSULTA_DEUDA_AFORO_MENU,usuario.getRol());
					}
					else{
						rolFachada.verificarMenu(Constantes.CONSULTA_DEUDA_VALE_MENU,usuario.getRol());
					}
				}
			}*/			
			
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestalDTO guiaForestalVigente = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("paramForward", paramForward);
			request.setAttribute("guiaForestal", guiaForestalVigente);
			
			//Uso esta marca para reutilizar la pagina cargarGuiaForestalConsultaPorProductor.jsp
			//Indico a donde tiene que llamar el boton 'Volver'
			request.setAttribute("botonVolver", "javascript:volverConsultaGuia();");			

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}		
}
