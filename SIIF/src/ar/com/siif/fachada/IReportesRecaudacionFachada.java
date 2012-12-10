package ar.com.siif.fachada;

import ar.com.siif.negocio.exception.NegocioException;

public interface IReportesRecaudacionFachada {

	public byte[] generarReporteRecaudacionPorProductorEntreFechas(String path, String productor, String fechaDesde, 
																   String fechaHasta)throws NegocioException;
	
	public byte[] generarReporteRecaudacionPorProductorPorAnioForestal(String path, String productor, String periodo)throws NegocioException;
	
	public byte[] generarReporteRecaudacionPorProductorPorUbicacion(String path, String productor, String pmf, 
																	String tranzon, String marcacion)throws NegocioException;
}
