package com.diplo.mspago.event;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharekernel.core.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class PagoRealizado extends DomainEvent {

	private final UUID DeudaId;
	private final UUID ReservaId;
	private final Monto Total;
	private final Pago Pago;
	private final String Estado;

	public PagoRealizado(
		UUID deudaId,
		UUID reservaId,
		Monto total,
		com.diplo.mspago.model.deuda.Pago pago,
		String estado
	) {
		super(LocalDateTime.now());
		DeudaId = deudaId;
		ReservaId = reservaId;
		Total = total;
		Pago = pago;
		Estado = estado;
	}
}
