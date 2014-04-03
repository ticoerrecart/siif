package ar.com.siif.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings("deprecation")
public class ReportesPorProductorDAO extends HibernateDaoSupport {

	public byte[] generarReporteVolumenFiscalizadoTotal(String path,
			String periodo) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "volumenFiscalizadoTotalPorProductor.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteVolumenFiscalizadoPorProductos(String path,
			String periodo, Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "volumenFiscalizadoPorProductorPorProductos.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteVolumenGFBMontosPagos(String path,
			String periodo, Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "volumenGFBMontosPagos.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteVolumenGFBMontosAdeudados(String path,
			String periodo, Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "volumenGFBMontosAdeudados.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteListaBoletasTotales(String path,
			String periodo, Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "listadoBoletasDepositoTotales.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteListaBoletasPagas(String path, String periodo,
			Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "listadoBoletasDepositoPagas.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteListaBoletasImpagas(String path,
			String periodo, Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "listadoBoletasDepositoImpagas.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteListaValesDevueltos(String path,
			String periodo, Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "listadoValesTransporteDevueltos.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteListaValesEnUso(String path, String periodo,
			Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "listadoValesTransporteEnUso.jasper");

		Timestamp ts = new Timestamp(new Date().getTime());

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);
		parameters.put("fechaHoy", ts);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteListaValesTotales(String path, String periodo,
			Long idProductor) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "listadoValesTransporteTotales.jasper");

		Timestamp ts = new Timestamp(new Date().getTime());

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);
		parameters.put("fechaHoy", ts);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}

	public byte[] generarReporteVolumenPorUbicacion(String path,
			String periodo, Long idProductor, Long idPMF, Long idTranzon,
			Long idMarcacion) throws Exception {

		InputStream input = new FileInputStream(path + File.separatorChar
				+ "volumenPorProductorPorUbicacion.jasper");

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);
		parameters.put("idPMF", idPMF);
		parameters.put("idTranzon", idTranzon);
		parameters.put("idMarcacion", idMarcacion);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());

	}
}
