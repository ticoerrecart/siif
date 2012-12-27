package ar.com.siif.fachada;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public class ReportesFachada implements IReportesFachada {

	private ReportesDAO reportesDAO;
		
	public ReportesFachada(){}
	
	public ReportesFachada(ReportesDAO pReportesDAO){
		
		this.reportesDAO = pReportesDAO;
	}
	
	public byte[] pruebaJasper(String path) throws NegocioException{
		
		try{
			return reportesDAO.pruebaJasper(path);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteGuiaForestal(long idGuiaForestal,String path)throws NegocioException{
	
		try{
			return reportesDAO.generarReporteGuiaForestal(idGuiaForestal,path);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path)throws NegocioException{
		
		try{
			return reportesDAO.generarReporteFiscalizacion(idFiscalizacion,path);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	/*public byte[] generarReporteVolumenFiscalizadoPorProductoForestalFecha(String path,
												String fechaDesde,String fechaHasta)throws NegocioException{
		
		try{
			return reportesDAO.generarReporteVolumenFiscalizadoPorProductoForestalFecha(path,fechaDesde,fechaHasta);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long idProd, String fechaDesde, 
																String fechaHasta, String path)throws NegocioException{
		
		try{
			return reportesDAO.generarReporteVolumenFiscalizadoPorProductorYFecha(idProd,fechaDesde,fechaHasta,path);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}*/	
}
