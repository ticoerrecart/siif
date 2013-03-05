package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.MarcacionDTO;
import ar.com.siif.dto.PMFDTO;
import ar.com.siif.dto.RodalDTO;
import ar.com.siif.dto.TranzonDTO;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.negocio.exception.NegocioException;

public interface IUbicacionFachada {

	public List<PMF> recuperarPMFs();

	public List<PMF> getPMFs(Long idPF)throws NegocioException;

	public List<Tranzon> getTranzonesById(Long idPMF) throws NegocioException;

	public List<Rodal> getRodales();
	
	public List<Rodal> getRodalesById(Long idMarcacion) throws NegocioException;	

	public List<Marcacion> getMarcacionesById(Long idTranzon) throws NegocioException;
	
	public List<Marcacion> getMarcaciones();

	public List<Tranzon> getTranzones();

	public Marcacion getMarcacion(Long idMarcacion);	
	
	public void altaRodal(String nombre, Long idMarcacion) throws NegocioException;

	public void altaMarcacion(String disposicionMarcacion, Long idTranzon) throws NegocioException;

	public void altaTranzon(String numero, String disposicionTranzon, Long idPMF) throws NegocioException;

	public void altaPMF(String expediente, String nombre, String tipoTerreno, Long idEntidad) throws NegocioException;

	public void deleteRodal(Long idRodal) throws NegocioException;

	public void modificarRodal(Long idRodal, String nombre)throws NegocioException;

	public void deleteMarcacion(Long idMarcacion) throws NegocioException;

	public void modificarMarcacion(Long idMarcacion, String disposicion) throws NegocioException;

	public void deleteTranzon(Long idTranzon) throws NegocioException;

	public void modificarTranzon(Long idTranzon, String numero, String disposicion) throws NegocioException;

	public void deletePMF(Long idPMF);

	public void modificarPMF(Long idPMF, String nombre, String expediente) throws NegocioException;
	
	public Rodal getRodal(Long idRodal);
	
	public RodalDTO getRodalDTO(Long idRodal);
	
	public List<PMFDTO> getPMFsDTO(Long idPF) throws NegocioException;
	
	public List<TranzonDTO> getTranzonesDTOById(Long idPMF) throws NegocioException;
	
	public List<MarcacionDTO> getMarcacionesDTOById(Long idTranzon) throws NegocioException;

	public List<RodalDTO> getRodalesDTOById(Long idMarcacion) throws NegocioException;
	
	public List<PMFDTO> recuperarPMFsDTO();
	
	public List<PMFDTO> getPMFsDTOPorProductor(Long idProductor);	
	
	public List<TranzonDTO> getTranzonesDTOPorProductor(Long idProductor);
	
	public List<MarcacionDTO> getMarcacionesDTOPorProductor(Long idProductor);
	
	public List<RodalDTO> getRodalesDTOPorProductor(Long idProductor);	
	
	public String getTipoTerrenoPMF(Long idPMF)throws NegocioException;
	
	public PMF getPMF(Long idPF);
}
