package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.negocio.GuiaForestal;

public interface IConsultasPorProductorFachada {

	public List<GuiaForestal> recuperarGuiasForestalesVigentes(long idProductor);
	
	public List<GuiaForestal> recuperarGuiasForestalesNoVigentes(long idProductor);
	
	public List<GuiaForestal> recuperarGuiasForestalesConDeudasAforo(long idProductor);
	
	public List<GuiaForestal> recuperarGuiasForestalesConDeudasVales(long idProductor);
	
	public byte[] pruebaJasper(String path);
}
