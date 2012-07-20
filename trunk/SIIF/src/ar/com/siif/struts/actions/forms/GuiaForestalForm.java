package ar.com.siif.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Fecha;

public class GuiaForestalForm extends ActionForm {

	// private int nroGuia;
	// private ActaMartilladoDTO fiscalizacion;
	// private long permisionario;
	// private String lugarCorte;
	// private String fechaVencimiento;
	// /private int distanciaAforoMovil;
	// private String periodoForestal;
	// private String nroExpediente;
	// private String disposicionNro;

	// private String tipo;
	// private String estado;
	// private String especie;
	// private double volumenTotal;
	// private int cantidad;
	// private double importe;

	// private double aforo;
	// private double inspFiscalizacion;
	// private String valorAforos;
	// private String observaciones;

	// private List<BoletaDepositoDTO> boletasDeposito;
	// private List<ValeTransporteDTO> valesTransporte;

	// private String localidad;
	// private String fecha;

	private GuiaForestal guiaForestal;

	private long idFiscalizacion;

	private long permisionario;

	private String fechaVencimiento;

	private String fecha;

	private List<BoletaDeposito> boletasDeposito;

	private List<ValeTransporte> valesTransporte;

	public GuiaForestalForm() {

		guiaForestal = new GuiaForestal();

		boletasDeposito = (List<BoletaDeposito>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(BoletaDeposito.class));

		valesTransporte = (List<ValeTransporte>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(ValeTransporte.class));

		/*
		 * boletasDeposito = new Vector<BoletaDeposito>(); for (int i = 0; i <
		 * Constantes.COLECCION_BOLETAS_DEPOSITO_MAX; i++) {
		 * boletasDeposito.add(new BoletaDeposito()); }
		 * 
		 * valesTransporte = new Vector<ValeTransporte>(); for (int i = 0; i <
		 * Constantes.COLECCION_VALES_TRANSPORTE_MAX; i++) {
		 * valesTransporte.add(new ValeTransporte()); }
		 */
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		GuiaForestal guia = (GuiaForestal) request.getAttribute("guia");

		if (guiaForestal.getNroGuia() == 0) {
			if (guia != null) {
				guiaForestal = guia;
			} else {
				guiaForestal = new GuiaForestal();
			}
		}

		boletasDeposito = (List<BoletaDeposito>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(BoletaDeposito.class));

		valesTransporte = (List<ValeTransporte>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(ValeTransporte.class));

		/*
		 * boletasDeposito = new Vector<BoletaDeposito>(); for (int i = 0; i <
		 * Constantes.COLECCION_BOLETAS_DEPOSITO_MAX; i++) {
		 * boletasDeposito.add(new BoletaDeposito()); }
		 * 
		 * valesTransporte = new Vector<ValeTransporte>(); for (int i = 0; i <
		 * Constantes.COLECCION_VALES_TRANSPORTE_MAX; i++) {
		 * valesTransporte.add(new ValeTransporte()); }
		 */
	}

	public long getPermisionario() {
		return permisionario;
	}

	public void setPermisionario(long permisionario) {
		this.permisionario = permisionario;
	}

	public GuiaForestal getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestal guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

	public long getIdFiscalizacion() {
		return idFiscalizacion;
	}

	public void setIdFiscalizacion(long idFiscalizacion) {
		this.idFiscalizacion = idFiscalizacion;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
		
		if (this.fechaVencimiento != null && this.fechaVencimiento != "") {
			this.getGuiaForestal().setFechaVencimiento(Fecha.stringDDMMAAAAToDate(this.fechaVencimiento));
		}		
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
		
		if (this.fecha != null && this.fecha != "") {
			this.getGuiaForestal().setFecha(Fecha.stringDDMMAAAAToDate(this.fecha));
		}		
		
	}

	public List<BoletaDeposito> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDeposito> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

	public List<ValeTransporte> getValesTransporte() {
		return valesTransporte;
	}

	public void setValesTransporte(List<ValeTransporte> valesTransporte) {
		this.valesTransporte = valesTransporte;
	}
	
	public boolean validar(StringBuffer error) {
		boolean ok = true;
		boolean ok2 = true;
		boolean ok3 = true;
		boolean ok4 = true;
		boolean ok5 = true;
		boolean ok6 = true;
		boolean ok7 = true;
		boolean ok8 = true;
		boolean ok9 = true;
		boolean ok10 = true;

		ok = Validator.validarEnteroMayorQue(0,
				Integer.toString(this.getGuiaForestal().getNroGuia()), "Nro de Guía", error);		
		ok2 = Validator.requerido(this.getFechaVencimiento(), "Valido Hasta", error);
		
		ok3 = Validator.requerido(String.valueOf(this.getGuiaForestal().getImporte()), "Importe", error);
		if(ok3){
			ok3 = Validator.validarDoubleMayorQue(0,String.valueOf(this.getGuiaForestal().getImporte()), "Importe", error);
		}
		
		ok4 = Validator.requerido(this.getGuiaForestal().getEstado(), "Estado", error);
		ok5 = Validator.requerido(this.getGuiaForestal().getEspecie(), "Especie", error);
		double montoTotal = this.getGuiaForestal().getImporte() + this.getGuiaForestal().getInspFiscalizacion(); 
		ok6 = Validator.validarBoletasDeposito(this.getBoletasDeposito(),montoTotal,error);
		ok7 = Validator.validarValesTransporte(this.getValesTransporte(),error);
		ok9 = Validator.requerido(this.getGuiaForestal().getLocalidad(), "Localidad", error);
		ok10 = Validator.requerido(this.getFecha(), "Fecha", error);

		/*
		 * ok = Validator.requerido(Integer.toString(nroOrden), "Nro de Orden",
		 * error) && Validator.validarEnteroMayorQue(0,
		 * Integer.toString(nroOrden), "Nro de Orden", error);
		 * 
		 * ok2 = Validator.requerido(Long.toString(productorForestal),
		 * "Productor Forestal", error);
		 * 
		 * ok3 = Validator.requerido(periodoForestal, "Periodo Forestal",
		 * error);
		 * 
		 * ok4 = Validator.requerido(expediente, "Expediente", error);
		 * 
		 * ok5 = Validator.requerido(fecha, "Fecha", error) &&
		 * Validator.validarFechaValida(fecha, "Fecha", error);
		 * 
		 * ok6 = Validator.requerido(ubicacionZonal, "Ubicacion Zonal", error);
		 * 
		 * ok7 = Validator.requerido(tipoTransporte, "Tipo Transporte", error);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * ok8 = Validator.validarRequeridoSi(Double.toString(cantidadMts),
		 * "0.0", Integer.toString(cantidadUnidades), "Cantidad Unidades",
		 * error); if(ok8){
		 * 
		 * ok8=Validator.validarEnteroMayorQue(0,
		 * Integer.toString(cantidadUnidades), "Cantidad Unidades", error); }
		 * else{ ok9 =
		 * Validator.validarRequeridoSi(Integer.toString(cantidadUnidades), "0",
		 * Double.toString(cantidadMts), "Cantidad Mts3", error); if(ok9){
		 * ok9=Validator.validarEnteroMayorQue(0, Double.toString(cantidadMts),
		 * "Cantidad Mts3", error); } }
		 * 
		 * 
		 * 
		 * 
		 * ok10 = Validator.requerido(Integer.toString(tamanio), "Tamaño",
		 * error) && Validator.validarEnteroMayorQue(0,
		 * Integer.toString(tamanio), "Tamaño", error);
		 */

		return ok && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10;
	}	
}
