package ar.com.siif.negocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class TipoProductoEnCertificado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private double volumenTipoProducto;
	
	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "certificadoOrigen_fk")	
	private CertificadoOrigen certificadoOrigen;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "tipoProductoExportacion_fk")	
	private TipoProductoExportacion tipoProductoExportacion;	
	
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

	public CertificadoOrigen getCertificadoOrigen() {
		return certificadoOrigen;
	}

	public void setCertificadoOrigen(CertificadoOrigen certificadoOrigen) {
		this.certificadoOrigen = certificadoOrigen;
	}

	public TipoProductoExportacion getTipoProductoExportacion() {
		return tipoProductoExportacion;
	}

	public void setTipoProductoExportacion(
			TipoProductoExportacion tipoProductoExportacion) {
		this.tipoProductoExportacion = tipoProductoExportacion;
	}
	
}
