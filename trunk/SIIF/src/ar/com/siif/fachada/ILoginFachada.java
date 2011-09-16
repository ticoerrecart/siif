package ar.com.siif.fachada;

import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;

public interface ILoginFachada {

	public Usuario login(String usuario, String password) throws NegocioException;

	public Usuario getUsuario(Long id);

}
