package ar.com.siif.dto;

import ar.com.siif.negocio.AreaDeCosecha;
import ar.com.siif.negocio.Entidad;

public class AreaDeCosechaDTO extends LocalizacionDTO {

	private String reservaForestalArea;

	private String nombreArea;

	private String disposicionArea;

	private String expedienteArea;

	private EntidadDTO productorForestal;

	private String tipoTerreno;	
	
	public String getReservaForestalArea() {
		return reservaForestalArea;
	}

	public void setReservaForestalArea(String reservaForestalArea) {
		this.reservaForestalArea = reservaForestalArea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getDisposicionArea() {
		return disposicionArea;
	}

	public void setDisposicionArea(String disposicionArea) {
		this.disposicionArea = disposicionArea;
	}

	public String getExpedienteArea() {
		return expedienteArea;
	}

	public void setExpedienteArea(String expedienteArea) {
		this.expedienteArea = expedienteArea;
	}

	public EntidadDTO getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(EntidadDTO productorForestal) {
		this.productorForestal = productorForestal;
	}

	public String getFullNombre() {

		//return "Rerserva: " + this.getReservaForestalArea() + " - Nombre: " + this.getNombreArea();
		return this.getReservaForestalArea() + " - " + this.getNombreArea();
	}

	@Override
	public AreaDeCosecha getLocalizacion(Entidad entidad) {
		AreaDeCosecha area = new AreaDeCosecha();
		area.setDisposicionArea(disposicionArea);
		area.setExpedienteArea(expedienteArea);
		area.setId(this.getId());
		area.setNombreArea(nombreArea);
		area.setProductorForestal(entidad);
		area.setReservaForestalArea(reservaForestalArea);
		area.setTipoTerreno(tipoTerreno);
		
		return area;
	}

	@Override
	public boolean estaIncluidoGeograficamenteEn(LocalizacionDTO localizacion) {
		return false;
	}
	
	public boolean getEsAreaDeCosecha() {
		return true;
	}
	
	public String getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(String tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}	
}
