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
	
	public List<Fiscalizacion> recuperarFiscalizacionesConGuiaForestal(long idProductor,String idPeriodo){

		return consultasFiscalizacionDAO.recuperarFiscalizacionesConGuiaForestal(idProductor,idPeriodo);	
	}

	public List<FiscalizacionDTO> recuperarFiscalizacionesConGuiaForestalDTO(long idProductor,String idPeriodo){

		List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
		List<Fiscalizacion> listaFiscalizaciones = consultasFiscalizacionDAO.
													recuperarFiscalizacionesConGuiaForestal(idProductor,idPeriodo);
		
		for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
			listaFiscalizacionesDTO.add(ProviderDTO.getFiscalizacionDTO(fiscalizacion));
		}
		return listaFiscalizacionesDTO;	
	}	

	public List<FiscalizacionDTO> recuperarFiscalizacionesSinGuiaForestalDTO(long idProductor,String idPeriodo){

		List<FiscalizacionDTO> listaFiscalizacionesDTO = new ArrayList<FiscalizacionDTO>();
		List<Fiscalizacion> listaFiscalizaciones = consultasFiscalizacionDAO.
													recuperarFiscalizacionesSinGuiaForestal(idProductor,idPeriodo);
		
		for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
			listaFiscalizacionesDTO.add(ProviderDTO.getFiscalizacionDTO(fiscalizacion));
		}
		return listaFiscalizacionesDTO;
	}	
	
	public List<Fiscalizacion> recuperarFiscalizacionesSinGuiaForestal(long idProductor,String idPeriodo){

		return consultasFiscalizacionDAO.recuperarFiscalizacionesSinGuiaForestal(idProductor,idPeriodo);
	}	
}
