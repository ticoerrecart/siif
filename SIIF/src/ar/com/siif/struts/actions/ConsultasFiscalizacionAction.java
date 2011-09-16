package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.fachada.IConsultasFiscalizacionFachada;
import ar.com.siif.fachada.IConsultasPorProductorFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.utils.Constantes;

public class ConsultasFiscalizacionAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarLocalidadesConsultaFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarLocalidadesConsultaFiscalizacion";

		try {
			String paramForward = request.getParameter("forward");
			String paramLoc = request.getParameter("idLoc");
			String paramProd = request.getParameter("idProd");

			WebApplicationContext ctx = getWebApplicationContext();
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");			
				
			List<Localidad> localidades = localidadFachada.getLocalidades();			
			
			request.setAttribute("localidades", localidades);
			request.setAttribute("paramForward", paramForward);
			request.setAttribute("idLoc", paramLoc);
			request.setAttribute("idProd", paramProd);

			if(paramForward.equals(Constantes.METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL)){
				request.setAttribute("titulo", Constantes.TITULO_CONSULTA_FISCALIZACIONES_CON_GUIA_FORESTAL);
			}
			else{
				if(paramForward.equals(Constantes.METODO_RECUPERAR_FISCALIZACIONES_SIN_GUIA_FORESTAL)){
					request.setAttribute("titulo", Constantes.TITULO_CONSULTA_FISCALIZACIONES_SIN_GUIA_FORESTAL);
				}
			/*	else{
					request.setAttribute("titulo", Constantes.TITULO_CONSULTA_GUIAS_FORESTALES_CON_DEUDA_AFORO);
				}*/
			}

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
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
			// strForward = "errorLogin";
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
			// strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarFiscalizacion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = 
								(IFiscalizacionFachada) ctx.getBean("fiscalizacionFachada");

			String paramForward = request.getParameter("paramForward");
			long idFiscalizacion = new Long(request.getParameter("idFiscalizacion"));
			
			Fiscalizacion fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(idFiscalizacion);

			request.setAttribute("fiscalizacion", fiscalizacion);
			request.setAttribute("paramForward", paramForward);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}	
}
