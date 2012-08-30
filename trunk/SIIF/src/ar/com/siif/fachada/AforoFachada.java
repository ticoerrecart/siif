package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siif.dao.AforoDAO;
import ar.com.siif.dao.TipoProductoForestalDAO;
import ar.com.siif.dto.AforoDTO;
import ar.com.siif.negocio.Aforo;
import ar.com.siif.negocio.TipoProducto;
import ar.com.siif.negocio.exception.DataBaseException;
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

		try{
			TipoProducto tipoProducto = tipoProductoFachada.recuperarTipoProductoForestal(aforo.getTipoProducto().getId());
	
			aforoDAO.altaAforo(ProviderDominio.getAforo(aforo,tipoProducto));
			
		}catch(DataBaseException e){
			throw new NegocioException(e.getMessage());
		}				
	}

	public List<Aforo> recuperarAforos() throws NegocioException {

		try{
			return aforoDAO.recuperarAforos();
			
		}catch(DataBaseException e){
			throw new NegocioException(e.getMessage());
		}			
	}

	public Aforo recuperarAforo(Long id) throws NegocioException {

		try{
			return aforoDAO.recuperarAforo(id);
			
		}catch(DataBaseException e){
			throw new NegocioException(e.getMessage());
		}			
	}

	public void modificacionAforo(AforoDTO aforoDTO) throws NegocioException {
		
		try{
			Aforo aforo = aforoDAO.recuperarAforo(aforoDTO.getId());
			TipoProducto tipoProducto = tipoProductoFachada.recuperarTipoProductoForestal(aforoDTO.getTipoProducto().getId());
			
			aforoDAO.modificacionAforo(ProviderDominio.getAforo(aforo, aforoDTO, tipoProducto));
			
		}catch(DataBaseException e){
			throw new NegocioException(e.getMessage());
		}			
	}

	public boolean existeAforo(AforoDTO aforo, Long idTipoProducto){
		
		return aforoDAO.existeAforo(aforo, idTipoProducto);
	}
	
	public String getValor(Long idFiscalizacion, String estado) throws NegocioException{
		
		try{
			Aforo aforo = aforoDAO.getAforo(idFiscalizacion,estado);
			
			if(aforo != null){
				return String.valueOf(aforo.getValorAforo());
			}	
			
			return null;
			
		}catch(DataBaseException e){
			throw new NegocioException(e.getMessage());
		}	
	}
	
	public List<AforoDTO> recuperarAforosDTO() throws NegocioException{
		
		try{
			List<AforoDTO> listaAforosDTO = new ArrayList<AforoDTO>();
			List<Aforo> listaAforos = aforoDAO.recuperarAforos();
			
			for (Aforo aforo : listaAforos) {
				listaAforosDTO.add(ProviderDTO.getAforoDTO(aforo));
			}
			
			return listaAforosDTO;
			
		}catch(DataBaseException e){
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public AforoDTO recuperarAforoDTO(Long id) throws NegocioException{
		try{
			Aforo aforo = aforoDAO.recuperarAforo(id);
			return ProviderDTO.getAforoDTO(aforo);
			
		}catch(DataBaseException e){
			throw new NegocioException(e.getMessage());
		}			
	}	
}
