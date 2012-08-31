package ar.com.siif.providers;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dto.AforoDTO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.ItemMenuDTO;
import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.RolDTO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.Obrajero;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.PPF;
import ar.com.siif.negocio.RecursosNaturales;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.struts.actions.forms.FiscalizacionForm;
import ar.com.siif.struts.actions.forms.GuiaForestalForm;
import ar.com.siif.utils.Fecha;

public abstract class ProviderDominio {

	public static Fiscalizacion getFiscalizacion(FiscalizacionDTO fiscalizacionDTO, List<MuestraDTO>muestrasDTO,
												Rodal rodal, Entidad productorForestal, Entidad oficinaForestal,
												TipoProducto tipoProducto, Usuario usuario){
		
		Fiscalizacion fiscalizacion = new Fiscalizacion();
		
		fiscalizacion.setCantidadMts(fiscalizacionDTO.getCantidadMts());
		fiscalizacion.setCantidadUnidades(fiscalizacionDTO.getCantidadUnidades());
		fiscalizacion.setFecha(Fecha.stringDDMMAAAAToUtilDate(fiscalizacionDTO.getFecha()));
		fiscalizacion.setOficinaAlta(oficinaForestal);
		fiscalizacion.setPeriodoForestal(fiscalizacionDTO.getPeriodoForestal());
		fiscalizacion.setProductorForestal(productorForestal);
		fiscalizacion.setRodal(rodal);
		fiscalizacion.setTamanioMuestra(fiscalizacionDTO.getTamanioMuestra());
		fiscalizacion.setTipoProducto(tipoProducto);
		fiscalizacion.setUsuario(usuario);

		List<Muestra> muestras = new ArrayList<Muestra>();
		for (MuestraDTO muestraDTO : muestrasDTO) {
			muestras.add(ProviderDominio.getMuestra(muestraDTO,fiscalizacion));
		}
		fiscalizacion.setMuestra(muestras);
		
		return fiscalizacion;
		
	}
	
	public static Muestra getMuestra(MuestraDTO muestraDTO, Fiscalizacion fiscalizacion){
		
		Muestra muestra = new Muestra();
		
		muestra.setLargo(muestraDTO.getLargo());
		muestra.setDiametro1(muestraDTO.getDiametro1());
		muestra.setDiametro2(muestraDTO.getDiametro2());
		muestra.setFiscalizacion(fiscalizacion);
		
		return muestra;
	}
	
	public static TipoProducto getTipoProductoForestal(TipoProductoDTO tipoProductoForestalDTO){
		
		TipoProducto tipoProducto = new TipoProducto();
		tipoProducto.setNombre(tipoProductoForestalDTO.getNombre());
		
		return tipoProducto;
	}
	
	public static Entidad getEntidad(EntidadDTO entidadDTO, Localidad localidad){
		
		Entidad entidad = null;
		if (TipoDeEntidad.PPF.getName().equalsIgnoreCase(entidadDTO.getTipoEntidad())) {
			entidad = new PPF();
		} else {
			if (TipoDeEntidad.OBR.getName().equals(entidadDTO.getTipoEntidad())) {
				entidad = new Obrajero();
			} else {
				entidad = new RecursosNaturales();
			}
		}

		entidad.setDireccion(entidadDTO.getDireccion());
		entidad.setEmail(entidadDTO.getEmail());
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setTelefono(entidadDTO.getTelefono());		
		entidad.setLocalidad(localidad);
		
		return entidad;
	}
	
	public static Entidad getEntidad(Entidad entidad, EntidadDTO entidadDTO, Localidad localidad){
		
		entidad.setDireccion(entidadDTO.getDireccion());
		entidad.setEmail(entidadDTO.getEmail());
		entidad.setLocalidad(localidad);
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setTelefono(entidadDTO.getTelefono());
		
		return entidad;
	}
	
	public static Aforo getAforo(AforoDTO aforoDTO, TipoProducto tipoProducto){
		
		Aforo aforo = new Aforo();
		
		aforo.setEstado(aforoDTO.getEstado());
		aforo.setTipoProducto(tipoProducto);
		aforo.setTipoProductor(aforoDTO.getTipoProductor());
		aforo.setValorAforo(aforoDTO.getValorAforo());

		return aforo;
	}
	
