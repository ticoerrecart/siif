package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.exception.NegocioException;

public interface ILocalidadFachada {

	public List<Localidad> getLocalidades();

	public Localidad getLocalidadPorId(Long id);

	public boolean existeLocalidad(LocalidadDTO localidad);

	public void altaLocalidad(LocalidadDTO localidadDTO) throws NegocioException;
	
	public List<LocalidadDTO> getLocalidadesDTO();
	
	public LocalidadDTO getLocalidadDTOPorId(Long id);
	
	public void modificacionLocalidad(LocalidadDTO localidadDTO) throws NegocioException;
}
