package ar.com.siif.dto;

import java.util.Date;

import ar.com.siif.negocio.Entidad;

@Deprecated
public class BoletaDepositoDTO {

	private long idBoleta;

	private int numero;

	private String concepto;

	private String area;

	private String efectivoCheque;

	private double monto;

	private String fechaPago;

	private String fechaVencimiento;

	private GuiaForestalDTO guiaForestal;

	public BoletaDepositoDTO() {

		guiaForestal = new GuiaForestalDTO();
	}

	public long getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(long idBoleta) {
		this.idBoleta = idBoleta;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEfectivoCheque() {
		return efectivoCheque;
	}

	public void setEfectivoCheque(String efectivoCheque) {
		this.efectivoCheque = efectivoCheque;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public GuiaForestalDTO getGuiaForestal() {
		return guiaForestal;
	}

	public void setGuiaForestal(GuiaForestalDTO guiaForestal) {
		this.guiaForestal = guiaForestal;
	}

}