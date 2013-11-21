package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.enums.EstadoProducto;
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.exception.NegocioException;

public interface ITipoProductoForestalFachada {

	public void altaTipoProductoForestal(TipoProductoForestalDTO tipoProductoForestalDTO)
			throws NegocioException;

	public List<TipoProductoForestal> recuperarTiposProductoForestal();

	public TipoProductoForestal recuperarTipoProductoForestal(long id);

	public TipoProductoForestalDTO recuperarTipoProductoForestalDTO(long id);

	public void modificacionTipoProductoForestal(TipoProductoForestalDTO tipoProductoForestalDTO)
			throws NegocioException;

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO);

	public List<TipoProductoForestalDTO> recuperarTiposProductoForestalDTO();

	public List<EstadoProducto> getEstadosProductos();

	public boolean existeTipoProductoExportacion(
			TipoProductoDTO tipoProdructoDTO);

	public void altaTipoProductoExportacion(
			TipoProductoDTO tipoProductoExportacionDTO) throws NegocioException;

	public List<TipoProductoDTO> recuperarTiposProductoExportacionDTO();

	public TipoProductoDTO recuperarTipoProductoExportacionDTO(long id);

	public void modificacionTipoProductoExportacion(
			TipoProductoDTO tipoProductoDTO) throws NegocioException;

	public TipoProductoExportacion recuperarTipoProductoExportacion(long id);
	
	public List<TipoProductoForestalDTO> recuperarTiposProductoForestalHabInhabDTO();

}
