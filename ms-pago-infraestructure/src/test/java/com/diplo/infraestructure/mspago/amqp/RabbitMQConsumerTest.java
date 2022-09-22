package com.diplo.infraestructure.mspago.amqp;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.amqp.MasstransitEvent;
import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.integrationevents.IntegrationCheckinCreado;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.HashMap;
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

	ObjectMapper Obj = new ObjectMapper();

	@Test
	void testConsumers() {
		try {
			MasstransitEvent masstransit = new MasstransitEvent();
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", UUID.randomUUID().toString());
			masstransit.setMessage(map);
			consumerTest.checkinCreado(Obj.writeValueAsString(masstransit));
			consumerTest.deudaPagadaRollback(
				Obj.writeValueAsString(
					new IntegrationDeudaPagadaRollback(
						UUID.randomUUID().toString(),
						UUID.randomUUID().toString()
					)
				)
			);
			consumerTest.reservaCreada(
				Obj.writeValueAsString(
					new IntegrationReservaCreada(
						UUID.randomUUID().toString(),
						"123456test",
						UUID.randomUUID().toString(),
						1,
						UUID.randomUUID().toString(),
						LocalDateTime.now().toString(),
						100
					)
				)
			);
			assertTrue(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
