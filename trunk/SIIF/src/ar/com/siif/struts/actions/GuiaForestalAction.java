package ar.com.siif.struts.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.RodalDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.fachada.IConsultasPorProductorFachada;
import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IGuiaForestalFachada;
import ar.com.siif.fachada.ILoginFachada;
import ar.com.siif.fachada.IRolFachada;
import ar.com.siif.fachada.ITipoProductoForestalFachada;
import ar.com.siif.fachada.IUbicacionFachada;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.struts.actions.forms.GuiaForestalForm;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class GuiaForestalAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaGuiaForestalBasica(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargaAltaGuiaForestalBasica";

		try {
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			ITipoProductoForestalFachada tipoProdFachada = (ITipoProductoForestalFachada) 
																ctx.getBean("tipoProductoForestalFachada");
			
			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");			
			//rolFachada.verificarMenu(Constantes.ALTA_GUIA_FORESTAL_MENU,usuario.getRol());
			
			IUbicacionFachada ubicacionFachada = (IUbicacionFachada) ctx
					.getBean("ubicacionFachada");			
			
			IFiscalizacionFachada fiscalizacionFachada = (IFiscalizacionFachada) ctx
					.getBean("fiscalizacionFachada");
					
			GuiaForestalForm guiaForm = (GuiaForestalForm) form;
			
			EntidadDTO productorForestal = entidadFachada.getEntidadDTO(guiaForm.getGuiaForestal().getProductorForestal().getId());
			RodalDTO rodal = ubicacionFachada.getRodalDTO(guiaForm.getGuiaForestal().getRodal().getId());
			
			List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
			HashMap<Long,SubImporteDTO> hashProductosFiscalizados = new HashMap<Long, SubImporteDTO>();
			for (FiscalizacionDTO fiscalizacionDTO : guiaForm.getListaFiscalizaciones()) {
				if(fiscalizacionDTO.getId() != 0){
					FiscalizacionDTO fis = fiscalizacionFachada.recuperarFiscalizacionDTO(
																		fiscalizacionDTO.getId());
					listaFiscalizacionesDTO.add(fis);
																		
					//Esto es para mostrar los subImportes
					SubImporteDTO subImporte = hashProductosFiscalizados.get(fis.getTipoProducto().getId());
					if(subImporte != null){
						subImporte.setCantidadMts(subImporte.getCantidadMts()+fis.getCantidadMts());
					}else{
						subImporte = new SubImporteDTO();
						subImporte.setCantidadMts(fis.getCantidadMts());
						subImporte.setTipoProducto(fis.getTipoProducto());
					}
					hashProductosFiscalizados.put(subImporte.getTipoProducto().getId(), subImporte);
				}	
			}
						
			request.setAttribute("tiposProductosForestales",tipoProdFachada.recuperarTiposProductoForestalDTO());
			request.setAttribute("estadosProductoForestal",tipoProdFachada.getEstadosProductos());
			request.setAttribute("productorForestal",productorForestal);
			request.setAttribute("rodal",rodal);
			request.setAttribute("fiscalizaciones",listaFiscalizacionesDTO);
			request.setAttribute("subImportes",hashProductosFiscalizados.values());
									
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

			GuiaForestalForm guiaForm = (GuiaForestalForm) form;
			GuiaForestalDTO guiaForestal = guiaForm.getGuiaForestal();

			guiaForestal.setUsuario(usr);
			guiaForestalFachada.altaGuiaForestalBasica(guiaForestal,
													   guiaForm.getBoletasDeposito(),
													   guiaForm.getRangos(), 
													   Fecha.stringDDMMAAAAToUtilDate(guiaForm.getFechaVencimiento()),
													   guiaForm.getListaFiscalizaciones(),
													   guiaForm.getListaSubImportes());

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
			GuiaForestalDTO guia = guiaForestalFachada.recuperarGuiaForestal(idGuiaForestal);

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
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_GUIA_FORESTAL_MENU, usuario.getRol());

			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");
			String idRodal = request.getParameter("idRodal");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("idRodal", idRodal);
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
			String idRodal = request.getParameter("idRodal");

			List<FiscalizacionDTO> fiscalizaciones = fiscalizacionFachada
					.recuperarFiscalizacionesDTOParaAltaGFB(new Long(idProductor),new Long(idRodal));

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
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_GUIA_FORESTAL_MENU, usuario.getRol());

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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

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

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
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

			List<GuiaForestalDTO> guiasForestales = consultasPorProductorFachada
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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());

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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());

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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

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

			IEntidadFachada entidadFachada = 
				(IEntidadFachada) ctx.getBean("entidadFachada");
			
			List<EntidadDTO> productores = entidadFachada.getProductoresDTO();
			
			String idTipoDeEntidad = request.getParameter("idTipoDeEntidad");
			String idProductor = request.getParameter("idProductor");

			request.setAttribute("productores", productores);
			request.setAttribute("forwardBuscarNroGuia", forwardBuscarNroGuia);			
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());
			request.setAttribute("idTipoDeEntidad", idTipoDeEntidad);
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("urlDetalle",
					"../../guiaForestal.do?metodo=recuperarGuiasForestalesParaValeTransporte");
			request.setAttribute("paramForward", paramForward);
			

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

			List<GuiaForestalDTO> guiasForestales = consultasPorProductorFachada
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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());

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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestalPorNroGuia(guiaForm.getGuiaForestal().getNroGuia());

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

			GuiaForestalDTO guiaForestal = guiaForestalFachada.recuperarGuiaForestal(Long.parseLong(idGuia));

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

	public boolean validarFiscalizacionesParaAltaGuiaForestalForm(StringBuffer error, ActionForm form) {
		
		/*GuiaForestalForm guiaForestalForm = (GuiaForestalForm) form;
		Long idTipoProd = null;
		List<FiscalizacionDTO> listaFiscalizacion = normalizarListaFiscalizacionesParaAltaGuia(guiaForestalForm.getListaFiscalizaciones());
		//FiscalizacionDTO fis = listaFiscalizacion.get(0);
		//if (fis != null){
		if(listaFiscalizacion.size() > 0){
			FiscalizacionDTO fis = listaFiscalizacion.get(0);
			idTipoProd = fis.getTipoProducto().getId();
		}
		for (FiscalizacionDTO fiscalizacion : listaFiscalizacion) {
			if(idTipoProd.longValue() != fiscalizacion.getTipoProducto().getId()){
				Validator.addErrorXML(error, "Las Fiscalizaciones seleccionadas deben tener el mismo Tipo de Producto");
				return false;
			}
		}*/
		
		return true;
	}	
	
	private List<FiscalizacionDTO> normalizarListaFiscalizacionesParaAltaGuia(List<FiscalizacionDTO> listaFiscalizaciones){
		
		List<FiscalizacionDTO> listaFis = new ArrayList<FiscalizacionDTO>();
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizaciones) {
			TipoProductoDTO tipoProd = fiscalizacionDTO.getTipoProducto();
			if(tipoProd.getId() != null && tipoProd.getId().longValue() != 0){
				listaFis.add(fiscalizacionDTO);
			}
		}
		return listaFis;
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
