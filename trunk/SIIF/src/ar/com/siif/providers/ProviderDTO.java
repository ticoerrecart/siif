package ar.com.siif.providers;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dto.ActaMartilladoDTO;
import ar.com.siif.dto.AforoDTO;
import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.ItemMenuDTO;
import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.dto.MarcacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.PMFDTO;
import ar.com.siif.dto.RodalDTO;
import ar.com.siif.dto.RolDTO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TranzonDTO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.struts.actions.forms.FiscalizacionForm;
import ar.com.siif.struts.actions.forms.GuiaForestalForm;
import ar.com.siif.utils.Fecha;

public abstract class ProviderDTO {
	
		public static EntidadDTO getEntidadDTO(Entidad entidad){
			
			EntidadDTO entidadDTO = new EntidadDTO();

			entidadDTO.setId(entidad.getId());
			entidadDTO.setNombre(entidad.getNombre());
			entidadDTO.setDireccion(entidad.getDireccion());
			entidadDTO.setTelefono(entidad.getTelefono());
			entidadDTO.setEmail(entidad.getEmail());
			entidadDTO.setLocalidad(ProviderDTO.getLocalidadDTO(entidad.getLocalidad()));
			entidadDTO.setTipoEntidadDesc(entidad.getTipoEntidad());
			entidadDTO.setTipoEntidad(entidad.getIdTipoEntidad());
			
			return entidadDTO;
		}
		
		public static TipoProductoDTO getTipoProductoDTO(TipoProducto tipoProducto){
			
			TipoProductoDTO tipoProductoDTO = new TipoProductoDTO();
			
			tipoProductoDTO.setId(tipoProducto.getId());
			tipoProductoDTO.setNombre(tipoProducto.getNombre());
			
			return tipoProductoDTO;
			
		}
		
		public static PMFDTO getPMFDTO(PMF pmf){
			
			PMFDTO pmfDTO = new PMFDTO();
			
			pmfDTO.setId(pmf.getId());
			pmfDTO.setExpediente(pmf.getExpediente());
			pmfDTO.setNombre(pmf.getNombre());
			pmfDTO.setProductorForestal(ProviderDTO.getEntidadDTO(pmf.getProductorForestal()));
			
			return pmfDTO;			
		}
		
		public static TranzonDTO getTranzonDTO(Tranzon tranzon){
			
			TranzonDTO tranzonDTO = new TranzonDTO();
			
			tranzonDTO.setId(tranzon.getId());
			tranzonDTO.setDisposicion(tranzon.getDisposicion());
			tranzonDTO.setNumero(tranzon.getNumero());
			tranzonDTO.setPmf(ProviderDTO.getPMFDTO(tranzon.getPmf()));
			
			return tranzonDTO;
		}
		
		public static MarcacionDTO getMarcacionDTO(Marcacion marcacion){
			
			MarcacionDTO marcacionDTO = new MarcacionDTO();
			
			marcacionDTO.setId(marcacion.getId());
			marcacionDTO.setDisposicion(marcacion.getDisposicion());
			marcacionDTO.setTranzon(ProviderDTO.getTranzonDTO(marcacion.getTranzon()));
			
			return marcacionDTO;
		}
		
		public static RodalDTO getRodalDTO(Rodal rodal){
			
			RodalDTO rodalDTO = new RodalDTO();
			
			rodalDTO.setId(rodal.getId());
			rodalDTO.setNombre(rodal.getNombre());
			rodalDTO.setMarcacion(ProviderDTO.getMarcacionDTO(rodal.getMarcacion()));
			
			return rodalDTO;
		}
				
		public static UsuarioDTO getUsuarioDTO(Usuario usuario){
						
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			
			usuarioDTO.setId(usuario.getId());
			usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
			usuarioDTO.setPassword(usuario.getPassword());
			usuarioDTO.setRol(ProviderDTO.getRolDTO(usuario.getRol()));
			usuarioDTO.setEntidad(ProviderDTO.getEntidadDTO(usuario.getEntidad()));
			usuarioDTO.setHabilitado(usuario.isHabilitado());
			
			return usuarioDTO;
		}
		
		public static LocalidadDTO getLocalidadDTO(Localidad localidad){
			
			LocalidadDTO localidadDTO = new LocalidadDTO();
			
			localidadDTO.setId(localidad.getId());
			localidadDTO.setNombre(localidad.getNombre());
			
			return localidadDTO;
		}
		
