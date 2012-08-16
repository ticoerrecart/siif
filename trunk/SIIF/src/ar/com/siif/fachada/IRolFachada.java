package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.ItemMenuDTO;
import ar.com.siif.dto.RolDTO;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.exception.AccesoDenegadoException;
import ar.com.siif.negocio.exception.NegocioException;

public interface IRolFachada {

	public List<Rol> getRoles()throws NegocioException;

	public Rol getRol(Long id)throws NegocioException;

	public List<ItemMenu> recuperarMenues()throws NegocioException;
	
	public List<ItemMenuDTO> recuperarMenuesDTO()throws NegocioException;

	public void altaRol(Rol rol, List<ItemMenu> menues) throws NegocioException;
	
	public void altaRol(RolDTO rol, List<ItemMenuDTO> menues) throws NegocioException;

	public Rol recuperarRol(long idRol)throws NegocioException;
	
	public RolDTO recuperarRolDTO(long idRol)throws NegocioException;

	public void modificacionRol(RolDTO rol, List<ItemMenuDTO> menues) throws NegocioException;

	public boolean existeRol(RolDTO rol) throws NegocioException;
	
	public void verificarMenu(String pNombreMenu,Rol pRol)throws AccesoDenegadoException;
	
	public List<RolDTO> getRolesDTO()throws NegocioException;
	
	public RolDTO getRolAdministrador()throws NegocioException;
	
	public List<RolDTO> cargarRolesSegunEntidad(Long idEntidad, Long idUsuarioLogueado)throws NegocioException;	
}
