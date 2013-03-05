package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siif.dto.LocalidadDestinoDTO;
import ar.com.siif.struts.utils.Validator;

public class LocalidadDestinoForm extends ActionForm {

	private LocalidadDestinoDTO localidadDestinoDTO;
	
	public LocalidadDestinoForm(){
		
		localidadDestinoDTO = new LocalidadDestinoDTO();
	}

	public LocalidadDestinoDTO getLocalidadDestinoDTO() {
		return localidadDestinoDTO;
	}

	public void setLocalidadDestinoDTO(LocalidadDestinoDTO localidadDestinoDTO) {
		this.localidadDestinoDTO = localidadDestinoDTO;
	}
	
	public boolean validar(StringBuffer error) {
		return Validator.requerido(this.getLocalidadDestinoDTO().getNombre(), "Nombre", error);
	}	
}
