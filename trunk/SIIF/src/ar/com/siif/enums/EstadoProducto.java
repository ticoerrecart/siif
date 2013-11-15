package ar.com.siif.enums;

public enum EstadoProducto {

	/*BMP("Bosque Maduros Primarios"), BS(
			"Bosques Secundarios/ juveniles/Volteo de viento");*/

	BMP("Bosque Maduros Primarios"),
	BP("Bosque Primarios"), 
	TSI("Trat. Silviculturales Incompletos"),
	BS("Bosque Secundario"),
	VV("Volteo de Viento"),
	SP("Silvopastoriles");
	
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
