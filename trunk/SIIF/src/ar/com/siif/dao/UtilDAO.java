package ar.com.siif.dao;

import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UtilDAO extends HibernateDaoSupport {

	public Integer execute(String sql) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.executeUpdate();
	}
}
