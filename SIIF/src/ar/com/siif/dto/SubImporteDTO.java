package ar.com.siif.dto;

import ar.com.siif.enums.EspecieProducto;

public class SubImporteDTO implements Comparable<SubImporteDTO> {

	private Long id;

	private boolean comercializaDentroProvincia;

	private EspecieProducto especie;

	private double valorAforos;

	private int cantidadUnidades;

	private double cantidadMts;

	private double importe;

	private TipoProductoForestalDTO tipoProducto;

	private GuiaForestalDTO guiaForestal;

	public SubImporteDTO() {

		tipoProducto = new TipoProductoForestalDTO();
		guiaForestal = new GuiaForestalDTO();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isComercializaDentroProvincia() {
		return comercializaDentroProvincia;
	}

	public void setComercializaDentroProvincia(
			boolean comercializaDentroProvincia) {
		this.comercializaDentroProvincia = comercializaDentroProvincia;
	}

	public void setComercializaDentroProvinciaStr(
			String comercializaDentroProvincia) {
		this.comercializaDentroProvincia = Boolean
				.valueOf(comercializaDentroProvincia);
	}

	public String getComercializaDentroProvinciaStr() {
		return Boolean.toString(this.comercializaDentroProvincia);
	}

	public EspecieProducto getEspecie() {
		return especie;
	}

	public void setEspecie(EspecieProducto especie) {
		this.especie = especie;
	}

	public void setEspecieStr(String especie) {
		this.especie = EspecieProducto.valueOf(especie);
	}

	public double getValorAforos() {
		return valorAforos;
	}

	public void setValorAforos(double valorAforos) {
		this.valorAforos = valorAforos;
	}

	public int getCantidadUnidades() {
		return cantidadUnidades;
	}

	public void setCantidadUnidades(int cantidadUnidades) {
		this.cantidadUnidades = cantidadUnidades;
	}

	public double getCantidadMts() {
		return cantidadMts;
	}

	public void setCantidadMts(double cantidadMts) {
		this.cantidadMts = cantidadMts;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public TipoProductoForestalDTO getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoForestalDTO tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public GuiaForestalDTO getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestalDTO guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

	public int compareTo(SubImporteDTO o) {
		return this.getTipoProducto().getId()
				.compareTo(o.getTipoProducto().getId());
	}
}
