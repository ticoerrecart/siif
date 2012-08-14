package ar.com.siif.struts.actions;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IConsultasPorProductorFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IGuiaForestalFachada;
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.utils.Constantes;

public class ConsultasPorProductorAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarConsultaPorProductores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarConsultaPorProductor";

		try {
			String paramForward = request.getParameter("forward");

			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);						
			WebApplicationContext ctx = getWebApplicationContext();			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			
			if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_VIGENTES)){
				//rolFachada.verificarMenu(Constantes.CONSULTA_GUIA_VIGENTE_MENU,usuario.getRol());
				request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_VIGENTES);
			}
			else{
				if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_NO_VIGENTES)){
					//rolFachada.verificarMenu(Constantes.CONSULTA_GUIA_NO_VIGENTE_MENU,usuario.getRol());
					request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_NO_VIGENTES);
				}
				else{
					if(paramForward.equals(Constantes.METODO_RECUPERAR_GUIAS_CON_DEUDAS_AFORO)){
						//rolFachada.verificarMenu(Constantes.CONSULTA_DEUDA_AFORO_MENU,usuario.getRol());
						request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_AFORO);
					}
					else{
						//rolFachada.verificarMenu(Constantes.CONSULTA_DEUDA_VALE_MENU,usuario.getRol());
						request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_VALE_TRANSPORTE);
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
	

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			List<Entidad> productores = fiscalizacionFachada.recuperarProductores();

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

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
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

			List<GuiaForestal> guiasForestalesVigentes = consultasPorProductorFachada
					.recuperarGuiasForestalesVigentes(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesVigentes);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_VIGENTES);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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

			List<GuiaForestal> guiasForestalesVigentes = consultasPorProductorFachada
					.recuperarGuiasForestalesNoVigentes(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesVigentes);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_NO_VIGENTES);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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

			List<GuiaForestal> guiasForestalesVigentes = consultasPorProductorFachada
					.recuperarGuiasForestalesConDeudasAforo(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesVigentes);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_CON_DEUDAS_AFORO);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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

			List<GuiaForestal> guiasForestalesVigentes = consultasPorProductorFachada
					.recuperarGuiasForestalesConDeudasVales(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestalesVigentes);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_GUIAS_CON_DEUDAS_VALE_TRANSPORTE);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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

			GuiaForestal guiaForestalVigente = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("paramForward", paramForward);
			request.setAttribute("guiaForestal", guiaForestalVigente);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}		
}
