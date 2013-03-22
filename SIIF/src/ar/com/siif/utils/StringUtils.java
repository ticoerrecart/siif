package ar.com.siif.utils;

public abstract class StringUtils {

	public static String nullToBlank(String s) {
		return s == null ? "" : s;
	}
}
