package ar.com.siif.fachada;

import java.util.Collection;
import java.util.List;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.exception.NegocioException;

public interface IEntidadFachada {

	public void altaEntidad(Entidad laEntidad) throws NegocioException;

	public List<Entidad> getEntidades();

	public Entidad getEntidad(Long id);

	public boolean existeEntidad(String nombre, Long id);

	public List<Entidad> getEntidadesPorLocalidad(Long idLocalidad);
}
