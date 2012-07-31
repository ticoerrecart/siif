package ar.com.siif.fachada;

import java.util.List;
import ar.com.siif.dao.FiscalizacionDAO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.providers.ProviderDominio;

public class FiscalizacionFachada implements IFiscalizacionFachada {

	private FiscalizacionDAO fiscalizacionDAO;
	private IUbicacionFachada ubicacionFachada; 
	private IEntidadFachada entidadFachada;
	private ITipoProductoForestalFachada tipoProductoForestalFachada;
	private IUsuarioFachada usuarioFachada;
	
	public FiscalizacionFachada() {
	}
	
	public FiscalizacionFachada(FiscalizacionDAO fiscalizacionDAO, IUbicacionFachada pUbicacionFachada,
			IEntidadFachada pEntidadFachada, ITipoProductoForestalFachada pTipoProductoForestalFachada,
			IUsuarioFachada pUsuarioFachada) {

		this.fiscalizacionDAO = fiscalizacionDAO;
		this.ubicacionFachada = pUbicacionFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
		this.usuarioFachada = pUsuarioFachada;
	}

	public List<Entidad> recuperarEntidades() {

		return fiscalizacionDAO.recuperarEntidades();
	}

	public List<Entidad> recuperarProductores() {

		return fiscalizacionDAO.recuperarProductores();
	}

	public List<Fiscalizacion> recuperarFiscalizaciones() {

		return fiscalizacionDAO.recuperarFiscalizaciones();
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaAltaGFB(Long idProductor) {

		return fiscalizacionDAO.recuperarFiscalizacionesParaAltaGFB(idProductor);
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(Long idProductor) {

		return fiscalizacionDAO.recuperarFiscalizacionesParaModificacionGFB(idProductor);
	}	
	
	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion) {

		return fiscalizacionDAO.recuperarFiscalizacion(idFiscalizacion);
	}

	public void modificacionFiscalizacion(Fiscalizacion fiscalizacion,
										  List<Muestra> muestrasAEliminar)
											throws DataBaseException 
	{
		fiscalizacionDAO.modificacionFiscalizacion(fiscalizacion,muestrasAEliminar);
	}

	public void altaFiscalizacion(Fiscalizacion fiscalizacion)
		throws DataBaseException{
		fiscalizacionDAO.altaFiscalizacion(fiscalizacion);
	}

	public Entidad getProductorForestal(long idProductorForestal) {
		return fiscalizacionDAO.getProductorForestal(idProductorForestal);
	}

	public TipoProducto getTipoProducto(long idTipoProductoForestal) {
		return fiscalizacionDAO.getTipoProducto(idTipoProductoForestal);
	}

	/*public List<Fiscalizacion> recuperarFiscalizacionesPorLocalidad(Long idLocalidad){
		return fiscalizacionDAO.recuperarFiscalizacionesPorLocalidad(idLocalidad);
	}*/
	
	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor){
		return fiscalizacionDAO.recuperarFiscalizacionesPorProductor(idProductor);
	}	
	
	public void actualizarFiscalizacion(Fiscalizacion fiscalizacion)
		throws DataBaseException {
		fiscalizacionDAO.actualizarFiscalizacion(fiscalizacion);
	}
	
	public void altaFiscalizacion(FiscalizacionDTO fiscalizacionDTO, List<MuestraDTO> muestrasDTO)
		throws DataBaseException{
		
		Rodal rodal = ubicacionFachada.getRodal(fiscalizacionDTO.getIdRodal());
		Entidad productorForestal = entidadFachada.getEntidad(fiscalizacionDTO.getIdProductorForestal());
		Entidad oficinaForestal = entidadFachada.getEntidad(fiscalizacionDTO.getIdOficinaForestal());
		TipoProducto tipoProducto = tipoProductoForestalFachada.recuperarTipoProductoForestal(
				fiscalizacionDTO.getIdTipoProductoForestal());
		Usuario usuario = usuarioFachada.getUsuario(fiscalizacionDTO.getIdUsuario());
		
		Fiscalizacion fiscalizacion = ProviderDominio.getFiscalizacion(fiscalizacionDTO,muestrasDTO,rodal,
																		productorForestal,oficinaForestal,
																		tipoProducto,usuario);
		fiscalizacionDAO.altaFiscalizacion(fiscalizacion);
	}	
}
