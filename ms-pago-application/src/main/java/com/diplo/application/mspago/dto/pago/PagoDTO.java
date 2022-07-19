package com.diplo.application.mspago.dto.pago;

import com.diplo.mspago.model.deuda.Pago;
import java.time.LocalDateTime;
import java.util.UUID;

public class PagoDTO {

	private String pagoId;
	private double MontoPagado;
	private String detalle;
	private String deudaId;

	public PagoDTO() {
		super();
	}

	public PagoDTO(
		String pagoId,
		double montoPagado,
		String detalle,
		String deudaId
	) {
		super();
		this.pagoId = pagoId;
		MontoPagado = montoPagado;
		this.detalle = detalle;
		this.deudaId = deudaId;
	}

	public PagoDTO(double montoPagado, String detalle, String deudaId) {
		super();
		MontoPagado = montoPagado;
		this.detalle = detalle;
		this.deudaId = deudaId;
	}

	public PagoDTO(Pago aux) {
		super();
		this.pagoId = aux.getId().toString();
		this.deudaId = aux.getDeudaId().toString();
		MontoPagado = aux.getMontoPagado().getMonto();
		this.detalle = aux.getRecibo().getDetalle().getDetalle();
	}

	public String getPagoId() {
		return pagoId;
	}

	public void setPagoId(String pagoId) {
		this.pagoId = pagoId;
	}

	public double getMontoPagado() {
		return MontoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		MontoPagado = montoPagado;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getDeudaId() {
		return deudaId;
	}

	public void setDeudaId(String deudaId) {
		this.deudaId = deudaId;
	}
}
