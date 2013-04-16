package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.FiscalizacionDAO;
import ar.com.siif.dto.FilaTablaVolFiscAsociarDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Localizacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.utils.Fecha;
import ar.com.siif.utils.MyLogger;

public class FiscalizacionFachada implements IFiscalizacionFachada {

	private FiscalizacionDAO fiscalizacionDAO;

	private IUbicacionFachada ubicacionFachada;

	private IEntidadFachada entidadFachada;

	private ITipoProductoForestalFachada tipoProductoForestalFachada;

	private IUsuarioFachada usuarioFachada;

	public FiscalizacionFachada() {
	}

	public FiscalizacionFachada(FiscalizacionDAO fiscalizacionDAO,
			IUbicacionFachada pUbicacionFachada,
			IEntidadFachada pEntidadFachada,
			ITipoProductoForestalFachada pTipoProductoForestalFachada,
			IUsuarioFachada pUsuarioFachada) {

		this.fiscalizacionDAO = fiscalizacionDAO;
		this.ubicacionFachada = pUbicacionFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
		this.usuarioFachada = pUsuarioFachada;
	}

	public List<Fiscalizacion> recuperarFiscalizaciones() {

		return fiscalizacionDAO.recuperarFiscalizaciones();
	}

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAltaGFB(
			Long idProductor) {

		List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
		List<Fiscalizacion> listaFiscalizaciones = fiscalizacionDAO
				.recuperarFiscalizacionesParaAltaGFB(idProductor);

		for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
			listaFiscalizacionesDTO.add(ProviderDTO
					.getFiscalizacionDTO(fiscalizacion));
		}

