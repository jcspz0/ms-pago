package com.diplo.mspago.event;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharekernel.core.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class VencerReserva extends DomainEvent {

	private final UUID ReservaId;

	public VencerReserva(UUID reservaId) {
		super(LocalDateTime.now());
		ReservaId = reservaId;
	}
}
