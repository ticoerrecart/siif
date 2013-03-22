package ar.com.siif.negocio;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siif.dto.TranzonDTO;

@Entity
@DiscriminatorValue("TRANZON")
public class Tranzon extends Localizacion {

	public Tranzon() {
		super();
	}

	public Tranzon(String numero, String disposicion, PMF pmf) {
		super();
		this.numeroTranzon = numero;
		this.disposicionTranzon = disposicion;
		this.pmf = pmf;
	}

	private String numeroTranzon;

	private String disposicionTranzon;

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

	public String getNumeroTranzon() {
		return numeroTranzon;
	}

	public void setNumeroTranzon(String numeroTranzon) {
		this.numeroTranzon = numeroTranzon;
	}

	public String getDisposicionTranzon() {
		return disposicionTranzon;
	}

	public void setDisposicionTranzon(String disposicionTranzon) {
		this.disposicionTranzon = disposicionTranzon;
	}

	public PMF getPmf() {
		return pmf;
	}

	public void setPmf(PMF pmf) {
		this.pmf = pmf;
	}

	public String getNumeroDisposicionTranzon() {

		return (this.getNumeroTranzon() + " - " + this.getDisposicionTranzon());
	}

	@Override
	public Entidad getProductorForestal() {
		return getPmf().getProductorForestal();
	}

	@Override
	public TranzonDTO getLocalizacionDTO() {
		TranzonDTO tranzonDTO = new TranzonDTO();

		tranzonDTO.setId(this.getId());
		tranzonDTO.setDisposicionTranzon(this.getDisposicionTranzon());
		tranzonDTO.setNumeroTranzon(this.getNumeroTranzon());
		tranzonDTO.setPmf(this.getPmf().getLocalizacionDTO());

		return tranzonDTO;
	}

	public boolean esTranzon() {
		return true;
	}

	@Override
	public boolean estaIncluidoGeograficamenteEn(Localizacion localizacion) {
		if (localizacion.esRodal() || localizacion.esMarcacion() || localizacion.esTranzon()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean esParteDeLaLocalizacion(Localizacion localizacion) {
		return localizacion.tieneTranzon(this.getId());
	}

	@Override
	public boolean tieneRodal(Long idRodal) {
		for (Marcacion marcacion : this.getMarcaciones()) {
			if (marcacion.tieneRodal(idRodal)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean tieneMarcacion(Long idMarcacion) {
		for (Marcacion marcacion : this.getMarcaciones()) {
			if (marcacion.getId() == idMarcacion) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean tieneTranzon(Long idTranzon) {
		return this.getId() == idTranzon;
	}

	@Override
	public String getExpedientePMF() {
		if (this.getPmf() != null) {
			return this.getPmf().getExpedientePMF();
		}
		return null;
	}

	@Override
	public String getNombrePMF() {
		if (this.getPmf() != null) {
			return this.getPmf().getNombrePMF();
		}
		return null;
	}
	
	@Override
	public String getNombreLocalizacion(){
		return this.getPmf().getNombreLocalizacion() + " Tranzon:" + this.getNumeroDisposicionTranzon();
	}
}
