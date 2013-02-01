package ar.com.siif.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.dao.ReportesRecaudacionDAO;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class ReportesRecaudacionFachada implements IReportesRecaudacionFachada {

	private ReportesDAO reportesDAO;
	private ReportesRecaudacionDAO reportesRecaudacionDAO;
	
	public ReportesRecaudacionFachada(){}
	
	public ReportesRecaudacionFachada(ReportesDAO pReportesDAO){
		
		reportesDAO = pReportesDAO;
	}
	
	public byte[] generarReporteRecaudacionPorProductorEntreFechas(String path,String productor, 
																   String fechaDesde, String fechaHasta)
																   throws NegocioException 
	{
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("idProductor", new Long(productor));
			parameters.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
			parameters.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_RECAUDACION_POR_PRODUCTOR_ENTRE_FECHAS, parameters);
			/*return reportesRecaudacionDAO.generarReporteRecaudacionPorProductorEntreFechas(path,productor,
																					fechaDesde,fechaHasta);*/
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}

	public byte[] generarReporteRecaudacionPorProductorPorAnioForestal(String path, String productor, 
																	   String periodo)throws NegocioException
	{
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("idProductor", new Long(productor));
			parameters.put("periodoForestal", periodo);			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_RECAUDACION_POR_PRODUCTOR_POR_ANIO_FORESTAL, parameters);			
			//return reportesRecaudacionDAO.generarReporteRecaudacionPorProductorPorAnioForestal(path,productor,periodo);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteRecaudacionPorProductorPorUbicacion(String path, String productor, String pmf, 
																	String tranzon, String marcacion)throws NegocioException
	{
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("idProductor", new Long(productor));
			parameters.put("idPMF", new Long(pmf));
			parameters.put("idTranzon", new Long(tranzon));
			parameters.put("idMarcacion", new Long(marcacion));			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_RECAUDACION_POR_PRODUCTOR_POR_UBICACION, parameters);
			//return reportesRecaudacionDAO.generarReporteRecaudacionPorProductorPorUbicacion(path,productor,pmf,tranzon,marcacion);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteRecaudacionTotalProductoresEntreFechas(String path, String fechaDesde, String fechaHasta)
																   							throws NegocioException 
	{
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
			parameters.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_RECAUDACION_TOTAL_PRODUCTORES_ENTRE_FECHAS, parameters);
			//return reportesRecaudacionDAO.generarReporteRecaudacionTotalProductoresEntreFechas(path,fechaDesde,fechaHasta);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteRecaudacionPorAnioForestalPorProductor(String path, String productor)throws NegocioException
	{
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("idProductor", new Long(productor));			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_RECAUDACION_POR_ANIO_FORESTAL_POR_PRODUCTOR, parameters);
			//return reportesRecaudacionDAO.generarReporteRecaudacionPorAnioForestalPorProductor(path,productor);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteRecaudacionPorAnioForestalTotalProductores(String path)throws NegocioException
	{
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_RECAUDACION_POR_ANIO_FORESTAL_TOTAL_PRODUCTORES, parameters);
			//return reportesRecaudacionDAO.generarReporteRecaudacionPorAnioForestalTotalProductores(path);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
}
