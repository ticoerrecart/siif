package ar.com.siif.enums;

public enum TipoDeEntidad {
	OBR("Obrajero"), PPF("Pequeño Productor Forestal"), RN("Recursos Naturales");

	private String descripcion;

	TipoDeEntidad(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getName() {
		return name();
	}
}