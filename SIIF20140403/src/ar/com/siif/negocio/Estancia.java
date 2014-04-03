package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siif.enums.TipoDeEntidad;

@Entity
@DiscriminatorValue("EST")
public class Estancia extends Entidad {

	public String getTipoEntidad() {
		return TipoDeEntidad.EST.getDescripcion();
	}

	public String getIdTipoEntidad() {
		return TipoDeEntidad.EST.getName();
	}
}
