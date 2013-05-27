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
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesVigentes(long idProductor, String idPeriodo){

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesVigentes(idProductor,idPeriodo);
		
		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
		}
		
		return listaGuiasForestalesDTO;
	}
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesNoVigentes(long idProductor, String idPeriodo){

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesNoVigentes(idProductor,idPeriodo);
		
		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
		}
		
		return listaGuiasForestalesDTO;
	}
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesAnuladas(long idProductor, String idPeriodo){

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesAnuladas(idProductor, idPeriodo);
		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
		}
		return listaGuiasForestalesDTO;
	}
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasAforo(long idProductor, String idPeriodo){

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesConDeudasAforo(idProductor,idPeriodo);
		
		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
		}
		
		return listaGuiasForestalesDTO;
	}
	
	public List<GuiaForestalDTO> recuperarGuiasForestalesConDeudasVales(long idProductor, String idPeriodo){

		List<GuiaForestalDTO> listaGuiasForestalesDTO = new ArrayList<GuiaForestalDTO>();
		List<GuiaForestal> listaGuiasForestales = consultasPorProductorDAO.recuperarGuiasForestalesConDeudasVales(idProductor, idPeriodo);
		
		for (GuiaForestal guiaForestal : listaGuiasForestales) {
			listaGuiasForestalesDTO.add(ProviderDTO.getGuiaForestalDTO(guiaForestal));
		}
		
		return listaGuiasForestalesDTO;		
	}
	
	public byte[] pruebaJasper(String path){
		
		return consultasPorProductorDAO.pruebaJasper(path);
		//return new byte[5];
	}
}
