package ar.com.siif.negocio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siif.utils.Fecha;

@Entity
public class ValeTransporte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Long numero;

	//private Date fecha;
	
	private Date fechaDevolucion;	

	private String origen;

	private String destino;

	private String vehiculo;

	private String marca;

	private String dominio;

	@Column(nullable = false)
	private Date fechaVencimiento;

	private String producto;

	private int nroPiezas;

	private double cantidadMts;

	private String especie;
	
	@Column(nullable = false)
	private boolean anulado;	
	
	@Transient
	private String fechaVencimientoTransient;

	/*@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "id_Entidad")	
	private Entidad empresa;*/

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "guiaForestal_fk")
	private GuiaForestal guiaForestal;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	/*public Date getFecha() {
		return fecha;
	}*/

	/*public void setFecha(Date fecha) {
		this.fecha = fecha;
	}*/

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getNroPiezas() {
		return nroPiezas;
	}

	public void setNroPiezas(int nroPiezas) {
		this.nroPiezas = nroPiezas;
	}

	public double getCantidadMts() {
		return cantidadMts;
	}

	public void setCantidadMts(double cantidadMts) {
		this.cantidadMts = cantidadMts;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/*public Entidad getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Entidad empresa) {
		this.empresa = empresa;
	}*/
	public GuiaForestal getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestal guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getFechaVencimientoTransient() {
		return fechaVencimientoTransient;
	}

	public void setFechaVencimientoTransient(String fechaVencimientoTransient) {
		this.fechaVencimientoTransient = fechaVencimientoTransient;
		
		if (this.fechaVencimientoTransient != null && this.fechaVencimientoTransient != "") {
			this.setFechaVencimiento(Fecha.stringDDMMAAAAToDate(this.fechaVencimientoTransient));
		}				
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

}
