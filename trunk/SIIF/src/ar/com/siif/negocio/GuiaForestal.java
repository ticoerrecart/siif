package ar.com.siif.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
public class GuiaForestal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private int nroGuia;

	private Date fechaVencimiento;

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

	private Date fecha;

	@OneToMany(mappedBy = "guiaForestal")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<ValeTransporte> valesTransporte = new ArrayList<ValeTransporte>();

	@OneToMany(mappedBy = "guiaForestal")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<BoletaDeposito> boletasDeposito = new ArrayList<BoletaDeposito>();

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "usuario_fk")
	private Usuario usuario;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "fiscalizacion_fk")
	private Fiscalizacion fiscalizacion;

	public int getNroGuia() {
		return nroGuia;
	}

	public void setNroGuia(int nroGuia) {
		this.nroGuia = nroGuia;
	}

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

	public List<ValeTransporte> getValesTransporte() {
		return valesTransporte;
	}

	public void setValesTransporte(List<ValeTransporte> valesTransporte) {
		this.valesTransporte = valesTransporte;
	}

	public List<BoletaDeposito> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDeposito> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getValorAforos() {
		return valorAforos;
	}

	public void setValorAforos(String valorAforos) {
		this.valorAforos = valorAforos;
	}

	public Fiscalizacion getFiscalizacion() {
		return fiscalizacion;
	}

	public void setFiscalizacion(Fiscalizacion fiscalizacion) {
		this.fiscalizacion = fiscalizacion;
	}

}
