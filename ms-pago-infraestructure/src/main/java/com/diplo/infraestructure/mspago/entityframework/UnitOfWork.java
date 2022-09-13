package com.diplo.infraestructure.mspago.entityframework;

import com.diplo.application.mspago.listenerevent.ListenerIntegrationEventTracker;
import com.diplo.infraestructure.mspago.tracker.ListenerEventTrackerInfra;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.event.DomainMessage;
import com.diplo.sharedkernel.event.IListenerIntegrationTracker;
import com.diplo.sharedkernel.event.IListenerTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.event.MessageEvent;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class UnitOfWork implements IUnitOfWork, ApplicationEventPublisherAware {

	@Autowired
	private ListenerEventTrackerInfra tracker;

	@Autowired
	private ApplicationEventPublisher publisherDomain = null;

	@Autowired
	private IListenerTracker publisher;

	@Autowired
	private IListenerIntegrationTracker publisherIntegration;

	@Autowired
	IDeudaRepository _deudaRepository;

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.publisherDomain = applicationEventPublisher;
	}

	@Override
	public Future<Void> Commit() {
		ArrayList<MessageEvent> TrackerDomainToExecute = new ArrayList<>(
			tracker.getTrackersCargados()
		);

		tracker.clearTracker();

		for (MessageEvent message : TrackerDomainToExecute) {
			if (message.getMessage() instanceof DomainMessage) {
				Object domainEvent = message.getMessage();
				//this.publisherDomain.publishEvent(new DomainMessage(domainEvent, message.getAction()));
				publisher.publish(
					new MessageEvent(domainEvent, message.getAction())
				);
			}
		}

		while (tracker.getTrackersCargados().size() > 0) {
			System.out.println(
				"Se ha capturado nuevos eventos de Dominio, cantidad=" +
				tracker.getTrackersCargados().size()
			);
			TrackerDomainToExecute =
				new ArrayList<>(tracker.getTrackersCargados());
			//TrackerIntegrationToExecute.addAll(TrackerDomainToExecute);
			tracker.clearTracker();

			for (MessageEvent message : TrackerDomainToExecute) {
				if (message.getMessage() instanceof DomainMessage) {
					Object domainEvent = message.getMessage();
					//this.publisherDomain.publishEvent(new DomainMessage(domainEvent, message.getAction()));
					publisher.publish(
						new MessageEvent(domainEvent, message.getAction())
					);
				}
			}
		}

		_deudaRepository.commit();

		ArrayList<IntegrationEvent> TrackerIntegrationToExecute = new ArrayList<>(
			tracker.getIntegrationTrackersCargados()
		);
		tracker.clearIntegrationTracker();

		for (IntegrationEvent message : TrackerIntegrationToExecute) {
			System.out.println("evento a propagar de integracion en unitofwork");
			//this.publisherIntegration.publishEvent(new IntegrationMessage(integrationEvent, message.getAction()));
			publisherIntegration.publish(
				new IntegrationEvent(
					message.getMessage(),
					Constant.EVENTOCONFIRMADO
				)
			);
		}

		return CompletableFuture.completedFuture(null);
	}
}
