package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;

public interface IUsuarioFachada {

	public void altaUsuario(Usuario elUsuario) throws NegocioException;

	public boolean existeUsuario(String nombre, Long id);

	public List<Usuario> getUsuarios();

	public Usuario getUsuario(Long id);
}
