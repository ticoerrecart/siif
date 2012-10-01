package ar.com.siif.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.utils.Constantes;

public class FiscalizacionDAO extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Fiscalizacion> recuperarFiscalizaciones() throws DataBaseException {

		try {
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

	public List<Fiscalizacion> recuperarFiscalizacionesParaAltaGFB(long idProductor)
			throws DataBaseException {

		try {
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			criteria.createAlias("productorForestal", "pf");

			criteria.add(Restrictions.conjunction().add(Restrictions.isNull("guiaForestal"))
					.add(Restrictions.eq("pf.id", idProductor)));
			//.add(Restrictions.eq("rodal.id", idRodal)));

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

	public List<Fiscalizacion> recuperarFiscalizacionesParaModificacionGFB(Long idProductor)
			throws DataBaseException {

		try {
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			criteria.createAlias("productorForestal", "pf");

			criteria.add(Restrictions.conjunction().add(Restrictions.isNull("guiaForestal"))
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

		try {
			Fiscalizacion acta = (Fiscalizacion) getSession().get(Fiscalizacion.class,
					idFiscalizacion);
			return acta;

		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACION);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACION);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_FISCALIZACION);
		}
	}

	public void altaFiscalizacion(Fiscalizacion fiscalizacion) throws DataBaseException {
		try {
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

	public List<Fiscalizacion> recuperarFiscalizacionesPorProductor(Long idProductor)
			throws DataBaseException {

		try {
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			//criteria.createAlias("productorForestal.l", "localidad");
			criteria.createAlias("productorForestal", "pf");
			criteria.add(Restrictions.conjunction().add(
					Restrictions.eq("productorForestal.id", idProductor)));

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

	public List<Fiscalizacion> recuperarFiscalizacionesDTOParaAsociarAGuia(Long idProductor,
			Long idRodal, List<SubImporteDTO> listaSubImportesDTO) throws DataBaseException {

		try {
			List<Long> listaIdsTipoProducto = new ArrayList<Long>();

			for (SubImporteDTO subImporte : listaSubImportesDTO) {
				listaIdsTipoProducto.add(subImporte.getTipoProducto().getId());
			}

			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);
			criteria.createAlias("productorForestal", "pf");

			criteria.add(Restrictions.conjunction().add(Restrictions.isNull("guiaForestal"))
					.add(Restrictions.eq("pf.id", idProductor))
					.add(Restrictions.eq("rodal.id", idRodal))
					.add(Restrictions.in("tipoProducto.id", listaIdsTipoProducto)));

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

	public List<Fiscalizacion> recuperarFiscalizacionesAAnularPorProductor(Long idProductor)
			throws DataBaseException {

		try {
			Criteria criteria = getSession().createCriteria(Fiscalizacion.class);

			criteria.createAlias("productorForestal", "pf");
			criteria.add(Restrictions.conjunction()
					.add(Restrictions.eq("productorForestal.id", idProductor))
					.add(Restrictions.isNull("guiaForestal")));

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

	public void anularFiscalizacion(Long idFiscalizacion) throws DataBaseException {
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
}
