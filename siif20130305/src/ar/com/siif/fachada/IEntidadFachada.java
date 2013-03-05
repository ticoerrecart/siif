package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.exception.NegocioException;

public interface IEntidadFachada {

	public void altaEntidad(EntidadDTO entidadDTO) throws NegocioException;

	public List<Entidad> getEntidades();

	public Entidad getEntidad(Long id);

	public boolean existeEntidad(String nombre, Long id);

	public List<Entidad> getEntidadesPorLocalidad(Long idLocalidad) throws NegocioException;

	public List<TipoDeEntidad> getTiposDeEntidad();

	public List<TipoDeEntidad> getTiposDeEntidadProductores();

	public List<Entidad> getEntidadesPorTipoDeEntidad(String tipoDeEntidad);

	public List<Entidad> getOficinasForestales();

	public List<EntidadDTO> getOficinasForestalesDTO();

	public List<EntidadDTO> getEntidadesPorTipoDeEntidadDTO(String tipoDeEntidad)throws NegocioException;

	public List<EntidadDTO> getEntidadesDTO();

	public EntidadDTO getEntidadDTO(Long id) throws NegocioException;

	public void modificacionEntidad(EntidadDTO entidad);

	public List<EntidadDTO> getProductoresDTO();

	public boolean existeEntidadConMatricula(Long nroMatricula, Long id);
}
