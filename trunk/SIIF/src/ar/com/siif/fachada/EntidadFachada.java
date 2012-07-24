package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.EntidadDAO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.exception.NegocioException;

public class EntidadFachada implements IEntidadFachada {

	private EntidadDAO entidadDAO;

	public EntidadFachada() {

	}

	public EntidadFachada(EntidadDAO laEntidadDAO) {
		this.entidadDAO = laEntidadDAO;
	}

	public void altaEntidad(Entidad laEntidad) throws NegocioException {
		entidadDAO.altaEntidad(laEntidad);
	}

	public List<Entidad> getEntidades() {
		return entidadDAO.getEntidades();
	}

	public Entidad getEntidad(Long id) {
		return entidadDAO.getEntidad(id);
	}

	public boolean existeEntidad(String nombre, Long id) {
		return entidadDAO.existeEntidad(nombre, id);
	}

	public List<Entidad> getEntidadesPorLocalidad(Long idLocalidad) {
		return entidadDAO.getEntidades(idLocalidad);
	}

	public List<TipoDeEntidad> getTiposDeEntidad() {
		List<TipoDeEntidad> tiposDeEntidad = new ArrayList<TipoDeEntidad>();
		tiposDeEntidad.add(TipoDeEntidad.OBR);
		tiposDeEntidad.add(TipoDeEntidad.PPF);

		return tiposDeEntidad;

	}

	public List<Entidad> getEntidadesPorTipoDeEntidad(String tipoDeEntidad) {
		return entidadDAO.getEntidades(TipoDeEntidad.valueOf(tipoDeEntidad));
	}

}
