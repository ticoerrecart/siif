package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dao.UsuarioDAO;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;

public class UsuarioFachada implements IUsuarioFachada {

	private UsuarioDAO usuarioDAO;

	public UsuarioFachada() {
	}

	public UsuarioFachada(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public void altaUsuario(Usuario elUsuario) throws NegocioException {
		usuarioDAO.altaUsuario(elUsuario);
	}

	public boolean existeUsuario(String nombre, Long id) {
		return usuarioDAO.existeUsuario(nombre, id);
	}

	public List<Usuario> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}

	public Usuario getUsuario(Long id) {
		return usuarioDAO.getUsuario(id);
	}
}
