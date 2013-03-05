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
public class PMF {

	public PMF() {
		super();
	}

	public PMF(String expediente, String nombre, Entidad entidad) {
		super();
		this.expediente = expediente;
		this.nombre = nombre;
		this.productorForestal = entidad;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String expediente;

	private String nombre;

	private String tipoTerreno;
	
	@OneToMany(mappedBy = "pmf")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private List<Tranzon> tranzones;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "entidad_fk")
	private Entidad productorForestal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Entidad getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(Entidad productorForestal) {
		this.productorForestal = productorForestal;
	}

	public List<Tranzon> getTranzones() {
		return tranzones;
	}

	public void setTranzones(List<Tranzon> tranzones) {
		this.tranzones = tranzones;
	}
	
	public String getNombreExpediente(){
		
		return this.getNombre()+" - "+this.getExpediente();
	}

	public String getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(String tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}

}