	public static Aforo getAforo(Aforo aforo, AforoDTO aforoDTO, TipoProducto tipoProducto){
		
		aforo.setEstado(aforoDTO.getEstado());
		aforo.setTipoProducto(tipoProducto);
		aforo.setTipoProductor(aforoDTO.getTipoProductor());
		aforo.setValorAforo(aforoDTO.getValorAforo());
		
		return aforo;
	}	
	
	public static Localidad getLocalidad(LocalidadDTO localidadDTO){
		
		Localidad localidad = new Localidad();		
		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}

	public static Localidad getLocalidad(Localidad localidad, LocalidadDTO localidadDTO){
		
		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}	
	
	public static PMF getPMF(String expediente,String nombre, Entidad entidad){
		
		PMF pmf = new PMF();
		pmf.setExpediente(expediente);
		pmf.setNombre(nombre);
		pmf.setProductorForestal(entidad);
		pmf.setTranzones(null);
		
		return pmf;
	}
	
	public static Tranzon getTranzon(String numero, String disposicionTranzon,PMF pmf){
		
		Tranzon tranzon = new Tranzon();
		tranzon.setDisposicion(disposicionTranzon);
		tranzon.setNumero(numero);
		tranzon.setPmf(pmf);
		tranzon.setMarcaciones(null);
		
		return tranzon;
	}
	
	public static Marcacion getMarcacion(String disposicionMarcacion, Tranzon tranzon){
		
		Marcacion marcacion = new Marcacion();
		marcacion.setDisposicion(disposicionMarcacion);
		marcacion.setTranzon(tranzon);
		marcacion.setRodales(null);
		
		return marcacion;		
	}
	
	public static Rodal getRodal(String nombre, Marcacion marcacion){
		
		Rodal rodal = new Rodal();
		rodal.setNombre(nombre);
		rodal.setMarcacion(marcacion);
		
		return rodal;
	}
	
	public static Usuario getUsuario(UsuarioDTO usuarioDTO, Entidad entidad, Rol rol){
		
		Usuario usuario = new Usuario();
		usuario.setEntidad(entidad);
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);
		
