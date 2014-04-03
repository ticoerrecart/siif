package ar.com.siif.utils;

import org.apache.commons.collections.Predicate;

public class ClaseTestPredicado implements Predicate {

	public boolean evaluate(Object arg0) {
		Class clase = (Class) arg0;
		return clase.getName().endsWith("Test");
	}

}
