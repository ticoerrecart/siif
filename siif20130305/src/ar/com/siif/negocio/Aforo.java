package ar.com.siif.negocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siif.enums.TipoDeEntidad;

@Entity
public class Aforo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String estado;

	private String tipoProductor;

	private double valorAforo;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "tipoProducto_fk")
	private TipoProducto tipoProducto;

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

	public double getValorAforo() {
		return valorAforo;
	}

	public void setValorAforo(double valorAforo) {
		this.valorAforo = valorAforo;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getTipoProductor() {
		return tipoProductor;
	}

	public void setTipoProductor(String tipoProductor) {
		this.tipoProductor = tipoProductor;
	}

	public String getTipoProductorDesc(){
		
		if(this.getTipoProductor().equals(TipoDeEntidad.OBR.getName())){
			return TipoDeEntidad.OBR.getDescripcion();
		}
		else{
			return TipoDeEntidad.PPF.getDescripcion();
		}
	}
}
