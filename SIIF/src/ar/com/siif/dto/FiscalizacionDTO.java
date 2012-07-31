package ar.com.siif.dto;

import java.util.List;

public class FiscalizacionDTO {

	private Long id;

	private String periodoForestal;

	private String fecha;

	private int cantidadUnidades;

	private double cantidadMts;

	private int tamanioMuestra;
	
	private long idProductorForestal;

	private long idTipoProductoForestal;

	private long idPlanManejoForestal;
	
	private long idTranzon;
	
	private long idMarcacion;
	
	private long idRodal;

	private long idOficinaForestal;	
	
	private long idUsuario;
	
	private List<MuestraDTO> muestra;

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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
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

	public int getTamanioMuestra() {
		return tamanioMuestra;
	}

	public void setTamanioMuestra(int tamanioMuestra) {
		this.tamanioMuestra = tamanioMuestra;
	}

	public long getIdProductorForestal() {
		return idProductorForestal;
	}

	public void setIdProductorForestal(long idProductorForestal) {
		this.idProductorForestal = idProductorForestal;
	}

	public long getIdTipoProductoForestal() {
		return idTipoProductoForestal;
	}

	public void setIdTipoProductoForestal(long idTipoProductoForestal) {
		this.idTipoProductoForestal = idTipoProductoForestal;
	}

	public long getIdPlanManejoForestal() {
		return idPlanManejoForestal;
	}

	public void setIdPlanManejoForestal(long idPlanManejoForestal) {
		this.idPlanManejoForestal = idPlanManejoForestal;
	}

	public long getIdTranzon() {
		return idTranzon;
	}

	public void setIdTranzon(long idTranzon) {
		this.idTranzon = idTranzon;
	}

	public long getIdMarcacion() {
		return idMarcacion;
	}

	public void setIdMarcacion(long idMarcacion) {
		this.idMarcacion = idMarcacion;
	}

	public long getIdRodal() {
		return idRodal;
	}

	public void setIdRodal(long idRodal) {
		this.idRodal = idRodal;
	}

	public long getIdOficinaForestal() {
		return idOficinaForestal;
	}

	public void setIdOficinaForestal(long idOficinaForestal) {
		this.idOficinaForestal = idOficinaForestal;
	}

	public List<MuestraDTO> getMuestra() {
		return muestra;
	}

	public void setMuestra(List<MuestraDTO> muestra) {
		this.muestra = muestra;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
