package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.enums.EstadoProducto;
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class TipoProductoForestalFachada implements
		ITipoProductoForestalFachada {

	private TipoProductoForestalDAO datosSistemaDAO;

	public TipoProductoForestalFachada() {
	}

	public TipoProductoForestalFachada(TipoProductoForestalDAO datosSistemaDAO) {
		this.datosSistemaDAO = datosSistemaDAO;
	}

	public void altaTipoProductoForestal(TipoProductoDTO tipoProductoForestalDTO)
			throws NegocioException {
		datosSistemaDAO.altaTipoProductoForestal(ProviderDominio
				.getTipoProductoForestal(tipoProductoForestalDTO));
	}

	public List<TipoProductoForestal> recuperarTiposProductoForestal() {
		return datosSistemaDAO.recuperarTiposProducto();
	}

	public TipoProductoForestal recuperarTipoProductoForestal(long id) {
		return datosSistemaDAO.recuperarTipoProductoForestal(id);
	}

	public TipoProductoDTO recuperarTipoProductoForestalDTO(long id) {
		return ProviderDTO.getTipoProductoDTO(datosSistemaDAO
				.recuperarTipoProductoForestal(id));
	}

	public void modificacionTipoProductoForestal(TipoProductoDTO tipoProductoDTO)
			throws NegocioException {
		TipoProductoForestal tipoProducto = datosSistemaDAO
				.recuperarTipoProductoForestal(tipoProductoDTO.getId());
		tipoProducto.setNombre(tipoProductoDTO.getNombre());
		datosSistemaDAO.modificacionTipoProductoForestal(tipoProducto);
	}

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO) {
		return datosSistemaDAO.existeTipoProductoForestal(
				tipoProdructoDTO.getNombre(), tipoProdructoDTO.getId());
	}

	public List<TipoProductoDTO> recuperarTiposProductoForestalDTO() {
		List<TipoProductoDTO> tiposProductoDTO = new ArrayList<TipoProductoDTO>();
		List<TipoProductoForestal> tiposProducto = datosSistemaDAO
				.recuperarTiposProducto();
		for (TipoProductoForestal tipoProducto : tiposProducto) {
			tiposProductoDTO.add(ProviderDTO.getTipoProductoDTO(tipoProducto));
		}
		return tiposProductoDTO;
	}

	public List<EstadoProducto> getEstadosProductos() {
		List<EstadoProducto> estadosProductos = new ArrayList<EstadoProducto>();
		// estadosProductos.add(EstadoProducto.Seco);
		estadosProductos.add(EstadoProducto.Verde);
		return estadosProductos;
	}

	public boolean existeTipoProductoExportacion(
			TipoProductoDTO tipoProdructoDTO) {
		return datosSistemaDAO.existeTipoProductoExportacion(
				tipoProdructoDTO.getNombre(), tipoProdructoDTO.getId());
	}

	public void altaTipoProductoExportacion(TipoProductoDTO tipoProductoDTO)
			throws NegocioException {
		datosSistemaDAO.altaTipoProductoExportacion(ProviderDominio
				.getTipoProductoExportacion(tipoProductoDTO));
	}

	public List<TipoProductoDTO> recuperarTiposProductoExportacionDTO() {
		List<TipoProductoDTO> tiposProductoDTO = new ArrayList<TipoProductoDTO>();
		List<TipoProductoExportacion> tiposProducto = datosSistemaDAO
				.recuperarTiposProductoExportacion();
		for (TipoProductoExportacion tipoProducto : tiposProducto) {

			tiposProductoDTO.add(ProviderDTO.getTipoProductoDTO(tipoProducto));
		}
		return tiposProductoDTO;
	}

	public TipoProductoDTO recuperarTipoProductoExportacionDTO(long id) {
		return ProviderDTO.getTipoProductoDTO(datosSistemaDAO
				.recuperarTipoProductoExportacion(id));
	}

	public void modificacionTipoProductoExportacion(
			TipoProductoDTO tipoProductoDTO) throws NegocioException {

		TipoProductoExportacion tipoProducto = datosSistemaDAO
				.recuperarTipoProductoExportacion(tipoProductoDTO.getId());
		tipoProducto.setNombre(tipoProductoDTO.getNombre());
		datosSistemaDAO.modificacionTipoProductoExportacion(tipoProducto);
	}

	public TipoProductoExportacion recuperarTipoProductoExportacion(long id) {
		return datosSistemaDAO.recuperarTipoProductoExportacion(id);
	}

}
