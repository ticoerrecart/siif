package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;

import ar.com.siif.dao.GuiaForestalDAO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.enums.TipoDeAforo;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.CaminoConstruido;
import ar.com.siif.negocio.CuentaCorrienteFiscalizacion;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Localizacion;
import ar.com.siif.negocio.OperacionGuiaForestal;
import ar.com.siif.negocio.SubImporte;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;
import ar.com.siif.utils.MyLogger;

public class GuiaForestalFachada implements IGuiaForestalFachada {

	private GuiaForestalDAO guiaForestalDAO;

	private IUsuarioFachada usuarioFachada;

	private IFiscalizacionFachada fiscalizacionFachada;

	private IEntidadFachada entidadFachada;

	private ITipoProductoForestalFachada tipoProductoForestalFachada;

	private IUbicacionFachada ubicacionFachada;

	private ILocalidadFachada localidadFachada;
	
	private ICaminoFachada caminoFachada;
	
	private IAforoFachada aforoFachada;
	

	public GuiaForestalFachada() {
	}

	public GuiaForestalFachada(GuiaForestalDAO guiaForestalDAO, IUsuarioFachada pUsuarioFachada,
			IFiscalizacionFachada pFiscalizacionFachada, IEntidadFachada pEntidadFachada,
			ITipoProductoForestalFachada pTipoProductoForestalFachada,
			IUbicacionFachada pUbicacionFachada, ILocalidadFachada pLocalidadFachada,
			ICaminoFachada pCaminoFachada, IAforoFachada pAforoFachada) {

		this.guiaForestalDAO = guiaForestalDAO;
		this.usuarioFachada = pUsuarioFachada;
		this.fiscalizacionFachada = pFiscalizacionFachada;
		this.entidadFachada = pEntidadFachada;
		this.tipoProductoForestalFachada = pTipoProductoForestalFachada;
		this.ubicacionFachada = pUbicacionFachada;
		this.localidadFachada = pLocalidadFachada;
		this.caminoFachada = pCaminoFachada;
		this.aforoFachada = pAforoFachada;
	}

	public GuiaForestalDAO getGuiaForestalDAO() {
		return guiaForestalDAO;
	}

	public void setGuiaForestalDAO(GuiaForestalDAO guiaForestalDAO) {
		this.guiaForestalDAO = guiaForestalDAO;
	}

	public IUsuarioFachada getUsuarioFachada() {
		return usuarioFachada;
	}

	public void setUsuarioFachada(IUsuarioFachada usuarioFachada) {
		this.usuarioFachada = usuarioFachada;
	}

	public IFiscalizacionFachada getFiscalizacionFachada() {
		return fiscalizacionFachada;
	}

	public void setFiscalizacionFachada(IFiscalizacionFachada fiscalizacionFachada) {
		this.fiscalizacionFachada = fiscalizacionFachada;
	}

	public IEntidadFachada getEntidadFachada() {
		return entidadFachada;
	}

	public void setEntidadFachada(IEntidadFachada entidadFachada) {
		this.entidadFachada = entidadFachada;
	}

	public ITipoProductoForestalFachada getTipoProductoForestalFachada() {
		return tipoProductoForestalFachada;
	}

	public void setTipoProductoForestalFachada(
			ITipoProductoForestalFachada tipoProductoForestalFachada) {
		this.tipoProductoForestalFachada = tipoProductoForestalFachada;
	}

	public IUbicacionFachada getUbicacionFachada() {
		return ubicacionFachada;
	}

	public void setUbicacionFachada(IUbicacionFachada ubicacionFachada) {
		this.ubicacionFachada = ubicacionFachada;
	}

	public ILocalidadFachada getLocalidadFachada() {
		return localidadFachada;
	}

	public void setLocalidadFachada(ILocalidadFachada localidadFachada) {
		this.localidadFachada = localidadFachada;
	}

