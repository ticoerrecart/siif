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
	
	public byte[] generarReporteRecaudacionPorProductorPorUbicacion(String path, String productor, String pmf, 
																	String tranzon, String marcacion)throws NegocioException
	{
		try{
			return reportesRecaudacionDAO.generarReporteRecaudacionPorProductorPorUbicacion(path,productor,pmf,tranzon,marcacion);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteRecaudacionTotalProductoresEntreFechas(String path, String fechaDesde, String fechaHasta)
																   							throws NegocioException 
	{
		try{
		return reportesRecaudacionDAO.generarReporteRecaudacionTotalProductoresEntreFechas(path,fechaDesde,fechaHasta);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteRecaudacionPorAnioForestalPorProductor(String path, String productor)throws NegocioException
	{
		try{
			return reportesRecaudacionDAO.generarReporteRecaudacionPorAnioForestalPorProductor(path,productor);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteRecaudacionPorAnioForestalTotalProductores(String path)throws NegocioException
	{
		try{
			return reportesRecaudacionDAO.generarReporteRecaudacionPorAnioForestalTotalProductores(path);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
}
