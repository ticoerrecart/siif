package ar.com.siif.fachada;

import java.util.Date;
import java.util.List;

import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.GuiaForestal;

public interface IGuiaForestalFachada {

	public List<Entidad> recuperarPermicionarios();

	public void altaGuiaForestalBasica(GuiaForestal guia);

	public List<GuiaForestal> recuperarGuiasForestales();

	public GuiaForestal recuperarGuiaForestal(long idGuiaForestal);
	
	public GuiaForestal recuperarGuiaForestalPorNroGuia(int nroGuiaForestal);

	//public GuiaForestal registrarPagoBoletaDeposito(long idBoleta);
	
	public String registrarPagoBoletaDeposito(long idBoleta);	
	
	public String reemplazarBoletaDeDeposito(long idBoleta,int numero, 
											 String concepto,String area, 
											 String efectivoCheque,String fechaVencimiento);
	
	public String registrarDevolucionValeTransporte(long idVale);
	
	public String reemplazarValeTransporte(long idVale,int numeroVale,String origen,
											 String destino,String vehiculo,String marca,
											 String dominio,String producto,int nroPiezas,
											 double cantM3,String especie,String fechaVencimiento);

	public boolean existeGuiaForestal(int nroGuia);	
}
