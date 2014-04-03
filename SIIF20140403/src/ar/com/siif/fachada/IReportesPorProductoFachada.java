package ar.com.siif.fachada;

public interface IReportesPorProductoFachada {

	public byte[] generarReporteVolumenPorAnioForestal(String path, String volumen) throws Exception;

	public byte[] generarReporteVolumenPorProductorEntreFechas(String path, String volumen, String productor, String fechaDesde, String fechaHasta)
			throws Exception;

	public byte[] generarReporteVolumenPorProductoPorProductorPorUbicacion(String path, String volumen, String Productor, String periodo, String pmf,
			String tranzon, String marcacion, String area) throws Exception;

}
