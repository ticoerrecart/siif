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
import ar.com.siif.utils.MyLogger;
import ar.com.siif.enums.TipoDocumento;

public class EntidadFachada implements IEntidadFachada {

	private EntidadDAO entidadDAO;
	private ILocalidadFachada localidadFachada;
	
	public EntidadFachada() {

	}

	public EntidadFachada(EntidadDAO laEntidadDAO, ILocalidadFachada pLocalidadFachada) {
		this.entidadDAO = laEntidadDAO;
		this.localidadFachada = pLocalidadFachada;
	}

	public void altaEntidad(EntidadDTO entidadDTO)throws NegocioException {
		try{
			Localidad localidad = localidadFachada.getLocalidadPorId(entidadDTO.getIdLocalidad());
			entidadDAO.altaEntidad(ProviderDominio.getEntidad(entidadDTO,localidad));	
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}		
	}

	public List<Entidad> getEntidades(){

		return entidadDAO.getEntidades();
	}

	public Entidad getEntidad(Long id){

		return entidadDAO.getEntidad(id);	
	}

	public boolean existeEntidad(String nombre, Long id) {
		return entidadDAO.existeEntidad(nombre, id);
	}

	public List<Entidad> getEntidadesPorLocalidad(Long idLocalidad) throws NegocioException{
		try{
			return entidadDAO.getEntidades(idLocalidad);
		
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}			
	}

	public List<TipoDeEntidad> getTiposDeEntidad() {
		List<TipoDeEntidad> tiposDeEntidad = new ArrayList<TipoDeEntidad>();
		//tiposDeEntidad.add(TipoDeEntidad.RN);
		tiposDeEntidad.add(TipoDeEntidad.OBR);
		tiposDeEntidad.add(TipoDeEntidad.PPF);
		tiposDeEntidad.add(TipoDeEntidad.SFDL);
		tiposDeEntidad.add(TipoDeEntidad.EST);

		return tiposDeEntidad;
	}	
	
	public List<TipoDeEntidad> getTiposDeEntidadProductores() {
		List<TipoDeEntidad> tiposDeEntidad = new ArrayList<TipoDeEntidad>();
		tiposDeEntidad.add(TipoDeEntidad.OBR);
		tiposDeEntidad.add(TipoDeEntidad.PPF);
		tiposDeEntidad.add(TipoDeEntidad.EST);
		tiposDeEntidad.add(TipoDeEntidad.SFDL);
		return tiposDeEntidad;
	}
	
	public List<Entidad> getEntidadesPorTipoDeEntidad(String tipoDeEntidad){

		return entidadDAO.getEntidades(TipoDeEntidad.valueOf(tipoDeEntidad));			
	}

	public List<Entidad> getOficinasForestales(){

		return entidadDAO.getOficinasForestales();
			
	}	
	
	public List<EntidadDTO> getOficinasForestalesDTO(){

		List<EntidadDTO> oficianasDTO = new ArrayList<EntidadDTO>();
		List<Entidad> oficinas = entidadDAO.getOficinasForestales();
		
		for (Entidad entidad : oficinas) {
			oficianasDTO.add(ProviderDTO.getEntidadDTO(entidad));
		}
		return oficianasDTO;
			
	}	
	
	public List<EntidadDTO> getEntidadesPorTipoDeEntidadDTO(String tipoDeEntidad) throws NegocioException{

		List<EntidadDTO> entidadesDTO = null;
		try{
			
			entidadesDTO = new ArrayList<EntidadDTO>();
			List<Entidad> entidades = entidadDAO.getEntidades(TipoDeEntidad.valueOf(tipoDeEntidad));
				
			for (Entidad entidad : entidades) {
				entidadesDTO.add(ProviderDTO.getEntidadDTO(entidad));
			}			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
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
	
	public EntidadDTO getEntidadDTO(Long id) throws NegocioException{
		try{
			return ProviderDTO.getEntidadDTO(entidadDAO.getEntidad(id));
	
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}			
	}	
	
	public void modificacionEntidad(EntidadDTO entidadDTO){

		Entidad entidad = entidadDAO.getEntidad(entidadDTO.getId());
		Localidad localidad = localidadFachada.getLocalidadPorId(entidadDTO.getIdLocalidad());
		
		entidadDAO.modificacionEntidad(ProviderDominio.getEntidad(entidad, entidadDTO, localidad));	
	}
	
	public List<EntidadDTO> getProductoresDTO(){

		List<EntidadDTO> listaEntidadesDTO = new ArrayList<EntidadDTO>();
		List<Entidad> listaEntidades = entidadDAO.getProductores();
		
		for (Entidad entidad : listaEntidades) {
			listaEntidadesDTO.add(ProviderDTO.getEntidadDTO(entidad));
		}
		return listaEntidadesDTO;
	}
	
	public List<Entidad> getProductores(){
		return entidadDAO.getProductores();
	}
	
	public boolean existeEntidadConMatricula(Long nroMatricula, Long id, String tipoEntidad){
		return entidadDAO.existeEntidadConMatricula(nroMatricula, id, tipoEntidad);
	}
	
	public List<TipoDocumento> recuperarTiposDocumento(){
		
		List<TipoDocumento> lista = new ArrayList<TipoDocumento>();
		
		for(int i=0;i<TipoDocumento.values().length;i++){
			lista.add(TipoDocumento.values()[i]);
		}
		
		return lista;
	}	
}
