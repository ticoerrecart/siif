package ar.com.siif.struts.actions.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.struts.utils.Validator;

public class TipoProductoForestalForm extends ActionForm {

	//private String tipoProductoForestal;
	private TipoProducto productoForestal;
	private TipoProductoDTO productoForestalDTO;

	public TipoProductoForestalForm() {

		productoForestal = new TipoProducto();
		productoForestalDTO = new TipoProductoDTO();
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		/*TipoProducto tipoProducto = (TipoProducto) request.getSession()
				.getAttribute("tipoProducto");
		if (tipoProducto != null) {
			productoForestal = tipoProducto;
		}*/
		
		TipoProductoDTO tipoProductoDTO = (TipoProductoDTO) request.getSession()
				.getAttribute("tipoProducto");
		if (tipoProductoDTO != null) {
			productoForestalDTO = tipoProductoDTO;
		}		
	}

	public TipoProducto getProductoForestal() {
		return productoForestal;
	}

	public void setProductoForestal(TipoProducto productoForestal) {
		this.productoForestal = productoForestal;
	}

	public TipoProductoDTO getProductoForestalDTO() {
		return productoForestalDTO;
	}

	public void setProductoForestalDTO(TipoProductoDTO productoForestalDTO) {
		this.productoForestalDTO = productoForestalDTO;
	}

	public boolean validar(StringBuffer error) {

		//return Validator.requerido(productoForestal.getNombre(), "Producto Forestal", error);
		return Validator.requerido(productoForestalDTO.getNombre(), "Producto Forestal", error);
	}
}