		return usuario;
	}
	
	public static Usuario getUsuario(Usuario usuario, UsuarioDTO usuarioDTO, Entidad entidad, Rol rol){
		
		usuario.setEntidad(entidad);
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);
		
		return usuario;
	}	
	
	public static Rol getRol(RolDTO rolDTO){
		
		Rol rol = new Rol();		
		rol.setRol(rolDTO.getRol());
		rol.setMenues(new ArrayList<ItemMenu>());

		return rol;
	}
	
	public static GuiaForestal getGuiaForestal(GuiaForestalDTO guiaDTO,List<BoletaDepositoDTO> listaBoletaDepositoDTO,
			   									List<ValeTransporteDTO> listaValeTransporteDTO,
			   									Fiscalizacion fiscalizacion, Usuario usuario)
	{
		
		GuiaForestal guia = new GuiaForestal();
		
		if(guiaDTO.getId() != null && guiaDTO.getId() != 0){
			guia.setId(guiaDTO.getId());
		}	
		guia.setAforo(guiaDTO.getAforo());
		guia.setCantidad(guiaDTO.getCantidad());
		guia.setCantidadMts(guiaDTO.getCantidadMts());
		guia.setCantidadUnidades(guiaDTO.getCantidadUnidades());
		guia.setDistanciaAforoMovil(guiaDTO.getDistanciaAforoMovil());
		guia.setEspecie(guiaDTO.getEspecie());
		guia.setEstado(guiaDTO.getEstado());
		guia.setFecha(Fecha.stringDDMMAAAAToUtilDate(guiaDTO.getFecha()));
		guia.setFechaVencimiento(Fecha.stringDDMMAAAAToUtilDate(guiaDTO.getFechaVencimiento()));
		guia.setFiscalizacion(fiscalizacion);
		guia.setImporte(guiaDTO.getImporte());
		guia.setInspFiscalizacion(guiaDTO.getInspFiscalizacion());
		guia.setLocalidad(guiaDTO.getLocalidad());
		guia.setNroGuia(guiaDTO.getNroGuia());
		guia.setObservaciones(guiaDTO.getObservaciones());
		guia.setUsuario(usuario);
		guia.setValorAforos(guiaDTO.getValorAforos());

		for (BoletaDepositoDTO boletaDTO : listaBoletaDepositoDTO) {
			guia.getBoletasDeposito().add(ProviderDominio.getBoletaDeposito(guia,boletaDTO));
		}
		
		for (ValeTransporteDTO valeDTO : listaValeTransporteDTO) {
			guia.getValesTransporte().add(ProviderDominio.getValeTransporte(guia,valeDTO));
		}		
		
		return guia;
	}
	
	public static BoletaDeposito getBoletaDeposito(GuiaForestal guia, BoletaDepositoDTO boletaDTO){
		
		BoletaDeposito boleta = new BoletaDeposito();
		
		if(boletaDTO.getIdBoleta() != 0){
			boleta.setId(boletaDTO.getIdBoleta());
		}	
		boleta.setArea(boletaDTO.getArea());
		boleta.setConcepto(boletaDTO.getConcepto());
		boleta.setEfectivoCheque(boletaDTO.getEfectivoCheque());
		if(boletaDTO.getFechaPago() != null && !boletaDTO.getFechaPago().equals("")){
			boleta.setFechaPago(Fecha.stringDDMMAAAAToUtilDate(boletaDTO.getFechaPago()));
		}	
		boleta.setFechaVencimiento(Fecha.stringDDMMAAAAToUtilDate(boletaDTO.getFechaVencimiento()));
		boleta.setGuiaForestal(guia);
		boleta.setMonto(boletaDTO.getMonto());
		boleta.setNumero(boletaDTO.getNumero());
				
		return boleta;
	}
	
	public static ValeTransporte getValeTransporte(GuiaForestal guia, ValeTransporteDTO valeDTO){
		
		ValeTransporte vale = new ValeTransporte();
		
		vale.setCantidadMts(valeDTO.getCantidadMts());
		vale.setDestino(valeDTO.getDestino());
		vale.setDominio(valeDTO.getDominio());
		vale.setEspecie(valeDTO.getEspecie());
		if(valeDTO.getFechaDevolucion() != null && !valeDTO.getFechaDevolucion().equals("")){
			vale.setFechaDevolucion(Fecha.stringDDMMAAAAToUtilDate(valeDTO.getFechaDevolucion()));
		}	
		vale.setFechaVencimiento(Fecha.stringDDMMAAAAToUtilDate(valeDTO.getFechaVencimiento()));
		vale.setGuiaForestal(guia);
		if(valeDTO.getId() != 0){
			vale.setId(valeDTO.getId());
		}	
		vale.setMarca(valeDTO.getMarca());
		vale.setNroPiezas(valeDTO.getNroPiezas());
		vale.setNumero(valeDTO.getNumero());
		vale.setOrigen(valeDTO.getOrigen());
		vale.setProducto(valeDTO.getProducto());
		vale.setVehiculo(valeDTO.getVehiculo());
				
		return vale;
	}
	
	/*public static Fiscalizacion getActaMartillado(FiscalizacionForm form) {

		Fiscalizacion acta = form.getFiscalizacion();
		if (form.getFecha() != null && !form.getFecha().equals("")) {
			acta.setFecha(Fecha.stringDDMMAAAAToDate(form.getFecha()));
		}

		return acta;
	}

	public static void getActaMartilladoAModificar(Fiscalizacion actaBD, Fiscalizacion acta) {

		actaBD.setCantidadMts(acta.getCantidadMts());
		actaBD.setCantidadUnidades(acta.getCantidadUnidades());
		// actaBD.getUbicacion().setExpediente(acta.getUbicacion().getExpediente());
		if (acta.getFecha() != null) {
			actaBD.setFecha(acta.getFecha());
		}
		actaBD.setPeriodoForestal(acta.getPeriodoForestal());
		actaBD.setProductorForestal(acta.getProductorForestal());
		actaBD.setTamanioMuestra(acta.getTamanioMuestra());
		// actaBD.setUbicacionZonal(acta.getUbicacionZonal());

	}

	public static GuiaForestal getGuiaForestal(GuiaForestalForm guiaForm,
			Fiscalizacion actaMartillado, Usuario usuario) {

		GuiaForestal guia = guiaForm.getGuiaForestal();
		guia.setFiscalizacion(actaMartillado);
		actaMartillado.setGuiaForestal(guia);
		guia.setFecha(Fecha.stringDDMMAAAAToDate(guiaForm.getFecha()));
		guia.setFechaVencimiento(Fecha.stringDDMMAAAAToDate(guiaForm.getFechaVencimiento()));

		guia.setBoletasDeposito(getBoletasDeposito(guiaForm.getBoletasDeposito(), guia));
		guia.setValesTransporte(getValesTransporte(guiaForm.getValesTransporte(), guia));

		guia.setUsuario(usuario);

		return guia;
	}

	public static List<BoletaDeposito> getBoletasDeposito(List<BoletaDeposito> boletas,
			GuiaForestal guia) {

		List<BoletaDeposito> boletasAEliminar = new ArrayList<BoletaDeposito>();

		for (BoletaDeposito boleta : boletas) {

			if (boleta.getNumero() == 0) {
				boletasAEliminar.add(boleta);
			} else {
				boleta.setGuiaForestal(guia);

				// BORRAR
				boleta.setFechaVencimiento(guia.getFechaVencimiento());
				// boleta.setFechaPago(guia.getFecha());
				//
			}
		}
		boletas.removeAll(boletasAEliminar);

		return boletas;
	}

	public static List<ValeTransporte> getValesTransporte(List<ValeTransporte> vales,
			GuiaForestal guia) {

		List<ValeTransporte> valesAEliminar = new ArrayList<ValeTransporte>();

		for (ValeTransporte vale : vales) {

			if (vale.getNumero() == 0) {
				valesAEliminar.add(vale);
			} else {
				vale.setGuiaForestal(guia);

				// BORRAR
				// vale.setFecha(guia.getFecha());
				vale.setFechaVencimiento(guia.getFechaVencimiento());
				//
			}
		}

		vales.removeAll(valesAEliminar);

		return vales;
	}

	public static GuiaForestal getGuiaForestalRegistrarPago(GuiaForestalForm guiaForm) {

		GuiaForestal guia = guiaForm.getGuiaForestal();
		int i = 0;
		for (BoletaDeposito boleta : guiaForm.getBoletasDeposito()) {

			if (boleta != null && boleta.getFechaPagoTransient() != null
					&& !boleta.getFechaPagoTransient().equals("")) {

				BoletaDeposito boletaGuia = guia.getBoletasDeposito().get(i);
				boletaGuia.setFechaPago(Fecha.stringDDMMAAAAToDate(boleta.getFechaPagoTransient()));
			}
			i++;
		}

		return guia;
	}*/

	/*
	 * public static Entidad getEntidad(EntidadDTO entidadDTO){
	 * 
	 * Entidad entidad = new Entidad();
	 * entidad.setDireccion(entidadDTO.getDireccion());
	 * entidad.setEmail(entidadDTO.getEmail());
	 * entidad.setId(entidadDTO.getId());
	 * entidad.setLocalidad(entidadDTO.getLocalidad());
	 * entidad.setNombre(entidadDTO.getNombre());
	 * entidad.setTelefono(entidadDTO.getTelefono());
	 * 
	 * return entidad; }
	 * 
	 * public static ActaMartillado getActaMartillado(ActaMartilladoDTO
	 * actaDTO){
	 * 
	 * ActaMartillado acta = new ActaMartillado();
	 * acta.setNroOrden(actaDTO.getNroOrden());
	 * acta.setProductorForestal(getEntidad(actaDTO.getProductorForestal()));
	 * acta.setPeriodoForestal(actaDTO.getPeriodoForestal());
	 * acta.setExpediente(actaDTO.getExpediente());
	 * acta.setFecha(actaDTO.getFecha());
	 * acta.setUbicacionZonal(actaDTO.getUbicacionZonal());
	 * acta.setCantidadUnidades(actaDTO.getCantidadUnidades());
	 * acta.setTipoTransporte(actaDTO.getTipoTransporte());
	 * acta.setCantidadMts(actaDTO.getCantidadMts());
	 * acta.setTamanio(actaDTO.getTamanio()); if(actaDTO.getGuiaForestal() !=
	 * null && actaDTO.getGuiaForestal().getNroGuia() != 0){
	 * acta.setGuiaForestal(getGuiaForestal(actaDTO.getGuiaForestal(), acta)); }
	 * 
	 * return acta; }
	 * 
	 * public static GuiaForestal getGuiaForestal(GuiaForestalDTO guiaDTO,
	 * ActaMartillado acta){
	 * 
	 * GuiaForestal guia = new GuiaForestal();
	 * 
	 * guia.setNroGuia(guiaDTO.getNroGuia());
	 * guia.setLugarCorte(guiaDTO.getLugarCorte());
	 * guia.setFechaVencimiento(guiaDTO.getFechaVencimiento());
	 * guia.setDistanciaAforoMovil(guiaDTO.getDistanciaAforoMovil());
	 * guia.setNroExpediente(guiaDTO.getNroExpediente());
	 * guia.setPeriodoForestal(guiaDTO.getPeriodoForestal());
	 * guia.setDisposicionNro(guiaDTO.getDisposicionNro());
	 * guia.setTipo(guiaDTO.getTipo()); guia.setEstado(guiaDTO.getEstado());
	 * guia.setEspecie(guiaDTO.getEspecie());
	 * guia.setVolumenTotal(guiaDTO.getVolumenTotal());
	 * guia.setCantidad(guiaDTO.getCantidad());
	 * guia.setImporte(guiaDTO.getImporte()); guia.setAforo(guiaDTO.getAforo());
	 * guia.setInspFiscalizacion(guiaDTO.getInspFiscalizacion());
	 * guia.setValorAforos(guiaDTO.getValorAforos());
	 * guia.setObservaciones(guiaDTO.getObservaciones());
	 * guia.setLocalidad(guiaDTO.getLocalidad());
	 * guia.setFecha(guiaDTO.getFecha());
	 * guia.setValesTransporte(getValesTransporte
	 * (guiaDTO.getValesTransporte(),guia));
	 * guia.setBoletasDeposito(getBoletasDeposito
	 * (guiaDTO.getBoletasDeposito(),guia));
	 * guia.setUsuario(guiaDTO.getUsuario()); guia.setActaMartillado(acta);
	 * 
	 * return guia; }
	 * 
	 * public static List<BoletaDeposito>
	 * getBoletasDeposito(List<BoletaDepositoDTO> boletasDepositoDTO,
	 * GuiaForestal guia) { List<BoletaDeposito> boletasDeposito = new
	 * ArrayList<BoletaDeposito>();
	 * 
	 * for (BoletaDepositoDTO boletaDTO : boletasDepositoDTO) {
	 * 
	 * boletasDeposito.add(getBoletaDeposito(boletaDTO,guia)); }
	 * 
	 * return boletasDeposito; }
	 * 
	 * public static BoletaDeposito getBoletaDeposito(BoletaDepositoDTO
	 * boletaDTO, GuiaForestal guia) {
	 * 
	 * BoletaDeposito boleta = new BoletaDeposito();
	 * boleta.setNumero(boletaDTO.getNumero());
	 * boleta.setConcepto(boletaDTO.getConcepto());
	 * boleta.setArea(boletaDTO.getArea());
	 * boleta.setEfectivoCheque(boletaDTO.getEfectivoCheque());
	 * boleta.setMonto(boletaDTO.getMonto());
	 * boleta.setFechaPago(Fecha.stringDDMMAAAAToDate
	 * (boletaDTO.getFechaPago()));
	 * boleta.setFechaVencimiento(Fecha.stringDDMMAAAAToDate
	 * (boletaDTO.getFechaVencimiento())); boleta.setGuiaForestal(guia);
	 * 
	 * return boleta; }
	 * 
	 * public static List<ValeTransporte>
	 * getValesTransporte(List<ValeTransporteDTO> valesTransporteDTO,
	 * GuiaForestal guia) {
	 * 
	 * List<ValeTransporte> valesTransporte = new ArrayList<ValeTransporte>();
	 * 
	 * for (ValeTransporteDTO valeTransporteDTO : valesTransporteDTO) {
	 * 
	 * valesTransporte.add(getValeTransporte(valeTransporteDTO,guia)); }
	 * 
	 * return valesTransporte; }
	 * 
	 * private static ValeTransporte getValeTransporte(ValeTransporteDTO
	 * valeDTO, GuiaForestal guia) {
	 * 
	 * ValeTransporte vale = new ValeTransporte();
	 * 
	 * vale.setNumero(valeDTO.getNumero());
	 * vale.setFecha(Fecha.stringDDMMAAAAToDate(valeDTO.getFecha()));
	 * vale.setOrigen(valeDTO.getOrigen());
	 * vale.setDestino(valeDTO.getDestino());
	 * vale.setVehiculo(valeDTO.getVehiculo());
	 * vale.setMarca(valeDTO.getMarca()); vale.setDominio(valeDTO.getDominio());
	 * vale
	 * .setFechaVencimiento(Fecha.stringDDMMAAAAToDate(valeDTO.getFechaVencimiento
	 * ())); vale.setProducto(valeDTO.getProducto());
	 * vale.setNroPiezas(valeDTO.getNroPiezas());
	 * vale.setCantidadMts(valeDTO.getCantidadMts());
	 * vale.setEspecie(valeDTO.getEspecie()); vale.setGuiaForestal(guia);
	 * 
	 * return vale; }
	 */
}
