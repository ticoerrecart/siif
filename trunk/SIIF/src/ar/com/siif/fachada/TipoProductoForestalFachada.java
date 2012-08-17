package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.enums.EstadoProducto;
import ar.com.siif.negocio.TipoProducto;
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

	public List<TipoProducto> recuperarTiposProductoForestal()throws NegocioException {

		try{
			return datosSistemaDAO.recuperarTiposProducto();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public TipoProducto recuperarTipoProductoForestal(long id)throws NegocioException {

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
			TipoProducto tipoProducto = datosSistemaDAO.recuperarTipoProductoForestal(tipoProductoDTO.getId());
			tipoProducto.setNombre(tipoProductoDTO.getNombre());
			datosSistemaDAO.modificacionTipoProductoForestal(tipoProducto);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO) {

		return datosSistemaDAO.existeTipoProductoForestal(tipoProdructoDTO);
	}
	
	public List<TipoProductoDTO> recuperarTiposProductoForestalDTO()throws NegocioException{
		
		try{
			List<TipoProductoDTO> tiposProductoDTO = new ArrayList<TipoProductoDTO>();
			List<TipoProducto> tiposProducto = datosSistemaDAO.recuperarTiposProducto();
			
			for (TipoProducto tipoProducto : tiposProducto) {
				
				tiposProductoDTO.add(ProviderDTO.getTipoProductoDTO(tipoProducto));
			}
			
			return tiposProductoDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public List<EstadoProducto> getEstadosProductos(){
		
		List<EstadoProducto> estadosProductos = new ArrayList<EstadoProducto>();
		estadosProductos.add(EstadoProducto.Seco);
		estadosProductos.add(EstadoProducto.Verde);
		
		return estadosProductos;
	}	
}
