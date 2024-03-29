package ar.com.siif.fachada;

import java.util.Date;
import java.util.List;

import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.exception.NegocioException;

public interface IGuiaForestalFachada {

	public void altaGuiaForestalBasica(GuiaForestalDTO guia,
			List<BoletaDepositoDTO> listaBoletaDepositoDTO,
			List<RangoDTO> listaRangosDTO, Date fechaVencimiento,
			List<FiscalizacionDTO> listaFiscalizacionesDTO,
			List<SubImporteDTO> listaSubImportesDTO) throws NegocioException;

	public List<GuiaForestalDTO> recuperarGuiasForestalesPorProductor(
			long idProductor);

	public List<GuiaForestalDTO> recuperarGuiasForestalesPorProductor(
			long idProductor, boolean sinAnulados);

	public GuiaForestalDTO recuperarGuiaForestal(long idGuiaForestal);

	public GuiaForestalDTO recuperarGuiaForestalPorNroGuia(int nroGuiaForestal);

	public GuiaForestalDTO recuperarGuiaForestalPorNroGuia(int nroGuiaForestal,
			boolean sinAnulados);

	public String registrarPagoBoletaDeposito(long idBoleta)
			throws NegocioException;

	public String reemplazarBoletaDeDeposito(long idBoleta, int numero,
			String concepto, String area, String efectivoCheque,
			String fechaVencimiento) throws NegocioException;

	public String registrarDevolucionValeTransporte(long idVale)
			throws NegocioException;

	public String registrarDevolucionYCompletarDatosValeTransporte(long idVale,
			String destino, String vehiculo, String marca, String dominio,
			String producto, int nroPiezas, double cantM3, String especie,
			String fechaDevolucion) throws NegocioException;

	public String reemplazarValeTransporte(long idVale, int numeroVale,
			String origen, String destino, String vehiculo, String marca,
			String dominio, String producto, int nroPiezas, double cantM3,
			String especie, String fechaVencimiento) throws NegocioException;

	public boolean existeGuiaForestal(int nroGuia);

	public void asociarFiscalizacionesConGuiasForestales(long id,
			List<FiscalizacionDTO> listaFiscalizacionesAAsociar);

	public void desasociarFiscalizacionesConGuiasForestales(long id,
			List<FiscalizacionDTO> listaFiscalizacionesAAsociar);

	public boolean verificarBoletasDepositoVencidasImpagas(long idProductor);

	public void anularGuiaForestal(GuiaForestalDTO guiaForestalDTO);

	public void modificacionGuiaForestalBasica(GuiaForestalDTO guia,
			List<RangoDTO> listaRangosDTO,
			List<ValeTransporteDTO> valesTransporteDTO, Date fechaVencimiento);

	public boolean existeGuiaForestal(long idGuia, int nroGuia);
}
