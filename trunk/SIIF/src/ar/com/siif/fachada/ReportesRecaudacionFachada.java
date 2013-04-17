package ar.com.siif.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.negocio.Localizacion;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class ReportesRecaudacionFachada implements IReportesRecaudacionFachada {

	private ReportesDAO reportesDAO;

	public ReportesRecaudacionFachada() {
	}

	public ReportesRecaudacionFachada(ReportesDAO pReportesDAO) {

		reportesDAO = pReportesDAO;
	}

	public byte[] generarReporteRecaudacionPorProductorEntreFechas(String path,
			String productor, String fechaDesde, String fechaHasta)
			throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));
		parameters
				.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters
				.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));

		return reportesDAO.generarReporte(
				Constantes.REPORTE_RECAUDACION_POR_PRODUCTOR_ENTRE_FECHAS,
				parameters);
		/*
		 * return
		 * reportesRecaudacionDAO.generarReporteRecaudacionPorProductorEntreFechas
		 * (path,productor, fechaDesde,fechaHasta);
		 */

	}

	public byte[] generarReporteRecaudacionPorProductorPorAnioForestal(
			String path, String productor, String periodo) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));
		parameters.put("periodoForestal", periodo);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_RECAUDACION_POR_PRODUCTOR_POR_ANIO_FORESTAL,
				parameters);
		// return
		// reportesRecaudacionDAO.generarReporteRecaudacionPorProductorPorAnioForestal(path,productor,periodo);

	}

	public byte[] generarReporteRecaudacionPorProductorPorUbicacion(
			String path, String productor, String area, String pmf, String tranzon,
			String marcacion) throws Exception {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));
		
		Long idLocalizacion = null;
		if ( "-1".equals(marcacion)){
			if ("-1".equals(tranzon)){
				if ("-1".equals(pmf)){
					idLocalizacion = new Long(area);
				} else {
					idLocalizacion = new Long(pmf);
				}
			} else {
				idLocalizacion = new Long(tranzon);
			} 
		} else {
			idLocalizacion = new Long(marcacion);
		}
		
		parameters.put("idLocalizacion", idLocalizacion);
		Localizacion localizacion = (Localizacion)reportesDAO.getHibernateTemplate().get(Localizacion.class, idLocalizacion);
		parameters.put("localizacion",localizacion.getNombreLocalizacion());
		
		return reportesDAO.generarReporte(
				Constantes.REPORTE_RECAUDACION_POR_PRODUCTOR_POR_UBICACION,
				parameters);
		// return
		// reportesRecaudacionDAO.generarReporteRecaudacionPorProductorPorUbicacion(path,productor,pmf,tranzon,marcacion);

	}

	public byte[] generarReporteRecaudacionTotalProductoresEntreFechas(
			String path, String fechaDesde, String fechaHasta) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters
				.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters
				.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));

		return reportesDAO.generarReporte(
				Constantes.REPORTE_RECAUDACION_TOTAL_PRODUCTORES_ENTRE_FECHAS,
				parameters);
		// return
		// reportesRecaudacionDAO.generarReporteRecaudacionTotalProductoresEntreFechas(path,fechaDesde,fechaHasta);

	}

	public byte[] generarReporteRecaudacionPorAnioForestalPorProductor(
			String path, String productor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));

		return reportesDAO.generarReporte(
				Constantes.REPORTE_RECAUDACION_POR_ANIO_FORESTAL_POR_PRODUCTOR,
				parameters);
		// return
		// reportesRecaudacionDAO.generarReporteRecaudacionPorAnioForestalPorProductor(path,productor);

	}

	public byte[] generarReporteRecaudacionPorAnioForestalTotalProductores(
			String path) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);

		return reportesDAO
				.generarReporte(
						Constantes.REPORTE_RECAUDACION_POR_ANIO_FORESTAL_TOTAL_PRODUCTORES,
						parameters);
		// return
		// reportesRecaudacionDAO.generarReporteRecaudacionPorAnioForestalTotalProductores(path);

	}
}
