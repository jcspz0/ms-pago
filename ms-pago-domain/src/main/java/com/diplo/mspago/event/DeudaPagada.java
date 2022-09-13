package com.diplo.mspago.event;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.event.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class DeudaPagada extends DomainEvent {

	private static final long serialVersionUID = 1L;
	private final UUID ReservaId;

	public DeudaPagada(UUID reservaId) {
		super("DeudaPagada", LocalDateTime.now());
		ReservaId = reservaId;
	}

	public UUID getReservaId() {
		return ReservaId;
	}
}
