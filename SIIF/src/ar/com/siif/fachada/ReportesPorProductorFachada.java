package ar.com.siif.fachada;

import ar.com.siif.dao.ReportesPorProductorDAO;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public class ReportesPorProductorFachada implements
		IReportesPorProductorFachada {

	private ReportesPorProductorDAO reportePorProductorDAO;
	
	public ReportesPorProductorFachada(){}
	
	public ReportesPorProductorFachada(ReportesPorProductorDAO pReportePorProductorDAO){
		
		this.reportePorProductorDAO = pReportePorProductorDAO;
	}
	
	public byte[] generarReporteVolumenFiscalizadoTotal(String path, String periodo)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteVolumenFiscalizadoTotal(path,periodo);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	

	public byte[] generarReporteVolumenFiscalizadoPorProductos(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteVolumenFiscalizadoPorProductos(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public byte[] generarReporteVolumenGFBMontosPagos(String path, String periodo, Long idProductor)throws NegocioException{
		
		try{
			return reportePorProductorDAO.generarReporteVolumenGFBMontosPagos(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteVolumenGFBMontosAdeudados(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteVolumenGFBMontosAdeudados(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteListaBoletasTotales(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteListaBoletasTotales(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteListaBoletasPagas(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteListaBoletasPagas(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteListaBoletasImpagas(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteListaBoletasImpagas(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	

	public byte[] generarReporteListaValesDevueltos(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteListaValesDevueltos(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	

	public byte[] generarReporteListaValesEnUso(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteListaValesEnUso(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public byte[] generarReporteListaValesTotales(String path, String periodo, Long idProductor)throws NegocioException{

		try{
			return reportePorProductorDAO.generarReporteListaValesTotales(path,periodo,idProductor);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	

	public byte[] generarReporteVolumenPorUbicacion(String path, String periodo, Long idProductor,
								Long idPMF, Long idTranzon, Long idMarcacion)throws NegocioException
	{
		try{
			return reportePorProductorDAO.generarReporteVolumenPorUbicacion(path,periodo,idProductor,idPMF,idTranzon,idMarcacion);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}		
	
}
