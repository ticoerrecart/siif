package ar.com.siif.dto;

import ar.com.siif.enums.TipoDeAforo;

public class AforoNuevoDTO {

	private Long id;

	private Double monto;

	private Integer porcentaje;

	private TipoDeAforo tipoDeAforo;

	public AforoNuevoDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public TipoDeAforo getTipoDeAforo() {
		return tipoDeAforo;
	}

	public void setTipoDeAforo(TipoDeAforo tipoDeAforo) {
		this.tipoDeAforo = tipoDeAforo;
	}

}
