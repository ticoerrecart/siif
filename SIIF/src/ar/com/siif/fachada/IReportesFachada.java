package ar.com.siif.fachada;

public interface IReportesFachada {

	public byte[] pruebaJasper(String path) throws Exception;

	public byte[] generarReporteGuiaForestal(long idGuiaForestal, String path)
			throws Exception;

	public byte[] generarReporteFiscalizacion(long idFiscalizacion, String path)
			throws Exception;

	public byte[] generarReporteCertificadoOrigen(long idCertificado,
			String path) throws Exception;

	public byte[] generarReporteDetalleGuiasEntreFechas(
			String path, String fechaDesde, String fechaHasta) throws Exception;	
	
	public byte[] generarReporteGuiasForestalesPorProductorEntreFechas(String path,
			String productor, String fechaDesde,String fechaHasta) throws Exception;	
	
	/*
	 * public byte[] generarReporteVolumenFiscalizadoPorProductoForestalFecha(
	 * String path,String fechaDesde,String fechaHasta)throws NegocioException;
	 * 
	 * public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long
	 * idProd, String fechaDesde,String fechaHasta,String path)throws
	 * NegocioException;
	 */
}
