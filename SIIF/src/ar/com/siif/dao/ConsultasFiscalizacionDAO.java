package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.utils.Constantes;

public class ConsultasFiscalizacionDAO extends HibernateDaoSupport {

	public List<Fiscalizacion> recuperarFiscalizacionesConGuiaForestal(long idProductor)throws DataBaseException{
		
		try{
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			criteria.createAlias("productorForestal", "pf");		
			
			criteria.add(Restrictions.conjunction()
					.add(Restrictions.isNotNull("guiaForestal"))
					.add(Restrictions.eq("pf.id", idProductor)));
			
			criteria.addOrder(Order.asc("pf.nombre"));
			criteria.addOrder(Order.asc("fecha"));		
			
			List<Fiscalizacion> fiscalizaciones = criteria.list();
			return fiscalizaciones;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (HibernateSystemException hse) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		}			
	}
	
	public List<Fiscalizacion> recuperarFiscalizacionesSinGuiaForestal(long idProductor) throws DataBaseException{
		
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
		} catch (HibernateSystemException hse) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACIONES);
		}			
	}	
}
