package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.AreaDeCosecha;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localizacion;
import ar.com.siif.negocio.Marcacion;
import ar.com.siif.negocio.PMF;
import ar.com.siif.negocio.Rodal;
import ar.com.siif.negocio.Tranzon;

@SuppressWarnings("unchecked")
public class UbicacionDAO extends HibernateDaoSupport {

	public List<PMF> recuperarPMFs() {
		return this.getHibernateTemplate().loadAll(PMF.class);
	}

	public Tranzon getTranzon(Long idTranzon) {
		return (Tranzon) this.getHibernateTemplate().get(Tranzon.class, idTranzon);
	}

	public Marcacion getMarcacion(Long idMarcacion) {
		return (Marcacion) this.getHibernateTemplate().get(Marcacion.class, idMarcacion);
	}

	public Rodal getRodal(Long idRodal) {
		return (Rodal) this.getHibernateTemplate().get(Rodal.class, idRodal);
	}
	
	public Localizacion getLocalizacion(Long idLocalizacion) {
		return (Localizacion) this.getHibernateTemplate().get(Localizacion.class, idLocalizacion);
	}
	
	public List<Rodal> getRodales() {
		return this.getHibernateTemplate().loadAll(Rodal.class);
	}

	public List<Marcacion> getMarcaciones() {
		return this.getHibernateTemplate().loadAll(Marcacion.class);
	}

	public List<Tranzon> getTranzones() {
		return this.getHibernateTemplate().loadAll(Tranzon.class);
	}

	public PMF getPMF(Long idPMF) {
		return (PMF) this.getHibernateTemplate().get(PMF.class, idPMF);
	}

	public Entidad getEntidad(Long idEntidad) {
		return (Entidad) this.getHibernateTemplate().get(Entidad.class, idEntidad);
	}

	public void deleteRodal(Long idRodal) {
		Rodal rodal = this.getRodal(idRodal);
		this.getHibernateTemplate().delete(rodal);
	}

	public void deleteMarcacion(Long idMarcacion) {
		Marcacion marcacion = this.getMarcacion(idMarcacion);
		this.getHibernateTemplate().delete(marcacion);
	}

	public void deleteTranzon(Long idTranzon) {
		Tranzon tranzon = this.getTranzon(idTranzon);
		this.getHibernateTemplate().delete(tranzon);
	}

	public void deletePMF(Long idPMF) {
		PMF pmf = this.getPMF(idPMF);
		this.getHibernateTemplate().delete(pmf);
	}

	public List<Rodal> getRodalesPorNombreParaMarcacion(String nombre, Long marcacionId) {
		Criteria criteria = getSession().createCriteria(Rodal.class);
		criteria.add(Restrictions.eq("nombreRodal", nombre));
		criteria.add(Restrictions.eq("marcacion.id", marcacionId));
		return criteria.list();
	}

	public List<Rodal> getMarcacionesPorDisposicionParaTranzon(String disposicion, Long tranzonId) {
		Criteria criteria = getSession().createCriteria(Marcacion.class);
		criteria.add(Restrictions.eq("disposicionMarcacion", disposicion));
		criteria.add(Restrictions.eq("tranzon.id", tranzonId));
		return criteria.list();
	}

	public List<Tranzon> getTranzonesPorNumeroParaPMF(String numero, Long pmfId) {
		Criteria criteria = getSession().createCriteria(Tranzon.class);
		criteria.add(Restrictions.eq("numeroTranzon", numero));
		criteria.add(Restrictions.eq("pmf.id", pmfId));
		return criteria.list();
	}

	public List<PMF> getPMFsPorNombreParaPF(String nombre, Long pfId) {
		Criteria criteria = getSession().createCriteria(PMF.class);
		criteria.add(Restrictions.eq("nombrePMF", nombre));
		criteria.add(Restrictions.eq("productorForestal.id", pfId));
		return criteria.list();
	}

	public List<AreaDeCosecha> getAreasPorNombreParaPF(String nombreArea, Long idEntidad) {
		Criteria criteria = getSession().createCriteria(AreaDeCosecha.class);
		criteria.add(Restrictions.eq("nombreArea", nombreArea));
		criteria.add(Restrictions.eq("productorForestal.id", idEntidad));
		return criteria.list();
	}

	public void altaPMF(PMF pmf) {
		this.getHibernateTemplate().saveOrUpdate(pmf);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public void altaArea(AreaDeCosecha areaDeCosecha) {
		this.getHibernateTemplate().saveOrUpdate(areaDeCosecha);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public void altaTranzon(Tranzon tranzon) {
		this.getHibernateTemplate().saveOrUpdate(tranzon);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public void altaMarcacion(Marcacion marcacion) {
		this.getHibernateTemplate().saveOrUpdate(marcacion);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public void altaRodal(Rodal rodal) {
		this.getHibernateTemplate().saveOrUpdate(rodal);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

	}

	public List<Tranzon> getTranzonesPorProductor(Long idProductor) {
		Criteria criteria = getSession().createCriteria(Tranzon.class);
		criteria.createAlias("pmf", "prod");
		criteria.add(Restrictions.eq("prod.productorForestal.id", idProductor));
		return criteria.list();

	}

	public List<Marcacion> getMarcacionesPorProductor(Long idProductor) {
		Criteria criteria = getSession().createCriteria(Marcacion.class);
		criteria.createAlias("tranzon.pmf", "prod");
		criteria.add(Restrictions.eq("prod.productorForestal.id", idProductor));
		return criteria.list();

	}

	public List<Rodal> getRodalesPorProductor(Long idProductor) {
		Criteria criteria = getSession().createCriteria(Rodal.class);
		criteria.createAlias("marcacion.tranzon.pmf", "prod");
		criteria.add(Restrictions.eq("prod.productorForestal.id", idProductor));
		return criteria.list();

	}

	public List<PMF> getPMFs(Long idEntidad) {
		Criteria criteria = getSession().createCriteria(PMF.class);
		criteria.add(Restrictions.eq("productorForestal.id", idEntidad));
		return criteria.list();
	}

	public List<AreaDeCosecha> getAreas(Long idEntidad) {
		Criteria criteria = getSession().createCriteria(AreaDeCosecha.class);
		criteria.add(Restrictions.eq("productorForestal.id", idEntidad));
		return criteria.list();
	}

	public AreaDeCosecha getArea(Long idArea) {
		return (AreaDeCosecha) this.getHibernateTemplate().get(AreaDeCosecha.class, idArea);
	}

	public void deleteArea(Long idArea) {
		AreaDeCosecha area = this.getArea(idArea);
		this.getHibernateTemplate().delete(area);
	}

}
