package ar.com.siif.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localizacion;
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
			String path, String volumen,String productor, String periodo,
			String pmf, String tranzon, String marcacion, String area ) throws Exception {

		
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("volumen", volumen);
		parameters.put("periodoForestal", periodo);

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
		
		parameters.put("idLoc", idLocalizacion);
		Localizacion localizacion = (Localizacion)reportesDAO.getHibernateTemplate().get(Localizacion.class, idLocalizacion);
		parameters.put("localizacion",localizacion.getNombreLocalizacion());
		
		Entidad entidad = (Entidad)reportesDAO.getHibernateTemplate().get(Entidad.class, new Long(productor));
		
		parameters.put("tipoEntidad", entidad.getTipoEntidad());
		parameters.put("productorForestal", entidad.getNombre());
		
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
