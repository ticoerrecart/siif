package ar.com.siif.struts.actions.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
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

	private long idOficinaForestal;
	
	private String fecha;

	private List<Muestra> muestras;

	
	//-------------//
	private FiscalizacionDTO fiscalizacionDTO;
	
	private List<MuestraDTO> muestrasDTO;
	//-------------//
	
	public FiscalizacionForm() {

		fiscalizacion = new Fiscalizacion();

		fiscalizacionDTO = new FiscalizacionDTO();
		
		muestras = (List<Muestra>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(Muestra.class));

		muestrasDTO = (List<MuestraDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(MuestraDTO.class));		
		
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		Fiscalizacion f = (Fiscalizacion)request.getSession().getAttribute("fiscalizacion");
		
		FiscalizacionDTO fDTO = (FiscalizacionDTO)request.getSession().getAttribute("fiscalizacionDTO");
		
		if(f != null){
			fiscalizacion = f;
			fiscalizacionDTO = fDTO;
		}
		else{
			fiscalizacion = new Fiscalizacion();
			fiscalizacionDTO = new FiscalizacionDTO();
		}
		
		muestras = (List<Muestra>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(Muestra.class));
		
		muestrasDTO = (List<MuestraDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(MuestraDTO.class));		
	}

	public boolean validar(StringBuffer error) {

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

		/*ok2 = Validator.validarComboRequerido("-1",Long.toString(idProductorForestal), "Productor Forestal", error);		
		
		ok5 = Validator.requerido(fecha, "Fecha", error) && Validator.validarFechaValida(fecha, "Fecha", error);
		
		ok3 = Validator.requerido(fiscalizacion.getPeriodoForestal(), "Periodo Forestal", error);
		
		ok8 = Validator.validarEnteroMayorQue(0, Integer.toString(fiscalizacion.getCantidadUnidades()), "Cantidad Unidades", error);			
		
		ok11 = Validator.validarComboRequerido("-1",Long.toString(idTipoProductoForestal), "Tipo de Producto", error);
		
		ok9 = Validator.validarDoubleMayorQue(0, Double.toString(fiscalizacion.getCantidadMts()), "Cantidad Mts3", error);
		
		ok10 = Validator.validarEnteroMayorQue(0, Integer.toString(fiscalizacion.getTamanioMuestra()), "Tama�o de la Muestra", error);		
		
		ok12 = Validator.validarComboRequerido("-1",Long.toString(idPlanManejoForestal), "Plan de Manejo Forestal", error);
		
		ok13 = Validator.validarComboRequerido("-1",Long.toString(idTranzon), "Tranz�n", error);
		
		ok14 = Validator.validarComboRequerido("-1",Long.toString(idMarcacion), "Marcaci�n", error);
		
		ok15 = Validator.validarComboRequerido("-1",Long.toString(idRodal), "Rodal", error);
		
		if(ok10){
			ok16 = Validator.validarMuestras(this.getMuestras(),error);
		}	*/
		
		ok2 = Validator.validarComboRequerido("-1",Long.toString(fiscalizacionDTO.getIdProductorForestal()), "Productor Forestal", error);		
		
		ok5 = Validator.requerido(fiscalizacionDTO.getFecha(), "Fecha", error) && Validator.validarFechaValida(fiscalizacionDTO.getFecha(), "Fecha", error);
		
		ok3 = Validator.requerido(fiscalizacionDTO.getPeriodoForestal(), "Periodo Forestal", error);
		
		ok8 = Validator.validarEnteroMayorQue(0, Integer.toString(fiscalizacionDTO.getCantidadUnidades()), "Cantidad Unidades", error);			
		
		ok11 = Validator.validarComboRequerido("-1",Long.toString(fiscalizacionDTO.getIdTipoProductoForestal()), "Tipo de Producto", error);
		
		ok9 = Validator.validarDoubleMayorQue(0, Double.toString(fiscalizacionDTO.getCantidadMts()), "Cantidad Mts3", error);
		
		ok10 = Validator.validarEnteroMayorQue(0, Integer.toString(fiscalizacionDTO.getTamanioMuestra()), "Tama�o de la Muestra", error);		
		
		ok11 = Validator.validarComboRequerido("-1", Long.toString(fiscalizacionDTO.getIdOficinaForestal()), "Oficina", error);		
		
		//ok12 = Validator.validarComboRequerido("-1",Long.toString(idPlanManejoForestal), "Plan de Manejo Forestal", error);
		
		//ok13 = Validator.validarComboRequerido("-1",Long.toString(idTranzon), "Tranz�n", error);
		
		//ok14 = Validator.validarComboRequerido("-1",Long.toString(idMarcacion), "Marcaci�n", error);
		
		ok15 = Validator.validarComboRequerido("-1",Long.toString(fiscalizacionDTO.getIdRodal()), "Rodal", error);
		
		if(ok10){
			ok16 = Validator.validarMuestras(this.getMuestrasDTO(),error);
		}		
		
		//VALIDACIONES FISCALIZACION
		return ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10 && ok11 && ok12 && ok13 && ok14 && ok15 && ok16;
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

	public long getIdOficinaForestal() {
		return idOficinaForestal;
	}

	public void setIdOficinaForestal(long idOficinaForestal) {
		this.idOficinaForestal = idOficinaForestal;
	}

	public FiscalizacionDTO getFiscalizacionDTO() {
		return fiscalizacionDTO;
	}

	public void setFiscalizacionDTO(FiscalizacionDTO fiscalizacionDTO) {
		this.fiscalizacionDTO = fiscalizacionDTO;
	}

	public List<MuestraDTO> getMuestrasDTO() {
		return muestrasDTO;
	}

	public void setMuestrasDTO(List<MuestraDTO> muestrasDTO) {
		this.muestrasDTO = muestrasDTO;
	}
	
}
