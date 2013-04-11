package ar.com.siif.fachada;

public interface IReportesPorProductorFachada {

	public byte[] generarReporteVolumenFiscalizadoTotal(String path,
			String periodo) throws Exception;

	public byte[] generarReporteVolumenFiscalizadoPorProductos(String path,
			String periodo, Long idProductor) throws Exception;

	public byte[] generarReporteVolumenGFBMontosPagos(String path,
			String periodo, Long idProductor) throws Exception;

	public byte[] generarReporteVolumenGFBMontosAdeudados(String path,
			String periodo, Long idProductor) throws Exception;

	public byte[] generarReporteListaBoletasTotales(String path,
			String periodo, Long idProductor) throws Exception;

	public byte[] generarReporteListaBoletasPagas(String path, String periodo,
			Long idProductor) throws Exception;

	public byte[] generarReporteListaBoletasImpagas(String path,
			String periodo, Long idProductor) throws Exception;

	public byte[] generarReporteListaValesDevueltos(String path,
			String periodo, Long idProductor) throws Exception;

	public byte[] generarReporteListaValesEnUso(String path, String periodo,
			Long idProductor) throws Exception;

	public byte[] generarReporteListaValesTotales(String path, String periodo,
			Long idProductor) throws Exception;

	public byte[] generarReporteVolumenPorUbicacion(String path,
			String periodo, Long idProductor, Long idPMF, Long idTranzon,
			Long idMarcacion, Long idArea) throws Exception;
}
