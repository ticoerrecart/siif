package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.exception.NegocioException;

public interface IEntidadFachada {

	public void altaEntidad(EntidadDTO entidadDTO) throws NegocioException;

	public List<Entidad> getEntidades()throws NegocioException;

	public Entidad getEntidad(Long id)throws NegocioException;

	public boolean existeEntidad(String nombre, Long id);

	public List<Entidad> getEntidadesPorLocalidad(Long idLocalidad)throws NegocioException;

	public List<TipoDeEntidad> getTiposDeEntidad()throws NegocioException;

	public List<TipoDeEntidad> getTiposDeEntidadProductores()throws NegocioException;
	
	public List<Entidad> getEntidadesPorTipoDeEntidad(String tipoDeEntidad)throws NegocioException;
	
	public List<Entidad> getOficinasForestales()throws NegocioException;
	
	public List<EntidadDTO> getOficinasForestalesDTO()throws NegocioException;
	
	public List<EntidadDTO> getEntidadesPorTipoDeEntidadDTO(String tipoDeEntidad)throws NegocioException;

	public List<EntidadDTO> getEntidadesDTO()throws NegocioException;
	
	public EntidadDTO getEntidadDTO(Long id)throws NegocioException;	
	
	public void modificacionEntidad(EntidadDTO entidad)throws NegocioException ;
}
