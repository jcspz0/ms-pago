package com.diplo.infraestructure.mspago.entityframework;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.diplo.infraestructure.mspago.tracker.ListenerEventTrackerInfra;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.sharedkernel.event.IListenerIntegrationTracker;
import com.diplo.sharedkernel.event.IListenerTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.MessageEvent;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class UnitOfWorkTest {

	@InjectMocks
	UnitOfWork unitOfWork;

	@Mock
	private ListenerEventTrackerInfra tracker;

	@Mock
	private ApplicationEventPublisher publisherDomain;

	@Mock
	private IListenerTracker publisher;

	@Mock
	private IListenerIntegrationTracker publisherIntegration;

	@Mock
	IDeudaRepository _deudaRepository;

	@Test
	void Commit() {
		try {
			ArrayList<MessageEvent> lista = new ArrayList<MessageEvent>();
			lista.add(new MessageEvent("EventDomain", null));
			ArrayList<IntegrationEvent> integrationLista = new ArrayList<IntegrationEvent>();
			integrationLista.add(new IntegrationEvent("IntegrationEvent", null));
			when(tracker.getTrackersCargados()).thenReturn(lista);
			//when(tracker.getIntegrationTrackersCargados()).thenReturn(integrationLista);
			when(tracker.getTrackersCargados().size()).thenReturn(0);
			unitOfWork.Commit();
			assertNotNull(unitOfWork);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
