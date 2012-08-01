package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.struts.utils.Validator;

public class LocalidadForm extends ActionForm {

	private Localidad localidad;
	private LocalidadDTO localidadDTO;
	
	public LocalidadForm() {
		localidad = new Localidad();
		localidadDTO = new LocalidadDTO();
	}

	public boolean validar(StringBuffer error) {
		return Validator.requerido(this.getLocalidadDTO().getNombre(), "Nombre", error);
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public LocalidadDTO getLocalidadDTO() {
		return localidadDTO;
	}

	public void setLocalidadDTO(LocalidadDTO localidadDTO) {
		this.localidadDTO = localidadDTO;
	}

}
