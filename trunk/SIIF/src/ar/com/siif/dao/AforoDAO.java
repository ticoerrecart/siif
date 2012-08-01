package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.dto.AforoDTO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class AforoDAO extends HibernateDaoSupport {

	public List<TipoProducto> recuperarTiposProducto() {

		Criteria criteria = getSession().createCriteria(TipoProducto.class);
		List<TipoProducto> tiposProducto = criteria.list();

		return tiposProducto;
	}

	public void altaAforo(Aforo aforo) throws NegocioException {

		Criteria criteria = getSession().createCriteria(Aforo.class);
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("estado", aforo.getEstado()))
				.add(Restrictions.eq("tipoProductor", aforo.getTipoProductor()))
				.add(Restrictions.eq("tipoProducto.id", aforo.getTipoProducto().getId())));

		List<Aforo> aforos = criteria.list();

		if (aforos.size() > 0) {
			throw new NegocioException(Constantes.EXISTE_AFORO);
		}

		this.getHibernateTemplate().saveOrUpdate(aforo);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	@SuppressWarnings("unchecked")
	public List<Aforo> recuperarAforos() {

		Criteria criteria = getSession().createCriteria(Aforo.class);

		criteria.createAlias("tipoProducto", "tipoP");

		criteria.addOrder(Order.asc("tipoProductor"));
		criteria.addOrder(Order.asc("tipoP.nombre"));
		criteria.addOrder(Order.asc("estado"));

		return criteria.list();

		//return getHibernateTemplate().loadAll(Aforo.class);
	}

	@SuppressWarnings("unchecked")
	public Aforo recuperarAforo(Long id) {

		return (Aforo) getSession().get(Aforo.class, id);
	}

	public void modificacionAforo(Aforo aforo) throws NegocioException {

		Criteria criteria = getSession().createCriteria(Aforo.class);
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("estado", aforo.getEstado()))
				.add(Restrictions.eq("tipoProductor", aforo.getTipoProductor()))
				.add(Restrictions.eq("tipoProducto.id", aforo.getTipoProducto().getId()))
				.add(Restrictions.ne("id", aforo.getId())));

		List<Aforo> aforos = criteria.list();

		if (aforos.size() > 0) {
			throw new NegocioException(Constantes.EXISTE_AFORO);
		}

		this.getHibernateTemplate().saveOrUpdate(aforo);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public boolean existeAforo(AforoDTO aforo, Long idTipoProducto) {

		Criteria criteria = getSession().createCriteria(Aforo.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("estado", aforo.getEstado()));
		conj.add(Restrictions.eq("tipoProductor", aforo.getTipoProductor()));
		conj.add(Restrictions.eq("tipoProducto.id", idTipoProducto));
		if (aforo.getId() != null) {
			conj.add(Restrictions.ne("id", aforo.getId()));
		}
		criteria.add(conj);

		List<Aforo> aforos = criteria.list();
		return (aforos.size() > 0);
	}
	
	@SuppressWarnings("finally")
	public Aforo getAforo(Long idFiscalizacion,String estado)throws Exception{
		
		try{
			Fiscalizacion fiscalizacion = (Fiscalizacion)this.getHibernateTemplate().get(Fiscalizacion.class, idFiscalizacion);
			
			Criteria criteria = getSession().createCriteria(Aforo.class);
			Conjunction conj = Restrictions.conjunction();
			conj.add(Restrictions.eq("estado", estado));
			conj.add(Restrictions.eq("tipoProductor", fiscalizacion.getProductorForestal().getIdTipoEntidad()));
			conj.add(Restrictions.eq("tipoProducto.id", fiscalizacion.getTipoProducto().getId()));
			
			criteria.add(conj);
	
			List<Aforo> aforos = criteria.list();
			return aforos.get(0);
			
		}catch(Exception e){
			throw e;
		}
	}
	
}
