package ar.com.siif.fachada;

import ar.com.siif.dao.LoginDAO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;

public class LoginFachada implements ILoginFachada {

	private LoginDAO loginDAO;

	public LoginFachada() {
	}

	public LoginFachada(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public UsuarioDTO login(String usuario, String password) throws NegocioException {

		Usuario usr = loginDAO.login(usuario, password);
		UsuarioDTO usrDTO = ProviderDTO.getUsuarioDTO(usr);
			
		return usrDTO;			
	}

	public Usuario getUsuario(Long id){

		return loginDAO.getUsuario(id);
	}
}
