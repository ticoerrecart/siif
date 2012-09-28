package ar.com.siif.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import ar.com.siif.fachada.IEntidadFachada;
import ar.com.siif.fachada.IFiscalizacionFachada;
import ar.com.siif.fachada.IUsuarioFachada;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.negocio.ItemMenu;
import ar.com.siif.negocio.Muestra;
import ar.com.siif.negocio.Usuario;
import ar.com.siif.negocio.exception.DataBaseException;

public class MenuDAOTest extends AbstractDependencyInjectionSpringContextTests {

	MenuDAO menuDAO;
	UsuarioDAO usuarioDAO;
	FiscalizacionDAO fiscalizacionDAO;
	IEntidadFachada fe;
	IFiscalizacionFachada ff;
	IUsuarioFachada fu;
	
	public MenuDAOTest() {
		this.setAutowireMode(AUTOWIRE_BY_NAME);
		this.menuDAO = (MenuDAO) this.getApplicationContext()
				.getBean("menuDAO");
		this.usuarioDAO = (UsuarioDAO) this.getApplicationContext().getBean("usuarioDAO");
		this.fiscalizacionDAO = (FiscalizacionDAO) this.getApplicationContext().getBean("fiscalizacionDAO");
		this.ff = (IFiscalizacionFachada) this.getApplicationContext().getBean("fiscalizacionFachada");
		this.fe = (IEntidadFachada) this.getApplicationContext().getBean("entidadFachada");
		this.fu = (IUsuarioFachada) this.getApplicationContext().getBean("usuarioFachada");
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "applicationContext.xml" };
	}

	public void testGetItemsMenu() {
		ItemMenu im = (ItemMenu) menuDAO.getHibernateTemplate().get(ItemMenu.class, 1L);
		assertNotNull(im);
	}

	public void xtestIdentidad() {
		try {
		Usuario u = this.usuarioDAO.getUsuario(3L);
		System.out.println(u.getEntidad().getNombre());
		Fiscalizacion f = this.fiscalizacionDAO.recuperarFiscalizacion(1L);
		System.out.println(f.getProductorForestal().getNombre());
		this.fiscalizacionDAO.getHibernateTemplate().saveOrUpdate(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void testIdentidad2() {
		try {
			Fiscalizacion fiscalizacion = new Fiscalizacion();
			fiscalizacion.setCantidadMts(50);
			fiscalizacion.setCantidadUnidades(50);
			fiscalizacion.setTamanioMuestra(1);
			 
			
			Entidad e2 = fe.getEntidad(1L);
			Usuario u = fu.getUsuario(1L);
			
		
			List<Muestra> muestras = new ArrayList<Muestra>();
			
			Muestra m = new Muestra();
			//Muestra m2 = ff.getNewMuestra(fiscalizacion, m);
			
			m.setFiscalizacion(fiscalizacion);
			muestras.add(m);
			fiscalizacion.setMuestra(muestras);
			
			ff.altaFiscalizacion(fiscalizacion);
			
			
			fiscalizacion.setOficinaAlta(e2);
			fiscalizacion.setUsuario(u);
			
			//ff.actFiscalizacion(fiscalizacion);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	
	
}
