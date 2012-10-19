package ar.com.siif.fachada;

import ar.com.siif.negocio.exception.NegocioException;

public interface IReportesPorProductorFachada {

	public byte[] generarReporteVolumenFiscalizadoTotal(String path, String periodo)throws NegocioException;
	
	public byte[] generarReporteVolumenFiscalizadoPorProductos(String path, String periodo, Long idProductor)throws NegocioException;
	
	public byte[] generarReporteVolumenGFBMontosPagos(String path, String periodo, Long idProductor)throws NegocioException;
	
	public byte[] generarReporteVolumenGFBMontosAdeudados(String path, String periodo, Long idProductor)throws NegocioException;
	
	public byte[] generarReporteListaBoletasTotales(String path, String periodo, Long idProductor)throws NegocioException;
	
	public byte[] generarReporteListaBoletasPagas(String path, String periodo, Long idProductor)throws NegocioException;
	
	public byte[] generarReporteListaBoletasImpagas(String path, String periodo, Long idProductor)throws NegocioException;	
	
	public byte[] generarReporteListaValesDevueltos(String path, String periodo, Long idProductor)throws NegocioException;
	
	public byte[] generarReporteListaValesEnUso(String path, String periodo, Long idProductor)throws NegocioException;
	
	public byte[] generarReporteListaValesTotales(String path, String periodo, Long idProductor)throws NegocioException;
	
	public byte[] generarReporteVolumenPorUbicacion(String path, String periodo, Long idProductor,Long idPMF, Long idTranzon, 
														Long idMarcacion)throws NegocioException;	
}
