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
public class Rodal {

	public Rodal() {
		super();
	}

	public Rodal(String nombre, Marcacion marcacion) {
		super();
		this.nombre = nombre;
		this.marcacion = marcacion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nombre;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "marcacion_fk")
	private Marcacion marcacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Marcacion getMarcacion() {
		return marcacion;
	}

	public void setMarcacion(Marcacion marcacion) {
		this.marcacion = marcacion;
	}

}
