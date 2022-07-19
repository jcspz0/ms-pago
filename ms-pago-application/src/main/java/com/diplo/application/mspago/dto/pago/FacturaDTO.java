package com.diplo.application.mspago.dto.pago;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Factura;
import com.diplo.mspago.model.deuda.Pago;
import java.util.ArrayList;
import java.util.List;

public class FacturaDTO {

	private String detalle;
	private double total;
	private int nit;
	private String codigoFactura;

	public FacturaDTO() {
		super();
	}

	public FacturaDTO(
		String detalle,
		double total,
		int nit,
		String codigoFactura
	) {
		super();
		this.detalle = detalle;
		this.total = total;
		this.nit = nit;
		this.codigoFactura = codigoFactura;
	}

	public FacturaDTO(Factura aux) {
		super();
		this.detalle = aux.getDetalle().getDetalle();
		this.total = aux.getTotal().getMonto();
		this.nit = aux.getNit().getNroNit();
		this.codigoFactura = aux.getCodigoFactura().getCodigo();
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getCodigoFactura() {
		return codigoFactura;
	}

	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}
}
