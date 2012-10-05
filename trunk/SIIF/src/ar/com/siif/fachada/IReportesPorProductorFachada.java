package ar.com.siif.fachada;

import ar.com.siif.negocio.exception.NegocioException;

public interface IReportesPorProductorFachada {

	public byte[] generarReporteVolumenFiscalizadoTotal(String path, String periodo)throws NegocioException;
}
