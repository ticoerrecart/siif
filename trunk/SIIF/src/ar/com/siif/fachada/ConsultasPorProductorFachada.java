package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.ConsultasPorProductorDAO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;

public class ConsultasPorProductorFachada implements IConsultasPorProductorFachada {

	private ConsultasPorProductorDAO consultasPorProductorDAO;

	public ConsultasPorProductorFachada() {
	}

	public ConsultasPorProductorFachada(ConsultasPorProductorDAO consultasDAO) {

		consultasPorProductorDAO = consultasDAO;
	}

	public List<GuiaForestal> recuperarGuiasForestales(long idProductor){
		
		return consultasPorProductorDAO.recuperarGuiasForestales(idProductor);
	}
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesVigentes(long idProductor) throws NegocioException {
		
		try{
			List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
			List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesVigentes(idProductor);
			
			for (GuiaForestal guiaForestal : listaGuiasForestales) {
				listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
			}
			
			return listaGuiasForestalesDTO;
			
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}		
		
	}
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesNoVigentes(long idProductor) throws NegocioException {
		try{
			List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
			List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesNoVigentes(idProductor);
			
			for (GuiaForestal guiaForestal : listaGuiasForestales) {
				listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
			}
			
			return listaGuiasForestalesDTO;
			
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}			
	}
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasAforo(long idProductor) throws NegocioException{

		try{
			List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
			List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesConDeudasAforo(idProductor);
			
			for (GuiaForestal guiaForestal : listaGuiasForestales) {
				listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
			}
			
			return listaGuiasForestalesDTO;
			
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}		
	}
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasVales(long idProductor) throws NegocioException{

		try{
			List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
			List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesConDeudasVales(idProductor);
			
			for (GuiaForestal guiaForestal : listaGuiasForestales) {
				listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
			}
			
			return listaGuiasForestalesDTO;
			
		}catch(DataBaseException dbe){
			throw new NegocioException(dbe.getMessage());
		}		
	}
	
	public byte[] pruebaJasper(String path){
		
		return consultasPorProductorDAO.pruebaJasper(path);
		//return new byte[5];
	}
}
