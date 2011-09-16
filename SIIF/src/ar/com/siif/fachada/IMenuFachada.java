package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.ItemMenu;

public interface IMenuFachada {

	public List<ItemMenu> getItemsMenu(String rol);
}
