package ar.com.siif.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.dao.ReportesPorProductoDAO;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class ReportesPorProductoFachada implements IReportesPorProductoFachada {

	private ReportesDAO reportesDAO;
	private ReportesPorProductoDAO reportePorProductoDAO;
	
	public ReportesPorProductoFachada(){}
	
	public ReportesPorProductoFachada(ReportesDAO pReportesDAO){
		
		this.reportesDAO = pReportesDAO;
	}	
	
	public byte[] generarReporteVolumenPorAnioForestal(String path, String volumen)throws NegocioException{
		
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("volumen", volumen);
			
			return reportesDAO.generarReporte(Constantes.VOLUMEN_FISCALIZADO_POR_PRODUCTO_POR_ANIO_FORESTAL, parameters);
			//return reportePorProductoDAO.generarReporteVolumenPorAnioForestal(path,volumen);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteVolumenPorProductorEntreFechas(String path, String volumen,
															   String productor, String fechaDesde, 
															   String fechaHasta)throws NegocioException{
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("volumen", volumen);
			parameters.put("productor", new Long(productor));
			parameters.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
			parameters.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));			
			
			return reportesDAO.generarReporte(Constantes.VOLUMEN_POR_PRODUCTO_POR_PRODUCTOR_ENTRE_FECHAS, parameters);
			/*return reportePorProductoDAO.generarReporteVolumenPorProductorEntreFechas(path,volumen,
																		productor,fechaDesde,fechaHasta);*/
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}
	
	public byte[] generarReporteVolumenPorProductoPorProductorPorUbicacion(String path, String volumen, String productor, 
																		   String periodo, String pmf, String tranzon, 
																		   String marcacion)throws NegocioException{

		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("volumen", volumen);
			parameters.put("idProductor", new Long(productor));
			parameters.put("periodoForestal", periodo);
			parameters.put("idPMF", new Long(pmf));
			parameters.put("idTranzon", new Long(tranzon));
			parameters.put("idMarcacion", new Long(marcacion));			
			
			return reportesDAO.generarReporte(Constantes.VOLUMEN_POR_PRODUCTO_POR_PRODUCTOR_POR_UBICACION, parameters);
			/*return reportePorProductoDAO.generarReporteVolumenPorProductoPorProductorPorUbicacion(path,volumen,
																		productor,periodo,pmf,tranzon,marcacion);*/
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
}
