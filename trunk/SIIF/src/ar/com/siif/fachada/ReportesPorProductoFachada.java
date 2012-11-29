package ar.com.siif.fachada;

import ar.com.siif.dao.ReportesPorProductoDAO;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public class ReportesPorProductoFachada implements IReportesPorProductoFachada {

	private ReportesPorProductoDAO reportePorProductoDAO;
	
	public ReportesPorProductoFachada(){}
	
	public ReportesPorProductoFachada(ReportesPorProductoDAO pReportePorProductoDAO){
		
		this.reportePorProductoDAO = pReportePorProductoDAO;
	}	
	
	public byte[] generarReporteVolumenPorAnioForestal(String path, String volumen)throws NegocioException{
		
		try{
			return reportePorProductoDAO.generarReporteVolumenPorAnioForestal(path,volumen);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteVolumenPorProductorEntreFechas(String path, String volumen,
															   String productor, String fechaDesde, 
															   String fechaHasta)throws NegocioException{
		try{
			return reportePorProductoDAO.generarReporteVolumenPorProductorEntreFechas(path,volumen,
																		productor,fechaDesde,fechaHasta);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteVolumenPorProductoPorProductorPorUbicacion(String path, String volumen, String productor, 
																		   String periodo, String pmf, String tranzon, 
																		   String marcacion)throws NegocioException{

		try{
			return reportePorProductoDAO.generarReporteVolumenPorProductoPorProductorPorUbicacion(path,volumen,
																		productor,periodo,pmf,tranzon,marcacion);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
}
