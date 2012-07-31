package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IConsultasFiscalizacionFachada;
import ar.com.siif.fachada.IConsultasPorProductorFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.utils.Constantes;

public class ConsultasFiscalizacionAction extends ValidadorAction {

/*	@SuppressWarnings("unchecked")
	public ActionForward cargarLocalidadesConsultaFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarLocalidadesConsultaFiscalizacion";

		try {
			String paramForward = request.getParameter("forward");	
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			
			if(paramForward.equals(Constantes.METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL)){
				//rolFachada.verificarMenu(Constantes.CONSULTA_FISCALIZACIONES_CON_GUIA_MENU,usuario.getRol());
				request.setAttribute("titulo", Constantes.TITULO_CONSULTA_FISCALIZACIONES_CON_GUIA_FORESTAL);
			}
			else{
				if(paramForward.equals(Constantes.METODO_RECUPERAR_FISCALIZACIONES_SIN_GUIA_FORESTAL)){
					//rolFachada.verificarMenu(Constantes.CONSULTA_FISCALIZACIONES_SIN_GUIA_MENU,usuario.getRol());
					request.setAttribute("titulo", Constantes.TITULO_CONSULTA_FISCALIZACIONES_SIN_GUIA_FORESTAL);
				}
			}	
			
			String paramLoc = request.getParameter("idLoc");
			String paramProd = request.getParameter("idProd");

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");			
				
			List<Localidad> localidades = localidadFachada.getLocalidades();			
			
			request.setAttribute("localidades", localidades);
			request.setAttribute("paramForward", paramForward);
			request.setAttribute("idLoc", paramLoc);
			request.setAttribute("idProd", paramProd);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
*/	
	@SuppressWarnings("unchecked")
	public ActionForward cargarTiposDeEntidadConsultaFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarLocalidadesConsultaFiscalizacion";

		try {
			String paramForward = request.getParameter("forward");	
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			
			if(paramForward.equals(Constantes.METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL)){
				//rolFachada.verificarMenu(Constantes.CONSULTA_FISCALIZACIONES_CON_GUIA_MENU,usuario.getRol());
				request.setAttribute("titulo", Constantes.TITULO_CONSULTA_FISCALIZACIONES_CON_GUIA_FORESTAL);
			}
			else{
				if(paramForward.equals(Constantes.METODO_RECUPERAR_FISCALIZACIONES_SIN_GUIA_FORESTAL)){
					//rolFachada.verificarMenu(Constantes.CONSULTA_FISCALIZACIONES_SIN_GUIA_MENU,usuario.getRol());
					request.setAttribute("titulo", Constantes.TITULO_CONSULTA_FISCALIZACIONES_SIN_GUIA_FORESTAL);
				}
			}	

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidad());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../consultasFiscalizacion.do?metodo=" + paramForward);
			request.setAttribute("paramForward", paramForward);
			

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}		


	@SuppressWarnings("unchecked")
	public ActionForward recuperarFiscalizacionesConGuiaForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarFiscalizacionesConGuiaForestal";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasFiscalizacionFachada consultasFiscalizacionFachada = (IConsultasFiscalizacionFachada) ctx
					.getBean("consultasFiscalizacionFachada");

			String idProductor = request.getParameter("idProductor");

			List<Fiscalizacion> fiscalizacionesConGuia = consultasFiscalizacionFachada
											.recuperarFiscalizacionesConGuiaForestal(Long.parseLong(idProductor));

			request.setAttribute("fiscalizaciones", fiscalizacionesConGuia);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward recuperarFiscalizacionesSinGuiaForestal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarFiscalizacionesSinGuiaForestal";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasFiscalizacionFachada consultasFiscalizacionFachada = (IConsultasFiscalizacionFachada) ctx
					.getBean("consultasFiscalizacionFachada");

			String idProductor = request.getParameter("idProductor");

			List<Fiscalizacion> fiscalizacionesSinGuia = consultasFiscalizacionFachada
											.recuperarFiscalizacionesSinGuiaForestal(Long.parseLong(idProductor));

			request.setAttribute("fiscalizaciones", fiscalizacionesSinGuia);
			request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_FISCALIZACIONES_SIN_GUIA_FORESTAL);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarFiscalizacion";

		try {
			String paramForward = request.getParameter("paramForward");			
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			
			WebApplicationContext ctx = getWebApplicationContext();			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			
			/*if(paramForward.equals(Constantes.METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL)){
				rolFachada.verificarMenu(Constantes.CONSULTA_FISCALIZACIONES_CON_GUIA_MENU,usuario.getRol());											
			}
			else{
				rolFachada.verificarMenu(Constantes.CONSULTA_FISCALIZACIONES_SIN_GUIA_MENU,usuario.getRol());				
			}*/						
			
			IFiscalizacionFachada fiscalizacionFachada = 
								(IFiscalizacionFachada) ctx.getBean("fiscalizacionFachada");
			
			long idFiscalizacion = new Long(request.getParameter("idFiscalizacion"));
			
			Fiscalizacion fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(idFiscalizacion);

			request.setAttribute("fiscalizacion", fiscalizacion);
			request.setAttribute("paramForward", paramForward);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}	
}