	public void altaGuiaForestalBasica(GuiaForestalDTO guia,
			List<BoletaDepositoDTO> listaBoletaDepositoDTO, List<RangoDTO> listaRangosDTO,
			Date fechaVencimiento, List<FiscalizacionDTO> listaFiscalizacionesDTO,
			List<SubImporteDTO> listaSubImportesDTO) throws NegocioException{

		List<Fiscalizacion> listaFiscalizaciones = new ArrayList<Fiscalizacion>();
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesDTO) {
			listaFiscalizaciones.add(fiscalizacionFachada.recuperarFiscalizacion(fiscalizacionDTO
					.getId()));
		}

		TipoProductoForestal tipoProducto;
		List<SubImporte> listaSubImporte = new ArrayList<SubImporte>();
		for (SubImporteDTO subImporteDTO : listaSubImportesDTO) {
			tipoProducto = tipoProductoForestalFachada.recuperarTipoProductoForestal(subImporteDTO
					.getTipoProducto().getId());
			listaSubImporte.add(ProviderDominio.getSubImporte(null, tipoProducto, subImporteDTO));
		}

		Usuario usuarioAlta = usuarioFachada.getUsuario(guia.getOperacionAlta().getUsuario().getId());
		
		Entidad productorForestal = entidadFachada.getEntidad(guia.getProductorForestal().getId());
		Localizacion localizacion = ubicacionFachada.getLocalizacion(Long.parseLong(guia
				.getIdLocalizacion()));
		Localidad localidad = localidadFachada.getLocalidadPorId(guia.getLocalidad().getId());

		GuiaForestal guiaForestal = ProviderDominio.getGuiaForestal(guia, listaBoletaDepositoDTO,
				listaRangosDTO, fechaVencimiento, listaFiscalizaciones, listaSubImporte,
				productorForestal, localizacion, localidad, usuarioAlta, null, null);

		this.guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);

		for (Fiscalizacion fiscalizacion : listaFiscalizaciones) {
			fiscalizacion.setGuiaForestal(guiaForestal);
			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
		}
		
		CaminoConstruido caminoConstruido = new CaminoConstruido(); 
		caminoConstruido.setGuiaForestal(guiaForestal);
		caminoConstruido.setMonto(guia.getCompensacionCaminos() * -1);
		caminoConstruido.setProductor(guiaForestal.getProductorForestal());
		caminoConstruido.setUsuario(usuarioAlta);
		this.caminoFachada.altaCamino(caminoConstruido);
		
		CuentaCorrienteFiscalizacion cuentaCorrienteFiscalizacion = new CuentaCorrienteFiscalizacion(); 
		cuentaCorrienteFiscalizacion.setGuiaForestal(guiaForestal);
		cuentaCorrienteFiscalizacion.setMonto(guia.getCompensacionFiscalizacion() * -1);
		cuentaCorrienteFiscalizacion.setProductor(guiaForestal.getProductorForestal());
		cuentaCorrienteFiscalizacion.setUsuario(usuarioAlta);
		cuentaCorrienteFiscalizacion.setFecha(new Date());
		this.guiaForestalDAO.altaCuentaCorrienteFiscalizacion(cuentaCorrienteFiscalizacion);		
	}

	public List<GuiaForestalDTO> recuperarGuiasForestalesPorProductor(long idProductor, String idPeriodo) {

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = guiaForestalDAO
				.recuperarGuiasForestalesPorProductor(idProductor, true, idPeriodo);

		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
		}

		return listaGuiasForestalesDTO;
	}

