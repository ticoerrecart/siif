package ar.com.siif.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.struts.utils.Validator;

public class RolForm extends ActionForm {

	private Rol rol;

	private List<ItemMenu> menues;

	public RolForm() {

		rol = new Rol();
		menues = (List<ItemMenu>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(ItemMenu.class));
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		Rol rolParam = (Rol) request.getSession().getAttribute("rolF");
		if (rolParam != null) {
			rol = rolParam;
		}

		menues = (List<ItemMenu>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(ItemMenu.class));
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean validar(StringBuffer error) {

		return Validator.requerido(rol.getRol(), "Nombre Rol", error);
	}

	public List<ItemMenu> getMenues() {
		return menues;
	}

	public void setMenues(List<ItemMenu> menues) {
		this.menues = menues;
	}

}
