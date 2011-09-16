package ar.com.siif.fachada;

import java.util.Date;
import java.util.List;

import ar.com.siif.dao.GuiaForestalDAO;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.GuiaForestal;

public class GuiaForestalFachada implements IGuiaForestalFachada {

	private GuiaForestalDAO guiaForestalDAO;

	public GuiaForestalFachada() {
	}

	public GuiaForestalFachada(GuiaForestalDAO guiaForestalDAO) {

		this.guiaForestalDAO = guiaForestalDAO;
	}

	public List<Entidad> recuperarPermicionarios() {

		return guiaForestalDAO.recuperarPermicionarios();
	}

	public void altaGuiaForestalBasica(GuiaForestal guia) {

		this.guiaForestalDAO.altaGuiaForestalBasica(guia);
	}

	public List<GuiaForestal> recuperarGuiasForestales() {

		return guiaForestalDAO.recuperarGuiasForestales();
	}

	public GuiaForestal recuperarGuiaForestal(long idGuiaForestal) {

		return guiaForestalDAO.recuperarGuiaForestal(idGuiaForestal);
	}

	public GuiaForestal recuperarGuiaForestalPorNroGuia(int nroGuiaForestal){
		
		return guiaForestalDAO.recuperarGuiaForestalPorNroGuia(nroGuiaForestal);
	}

	public String registrarPagoBoletaDeposito(long idBoleta) {

		return guiaForestalDAO.registrarPagoBoletaDeposito(idBoleta);
	}
	
	public String reemplazarBoletaDeDeposito(long idBoleta, int numero, String concepto,
			String area, String efectivoCheque, String fechaVencimiento) {

		return guiaForestalDAO.reemplazarBoletaDeDeposito(idBoleta,numero,concepto,
												area,efectivoCheque,fechaVencimiento);
	}
	
	public String registrarDevolucionValeTransporte(long idVale){
		
		return guiaForestalDAO.registrarDevolucionValeTransporte(idVale);
	}

	public String reemplazarValeTransporte(long idVale, int numeroVale, String origen,
			String destino, String vehiculo, String marca, String dominio, String producto,
			int nroPiezas, double cantM3, String especie, String fechaVencimiento) {

		return guiaForestalDAO.reemplazarValeTransporte(idVale,numeroVale,origen,
													destino,vehiculo,marca,dominio,producto,
													nroPiezas,cantM3,especie,fechaVencimiento);
	}

	public boolean existeGuiaForestal(int nroGuia){
		return guiaForestalDAO.existeGuiaForestal(nroGuia); 
	}

}
