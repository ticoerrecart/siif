package ar.com.siif.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ar.com.siif.negocio.Usuario;

public class GuiaForestalDTO {

	private int nroGuia;

	private FiscalizacionDTO fiscalizacion;

	private String lugarCorte;

	//private long permisionario;
	private Date fechaVencimiento;

	private int distanciaAforoMovil;

	private String nroExpediente;

	private String periodoForestal;

	private String disposicionNro;

	private String tipo;

	private String estado;

	private String especie;

	private double volumenTotal;

	private int cantidad;

	private double importe;

	private double aforo;

	private double inspFiscalizacion;

	private String valorAforos;

	private String observaciones;

	private String localidad;

	private Date fecha;

	private List<ValeTransporteDTO> valesTransporte = new ArrayList<ValeTransporteDTO>();

	private List<BoletaDepositoDTO> boletasDeposito = new ArrayList<BoletaDepositoDTO>();

	private Usuario usuario;

	public GuiaForestalDTO() {

		fiscalizacion = new FiscalizacionDTO();
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

	public String getLugarCorte() {
		return lugarCorte;
	}

	public void setLugarCorte(String lugarCorte) {
		this.lugarCorte = lugarCorte;
	}

	/*public long getPermisionario() {
		return permisionario;
	}
	public void setPermisionario(long permisionario) {
		this.permisionario = permisionario;
	}*/
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getDistanciaAforoMovil() {
		return distanciaAforoMovil;
	}

	public void setDistanciaAforoMovil(int distanciaAforoMovil) {
		this.distanciaAforoMovil = distanciaAforoMovil;
	}

	public String getNroExpediente() {
		return nroExpediente;
	}

	public void setNroExpediente(String nroExpediente) {
		this.nroExpediente = nroExpediente;
	}

	public String getPeriodoForestal() {
		return periodoForestal;
	}

	public void setPeriodoForestal(String periodoForestal) {
		this.periodoForestal = periodoForestal;
	}

	public String getDisposicionNro() {
		return disposicionNro;
	}

	public void setDisposicionNro(String disposicionNro) {
		this.disposicionNro = disposicionNro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public double getVolumenTotal() {
		return volumenTotal;
	}

	public void setVolumenTotal(double volumenTotal) {
		this.volumenTotal = volumenTotal;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
