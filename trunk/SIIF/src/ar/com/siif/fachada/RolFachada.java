package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.com.siif.dao.EntidadDAO;
import ar.com.siif.dao.RolDAO;
import ar.com.siif.dao.UsuarioDAO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.NegocioException;
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

	public List<Rol> getRoles() {
		return rolDAO.getRoles();
	}

	public Rol getRol(Long id) {
		return rolDAO.getRol(id);
	}

	public List<ItemMenu> recuperarMenues() {

		return rolDAO.recuperarMenues();
	}

	public void altaRol(Rol rol, List<ItemMenu> menues) throws NegocioException {

		this.getItemsMenues(rol, menues);

		rolDAO.altaRol(rol);
	}

	public Rol recuperarRol(long idRol) {

		return rolDAO.recuperarRol(idRol);
	}

	public void modificacionRol(Rol rol, List<ItemMenu> menues) throws NegocioException {

		rol.setMenues(new ArrayList<ItemMenu>());
		this.getItemsMenues(rol, menues);

		rolDAO.modificacionRol(rol);
	}

	public boolean existeRol(Rol rol) {

		return rolDAO.existeRol(rol);
	}

	public List<Rol> cargarRolesSegunEntidad(Long idEntidad, Long idUsuarioLogueado) {

		Entidad entidad = entidadDAO.getEntidad(idEntidad);
		Usuario usuario = null;// usuarioDAO.getUsuario(idUsuario);
		if (idUsuarioLogueado != null) {
			usuario = usuarioDAO.getUsuario(idUsuarioLogueado);
		}
		List<Rol> roles = null;
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

		return roles;
	}

	private void getItemsMenues(Rol rol, List<ItemMenu> menues) {

		HashMap<Long, String> hashMenu = new HashMap<Long, String>();

		for (ItemMenu itemMenu : menues) {
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

	}

}
