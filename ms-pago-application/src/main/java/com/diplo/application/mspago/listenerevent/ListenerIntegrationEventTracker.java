package com.diplo.application.mspago.listenerevent;

import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.IListenerIntegrationTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerIntegrationEventTracker
	implements IListenerIntegrationTracker, ApplicationEventPublisherAware {

	@Autowired
	private ApplicationEventPublisher publisher = null;

	@Override
	public void publish(IntegrationEvent event) {
		System.out.println(
			"ListenerIntegrationEventTracker, llego evento " + event.getMessage()
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
