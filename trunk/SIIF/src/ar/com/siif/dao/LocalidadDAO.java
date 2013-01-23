package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.LocalidadDestino;
import ar.com.siif.negocio.ProvinciaDestino;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class LocalidadDAO extends HibernateDaoSupport {

	public List<Localidad> getLocalidades() throws DataBaseException {
		try{
			return getHibernateTemplate().loadAll(Localidad.class);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		}			
	}

	public Localidad getLocalidadPorId(Long id) throws DataBaseException {
		try{
			return (Localidad) getHibernateTemplate().get(Localidad.class, id);
						
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		}			
	}

	public boolean existeLocalidad(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(Localidad.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<Localidad> localidades = criteria.list();
		return (localidades.size() > 0);
	}

	public void alta_modficacion_Localidad(Localidad localidad) throws DataBaseException {

		try{
			if (existeLocalidad(localidad.getNombre(), localidad.getId())) {
				throw new DataBaseException(Constantes.EXISTE_LOCALIDAD);
			}
			this.getHibernateTemplate().saveOrUpdate(localidad);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		}			
	}

	public void alta_modficacion_Provincia(ProvinciaDestino provincia) throws DataBaseException {

		try{
			if (existeProvincia(provincia.getNombre(), provincia.getId())) {
				throw new DataBaseException(Constantes.EXISTE_PROVINCIA);
			}
			this.getHibernateTemplate().saveOrUpdate(provincia);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_PROVINCIA);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_PROVINCIA);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_PROVINCIA);
		}			
	}	
	
	public boolean existeProvincia(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(ProvinciaDestino.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<ProvinciaDestino> provincias = criteria.list();
		return (provincias.size() > 0);
	}	
	
	public List<ProvinciaDestino> getProvincias() throws DataBaseException {
		try{
			return getHibernateTemplate().loadAll(ProvinciaDestino.class);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_PROVINCIAS);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_PROVINCIAS);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_PROVINCIAS);
		}			
	}	
	
	public ProvinciaDestino getProvinciaDestinoPorId(Long id) throws DataBaseException {
		try{
			return (ProvinciaDestino) getHibernateTemplate().get(ProvinciaDestino.class, id);
						
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_PROVINCIA);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_PROVINCIA);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_PROVINCIA);
		}			
	}	
	
	public boolean existeLocalidadDestino(String nombre, Long id, Long idProvincia) {
		Criteria criteria = getSession().createCriteria(LocalidadDestino.class);
		criteria.createAlias("provinciaDestino", "pd");
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		conj.add(Restrictions.eq("pd.id", idProvincia));
		
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<LocalidadDestino> localidades = criteria.list();
		return (localidades.size() > 0);
	}
	
	public void alta_modficacion_LocalidadDestino(LocalidadDestino localidad) throws DataBaseException {

		try{
			if (existeLocalidadDestino(localidad.getNombre(), localidad.getId(),localidad.getProvinciaDestino().getId())) {
				throw new DataBaseException(Constantes.EXISTE_LOCALIDAD_DESTINO);
			}
			this.getHibernateTemplate().saveOrUpdate(localidad);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		}			
	}
	
	public List<LocalidadDestino> getLocalidadesDeProvincia(Long idProvincia)throws DataBaseException {
		
		try{
			Criteria criteria = getSession().createCriteria(LocalidadDestino.class);
			criteria.createAlias("provinciaDestino", "pd");
			Conjunction conj = Restrictions.conjunction();
			conj.add(Restrictions.eq("pd.id", idProvincia));
			criteria.add(conj);
			
			List<LocalidadDestino> localidades = criteria.list();
			return localidades;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		}		
	}
	
	public LocalidadDestino getLocalidadDestinoPorId(Long id) throws DataBaseException {
		try{
			return (LocalidadDestino) getHibernateTemplate().get(LocalidadDestino.class, id);
						
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		}			
	}
	
}
