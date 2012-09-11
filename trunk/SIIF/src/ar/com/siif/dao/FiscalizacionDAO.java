package ar.com.siif.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.Obrajero;
import ar.com.siif.negocio.PPF;
import ar.com.siif.negocio.Rol;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.utils.Constantes;

public class FiscalizacionDAO extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	/*public List<Entidad> recuperarEntidades() throws DataBaseException {
		try{
			return this.getHibernateTemplate().loadAll(Entidad.class);

		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ENTIDAD);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ENTIDAD);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_ENTIDAD);
		}			
	}*/

	/*public List<Entidad> recuperarProductores() throws DataBaseException {
		try{
			Criteria criteria = getSession().createCriteria(Obrajero.class);
			List<Entidad> obrajeros = criteria.list();
			criteria = getSession().createCriteria(PPF.class);
			List<Entidad> ppf = criteria.list();
	
			obrajeros.addAll(ppf);
			Collections.sort(obrajeros);
			
			return obrajeros;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_PRODUCTORES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_PRODUCTORES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_PRODUCTORES);
		}
	}*/

	public List<Fiscalizacion> recuperarFiscalizaciones() throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			List<Fiscalizacion> fiscalizaciones = criteria.list();
	
			return fiscalizaciones;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		}			
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaAltaGFB(long idProductor, long idRodal) throws DataBaseException {
		
		try{
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			criteria.createAlias("productorForestal", "pf");		
			
			criteria.add(Restrictions.conjunction()
					.add(Restrictions.isNull("guiaForestal"))
					.add(Restrictions.eq("pf.id", idProductor))
					.add(Restrictions.eq("rodal.id", idRodal)));
			
			criteria.addOrder(Order.asc("pf.nombre"));
			criteria.addOrder(Order.asc("fecha"));		
			
			List<Fiscalizacion> fiscalizaciones = criteria.list();
			return fiscalizaciones;

		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		}			
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(Long idProductor) throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			criteria.createAlias("productorForestal", "pf");		
			
			criteria.add(Restrictions.conjunction()
					.add(Restrictions.isNull("guiaForestal"))
					.add(Restrictions.eq("pf.id", idProductor)));
			
			criteria.addOrder(Order.asc("pf.nombre"));
			criteria.addOrder(Order.asc("fecha"));		
			
			List<Fiscalizacion> fiscalizaciones = criteria.list();
			return fiscalizaciones;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		}			
	}	
	
	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion) throws DataBaseException {
		
		try{
			Fiscalizacion acta = (Fiscalizacion) getSession().get(Fiscalizacion.class, idFiscalizacion);
			return acta;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACION);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACION);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACION);
		}
	}

	public void modificacionFiscalizacion(Fiscalizacion pFiscalizacion, List<Muestra> muestrasAEliminar)
			throws DataBaseException {

		try {
			
			for (Muestra muestra : muestrasAEliminar) {

				muestra.setFiscalizacion(null);
				
				this.getHibernateTemplate().delete(muestra);
				this.getHibernateTemplate().flush();
				this.getHibernateTemplate().clear();				
			}
						
			this.getHibernateTemplate().saveOrUpdate(pFiscalizacion);

		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_FISCALIZACION);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_FISCALIZACION);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_FISCALIZACION);
		}
	}

	public void altaFiscalizacion(Fiscalizacion fiscalizacion)
		throws DataBaseException{ 
		try{			
			this.getHibernateTemplate().saveOrUpdate(fiscalizacion);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_FISCALIZACION);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_FISCALIZACION);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_FISCALIZACION);
		}			
	}

	public void actualizarFiscalizacion(Fiscalizacion fiscalizacion)
		throws DataBaseException {
						
		try{
			
			this.getHibernateTemplate().merge(fiscalizacion);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_FISCALIZACION);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_FISCALIZACION);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_MODIFICACION_FISCALIZACION);
		}			
	}	
	
	/*public Entidad getProductorForestal(long idProductorForestal) {
		return (Entidad) this.getHibernateTemplate().get(Entidad.class, idProductorForestal);
	}*/

	/*public TipoProducto getTipoProducto(long idTipoProductoForestal) {
		return (TipoProducto) this.getHibernateTemplate().get(TipoProducto.class,
				idTipoProductoForestal);
	}*/

	/*public List<Fiscalizacion> recuperarFiscalizacionesPorLocalidad(Long idLocalidad){
		
		Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
		criteria.createAlias("productorForestal.localidad", "localidad");
		criteria.createAlias("productorForestal", "pf");
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("localidad.id", idLocalidad)));

		criteria.addOrder(Order.asc("pf.nombre"));
		criteria.addOrder(Order.asc("fecha"));
		
		List<Fiscalizacion> fiscalizaciones = criteria.list();
		return fiscalizaciones;		
		
	}*/
	
	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor) throws DataBaseException{
	
		try{
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			//criteria.createAlias("productorForestal.l", "localidad");
			criteria.createAlias("productorForestal", "pf");
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("productorForestal.id", idProductor)));
	
			criteria.addOrder(Order.asc("pf.nombre"));
			criteria.addOrder(Order.asc("fecha"));
			
			List<Fiscalizacion> fiscalizaciones = criteria.list();
			return fiscalizaciones;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		}			
	}	
}
