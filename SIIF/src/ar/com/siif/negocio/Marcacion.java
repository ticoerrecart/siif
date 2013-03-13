package ar.com.siif.negocio;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siif.dto.MarcacionDTO;
import ar.com.siif.dto.TranzonDTO;

@Entity
@DiscriminatorValue("MARCACION")
public class Marcacion extends Localizacion {

	public Marcacion() {
		super();
	}

	public Marcacion(String disposicion, Tranzon tranzon) {
		super();
		this.disposicionMarcacion = disposicion;
		this.tranzon = tranzon;
	}

	private String disposicionMarcacion;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "tranzon_fk")
	private Tranzon tranzon;

	public String getDisposicionMarcacion() {
		return disposicionMarcacion;
	}

	public void setDisposicionMarcacion(String disposicionMarcacion) {
		this.disposicionMarcacion = disposicionMarcacion;
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

	@Override
	public Entidad getProductorForestal() {
		return this.getTranzon().getProductorForestal();
	}

	@Override
	public MarcacionDTO getLocalizacionDTO() {
		MarcacionDTO marcacionDTO = new MarcacionDTO();
		marcacionDTO.setId(this.getId());
		marcacionDTO.setDisposicionMarcacion(this.getDisposicionMarcacion());
		marcacionDTO.setTranzon((TranzonDTO) this.getTranzon().getLocalizacionDTO());

		return marcacionDTO;
	}

}
