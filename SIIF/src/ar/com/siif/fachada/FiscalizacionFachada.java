package ar.com.siif.fachada;

import java.util.List;
import ar.com.siif.dao.FiscalizacionDAO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.DataBaseException;

public class FiscalizacionFachada implements IFiscalizacionFachada {

	private FiscalizacionDAO fiscalizacionDAO;

	public FiscalizacionFachada() {
	}

	public FiscalizacionFachada(FiscalizacionDAO fiscalizacionDAO) {

		this.fiscalizacionDAO = fiscalizacionDAO;
	}

	public List<Entidad> recuperarEntidades() {

		return fiscalizacionDAO.recuperarEntidades();
	}

	public List<Entidad> recuperarProductores() {

		return fiscalizacionDAO.recuperarProductores();
	}

	public List<Fiscalizacion> recuperarFiscalizaciones() {

		return fiscalizacionDAO.recuperarFiscalizaciones();
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaAltaGFB(Long idProductor) {

		return fiscalizacionDAO.recuperarFiscalizacionesParaAltaGFB(idProductor);
	}

	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion) {

		return fiscalizacionDAO.recuperarFiscalizacion(idFiscalizacion);
	}

	public void modificacionFiscalizacion(Fiscalizacion fiscalizacion,
										  List<Muestra> muestrasAEliminar)
											throws DataBaseException 
	{
		fiscalizacionDAO.modificacionFiscalizacion(fiscalizacion,muestrasAEliminar);
	}

	public void altaFiscalizacion(Fiscalizacion acta) {
		fiscalizacionDAO.altaFiscalizacion(acta);
	}

	public Entidad getProductorForestal(long idProductorForestal) {
		return fiscalizacionDAO.getProductorForestal(idProductorForestal);
	}

	public TipoProducto getTipoProducto(long idTipoProductoForestal) {
		return fiscalizacionDAO.getTipoProducto(idTipoProductoForestal);
	}

	/*public List<Fiscalizacion> recuperarFiscalizacionesPorLocalidad(Long idLocalidad){
		return fiscalizacionDAO.recuperarFiscalizacionesPorLocalidad(idLocalidad);
	}*/
	
	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor){
		return fiscalizacionDAO.recuperarFiscalizacionesPorProductor(idProductor);
	}	
}
