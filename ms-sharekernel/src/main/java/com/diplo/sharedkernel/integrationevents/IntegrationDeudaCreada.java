package com.diplo.sharedkernel.integrationevents;

import com.diplo.sharedkernel.event.IntegrationEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public class IntegrationDeudaCreada {

	private static final long serialVersionUID = 1L;
	private final UUID DeudaId;
	private final String Estado;
	private final UUID ReservaId;
	private final double Total;

	public IntegrationDeudaCreada(
		UUID deudaId,
		UUID reservaId,
		double total,
		String estado
	) {
		//super("DeudaCreada",LocalDateTime.now().toString());
		DeudaId = deudaId;
		ReservaId = reservaId;
		Total = total;
		Estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public double getTotal() {
		return Total;
	}
}
