package ar.com.siif.dto;

public class MarcacionDTO {

	private Long id;

	private String disposicion;

	private TranzonDTO tranzon;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisposicion() {
		return disposicion;
	}

	public void setDisposicion(String disposicion) {
		this.disposicion = disposicion;
	}

	public TranzonDTO getTranzon() {
		return tranzon;
	}

	public void setTranzon(TranzonDTO tranzon) {
		this.tranzon = tranzon;
	}
	
}
