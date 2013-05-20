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

	public String getExpedientePMF() {
		if (this.getTranzon() != null) {
			return this.getTranzon().getExpedientePMF();
		}
		return null;
	}

	public String getNombrePMF() {
		if (this.getTranzon() != null) {
			return this.getTranzon().getNombrePMF();
		}
		return null;
	}

	public String getNumeroTranzon() {
		if (this.getTranzon() != null) {
			return this.getTranzon().getNumeroTranzon();
		}
		return null;
	}

	public String getDisposicionTranzon() {
		if (this.getTranzon() != null) {
			return this.getTranzon().getDisposicionTranzon();
		}
		return null;
	}

	public boolean esMarcacion() {
		return true;
	}

	@Override
	public boolean estaIncluidoGeograficamenteEn(LocalizacionDTO localizacion) {
		if (localizacion.esRodal() || localizacion.esMarcacion()) {
			return false;
		}
		return true;
	}

	@Override
	public String getTipoTerreno() {
		return this.getTranzon().getTipoTerreno();
	}
}
