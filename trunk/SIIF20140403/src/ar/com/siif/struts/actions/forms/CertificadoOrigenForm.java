package ar.com.siif.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.CertificadoOrigenDTO;
import ar.com.siif.dto.TipoProductoEnCertificadoDTO;

public class CertificadoOrigenForm extends ActionForm {

	private CertificadoOrigenDTO certificadoOrigenDTO;
	private List<TipoProductoEnCertificadoDTO> tiposProductoEnCertificado;
	private boolean tieneDeuda;
	private String radioDeuda;
	private double volumenMaximoParaExportar;	
	
	public CertificadoOrigenForm(){
		
		certificadoOrigenDTO = new CertificadoOrigenDTO();
		tiposProductoEnCertificado = (List<TipoProductoEnCertificadoDTO>) LazyList.decorate(new ArrayList(),
										FactoryUtils.instantiateFactory(TipoProductoEnCertificadoDTO.class));
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		tiposProductoEnCertificado = (List<TipoProductoEnCertificadoDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(TipoProductoEnCertificadoDTO.class));		
	}
	
	public CertificadoOrigenDTO getCertificadoOrigenDTO() {
		return certificadoOrigenDTO;
	}

	public void setCertificadoOrigenDTO(CertificadoOrigenDTO certificadoOrigenDTO) {
		this.certificadoOrigenDTO = certificadoOrigenDTO;
	}

	public List<TipoProductoEnCertificadoDTO> getTiposProductoEnCertificado() {
		return tiposProductoEnCertificado;
	}

	public void setTiposProductoEnCertificado(
			List<TipoProductoEnCertificadoDTO> tiposProductoEnCertificado) {
		this.tiposProductoEnCertificado = tiposProductoEnCertificado;
	}
	
	public double getVolumenMaximoParaExportar() {
		return volumenMaximoParaExportar;
	}

	public void setVolumenMaximoParaExportar(double volumenMaximoParaExportar) {
		this.volumenMaximoParaExportar = volumenMaximoParaExportar;
	}

	public boolean isTieneDeuda() {
		return tieneDeuda;
	}

	public void setTieneDeuda(boolean tieneDeuda) {
		this.tieneDeuda = tieneDeuda;
	}

	public String getRadioDeuda() {
		return radioDeuda;
	}

	public void setRadioDeuda(String radioDeuda) {
		this.radioDeuda = radioDeuda;
	}

	public void normalizarListaTiposProducto(){
		
		List<TipoProductoEnCertificadoDTO> listaEliminar =  new ArrayList<TipoProductoEnCertificadoDTO>();
		for (TipoProductoEnCertificadoDTO tipoProducto : tiposProductoEnCertificado) {
			if(tipoProducto != null && tipoProducto.getVolumenTipoProducto() <= 0.0){
				listaEliminar.add(tipoProducto);
			}
		}
		tiposProductoEnCertificado.removeAll(listaEliminar);
	}	
	
}
