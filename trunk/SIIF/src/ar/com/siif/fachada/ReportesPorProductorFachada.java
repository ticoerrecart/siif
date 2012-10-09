package ar.com.siif.fachada;

import ar.com.siif.dao.ReportesPorProductorDAO;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public class ReportesPorProductorFachada implements
		IReportesPorProductorFachada {

	private ReportesPorProductorDAO reportePorProductorDAO;
	
	public ReportesPorProductorFachada(){}
	
	public ReportesPorProductorFachada(ReportesPorProductorDAO pReportePorProductorDAO){
		
		this.reportePorProductorDAO = pReportePorProductorDAO;
	}
	
	public byte[] generarReporteVolumenFiscalizadoTotal(String path, String periodo)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteVolumenFiscalizadoTotal(path,periodo);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	

	public byte[] generarReporteVolumenFiscalizadoPorProductos(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteVolumenFiscalizadoPorProductos(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}		
}
