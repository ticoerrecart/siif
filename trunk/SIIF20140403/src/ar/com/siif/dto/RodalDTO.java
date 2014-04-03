package ar.com.siif.dto;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Rodal;

public class RodalDTO extends LocalizacionDTO {

	private String nombreRodal;

	private MarcacionDTO marcacion;

	public String getNombreRodal() {
		return nombreRodal;
	}

	public void setNombreRodal(String nombreRodal) {
		this.nombreRodal = nombreRodal;
	}

	public MarcacionDTO getMarcacion() {
		return marcacion;
	}

	public void setMarcacion(MarcacionDTO marcacionDTO) {
		this.marcacion = marcacionDTO;
	}

	@Override
	public Rodal getLocalizacion(Entidad entidad) {
		return new Rodal(this.nombreRodal, null);
	}

	public String getExpedientePMF() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getExpedientePMF();
		}
		return null;
	}

	public String getNombrePMF() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getNombrePMF();
		}
		return null;
	}

	public String getNumeroTranzon() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getNumeroTranzon();
		}
		return null;
	}

	public String getDisposicionTranzon() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getDisposicionTranzon();
		}
		return null;
	}

	public String getDisposicionMarcacion() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getDisposicionMarcacion();
		}
		return null;
	}

	public boolean esRodal() {
		return true;
	}

	@Override
	public boolean estaIncluidoGeograficamenteEn(LocalizacionDTO localizacion) {
		if (localizacion.esRodal()) {
			return false;
		}
		return true;
	}

	@Override
	public String getTipoTerreno() {
		return this.getMarcacion().getTipoTerreno();
	}

}
