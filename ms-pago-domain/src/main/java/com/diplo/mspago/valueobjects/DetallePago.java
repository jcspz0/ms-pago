package com.diplo.mspago.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class DetallePago extends ValueObject {

	private final String Detalle;

	public DetallePago(String detalle) {
		super();
		Detalle = detalle;
	}

	public String getDetalle() {
		return Detalle;
	}
}
