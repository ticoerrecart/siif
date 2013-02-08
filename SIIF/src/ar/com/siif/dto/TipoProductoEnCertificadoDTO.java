package ar.com.siif.dto;

public class TipoProductoEnCertificadoDTO {

	private Long id;
	private double volumenTipoProducto;
	private CertificadoOrigenDTO certificadoOrigen;
	//private TipoProductoExportacionDTO tipoProductoExportacion;
	private TipoProductoDTO tipoProductoExportacion;
	
	public TipoProductoEnCertificadoDTO(){
		
		certificadoOrigen = new CertificadoOrigenDTO();
		tipoProductoExportacion = new TipoProductoDTO();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getVolumenTipoProducto() {
		return volumenTipoProducto;
	}

	public void setVolumenTipoProducto(double volumenTipoProducto) {
		this.volumenTipoProducto = volumenTipoProducto;
	}

	public CertificadoOrigenDTO getCertificadoOrigen() {
		return certificadoOrigen;
	}

	public void setCertificadoOrigen(CertificadoOrigenDTO certificadoOrigen) {
		this.certificadoOrigen = certificadoOrigen;
	}

	public TipoProductoDTO getTipoProductoExportacion() {
		return tipoProductoExportacion;
	}

	public void setTipoProductoExportacion(
			TipoProductoDTO tipoProductoExportacion) {
		this.tipoProductoExportacion = tipoProductoExportacion;
	}
	
	
}
