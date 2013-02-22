package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.NegocioException;

public interface IConsultasPorProductorFachada {

	public List<GuiaForestalDTO> recuperarGuiasForestalesVigentes(long idProductor);
	
	public List<GuiaForestal> recuperarGuiasForestales(long idProductor);
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesNoVigentes(long idProductor);
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesAnuladas(long idProductor);  
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasAforo(long idProductor);
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasVales(long idProductor);
	
	public byte[] pruebaJasper(String path);
}
