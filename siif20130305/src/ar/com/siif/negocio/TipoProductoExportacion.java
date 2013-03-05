package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import ar.com.siif.enums.TipoDeProducto;

@Entity
@DiscriminatorValue("EXP")
public class TipoProductoExportacion extends TipoProducto{
	
	public String getTipoEntidad() {
		return TipoDeProducto.EXP.getDescripcion();
	}

	public String getIdTipoEntidad() {
		return TipoDeProducto.EXP.getName();
	}	
}
