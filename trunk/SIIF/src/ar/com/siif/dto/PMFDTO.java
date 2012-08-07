package ar.com.siif.dto;

public class PMFDTO {

	private Long id;

	private String expediente;

	private String nombre;

	private EntidadDTO productorForestal;
	
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
	
	public EntidadDTO getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(EntidadDTO productorForestal) {
		this.productorForestal = productorForestal;
	}

	public String getNombreExpediente(){
		
		return this.getNombre()+" - "+this.getExpediente();
	}
}
