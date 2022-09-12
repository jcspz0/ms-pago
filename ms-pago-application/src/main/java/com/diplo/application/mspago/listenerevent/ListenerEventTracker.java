package com.diplo.application.mspago.listenerevent;

import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.DomainMessage;
import com.diplo.sharedkernel.event.IListenerTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.event.MessageEvent;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerEventTracker
	implements IListenerTracker, ApplicationEventPublisherAware {

	@Autowired
	private ApplicationEventPublisher publisher = null;

	@Override
	public void publish(MessageEvent event) {
		System.out.println(
			"ListenerEventTracker, llego evento " + event.getMessage()
		);
		if (
			event.getAction().compareToIgnoreCase(Constant.EVENTOCONFIRMADO) == 0
		) {
			System.out.println(
				"ListenerEventTracker, llego evento de integracion" +
				event.getMessage()
			);
			this.publisher.publishEvent(
					new IntegrationMessage(event.getMessage(), event.getAction())
				);
			this.publisher.publishEvent(event.getMessage());
		} else {
			System.out.println(
				"ListenerEventTracker, llego evento de dominio " +
				event.getMessage()
			);
			this.publisher.publishEvent(
					new DomainMessage(event.getMessage(), event.getAction())
				);
		}
	}

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.publisher = applicationEventPublisher;
	}
}
