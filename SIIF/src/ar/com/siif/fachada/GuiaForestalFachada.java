package ar.com.siif.fachada;

import java.util.Date;
import java.util.List;

import ar.com.siif.dao.GuiaForestalDAO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class GuiaForestalFachada implements IGuiaForestalFachada {

	private GuiaForestalDAO guiaForestalDAO;
	private IUsuarioFachada usuarioFachada;
	private IFiscalizacionFachada fiscalizacionFachada;
	
	public GuiaForestalFachada() {
	}

	public GuiaForestalFachada(GuiaForestalDAO guiaForestalDAO, IUsuarioFachada pUsuarioFachada, 
								IFiscalizacionFachada pFiscalizacionFachada) 
	{

		this.guiaForestalDAO = guiaForestalDAO;
		this.usuarioFachada = pUsuarioFachada;
		this.fiscalizacionFachada = pFiscalizacionFachada;
	}

	public void altaGuiaForestalBasica(GuiaForestalDTO guia,
									   List<BoletaDepositoDTO> listaBoletaDepositoDTO,
									   List<ValeTransporteDTO> listaValeTransporteDTO
									   ) throws NegocioException {

		try{
			
			Fiscalizacion fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(guia.getFiscalizacion().getId()); 
			Usuario usuario = usuarioFachada.getUsuario(guia.getUsuario().getId());
			
			GuiaForestal guiaForestal = ProviderDominio.getGuiaForestal(guia,listaBoletaDepositoDTO,
																		listaValeTransporteDTO,fiscalizacion,usuario);
			fiscalizacion.setGuiaForestal(guiaForestal);
			
			this.guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
			
			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}				
	}

	public List<GuiaForestal> recuperarGuiasForestales() throws NegocioException {

		try{
			return guiaForestalDAO.recuperarGuiasForestales();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public GuiaForestalDTO recuperarGuiaForestal(long idGuiaForestal) throws NegocioException {

		try{
			GuiaForestal guiaForestal = guiaForestalDAO.recuperarGuiaForestal(idGuiaForestal);
			
			return ProviderDTO.getGuiaForestalDTO(guiaForestal);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public GuiaForestalDTO recuperarGuiaForestalPorNroGuia(int nroGuiaForestal) throws NegocioException{
		
		try{
			GuiaForestal guiaForestal = guiaForestalDAO.recuperarGuiaForestalPorNroGuia(nroGuiaForestal);
			
			return ProviderDTO.getGuiaForestalDTO(guiaForestal);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public String registrarPagoBoletaDeposito(long idBoleta) {
		try{
			return guiaForestalDAO.registrarPagoBoletaDeposito(idBoleta);
			
		} catch (DataBaseException e) {
			return e.getMessage();
		}			
	}
	
	public String reemplazarBoletaDeDeposito(long idBoleta, int numero, String concepto,
			String area, String efectivoCheque, String fechaVencimiento) {

		try{
			return guiaForestalDAO.reemplazarBoletaDeDeposito(idBoleta,numero,concepto,
													area,efectivoCheque,fechaVencimiento);
		} catch (DataBaseException e) {
			return e.getMessage();
		}			
	}
	
	public String registrarDevolucionValeTransporte(long idVale){
		
		try{
			return guiaForestalDAO.registrarDevolucionValeTransporte(idVale);
			
		} catch (DataBaseException e) {
			return e.getMessage();
		}			
	}

	public String reemplazarValeTransporte(long idVale, int numeroVale, String origen,
			String destino, String vehiculo, String marca, String dominio, String producto,
			int nroPiezas, double cantM3, String especie, String fechaVencimiento) {
			
		try{
			return guiaForestalDAO.reemplazarValeTransporte(idVale,numeroVale,origen,
														destino,vehiculo,marca,dominio,producto,
														nroPiezas,cantM3,especie,fechaVencimiento);
		} catch (DataBaseException e) {
			return e.getMessage();
		}			
	}

	public boolean existeGuiaForestal(int nroGuia){
		return guiaForestalDAO.existeGuiaForestal(nroGuia); 
	}

}
