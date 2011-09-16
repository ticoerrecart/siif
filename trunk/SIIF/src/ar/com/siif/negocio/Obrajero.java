package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OBR")
public class Obrajero extends Entidad {

	public String getTipoEntidad() {
		return "Obrajero";
	}

	public String getIdTipoEntidad() {
		return "OBR";
	}
}
