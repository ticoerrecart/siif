package ar.com.siif.enums;

public enum TipoDeAforo {
	BASICO("Aforo Básico"),
	MAT_CAIDO_O_TRAT_SILVIC_INCOMPL("Productos de Lenga o guindo provenientes de material caído o tratamientos silviculturales incompletos"),
	PM_SILVOPASTORIL("Productos forestales de Ñire, provenientes de un PM Silvopastoril") ,
	USO_EN_EL_LUGAR("Productos de Lenga, Ñire, Guindo para uso en el lugar"),
	USO_COMERCIAL("Leña de uso comercial, de Lenga, Ñire o Guindo"), 
	ESTRUCTURA_IRREGULAR("Productos forestales de Lenga o Guindo provenientes de bosques Juveniles y bosques secundario de estructura irregular"),
	CLASIFICACION_DIAMETROS("Productos forestales de Lenga o guindo, con clasificación de diámetros");
	
	private String descripcion;
	
	TipoDeAforo(String descripcion){
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	
}
