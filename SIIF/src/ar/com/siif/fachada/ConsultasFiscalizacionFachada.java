package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dao.ConsultasFiscalizacionDAO;
import ar.com.siif.negocio.Fiscalizacion;

public class ConsultasFiscalizacionFachada implements IConsultasFiscalizacionFachada {

	private ConsultasFiscalizacionDAO consultasFiscalizacionDAO;
	
	public ConsultasFiscalizacionFachada(){}
	
	public ConsultasFiscalizacionFachada(ConsultasFiscalizacionDAO pConsultasFiscalizacionDAO){
		
		this.consultasFiscalizacionDAO = pConsultasFiscalizacionDAO;
	}
	
	public List<Fiscalizacion> recuperarFiscalizacionesConGuiaForestal(long idProductor){
		
		return consultasFiscalizacionDAO.recuperarFiscalizacionesConGuiaForestal(idProductor);
	}
	
	public List<Fiscalizacion> recuperarFiscalizacionesSinGuiaForestal(long idProductor){
		
		return consultasFiscalizacionDAO.recuperarFiscalizacionesSinGuiaForestal(idProductor);
	}	
}
