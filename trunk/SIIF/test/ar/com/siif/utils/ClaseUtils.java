package ar.com.siif.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public abstract class ClaseUtils {
	/**
	 * Calcula en forma recursiva las clases que est�n dentro de una carpeta.
	 * 
	 * @param path
	 * @param clases
	 * @throws ClassNotFoundException
	 */
	public static void getClases(String path, ArrayList clases)
			throws ClassNotFoundException {
		File f = new File(path);
		if (f.exists()) {
			if (f.isDirectory()) {
				String[] files = f.list();
				for (int i = 0; i < files.length; i++) {
					getClases(path + "/" + files[i], clases);
				}
			} else {
				if (f.getName().endsWith(".java")) {
					int primeraBarra = path.indexOf("/") + 1;
					String sClase = path.substring(primeraBarra);// saco hasta
																	// la
																	// primera
																	// "/"
					int ultimoPunto = sClase.lastIndexOf(".");
					sClase = sClase.substring(0, ultimoPunto).replace('/', '.');// elimino
																				// ".java"
					Class clase = Class.forName(sClase);
					clases.add(clase);
				}

			}
		}
	}

	/**
	 * Busca recursivamente todas las clases accesibles desde el contextLoader
	 * que pertenecen al package.
	 * 
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Collection getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = ClaseUtils.class.getClassLoader();
		// assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration resources = classLoader.getResources(path);
		List dirs = new ArrayList();
		while (resources.hasMoreElements()) {
			URL resource = (URL) resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		List classes = new ArrayList();
		for (Iterator iter = dirs.iterator(); iter.hasNext();) {
			File directory = (File) iter.next();
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}

	/**
	 * Busca recursivamente todas las clases que terminen en "Test" accesibles
	 * desde el contextLoader que pertenecen al package.
	 * 
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Collection getTestClasses(String packageName)
			throws ClassNotFoundException, IOException {
		Collection classes = getClasses(packageName);
		CollectionUtils.filter(classes, new ClaseTestPredicado());
		return classes;
	}

	/**
	 * Busca recursivamente todas las clases que NO terminen en "Test"
	 * accesibles desde el contextLoader que pertenecen al package.
	 * 
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	/*
	 * public static Collection getNoTestClasses(String packageName) throws
	 * ClassNotFoundException, IOException { Collection classes =
	 * getClasses(packageName); CollectionUtils.filter(classes, new
	 * ClaseNoTestPredicado()); return classes; }
	 */

	/**
	 * Metodo recursivo para encontrar, dado un directorio, todas las clases.
	 * 
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static Collection findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List classes = new ArrayList();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else {
				String className = file.getName();
				if (className.endsWith(".class")) {
					classes.add(Class.forName(packageName
							+ '.'
							+ file.getName().substring(0,
									file.getName().length() - 6)));
				}
			}

		}

		return classes;
	}
}
