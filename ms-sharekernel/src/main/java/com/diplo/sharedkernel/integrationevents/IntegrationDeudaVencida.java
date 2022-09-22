package com.diplo.sharedkernel.integrationevents;

import com.diplo.sharedkernel.event.IntegrationEvent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

//public class IntegrationReservaCreada extends IntegrationEvent  {
public class IntegrationDeudaVencida {

	/**
	 *
	 */

	private String reservaId;

	public IntegrationDeudaVencida() {
		super();
	}

	public IntegrationDeudaVencida(String reservaId) {
		//super("ReservaCreada",LocalDateTime.now().toString());
		this.reservaId = reservaId;
	}

	public String getReservaId() {
		return reservaId;
	}
}
