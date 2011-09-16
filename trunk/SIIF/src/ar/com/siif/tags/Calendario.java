package ar.com.siif.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Calendario extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5044520160261126248L;

	private String propiedad;

	private String formato;

	private String name;

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			imprimirEncabezado(out);
			imprimirCuerpo(out);
			imprimirPie(out);
		} catch (Exception ex) {
			throw new JspException("CalendaTag: " + ex.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

	private void imprimirEncabezado(JspWriter out) throws IOException {
		String salida;
		salida = "<script language='javascript' src='../../js/calendario.js'></script>";
		salida = salida + "<script language='javascript' src='../../js/funcUtiles.js'></script>";
		salida = salida + "<link href='../../css/calendarXP.css' rel='stylesheet' type='text/css'>";

		salida = salida + "&nbsp;<img align='top' border='0' height='21' id='img_" + getName()
				+ "' src='../../imagenes/calendar/calendar2.gif' width='17' onclick='javascript:c_"
				+ getName() + ".show(\"" + getPropiedad() + "\");'>";
		salida = salida + "<div class='xpcalCellLines' id='" + getName()
				+ "' style='width:220;visibility:hidden;position:absolute;'>";
		salida = salida + "<table width='100%' border='0' cellpadding='0' cellspacing='0'>";
		salida = salida + "<tr><td colspan='3' class='xpcalBorder'></td></tr>";
		salida = salida + "<tr><td width='1' class='xpcalBorder'></td>";
		salida = salida
				+ "<td><table border='0' cellpadding='0' cellspacing='0' width='100%'><tr><td>";
		salida = salida
				+ "<table border='0' cellpadding='3' cellspacing='0' width='100%'><tr><td align='left' class='xpcalHeader'>";
		salida = salida + "<span id='" + getName()
				+ "_HdrPrevYY'><a href='javascript:DotJ_doPrev(c_" + getName() + ",\"y\");'>";

		out.print(salida);

		salida = "<img align='middle' src='../../imagenes/calendar/_calprevyr.gif' border=0 width=17 height=17 title='Año anterior'></a></span>";
		salida = salida + "<span id='" + getName()
				+ "_HdrPrevMM'><a href='javascript:DotJ_doPrev(c_" + getName() + ",\"m\");'>";
		salida = salida
				+ "<img align='middle' src='../../imagenes/calendar/_calprevmm.gif' border=0 width=17 height=17 title='Mes anterior'></a></span>";
		salida = salida + "</td><td id='" + getName()
				+ "_hdr' class='xpcalHeader'>&nbsp;</td><td align='right' id='" + getName()
				+ "_HdrNext' class='xpcalHeader'>";
		salida = salida + "<span id='" + getName()
				+ "_HdrNextMM'><a href='javascript:DotJ_doNext(c_" + getName() + ",\"m\");'>";
		salida = salida
				+ "<img align='middle' src='../../imagenes/calendar/_calnextmm.gif' border=0 width=17 height=17 title='Mes siguiente'></a></span>";
		salida = salida + "<span id='" + getName()
				+ "_HdrNextYY'><a href='javascript:DotJ_doNext(c_" + getName() + ",\"y\");'>";
		salida = salida
				+ "<img align='middle' src='../../imagenes/calendar/_calnextyr.gif' border=0 width=17 height=17 title='Año siguiente'></a></span>";
		salida = salida + "</td></tr></table></td></tr>";

		out.print(salida);

		salida = "<tr><td class='xpcalSeperator'></td></tr><tr><td><table border='0' class='xpcalCellLines' cellspacing='1' cellpadding='3' width='100%'><tr>";
		char[] dias = { 'D', 'L', 'M', 'M', 'J', 'V', 'S' };
		for (int i = 0; i < 7; i++) {
			salida = salida + "<td class='xpcalDayLabels'>" + dias[i] + "</td>";
		}
		salida = salida + "</tr>";

		out.print(salida);
	}

	private void imprimirCuerpo(JspWriter out) throws IOException {
		String salida = "";

		for (int j = 0; j < 6; j++) {
			salida = "<tr>";
			for (int i = 1; i < 8; i++) {
				salida = salida + "<td id='" + getName() + "_TD_" + ((j * 7) + i)
						+ "' class='xpcalWeekday' valign='top' onclick='c_" + getName()
						+ ".selectDay(\"" + getName() + "_TD_" + ((j * 7) + i) + "\")'>&nbsp;</td>";
			}
			salida = salida + "</tr>";
			out.print(salida);
		}

		salida = "</table></td></tr><tr><td><table border='0' cellspacing='1' cellpadding='3' width='100%'><tr>";
		salida = salida
				+ "<td class='xpcalDayLabels' align='center'><table width='100%' border='0' cellpadding='0' cellspacing='0'><tr>";
		salida = salida
				+ "<td width='50%' align='center' class='xpcalDayLabels'><a class='xpcalDayLabels' href='javascript:DotJ_gotoToday(c_"
				+ getName() + ");'>Hoy</a></td>";
		// ver aca el cerrar
		salida = salida
				+ "<td width='50%' align='center' class='xpcalDayLabels'><a class='xpcalDayLabels' href='javascript:c_"
				+ getName() + ".show(\"" + getPropiedad() + "\");'>Cancelar</a></td>";
		salida = salida + "</tr></table></td></tr></table>";

		out.print(salida);
	}

	private void imprimirPie(JspWriter out) throws IOException {
		String salida = "";

		salida = "</td></tr></table><td width='1' class='xpcalBorder'></td></tr><tr>";
		salida = salida + "<td colspan='3' class='xpcalBorder'></td></tr></table></div>";
		salida = salida + "<script language='Javascript'>";
		salida = salida
				+ "var c_"
				+ getName()
				+ " = new DotJ_Calendar('"
				+ getName()
				+ "','"
				+ getFormato()
				+ "','popup',DotJ_today.getFullYear(),DotJ_today.getMonth() + 1,DotJ_today.getDate(),true,'xpcal',null,'',null,null,1);";
		salida = salida
				+ "c_"
				+ getName()
				+ ".months = new Array('n/a','Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic','');";
		salida = salida + "c_" + getName() + ".daySlots = DotJ_makeSlots();";
		salida = salida + "</script>";
		out.print(salida);

	}

	/**
	 * @return
	 */
	public String getFormato() {
		return formato;
	}

	/**
	 * @return
	 */
	public String getPropiedad() {
		return propiedad;
	}

	/**
	 * @param string
	 */
	public void setFormato(String string) {
		formato = string;
	}

	/**
	 * @param string
	 */
	public void setPropiedad(String string) {
		propiedad = string;
	}

	public void release() {
		propiedad = "";
		formato = "";
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

}