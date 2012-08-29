package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.NegocioException;

public interface IConsultasPorProductorFachada {

	public List<GuiaForestal> recuperarGuiasForestalesVigentes(long idProductor);
	
	public List<GuiaForestal> recuperarGuiasForestales(long idProductor);
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesNoVigentes(long idProductor)throws NegocioException;
	
	public List<GuiaForestal> recuperarGuiasForestalesConDeudasAforo(long idProductor);
	
	public List<GuiaForestal> recuperarGuiasForestalesConDeudasVales(long idProductor);
	
	public byte[] pruebaJasper(String path);
}
