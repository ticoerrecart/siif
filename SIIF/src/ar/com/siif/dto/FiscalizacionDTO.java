package ar.com.siif.dto;

import java.util.List;

public class FiscalizacionDTO {

	private Long id;

	private String periodoForestal;

	private String fecha;

	private int cantidadUnidades;

	private double cantidadMts;

	private int tamanioMuestra;

	// private long idProductorForestal;
	private EntidadDTO productorForestal;

	// private long idTipoProductoForestal;
	private TipoProductoForestalDTO tipoProducto;

	private long idArea;

	private long idPlanManejoForestal;

	private long idRodal;

	private long idTranzon;

	private long idMarcacion;

	private long idLocalizacion;

	// private long idOficinaForestal;
	private EntidadDTO oficinaAlta;

	// private long idUsuario;
	private UsuarioDTO usuario;

	private List<MuestraDTO> muestra;

	private GuiaForestalDTO guiaForestal;

	private LocalizacionDTO localizacion;

	public FiscalizacionDTO() {

		productorForestal = new EntidadDTO();
		tipoProducto = new TipoProductoForestalDTO();
		oficinaAlta = new EntidadDTO();
		usuario = new UsuarioDTO();
	}

	public Long getLocalizacionId() {
		if (idRodal != -1)
			return idRodal;
		if (idMarcacion != -1)
			return idMarcacion;
		if (idTranzon != -1)
			return idTranzon;
		if (idPlanManejoForestal != -1)
			return idPlanManejoForestal;
		if (idArea != -1)
			return idArea;
		return null;
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

	public List<MuestraDTO> getMuestra() {
		return muestra;
	}

	public void setMuestra(List<MuestraDTO> muestra) {
		this.muestra = muestra;
	}

	public EntidadDTO getProductorForestal() {
		return productorForestal;
	}

	public void setProductorForestal(EntidadDTO productorForestal) {
		this.productorForestal = productorForestal;
	}

	public TipoProductoForestalDTO getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoForestalDTO tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public EntidadDTO getOficinaAlta() {
		return oficinaAlta;
	}

	public void setOficinaAlta(EntidadDTO oficinaAlta) {
		this.oficinaAlta = oficinaAlta;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public GuiaForestalDTO getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestalDTO guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public long getIdRodal() {
		return idRodal;
	}

	public void setIdRodal(long idRodal) {
		this.idRodal = idRodal;
	}

	public long getIdLocalizacion() {
		return idLocalizacion;
	}

	public void setIdLocalizacion(long idLocalizacion) {
		this.idLocalizacion = idLocalizacion;
	}

	public LocalizacionDTO getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(LocalizacionDTO localizacion) {
		this.localizacion = localizacion;
	}

}
