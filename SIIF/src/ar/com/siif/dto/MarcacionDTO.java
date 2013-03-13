package ar.com.siif.dto;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Marcacion;

public class MarcacionDTO extends LocalizacionDTO {

	private String disposicionMarcacion;

	private TranzonDTO tranzon;

	public String getDisposicionMarcacion() {
		return disposicionMarcacion;
	}

	public void setDisposicionMarcacion(String disposicionMarcacion) {
		this.disposicionMarcacion = disposicionMarcacion;
	}

	public TranzonDTO getTranzon() {
		return tranzon;
	}

	public void setTranzon(TranzonDTO tranzonDTO) {
		this.tranzon = tranzonDTO;
	}

	@Override
	public Marcacion getLocalizacion(Entidad entidad) {
		return new Marcacion(this.disposicionMarcacion, null);
	}
}
