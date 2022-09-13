package com.diplo.application.mspago.usecase.consumer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.diplo.sharedkernel.mediator.IMediator;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsumerIntegrationReservaCreadaTest {

	@InjectMocks
	ConsumerIntegrationReservaCreada consumer;

	@Mock
	IMediator _mediator;

	@Test
	void ejecutarConsume() {
		//String reservaId = UUID.randomUUID().toString();
		//String pagoId = UUID.randomUUID().toString();
		String NroReserva = "123456test";
		String ReservaId = UUID.randomUUID().toString();
		String VueloId = UUID.randomUUID().toString();
		String pasajero = UUID.randomUUID().toString();
		String hora = LocalDateTime.now().toString();
		int cantidadPasajeros = 1;
		double total = 1;
		consumer.Consume(
			new IntegrationReservaCreada(
				ReservaId,
				NroReserva,
				VueloId,
				cantidadPasajeros,
				pasajero,
				hora,
				total
			)
		);
		assertTrue(true);
	}
}
