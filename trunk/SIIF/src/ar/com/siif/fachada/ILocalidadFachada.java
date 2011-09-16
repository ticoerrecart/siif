package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.exception.NegocioException;

public interface ILocalidadFachada {

	public List<Localidad> getLocalidades();

	public Localidad getLocalidadPorId(Long id);

	public boolean existeLocalidad(String nombre, Long id);

	public void altaLocalidad(Localidad laLocalidad) throws NegocioException;
}
