package ar.com.siif.dto;

public class RodalDTO {

	private Long id;

	private String nombre;

	private MarcacionDTO marcacion;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public MarcacionDTO getMarcacion() {
		return marcacion;
	}

	public void setMarcacion(MarcacionDTO marcacion) {
		this.marcacion = marcacion;
	}
	
	
}
