package ar.com.siif.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.dto.OperacionGuiaForestalDTO;
import ar.com.siif.enums.TipoOperacion;
import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.Fecha;

public class GuiaForestalDAO extends HibernateDaoSupport {

	/*public List<Entidad> recuperarPermicionarios() {

		Criteria criteria = getSession().createCriteria(Obrajero.class);
		List<Entidad> obrajeros = criteria.list();

		criteria = getSession().createCriteria(PPF.class);
		List<Entidad> ppf = criteria.list();

		obrajeros.addAll(ppf);

		return obrajeros;
	}*/

	public void altaGuiaForestalBasica(GuiaForestal guia){

		this.getHibernateTemplate().saveOrUpdate(guia);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public List<GuiaForestal> recuperarGuiasForestalesPorProductor(long idProductor, boolean sinAnulados, String idPeriodo){

		Criteria criteria = getSession().createCriteria(GuiaForestal.class);
		criteria.createAlias("productorForestal", "productor");
		criteria.addOrder(Order.asc("nroGuia"));

		criteria.add(Restrictions.conjunction().add(
				Restrictions.eq("productor.id", idProductor)));
		if (sinAnulados){
			criteria.add(Restrictions.eq("anulado", false));	
		}
		if(idPeriodo!=null){
			criteria.add(Restrictions.eq("periodoForestal", idPeriodo));
		}
		
		List<GuiaForestal> lista = criteria.list();
		return lista;
	}

	public GuiaForestal recuperarGuiaForestal(long idGuiaForestal){

		return (GuiaForestal) getSession().get(GuiaForestal.class, idGuiaForestal);
	}

	public GuiaForestal recuperarGuiaForestalPorNroGuia(long nroGuiaForestal, boolean sinAnulados){

		Criteria criteria = getSession().createCriteria(GuiaForestal.class);
		criteria.add(Restrictions.eq("nroGuia", nroGuiaForestal));
		if (sinAnulados){
			criteria.add(Restrictions.eq("anulado", false));	
		}

		return (GuiaForestal) criteria.uniqueResult();			
	}

	public String registrarPagoBoletaDeposito(long idBoleta, String fechaPago){

		BoletaDeposito boletaDeposito = (BoletaDeposito) getSession().get(BoletaDeposito.class,
				idBoleta);

		//boletaDeposito.setFechaPago(new Date());
		boletaDeposito.setFechaPago(Fecha.stringDDMMAAAAToUtilDate(fechaPago));

		this.getHibernateTemplate().saveOrUpdate(boletaDeposito);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

		return Fecha.getFechaDDMMAAAASlash(Fecha.dateToStringDDMMAAAA(boletaDeposito
				.getFechaPago()));
	}

	public String reemplazarBoletaDeDeposito(long idBoleta, Long numero, String concepto,
			String area, String efectivoCheque, String fechaVencimiento) throws NegocioException {

			BoletaDeposito boletaDeposito = (BoletaDeposito) getSession().get(BoletaDeposito.class,
					idBoleta);

			Criteria criteria = getSession().createCriteria(BoletaDeposito.class);
			criteria.createAlias("guiaForestal", "guia");
			criteria.add(Restrictions.eq("numero", numero)).add(
					Restrictions.eq("guia.id", boletaDeposito.getGuiaForestal().getId()));

			List<BoletaDeposito> lista = criteria.list();

			if (numero == boletaDeposito.getNumero() || lista.size() == 0) {//Si no hay boletas que tengan el mismo nro de boleta

				boletaDeposito.setNumero(numero);
				boletaDeposito.setConcepto(concepto);
				boletaDeposito.setArea(area);
				boletaDeposito.setEfectivoCheque(efectivoCheque);
				boletaDeposito.setFechaVencimiento(Fecha.stringDDMMAAAAToDate(fechaVencimiento));

				this.getHibernateTemplate().saveOrUpdate(boletaDeposito);
				this.getHibernateTemplate().flush();
				this.getHibernateTemplate().clear();
			} else {
				throw new NegocioException(Constantes.ERROR_REEMPLAZAR_BOLETA_DEPOSITO_NRO_EXISTENTE);
			}

		return null;
	}

	public String registrarDevolucionValeTransporte(long idVale){

		ValeTransporte vale = (ValeTransporte) getSession().get(ValeTransporte.class, idVale);

		vale.setFechaDevolucion(new Date());

		this.getHibernateTemplate().saveOrUpdate(vale);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

		return Fecha
				.getFechaDDMMAAAASlash(Fecha.dateToStringDDMMAAAA(vale.getFechaDevolucion()));
	}

	public String registrarDevolucionYCompletarDatosValeTransporte(long idVale, String destino,
			String vehiculo, String marca, String dominio, String producto, int nroPiezas,
			double cantM3, String especie, String fechaDevolucion){

		ValeTransporte vale = (ValeTransporte) getSession().get(ValeTransporte.class, idVale);
		vale.setDestino(destino);
		vale.setVehiculo(vehiculo);
		vale.setMarca(marca);
		vale.setDominio(dominio);
		vale.setProducto(producto);
		vale.setNroPiezas(nroPiezas);
		vale.setCantidadMts(cantM3);
		vale.setEspecie(especie);
		vale.setFechaDevolucion(Fecha.stringDDMMAAAAToDate(fechaDevolucion));

		this.getHibernateTemplate().saveOrUpdate(vale);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

		return null;
	}

	public String reemplazarValeTransporte(long idVale, Long numeroVale, String origen,
			String destino, String vehiculo, String marca, String dominio, String producto,
			int nroPiezas, double cantM3, String especie, String fechaVencimiento)
			throws NegocioException {

			ValeTransporte vale = (ValeTransporte) getSession().get(ValeTransporte.class, idVale);

			Criteria criteria = getSession().createCriteria(ValeTransporte.class);
			criteria.createAlias("guiaForestal", "guia");
			criteria.add(Restrictions.eq("numero", numeroVale)).add(
					Restrictions.eq("guia.id", vale.getGuiaForestal().getId()));

			List<ValeTransporte> lista = criteria.list();

			if (numeroVale == vale.getNumero() || lista.size() == 0) {//Si no hay vales que tengan el mismo nro de vale 

				vale.setNumero(numeroVale);
				vale.setOrigen(origen);
				vale.setDestino(destino);
				vale.setVehiculo(vehiculo);
				vale.setMarca(marca);
				vale.setDominio(dominio);
				vale.setProducto(producto);
				vale.setNroPiezas(nroPiezas);
				vale.setCantidadMts(cantM3);
				vale.setEspecie(especie);
				vale.setFechaVencimiento(Fecha.stringDDMMAAAAToDate(fechaVencimiento));

				this.getHibernateTemplate().saveOrUpdate(vale);
				this.getHibernateTemplate().flush();
				this.getHibernateTemplate().clear();

			} else {
				throw new NegocioException(
						Constantes.ERROR_REEMPLAZAR_VALE_TRANSPORTE_NRO_EXISTENTE);
			}

		return null;
	}

	public boolean existeGuiaForestal(long nroGuia) {

		Criteria criteria = getSession().createCriteria(GuiaForestal.class);
		criteria.add(Restrictions.eq("nroGuia", nroGuia));
		
		//Tengo que comentarlo pq sino se puede crear una guia con un mismo nro de guia que otra
		//que este anulada
		//criteria.add(Restrictions.eq("anulado", false)); 

		List<GuiaForestal> guias = criteria.list();

		return (guias.size() > 0);
	}

	public boolean existeGuiaForestal(long idGuia, long nroGuia) {

		Criteria criteria = getSession().createCriteria(GuiaForestal.class);
		criteria.add(Restrictions.eq("nroGuia", nroGuia));
		criteria.add(Restrictions.ne("id", idGuia));

		List<GuiaForestal> guias = criteria.list();

		return (guias.size() > 0);
	}

	public boolean verificarBoletasDepositoVencidasImpagas(long idProductor){

		Date fechaActual = new Date();

		Criteria criteria = getSession().createCriteria(GuiaForestal.class)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		criteria.createAlias("productorForestal", "productor");
		criteria.createAlias("boletasDeposito", "listaBoletasDeposito");

		criteria.add(Restrictions.conjunction()
				.add(Restrictions.eq("anulado", false))					
				.add(Restrictions.eq("productor.id", idProductor))
				.add(Restrictions.isNull("listaBoletasDeposito.fechaPago"))
				.add(Restrictions.lt("listaBoletasDeposito.fechaVencimiento", fechaActual)));

		List<GuiaForestal> lista = criteria.list();

		return lista.size() > 0;
	}
	
	public void restablecerGuias(Long idGuia, Usuario usuario){
		
		GuiaForestal guia = this.recuperarGuiaForestal(idGuia);
		guia.setAnulado(false);
		
		for (BoletaDeposito boleta : guia.getBoletasDeposito()) {			
			boleta.setAnulado(false);
		}
		for(ValeTransporte vale : guia.getValesTransporte()){
			vale.setAnulado(false);
		}
		
		OperacionGuiaForestalDTO operacionDTO = new OperacionGuiaForestalDTO();
		operacionDTO.setFecha(Fecha.getFechaHoyDDMMAAAAhhmmssSlash());
		operacionDTO.setTipoOperacion(TipoOperacion.ALTA.getDescripcion());
		
		guia.setOperacionAlta(ProviderDominio.getOperacionGuiaForestal(operacionDTO,
																	   guia,usuario));		
		
		this.getHibernateTemplate().saveOrUpdate(guia);
	}
}
