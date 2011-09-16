package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RN")
public class RecursosNaturales extends Entidad {

	public String getTipoEntidad() {
		return "Recursos Naturales";
	}

	public String getIdTipoEntidad() {
		return "RN";
	}
}
