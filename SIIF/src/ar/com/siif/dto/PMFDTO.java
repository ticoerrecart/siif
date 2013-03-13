package ar.com.siif.dto;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.PMF;

public class PMFDTO extends LocalizacionDTO {

	private String expedientePMF;

	private String nombrePMF;

	private String tipoTerrenoPMF;

	private EntidadDTO productorForestal;

	public EntidadDTO getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(EntidadDTO productorForestal) {
		this.productorForestal = productorForestal;
	}

	public String getNombreExpediente() {

		return this.getNombrePMF() + " - " + this.getExpedientePMF();
	}

	public String getExpedientePMF() {
		return expedientePMF;
	}

	public void setExpedientePMF(String expedientePMF) {
		this.expedientePMF = expedientePMF;
	}

	public String getNombrePMF() {
		return nombrePMF;
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

	@Override
	public PMF getLocalizacion(Entidad entidad) {
		PMF pmf = new PMF(this.expedientePMF, this.nombrePMF, this.getTipoTerrenoPMF(), entidad);
		return pmf;
	}

}
