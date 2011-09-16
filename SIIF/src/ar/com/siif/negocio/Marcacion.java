package ar.com.siif.negocio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Marcacion {

	public Marcacion() {
		super();
	}

	public Marcacion(String disposicion, Tranzon tranzon) {
		super();
		this.disposicion = disposicion;
		this.tranzon = tranzon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String disposicion;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "tranzon_fk")
	private Tranzon tranzon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisposicion() {
		return disposicion;
	}

	public void setDisposicion(String disposicion) {
		this.disposicion = disposicion;
	}

	public Tranzon getTranzon() {
		return tranzon;
	}

	public void setTranzon(Tranzon tranzon) {
		this.tranzon = tranzon;
	}

	public List<Rodal> getRodales() {
		return rodales;
	}

	public void setRodales(List<Rodal> rodales) {
		this.rodales = rodales;
	}

	@OneToMany(mappedBy = "marcacion")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private List<Rodal> rodales;
}
