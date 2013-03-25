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

	public long altaCertificadoOrigen(CertificadoOrigen certificadoOrigen){

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
	}	
	
	public double obtenerVolumenExportado(Long idProductor, String periodo, Long idLocalizacion){

		double volumen = 0;
		Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);
		criteria.createAlias("productor", "prod");
		criteria.createAlias("localizacion", "localizacion");

		criteria.add(Restrictions.conjunction().add(Restrictions.eq("prod.id", idProductor)));
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("localizacion.id", idLocalizacion)));
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("periodoForestal", periodo)));
		
		List<CertificadoOrigen> lista = criteria.list();
		for (CertificadoOrigen certificadoOrigen : lista) {
			
			volumen = volumen + certificadoOrigen.getVolumenTotalTipoProductos();
		}		
		
		return MathUtils.round(volumen, 2);				
	}	
	
	public List<CertificadoOrigen> getCertificadosOrigen(Long idProductor, String periodo, Long idPMF){

		Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);
		criteria.createAlias("productor", "prod");
		criteria.createAlias("pmf", "planManejo");

		criteria.add(Restrictions.conjunction().add(Restrictions.eq("prod.id", idProductor)));
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("planManejo.id", idPMF)));
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("periodoForestal", periodo)));
		
		List<CertificadoOrigen> listaCertificados = criteria.list();
		
		return listaCertificados;
}
	
	public CertificadoOrigen recuperarCertificadoOrigen(long idCertificado){

		return (CertificadoOrigen) getSession().get(CertificadoOrigen.class, idCertificado);
	}

	public CertificadoOrigen recuperarCertificadoOrigenPorNroCertificado(long nroCertificado){

		Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);
		criteria.add(Restrictions.eq("nroCertificado", nroCertificado));
			
		List<CertificadoOrigen> listaCertificados = criteria.list();
			
		return listaCertificados.get(0);
	}	
	
	public boolean existeCertificado(long nroCertificado){
		
		Criteria criteria = getSession().createCriteria(CertificadoOrigen.class);
		criteria.add(Restrictions.eq("nroCertificado", nroCertificado));
		
		List<CertificadoOrigen> listaCertificados = criteria.list();
		
		return (listaCertificados.size() > 0);		
	}	
}
