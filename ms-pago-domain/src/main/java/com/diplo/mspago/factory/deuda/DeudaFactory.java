package com.diplo.mspago.factory.deuda;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;

public class DeudaFactory implements IDeudaFactory {

	@Override
	public Deuda Create(String deudaID, String reservaId, double total) {
		return new Deuda(
			UUID.fromString(deudaID),
			UUID.fromString(reservaId),
			new Monto(total)
		);
	}
}
