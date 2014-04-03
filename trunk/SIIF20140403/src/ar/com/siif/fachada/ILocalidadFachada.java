package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.dto.LocalidadDestinoDTO;
import ar.com.siif.dto.ProvinciaDestinoDTO;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.LocalidadDestino;
import ar.com.siif.negocio.exception.NegocioException;

public interface ILocalidadFachada {

	public List<Localidad> getLocalidades();

	public Localidad getLocalidadPorId(Long id);

	public boolean existeLocalidad(LocalidadDTO localidad);
	
	public void altaLocalidad(LocalidadDTO localidadDTO)throws NegocioException;
	
	public List<LocalidadDTO> getLocalidadesDTO();
	
	public LocalidadDTO getLocalidadDTOPorId(Long id);
	
	public void modificacionLocalidad(LocalidadDTO localidadDTO)throws NegocioException;
	
	public boolean existeProvincia(ProvinciaDestinoDTO provincia);
	
	public void altaProvincia(ProvinciaDestinoDTO provinciaDTO)throws NegocioException;
	
	public List<ProvinciaDestinoDTO> getProvinciasDTO();
	
	public ProvinciaDestinoDTO getProvinciaDestinoDTOPorId(Long id);
	
	public void modificacionProvinciaDestino(ProvinciaDestinoDTO provinciaDTO)throws NegocioException;
	
	public boolean existeLocalidadDestino(LocalidadDestinoDTO localidad);
	
	public void altaLocalidadDestino(LocalidadDestinoDTO localidadDTO)throws NegocioException;
	
	public List<LocalidadDestinoDTO> getLocalidadesDetinoDTODeProvincia(Long idProvincia)throws NegocioException;
	
	public LocalidadDestinoDTO getLocalidadDestinoDTOPorId(Long id);
	
	public void modificacionLocalidadDestino(LocalidadDestinoDTO localidadDTO)throws NegocioException;
	
	public LocalidadDestino getLocalidadDestinoPorId(Long id);	
}
