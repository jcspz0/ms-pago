package com.diplo.application.mspago.usecase.producer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProducerIntegrationDeudaPagadaTest {

	@Mock
	IAmqpProducer<IntegrationDeudaPagada> amqp;

	@InjectMocks
	ProducerIntegrationDeudaPagada test;

	@Test
	void OnProcessEventDeudaPagada() {
		test.onProcessEvent(
			new IntegrationDeudaPagada(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString()
			)
		);
		assertTrue(true);
	}
}
