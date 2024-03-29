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

	private String periodoForestal;

	private int distanciaAforoMovil;

	@Column(nullable = false)
	private boolean anulado;

	private double importeTotal;

	private double inspFiscalizacion;

	private String observaciones;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "localidad_fk")
	private Localidad localidad;

	private Date fecha;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "rodal_fk")
	private Rodal rodal;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "entidad_fk")
	private Entidad productorForestal;

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

	@OneToMany(mappedBy = "guiaForestal")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<Fiscalizacion> fiscalizaciones;

	@OneToMany(mappedBy = "guiaForestal")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<SubImporte> subImportes;

	public GuiaForestal() {

		boletasDeposito = new ArrayList<BoletaDeposito>();
		valesTransporte = new ArrayList<ValeTransporte>();
		fiscalizaciones = new ArrayList<Fiscalizacion>();
		subImportes = new ArrayList<SubImporte>();
	}

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

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad pLocalidad) {
		this.localidad = pLocalidad;
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

	public String getPeriodoForestal() {
		return periodoForestal;
	}

	public void setPeriodoForestal(String periodoForestal) {
		this.periodoForestal = periodoForestal;
	}

	public Entidad getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(Entidad productorForestal) {
		this.productorForestal = productorForestal;
	}

	public List<Fiscalizacion> getFiscalizaciones() {
		return fiscalizaciones;
	}

	public void setFiscalizaciones(List<Fiscalizacion> fiscalizaciones) {
		this.fiscalizaciones = fiscalizaciones;
	}

	public Rodal getRodal() {
		return rodal;
	}

	public void setRodal(Rodal rodal) {
		this.rodal = rodal;
	}

	public List<SubImporte> getSubImportes() {
		return subImportes;
	}

	public void setSubImportes(List<SubImporte> subImportes) {
		this.subImportes = subImportes;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

}
