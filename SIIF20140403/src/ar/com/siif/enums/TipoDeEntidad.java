package ar.com.siif.enums;

import ar.com.siif.negocio.Estancia;
import ar.com.siif.negocio.Obrajero;
import ar.com.siif.negocio.SinFinesDeLucro;

public enum TipoDeEntidad {
	OBR("Obrajero",Obrajero.class), PPF("Pequeño Productor Forestal", ar.com.siif.negocio.PPF.class ), EST("Estancia",Estancia.class), SFDL("Sin Fines de Lucro",SinFinesDeLucro.class);

	private String descripcion;
	private Class clase;
	
	TipoDeEntidad(String pDescripcion,Class pClass) {
		this.descripcion = pDescripcion;
		this.clase = pClass;
	}

	public Class getClase() {
		return clase;
	}

	public void setClase(Class clase) {
		this.clase = clase;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getName() {
		return name();
	}
}