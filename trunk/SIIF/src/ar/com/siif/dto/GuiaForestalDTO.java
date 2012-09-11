package ar.com.siif.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Usuario;

public class GuiaForestalDTO {

	private Long id;
	
	private int nroGuia;

	private EntidadDTO productorForestal;
	
	private String periodoForestal;	
		
	private String fechaVencimiento;

	private int distanciaAforoMovil;

	/*private String estado;

	private String especie;

	private int cantidad;

	private int cantidadUnidades;

	private double cantidadMts;		

	private double aforo;

	private String valorAforos;
	
	private TipoProductoDTO tipoProducto;*/	
	
	private double importeTotal;	
	
	private double inspFiscalizacion;

	private String observaciones;

	private String localidad;

	private String fecha;

	private List<ValeTransporteDTO> valesTransporte;

	private List<BoletaDepositoDTO> boletasDeposito;

	private UsuarioDTO usuario;

	private RodalDTO rodal;
	
	private List<FiscalizacionDTO> fiscalizaciones;	
	
	private List<SubImporteDTO> subImportes;
	
	public GuiaForestalDTO() {

		fiscalizaciones = new ArrayList<FiscalizacionDTO>();
		valesTransporte = new ArrayList<ValeTransporteDTO>();
		boletasDeposito = new ArrayList<BoletaDepositoDTO>();
		usuario = new UsuarioDTO();
		productorForestal = new EntidadDTO();
		rodal = new RodalDTO();
		subImportes = new ArrayList<SubImporteDTO>();
	}

	public int getNroGuia() {
		return nroGuia;
	}

	public void setNroGuia(int nroGuia) {
		this.nroGuia = nroGuia;
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

	/*public String getEstado() {
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

	public String getValorAforos() {
		return valorAforos;
	}

	public void setValorAforos(String valorAforos) {
		this.valorAforos = valorAforos;
	}*/

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

	/*public int getCantidadUnidades() {
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
	}*/

	public EntidadDTO getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(EntidadDTO productorForestal) {
		this.productorForestal = productorForestal;
	}

	public String getPeriodoForestal() {
		return periodoForestal;
	}

	public void setPeriodoForestal(String periodoForestal) {
		this.periodoForestal = periodoForestal;
	}

	/*public TipoProductoDTO getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoDTO tipoProducto) {
		this.tipoProducto = tipoProducto;
	}*/

	public List<FiscalizacionDTO> getFiscalizaciones() {
		return fiscalizaciones;
	}

	public void setFiscalizaciones(List<FiscalizacionDTO> fiscalizaciones) {
		this.fiscalizaciones = fiscalizaciones;
	}

	public RodalDTO getRodal() {
		return rodal;
	}

	public void setRodal(RodalDTO rodal) {
		this.rodal = rodal;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public List<SubImporteDTO> getSubImportes() {
		return subImportes;
	}

	public void setSubImportes(List<SubImporteDTO> subImportes) {
		this.subImportes = subImportes;
	}

}
