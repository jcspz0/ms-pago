package com.diplo.application.mspago.usecase.command.pago.realizarpago;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RealizarPagoCommand implements IRequest<UUID> {

	public RealizarPagoCommand() {}

	private String deudaId;
	private String pagoId;
	private double totalPagado;
	private String detalle;

	public RealizarPagoCommand(String deudaId, double total, String detalle) {
		super();
		this.totalPagado = total;
		this.detalle = detalle;
		this.deudaId = deudaId;
	}

	public RealizarPagoCommand(
		String pagoId,
		String deudaId,
		double total,
		String detalle
	) {
		super();
		this.deudaId = deudaId;
		this.pagoId = pagoId;
		this.totalPagado = total;
		this.detalle = detalle;
	}

	public RealizarPagoCommand(double total, String detalle) {
		super();
		this.totalPagado = total;
		this.detalle = detalle;
	}

	public String getPagoId() {
		return pagoId;
	}

	public void setPagoId(String pagoId) {
		this.pagoId = pagoId;
	}

	public double getTotalPagado() {
		return totalPagado;
	}

	public void setTotalPagado(double total) {
		this.totalPagado = total;
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
