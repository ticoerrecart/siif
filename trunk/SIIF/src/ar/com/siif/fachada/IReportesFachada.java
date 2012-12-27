package ar.com.siif.fachada;

import ar.com.siif.negocio.exception.NegocioException;

public interface IReportesFachada {

	public byte[] pruebaJasper(String path)throws NegocioException;
	
	public byte[] generarReporteGuiaForestal(long idGuiaForestal,String path)throws NegocioException;
	
	public byte[] generarReporteFiscalizacion(long idFiscalizacion,String path)throws NegocioException;
	
	/*public byte[] generarReporteVolumenFiscalizadoPorProductoForestalFecha(
									String path,String fechaDesde,String fechaHasta)throws NegocioException;
	
	public byte[] generarReporteVolumenFiscalizadoPorProductorYFecha(long idProd,
									String fechaDesde,String fechaHasta,String path)throws NegocioException;*/
}
