package com.diplo.mspago.factory.deuda;

import com.diplo.mspago.event.DeudaCreada;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaCreada;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DeudaFactory implements IDeudaFactory {

	@Override
	public Deuda Create(String deudaID, String reservaId, double total) {
		Deuda obj = new Deuda(
			UUID.fromString(deudaID),
			UUID.fromString(reservaId),
			new Monto(total)
		);

		DomainEvent domainEvent = new DeudaCreada(
			obj.getId(),
			obj.getReservaId(),
			obj.getTotal(),
			obj.getEstado()
		);
		IntegrationEvent integrationEvent = new IntegrationEvent(
			new IntegrationDeudaCreada(
				obj.getId(),
				obj.getReservaId(),
				obj.getTotal().getMonto(),
				obj.getEstado()
			),
			LocalDateTime.now().toString()
		);

		obj.AddDomainEvent(domainEvent);
		obj.AddIntegrationEvent(integrationEvent);

		return obj;
	}
}
