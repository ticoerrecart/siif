package ar.com.siif.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
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
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class ReportesPorProductorDAO extends HibernateDaoSupport {

	public byte[] generarReporteVolumenFiscalizadoTotal(String path, String periodo)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "volumenFiscalizadoTotalPorProductor.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			
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
	
	public byte[] generarReporteVolumenFiscalizadoPorProductos(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "volumenFiscalizadoPorProductorPorProductos.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
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
	
	public byte[] generarReporteVolumenGFBMontosPagos(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "volumenGFBMontosPagos.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
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

	public byte[] generarReporteVolumenGFBMontosAdeudados(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "volumenGFBMontosAdeudados.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
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

	public byte[] generarReporteListaBoletasTotales(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "listadoBoletasDepositoTotales.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
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

	public byte[] generarReporteListaBoletasPagas(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "listadoBoletasDepositoPagas.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
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

	public byte[] generarReporteListaBoletasImpagas(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "listadoBoletasDepositoImpagas.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
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

	public byte[] generarReporteListaValesDevueltos(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "listadoValesTransporteDevueltos.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			
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

	public byte[] generarReporteListaValesEnUso(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "listadoValesTransporteEnUso.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			Timestamp ts = new Timestamp(new Date().getTime());
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			parameters.put("fechaHoy", ts);
			
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
	
	public byte[] generarReporteListaValesTotales(String path, String periodo, Long idProductor)throws DataBaseException{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "listadoValesTransporteTotales.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			Timestamp ts = new Timestamp(new Date().getTime());
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			parameters.put("fechaHoy", ts);
			
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

	public byte[] generarReporteVolumenPorUbicacion(String path, String periodo, Long idProductor,
			 				Long idPMF, Long idTranzon, Long idMarcacion)throws DataBaseException
	{

		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "volumenPorProductorPorUbicacion.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
						
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("PATH_SUB_REPORTES", path);
			parameters.put("periodo", periodo);
			parameters.put("idProductor", idProductor);
			parameters.put("idPMF", idPMF);
			parameters.put("idTranzon", idTranzon);
			parameters.put("idMarcacion", idMarcacion);
			
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
}
