package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.negocio.TipoProducto;
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

		datosSistemaDAO.altaTipoProductoForestal(ProviderDominio.getTipoProductoForestal(tipoProductoForestalDTO));
	}

	public List<TipoProducto> recuperarTiposProductoForestal() {

		return datosSistemaDAO.recuperarTiposProducto(); 
		
	}

	public TipoProducto recuperarTipoProductoForestal(long id) {

		return datosSistemaDAO.recuperarTipoProductoForestal(id);
	}	
	
	public TipoProductoDTO recuperarTipoProductoForestalDTO(long id) {

		return ProviderDTO.getTipoProductoDTO(datosSistemaDAO.recuperarTipoProductoForestal(id));
	}

	public void modificacionTipoProductoForestal(TipoProductoDTO tipoProductoDTO) throws NegocioException {

		TipoProducto tipoProducto = datosSistemaDAO.recuperarTipoProductoForestal(tipoProductoDTO.getId());
		tipoProducto.setNombre(tipoProductoDTO.getNombre());
		datosSistemaDAO.modificacionTipoProductoForestal(tipoProducto);
	}

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO) {

		return datosSistemaDAO.existeTipoProductoForestal(tipoProdructoDTO);
	}
	
	public List<TipoProductoDTO> recuperarTiposProductoForestalDTO(){
		
		List<TipoProductoDTO> tiposProductoDTO = new ArrayList<TipoProductoDTO>();
		List<TipoProducto> tiposProducto = datosSistemaDAO.recuperarTiposProducto();
		
		for (TipoProducto tipoProducto : tiposProducto) {
			
			tiposProductoDTO.add(ProviderDTO.getTipoProductoDTO(tipoProducto));
		}
		
		return tiposProductoDTO;
	}	
}
