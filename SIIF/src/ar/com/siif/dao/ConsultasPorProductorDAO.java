package ar.com.siif.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siif.negocio.GuiaForestal;
import ar.com.siif.negocio.exception.DataBaseException;
import ar.com.siif.utils.Constantes;

public class ConsultasPorProductorDAO extends HibernateDaoSupport {

	public List<GuiaForestal> recuperarGuiasForestales(long idProductor){
		
		Criteria criteria = getSession().createCriteria(GuiaForestal.class);

		criteria.createAlias("productorForestal", "productor");

		criteria.addOrder(Order.asc("nroGuia"));
		
		criteria.add(Restrictions.conjunction().add(Restrictions.eq("productor.id", idProductor)));
		criteria.add(Restrictions.eq("anulado", false));
		List<GuiaForestal> lista = criteria.list();

		return lista;
		
	}	
	
	public List<GuiaForestal> recuperarGuiasForestalesVigentes(long idProductor) throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(GuiaForestal.class);
	
			criteria.createAlias("productorForestal", "productor");
	
			criteria.addOrder(Order.asc("nroGuia"));
	
			Date fechaActual = new Date();
			
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("productor.id", idProductor))
					.add(Restrictions.ge("fechaVencimiento", fechaActual)));
			criteria.add(Restrictions.eq("anulado", false));
			List<GuiaForestal> lista = criteria.list();
	
			return lista;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (HibernateSystemException hse) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		}			
	}
	
	public List<GuiaForestal> recuperarGuiasForestalesNoVigentes(long idProductor) throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(GuiaForestal.class);
	
			criteria.createAlias("productorForestal", "productor");
	
			criteria.addOrder(Order.asc("nroGuia"));
	
			Date fechaActual = new Date();
			
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("productor.id", idProductor))
					.add(Restrictions.lt("fechaVencimiento", fechaActual)));
			criteria.add(Restrictions.eq("anulado", false));
			List<GuiaForestal> lista = criteria.list();
	
			return lista;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (HibernateSystemException hse) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		}			
	}	
	
	public List<GuiaForestal> recuperarGuiasForestalesAnuladas(long idProductor) throws DataBaseException {

		try{
			Criteria criteria = getSession().createCriteria(GuiaForestal.class);
	
			criteria.createAlias("productorForestal", "productor");
	
			criteria.add(Restrictions.eq("productor.id", idProductor)).add(Restrictions.eq("anulado", true));

			criteria.addOrder(Order.asc("nroGuia"));
			
			List<GuiaForestal> lista = criteria.list();
	
			return lista;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (HibernateSystemException hse) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		}			
	}
	
	public List<GuiaForestal> recuperarGuiasForestalesConDeudasAforo(long idProductor) throws DataBaseException{
		
		try{
			Criteria criteria = getSession().createCriteria(GuiaForestal.class)
								.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	
			criteria.createAlias("productorForestal", "productor");
			criteria.createAlias("boletasDeposito", "listaBoletasDeposito");
			
			criteria.addOrder(Order.asc("nroGuia"));
			
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("productor.id", idProductor))   
					.add(Restrictions.isNull("listaBoletasDeposito.fechaPago")));
			criteria.add(Restrictions.eq("anulado", false));
			List<GuiaForestal> lista = criteria.list();
	
			return lista;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (HibernateSystemException hse) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		}			
	}	
	
	
	public List<GuiaForestal> recuperarGuiasForestalesConDeudasVales(long idProductor) throws DataBaseException{
		
		try{
			Date fechaActual = new Date();
			
			Criteria criteria = getSession().createCriteria(GuiaForestal.class)
								.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	
			criteria.createAlias("productorForestal", "productor");
			criteria.createAlias("valesTransporte", "listaValesTransporte");
			
			criteria.addOrder(Order.asc("nroGuia"));
			
			criteria.add(Restrictions.conjunction().add(Restrictions.eq("productor.id", idProductor)) 
					//.add(Restrictions.ge("fechaVencimiento", fechaActual))				
					.add(Restrictions.isNull("listaValesTransporte.fechaDevolucion")));
			criteria.add(Restrictions.eq("anulado", false));
			List<GuiaForestal> lista = criteria.list();
			
			return lista;	
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (HibernateSystemException hse) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERAR_GUIAS_FORESTALES);
		}			
	}	
	
	@SuppressWarnings("deprecation")
	public byte[] pruebaJasper(String path){
		
		//ByteArrayOutputStream baos = null;
		byte[] bytes = null;
		
		try{
			InputStream input = new FileInputStream(path + File.separatorChar + "fiscalizacion.jasper");
			String fileImagen = path + File.separatorChar + "logo.GIF";
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);		
			Map parameters = new HashMap();
			parameters.put("fiscalizacion", 3);	
			parameters.put("PATH_SUB_REPORTES", path);
			
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, getSession().connection());
			
			//baos = new ByteArrayOutputStream();
			//baos.write(bytes, 0, bytes.length);		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;		
	}
}
