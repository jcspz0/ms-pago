package com.diplo.application.mspago.listenerevent;

import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.event.IListenerIntegrationTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaCreada;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerIntegrationConsumer
	implements IListenerIntegrationConsumer, ApplicationEventPublisherAware {

	@Autowired
	private ApplicationEventPublisher publisher = null;

	@Override
	public void consume(IntegrationEvent event) {
		System.out.println(
			"ListenerIntegrationConsumer, llego evento " + event.getMessage()
		);
		this.publisher.publishEvent(event.getMessage());
	}

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.publisher = applicationEventPublisher;
	}
}
