package ar.com.siif.fachada;

public interface IReportesFachada {

	public byte[] pruebaJasper(String path);
	
	public byte[] generarReporteGuiaForestal(long idGuiaForestal,String path);
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path);
	
	public byte[] generarReporteVolumenFiscalizadoPorProductoForestalFecha(String path,String fechaDesde,String fechaHasta);
	
	public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long idProd,String fechaDesde,String fechaHasta,String path);
}