		public static AforoDTO getAforoDTO(Aforo aforo){
			
			AforoDTO aforoDTO = new AforoDTO();
			
			aforoDTO.setEstado(aforo.getEstado());
			aforoDTO.setId(aforo.getId());
			aforoDTO.setTipoProducto(ProviderDTO.getTipoProductoDTO(aforo.getTipoProducto()));
			aforoDTO.setTipoProductor(aforo.getTipoProductor());
			aforoDTO.setTipoProductorDesc(aforo.getTipoProductorDesc());
			aforoDTO.setValorAforo(aforo.getValorAforo());
			
			return aforoDTO;
		}
		
		public static RolDTO getRolDTO(Rol rol){
			
			List<ItemMenuDTO> listaMenuesDTO = new ArrayList<ItemMenuDTO>();
			RolDTO rolDTO = new RolDTO();
			
			rolDTO.setId(rol.getId());
			rolDTO.setRol(rol.getRol());
			
			for (ItemMenu menu : rol.getMenues()) {
				listaMenuesDTO.add(ProviderDTO.getItemMenuDTO(menu));
			}
			rolDTO.setMenues(listaMenuesDTO);
			
			return rolDTO;
		}		
		
		public static ItemMenuDTO getItemMenuDTO(ItemMenu menu){
			return ProviderDTO.getItemMenuDTO(menu, null);
		}
		
		private static ItemMenuDTO getItemMenuDTO(ItemMenu menu, ItemMenuDTO padre){
			
			if(menu != null){
				List<ItemMenuDTO> listaMenuDTO = new ArrayList<ItemMenuDTO>();
				ItemMenuDTO menuDTO = new ItemMenuDTO();
				
				menuDTO.setId(menu.getId());
				menuDTO.setItem(menu.getItem());
				menuDTO.setOrden(menu.getOrden());
				menuDTO.setPadre(padre);
				menuDTO.setUrl(menu.getUrl());

				for (ItemMenu menuHijo : menu.getHijos()) {
					listaMenuDTO.add(ProviderDTO.getItemMenuDTO(menuHijo,menuDTO));
				}
				menuDTO.setHijos(listaMenuDTO);
				
				return menuDTO;
			}	
			else{
				return null;
			}
		}
		
		public static FiscalizacionDTO getFiscalizacionDTO(Fiscalizacion fiscalizacion){
			
			List<MuestraDTO> listaMuestrasDTO = new ArrayList<MuestraDTO>();
			FiscalizacionDTO fiscalizacionDTO = new FiscalizacionDTO();
			
			fiscalizacionDTO.setCantidadMts(fiscalizacion.getCantidadMts());
			fiscalizacionDTO.setCantidadUnidades(fiscalizacion.getCantidadUnidades());
			fiscalizacionDTO.setFecha(Fecha.getFechaDDMMAAAASlash(Fecha.dateToStringDDMMAAAA(fiscalizacion.getFecha())));
			fiscalizacionDTO.setId(fiscalizacion.getId());
			//fiscalizacionDTO.setIdMarcacion(fiscalizacion.getRodal().getMarcacion().getId());
			fiscalizacionDTO.setOficinaAlta(ProviderDTO.getEntidadDTO(fiscalizacion.getOficinaAlta()));
			//fiscalizacionDTO.setIdPlanManejoForestal(fiscalizacion.getRodal().getMarcacion().getTranzon().getPmf().getId());
			fiscalizacionDTO.setProductorForestal(ProviderDTO.getEntidadDTO(fiscalizacion.getProductorForestal()));
			fiscalizacionDTO.setRodal(ProviderDTO.getRodalDTO(fiscalizacion.getRodal()));
			fiscalizacionDTO.setTipoProducto(ProviderDTO.getTipoProductoDTO(fiscalizacion.getTipoProducto()));
			//fiscalizacionDTO.setIdTranzon(fiscalizacion.getRodal().getMarcacion().getTranzon().getId());
			fiscalizacionDTO.setUsuario(ProviderDTO.getUsuarioDTO(fiscalizacion.getUsuario()));
			fiscalizacionDTO.setPeriodoForestal(fiscalizacion.getPeriodoForestal());
			fiscalizacionDTO.setTamanioMuestra(fiscalizacion.getTamanioMuestra());

			List<Muestra> listaMuestra = fiscalizacion.getMuestra();
			for (Muestra muestra : listaMuestra) {
				listaMuestrasDTO.add(ProviderDTO.getMuestraDTO(muestra));
			}			
			fiscalizacionDTO.setMuestra(listaMuestrasDTO);
			
			return fiscalizacionDTO;
		}
		
