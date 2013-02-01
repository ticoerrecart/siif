package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.CertificadoOrigenDTO;
import ar.com.siif.dto.TipoProductoEnCertificadoDTO;
import ar.com.siif.negocio.exception.NegocioException;

public interface ICertificadoDeOrigenFachada {

	public void altaCertificadoOrigen(CertificadoOrigenDTO certificadoOrigen, 
			List<TipoProductoEnCertificadoDTO> listaTipoProductoEnCertificado)throws NegocioException;	
	
	public double obtenerVolumenExportado(Long idProductor, String periodo, Long idPMF)throws NegocioException;
}
