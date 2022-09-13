package com.diplo.sharedkernel.amqp;

import com.diplo.sharedkernel.event.IntegrationEvent;
import org.springframework.context.ApplicationEvent;

public interface IAmqpMessage {
	Object getMessage();
}
