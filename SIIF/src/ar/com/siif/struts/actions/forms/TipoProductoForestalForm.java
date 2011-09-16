package ar.com.siif.struts.actions.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.struts.utils.Validator;

public class TipoProductoForestalForm extends ActionForm {

	//private String tipoProductoForestal;
	private TipoProducto productoForestal;

	public TipoProductoForestalForm() {

		productoForestal = new TipoProducto();
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		TipoProducto tipoProducto = (TipoProducto) request.getSession()
				.getAttribute("tipoProducto");
		if (tipoProducto != null) {
			productoForestal = tipoProducto;
		}
	}

	public TipoProducto getProductoForestal() {
		return productoForestal;
	}

	public void setProductoForestal(TipoProducto productoForestal) {
		this.productoForestal = productoForestal;
	}

	public boolean validar(StringBuffer error) {

		return Validator.requerido(productoForestal.getNombre(), "Producto Forestal", error);
	}
}
