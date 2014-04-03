package ar.com.siif.dto;

public class RangoDTO {

	public RangoDTO() {
		super();
	}

	public Long getDesde() {
		return desde;
	}

	public void setDesde(Long desde) {
		this.desde = desde;
	}

	public Long getHasta() {
		return hasta;
	}

	public void setHasta(Long hasta) {
		this.hasta = hasta;
	}

	private Long desde;
	private Long hasta;

}
