package ar.com.siif.dto;

import java.util.ArrayList;
import java.util.List;

public class GuiaForestalDTO {

	private Long id;

	private long nroGuia;

	private EntidadDTO productorForestal;

	private String periodoForestal;

	private String fechaVencimiento;

	private int distanciaAforoMovil;

	private double importeTotal;

	private double inspFiscalizacion;

	private String observaciones;

	private LocalidadDTO localidad;

	private String fecha;

	private List<ValeTransporteDTO> valesTransporte;

	private List<BoletaDepositoDTO> boletasDeposito;

	//private UsuarioDTO usuarioAlta;

	private OperacionGuiaForestalDTO operacionAlta;
	
	private OperacionGuiaForestalDTO operacionModificacion;
	
	private OperacionGuiaForestalDTO operacionAnulacion;
	
	private LocalizacionDTO localizacion;

	private List<FiscalizacionDTO> fiscalizaciones;

	private List<SubImporteDTO> subImportes;

	private boolean anulado;

	private String idLocalizacion;

	public GuiaForestalDTO() {

		fiscalizaciones = new ArrayList<FiscalizacionDTO>();
		valesTransporte = new ArrayList<ValeTransporteDTO>();
		boletasDeposito = new ArrayList<BoletaDepositoDTO>();
		//usuarioAlta = new UsuarioDTO();
		operacionAlta = new OperacionGuiaForestalDTO();
		operacionModificacion = new OperacionGuiaForestalDTO();
		operacionAnulacion = new OperacionGuiaForestalDTO();
		productorForestal = new EntidadDTO();
		subImportes = new ArrayList<SubImporteDTO>();
		localidad = new LocalidadDTO();
	}

	public long getNroGuia() {
		return nroGuia;
	}

	public void setNroGuia(long nroGuia) {
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

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
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

	/*public UsuarioDTO getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(UsuarioDTO usuario) {
		this.usuarioAlta = usuario;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<FiscalizacionDTO> getFiscalizaciones() {
		return fiscalizaciones;
	}

	public void setFiscalizaciones(List<FiscalizacionDTO> fiscalizaciones) {
		this.fiscalizaciones = fiscalizaciones;
	}

	/*	public LocalizacionDTO getLocalizacion() {
			return localizacion;
		}

		public void setLocalizacion(LocalizacionDTO localizacion) {
			this.localizacion = localizacion;
		}*/

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

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	/**
	 * me fijo en los subimportes para ver los productos que tiene la guia
	 * Los estados tambien los saco de Ahi
	 * y la relacion la saco de las fiscalizaciones... si no hay devuelvo 0
	 * 
	 */
	public List<ProductoEspecieYRelacionMtsPorPiezaDTO> getProductosEspeciesYRelacionMtsPorPieza() {

		List<ProductoEspecieYRelacionMtsPorPiezaDTO> prods = new ArrayList<ProductoEspecieYRelacionMtsPorPiezaDTO>();
		for (SubImporteDTO subImporteDTO : this.getSubImportes()) {
			ProductoEspecieYRelacionMtsPorPiezaDTO prod = new ProductoEspecieYRelacionMtsPorPiezaDTO();
			prod.setProducto(subImporteDTO.getTipoProducto().getNombre());
			prod.setEspecie(subImporteDTO.getEspecie());
			double mts3 = 0;
			double piezas = 0;
			for (FiscalizacionDTO fisc : this.getFiscalizaciones()) {
				if (fisc.getTipoProducto().getNombre().equalsIgnoreCase(prod.getProducto())) {
					mts3 = mts3 + fisc.getCantidadMts();
					piezas = piezas + fisc.getCantidadUnidades();
				}
			}
			if (mts3 > 0) {
				if (piezas > 0) {
					prod.setMts3xpieza(mts3 / piezas);
				}
			}
			prods.add(prod);
		}
		return prods;
	}

	public String getIdLocalizacion() {
		return idLocalizacion;
	}

	public void setIdLocalizacion(String idLocalizacion) {
		this.idLocalizacion = idLocalizacion;
	}

	public LocalizacionDTO getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(LocalizacionDTO localizacion) {
		this.localizacion = localizacion;
	}

	public OperacionGuiaForestalDTO getOperacionAlta() {
		return operacionAlta;
	}

	public void setOperacionAlta(OperacionGuiaForestalDTO operacionAlta) {
		this.operacionAlta = operacionAlta;
	}

	public OperacionGuiaForestalDTO getOperacionModificacion() {
		return operacionModificacion;
	}

	public void setOperacionModificacion(
			OperacionGuiaForestalDTO operacionModificacion) {
		this.operacionModificacion = operacionModificacion;
	}

	public OperacionGuiaForestalDTO getOperacionAnulacion() {
		return operacionAnulacion;
	}

	public void setOperacionAnulacion(OperacionGuiaForestalDTO operacionAnulacion) {
		this.operacionAnulacion = operacionAnulacion;
	}

}
