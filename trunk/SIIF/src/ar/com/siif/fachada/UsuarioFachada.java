package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.UsuarioDAO;
import ar.com.siif.dto.UsuarioDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class UsuarioFachada implements IUsuarioFachada {

	private UsuarioDAO usuarioDAO;
	private IEntidadFachada entidadFachada;
	private IRolFachada rolFachada;
	
	public UsuarioFachada() {
	}

	public UsuarioFachada(UsuarioDAO usuarioDAO, IEntidadFachada pEntidadFachada, IRolFachada pRolFachada) {
		this.usuarioDAO = usuarioDAO;
		this.entidadFachada = pEntidadFachada;
		this.rolFachada = pRolFachada;
	}

	public void altaUsuario(Usuario elUsuario) throws NegocioException {
			
		try {
			usuarioDAO.altaUsuario(elUsuario);
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}	
	}

	public boolean existeUsuario(String nombre, Long id) {
		return usuarioDAO.existeUsuario(nombre, id);
	}

	public List<Usuario> getUsuarios()throws NegocioException {
		try{
			return usuarioDAO.getUsuarios();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	public List<UsuarioDTO> getUsuariosDTO()throws NegocioException {
		
		try{
			List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
			List<Usuario> usuarios = usuarioDAO.getUsuarios();
			
			for (Usuario usuario : usuarios) {
				usuariosDTO.add(ProviderDTO.getUsuarioDTO(usuario));
			}			
			return usuariosDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public Usuario getUsuario(Long id) throws NegocioException{
		try{
			return usuarioDAO.getUsuario(id);
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public UsuarioDTO getUsuarioDTO(Long id) throws NegocioException{
		try{	
			Usuario usuario = usuarioDAO.getUsuario(id);
			return ProviderDTO.getUsuarioDTO(usuario);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public void altaUsuario(UsuarioDTO usuario) throws NegocioException{
				
		try {
			Entidad entidad = entidadFachada.getEntidad(usuario.getEntidad().getId());
			Rol rol = rolFachada.getRol(usuario.getRol().getId());
			
			usuarioDAO.altaUsuario(ProviderDominio.getUsuario(usuario,entidad,rol));
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public void modificacionUsuario(UsuarioDTO usuarioDTO) throws NegocioException{
		try {
			Usuario usuario = usuarioDAO.getUsuario(usuarioDTO.getId());
			Entidad entidad = entidadFachada.getEntidad(usuarioDTO.getEntidad().getId());
			Rol rol = rolFachada.getRol(usuarioDTO.getRol().getId());
			
			usuarioDAO.modificacionUsuario(ProviderDominio.getUsuario(usuario,usuarioDTO,entidad,rol));
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
}
