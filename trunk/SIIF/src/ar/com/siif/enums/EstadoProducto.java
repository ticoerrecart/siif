package ar.com.siif.enums;

public enum EstadoProducto {
	// Seco("Seco"), Verde("Verde");
	// Verde("Verde");
	BMP("Bosque Maduros Primarios"), BS(
			"Bosques Secundarios/ juveniles/Volteo de viento");

	private String descripcion;

	EstadoProducto(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getName() {
		return name();
	}
}
