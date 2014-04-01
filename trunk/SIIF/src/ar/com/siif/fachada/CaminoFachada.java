package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dao.CaminoDAO;
import ar.com.siif.negocio.CaminoConstruido;
import ar.com.siif.negocio.exception.NegocioException;

public class CaminoFachada implements ICaminoFachada {

	private CaminoDAO caminoDAO;

	public CaminoFachada() {
	}

	public CaminoFachada(CaminoDAO elCaminoDAO) {
		this.caminoDAO = elCaminoDAO;
	}

	public List<CaminoConstruido> getCaminos() {
		return caminoDAO.getCaminos();
	}

	public void altaCamino(CaminoConstruido caminoConstruido) throws NegocioException {
		caminoDAO.altaCamino(caminoConstruido);
	}

}
