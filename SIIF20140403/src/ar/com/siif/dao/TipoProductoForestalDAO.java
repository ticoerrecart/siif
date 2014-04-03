package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class TipoProductoForestalDAO extends HibernateDaoSupport {

	public void altaTipoProductoForestal(TipoProducto tipoProductoForestal)
			throws NegocioException {
		if (existeTipoProductoForestal(tipoProductoForestal.getNombre(),
				tipoProductoForestal.getId())) {
			throw new NegocioException(Constantes.EXISTE_TIPO_PRODUCTO);
		}
		this.getHibernateTemplate().save(tipoProductoForestal);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public List<TipoProductoForestal> recuperarTiposProducto() {
		Criteria criteria = getSession().createCriteria(TipoProductoForestal.class);
		criteria.add(Restrictions.eq("habilitado", true));
		List<TipoProductoForestal> tiposProducto = criteria.list();
		return tiposProducto;
	}

	public TipoProductoForestal recuperarTipoProductoForestal(long id) {
		return (TipoProductoForestal) getSession().get(
				TipoProductoForestal.class, id);
	}

	public void modificacionTipoProductoForestal(
			TipoProductoForestal tipoProducto) throws NegocioException {
		if (existeTipoProductoForestal(tipoProducto.getNombre(),
				tipoProducto.getId())) {
			throw new NegocioException(Constantes.EXISTE_TIPO_PRODUCTO);
		}
		this.getHibernateTemplate().saveOrUpdate(tipoProducto);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

	}

	public void altaTipoProductoExportacion(TipoProducto tipoProductoExportacion)
			throws NegocioException {
		if (existeTipoProductoExportacion(tipoProductoExportacion.getNombre(),
				tipoProductoExportacion.getId())) {
			throw new NegocioException(
					Constantes.EXISTE_TIPO_PRODUCTO_EXPORTACION);
		}
		this.getHibernateTemplate().save(tipoProductoExportacion);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

	}

	public boolean existeTipoProductoExportacion(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(
				TipoProductoExportacion.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<TipoProductoExportacion> tiposProducto = criteria.list();
		return (tiposProducto.size() > 0);

	}

	public boolean existeTipoProductoForestal(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(
				TipoProductoForestal.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);
		List<TipoProductoForestal> tiposProducto = criteria.list();
		return (tiposProducto.size() > 0);

	}

	public List<TipoProductoExportacion> recuperarTiposProductoExportacion() {
		Criteria criteria = getSession().createCriteria(
				TipoProductoExportacion.class);
		List<TipoProductoExportacion> tiposProducto = criteria.list();
		return tiposProducto;
	}

	public TipoProductoExportacion recuperarTipoProductoExportacion(long id) {
		return (TipoProductoExportacion) getSession().get(
				TipoProductoExportacion.class, id);
	}

	public void modificacionTipoProductoExportacion(
			TipoProductoExportacion tipoProducto) throws NegocioException {

		if (existeTipoProductoExportacion(tipoProducto.getNombre(),
				tipoProducto.getId())) {
			throw new NegocioException(
					Constantes.EXISTE_TIPO_PRODUCTO_EXPORTACION);
		}
		this.getHibernateTemplate().saveOrUpdate(tipoProducto);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public List<TipoProductoForestal> recuperarTiposProductoForestalHabInhabDTO(){
		
		Criteria criteria = getSession().createCriteria(TipoProductoForestal.class);
		List<TipoProductoForestal> tiposProducto = criteria.list();
		return tiposProducto;		
	}	
}
