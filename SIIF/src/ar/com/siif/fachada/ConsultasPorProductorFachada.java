package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dao.ConsultasPorProductorDAO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.GuiaForestal;

public class ConsultasPorProductorFachada implements IConsultasPorProductorFachada {

	private ConsultasPorProductorDAO consultasPorProductorDAO;

	public ConsultasPorProductorFachada() {
	}

	public ConsultasPorProductorFachada(ConsultasPorProductorDAO consultasDAO) {

		consultasPorProductorDAO = consultasDAO;
	}

	public List<GuiaForestal> recuperarGuiasForestalesVigentes(long idProductor) {

		return consultasPorProductorDAO.recuperarGuiasForestalesVigentes(idProductor);
	}
	
	public List<GuiaForestal> recuperarGuiasForestalesNoVigentes(long idProductor) {

		return consultasPorProductorDAO.recuperarGuiasForestalesNoVigentes(idProductor);
	}
	
	public List<GuiaForestal> recuperarGuiasForestalesConDeudasAforo(long idProductor){
		
		return consultasPorProductorDAO.recuperarGuiasForestalesConDeudasAforo(idProductor);		
	}
	
	public List<GuiaForestal> recuperarGuiasForestalesConDeudasVales(long idProductor){
		
		return consultasPorProductorDAO.recuperarGuiasForestalesConDeudasVales(idProductor);
	}
	
	public byte[] pruebaJasper(String path){
		
		return consultasPorProductorDAO.pruebaJasper(path);
		//return new byte[5];
	}
}
