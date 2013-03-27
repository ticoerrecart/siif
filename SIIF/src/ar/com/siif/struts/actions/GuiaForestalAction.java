package ar.com.siif.struts.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FilaTablaVolFiscAsociarDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.LocalizacionDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.fachada.IConsultasPorProductorFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IGuiaForestalFachada;
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.fachada.ILoginFachada;
import ar.com.siif.fachada.IPeriodoFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.fachada.IUbicacionFachada;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Localizacion;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.struts.actions.forms.GuiaForestalForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;
import ar.com.siif.utils.MathUtils;
import ar.com.siif.utils.MyLogger;

//En el altaGuiaForestal.jsp en la function calcularTotales() preguntar antes de todo si 
//el tipoTerreno == Privado, si es asi hacer el calculo q se hace en esa function pero sin poner 
//los subImportes, sino seguir calculando como esta.

public class GuiaForestalAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaGuiaForestalBasica(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargaAltaGuiaForestalBasica";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");
			ITipoProductoForestalFachada tipoProdFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.ALTA_GUIA_FORESTAL_MENU,usuario.getRol());

			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");

			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;
			guiaForm.normalizarListaFiscalizaciones();

			EntidadDTO productorForestal = entidadFachada
					.getEntidadDTO(guiaForm.getGuiaForestal()
							.getProductorForestal().getId());

			// RodalDTO rodal = null;
			LocalizacionDTO localizacion = null;
			// List<PMFDTO> listaPMFs = new ArrayList<PMFDTO>();

			if (!guiaForm.getListaFiscalizaciones().isEmpty()) {
				List<Fiscalizacion> fiscalizaciones = new ArrayList<Fiscalizacion>();
				for (FiscalizacionDTO fiscalizacionDTO : guiaForm
						.getListaFiscalizaciones()) {
					fiscalizaciones.add(fiscalizacionFachada
							.recuperarFiscalizacion(fiscalizacionDTO.getId()));

				}

				localizacion = ProviderDTO
						.getLocalizacionDTO(getLocalizacionMayor(fiscalizaciones));
			}/*
			 * else { listaPMFs =
			 * ubicacionFachada.getPMFsDTO(guiaForm.getGuiaForestal()
			 * .getProductorForestal().getId()); }
			 */

			List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
			HashMap<Long, SubImporteDTO> hashProductosFiscalizados = new HashMap<Long, SubImporteDTO>();
			for (FiscalizacionDTO fiscalizacionDTO : guiaForm
					.getListaFiscalizaciones()) {
				if (fiscalizacionDTO.getId() != 0) {
					FiscalizacionDTO fis = fiscalizacionFachada
							.recuperarFiscalizacionDTO(fiscalizacionDTO.getId());
					listaFiscalizacionesDTO.add(fis);

					// Esto es para mostrar los subImportes
					SubImporteDTO subImporte = hashProductosFiscalizados
							.get(fis.getTipoProducto().getId());
					if (subImporte != null) {
						subImporte.setCantidadMts(subImporte.getCantidadMts()
								+ fis.getCantidadMts());
					} else {
						subImporte = new SubImporteDTO();
						subImporte.setCantidadMts(fis.getCantidadMts());
						subImporte.setTipoProducto(fis.getTipoProducto());
					}
					hashProductosFiscalizados.put(subImporte.getTipoProducto()
							.getId(), subImporte);
				}
			}

			request.setAttribute("localidades",
					localidadFachada.getLocalidadesDTO());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("tiposProductosForestales",
					tipoProdFachada.recuperarTiposProductoForestalDTO());
			request.setAttribute("estadosProductoForestal",
					tipoProdFachada.getEstadosProductos());
			request.setAttribute("productorForestal", productorForestal);
			// request.setAttribute("rodal", rodal);
			request.setAttribute("localizacion", localizacion);
			// request.setAttribute("pmfs", listaPMFs);
			request.setAttribute("fiscalizaciones", listaFiscalizacionesDTO);
			request.setAttribute("subImportes",
					hashProductosFiscalizados.values());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaGuiaForestalBasica(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaGuiaForestalBasica";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			UsuarioDTO usr = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);

			ILoginFachada loginFachada = (ILoginFachada) ctx
					.getBean("loginFachada");
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;
			GuiaForestalDTO guiaForestal = guiaForm.getGuiaForestal();

			guiaForestal.setUsuario(usr);
			String fecha = guiaForm.getFechaVencimiento().trim();
			Date dFecha = null;
			if (fecha != null && !"".equals(fecha)) {
				dFecha = Fecha.stringDDMMAAAAToUtilDate(fecha);
			}
			guiaForestalFachada.altaGuiaForestalBasica(guiaForestal,
					guiaForm.getBoletasDeposito(), guiaForm.getRangos(),
					dFecha, guiaForm.getListaFiscalizaciones(),
					guiaForm.getListaSubImportes());

			request.setAttribute("exitoModificacion",
					Constantes.EXITO_ALTA_GUIA_FORESTAL);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	/*
	 * @SuppressWarnings("unchecked") public ActionForward
	 * recuperarGuiasForestalesPlanDePagos(ActionMapping mapping, ActionForm
	 * form, HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * String strForward = "exitoRecuperarGuiasForestalesPlanDePagos";
	 * 
	 * try {
	 * 
	 * WebApplicationContext ctx = getWebApplicationContext();
	 * IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
	 * .getBean("guiaForestalFachada");
	 * 
	 * List<GuiaForestalDTO> guias = new
	 * ArrayList<GuiaForestalDTO>();//guiaForestalFachada
	 * .recuperarGuiasForestales();
	 * 
	 * // BORRAR // guias.add(guias.get(0)); // guias.add(guias.get(0)); //
	 * guias.add(guias.get(0)); // guias.add(guias.get(0));
	 * 
	 * 
	 * request.setAttribute("guias", guias); request.setAttribute("metodo",
	 * "cargarPlanPagoAModificar"); request.setAttribute("titulo",
	 * "Plan de Pagos de Guias Forestales"); request.setAttribute("label",
	 * "Editar Plan de Pagos");
	 * 
	 * } catch (Exception e) { request.setAttribute("error", e.getMessage()); //
	 * strForward = "errorLogin"; }
	 * 
	 * return mapping.findForward(strForward); }
	 */

	/*
	 * @SuppressWarnings("unchecked") public ActionForward
	 * cargarPlanPagoAModificar(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * String strForward = "exitoCargarPlanPagoAModificar";
	 * 
	 * try {
	 * 
	 * WebApplicationContext ctx = getWebApplicationContext();
	 * IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
	 * .getBean("guiaForestalFachada");
	 * 
	 * long idGuiaForestal = new Long(request.getParameter("id"));
	 * GuiaForestalDTO guia =
	 * guiaForestalFachada.recuperarGuiaForestal(idGuiaForestal);
	 * 
	 * request.setAttribute("guia", guia);
	 * 
	 * } catch (Exception e) { request.setAttribute("error", e.getMessage()); //
	 * strForward = "errorLogin"; }
	 * 
	 * return mapping.findForward(strForward); }
	 */

	/*
	 * @SuppressWarnings("unchecked") public ActionForward
	 * recuperarLocalidadesParaAltaGFB( ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception {
	 * 
	 * String strForward = "exitoRecuperarLocalidadesParaAltaGFB";
	 * 
	 * try { Usuario usuario =
	 * (Usuario)request.getSession().getAttribute(Constantes
	 * .USER_LABEL_SESSION); WebApplicationContext ctx =
	 * getWebApplicationContext();
	 * 
	 * IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
	 * rolFachada
	 * .verificarMenu(Constantes.ALTA_GUIA_FORESTAL_MENU,usuario.getRol());
	 * 
	 * ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
	 * .getBean("localidadFachada");
	 * 
	 * String paramLocalidad = request.getParameter("idLocalidad"); String
	 * idProductor = request.getParameter("idProductor");
	 * 
	 * //List<Fiscalizacion> fiscalizaciones =
	 * fiscalizacionFachada.recuperarFiscalizaciones(); List<Localidad>
	 * localidades = localidadFachada.getLocalidades();
	 * 
	 * //request.setAttribute("fiscalizaciones", fiscalizaciones);
	 * request.setAttribute("localidades", localidades);
	 * request.setAttribute("idLocalidad", paramLocalidad);
	 * request.setAttribute("idProductor", idProductor);
	 * 
	 * } catch (Exception e) { request.setAttribute("error", e.getMessage());
	 * strForward = "error"; }
	 * 
	 * return mapping.findForward(strForward); }
	 */

	@SuppressWarnings("unchecked")
	public ActionForward recuperarTiposDeEntidadParaAltaGFB(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarTiposDeEntidadParaAltaGFB";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.ALTA_GUIA_FORESTAL_MENU,
			// usuario.getRol());

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarFiscalizacionesParaAltaGFB");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarFiscalizacionesParaAltaGFB(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarFiscalizacionesParaAltaGFB";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			String idProductor = request.getParameter("idProductor");
			// String idRodal = request.getParameter("idRodal");

			List<FiscalizacionDTO> fiscalizaciones = fiscalizacionFachada
					.recuperarFiscalizacionesDTOParaAltaGFB(new Long(
							idProductor));

			request.setAttribute("fiscalizaciones", fiscalizaciones);
			request.setAttribute("idLocalidad", idProductor);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarTiposDeEntidadParaModificacionGFB(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarTiposDeEntidadParaModificacionGFB";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.MODIFICACION_GUIA_FORESTAL_MENU,
			// usuario.getRol());

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaModificacionGFB");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaModificacionGFB(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesParaModificacionGFB";

		try {
			// String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			List<GuiaForestalDTO> guiasForestales = guiaForestalFachada
					.recuperarGuiasForestalesPorProductor(Long
							.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			// request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionGuiaForestalBasica(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarModificacionGuiaForestalBasica";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.MODIFICACION_GUIA_FORESTAL_MENU,usuario.getRol());

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");

			GuiaForestalDTO guiaForestal = null;
			String idGuia = request.getParameter("idGuia");
			if (idGuia == null) {
				GuiaForestalDTO guiaForestalDTO = ((GuiaForestalForm) form)
						.getGuiaForestal();
				guiaForestal = guiaForestalFachada
						.recuperarGuiaForestalPorNroGuia(guiaForestalDTO
								.getNroGuia());
			} else {
				guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long
						.parseLong(idGuia));
			}

			request.setAttribute("guiaForestal", guiaForestal);
			request.setAttribute("localidades",
					localidadFachada.getLocalidadesDTO());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionGuiaForestalBasica(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionGuiaForestalBasica";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");
			GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
			UsuarioDTO usr = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			GuiaForestalDTO guiaForestal = guiaForestalForm.getGuiaForestal();
			guiaForestal.setUsuario(usr);
			String fecha = guiaForestalForm.getFechaVencimiento().trim();
			Date dFecha = null;
			if (fecha != null && !"".equals(fecha)) {
				dFecha = Fecha.stringDDMMAAAAToUtilDate(fecha);
			}
			guiaForestalFachada.modificacionGuiaForestalBasica(guiaForestal,
					guiaForestalForm.getRangos(),
					guiaForestalForm.getValesTransporte(), dFecha);

			request.setAttribute("exitoModificacion",
					Constantes.EXITO_MODIFICACION_GUIA_FORESTAL);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarProductoresParaBoletasDeposito(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarProductoresParaBoletaDeposito";
		try {
			String paramForward = request.getParameter("forward");
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);

			WebApplicationContext ctx = getWebApplicationContext();
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

			if (paramForward
					.equals(Constantes.METODO_CARGAR_GUIA_PAGO_BOLETA_DEPOSITO)) {
				// rolFachada.verificarMenu(Constantes.REGISTRAR_PAGO_BOLETA_MENU,usuario.getRol());
				request.setAttribute("titulo",
						"Registrar Pago Boleta de Deposito");
			} else {
				// rolFachada.verificarMenu(Constantes.REEMPLAZAR_BOLETA_MENU,usuario.getRol());
				request.setAttribute("titulo", "Reemplazar Boleta de Deposito");
			}

			String forwardBuscarNroGuia = request
					.getParameter("forwardBuscarNroGuia");
			request.setAttribute("forwardBuscarNroGuia", forwardBuscarNroGuia);

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaBoletaDeposito");
			request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaBoletaDeposito(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesParaBoletaDeposito";

		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestales = consultasPorProductorFachada
					.recuperarGuiasForestalesConDeudasAforo(Long
							.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalPagoBoletaDepositoPorNroGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalPagoBoletaDeposito";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;

			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal()
							.getNroGuia());

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalPagoBoletaDeposito(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalPagoBoletaDeposito";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.REGISTRAR_PAGO_BOLETA_MENU,usuario.getRol());

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalReemBoletaDepositoPorNroGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalReemBoletaDeposito";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;

			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal()
							.getNroGuia());

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward cargarGuiaForestalReemBoletaDeposito(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalReemBoletaDeposito";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.REEMPLAZAR_BOLETA_MENU,usuario.getRol());

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarProductoresParaValeTransporte(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarProductoresParaValeTransporte";
		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

			if (paramForward
					.equals(Constantes.METODO_CARGAR_GUIA_DEVOLUCION_VALE)) {
				// rolFachada.verificarMenu(Constantes.DEVOLUCION_VALE_MENU,usuario.getRol());
				request.setAttribute("titulo",
						"Devolución de Vale de Transporte");
			} else {
				// rolFachada.verificarMenu(Constantes.REEMPLAZAR_VALE_MENU,usuario.getRol());
				request.setAttribute("titulo", "Reemplazar Vale de Transporte");
			}

			String forwardBuscarNroGuia = request
					.getParameter("forwardBuscarNroGuia");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			List<EntidadDTO> productores = entidadFachada.getProductoresDTO();

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");

			request.setAttribute("productores", productores);
			request.setAttribute("forwardBuscarNroGuia", forwardBuscarNroGuia);
			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaValeTransporte");
			request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaValeTransporte(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesParaValeTransporte";

		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestales = consultasPorProductorFachada
					.recuperarGuiasForestalesConDeudasVales(Long
							.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalDevolucionValeTransportePorNroGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalDevolucionValeTransporte";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;

			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal()
							.getNroGuia());

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward cargarGuiaForestalDevolucionValeTransporte(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalDevolucionValeTransporte";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.DEVOLUCION_VALE_MENU,usuario.getRol());

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalReemplazarValeTransportePorNroGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalReemplazarValeTransporte";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;

			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal()
							.getNroGuia());

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward cargarGuiaForestalReemplazarValeTransporte(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalReemplazarValeTransporte";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.REEMPLAZAR_VALE_MENU,usuario.getRol());

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarGuiaAsociarFiscalizacion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiaAsociarFiscalizacion";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			String nroGuia = request.getParameter("nroGuia");
			GuiaForestalDTO guiaForestal = null;
			if (nroGuia == null) {
				GuiaForestalForm guiaForm = (GuiaForestalForm) form;

				guiaForestal = guiaForestalFachada
						.recuperarGuiaForestalPorNroGuia(guiaForm
								.getGuiaForestal().getNroGuia());
			} else {
				guiaForestal = guiaForestalFachada
						.recuperarGuiaForestalPorNroGuia(Integer
								.valueOf(nroGuia));
			}

			String idProductor = guiaForestal.getProductorForestal().getId()
					.toString();
			String idLocalizacion = guiaForestal.getIdLocalizacion();

			List<FilaTablaVolFiscAsociarDTO> tablaVolFiscAsociar = this
					.armarTablaVolumenesFiscalizacionesAAsociar(guiaForestal);

			// La lista de fiscalizaciones a asociar debe contener solo las
			// fiscalizaciones que tengan
			// tipos de productos que esten en los subimportes de la guia.
			List<FiscalizacionDTO> fiscalizaciones = fiscalizacionFachada
					.recuperarFiscalizacionesDTOParaAsociarAGuia(new Long(
							idProductor), new Long(idLocalizacion),
							guiaForestal.getSubImportes(), tablaVolFiscAsociar);

			request.setAttribute("fiscalizaciones", fiscalizaciones);
			request.setAttribute("guiaForestal", guiaForestal);
			request.setAttribute("tablaVolFiscAsociar", tablaVolFiscAsociar);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	private List<FilaTablaVolFiscAsociarDTO> armarTablaVolumenesFiscalizacionesAAsociar(
			GuiaForestalDTO guiaForestal) {

		HashMap<Long, Double> mapVol = new HashMap<Long, Double>();
		List<FilaTablaVolFiscAsociarDTO> tabla = new ArrayList<FilaTablaVolFiscAsociarDTO>();
		List<FiscalizacionDTO> listaFiscalizaciones = guiaForestal
				.getFiscalizaciones();
		List<SubImporteDTO> listaSubImportes = guiaForestal.getSubImportes();

		for (FiscalizacionDTO fiscalizacion : listaFiscalizaciones) {

			double volFisc = fiscalizacion.getCantidadMts();
			Double vol = mapVol.get(fiscalizacion.getTipoProducto().getId());
			if (vol != null) {
				volFisc = volFisc + vol.doubleValue();
			}
			mapVol.put(fiscalizacion.getTipoProducto().getId(),
					MathUtils.round(volFisc, 2));
		}

		for (SubImporteDTO subImporteDTO : listaSubImportes) {

			FilaTablaVolFiscAsociarDTO fila = new FilaTablaVolFiscAsociarDTO();
			fila.setIdTipoProducto(subImporteDTO.getTipoProducto().getId());
			fila.setNombreProducto(subImporteDTO.getTipoProducto().getNombre());
			fila.setVolumenTotalEnGuia(subImporteDTO.getCantidadMts());

			Double volEnFisc = mapVol.get(subImporteDTO.getTipoProducto()
					.getId());
			fila.setVolumenEnFiscalizaciones((volEnFisc == null) ? 0.0
					: volEnFisc);

			double volumenFaltante = fila.getVolumenTotalEnGuia()
					- fila.getVolumenEnFiscalizaciones();

			fila.setVolumenFaltante(MathUtils.round(volumenFaltante, 2));

			tabla.add(fila);
		}

		return tabla;
	}

	public ActionForward asociarFiscalizacionesConGuiasForestales(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoAsociarFiscalizacionesConGuiasForestales";
		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;
			guiaForm.normalizarListaFiscalizaciones();
			guiaForestalFachada.asociarFiscalizacionesConGuiasForestales(
					guiaForm.getGuiaForestal().getId(),
					guiaForm.getListaFiscalizaciones());

			request.setAttribute("exitoAsociacion",
					Constantes.EXITO_MODIFICACION_GUIA_FORESTAL);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarGuiaDesasociarFiscalizacion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiaDesasociarFiscalizacion";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");
			String nroGuia = request.getParameter("nroGuia");
			GuiaForestalDTO guiaForestal = null;
			if (nroGuia == null) {
				GuiaForestalForm guiaForm = (GuiaForestalForm) form;

				guiaForestal = guiaForestalFachada
						.recuperarGuiaForestalPorNroGuia(guiaForm
								.getGuiaForestal().getNroGuia());
			} else {
				guiaForestal = guiaForestalFachada
						.recuperarGuiaForestalPorNroGuia(Integer
								.valueOf(nroGuia));
			}

			List<FilaTablaVolFiscAsociarDTO> tablaVolFiscAsociar = this
					.armarTablaVolumenesFiscalizacionesAAsociar(guiaForestal);

			request.setAttribute("guiaForestal", guiaForestal);
			request.setAttribute("tablaVolFiscAsociar", tablaVolFiscAsociar);
			// request.setAttribute("idTipoDeEntidad", guiaForestal.gete);
			// request.setAttribute("idProductor", arg1);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward desasociarFiscalizacionesConGuiasForestales(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoDesasociarFiscalizacionesConGuiasForestales";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;
			guiaForm.normalizarListaFiscalizaciones();
			guiaForestalFachada.desasociarFiscalizacionesConGuiasForestales(
					guiaForm.getGuiaForestal().getId(),
					guiaForm.getListaFiscalizaciones());

			request.setAttribute("exitoAsociacion",
					Constantes.EXITO_MODIFICACION_GUIA_FORESTAL);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward anularGuiaForestal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoAnularGuiaForestal";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;
			GuiaForestalDTO guiaForestal = guiaForestalFachada
					.recuperarGuiaForestal(guiaForm.getGuiaForestal().getId());
			guiaForestalFachada.anularGuiaForestal(guiaForestal);

			request.getSession().setAttribute("exito",
					Constantes.EXITO_ANULACION_GUIA_FORESTAL);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaAsociarFiscalizaciones(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesParaAsociarFiscalizaciones";

		try {
			// String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			List<GuiaForestalDTO> guiasForestales = guiaForestalFachada
					.recuperarGuiasForestalesPorProductor(Long
							.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			// request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaDesasociarFiscalizaciones(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesParaDesasociarFiscalizaciones";

		try {
			// String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			List<GuiaForestalDTO> guiasForestales = guiaForestalFachada
					.recuperarGuiasForestalesPorProductor(Long
							.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			// request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarProductoresParaAsociarFiscalizacionesAGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarProductoresParaAsociarFiscalizacionesAGuia";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");
			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());//
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);//
			request.setAttribute("idProductor", idProductor);//
			request.setAttribute(
					"urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaAsociarFiscalizaciones");//
			request.setAttribute("paramForward", paramForward);//
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarProductoresParaDesasociarFiscalizacionesAGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarProductoresParaDesasociarFiscalizacionesAGuia";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");
			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			String paramForward = request.getParameter("paramForward");

			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());//
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);//
			request.setAttribute("idProductor", idProductor);//
			request.setAttribute(
					"urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaDesasociarFiscalizaciones");//
			request.setAttribute("paramForward", paramForward);//

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarProductoresParaAnulacionDeGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarProductoresParaAnulacionDeGuia";
		try {
			WebApplicationContext ctx = getWebApplicationContext();

			request.setAttribute("exito",
					request.getSession().getAttribute("exito"));
			request.getSession().setAttribute("exito", null);
			request.setAttribute("titulo", "Anulación de Guia Forestal Básica");

			String forwardBuscarNroGuia = request
					.getParameter("forwardBuscarNroGuia");
			String paramForward = request.getParameter("paramForward");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			List<EntidadDTO> productores = entidadFachada.getProductoresDTO();

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");

			request.setAttribute("productores", productores);
			request.setAttribute("forwardBuscarNroGuia", forwardBuscarNroGuia);
			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());//
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);//
			request.setAttribute("idProductor", idProductor);//
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaAnulacionDeGuia");//
			request.setAttribute("paramForward", paramForward);//
			request.setAttribute("mostrarBusquedaNroGuia", true);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaAnulacionDeGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesParaAnulacionDeGuia";

		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestales = guiaForestalFachada
					.recuperarGuiasForestalesPorProductor(Long
							.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalParaAnulacionDeGuiaPorNroGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalBasicaParaAnulacion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestalDTO guiaForestal = null;
			if (null == idGuia) {
				GuiaForestalForm guiaForm = (GuiaForestalForm) form;
				guiaForestal = guiaForestalFachada
						.recuperarGuiaForestalPorNroGuia(guiaForm
								.getGuiaForestal().getNroGuia());
			} else {
				guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long
						.parseLong(idGuia));
			}

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarProductoresParaRestablecerGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarProductoresParaRestablecerGuia";
		try {
			WebApplicationContext ctx = getWebApplicationContext();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			List<EntidadDTO> productores = entidadFachada.getProductoresDTO();

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");

			request.setAttribute("productores", productores);
			request.setAttribute("tiposDeEntidad",
					entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaRestablecerGuia");
			request.setAttribute("mostrarBusquedaNroGuia", false);
			request.setAttribute("titulo", "Restablecer Guia Forestal Básica");

			// Para cuando se restablece una o unas guias se muestra el mensaje
			// de exito
			String mensaje = request.getParameter("mensaje");
			request.setAttribute("exito", mensaje);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaRestablecerGuia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoRecuperarGuiasForestalesParaRestablecerGuia";

		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestalDTO> guiasForestales = consultasPorProductorFachada
					.recuperarGuiasForestalesAnuladas(Long
							.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			request.setAttribute("paramForward", paramForward);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalParaRestablecer(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarGuiaForestalBasicaParaRestablecer";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestalDTO guiaForestal = null;
			guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long
					.parseLong(idGuia));

			// Uso esta marca para reutilizar la pagina
			// cargarGuiaForestalConsultaPorProductor.jsp
			// Indico a donde tiene que llamar el boton 'Volver'
			request.setAttribute("botonVolver",
					"javascript:volverRestablecerGuia();");

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public boolean validarAltaGuiaForestalBasicaForm(StringBuffer error,
			ActionForm form) {

		try {
			GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

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

			ok = Validator.validarLongMayorQue(0, Long
					.toString(guiaForestalForm.getGuiaForestal().getNroGuia()),
					"Nro de Guía", error);

			if (ok) {
				ok1 = !guiaFachada.existeGuiaForestal(guiaForestalForm
						.getGuiaForestal().getNroGuia());

				if (!ok1) {
					Validator.addErrorXML(error, Constantes.NRO_GUIA_EXISTENTE);
				}
			}

			ok2 = Validator.requerido(guiaForestalForm.getGuiaForestal()
					.getFechaVencimiento(), "Valido Hasta", error);

			ok12 = Validator.validarLocalizacionRequerido(guiaForestalForm
					.getGuiaForestal().getIdLocalizacion(), error);

			ok3 = Validator.validarSubImportes(
					guiaForestalForm.getListaSubImportes(),
					guiaForestalForm.getListaFiscalizaciones(),
					guiaForestalForm.getTipoTerreno(), error);

			ok4 = Validator.validarDoubleMayorQue(0, String
					.valueOf(guiaForestalForm.getGuiaForestal()
							.getImporteTotal()), "Importe Total", error);

			double montoTotal = guiaForestalForm.getGuiaForestal()
					.getImporteTotal();
			ok6 = Validator.validarBoletasDeposito(
					guiaForestalForm.getBoletasDeposito(), montoTotal, error);
			ok7 = Validator.validarRangos(guiaForestalForm.getRangos(), error);

			if (guiaForestalForm.getGuiaForestal().getLocalidad().getId() == null
					|| "".equals(guiaForestalForm.getGuiaForestal()
							.getLocalidad().getId())
					|| guiaForestalForm.getGuiaForestal().getLocalidad()
							.getId() <= 0) {
				Validator.addErrorXML(error, "Localidad es requerida");
				ok9 = false;
			}

			ok10 = Validator.requerido(guiaForestalForm.getGuiaForestal()
					.getFecha(), "Fecha", error);
			if (guiaForestalForm.getRangos().size() > 0) {
				ok5 = Validator.requerido(
						guiaForestalForm.getFechaVencimiento(),
						"Fecha de Vencimiento de Vales de Transporte", error);
			}
			ok11 = Validator.validarFechaValida(
					guiaForestalForm.getFechaVencimiento(),
					"Fecha de Vencimiento de Vales de Transporte", error);

			return ok && ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8
					&& ok9 && ok10 && ok11 && ok12;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}

	private Localizacion getLocalizacionMayor(
			List<Fiscalizacion> fiscalizaciones) {
		Localizacion localizacionMayor = null;
		for (Fiscalizacion fiscalizacion : fiscalizaciones) {
			if (localizacionMayor == null) {
				localizacionMayor = fiscalizacion.getLocalizacion();
			} else {
				if (localizacionMayor
						.estaIncluidoGeograficamenteEn(fiscalizacion
								.getLocalizacion())) {
					localizacionMayor = fiscalizacion.getLocalizacion();
				}
			}
		}
		return localizacionMayor;
	}

	private boolean tienenLocalizacionValida(List<Fiscalizacion> fiscalizaciones) {
		Localizacion localizacionMayor = getLocalizacionMayor(fiscalizaciones);
		for (Fiscalizacion fiscalizacion : fiscalizaciones) {
			if (localizacionMayor == null) {
				localizacionMayor = fiscalizacion.getLocalizacion();
			} else {
				if (localizacionMayor
						.estaIncluidoGeograficamenteEn(fiscalizacion
								.getLocalizacion())) {
					localizacionMayor = fiscalizacion.getLocalizacion();
				}
			}
		}

		if (localizacionMayor != null) {
			for (Fiscalizacion fiscalizacion : fiscalizaciones) {
				if (!fiscalizacion.getLocalizacion().esParteDeLaLocalizacion(
						localizacionMayor)) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean validarFiscalizacionesParaAltaGuiaForestalForm(
			StringBuffer error, ActionForm form) {
		try {
			GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
			guiaForestalForm.normalizarListaFiscalizaciones();

			if (guiaForestalForm.getListaFiscalizaciones().size() > 0) {
				WebApplicationContext ctx = getWebApplicationContext();
				IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
						.getBean("fiscalizacionFachada");

				List<Fiscalizacion> fiscalizaciones = new ArrayList<Fiscalizacion>();
				for (FiscalizacionDTO fiscalizacionDTO : guiaForestalForm
						.getListaFiscalizaciones()) {
					fiscalizaciones.add(fiscalizacionFachada
							.recuperarFiscalizacion(fiscalizacionDTO.getId()));

				}

				if (fiscalizaciones.size() > 1) {
					if (tienenLocalizacionValida(fiscalizaciones)) {
						return true;
					} else {
						Validator
								.addErrorXML(error,
										"Las Fiscalizaciones seleccionadas deben tener la misma Localización");
						return false;

					}
					// }
				}
			}

			return true;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}

	public boolean validarNroGuiaForm(StringBuffer error, ActionForm form) {
		try {
			GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			boolean valido = Validator.validarLongMayorQue(0, Long
					.toString(guiaForestalForm.getGuiaForestal().getNroGuia()),
					"Nro de Guía", error);

			boolean existe = valido;

			if (valido) {
				GuiaForestalDTO guiaForestalDTO;
				guiaForestalDTO = guiaFachada.recuperarGuiaForestalPorNroGuia(
						guiaForestalForm.getGuiaForestal().getNroGuia(), false);
				if (guiaForestalDTO == null) {
					Validator.addErrorXML(error, Constantes.NO_EXISTE_GUIA);
					return false;
				}
				if (guiaForestalDTO.isAnulado()) {
					Validator.addErrorXML(error, Constantes.GUIA_ANULADA);
					return false;
				}
			}
			return existe;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}

	public boolean validarRegistrarPagoCuotaForm(StringBuffer error,
			ActionForm form) {
		GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
		return true;
	}

	public boolean validarAnulacionGuiaForestalForm(StringBuffer error,
			ActionForm form) {
		GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
		return true;
	}

	public boolean validarModificacionGuiaForestalBasicaForm(
			StringBuffer error, ActionForm form) {
		try {
			GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			boolean ok = true;
			boolean ok1 = true;
			boolean ok2 = true;
			boolean ok3 = true;
			boolean ok4 = true;
			boolean ok5 = true;
			boolean ok6 = true;
			GuiaForestalDTO guiaForestal = guiaFachada
					.recuperarGuiaForestal(guiaForestalForm.getGuiaForestal()
							.getId());

			ok = Validator.validarLongMayorQue(0, Long
					.toString(guiaForestalForm.getGuiaForestal().getNroGuia()),
					"Nro de Guía", error);

			if (ok) {
				ok1 = !guiaFachada.existeGuiaForestal(guiaForestalForm
						.getGuiaForestal().getId(), guiaForestalForm
						.getGuiaForestal().getNroGuia());

				if (!ok1) {
					Validator.addErrorXML(error, Constantes.NRO_GUIA_EXISTENTE);
				}
			}

			ok2 = Validator.requerido(guiaForestalForm.getGuiaForestal()
					.getFechaVencimiento(), "Valido Hasta", error);

			if (guiaForestalForm.getRangos().size() > 0) {
				ok6 = Validator.requerido(
						guiaForestalForm.getFechaVencimiento(),
						"Fecha de Vencimiento de Vales de Transporte", error);
				if (ok6) {
					if (guiaForestal.getValesTransporte().size() > 0) {
						ValeTransporteDTO primerVale = guiaForestal
								.getValesTransporte().get(0);

						if (!primerVale.getFechaVencimiento().equalsIgnoreCase(
								guiaForestalForm.getFechaVencimiento())) {
							Validator
									.addErrorXML(
											error,
											"La fecha de vencimiento de los vales de transporte que está ingresando "
													+ guiaForestalForm
															.getFechaVencimiento()
													+ " no coincide con la fecha de los vales de transporte ya ingresados ("
													+ primerVale
															.getFechaVencimiento()
													+ ")");
						}
					}
				}
			}

			ok4 = Validator.validarFechaValida(
					guiaForestalForm.getFechaVencimiento(),
					"Fecha de Vencimiento de Vales de Transporte", error);

			ok5 = Validator.validarRangos(guiaForestalForm.getRangos(),
					guiaForestal.getValesTransporte(), error);

			return ok && ok1 && ok2 && ok3 && ok4 && ok5 && ok6;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}

}