		return listaFiscalizacionesDTO;
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(
			Long idProductor) {

		return fiscalizacionDAO
				.recuperarFiscalizacionesParaModificacionGFB(idProductor);
	}

	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion) {

		return fiscalizacionDAO.recuperarFiscalizacion(idFiscalizacion);
	}

	/**
	 * Modificación de Fiscalización. Se puede modificar: Fecha, Periodo
	 * Forestal, Cantidad de Unidades, Oficina y las muestas.
	 * 
	 * @param fiscalizacionDTO
	 * @param muestrasNuevasDTO
	 * @throws NegocioException
	 */
	public void modificacionFiscalizacion(FiscalizacionDTO fiscalizacionDTO,
			List<MuestraDTO> muestrasNuevasDTO) {

		Fiscalizacion fiscalizacion = fiscalizacionDAO
				.recuperarFiscalizacion(fiscalizacionDTO.getId());

		fiscalizacion.setFecha((Fecha.stringDDMMAAAAToUtilDate(fiscalizacionDTO
				.getFecha())));
		fiscalizacion.setPeriodoForestal(fiscalizacionDTO.getPeriodoForestal());
		fiscalizacion.setCantidadUnidades(fiscalizacionDTO
				.getCantidadUnidades());
		fiscalizacion.setCantidadMts(fiscalizacionDTO.getCantidadMts());
		Entidad oficinaAlta = entidadFachada.getEntidad(fiscalizacionDTO
				.getOficinaAlta().getId());
		fiscalizacion.setOficinaAlta(oficinaAlta);
		fiscalizacion.setTamanioMuestra(muestrasNuevasDTO.size());
		fiscalizacion.setLocalizacion(ubicacionFachada
				.getLocalizacion(fiscalizacionDTO.getIdLocalizacion()));
		fiscalizacion.setUsuarioModificacion(usuarioFachada.getUsuario(
								fiscalizacionDTO.getUsuarioModificacion().getId()));
		fiscalizacionDAO.actualizarFiscalizacion(fiscalizacion,
				muestrasNuevasDTO);
	}

	public void altaFiscalizacion(Fiscalizacion fiscalizacion) {

		fiscalizacionDAO.altaFiscalizacion(fiscalizacion);

	}

	/*
	 * public Entidad getProductorForestal(long idProductorForestal) { return
	 * fiscalizacionDAO.getProductorForestal(idProductorForestal); }
	 */

	/*
	 * public TipoProducto getTipoProducto(long idTipoProductoForestal) { return
	 * fiscalizacionDAO.getTipoProducto(idTipoProductoForestal); }
	 */

	/*
	 * public List<Fiscalizacion> recuperarFiscalizacionesPorLocalidad(Long
	 * idLocalidad){ return
	 * fiscalizacionDAO.recuperarFiscalizacionesPorLocalidad(idLocalidad); }
	 */

	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(
			Long idProductor) {
		return fiscalizacionDAO
				.recuperarFiscalizacionesPorProductor(idProductor);
	}

	public void altaFiscalizacion(FiscalizacionDTO fiscalizacionDTO,
			List<MuestraDTO> muestrasDTO) {

		Entidad productorForestal = entidadFachada.getEntidad(fiscalizacionDTO
				.getProductorForestal().getId());
		Entidad oficinaForestal = entidadFachada.getEntidad(fiscalizacionDTO
				.getOficinaAlta().getId());
		TipoProductoForestal tipoProducto = tipoProductoForestalFachada
				.recuperarTipoProductoForestal(fiscalizacionDTO
						.getTipoProducto().getId());

		Usuario usuarioAlta = usuarioFachada.getUsuario(fiscalizacionDTO
				.getUsuarioAlta().getId());

		Long idLocalizacion = fiscalizacionDTO.getIdLocalizacion();

		Localizacion localizacion = ubicacionFachada
				.getLocalizacion(idLocalizacion);

		Fiscalizacion fiscalizacion = ProviderDominio.getFiscalizacion(
				fiscalizacionDTO, muestrasDTO, localizacion, productorForestal,
				oficinaForestal, tipoProducto, usuarioAlta, null);
		fiscalizacionDAO.altaFiscalizacion(fiscalizacion);
	}

	public FiscalizacionDTO recuperarFiscalizacionDTO(long idFiscalizacion) {

		return ProviderDTO.getFiscalizacionDTO(fiscalizacionDAO
				.recuperarFiscalizacion(idFiscalizacion));

	}

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAsociarAGuia(
			Long idProductor, Long idLocalizacion,
			List<SubImporteDTO> listaSubImportesDTO,
			List<FilaTablaVolFiscAsociarDTO> tablaVolFiscAsociar)

	{
		List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
		List<Fiscalizacion> listaFiscalizaciones = fiscalizacionDAO
				.recuperarFiscalizacionesDTOParaAsociarAGuia(idProductor,
						listaSubImportesDTO, tablaVolFiscAsociar);

		if (listaFiscalizaciones.size() > 0) {
			Localizacion localizacionOrigen = ubicacionFachada
					.getLocalizacion(idLocalizacion);
			for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
				if (fiscalizacion.getLocalizacion().esParteDeLaLocalizacion(
						localizacionOrigen)
						|| localizacionOrigen
								.esParteDeLaLocalizacion(fiscalizacion
										.getLocalizacion())) {
					listaFiscalizacionesDTO.add(ProviderDTO
							.getFiscalizacionDTO(fiscalizacion));
				}
			}
		}

		return listaFiscalizacionesDTO;
	}

	public void validarFiscalizacionDTO(String idFiscalizacion,
			String idProductorForestal, String idTipoProducto, String idRodal)
			throws NumberFormatException, NegocioException {
		Fiscalizacion fiscalizacion = fiscalizacionDAO
				.recuperarFiscalizacion(Long.parseLong(idFiscalizacion));
		if (fiscalizacion.getGuiaForestal() != null) {
			String msjPrefijo = "La Fiscalización tiene asociada la Guía Forestal Nro. "
					+ fiscalizacion.getGuiaForestal().getNroGuia()
					+ " y se está/n cambiando ";
			String msjDesvincular = " Desea desvincularla de la Guía Forestal y guardarla de todas formas?";
			/*
			 * if (!fiscalizacion.getProductorForestal().getIdTipoEntidad()
			 * .equalsIgnoreCase(tipoEntidad)) { throw new
			 * NegocioException(msjPrefijo + "el Tipo De Entidad." +
			 * msjDesvincular); } else {
			 */
			if (fiscalizacion.getProductorForestal().getId() != Long
					.parseLong(idProductorForestal)) {
				throw new NegocioException(msjPrefijo
						+ "el Productor Forestal." + msjDesvincular);
			} else {
				if (fiscalizacion.getTipoProducto().getId() != Long
						.parseLong(idTipoProducto)) {
					throw new NegocioException(msjPrefijo
							+ "el Tipo de Producto." + msjDesvincular);
				} else {
					/*
					 * if
					 * (fiscalizacion.getRodal().getMarcacion().getTranzon().getPmf
					 * ().getId() != Long .parseLong(idPMF)) { throw new
					 * NegocioException(msjPrefijo +
					 * "el Plan de Manejo Forestal." + msjDesvincular); } else {
					 * if
					 * (fiscalizacion.getRodal().getMarcacion().getTranzon().getId
					 * () != Long .parseLong(idTranzon)) { throw new
					 * NegocioException(msjPrefijo + "el Tranzón." +
					 * msjDesvincular); } else { if
					 * (fiscalizacion.getRodal().getMarcacion().getId() != Long
					 * .parseLong(idMarcacion)) { throw new
					 * NegocioException(msjPrefijo + "la Marcación." +
					 * msjDesvincular); } else {
					 */
					if (fiscalizacion.getLocalizacion().getId() != Long
							.parseLong(idRodal)) {
						throw new NegocioException(msjPrefijo + "el Rodal."
								+ msjDesvincular);
						/*
						 * } else { //valido las muestras if (muestras.length >
						 * 0) { if (muestras.length !=
						 * fiscalizacion.getMuestra() .size()) { throw new
						 * NegocioException(msjPrefijo + "las Muestras." +
						 * msjDesvincular); } }
						 */
					}// else Rodal
						// }//else Marcacion
						// }//else Tranzon
						// }//else PMF
				}// else TipoProducto
			}// else Prod. Forestal
				// }//else tipoEntidad
		}// hay GForestal

	}

	public List<Fiscalizacion> recuperarFiscalizacionesAAnularPorProductor(
			Long idProductor) {
		return fiscalizacionDAO
				.recuperarFiscalizacionesAAnularPorProductor(idProductor);
	}

	public void anularFiscalizaciones(Long[] idsFiscalizaciones)
			throws NegocioException {
		try {
			if (idsFiscalizaciones == null || idsFiscalizaciones.length == 0) {
				throw new NegocioException("Seleccione alguna Fiscalización");
			}
			for (Long idFiscalizacion : idsFiscalizaciones) {
				fiscalizacionDAO.anularFiscalizacion(idFiscalizacion);
			}
		} catch (NegocioException ne) {
			throw ne;
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAltaCertificadoOrigen(
			Long idProductor, String periodo, Long idLocalizacion) {
		List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();

		List<Fiscalizacion> fiscalizaciones = fiscalizacionDAO
				.recuperarFiscalizacionesDTOParaAltaCertificadoOrigen(
						idProductor, periodo, idLocalizacion);
		if (fiscalizaciones.size() > 0) {
			Localizacion localizacionOrigen = ubicacionFachada
					.getLocalizacion(idLocalizacion);
			for (Fiscalizacion fiscalizacion : fiscalizaciones) {
				if (fiscalizacion.getLocalizacion().esParteDeLaLocalizacion(
						localizacionOrigen)) {
					listaFiscalizacionesDTO.add(ProviderDTO
							.getFiscalizacionDTO(fiscalizacion));
				}
			}
		}

		return listaFiscalizacionesDTO;
	}
}
