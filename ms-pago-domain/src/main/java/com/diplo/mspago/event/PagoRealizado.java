package com.diplo.mspago.event;

import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.event.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class PagoRealizado extends DomainEvent {

	private static final long serialVersionUID = 1L;
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
		super("PagoRealizado", LocalDateTime.now());
		DeudaId = deudaId;
		ReservaId = reservaId;
		Total = total;
		Pago = pago;
		Estado = estado;
	}

	public UUID getDeudaId() {
		return DeudaId;
	}

	public UUID getReservaId() {
		return ReservaId;
	}

	public Monto getTotal() {
		return Total;
	}

	public Pago getPago() {
		return Pago;
	}

	public String getEstado() {
		return Estado;
	}
}
