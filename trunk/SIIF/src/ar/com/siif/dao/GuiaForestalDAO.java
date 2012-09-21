package ar.com.siif.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.BoletaDeposito;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.Obrajero;
import ar.com.siif.negocio.PPF;
import ar.com.siif.negocio.ValeTransporte;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.negocio.exception.NegocioException;
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

	public void altaGuiaForestalBasica(GuiaForestal guia) throws DataBaseException {

		try{
			this.getHibernateTemplate().saveOrUpdate(guia);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_GUIA_FORESTAL);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_GUIA_FORESTAL);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_GUIA_FORESTAL);
		}			
	}

	public List<GuiaForestal> recuperarGuiasForestales() throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(GuiaForestal.class);
			List<GuiaForestal> guiasForestales = criteria.list();
	
			return guiasForestales;

		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		}			
	}

	public GuiaForestal recuperarGuiaForestal(long idGuiaForestal) throws DataBaseException {
		
		try{
			return (GuiaForestal) getSession().get(GuiaForestal.class, idGuiaForestal);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIA_FORESTAL);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIA_FORESTAL);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIA_FORESTAL);
		}			
	}

	public GuiaForestal recuperarGuiaForestalPorNroGuia(int nroGuiaForestal) throws DataBaseException{
		
		try{
			Criteria criteria = getSession().createCriteria(GuiaForestal.class);
			criteria.add(Restrictions.eq("nroGuia", nroGuiaForestal));
			
			List<GuiaForestal> guias = criteria.list();
			
			return (guias.get(0));
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIA_FORESTAL);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIA_FORESTAL);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIA_FORESTAL);
		}			
	}	
	
	public String registrarPagoBoletaDeposito(long idBoleta) throws DataBaseException {

		try{
			BoletaDeposito boletaDeposito = (BoletaDeposito)getSession().get(BoletaDeposito.class,idBoleta);
			
			boletaDeposito.setFechaPago(new Date());
			
			this.getHibernateTemplate().saveOrUpdate(boletaDeposito);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
	
			return Fecha.getFechaDDMMAAAASlash(Fecha.dateToStringDDMMAAAA(boletaDeposito.getFechaPago()));

		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_PAGO_BOLETA_DEPOSITO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_PAGO_BOLETA_DEPOSITO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_PAGO_BOLETA_DEPOSITO);
		}			
	}
	
	public String reemplazarBoletaDeDeposito(long idBoleta, int numero, String concepto,
			String area, String efectivoCheque, String fechaVencimiento) throws DataBaseException {

		try{
			BoletaDeposito boletaDeposito = (BoletaDeposito)getSession().get(BoletaDeposito.class,idBoleta);
			
			Criteria criteria = getSession().createCriteria(BoletaDeposito.class);
			criteria.createAlias("guiaForestal", "guia");
			criteria.add(Restrictions.eq("numero", numero))
					.add(Restrictions.eq("guia.id", boletaDeposito.getGuiaForestal().getId())); 
			
			List<BoletaDeposito> lista = criteria.list();			
					
			if(numero == boletaDeposito.getNumero() || lista.size() == 0){//Si no hay boletas que tengan el mismo nro de boleta
							
				boletaDeposito.setNumero(numero);
				boletaDeposito.setConcepto(concepto);
				boletaDeposito.setArea(area);
				boletaDeposito.setEfectivoCheque(efectivoCheque);
				boletaDeposito.setFechaVencimiento(Fecha.stringDDMMAAAAToDate(fechaVencimiento));
				
				this.getHibernateTemplate().saveOrUpdate(boletaDeposito);
				this.getHibernateTemplate().flush();
				this.getHibernateTemplate().clear();
			}
			else{
				throw new DataBaseException(Constantes.ERROR_REEMPLAZAR_BOLETA_DEPOSITO_NRO_EXISTENTE);
			}
			
		} catch (DataBaseException dbe) {
			throw dbe;			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_REEMPLAZAR_BOLETA_DEPOSITO);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_REEMPLAZAR_BOLETA_DEPOSITO);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_REEMPLAZAR_BOLETA_DEPOSITO);
		}

		return null;		
	}	
	
	public String registrarDevolucionValeTransporte(long idVale) throws DataBaseException{
		
		try{
			ValeTransporte vale = (ValeTransporte)getSession().get(ValeTransporte.class,idVale);
			
			vale.setFechaDevolucion(new Date());
			
			this.getHibernateTemplate().saveOrUpdate(vale);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
	
			return Fecha.getFechaDDMMAAAASlash(Fecha.dateToStringDDMMAAAA(vale.getFechaDevolucion()));

		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_DEVOLUCION_VALE_TRANSPORTE);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_DEVOLUCION_VALE_TRANSPORTE);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_DEVOLUCION_VALE_TRANSPORTE);
		}			
	}

	public String registrarDevolucionYCompletarDatosValeTransporte(long idVale,
			String destino, String vehiculo, String marca, String dominio,
			String producto, int nroPiezas, double cantM3, String especie,
			String fechaDevolucion) throws DataBaseException{
		
		try{
			ValeTransporte vale = (ValeTransporte)getSession().get(ValeTransporte.class,idVale);
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
	
			return fechaDevolucion;

		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_DEVOLUCION_VALE_TRANSPORTE);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_DEVOLUCION_VALE_TRANSPORTE);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_DEVOLUCION_VALE_TRANSPORTE);
		}			
	}
	
	
	
	public String reemplazarValeTransporte(long idVale, int numeroVale, String origen,
			String destino, String vehiculo, String marca, String dominio, String producto,
			int nroPiezas, double cantM3, String especie, String fechaVencimiento) throws DataBaseException {

		try{
			ValeTransporte vale = (ValeTransporte)getSession().get(ValeTransporte.class, idVale);
			
			Criteria criteria = getSession().createCriteria(ValeTransporte.class);
			criteria.createAlias("guiaForestal", "guia");
			criteria.add(Restrictions.eq("numero", numeroVale))
					.add(Restrictions.eq("guia.id", vale.getGuiaForestal().getId())); 
			
			List<ValeTransporte> lista = criteria.list();			
			
			if(numeroVale == vale.getNumero() || lista.size() == 0){//Si no hay vales que tengan el mismo nro de vale 
								
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
				
			}
			else{
				throw new DataBaseException(Constantes.ERROR_REEMPLAZAR_VALE_TRANSPORTE_NRO_EXISTENTE);
			}

		} catch (DataBaseException dbe) {
			throw dbe;			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_REEMPLAZAR_VALE_TRANSPORTE);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_REEMPLAZAR_VALE_TRANSPORTE);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_REEMPLAZAR_VALE_TRANSPORTE);
		}		
		return null;
	}

	public boolean existeGuiaForestal(int nroGuia) {

		Criteria criteria = getSession().createCriteria(GuiaForestal.class);
		criteria.add(Restrictions.eq("nroGuia", nroGuia));
		
		List<GuiaForestal> guias = criteria.list();
		
		return (guias.size() > 0);
	}	
}
