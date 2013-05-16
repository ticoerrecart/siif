package ar.com.siif.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UtilDAO extends HibernateDaoSupport {

	public Integer executeUpdateDelete(String sql){
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.executeUpdate();
	}
	
	public List executeSelect(String sql){
		Query query = getSession().createQuery(sql);
		List results = query.list();
		return results;
	}

}
