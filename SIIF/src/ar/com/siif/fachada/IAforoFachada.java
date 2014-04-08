package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.AforoDTO;
import ar.com.siif.dto.AforoNuevoDTO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.AforoNuevo;
import ar.com.siif.negocio.exception.NegocioException;

public interface IAforoFachada {

	public void altaAforo(AforoDTO aforo) throws NegocioException;

	public List<Aforo> recuperarAforos();

	public Aforo recuperarAforo(Long id);

	public AforoNuevo recuperarAforoNuevo(Long id);
	
	public AforoNuevo recuperarAforoNuevoBasico();

	public void modificacionAforo(AforoDTO aforoDTO) throws NegocioException;

	public void modificacionAforoNuevo(AforoNuevoDTO aforoDTO);

	public boolean existeAforo(AforoDTO aforo, Long idTipoProducto);

	public List<AforoDTO> recuperarAforosDTO();

	public List<AforoNuevoDTO> recuperarAforosNuevosDTO();

	public AforoDTO recuperarAforoDTO(Long id);

	public AforoNuevoDTO recuperarAforoNuevoDTO(Long id);

	public String getValor(String estado, Long idTipoProducto,
			Long idProdForestal) throws NegocioException;
	
	public String getValorAforoNuevo(String tipoDeAforo, String comercializaEnProvincia)
			throws NegocioException;
}
