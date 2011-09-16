package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class RolDAO extends HibernateDaoSupport {

	public List<Rol> getRoles() {
		return getHibernateTemplate().loadAll(Rol.class);
	}

	public List<ItemMenu> recuperarMenues() {

		//return getHibernateTemplate().loadAll(ItemMenu.class);

		Criteria criteria = getSession().createCriteria(ItemMenu.class);
		criteria.add(Restrictions.conjunction().add(Restrictions.gt("orden", 0)));

		return criteria.list();
	}

	public void altaRol(Rol rol) throws NegocioException {

		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(Restrictions.disjunction().add(Restrictions.eq("rol", rol.getRol())));

		List<Rol> listaRol = criteria.list();

		if (listaRol.size() > 0) {
			throw new NegocioException(Constantes.EXISTE_ROL);
		}

		this.getHibernateTemplate().save(rol);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public Rol recuperarRol(long idRol) {

		return (Rol) getSession().get(Rol.class, idRol);
	}

	public void modificacionRol(Rol rol) throws NegocioException {

		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("rol", rol.getRol()))).add(
				Restrictions.ne("id", rol.getId()));

		List<Rol> roles = criteria.list();

		if (roles.size() > 0) {
			throw new NegocioException(Constantes.EXISTE_ROL);
		}

		//Rol r = (Rol)this.getHibernateTemplate().get(Rol.class, rol.getId());
		
		this.getHibernateTemplate().saveOrUpdate(rol);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public ItemMenu getItemMenu(Long idMenu) {

		return (ItemMenu) getHibernateTemplate().get(ItemMenu.class, idMenu);
	}

	public Rol getRol(Long id) {
		return (Rol) this.getHibernateTemplate().get(Rol.class, id);
	}

	public boolean existeRol(Rol rol) {

		Criteria criteria = getSession().createCriteria(Rol.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("rol", rol.getRol()));
		if (rol.getId() != null) {
			conj.add(Restrictions.ne("id", rol.getId()));
		}
		criteria.add(conj);

		List<Rol> roles = criteria.list();
		return (roles.size() > 0);
	}
}
