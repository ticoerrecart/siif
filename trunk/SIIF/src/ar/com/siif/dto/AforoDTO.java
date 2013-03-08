package ar.com.siif.dto;

public class AforoDTO {

	private Long id;

	private String estado;

	private String tipoProductor;

	private String tipoProductorDesc;	
	
	private double valorAforo;
	
	private TipoProductoForestalDTO tipoProducto;

	public AforoDTO(){
		
		tipoProducto = new TipoProductoForestalDTO();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoProductor() {
		return tipoProductor;
	}

	public void setTipoProductor(String tipoProductor) {
		this.tipoProductor = tipoProductor;
	}

	public double getValorAforo() {
		return valorAforo;
	}

	public void setValorAforo(double valorAforo) {
		this.valorAforo = valorAforo;
	}

	public TipoProductoForestalDTO getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoForestalDTO tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getTipoProductorDesc() {
		return tipoProductorDesc;
	}

	public void setTipoProductorDesc(String tipoProductorDesc) {
		this.tipoProductorDesc = tipoProductorDesc;
	}
}
