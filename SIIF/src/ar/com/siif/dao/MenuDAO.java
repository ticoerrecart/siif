package ar.com.siif.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.utils.ComparadorItemsMenu;

public class MenuDAO extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<ItemMenu> getItemsMenu(String pRol) {
		Criteria criteria = getSession().createCriteria(Rol.class);
		String[] rol = { pRol };
		criteria.add(Restrictions.in("rol", rol));
		List<Rol> roles = criteria.list();

		List<ItemMenu> items = null;
		for (Rol r : roles) {
			items = r.getMenues();
		}
		Collections.sort(items, new ComparadorItemsMenu());
		HashMap<Long, ItemMenu> hashMenu = new HashMap<Long, ItemMenu>();
		for (ItemMenu itemMenu : items) {

			ItemMenu iMenu = new ItemMenu();
			iMenu.setId(itemMenu.getId());
			iMenu.setItem(itemMenu.getItem());
			iMenu.setOrden(itemMenu.getOrden());
			iMenu.setPadre(itemMenu.getPadre());
			iMenu.setUrl(itemMenu.getUrl());
			iMenu.setHijos(new ArrayList<ItemMenu>());

			if (iMenu.getPadre() == null) {
				hashMenu.put(iMenu.getId(), iMenu);
			} else {
				ItemMenu padreMenu = hashMenu.get(iMenu.getPadre().getId());
				padreMenu.getHijos().add(iMenu);

				if (itemMenu.getHijos() != null && itemMenu.getHijos().size() > 0) {//Si el item tiene hijos lo tengo q poner en el Map
					hashMenu.put(iMenu.getId(), iMenu);
				}
			}

		}
		List<ItemMenu> listaMenues = new ArrayList<ItemMenu>();
		listaMenues.addAll(hashMenu.values());

		return listaMenues;
	}
}
