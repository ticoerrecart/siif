package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.LocalidadDAO;
import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.dto.LocalidadDestinoDTO;
import ar.com.siif.dto.ProvinciaDestinoDTO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.LocalidadDestino;
import ar.com.siif.negocio.ProvinciaDestino;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class LocalidadFachada implements ILocalidadFachada {

	private LocalidadDAO localidadDAO;

	public LocalidadFachada() {
	}

	public LocalidadFachada(LocalidadDAO laLocalidaDAO) {
		this.localidadDAO = laLocalidaDAO;
	}

	public List<Localidad> getLocalidades(){

		return localidadDAO.getLocalidades();			
	}

	public Localidad getLocalidadPorId(Long id) {

		return localidadDAO.getLocalidadPorId(id);			
	}

	public boolean existeLocalidad(LocalidadDTO localidad) {
		return localidadDAO.existeLocalidad(localidad.getNombre(),localidad.getId());
	}

	public void altaLocalidad(LocalidadDTO localidadDTO) throws NegocioException{

		localidadDAO.alta_modficacion_Localidad(ProviderDominio.getLocalidad(localidadDTO));			
	}
	
	public List<LocalidadDTO> getLocalidadesDTO(){

		List<LocalidadDTO> localidadesDTO = new ArrayList<LocalidadDTO>();
		List<Localidad> localidades = localidadDAO.getLocalidades();
		
		for (Localidad localidad : localidades) {
			localidadesDTO.add(ProviderDTO.getLocalidadDTO(localidad));
		}
		
		return localidadesDTO;		
	}	
	
	public LocalidadDTO getLocalidadDTOPorId(Long id){

		Localidad localidad = localidadDAO.getLocalidadPorId(id);
		return ProviderDTO.getLocalidadDTO(localidad);			
	}
	
	public void modificacionLocalidad(LocalidadDTO localidadDTO) throws NegocioException{

		Localidad localidad = localidadDAO.getLocalidadPorId(localidadDTO.getId());	
		localidadDAO.alta_modficacion_Localidad(ProviderDominio.getLocalidad(localidad,localidadDTO));		
	}
	
	public boolean existeProvincia(ProvinciaDestinoDTO provincia){
		
		return localidadDAO.existeProvincia(provincia.getNombre(),provincia.getId());
	}	
	
	public void altaProvincia(ProvinciaDestinoDTO provinciaDTO) throws NegocioException{

		localidadDAO.alta_modficacion_Provincia(ProviderDominio.getProvincia(provinciaDTO));	
	}
	
	public List<ProvinciaDestinoDTO> getProvinciasDTO(){

		List<ProvinciaDestinoDTO> provinciasDTO = new ArrayList<ProvinciaDestinoDTO>();
		List<ProvinciaDestino> provincias = localidadDAO.getProvincias();
		
		for (ProvinciaDestino provincia : provincias) {
			provinciasDTO.add(ProviderDTO.getProvinciaDestinoDTO(provincia));
		}
		
		return provinciasDTO;
	}	
	
	public ProvinciaDestinoDTO getProvinciaDestinoDTOPorId(Long id){

		ProvinciaDestino provincia = localidadDAO.getProvinciaDestinoPorId(id);
		return ProviderDTO.getProvinciaDestinoDTO(provincia);
	}
	
	public void modificacionProvinciaDestino(ProvinciaDestinoDTO provinciaDTO) throws NegocioException{

		ProvinciaDestino provincia = localidadDAO.getProvinciaDestinoPorId(provinciaDTO.getId());	
		localidadDAO.alta_modficacion_Provincia(ProviderDominio.getProvincia(provincia,provinciaDTO));		
	}
	
	public boolean existeLocalidadDestino(LocalidadDestinoDTO localidad){
		
		return localidadDAO.existeLocalidadDestino(localidad.getNombre(),localidad.getId(),localidad.getProvinciaDestinoDTO().getId());		
	}	
	
	public void altaLocalidadDestino(LocalidadDestinoDTO localidadDTO) throws NegocioException{

		ProvinciaDestino provincia = localidadDAO.getProvinciaDestinoPorId(localidadDTO.getProvinciaDestinoDTO().getId());
		localidadDAO.alta_modficacion_LocalidadDestino(ProviderDominio.getLocalidadDestino(localidadDTO,provincia));		
	}
	
	public List<LocalidadDestinoDTO> getLocalidadesDetinoDTODeProvincia(Long idProvincia){

		List<LocalidadDestinoDTO> localidadesDTO = new ArrayList<LocalidadDestinoDTO>();
		List<LocalidadDestino> localidades = localidadDAO.getLocalidadesDeProvincia(idProvincia);
			
		for (LocalidadDestino localidad : localidades) {
			localidadesDTO.add(ProviderDTO.getLocalidadDestinoDTO(localidad));
		}
		return localidadesDTO;			
	}
	
	public LocalidadDestinoDTO getLocalidadDestinoDTOPorId(Long id){

		LocalidadDestino localidad = localidadDAO.getLocalidadDestinoPorId(id);
		return ProviderDTO.getLocalidadDestinoDTO(localidad);		
	}
	
	public void modificacionLocalidadDestino(LocalidadDestinoDTO localidadDTO) throws NegocioException{

		LocalidadDestino localidad = localidadDAO.getLocalidadDestinoPorId(localidadDTO.getId());
		ProvinciaDestino provincia = localidadDAO.getProvinciaDestinoPorId(localidadDTO.getProvinciaDestinoDTO().getId());
		localidadDAO.alta_modficacion_LocalidadDestino(ProviderDominio.getLocalidadDestino(localidadDTO,localidad,provincia));			
	}	
	
	public LocalidadDestino getLocalidadDestinoPorId(Long id){

		return localidadDAO.getLocalidadDestinoPorId(id);		
	}	
}
