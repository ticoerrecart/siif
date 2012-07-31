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
import ar.com.siif.fachada.ILoginFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.struts.actions.forms.GuiaForestalForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class GuiaForestalAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaGuiaForestalBasica(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargaAltaGuiaForestalBasica";
		String idFiscalizacion = request.getParameter("id");
		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			//rolFachada.verificarMenu(Constantes.ALTA_GUIA_FORESTAL_MENU,usuario.getRol());
			
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");
			Fiscalizacion actaMartillado = fiscalizacionFachada.recuperarFiscalizacion(Long
					.parseLong(idFiscalizacion));
			request.setAttribute("fiscalizacion", actaMartillado);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaGuiaForestalBasica(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaGuiaForestalBasica";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			UsuarioDTO usr = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);

			ILoginFachada loginFachada = (ILoginFachada) ctx.getBean("loginFachada");
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			Usuario usuario = loginFachada.getUsuario(usr.getId());
			GuiaForestalForm guiaForm = (GuiaForestalForm) form;
			GuiaForestal guiaForestal = guiaForm.getGuiaForestal();

			Fiscalizacion fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(guiaForm
					.getIdFiscalizacion());

			guiaForestal.setFiscalizacion(fiscalizacion);
			fiscalizacion.setGuiaForestal(guiaForestal);

			for (BoletaDeposito boleta : guiaForm.getBoletasDeposito()) {

				boleta.setGuiaForestal(guiaForestal);
				guiaForestal.getBoletasDeposito().add(boleta);
			}
			for (ValeTransporte vale : guiaForm.getValesTransporte()) {

				vale.setGuiaForestal(guiaForestal);
				guiaForestal.getValesTransporte().add(vale);
			}

			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);

			//guiaForestalFachada.altaGuiaForestalBasica(guiaForestal);

			//guiaForestalFachada.altaGuiaForestalBasica(ProviderDominio.getGuiaForestal(guiaForm,
			//		actaMartillado, usuario));

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionGuiaForestalBasica(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionGuiaForestalBasica";

		try {
			GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
			request.setAttribute("exitoModificacion", Constantes.EXITO_MODIFICACION_GUIA_FORESTAL);

		} catch (Exception e) {
			request.setAttribute("errorModificacion", e.getMessage());
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesPlanDePagos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoRecuperarGuiasForestalesPlanDePagos";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			List<GuiaForestal> guias = guiaForestalFachada.recuperarGuiasForestales();

			/*BORRAR
			guias.add(guias.get(0));
			guias.add(guias.get(0));
			guias.add(guias.get(0));
			guias.add(guias.get(0));
			*/

			request.setAttribute("guias", guias);
			request.setAttribute("metodo", "cargarPlanPagoAModificar");
			request.setAttribute("titulo", "Plan de Pagos de Guias Forestales");
			request.setAttribute("label", "Editar Plan de Pagos");

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarPlanPagoAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarPlanPagoAModificar";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			long idGuiaForestal = new Long(request.getParameter("id"));
			GuiaForestal guia = guiaForestalFachada.recuperarGuiaForestal(idGuiaForestal);

			request.setAttribute("guia", guia);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}

	/*	@SuppressWarnings("unchecked")
		public ActionForward recuperarLocalidadesParaAltaGFB(
										ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response) 
										throws Exception {

			String strForward = "exitoRecuperarLocalidadesParaAltaGFB";

			try {
				Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
				WebApplicationContext ctx = getWebApplicationContext();
				
				IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
				rolFachada.verificarMenu(Constantes.ALTA_GUIA_FORESTAL_MENU,usuario.getRol());
				
				ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
						.getBean("localidadFachada");			
				
				String paramLocalidad = request.getParameter("idLocalidad");
				String idProductor = request.getParameter("idProductor");
				
				//List<Fiscalizacion> fiscalizaciones = fiscalizacionFachada.recuperarFiscalizaciones();
				List<Localidad> localidades = localidadFachada.getLocalidades();
				
				//request.setAttribute("fiscalizaciones", fiscalizaciones);
				request.setAttribute("localidades", localidades);
				request.setAttribute("idLocalidad", paramLocalidad);
				request.setAttribute("idProductor", idProductor);
				
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				strForward = "error";
			}

			return mapping.findForward(strForward);
		}	
	*/

	@SuppressWarnings("unchecked")
	public ActionForward recuperarTiposDeEntidadParaAltaGFB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarTiposDeEntidadParaAltaGFB";

		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			rolFachada.verificarMenu(Constantes.ALTA_GUIA_FORESTAL_MENU, usuario.getRol());

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidad());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarFiscalizacionesParaAltaGFB");

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarFiscalizacionesParaAltaGFB(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoRecuperarFiscalizacionesParaAltaGFB";

		try {

			WebApplicationContext ctx = getWebApplicationContext();
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");

			String idProductor = request.getParameter("idProductor");

			List<Fiscalizacion> fiscalizaciones = fiscalizacionFachada
					.recuperarFiscalizacionesParaAltaGFB(new Long(idProductor));

			request.setAttribute("fiscalizaciones", fiscalizaciones);
			request.setAttribute("idLocalidad", idProductor);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

/*	@SuppressWarnings("unchecked")
	public ActionForward recuperarLocalidadesParaModificacionGFB(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoRecuperarLocalidadesParaModificacionGFB";

		try {
				Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			rolFachada.verificarMenu(Constantes.MODIFICACION_GUIA_FORESTAL_MENU, usuario.getRol());

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");

			String paramLocalidad = request.getParameter("idLocalidad");
			String idProductor = request.getParameter("idProductor");

			//List<Fiscalizacion> fiscalizaciones = fiscalizacionFachada.recuperarFiscalizaciones();
			List<Localidad> localidades = localidadFachada.getLocalidades();

			//request.setAttribute("fiscalizaciones", fiscalizaciones);
			request.setAttribute("localidades", localidades);
			request.setAttribute("idLocalidad", paramLocalidad);
			request.setAttribute("idProductor", idProductor);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}
*/

	@SuppressWarnings("unchecked")
	public ActionForward recuperarTiposDeEntidadParaModificacionGFB(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoRecuperarTiposDeEntidadParaModificacionGFB";

		try {
			Usuario usuario = (Usuario)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			rolFachada.verificarMenu(Constantes.MODIFICACION_GUIA_FORESTAL_MENU, usuario.getRol());

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidad());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaModificacionGFB");

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}


	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaModificacionGFB(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception 
	{
		String strForward = "exitoRecuperarGuiasForestalesParaModificacionGFB";

		try {
			//String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestal> guiasForestales = consultasPorProductorFachada
					.recuperarGuiasForestales(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			//request.setAttribute("paramForward", paramForward);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionGuiaForestalBasica(
														ActionMapping mapping, ActionForm form,
														HttpServletRequest request, HttpServletResponse response) 
														throws Exception 
	{
		String strForward = "exitoCargarModificacionGuiaForestalBasica";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			//rolFachada.verificarMenu(Constantes.MODIFICACION_GUIA_FORESTAL_MENU,usuario.getRol());
			
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarProductoresParaBoletasDeposito(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception
	{
		String strForward = "exitoRecuperarProductoresParaBoletaDeposito";
		try {
			String paramForward = request.getParameter("forward");
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);

			WebApplicationContext ctx = getWebApplicationContext();
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			
			if(paramForward.equals(Constantes.METODO_CARGAR_GUIA_PAGO_BOLETA_DEPOSITO)){
				//rolFachada.verificarMenu(Constantes.REGISTRAR_PAGO_BOLETA_MENU,usuario.getRol());
				request.setAttribute("titulo", "Registrar Pago Boleta de Deposito");				
			}
			else{
				//rolFachada.verificarMenu(Constantes.REEMPLAZAR_BOLETA_MENU,usuario.getRol());				
				request.setAttribute("titulo", "Reemplazar Boleta de Deposito");
			}

			String forwardBuscarNroGuia = request.getParameter("forwardBuscarNroGuia");
			request.setAttribute("forwardBuscarNroGuia", forwardBuscarNroGuia);

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidad());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaBoletaDeposito");
			request.setAttribute("paramForward", paramForward);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaBoletaDeposito(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception 
	{
		String strForward = "exitoRecuperarGuiasForestalesParaBoletaDeposito";

		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestal> guiasForestales = consultasPorProductorFachada
					.recuperarGuiasForestalesConDeudasAforo(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			request.setAttribute("paramForward", paramForward);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalPagoBoletaDepositoPorNroGuia(ActionMapping mapping, ActionForm form,
																		HttpServletRequest request, HttpServletResponse response) 
																		throws Exception 
	{
		String strForward = "exitoCargarGuiaForestalPagoBoletaDeposito";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());

			request.setAttribute("guiaForestal", guiaForestal);

			/*long nroguia = guiaForestalFachada.recuperarIdPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());
			
			request.setAttribute("idGuia",String.valueOf(nroguia));*/

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalPagoBoletaDeposito(
														ActionMapping mapping, ActionForm form,
														HttpServletRequest request, HttpServletResponse response) 
														throws Exception 
	{
		String strForward = "exitoCargarGuiaForestalPagoBoletaDeposito";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			//rolFachada.verificarMenu(Constantes.REGISTRAR_PAGO_BOLETA_MENU,usuario.getRol());
			
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			//String idGuia = (request.getParameter("idGuia")!=null)?request.getParameter("idGuia"):(String)request.getAttribute("idGuia");

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalReemBoletaDepositoPorNroGuia(ActionMapping mapping, ActionForm form,
																		HttpServletRequest request, HttpServletResponse response) 
																		throws Exception 
	{
		String strForward = "exitoCargarGuiaForestalReemBoletaDeposito";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());

			request.setAttribute("guiaForestal", guiaForestal);

			/*long nroguia = guiaForestalFachada.recuperarIdPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());
			
			request.setAttribute("idGuia",String.valueOf(nroguia));*/

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		return mapping.findForward(strForward);
	}

	public ActionForward cargarGuiaForestalReemBoletaDeposito(
											ActionMapping mapping, ActionForm form,
											HttpServletRequest request, HttpServletResponse response) 
											throws Exception 
	{
		String strForward = "exitoCargarGuiaForestalReemBoletaDeposito";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			//rolFachada.verificarMenu(Constantes.REEMPLAZAR_BOLETA_MENU,usuario.getRol());
			
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarProductoresParaValeTransporte(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception
	{
		String strForward = "exitoRecuperarProductoresParaValeTransporte";
		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			String paramForward = request.getParameter("forward");

			WebApplicationContext ctx = getWebApplicationContext();
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
						
			if(paramForward.equals(Constantes.METODO_CARGAR_GUIA_DEVOLUCION_VALE)){
				//rolFachada.verificarMenu(Constantes.DEVOLUCION_VALE_MENU,usuario.getRol());				
				request.setAttribute("titulo", "Devolución de Vale de Transporte");				
			}
			else{
				//rolFachada.verificarMenu(Constantes.REEMPLAZAR_VALE_MENU,usuario.getRol());				
				request.setAttribute("titulo", "Reemplazar Vale de Transporte");
			}

			String forwardBuscarNroGuia = request.getParameter("forwardBuscarNroGuia");
			String paramLoc = request.getParameter("idLoc");
			String paramProd = request.getParameter("idProd");

			ILocalidadFachada localidadFachada = 
				(ILocalidadFachada) ctx.getBean("localidadFachada");			

			IFiscalizacionFachada fiscalizacionFachada = 
				(IFiscalizacionFachada) ctx.getBean("fiscalizacionFachada");

			List<Localidad> localidades = localidadFachada.getLocalidades();
			List<Entidad> productores = fiscalizacionFachada.recuperarProductores();

			request.setAttribute("localidades", localidades);
			request.setAttribute("productores", productores);
			request.setAttribute("paramForward", paramForward);
			request.setAttribute("forwardBuscarNroGuia", forwardBuscarNroGuia);
			request.setAttribute("idLoc", paramLoc);
			request.setAttribute("idProd", paramProd);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarGuiasForestalesParaValeTransporte(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception 
	{
		String strForward = "exitoRecuperarGuiasForestalesParaValeTransporte";

		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
			IConsultasPorProductorFachada consultasPorProductorFachada = (IConsultasPorProductorFachada) ctx
					.getBean("consultasPorProductorFachada");

			String idProductor = request.getParameter("idProductor");

			List<GuiaForestal> guiasForestales = consultasPorProductorFachada
					.recuperarGuiasForestalesConDeudasVales(Long.parseLong(idProductor));

			request.setAttribute("guiasForestales", guiasForestales);
			request.setAttribute("paramForward", paramForward);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "bloqueError";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalDevolucionValeTransportePorNroGuia(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception 
	{
		String strForward = "exitoCargarGuiaForestalDevolucionValeTransporte";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		return mapping.findForward(strForward);
	}

	public ActionForward cargarGuiaForestalDevolucionValeTransporte(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception 
	{
		String strForward = "exitoCargarGuiaForestalDevolucionValeTransporte";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			//rolFachada.verificarMenu(Constantes.DEVOLUCION_VALE_MENU,usuario.getRol());
			
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarGuiaForestalReemplazarValeTransportePorNroGuia(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception 
	{
		String strForward = "exitoCargarGuiaForestalReemplazarValeTransporte";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx
					.getBean("guiaForestalFachada");

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		return mapping.findForward(strForward);
	}

	public ActionForward cargarGuiaForestalReemplazarValeTransporte(
												ActionMapping mapping, ActionForm form,
												HttpServletRequest request, HttpServletResponse response) 
												throws Exception 
	{
		String strForward = "exitoCargarGuiaForestalReemplazarValeTransporte";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			//rolFachada.verificarMenu(Constantes.REEMPLAZAR_VALE_MENU,usuario.getRol());
			
			IGuiaForestalFachada guiaForestalFachada = (IGuiaForestalFachada) ctx.getBean("guiaForestalFachada");

			String idGuia = request.getParameter("idGuia");

			GuiaForestal guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

			request.setAttribute("guiaForestal", guiaForestal);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public boolean validarGuiaForestalBasicaForm(StringBuffer error, ActionForm form) {
		GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
		return guiaForestalForm.validar(error);
	}

	public boolean validarNroGuiaForm(StringBuffer error, ActionForm form) {

		GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		IGuiaForestalFachada guiaFachada = (IGuiaForestalFachada) ctx.getBean("guiaForestalFachada");

		boolean valido = Validator.validarEnteroMayorQue(0,
					Integer.toString(guiaForestalForm.getGuiaForestal().getNroGuia()), "Nro de Guía", error);		

		boolean existe = valido;

		if (valido) {
			existe = guiaFachada.existeGuiaForestal(guiaForestalForm.getGuiaForestal().getNroGuia());

			if (!existe) {
				Validator.addErrorXML(error, Constantes.NO_EXISTE_GUIA);
			}
		}

		return existe;
	}

	public boolean validarRegistrarPagoCuotaForm(StringBuffer error, ActionForm form) {
		GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
		return true;
	}
}
