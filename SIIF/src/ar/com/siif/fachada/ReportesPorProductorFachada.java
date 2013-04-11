package ar.com.siif.fachada;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localizacion;
import ar.com.siif.utils.Constantes;

public class ReportesPorProductorFachada implements
		IReportesPorProductorFachada {

	private ReportesDAO reportesDAO;

	public ReportesPorProductorFachada() {
	}

	public ReportesPorProductorFachada(ReportesDAO pReportesDAO) {
		this.reportesDAO = pReportesDAO;
	}

	public byte[] generarReporteVolumenFiscalizadoTotal(String path,
			String periodo) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_VOLUMEN_FISCALIZADO_TOTAL_POR_PRODUCTOR,
				parameters);

		// return
		// reportePorProductorDAO.generarReporteVolumenFiscalizadoTotal(path,periodo);

	}

	public byte[] generarReporteVolumenFiscalizadoPorProductos(String path,
			String periodo, Long idProductor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return reportesDAO
				.generarReporte(
						Constantes.REPORTE_VOLUMEN_FISCALIZADO_POR_PRODUCTOR_POR_PRODUCTO,
						parameters);

		// return
		// reportePorProductorDAO.generarReporteVolumenFiscalizadoPorProductos(path,periodo,idProductor);

	}

	public byte[] generarReporteVolumenGFBMontosPagos(String path,
			String periodo, Long idProductor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_VOLUMEN_GFB_MONTOS_PAGOS, parameters);
		// return
		// reportePorProductorDAO.generarReporteVolumenGFBMontosPagos(path,periodo,idProductor);

	}

	public byte[] generarReporteVolumenGFBMontosAdeudados(String path,
			String periodo, Long idProductor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_VOLUMEN_GFB_MONTOS_ADEUDADOS, parameters);
		// return
		// reportePorProductorDAO.generarReporteVolumenGFBMontosAdeudados(path,periodo,idProductor);

	}

	public byte[] generarReporteListaBoletasTotales(String path,
			String periodo, Long idProductor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return reportesDAO
				.generarReporte(
						Constantes.REPORTE_LISTADO_BOLETAS_DEPOSITO_TOTALES,
						parameters);
		// return
		// reportePorProductorDAO.generarReporteListaBoletasTotales(path,periodo,idProductor);

	}

	public byte[] generarReporteListaBoletasPagas(String path, String periodo,
			Long idProductor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_LISTADO_BOLETAS_DEPOSITO_PAGAS, parameters);
		// return
		// reportePorProductorDAO.generarReporteListaBoletasPagas(path,periodo,idProductor);

	}

	public byte[] generarReporteListaBoletasImpagas(String path,
			String periodo, Long idProductor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return reportesDAO
				.generarReporte(
						Constantes.REPORTE_LISTADO_BOLETAS_DEPOSITO_IMPAGAS,
						parameters);
		// return
		// reportePorProductorDAO.generarReporteListaBoletasImpagas(path,periodo,idProductor);

	}

	public byte[] generarReporteListaValesDevueltos(String path,
			String periodo, Long idProductor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_LISTADO_VALES_TRANSPORTE_DEVUELTOS,
				parameters);
		// return
		// reportePorProductorDAO.generarReporteListaValesDevueltos(path,periodo,idProductor);

	}

	public byte[] generarReporteListaValesEnUso(String path, String periodo,
			Long idProductor) throws Exception {

		Timestamp ts = new Timestamp(new Date().getTime());

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);
		parameters.put("fechaHoy", ts);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_LISTADO_VALES_TRANSPORTE_EN_USO, parameters);
		// return
		// reportePorProductorDAO.generarReporteListaValesEnUso(path,periodo,idProductor);

	}

	public byte[] generarReporteListaValesTotales(String path, String periodo,
			Long idProductor) throws Exception {

		Timestamp ts = new Timestamp(new Date().getTime());

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);
		parameters.put("fechaHoy", ts);

		return reportesDAO
				.generarReporte(
						Constantes.REPORTE_LISTADO_VALES_TRANSPORTE_TOTALES,
						parameters);
		// return
		// reportePorProductorDAO.generarReporteListaValesTotales(path,periodo,idProductor);

	}

	public byte[] generarReporteVolumenPorUbicacion(String path,
			String periodo, Long idProductor, Long idPMF, Long idTranzon,
			Long idMarcacion, Long idArea) throws Exception {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		
		boolean esPlan = true;
		
		Long idLocalizacion = null;
		if (idMarcacion == -1){
			if (idTranzon == -1){
				if (idPMF == -1){
					idLocalizacion = idArea;
					esPlan = false;
				} else {
					idLocalizacion = idPMF;
				}
			} else {
				idLocalizacion = idTranzon;
			} 
		} else {
			idLocalizacion = idMarcacion;
		}
		
		parameters.put("idLocalizacion", idLocalizacion);
		parameters.put("porPlan", esPlan);
		
		Localizacion localizacion = (Localizacion)reportesDAO.getHibernateTemplate().get(Localizacion.class, idLocalizacion);
		Entidad entidad = (Entidad)reportesDAO.getHibernateTemplate().get(Entidad.class, idProductor);
		
		parameters.put("tipoEntidad", entidad.getTipoEntidad());
		parameters.put("nombreProductor", entidad.getNombre());
		parameters.put("porPlan", esPlan);
		
		parameters.put("nombreLocalizacion", localizacion.getNombreLocalizacion());
		
		
		return reportesDAO.generarReporte(
				Constantes.VOLUMEN_POR_PRODUCTOR_POR_UBICACION, parameters);
		// return
		// reportePorProductorDAO.generarReporteVolumenPorUbicacion(path,periodo,idProductor,idPMF,idTranzon,idMarcacion);

	}

}
