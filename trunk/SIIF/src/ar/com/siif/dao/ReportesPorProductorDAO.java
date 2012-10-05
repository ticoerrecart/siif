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
}
