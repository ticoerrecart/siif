package ar.com.siif.fachada;

import java.util.List;

import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.negocio.Periodo;
import ar.com.siif.negocio.exception.NegocioException;

public interface IPeriodoFachada {

	public List<Periodo> getPeriodos() throws NegocioException;

	public Periodo getPeriodoPorId(Long id) throws NegocioException;

	public boolean existePeriodo(PeriodoDTO periodo);

	public void altaPeriodo(PeriodoDTO periodoDTO) throws NegocioException;

	public List<PeriodoDTO> getPeriodosDTO() throws NegocioException;

	public PeriodoDTO getPeriodoDTOPorId(Long id) throws NegocioException;

	public void modificacionPeriodo(PeriodoDTO periodoDTO)
			throws NegocioException;
}
