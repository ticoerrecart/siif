package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.PeriodoDAO;
import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.negocio.Periodo;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class PeriodoFachada implements IPeriodoFachada {

	private PeriodoDAO PeriodoDAO;

	public PeriodoFachada() {
	}

	public PeriodoFachada(PeriodoDAO laPeriodoAO) {
		this.PeriodoDAO = laPeriodoAO;
	}

	public List<Periodo> getPeriodos() {
		return PeriodoDAO.getPeriodos();
	}

	public Periodo getPeriodoPorId(Long id) {
		return PeriodoDAO.getPeriodoPorId(id);
	}

	public boolean existePeriodo(PeriodoDTO Periodo) {
		return PeriodoDAO.existePeriodo(Periodo.getPeriodo(), Periodo.getId());
	}

	public void altaPeriodo(PeriodoDTO PeriodoDTO) throws NegocioException {
		PeriodoDAO.alta_modficacion_Periodo(ProviderDominio
				.getPeriodo(PeriodoDTO));
	}

	public List<PeriodoDTO> getPeriodosDTO() {
		List<PeriodoDTO> periodosDTO = new ArrayList<PeriodoDTO>();
		List<Periodo> periodos = PeriodoDAO.getPeriodos();

		for (Periodo Periodo : periodos) {
			periodosDTO.add(ProviderDTO.getPeriodoDTO(Periodo));
		}

		return periodosDTO;
	}

	public PeriodoDTO getPeriodoDTOPorId(Long id) {
		Periodo Periodo = PeriodoDAO.getPeriodoPorId(id);
		return ProviderDTO.getPeriodoDTO(Periodo);
	}

	public void modificacionPeriodo(PeriodoDTO PeriodoDTO)
			throws NegocioException {
		Periodo Periodo = PeriodoDAO.getPeriodoPorId(PeriodoDTO.getId());
		PeriodoDAO.alta_modficacion_Periodo(ProviderDominio.getPeriodo(Periodo,
				PeriodoDTO));
	}
}
