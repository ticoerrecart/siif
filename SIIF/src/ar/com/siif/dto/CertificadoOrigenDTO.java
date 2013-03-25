package ar.com.siif.dto;

import java.util.ArrayList;
import java.util.List;

public class CertificadoOrigenDTO {

	private Long id;

	private Long nroCertificado;

	private EntidadDTO exportador;

	private EntidadDTO productor;

	private String periodoForestal;

	private PMFDTO pmf;

	private AreaDeCosechaDTO areaDeCosecha;

	private String reservaForestal;

	private String nroFactura;

	private double volumenTransferido;

	// private boolean deudaAforo;

	// private boolean compromisoPago;

	private String origenMateriaPrima;

	private String nroRemito;

	private LocalidadDestinoDTO localidadDestino;

	private List<TipoProductoEnCertificadoDTO> tiposProductoEnCertificado;

	private double volumenTotalTipoProductos;

	private String fecha;

	private UsuarioDTO usuarioAlta;

	public CertificadoOrigenDTO() {

		exportador = new EntidadDTO();
		productor = new EntidadDTO();
		pmf = new PMFDTO();
		areaDeCosecha = new AreaDeCosechaDTO();
		localidadDestino = new LocalidadDestinoDTO();
		tiposProductoEnCertificado = new ArrayList<TipoProductoEnCertificadoDTO>();
		usuarioAlta = new UsuarioDTO();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntidadDTO getExportador() {
		return exportador;
	}

	public void setExportador(EntidadDTO exportador) {
		this.exportador = exportador;
	}

	public EntidadDTO getProductor() {
		return productor;
	}

	public void setProductor(EntidadDTO productor) {
		this.productor = productor;
	}

	public String getPeriodoForestal() {
		return periodoForestal;
	}

	public void setPeriodoForestal(String periodoForestal) {
		this.periodoForestal = periodoForestal;
	}

	public PMFDTO getPmf() {
		return pmf;
	}

	public void setPmf(PMFDTO pmf) {
		this.pmf = pmf;
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

	public LocalidadDestinoDTO getLocalidadDestino() {
		return localidadDestino;
	}

	public void setLocalidadDestino(LocalidadDestinoDTO localidadDestino) {
		this.localidadDestino = localidadDestino;
	}

	public double getVolumenTotalTipoProductos() {
		return volumenTotalTipoProductos;
	}

	public void setVolumenTotalTipoProductos(double volumenTotalTipoProductos) {
		this.volumenTotalTipoProductos = volumenTotalTipoProductos;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public UsuarioDTO getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(UsuarioDTO usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public Long getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(Long nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public List<TipoProductoEnCertificadoDTO> getTiposProductoEnCertificado() {
		return tiposProductoEnCertificado;
	}

	public void setTiposProductoEnCertificado(
			List<TipoProductoEnCertificadoDTO> tiposProductoEnCertificado) {
		this.tiposProductoEnCertificado = tiposProductoEnCertificado;
	}

	public AreaDeCosechaDTO getAreaDeCosecha() {
		return areaDeCosecha;
	}

	public void setAreaDeCosecha(AreaDeCosechaDTO areaDeCosecha) {
		this.areaDeCosecha = areaDeCosecha;
	}

}
