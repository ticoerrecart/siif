package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoExportacionDTO;
import ar.com.siif.enums.EstadoProducto;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class TipoProductoForestalFachada implements ITipoProductoForestalFachada {

	private TipoProductoForestalDAO datosSistemaDAO;

	public TipoProductoForestalFachada() {
	}

	public TipoProductoForestalFachada(TipoProductoForestalDAO datosSistemaDAO) {

		this.datosSistemaDAO = datosSistemaDAO;
	}

	public void altaTipoProductoForestal(TipoProductoDTO tipoProductoForestalDTO) throws NegocioException {

		try{
			datosSistemaDAO.altaTipoProductoForestal(ProviderDominio.getTipoProductoForestal(tipoProductoForestalDTO));
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public List<TipoProductoForestal> recuperarTiposProductoForestal()throws NegocioException {

		try{
			return datosSistemaDAO.recuperarTiposProducto();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public TipoProductoForestal recuperarTipoProductoForestal(long id)throws NegocioException {

		try{
			return datosSistemaDAO.recuperarTipoProductoForestal(id);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public TipoProductoDTO recuperarTipoProductoForestalDTO(long id)throws NegocioException {
		
		try{
			return ProviderDTO.getTipoProductoDTO(datosSistemaDAO.recuperarTipoProductoForestal(id));
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public void modificacionTipoProductoForestal(TipoProductoDTO tipoProductoDTO) throws NegocioException {

		try{
			TipoProductoForestal tipoProducto = datosSistemaDAO.recuperarTipoProductoForestal(tipoProductoDTO.getId());
			tipoProducto.setNombre(tipoProductoDTO.getNombre());
			datosSistemaDAO.modificacionTipoProductoForestal(tipoProducto);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO) {

		return datosSistemaDAO.existeTipoProductoForestal(tipoProdructoDTO.getNombre(),tipoProdructoDTO.getId());
	}
	
	public List<TipoProductoDTO> recuperarTiposProductoForestalDTO()throws NegocioException{
		
		try{
			List<TipoProductoDTO> tiposProductoDTO = new ArrayList<TipoProductoDTO>();
			List<TipoProductoForestal> tiposProducto = datosSistemaDAO.recuperarTiposProducto();
			
			for (TipoProductoForestal tipoProducto : tiposProducto) {
				
				tiposProductoDTO.add(ProviderDTO.getTipoProductoDTO(tipoProducto));
			}
			
			return tiposProductoDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public List<EstadoProducto> getEstadosProductos(){
		
		List<EstadoProducto> estadosProductos = new ArrayList<EstadoProducto>();
		//estadosProductos.add(EstadoProducto.Seco);
		estadosProductos.add(EstadoProducto.Verde);
		
		return estadosProductos;
	}	
	
	public boolean existeTipoProductoExportacion(TipoProductoDTO tipoProdructoDTO) {

		return datosSistemaDAO.existeTipoProductoExportacion(tipoProdructoDTO.getNombre(),tipoProdructoDTO.getId());
	}	
	
	public void altaTipoProductoExportacion(TipoProductoDTO tipoProductoDTO) throws NegocioException{
		
		try{
			datosSistemaDAO.altaTipoProductoExportacion(ProviderDominio.getTipoProductoExportacion(tipoProductoDTO));
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public List<TipoProductoDTO> recuperarTiposProductoExportacionDTO()throws NegocioException{

		try{
			List<TipoProductoDTO> tiposProductoDTO = new ArrayList<TipoProductoDTO>();
			List<TipoProductoExportacion> tiposProducto = datosSistemaDAO.recuperarTiposProductoExportacion();
			
			for (TipoProductoExportacion tipoProducto : tiposProducto) {
				
				tiposProductoDTO.add(ProviderDTO.getTipoProductoDTO(tipoProducto));
			}
			
			return tiposProductoDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public TipoProductoDTO recuperarTipoProductoExportacionDTO(long id)throws NegocioException{
		
		try{
			return ProviderDTO.getTipoProductoDTO(datosSistemaDAO.recuperarTipoProductoExportacion(id));
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public void modificacionTipoProductoExportacion(TipoProductoDTO tipoProductoDTO) throws NegocioException{
		
		try{
			TipoProductoExportacion tipoProducto = datosSistemaDAO.recuperarTipoProductoExportacion(tipoProductoDTO.getId());
			tipoProducto.setNombre(tipoProductoDTO.getNombre());
			datosSistemaDAO.modificacionTipoProductoExportacion(tipoProducto);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
}
