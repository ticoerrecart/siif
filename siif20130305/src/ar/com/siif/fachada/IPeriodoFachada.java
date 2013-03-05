package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.negocio.Periodo;
import ar.com.siif.negocio.exception.NegocioException;

public interface IPeriodoFachada {

	public List<Periodo> getPeriodos();

	public Periodo getPeriodoPorId(Long id);

	public boolean existePeriodo(PeriodoDTO periodo);

	public void altaPeriodo(PeriodoDTO periodoDTO) throws NegocioException;

	public List<PeriodoDTO> getPeriodosDTO();

	public PeriodoDTO getPeriodoDTOPorId(Long id);

	public void modificacionPeriodo(PeriodoDTO periodoDTO)
			throws NegocioException;
}
