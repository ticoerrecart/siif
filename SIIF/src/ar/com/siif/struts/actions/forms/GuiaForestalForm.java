package ar.com.siif.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Fecha;

public class GuiaForestalForm extends ActionForm {

	private GuiaForestalDTO guiaForestal;
	
	private List<BoletaDepositoDTO> boletasDeposito;
	
	private List<ValeTransporteDTO> valesTransporte;	
	
	private List<FiscalizacionDTO> listaFiscalizaciones;
	
	private List<SubImporteDTO> listaSubImportes;
	
	public GuiaForestalForm() {

		guiaForestal = new GuiaForestalDTO();

		boletasDeposito = (List<BoletaDepositoDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(BoletaDepositoDTO.class));

		valesTransporte = (List<ValeTransporteDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(ValeTransporteDTO.class));

		listaFiscalizaciones = (List<FiscalizacionDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(FiscalizacionDTO.class));

		listaSubImportes = (List<SubImporteDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(SubImporteDTO.class));		
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		GuiaForestalDTO guia = (GuiaForestalDTO) request.getAttribute("guia");

		if (guiaForestal.getNroGuia() == 0) {
			if (guia != null) {
				guiaForestal = guia;
			} else {
				guiaForestal = new GuiaForestalDTO();
			}
		}

		boletasDeposito = (List<BoletaDepositoDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(BoletaDepositoDTO.class));

		valesTransporte = (List<ValeTransporteDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(ValeTransporteDTO.class));

		listaFiscalizaciones = (List<FiscalizacionDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(FiscalizacionDTO.class));	
		
		listaSubImportes = (List<SubImporteDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(SubImporteDTO.class));		
	}

	public GuiaForestalDTO getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestalDTO guiaForestal) {
		this.guiaForestal = guiaForestal;
	}	
	
	public List<BoletaDepositoDTO> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

	public List<ValeTransporteDTO> getValesTransporte() {
		return valesTransporte;
	}

	public void setValesTransporte(List<ValeTransporteDTO> valesTransporte) {
		this.valesTransporte = valesTransporte;
	}

	public List<FiscalizacionDTO> getListaFiscalizaciones() {
		return listaFiscalizaciones;
	}

	public void setListaFiscalizaciones(List<FiscalizacionDTO> listaFiscalizaciones) {
		this.listaFiscalizaciones = listaFiscalizaciones;
	}

	public List<SubImporteDTO> getListaSubImportes() {
		return listaSubImportes;
	}

	public void setListaSubImportes(List<SubImporteDTO> listaSubImportes) {
		this.listaSubImportes = listaSubImportes;
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

		ok = Validator.validarEnteroMayorQue(0,
				Integer.toString(this.getGuiaForestal().getNroGuia()), "Nro de Guía", error);		
		ok2 = Validator.requerido(this.getGuiaForestal().getFechaVencimiento(), "Valido Hasta", error);
		
		FiscalizacionDTO fiscalizacion = listaFiscalizaciones.get(0);
		if(fiscalizacion != null && fiscalizacion.getId() != null){
			/*ok11 = Validator.validarTipoProductoAltaGFB(this.getGuiaForestal().getTipoProducto().getId(),
														fiscalizacion.getTipoProducto().getId(), error);*/
		}
		
		/*ok3 = Validator.requerido(String.valueOf(this.getGuiaForestal().getImporte()), "Importe", error);
		if(ok3){
			ok3 = Validator.validarDoubleMayorQue(0,String.valueOf(this.getGuiaForestal().getImporte()), "Importe", error);
		}*/
		
		/*ok4 = Validator.requerido(this.getGuiaForestal().getEstado(), "Estado", error);
		ok5 = Validator.requerido(this.getGuiaForestal().getEspecie(), "Especie", error);*/
		double montoTotal = this.getGuiaForestal().getImporteTotal() + this.getGuiaForestal().getInspFiscalizacion(); 
		ok6 = Validator.validarBoletasDeposito(this.getBoletasDeposito(),montoTotal,error);
		ok7 = Validator.validarValesTransporte(this.getValesTransporte(),error);
		ok9 = Validator.requerido(this.getGuiaForestal().getLocalidad(), "Localidad", error);
		ok10 = Validator.requerido(this.getGuiaForestal().getFecha(), "Fecha", error);

		return ok && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10 && ok11;
	}	
}
