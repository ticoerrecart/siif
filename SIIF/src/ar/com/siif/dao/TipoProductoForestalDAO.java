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
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.Constantes;

public class TipoProductoForestalDAO extends HibernateDaoSupport {

	public void altaTipoProductoForestal(TipoProducto tipoProductoForestal) throws DataBaseException {

		try{
	
			if (existeTipoProductoForestal(tipoProductoForestal.getNombre(),tipoProductoForestal.getId())) {
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

	public List<TipoProductoForestal> recuperarTiposProducto()throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(TipoProductoForestal.class);
			List<TipoProductoForestal> tiposProducto = criteria.list();
	
			return tiposProducto;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		}			
	}

	public TipoProductoForestal recuperarTipoProductoForestal(long id)throws DataBaseException {

		try{
			return (TipoProductoForestal) getSession().get(TipoProductoForestal.class, id);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		}			
	}

	public void modificacionTipoProductoForestal(TipoProductoForestal tipoProducto) throws DataBaseException {

		try{
			if (existeTipoProductoForestal(tipoProducto.getNombre(),tipoProducto.getId())) {
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

	public void altaTipoProductoExportacion(TipoProducto tipoProductoExportacion) throws DataBaseException {

		try{
	
			if (existeTipoProductoExportacion(tipoProductoExportacion.getNombre(),tipoProductoExportacion.getId())) {
				throw new DataBaseException(Constantes.EXISTE_TIPO_PRODUCTO_EXPORTACION);
			}
	
			this.getHibernateTemplate().save(tipoProductoExportacion);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (DataBaseException he) {
			throw he;			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TIPO_PRODUCTO_EXPORTACION);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TIPO_PRODUCTO_EXPORTACION);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_TIPO_PRODUCTO_EXPORTACION);
		}			
	}	

	public boolean existeTipoProductoExportacion(String nombre, Long id) {

		Criteria criteria = getSession().createCriteria(TipoProductoExportacion.class);
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

		Criteria criteria = getSession().createCriteria(TipoProductoForestal.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<TipoProductoForestal> tiposProducto = criteria.list();
		return (tiposProducto.size() > 0);
		
	}	
	
	public List<TipoProductoExportacion> recuperarTiposProductoExportacion()throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(TipoProductoExportacion.class);
			List<TipoProductoExportacion> tiposProducto = criteria.list();
	
			return tiposProducto;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPOS_PRODUCTOS);
		}			
	}	
	
	public TipoProductoExportacion recuperarTipoProductoExportacion(long id)throws DataBaseException {

		try{
			return (TipoProductoExportacion) getSession().get(TipoProductoExportacion.class, id);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_TIPO_PRODUCTO);
		}			
	}	
	
	public void modificacionTipoProductoExportacion(TipoProductoExportacion tipoProducto) throws DataBaseException {

		try{
			if (existeTipoProductoExportacion(tipoProducto.getNombre(),tipoProducto.getId())) {
				throw new DataBaseException(Constantes.EXISTE_TIPO_PRODUCTO_EXPORTACION);
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
	
	
	/*public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO) {

		Criteria criteria = getSession().createCriteria(TipoProducto.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", tipoProdructoDTO.getNombre()));
		if (tipoProdructoDTO.getId() != null) {
			conj.add(Restrictions.ne("id", tipoProdructoDTO.getId()));
		}
		criteria.add(conj);

		List<TipoProducto> tiposProducto = criteria.list();
		return (tiposProducto.size() > 0);
		
	}*/
}
