package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dao.MenuDAO;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public class MenuFachada implements IMenuFachada {

	private MenuDAO menuDAO;

	/**
	 * Constructor por defecto.
	 * 
	 */
	public MenuFachada() {
	}

	public MenuFachada(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public List<ItemMenu> getItemsMenu(String pRol) throws NegocioException {

		try{
			return menuDAO.getItemsMenu(pRol);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

}
