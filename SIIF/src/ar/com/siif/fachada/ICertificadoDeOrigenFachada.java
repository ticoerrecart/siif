package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.CertificadoOrigenDTO;
import ar.com.siif.dto.TipoProductoEnCertificadoDTO;
import ar.com.siif.negocio.exception.NegocioException;

public interface ICertificadoDeOrigenFachada {

	public long altaCertificadoOrigen(CertificadoOrigenDTO certificadoOrigen, 
			List<TipoProductoEnCertificadoDTO> listaTipoProductoEnCertificado);	
	
	public double obtenerVolumenExportado(Long idProductor, String periodo, Long idLocalizacion);
	
	public List<CertificadoOrigenDTO> getCertificadosOrigen(Long idProductor, String periodo, Long idLocalizacion);
	
	public CertificadoOrigenDTO recuperarCertificadoOrigen(long idCertificado);
	
	public CertificadoOrigenDTO recuperarCertificadoOrigenPorNroCertificado(long nroCertificado);
	
	public boolean existeCertificado(long nroCertificado);
}
