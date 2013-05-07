package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siif.enums.TipoDeEntidad;

@Entity
@DiscriminatorValue("SFDL")
public class SinFinesDeLucro extends Entidad {

	public String getTipoEntidad() {
		return TipoDeEntidad.SFDL.getDescripcion();
	}

	public String getIdTipoEntidad() {
		//
		return TipoDeEntidad.SFDL.getName();
	}
}
