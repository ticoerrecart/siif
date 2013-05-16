package ar.com.siif.fachada;

import ar.com.siif.dao.UtilDAO;

public class UtilFachada implements IUtilFachada {

	private UtilDAO utilDAO;

	public UtilFachada() {
	}

	public UtilFachada(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	@Override
	public Integer execute(String sql) {
		return utilDAO.execute(sql.trim());
	}

}
