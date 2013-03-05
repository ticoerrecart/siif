package ar.com.siif.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.utils.Constantes;

public class ReportesFachada implements IReportesFachada {

	private ReportesDAO reportesDAO;

	public ReportesFachada() {
	}

	public ReportesFachada(ReportesDAO pReportesDAO) {

		this.reportesDAO = pReportesDAO;
	}

	public byte[] pruebaJasper(String path) throws Exception {

		return reportesDAO.pruebaJasper(path);

	}

	public byte[] generarReporteGuiaForestal(long idGuiaForestal, String path)
			throws Exception {

		Map parameters = new HashMap();
		parameters.put("idGuiaForestal", idGuiaForestal);
		parameters.put("PATH_SUB_REPORTES", path);

		return reportesDAO.generarReporte(Constantes.REPORTE_GUIA_FORESTAL,
				parameters);
		// return reportesDAO.generarReporteGuiaForestal(idGuiaForestal,path);

	}

	public byte[] generarReporteFiscalizacion(long idFiscalizacion, String path)
			throws Exception {

		Map parameters = new HashMap();
		parameters.put("idFiscalizacion", idFiscalizacion);
		parameters.put("PATH_SUB_REPORTES", path);

		return reportesDAO.generarReporte(Constantes.REPORTE_FISCALIZACION,
				parameters);

	}

	public byte[] generarReporteCertificadoOrigen(long idCertificado,
			String path) throws Exception {

		Map parameters = new HashMap();
		parameters.put("idCertificado", idCertificado);
		parameters.put("PATH_SUB_REPORTES", path);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_CERTIFICADO_ORIGEN, parameters);

	}

	/*
	 * public byte[]
	 * generarReporteVolumenFiscalizadoPorProductoForestalFecha(String path,
	 * String fechaDesde,String fechaHasta)throws NegocioException{
	 * 
	 * try{ return
	 * reportesDAO.generarReporteVolumenFiscalizadoPorProductoForestalFecha
	 * (path,fechaDesde,fechaHasta);
	 * 
	 * } catch (DataBaseException e) { throw new
	 * NegocioException(e.getMessage()); } }
	 * 
	 * public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long
	 * idProd, String fechaDesde, String fechaHasta, String path)throws
	 * NegocioException{
	 * 
	 * try{ return
	 * reportesDAO.generarReporteVolumenFiscalizadoPorProductorYFecha
	 * (idProd,fechaDesde,fechaHasta,path);
	 * 
	 * } catch (DataBaseException e) { throw new
	 * NegocioException(e.getMessage()); } }
	 */
}
