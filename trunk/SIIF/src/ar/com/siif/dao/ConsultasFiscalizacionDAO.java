package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Fiscalizacion;

public class ConsultasFiscalizacionDAO extends HibernateDaoSupport {

	public List<Fiscalizacion> recuperarFiscalizacionesConGuiaForestal(
			long idProductor, String idPeriodo) {

		Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
		criteria.createAlias("productorForestal", "pf");

		criteria.add(Restrictions.conjunction()
				.add(Restrictions.isNotNull("guiaForestal"))
				.add(Restrictions.eq("pf.id", idProductor)));

		if (idPeriodo != null) {
			criteria.add(Restrictions.eq("periodoForestal", idPeriodo));
		}
		criteria.addOrder(Order.asc("pf.nombre"));
		criteria.addOrder(Order.asc("fecha"));

		List<Fiscalizacion> fiscalizaciones = criteria.list();
		return fiscalizaciones;
	}

	public List<Fiscalizacion> recuperarFiscalizacionesSinGuiaForestal(
			long idProductor, String idPeriodo) {

		Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
		criteria.createAlias("productorForestal", "pf");

		criteria.add(Restrictions.conjunction()
				.add(Restrictions.isNull("guiaForestal"))
				.add(Restrictions.eq("pf.id", idProductor)));
		if (idPeriodo != null) {
			criteria.add(Restrictions.eq("periodoForestal", idPeriodo));
		}

		criteria.addOrder(Order.asc("pf.nombre"));
		criteria.addOrder(Order.asc("fecha"));

		List<Fiscalizacion> fiscalizaciones = criteria.list();
		return fiscalizaciones;
	}
}
