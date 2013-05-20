package ar.com.siif.dto;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localizacion;

public abstract class LocalizacionDTO {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public abstract Localizacion getLocalizacion(Entidad entidad);

	public String getExpedientePMF() {
		return null;
	}

	public String getNombrePMF() {
		return null;
	}

	public String getNumeroTranzon() {
		return null;
	}

	public String getDisposicionTranzon() {
		return null;
	}

	public String getDisposicionMarcacion() {
		return null;
	}

	public String getNombreRodal() {
		return null;
	}

	public String getNombreArea() {
		return null;
	}

	public abstract boolean estaIncluidoGeograficamenteEn(LocalizacionDTO localizacion);

	public boolean esRodal() {
		return false;
	}

	public boolean esMarcacion() {
		return false;
	}

	public boolean esTranzon() {
		return false;
	}

	public boolean getEsAreaDeCosecha() {
		return false;
	}

	public abstract String getTipoTerreno();
}
