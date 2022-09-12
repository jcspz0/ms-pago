package com.diplo.application.mspago.usecase.command.pago.confirmarpago;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfirmarPagoCommand implements IRequest<UUID> {

	public ConfirmarPagoCommand() {}

	private String reservaId;

	public ConfirmarPagoCommand(String reservaId) {
		super();
		this.reservaId = reservaId;
	}

	public String getReservaId() {
		return reservaId;
	}

	public void setReservaId(String reservaId) {
		this.reservaId = reservaId;
	}
}
