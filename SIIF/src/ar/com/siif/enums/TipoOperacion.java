package ar.com.siif.enums;

public enum TipoOperacion {

	ALTA("ALTA"), BAJA("BAJA"), MOD("MODIFICACION"), 
	MOD_ASOCIACION_FISCA("MODIFICACION_POR_ASOCIACION_FISCA"), MODGUIA_POR_MODFISCA("MODIFICACION_GUIA_X_MODIFICACION_FISCA"),
	MOD_DESASOCIACION_FISCA("MODIFICACION_POR_DESASOCIACION_FISCA");

	private String descripcion;

	TipoOperacion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getName() {
		return name();
	}	
}
