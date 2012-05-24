package ar.com.siif.struts.actions.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Fecha;

public class FiscalizacionForm extends ActionForm {

	/*private int nroOrden;	
	private EntidadDTO productorForestal;
	private String periodoForestal;
	private String expediente;
	private String fecha;
	private String ubicacionZonal;
	private String tipoTransporte;
	private int cantidadUnidades;
	private double cantidadMts;
	private int tamanio;
	private GuiaForestalDTO guiaForestal;*/

	private Fiscalizacion fiscalizacion;

	private long idProductorForestal;

	private long idTipoProductoForestal;

	private long idPlanManejoForestal;
	
	private long idTranzon;
	
	private long idMarcacion;
	
	private long idRodal;

	private String fecha;

	private List<Muestra> muestras;

	public FiscalizacionForm() {

		fiscalizacion = new Fiscalizacion();

		muestras = (List<Muestra>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(Muestra.class));

	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		Fiscalizacion f = (Fiscalizacion)request.getSession().getAttribute("fiscalizacion");			
		
		if(f != null){
			fiscalizacion = f;	
		}
		else{
			fiscalizacion = new Fiscalizacion();
		}
		
		muestras = (List<Muestra>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(Muestra.class));
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
		boolean ok11 = true;
		boolean ok12 = true;
		boolean ok13 = true;
		boolean ok14 = true;
		boolean ok15 = true;
		boolean ok16 = true;

		/*ok = Validator.requerido(Integer.toString(nroOrden), "Nro de Orden", error) 
			&& Validator.validarEnteroMayorQue(0, Integer.toString(nroOrden), "Nro de Orden", error);

		ok2 = Validator.requerido(Long.toString(productorForestal), "Productor Forestal", error);		
		
		ok3 = Validator.requerido(periodoForestal, "Periodo Forestal", error);		
		
		ok4 = Validator.requerido(expediente, "Expediente", error);				
		
		ok5 = Validator.requerido(fecha, "Fecha", error) && Validator.validarFechaValida(fecha, "Fecha", error);
		
		ok6 = Validator.requerido(ubicacionZonal, "Ubicacion Zonal", error);
		
		ok7 = Validator.requerido(tipoTransporte, "Tipo Transporte", error);
		
		
		
		
		
		
		
		ok8 = Validator.validarRequeridoSi(Double.toString(cantidadMts), "0.0", Integer.toString(cantidadUnidades), "Cantidad Unidades", error);
		if(ok8){

			ok8=Validator.validarEnteroMayorQue(0, Integer.toString(cantidadUnidades), "Cantidad Unidades", error);
		}
		else{
			ok9 = Validator.validarRequeridoSi(Integer.toString(cantidadUnidades), "0", Double.toString(cantidadMts), "Cantidad Mts3", error);
			if(ok9){
				ok9=Validator.validarEnteroMayorQue(0, Double.toString(cantidadMts), "Cantidad Mts3", error);
			}			
		}
		
		
		ok10 = Validator.requerido(Integer.toString(tamanio), "Tamaño", error) 
		&& Validator.validarEnteroMayorQue(0, Integer.toString(tamanio), "Tamaño", error);	*/

		ok2 = Validator.validarComboRequerido("-1",Long.toString(idProductorForestal), "Productor Forestal", error);		
		
		ok5 = Validator.requerido(fecha, "Fecha", error) && Validator.validarFechaValida(fecha, "Fecha", error);
		
		ok3 = Validator.requerido(fiscalizacion.getPeriodoForestal(), "Periodo Forestal", error);
		
		ok8 = Validator.validarEnteroMayorQue(0, Integer.toString(fiscalizacion.getCantidadUnidades()), "Cantidad Unidades", error);			
		
		ok11 = Validator.validarComboRequerido("-1",Long.toString(idTipoProductoForestal), "Tipo de Producto", error);
		
		ok9 = Validator.validarDoubleMayorQue(0, Double.toString(fiscalizacion.getCantidadMts()), "Cantidad Mts3", error);
		
		ok10 = Validator.validarEnteroMayorQue(0, Integer.toString(fiscalizacion.getTamanioMuestra()), "Tamaño de la Muestra", error);		
		
		ok12 = Validator.validarComboRequerido("-1",Long.toString(idPlanManejoForestal), "Plan de Manejo Forestal", error);
		
		ok13 = Validator.validarComboRequerido("-1",Long.toString(idTranzon), "Tranzón", error);
		
		ok14 = Validator.validarComboRequerido("-1",Long.toString(idMarcacion), "Marcación", error);
		
		ok15 = Validator.validarComboRequerido("-1",Long.toString(idRodal), "Rodal", error);
		
		if(ok10){
			ok16 = Validator.validarMuestras(this.getMuestras(),error);
		}	
		//VALIDACIONES FISCALIZACION
		
		return ok && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10 && ok11 && ok12 && ok13 && ok14 && ok15 && ok16;
	}

	public Fiscalizacion getFiscalizacion() {
		return fiscalizacion;
	}

	public void setFiscalizacion(Fiscalizacion fiscalizacion) {
		this.fiscalizacion = fiscalizacion;
	}

	public long getIdProductorForestal() {
		return idProductorForestal;
	}

	public void setIdProductorForestal(long idProductorForestal) {
		this.idProductorForestal = idProductorForestal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public long getIdTipoProductoForestal() {
		return idTipoProductoForestal;
	}

	public void setIdTipoProductoForestal(long idTipoProductoForestal) {
		this.idTipoProductoForestal = idTipoProductoForestal;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

	public long getIdPlanManejoForestal() {
		return idPlanManejoForestal;
	}

	public void setIdPlanManejoForestal(long idPlanManejoForestal) {
		this.idPlanManejoForestal = idPlanManejoForestal;
	}

	public long getIdTranzon() {
		return idTranzon;
	}

	public void setIdTranzon(long idTranzon) {
		this.idTranzon = idTranzon;
	}

	public long getIdMarcacion() {
		return idMarcacion;
	}

	public void setIdMarcacion(long idMarcacion) {
		this.idMarcacion = idMarcacion;
	}

	public long getIdRodal() {
		return idRodal;
	}

	public void setIdRodal(long idRodal) {
		this.idRodal = idRodal;
	}

}
