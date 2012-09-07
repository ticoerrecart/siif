package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.NegocioException;

public interface IGuiaForestalFachada {

	public void altaGuiaForestalBasica(GuiaForestalDTO guia,
									   List<BoletaDepositoDTO> listaBoletaDepositoDTO,
									   List<ValeTransporteDTO> listaValeTransporteDTO,
									   List<FiscalizacionDTO> listaFiscalizacionesDTO)
									   throws NegocioException;

	public List<GuiaForestal> recuperarGuiasForestales()throws NegocioException;

	public GuiaForestalDTO recuperarGuiaForestal(long idGuiaForestal)throws NegocioException;
	
	public GuiaForestalDTO recuperarGuiaForestalPorNroGuia(int nroGuiaForestal)throws NegocioException;
	
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
