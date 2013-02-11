package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.NegocioException;

public interface IConsultasPorProductorFachada {

	public List<GuiaForestalDTO> recuperarGuiasForestalesVigentes(long idProductor)throws NegocioException;
	
	public List<GuiaForestal> recuperarGuiasForestales(long idProductor);
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesNoVigentes(long idProductor)throws NegocioException;
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesAnuladas(long idProductor) throws NegocioException; 
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasAforo(long idProductor)throws NegocioException;
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasVales(long idProductor)throws NegocioException;
	
	public byte[] pruebaJasper(String path);
}
