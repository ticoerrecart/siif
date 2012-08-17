package ar.com.siif.fachada;

import java.util.Date;
import java.util.List;

import ar.com.siif.dao.GuiaForestalDAO;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;

public class GuiaForestalFachada implements IGuiaForestalFachada {

	private GuiaForestalDAO guiaForestalDAO;

	public GuiaForestalFachada() {
	}

	public GuiaForestalFachada(GuiaForestalDAO guiaForestalDAO) {

		this.guiaForestalDAO = guiaForestalDAO;
	}

	/*public List<Entidad> recuperarPermicionarios() {

		return guiaForestalDAO.recuperarPermicionarios();
	}*/

	public void altaGuiaForestalBasica(GuiaForestal guia) throws NegocioException {

		try{
			this.guiaForestalDAO.altaGuiaForestalBasica(guia);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}				
	}

	public List<GuiaForestal> recuperarGuiasForestales() throws NegocioException {

		try{
			return guiaForestalDAO.recuperarGuiasForestales();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public GuiaForestal recuperarGuiaForestal(long idGuiaForestal) throws NegocioException {

		try{
			return guiaForestalDAO.recuperarGuiaForestal(idGuiaForestal);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public GuiaForestal recuperarGuiaForestalPorNroGuia(int nroGuiaForestal) throws NegocioException{
		
		try{
			return guiaForestalDAO.recuperarGuiaForestalPorNroGuia(nroGuiaForestal);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public String registrarPagoBoletaDeposito(long idBoleta) {
		try{
			return guiaForestalDAO.registrarPagoBoletaDeposito(idBoleta);
			
		} catch (DataBaseException e) {
			return e.getMessage();
		}			
	}
	
	public String reemplazarBoletaDeDeposito(long idBoleta, int numero, String concepto,
			String area, String efectivoCheque, String fechaVencimiento) {

		try{
			return guiaForestalDAO.reemplazarBoletaDeDeposito(idBoleta,numero,concepto,
													area,efectivoCheque,fechaVencimiento);
		} catch (DataBaseException e) {
			return e.getMessage();
		}			
	}
	
	public String registrarDevolucionValeTransporte(long idVale){
		
		try{
			return guiaForestalDAO.registrarDevolucionValeTransporte(idVale);
			
		} catch (DataBaseException e) {
			return e.getMessage();
		}			
	}

	public String reemplazarValeTransporte(long idVale, int numeroVale, String origen,
			String destino, String vehiculo, String marca, String dominio, String producto,
			int nroPiezas, double cantM3, String especie, String fechaVencimiento) {
			
		try{
			return guiaForestalDAO.reemplazarValeTransporte(idVale,numeroVale,origen,
														destino,vehiculo,marca,dominio,producto,
														nroPiezas,cantM3,especie,fechaVencimiento);
		} catch (DataBaseException e) {
			return e.getMessage();
		}			
	}

	public boolean existeGuiaForestal(int nroGuia){
		return guiaForestalDAO.existeGuiaForestal(nroGuia); 
	}

}
