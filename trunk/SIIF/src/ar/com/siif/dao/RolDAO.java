package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.dto.RolDTO;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.exception.AccesoDenegadoException;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class RolDAO extends HibernateDaoSupport {

	public List<Rol> getRoles() throws DataBaseException {
		try{
			return getHibernateTemplate().loadAll(Rol.class);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ROLES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ROLES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ROLES);
		}			
	}

	public List<ItemMenu> recuperarMenues() throws DataBaseException {

		//return getHibernateTemplate().loadAll(ItemMenu.class);
		try{
			Criteria criteria = getSession().createCriteria(ItemMenu.class);
			criteria.add(Restrictions.conjunction().add(Restrictions.gt("orden", 0)));
	
			return criteria.list();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_MENUES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_MENUES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_MENUES);
		}			
	}

	public void altaRol(Rol rol) throws NegocioException, DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(Rol.class);
			criteria.add(Restrictions.disjunction().add(Restrictions.eq("rol", rol.getRol())));
	
			List<Rol> listaRol = criteria.list();
	
			if (listaRol.size() > 0) {
				throw new NegocioException(Constantes.EXISTE_ROL);
			}
	
			this.getHibernateTemplate().save(rol);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_ROL);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_ROL);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_ROL);
		}			
	}

	public Rol recuperarRol(long idRol) throws DataBaseException {

		try{
			return (Rol) getSession().get(Rol.class, idRol);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ROL);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ROL);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ROL);
		}			
	}

	public void modificacionRol(Rol rol) throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(Rol.class);
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("rol", rol.getRol()))).add(
					Restrictions.ne("id", rol.getId()));
	
			List<Rol> roles = criteria.list();
	
			if (roles.size() > 0) {
				throw new NegocioException(Constantes.EXISTE_ROL);
			}
			
			/*this.getHibernateTemplate().saveOrUpdate(rol);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();*/
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_ROL);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_ROL);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_ROL);
		}				
	}

	public ItemMenu getItemMenu(Long idMenu) {

		return (ItemMenu) getHibernateTemplate().get(ItemMenu.class, idMenu);
	}

	public Rol getRol(Long id) {
		return (Rol) this.getHibernateTemplate().get(Rol.class, id);
	}

	public boolean existeRol(RolDTO rol) {

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
	
	public void verificarMenu(String pNombreMenu,Rol pRol)throws AccesoDenegadoException{
		
		Rol rol = (Rol)getHibernateTemplate().load(Rol.class, pRol.getId());
		for (ItemMenu item : rol.getMenues()) {
			if(item.getItem().equals(pNombreMenu)){
				return;
			}
		}
		
		throw new AccesoDenegadoException("El rol "+pRol.getRol()+" no puede ejecutar la acción: "+pNombreMenu); 
	}
	
	public Rol getRolAdministrador()throws DataBaseException {
		
		try{
			Criteria criteria = getSession().createCriteria(Rol.class);
			Conjunction conj = Restrictions.conjunction();
			conj.add(Restrictions.eq("rol", Constantes.ADMINISTRADOR));			
			
			criteria.add(conj);
			List<Rol> roles = criteria.list();
			
			return roles.get(0);
			
		} catch (HibernateException he) {
			throw new DataBaseException();
		} catch (HibernateSystemException he) {
			throw new DataBaseException();
		} catch (Exception e) {
			throw new DataBaseException();
		}		
	}
}
