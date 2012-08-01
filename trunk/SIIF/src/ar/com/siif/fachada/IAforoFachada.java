package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.AforoDTO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;

public interface IAforoFachada {

	public void altaAforo(AforoDTO aforo) throws NegocioException;

	public List<Aforo> recuperarAforos();

	public Aforo recuperarAforo(Long id);

	public void modificacionAforo(AforoDTO aforoDTO) throws NegocioException;

	public boolean existeAforo(AforoDTO aforo, Long idTipoProducto);
	
	public List<AforoDTO> recuperarAforosDTO();
	
	public AforoDTO recuperarAforoDTO(Long id);
}
