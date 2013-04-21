package ar.com.siif.fachada;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.siif.dao.ReportesDAO;
import ar.com.siif.negocio.CertificadoOrigen;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Localizacion;
import ar.com.siif.negocio.Reporte;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;
import ar.com.siif.utils.StringUtils;

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

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idGuiaForestal", idGuiaForestal);
		parameters.put("PATH_SUB_REPORTES", path);

		GuiaForestal guia = (GuiaForestal) reportesDAO.getHibernateTemplate()
				.get(GuiaForestal.class, idGuiaForestal);
		setParemetersLocalizacion(parameters, guia.getLocalizacion());
		parameters.put(
				"tipoTerreno",
				StringUtils.nullToBlank(guia.getLocalizacion()
						.getLocalizacionDTO().getTipoTerrenoPMF()));
		return reportesDAO.generarReporte(Constantes.REPORTE_GUIA_FORESTAL,
				parameters);
		// return reportesDAO.generarReporteGuiaForestal(idGuiaForestal,path);

	}

	public byte[] generarReporteFiscalizacion(long idFiscalizacion, String path)
			throws Exception {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idFiscalizacion", idFiscalizacion);
		parameters.put("PATH_SUB_REPORTES", path);

		Fiscalizacion fisc = (Fiscalizacion) reportesDAO.getHibernateTemplate()
				.get(Fiscalizacion.class, idFiscalizacion);
		setParemetersLocalizacion(parameters, fisc.getLocalizacion());
		return reportesDAO.generarReporte(Constantes.REPORTE_FISCALIZACION,
				parameters);

	}

	public byte[] generarReporteCertificadoOrigen(long idCertificado,
			String path) throws Exception {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idCertificado", idCertificado);
		parameters.put("PATH_SUB_REPORTES", path);

		CertificadoOrigen cert = (CertificadoOrigen) reportesDAO
				.getHibernateTemplate().get(CertificadoOrigen.class,
						idCertificado);
		setParemetersLocalizacion(parameters, cert.getLocalizacion());
		return reportesDAO.generarReporte(
				Constantes.REPORTE_CERTIFICADO_ORIGEN, parameters);

	}

	public byte[] generarReporteDetalleGuiasEntreFechas(String path,
			String fechaDesde, String fechaHasta) throws Exception {

		Timestamp ts = new Timestamp(new Date().getTime());

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters
				.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters
				.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));
		parameters.put("fechaHoy", ts);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_DETALLE_GUIAS_ENTRE_FECHAS, parameters);
	}

	public byte[] generarReporteGuiasForestalesPorProductorEntreFechas(
			String path, String productor, String fechaDesde, String fechaHasta)
			throws Exception {
		Timestamp ts = new Timestamp(new Date().getTime());

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idProductor", new Long(productor));
		parameters
				.put("fechaDesde", Fecha.stringDDMMAAAAToUtilDate(fechaDesde));
		parameters
				.put("fechaHasta", Fecha.stringDDMMAAAAToUtilDate(fechaHasta));
		parameters.put("fechaHoy", ts);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_GUIAS_FORESTALES_POR_PRODUCTOR_ENTRE_FECHAS,
				parameters);
	}

	private void setParemetersLocalizacion(Map<String, Object> parameters,
			Localizacion localizacion) {
		parameters.put("area",
				StringUtils.nullToBlank(localizacion.getNombreArea()));
		parameters.put("nombrePmf",
				StringUtils.nullToBlank(localizacion.getNombrePMF()));
		parameters.put("expPmf",
				StringUtils.nullToBlank(localizacion.getExpedientePMF()));
		parameters.put("numeroTranzon",
				StringUtils.nullToBlank(localizacion.getNumeroTranzon()));
		parameters.put("dispTranzon",
				StringUtils.nullToBlank(localizacion.getDisposicionTranzon()));
		parameters
				.put("dispMarcacion", StringUtils.nullToBlank(localizacion
						.getDisposicionMarcacion()));
		parameters.put("nombreRodal",
				StringUtils.nullToBlank(localizacion.getNombreRodal()));
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

	public List<Reporte> obtenerReportes() {
		return reportesDAO.obtenerReportes();
	}

}
