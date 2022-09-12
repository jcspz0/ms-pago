package com.diplo.application.mspago.usecase.command.deuda.vencerdeuda;

import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VencerDeudaCommand implements IRequest<UUID> {

	public VencerDeudaCommand() {}

	private String deudaId;

	public VencerDeudaCommand(String deudaId) {
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
