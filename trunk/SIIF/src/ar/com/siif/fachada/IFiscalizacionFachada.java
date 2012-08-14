package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public interface IFiscalizacionFachada {

	public List<Entidad> recuperarEntidades();

	public List<Entidad> recuperarProductores();

	public List<Fiscalizacion> recuperarFiscalizaciones();

	public List<Fiscalizacion> recuperarFiscalizacionesParaAltaGFB(Long idProductor);

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(Long idProductor);	
	
	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion)throws NegocioException;

	public void modificacionFiscalizacion(Fiscalizacion fiscalizacion, 
										  List<Muestra> muestrasAEliminar)
											throws DataBaseException;

	public void altaFiscalizacion(Fiscalizacion acta)throws DataBaseException;

	public Entidad getProductorForestal(long idProductorForestal);

	public TipoProducto getTipoProducto(long idTipoProductoForestal);
	
	//public List<Fiscalizacion> recuperarFiscalizacionesPorLocalidad(Long idLocalidad);
	
	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor);
	
	public void actualizarFiscalizacion(Fiscalizacion fiscalizacion)throws DataBaseException;
	
	public void altaFiscalizacion(FiscalizacionDTO fiscalizacion, List<MuestraDTO> muestrasDTO)throws DataBaseException, NegocioException;	
	
	public List<EntidadDTO> recuperarProductoresDTO();
	
	public FiscalizacionDTO recuperarFiscalizacionDTO(long idFiscalizacion)throws NegocioException;
}
