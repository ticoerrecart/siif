package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.CaminoConstruido;
import ar.com.siif.negocio.exception.NegocioException;

public interface ICaminoFachada {

	public List<CaminoConstruido> getCaminos();

	public void altaCamino(CaminoConstruido caminoConstruido) throws NegocioException;

}
