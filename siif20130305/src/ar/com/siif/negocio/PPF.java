package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siif.enums.TipoDeEntidad;

@Entity
@DiscriminatorValue("PPF")
public class PPF extends Entidad {

	public String getTipoEntidad() {
		return TipoDeEntidad.PPF.getDescripcion();
	}

	public String getIdTipoEntidad() {
		//return "PPF";
		return TipoDeEntidad.PPF.getName();
	}
}
