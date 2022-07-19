package com.diplo.mspago.event;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharekernel.core.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class DeudaCreada extends DomainEvent {

	private final UUID DeudaId;
	private final String Estado;
	private final UUID ReservaId;
	private final Monto Total;

	public DeudaCreada(
		UUID deudaId,
		UUID reservaId,
		Monto total,
		String estado
	) {
		super(LocalDateTime.now());
		DeudaId = deudaId;
		ReservaId = reservaId;
		Total = total;
		Estado = estado;
	}
}
