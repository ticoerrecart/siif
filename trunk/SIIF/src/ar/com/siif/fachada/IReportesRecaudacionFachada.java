package ar.com.siif.fachada;

import ar.com.siif.negocio.exception.NegocioException;

public interface IReportesRecaudacionFachada {

	public byte[] generarReporteRecaudacionPorProductorEntreFechas(String path, String productor, String fechaDesde, 
																   String fechaHasta)throws NegocioException;
	
	public byte[] generarReporteRecaudacionPorProductorPorAnioForestal(String path, String productor, String periodo)throws NegocioException;
	
	public byte[] generarReporteRecaudacionPorProductorPorUbicacion(String path, String productor, String pmf, 
																	String tranzon, String marcacion)throws NegocioException;
	
	public byte[] generarReporteRecaudacionTotalProductoresEntreFechas(String path, String fechaDesde, String fechaHasta)
																									throws NegocioException;
	
	public byte[] generarReporteRecaudacionPorAnioForestalPorProductor(String path, String productor)throws NegocioException;
	
	public byte[] generarReporteRecaudacionPorAnioForestalTotalProductores(String path)throws NegocioException;
}
