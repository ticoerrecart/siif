package ar.com.siif.struts.utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.siif.dto.BoletaDepositoDTO;
import ar.com.siif.dto.FiscalizacionDTO;
import ar.com.siif.dto.MuestraDTO;
import ar.com.siif.dto.RangoDTO;
import ar.com.siif.dto.SubImporteDTO;
import ar.com.siif.dto.ValeTransporteDTO;
import ar.com.siif.negocio.Fiscalizacion;
import ar.com.siif.utils.Constantes;
import ar.com.siif.utils.DateUtils;
import ar.com.siif.utils.MathUtils;

/**
 * @author rdiaz
 * 
 *         Contiene metodos statics para la validacion de los objetos es para
 *         hacer validaciones usando AJAX.
 * 
 */
public abstract class Validator {

	public static String XML_HEADER = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?> ";

	public static StringBuffer abrirXML() {
		return new StringBuffer(XML_HEADER + "\n<datos>");
	}

	public static StringBuffer cerrarXML(StringBuffer xml) {
		return xml.append("\n</datos>");
	}

	public static StringBuffer addErrorXML(StringBuffer xml, String pMensaje) {
		xml.append("\n" + "<error>" + pMensaje + "</error>");
		return xml;
	}

	public static StringBuffer addIdJspXML(StringBuffer xml, String pMensaje) {
		xml.append("\n" + "<formId>" + pMensaje + "</formId>");
		return xml;
	}

	public static boolean requerido(String entrada, String label, StringBuffer pError) {
		if (entrada != null && !entrada.equals("")) {
			return true;
		}
		addErrorXML(pError, label + " es un dato obligatorio");

		return false;
	}

