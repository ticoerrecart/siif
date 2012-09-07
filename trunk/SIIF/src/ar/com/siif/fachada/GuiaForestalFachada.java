package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.siif.dao.GuiaForestalDAO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class GuiaForestalFachada implements IGuiaForestalFachada {

	private GuiaForestalDAO guiaForestalDAO;
	private IUsuarioFachada usuarioFachada;
	private IFiscalizacionFachada fiscalizacionFachada;
	private IEntidadFachada entidadFachada;
	private ITipoProductoForestalFachada tipoProductoForestalFachada;
	
	public GuiaForestalFachada() {
	}

	public GuiaForestalFachada(GuiaForestalDAO guiaForestalDAO, IUsuarioFachada pUsuarioFachada, 
								IFiscalizacionFachada pFiscalizacionFachada, IEntidadFachada pEntidadFachada,
								ITipoProductoForestalFachada pTipoProductoForestalFachada) 
	{

		this.guiaForestalDAO = guiaForestalDAO;
		this.usuarioFachada = pUsuarioFachada;
		this.fiscalizacionFachada = pFiscalizacionFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
	}

	public void altaGuiaForestalBasica(GuiaForestalDTO guia,
									   List<BoletaDepositoDTO> listaBoletaDepositoDTO,
									   List<ValeTransporteDTO> listaValeTransporteDTO,
									   List<FiscalizacionDTO> listaFiscalizacionesDTO
									   ) throws NegocioException {

		try{
			
			//Fiscalizacion fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(guia.getFiscalizacion().getId());
			
			List<Fiscalizacion> listaFiscalizaciones = new ArrayList<Fiscalizacion>();
			for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesDTO) {
				listaFiscalizaciones.add(fiscalizacionFachada.recuperarFiscalizacion(fiscalizacionDTO.getId()));
			}
			
			Usuario usuario = usuarioFachada.getUsuario(guia.getUsuario().getId());
			Entidad productorForestal = entidadFachada.getEntidad(guia.getProductorForestal().getId());
			TipoProducto tipoProducto = tipoProductoForestalFachada.recuperarTipoProductoForestal(guia.getTipoProducto().getId());
			
			GuiaForestal guiaForestal = ProviderDominio.getGuiaForestal(guia,listaBoletaDepositoDTO,
																		listaValeTransporteDTO,listaFiscalizaciones,
																		productorForestal,tipoProducto,usuario);
			
			this.guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
			
			for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
				fiscalizacion.setGuiaForestal(guiaForestal);
				fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
			}												
			
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
