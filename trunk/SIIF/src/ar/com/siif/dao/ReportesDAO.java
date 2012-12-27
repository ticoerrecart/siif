package ar.com.siif.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.HibernateException;
import org.jfree.chart.plot.PlotOrientation;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class ReportesDAO extends HibernateDaoSupport {

	@SuppressWarnings("deprecation")
	public byte[] pruebaJasper(String path) throws DataBaseException{
		
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "fiscalizacion.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("fiscalizacion", 3);	
			parameters.put("PATH_SUB_REPORTES", path);
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());
			
		} catch (FileNotFoundException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (JRException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		}
		return bytes;		
	}	
	
	@SuppressWarnings("deprecation")
	public byte[] generarReporteGuiaForestal(long idGuiaForestal,String path) throws DataBaseException{
		
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "guiaForestal.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("idGuiaForestal", idGuiaForestal);	
			parameters.put("PATH_SUB_REPORTES", path);
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());

		} catch (FileNotFoundException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (JRException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		}	
		return bytes;		
	}	
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path) throws DataBaseException{
		
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "fiscalizacion.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("idFiscalizacion", idFiscalizacion);	
			parameters.put("PATH_SUB_REPORTES", path);
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());		
			
		} catch (FileNotFoundException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (JRException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		}
		return bytes;	
	}	
	
	/*public byte[] generarReporteVolumenFiscalizadoPorProductoForestalFecha(String path,
									String fechaDesde,String fechaHasta) throws DataBaseException{
		
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "volumenFiscalizadoPorProductoForestalYFechas.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
			parameters.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));			
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());		
			
		} catch (FileNotFoundException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (JRException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		}
		return bytes;
	}
	
	public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long idProd, 
						String fechaDesde, String fechaHasta, String path) throws DataBaseException{
		
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "volumenFiscalizadoPorProductorYFecha.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("idProd", idProd);
			parameters.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
			parameters.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());		
			
		} catch (FileNotFoundException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (JRException e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		}
		return bytes;		
	}	*/
}
