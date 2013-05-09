package ar.com.siif.utils;

import java.io.InputStream;
import java.util.List;

import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.GuiaForestalDTO;
import ar.com.siif.dto.SubImporteDTO;

import com.thoughtworks.xstream.XStream;

public abstract class GuiaForestalTestUtil {

	private static XStream xs;

	static {
		xs = new XStream();
	}

	public static GuiaForestalDTO getGuiaForestalDTO(
			String nombreArchivoCompleto) {
		XStream xStream = new XStream();
		InputStream is = GuiaForestalTestUtil.class
				.getResourceAsStream(nombreArchivoCompleto);
		GuiaForestalDTO usuario = (GuiaForestalDTO) xStream.fromXML(is);
		return usuario;
	}

	public static List<BoletaDepositoDTO> getBoletasDepositoDTO(
			String nombreArchivoCompleto) {
		XStream xStream = new XStream();
		InputStream is = GuiaForestalTestUtil.class
				.getResourceAsStream(nombreArchivoCompleto);
		List<BoletaDepositoDTO> boletas = (List<BoletaDepositoDTO>) xStream
				.fromXML(is);
		return boletas;
	}

	public static List<SubImporteDTO> getSubImportesDTO(
			String nombreArchivoCompleto) {
		XStream xStream = new XStream();
		InputStream is = GuiaForestalTestUtil.class
				.getResourceAsStream(nombreArchivoCompleto);
		List<SubImporteDTO> subImportes = (List<SubImporteDTO>) xStream
				.fromXML(is);
		return subImportes;
	}
}
