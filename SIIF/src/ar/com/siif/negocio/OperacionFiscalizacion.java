package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@DiscriminatorValue("FISCALIZACION")
public class OperacionFiscalizacion extends Operacion {

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "fiscalizacion_fk")
	private Fiscalizacion fiscalizacion;

	public Fiscalizacion getFiscalizacion() {
		return fiscalizacion;
	}

	public void setFiscalizacion(Fiscalizacion fiscalizacion) {
		this.fiscalizacion = fiscalizacion;
	}
	
	
}
