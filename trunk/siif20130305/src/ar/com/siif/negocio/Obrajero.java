package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siif.enums.TipoDeEntidad;

@Entity
@DiscriminatorValue("OBR")
public class Obrajero extends Entidad {

	public String getTipoEntidad() {
		return TipoDeEntidad.OBR.getDescripcion();
	}

	public String getIdTipoEntidad() {
		//return "OBR";
		return TipoDeEntidad.OBR.getName();
	}
}
