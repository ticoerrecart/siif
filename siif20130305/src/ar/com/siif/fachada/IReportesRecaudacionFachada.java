package ar.com.siif.fachada;

public interface IReportesRecaudacionFachada {

	public byte[] generarReporteRecaudacionPorProductorEntreFechas(String path,
			String productor, String fechaDesde, String fechaHasta)
			throws Exception;

	public byte[] generarReporteRecaudacionPorProductorPorAnioForestal(
			String path, String productor, String periodo) throws Exception;

	public byte[] generarReporteRecaudacionPorProductorPorUbicacion(
			String path, String productor, String pmf, String tranzon,
			String marcacion) throws Exception;

	public byte[] generarReporteRecaudacionTotalProductoresEntreFechas(
			String path, String fechaDesde, String fechaHasta) throws Exception;

	public byte[] generarReporteRecaudacionPorAnioForestalPorProductor(
			String path, String productor) throws Exception;

	public byte[] generarReporteRecaudacionPorAnioForestalTotalProductores(
			String path) throws Exception;
}
