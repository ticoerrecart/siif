package ar.com.siif.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.struts.utils.Validator;
import ar.com.siif.utils.Constantes;

public class FiscalizacionForm extends ActionForm {

	//-------------//
	private FiscalizacionDTO fiscalizacionDTO;

	private List<MuestraDTO> muestrasDTO;

	//-------------//

	public FiscalizacionForm() {

		//fiscalizacion = new Fiscalizacion();	

		/*muestras = (List<Muestra>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(Muestra.class));*/

		fiscalizacionDTO = new FiscalizacionDTO();

		muestrasDTO = (List<MuestraDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(MuestraDTO.class));

	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		/*Fiscalizacion f = (Fiscalizacion)request.getSession().getAttribute("fiscalizacion");
		
		muestras = (List<Muestra>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(Muestra.class));*/

		FiscalizacionDTO fDTO = (FiscalizacionDTO) request.getSession().getAttribute(
				"fiscalizacionDTO");

		if (fDTO != null) {
			//fiscalizacion = f;
			fiscalizacionDTO = fDTO;
		} else {
			//fiscalizacion = new Fiscalizacion();
			fiscalizacionDTO = new FiscalizacionDTO();
		}

		muestrasDTO = (List<MuestraDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(MuestraDTO.class));
	}

	public boolean validar(StringBuffer error) {

		boolean ok2 = true;
		boolean ok3 = true;
		boolean ok4 = true;
		boolean ok5 = true;
		boolean ok6 = true;
		boolean ok7 = true;
		boolean ok8 = true;
		boolean ok9 = true;
		boolean ok10 = true;
		boolean ok11 = true;
		boolean ok12 = true;
		boolean ok13 = true;
		boolean ok14 = true;
		boolean ok15 = true;
		boolean ok16 = true;

		ok2 = Validator.validarComboRequerido("-1",
				Long.toString(fiscalizacionDTO.getProductorForestal().getId()),
				"Productor Forestal", error);

		ok5 = Validator.requerido(fiscalizacionDTO.getFecha(), "Fecha", error)
				&& Validator.validarFechaValida(fiscalizacionDTO.getFecha(), "Fecha", error);

		ok3 = Validator.requerido(fiscalizacionDTO.getPeriodoForestal(), "Periodo Forestal", error);

		ok11 = Validator.validarComboRequerido("-1",
				Long.toString(fiscalizacionDTO.getTipoProducto().getId()), "Tipo de Producto",
				error);

		ok9 = Validator.validarDoubleMayorQue(0,
				Double.toString(fiscalizacionDTO.getCantidadMts()), "Cantidad Mts3", error);

		if (fiscalizacionDTO.getTipoProducto().getId().longValue() == Constantes.LENIA_ID) {
			ok8 = Validator.validarEnteroMayorQue(0,
					Integer.toString(fiscalizacionDTO.getCantidadUnidades()), "Cantidad Unidades",
					error);
			ok10 = Validator.validarEnteroMayorQue(0,
					Integer.toString(fiscalizacionDTO.getTamanioMuestra()), "Tamaño de la Muestra",
					error);
		}

		ok11 = Validator.validarComboRequerido("-1",
				Long.toString(fiscalizacionDTO.getOficinaAlta().getId()), "Oficina", error);

		//ok12 = Validator.validarComboRequerido("-1",Long.toString(idPlanManejoForestal), "Plan de Manejo Forestal", error);

		//ok13 = Validator.validarComboRequerido("-1",Long.toString(idTranzon), "Tranzón", error);

		//ok14 = Validator.validarComboRequerido("-1",Long.toString(idMarcacion), "Marcación", error);

		ok15 = Validator.validarComboRequerido("-1",
				Long.toString(fiscalizacionDTO.getRodal().getId()), "Rodal", error);

		if (ok10) {
			ok16 = Validator.validarMuestras(this.getMuestrasDTO(), fiscalizacionDTO
					.getTipoProducto().getId(), error);
		}

		//VALIDACIONES FISCALIZACION
		return ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10 && ok11 && ok12 && ok13
				&& ok14 && ok15 && ok16;
	}

	public FiscalizacionDTO getFiscalizacionDTO() {
		return fiscalizacionDTO;
	}

	public void setFiscalizacionDTO(FiscalizacionDTO fiscalizacionDTO) {
		this.fiscalizacionDTO = fiscalizacionDTO;
	}

	public List<MuestraDTO> getMuestrasDTO() {
		return muestrasDTO;
	}

	public void setMuestrasDTO(List<MuestraDTO> muestrasDTO) {
		this.muestrasDTO = muestrasDTO;
	}

}
