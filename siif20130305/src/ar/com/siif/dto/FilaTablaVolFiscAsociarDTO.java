package ar.com.siif.dto;

public class FilaTablaVolFiscAsociarDTO {
	
	private Long idTipoProducto;
	private String nombreProducto;
	private double volumenTotalEnGuia;
	private double volumenEnFiscalizaciones;
	private double volumenFaltante;
	
	public FilaTablaVolFiscAsociarDTO(){}
	
	public FilaTablaVolFiscAsociarDTO(Long pIdTipoProducto, String pNombreProducto, double pVolumenTotalEnGuia,
										double pVolumenEnFiscalizaciones, double pVolumenFaltante)
	{
		this.setIdTipoProducto(pIdTipoProducto);
		this.setNombreProducto(pNombreProducto);
		this.setVolumenTotalEnGuia(pVolumenTotalEnGuia);
		this.setVolumenEnFiscalizaciones(pVolumenEnFiscalizaciones);
		this.setVolumenFaltante(pVolumenFaltante);
	}
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public double getVolumenTotalEnGuia() {
		return volumenTotalEnGuia;
	}
	public void setVolumenTotalEnGuia(double volumenTotalEnGuia) {
		this.volumenTotalEnGuia = volumenTotalEnGuia;
	}
	public double getVolumenEnFiscalizaciones() {
		return volumenEnFiscalizaciones;
	}
	public void setVolumenEnFiscalizaciones(double volumenEnFiscalizaciones) {
		this.volumenEnFiscalizaciones = volumenEnFiscalizaciones;
	}
	public double getVolumenFaltante() {
		return volumenFaltante;
	}
	public void setVolumenFaltante(double volumenFaltante) {
		this.volumenFaltante = volumenFaltante;
	}
	public Long getIdTipoProducto() {
		return idTipoProducto;
	}
	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}
}
