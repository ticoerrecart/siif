package ar.com.siif.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.fachada.IUbicacionFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.struts.actions.forms.FiscalizacionForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

public class FiscalizacionAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargaAltaFiscalizacion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");

			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			List<TipoDeEntidad> tiposEntidad = entidadFachada.getTiposDeEntidadProductores();

			List<TipoProductoForestalDTO> tiposProducto = tipoProductoForestalFachada
					.recuperarTiposProductoForestalDTO();

			List<EntidadDTO> oficinas = entidadFachada.getOficinasForestalesDTO();

			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("tiposProducto", tiposProducto);
			request.setAttribute("tiposEntidad", tiposEntidad);
			request.setAttribute("oficinas", oficinas);
			//request.getSession().setAttribute("fiscalizacion", null);//Por si quedo alguna Fiscalizacion en el session
			request.getSession().setAttribute("fiscalizacionDTO", null);//Por si quedo alguna Fiscalizacion en el session

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	/*	@SuppressWarnings("unchecked")
		public ActionForward recuperarLocalidadesParaFiscalizacionesAModificar(ActionMapping mapping,
				ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception {

			String strForward = "exitoRecuperarLocalidadesParaFiscalizacionesAModificar";

			try {
				Usuario usuario = (Usuario) request.getSession().getAttribute(
						Constantes.USER_LABEL_SESSION);
				WebApplicationContext ctx = getWebApplicationContext();

				IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
				rolFachada.verificarMenu(Constantes.MODIFICACION_FISCALIZACION_MENU, usuario.getRol());

				IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
						.getBean("fiscalizacionFachada");

				ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
						.getBean("localidadFachada");

				String idLocalidad = request.getParameter("idLocalidad");
				String idProductor = request.getParameter("idProductor");

				//List<Fiscalizacion> fiscalizaciones = fiscalizacionFachada.recuperarFiscalizaciones();
				List<Localidad> localidades = localidadFachada.getLocalidades();

				//request.setAttribute("fiscalizaciones", fiscalizaciones);
				request.setAttribute("localidades", localidades);
				request.setAttribute("idLocalidad", idLocalidad);
				request.setAttribute("idProductor", idProductor);

			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				strForward = "error";
			}

			return mapping.findForward(strForward);
		}
	*/

	@SuppressWarnings("unchecked")
	public ActionForward recuperarTiposDeEntidadParaFiscalizacionesAModificar(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarTiposDeEntidadParaFiscalizacionesAModificar";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
				IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../fiscalizacion.do?metodo=recuperarFiscalizacionesAModificar");

			request.setAttribute("titulo", Constantes.TITULO_MODIFICACION_FISCALIZACION);

			String exitoModificacion = request.getParameter("modificacionRealizada");
			if (exitoModificacion != null) {
				request.setAttribute("exitoModificacion",
						Constantes.EXITO_MODIFICACION_FISCALIZACION);
			}
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarFiscalizacionesAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarFiscalizacionesAModificar";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			//String idLocalidad = request.getParameter("idLocalidad");
			String idProductor = request.getParameter("idProductor");

			List<Fiscalizacion> fiscalizaciones = fiscalizacionFachada
					.recuperarFiscalizacionesPorProductor(new Long(idProductor));

			request.setAttribute("fiscalizaciones", fiscalizaciones);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarFiscalizacionAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarFiscalizacionAModificar";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_FISCALIZACION_MENU, usuario.getRol());

			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");

			long idFiscalizacion = new Long(request.getParameter("id"));
			Fiscalizacion fiscalizacion = fiscalizacionFachada
					.recuperarFiscalizacion(idFiscalizacion);

			List<Entidad> productores = entidadFachada.getEntidadesPorLocalidad(fiscalizacion.getLocalizacion().getProductorForestal()
					.getLocalidad().getId());
			List<TipoProductoForestalDTO> tiposProducto = tipoProductoForestalFachada.recuperarTiposProductoForestalDTO();

			List<PMF> pmf = ubicacionFachada.getPMFs(fiscalizacion.getProductorForestal().getId());
			List<Tranzon> tranzones = ubicacionFachada.getTranzonesById(fiscalizacion.getRodal()
					.getMarcacion().getTranzon().getPmf().getId());
			List<Marcacion> marcaciones = ubicacionFachada.getMarcacionesById(fiscalizacion
					.getRodal().getMarcacion().getTranzon().getId());
			List<Rodal> rodales = ubicacionFachada.getRodalesById(fiscalizacion.getRodal()
					.getMarcacion().getId());

			FiscalizacionDTO fiscalizacionDTO = ProviderDTO.getFiscalizacionDTO(fiscalizacion);
			request.getSession().setAttribute("fiscalizacionDTO", fiscalizacionDTO);

			List<EntidadDTO> oficinas = entidadFachada.getOficinasForestalesDTO();
			request.setAttribute("oficinas", oficinas);

			List<TipoDeEntidad> tiposEntidad = entidadFachada.getTiposDeEntidadProductores();
			request.setAttribute("tiposEntidad", tiposEntidad);
			request.setAttribute("productores", productores);
			request.setAttribute("tiposProducto", tiposProducto);
			request.setAttribute("pmfs", pmf);
			request.setAttribute("tranzones", tranzones);
			request.setAttribute("marcaciones", marcaciones);
			request.setAttribute("rodales", rodales);
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());

			request.setAttribute("LENIA", Constantes.LENIA_ID);
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	/**
	 * Modificación de Fiscalización.  Se puede modificar: Fecha, Periodo Forestal, Cantidad de Unidades, Oficina y las muestas.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward modificacionFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionFiscalizacion";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");
			FiscalizacionForm fiscalizacionForm = (FiscalizacionForm) form;
			/*Fiscalizacion fiscalizacion = fiscalizacionFachada
					.recuperarFiscalizacion(fiscalizacionForm.getFiscalizacionDTO().getId());
			fiscalizacionFachada.modificacionFiscalizacion(fiscalizacion);*/
			fiscalizacionFachada.modificacionFiscalizacion(fiscalizacionForm.getFiscalizacionDTO(),
					fiscalizacionForm.getMuestrasDTO());
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward altaFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaFiscalizacion";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);

			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			FiscalizacionForm fiscalizacionForm = (FiscalizacionForm) form;
			FiscalizacionDTO fiscalizacionDTO = fiscalizacionForm.getFiscalizacionDTO();
			fiscalizacionDTO.setUsuario(usuario);

			//Hay que hacer el alta de la Fiscalizacion, con la FiscalizacionDTO y la lista de MuestraDTO.
			//Hay que cambiar el usuario que se guarda en el session por usuarioDTO.
			fiscalizacionFachada.altaFiscalizacion(fiscalizacionDTO,
					fiscalizacionForm.getMuestrasDTO());

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_FISCALIZACION);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarTiposDeEntidadParaFiscalizacionesAAnular(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoRecuperarTiposDeEntidadParaFiscalizacionesAModificar";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../fiscalizacion.do?metodo=recuperarFiscalizacionesAAnular");

			request.setAttribute("titulo", Constantes.TITULO_ANULACION_FISCALIZACION);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarFiscalizacionesAAnular(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarFiscalizacionesAAnular";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			String idProductor = request.getParameter("idProductor");

			List<Fiscalizacion> fiscalizaciones = fiscalizacionFachada
					.recuperarFiscalizacionesAAnularPorProductor(new Long(idProductor));

			request.setAttribute("fiscalizaciones", fiscalizaciones);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	
	//-----------------------------------------------------------------------------//
	//-------------------- Metodo de Validacion de la Fiscalizacion ---------------//
	//-----------------------------------------------------------------------------//
	
	public boolean validarFiscalizacionForm(StringBuffer error, ActionForm form) {
		FiscalizacionForm fiscalizacionForm = (FiscalizacionForm) form;
		FiscalizacionDTO fiscalizacionDTO = fiscalizacionForm.getFiscalizacionDTO();
		
		WebApplicationContext ctx = getWebApplicationContext();
		ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
																.getBean("tipoProductoForestalFachada");		
		TipoProductoForestalDTO tipoProductoForestalDTO = tipoProductoForestalFachada.recuperarTipoProductoForestalDTO(
																			fiscalizacionDTO.getTipoProducto().getId());
		
		//Esto es pq desde la pagina de alta de fiscalizacion, puedo cambiar la cantidad de diametros del tipo de prod,
		//y debo validar en consecuencia de eso y no por la cantidad de diametros que tenga el tipo de producto recuperado
		//de la base.
		tipoProductoForestalDTO.setCantDiametros(fiscalizacionDTO.getTipoProducto().getCantDiametros());
		tipoProductoForestalDTO.replicarDiametro2();
		
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
		boolean ok13 = true;
		boolean ok14 = true;
		boolean ok15 = true;
		boolean ok16 = true;

		ok2 = Validator.validarComboRequerido("-1",
				Long.toString(fiscalizacionDTO.getProductorForestal().getId()),
				"Productor Forestal", error);

		ok5 = Validator.requerido(fiscalizacionDTO.getFecha(), "Fecha", error)
				&& Validator.validarFechaValida(fiscalizacionDTO.getFecha(), "Fecha", error);

		ok3 = Validator.requerido(fiscalizacionDTO.getPeriodoForestal(), "Periodo Forestal", error);

		ok11 = Validator.validarComboRequerido("-1",
				Long.toString(fiscalizacionDTO.getTipoProducto().getId()), "Tipo de Producto",
				error);

		ok9 = Validator.validarDoubleMayorQue(0,
				Double.toString(fiscalizacionDTO.getCantidadMts()), "Cantidad Mts3", error);

		//if (fiscalizacionDTO.getTipoProducto().getId().longValue() != Constantes.LENIA_ID) {
		if (tipoProductoForestalDTO.getCantDiametros() > 0) {
		
			ok8 = Validator.validarEnteroMayorQue(0,
					Integer.toString(fiscalizacionDTO.getCantidadUnidades()), "Cantidad Unidades",
					error);
			ok10 = Validator.validarEnteroMayorQue(0,
					Integer.toString(fiscalizacionDTO.getTamanioMuestra()), "Tamaño de la Muestra",
					error);
			ok16 = Validator.validarMuestras(fiscalizacionForm.getMuestrasDTO(), 
											 tipoProductoForestalDTO,
											 error);			
		}

		ok11 = Validator.validarComboRequerido("-1",
				Long.toString(fiscalizacionDTO.getOficinaAlta().getId()), "Oficina", error);

		/*ok15 = Validator.requerido(fiscalizacionDTO.getLocalizacion(), "Localizacion", error);
		 */
		//VALIDACIONES FISCALIZACION
		return ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10 && ok11 && ok12 && ok13
				&& ok14 && ok15 && ok16;		
	}	
}
