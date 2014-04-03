package ar.com.siif.negocio;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siif.dto.LocalizacionDTO;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoLocalizacion", discriminatorType = DiscriminatorType.STRING)
public abstract class Localizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "entidad_fk")
	protected Entidad productorForestal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entidad getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(Entidad productorForestal) {
		this.productorForestal = productorForestal;
	}

	public abstract LocalizacionDTO getLocalizacionDTO();

	// metodos nuevos//

	public boolean esAreaDeCosecha() {
		return false;
	}

	public abstract boolean estaIncluidoGeograficamenteEn(Localizacion localizacion);

	public boolean esRodal() {
		return false;
	}

	public boolean esMarcacion() {
		return false;
	}

	public boolean esTranzon() {
		return false;
	}

	public abstract boolean esParteDeLaLocalizacion(Localizacion localizacion);

	public abstract boolean tieneRodal(Long idRodal);

	public abstract boolean tieneMarcacion(Long idMarcacion);

	public abstract boolean tieneTranzon(Long idTranzon);

	public String getExpedientePMF() {
		return null;
	}

	public String getNombrePMF() {
		return null;
	}

	public String getDisposicionTranzon() {
		return null;
	}

	public String getNumeroTranzon() {
		return null;
	}

	public String getDisposicionMarcacion() {
		return null;
	}

	public String getNombreRodal() {
		return null;
	}

	public String getNombreArea() {
		return null;
	}

	public String getNombreLocalizacion(){
		return null;
	}
}
