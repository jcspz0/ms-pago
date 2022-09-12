package com.diplo.infraestructure.mspago.tracker;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.sharedkernel.event.IListenerIntegrationTracker;
import com.diplo.sharedkernel.event.IListenerTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.event.MessageEvent;
import java.util.ArrayList;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerEventTrackerInfra {

	ArrayList<MessageEvent> lista = new ArrayList<MessageEvent>();
	ArrayList<IntegrationEvent> integrationLista = new ArrayList<IntegrationEvent>();

	@EventListener
	public void publish(MessageEvent event) {
		//Deuda aux = (Deuda) event.getMessage();
		System.out.println(
			"Se recibio el evento en el listener tracker " + event.getMessage()
		);
		lista.add(event);
		System.out.println(
			"ListenerEventTracker->Cantidad en lista " + lista.size()
		);
	}

	@EventListener
	public void publish(IntegrationEvent event) {
		//Deuda aux = (Deuda) event.getMessage();
		System.out.println(
			"Se recibio el evento en el listener tracker " + event.getMessage()
		);
		integrationLista.add(event);
		System.out.println(
			"ListenerEventTracker->Cantidad en lista de integration " +
			integrationLista.size()
		);
	}

	public ArrayList<MessageEvent> getTrackersCargados() {
		return lista;
	}

	public void clearTracker() {
		lista = new ArrayList<MessageEvent>();
	}

	public ArrayList<IntegrationEvent> getIntegrationTrackersCargados() {
		return integrationLista;
	}

	public void clearIntegrationTracker() {
		integrationLista = new ArrayList<IntegrationEvent>();
	}
}
