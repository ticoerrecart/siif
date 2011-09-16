package ar.com.siif.fachada;

import ar.com.siif.dao.LoginDAO;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;

public class LoginFachada implements ILoginFachada {

	private LoginDAO loginDAO;

	public LoginFachada() {
	}

	public LoginFachada(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public Usuario login(String usuario, String password) throws NegocioException {

		return loginDAO.login(usuario, password);
	}

	public Usuario getUsuario(Long id) {
		return loginDAO.getUsuario(id);
	}
}
