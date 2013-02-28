package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.ItemMenuDTO;
import ar.com.siif.dto.RolDTO;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.exception.AccesoDenegadoException;
import ar.com.siif.negocio.exception.NegocioException;

public interface IRolFachada {

	public List<Rol> getRoles();

	public Rol getRol(Long id);

	public List<ItemMenu> recuperarMenues();

	public List<ItemMenuDTO> recuperarMenuesDTO();

	public void altaRol(RolDTO rol, List<ItemMenuDTO> menues)
			throws NegocioException;

	public Rol recuperarRol(long idRol);

	public RolDTO recuperarRolDTO(long idRol);

	public void modificacionRol(RolDTO rol, List<ItemMenuDTO> menues)
			throws NegocioException;

	public boolean existeRol(RolDTO rol);

	public void verificarMenu(String pNombreMenu, Rol pRol)
			throws AccesoDenegadoException;

	public List<RolDTO> getRolesDTO();

	public RolDTO getRolAdministrador();

	public List<RolDTO> cargarRolesSegunEntidad(Long idEntidad,
			Long idUsuarioLogueado)throws NegocioException;
}
