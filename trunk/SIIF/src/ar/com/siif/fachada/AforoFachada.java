package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.AforoDAO;
import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.dto.AforoDTO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.providers.ProviderDTO;
import ar.com.siif.providers.ProviderDominio;

public class AforoFachada implements IAforoFachada {

	private AforoDAO aforoDAO;
	private ITipoProductoForestalFachada tipoProductoFachada;

	public AforoFachada() {
	}

	public AforoFachada(AforoDAO aforoDAO, ITipoProductoForestalFachada pTipoProductoFachada) {

		this.aforoDAO = aforoDAO;
		this.tipoProductoFachada = pTipoProductoFachada;
	}

	public void altaAforo(AforoDTO aforo) throws NegocioException {

		TipoProducto tipoProducto = tipoProductoFachada.recuperarTipoProductoForestal(aforo.getTipoProducto().getId());

		aforoDAO.altaAforo(ProviderDominio.getAforo(aforo,tipoProducto));
	}

	public List<Aforo> recuperarAforos() {

		return aforoDAO.recuperarAforos();
	}

	public Aforo recuperarAforo(Long id) {

		return aforoDAO.recuperarAforo(id);
	}

	public void modificacionAforo(AforoDTO aforoDTO) throws NegocioException {
		
		Aforo aforo = aforoDAO.recuperarAforo(aforoDTO.getId());
		TipoProducto tipoProducto = tipoProductoFachada.recuperarTipoProductoForestal(aforoDTO.getTipoProducto().getId());
		
		aforoDAO.modificacionAforo(ProviderDominio.getAforo(aforo, aforoDTO, tipoProducto));
	}

	public boolean existeAforo(AforoDTO aforo, Long idTipoProducto) {

		return aforoDAO.existeAforo(aforo, idTipoProducto);
	}
	
	public String getValor(Long idFiscalizacion, String estado){
		
		try{
			Aforo aforo = aforoDAO.getAforo(idFiscalizacion,estado);
			
			return String.valueOf(aforo.getValorAforo());
			
		}catch(Exception e){
			return null;
		}	
	}
	
	public List<AforoDTO> recuperarAforosDTO(){
		
		List<AforoDTO> listaAforosDTO = new ArrayList<AforoDTO>();
		List<Aforo> listaAforos = aforoDAO.recuperarAforos();
		
		for (Aforo aforo : listaAforos) {
			listaAforosDTO.add(ProviderDTO.getAforoDTO(aforo));
		}
		
		return listaAforosDTO;
	}	
	
	public AforoDTO recuperarAforoDTO(Long id){
		
		Aforo aforo = aforoDAO.recuperarAforo(id);
		return ProviderDTO.getAforoDTO(aforo);
	}	
}
