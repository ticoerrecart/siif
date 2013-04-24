package ar.com.siif.fachada;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ar.com.siif.negocio.Reporte;

public interface IReportesFachada {

	public byte[] pruebaJasper(String path) throws Exception;

	public byte[] generarReporteGuiaForestal(long idGuiaForestal, String path)
			throws Exception;

	public byte[] generarReporteFiscalizacion(long idFiscalizacion, String path)
			throws Exception;

	public byte[] generarReporteCertificadoOrigen(long idCertificado,
			String path) throws Exception;

	public byte[] generarReporteDetalleGuiasEntreFechas(String path,
			String fechaDesde, String fechaHasta) throws Exception;

	public byte[] generarReporteGuiasForestalesPorProductorEntreFechas(
			String path, String productor, String fechaDesde, String fechaHasta)
			throws Exception;

	public byte[] generarReporteVolumenPorTipoProductorEntreFechas(String path,
			String fechaDesde, String fechaHasta) throws Exception;	
	
	/*
	 * public byte[] generarReporteVolumenFiscalizadoPorProductoForestalFecha(
	 * String path,String fechaDesde,String fechaHasta)throws NegocioException;
	 * 
	 * public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long
	 * idProd, String fechaDesde,String fechaHasta,String path)throws
	 * NegocioException;
	 */

	public List<Reporte> obtenerReportes();

	public void actualizarReporte(Long idReporte, InputStream is)throws IOException;
}
