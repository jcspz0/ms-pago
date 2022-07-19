package com.diplo.mspago.model.reserva;

import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharekernel.core.AggregateRoot;
import java.util.UUID;

public class Reserva extends AggregateRoot<UUID> {

	private Monto Precio;

	public Monto getPrecio() {
		return Precio;
	}

	public Reserva(Monto precio) {
		super();
		Precio = precio;
	}

	public Reserva(UUID reservaID, Monto precio) {
		super();
		Id = reservaID;
		Precio = precio;
	}
}
