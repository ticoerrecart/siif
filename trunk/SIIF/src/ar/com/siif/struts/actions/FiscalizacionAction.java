package ar.com.siif.struts.actions;

import java.util.ArrayList;
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
import ar.com.siif.fachada.ILocalidadFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.fachada.IUbicacionFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.AccesoDenegadoException;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.struts.actions.forms.FiscalizacionForm;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

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
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");
			List<TipoDeEntidad> tiposEntidad = entidadFachada.getTiposDeEntidadProductores();
			
			List<TipoProductoDTO> tiposProducto = tipoProductoForestalFachada.recuperarTiposProductoForestalDTO();

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

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidad());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle", "../../fiscalizacion.do?metodo=recuperarFiscalizacionesAModificar");
			
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
			List<TipoProducto> tiposProducto = tipoProductoForestalFachada.recuperarTiposProductoForestal();
			List<PMF> pmf = ubicacionFachada.getPMFs(fiscalizacion.getProductorForestal().getId());
			List<Tranzon> tranzones = ubicacionFachada.getTranzonesById(fiscalizacion.getRodal()
					.getMarcacion().getTranzon().getPmf().getId());
			List<Marcacion> marcaciones = ubicacionFachada.getMarcacionesById(fiscalizacion
					.getRodal().getMarcacion().getTranzon().getId());
			List<Rodal> rodales = ubicacionFachada.getRodalesById(fiscalizacion.getRodal()
					.getMarcacion().getId());
			
			request.getSession().setAttribute("fiscalizacion", fiscalizacion);
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

	@SuppressWarnings("unchecked")
	public ActionForward modificacionFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionFiscalizacion";

		/*try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_FISCALIZACION_MENU, usuario.getRol());

			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");

			FiscalizacionForm fiscalizacionForm = (FiscalizacionForm) form;
			Fiscalizacion fiscalizacion = fiscalizacionForm.getFiscalizacion();

			fiscalizacion.setProductorForestal(fiscalizacionFachada
					.getProductorForestal(fiscalizacionForm.getIdProductorForestal()));
			fiscalizacion.setTipoProducto(fiscalizacionFachada.getTipoProducto(fiscalizacionForm
					.getIdTipoProductoForestal()));
			fiscalizacion.setFecha(Fecha.stringDDMMAAAAToUtilDate(fiscalizacionForm.getFecha()));			
			fiscalizacion.setRodal(ubicacionFachada.getRodal(fiscalizacionForm.getIdRodal()));
			fiscalizacion.setTamanioMuestra(fiscalizacionForm.getMuestras().size());

			int i = 0;
			for (Muestra muestra : fiscalizacionForm.getMuestras()) {

				if (fiscalizacion.getMuestra().size() <= i) {
					if (muestra.getLargo() > 0.0) {
						muestra.setFiscalizacion(fiscalizacion);
						fiscalizacion.getMuestra().add(muestra);
					}
				} else {
					Muestra muestraAModificar = fiscalizacion.getMuestra().get(i);
					muestraAModificar.setDiametro1(muestra.getDiametro1());
					muestraAModificar.setDiametro2(muestra.getDiametro2());
					muestraAModificar.setLargo(muestra.getLargo());
				}
				i++;
			}
			List<Muestra> muestrasAEliminar = new ArrayList<Muestra>();
			for (int j = fiscalizacion.getMuestra().size() - 1; j >= fiscalizacionForm
					.getMuestras().size(); j--) {

				muestrasAEliminar.add(fiscalizacion.getMuestra().get(j));
				fiscalizacion.getMuestra().set(j, null);
			}

			Usuario usr = fiscalizacion.getUsuario();
			fiscalizacion.setUsuario(null);
			Entidad oficina = fiscalizacion.getOficinaAlta();
			fiscalizacion.setOficinaAlta(null);			
			
			fiscalizacionFachada.modificacionFiscalizacion(fiscalizacion, muestrasAEliminar);

			fiscalizacion.setUsuario(usr);
			fiscalizacion.setOficinaAlta(oficina);			
			
			fiscalizacionFachada.actualizarFiscalizacion(fiscalizacion);
			
			request.setAttribute("exitoModificacion", Constantes.EXITO_MODIFICACION_FISCALIZACION);
			request.getSession().setAttribute("fiscalizacion", null);

		} catch (DataBaseException dbe) {
			request.setAttribute("errorModificacion", dbe.getMessage());
			// strForward = "errorLogin";
		} catch (Exception e) {
			request.setAttribute("errorModificacion", e.getMessage());
			// strForward = "errorLogin";
		}*/

		return mapping.findForward(strForward);
	}

	public ActionForward altaFiscalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaFiscalizacion";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			
			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");
			
			FiscalizacionForm fiscalizacionForm = (FiscalizacionForm) form;
			FiscalizacionDTO fiscalizacionDTO = fiscalizacionForm.getFiscalizacionDTO();
			fiscalizacionDTO.setUsuario(usuario);
			
			/*Fiscalizacion fiscalizacion = fiscalizacionForm.getFiscalizacion();

			Entidad productorForestal = fiscalizacionFachada.getProductorForestal(fiscalizacionForm
					.getIdProductorForestal());
			TipoProducto tipoProducto = fiscalizacionFachada.getTipoProducto(fiscalizacionForm
					.getIdTipoProductoForestal());
			fiscalizacion.setProductorForestal(productorForestal);
			fiscalizacion.setTipoProducto(tipoProducto);
			fiscalizacion.setFecha(Fecha.stringDDMMAAAAToUtilDate(fiscalizacionForm.getFecha()));
			fiscalizacion.setRodal(ubicacionFachada.getRodal(fiscalizacionForm.getIdRodal()));
			fiscalizacion.setUsuario(usuario);
			fiscalizacion.setOficinaAlta(entidadFachada.getEntidad(fiscalizacionForm.getIdOficinaForestal()));
			
			for (Muestra muestra : fiscalizacionForm.getMuestras()) {

				if (muestra.getLargo() > 0.0) {
					muestra.setFiscalizacion(fiscalizacion);
					fiscalizacion.getMuestra().add(muestra);
				}
			}

			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
			fiscalizacionFachada.actualizarFiscalizacion(fiscalizacion);*/
			
			//Hay que hacer el alta de la Fiscalizacion, con la FiscalizacionDTO y la lista de MuestraDTO.
			//Hay que cambiar el usuario que se guarda en el session por usuarioDTO.
			fiscalizacionFachada.altaFiscalizacion(fiscalizacionDTO,fiscalizacionForm.getMuestrasDTO());
			
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
