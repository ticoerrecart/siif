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

import ar.com.siif.enums.TipoOperacion;

@Entity
public class Fiscalizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String periodoForestal;

	private Date fecha;

	private int cantidadUnidades;

	private double cantidadMts;

	private int tamanioMuestra;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "entidad_fk")
	private Entidad productorForestal;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "guiaForestal_fk")
	private GuiaForestal guiaForestal;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "localizacion_fk")
	private Localizacion localizacion;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "tipoProducto_fk")
	private TipoProductoForestal tipoProducto;

	@OneToMany(mappedBy = "fiscalizacion")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<Muestra> muestra = new ArrayList<Muestra>();

	@OneToMany(mappedBy = "fiscalizacion")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<OperacionFiscalizacion> operaciones;	
	
	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "oficina_fk")
	private Entidad oficinaAlta;

	public String getPeriodoForestal() {
		return periodoForestal;
	}

	public void setPeriodoForestal(String periodoForestal) {
		this.periodoForestal = periodoForestal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidadUnidades() {
		return cantidadUnidades;
	}

	public void setCantidadUnidades(int cantidadUnidades) {
		this.cantidadUnidades = cantidadUnidades;
	}

	public double getCantidadMts() {
		return cantidadMts;
	}

	public void setCantidadMts(double cantidadMts) {
		this.cantidadMts = cantidadMts;
	}

	public Entidad getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(Entidad productorForestal) {
		this.productorForestal = productorForestal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GuiaForestal getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestal guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

	public int getTamanioMuestra() {
		return tamanioMuestra;
	}

	public void setTamanioMuestra(int tamanioMuestra) {
		this.tamanioMuestra = tamanioMuestra;
	}

	public TipoProductoForestal getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoForestal tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public List<Muestra> getMuestra() {
		return muestra;
	}

	public void setMuestra(List<Muestra> muestra) {
		this.muestra = muestra;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	/*public Usuario getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(Usuario usuario) {
		this.usuarioAlta = usuarioAlta;
	}*/

	public Entidad getOficinaAlta() {
		return oficinaAlta;
	}

	public void setOficinaAlta(Entidad oficinaAlta) {
		this.oficinaAlta = oficinaAlta;
	}

	/*public Usuario getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(Usuario usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}*/

	public OperacionFiscalizacion getOperacionAlta() {
		for (OperacionFiscalizacion operacion : this.getOperaciones()) {
			if (operacion.getTipoOperacion().equals(TipoOperacion.ALTA.getDescripcion())){
				return operacion;
			}
		}
		return null;
	}

	public List<OperacionFiscalizacion> getOperacionesModificacion() {
		List<OperacionFiscalizacion> operacionesModificacion = new ArrayList<OperacionFiscalizacion>();
		for (OperacionFiscalizacion operacion : this.getOperaciones()) {
			if (operacion.getTipoOperacion().equals(TipoOperacion.MOD.getDescripcion())){
				operacionesModificacion.add(operacion);
			}
		}
		return operacionesModificacion;
	}

	public void addOperacion(OperacionFiscalizacion operacionFiscalizacion) {
		if (this.operaciones == null) {
			setOperaciones (new ArrayList<OperacionFiscalizacion>());
		}
		this.operaciones.add(operacionFiscalizacion);
	}

	public List<OperacionFiscalizacion> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<OperacionFiscalizacion> operaciones) {
		this.operaciones = operaciones;
	}
	
}
