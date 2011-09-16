package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.struts.utils.Validator;

public class EntidadForm extends ActionForm {

	private Entidad entidad;

	private Long idLocalidad;

	private String tipoEntidad;

	private String confirmacionEmail;

	public EntidadForm() {
		this.entidad = new Entidad();
	}

	/*
	 * @Override public void reset(ActionMapping mapping, HttpServletRequest
	 * request) { Entidad laEntidad = (Entidad)
	 * request.getSession().getAttribute( "entidad"); if (laEntidad != null) {
	 * this.entidad = laEntidad; } }
	 */
	public boolean validar(StringBuffer error) {
		boolean mailsOk = true;
		boolean ok1 = Validator.requerido(this.getEntidad().getNombre(), "Nombre", error)
				&& Validator.validarEmail(this.getEntidad().getEmail(), "E-Mail", error)
				&& Validator.validarEmail(this.getConfirmacionEmail(), "Confirmación de E-Mail",
						error);
		if (ok1 && !this.getEntidad().getEmail().equalsIgnoreCase(this.getConfirmacionEmail())) {
			Validator.addErrorXML(error, "Los e-mails no coinciden.  Verifique.");
			mailsOk = false;
		}

		return ok1 && mailsOk;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(String tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	public String getConfirmacionEmail() {
		return confirmacionEmail;
	}

	public void setConfirmacionEmail(String confirmacionEmail) {
		this.confirmacionEmail = confirmacionEmail;
	}
}