		public static MuestraDTO getMuestraDTO(Muestra muestra){
			
			MuestraDTO muestraDTO = new MuestraDTO();
			
			muestraDTO.setDiametro1(muestra.getDiametro1());
			muestraDTO.setDiametro2(muestra.getDiametro2());
			muestraDTO.setId(muestra.getId());
			muestraDTO.setLargo(muestra.getLargo());

			return muestraDTO;			
		}
		
	/*	
		public static ActaMartilladoDTO getActaMartilladoDTO(FiscalizacionForm form){
			
			ActaMartilladoDTO acta = new ActaMartilladoDTO();
			acta.setNroOrden(form.getNroOrden());
			acta.setProductorForestal(form.getProductorForestal());
			acta.setPeriodoForestal(form.getPeriodoForestal());
			acta.setExpediente(form.getExpediente());
			acta.setFecha(Fecha.stringDDMMAAAAToDate(form.getFecha()));
			acta.setUbicacionZonal(form.getUbicacionZonal());
			acta.setCantidadUnidades(form.getCantidadUnidades());
			acta.setTipoTransporte(form.getTipoTransporte());
			acta.setCantidadMts(form.getCantidadMts());
			acta.setTamanio(form.getTamanio());
			if(form.getGuiaForestal() != null && form.getGuiaForestal().getNroGuia() != 0){
				acta.setGuiaForestal(form.getGuiaForestal());
			}	
			
			return acta;
		}

		public static ActaMartilladoDTO getActaMartilladoDTO(ActaMartillado acta){
			
			ActaMartilladoDTO actaDTO = new ActaMartilladoDTO();
			actaDTO.setNroOrden(acta.getNroOrden());
			actaDTO.setProductorForestal(getEntidadDTO(acta.getProductorForestal()));
			actaDTO.setPeriodoForestal(acta.getPeriodoForestal());
			actaDTO.setExpediente(acta.getExpediente());
			actaDTO.setFecha(acta.getFecha());
			actaDTO.setUbicacionZonal(acta.getUbicacionZonal());
			actaDTO.setCantidadUnidades(acta.getCantidadUnidades());
			actaDTO.setTipoTransporte(acta.getTipoTransporte());
			actaDTO.setCantidadMts(acta.getCantidadMts());
			actaDTO.setTamanio(acta.getTamanio());		
			if(acta.getGuiaForestal() != null && acta.getGuiaForestal().getNroGuia() != 0){
				actaDTO.setGuiaForestal(getGuiaForestalDTO(acta.getGuiaForestal(),actaDTO));
			}
			
			return actaDTO;
		}	

		public static ActaMartilladoDTO getActaMartilladoDTO(ActaMartillado acta, GuiaForestalDTO guiaDTO){
			
			ActaMartilladoDTO actaDTO = new ActaMartilladoDTO();
			actaDTO.setNroOrden(acta.getNroOrden());
			actaDTO.setProductorForestal(getEntidadDTO(acta.getProductorForestal()));
			actaDTO.setPeriodoForestal(acta.getPeriodoForestal());
			actaDTO.setExpediente(acta.getExpediente());
			actaDTO.setFecha(acta.getFecha());
			actaDTO.setUbicacionZonal(acta.getUbicacionZonal());
			actaDTO.setCantidadUnidades(acta.getCantidadUnidades());
			actaDTO.setTipoTransporte(acta.getTipoTransporte());
			actaDTO.setCantidadMts(acta.getCantidadMts());
			actaDTO.setTamanio(acta.getTamanio());		
			actaDTO.setGuiaForestal(guiaDTO);
			
			return actaDTO;
		}	
		
		public static GuiaForestalDTO getGuiaForestalDTO(GuiaForestalForm form, Usuario usuario){
							
			GuiaForestalDTO guia = new GuiaForestalDTO();
			
			guia.setNroGuia(form.getNroGuia());
			guia.setFiscalizacion(form.getFiscalizacion());
			guia.setLugarCorte(form.getLugarCorte());
			//guia.setPermisionario(form.getPermisionario());
			guia.setFechaVencimiento(Fecha.stringDDMMAAAAToDate(form.getFechaVencimiento()));
			guia.setDistanciaAforoMovil(form.getDistanciaAforoMovil());
			guia.setNroExpediente(form.getNroExpediente());
			guia.setPeriodoForestal(form.getPeriodoForestal());
			guia.setDisposicionNro(form.getDisposicionNro());
			guia.setTipo(form.getTipo());
			guia.setEstado(form.getEstado());
			guia.setEspecie(form.getEspecie());
			guia.setVolumenTotal(form.getVolumenTotal());
			guia.setCantidad(form.getCantidad());
			guia.setImporte(form.getImporte());
			guia.setAforo(form.getAforo());
			guia.setInspFiscalizacion(form.getInspFiscalizacion());
			guia.setValorAforos(form.getValorAforos());
			guia.setObservaciones(form.getObservaciones());
			guia.setLocalidad(form.getLocalidad());
			guia.setFecha(Fecha.stringDDMMAAAAToDate(form.getFecha()));		
			guia.setValesTransporte(normalizarValesTransporte(form.getValesTransporte()));
			guia.setBoletasDeposito(normalizarBoletasDeposito(form.getBoletasDeposito()));
			guia.setUsuario(usuario);
			
			return guia;
		}

		public static GuiaForestalDTO getGuiaForestalDTO(GuiaForestal guia, ActaMartilladoDTO actaDTO){
			
			GuiaForestalDTO guiaDTO = new GuiaForestalDTO();
			
			guiaDTO.setNroGuia(guia.getNroGuia());
			guiaDTO.setFiscalizacion(actaDTO);
			guiaDTO.setLugarCorte(guia.getLugarCorte());
			//guiaDTO.setPermisionario(guia.getPermisionario().getId());
			guiaDTO.setFechaVencimiento(guia.getFechaVencimiento());
			guiaDTO.setDistanciaAforoMovil(guia.getDistanciaAforoMovil());
			guiaDTO.setNroExpediente(guia.getNroExpediente());
			guiaDTO.setPeriodoForestal(guia.getPeriodoForestal());
			guiaDTO.setDisposicionNro(guia.getDisposicionNro());
			guiaDTO.setTipo(guia.getTipo());
			guiaDTO.setEstado(guia.getEstado());
			guiaDTO.setEspecie(guia.getEspecie());
			guiaDTO.setVolumenTotal(guia.getVolumenTotal());
			guiaDTO.setCantidad(guia.getCantidad());
			guiaDTO.setImporte(guia.getImporte());
			guiaDTO.setAforo(guia.getAforo());
			guiaDTO.setInspFiscalizacion(guia.getInspFiscalizacion());
			guiaDTO.setValorAforos(guia.getValorAforos());
			guiaDTO.setObservaciones(guia.getObservaciones());
			guiaDTO.setLocalidad(guia.getLocalidad());
			guiaDTO.setFecha(guia.getFecha());		
			guiaDTO.setValesTransporte(getValesTransporteDTO(guia.getValesTransporte(),guiaDTO));
			guiaDTO.setBoletasDeposito(getBoletasDesposito(guia.getBoletasDeposito(),guiaDTO));
			guiaDTO.setUsuario(guia.getUsuario());
			
			return guiaDTO;		
		}	
		
		public static GuiaForestalDTO getGuiaForestalDTO(GuiaForestal guia){
			
			GuiaForestalDTO guiaDTO = new GuiaForestalDTO();
			
			guiaDTO.setNroGuia(guia.getNroGuia());
			guiaDTO.setFiscalizacion(getActaMartilladoDTO(guia.getActaMartillado(),guiaDTO));
			guiaDTO.setLugarCorte(guia.getLugarCorte());
			//guiaDTO.setPermisionario(guia.getPermisionario().getId());
			guiaDTO.setFechaVencimiento(guia.getFechaVencimiento());
			guiaDTO.setDistanciaAforoMovil(guia.getDistanciaAforoMovil());
			guiaDTO.setNroExpediente(guia.getNroExpediente());
			guiaDTO.setPeriodoForestal(guia.getPeriodoForestal());
			guiaDTO.setDisposicionNro(guia.getDisposicionNro());
			guiaDTO.setTipo(guia.getTipo());
			guiaDTO.setEstado(guia.getEstado());
			guiaDTO.setEspecie(guia.getEspecie());
			guiaDTO.setVolumenTotal(guia.getVolumenTotal());
			guiaDTO.setCantidad(guia.getCantidad());
			guiaDTO.setImporte(guia.getImporte());
			guiaDTO.setAforo(guia.getAforo());
			guiaDTO.setInspFiscalizacion(guia.getInspFiscalizacion());
			guiaDTO.setValorAforos(guia.getValorAforos());
			guiaDTO.setObservaciones(guia.getObservaciones());
			guiaDTO.setLocalidad(guia.getLocalidad());
			guiaDTO.setFecha(guia.getFecha());		
			guiaDTO.setValesTransporte(getValesTransporteDTO(guia.getValesTransporte(),guiaDTO));
			guiaDTO.setBoletasDeposito(getBoletasDesposito(guia.getBoletasDeposito(),guiaDTO));
			guiaDTO.setUsuario(guia.getUsuario());
			
			return guiaDTO;		
		}	
		
		public static List<BoletaDepositoDTO> getBoletasDesposito(List<BoletaDeposito> boletas, GuiaForestalDTO guiaDTO){
			
			List<BoletaDepositoDTO> boletasDTO = new ArrayList<BoletaDepositoDTO>();
			
			for (BoletaDeposito boleta : boletas) {
				
				boletasDTO.add(getBoletaDesposito(boleta,guiaDTO));
			}
			
			return boletasDTO;
		}	
		
		public static BoletaDepositoDTO getBoletaDesposito(BoletaDeposito boleta, GuiaForestalDTO guiaDTO){
			
			BoletaDepositoDTO boletaDTO = new BoletaDepositoDTO();
			
			boletaDTO.setArea(boleta.getArea());
			boletaDTO.setConcepto(boleta.getConcepto());
			boletaDTO.setEfectivoCheque(boleta.getEfectivoCheque());
			boletaDTO.setFechaPago(Fecha.dateToStringDDMMAAAA(boleta.getFechaPago()));
			boletaDTO.setFechaVencimiento(Fecha.dateToStringDDMMAAAA(boleta.getFechaVencimiento()));
			boletaDTO.setMonto(boleta.getMonto());
			boletaDTO.setNumero(boleta.getNumero());
			boletaDTO.setGuiaForestal(guiaDTO);
			
			return boletaDTO;	
		}
		
		public static List<ValeTransporteDTO> getValesTransporteDTO(List<ValeTransporte> vales, GuiaForestalDTO guiaDTO){
			
			List<ValeTransporteDTO> valesDTO = new ArrayList<ValeTransporteDTO>();
			
			for (ValeTransporte vale : vales) {
				
				valesDTO.add(getValeTransporte(vale,guiaDTO));
			}
			
			return valesDTO;
		}
		
		public static ValeTransporteDTO getValeTransporte(ValeTransporte vale, GuiaForestalDTO guiaDTO){
			
			ValeTransporteDTO valeDTO = new ValeTransporteDTO();
			
			valeDTO.setCantidadMts(vale.getCantidadMts());
			valeDTO.setDestino(vale.getDestino());
			valeDTO.setDominio(vale.getDominio());
			valeDTO.setEspecie(vale.getEspecie());
			valeDTO.setFecha(Fecha.dateToStringDDMMAAAA(vale.getFecha()));
			valeDTO.setFechaVencimiento(Fecha.dateToStringDDMMAAAA(vale.getFechaVencimiento()));
			valeDTO.setMarca(vale.getMarca());
			valeDTO.setNroPiezas(vale.getNroPiezas());
			valeDTO.setNumero(vale.getNumero());
			valeDTO.setOrigen(vale.getOrigen());
			valeDTO.setProducto(vale.getProducto());
			valeDTO.setGuiaForestal(guiaDTO);
			valeDTO.setVehiculo(vale.getVehiculo());
			
			return valeDTO;
		}
		
		private static List<BoletaDepositoDTO> normalizarBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) 
		{
			List<BoletaDepositoDTO> boletasAEliminar = new ArrayList<BoletaDepositoDTO>();
			for (BoletaDepositoDTO boleta : boletasDeposito) {
				
				if(boleta.getNumero() == 0){
					boletasAEliminar.add(boleta);
				}
			}
			boletasDeposito.removeAll(boletasAEliminar);
			
			return boletasDeposito;
		}

		private static List<ValeTransporteDTO> normalizarValesTransporte(List<ValeTransporteDTO> valesTransporte) 
		{

			List<ValeTransporteDTO> valesAEliminar = new ArrayList<ValeTransporteDTO>();
			for (ValeTransporteDTO vale : valesTransporte) {
				
				if(vale.getNumero() == 0){
					valesAEliminar.add(vale);
				}
			}
			valesTransporte.removeAll(valesAEliminar);
			
			return valesTransporte;
		}*/
}
