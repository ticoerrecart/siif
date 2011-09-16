package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class TipoProductoForestalDAO extends HibernateDaoSupport {

	public void altaTipoProductoForestal(String tipoProductoForestal) throws NegocioException {

		Criteria criteria = getSession().createCriteria(TipoProducto.class);
		criteria.add(Restrictions.disjunction()
				.add(Restrictions.eq("nombre", tipoProductoForestal)));

		List<TipoProducto> tiposProducto = criteria.list();

		if (tiposProducto.size() > 0) {
			throw new NegocioException(Constantes.EXISTE_TIPO_PRODUCTO);
		}

		TipoProducto tipoProducto = new TipoProducto();
		tipoProducto.setNombre(tipoProductoForestal);

		this.getHibernateTemplate().save(tipoProducto);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public List<TipoProducto> recuperarTiposProducto() {

		Criteria criteria = getSession().createCriteria(TipoProducto.class);
		List<TipoProducto> tiposProducto = criteria.list();

		return tiposProducto;
	}

	public TipoProducto recuperarTipoProductoForestal(long id) {

		return (TipoProducto) getSession().get(TipoProducto.class, id);
	}

	public void modificacionTipoProductoForestal(TipoProducto tipoProducto) throws NegocioException {

		Criteria criteria = getSession().createCriteria(TipoProducto.class);
		criteria.add(Restrictions.conjunction()
				.add(Restrictions.eq("nombre", tipoProducto.getNombre()))
				.add(Restrictions.ne("id", tipoProducto.getId())));

		List<TipoProducto> tiposProducto = criteria.list();

		if (tiposProducto.size() > 0) {
			throw new NegocioException(Constantes.EXISTE_TIPO_PRODUCTO);
		}

		this.getHibernateTemplate().saveOrUpdate(tipoProducto);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public boolean existeTipoProductoForestal(String nombre, Long id) {

		Criteria criteria = getSession().createCriteria(TipoProducto.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<TipoProducto> tiposProducto = criteria.list();
		return (tiposProducto.size() > 0);
	}
}
