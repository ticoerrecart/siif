package ar.com.siif.dto;

import ar.com.siif.negocio.AreaDeCosecha;
import ar.com.siif.negocio.Entidad;

public class AreaDeCosechaDTO extends LocalizacionDTO {

	private String reservaForestalArea;

	private String nombreArea;

	private String disposicionArea;

	private String expedienteArea;

	private EntidadDTO productorForestal;

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

		return "Rerserva: " + this.getReservaForestalArea() + " - Nombre: " + this.getNombreArea();
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
		return area;
	}

	@Override
	public boolean estaIncluidoGeograficamenteEn(LocalizacionDTO localizacion) {
		return false;
	}
}
