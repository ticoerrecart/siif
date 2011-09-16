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
public class Tranzon {

	public Tranzon() {
		super();
	}

	public Tranzon(String numero, String disposicion, PMF pmf) {
		super();
		this.numero = numero;
		this.disposicion = disposicion;
		this.pmf = pmf;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String numero;

	private String disposicion;
	
	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "pmf_fk")
	private PMF pmf;

	@OneToMany(mappedBy = "tranzon")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private List<Marcacion> marcaciones;

	public List<Marcacion> getMarcaciones() {
		return marcaciones;
	}

	public void setMarcaciones(List<Marcacion> marcaciones) {
		this.marcaciones = marcaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDisposicion() {
		return disposicion;
	}

	public void setDisposicion(String disposicion) {
		this.disposicion = disposicion;
	}

	public PMF getPmf() {
		return pmf;
	}

	public void setPmf(PMF pmf) {
		this.pmf = pmf;
	}
		
	public String getNumeroDisposicion(){
		
		return (this.getNumero()+" - "+this.getDisposicion());
	}
}
