package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.UbicacionDAO;
import ar.com.siif.dto.AreaDeCosechaDTO;
import ar.com.siif.dto.MarcacionDTO;
import ar.com.siif.dto.PMFDTO;
import ar.com.siif.dto.RodalDTO;
import ar.com.siif.dto.TranzonDTO;
import ar.com.siif.negocio.AreaDeCosecha;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MyLogger;

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

	public List<PMF> getPMFs(Long idPF) throws NegocioException {
		try {
			List<PMF> list = ubicacionDAO.getPMFs(idPF);
			return list;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<Tranzon> getTranzonesById(Long idPMF) throws NegocioException {
		try {
			PMF pmf = ubicacionDAO.getPMF(idPMF);
			if (pmf == null){
				return null;
			}	
			List<Tranzon> list = pmf.getTranzones();
			ubicacionDAO.getHibernateTemplate().initialize(list);
			return list;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<Marcacion> getMarcacionesById(Long idTranzon) throws NegocioException {
		try {
			Tranzon tranzon = ubicacionDAO.getTranzon(idTranzon);
			if (tranzon == null){
				return null;
			}
			List<Marcacion> list = tranzon.getMarcaciones();
			ubicacionDAO.getHibernateTemplate().initialize(list);
			return list;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<Rodal> getRodalesById(Long idMarcacion) throws NegocioException {
		try {
			Marcacion marcacion = ubicacionDAO.getMarcacion(idMarcacion);
			if (marcacion == null){
				return null;
			}
			List<Rodal> list = marcacion.getRodales();
			ubicacionDAO.getHibernateTemplate().initialize(list);
			return list;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
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

	public Marcacion getMarcacion(Long idMarcacion) {
		return this.ubicacionDAO.getMarcacion(idMarcacion);
	}

	/**
	 * Validacion 1: No puede haber dos rodales con el mismo nombre para la
	 * misma Marcacion.
	 * 
	 * @throws NegocioException
	 */
	public void altaRodal(String nombre, Long idMarcacion) throws NegocioException {
		try {
			if (ubicacionDAO.getRodalesPorNombreParaMarcacion(nombre, idMarcacion).size() > 0) {
				throw new NegocioException(Constantes.ERROR_EXISTE_RODAL + nombre);
			}
			Marcacion marcacion = ubicacionDAO.getMarcacion(idMarcacion);
			ubicacionDAO.altaRodal(new Rodal(nombre, marcacion));

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void altaMarcacion(String disposicionMarcacion, Long idTranzon) throws NegocioException {
		try {
			if (ubicacionDAO.getMarcacionesPorDisposicionParaTranzon(disposicionMarcacion, idTranzon).size() > 0) {
				throw new NegocioException(Constantes.ERROR_EXISTE_MARCACION + disposicionMarcacion);
			}
			Tranzon tranzon = ubicacionDAO.getTranzon(idTranzon);
			// tranzon.getMarcaciones().add(new Marcacion(disposicionMarcacion,
			// tranzon));
			ubicacionDAO.altaMarcacion(new Marcacion(disposicionMarcacion, tranzon));

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void altaTranzon(String numero, String disposicionTranzon, Long idPMF) throws NegocioException {
		try {
			if (ubicacionDAO.getTranzonesPorNumeroParaPMF(numero, idPMF).size() > 0) {
				throw new NegocioException(Constantes.ERROR_EXISTE_TRANZON + numero);
			}
			PMF pmf = ubicacionDAO.getPMF(idPMF);
			ubicacionDAO.altaTranzon(new Tranzon(numero, disposicionTranzon, pmf));

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void altaPMF(String expediente, String nombre, String tipoTerreno, Long idEntidad) throws NegocioException {
		try {
			if (ubicacionDAO.getPMFsPorNombreParaPF(nombre, idEntidad).size() > 0) {
				throw new NegocioException(Constantes.ERROR_EXISTE_PMF + nombre);
			}
			Entidad entidad = ubicacionDAO.getEntidad(idEntidad);
			ubicacionDAO.altaPMF(new PMF(expediente, nombre, tipoTerreno, entidad));

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void altaArea(String reservaForestal, String nombreArea, String disposicionArea, String expedienteArea, Long idEntidad)
			throws NegocioException {
		try {
			if (ubicacionDAO.getAreasPorNombreParaPF(nombreArea, idEntidad).size() > 0) {
				throw new NegocioException(Constantes.ERROR_EXISTE_AREA + nombreArea);
			}
			Entidad entidad = ubicacionDAO.getEntidad(idEntidad);
			// entidad.getPmfs().add(new PMF(expediente, nombre, e));
			ubicacionDAO.altaArea(new AreaDeCosecha(reservaForestal, nombreArea, disposicionArea, expedienteArea, entidad));

		} catch (NegocioException ne) {
			throw ne;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void deleteRodal(Long idRodal) throws NegocioException {
		try {
			this.ubicacionDAO.deleteRodal(idRodal);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void modificarRodal(Long idRodal, String nombre) throws NegocioException {
		try {
			Rodal r = this.ubicacionDAO.getRodal(idRodal);
			r.setNombreRodal(nombre);
			this.ubicacionDAO.altaRodal(r);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void deleteMarcacion(Long idMarcacion) throws NegocioException {
		try {
			this.ubicacionDAO.deleteMarcacion(idMarcacion);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void modificarMarcacion(Long idMarcacion, String disposicion) throws NegocioException {
		try {
			Marcacion m = this.ubicacionDAO.getMarcacion(idMarcacion);
			m.setDisposicionMarcacion(disposicion);
			this.ubicacionDAO.altaMarcacion(m);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void deleteTranzon(Long idTranzon) throws NegocioException {
		try {
			this.ubicacionDAO.deleteTranzon(idTranzon);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void modificarTranzon(Long idTranzon, String numero, String disposicion) throws NegocioException {
		try {
			Tranzon t = this.ubicacionDAO.getTranzon(idTranzon);
			t.setDisposicionTranzon(disposicion);
			t.setNumeroTranzon(numero);

			this.ubicacionDAO.altaTranzon(t);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void deletePMF(Long idPMF) {
		this.ubicacionDAO.deletePMF(idPMF);
	}

	public void deleteArea(Long idArea) throws NegocioException {
		try {
			this.ubicacionDAO.deleteArea(idArea);
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void modificarPMF(Long idPMF, String nombre, String expediente) throws NegocioException {
		try {
			PMF pmf = this.ubicacionDAO.getPMF(idPMF);
			pmf.setExpedientePMF(expediente);
			pmf.setNombrePMF(nombre);

			this.ubicacionDAO.altaPMF(pmf);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public void modificarArea(Long idArea, String reservaForestal, String nombreArea, String disposicionArea, String expedienteArea)
			throws NegocioException {
		try {
			AreaDeCosecha area = this.ubicacionDAO.getArea(idArea);
			area.setReservaForestalArea(reservaForestal);
			area.setNombreArea(nombreArea);
			area.setDisposicionArea(disposicionArea);
			area.setExpedienteArea(expedienteArea);
			this.ubicacionDAO.altaArea(area);

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public Rodal getRodal(Long idRodal) {
		return this.ubicacionDAO.getRodal(idRodal);
	}

	public RodalDTO getRodalDTO(Long idRodal) {
		Rodal rodal = this.ubicacionDAO.getRodal(idRodal);
		return rodal.getLocalizacionDTO();
	}

	public List<PMFDTO> getPMFsDTO(Long idPF) throws NegocioException {
		try {
			List<PMF> list = ubicacionDAO.getPMFs(idPF);

			List<PMFDTO> pmfListDTO = new ArrayList<PMFDTO>();
			for (PMF pmf : list) {
				pmfListDTO.add(pmf.getLocalizacionDTO());
			}
			return pmfListDTO;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<AreaDeCosechaDTO> getAreasDTO(Long idPF) throws NegocioException {
		try {
			List<AreaDeCosecha> areas = ubicacionDAO.getAreas(idPF);
			List<AreaDeCosechaDTO> areaListDTO = new ArrayList<AreaDeCosechaDTO>();
			for (AreaDeCosecha area : areas) {
				areaListDTO.add(area.getLocalizacionDTO());
			}
			return areaListDTO;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<TranzonDTO> getTranzonesDTOById(Long idPMF) throws NegocioException {
		try {
			PMF pmf = ubicacionDAO.getPMF(idPMF);
			if (pmf == null){
				return null;
			}
			List<Tranzon> list = pmf.getTranzones();
			ubicacionDAO.getHibernateTemplate().initialize(list);

			List<TranzonDTO> tranzonesDTO = new ArrayList<TranzonDTO>();
			for (Tranzon tranzon : list) {
				tranzonesDTO.add(tranzon.getLocalizacionDTO());
			}
			return tranzonesDTO;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<MarcacionDTO> getMarcacionesDTOById(Long idTranzon) throws NegocioException {
		try {
			Tranzon tranzon = ubicacionDAO.getTranzon(idTranzon);
			if (tranzon == null){
				return null;
			}
			List<Marcacion> list = tranzon.getMarcaciones();
			ubicacionDAO.getHibernateTemplate().initialize(list);

			List<MarcacionDTO> marcacionesDTO = new ArrayList<MarcacionDTO>();
			for (Marcacion marcacion : list) {
				marcacionesDTO.add(marcacion.getLocalizacionDTO());
			}
			return marcacionesDTO;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<RodalDTO> getRodalesDTOById(Long idMarcacion) throws NegocioException {
		try {
			Marcacion marcacion = ubicacionDAO.getMarcacion(idMarcacion);
			if (marcacion == null){
				return null;
			}
			List<Rodal> list = marcacion.getRodales();
			ubicacionDAO.getHibernateTemplate().initialize(list);

			List<RodalDTO> rodalesDTO = new ArrayList<RodalDTO>();
			for (Rodal rodal : list) {
				rodalesDTO.add(rodal.getLocalizacionDTO());
			}

			return rodalesDTO;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public List<PMFDTO> recuperarPMFsDTO() {

		List<PMFDTO> listaPmfDTO = new ArrayList<PMFDTO>();
		List<PMF> listaPmf = this.ubicacionDAO.recuperarPMFs();

		for (PMF pmf : listaPmf) {
			listaPmfDTO.add(pmf.getLocalizacionDTO());
		}
		return listaPmfDTO;
	}

	public List<PMFDTO> getPMFsDTOPorProductor(Long idProductor) {
		List<PMFDTO> listaPmfDTO = new ArrayList<PMFDTO>();
		for (PMF pmf : ubicacionDAO.getPMFs(idProductor)) {
			listaPmfDTO.add(pmf.getLocalizacionDTO());
		}

		return listaPmfDTO;
	}

	public List<AreaDeCosechaDTO> getAreasDTOPorProductor(Long idProductor) {
		List<AreaDeCosechaDTO> listaAreasDTO = new ArrayList<AreaDeCosechaDTO>();
		for (AreaDeCosecha area : ubicacionDAO.getAreas(idProductor)) {
			listaAreasDTO.add(area.getLocalizacionDTO());
		}
		return listaAreasDTO;
	}

	public List<TranzonDTO> getTranzonesDTOPorProductor(Long idProductor) {

		List<TranzonDTO> listaTranzonDTO = new ArrayList<TranzonDTO>();

		List<Tranzon> listaTranzon = ubicacionDAO.getTranzonesPorProductor(idProductor);

		for (Tranzon tranzon : listaTranzon) {
			listaTranzonDTO.add(tranzon.getLocalizacionDTO());
		}

		return listaTranzonDTO;
	}

	public List<MarcacionDTO> getMarcacionesDTOPorProductor(Long idProductor) {

		List<MarcacionDTO> listaMarcacionDTO = new ArrayList<MarcacionDTO>();

		List<Marcacion> listaMarcacion = ubicacionDAO.getMarcacionesPorProductor(idProductor);

		for (Marcacion marcacion : listaMarcacion) {
			listaMarcacionDTO.add(marcacion.getLocalizacionDTO());
		}

		return listaMarcacionDTO;
	}

	public List<RodalDTO> getRodalesDTOPorProductor(Long idProductor) {

		List<RodalDTO> listaRodalDTO = new ArrayList<RodalDTO>();

		List<Rodal> listaRodal = ubicacionDAO.getRodalesPorProductor(idProductor);

		for (Rodal rodal : listaRodal) {
			listaRodalDTO.add(rodal.getLocalizacionDTO());
		}

		return listaRodalDTO;
	}

	public String getTipoTerrenoPMF(Long idPMF) throws NegocioException {
		try {
			PMF pmf = ubicacionDAO.getPMF(idPMF);
			return pmf.getTipoTerrenoPMF();

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}

	public PMF getPMF(Long idPMF) {

		return ubicacionDAO.getPMF(idPMF);
	}
}
