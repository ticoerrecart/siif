package ar.com.siif.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.enums.TipoDeEntidad;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Obrajero;
import ar.com.siif.negocio.PPF;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class EntidadDAO extends HibernateDaoSupport {

	public void altaEntidad(Entidad laEntidad) throws NegocioException {

		if (existeEntidad(laEntidad.getNombre(), laEntidad.getId())) {
			throw new NegocioException(Constantes.EXISTE_ENTIDAD);
		}
		this.getHibernateTemplate().saveOrUpdate(laEntidad);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public boolean existeEntidad(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(Entidad.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<Entidad> entidades = criteria.list();
		return (entidades.size() > 0);
	}

	public Entidad getEntidadPorNombre(String nombre) {
		Criteria criteria = getSession().createCriteria(Entidad.class);
		criteria.add(Restrictions.eq("nombre", nombre));

		List<Entidad> entidades = criteria.list();
		if (entidades.size() > 0) {
			return entidades.get(0);
		} else {
			return null;
		}
	}

	public List<Entidad> getEntidades() {
		return getHibernateTemplate().loadAll(Entidad.class);
	}

	public Entidad getEntidad(Long id) {
		return (Entidad) getHibernateTemplate().get(Entidad.class, id);
	}

	public List<Entidad> getEntidades(Long idLocalidad) {

		Localidad localidad = (Localidad) getHibernateTemplate().get(Localidad.class, idLocalidad);

		Criteria criteria = getSession().createCriteria(Obrajero.class);
		criteria.add(Restrictions.eq("localidad", localidad));
		List<Entidad> obrajeros = criteria.list();

		criteria = getSession().createCriteria(PPF.class);
		criteria.add(Restrictions.eq("localidad", localidad));
		List<Entidad> ppf = criteria.list();

		obrajeros.addAll(ppf);
		Collections.sort(obrajeros);

		//TreeSet<Entidad> lista = new TreeSet<Entidad>(obrajeros);

		return obrajeros;
		//return lista;				
	}

	public List<Entidad> getEntidades(TipoDeEntidad tipoDeEntidad) {
		List<Entidad> obrajeros = null;
		Criteria criteria = null;
		if (tipoDeEntidad == TipoDeEntidad.OBR) {
			criteria = getSession().createCriteria(Obrajero.class);
			obrajeros = criteria.list();
		}

		if (tipoDeEntidad == TipoDeEntidad.PPF) {
			criteria = getSession().createCriteria(PPF.class);
			obrajeros = criteria.list();
		}

		Collections.sort(obrajeros);

		return obrajeros;
	}
}
