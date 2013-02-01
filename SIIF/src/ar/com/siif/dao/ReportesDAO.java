package ar.com.siif.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Reporte;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.utils.Constantes;

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
	
	public byte[] generarReporteFiscalizacion(String nombreReporte, Map parameters) throws DataBaseException{
		
		byte[] bytes = null;
		try{
			InputStream input = obtenerReporte(nombreReporte);
			
			//InputStream input = new FileInputStream(path + File.separatorChar + "fiscalizacion.jasper");
			//String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			/*Map parameters = new HashMap();
			parameters.put("idFiscalizacion", idFiscalizacion);	
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("subRepMuestrasFiscalizaciones", obtenerReporte("subRepMuestrasFiscalizaciones"));*/
			
			cargarSubReportes(nombreReporte, parameters);
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());		
			
		//} catch (FileNotFoundException e) {
		//	throw new DataBaseException(Constantes.ERROR_GENERACION_REPORTE);
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
	
	private InputStream obtenerReporte(String nombreReporte) throws SQLException{
		
		Criteria criteria = getSession().createCriteria(Reporte.class);
		criteria.add(Restrictions.eq("nombreReporte", nombreReporte));
			
		List<Reporte> lista = criteria.list();
		Reporte r = lista.get(0);
			
		return r.getArchivoReporte().getBinaryStream();				
	}
	
	private void cargarSubReportes(String nombrePadre, Map parameters) throws SQLException{
		
		Criteria criteria = getSession().createCriteria(Reporte.class);
		criteria.add(Restrictions.eq("nombreReportePadre", nombrePadre));
			
		List<Reporte> lista = criteria.list();		
		for (Reporte reporte : lista) {
			
			parameters.put(reporte.getNombreReporte(), reporte.getArchivoReporte().getBinaryStream());			
		}
	}
	
	public byte[] generarReporte(String nombreReporte, Map parameters) throws DataBaseException{
		
		byte[] bytes = null;
		try{
			InputStream input = obtenerReporte(nombreReporte);
			this.cargarSubReportes(nombreReporte, parameters);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);					
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());		

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
}
