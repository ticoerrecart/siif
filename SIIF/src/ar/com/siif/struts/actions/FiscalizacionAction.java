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
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.fachada.IUbicacionFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.struts.actions.forms.FiscalizacionForm;
import ar.com.siif.utils.Constantes;

public class FiscalizacionAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargaAltaFiscalizacion";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_FISCALIZACION_MENU, usuario.getRol());

			ITipoProductoForestalFachada tipoProductoForestalFachada = (ITipoProductoForestalFachada) ctx
					.getBean("tipoProductoForestalFachada");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			List<TipoDeEntidad> tiposEntidad = entidadFachada.getTiposDeEntidadProductores();

			List<TipoProductoDTO> tiposProducto = tipoProductoForestalFachada
					.recuperarTiposProductoForestalDTO();

			List<EntidadDTO> oficinas = entidadFachada.getOficinasForestalesDTO();

			request.setAttribute("tiposProducto", tiposProducto);
			request.setAttribute("tiposEntidad", tiposEntidad);
			request.setAttribute("oficinas", oficinas);
			//request.getSession().setAttribute("fiscalizacion", null);//Por si quedo alguna Fiscalizacion en el session
			request.getSession().setAttribute("fiscalizacionDTO", null);//Por si quedo alguna Fiscalizacion en el session

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_FISCALIZACION_MENU, usuario.getRol());

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../fiscalizacion.do?metodo=recuperarFiscalizacionesAModificar");

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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

			long idFiscalizacion = new Long(request.getParameter("id"));
			Fiscalizacion fiscalizacion = fiscalizacionFachada
					.recuperarFiscalizacion(idFiscalizacion);

			List<Entidad> productores = entidadFachada.getEntidadesPorLocalidad(fiscalizacion
					.getRodal().getMarcacion().getTranzon().getPmf().getProductorForestal()
					.getLocalidad().getId());
			List<TipoProducto> tiposProducto = tipoProductoForestalFachada
					.recuperarTiposProductoForestal();
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

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
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

		} catch (Exception e) {
			request.setAttribute("errorModificacion", e.getMessage());
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

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}

	public boolean validarFiscalizacionForm(StringBuffer error, ActionForm form) {
		FiscalizacionForm fiscalizacionForm = (FiscalizacionForm) form;
		return fiscalizacionForm.validar(error);
	}
}
