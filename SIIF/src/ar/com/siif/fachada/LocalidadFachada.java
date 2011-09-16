package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dao.LocalidadDAO;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.exception.NegocioException;

public class LocalidadFachada implements ILocalidadFachada {

	private LocalidadDAO localidadDAO;

	public LocalidadFachada() {
	}

	public LocalidadFachada(LocalidadDAO laLocalidaDAO) {
		this.localidadDAO = laLocalidaDAO;
	}

	public List<Localidad> getLocalidades() {
		return localidadDAO.getLocalidades();
	}

	public Localidad getLocalidadPorId(Long id) {
		return localidadDAO.getLocalidadPorId(id);
	}

	public boolean existeLocalidad(String nombre, Long id) {
		return localidadDAO.existeLocalidad(nombre, id);
	}

	public void altaLocalidad(Localidad laLocalidad) throws NegocioException {
		localidadDAO.altaLocalidad(laLocalidad);
	}
}
