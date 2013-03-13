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

}
