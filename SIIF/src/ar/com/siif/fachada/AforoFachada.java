package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dao.AforoDAO;
import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;

public class AforoFachada implements IAforoFachada {

	private AforoDAO aforoDAO;

	private TipoProductoForestalDAO tipoProductoDAO;

	public AforoFachada() {
	}

	public AforoFachada(AforoDAO aforoDAO, TipoProductoForestalDAO tipoProductoDAO) {

		this.aforoDAO = aforoDAO;
		this.tipoProductoDAO = tipoProductoDAO;
	}

	public List<TipoProducto> recuperarTiposProducto() {

		return aforoDAO.recuperarTiposProducto();
	}

	public void altaAforo(Aforo aforo, long idTipoProducto) throws NegocioException {

		TipoProducto tipoProducto = tipoProductoDAO.recuperarTipoProductoForestal(idTipoProducto);
		aforo.setTipoProducto(tipoProducto);

		aforoDAO.altaAforo(aforo);
	}

	public List<Aforo> recuperarAforos() {

		return aforoDAO.recuperarAforos();
	}

	public Aforo recuperarAforo(Long id) {

		return aforoDAO.recuperarAforo(id);
	}

	public void modificacionAforo(Aforo aforo, long idTipoProducto) throws NegocioException {

		if (aforo.getTipoProducto() == null || aforo.getTipoProducto().getId() != idTipoProducto) {
			TipoProducto tipoProducto = tipoProductoDAO
					.recuperarTipoProductoForestal(idTipoProducto);
			aforo.setTipoProducto(tipoProducto);
		}
		aforoDAO.modificacionAforo(aforo);
	}

	public boolean existeAforo(Aforo aforo, Long idTipoProducto) {

		return aforoDAO.existeAforo(aforo, idTipoProducto);
	}
	
	public String getValor(Long idFiscalizacion, String estado){
		
		try{
			Aforo aforo = aforoDAO.getAforo(idFiscalizacion,estado);
			
			return String.valueOf(aforo.getValorAforo());
			
		}catch(Exception e){
			return null;
		}	
	}
}
