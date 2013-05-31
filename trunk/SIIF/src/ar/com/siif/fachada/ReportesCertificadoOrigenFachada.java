package ar.com.siif.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class ReportesCertificadoOrigenFachada implements
		IReportesCertificadoOrigenFachada {

	private ReportesDAO reportesDAO;

	public ReportesCertificadoOrigenFachada() {
	}

	public ReportesCertificadoOrigenFachada(ReportesDAO pReportesDAO) {

		reportesDAO = pReportesDAO;
	}
	
	public byte[] generarReporteCertificadosOrigenPorProductorEntreFechas(String path,
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
				Constantes.REPORTE_CERTIFICADOS_ORIGEN_POR_PRODUCTOR_ENTRE_FECHAS,
				parameters);
	}	
	
	public byte[] generarReporteCertificadosOrigenTotalProductoresEntreFechas(String path,
			String fechaDesde, String fechaHasta)throws Exception{

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));

		return reportesDAO.generarReporte(
				Constantes.REPORTE_CERTIFICADOS_ORIGEN_TOTAL_PRODUCTORES_ENTRE_FECHAS,
				parameters);		
	}
	
	public byte[] generarReporteCertificadosOrigenTotalExportadoresEntreFechas(String path,
			String fechaDesde, String fechaHasta)throws Exception{

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));

		return reportesDAO.generarReporte(
				Constantes.REPORTE_CERTIFICADOS_ORIGEN_TOTAL_EXPORTADORES_ENTRE_FECHAS,
				parameters);		
	}	
}
