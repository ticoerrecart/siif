package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.FilaTablaVolFiscAsociarDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.OperacionFiscalizacionDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.exception.NegocioException;

public interface IFiscalizacionFachada {

	public List<Fiscalizacion> recuperarFiscalizaciones();

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAltaGFB(
			Long idProductor, String idPeriodo);

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAsociarAGuia(
			Long idProductor, String periodoForestalGuia, Long idFiscalizacion,
			List<SubImporteDTO> listaSubImportesDTO,
			List<FilaTablaVolFiscAsociarDTO> tablaVolFiscAsociar);

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(
			Long idProductor);

	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion);

	public void modificacionFiscalizacion(FiscalizacionDTO fiscalizacionDTO,
			List<MuestraDTO> muestrasNuevasDTO,
			OperacionFiscalizacionDTO operacion);

	public String validarFiscalizacionAsociadaAGuia(
			FiscalizacionDTO fiscalizacionDTO);

	public void altaFiscalizacion(Fiscalizacion acta);

	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(
			Long idProductor, String idPeriodo);

	public void altaFiscalizacion(FiscalizacionDTO fiscalizacion,
			List<MuestraDTO> muestrasDTO);

	public FiscalizacionDTO recuperarFiscalizacionDTO(long idFiscalizacion);

	public void validarFiscalizacionDTO(String idFiscalizacion,
			String idProductorForestal, String idTipoProducto, String idRodal)
			throws NumberFormatException, NegocioException;

	public List<Fiscalizacion> recuperarFiscalizacionesAAnularPorProductor(
			Long idProductor, String idPeriodo);

	public void anularFiscalizaciones(Long[] idsFiscalizaciones)
			throws NegocioException, NegocioException;

	public List<FiscalizacionDTO> recuperarFiscalizacionesDTOParaAltaCertificadoOrigen(
			Long idProductor, String periodo, Long idLocalizacion);
}
