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

	public List<PMF> getPMFs(Long idPF);

	public List<Tranzon> getTranzonesById(Long idPMF);

	public List<Rodal> getRodales();
	
	public List<Rodal> getRodalesById(Long idMarcacion);	

	public List<Marcacion> getMarcacionesById(Long idTranzon);
	
	public List<Marcacion> getMarcaciones();

	public List<Tranzon> getTranzones();

	public Marcacion getMarcacion(Long idMarcacion);	
	
	public void altaRodal(String nombre, Long idMarcacion) throws NegocioException;

	public void altaMarcacion(String disposicionMarcacion, Long idTranzon) throws NegocioException;

	public void altaTranzon(String numero, String disposicionTranzon, Long idPMF) throws NegocioException;

	public void altaPMF(String expediente, String nombre, Long idEntidad) throws NegocioException;

	public void deleteRodal(Long idRodal);

	public void modificarRodal(Long idRodal, String nombre);

	public void deleteMarcacion(Long idMarcacion);

	public void modificarMarcacion(Long idMarcacion, String disposicion);

	public void deleteTranzon(Long idTranzon);

	public void modificarTranzon(Long idTranzon, String numero, String disposicion);

	public void deletePMF(Long idPMF);

	public void modificarPMF(Long idPMF, String nombre, String expediente);
	
	public Rodal getRodal(Long idRodal);
	
	public List<PMFDTO> getPMFsDTO(Long idPF);
	
	public List<TranzonDTO> getTranzonesDTOById(Long idPMF);
	
	public List<MarcacionDTO> getMarcacionesDTOById(Long idTranzon);

	public List<RodalDTO> getRodalesDTOById(Long idMarcacion);
	
	public List<PMFDTO> recuperarPMFsDTO();
	
}
