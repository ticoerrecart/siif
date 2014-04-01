package ar.com.siif.struts.actions.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.AforoNuevoDTO;
import ar.com.siif.negocio.AforoNuevo;
import ar.com.siif.struts.utils.Validator;

public class AforoNuevoForm extends ActionForm {

	private AforoNuevo aforo;

	private AforoNuevoDTO aforoDTO;

	public AforoNuevoForm() {

		aforo = new AforoNuevo();
		aforoDTO = new AforoNuevoDTO();
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		AforoNuevoDTO aforoParam = (AforoNuevoDTO) request.getSession()
				.getAttribute("aforoParam");
		if (aforoParam != null) {
			aforoDTO = aforoParam;
		}
	}

	public AforoNuevo getAforo() {
		return aforo;
	}

	public void setAforo(AforoNuevo aforo) {
		this.aforo = aforo;
	}

	public AforoNuevoDTO getAforoDTO() {
		return aforoDTO;
	}

	public void setAforoDTO(AforoNuevoDTO aforoDTO) {
		this.aforoDTO = aforoDTO;
	}

	public boolean validar(StringBuffer error) {

		boolean ok = true;
		//if (this.getAforoDTO().getTipoProducto().getId() == null) {
			Validator.addErrorXML(error,
					"Seleccione un Tipo de Producto Forestal");
			ok = false;
		//}
		return ok;
	}

}
