package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.siif.dao.GuiaForestalDAO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.SubImporte;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.struts.utils.Validator;

public class GuiaForestalFachada implements IGuiaForestalFachada {

	private GuiaForestalDAO guiaForestalDAO;
	private IUsuarioFachada usuarioFachada;
	private IFiscalizacionFachada fiscalizacionFachada;
	private IEntidadFachada entidadFachada;
	private ITipoProductoForestalFachada tipoProductoForestalFachada;
	private IUbicacionFachada ubicacionFachada;
	
	public GuiaForestalFachada() {
	}

	public GuiaForestalFachada(GuiaForestalDAO guiaForestalDAO, IUsuarioFachada pUsuarioFachada, 
								IFiscalizacionFachada pFiscalizacionFachada, IEntidadFachada pEntidadFachada,
								ITipoProductoForestalFachada pTipoProductoForestalFachada, 
								IUbicacionFachada pUbicacionFachada) 
	{

		this.guiaForestalDAO = guiaForestalDAO;
		this.usuarioFachada = pUsuarioFachada;
		this.fiscalizacionFachada = pFiscalizacionFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
		this.ubicacionFachada = pUbicacionFachada;
	}

	public void altaGuiaForestalBasica(GuiaForestalDTO guia,
									   List<BoletaDepositoDTO> listaBoletaDepositoDTO,
									   List<RangoDTO> listaRangosDTO, Date fechaVencimiento,
									   List<FiscalizacionDTO> listaFiscalizacionesDTO,
									   List<SubImporteDTO> listaSubImportesDTO
									   ) throws NegocioException {

		try{
			
			//Fiscalizacion fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(guia.getFiscalizacion().getId());
			
			List<Fiscalizacion> listaFiscalizaciones = new ArrayList<Fiscalizacion>();
			for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesDTO) {
				listaFiscalizaciones.add(fiscalizacionFachada.recuperarFiscalizacion(fiscalizacionDTO.getId()));
			}
			
			TipoProducto tipoProducto;
			List<SubImporte> listaSubImporte = new ArrayList<SubImporte>();
			for (SubImporteDTO subImporteDTO : listaSubImportesDTO) {
				tipoProducto =  tipoProductoForestalFachada.recuperarTipoProductoForestal(subImporteDTO.getTipoProducto().getId());
				listaSubImporte.add(ProviderDominio.getSubImporte(null, tipoProducto, subImporteDTO));
			}
			
			Usuario usuario = usuarioFachada.getUsuario(guia.getUsuario().getId());
			Entidad productorForestal = entidadFachada.getEntidad(guia.getProductorForestal().getId());
			Rodal rodal = ubicacionFachada.getRodal(guia.getRodal().getId());
			
			GuiaForestal guiaForestal = ProviderDominio.getGuiaForestal(guia,listaBoletaDepositoDTO,
																		listaRangosDTO, fechaVencimiento, listaFiscalizaciones,
																		listaSubImporte,productorForestal,rodal,usuario);
			
			this.guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
			
			for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
				fiscalizacion.setGuiaForestal(guiaForestal);
				fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
			}												
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}				
	}

	public List<GuiaForestalDTO> recuperarGuiasForestalesPorProductor(long idProductor) throws NegocioException {

		try{
			List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
			List<GuiaForestal> listaGuiasForestales = guiaForestalDAO.recuperarGuiasForestalesPorProductor(idProductor);
			
			for (GuiaForestal guiaForestal : listaGuiasForestales) {
				listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
			}
			
			return listaGuiasForestalesDTO;
			
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

	public String registrarDevolucionYCompletarDatosValeTransporte(long idVale,
			String destino, String vehiculo, String marca, String dominio,
			String producto, int nroPiezas, double cantM3, String especie,
			String fechaDevolucion) {

		try {
			//Validar que en la devoluci�n de Vales de Transporte, 
			// los m3 del vale no sobrepasen lo que esta fiscalizado en la gu�a para el tipo de producto declarado en el vale, 
			// ni tampoco que la suma de los m3 de los vales devueltos, 
			// mas el del vale en cuesti�n, no sobrepase lo fiscalizado en la gu�a para ese tipo de producto.
			StringBuffer pError = new StringBuffer(); 
			boolean ok = Validator.requerido(fechaDevolucion, "Fecha Devoluci�n", pError);
			boolean ok2 = Validator.validarDoubleMayorQue(0, String.valueOf(cantM3), "Cantidad(m3)", pError);
			
			
			
			double totalMts = 0;
			ValeTransporte vale = (ValeTransporte) this.guiaForestalDAO.getHibernateTemplate().get(ValeTransporte.class, idVale);
			List<Fiscalizacion> fiscalizaciones = vale.getGuiaForestal().getFiscalizaciones();
			
			boolean ok3 = Validator.validarFiscalizacionExistenteParaVale(fiscalizaciones,producto,pError);
			
			for (Fiscalizacion fiscalizacion : fiscalizaciones) {
				if (fiscalizacion.getTipoProducto().getNombre().equals(producto))
				totalMts = totalMts  + fiscalizacion.getCantidadMts();
			}
			
			double totalMtsVales = cantM3;
			List<ValeTransporte> vales = vale.getGuiaForestal().getValesTransporte();
			for (ValeTransporte valeTransporte : vales) {
				if (valeTransporte.getFechaDevolucion() != null && producto.equalsIgnoreCase(valeTransporte.getProducto())){
					totalMtsVales  = totalMtsVales  + valeTransporte.getCantidadMts();
				}
			}
			 
			 
			boolean ok4 = Validator.validarM3ValesMenorQueM3Fiscalizaciones(totalMtsVales,totalMts,pError);			
			if (ok && ok2 && ok3 && ok4){
				return guiaForestalDAO.registrarDevolucionYCompletarDatosValeTransporte(idVale,
					destino, vehiculo, marca, dominio,
					producto, nroPiezas, cantM3,especie,
					fechaDevolucion);
			} else {
				return pError.toString();
			}

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

	public void asociarFiscalizacionesConGuiasForestales(long id, List<FiscalizacionDTO> listaFiscalizacionesAAsociar)
														throws NegocioException
	{
		try {
			GuiaForestal guiaForestal = guiaForestalDAO.recuperarGuiaForestal(id);
			Fiscalizacion fiscalizacion;
			for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesAAsociar) {
				
				fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(fiscalizacionDTO.getId());				
				guiaForestal.getFiscalizaciones().add(fiscalizacion);
				fiscalizacion.setGuiaForestal(guiaForestal);
				
				fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
			}
			
			guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
}
