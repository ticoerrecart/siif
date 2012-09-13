package ar.com.siif.dto;

public class RangoDTO {

	public RangoDTO() {
		super();
	}

	public int getDesde() {
		return desde;
	}

	public void setDesde(int desde) {
		this.desde = desde;
	}

	public int getHasta() {
		return hasta;
	}

	public void setHasta(int hasta) {
		this.hasta = hasta;
	}

	private int desde;
	private int hasta;

}
