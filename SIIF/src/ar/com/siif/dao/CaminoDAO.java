package ar.com.siif.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.CaminoConstruido;
import ar.com.siif.negocio.exception.NegocioException;

public class CaminoDAO extends HibernateDaoSupport {

	public List<CaminoConstruido> getCaminos() {
		return getHibernateTemplate().loadAll(CaminoConstruido.class);
	}

	public void altaCamino(CaminoConstruido caminoConstruido) throws NegocioException {
		this.getHibernateTemplate().saveOrUpdate(caminoConstruido);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}
}
