package ar.com.siif;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ar.com.siif.utils.ClaseUtils;

public class AllTests extends TestCase {

	/**
	 * Test Suite para el DSISIC. Toma todas las clases que terminen con la
	 * palabra "Test" que est�n en alguno de los packages definidos en
	 * cactus.sample.packages.properties.
	 * 
	 * @return Test
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for SIIF");
		// $JUnit-BEGIN$
		// suite.addTestSuite(SampleServletTest.class);
		try {
			// busco las clases con el ClassLoader.
			Collection todosLosTests = getClasesFromClassLoader();

			// si no encontr� las clases con el ClassLoader, entonces las busco
			// por Carpeta.
			/*if (todosLosTests.size() == 0) {
				todosLosTests = getClasesFromFolder();
			}*/

			// agrego las clases recuperadas de alguna de las dos formas a la
			// Suite.
			for (Iterator iter = todosLosTests.iterator(); iter.hasNext();) {
				Class clase = (Class) iter.next();
				suite.addTestSuite(clase);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// $JUnit-END$
		System.out.println("Cant. de tests: " + suite.countTestCases());

		return suite;
	}

	/**
	 * Recupero el Bundle con la lista de packages.
	 * 
	 * @return
	 */
	private static ResourceBundle getBundle() {
		ResourceBundle resource = ResourceBundle
				.getBundle("ar.com.siif.packages");
		return resource;
	}

	/**
	 * Recupero las clases con el ClassLoader.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Collection getClasesFromClassLoader()
			throws ClassNotFoundException, IOException {
		List tests = new ArrayList();
		ResourceBundle resource = getBundle();
		Enumeration enumeration = resource.getKeys();
		while (enumeration.hasMoreElements()) {
			String paquete = (String) enumeration.nextElement();
			tests.addAll(ClaseUtils.getTestClasses(paquete));
		}
		return tests;
	}

	/**
	 * Recupero las clases desde las carpetas.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static Collection getClasesFromFolder()
			throws ClassNotFoundException {
		List tests = new ArrayList();
		ResourceBundle resource = getBundle();
		Enumeration enumeration = resource.getKeys();
		while (enumeration.hasMoreElements()) {
			String paquete = (String) enumeration.nextElement();
			paquete = "junit/" + paquete.replace('.', '/');
			ArrayList clases = new ArrayList();
			ClaseUtils.getClases(paquete, clases);
			tests.addAll(clases);
		}
		return tests;
	}
}
