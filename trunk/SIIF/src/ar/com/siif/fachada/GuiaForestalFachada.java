package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.siif.dao.GuiaForestalDAO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.SubImporte;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Fecha;
import ar.com.siif.utils.MyLogger;

public class GuiaForestalFachada implements IGuiaForestalFachada {

	private GuiaForestalDAO guiaForestalDAO;

	private IUsuarioFachada usuarioFachada;

	private IFiscalizacionFachada fiscalizacionFachada;

	private IEntidadFachada entidadFachada;

	private ITipoProductoForestalFachada tipoProductoForestalFachada;

	private IUbicacionFachada ubicacionFachada;

	private ILocalidadFachada localidadFachada;

	public GuiaForestalFachada() {
	}

	public GuiaForestalFachada(GuiaForestalDAO guiaForestalDAO,
			IUsuarioFachada pUsuarioFachada,
			IFiscalizacionFachada pFiscalizacionFachada,
			IEntidadFachada pEntidadFachada,
			ITipoProductoForestalFachada pTipoProductoForestalFachada,
			IUbicacionFachada pUbicacionFachada,
			ILocalidadFachada pLocalidadFachada) {

		this.guiaForestalDAO = guiaForestalDAO;
		this.usuarioFachada = pUsuarioFachada;
		this.fiscalizacionFachada = pFiscalizacionFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
		this.ubicacionFachada = pUbicacionFachada;
		this.localidadFachada = pLocalidadFachada;
	}

	public void altaGuiaForestalBasica(GuiaForestalDTO guia,
			List<BoletaDepositoDTO> listaBoletaDepositoDTO,
			List<RangoDTO> listaRangosDTO, Date fechaVencimiento,
			List<FiscalizacionDTO> listaFiscalizacionesDTO,
			List<SubImporteDTO> listaSubImportesDTO) {

		List<Fiscalizacion> listaFiscalizaciones = new ArrayList<Fiscalizacion>();
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesDTO) {
			listaFiscalizaciones.add(fiscalizacionFachada
					.recuperarFiscalizacion(fiscalizacionDTO.getId()));
		}

		TipoProducto tipoProducto;
		List<SubImporte> listaSubImporte = new ArrayList<SubImporte>();
		for (SubImporteDTO subImporteDTO : listaSubImportesDTO) {
			tipoProducto = tipoProductoForestalFachada
					.recuperarTipoProductoForestal(subImporteDTO
							.getTipoProducto().getId());
			listaSubImporte.add(ProviderDominio.getSubImporte(null,
					tipoProducto, subImporteDTO));
		}

		Usuario usuario = usuarioFachada.getUsuario(guia.getUsuario().getId());
		Entidad productorForestal = entidadFachada.getEntidad(guia
				.getProductorForestal().getId());
		Rodal rodal = ubicacionFachada.getRodal(guia.getRodal().getId());
		Localidad localidad = localidadFachada.getLocalidadPorId(guia
				.getLocalidad().getId());

		GuiaForestal guiaForestal = ProviderDominio.getGuiaForestal(guia,
				listaBoletaDepositoDTO, listaRangosDTO, fechaVencimiento,
				listaFiscalizaciones, listaSubImporte, productorForestal,
				rodal, localidad, usuario);

