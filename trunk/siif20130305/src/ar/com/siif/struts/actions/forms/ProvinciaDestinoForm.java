package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siif.dto.ProvinciaDestinoDTO;
import ar.com.siif.struts.utils.Validator;

public class ProvinciaDestinoForm extends ActionForm {

	private ProvinciaDestinoDTO provinciaDTO;
	
	public ProvinciaDestinoForm() {

		provinciaDTO = new ProvinciaDestinoDTO();
	}

	public boolean validar(StringBuffer error) {
		return Validator.requerido(this.getProvinciaDTO().getNombre(), "Nombre", error);
	}

	public ProvinciaDestinoDTO getProvinciaDTO() {
		return provinciaDTO;
	}

	public void setProvinciaDTO(ProvinciaDestinoDTO provinciaDTO) {
		this.provinciaDTO = provinciaDTO;
	}


}
