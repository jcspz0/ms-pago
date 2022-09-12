package com.diplo.application.mspago.usecase.command.deuda.deudapagadarollback;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeudaPagadaRollbackCommand implements IRequest<UUID> {

	public DeudaPagadaRollbackCommand() {}

	private String reservaId;
	private String pagoId;

	public DeudaPagadaRollbackCommand(String reservaId, String pagoId) {
		super();
		this.reservaId = reservaId;
		this.pagoId = pagoId;
	}

	public String getReservaId() {
		return reservaId;
	}

	public void setReservaId(String reservaId) {
		this.reservaId = reservaId;
	}

	public String getPagoId() {
		return pagoId;
	}

	public void setPagoId(String pagoId) {
		this.pagoId = pagoId;
	}
}
