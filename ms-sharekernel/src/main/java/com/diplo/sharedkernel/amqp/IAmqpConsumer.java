package com.diplo.sharedkernel.amqp;

import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import org.springframework.context.ApplicationEvent;

//public interface IAmqpConsumer <T extends IntegrationEvent> {
public interface IAmqpConsumer<T> {
	void Consume(T message);
}
