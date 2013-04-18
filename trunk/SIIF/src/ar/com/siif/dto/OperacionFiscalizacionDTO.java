package ar.com.siif.dto;

public class OperacionFiscalizacionDTO extends OperacionDTO {

	private FiscalizacionDTO fiscalizacion;
	
	public OperacionFiscalizacionDTO(){
				
	}

	public FiscalizacionDTO getFiscalizacion() {
		return fiscalizacion;
	}

	public void setFiscalizacion(FiscalizacionDTO fiscalizacion) {
		this.fiscalizacion = fiscalizacion;
	}

}
