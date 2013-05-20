package ar.com.siif.dto;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.PMF;

public class PMFDTO extends LocalizacionDTO {

	private String expedientePMF;

	private String nombrePMF;

	private String tipoTerreno;

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

	public String getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(String tipoTerrenoPMF) {
		this.tipoTerreno = tipoTerrenoPMF;
	}

	@Override
	public PMF getLocalizacion(Entidad entidad) {
		PMF pmf = new PMF(this.expedientePMF, this.nombrePMF, this.getTipoTerreno(), entidad);
		return pmf;
	}

	@Override
	public boolean estaIncluidoGeograficamenteEn(LocalizacionDTO localizacion) {
		return false;
	}
}
