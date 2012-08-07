package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.Tranzon;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class UbicacionDAO extends HibernateDaoSupport {
	@SuppressWarnings("unchecked")
	public List<PMF> recuperarPMFs() {
		return this.getHibernateTemplate().loadAll(PMF.class);
	}

	public Tranzon getTranzon(Long idTranzon) {
		return (Tranzon) this.getHibernateTemplate().get(Tranzon.class, idTranzon);
	}

	public Marcacion getMarcacion(Long idMarcacion) {
		return (Marcacion) this.getHibernateTemplate().get(Marcacion.class, idMarcacion);
	}

	public Rodal getRodal(Long idRodal) {
		return (Rodal) this.getHibernateTemplate().get(Rodal.class, idRodal);
	}

	@SuppressWarnings("unchecked")
	public List<Rodal> getRodales() {
		return this.getHibernateTemplate().loadAll(Rodal.class);
	}

	@SuppressWarnings("unchecked")
	public List<Marcacion> getMarcaciones() {
		return this.getHibernateTemplate().loadAll(Marcacion.class);
	}

	@SuppressWarnings("unchecked")
	public List<Tranzon> getTranzones() {
		return this.getHibernateTemplate().loadAll(Tranzon.class);
	}

	public PMF getPMF(Long idPMF) {
		return (PMF) this.getHibernateTemplate().get(PMF.class, idPMF);
	}

	public Entidad getEntidad(Long idEntidad) {
		return (Entidad) this.getHibernateTemplate().get(Entidad.class, idEntidad);
	}

	public void deleteRodal(Long idRodal) {
		Rodal rodal = this.getRodal(idRodal);
		this.getHibernateTemplate().delete(rodal);
	}

	public void deleteMarcacion(Long idMarcacion) {
		Marcacion marcacion = this.getMarcacion(idMarcacion);
		this.getHibernateTemplate().delete(marcacion);
	}

	public void deleteTranzon(Long idTranzon) {
		Tranzon tranzon = this.getTranzon(idTranzon);
		this.getHibernateTemplate().delete(tranzon);
	}

	public void deletePMF(Long idPMF) {
		PMF pmf = this.getPMF(idPMF);
		this.getHibernateTemplate().delete(pmf);
	}

	@SuppressWarnings("unchecked")
	public List<Rodal> getRodalesPorNombreParaMarcacion(String nombre, Long marcacionId) {
		Criteria criteria = getSession().createCriteria(Rodal.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("marcacion.id", marcacionId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Rodal> getMarcacionesPorDisposicionParaTranzon(String disposicion, Long tranzonId) {
		Criteria criteria = getSession().createCriteria(Marcacion.class);
		criteria.add(Restrictions.eq("disposicion", disposicion));
		criteria.add(Restrictions.eq("tranzon.id", tranzonId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Tranzon> getTranzonesPorNumeroParaPMF(String numero, Long pmfId) {
		Criteria criteria = getSession().createCriteria(Tranzon.class);
		criteria.add(Restrictions.eq("numero", numero));
		criteria.add(Restrictions.eq("pmf.id", pmfId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<PMF> getPMFsPorNombreParaPF(String nombre, Long pfId) {
		Criteria criteria = getSession().createCriteria(PMF.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("productorForestal.id", pfId));
		return criteria.list();
	}

	public void altaPMF(PMF pmf) throws DataBaseException {
		
		try{
			this.getHibernateTemplate().saveOrUpdate(pmf);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_PMF);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_PMF);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_PMF);
		}			
	}
	
	public void altaTranzon(Tranzon tranzon) throws DataBaseException {
		
		try{
			this.getHibernateTemplate().saveOrUpdate(tranzon);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TRANZON);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TRANZON);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TRANZON);
		}			
	}	
	
	public void altaMarcacion(Marcacion marcacion) throws DataBaseException {
		
		try{
			this.getHibernateTemplate().saveOrUpdate(marcacion);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_MARCACION);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_MARCACION);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_MARCACION);
		}			
	}
	
	public void altaRodal(Rodal rodal) throws DataBaseException {
		
		try{
			this.getHibernateTemplate().saveOrUpdate(rodal);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_RODAL);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_RODAL);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_RODAL);
		}			
	}		
}
