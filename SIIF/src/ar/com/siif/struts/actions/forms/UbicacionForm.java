package ar.com.siif.struts.actions.forms;

import org.apache.struts.action.ActionForm;

public class UbicacionForm extends ActionForm {

	private Long idProductorForestal;

	private Long idPMF;

	private Long idArea;

	public Long getIdZMF() {
		return idZMF;
	}

	private Long idTranzon;

	private Long idRodal;

	private Long idMarcacion;

	public Long getIdTranzon() {
		return idTranzon;
	}

	public void setIdTranzon(Long idTranzon) {
		this.idTranzon = idTranzon;
	}

	public Long getIdRodal() {
		return idRodal;
	}

	public void setIdRodal(Long idRodal) {
		this.idRodal = idRodal;
	}

	public Long getIdMarcacion() {
		return idMarcacion;
	}

	public void setIdMarcacion(Long idMarcacion) {
		this.idMarcacion = idMarcacion;
	}

	public Long getIdProductorForestal() {
		return idProductorForestal;
	}

	public void setIdProductorForestal(Long idProductorForestal) {
		this.idProductorForestal = idProductorForestal;
	}

	public Long getIdPMF() {
		return idPMF;
	}

	public void setIdPMF(Long idPMF) {
		this.idPMF = idPMF;
	}

	public void setIdZMF(Long idZMF) {
		this.idZMF = idZMF;
	}

	private Long idZMF;

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public UbicacionForm() {

	}

	public boolean validar(StringBuffer error) {
		return true;
	}

}
