package ar.com.siif.fachada;

public interface IReportesCertificadoOrigenFachada {

	public byte[] generarReporteCertificadosOrigenPorProductorEntreFechas(String path,
			String productor, String fechaDesde, String fechaHasta)throws Exception;	

	public byte[] generarReporteCertificadosOrigenTotalProductoresEntreFechas(String path,
			String fechaDesde, String fechaHasta)throws Exception;		
}
