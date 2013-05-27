package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.NegocioException;

public interface IConsultasPorProductorFachada {

	public List<GuiaForestalDTO> recuperarGuiasForestalesVigentes(long idProductor, String idPeriodo);
	
	public List<GuiaForestal> recuperarGuiasForestales(long idProductor);
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesNoVigentes(long idProductor, String idPeriodo);
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesAnuladas(long idProductor, String idPeriodo);  
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasAforo(long idProductor, String idPeriodo);
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasVales(long idProductor, String idPeriodo);
	
	public byte[] pruebaJasper(String path);
}
