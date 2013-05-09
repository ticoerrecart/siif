package ar.com.siif.dto;

import ar.com.siif.enums.EstadoProducto;

public class AforoDTO {

	private Long id;

	private EstadoProducto estado;

	private String tipoProductor;

	private String tipoProductorDesc;

	private double valorAforo;

	private TipoProductoForestalDTO tipoProducto;

	public AforoDTO() {

		tipoProducto = new TipoProductoForestalDTO();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	public void setEstadoStr(String estado) {
		this.estado = EstadoProducto.valueOf(estado);
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
