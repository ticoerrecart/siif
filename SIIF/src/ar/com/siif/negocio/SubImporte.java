package ar.com.siif.negocio;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siif.enums.EstadoProducto;

@Entity
public class SubImporte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoProducto estado;

	private String especie;

	private double valorAforos;

	private int cantidadUnidades;

	private double cantidadMts;

	private double importe;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "tipoProducto_fk")
	private TipoProductoForestal tipoProducto;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "guiaForestal_fk")
	private GuiaForestal guiaForestal;

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public double getValorAforos() {
		return valorAforos;
	}

	public void setValorAforos(double valorAforos) {
		this.valorAforos = valorAforos;
	}

	public int getCantidadUnidades() {
		return cantidadUnidades;
	}

	public void setCantidadUnidades(int cantidadUnidades) {
		this.cantidadUnidades = cantidadUnidades;
	}

	public double getCantidadMts() {
		return cantidadMts;
	}

	public void setCantidadMts(double cantidadMts) {
		this.cantidadMts = cantidadMts;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public TipoProductoForestal getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoForestal tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public GuiaForestal getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestal guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
