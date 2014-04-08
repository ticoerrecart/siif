package ar.com.siif.negocio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class CuentaCorrienteFiscalizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "productor_fk")
	private Entidad productor;

	@Column(nullable = false)
	private double monto;

	@Column(nullable = false)
	private Date fecha;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "usuario_fk")
	private Usuario usuario;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "guiaForestal_fk")
	private GuiaForestal guiaForestal;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "fiscalizacion_fk")
	private Fiscalizacion fiscalizacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entidad getProductor() {
		return productor;
	}

	public void setProductor(Entidad productor) {
		this.productor = productor;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public GuiaForestal getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestal guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Fiscalizacion getFiscalizacion() {
		return fiscalizacion;
	}

	public void setFiscalizacion(Fiscalizacion fiscalizacion) {
		this.fiscalizacion = fiscalizacion;
	}

}
