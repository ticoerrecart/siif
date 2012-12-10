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
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class ReportesRecaudacionDAO extends HibernateDaoSupport {

	public byte[] generarReporteRecaudacionPorProductorEntreFechas(String path, String productor, 
															   String fechaDesde,String fechaHasta)
															   throws DataBaseException
	{
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "reporteRecaudacionPorProductorEntreFechas.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("idProductor", new Long(productor));
			parameters.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
			parameters.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (JRException e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateSystemException he) {
			he.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		}
		return bytes;			
	}
	
	public byte[] generarReporteRecaudacionPorProductorPorAnioForestal(String path, String productor, 
			   													   String periodo)throws DataBaseException
	{
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "reporteRecaudacionPorProductorPorAnioForestal.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("idProductor", new Long(productor));
			parameters.put("periodoForestal", periodo);
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (JRException e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateSystemException he) {
			he.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		}
		return bytes;			
	}
	
	public byte[] generarReporteRecaudacionPorProductorPorUbicacion(String path, String productor, String pmf, 
																	   String tranzon, String marcacion)throws DataBaseException
	{
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "reporteRecaudacionPorProductorPorUbicacion.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("idProductor", new Long(productor));
			parameters.put("idPMF", new Long(pmf));
			parameters.put("idTranzon", new Long(tranzon));
			parameters.put("idMarcacion", new Long(marcacion));
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (JRException e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (HibernateSystemException he) {
			he.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
		}
		return bytes;			
	}	
}
