package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;
import ar.com.siif.dao.FiscalizacionDAO;
import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
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
	
	public FiscalizacionFachada(FiscalizacionDAO fiscalizacionDAO, IUbicacionFachada pUbicacionFachada,
			IEntidadFachada pEntidadFachada, ITipoProductoForestalFachada pTipoProductoForestalFachada,
			IUsuarioFachada pUsuarioFachada) {

		this.fiscalizacionDAO = fiscalizacionDAO;
		this.ubicacionFachada = pUbicacionFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
		this.usuarioFachada = pUsuarioFachada;
	}

	/*public List<Entidad> recuperarEntidades() throws NegocioException {
		try{
			return fiscalizacionDAO.recuperarEntidades();

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}*/

	/*public List<Entidad> recuperarProductores() throws NegocioException {
		try{
			return fiscalizacionDAO.recuperarProductores();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}*/

	public List<Fiscalizacion> recuperarFiscalizaciones() throws NegocioException {
		try{
			return fiscalizacionDAO.recuperarFiscalizaciones();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAltaGFB(Long idProductor, Long idRodal) throws NegocioException {

		try{
			List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
			List<Fiscalizacion> listaFiscalizaciones = fiscalizacionDAO.recuperarFiscalizacionesParaAltaGFB(idProductor,idRodal);
			
			for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
				listaFiscalizacionesDTO.add(ProviderDTO.getFiscalizacionDTO(fiscalizacion));
			}

			return listaFiscalizacionesDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(Long idProductor) throws NegocioException {

		try{
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

	public void modificacionFiscalizacion(Fiscalizacion fiscalizacion, List<Muestra> muestrasAEliminar)
																				throws NegocioException 
	{
		try{
			fiscalizacionDAO.modificacionFiscalizacion(fiscalizacion,muestrasAEliminar);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}	
	}

	public void altaFiscalizacion(Fiscalizacion fiscalizacion) throws NegocioException{
		try{
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
	
	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor) throws NegocioException{
		try{
			return fiscalizacionDAO.recuperarFiscalizacionesPorProductor(idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public void actualizarFiscalizacion(Fiscalizacion fiscalizacion) throws NegocioException {
		try{
			fiscalizacionDAO.actualizarFiscalizacion(fiscalizacion);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
	
	public void altaFiscalizacion(FiscalizacionDTO fiscalizacionDTO, List<MuestraDTO> muestrasDTO)
		throws NegocioException{
		try{
			Rodal rodal = ubicacionFachada.getRodal(fiscalizacionDTO.getRodal().getId());
			Entidad productorForestal = entidadFachada.getEntidad(fiscalizacionDTO.getProductorForestal().getId());
			Entidad oficinaForestal = entidadFachada.getEntidad(fiscalizacionDTO.getOficinaAlta().getId());
			TipoProducto tipoProducto = tipoProductoForestalFachada.recuperarTipoProductoForestal(
					fiscalizacionDTO.getTipoProducto().getId());
			Usuario usuario = usuarioFachada.getUsuario(fiscalizacionDTO.getUsuario().getId());
			
			Fiscalizacion fiscalizacion = ProviderDominio.getFiscalizacion(fiscalizacionDTO,muestrasDTO,rodal,
																			productorForestal,oficinaForestal,
																			tipoProducto,usuario);
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
	
	public FiscalizacionDTO recuperarFiscalizacionDTO(long idFiscalizacion)throws NegocioException{

		try {
			return ProviderDTO.getFiscalizacionDTO(fiscalizacionDAO.recuperarFiscalizacion(idFiscalizacion));
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
