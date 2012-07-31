package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.UbicacionDAO;
import ar.com.siif.dto.MarcacionDTO;
import ar.com.siif.dto.PMFDTO;
import ar.com.siif.dto.RodalDTO;
import ar.com.siif.dto.TranzonDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;

public class UbicacionFachada implements IUbicacionFachada {

	private UbicacionDAO ubicacionDAO;

	public UbicacionFachada() {
	}

	public UbicacionFachada(UbicacionDAO ubicacionDAO) {
		this.ubicacionDAO = ubicacionDAO;
	}

	public List<PMF> recuperarPMFs() {
		return this.ubicacionDAO.recuperarPMFs();
	}

	public List<PMF> getPMFs(Long idPF) {
		Entidad e = ubicacionDAO.getEntidad(idPF);
		List<PMF> list = e.getPmfs();
		ubicacionDAO.getHibernateTemplate().initialize(list);
		return list;
	}

	public List<Tranzon> getTranzonesById(Long idPMF) {
		PMF pmf = ubicacionDAO.getPMF(idPMF);
		List<Tranzon> list = pmf.getTranzones();
		ubicacionDAO.getHibernateTemplate().initialize(list);
		return list;
	}

	public List<Marcacion> getMarcacionesById(Long idTranzon) {
		Tranzon tranzon = ubicacionDAO.getTranzon(idTranzon);
		List<Marcacion> list = tranzon.getMarcaciones();
		ubicacionDAO.getHibernateTemplate().initialize(list);
		return list;
	}

	public List<Rodal> getRodalesById(Long idMarcacion) {
		Marcacion marcacion = ubicacionDAO.getMarcacion(idMarcacion);
		List<Rodal> list = marcacion.getRodales();
		ubicacionDAO.getHibernateTemplate().initialize(list);
		return list;
	}

	public List<Rodal> getRodales() {
		return this.ubicacionDAO.getRodales();
	}

	public List<Marcacion> getMarcaciones() {
		return this.ubicacionDAO.getMarcaciones();
	}

	public List<Tranzon> getTranzones() {
		return this.ubicacionDAO.getTranzones();
	}

	public Marcacion getMarcacion(Long idMarcacion){
		return this.ubicacionDAO.getMarcacion(idMarcacion);
	}	
	
	/**
	 * Validacion 1: No puede haber dos rodales con el mismo nombre para la misma Marcacion.
	 * @throws NegocioException 
	 */
	public void altaRodal(String nombre, Long idMarcacion) throws NegocioException {
		if (ubicacionDAO.getRodalesPorNombreParaMarcacion(nombre, idMarcacion).size() > 0 ){
			throw new NegocioException("Ya Existe un Rodal con el nombre:" + nombre);
		}
		Marcacion marcacion = ubicacionDAO.getMarcacion(idMarcacion);
		marcacion.getRodales().add(new Rodal(nombre, marcacion));
	}

	public void altaMarcacion(String disposicionMarcacion, Long idTranzon) throws NegocioException {
		if (ubicacionDAO.getMarcacionesPorDisposicionParaTranzon(disposicionMarcacion, idTranzon).size() > 0 ){
			throw new NegocioException("Ya Existe una Marcación con la disposición:" + disposicionMarcacion);
		}
		Tranzon tranzon = ubicacionDAO.getTranzon(idTranzon);
		tranzon.getMarcaciones().add(new Marcacion(disposicionMarcacion, tranzon));
	}

	public void altaTranzon(String numero, String disposicionTranzon, Long idPMF) throws NegocioException {
		if (ubicacionDAO.getTranzonesPorNumeroParaPMF(numero, idPMF).size() > 0 ){
			throw new NegocioException("Ya Existe un Tranzon con el número:" + numero);
		}
		PMF pmf = ubicacionDAO.getPMF(idPMF);
		pmf.getTranzones().add(new Tranzon(numero, disposicionTranzon, pmf));
	}

