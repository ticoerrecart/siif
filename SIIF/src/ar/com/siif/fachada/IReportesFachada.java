package ar.com.siif.fachada;

public interface IReportesFachada {

	public byte[] pruebaJasper(String path);
	
	public byte[] generarReporteGuiaForestal(int idGuiaForestal,String path);
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path);
	
	public byte[] generarReporteVolumenFiscalizadoPorProducto(String path);
}
