package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;

public interface ITipoProductoForestalFachada {

	public void altaTipoProductoForestal(String tipoProductoForestal) throws NegocioException;

	public List<TipoProducto> recuperarTiposProducto();

	public TipoProducto recuperarTipoProductoForestal(long id);

	public void modificacionTipoProductoForestal(TipoProducto tipoProducto) throws NegocioException;

	public boolean existeTipoProductoForestal(String nombre, Long id);
}
