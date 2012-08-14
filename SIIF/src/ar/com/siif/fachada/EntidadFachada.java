package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.EntidadDAO;
import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class EntidadFachada implements IEntidadFachada {

	private EntidadDAO entidadDAO;
	private ILocalidadFachada localidadFachada;
	
	public EntidadFachada() {

	}

	public EntidadFachada(EntidadDAO laEntidadDAO, ILocalidadFachada pLocalidadFachada) {
		this.entidadDAO = laEntidadDAO;
		this.localidadFachada = pLocalidadFachada;
	}

	public void altaEntidad(EntidadDTO entidadDTO) throws NegocioException {
		
		Localidad localidad = localidadFachada.getLocalidadPorId(entidadDTO.getIdLocalidad());
		entidadDAO.altaEntidad(ProviderDominio.getEntidad(entidadDTO,localidad));
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
		tiposDeEntidad.add(TipoDeEntidad.RN);
		tiposDeEntidad.add(TipoDeEntidad.OBR);
		tiposDeEntidad.add(TipoDeEntidad.PPF);

		return tiposDeEntidad;
	}	
	
	public List<TipoDeEntidad> getTiposDeEntidadProductores() {
		List<TipoDeEntidad> tiposDeEntidad = new ArrayList<TipoDeEntidad>();
		tiposDeEntidad.add(TipoDeEntidad.OBR);
		tiposDeEntidad.add(TipoDeEntidad.PPF);

		return tiposDeEntidad;
	}
	
	public List<Entidad> getEntidadesPorTipoDeEntidad(String tipoDeEntidad) {
		return entidadDAO.getEntidades(TipoDeEntidad.valueOf(tipoDeEntidad));
	}

	public List<Entidad> getOficinasForestales()throws NegocioException{
		try{
			return entidadDAO.getOficinasForestales();
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public List<EntidadDTO> getOficinasForestalesDTO()throws NegocioException{
		
		try{
			List<EntidadDTO> oficianasDTO = new ArrayList<EntidadDTO>();
			List<Entidad> oficinas = entidadDAO.getOficinasForestales();
			
			for (Entidad entidad : oficinas) {
				oficianasDTO.add(ProviderDTO.getEntidadDTO(entidad));
			}
			return oficianasDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public List<EntidadDTO> getEntidadesPorTipoDeEntidadDTO(String tipoDeEntidad){
		
		List<EntidadDTO> entidadesDTO = new ArrayList<EntidadDTO>();
		List<Entidad> entidades = entidadDAO.getEntidades(TipoDeEntidad.valueOf(tipoDeEntidad));
			
		for (Entidad entidad : entidades) {
			entidadesDTO.add(ProviderDTO.getEntidadDTO(entidad));
		}
		return entidadesDTO;			
	}	
	
	public List<EntidadDTO> getEntidadesDTO(){
		
		List<EntidadDTO> listaEntidadesDTO = new ArrayList<EntidadDTO>();
		List<Entidad> listaEntidades = entidadDAO.getEntidades();
		
		for (Entidad entidad : listaEntidades) {
			listaEntidadesDTO.add(ProviderDTO.getEntidadDTO(entidad));
		}
		
		return listaEntidadesDTO;
	}	
	
	public EntidadDTO getEntidadDTO(Long id){
		
		return ProviderDTO.getEntidadDTO(entidadDAO.getEntidad(id));
	}	
	
	public void modificacionEntidad(EntidadDTO entidadDTO) throws NegocioException {
		
		Entidad entidad = entidadDAO.getEntidad(entidadDTO.getId());
		Localidad localidad = localidadFachada.getLocalidadPorId(entidadDTO.getIdLocalidad());
		
		entidadDAO.modificacionEntidad(ProviderDominio.getEntidad(entidad, entidadDTO, localidad));
	}
}
