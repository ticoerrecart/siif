package ar.com.siif.dto;

public class PMFDTO {

	private Long id;

	private String expediente;

	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public String getNombreExpediente(){
		
		return this.getNombre()+" - "+this.getExpediente();
	}	
}
