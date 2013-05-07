package ar.com.siif.fachada;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import ar.com.siif.negocio.exception.NegocioException;

public class FiscalizacionFachadaTest extends AbstractDependencyInjectionSpringContextTests {
	
	IFiscalizacionFachada ff;
	
	public FiscalizacionFachadaTest(){
		this.setAutowireMode(AUTOWIRE_BY_NAME);
		this.ff = (IFiscalizacionFachada) this.getApplicationContext().getBean("fiscalizacionFachada");
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "applicationContext.xml" };
	}
	
	public void testAnularFiscalizaciones() {
		Long[] ids = new Long[]{new Long(23)};
		
		try {
			ff.anularFiscalizaciones(ids);
		} catch (NegocioException e) {
			fail(e.toString());
		}
		
	}

	
	
/*	public void testAltaFiscalizacion(){
		FiscalizacionDTO fiscalizacion = new FiscalizacionDTO();
		fiscalizacion.setLocalizacion(localizacion)
		MuestraDTO muestraDTO = new MuestraDTO(); 
		
		ff.altaFiscalizacion(fiscalizacion, muestrasDTO)
	}
	*/
}
