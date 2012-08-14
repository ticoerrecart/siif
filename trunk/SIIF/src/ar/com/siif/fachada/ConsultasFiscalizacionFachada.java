package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.ConsultasFiscalizacionDAO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;

public class ConsultasFiscalizacionFachada implements IConsultasFiscalizacionFachada {

	private ConsultasFiscalizacionDAO consultasFiscalizacionDAO;
	
	public ConsultasFiscalizacionFachada(){}
	
	public ConsultasFiscalizacionFachada(ConsultasFiscalizacionDAO pConsultasFiscalizacionDAO){
		
		this.consultasFiscalizacionDAO = pConsultasFiscalizacionDAO;
	}
	
	public List<Fiscalizacion> recuperarFiscalizacionesConGuiaForestal(long idProductor) throws NegocioException{
		try{
			return consultasFiscalizacionDAO.recuperarFiscalizacionesConGuiaForestal(idProductor);
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}			
	}

	public List<FiscalizacionDTO> recuperarFiscalizacionesConGuiaForestalDTO(long idProductor) throws NegocioException{
		
		try{
			List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
			List<Fiscalizacion> listaFiscalizaciones = consultasFiscalizacionDAO.
														recuperarFiscalizacionesConGuiaForestal(idProductor);
			
			for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
				listaFiscalizacionesDTO.add(ProviderDTO.getFiscalizacionDTO(fiscalizacion));
			}
			return listaFiscalizacionesDTO;
			
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}			
	}	

	public List<FiscalizacionDTO> recuperarFiscalizacionesSinGuiaForestalDTO(long idProductor) throws NegocioException{
		
		try{
			List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
			List<Fiscalizacion> listaFiscalizaciones = consultasFiscalizacionDAO.
														recuperarFiscalizacionesSinGuiaForestal(idProductor);
			
			for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
				listaFiscalizacionesDTO.add(ProviderDTO.getFiscalizacionDTO(fiscalizacion));
			}
			return listaFiscalizacionesDTO;
		
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}
	}	
	
	public List<Fiscalizacion> recuperarFiscalizacionesSinGuiaForestal(long idProductor) throws NegocioException{
		
		try{
			return consultasFiscalizacionDAO.recuperarFiscalizacionesSinGuiaForestal(idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}	
}
