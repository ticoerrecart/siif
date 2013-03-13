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

}
