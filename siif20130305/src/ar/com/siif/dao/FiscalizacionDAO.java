package ar.com.siif.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.dto.FilaTablaVolFiscAsociarDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Muestra;

public class FiscalizacionDAO extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Fiscalizacion> recuperarFiscalizaciones(){

		Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
		List<Fiscalizacion> fiscalizaciones = criteria.list();

		return fiscalizaciones;
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaAltaGFB(long idProductor){

		Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
		criteria.createAlias("productorForestal", "pf");
		criteria.createAlias("rodal", "rod");			
		criteria.createAlias("tipoProducto", "tp");
		
		criteria.add(Restrictions.conjunction().add(Restrictions.isNull("guiaForestal"))
				.add(Restrictions.eq("pf.id", idProductor)));
		//.add(Restrictions.eq("rodal.id", idRodal)));

		//criteria.addOrder(Order.asc("pf.nombre"));
		criteria.addOrder(Order.asc("rod.id"));
		criteria.addOrder(Order.asc("tp.id"));
		criteria.addOrder(Order.asc("fecha"));

		List<Fiscalizacion> fiscalizaciones = criteria.list();
		return fiscalizaciones;
	}

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(Long idProductor){

		Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
		criteria.createAlias("productorForestal", "pf");

		criteria.add(Restrictions.conjunction().add(Restrictions.isNull("guiaForestal"))
				.add(Restrictions.eq("pf.id", idProductor)));

		criteria.addOrder(Order.asc("pf.nombre"));
		criteria.addOrder(Order.asc("fecha"));

		List<Fiscalizacion> fiscalizaciones = criteria.list();
		return fiscalizaciones;
	}

	public Fiscalizacion recuperarFiscalizacion(long idFiscalizacion){

		Fiscalizacion acta = (Fiscalizacion) getSession().get(Fiscalizacion.class,
				idFiscalizacion);
		return acta;
	}

	public void altaFiscalizacion(Fiscalizacion fiscalizacion){

		this.getHibernateTemplate().saveOrUpdate(fiscalizacion);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public void actualizarFiscalizacion(Fiscalizacion fiscalizacion,
			List<MuestraDTO> muestrasNuevasDTO) {
		for (Muestra muestra : fiscalizacion.getMuestra()) {
			muestra.setFiscalizacion(null);
			this.getHibernateTemplate().delete(muestra);
		}

		List<Muestra> muestrasNuevas = new ArrayList<Muestra>();
		for (MuestraDTO muestraDTO : muestrasNuevasDTO) {
			Muestra muestraNueva = new Muestra();
			muestraNueva.setDiametro1(muestraDTO.getDiametro1());
			muestraNueva.setDiametro2(muestraDTO.getDiametro2());
			muestraNueva.setLargo(muestraDTO.getLargo());
			muestraNueva.setFiscalizacion(fiscalizacion);
			muestrasNuevas.add(muestraNueva);
			this.getHibernateTemplate().save(muestraNueva);
		}

		fiscalizacion.setMuestra(muestrasNuevas);
		this.getHibernateTemplate().saveOrUpdate(fiscalizacion);
	}

	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor){

		Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
		//criteria.createAlias("productorForestal.l", "localidad");
		criteria.createAlias("productorForestal", "pf");
		criteria.add(Restrictions.conjunction().add(
				Restrictions.eq("productorForestal.id", idProductor)));

		criteria.addOrder(Order.asc("pf.nombre"));
		criteria.addOrder(Order.asc("fecha"));

		List<Fiscalizacion> fiscalizaciones = criteria.list();
		return fiscalizaciones;
	}

	public List<Fiscalizacion> recuperarFiscalizacionesDTOParaAsociarAGuia(Long idProductor,
			Long idRodal, List<SubImporteDTO> listaSubImportesDTO, List<FilaTablaVolFiscAsociarDTO> tablaVolFiscAsociar)
	{	
		List<Long> listaIdsTipoProducto = new ArrayList<Long>();
		List<Fiscalizacion> fiscalizaciones = new ArrayList<Fiscalizacion>();
		
		/*for (SubImporteDTO subImporte : listaSubImportesDTO) {	
			listaIdsTipoProducto.add(subImporte.getTipoProducto().getId());
		}							
		}*/

		for (FilaTablaVolFiscAsociarDTO fila : tablaVolFiscAsociar) {
			if (fila.getVolumenFaltante() > 0.0){			
				listaIdsTipoProducto.add(fila.getIdTipoProducto());
			}
		}			
		if(!listaIdsTipoProducto.isEmpty()){//Esto lo tengo que hacer pq si esta vacia me da un error el query
			
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			criteria.createAlias("productorForestal", "pf");

			criteria.add(Restrictions.conjunction().add(Restrictions.isNull("guiaForestal"))
					.add(Restrictions.eq("pf.id", idProductor))
					.add(Restrictions.eq("rodal.id", idRodal))
					.add(Restrictions.in("tipoProducto.id", listaIdsTipoProducto)));

			criteria.addOrder(Order.asc("pf.nombre"));
			criteria.addOrder(Order.asc("fecha"));

			fiscalizaciones = criteria.list();
		}
		return fiscalizaciones;
	}

	public List<Fiscalizacion> recuperarFiscalizacionesAAnularPorProductor(Long idProductor){

		Criteria criteria = getSession().createCriteria(Fiscalizacion.class);

		criteria.createAlias("productorForestal", "pf");
		criteria.createAlias("rodal", "rod");			
		criteria.createAlias("tipoProducto", "tp");
		
		criteria.add(Restrictions.conjunction()
				.add(Restrictions.eq("productorForestal.id", idProductor))
				.add(Restrictions.isNull("guiaForestal")));

		criteria.addOrder(Order.asc("rod.id"));
		/*criteria.addOrder(Order.asc("tp.id"));*/
		criteria.addOrder(Order.asc("fecha"));

		List<Fiscalizacion> fiscalizaciones = criteria.list();
		return fiscalizaciones;
	}

	public void anularFiscalizacion(Long idFiscalizacion){
		
		Fiscalizacion fiscalizacion = this.recuperarFiscalizacion(idFiscalizacion);
		fiscalizacion.setTipoProducto(null);
		fiscalizacion.setGuiaForestal(null);
		fiscalizacion.setOficinaAlta(null);
		fiscalizacion.setProductorForestal(null);
		fiscalizacion.setRodal(null);
		fiscalizacion.setUsuario(null);
		fiscalizacion.setTipoProducto(null);
		//fiscalizacion.setMuestra(null);

		for (Muestra muestra : fiscalizacion.getMuestra()) {
			muestra.setFiscalizacion(null);
			this.getHibernateTemplate().delete(muestra);
		}
		this.getHibernateTemplate().delete(fiscalizacion);
	}
	
	public List<Fiscalizacion> recuperarFiscalizacionesDTOParaAltaCertificadoOrigen(Long idProductor, String periodo,
																				    Long idPMF) 
	{	
		String q = "from Fiscalizacion where productorForestal.id = :idPf and periodoForestal = :periodo "+
											  "and rodal.marcacion.tranzon.pmf.id = :idPmf and "+
											  "tipoProducto.id != :idLenia order by fecha";
		Query query = getSession().createQuery(q);
		query.setParameter("idPf", idProductor);
		query.setParameter("periodo", periodo);
		query.setParameter("idPmf", idPMF);
		query.setParameter("idLenia", 3L);
		
		List<Fiscalizacion> fiscalizaciones = query.list();
		
		return fiscalizaciones;		
	}
}
