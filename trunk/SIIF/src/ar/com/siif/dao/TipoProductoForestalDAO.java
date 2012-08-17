package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class TipoProductoForestalDAO extends HibernateDaoSupport {

	public void altaTipoProductoForestal(TipoProducto tipoProductoForestal) throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(TipoProducto.class);
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.eq("nombre", tipoProductoForestal.getNombre())));
	
			List<TipoProducto> tiposProducto = criteria.list();
	
			if (tiposProducto.size() > 0) {
				throw new DataBaseException(Constantes.EXISTE_TIPO_PRODUCTO);
			}
	
			this.getHibernateTemplate().save(tipoProductoForestal);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (DataBaseException he) {
			throw he;			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TIPO_PRODUCTO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TIPO_PRODUCTO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TIPO_PRODUCTO);
		}			
	}

	public List<TipoProducto> recuperarTiposProducto()throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(TipoProducto.class);
			List<TipoProducto> tiposProducto = criteria.list();
	
			return tiposProducto;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		}			
	}

	public TipoProducto recuperarTipoProductoForestal(long id)throws DataBaseException {

		try{
			return (TipoProducto) getSession().get(TipoProducto.class, id);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		}			
	}

	public void modificacionTipoProductoForestal(TipoProducto tipoProducto) throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(TipoProducto.class);
			criteria.add(Restrictions.conjunction()
					.add(Restrictions.eq("nombre", tipoProducto.getNombre()))
					.add(Restrictions.ne("id", tipoProducto.getId())));
	
			List<TipoProducto> tiposProducto = criteria.list();
	
			if (tiposProducto.size() > 0) {
				throw new DataBaseException(Constantes.EXISTE_TIPO_PRODUCTO);
			}
	
			this.getHibernateTemplate().saveOrUpdate(tipoProducto);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (DataBaseException he) {
			throw he;			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_TIPO_PRODUCTO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_TIPO_PRODUCTO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_TIPO_PRODUCTO);
		}			
	}

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO) {

		Criteria criteria = getSession().createCriteria(TipoProducto.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", tipoProdructoDTO.getNombre()));
		if (tipoProdructoDTO.getId() != null) {
			conj.add(Restrictions.ne("id", tipoProdructoDTO.getId()));
		}
		criteria.add(conj);

		List<TipoProducto> tiposProducto = criteria.list();
		return (tiposProducto.size() > 0);
		
	}
}
