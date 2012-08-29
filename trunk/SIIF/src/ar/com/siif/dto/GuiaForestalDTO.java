package ar.com.siif.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ar.com.siif.negocio.Usuario;

public class GuiaForestalDTO {

	private Long id;
	
	private int nroGuia;

	private FiscalizacionDTO fiscalizacion;

	private String fechaVencimiento;

	private int distanciaAforoMovil;

	private String estado;

	private String especie;

	private int cantidad;

	private double importe;

	private double aforo;

	private double inspFiscalizacion;

	private String valorAforos;

	private String observaciones;

	private String localidad;

	private String fecha;

	private List<ValeTransporteDTO> valesTransporte;

	private List<BoletaDepositoDTO> boletasDeposito;

	private UsuarioDTO usuario;

	public GuiaForestalDTO() {

		fiscalizacion = new FiscalizacionDTO();
		valesTransporte = new ArrayList<ValeTransporteDTO>();
		boletasDeposito = new ArrayList<BoletaDepositoDTO>();
		usuario = new UsuarioDTO();
	}

	public int getNroGuia() {
		return nroGuia;
	}

	public void setNroGuia(int nroGuia) {
		this.nroGuia = nroGuia;
	}

	public FiscalizacionDTO getFiscalizacion() {
		return fiscalizacion;
	}

	public void setFiscalizacion(FiscalizacionDTO fiscalizacion) {
		this.fiscalizacion = fiscalizacion;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getDistanciaAforoMovil() {
		return distanciaAforoMovil;
	}

	public void setDistanciaAforoMovil(int distanciaAforoMovil) {
		this.distanciaAforoMovil = distanciaAforoMovil;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getAforo() {
		return aforo;
	}

	public void setAforo(double aforo) {
		this.aforo = aforo;
	}

	public double getInspFiscalizacion() {
		return inspFiscalizacion;
	}

	public void setInspFiscalizacion(double inspFiscalizacion) {
		this.inspFiscalizacion = inspFiscalizacion;
	}

	public String getValorAforos() {
		return valorAforos;
	}

	public void setValorAforos(String valorAforos) {
		this.valorAforos = valorAforos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<ValeTransporteDTO> getValesTransporte() {
		return valesTransporte;
	}

	public void setValesTransporte(List<ValeTransporteDTO> valesTransporte) {
		this.valesTransporte = valesTransporte;
	}

	public List<BoletaDepositoDTO> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
