package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.dto.TipoProductoDTO;
import ar.com.siif.dto.TipoProductoForestalDTO;
import ar.com.siif.enums.EstadoProducto;
import ar.com.siif.negocio.TipoProductoExportacion;
import ar.com.siif.negocio.TipoProductoForestal;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;
import ar.com.siif.utils.MyLogger;

public class TipoProductoForestalFachada implements
		ITipoProductoForestalFachada {

	private TipoProductoForestalDAO datosSistemaDAO;

	public TipoProductoForestalFachada() {
	}

	public TipoProductoForestalFachada(TipoProductoForestalDAO datosSistemaDAO) {
		this.datosSistemaDAO = datosSistemaDAO;
	}

	public void altaTipoProductoForestal(
			TipoProductoForestalDTO tipoProductoForestalDTO)
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

	public TipoProductoForestalDTO recuperarTipoProductoForestalDTO(long id) {
		return ProviderDTO.getTipoProductoForestalDTO(datosSistemaDAO
				.recuperarTipoProductoForestal(id));
	}

	public void modificacionTipoProductoForestal(
			TipoProductoForestalDTO tipoProductoForestalDTO)
			throws NegocioException {
		TipoProductoForestal tipoProducto = datosSistemaDAO
				.recuperarTipoProductoForestal(tipoProductoForestalDTO.getId());

		tipoProducto.setNombre(tipoProductoForestalDTO.getNombre());
		tipoProducto.setCantDiametros(tipoProductoForestalDTO
				.getCantDiametros());
		tipoProducto.setDiam1Desde(tipoProductoForestalDTO.getDiam1Desde());
		tipoProducto.setDiam1Hasta(tipoProductoForestalDTO.getDiam1Hasta());
		tipoProducto.setDiam2Desde(tipoProductoForestalDTO.getDiam2Desde());
		tipoProducto.setDiam2Hasta(tipoProductoForestalDTO.getDiam2Hasta());
		tipoProducto.setLargoDesde(tipoProductoForestalDTO.getLargoDesde());
		tipoProducto.setLargoHasta(tipoProductoForestalDTO.getLargoHasta());
		tipoProducto.setEsDeExportacion(tipoProductoForestalDTO.isEsDeExportacion());
		tipoProducto.setHabilitado(tipoProductoForestalDTO.isHabilitado());
		
		datosSistemaDAO.modificacionTipoProductoForestal(tipoProducto);
	}

	public boolean existeTipoProductoForestal(TipoProductoDTO tipoProdructoDTO) {
		return datosSistemaDAO.existeTipoProductoForestal(
				tipoProdructoDTO.getNombre(), tipoProdructoDTO.getId());
	}

	public List<TipoProductoForestalDTO> recuperarTiposProductoForestalDTO() {
		List<TipoProductoForestalDTO> tipoProductoForestalDTO = new ArrayList<TipoProductoForestalDTO>();
		List<TipoProductoForestal> tiposProducto = datosSistemaDAO
				.recuperarTiposProducto();
		for (TipoProductoForestal tipoProducto : tiposProducto) {
			tipoProductoForestalDTO.add(ProviderDTO
					.getTipoProductoForestalDTO(tipoProducto));
		}
		return tipoProductoForestalDTO;
	}

	public List<EstadoProducto> getEstadosProductos() {
		List<EstadoProducto> estadosProductos = new ArrayList<EstadoProducto>();

		estadosProductos.add(EstadoProducto.BP);
		estadosProductos.add(EstadoProducto.BS);
		estadosProductos.add(EstadoProducto.SP);
		estadosProductos.add(EstadoProducto.TSI);
		estadosProductos.add(EstadoProducto.VV);
		
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

	public int getCantidadDiametros(long idTipoProductoForestal)
			throws NegocioException {
		try {
			TipoProductoForestal tipoProductoForestal = datosSistemaDAO
					.recuperarTipoProductoForestal(idTipoProductoForestal);

			return tipoProductoForestal.getCantDiametros();
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}
	
	public List<TipoProductoForestalDTO> recuperarTiposProductoForestalHabInhabDTO(){
		
		List<TipoProductoForestalDTO> tipoProductoForestalDTO = new ArrayList<TipoProductoForestalDTO>();
		List<TipoProductoForestal> tiposProducto = datosSistemaDAO
				.recuperarTiposProductoForestalHabInhabDTO();
		for (TipoProductoForestal tipoProducto : tiposProducto) {
			tipoProductoForestalDTO.add(ProviderDTO
					.getTipoProductoForestalDTO(tipoProducto));
		}
		return tipoProductoForestalDTO;		
	}
}