		this.guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);

		for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
			fiscalizacion.setGuiaForestal(guiaForestal);
			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
		}
	}

	public List<GuiaForestalDTO> recuperarGuiasForestalesPorProductor(
			long idProductor) {

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = guiaForestalDAO
				.recuperarGuiasForestalesPorProductor(idProductor, true);

		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO
					.getGuiaForestalDTO(guiaForestal));
		}

		return listaGuiasForestalesDTO;
	}

	public List<GuiaForestalDTO> recuperarGuiasForestalesPorProductor(
			long idProductor, boolean sinAnulados) {

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = guiaForestalDAO
				.recuperarGuiasForestalesPorProductor(idProductor, sinAnulados);

		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO
					.getGuiaForestalDTO(guiaForestal));
		}
		return listaGuiasForestalesDTO;
	}

	public GuiaForestalDTO recuperarGuiaForestal(long idGuiaForestal) {

		GuiaForestal guiaForestal = guiaForestalDAO
				.recuperarGuiaForestal(idGuiaForestal);
		return ProviderDTO.getGuiaForestalDTO(guiaForestal);
	}

	public GuiaForestalDTO recuperarGuiaForestalPorNroGuia(int nroGuiaForestal) {

		GuiaForestal guiaForestal = guiaForestalDAO
				.recuperarGuiaForestalPorNroGuia(nroGuiaForestal, true);
		if (guiaForestal != null) {
			return ProviderDTO.getGuiaForestalDTO(guiaForestal);
		}
		return null;
	}

	public GuiaForestalDTO recuperarGuiaForestalPorNroGuia(int nroGuiaForestal,
			boolean sinAnulados) {

		GuiaForestal guiaForestal = guiaForestalDAO
				.recuperarGuiaForestalPorNroGuia(nroGuiaForestal, sinAnulados);
		if (guiaForestal != null) {
			return ProviderDTO.getGuiaForestalDTO(guiaForestal);
		}
		return null;
	}

	public String registrarPagoBoletaDeposito(long idBoleta)
			throws NegocioException {
		try {
			return guiaForestalDAO.registrarPagoBoletaDeposito(idBoleta);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public String reemplazarBoletaDeDeposito(long idBoleta, int numero,
			String concepto, String area, String efectivoCheque,
			String fechaVencimiento) throws NegocioException {
		try {
			return guiaForestalDAO.reemplazarBoletaDeDeposito(idBoleta, numero,
					concepto, area, efectivoCheque, fechaVencimiento);

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public String registrarDevolucionValeTransporte(long idVale)
			throws NegocioException {

		try {
			return guiaForestalDAO.registrarDevolucionValeTransporte(idVale);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public String registrarDevolucionYCompletarDatosValeTransporte(long idVale,
			String destino, String vehiculo, String marca, String dominio,
			String producto, int nroPiezas, double cantM3, String especie,
			String fechaDevolucion) throws NegocioException {

		try {
			// Validar que en la devolución de Vales de Transporte,
			// los m3 del vale no sobrepasen lo que esta fiscalizado en la guía
			// para el tipo de producto declarado en el vale,
			// ni tampoco que la suma de los m3 de los vales devueltos,
			// mas el del vale en cuestión, no sobrepase lo fiscalizado en la
			// guía para ese tipo de producto.
			StringBuffer pError = new StringBuffer();
			boolean ok = Validator.requerido(fechaDevolucion,
					"Fecha Devolución", pError);
			boolean ok2 = Validator.validarDoubleMayorQue(0,
					String.valueOf(cantM3), "Cantidad(m3)", pError);

			double totalMts = 0;
			ValeTransporte vale = (ValeTransporte) this.guiaForestalDAO
					.getHibernateTemplate().get(ValeTransporte.class, idVale);
			List<Fiscalizacion> fiscalizaciones = vale.getGuiaForestal()
					.getFiscalizaciones();

			boolean ok3 = Validator.validarFiscalizacionExistenteParaVale(
					fiscalizaciones, producto, pError);

			for (Fiscalizacion fiscalizacion : fiscalizaciones) {
				if (fiscalizacion.getTipoProducto().getNombre()
						.equals(producto))
					totalMts = totalMts + fiscalizacion.getCantidadMts();
			}

			double totalMtsVales = cantM3;
			List<ValeTransporte> vales = vale.getGuiaForestal()
					.getValesTransporte();
			for (ValeTransporte valeTransporte : vales) {
				if (valeTransporte.getFechaDevolucion() != null
						&& producto.equalsIgnoreCase(valeTransporte
								.getProducto())) {
					totalMtsVales = totalMtsVales
							+ valeTransporte.getCantidadMts();
				}
			}

			boolean ok4 = Validator.validarM3ValesMenorQueM3Fiscalizaciones(
					totalMtsVales, totalMts, pError);
			if (ok && ok2 && ok3 && ok4) {
				return guiaForestalDAO
						.registrarDevolucionYCompletarDatosValeTransporte(
								idVale, destino, vehiculo, marca, dominio,
								producto, nroPiezas, cantM3, especie,
								fechaDevolucion);
			} else {
				return pError.toString();
			}

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public String reemplazarValeTransporte(long idVale, int numeroVale,
			String origen, String destino, String vehiculo, String marca,
			String dominio, String producto, int nroPiezas, double cantM3,
			String especie, String fechaVencimiento) throws NegocioException {

		try {
			return guiaForestalDAO.reemplazarValeTransporte(idVale, numeroVale,
					origen, destino, vehiculo, marca, dominio, producto,
					nroPiezas, cantM3, especie, fechaVencimiento);

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public boolean existeGuiaForestal(int nroGuia) {
		return guiaForestalDAO.existeGuiaForestal(nroGuia);
	}

	public boolean existeGuiaForestal(long idGuia, int nroGuia) {
		return guiaForestalDAO.existeGuiaForestal(idGuia, nroGuia);
	}

	public void asociarFiscalizacionesConGuiasForestales(long id,
			List<FiscalizacionDTO> listaFiscalizacionesAAsociar) {

		GuiaForestal guiaForestal;
		Fiscalizacion fiscalizacion;
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesAAsociar) {

			guiaForestal = guiaForestalDAO.recuperarGuiaForestal(id);

			fiscalizacion = fiscalizacionFachada
					.recuperarFiscalizacion(fiscalizacionDTO.getId());
			guiaForestal.getFiscalizaciones().add(fiscalizacion);
			fiscalizacion.setGuiaForestal(guiaForestal);

			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
		}

		// guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);

	}

	public void desasociarFiscalizacionesConGuiasForestales(long id,
			List<FiscalizacionDTO> listaFiscalizacionesAAsociar) {
		GuiaForestal guiaForestal;
		Fiscalizacion fiscalizacion;
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesAAsociar) {

			guiaForestal = guiaForestalDAO.recuperarGuiaForestal(id);

			fiscalizacion = fiscalizacionFachada
					.recuperarFiscalizacion(fiscalizacionDTO.getId());
			guiaForestal.getFiscalizaciones().remove(fiscalizacion);
			fiscalizacion.setGuiaForestal(null);

			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
		}

		// guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
	}

	public boolean verificarBoletasDepositoVencidasImpagas(long idProductor) {

		return guiaForestalDAO
				.verificarBoletasDepositoVencidasImpagas(idProductor);
	}

	/*
	 * public List<GuiaForestalDTO> recuperarGuiasParaCertificado(long
	 * idProductor, String periodo, long idPMF)throws NegocioException { try{
	 * List<GuiaForestalDTO> listaGuiasForestalesDTO = new
	 * ArrayList<GuiaForestalDTO>(); List<GuiaForestal> listaGuiasForestales =
	 * guiaForestalDAO
	 * .recuperarGuiasPorProductorPeriodoPMF(idProductor,periodo,idPMF);
	 * 
	 * GuiaForestalDTO guiaDTO; for (GuiaForestal guiaForestal :
	 * listaGuiasForestales) { guiaDTO =
	 * ProviderDTO.getGuiaForestalDTO(guiaForestal);
	 * //listaGuiasForestalesDTO.add
	 * (ProviderDTO.getGuiaForestalDTO(guiaForestal));
	 * 
	 * this.armarGuiaParaCertificado(guiaDTO); }
	 * 
	 * return listaGuiasForestalesDTO;
	 * 
	 * } catch (DataBaseException e) { throw new
	 * NegocioException(e.getMessage()); } }
	 * 
	 * private void armarGuiaParaCertificado(GuiaForestalDTO guiaDTO){
	 * 
	 * double montoPagado = 0; for (BoletaDepositoDTO boletaDTO :
	 * guiaDTO.getBoletasDeposito()) {
	 * 
	 * if(boletaDTO.getFechaPago() != null){ montoPagado = montoPagado +
	 * boletaDTO.getMonto(); } }
	 * 
	 * double importeLenia = 0; for (SubImporteDTO subImporteDTO :
	 * guiaDTO.getSubImportes()) { if(subImporteDTO.getTipoProducto().getId() ==
	 * 3L){ importeLenia = subImporteDTO.getImporte()*1.2;//Lo multiplico por
	 * 1.2 para agregarle el impuesto a la fiscalizacion } } }
	 */

	public void anularGuiaForestal(GuiaForestalDTO guiaForestalDTO) {

		this.desasociarFiscalizacionesConGuiasForestales(
				guiaForestalDTO.getId(), guiaForestalDTO.getFiscalizaciones());
		GuiaForestal guiaForestal = guiaForestalDAO
				.recuperarGuiaForestal(guiaForestalDTO.getId());
		for (BoletaDeposito boleta : guiaForestal.getBoletasDeposito()) {
			boleta.setAnulado(true);
		}

		for (ValeTransporte vale : guiaForestal.getValesTransporte()) {
			vale.setAnulado(true);
		}
		guiaForestal.setAnulado(true);

	}

	public void modificacionGuiaForestalBasica(GuiaForestalDTO guia,
			List<RangoDTO> listaRangosDTO,
			List<ValeTransporteDTO> valesTransporteDTO, Date fechaVencimiento) {

		if (!guiaForestalDAO
				.existeGuiaForestal(guia.getId(), guia.getNroGuia())) {
			GuiaForestal guiaForestal = this.guiaForestalDAO
					.recuperarGuiaForestal(guia.getId());
			guiaForestal.setNroGuia(guia.getNroGuia());
			guiaForestal.setFechaVencimiento(Fecha
					.stringDDMMAAAAToUtilDate(guia.getFechaVencimiento()));
			guiaForestal.setDistanciaAforoMovil(guia.getDistanciaAforoMovil());
			guiaForestal.setPeriodoForestal(guia.getPeriodoForestal());
			for (RangoDTO rangoDTO : listaRangosDTO) {
				guiaForestal.getValesTransporte().addAll(
						ProviderDominio.getValesTransportes(guiaForestal,
								rangoDTO, fechaVencimiento));
			}

			List<ValeTransporte> valesNuevos = new ArrayList<ValeTransporte>();

			for (ValeTransporte valeTransporte : guiaForestal
					.getValesTransporte()) {
				boolean encontre = false;
				for (ValeTransporteDTO valeTransporteDTO : valesTransporteDTO) {
					if (valeTransporteDTO != null
							&& valeTransporteDTO.getId() > 0) {
						if (valeTransporteDTO.getId() == valeTransporte.getId()) {
							encontre = true;
							valeTransporte.setGuiaForestal(null);
							break;
						}
					}
				}// for ValeTransporteDTO
				if (!encontre) {
					valesNuevos.add(valeTransporte);
				}
			}
			if (valesNuevos.size() > 0) {
				guiaForestal.setValesTransporte(valesNuevos);
			}
			this.guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
		}
	}

	public String restablecerGuias(Long[] idsGuias) throws NegocioException {
		try {
			if (idsGuias == null || idsGuias.length == 0) {
				throw new NegocioException("Seleccione alguna Guía Forestal");
			}

			String mensaje = (idsGuias.length > 1) ? "Las Guías Forestales fueron restablecidas con exito"
					: "La Guía Forestal fue restablecida con exito";

			for (Long idGuia : idsGuias) {

				guiaForestalDAO.restablecerGuias(idGuia);
			}

			return mensaje;

		} catch (NegocioException ne) {
			throw ne;
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

}
