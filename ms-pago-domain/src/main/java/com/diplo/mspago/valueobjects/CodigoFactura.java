package com.diplo.mspago.valueobjects;

import com.diplo.sharekernel.core.ValueObject;

public class CodigoFactura extends ValueObject {

	private final String codigo;

	public CodigoFactura(String codigo) {
		super();
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
}
