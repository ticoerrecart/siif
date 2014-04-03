package ar.com.siif.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class CertificadoOrigen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long nroCertificado;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "exportador_fk")
	private Entidad exportador;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "productor_fk")
	private Entidad productor;

	private String periodoForestal;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "localizacion_fk")
	private Localizacion localizacion;

	private String reservaForestal;

	private String nroFactura;

	private double volumenTransferido;

	// private boolean deudaAforo;

	// private boolean compromisoPago;

	private String origenMateriaPrima;

	private String nroRemito;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "localidadDestino_fk")
	private LocalidadDestino localidadDestino;

	@OneToMany(mappedBy = "certificadoOrigen")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<TipoProductoEnCertificado> tiposProductoEnCertificado = new ArrayList<TipoProductoEnCertificado>();

	private double volumenTotalTipoProductos;

	private Date fecha;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "usuario_fk")
	private Usuario usuarioAlta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(Long nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public Entidad getExportador() {
		return exportador;
	}

	public void setExportador(Entidad exportador) {
		this.exportador = exportador;
	}

	public Entidad getProductor() {
		return productor;
	}

	public void setProductor(Entidad productor) {
		this.productor = productor;
	}

	public String getPeriodoForestal() {
		return periodoForestal;
	}

	public void setPeriodoForestal(String periodoForestal) {
		this.periodoForestal = periodoForestal;
	}

	public String getReservaForestal() {
		return reservaForestal;
	}

	public void setReservaForestal(String reservaForestal) {
		this.reservaForestal = reservaForestal;
	}

	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public double getVolumenTransferido() {
		return volumenTransferido;
	}

	public void setVolumenTransferido(double volumenTransferido) {
		this.volumenTransferido = volumenTransferido;
	}

	public String getOrigenMateriaPrima() {
		return origenMateriaPrima;
	}

	public void setOrigenMateriaPrima(String origenMateriaPrima) {
		this.origenMateriaPrima = origenMateriaPrima;
	}

	public String getNroRemito() {
		return nroRemito;
	}

	public void setNroRemito(String nroRemito) {
		this.nroRemito = nroRemito;
	}

	public LocalidadDestino getLocalidadDestino() {
		return localidadDestino;
	}

	public void setLocalidadDestino(LocalidadDestino localidadDestino) {
		this.localidadDestino = localidadDestino;
	}

	public double getVolumenTotalTipoProductos() {
		return volumenTotalTipoProductos;
	}

	public void setVolumenTotalTipoProductos(double volumenTotalTipoProductos) {
		this.volumenTotalTipoProductos = volumenTotalTipoProductos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(Usuario usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public List<TipoProductoEnCertificado> getTiposProductoEnCertificado() {
		return tiposProductoEnCertificado;
	}

	public void setTiposProductoEnCertificado(
			List<TipoProductoEnCertificado> tiposProductoEnCertificado) {
		this.tiposProductoEnCertificado = tiposProductoEnCertificado;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

}
