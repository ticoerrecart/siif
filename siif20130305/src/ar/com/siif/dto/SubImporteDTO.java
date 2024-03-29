package ar.com.siif.dto;

public class SubImporteDTO implements Comparable<SubImporteDTO>{

	private Long id;	
	
	private String estado;
	
	private String especie;
	
	private double valorAforos;
	
	private int cantidadUnidades;
	
	private double cantidadMts;
	
	private double importe;
	
	private TipoProductoDTO tipoProducto;
	
	private GuiaForestalDTO guiaForestal;

	public SubImporteDTO(){
		
		tipoProducto = new TipoProductoDTO();
		guiaForestal = new GuiaForestalDTO();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getValorAforos() {
		return valorAforos;
	}

	public void setValorAforos(double valorAforos) {
		this.valorAforos = valorAforos;
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

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public TipoProductoDTO getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoDTO tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public GuiaForestalDTO getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestalDTO guiaForestal) {
		this.guiaForestal = guiaForestal;
	}
	
	public int compareTo(SubImporteDTO o) {
		
		if(this.getTipoProducto().getId().longValue() == o.getTipoProducto().getId().longValue() && 
				this.getEstado().equals(o.getEstado())){
				
				return 0;
			}
			return 1;
	}
}
