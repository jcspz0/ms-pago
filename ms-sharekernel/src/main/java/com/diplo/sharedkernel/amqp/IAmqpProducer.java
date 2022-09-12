package com.diplo.sharedkernel.amqp;

import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;

public interface IAmqpProducer<T> {
	void sendMessage(
		IAmqpMessage message,
		String exchange,
		String queue,
		String routingkey
	);
}
