package ar.com.siif.struts.actions.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.AforoDTO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.struts.utils.Validator;

public class AforoForm extends ActionForm {

	private Aforo aforo;

	private long idTipoProductoForestal;

	private AforoDTO aforoDTO;
	
	public AforoForm() {

		aforo = new Aforo();
		aforoDTO = new AforoDTO();
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		/*Aforo aforoParam = (Aforo) request.getSession().getAttribute("aforoParam");
		if (aforoParam != null) {
			aforo = aforoParam;
			idTipoProductoForestal = aforo.getTipoProducto().getId();
		}*/
		
		AforoDTO aforoParam = (AforoDTO) request.getSession().getAttribute("aforoParam");
		if (aforoParam != null) {
			aforoDTO = aforoParam;			
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

		return Validator.validarDoubleMayorQue(0, String.valueOf(aforoDTO.getValorAforo()),
				"Valor Aforo", error);
	}

	public AforoDTO getAforoDTO() {
		return aforoDTO;
	}

	public void setAforoDTO(AforoDTO aforoDTO) {
		this.aforoDTO = aforoDTO;
	}
	
}
