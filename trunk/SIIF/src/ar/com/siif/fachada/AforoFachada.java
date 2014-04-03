package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.AforoDAO;
import ar.com.siif.dto.AforoDTO;
import ar.com.siif.dto.AforoNuevoDTO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.AforoNuevo;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.utils.MyLogger;

public class AforoFachada implements IAforoFachada {

	private AforoDAO aforoDAO;
	private ITipoProductoForestalFachada tipoProductoFachada;

	public AforoFachada() {
	}

	public AforoFachada(AforoDAO aforoDAO,
			ITipoProductoForestalFachada pTipoProductoFachada) {

		this.aforoDAO = aforoDAO;
		this.tipoProductoFachada = pTipoProductoFachada;
	}

	public void altaAforo(AforoDTO aforo) throws NegocioException {

		TipoProductoForestal tipoProducto = tipoProductoFachada
				.recuperarTipoProductoForestal(aforo.getTipoProducto().getId());
		aforoDAO.altaAforo(ProviderDominio.getAforo(aforo, tipoProducto));
	}

	public List<Aforo> recuperarAforos() {

		return aforoDAO.recuperarAforos();
	}

	public Aforo recuperarAforo(Long id) {

		return aforoDAO.recuperarAforo(id);
	}

	public AforoNuevo recuperarAforoNuevo(Long id) {
		return aforoDAO.recuperarAforoNuevo(id);
	}

	public void modificacionAforo(AforoDTO aforoDTO) throws NegocioException {

		Aforo aforo = aforoDAO.recuperarAforo(aforoDTO.getId());
		TipoProductoForestal tipoProducto = tipoProductoFachada
				.recuperarTipoProductoForestal(aforoDTO.getTipoProducto()
						.getId());

		aforoDAO.modificacionAforo(ProviderDominio.getAforo(aforo, aforoDTO,
				tipoProducto));
	}

	public void modificacionAforoNuevo(AforoNuevoDTO aforoDTO) {
		AforoNuevo aforoNuevo = aforoDAO.recuperarAforoNuevo(aforoDTO.getId());
		if (aforoDTO.getMonto().doubleValue() > 0.0) {
			aforoNuevo.setMonto(aforoDTO.getMonto());
		} else {
			if (aforoDTO.getPorcentaje().intValue() > 0) {

				aforoNuevo.setPorcentaje(aforoDTO.getPorcentaje());
			}
		}

	}

	public boolean existeAforo(AforoDTO aforo, Long idTipoProducto) {

		return aforoDAO.existeAforo(aforo, idTipoProducto);
	}

	public String getValor(String estado, Long idTipoProducto,
			Long idProdForestal) throws NegocioException {

		try {
			Aforo aforo = aforoDAO.getAforo(estado, idTipoProducto,
					idProdForestal);

			if (aforo != null) {
				return String.valueOf(aforo.getValorAforo());
			}
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}

		return null;
	}

	public AforoNuevo recuperarAforoNuevoBasico() {
		return aforoDAO.recuperarAforoNuevoBasico();
	}

	public String getValorAforoNuevo(String tipoDeAforo, String comercializaEnProvincia)
			throws NegocioException {

		try {
			AforoNuevo aforoNuevo = null;
			boolean comercializaEnProv = Boolean
					.parseBoolean(comercializaEnProvincia);
			if (comercializaEnProv) {
				return "0";
			} else {
				aforoNuevo = aforoDAO.getAforoNuevo(tipoDeAforo);
			}

			if (aforoNuevo != null) {
				AforoNuevo aforoNuevoBasico = this.recuperarAforoNuevoBasico();
				return String.valueOf(aforoNuevo
						.getValorSegunMontoOPorcentaje(aforoNuevoBasico.getMonto()));
			}

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}

		return null;
	}

	public List<AforoDTO> recuperarAforosDTO() {

		List<AforoDTO> listaAforosDTO = new ArrayList<AforoDTO>();
		List<Aforo> listaAforos = aforoDAO.recuperarAforos();

		for (Aforo aforo : listaAforos) {
			listaAforosDTO.add(ProviderDTO.getAforoDTO(aforo));
		}

		return listaAforosDTO;
	}

	public List<AforoNuevoDTO> recuperarAforosNuevosDTO() {

		List<AforoNuevoDTO> listaAforosDTO = new ArrayList<AforoNuevoDTO>();
		List<AforoNuevo> listaAforos = aforoDAO.recuperarAforosNuevos();

		for (AforoNuevo aforo : listaAforos) {
			listaAforosDTO.add(ProviderDTO.getAforoNuevoDTO(aforo));
		}

		return listaAforosDTO;
	}

	public AforoDTO recuperarAforoDTO(Long id) {

		Aforo aforo = aforoDAO.recuperarAforo(id);
		return ProviderDTO.getAforoDTO(aforo);
	}

	public AforoNuevoDTO recuperarAforoNuevoDTO(Long id) {
		AforoNuevo aforo = aforoDAO.recuperarAforoNuevo(id);
		return ProviderDTO.getAforoNuevoDTO(aforo);
	}
}
