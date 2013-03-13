package ar.com.siif.negocio;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siif.dto.PMFDTO;
import ar.com.siif.providers.ProviderDTO;

@Entity
@DiscriminatorValue("PMF")
public class PMF extends Localizacion {

	public PMF() {
		super();
	}

	public PMF(String expediente, String nombre, String tipoTerreno, Entidad entidad) {
		super();
		this.expedientePMF = expediente;
		this.nombrePMF = nombre;
		this.tipoTerrenoPMF = tipoTerreno;
		this.productorForestal = entidad;

	}

	private String expedientePMF;

	private String nombrePMF;

	private String tipoTerrenoPMF;

	@OneToMany(mappedBy = "pmf")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private List<Tranzon> tranzones;

	public String getExpedientePMF() {
		return expedientePMF;
	}

	public void setExpedientePMF(String expedientePMF) {
		this.expedientePMF = expedientePMF;
	}

	public String getNombrePMF() {
		return nombrePMF;
	}

	public String getNombreExpediente() {
		return this.getNombrePMF() + " - " + this.getExpedientePMF();
	}

	public void setNombrePMF(String nombrePMF) {
		this.nombrePMF = nombrePMF;
	}

	public String getTipoTerrenoPMF() {
		return tipoTerrenoPMF;
	}

	public void setTipoTerrenoPMF(String tipoTerrenoPMF) {
		this.tipoTerrenoPMF = tipoTerrenoPMF;
	}

	public List<Tranzon> getTranzones() {
		return tranzones;
	}

	public void setTranzones(List<Tranzon> tranzones) {
		this.tranzones = tranzones;
	}

	public PMFDTO getLocalizacionDTO() {
		PMFDTO pmfDTO = new PMFDTO();
		pmfDTO.setId(this.getId());
		pmfDTO.setExpedientePMF(this.getExpedientePMF());
		pmfDTO.setNombrePMF(this.getNombrePMF());
		pmfDTO.setTipoTerrenoPMF(this.getTipoTerrenoPMF());
		pmfDTO.setProductorForestal(ProviderDTO.getEntidadDTO(this.getProductorForestal()));

		return pmfDTO;
	}

}
