package com.diplo.mspago.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class Monto extends ValueObject {

	private final double Monto;

	public Monto(double monto) {
		super();
		Monto = monto;
	}

	public double getMonto() {
		return Monto;
	}
}
