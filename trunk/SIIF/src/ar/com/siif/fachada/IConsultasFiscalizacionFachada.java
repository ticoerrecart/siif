package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.Fiscalizacion;

public interface IConsultasFiscalizacionFachada {

	public List<Fiscalizacion> recuperarFiscalizacionesConGuiaForestal(long idProductor);
	
	public List<Fiscalizacion> recuperarFiscalizacionesSinGuiaForestal(long idProductor);
}
