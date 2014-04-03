package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siif.enums.TipoDeProducto;

@Entity
@DiscriminatorValue("FST")
public class TipoProductoForestal extends TipoProducto {

	private int cantDiametros;
	
	private double largoDesde;
	
	private double largoHasta;
	
	private double diam1Desde;
	
	private double diam1Hasta;
	
	private double diam2Desde;
	
	private double diam2Hasta;	
	
	private boolean esDeExportacion;
	
	private boolean habilitado;
	
	public String getTipoEntidad() {
		return TipoDeProducto.FST.getDescripcion();
	}

	public String getIdTipoEntidad() {
		return TipoDeProducto.FST.getName();
	}

	public int getCantDiametros() {
		return cantDiametros;
	}

	public void setCantDiametros(int cantDiametros) {
		this.cantDiametros = cantDiametros;
	}

	public double getLargoDesde() {
		return largoDesde;
	}

	public void setLargoDesde(double largoDesde) {
		this.largoDesde = largoDesde;
	}

	public double getLargoHasta() {
		return largoHasta;
	}

	public void setLargoHasta(double largoHasta) {
		this.largoHasta = largoHasta;
	}

	public double getDiam1Desde() {
		return diam1Desde;
	}

	public void setDiam1Desde(double diam1Desde) {
		this.diam1Desde = diam1Desde;
	}

	public double getDiam1Hasta() {
		return diam1Hasta;
	}

	public void setDiam1Hasta(double diam1Hasta) {
		this.diam1Hasta = diam1Hasta;
	}

	public double getDiam2Desde() {
		return diam2Desde;
	}

	public void setDiam2Desde(double diam2Desde) {
		this.diam2Desde = diam2Desde;
	}

	public double getDiam2Hasta() {
		return diam2Hasta;
	}

	public void setDiam2Hasta(double diam2Hasta) {
		this.diam2Hasta = diam2Hasta;
	}

	public boolean isEsDeExportacion() {
		return esDeExportacion;
	}

	public void setEsDeExportacion(boolean esDeExportacion) {
		this.esDeExportacion = esDeExportacion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}	
}
