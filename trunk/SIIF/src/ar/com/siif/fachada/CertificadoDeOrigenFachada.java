package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.siif.dao.CertificadoDeOrigenDAO;
import ar.com.siif.dto.CertificadoOrigenDTO;
import ar.com.siif.dto.TipoProductoEnCertificadoDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.LocalidadDestino;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.TipoProductoEnCertificado;
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.utils.Fecha;

public class CertificadoDeOrigenFachada implements ICertificadoDeOrigenFachada {

	private CertificadoDeOrigenDAO certificadoDeOrigenDAO;
	private IEntidadFachada entidadFachada;
	private ITipoProductoForestalFachada tipoProductoForestalFachada;
	private IUbicacionFachada ubicacionFachada;
	private IUsuarioFachada usuarioFachada;
	private ILocalidadFachada localidadFachada;
	
	public CertificadoDeOrigenFachada(){}
	
	public CertificadoDeOrigenFachada(CertificadoDeOrigenDAO pCertificadoDeOrigenDAO, IUsuarioFachada pUsuarioFachada,
										IEntidadFachada pEntidadFachada, ITipoProductoForestalFachada pTipoProductoForestalFachada,
										IUbicacionFachada pUbicacionFachada, ILocalidadFachada pLocalidadFachada){
		
		this.certificadoDeOrigenDAO = pCertificadoDeOrigenDAO;
		this.usuarioFachada = pUsuarioFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
		this.ubicacionFachada = pUbicacionFachada;
		this.localidadFachada = pLocalidadFachada;
	}
	
	public void altaCertificadoOrigen(CertificadoOrigenDTO certificadoOrigen, 
									  List<TipoProductoEnCertificadoDTO> listaTipoProductoEnCertificado)
									  throws NegocioException
	{
		try{
			Usuario usuario = usuarioFachada.getUsuario(certificadoOrigen.getUsuarioAlta().getId());
			Entidad productor = entidadFachada.getEntidad(certificadoOrigen.getProductor().getId());
			Entidad exportador = entidadFachada.getEntidad(certificadoOrigen.getExportador().getId());
			LocalidadDestino localidadDestino = localidadFachada.getLocalidadDestinoPorId(certificadoOrigen.getLocalidadDestino().getId());
			PMF pmf = ubicacionFachada.getPMF(certificadoOrigen.getPmf().getId());
			
			Date fecha = Fecha.stringDDMMAAAAToUtilDate(certificadoOrigen.getFecha());
			
			TipoProductoExportacion tipoProductoExportacion;
			List<TipoProductoEnCertificado> listaTipoProdEnCert = new ArrayList<TipoProductoEnCertificado>();
			for (TipoProductoEnCertificadoDTO tipoProductoEnCertificadoDTO : listaTipoProductoEnCertificado) {
				
				tipoProductoExportacion = tipoProductoForestalFachada.recuperarTipoProductoExportacion(
																tipoProductoEnCertificadoDTO.getTipoProductoExportacion().getId());
				
				listaTipoProdEnCert.add(ProviderDominio.getTipoProductoEnCertificado(
															null,tipoProductoExportacion,tipoProductoEnCertificadoDTO));
			}
			
			this.certificadoDeOrigenDAO.altaCertificadoOrigen(
											ProviderDominio.getCertificadoOrigen(certificadoOrigen, usuario, productor, 
																				 exportador, localidadDestino, pmf, fecha, 
																				 listaTipoProdEnCert));
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}
	}
	
	public double obtenerVolumenExportado(Long idProductor, String periodo, Long idPMF)throws NegocioException{
		
		try{
		
			return certificadoDeOrigenDAO.obtenerVolumenExportado(idProductor,periodo,idPMF);
			
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}		
	}	
}
