package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siif.negocio.Localidad;
import ar.com.siif.struts.utils.Validator;

public class LocalidadForm extends ActionForm {

	private Localidad localidad;

	public LocalidadForm() {
		localidad = new Localidad();
	}

	public boolean validar(StringBuffer error) {
		return Validator.requerido(this.getLocalidad().getNombre(), "Nombre", error);
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

}
