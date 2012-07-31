package ar.com.siif.dto;

public class TranzonDTO {

	private Long id;

	private String numero;

	private String disposicion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDisposicion() {
		return disposicion;
	}

	public void setDisposicion(String disposicion) {
		this.disposicion = disposicion;
	}
	
	public String getNumeroDisposicion(){
		
		return (this.getNumero()+" - "+this.getDisposicion());
	}	
}
