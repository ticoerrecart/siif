package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.exception.NegocioException;

public interface IMenuFachada {

	public List<ItemMenu> getItemsMenu(String rol)throws NegocioException;
}
