package ar.com.siif.enums;

public enum EspecieProducto {

	LENGA("Lenga"), Nire("Ñire"), GUINDO("Guindo");

	private String descripcion;

	EspecieProducto(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getName() {
		return name();
	}	
}
