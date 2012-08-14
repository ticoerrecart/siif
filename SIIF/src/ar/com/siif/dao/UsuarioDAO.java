package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class UsuarioDAO extends HibernateDaoSupport {

	public void altaUsuario(Usuario elUsuario) throws DataBaseException {

		try{
			if (existeUsuario(elUsuario.getNombreUsuario(), elUsuario.getId())) {
				throw new DataBaseException(Constantes.EXISTE_USUARIO);
			}
			this.getHibernateTemplate().saveOrUpdate(elUsuario);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_USUARIO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_USUARIO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_USUARIO);
		}	
	}

	public boolean existeUsuario(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombreUsuario", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<Usuario> usuarios = criteria.list();
		return (usuarios.size() > 0);
	}

	public List<Usuario> getUsuarios()throws DataBaseException {
		try{			
			return this.getHibernateTemplate().loadAll(Usuario.class);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_USUARIOS);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_USUARIOS);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_USUARIOS);
		}			
	}

	public Usuario getUsuario(Long id) throws DataBaseException {
		try{
			return (Usuario) this.getHibernateTemplate().get(Usuario.class, id);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_USUARIO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_USUARIO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_USUARIO);
		}			
	}
	
	public void modificacionUsuario(Usuario elUsuario) throws DataBaseException {

		try{
			if (existeUsuario(elUsuario.getNombreUsuario(), elUsuario.getId())) {
				throw new DataBaseException(Constantes.EXISTE_USUARIO);
			}
			this.getHibernateTemplate().saveOrUpdate(elUsuario);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_USUARIO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_USUARIO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_USUARIO);
		}	
	}	
}
