package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public interface IFiscalizacionFachada {

	//public List<Entidad> recuperarEntidades()throws NegocioException;

	//public List<Entidad> recuperarProductores()throws NegocioException;

	public List<Fiscalizacion> recuperarFiscalizaciones() throws NegocioException;

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAltaGFB(Long idProductor)
			throws NegocioException;

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAsociarAGuia(Long idProductor,
			Long idRodal, List<SubImporteDTO> listaSubImportesDTO) throws NegocioException;

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(Long idProductor)
			throws NegocioException;

	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion) throws NegocioException;


	public void modificacionFiscalizacion(FiscalizacionDTO fiscalizacionDTO,
			List<MuestraDTO> muestrasNuevasDTO) throws NegocioException;

	public void altaFiscalizacion(Fiscalizacion acta) throws NegocioException;

	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor)
			throws NegocioException;

	public void altaFiscalizacion(FiscalizacionDTO fiscalizacion, List<MuestraDTO> muestrasDTO)
			throws NegocioException;

	public FiscalizacionDTO recuperarFiscalizacionDTO(long idFiscalizacion) throws NegocioException;

	public void validarFiscalizacionDTO(String idFiscalizacion, String idProductorForestal,
			String idTipoProducto, String idRodal) throws NumberFormatException, DataBaseException,
			NegocioException;
}
