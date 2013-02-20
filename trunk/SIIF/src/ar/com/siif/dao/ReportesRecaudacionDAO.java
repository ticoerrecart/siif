package ar.com.siif.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.utils.Fecha;

@SuppressWarnings("deprecation")
public class ReportesRecaudacionDAO extends HibernateDaoSupport {

	public byte[] generarReporteRecaudacionPorProductorEntreFechas(String path,
			String productor, String fechaDesde, String fechaHasta)
			throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "reporteRecaudacionPorProductorEntreFechas.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));
		parameters
				.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters
				.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteRecaudacionPorProductorPorAnioForestal(
			String path, String productor, String periodo) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "reporteRecaudacionPorProductorPorAnioForestal.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));
		parameters.put("periodoForestal", periodo);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteRecaudacionPorProductorPorUbicacion(
			String path, String productor, String pmf, String tranzon,
			String marcacion) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "reporteRecaudacionPorProductorPorUbicacion.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));
		parameters.put("idPMF", new Long(pmf));
		parameters.put("idTranzon", new Long(tranzon));
		parameters.put("idMarcacion", new Long(marcacion));

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteRecaudacionTotalProductoresEntreFechas(
			String path, String fechaDesde, String fechaHasta) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "reporteRecaudacionTotalProductoresEntreFechas.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters
				.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters
				.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteRecaudacionPorAnioForestalPorProductor(
			String path, String productor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "reporteRecaudacionPorAnioForestalPorProductor.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteRecaudacionPorAnioForestalTotalProductores(
			String path) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "reporteRecaudacionPorAnioForestalTotalProductores.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}
}
