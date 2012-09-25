package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.FiscalizacionDAO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class FiscalizacionFachada implements IFiscalizacionFachada {

	private FiscalizacionDAO fiscalizacionDAO;

	private IUbicacionFachada ubicacionFachada;

	private IEntidadFachada entidadFachada;

	private ITipoProductoForestalFachada tipoProductoForestalFachada;

	private IUsuarioFachada usuarioFachada;

	public FiscalizacionFachada() {
	}

	public FiscalizacionFachada(FiscalizacionDAO fiscalizacionDAO,
			IUbicacionFachada pUbicacionFachada, IEntidadFachada pEntidadFachada,
			ITipoProductoForestalFachada pTipoProductoForestalFachada,
			IUsuarioFachada pUsuarioFachada) {

		this.fiscalizacionDAO = fiscalizacionDAO;
		this.ubicacionFachada = pUbicacionFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
		this.usuarioFachada = pUsuarioFachada;
	}

	public List<Fiscalizacion> recuperarFiscalizaciones() throws NegocioException {
		try {
			return fiscalizacionDAO.recuperarFiscalizaciones();

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAltaGFB(Long idProductor,
			Long idRodal) throws NegocioException {

		try {
			List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
			List<Fiscalizacion> listaFiscalizaciones = fiscalizacionDAO
					.recuperarFiscalizacionesParaAltaGFB(idProductor, idRodal);

			for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
				listaFiscalizacionesDTO.add(ProviderDTO.getFiscalizacionDTO(fiscalizacion));
			}

			return listaFiscalizacionesDTO;

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(Long idProductor)
			throws NegocioException {

		try {
			return fiscalizacionDAO.recuperarFiscalizacionesParaModificacionGFB(idProductor);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion) throws NegocioException {

		try {
			return fiscalizacionDAO.recuperarFiscalizacion(idFiscalizacion);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public void modificacionFiscalizacion(Fiscalizacion fiscalizacion,
			List<Muestra> muestrasAEliminar) throws NegocioException {
		try {
			fiscalizacionDAO.modificacionFiscalizacion(fiscalizacion, muestrasAEliminar);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public void altaFiscalizacion(Fiscalizacion fiscalizacion) throws NegocioException {
		try {
			fiscalizacionDAO.altaFiscalizacion(fiscalizacion);
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	/*public Entidad getProductorForestal(long idProductorForestal) {
		return fiscalizacionDAO.getProductorForestal(idProductorForestal);
	}*/

	/*public TipoProducto getTipoProducto(long idTipoProductoForestal) {
		return fiscalizacionDAO.getTipoProducto(idTipoProductoForestal);
	}*/

	/*public List<Fiscalizacion> recuperarFiscalizacionesPorLocalidad(Long idLocalidad){
		return fiscalizacionDAO.recuperarFiscalizacionesPorLocalidad(idLocalidad);
	}*/

	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor)
			throws NegocioException {
		try {
			return fiscalizacionDAO.recuperarFiscalizacionesPorProductor(idProductor);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public void actualizarFiscalizacion(Fiscalizacion fiscalizacion) throws NegocioException {
		try {
			fiscalizacionDAO.actualizarFiscalizacion(fiscalizacion);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public void altaFiscalizacion(FiscalizacionDTO fiscalizacionDTO, List<MuestraDTO> muestrasDTO)
			throws NegocioException {
		try {
			Rodal rodal = ubicacionFachada.getRodal(fiscalizacionDTO.getRodal().getId());
			Entidad productorForestal = entidadFachada.getEntidad(fiscalizacionDTO
					.getProductorForestal().getId());
			Entidad oficinaForestal = entidadFachada.getEntidad(fiscalizacionDTO.getOficinaAlta()
					.getId());
			TipoProducto tipoProducto = tipoProductoForestalFachada
					.recuperarTipoProductoForestal(fiscalizacionDTO.getTipoProducto().getId());
			Usuario usuario = usuarioFachada.getUsuario(fiscalizacionDTO.getUsuario().getId());

			Fiscalizacion fiscalizacion = ProviderDominio.getFiscalizacion(fiscalizacionDTO,
					muestrasDTO, rodal, productorForestal, oficinaForestal, tipoProducto, usuario);
			fiscalizacionDAO.altaFiscalizacion(fiscalizacion);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	/*public List<EntidadDTO> recuperarProductoresDTO() throws NegocioException{
		try{
			List<EntidadDTO> listaProductoresDTO = new ArrayList<EntidadDTO>();
			List<Entidad> listaProductores = fiscalizacionDAO.recuperarProductores();
			
			for (Entidad entidad : listaProductores) {
				listaProductoresDTO.add(ProviderDTO.getEntidadDTO(entidad));
			}
			
			return listaProductoresDTO;

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}*/

	public FiscalizacionDTO recuperarFiscalizacionDTO(long idFiscalizacion) throws NegocioException {

		try {
			return ProviderDTO.getFiscalizacionDTO(fiscalizacionDAO
					.recuperarFiscalizacion(idFiscalizacion));

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAsociarAGuia(Long idProductor,
			Long idRodal, List<SubImporteDTO> listaSubImportesDTO) throws NegocioException
	{
		try {
			List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
			List<Fiscalizacion> listaFiscalizaciones = fiscalizacionDAO
					.recuperarFiscalizacionesDTOParaAsociarAGuia(idProductor, idRodal, listaSubImportesDTO);

			for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
				listaFiscalizacionesDTO.add(ProviderDTO.getFiscalizacionDTO(fiscalizacion));
			}

			return listaFiscalizacionesDTO;

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}		
	
	public void validarFiscalizacionDTO(String idFiscalizacion, String idProductorForestal,
			String idTipoProducto, String idRodal) throws NumberFormatException, DataBaseException,
			NegocioException {
		Fiscalizacion fiscalizacion = fiscalizacionDAO.recuperarFiscalizacion(Long
				.parseLong(idFiscalizacion));
		if (fiscalizacion.getGuiaForestal() != null) {
			String msjPrefijo = "La Fiscalización tiene asociada la Guía Forestal Nro. "
					+ fiscalizacion.getGuiaForestal().getNroGuia() + " y se está/n cambiando ";
			String msjDesvincular = " Desea desvincularla de la Guía Forestal y guardarla de todas formas?";
			/*if (!fiscalizacion.getProductorForestal().getIdTipoEntidad()
					.equalsIgnoreCase(tipoEntidad)) {
				throw new NegocioException(msjPrefijo + "el Tipo De Entidad." + msjDesvincular);
			} else {*/
			if (fiscalizacion.getProductorForestal().getId() != Long.parseLong(idProductorForestal)) {
				throw new NegocioException(msjPrefijo + "el Productor Forestal." + msjDesvincular);
			} else {
				if (fiscalizacion.getTipoProducto().getId() != Long.parseLong(idTipoProducto)) {
					throw new NegocioException(msjPrefijo + "el Tipo de Producto." + msjDesvincular);
				} else {
					/*if (fiscalizacion.getRodal().getMarcacion().getTranzon().getPmf().getId() != Long
							.parseLong(idPMF)) {
						throw new NegocioException(msjPrefijo + "el Plan de Manejo Forestal."
								+ msjDesvincular);
					} else {
						if (fiscalizacion.getRodal().getMarcacion().getTranzon().getId() != Long
								.parseLong(idTranzon)) {
							throw new NegocioException(msjPrefijo + "el Tranzón."
									+ msjDesvincular);
						} else {
							if (fiscalizacion.getRodal().getMarcacion().getId() != Long
									.parseLong(idMarcacion)) {
								throw new NegocioException(msjPrefijo + "la Marcación."
										+ msjDesvincular);
							} else {*/
					if (fiscalizacion.getRodal().getId() != Long.parseLong(idRodal)) {
						throw new NegocioException(msjPrefijo + "el Rodal." + msjDesvincular);
						/*} else {
							//valido las muestras
							if (muestras.length > 0) {
								if (muestras.length != fiscalizacion.getMuestra()
										.size()) {
									throw new NegocioException(msjPrefijo
											+ "las Muestras." + msjDesvincular);
								}
							}*/
					}//else Rodal
						//}//else Marcacion
						//}//else Tranzon
						//}//else PMF
				}//else TipoProducto
			}//else Prod. Forestal
			//}//else tipoEntidad
		}//hay GForestal

	}

}
