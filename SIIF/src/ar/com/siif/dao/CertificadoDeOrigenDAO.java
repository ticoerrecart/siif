package ar.com.siif.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import ar.com.siif.negocio.CertificadoOrigen;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.MathUtils;

public class CertificadoDeOrigenDAO extends HibernateDaoSupport {

	public long altaCertificadoOrigen(CertificadoOrigen certificadoOrigen) throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);			
			criteria.addOrder(Order.desc("nroCertificado"));
			
			long nroCertificado = 1000L;
			
			List<CertificadoOrigen> lista = criteria.list();
			Iterator<CertificadoOrigen> iterador = lista.iterator();
			if(iterador.hasNext()){
				CertificadoOrigen certificado = iterador.next();
				nroCertificado = certificado.getNroCertificado()+1;
			}
			
			certificadoOrigen.setNroCertificado(nroCertificado);
			
			this.getHibernateTemplate().saveOrUpdate(certificadoOrigen);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
			return nroCertificado;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_CERTIFICADO_ORIGEN);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_CERTIFICADO_ORIGEN);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_CERTIFICADO_ORIGEN);
		}			
	}	
	
	public double obtenerVolumenExportado(Long idProductor, String periodo, Long idPMF)throws DataBaseException {
		
		try{
			double volumen = 0;
			Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);
			criteria.createAlias("productor", "prod");
			criteria.createAlias("pmf", "planManejo");

			criteria.add(Restrictions.conjunction().add(Restrictions.eq("prod.id", idProductor)));
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("planManejo.id", idPMF)));
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("periodoForestal", periodo)));
			
			List<CertificadoOrigen> lista = criteria.list();
			for (CertificadoOrigen certificadoOrigen : lista) {
				
				volumen = volumen + certificadoOrigen.getVolumenTotalTipoProductos();
			}		
			
			return MathUtils.round(volumen, 2);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_VOLUMEN_EXPORTADO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_VOLUMEN_EXPORTADO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_VOLUMEN_EXPORTADO);
		}				
	}	
	
	public List<CertificadoOrigen> getCertificadosOrigen(Long idProductor, String periodo, Long idPMF)throws DataBaseException {
		
		try{
			Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);
			criteria.createAlias("productor", "prod");
			criteria.createAlias("pmf", "planManejo");

			criteria.add(Restrictions.conjunction().add(Restrictions.eq("prod.id", idProductor)));
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("planManejo.id", idPMF)));
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("periodoForestal", periodo)));
			
			List<CertificadoOrigen> listaCertificados = criteria.list();
			
			return listaCertificados;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADOS_ORIGEN);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADOS_ORIGEN);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADOS_ORIGEN);
		}
	}
	
	public CertificadoOrigen recuperarCertificadoOrigen(long idCertificado)throws DataBaseException{
		
		try{

			return (CertificadoOrigen) getSession().get(CertificadoOrigen.class, idCertificado);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADO_ORIGEN);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADO_ORIGEN);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADO_ORIGEN);
		}
	}

	public CertificadoOrigen recuperarCertificadoOrigenPorNroCertificado(long nroCertificado)throws DataBaseException{
		
		try{

			Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);
			criteria.add(Restrictions.eq("nroCertificado", nroCertificado));
			
			List<CertificadoOrigen> listaCertificados = criteria.list();
			
			return listaCertificados.get(0);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADO_ORIGEN);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADO_ORIGEN);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_OBTENER_CERTIFICADO_ORIGEN);
		}
	}	
	
	public boolean existeCertificado(long nroCertificado){
		
		Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);
		criteria.add(Restrictions.eq("nroCertificado", nroCertificado));
		
		List<CertificadoOrigen> listaCertificados = criteria.list();
		
		return (listaCertificados.size() > 0);		
	}	
}
