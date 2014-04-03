package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siif.negocio.CaminoConstruido;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.struts.utils.Validator;

public class CaminoForm extends ActionForm {

	private CaminoConstruido caminoConstruido;

	private Long idProductor;

	public CaminoForm() {
		caminoConstruido = new CaminoConstruido();
	}

	public boolean validar(StringBuffer error) {
		return Validator.requerido(this.getCaminoConstruido().getMonto(), "Monto", error);
	}

	public CaminoConstruido getCaminoConstruido() {
		return caminoConstruido;
	}

	public void setCaminoConstruido(CaminoConstruido caminoConstruido) {
		this.caminoConstruido = caminoConstruido;
	}

	public Long getIdProductor() {
		return idProductor;
	}

	public void setIdProductor(Long idProductor) {
		this.idProductor = idProductor;
	}

}
