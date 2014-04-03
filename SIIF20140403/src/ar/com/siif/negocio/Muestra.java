package ar.com.siif.negocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Muestra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private double largo;

	private double diametro1;

	private double diametro2;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "fiscalizacion_fk")
	private Fiscalizacion fiscalizacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public double getDiametro1() {
		return diametro1;
	}

	public void setDiametro1(double diametro1) {
		this.diametro1 = diametro1;
	}

	public double getDiametro2() {
		return diametro2;
	}

	public void setDiametro2(double diametro2) {
		this.diametro2 = diametro2;
	}

	public Fiscalizacion getFiscalizacion() {
		return fiscalizacion;
	}

	public void setFiscalizacion(Fiscalizacion fiscalizacion) {
		this.fiscalizacion = fiscalizacion;
	}

}
