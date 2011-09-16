package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;

public class TipoProductoForestalFachada implements ITipoProductoForestalFachada {

	private TipoProductoForestalDAO datosSistemaDAO;

	public TipoProductoForestalFachada() {
	}

	public TipoProductoForestalFachada(TipoProductoForestalDAO datosSistemaDAO) {

		this.datosSistemaDAO = datosSistemaDAO;
	}

	public void altaTipoProductoForestal(String tipoProductoForestal) throws NegocioException {

		datosSistemaDAO.altaTipoProductoForestal(tipoProductoForestal);
	}

	public List<TipoProducto> recuperarTiposProducto() {

		return datosSistemaDAO.recuperarTiposProducto();
	}

	public TipoProducto recuperarTipoProductoForestal(long id) {

		return datosSistemaDAO.recuperarTipoProductoForestal(id);
	}

	public void modificacionTipoProductoForestal(TipoProducto tipoProducto) throws NegocioException {

		datosSistemaDAO.modificacionTipoProductoForestal(tipoProducto);
	}

	public boolean existeTipoProductoForestal(String nombre, Long id) {

		return datosSistemaDAO.existeTipoProductoForestal(nombre, id);
	}
}
