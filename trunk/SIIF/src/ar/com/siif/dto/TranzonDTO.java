package ar.com.siif.dto;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Tranzon;

public class TranzonDTO extends LocalizacionDTO {

	private String numeroTranzon;

	private String disposicionTranzon;

	private PMFDTO pmf;

	public String getNumeroTranzon() {
		return numeroTranzon;
	}

	public void setNumeroTranzon(String numeroTranzon) {
		this.numeroTranzon = numeroTranzon;
	}

	public String getDisposicionTranzon() {
		return disposicionTranzon;
	}

	public void setDisposicionTranzon(String disposicionTranzon) {
		this.disposicionTranzon = disposicionTranzon;
	}

	public String getNumeroDisposicionTranzon() {
		return (this.getNumeroTranzon() + " - " + this.getDisposicionTranzon());
	}

	public PMFDTO getPmf() {
		return pmf;
	}

	public void setPmf(PMFDTO pmf) {
		this.pmf = pmf;
	}

	@Override
	public Tranzon getLocalizacion(Entidad entidad) {
		return new Tranzon(this.numeroTranzon, this.disposicionTranzon, null);
	}
}