	public void altaPMF(String expediente, String nombre, Long idEntidad) throws NegocioException {
		if (ubicacionDAO.getPMFsPorNombreParaPF(nombre, idEntidad) .size() > 0 ){
			throw new NegocioException("Ya Existe un Plan de Manejo Forestal con el nombre:" + nombre);
		}
		Entidad e = ubicacionDAO.getEntidad(idEntidad);
		e.getPmfs().add(new PMF(expediente, nombre, e));
	}

	public void deleteRodal(Long idRodal) {
		this.ubicacionDAO.deleteRodal(idRodal);
	}

	public void modificarRodal(Long idRodal, String nombre) {
		Rodal r = this.ubicacionDAO.getRodal(idRodal);
		r.setNombre(nombre);
	}

	public void deleteMarcacion(Long idMarcacion) {
		this.ubicacionDAO.deleteMarcacion(idMarcacion);
	}

	public void modificarMarcacion(Long idMarcacion, String disposicion) {
		Marcacion m = this.ubicacionDAO.getMarcacion(idMarcacion);
		m.setDisposicion(disposicion);
	}

	public void deleteTranzon(Long idTranzon) {
		this.ubicacionDAO.deleteTranzon(idTranzon);
	}

	public void modificarTranzon(Long idTranzon, String numero, String disposicion) {
		Tranzon t = this.ubicacionDAO.getTranzon(idTranzon);
		t.setDisposicion(disposicion);
		t.setNumero(numero);
	}

	public void deletePMF(Long idPMF) {
		this.ubicacionDAO.deletePMF(idPMF);
	}

	public void modificarPMF(Long idPMF, String nombre, String expediente) {
		PMF pmf = this.ubicacionDAO.getPMF(idPMF);
		pmf.setExpediente(expediente);
		pmf.setNombre(nombre);
	}

	public Rodal getRodal(Long idRodal){
		return this.ubicacionDAO.getRodal(idRodal);
	}	
	
	public List<PMFDTO> getPMFsDTO(Long idPF){
		
		Entidad e = ubicacionDAO.getEntidad(idPF);
		List<PMF> list = e.getPmfs();
		ubicacionDAO.getHibernateTemplate().initialize(list);		
		
		List<PMFDTO> pmfListDTO = new ArrayList<PMFDTO>();
		for (PMF pmf : list) {
			pmfListDTO.add(ProviderDTO.getPMFDTO(pmf));
		}
		return pmfListDTO;
	}	
	
	public List<TranzonDTO> getTranzonesDTOById(Long idPMF){
		
		PMF pmf = ubicacionDAO.getPMF(idPMF);
		List<Tranzon> list = pmf.getTranzones();
		ubicacionDAO.getHibernateTemplate().initialize(list);
		
		List<TranzonDTO> tranzonesDTO = new ArrayList<TranzonDTO>();
		for (Tranzon tranzon : list) {
			tranzonesDTO.add(ProviderDTO.getTranzonDTO(tranzon));
		}
		return tranzonesDTO;		
	}
	
	public List<MarcacionDTO> getMarcacionesDTOById(Long idTranzon){
		
		Tranzon tranzon = ubicacionDAO.getTranzon(idTranzon);
		List<Marcacion> list = tranzon.getMarcaciones();
		ubicacionDAO.getHibernateTemplate().initialize(list);		
		
		List<MarcacionDTO> marcacionesDTO = new ArrayList<MarcacionDTO>();
		for (Marcacion marcacion : list) {
			marcacionesDTO.add(ProviderDTO.getMarcacionDTO(marcacion));
		}
		return marcacionesDTO;
	}
	
	public List<RodalDTO> getRodalesDTOById(Long idMarcacion){
		
		Marcacion marcacion = ubicacionDAO.getMarcacion(idMarcacion);
		List<Rodal> list = marcacion.getRodales();
		ubicacionDAO.getHibernateTemplate().initialize(list);
		
		List<RodalDTO> rodalesDTO = new ArrayList<RodalDTO>();
		for (Rodal rodal : list) {
			rodalesDTO.add(ProviderDTO.getRodalDTO(rodal));
		}
		
		return rodalesDTO;
	}	
}