/*	public List<GuiaForestalDTO> recuperarGuiasForestalesPorProductor(long idProductor,
			boolean sinAnulados) {

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = guiaForestalDAO
				.recuperarGuiasForestalesPorProductor(idProductor, sinAnulados);

		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
		}
		return listaGuiasForestalesDTO;
	}
*/
	public GuiaForestalDTO recuperarGuiaForestal(long idGuiaForestal) {

		GuiaForestal guiaForestal = guiaForestalDAO.recuperarGuiaForestal(idGuiaForestal);
		return ProviderDTO.getGuiaForestalDTO(guiaForestal);
	}

	public GuiaForestalDTO recuperarGuiaForestalPorNroGuia(long nroGuiaForestal) {

		GuiaForestal guiaForestal = guiaForestalDAO.recuperarGuiaForestalPorNroGuia(
				nroGuiaForestal, true);
		if (guiaForestal != null) {
			return ProviderDTO.getGuiaForestalDTO(guiaForestal);
		}
		return null;
	}

	public GuiaForestalDTO recuperarGuiaForestalPorNroGuia(long nroGuiaForestal, boolean sinAnulados) {

		GuiaForestal guiaForestal = guiaForestalDAO.recuperarGuiaForestalPorNroGuia(
				nroGuiaForestal, sinAnulados);
		if (guiaForestal != null) {
			return ProviderDTO.getGuiaForestalDTO(guiaForestal);
		}
		return null;
	}

	public String registrarPagoBoletaDeposito(long idBoleta, String fechaPago) throws NegocioException {
		try {
			return guiaForestalDAO.registrarPagoBoletaDeposito(idBoleta,fechaPago);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public String reemplazarBoletaDeDeposito(long idBoleta, Long numero, String concepto,
			String area, String efectivoCheque, String fechaVencimiento) throws NegocioException {
		try {
			return guiaForestalDAO.reemplazarBoletaDeDeposito(idBoleta, numero, concepto, area,
					efectivoCheque, fechaVencimiento);

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public String registrarDevolucionValeTransporte(long idVale) throws NegocioException {

		try {
			return guiaForestalDAO.registrarDevolucionValeTransporte(idVale);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public String registrarDevolucionYCompletarDatosValeTransporte(long idVale, String destino,
			String vehiculo, String marca, String dominio, String producto, int nroPiezas,
			double cantM3, String especie, String fechaDevolucion) throws NegocioException {

		try {
			// Validar que en la devolución de Vales de Transporte,
			// los m3 del vale no sobrepasen lo que esta fiscalizado en la guía
			// para el tipo de producto declarado en el vale,
			// ni tampoco que la suma de los m3 de los vales devueltos,
			// mas el del vale en cuestión, no sobrepase lo fiscalizado en la
			// guía para ese tipo de producto.
			StringBuffer pError = new StringBuffer();
			
			boolean ok = Validator.requerido(fechaDevolucion, "Fecha Devolución", pError);
			boolean ok1 = true;
			
			//Quieren poder devolver un vale vacio.
			/*boolean ok2 = Validator.validarDoubleMayorQue(0, String.valueOf(cantM3),
					"Cantidad(m3)", pError);*/

			double totalMts = 0;
			ValeTransporte vale = (ValeTransporte) this.guiaForestalDAO.getHibernateTemplate().get(
					ValeTransporte.class, idVale);
			List<Fiscalizacion> fiscalizaciones = vale.getGuiaForestal().getFiscalizaciones();

			boolean ok3 = true;

			for (Fiscalizacion fiscalizacion : fiscalizaciones) {
				if (fiscalizacion.getTipoProducto().getNombre().equals(producto))
					totalMts = totalMts + fiscalizacion.getCantidadMts();
			}

			double totalMtsVales = cantM3;
			List<ValeTransporte> vales = vale.getGuiaForestal().getValesTransporte();
			for (ValeTransporte valeTransporte : vales) {
				if (valeTransporte.getFechaDevolucion() != null
						&& producto.equalsIgnoreCase(valeTransporte.getProducto())) {
					totalMtsVales = totalMtsVales + valeTransporte.getCantidadMts();
				}
			}

			GuiaForestal guia = vale.getGuiaForestal();			
			
			//Si el tipo de producto del vale es Leña o si el tipo de productor es Obrajero o Sin Fines de Lucro
			//tengo que verificar el volumen contra lo declarado en la guia.
			//Sino, tengo que verificar contra las fiscalizaciones asociadas a la guia.
			boolean ok4 = true;
			String tipoEntidad = guia.getProductorForestal().getTipoEntidad();
			if(producto.equals(Constantes.LENIA) ||					
				(tipoEntidad.equals(TipoDeEntidad.OBR.getDescripcion()) || tipoEntidad.equals(TipoDeEntidad.SFDL.getDescripcion())))
				//&&(totalMts==0)))	
			{
				List<SubImporte> subImportes = vale.getGuiaForestal().getSubImportes();
				double totalMtsEnGuia = 0;	
				for (SubImporte subImporte : subImportes) {
					if (subImporte.getTipoProducto().getNombre().equals(producto))
						totalMtsEnGuia = totalMtsEnGuia + subImporte.getCantidadMts();					
				}
				
				ok4 = Validator.validarM3DeLeniaEnValesMenorQueM3Guia(totalMtsVales,
						totalMtsEnGuia, pError);				

			}	
			else{
				//Quieren poder devolver un vale vacio.
				/*ok1 = Validator.validarDoubleMayorQue(0, String.valueOf(nroPiezas),
						"Nº de Piezas", pError);*/				
				
				ok3 = Validator.validarFiscalizacionExistenteParaVale(fiscalizaciones,
						producto, pError);				
				
				ok4 = Validator.validarM3ValesMenorQueM3Fiscalizaciones(totalMtsVales,
						totalMts, pError);				
			}
			
			if (ok && ok1 && ok3 && ok4) {
				return guiaForestalDAO.registrarDevolucionYCompletarDatosValeTransporte(idVale,
						destino, vehiculo, marca, dominio, producto, nroPiezas, cantM3, especie,
						fechaDevolucion);
			} else {
				return pError.toString();
			}

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public String reemplazarValeTransporte(long idVale, Long numeroVale, String origen,
			String destino, String vehiculo, String marca, String dominio, String producto,
			int nroPiezas, double cantM3, String especie, String fechaVencimiento)
			throws NegocioException {

		try {
			return guiaForestalDAO.reemplazarValeTransporte(idVale, numeroVale, origen, destino,
					vehiculo, marca, dominio, producto, nroPiezas, cantM3, especie,
					fechaVencimiento);

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public boolean existeGuiaForestal(long nroGuia) {
		return guiaForestalDAO.existeGuiaForestal(nroGuia);
	}

	public boolean existeGuiaForestal(long idGuia, long nroGuia) {
		return guiaForestalDAO.existeGuiaForestal(idGuia, nroGuia);
	}

	public double asociarFiscalizacionesConGuiasForestales(GuiaForestalDTO guiaDTO,
											List<FiscalizacionDTO> listaFiscalizacionesAAsociar) 
											throws NegocioException{

		double total = 0;
		GuiaForestal guiaForestal;
		Fiscalizacion fiscalizacion;
		
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesAAsociar) {

			//Tengo que recuperar la guia en cada iteracion pq sino queda desenganchada, pq hago el altaFiscalizacion 
			//que implicitamente hace el altaGuia
			guiaForestal = guiaForestalDAO.recuperarGuiaForestal(guiaDTO.getId());
			
			fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(fiscalizacionDTO.getId());
			guiaForestal.getFiscalizaciones().add(fiscalizacion);
						
			fiscalizacion.setGuiaForestal(guiaForestal);
			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
			
		}

		//Tengo que recuperar la guia otra vez pq me quedo desenganchada por el altaFiscalizacion.		
		guiaForestal = guiaForestalDAO.recuperarGuiaForestal(guiaDTO.getId());
		
		
	
		
		OperacionGuiaForestal operacion = ProviderDominio.getOperacionGuiaForestal(
											guiaDTO.getOperacionModificacion(),guiaForestal, 
											usuarioFachada.getUsuario(
													guiaDTO.getOperacionModificacion().getUsuario().getId()));
				
		guiaForestal.setOperacionModificacion(operacion);			
		
		/*MODIFICACION FISC CON DESCUENTO*/
		/*recorro las fiscalizaciones chequeo el getCantidadMtsExtento los multiplico y genero un credito x  la asociacion x cada fisc*/
		
		
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesAAsociar) {
			fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(fiscalizacionDTO.getId());
			Hibernate.initialize(guiaForestal.getSubImportes());
			
			if (guiaForestal.getTipoDeAforo() == TipoDeAforo.CLASIFICACION_DIAMETROS){
				double cantidadMts = 0;
				for (SubImporte subImporte : guiaForestal.getSubImportes()) {
					if (!subImporte.isComercializaDentroProvincia() && subImporte.getTipoProducto().getNombre().equals(fiscalizacion.getTipoProducto().getNombre())){
						cantidadMts = cantidadMts + subImporte.getCantidadMts();
					} 
				}
				//descuento por la cantidad de mts minima entre la fisc y los subimportes. ej si se fisalizaron 15mts y el subimporte es por 10mts.. hago descuento por 10; 
				double descMts = Math.min(fiscalizacion.getCantidadMts(), cantidadMts);
				if (descMts > 0){
					Double aforo = Double.parseDouble(aforoFachada.getValorAforoNuevo(guiaForestal.getTipoDeAforo().name(), "false"));
					CuentaCorrienteFiscalizacion cc = new CuentaCorrienteFiscalizacion();
					cc.setFecha(new Date());
					cc.setFiscalizacion(fiscalizacion);
					cc.setGuiaForestal(guiaForestal);
					cc.setMonto(aforo * descMts);
					cc.setProductor(guiaForestal.getProductorForestal());
					cc.setUsuario(usuarioFachada.getUsuario(guiaDTO.getOperacionModificacion().getUsuario().getId()));
					this.guiaForestalDAO.altaCuentaCorrienteFiscalizacion(cc);
					total = total + (aforo * descMts);
				}
			}
		}		
		//guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
		return total;		
	}

	public void desasociarFiscalizacionesConGuiasForestales(GuiaForestalDTO guiaDTO,
											List<FiscalizacionDTO> listaFiscalizacionesAAsociar) {
		
		desasociarFiscalizacionesConGuiasForestales(guiaDTO.getId(),listaFiscalizacionesAAsociar);

		//Tengo que recuperar la guia otra vez pq me quedo desenganchada por el altaFiscalizacion.		
		GuiaForestal guiaForestal = guiaForestalDAO.recuperarGuiaForestal(guiaDTO.getId());
		
		OperacionGuiaForestal operacion = ProviderDominio.getOperacionGuiaForestal(
											guiaDTO.getOperacionModificacion(),guiaForestal, 
											usuarioFachada.getUsuario(
													guiaDTO.getOperacionModificacion().getUsuario().getId()));
				
		guiaForestal.setOperacionModificacion(operacion);		
		
		// guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
	}

	private void desasociarFiscalizacionesConGuiasForestales(long idGuia, 
											List<FiscalizacionDTO> listaFiscalizacionesAAsociar){
		
		GuiaForestal guiaForestal;
		Fiscalizacion fiscalizacion;
		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizacionesAAsociar) {

			//Tengo que recuperar la guia en cada iteracion pq sino queda desenganchada, pq hago el altaFiscalizacion 
			//que implicitamente hace el altaGuia			
			guiaForestal = guiaForestalDAO.recuperarGuiaForestal(idGuia);
			
			fiscalizacion = fiscalizacionFachada.recuperarFiscalizacion(fiscalizacionDTO.getId());
			guiaForestal.getFiscalizaciones().remove(fiscalizacion);
			fiscalizacion.setGuiaForestal(null);

			fiscalizacionFachada.altaFiscalizacion(fiscalizacion);
		}		
	}
	
	public boolean verificarBoletasDepositoVencidasImpagas(long idProductor) {

		return guiaForestalDAO.verificarBoletasDepositoVencidasImpagas(idProductor);
	}

	/*
	 * public List<GuiaForestalDTO> recuperarGuiasParaCertificado(long
	 * idProductor, String periodo, long idPMF)throws NegocioException { try{
	 * List<GuiaForestalDTO> listaGuiasForestalesDTO = new
	 * ArrayList<GuiaForestalDTO>(); List<GuiaForestal> listaGuiasForestales =
	 * guiaForestalDAO
	 * .recuperarGuiasPorProductorPeriodoPMF(idProductor,periodo,idPMF);
	 * 
	 * GuiaForestalDTO guiaDTO; for (GuiaForestal guiaForestal :
	 * listaGuiasForestales) { guiaDTO =
	 * ProviderDTO.getGuiaForestalDTO(guiaForestal);
	 * //listaGuiasForestalesDTO.add
	 * (ProviderDTO.getGuiaForestalDTO(guiaForestal));
	 * 
	 * this.armarGuiaParaCertificado(guiaDTO); }
	 * 
	 * return listaGuiasForestalesDTO;
	 * 
	 * } catch (DataBaseException e) { throw new
	 * NegocioException(e.getMessage()); } }
	 * 
	 * private void armarGuiaParaCertificado(GuiaForestalDTO guiaDTO){
	 * 
	 * double montoPagado = 0; for (BoletaDepositoDTO boletaDTO :
	 * guiaDTO.getBoletasDeposito()) {
	 * 
	 * if(boletaDTO.getFechaPago() != null){ montoPagado = montoPagado +
	 * boletaDTO.getMonto(); } }
	 * 
	 * double importeLenia = 0; for (SubImporteDTO subImporteDTO :
	 * guiaDTO.getSubImportes()) { if(subImporteDTO.getTipoProducto().getId() ==
	 * 3L){ importeLenia = subImporteDTO.getImporte()*1.2;//Lo multiplico por
	 * 1.2 para agregarle el impuesto a la fiscalizacion } } }
	 */

	public void anularGuiaForestal(GuiaForestalDTO guiaForestalDTO) {

		this.desasociarFiscalizacionesConGuiasForestales(guiaForestalDTO.getId(),
														 guiaForestalDTO.getFiscalizaciones());
		GuiaForestal guiaForestal = guiaForestalDAO.recuperarGuiaForestal(guiaForestalDTO.getId());
		for (BoletaDeposito boleta : guiaForestal.getBoletasDeposito()) {
			boleta.setAnulado(true);
		}

		for (ValeTransporte vale : guiaForestal.getValesTransporte()) {
			vale.setAnulado(true);
		}
		
		guiaForestal.setOperacionAnulacion(
				ProviderDominio.getOperacionGuiaForestal(
								guiaForestalDTO.getOperacionAnulacion(),guiaForestal, 
								usuarioFachada.getUsuario(
										guiaForestalDTO.getOperacionAnulacion().getUsuario().getId())));		
		
		guiaForestal.setAnulado(true);

	}

	public void modificacionGuiaForestalBasica(GuiaForestalDTO guia, List<BoletaDepositoDTO> boletasDepositoDTO,
										List<RangoDTO> listaRangosDTO,List<ValeTransporteDTO> valesTransporteDTO, 
										Date fechaVencimiento) 
	{
		if (!guiaForestalDAO.existeGuiaForestal(guia.getId(), guia.getNroGuia())) {
			GuiaForestal guiaForestal = this.guiaForestalDAO.recuperarGuiaForestal(guia.getId());
			guiaForestal.setNroGuia(guia.getNroGuia());
			guiaForestal.setFechaVencimiento(Fecha.stringDDMMAAAAToUtilDate(guia
					.getFechaVencimiento()));
			guiaForestal.setDistanciaAforoMovil(guia.getDistanciaAforoMovil());
			guiaForestal.setPeriodoForestal(guia.getPeriodoForestal());

			/*actualizo boletas*/
			List<BoletaDeposito> boletasABorrar = new ArrayList<BoletaDeposito>();
			List<BoletaDeposito> boletas = guiaForestal.getBoletasDeposito();
			for (BoletaDeposito boleta : boletas) {
				for (BoletaDepositoDTO boletaDTO : boletasDepositoDTO) {
					if(boleta.getId().longValue() == boletaDTO.getIdBoleta() && boletaDTO.isEliminada()){
						boletasABorrar.add(boleta);
						boleta.setGuiaForestal(null);
					}
					
					if(boleta.getId().longValue() == boletaDTO.getIdBoleta() && boletaDTO.getFechaPago() != null
						&& boletaDTO.getFechaPago() != ""){
						boleta.setFechaPago(Fecha.stringDDMMAAAAToUtilDate(boletaDTO.getFechaPago()));
					}
				}
			}
			
			boletas.removeAll(boletasABorrar);
			
			/*agrego boletas nuevas*/
			for (BoletaDepositoDTO boletaDTO : boletasDepositoDTO) {
				if (boletaDTO.getIdBoleta() == 0){
					boletas.add(ProviderDominio.getBoletaDeposito(guiaForestal, boletaDTO));
				}
			}
		
			
			List<ValeTransporte> valesBorrados = new ArrayList<ValeTransporte>();
			for (ValeTransporte valeTransporte : guiaForestal.getValesTransporte()) {
				for (ValeTransporteDTO valeTransporteDTO : valesTransporteDTO) {
					if (valeTransporteDTO != null && valeTransporteDTO.getId() > 0) {
						if (valeTransporteDTO.getId() == valeTransporte.getId()) {
							valeTransporte.setGuiaForestal(null);
							valesBorrados.add(valeTransporte);
							break;
						}
					}
				}
			}
			
			guiaForestal.getValesTransporte().removeAll(valesBorrados);
			
			/*agrego vales nuevos*/
			for (RangoDTO rangoDTO : listaRangosDTO) {
				guiaForestal.getValesTransporte().addAll(
						ProviderDominio.getValesTransportes(guiaForestal, rangoDTO,
								fechaVencimiento));
			}
			
			guiaForestal.setOperacionModificacion(
					ProviderDominio.getOperacionGuiaForestal(
									guia.getOperacionModificacion(),guiaForestal, 
									usuarioFachada.getUsuario(
											guia.getOperacionModificacion().getUsuario().getId())));			
			
			String errorGuia = validarGuiaAsociadaAFiscalizacion(guia);
			if (!"".equalsIgnoreCase(errorGuia)) {
				List<Fiscalizacion> fiscalizaciones = guiaForestal.getFiscalizaciones();
				for (Fiscalizacion fiscalizacion : fiscalizaciones) {
					fiscalizacion.setGuiaForestal(null);
					guia.getFiscalizaciones().remove(fiscalizacion);
				}	
			}
			
			this.guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
		}
	}

	public String restablecerGuias(Long[] idsGuias, Long idUsuario) throws NegocioException {
		try {
			if (idsGuias == null || idsGuias.length == 0) {
				throw new NegocioException("Seleccione alguna Guía Forestal");
			}

			String mensaje = (idsGuias.length > 1) ? "Las Guías Forestales fueron restablecidas con exito"
					: "La Guía Forestal fue restablecida con exito";

			Usuario usuario = usuarioFachada.getUsuario(idUsuario);
							
			for (Long idGuia : idsGuias) {

				guiaForestalDAO.restablecerGuias(idGuia,usuario);
			}

			return mensaje;

		} catch (NegocioException ne) {
			throw ne;
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void modificarGuiaForestal(GuiaForestal guiaForestal){
		this.guiaForestalDAO.altaGuiaForestalBasica(guiaForestal);
	}	
	
	public GuiaForestal recuperarGuiaForestalDominio(long idGuiaForestal){
		
		return guiaForestalDAO.recuperarGuiaForestal(idGuiaForestal);
	}

	/**
	 * Verifico si el periodo es distinto
	 * **/
	public String validarGuiaAsociadaAFiscalizacion(GuiaForestalDTO guiaDTO) {
		String error = "";
		if (guiaDTO.getId() != null) {//es una modificación de Guia
			GuiaForestal guia = guiaForestalDAO.recuperarGuiaForestal(guiaDTO.getId());
					
			if (guia.getFiscalizaciones() != null && guia.getFiscalizaciones().size() > 0 ) {
				String errorDesasociar;
				if ( guia.getFiscalizaciones().size() > 1){
					errorDesasociar = "La Guia se va a desasociar de sus Fiscalizaciones: Desea Continuar?";
				} else {
					errorDesasociar = "La Guia se va a desasociar de su Fiscalizacion: Desea Continuar?";
				}
			
				Fiscalizacion fisc = guia.getFiscalizaciones().get(0);
				if (!guiaDTO.getPeriodoForestal().equals(fisc.getPeriodoForestal())) {
					error = "El Período  de la Guía Forestal no coincide con el de la Fiscalización. Período de la Guía Forestal: " + guiaDTO.getPeriodoForestal() +". Período de la Fiscalización:" + fisc.getPeriodoForestal() + "\n";
				}
				if (!"".equals(error)) {
					error = error + errorDesasociar;
				}
			}
		}

		return error;
	}
}
