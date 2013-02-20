package ar.com.siif.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class ReportesPorProductoFachada implements IReportesPorProductoFachada {

	private ReportesDAO reportesDAO;

	public ReportesPorProductoFachada() {
	}

	public ReportesPorProductoFachada(ReportesDAO pReportesDAO) {

		this.reportesDAO = pReportesDAO;
	}

	public byte[] generarReporteVolumenPorAnioForestal(String path,
			String volumen) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("volumen", volumen);

		return reportesDAO.generarReporte(
				Constantes.VOLUMEN_FISCALIZADO_POR_PRODUCTO_POR_ANIO_FORESTAL,
				parameters);
		// return
		// reportePorProductoDAO.generarReporteVolumenPorAnioForestal(path,volumen);

	}

	public byte[] generarReporteVolumenPorProductorEntreFechas(String path,
			String volumen, String productor, String fechaDesde,
			String fechaHasta) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("volumen", volumen);
		parameters.put("productor", new Long(productor));
		parameters
				.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters
				.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));

		return reportesDAO.generarReporte(
				Constantes.VOLUMEN_POR_PRODUCTO_POR_PRODUCTOR_ENTRE_FECHAS,
				parameters);
		/*
		 * return
		 * reportePorProductoDAO.generarReporteVolumenPorProductorEntreFechas
		 * (path,volumen, productor,fechaDesde,fechaHasta);
		 */

	}

	public byte[] generarReporteVolumenPorProductoPorProductorPorUbicacion(
			String path, String volumen, String productor, String periodo,
			String pmf, String tranzon, String marcacion) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("volumen", volumen);
		parameters.put("idProductor", new Long(productor));
		parameters.put("periodoForestal", periodo);
		parameters.put("idPMF", new Long(pmf));
		parameters.put("idTranzon", new Long(tranzon));
		parameters.put("idMarcacion", new Long(marcacion));

		return reportesDAO.generarReporte(
				Constantes.VOLUMEN_POR_PRODUCTO_POR_PRODUCTOR_POR_UBICACION,
				parameters);
		/*
		 * return reportePorProductoDAO.
		 * generarReporteVolumenPorProductoPorProductorPorUbicacion
		 * (path,volumen, productor,periodo,pmf,tranzon,marcacion);
		 */

	}
}
