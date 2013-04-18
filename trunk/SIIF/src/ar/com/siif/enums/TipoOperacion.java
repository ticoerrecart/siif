package ar.com.siif.enums;

public enum TipoOperacion {

	ALTA("ALTA"), BAJA("BAJA"), MOD("MODIFICACION");

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
