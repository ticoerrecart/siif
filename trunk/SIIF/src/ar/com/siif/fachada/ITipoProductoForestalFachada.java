package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.enums.EstadoProducto;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;

public interface ITipoProductoForestalFachada {

	public void altaTipoProductoForestal(TipoProductoDTO tipoProductoForestalDTO) throws NegocioException;

	public List<TipoProducto> recuperarTiposProductoForestal();

	public TipoProducto recuperarTipoProductoForestal(long id);
	
	public TipoProductoDTO recuperarTipoProductoForestalDTO(long id);

	public void modificacionTipoProductoForestal(TipoProductoDTO tipoProductoDTO) throws NegocioException;

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO);
	
	public List<TipoProductoDTO> recuperarTiposProductoForestalDTO();
	
	public List<EstadoProducto> getEstadosProductos();
}
