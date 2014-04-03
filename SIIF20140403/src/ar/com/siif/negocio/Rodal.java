package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siif.dto.MarcacionDTO;
import ar.com.siif.dto.RodalDTO;

@Entity
@DiscriminatorValue("RODAL")
public class Rodal extends Localizacion {

	public Rodal() {
		super();
	}

	public Rodal(String nombre, Marcacion marcacion) {
		super();
		this.nombreRodal = nombre;
		this.marcacion = marcacion;
	}

	private String nombreRodal;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "marcacion_fk")
	private Marcacion marcacion;

	public String getNombreRodal() {
		return nombreRodal;
	}

	public void setNombreRodal(String nombreRodal) {
		this.nombreRodal = nombreRodal;
	}

	public Marcacion getMarcacion() {
		return marcacion;
	}

	public void setMarcacion(Marcacion marcacion) {
		this.marcacion = marcacion;
	}

	@Override
	public Entidad getProductorForestal() {
		return getMarcacion().getProductorForestal();
	}

	@Override
	public RodalDTO getLocalizacionDTO() {
		RodalDTO rodalDTO = new RodalDTO();
		rodalDTO.setId(this.getId());
		rodalDTO.setNombreRodal(this.getNombreRodal());
		rodalDTO.setMarcacion((MarcacionDTO)this.getMarcacion().getLocalizacionDTO());

		return rodalDTO;
	}

	public boolean esRodal() {
		return true;
	}

	@Override
	public boolean estaIncluidoGeograficamenteEn(Localizacion localizacion) {
		if (localizacion.esRodal()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean esParteDeLaLocalizacion(Localizacion localizacion) {
		return localizacion.tieneRodal(this.getId());
	}

	@Override
	public boolean tieneRodal(Long idRodal) {
		return this.getId() == idRodal;
	}

	@Override
	public boolean tieneMarcacion(Long idMarcacion) {
		return false;
	}

	@Override
	public boolean tieneTranzon(Long idTranzon) {
		return false;
	}
	
	@Override
	public String getExpedientePMF() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getExpedientePMF();
		}
		return null;
	}

	@Override
	public String getNombrePMF() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getNombrePMF();
		}
		return null;
	}

	@Override
	public String getNumeroTranzon() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getNumeroTranzon();
		}
		return null;
	}

	@Override
	public String getDisposicionTranzon() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getDisposicionTranzon();
		}
		return null;
	}

	@Override
	public String getDisposicionMarcacion() {
		if (this.getMarcacion() != null) {
			return this.getMarcacion().getDisposicionMarcacion();
		}
		return null;
	}
	
	@Override
	public String getNombreLocalizacion(){
		return this.getMarcacion().getNombreLocalizacion() + " Rodal:" + this.getNombreRodal();
	}
}
