package com.diplo.application.mspago.usecase.query.deuda.getFacturaById;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.FacturaDTO;
import com.diplo.application.mspago.mediator.request.IRequest;
import java.util.UUID;

public class GetFacturaByIdDeudaQuery implements IRequest<FacturaDTO> {

	private UUID Id;
	private int nit;
	private String detalle;

	public GetFacturaByIdDeudaQuery(UUID id, int nit, String detalle) {
		super();
		Id = id;
		this.nit = nit;
		this.detalle = detalle;
	}

	public GetFacturaByIdDeudaQuery() {}

	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
}
