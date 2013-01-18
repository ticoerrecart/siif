package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IGuiaForestalFachada;
import ar.com.siif.fachada.IPeriodoFachada;

public class CertificadoDeOrigenAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward inicializarAltaCertificadoOrigen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strForward = "exitoInicializarAltaCertificadoOrigen";
		try {
			WebApplicationContext ctx = getWebApplicationContext();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");

			request.setAttribute("tiposEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}
	
	@SuppressWarnings("unchecked")						 
	public ActionForward recuperarDatosParaAltaCertificadoOrigen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoRecuperarDatosParaAltaCertificadoOrigen";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
													.getBean("fiscalizacionFachada");

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
													.getBean("guiaForestalFachada");			
			
			String idProductor = request.getParameter("idProductor");
			String periodo = request.getParameter("periodo");
			String idPMF = request.getParameter("idPMF");
			
			List<FiscalizacionDTO> fiscalizaciones = fiscalizacionFachada
											.recuperarFiscalizacionesDTOParaAltaCertificadoOrigen(Long.parseLong(idProductor),periodo,
																							 Long.parseLong(idPMF));

			boolean deuda = guiaForestalFachada.verificarBoletasDepositoVencidasImpagas(Long.parseLong(idProductor));
			
			request.setAttribute("fiscalizaciones", fiscalizaciones);
			request.setAttribute("deuda", deuda);
			//request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}			
}
