package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoExportacionDTO;
import ar.com.siif.enums.EstadoProducto;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.exception.NegocioException;

public interface ITipoProductoForestalFachada {

	public void altaTipoProductoForestal(TipoProductoDTO tipoProductoForestalDTO) throws NegocioException;

	public List<TipoProductoForestal> recuperarTiposProductoForestal()throws NegocioException;

	public TipoProductoForestal recuperarTipoProductoForestal(long id)throws NegocioException;
	
	public TipoProductoDTO recuperarTipoProductoForestalDTO(long id)throws NegocioException;

	public void modificacionTipoProductoForestal(TipoProductoDTO tipoProductoDTO) throws NegocioException;

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO);
	
	public List<TipoProductoDTO> recuperarTiposProductoForestalDTO()throws NegocioException;
	
	public List<EstadoProducto> getEstadosProductos();
	
	public boolean existeTipoProductoExportacion(TipoProductoDTO tipoProdructoDTO);
	
	public void altaTipoProductoExportacion(TipoProductoDTO tipoProductoExportacionDTO) throws NegocioException;	
	
	public List<TipoProductoDTO> recuperarTiposProductoExportacionDTO()throws NegocioException;
	
	public TipoProductoDTO recuperarTipoProductoExportacionDTO(long id)throws NegocioException;	
	
	public void modificacionTipoProductoExportacion(TipoProductoDTO tipoProductoDTO) throws NegocioException;
	
	public TipoProductoExportacion recuperarTipoProductoExportacion(long id)throws NegocioException;	
	
}
