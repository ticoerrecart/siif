package ar.com.siif.fachada;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import ar.com.siif.dao.UtilDAO;

public class UtilFachada implements IUtilFachada {

	private UtilDAO utilDAO;

	public UtilFachada() {
	}

	public UtilFachada(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	private static final HashSet<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	public static boolean isWrapperType(Class<?> clazz) {
		return WRAPPER_TYPES.contains(clazz);
	}

	private static HashSet<Class<?>> getWrapperTypes() {
		HashSet<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		return ret;
	}

	public String execute(String sql) {
		String strSelect = "select";
		int select = sql.toLowerCase().indexOf(strSelect);
		int from = sql.toLowerCase().indexOf("from");
		if (sql != null && (select > -1 || from == 0)) {
			sql = sql.replace(strSelect + " *", "");

			List results = utilDAO.executeSelect(sql);
			StringBuffer s = new StringBuffer("");
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				try {
					showFields(object, s);
					s.append("<br/>");
				} catch (Exception e) {
					s.append(" | " + e.toString());
				}
			}
			return s.toString();
		} else {
			// SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			// return String.valueOf(sqlQuery.executeUpdate());
			return String.valueOf(utilDAO.executeUpdateDelete(sql));
		}
	}

	public void showFields(Object o, StringBuffer s)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Class<?> clazz = o.getClass();

		for (Field field : clazz.getDeclaredFields()) {
			// you can also use .toGenericString() instead of .getName(). This
			// will
			// give you the type information as well.
			/*
			 * if (field.getClass().isPrimitive() ||
			 * isWrapperType(field.getType())) {
			 */
			s.append(field.getName() + ":'"
					+ PropertyUtils.getProperty(o, field.getName()) + "', ");
			// }

		}
	}

}
