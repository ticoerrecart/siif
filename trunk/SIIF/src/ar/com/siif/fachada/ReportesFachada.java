package ar.com.siif.fachada;

import ar.com.siif.dao.ReportesDAO;

public class ReportesFachada implements IReportesFachada {

	private ReportesDAO reportesDAO;
		
	public ReportesFachada(){}
	
	public ReportesFachada(ReportesDAO pReportesDAO){
		
		this.reportesDAO = pReportesDAO;
	}
	
	public byte[] pruebaJasper(String path){
		
		return reportesDAO.pruebaJasper(path);
	}
	
	public byte[] generarReporteGuiaForestal(long idGuiaForestal,String path){
	
		return reportesDAO.generarReporteGuiaForestal(idGuiaForestal,path);
	}	
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path){
		
		return reportesDAO.generarReporteFiscalizacion(idFiscalizacion,path);
	}	
	
	public byte[] generarReporteVolumenFiscalizadoPorProductoForestalFecha(String path,String fechaDesde,String fechaHasta){
		
		return reportesDAO.generarReporteVolumenFiscalizadoPorProductoForestalFecha(path,fechaDesde,fechaHasta);
	}	
	
	public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long idProd, String fechaDesde, String fechaHasta, String path){
		
		return reportesDAO.generarReporteVolumenFiscalizadoPorProductorYFecha(idProd,fechaDesde,fechaHasta,path);
	}	
}
