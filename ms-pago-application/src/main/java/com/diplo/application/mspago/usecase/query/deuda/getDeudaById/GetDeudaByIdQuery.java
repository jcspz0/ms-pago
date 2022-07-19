package com.diplo.application.mspago.usecase.query.deuda.getDeudaById;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.mediator.request.IRequest;
import java.util.UUID;

public class GetDeudaByIdQuery implements IRequest<DeudaDTO> {

	private UUID Id;

	public GetDeudaByIdQuery(UUID id) {
		super();
		Id = id;
	}

	public GetDeudaByIdQuery() {}

	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
	}
}
