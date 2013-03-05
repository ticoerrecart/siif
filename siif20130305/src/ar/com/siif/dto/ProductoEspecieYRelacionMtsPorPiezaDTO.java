package ar.com.siif.dto;

public class ProductoEspecieYRelacionMtsPorPiezaDTO {

	private String producto;
	private String especie;
	private double mts3xpieza;

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public double getMts3xpieza() {
		return mts3xpieza;
	}

	public void setMts3xpieza(double mts3xpieza) {
		this.mts3xpieza = mts3xpieza;
	}
}
