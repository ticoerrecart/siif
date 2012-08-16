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

import org.jfree.chart.plot.PlotOrientation;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.utils.Fecha;

public class ReportesDAO extends HibernateDaoSupport {

	@SuppressWarnings("deprecation")
	public byte[] pruebaJasper(String path){
		
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "fiscalizacion.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("fiscalizacion", 3);	
			parameters.put("PATH_SUB_REPORTES", path);
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());
			
			//baos = new ByteArrayOutputStream();
			//baos.write(bytes, 0, bytes.length);		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;		
	}	
	
	@SuppressWarnings("deprecation")
	public byte[] generarReporteGuiaForestal(long idGuiaForestal,String path){
		
		byte[] bytes = null;
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "guiaForestal.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("idGuiaForestal", idGuiaForestal);	
			parameters.put("PATH_SUB_REPORTES", path);
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());
			
			//baos = new ByteArrayOutputStream();
			//baos.write(bytes, 0, bytes.length);		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;		
	}	
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path){
		
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
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;	
	}	
	
	public byte[] generarReporteVolumenFiscalizadoPorProductoForestalFecha(String path,String fechaDesde,String fechaHasta){
		
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
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long idProd, String fechaDesde, String fechaHasta, String path){
		
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
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;		
	}	
}