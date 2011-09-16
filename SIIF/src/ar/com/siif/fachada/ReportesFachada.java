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
	
	public byte[] generarReporteGuiaForestal(int idGuiaForestal,String path){
	
		return reportesDAO.generarReporteGuiaForestal(idGuiaForestal,path);
	}	
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path){
		
		return reportesDAO.generarReporteFiscalizacion(idFiscalizacion,path);
	}	
	
	public byte[] generarReporteVolumenFiscalizadoPorProducto(String path){
		
		return reportesDAO.generarReporteVolumenFiscalizadoPorProducto(path);
	}	
}
