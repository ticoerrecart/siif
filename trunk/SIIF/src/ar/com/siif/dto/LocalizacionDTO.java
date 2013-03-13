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

}
