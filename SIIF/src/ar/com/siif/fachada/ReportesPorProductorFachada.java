package ar.com.siif.fachada;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.dao.ReportesPorProductorDAO;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class ReportesPorProductorFachada implements
		IReportesPorProductorFachada {

	private ReportesPorProductorDAO reportePorProductorDAO;
	private ReportesDAO reportesDAO;
	
	public ReportesPorProductorFachada(){}
	
	public ReportesPorProductorFachada(ReportesDAO pReportesDAO){
		
		this.reportesDAO = pReportesDAO;
	}
	
	public byte[] generarReporteVolumenFiscalizadoTotal(String path, String periodo)throws NegocioException{

		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			
			return reportesDAO.generarReporte(Constantes.REPORTE_VOLUMEN_FISCALIZADO_TOTAL_POR_PRODUCTOR, parameters);			
			
			//return reportePorProductorDAO.generarReporteVolumenFiscalizadoTotal(path,periodo);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	

	public byte[] generarReporteVolumenFiscalizadoPorProductos(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
			return reportesDAO.generarReporte(Constantes.REPORTE_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_POR_PRODUCTO, parameters);			
			
			//return reportePorProductorDAO.generarReporteVolumenFiscalizadoPorProductos(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public byte[] generarReporteVolumenGFBMontosPagos(String path, String periodo, Long idProductor)throws NegocioException{
		
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_VOLUMEN_GFB_MONTOS_PAGOS, parameters);
			//return reportePorProductorDAO.generarReporteVolumenGFBMontosPagos(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteVolumenGFBMontosAdeudados(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_VOLUMEN_GFB_MONTOS_ADEUDADOS, parameters);
			//return reportePorProductorDAO.generarReporteVolumenGFBMontosAdeudados(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteListaBoletasTotales(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
			return reportesDAO.generarReporte(Constantes.REPORTE_LISTADO_BOLETAS_DEPOSITO_TOTALES, parameters);
			//return reportePorProductorDAO.generarReporteListaBoletasTotales(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteListaBoletasPagas(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
			return reportesDAO.generarReporte(Constantes.REPORTE_LISTADO_BOLETAS_DEPOSITO_PAGAS, parameters);			
			//return reportePorProductorDAO.generarReporteListaBoletasPagas(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteListaBoletasImpagas(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
			return reportesDAO.generarReporte(Constantes.REPORTE_LISTADO_BOLETAS_DEPOSITO_IMPAGAS, parameters);						
			//return reportePorProductorDAO.generarReporteListaBoletasImpagas(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	

	public byte[] generarReporteListaValesDevueltos(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_LISTADO_VALES_TRANSPORTE_DEVUELTOS, parameters);
			//return reportePorProductorDAO.generarReporteListaValesDevueltos(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	

	public byte[] generarReporteListaValesEnUso(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			Timestamp ts = new Timestamp(new Date().getTime());
			
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			parameters.put("fechaHoy", ts);			
			
			return reportesDAO.generarReporte(Constantes.REPORTE_LISTADO_VALES_TRANSPORTE_EN_USO, parameters);
			//return reportePorProductorDAO.generarReporteListaValesEnUso(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteListaValesTotales(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			Timestamp ts = new Timestamp(new Date().getTime());			
			
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			parameters.put("fechaHoy", ts);
			
			return reportesDAO.generarReporte(Constantes.REPORTE_LISTADO_VALES_TRANSPORTE_TOTALES, parameters);
			//return reportePorProductorDAO.generarReporteListaValesTotales(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	

	public byte[] generarReporteVolumenPorUbicacion(String path, String periodo, Long idProductor,
								Long idPMF, Long idTranzon, Long idMarcacion)throws NegocioException
	{
		try{
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			parameters.put("idPMF", idPMF);
			parameters.put("idTranzon", idTranzon);
			parameters.put("idMarcacion", idMarcacion);
			
			return reportesDAO.generarReporte(Constantes.VOLUMEN_POR_PRODUCTOR_POR_UBICACION, parameters);
			//return reportePorProductorDAO.generarReporteVolumenPorUbicacion(path,periodo,idProductor,idPMF,idTranzon,idMarcacion);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}		
	
}
