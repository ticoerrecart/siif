package ar.com.siif.struts.actions.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.negocio.Aforo;
import ar.com.siif.struts.utils.Validator;

public class AforoForm extends ActionForm {

	private Aforo aforo;

	private long idTipoProductoForestal;

	public AforoForm() {

		aforo = new Aforo();
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		Aforo aforoParam = (Aforo) request.getSession().getAttribute("aforoParam");
		if (aforoParam != null) {
			aforo = aforoParam;
			idTipoProductoForestal = aforo.getTipoProducto().getId();
		}
	}

	public Aforo getAforo() {
		return aforo;
	}

	public void setAforo(Aforo aforo) {
		this.aforo = aforo;
	}

	public long getIdTipoProductoForestal() {
		return idTipoProductoForestal;
	}

	public void setIdTipoProductoForestal(long idTipoProductoForestal) {
		this.idTipoProductoForestal = idTipoProductoForestal;
	}

	public boolean validar(StringBuffer error) {

		return Validator.validarDoubleMayorQue(0, String.valueOf(aforo.getValorAforo()),
				"Valor Aforo", error);
	}
}
