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
public class ReportesPorProductoDAO extends HibernateDaoSupport {

	public byte[] generarReporteVolumenPorAnioForestal(String path,
			String volumen) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "volumenFiscalizadoPorProductoPorAnioForestal.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("volumen", volumen);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteVolumenPorProductorEntreFechas(String path,
			String volumen, String productor, String fechaDesde,
			String fechaHasta) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "volumenPorProductoPorProductorEntreFechas.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("volumen", volumen);
		parameters.put("productor", new Long(productor));
		parameters
				.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters
				.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteVolumenPorProductoPorProductorPorUbicacion(
			String path, String volumen, String productor, String periodo,
			String pmf, String tranzon, String marcacion) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "volumenPorProductoPorProductorPorUbicacion.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("volumen", volumen);
		parameters.put("idProductor", new Long(productor));
		parameters.put("periodoForestal", periodo);
		parameters.put("idPMF", new Long(pmf));
		parameters.put("idTranzon", new Long(tranzon));
		parameters.put("idMarcacion", new Long(marcacion));

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}
}
