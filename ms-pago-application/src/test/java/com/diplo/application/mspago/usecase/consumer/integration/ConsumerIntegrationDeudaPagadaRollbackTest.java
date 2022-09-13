package com.diplo.application.mspago.usecase.consumer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.mediator.IMediator;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsumerIntegrationDeudaPagadaRollbackTest {

	@InjectMocks
	ConsumerIntegrationDeudaPagadaRollback consumer;

	@Mock
	IMediator _mediator;

	@Test
	void ejecutarConsume() {
		String reservaId = UUID.randomUUID().toString();
		String pagoId = UUID.randomUUID().toString();
		consumer.Consume(new IntegrationDeudaPagadaRollback(reservaId, pagoId));
		assertTrue(true);
	}
}
