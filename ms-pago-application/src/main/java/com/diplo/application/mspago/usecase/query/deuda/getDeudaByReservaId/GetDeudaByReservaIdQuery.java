package com.diplo.application.mspago.usecase.query.deuda.getDeudaByReservaId;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.mediator.request.IRequest;
import java.util.UUID;

public class GetDeudaByReservaIdQuery implements IRequest<DeudaDTO> {

	private String Id;

	public GetDeudaByReservaIdQuery(String id) {
		super();
		Id = id;
	}

	public GetDeudaByReservaIdQuery() {}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
}
