package ar.com.siif.enums;

public enum TipoDeProducto {

	FST("Forestal"), EXP("Exportacion");

	private String descripcion;

	TipoDeProducto(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getName() {
		return name();
	}	
}
