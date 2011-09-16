package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class LocalidadDAO extends HibernateDaoSupport {

	public List<Localidad> getLocalidades() {
		return getHibernateTemplate().loadAll(Localidad.class);
	}

	public Localidad getLocalidadPorId(Long id) {
		return (Localidad) getHibernateTemplate().get(Localidad.class, id);
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

	public void altaLocalidad(Localidad laLocalidad) throws NegocioException {

		if (existeLocalidad(laLocalidad.getNombre(), laLocalidad.getId())) {
			throw new NegocioException(Constantes.EXISTE_LOCALIDAD);
		}
		this.getHibernateTemplate().saveOrUpdate(laLocalidad);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

}
