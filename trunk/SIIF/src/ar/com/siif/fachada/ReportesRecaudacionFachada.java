package ar.com.siif.fachada;

import ar.com.siif.dao.ReportesRecaudacionDAO;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public class ReportesRecaudacionFachada implements IReportesRecaudacionFachada {

	private ReportesRecaudacionDAO reportesRecaudacionDAO;
	
	public ReportesRecaudacionFachada(){}
	
	public ReportesRecaudacionFachada(ReportesRecaudacionDAO pReportesRecaudacionDAO){
		
		reportesRecaudacionDAO = pReportesRecaudacionDAO;
	}
	
	public byte[] generarReporteRecaudacionPorProductorEntreFechas(String path,String productor, 
																   String fechaDesde, String fechaHasta)
																   throws NegocioException 
	{
		try{
			return reportesRecaudacionDAO.generarReporteRecaudacionPorProductorEntreFechas(path,productor,
																					fechaDesde,fechaHasta);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}

	public byte[] generarReporteRecaudacionPorProductorPorAnioForestal(String path, String productor, 
																	   String periodo)throws NegocioException
	{
		try{
			return reportesRecaudacionDAO.generarReporteRecaudacionPorProductorPorAnioForestal(path,productor,periodo);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
}
