package com.diplo.application.mspago.usecase.command.deuda.vencerreserva;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.mediator.request.IRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VencerReservaCommand implements IRequest<UUID> {

	public VencerReservaCommand() {}

	private String deudaId;

	public VencerReservaCommand(String deudaId) {
		super();
		this.deudaId = deudaId;
	}

	public String getDeudaId() {
		return deudaId;
	}

	public void setDeudaId(String deudaId) {
		this.deudaId = deudaId;
	}
}
