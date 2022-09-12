package com.diplo.mspago.event;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.event.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class DeudaCreada extends DomainEvent {

	private static final long serialVersionUID = 1L;
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
		super("DeudaCreada", LocalDateTime.now());
		DeudaId = deudaId;
		ReservaId = reservaId;
		Total = total;
		Estado = estado;
	}

	public UUID getDeudaId() {
		return DeudaId;
	}

	public String getEstado() {
		return Estado;
	}

	public UUID getReservaId() {
		return ReservaId;
	}

	public Monto getTotal() {
		return Total;
	}
}
