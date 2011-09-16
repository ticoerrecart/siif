package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siif.negocio.Usuario;
import ar.com.siif.struts.utils.Validator;

public class UsuarioForm extends ActionForm {

	private Usuario usuario;

	private String contrasenia;

	private String idRol;

	private String idEntidad;

	public UsuarioForm() {
		usuario = new Usuario();
	}

	public boolean validar(StringBuffer error) {
		boolean requeridos = Validator.requerido(this.getUsuario().getNombreUsuario(), "Nombre",
				error)
				&& Validator.requerido(this.getUsuario().getPassword(), "Contraseña", error)
				&& Validator.requerido(this.getContrasenia(), "Confirmar Contraseña", error);
		boolean passValido = true;
		if (requeridos && !this.getUsuario().getPassword().equalsIgnoreCase(this.getContrasenia())) {
			Validator.addErrorXML(error, "La contraseña no coincide.  Verifique.");
			passValido = false;
		}

		return requeridos && passValido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(String idEntidad) {
		this.idEntidad = idEntidad;
	}
}
