package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PPF")
public class PPF extends Entidad {

	public String getTipoEntidad() {
		return "Peque�o Productor Forestal";
	}

	public String getIdTipoEntidad() {
		return "PPF";
	}
}
