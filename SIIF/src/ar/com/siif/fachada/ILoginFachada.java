package ar.com.siif.fachada;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;

public interface ILoginFachada {

	public UsuarioDTO login(String usuario, String password) throws NegocioException;

	public Usuario getUsuario(Long id)throws NegocioException;

}
