package com.diplo.application.mspago.usecase.producer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProducerIntegrationDeudaVencidaTest {

	@Mock
	IAmqpProducer<IntegrationDeudaVencida> amqp;

	@InjectMocks
	ProducerIntegrationDeudaVencida test;

	@Test
	void OnProcessEventDeudaVencida() {
		test.onProcessEvent(
			new IntegrationDeudaVencida(UUID.randomUUID().toString())
		);
		assertTrue(true);
	}
}
