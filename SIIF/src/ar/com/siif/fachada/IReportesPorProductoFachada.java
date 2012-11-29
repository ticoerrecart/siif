package ar.com.siif.fachada;

import ar.com.siif.negocio.exception.NegocioException;

public interface IReportesPorProductoFachada {

	public byte[] generarReporteVolumenPorAnioForestal(String path, String volumen)throws NegocioException;
	
	public byte[] generarReporteVolumenPorProductorEntreFechas(String path, String volumen, String productor, String fechaDesde, String fechaHasta)throws NegocioException;
	
	public byte[] generarReporteVolumenPorProductoPorProductorPorUbicacion(String path, String volumen, String productor, 
												String periodo, String pmf, String tranzon, String marcacion)throws NegocioException;
	
}
