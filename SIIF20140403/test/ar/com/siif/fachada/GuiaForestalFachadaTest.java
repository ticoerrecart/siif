package ar.com.siif.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.PMFDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.negocio.exception.NegocioException;
import ar.com.siif.utils.ConstantesTest;
import ar.com.siif.utils.DateUtils;
import ar.com.siif.utils.GuiaForestalTestUtil;

import com.thoughtworks.xstream.XStream;

public class GuiaForestalFachadaTest extends
		AbstractDependencyInjectionSpringContextTests {

	IGuiaForestalFachada gff;

	public GuiaForestalFachadaTest() {
		this.setAutowireMode(AUTOWIRE_BY_NAME);
		this.gff = (IGuiaForestalFachada) this.getApplicationContext().getBean(
				"guiaForestalFachada");
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "applicationContext.xml" };
	}

	public void testAltaGuiaForestalBasica() {
		try {

			GuiaForestalDTO guia = GuiaForestalTestUtil
					.getGuiaForestalDTO(ConstantesTest.guiaForestalSinFiscalizacionesSinVales);
			List<BoletaDepositoDTO> boletas = GuiaForestalTestUtil
					.getBoletasDepositoDTO(ConstantesTest.boletasDeDeposito);
			List<SubImporteDTO> subImportes = GuiaForestalTestUtil
					.getSubImportesDTO(ConstantesTest.subImportes);
			
			List<RangoDTO> rangos = new ArrayList<RangoDTO>();
			List<FiscalizacionDTO> fiscalizaciones = new ArrayList<FiscalizacionDTO>();
			Date fechaVencimiento = DateUtils.addDaysToDate(
					DateUtils.getTodayDate(), 30);
			gff.altaGuiaForestalBasica(guia, boletas, rangos, fechaVencimiento,
					fiscalizaciones, subImportes);
		} catch (NegocioException e) {
			e.printStackTrace();
		}

	}

	/*
	 * public void testAnularFiscalizaciones() { Long[] ids = new Long[]{new
	 * Long(23)};
	 * 
	 * try { ff.anularFiscalizaciones(ids); } catch (NegocioException e) {
	 * fail(e.toString()); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * public void testAltaFiscalizacion(){ FiscalizacionDTO fiscalizacion = new
	 * FiscalizacionDTO(); fiscalizacion.setLocalizacion(localizacion)
	 * MuestraDTO muestraDTO = new MuestraDTO();
	 * 
	 * ff.altaFiscalizacion(fiscalizacion, muestrasDTO) }
	 */
}
