package ar.com.siif.providers;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.struts.actions.forms.FiscalizacionForm;
import ar.com.siif.struts.actions.forms.GuiaForestalForm;
import ar.com.siif.utils.Fecha;

@Deprecated
public abstract class ProviderDominio {

	public static Fiscalizacion getActaMartillado(FiscalizacionForm form) {

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
	}

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
