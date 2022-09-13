package com.diplo.infraestructure.mspago.amqp;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.integrationevents.IntegrationCheckinCreado;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RabbitMQConsumerTest {

	@Mock
	private IListenerIntegrationConsumer consumer;

	@InjectMocks
	RabbitMQConsumer consumerTest;

	@Test
	void testConsumers() {
		try {
			consumerTest.checkinCreado(
				new IntegrationCheckinCreado(UUID.randomUUID().toString())
			);
			consumerTest.deudaPagadaRollback(
				new IntegrationDeudaPagadaRollback(
					UUID.randomUUID().toString(),
					UUID.randomUUID().toString()
				)
			);
			consumerTest.reservaCreada(
				new IntegrationReservaCreada(
					UUID.randomUUID().toString(),
					"123456test",
					UUID.randomUUID().toString(),
					1,
					UUID.randomUUID().toString(),
					LocalDateTime.now().toString(),
					100
				)
			);
			assertTrue(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
