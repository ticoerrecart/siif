package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.com.siif.dao.EntidadDAO;
import ar.com.siif.dao.RolDAO;
import ar.com.siif.dao.UsuarioDAO;
import ar.com.siif.dto.ItemMenuDTO;
import ar.com.siif.dto.RolDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.AccesoDenegadoException;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.utils.Constantes;

public class RolFachada implements IRolFachada {

	private RolDAO rolDAO;

	private EntidadDAO entidadDAO;

	private UsuarioDAO usuarioDAO;

	public RolFachada() {
	}

	public RolFachada(RolDAO elRolDAO, EntidadDAO laEntidadDAO, UsuarioDAO elUsuarioDAO) {
		this.rolDAO = elRolDAO;
		this.entidadDAO = laEntidadDAO;
		this.usuarioDAO = elUsuarioDAO;
	}

	public List<Rol> getRoles()throws NegocioException{
		try{
			return rolDAO.getRoles();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public Rol getRol(Long id) throws NegocioException {
		try{
			return rolDAO.getRol(id);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public List<ItemMenu> recuperarMenues() throws NegocioException{

		try{
			return rolDAO.recuperarMenues();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public List<ItemMenuDTO> recuperarMenuesDTO()throws NegocioException{
		
		try{
			List<ItemMenuDTO> menuesDTO = new ArrayList<ItemMenuDTO>();
			List<ItemMenu> menues = rolDAO.recuperarMenues();
			for (ItemMenu itemMenu : menues) {
				menuesDTO.add(ProviderDTO.getItemMenuDTO(itemMenu));
			}
			return menuesDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}	
	
	public void altaRol(Rol rol, List<ItemMenu> menues) throws NegocioException {

		/*this.getItemsMenues(rol, menues);

		rolDAO.altaRol(rol);*/
	}

	public void altaRol(RolDTO rolDTO, List<ItemMenuDTO> menuesDTO) throws NegocioException{

		try{
			
			Rol rol = ProviderDominio.getRol(rolDTO);
			this.getItemsMenues(rol, menuesDTO);
			
			rolDAO.altaRol(rol);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}		
	}	
	
	public Rol recuperarRol(long idRol) throws NegocioException {

		try{
			return rolDAO.recuperarRol(idRol);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}				
	}

	public RolDTO recuperarRolDTO(long idRol) throws NegocioException{
		try{
			Rol rol = rolDAO.recuperarRol(idRol);
			
			return ProviderDTO.getRolDTO(rol);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
	
	public void modificacionRol(RolDTO rolDTO, List<ItemMenuDTO> menuesDTO) throws NegocioException {

		/*rol.setMenues(new ArrayList<ItemMenu>());
		this.getItemsMenues(rol, menues);

		rolDAO.modificacionRol(rol);*/
		try{
			Rol rol = rolDAO.recuperarRol(rolDTO.getId());
			rol.setMenues(new ArrayList<ItemMenu>());
			this.getItemsMenues(rol, menuesDTO);
			
			rolDAO.modificacionRol(rol);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}

	public boolean existeRol(RolDTO rol) throws NegocioException {

		try {
			return rolDAO.existeRol(rol);
		} catch (DataBaseException e) {

			throw new NegocioException(e.getMessage());
		}
	}

	public List<RolDTO> cargarRolesSegunEntidad(Long idEntidad, Long idUsuarioLogueado) throws NegocioException{
		try{
			Entidad entidad = entidadDAO.getEntidad(idEntidad);
			Usuario usuario = null;// usuarioDAO.getUsuario(idUsuario);
			if (idUsuarioLogueado != null) {
				usuario = usuarioDAO.getUsuario(idUsuarioLogueado);
			}
			List<Rol> roles = null;
			List<RolDTO> rolesDTO = new ArrayList<RolDTO>();
			if (Constantes.ID_ROL_ADMINISTRADOR == usuario.getRol().getId().longValue()) {
				if (Constantes.ENTIDAD_RN.equalsIgnoreCase(entidad.getIdTipoEntidad())) {
					// si la Entidad es RN y es un Administrador, deben estar todos
					// los roles
					roles = rolDAO.getRoles();
				} else {
					// si la Entidad NO es RN y es un Administrador, no debe estar el rol Administrador
					roles = new ArrayList<Rol>();
					for (Rol rol : rolDAO.getRoles()) {
						if (Constantes.ID_ROL_ADMINISTRADOR != rol.getId().longValue()) {
							roles.add(rol);
						}
					}
				}
			} else {
				//si NO es un Administrador, entonces debe estar solo su rol
				roles = new ArrayList<Rol>();
				roles.add(usuario.getRol());
			}
	
			for (Rol rol : roles) {
				rolesDTO.add(ProviderDTO.getRolDTO(rol));
			}
			
			return rolesDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	private void getItemsMenues(Rol rol, List<ItemMenuDTO> menues) throws NegocioException {

		try{
			HashMap<Long, String> hashMenu = new HashMap<Long, String>();
	
			for (ItemMenuDTO itemMenu : menues) {
				if (itemMenu != null) {
					ItemMenu item = rolDAO.getItemMenu(itemMenu.getId());
					rol.getMenues().add(item);
	
					ItemMenu itemPadre = item.getPadre();
	
					while (itemPadre != null) {
	
						if (hashMenu.get(itemPadre.getId()) == null) {
							rol.getMenues().add(itemPadre);
							hashMenu.put(itemPadre.getId(), "S");
							itemPadre = itemPadre.getPadre();
						} else {
							itemPadre = null;
						}
					}
				}
			}
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public void verificarMenu(String pNombreMenu,Rol pRol)throws AccesoDenegadoException{
		
		rolDAO.verificarMenu(pNombreMenu,pRol);
	}	

	public List<RolDTO> getRolesDTO()throws NegocioException{
		try{
			List<RolDTO> listaRolesDTO = new ArrayList<RolDTO>();
			List<Rol> listaRoles = rolDAO.getRoles();
			
			for (Rol rol : listaRoles) {
				listaRolesDTO.add(ProviderDTO.getRolDTO(rol));			
			}
			
			return listaRolesDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public RolDTO getRolAdministrador() throws NegocioException{
		try{
			Rol rolAdministrador = rolDAO.getRolAdministrador();
			return ProviderDTO.getRolDTO(rolAdministrador);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
