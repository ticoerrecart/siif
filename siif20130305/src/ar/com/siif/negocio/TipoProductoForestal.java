package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siif.enums.TipoDeProducto;

@Entity
@DiscriminatorValue("FST")
public class TipoProductoForestal extends TipoProducto {

	public String getTipoEntidad() {
		return TipoDeProducto.FST.getDescripcion();
	}

	public String getIdTipoEntidad() {
		return TipoDeProducto.FST.getName();
	}	
}
