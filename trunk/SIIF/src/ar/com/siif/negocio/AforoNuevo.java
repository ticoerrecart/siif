package ar.com.siif.negocio;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.com.siif.enums.TipoDeAforo;

@Entity
public class AforoNuevo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double monto;

	private Integer porcentaje;

	@Enumerated(EnumType.STRING)
	private TipoDeAforo tipoDeAforo;

	public Double getValorSegunMontoOPorcentaje(Double montoBasico) {
		if (monto != null && monto.doubleValue() > 0.0) {
			return monto;
		} else {
			return montoBasico * porcentaje / 100;
		}
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

	public Long getId() {
		return id;
	}

}
