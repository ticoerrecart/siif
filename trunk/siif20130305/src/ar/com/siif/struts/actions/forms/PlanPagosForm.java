package ar.com.siif.struts.actions.forms;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.utils.Constantes;

public class PlanPagosForm extends ActionForm {

	private long idGuiaForestal;

	private List<BoletaDepositoDTO> boletasDeposito;

	public PlanPagosForm() {

		boletasDeposito = new Vector<BoletaDepositoDTO>();
		for (int i = 0; i < Constantes.COLECCION_BOLETAS_DEPOSITO_MAX; i++) {
			boletasDeposito.add(new BoletaDepositoDTO());
		}
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		boletasDeposito = new Vector<BoletaDepositoDTO>();
		for (int i = 0; i < Constantes.COLECCION_BOLETAS_DEPOSITO_MAX; i++) {
			boletasDeposito.add(new BoletaDepositoDTO());
		}
	}

	public boolean validar(StringBuffer error) {

		return true;
	}

	public long getIdGuiaForestal() {
		return idGuiaForestal;
	}

	public void setIdGuiaForestal(long idGuiaForestal) {
		this.idGuiaForestal = idGuiaForestal;
	}

	public List<BoletaDepositoDTO> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

}
