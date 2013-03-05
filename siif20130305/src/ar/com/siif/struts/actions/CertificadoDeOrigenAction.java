package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsx3.chart.CartesianChart;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dao.CertificadoDeOrigenDAO;
import ar.com.siif.dto.CertificadoOrigenDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.ProvinciaDestinoDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.ICertificadoDeOrigenFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IGuiaForestalFachada;
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.struts.actions.forms.CertificadoOrigenForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

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
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
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
			
			ITipoProductoForestalFachada tipoProductoForestalFachada = 
										(ITipoProductoForestalFachada) ctx.getBean("tipoProductoForestalFachada");			
			
			ICertificadoDeOrigenFachada certificadoOrigenFachada = (ICertificadoDeOrigenFachada) ctx
																			.getBean("certificadoDeOrigenFachada");			
			
			String idProductor = request.getParameter("idProductor");
			String periodo = request.getParameter("periodo");
			String idPMF = request.getParameter("idPMF");
			
			List<FiscalizacionDTO> fiscalizaciones = fiscalizacionFachada
											.recuperarFiscalizacionesDTOParaAltaCertificadoOrigen(Long.parseLong(idProductor),periodo,
																							 Long.parseLong(idPMF));

			boolean deuda = guiaForestalFachada.verificarBoletasDepositoVencidasImpagas(Long.parseLong(idProductor));
			
			/*List<GuiaForestalDTO> listaGuiasForestales = 
												guiaForestalFachada.recuperarGuiasParaCertificado(Long.parseLong(idProductor),periodo,
																								  Long.parseLong(idPMF));
			*/
			double volumenExportado = certificadoOrigenFachada.obtenerVolumenExportado(Long.parseLong(idProductor),periodo,
																					   Long.parseLong(idPMF));
			
			List<ProvinciaDestinoDTO> provincias = localidadFachada.getProvinciasDTO();
			
			request.setAttribute("fiscalizaciones", fiscalizaciones);
			request.setAttribute("deuda", deuda);
			request.setAttribute("provincias", provincias);
			request.setAttribute("tiposProductoExportacion", tipoProductoForestalFachada.recuperarTiposProductoExportacionDTO());
			request.setAttribute("volumenExportado", volumenExportado);
			//request.setAttribute("paramForward", Constantes.METODO_RECUPERAR_FISCALIZACIONES_CON_GUIA_FORESTAL);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}
	
	@SuppressWarnings("unchecked")						 
	public ActionForward altaCertificadoOrigen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoAltaCertificadoOrigen";

		try {
			
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			ICertificadoDeOrigenFachada certificadoOrigenFachada = (ICertificadoDeOrigenFachada) ctx
																	.getBean("certificadoDeOrigenFachada");			
			
			CertificadoOrigenForm certificadoOrigenForm = (CertificadoOrigenForm)form;
			certificadoOrigenForm.normalizarListaTiposProducto();
			
			CertificadoOrigenDTO certificadoOrigen = certificadoOrigenForm.getCertificadoOrigenDTO();
			certificadoOrigen.setUsuarioAlta(usuario);
			
			long nroCertificado = certificadoOrigenFachada.altaCertificadoOrigen(certificadoOrigen,
														   certificadoOrigenForm.getTiposProductoEnCertificado());
			
			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_CERTIFICADO_ORIGEN);
			request.setAttribute("nroCertificado", nroCertificado);
			request.setAttribute("volumenExportado", certificadoOrigenForm.getCertificadoOrigenDTO()
																		  .getVolumenTotalTipoProductos());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarProductoresParaConsultaCertificadoOrigen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strForward = "exitoCargarProductoresParaConsultaCertificadoOrigen";
		try {

			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");

			String idTipoDeEntidad = request.getParameter("entidad");
			String idProductor = request.getParameter("productor");			
			String idPMF = request.getParameter("pmf");
			String periodoForestal = request.getParameter("periodoForestal");
			
			request.setAttribute("tiposEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("idPMF", idPMF);
			request.setAttribute("periodoForestal", periodoForestal);			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	

	@SuppressWarnings("unchecked")
	public ActionForward recuperarCertificadosOrigenParaConsulta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strForward = "exitoRecuperarCertificadosOrigenParaConsulta";
		try {

			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			String idProductor = request.getParameter("idProductor");
			String periodo = request.getParameter("periodo");
			String idPmf = request.getParameter("idPmf");
			
			ICertificadoDeOrigenFachada certificadoOrigenFachada = (ICertificadoDeOrigenFachada) ctx
																		.getBean("certificadoDeOrigenFachada");	

			request.setAttribute("certificados", certificadoOrigenFachada.getCertificadosOrigen(Long.valueOf(idProductor),
																								periodo,Long.valueOf(idPmf)));
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}			

		return mapping.findForward(strForward);
	}	

	@SuppressWarnings("unchecked")
	public ActionForward cargarCertificadoOrigenPorNroCertificado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strForward = "exitoCargarCertificadoOrigen";
		try {

			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			CertificadoOrigenForm certificadoOrigenForm = (CertificadoOrigenForm)form;

			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
																		.getBean("tipoProductoForestalFachada");			
			ICertificadoDeOrigenFachada certificadoOrigenFachada = (ICertificadoDeOrigenFachada) ctx
																		.getBean("certificadoDeOrigenFachada");	

			request.setAttribute("tiposProductoExportacion", tipoProductoForestalFachada.recuperarTiposProductoExportacionDTO());			
			request.setAttribute("certificado", certificadoOrigenFachada.recuperarCertificadoOrigenPorNroCertificado(
															certificadoOrigenForm.getCertificadoOrigenDTO().getNroCertificado()));
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}		
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarCertificadoOrigen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String strForward = "exitoCargarCertificadoOrigen";
		try {

			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			String idCertificado = request.getParameter("idCertificado");

			ICertificadoDeOrigenFachada certificadoOrigenFachada = (ICertificadoDeOrigenFachada) ctx
																		.getBean("certificadoDeOrigenFachada");	

			request.setAttribute("certificado", certificadoOrigenFachada.recuperarCertificadoOrigen(Long.valueOf(idCertificado)));
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	public boolean validarCertificadoOrigenForm(StringBuffer error, ActionForm form) {
		
		CertificadoOrigenForm certificadoOrigenForm = (CertificadoOrigenForm)form;
		CertificadoOrigenDTO certificadoDTO = certificadoOrigenForm.getCertificadoOrigenDTO();
		
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
		boolean ok10 = true;
		boolean ok11 = true;
		boolean ok12 = true;
		
		ok = Validator.validarComboRequerido("-1",Long.toString(certificadoDTO.getExportador().getId()), 
											 "Exportador",error);		

		ok1 = Validator.validarComboRequerido("-1",Long.toString(certificadoDTO.getProductor().getId()), 
				 							 "Productor Forestal",error);
		
		ok2 = Validator.validarComboRequerido("-1",Long.toString(certificadoDTO.getPmf().getId()), 
				 							 "Plan de Manejo Forestal",error);
		
		//ok3 = Validator.requerido(certificadoDTO.getReservaForestal(), "Reserva Forestal", error);
		
		//Si el productor y el exportador son distintos tengo que validar los campos NroFactura y Volumen Transferido
		if(certificadoDTO.getExportador().getId().longValue() != certificadoDTO.getProductor().getId().longValue()){
			
			ok4 = Validator.requerido(certificadoDTO.getNroFactura(), "Nro Factura", error);
			
			if(ok4){
				ok4 = Validator.validarDoubleMayorQue(0, certificadoDTO.getNroFactura(),"Nro Factura", error);
			}		
			
			ok5 = Validator.requerido(Double.toString(certificadoDTO.getVolumenTransferido()), "Volúmen Transferido", error);
			
			if(ok5){
				ok5 = Validator.validarDoubleMayorQue(0, Double.toString(certificadoDTO.getVolumenTransferido()),
													 "Volúmen Transferido", error);
			}
		}
			
		ok6 = Validator.requerido(certificadoDTO.getOrigenMateriaPrima(), "Origen Materia Prima", error);
		
		ok7 = Validator.requerido(certificadoDTO.getNroRemito(), "Nro Remito", error);
		
		if(ok7){
			ok7 = Validator.validarDoubleMayorQue(0, certificadoDTO.getNroRemito(),"Nro Remito", error);
		}
		
		certificadoDTO.getLocalidadDestino().setId((certificadoDTO.getLocalidadDestino().getId() == null)
																	?-1:certificadoDTO.getLocalidadDestino().getId());
		
		ok8 = Validator.validarComboRequerido("-1",Long.toString(certificadoDTO.getLocalidadDestino().getId()), 
				 							  "Localdiad Destino",error);		
		
		ok9 = Validator.validarDoubleMayorQue(0, Double.toString(certificadoDTO.getVolumenTotalTipoProductos()),
				 							   "Volúmen Total", error);
		
		ok10 = Validator.requerido(certificadoDTO.getFecha(), "Fecha",error);		
		
		ok11 = Validator.validarVolumenExportacionCertificadoOrigen(certificadoDTO.getVolumenTotalTipoProductos(),
																	certificadoOrigenForm.getVolumenMaximoParaExportar(),
																	error); 
		
		ok12 = Validator.validarDeudaCertificadoOrigen(certificadoOrigenForm.isTieneDeuda(),certificadoOrigenForm.getRadioDeuda(),error);
		
		return ok && ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10 && ok11 && ok12;				
	}

	public boolean validarNroCertificadoForm(StringBuffer error, ActionForm form) {

		try{
			CertificadoOrigenForm certificadoOrigenForm = (CertificadoOrigenForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			ICertificadoDeOrigenFachada certificadoOrigenFachada = (ICertificadoDeOrigenFachada) ctx
																		.getBean("certificadoDeOrigenFachada");
	
			boolean valido = Validator.validarEnteroMayorQue(0,
					Long.toString(certificadoOrigenForm.getCertificadoOrigenDTO().getNroCertificado()), "Nro de Certificado",
					error);
	
			boolean existe = valido;
	
			if (valido) {
				existe = certificadoOrigenFachada.existeCertificado(
													certificadoOrigenForm.getCertificadoOrigenDTO().getNroCertificado());
	
				if (!existe) {
					Validator.addErrorXML(error, Constantes.NO_EXISTE_CERTIFICADO);
				}
			}
	
			return existe;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}			
	}	
}
