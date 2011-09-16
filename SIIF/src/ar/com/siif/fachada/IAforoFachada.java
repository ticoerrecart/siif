package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;

public interface IAforoFachada {

	public List<TipoProducto> recuperarTiposProducto();

	public void altaAforo(Aforo aforo, long idTipoProducto) throws NegocioException;

	public List<Aforo> recuperarAforos();

	public Aforo recuperarAforo(Long id);

	public void modificacionAforo(Aforo aforo, long idTipoProducto) throws NegocioException;

	public boolean existeAforo(Aforo aforo, Long idTipoProducto);
}
