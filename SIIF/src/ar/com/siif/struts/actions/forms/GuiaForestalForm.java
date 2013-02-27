package ar.com.siif.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.struts.utils.Validator;

public class GuiaForestalForm extends ActionForm {

	private GuiaForestalDTO guiaForestal;

	private List<BoletaDepositoDTO> boletasDeposito;

	private List<FiscalizacionDTO> listaFiscalizaciones;

	private List<SubImporteDTO> listaSubImportes;

	private List<RangoDTO> rangos;

	private String fechaVencimiento;

	private String tipoTerreno;

	public GuiaForestalForm() {

		guiaForestal = new GuiaForestalDTO();

		boletasDeposito = (List<BoletaDepositoDTO>) LazyList.decorate(
				new ArrayList(),
				FactoryUtils.instantiateFactory(BoletaDepositoDTO.class));

		listaFiscalizaciones = (List<FiscalizacionDTO>) LazyList.decorate(
				new ArrayList(),
				FactoryUtils.instantiateFactory(FiscalizacionDTO.class));

		listaSubImportes = (List<SubImporteDTO>) LazyList.decorate(
				new ArrayList(),
				FactoryUtils.instantiateFactory(SubImporteDTO.class));

		rangos = (List<RangoDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(RangoDTO.class));
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		GuiaForestalDTO guia = (GuiaForestalDTO) request.getAttribute("guia");

		if (guiaForestal.getNroGuia() == 0) {
			if (guia != null) {
				guiaForestal = guia;
			} else {
				guiaForestal = new GuiaForestalDTO();
			}
		}

		boletasDeposito = (List<BoletaDepositoDTO>) LazyList.decorate(
				new ArrayList(),
				FactoryUtils.instantiateFactory(BoletaDepositoDTO.class));

		listaFiscalizaciones = (List<FiscalizacionDTO>) LazyList.decorate(
				new ArrayList(),
				FactoryUtils.instantiateFactory(FiscalizacionDTO.class));

		listaSubImportes = (List<SubImporteDTO>) LazyList.decorate(
				new ArrayList(),
				FactoryUtils.instantiateFactory(SubImporteDTO.class));

		rangos = (List<RangoDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(RangoDTO.class));
	}

	public List<RangoDTO> getRangos() {
		return rangos;
	}

	public void setRangos(List<RangoDTO> rangos) {
		this.rangos = rangos;
	}

	public GuiaForestalDTO getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestalDTO guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

	public List<BoletaDepositoDTO> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

	public List<FiscalizacionDTO> getListaFiscalizaciones() {
		return listaFiscalizaciones;
	}

	public void setListaFiscalizaciones(
			List<FiscalizacionDTO> listaFiscalizaciones) {
		this.listaFiscalizaciones = listaFiscalizaciones;
	}

	public List<SubImporteDTO> getListaSubImportes() {
		return listaSubImportes;
	}

	public void setListaSubImportes(List<SubImporteDTO> listaSubImportes) {
		this.listaSubImportes = listaSubImportes;
	}
	
	
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(String tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}

	public void normalizarListaFiscalizaciones(){
		
		List<FiscalizacionDTO> listaEliminar =  new ArrayList<FiscalizacionDTO>();
		for (FiscalizacionDTO fiscalizacion : listaFiscalizaciones) {
			if(fiscalizacion != null && fiscalizacion.getId() == 0){
				listaEliminar.add(fiscalizacion);
			}
		}
		listaFiscalizaciones.removeAll(listaEliminar);
	}
	
}
