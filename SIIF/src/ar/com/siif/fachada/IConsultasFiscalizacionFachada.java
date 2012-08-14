package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.exception.NegocioException;

public interface IConsultasFiscalizacionFachada {

	public List<Fiscalizacion> recuperarFiscalizacionesConGuiaForestal(long idProductor)throws NegocioException;
	
	public List<FiscalizacionDTO> recuperarFiscalizacionesConGuiaForestalDTO(long idProductor)throws NegocioException;
	
	public List<FiscalizacionDTO> recuperarFiscalizacionesSinGuiaForestalDTO(long idProductor) throws NegocioException;
	
	public List<Fiscalizacion> recuperarFiscalizacionesSinGuiaForestal(long idProductor)throws NegocioException;
}
