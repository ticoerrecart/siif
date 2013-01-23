package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.ProvinciaDestinoDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IGuiaForestalFachada;
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.utils.Constantes;

public class CertificadoDeOrigenAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward inicializarAltaCertificadoOrigen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strForward = "exitoInicializarAltaCertificadoOrigen";
		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");

			request.setAttribute("tiposEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("usuarioAlta", usuario);
			
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

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");			
			
			String idProductor = request.getParameter("idProductor");
			String periodo = request.getParameter("periodo");
			String idPMF = request.getParameter("idPMF");
			
			List<FiscalizacionDTO> fiscalizaciones = fiscalizacionFachada
											.recuperarFiscalizacionesDTOParaAltaCertificadoOrigen(Long.parseLong(idProductor),periodo,
																							 Long.parseLong(idPMF));

			boolean deuda = guiaForestalFachada.verificarBoletasDepositoVencidasImpagas(Long.parseLong(idProductor));
			
			List<ProvinciaDestinoDTO> provincias = localidadFachada.getProvinciasDTO();
			
			request.setAttribute("fiscalizaciones", fiscalizaciones);
			request.setAttribute("deuda", deuda);
			request.setAttribute("provincias", provincias);
			//request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}			
}
