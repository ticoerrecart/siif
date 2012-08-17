package ar.com.siif.fachada;

import java.util.List;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.NegocioException;

public interface IGuiaForestalFachada {

	//public List<Entidad> recuperarPermicionarios()throws NegocioException;

	public void altaGuiaForestalBasica(GuiaForestal guia)throws NegocioException;

	public List<GuiaForestal> recuperarGuiasForestales()throws NegocioException;

	public GuiaForestal recuperarGuiaForestal(long idGuiaForestal)throws NegocioException;
	
	public GuiaForestal recuperarGuiaForestalPorNroGuia(int nroGuiaForestal)throws NegocioException;

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