	public static boolean validarRequeridoSi(String entradaCondicion, String valorCondicion,
			String entrada, String label, StringBuffer pError) {

		if (entradaCondicion != null && entradaCondicion.equals(valorCondicion)) {
			if (entrada != null && !entrada.equals("")) {
				return true;
			}
			addErrorXML(pError, label + " es un dato obligatorio");

			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarComboRequerido(String valorEntradaDistinto, String entrada,
			String label, StringBuffer pError) {

		if (entrada == null || entrada.equals("")) {
			return true;
		}
		if (entrada.equals(valorEntradaDistinto)) {
			addErrorXML(pError, label + " es un dato obligatorio");
			return false;
		}
		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarComboRequeridoSi(String entradaCondicion, String valorCondicion,
			String valorEntradaDistinto, String entrada, String label, StringBuffer pError) {

		if (entradaCondicion != null && entradaCondicion.equals(valorCondicion)) {
			if (entrada != null && !entrada.equals(valorEntradaDistinto)) {
				return true;
			}
			addErrorXML(pError, label + " es un dato obligatorio");

			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarEnteroMayorQue(int numeroMinimo, String entrada, String label,
			StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			int entradaInt = Integer.parseInt(entrada);
			if (entradaInt <= numeroMinimo) {
				addErrorXML(pError,
						label + " debe ser un número mayor a " + Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, label + " debe ser un número entero");
			return false;
		}
		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarEnteroMenorQue(int numeroMaximo, String entrada, String label,
			StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			int entradaInt = Integer.parseInt(entrada);
			if (entradaInt >= numeroMaximo) {
				addErrorXML(pError,
						label + " debe ser un número menor a " + Integer.toString(numeroMaximo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, label + " debe ser un número entero");
			return false;
		}
		return true;
	}

	public static boolean validarLongMayorQue(int numeroMinimo, String entrada, String label,
			StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			long entradaLong = Long.parseLong(entrada);
			if (entradaLong <= numeroMinimo) {
				addErrorXML(pError,
						label + " debe ser un número mayor a " + Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, " debe ser un número entero");
			return false;
		}

		return true;
	}

	public static boolean validarDoubleMayorQue(int numeroMinimo, String entrada, String label,
			StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			double entradaDouble = Double.parseDouble(entrada);
			if (entradaDouble <= numeroMinimo) {
				addErrorXML(pError,
						label + " debe ser un número mayor a " + Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, " debe ser un número entero con decimales válido");
			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido chequea que el año se
	 * mayor que 1900 y menor que 2100
	 */
	public static boolean validarFechaValida(String pEntrada, String label, StringBuffer pError) {
		if (pEntrada == null || pEntrada.equals("")) {
			return true;
		}
		String entrada = new String(pEntrada);
		try {
			int positionBarra = entrada.indexOf('/');
			String dia = entrada.substring(0, positionBarra);
			entrada = entrada.substring(positionBarra + 1);
			positionBarra = entrada.indexOf('/');
			String mes = entrada.substring(0, positionBarra);
			entrada = entrada.substring(positionBarra + 1);
			String anio = entrada;
			int anioInt = Integer.parseInt(anio);
			int mesInt = Integer.parseInt(mes);
			int diaInt = Integer.parseInt(dia);
			if (diaInt > 31 || diaInt < 1) {
				addErrorXML(pError, label
						+ " invalida, el dia debe ser menor igual que 31 y mayor que 0");
				return false;
			} else if (mesInt > 12 || mesInt < 1) {
				addErrorXML(pError, label
						+ " invalida, el mes debe ser menor igual que 12 y mayor que 0");
				return false;
			} else if (anioInt > 2100 || anioInt < 1900) {
				addErrorXML(pError, label
						+ " invalida, el año debe ser menor igual que 2100 y mayor que 1900");
				return false;
			}

			return DateUtils.validateDate(pEntrada, true, "dd/MM/yyyy");

		} catch (Exception e) {
			addErrorXML(pError, label
					+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
			return false;
		}

	}

	/**
	 * Verifica que la fecha sea válida y anterior a fecha actual. si es nula o
	 * blanco devuelve true
	 * 
	 * @param pEntrada
	 * @param label
	 * @param pError
	 * @return boolean
	 */
	public static boolean validarFechaPasadaValida(String pEntrada, String label,
			StringBuffer pError) {
		boolean salida = true;
		if (pEntrada == null || pEntrada.equals("")) {
			return true;
		}
		if (!DateUtils.validateDate(pEntrada, true, "dd/MM/yyyy")) {
			addErrorXML(pError, label
					+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
			return false;
		}
		if (!DateUtils.validateDatePeriod(DateUtils.dateFromString(pEntrada, "dd/MM/yyyy"),
				GregorianCalendar.getInstance().getTime())) {
			addErrorXML(pError, label + " invalida, debe ser una fecha pasada.");
			salida = false;
		}

		return salida;
	}

	/**
	 * Verifica que la fecha sea válida y anterior a fecha actual. si es nula o
	 * blanco devuelve true
	 * 
	 * @param pEntrada
	 * @param label
	 * @param pError
	 * @return boolean
	 */
	public static boolean validarFechaMenorA(String pFechaDesde, String pFechaHasta,
			String labelFechaDesde, String labelFechaHasta, StringBuffer pError) {

		boolean salida = true;
		if (pFechaDesde == null || pFechaDesde.equals("")) {
			addErrorXML(pError, labelFechaDesde + " es un dato obligatorio");
			salida = false;
		} else {
			if (!DateUtils.validateDate(pFechaDesde, true, "dd/MM/yyyy")) {
				addErrorXML(pError, labelFechaDesde
						+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
				salida = false;
			}
		}

		if (pFechaHasta == null || pFechaHasta.equals("")) {
			addErrorXML(pError, labelFechaHasta + " es un dato obligatorio");
			salida = false;
		} else {
			if (!DateUtils.validateDate(pFechaHasta, true, "dd/MM/yyyy")) {
				addErrorXML(pError, labelFechaHasta
						+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
				salida = false;
			}
		}

		if (salida
				&& DateUtils.isBefore(DateUtils.dateFromString(pFechaHasta, "dd/MM/yyyy"),
						DateUtils.dateFromString(pFechaDesde, "dd/MM/yyyy"))) {
			addErrorXML(pError, "Fecha Desde debe ser menor a Fecha Hasta");
			salida = false;
		}

		return salida;
	}

	public static boolean validarSN(String tipoLote) {
		return (tipoLote.equals("S") || tipoLote.equals("N"));
	}

	public static boolean validarEmail(String email, String label, StringBuffer pError) {
		if (email == null || email.equals("")) {
			return true;
		}
		// Input the string for validation
		// String email = "xyz@.com";
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given string with the pattern
		Matcher m = p.matcher(email);

		// check whether match is found
		boolean matchFound = m.matches();

		StringTokenizer st = new StringTokenizer(email, ".");
		String lastToken = null;
		while (st.hasMoreTokens()) {
			lastToken = st.nextToken();
		}

		if (matchFound && lastToken.length() >= 2 && email.length() - 1 != lastToken.length()) {
			// validate the country code
			return true;
		}

		addErrorXML(pError, label + " no es un e-mail válido");
		return false;

	}

	public static boolean validarAlfaNumerico(String valor, String label, StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("[\\w]*");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un AlfaNumérico válido");
		return false;
	}

	public static boolean validarNumerico(String valor, String label, StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("(?=[^A-Za-z]+$).*[0-9].*");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un Numérico válido");
		return false;
	}

	public static boolean validarCaracter(String valor, String label, StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("[a-zA-Z]");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un Caracter válido");
		return false;
	}

	public static boolean validarLongitudHasta(String valor, int longitud, String label,
			StringBuffer pError) {
		if ((valor == null) || valor.length() <= longitud) {
			return true;
		}
		addErrorXML(pError, label + " no es válido, se permite hasta " + longitud + " posiciones");
		return false;
	}

	/**
	 * true si es null , string vacío o letras, si el valor longitudMaxima es 0,
	 * no toma cuenta el largo del string.
	 */
	public static boolean validarLetras(String valor, int longitudMaxima, String label,
			StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		if ((valor.matches(" *[a-zA-Z]* *"))
				&& (longitudMaxima == 0 || valor.length() <= longitudMaxima)) {
			return true;
		}
		addErrorXML(pError, label + " no es válido, solo letras"
				+ (longitudMaxima != 0 ? " hasta " + longitudMaxima + " posiciones" : ""));
		return false;
	}

	public static boolean validarLetras(String valor, String label, StringBuffer pError) {
		return validarLetras(valor, 0, label, pError);
	}

	private static String mensajeErrorDiametroLargo(String valorIngresado, String valorMin,
			String valorMax, String msje) {
		return "El valor " + valorIngresado + " ingresado en el " + msje
				+ " no es un valor dentro del rango [" + valorMin + "," + valorMax + "]";
	}

	private static boolean validarDiametro(StringBuffer pError, Double diametro, Integer min,
			Integer max, String label) {
		if (diametro < min || diametro > max) {
			addErrorXML(
					pError,
					mensajeErrorDiametroLargo(diametro.toString(), min.toString(), max.toString(),
							label));
			return false;
		}
		return true;
	}

	private static boolean validarLargo(StringBuffer pError, Double diametro, Double min,
			Double max, String label) {
		if (diametro < min || diametro > max) {
			addErrorXML(
					pError,
					mensajeErrorDiametroLargo(diametro.toString(), min.toString(), max.toString(),
							label));
			return false;
		}
		return true;
	}

	private static boolean validarRangoMuestras(List<MuestraDTO> muestras, Long idTipoProducto,
			StringBuffer pError) {
		boolean ok = true;
		for (MuestraDTO muestra : muestras) {
			if (muestra != null) {
				String tipoProd = "";
				if (idTipoProducto == 1) {
					tipoProd = "ROLLIZO";
					ok = ok
							&& validarDiametro(pError, muestra.getDiametro1(),
									Constantes.minDiametroRollizo, Constantes.maxDiametroRollizo,
									"Diámetro 1 del " + tipoProd);

					ok = ok
							&& validarLargo(pError, muestra.getLargo(), Constantes.minLargoRollizo,
									Constantes.maxLargoRollizo, "Largo del " + tipoProd);

				}
				if (idTipoProducto == 2) {
					tipoProd = "FUSTE";
					ok = ok
							&& validarDiametro(pError, muestra.getDiametro1(),
									Constantes.minDiametroFuste, Constantes.maxDiametroFuste,
									"Diámetro 1 del " + tipoProd);

					ok = ok
							&& validarDiametro(pError, muestra.getDiametro2(),
									Constantes.minDiametroFuste, Constantes.maxDiametroFuste,
									"Diámetro 2 del " + tipoProd);

					ok = ok
							&& validarLargo(pError, muestra.getLargo(), Constantes.minLargoFuste,
									Constantes.maxLargoFuste, "Largo del " + tipoProd);

				}
				if (idTipoProducto == 4) {
					tipoProd = "POSTE";
					ok = ok
							&& validarDiametro(pError, muestra.getDiametro1(),
									Constantes.minDiametroPoste, Constantes.maxDiametroPoste,
									"Diámetro 1 del " + tipoProd);

					ok = ok
							&& validarLargo(pError, muestra.getLargo(), Constantes.minLargoPoste,
									Constantes.maxLargoPoste, "Largo del " + tipoProd);

				}
				if (idTipoProducto == 5) {
					tipoProd = "TRINEO";
					ok = ok
							&& validarDiametro(pError, muestra.getDiametro1(),
									Constantes.minDiametroTrineo, Constantes.maxDiametroTrineo,
									"Diámetro 1 del " + tipoProd);

					ok = ok
							&& validarDiametro(pError, muestra.getDiametro2(),
									Constantes.minDiametroTrineo, Constantes.maxDiametroTrineo,
									"Diámetro 2 del " + tipoProd);

					ok = ok
							&& validarLargo(pError, muestra.getLargo(), Constantes.minLargoTrineo,
									Constantes.maxLargoTrineo, "Largo del " + tipoProd);

				}

			}
		}

		return ok;
	}

	/*
	 * 
		<option value="1">Rollizos</option>
		<option value="2">Fustes</option>
		<option value="3">Leña</option>
		<option value="4">Postes</option>
		<option value="5">Trineos</option>
		a. ROLLIZOS: LARGO: 2 a m7 y diámetro: 10 a 160cm
		b. Fustes: Largo 5 a 18m y diámetro : 10 a 160cm
		c. Postes: Largo 1.5 a 3m y diamtetro: 10 a 40cm		
	 */
	public static boolean validarMuestras(List<MuestraDTO> muestras, Long idTipoProducto,
			StringBuffer pError) {

		//int cantNulos = 0;

		if (muestras.size() == 0) {
			addErrorXML(pError, "Cantidad de Muestras debe ser un numero mayor a 0");
			return false;
		}

		for (MuestraDTO muestra : muestras) {
			if (muestra != null) {
				if (idTipoProducto == 2 || idTipoProducto == 5) {
					if (muestra.getLargo() == 0.0 || muestra.getDiametro1() == 0.0
							|| muestra.getDiametro2() == 0.0) {
						addErrorXML(pError, "Faltan datos de Largo y/o Diametro en las Muestras");
						return false;
					}
				} else {
					if (idTipoProducto == 1 || idTipoProducto == 4) {
						if (muestra.getLargo() == 0.0 || muestra.getDiametro1() == 0.0) {
							addErrorXML(pError,
									"Faltan datos de Largo y/o Diametro en las Muestras");
							return false;
						}
					}
				}
			}
		}

		return validarRangoMuestras(muestras, idTipoProducto, pError);
	}

	public static boolean validarBoletasDeposito(List<BoletaDepositoDTO> boletas,
			double montoTotal, StringBuffer pError) {

		if (boletas.size() == 0) {
			addErrorXML(pError, "La Cantidad de Boletas de Deposito debe ser un numero mayor a 0");
			return false;
		}
		double montoSumaBoletas = 0;
		for (BoletaDepositoDTO boleta : boletas) {
			montoSumaBoletas = montoSumaBoletas + boleta.getMonto();
			if (boleta.getNumero() <= 0) {
				addErrorXML(pError, "Faltan datos en el Nro de Boleta de Deposito");
				return false;
			}

			if (boleta.getConcepto() == null || boleta.getConcepto().equals("")) {
				addErrorXML(pError, "Faltan datos en el Concepto de la Boleta de Deposito");
				return false;
			}

			if (boleta.getArea() == null || boleta.getArea().equals("")) {
				addErrorXML(pError, "Faltan datos en el Area de la Boleta de Deposito");
				return false;
			}

			if (boleta.getMonto() <= 0.0) {
				addErrorXML(pError, "Faltan datos en el monto de la Boleta de Deposito");
				return false;
			}

		}

		montoSumaBoletas = MathUtils.round(montoSumaBoletas, 2);

		if (montoSumaBoletas != montoTotal) {
			addErrorXML(pError,
					"La suma de los montos de las Boletas de Deposito debe ser igual al Importe Total");
			return false;
		}
		return true;
	}

	public static boolean validarValesTransporte(List<ValeTransporteDTO> vales, StringBuffer pError) {

		if (vales.size() == 0) {
			addErrorXML(pError, "La Cantidad de Vales de Transporte debe ser un numero mayor a 0");
			return false;
		}
		for (ValeTransporteDTO vale : vales) {

			if (vale.getNumero() <= 0 || vale.getOrigen() == null || vale.getOrigen().equals("")
					|| vale.getDestino() == null || vale.getDestino().equals("")
					|| vale.getVehiculo() == null || vale.getVehiculo().equals("")
					|| vale.getMarca() == null || vale.getMarca().equals("")
					|| vale.getDominio() == null || vale.getDominio().equals("")
					|| vale.getProducto() == null || vale.getProducto().equals("")
					|| (vale.getNroPiezas() <= 0 && vale.getCantidadMts() <= 0.0)
					|| vale.getEspecie() == null || vale.getEspecie().equals("")) {
				addErrorXML(pError, "Faltan datos en los Vales de Transporte");
				return false;
			}
		}
		return true;
	}

	public static boolean validarTipoProductoAltaGFB(Long idTipoProductoGuia,
			Long idTipoProductoFiscalizacion, StringBuffer pError) {

		if (idTipoProductoGuia.longValue() != idTipoProductoFiscalizacion.longValue()) {
			addErrorXML(pError, "El Tipo de Producto debe ser igual al de las Fiscalizaciones");
			return false;
		}
		return true;
	}

	public static boolean validarRangos(List<RangoDTO> rangos,
			List<ValeTransporteDTO> valesDeTransporte, StringBuffer pError) {
		boolean ok = validarRangos(rangos, pError);
		boolean okExisteVale = true;
		if (ok) {
			for (RangoDTO rango : rangos) {
				for (ValeTransporteDTO valeTransporteDTO : valesDeTransporte) {
					okExisteVale = valeTransporteDTO.getNumero() < rango.getDesde()
							|| valeTransporteDTO.getNumero() > rango.getHasta();
					if (!okExisteVale) {
						Validator
								.addErrorXML(
										pError,
										"El rango ["
												+ rango.getDesde()
												+ ","
												+ rango.getHasta()
												+ "] que está intentando ingresar se superpone con el Vale de Transporte nro. "
												+ valeTransporteDTO.getNumero());
						break;
					}
				}
				if (!okExisteVale) {
					break;
				}
			}
		}

		return ok && okExisteVale;
	}

	public static boolean validarRangos(List<RangoDTO> rangos, StringBuffer pError) {
		String titulo = "en los Vales de Transporte";
		/*if (rangos.size() == 0) {
			addErrorXML(pError, "La Cantidad de Rangos " + titulo + " debe ser un numero mayor a 0");
			return false;
		}*/
		for (RangoDTO rango : rangos) {
			if (rango.getDesde() <= 0 || rango.getHasta() <= 0) {
				addErrorXML(pError, "Los valores Desde y Hasta " + titulo
						+ " deben ser enteros positivos");
				return false;
			}
			if (rango.getDesde() > rango.getHasta()) {
				addErrorXML(pError, "El valor Desde " + titulo
						+ " no puede ser mayor que el valor Hasta");
				return false;
			}
		}
		List<Integer> lista = new ArrayList<Integer>();
		for (RangoDTO rango : rangos) {
			lista.add(rango.getDesde());
			lista.add(rango.getHasta() + 1);
		}
		Integer i = new Integer(-1);
		for (Integer integer : lista) {
			if (i > integer) {
				addErrorXML(pError, "Los valores de los rangos " + titulo + " estan superpuestos");
				return false;
			} else {
				i = integer;
			}
		}
		return true;
	}

	public static boolean validarSubImportes(List<SubImporteDTO> listaSubImportes,
			List<FiscalizacionDTO> listaFiscalizaciones, String tipoTerreno, StringBuffer pError) {

		HashMap<Long, SubImporteDTO> hashProductosFiscalizados = new HashMap<Long, SubImporteDTO>();
		Set<SubImporteDTO> listaIdTipoProducto = new TreeSet<SubImporteDTO>();

		for (SubImporteDTO subImporteDTO : listaSubImportes) {

			hashProductosFiscalizados.put(subImporteDTO.getTipoProducto().getId(), subImporteDTO);

			if (listaIdTipoProducto.contains(subImporteDTO)) {
				addErrorXML(pError, "Debe especificar un solo subImporte por cada Tipo de Producto");
				return false;
			}
			if (subImporteDTO.getEstado() == null || subImporteDTO.getEstado().equals("")) {
				addErrorXML(pError, "Faltan especificar datos en el Estado del Producto Forestal");
				return false;
			}
			if (subImporteDTO.getEspecie() == null || subImporteDTO.getEspecie().trim().equals("")) {
				addErrorXML(pError, "Faltan especificar datos en la Especie del Producto Forestal");
				return false;
			}
			if (subImporteDTO.getCantidadMts() <= 0.0) {
				addErrorXML(pError,
						"Faltan especificar datos en la cantidad de Metros del Producto Forestal");
				return false;
			}
			if (subImporteDTO.getValorAforos() <= 0.0) {
				addErrorXML(pError,
						"Faltan especificar datos en el Valor del Aforo del Producto Forestal");
				return false;
			}
			if (!tipoTerreno.equals("Privado") && subImporteDTO.getImporte() <= 0.0) {
				addErrorXML(pError, "Faltan especificar datos en el Importe del Producto Forestal");
				return false;
			}
			listaIdTipoProducto.add(subImporteDTO);
		}

		for (FiscalizacionDTO fiscalizacionDTO : listaFiscalizaciones) {
			if (fiscalizacionDTO.getId() != null) {
				SubImporteDTO subImporte = hashProductosFiscalizados.get(fiscalizacionDTO
						.getTipoProducto().getId());
				if (subImporte == null) {
					addErrorXML(pError,
							"Debe agregar todas las fiscalizaciones al calculo del importe");
					return false;
				}
			}
		}

		return true;
	}

	public static boolean validarRodalRequerido(Long idRodal, StringBuffer pError) {

		if (idRodal == null || idRodal.longValue() <= 0) {
			addErrorXML(pError, "Rodal es un dato obligatorio");
			return false;
		}
		return true;
	}

	public static boolean validarFormatoPeriodo(String periodo, StringBuffer pError) {
		try {
			String[] strArray = periodo.split("-");
			int n = Integer.parseInt(strArray[0]);
			int n2 = Integer.parseInt(strArray[1]);
			if (n + 1 != n2) {
				addErrorXML(pError, "Los Años del periodo deben ser consecutivos");
				return false;
			}
			return true;
		} catch (Exception e) {
			addErrorXML(pError, "El formato del periodo deben ser AAAA-AAAA. Ej 2011-2012");
			return false;
		}
	}

	public static boolean validarM3ValesMenorQueM3Fiscalizaciones(double m3Vales,
			double m3Fiscalizaciones, StringBuffer pError) {
		if (m3Vales > m3Fiscalizaciones + 1) {
			addErrorXML(
					pError,
					"La suma de los M3 de los vales de transporte deben ser menores que los M3 declarados en las fiscalizaciones");
			return false;
		}
		return true;
	}

	public static boolean validarFiscalizacionExistenteParaVale(
			List<Fiscalizacion> fiscalizaciones, String tipoProducto, StringBuffer pError) {
		for (Fiscalizacion fiscalizacion : fiscalizaciones) {
			if (fiscalizacion.getTipoProducto().getNombre().equalsIgnoreCase(tipoProducto)) {
				return true;
			}
		}
		addErrorXML(pError,
				"Debe existir al menos una Fiscalizacion para el tipo de Proucto del Vale de Transporte");
		return false;
	}

	public static boolean validarCuit(String strCuit, StringBuffer pError) {
		String strPrefijo;
		String strNumero;
		int valDigCuit;
		int valDigConstant;
		String strConstant = "54327654321";
		int valResult = 0;

		if (!strCuit.equals("")) {
			try {
				if (strCuit.length() != 11) {
					addErrorXML(pError, "La Cuit tiene una longitud inválida");
					return false;
				}

				strPrefijo = strCuit.substring(0, 2).trim();
				strNumero = strCuit.substring(2, 10).trim();
				// Que no sea 0 el cuerpo
				if (Integer.parseInt(strNumero) == 0) {
					addErrorXML(pError, "Nro de Cuit inválido");
					return false;
				}
				// Validacion prefijo
				if (!(strPrefijo.equals("20")) & !(strPrefijo.equals("23"))
						& !(strPrefijo.equals("24")) & !(strPrefijo.equals("27"))
						& !(strPrefijo.equals("30")) & !(strPrefijo.equals("33"))
						& !(strPrefijo.equals("34"))) {
					addErrorXML(pError, "Prefijo de Cuit inválido");
					return false;
				}
				// Validacion digito verificador
				for (int valPos = 0; valPos < 11; valPos++) {
					valDigCuit = Integer.parseInt(strCuit.substring(valPos, valPos + 1));
					valDigConstant = Integer.parseInt(strConstant.substring(valPos, valPos + 1));
					valResult = valResult + (valDigCuit * valDigConstant);
				}
				if ((valResult % 11) != 0) {
					addErrorXML(pError, "Dígito verificador de Cuit inválido");
					return false;
				}
			} catch (Exception e) {
				addErrorXML(pError, "Cuit inválido");
				return false;
			}
		} // if generico
		return true;
	}

	public static boolean validarVolumenExportacionCertificadoOrigen(
			double volumenTotalTipoProducto, double volumenMaximoPermitido, StringBuffer pError) {
		if (volumenTotalTipoProducto > volumenMaximoPermitido) {
			addErrorXML(pError,
					"El volúmen total debe ser menor al volúmen máximo permitido para exportar");
			return false;
		}
		return true;
	}

	public static boolean validarDeudaCertificadoOrigen(boolean tieneDeuda, String radioDeuda,
			StringBuffer pError) {
		if (tieneDeuda && radioDeuda.equals("N")) {
			addErrorXML(pError,
					"El Productor tiene deudas con la Dirección General de Bosques en concepto de aforo");
			return false;
		}
		return true;
	}
}