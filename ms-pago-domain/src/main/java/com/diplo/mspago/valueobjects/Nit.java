package com.diplo.mspago.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class Nit extends ValueObject {

	private final int NroNit;

	public Nit(int nroNit) {
		super();
		NroNit = nroNit;
	}

	public int getNroNit() {
		return NroNit;
	}
}
