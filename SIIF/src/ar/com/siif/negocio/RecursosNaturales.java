package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siif.enums.TipoDeEntidad;

@Entity
@DiscriminatorValue("RN")
public class RecursosNaturales extends Entidad {

	public String getTipoEntidad() {
		return TipoDeEntidad.RN.getDescripcion();
	}

	public String getIdTipoEntidad() {
		//return "RN";
		return TipoDeEntidad.RN.getName();
	}
}
