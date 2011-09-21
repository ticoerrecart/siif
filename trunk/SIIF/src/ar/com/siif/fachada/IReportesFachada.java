package ar.com.siif.fachada;

public interface IReportesFachada {

	public byte[] pruebaJasper(String path);
	
	public byte[] generarReporteGuiaForestal(long idGuiaForestal,String path);
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path);
	
	public byte[] generarReporteVolumenFiscalizadoPorProducto(String path);
	
	public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long idProd,String fechaDesde,String fechaHasta,String path);
}
