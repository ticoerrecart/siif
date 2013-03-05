package ar.com.siif.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Reporte;

@SuppressWarnings("deprecation")
public class ReportesDAO extends HibernateDaoSupport {

	public byte[] pruebaJasper(String path) throws Exception {
		InputStream input = new FileInputStream(path + File.separatorChar
				+ "fiscalizacion.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fiscalizacion", 3);
		parameters.put("PATH_SUB_REPORTES", path);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteGuiaForestal(long idGuiaForestal, String path)
			throws Exception {
		InputStream input = new FileInputStream(path + File.separatorChar
				+ "guiaForestal.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idGuiaForestal", idGuiaForestal);
		parameters.put("PATH_SUB_REPORTES", path);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteFiscalizacion(String nombreReporte,
			Map<String, Object> parameters) throws Exception {
		InputStream input = obtenerReporte(nombreReporte);

		// InputStream input = new FileInputStream(path + File.separatorChar +
		// "fiscalizacion.jasper");
		//

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		/*
		 * Map<String, Object> parameters = new HashMap<String, Object>();
		 * parameters.put("idFiscalizacion", idFiscalizacion);
		 * parameters.put("PATH_SUB_REPORTES", path);
		 * parameters.put("subRepMuestrasFiscalizaciones",
		 * obtenerReporte("subRepMuestrasFiscalizaciones"));
		 */

		cargarSubReportes(nombreReporte, parameters);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	private InputStream obtenerReporte(String nombreReporte)
			throws SQLException {

		Criteria criteria = getSession().createCriteria(Reporte.class);
		criteria.add(Restrictions.eq("nombreReporte", nombreReporte));

		List<Reporte> lista = criteria.list();
		Reporte r = lista.get(0);

		return r.getArchivoReporte().getBinaryStream();
	}

	private void cargarSubReportes(String nombrePadre,
			Map<String, Object> parameters) throws SQLException {

		Criteria criteria = getSession().createCriteria(Reporte.class);
		criteria.add(Restrictions.eq("nombreReportePadre", nombrePadre));

		List<Reporte> lista = criteria.list();
		for (Reporte reporte : lista) {

			parameters.put(reporte.getNombreReporte(), reporte
					.getArchivoReporte().getBinaryStream());
		}
	}

	public byte[] generarReporte(String nombreReporte,
			Map<String, Object> parameters) throws Exception {
		InputStream input = obtenerReporte(nombreReporte);
		this.cargarSubReportes(nombreReporte, parameters);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}
}
