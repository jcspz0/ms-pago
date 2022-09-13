package com.diplo.application.mspago.listenerevent;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.event.IntegrationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class ListenerIntegrationConsumerTest {

	@InjectMocks
	ListenerIntegrationConsumer listener;

	@Mock
	ApplicationEventPublisher publisher;

	@Test
	void consume() {
		listener.consume(new IntegrationEvent(listener, null));
		assertTrue(true);
	}
}
