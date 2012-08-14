package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;

public interface IUsuarioFachada {

	public void altaUsuario(Usuario elUsuario) throws NegocioException;

	public boolean existeUsuario(String nombre, Long id);

	public List<Usuario> getUsuarios()throws NegocioException;
	
	public List<UsuarioDTO> getUsuariosDTO()throws NegocioException;

	public Usuario getUsuario(Long id)throws NegocioException;

	public UsuarioDTO getUsuarioDTO(Long id)throws NegocioException;	
	
	public void altaUsuario(UsuarioDTO usuario) throws NegocioException;
	
	public void modificacionUsuario(UsuarioDTO usuario) throws NegocioException;
}
